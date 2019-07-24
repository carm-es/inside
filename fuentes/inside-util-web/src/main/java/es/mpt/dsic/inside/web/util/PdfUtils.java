/*
 * Copyright (C) 2016 MINHAP, Gobierno de España This program is licensed and may be used, modified
 * and redistributed under the terms of the European Public License (EUPL), either version 1.1 or
 * (at your option) any later version as soon as they are approved by the European Commission.
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and more details. You
 * should have received a copy of the EUPL1.1 license along with this program; if not, you may find
 * it at http://joinup.ec.europa.eu/software/page/eupl/licence-eupl
 */

package es.mpt.dsic.inside.web.util;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TimeZone;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;
import es.gob.utilidades.cliente.rec.model.JustificanteType;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoAuditoriaAcceso;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteToken;
import es.mpt.dsic.inside.model.objetos.usuario.ObjetoInsideUsuario;
import es.mpt.dsic.inside.service.util.InsideWSUtils;
import es.mpt.dsic.inside.service.util.WebConstants;
import es.mpt.dsic.inside.xml.inside.MetadatoAdicional;
import es.mpt.dsic.inside.xml.inside.ws.remisionEnLaNube.PeticionSolicitarAccesoExpedienteType;



// TODO: Auto-generated Javadoc
/**
 * The Class PdfUtils.
 */
@PropertySource("file:${config.path}/clientWSRegistroElectronico.properties")
@Component("pdfUtils")
public class PdfUtils {

  /** The logger. */
  private static Logger logger = Logger.getLogger(PdfUtils.class);

  /** The Constant DEFAULT_WIDTH_PERCENTAGE. */
  private static final float DEFAULT_WIDTH_PERCENTAGE = 100f;

  /** The Constant DEFAULT_BACKGROUND_TITLE. */
  private static final Color DEFAULT_BACKGROUND_TITLE = new Color(200, 200, 200);

  /** The Constant DEFAULT_FONT_TITLE. */
  private static final Font DEFAULT_FONT_TITLE = new Font(Font.NORMAL, 12, Font.BOLD, null);

  /** The Constant DEFAULT_ALIGNMENT_TITLE. */
  private static final int DEFAULT_ALIGNMENT_TITLE = Element.ALIGN_CENTER;

  /** The Constant DEFAULT_FONT_BOLD. */
  public static final Font DEFAULT_FONT_BOLD = new Font(Font.NORMAL, 8, Font.BOLD, null);

  /** The Constant DEFAULT_FONT_BASE. */
  public static final Font DEFAULT_FONT_BASE = new Font(Font.NORMAL, 12, Font.BOLD, null);

  /** The Constant DEFAULT_FONT_BASE_INDEX. */
  public static final Font DEFAULT_FONT_BASE_INDEX = new Font(Font.NORMAL, 8, Font.BOLD, null);

  /** The Constant WIDTH_TABLE_HOR. */
  private static final float WIDTH_TABLE_HOR = 727f;

  /** The Constant relativeWidthsHor. */
  private static final float[] relativeWidthsHor = {1f};


  /** The context. */
  @Autowired(required = true)
  private ApplicationContext context;

  @Autowired
  private MessageSource messageSource;

  /** The rec utils. */
  @Autowired
  private RecUtils recUtils;

  /** The registrar. */
  @Value("${registrar.acta}")
  private Boolean registrar;



