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
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
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
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteToken;
import es.mpt.dsic.inside.model.objetos.usuario.ObjetoInsideUsuario;



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

  /** The Constant WIDTH_TABLE_VER. */
  private static final float WIDTH_TABLE_VER = 480f;

  /** The Constant relativeWidthsHor. */
  private static final float[] relativeWidthsHor = {1f};

  /** The Constant relativeWidthsVer. */
  private static final float[] relativeWidthsVer = {1f};


  /** The context. */
  @Autowired(required = true)
  private ApplicationContext context;

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

        // Obtenemos un justificante de registro electr�nico del Acta de
        // Ingreso
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
        mapaActaANDJustificante.put(WebConstants.JUSTIFICANTE_REGISTRO_ELECTRONICO_SUFIJO,
            justificante);

      }



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



}