  /**
   * Creates the pdf received expedient.
   *
   * @param expedient the expedient
   * @param expediente the expediente
   * @param archivo the archivo
   * @return the files object
   * @throws UtilException the util exception
   */
  public Map<String, byte[]> createPdfReceivedExpedient(ObjetoInsideUsuario user,
      ObjetoExpedienteToken token, String basePath) throws Exception {

    byte[] justificante = null;
    byte[] pdfResult = null;
    Map<String, byte[]> mapaActaANDJustificante = new HashMap();
    try {

      Map<String, String> data = new HashMap<String, String>();
      data.put("fechaIngreso", InsideWSUtils.dateToString(new Date(), "dd/MM/yyyy HH:mm:ss"));
      data.put("identificador", token.getIdentificador());
      data.put("asunto", token.getAsunto());
      data.put("CSV", token.getCsv());
      data.put("UUID", token.getUuid());
      data.put("NIF", user.getNif());


      // String path="C:\\Temp\\inside\\";
      FileOutputStream fileOutputStream = new FileOutputStream(basePath + token.getIdentificador()
          + WebConstants.ACTA_DE_INGRESO_SUFIJO + WebConstants.FORMAT_PDF);
      ByteArrayOutputStream baos = new ByteArrayOutputStream();

      // creamos el pdf
      Document doc = createDocument();

      PdfWriter pw = PdfWriter.getInstance(doc, baos);
      // pw.setLinearPageMode();
      doc.open();

      doc.newPage();
      printTitle(doc,
          context.getMessage("pdf.remisionEnLaNube.titulo", null, WebConstants.LOCALE_DEFAULT));
      printGeneralData(data, doc);

      doc.close();
      pw.close();
      baos.writeTo(fileOutputStream);
      baos.close();
      fileOutputStream.close();


      pdfResult = addCabecera(baos.toByteArray(), doc);
      mapaActaANDJustificante.put(WebConstants.ACTA_DE_INGRESO_SUFIJO, pdfResult);

      if (registrar) {

        // Obtenemos un justificante de registro electronico del Acta de
        // Ingreso
        try {
          JustificanteType justificanteType = recUtils.doRegistroElectronico(
              token.getIdentificador() + WebConstants.ACTA_DE_INGRESO_SUFIJO
                  + WebConstants.FORMAT_PDF,
              new String(Base64.encodeBase64(pdfResult)),
              InsideWSUtils.hashData(pdfResult, WebConstants.ALGORITMO_MD5));


          InsideWSUtils.writeFile(new File(basePath),
              basePath + token.getIdentificador()
                  + WebConstants.JUSTIFICANTE_REGISTRO_ELECTRONICO_SUFIJO + WebConstants.FORMAT_PDF,
              Base64.decodeBase64(justificanteType.getBlJustificante()));

          justificante = Base64.decodeBase64(justificanteType.getBlJustificante());
        } catch (Exception e) {
          logger.error("Error al registrar en registro electrónico " + e.getMessage()
              + e.getLocalizedMessage());
          justificante = this.crearInformeNoRegistroElectronico(user, token);
        }

      } else {
        justificante = this.crearInformeNoRegistroElectronico(user, token);
      }
      mapaActaANDJustificante.put(WebConstants.JUSTIFICANTE_REGISTRO_ELECTRONICO_SUFIJO,
          justificante);


    } catch (FileNotFoundException e) {
      logger.error("Error al crear acta de ingreso: " + e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      logger.error("Error al crear acta de ingreso: " + e.getMessage(), e);
      throw e;
    }

    return mapaActaANDJustificante;

  }



  /**
   * Creates the document.
   *
   * @return the document
   */
  private Document createDocument() {
    Document doc = new Document();
    doc.setPageSize(new Rectangle(842, 595));
    doc.setMargins(75, 40, 100, 60);
    return doc;
  }


  private Document createDocumentoRemisionNube() {
    Document doc = new Document();
    doc.setPageSize(new Rectangle(595, 842));
    doc.setMargins(75, 40, 100, 60);
    return doc;
  }

  private Document createDocumentoNoRegistroElectronico() {
    Document doc = new Document();
    doc.setPageSize(new Rectangle(595, 842));
    doc.setMargins(75, 40, 100, 60);
    return doc;
  }

  /**
   * Prints the title.
   *
   * @param doc the doc
   * @param title the title
   * @throws DocumentException the document exception
   */
  private void printTitle(Document doc, String title) throws com.lowagie.text.DocumentException {
    PdfPTable table = new PdfPTable(1);
    table.setWidthPercentage(DEFAULT_WIDTH_PERCENTAGE);

    PdfPCell cell = new PdfPCell();
    cell.setBorderWidth(0.5f);
    cell.setBackgroundColor(DEFAULT_BACKGROUND_TITLE);
    cell.setPaddingTop(-3f);
    cell.setPaddingLeft(0f);

    Paragraph p = new Paragraph(title, DEFAULT_FONT_TITLE);
    p.setAlignment(DEFAULT_ALIGNMENT_TITLE);
    cell.addElement(p);

    table.addCell(cell);
    table.setSpacingAfter(10f);

    try {
      doc.add(table);
      doc.add(Chunk.NEWLINE);
    } catch (com.lowagie.text.DocumentException e) {
      logger.error("PdfUtils-printTitle No se puede imprimir el titulo  " + title + ", ", e);
      throw e;
    }
  }

  /**
   * Prints the general data.
   *
   * @param data the data
   * @param doc the doc
   * @throws DocumentException the document exception
   */
  private void printGeneralData(Map<String, String> data, Document doc) throws DocumentException {

    PdfPTable generalData = new PdfPTable(2);
    generalData.setWidthPercentage(DEFAULT_WIDTH_PERCENTAGE);
    generalData.setWidths(new float[] {0.25f, 0.75f});

    LinkedHashMap<String, String> dataPdf = new LinkedHashMap<String, String>();

    dataPdf.put("pdf.remisionEnLaNube.nifUsuarioUso", "NIF");

    dataPdf.put("pdf.remisionEnLaNube.CSV", "CSV");

    dataPdf.put("pdf.remisionEnLaNube.UUID", "UUID");

    dataPdf.put("pdf.remisionEnLaNube.identificador", "identificador");

    dataPdf.put("pdf.remisionEnLaNube.fechaIngreso", "fechaIngreso");

    dataPdf.put("pdf.remisionEnLaNube.asunto", "asunto");

    doc.add(fillPdf(dataPdf, data, generalData));
  }



  /**
   * Fill pdf.
   *
   * @param dataPDF the data pdf
   * @param data the data
   * @param generalData the general data
   * @return the pdf p table
   */
  private PdfPTable fillPdf(LinkedHashMap<String, String> dataPDF, Map<String, String> data,
      PdfPTable generalData) {
    for (Map.Entry<String, String> entry : dataPDF.entrySet()) {
      generalData.addCell(
          createCellClave(context.getMessage(entry.getKey(), null, WebConstants.LOCALE_DEFAULT),
              Element.ALIGN_LEFT, PdfPCell.NO_BORDER, DEFAULT_FONT_BOLD, -1));
      generalData.addCell(createCellValue(data.get(entry.getValue()), Element.ALIGN_LEFT,
          PdfPCell.NO_BORDER, DEFAULT_FONT_BASE, -1));
      generalData.setSpacingAfter(30f);
    }

    return generalData;
  }



  /**
   * Creates the cell clave.
   *
   * @param texto the texto
   * @param alignment the alignment
   * @param border the border
   * @param font the font
   * @param profundidad the profundidad
   * @return the pdf p cell
   */
  private PdfPCell createCellClave(String texto, int alignment, int border, Font font,
      int profundidad) {
    PdfPCell celdaClave = new PdfPCell();
    celdaClave.setBorder(border);
    if (profundidad != -1) {
      celdaClave.setPaddingLeft(8f * profundidad);
    } else {
      celdaClave.setPaddingLeft(4f);
    }
    celdaClave.setPaddingTop(-1f);
    Paragraph p = new Paragraph(texto, font);
    p.setAlignment(alignment);
    celdaClave.addElement(p);

    return celdaClave;
  }

  /**
   * Creates the cell value.
   *
   * @param texto the texto
   * @param alignment the alignment
   * @param border the border
   * @param font the font
   * @param profundidad the profundidad
   * @return the pdf p cell
   */
  private PdfPCell createCellValue(String texto, int alignment, int border, Font font,
      int profundidad) {
    PdfPCell celdaValor = new PdfPCell();
    celdaValor.setBorder(border);
    celdaValor.setPaddingTop(-2f);
    if (profundidad != -1) {
      celdaValor.setPaddingLeft(8f * profundidad);
    } else {
      celdaValor.setPaddingLeft(4f);
    }
    Paragraph p1 = new Paragraph(texto, font);
    p1.setAlignment(alignment);
    celdaValor.addElement(p1);
    return celdaValor;
  }



  /**
   * Adds the cabecera.
   *
   * @param pdfEntrada the pdf entrada
   * @param doc the doc
   * @return the byte[]
   * @throws UtilException the util exception
   */
  public byte[] addCabecera(byte[] pdfEntrada, Document doc) throws Exception {

    byte[] bytesSalida = null;

    ByteArrayInputStream bis = new ByteArrayInputStream(pdfEntrada);
    ByteArrayOutputStream salida = new ByteArrayOutputStream();
    try {
      PdfReader reader = new PdfReader(bis);
      salida = new ByteArrayOutputStream();
      GregorianCalendar calender = new GregorianCalendar();
      PdfStamper stamp = new PdfStamper(reader, salida, calender);
      stamp.setFormFlattening(false);
      int n = reader.getNumberOfPages();

      for (int i = 1; i <= n; i++) {

        PdfPTable cabecera = designTable(WIDTH_TABLE_HOR, relativeWidthsHor);

        cabecera.writeSelectedRows(0, -1, doc.leftMargin(),
            stamp.getReader().getPageSize(i).getHeight() - 15, stamp.getOverContent(i));
      }

      reader.close();
      salida.close();
      stamp.close(calender);

      bytesSalida = salida.toByteArray();

    } catch (Exception e) {
      logger.error("PdfUtils-addCabecera No se puede agregar la cabecera " + e.getMessage(), e);
      throw e;
    }

    return bytesSalida;
  }

  /**
   * Design table.
   *
   * @param widthTabla the width tabla
   * @param relativeWidths the relative widths
   * @return the pdf p table
   * @throws UtilException the util exception
   */
  private PdfPTable designTable(float widthTabla, float[] relativeWidths) throws Exception {
    PdfPTable tabla = new PdfPTable(1);
    tabla.setTotalWidth(widthTabla);
    try {
      tabla.setWidths(relativeWidths);
    } catch (DocumentException e) {

      throw e;
    }

    // Image img = null;
    // try {
    // img = Image.getInstance(context.getResource("classpath:escudo.png").getFilename());
    // } catch (Exception e) {
    // logger.error("PdfUtils-designTable No se puede agregar la cabecera " + e.getMessage(), e);
    // throw e;
    // }
    // tabla.addCell(celdaLogo(img));

    return tabla;
  }

  /**
   * Celda logo.
   *
   * @param img the img
   * @return the pdf p cell
   */
  private PdfPCell celdaLogo(Image img) {
    PdfPCell cellImg = new PdfPCell();
    cellImg.setBorder(PdfPCell.NO_BORDER);
    img.scalePercent(25);
    // cellImg.setImage(img);//addElement(img);
    return cellImg;
  }

  public byte[] crearInformeNoRegistroElectronico(ObjetoInsideUsuario usuario,
      ObjetoExpedienteToken token) throws DocumentException, IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    // creamos el pdf
    Document doc = createDocumentoNoRegistroElectronico();

    PdfWriter pw = PdfWriter.getInstance(doc, baos);
    doc.open();
    doc.newPage();

    // llamada para imprimir imagen de cabecera
    imprimirImagen(doc);

    // imprime titulo
    printTitle(doc, messageSource.getMessage("listadoAuditoriaAccesoNube.informe.titulo", null,
        WebConstants.LOCALE_DEFAULT));

    // imprime subtitulo
    doc.add(new Paragraph(messageSource.getMessage("listadoAuditoriaAccesoNube.informe.titulo2",
        null, WebConstants.LOCALE_DEFAULT), DEFAULT_FONT_TITLE));
    doc.add(new Paragraph(
        "                                                                                     "));

    // imprime datos acceso
    HashMap<String, String> data = new HashMap<String, String>();
    data.put("identificador", token.getIdentificador());
    data.put("csv", token.getCsv());
    data.put("uuid", token.getUuid());
    data.put("fechaAcceso", DateFormatUtils.format(new Date(), "dd/MM/yyyy hh:mm:ss"));
    data.put("usuario", usuario != null ? usuario.getNif() : "No dado de alte en Inside");
    data.put("textoError",
        messageSource.getMessage("listadoAuditoriaAccesoNube.informe.error.registroe.mensaje", null,
            WebConstants.LOCALE_DEFAULT));
    pintarDatosAccesoNoRegistro(data, doc);

    // imprime pie de fecha
    imprimirPieFecha(doc);

    doc.close();
    pw.close();
    baos.close();
    return baos.toByteArray();
  }

  public byte[] crearInformeAuditoriaAccesoDocumento(ObjetoAuditoriaAcceso datosAcceso)
      throws DocumentException, IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    // creamos el pdf
    Document doc = createDocumentoRemisionNube();

    PdfWriter pw = PdfWriter.getInstance(doc, baos);
    doc.open();
    doc.newPage();

    // llamada para imprimir imagen de cabecera
    imprimirImagen(doc);

    // imprime titulo
    printTitle(doc, messageSource.getMessage("listadoAuditoriaAccesoNube.informe.titulo", null,
        WebConstants.LOCALE_DEFAULT));

    // imprime subtitulo
    doc.add(new Paragraph(messageSource.getMessage("listadoAuditoriaAccesoNube.informe.titulo2",
        null, WebConstants.LOCALE_DEFAULT), DEFAULT_FONT_TITLE));
    doc.add(new Paragraph(
        "                                                                                     "));

    // imprime datos acceso
    HashMap<String, String> data = new HashMap<String, String>();
    data.put("identificador", datosAcceso.getIdentificador());
    data.put("unidadOrganica", datosAcceso.getUnidadOrganica());
    data.put("usuario", datosAcceso.getUsuario());
    data.put("unidadOrganicaUsuario", datosAcceso.getUnidadOrganicaUsuario());
    data.put("fechaAcceso",
        DateFormatUtils.format(datosAcceso.getFechaAcceso(), "dd/MM/yyyy hh:mm:ss"));
    pintarDatosAcceso(data, doc);

    // imprime pie de fecha
    imprimirPieFecha(doc);

    doc.close();
    pw.close();
    baos.close();
    return baos.toByteArray();
  }

  public byte[] crearInformeSolicitarAccesoExpediente(
      PeticionSolicitarAccesoExpedienteType peticionSolicitarAccesoExpedienteType,
      String dir3ExpedienteSolicitado, String urlDestino, String idPeticion, String respuestaSW)
      throws DocumentException, IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    // creamos el pdf
    Document doc = createDocumentoRemisionNube();

    PdfWriter pw = PdfWriter.getInstance(doc, baos);
    doc.open();
    doc.newPage();

    // llamada para imprimir imagen de cabecera
    imprimirImagen(doc);

    // imprime titulo
    printTitle(doc, messageSource.getMessage("solicitudesAccesoExpediente.informe.titulo", null,
        WebConstants.LOCALE_DEFAULT));

    // imprime subtitulo
    doc.add(new Paragraph(messageSource.getMessage("solicitudesAccesoExpediente.informe.titulo2",
        null, WebConstants.LOCALE_DEFAULT), DEFAULT_FONT_TITLE));
    doc.add(new Paragraph(
        "                                                                                     "));

    // imprime datos acceso
    HashMap<String, String> data = new HashMap<String, String>();
    data.put("unidadOrganicaDestino", dir3ExpedienteSolicitado);
    data.put("unidadOrganicaOrigen",
        peticionSolicitarAccesoExpedienteType.getPeticion().getUsuario());
    data.put("idPeticion", idPeticion);
    if (peticionSolicitarAccesoExpedienteType.getMetadatos() != null
        && peticionSolicitarAccesoExpedienteType.getMetadatos().getMetadatoAdicional() != null) {
      String metadatosAdicionales = "";
      boolean masDeUno =
          peticionSolicitarAccesoExpedienteType.getMetadatos().getMetadatoAdicional().size() > 1;
      for (MetadatoAdicional f : peticionSolicitarAccesoExpedienteType.getMetadatos()
          .getMetadatoAdicional()) {
        org.w3c.dom.Element e = (org.w3c.dom.Element) f.getValor();
        metadatosAdicionales +=
            f.getNombre() + " : " + e.getTextContent() + (masDeUno ? " / " : " ");
      }
      data.put("metadatosAdicionales", metadatosAdicionales);
    }
    data.put("fechaSolicitud", DateFormatUtils.format(new Date(), "dd/MM/yyyy HH:mm:ss"));
    data.put("urlDestino", urlDestino);
    data.put("urlRemitente", peticionSolicitarAccesoExpedienteType.getEndpointPeticionario());
    data.put("respuestaUrlDestino", respuestaSW);


    pintarDatosRecepcionSolicitudCredenciales(data, doc);

    // imprime pie de fecha
    imprimirPieFecha(doc);

    doc.close();
    pw.close();
    baos.close();
    return baos.toByteArray();
  }

  /**
   * Prints the general data.
   *
   * @param data the data
   * @param doc the doc
   * @throws DocumentException the document exception
   */
  void pintarDatosAcceso(Map<String, String> data, Document doc) throws DocumentException {

    PdfPTable generalData = new PdfPTable(2);
    generalData.setWidthPercentage(DEFAULT_WIDTH_PERCENTAGE);
    generalData.setWidths(new float[] {0.25f, 0.75f});

    LinkedHashMap<String, String> dataPdf = new LinkedHashMap<String, String>();

    dataPdf.put("listadoAuditoriaAccesoNube.informe.identificador", "identificador");

    dataPdf.put("listadoAuditoriaAccesoNube.informe.unidadOrganica", "unidadOrganica");

    dataPdf.put("listadoAuditoriaAccesoNube.informe.usuario", "usuario");

    dataPdf.put("listadoAuditoriaAccesoNube.informe.unidadOrganicaUsuario",
        "unidadOrganicaUsuario");

    dataPdf.put("listadoAuditoriaAccesoNube.informe.fechaAcceso", "fechaAcceso");

    doc.add(fillPdf(dataPdf, data, generalData));
  }

  /**
   * Prints the general data.
   *
   * @param data the data
   * @param doc the doc
   * @throws DocumentException the document exception
   */
  void pintarDatosAccesoNoRegistro(Map<String, String> data, Document doc)
      throws DocumentException {

    PdfPTable generalData = new PdfPTable(2);
    generalData.setWidthPercentage(DEFAULT_WIDTH_PERCENTAGE);
    generalData.setWidths(new float[] {0.25f, 0.75f});

    LinkedHashMap<String, String> dataPdf = new LinkedHashMap<String, String>();

    dataPdf.put("listadoAuditoriaAccesoNube.informe.identificador", "identificador");

    dataPdf.put("listadoAuditoriaAccesoNube.informe.token.csv", "csv");

    dataPdf.put("listadoAuditoriaAccesoNube.informe.token.uuid", "uuid");

    dataPdf.put("listadoAuditoriaAccesoNube.informe.fechaAcceso", "fechaAcceso");

    dataPdf.put("listadoAuditoriaAccesoNube.informe.usuario", "usuario");

    dataPdf.put("listadoAuditoriaAccesoNube.informe.error.registroe", "textoError");

    doc.add(fillPdf(dataPdf, data, generalData));
  }

  /**
   * Prints the general data.
   *
   * @param data the data
   * @param doc the doc
   * @throws DocumentException the document exception
   */
  void pintarDatosRecepcionSolicitudCredenciales(Map<String, String> data, Document doc)
      throws DocumentException {

    PdfPTable generalData = new PdfPTable(2);
    generalData.setWidthPercentage(DEFAULT_WIDTH_PERCENTAGE);
    generalData.setWidths(new float[] {0.30f, 0.70f});

    LinkedHashMap<String, String> dataPdf = new LinkedHashMap<String, String>();

    dataPdf.put("solicitudesAccesoExpediente.informe.dir3Destinatario", "unidadOrganicaDestino");

    dataPdf.put("solicitudesAccesoExpediente.informe.dir3Origen", "unidadOrganicaOrigen");

    dataPdf.put("solicitudesAccesoExpediente.informe.fechaSolicitud", "fechaSolicitud");

    dataPdf.put("solicitudesAccesoExpediente.informe.idPeticion", "idPeticion");

    dataPdf.put("solicitudesAccesoExpediente.informe.urlDestino", "urlDestino");

    dataPdf.put("solicitudesAccesoExpediente.informe.respuestaUrlDestino", "respuestaUrlDestino");

    dataPdf.put("solicitudesAccesoExpediente.informe.urlRemitente", "urlRemitente");

    dataPdf.put("solicitudesAccesoExpediente.informe.metadatosAdicionales", "metadatosAdicionales");

    doc.add(fillPdf(dataPdf, data, generalData));
  }

  void imprimirImagen(Document doc) throws DocumentException {
    // imprimir imagen de cabecera. La imagen a pelo en base64 porque no se consige cargarla
    Image img = null;
    String imagenBase64Properties = messageSource.getMessage(
        "listadoAuditoriaAccesoNube.imagen.cabecera", null, WebConstants.LOCALE_DEFAULT);


    try {
      // img =
      // Image.getInstance("C:\\desarrollo\\workspaceInside\\inside-web\\images\\logoSEAP_315x68.png");
      img = Image.getInstance(Base64.decodeBase64(imagenBase64Properties));

      doc.add(img);
      doc.add(new Paragraph(
          "                                                                                     "));
    } catch (MalformedURLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }


    /////// fin imprimir imagen
  }


  void imprimirPieFecha(Document doc) throws DocumentException {
    doc.add(new Paragraph(
        "                                                                                     "));
    SimpleDateFormat formateador = new SimpleDateFormat("EEEEEEEEE dd 'de' MMMMM 'de' yyyy");
    Calendar cal = Calendar.getInstance();
    cal.setTimeZone(TimeZone.getDefault());
    doc.add(new Paragraph(" Madrid, " + formateador.format(new Date()), DEFAULT_FONT_BASE));
  }



}
