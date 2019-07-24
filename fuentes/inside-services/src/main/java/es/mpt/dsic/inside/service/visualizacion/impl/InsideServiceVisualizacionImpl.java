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

package es.mpt.dsic.inside.service.visualizacion.impl;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import javax.annotation.PostConstruct;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import es.mpt.dsic.eeutil.client.model.DatosFirmados;
import es.mpt.dsic.eeutil.client.operFirma.model.ResultadoValidacionInfo;
import es.mpt.dsic.eeutil.client.visDocExp.model.ApplicationLogin;
import es.mpt.dsic.eeutil.client.visDocExp.model.DocumentoContenido;
import es.mpt.dsic.eeutil.client.visDocExp.model.DocumentoEniConMAdicionales;
import es.mpt.dsic.eeutil.client.visDocExp.model.InSideException;
import es.mpt.dsic.eeutil.client.visDocExp.model.Item;
import es.mpt.dsic.eeutil.client.visDocExp.model.ListaCadenas;
import es.mpt.dsic.eeutil.client.visDocExp.model.ListaPropiedades;
import es.mpt.dsic.eeutil.client.visDocExp.model.OpcionesLogo;
import es.mpt.dsic.eeutil.client.visDocExp.model.OpcionesVisualizacion;
import es.mpt.dsic.eeutil.client.visDocExp.model.Plantilla;
import es.mpt.dsic.eeutil.client.visDocExp.model.Propiedad;
import es.mpt.dsic.eeutil.client.visDocExp.model.SalidaVisualizacion;
import es.mpt.dsic.eeutil.client.visDocExp.service.EeUtilService;
import es.mpt.dsic.eeutil.client.visDocExp.service.EeUtilServiceImplService;
import es.mpt.dsic.infofirma.model.FirmaElectronica;
import es.mpt.dsic.infofirma.model.InfoFirmaElectronica;
import es.mpt.dsic.infofirma.model.InfoFirmante;
import es.mpt.dsic.infofirma.model.OpcionesInfoFirma;
import es.mpt.dsic.infofirma.service.InfoFirmaService;
import es.mpt.dsic.infofirma.service.exception.InfoFirmaServiceException;
import es.mpt.dsic.inside.model.converter.InsideConverterDocumento;
import es.mpt.dsic.inside.model.converter.exception.InsideConverterException;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideMetadatoAdicional;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInside;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInsideContenido;
import es.mpt.dsic.inside.model.objetos.documento.metadatos.ObjetoDocumentoInsideMetadatos;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteInside;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenido;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenidoElementoIndizado;
import es.mpt.dsic.inside.model.objetos.firmas.FirmaInside;
import es.mpt.dsic.inside.model.objetos.firmas.contenido.ContenidoFirmaCSVInside;
import es.mpt.dsic.inside.model.objetos.firmas.contenido.ContenidoFirmaCertificadoAlmacenableInside;
import es.mpt.dsic.inside.service.store.InsideServiceStore;
import es.mpt.dsic.inside.service.store.exception.InsideServiceStoreException;
import es.mpt.dsic.inside.service.store.exception.InsideStoreObjectNotFoundException;
import es.mpt.dsic.inside.service.util.Constantes;
import es.mpt.dsic.inside.service.util.InsideObjectsUtils;
import es.mpt.dsic.inside.service.util.InsideUtils;
import es.mpt.dsic.inside.service.visualizacion.InsideServiceVisualizacion;
import es.mpt.dsic.inside.service.visualizacion.OpcionesVisualizacionIndice;
import es.mpt.dsic.inside.service.visualizacion.ResultadoVisualizacionDocumento;
import es.mpt.dsic.inside.service.visualizacion.exception.InsideServiceVisualizacionException;
import es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.TipoDocumentoVisualizacionInside;
import es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.TipoResultadoVisualizacionDocumentoInside;

public class InsideServiceVisualizacionImpl implements InsideServiceVisualizacion {

  protected static final Log logger = LogFactory.getLog(InsideServiceVisualizacionImpl.class);

  private Properties properties;
  private EeUtilService port;
  private ApplicationLogin applicationLogin;

  private List<String> filasNombreOrganismo;
  private String modeloExpediente;
  private String modeloDocumento;
  private String estamparPie;
  private String textoPie;

  private boolean activo;
  private static String activoCadena = "S";

  @Autowired
  private InsideServiceStore insideStore;
  @Autowired
  private InfoFirmaService infoFirmaService;

  private static final String VALUE_NO = "N";
  private static final String VALUE_SI = "S";
  private static final String EXPEDIENTE = "expediente";
  private static final String DOCUMENTO = "documento";
  private static final String FIRMA_CSV = "itemFirmaCSV";
  private static final String FIRMA_NO_CSV = "itemFirmaNoCSV";
  private static final String DOCUMENTO_INDIZADO = "documentoIndizado";
  private static final String DOC_METADATO_ADICIONAL = "documentoMetadatoAdicional";
  private static final String EXP_METADATO_ADICIONAL = "expedienteMetadatoAdicional";
  private static final String ADD_ALL_METADATOS = "metadatoAdicionalTodos";

  @PostConstruct
  public void configure() {

    String visualizacionActivo = properties.getProperty("visualizacion.activo");

    if (!activoCadena.contentEquals(visualizacionActivo)) {
      logger.info("El WS de VISUALIZACIÓN no está activo");
      activo = false;
    } else {

      URL url = null;
      String urlVisualizacion = null;
      try {
        urlVisualizacion = properties.getProperty("visualizacion.url");
        logger.debug(String.format("El WS de VISUALIZACION se encuentra en %s", urlVisualizacion));
        url = new URL(urlVisualizacion);
      } catch (MalformedURLException me) {
        logger.error("No se puede crear la URL del servicio de visualizacion " + urlVisualizacion,
            me);
      }

      EeUtilServiceImplService ss = new EeUtilServiceImplService(url);

      port = ss.getEeUtilServiceImplPort();

      applicationLogin = new ApplicationLogin();
      applicationLogin.setIdaplicacion(properties.getProperty("visualizacion.idaplicacion"));
      applicationLogin.setPassword(properties.getProperty("visualizacion.password"));

      logger.debug(String.format("Utilizando para visualizacion idaplicacion/password : %s/%s",
          properties.getProperty("visualizacion.idaplicacion"),
          properties.getProperty("visualizacion.password")));

      filasNombreOrganismo =
          Arrays.asList(properties.getProperty("visualizacion.nombre.organismo").split(";"));

      logger.debug(String.format("Utilizando nombre de organismo: %s",
          properties.getProperty("visualizacion.nombre.organismo")));

      estamparPie = properties.getProperty("visualizacion.estamparpie");

      logger.debug(String.format("Utilizando estamparpie: %s",
          properties.getProperty("visualizacion.estamparpie")));

      textoPie = properties.getProperty("visualizacion.pie");

      logger
          .debug(String.format("Utilizando pie: %s", properties.getProperty("visualizacion.pie")));

      modeloExpediente = properties.getProperty("visualizacion.modelo.expediente");

      logger.debug(String.format("Utilizando modelo de expediente: %s",
          properties.getProperty("visualizacion.modelo.expediente")));

      modeloDocumento = properties.getProperty("visualizacion.modelo.documento");

      logger.debug(String.format("Utilizando modelo de documento: %s",
          properties.getProperty("visualizacion.modelo.documento")));

      activo = true;
    }

  }

  @Override
  /**
   * Devuelve un expediente Inside con la visualización del índice con las opciones indicadas en el
   * parámetro.
   */
  public ObjetoExpedienteInside expedienteConVisualizacionIndice(ObjetoExpedienteInside expediente,
      OpcionesVisualizacionIndice opciones) throws InsideServiceVisualizacionException {

    if (!activo) {
      throw new InsideServiceVisualizacionException(
          "El WS de VISUALIZACION no se encuentra activo");
    }
    // Construimos los parámetros de la llamada al servicio.
    Item itemExpediente = expedienteInsideToItemVisualizacion(expediente, opciones);

    OpcionesVisualizacion oVisualizacion = getOpcionesVisualizacionWS(opciones, modeloExpediente);
    SalidaVisualizacion salida = null;
    try {
      // Llamada al servicio
      salida = port.visualizar(applicationLogin, itemExpediente, oVisualizacion);
    } catch (Exception t) {
      throw new InsideServiceVisualizacionException("Error al obtener la visualización del índice",
          t);
    }

    // Rellenamos el objeto que contiene la visualización del índice.
    ObjetoDocumentoInsideContenido contenidoVisualizacion = new ObjetoDocumentoInsideContenido();
    contenidoVisualizacion.setIdentificadorEnDocumento("VISUALIZACION_INDICE");
    contenidoVisualizacion.setValorBinario(salida.getDocumentoContenido().getBytesDocumento());
    contenidoVisualizacion.setMime(salida.getDocumentoContenido().getMimeDocumento());
    String nombreFormato =
        InsideUtils.getNombreFormatoByMime(salida.getDocumentoContenido().getMimeDocumento());
    if (nombreFormato == null) {
      throw new InsideServiceVisualizacionException(
          "No se ha encontrado nombre de formato para el mime del contenido de la visualización del Índice");
    }
    contenidoVisualizacion.setNombreFormato(nombreFormato);

    expediente.setVisualizacionIndice(contenidoVisualizacion);
    return expediente;

  }

  /**
   * Devuelve un expediente Inside con la visualización del índice con las opciones por defecto. *
   */
  @Override
  public ObjetoExpedienteInside expedienteConVisualizacionIndice(ObjetoExpedienteInside expediente)
      throws InsideServiceVisualizacionException {

    OpcionesVisualizacionIndice opciones = new OpcionesVisualizacionIndice();
    opciones.setExterno(false);
    opciones.setEstamparImagen(true);
    opciones.setEstamparNombreOrganismo(true);
    opciones.setLineasNombreOrganismo(filasNombreOrganismo);
    opciones.setEstamparPie(VALUE_SI.equalsIgnoreCase(estamparPie));
    opciones.setTextoPie(textoPie);
    return expedienteConVisualizacionIndice(expediente, opciones);

  }

  @Override
  /**
   * Devuelve la visualización de un documento inside, con opciones de visualización.
   */
  public ResultadoVisualizacionDocumento visualizacionDocumento(ObjetoDocumentoInside documento,
      OpcionesVisualizacionIndice opciones, byte[] bytesContenido)
      throws InsideServiceVisualizacionException {

    if (!activo) {
      throw new InsideServiceVisualizacionException(
          "El WS de VISUALIZACION no se encuentra activo");
    }

    if (opciones.getLineasNombreOrganismo() == null
        || CollectionUtils.isEmpty(opciones.getLineasNombreOrganismo())) {
      List<String> lineas = new ArrayList<String>();
      lineas.add(properties.getProperty("visualizacion.filasNombreOrganismo.defecto"));
      opciones.setLineasNombreOrganismo(lineas);
    }

    ResultadoVisualizacionDocumento resultado = new ResultadoVisualizacionDocumento();
    Item itemDocumento = documentoInsideToItemVisualizacion(documento, bytesContenido);

    OpcionesVisualizacion oVisualizacion = getOpcionesVisualizacionWS(opciones, modeloDocumento);
    SalidaVisualizacion salida = null;
    try {
      // Llamada al servicio
      salida = port.visualizar(applicationLogin, itemDocumento, oVisualizacion);
    } catch (Exception t) {
      String identificador =
          itemDocumento.getIdentificador() != null ? itemDocumento.getIdentificador() : "";
      throw new InsideServiceVisualizacionException(
          "Error al obtener la visualización del documento " + identificador, t);
    }

    resultado.setVisualizacion(salida.getDocumentoContenido().getBytesDocumento());
    resultado.setMime(salida.getDocumentoContenido().getMimeDocumento());

    return resultado;
  }

  @Override
  /**
   * Devuelve la visualización de un documento inside, sin opciones, por lo que se usarán las de por
   * defecto
   */
  public ResultadoVisualizacionDocumento visualizacionDocumento(ObjetoDocumentoInside documento,
      byte[] bytesContenido) throws InsideServiceVisualizacionException {

    if (!activo) {
      throw new InsideServiceVisualizacionException(
          "El WS de VISUALIZACION no se encuentra activo");
    }

    OpcionesVisualizacionIndice opciones = new OpcionesVisualizacionIndice();
    opciones.setExterno(false);
    opciones.setEstamparImagen(true);
    opciones.setEstamparNombreOrganismo(true);
    opciones.setLineasNombreOrganismo(filasNombreOrganismo);
    opciones.setEstamparPie(estamparPie.equalsIgnoreCase(VALUE_SI));
    opciones.setTextoPie(textoPie);

    return visualizacionDocumento(documento, opciones, bytesContenido);

  }

  @Override
  /**
   * Devuelve true si el servicio está activo, false en caso contrario
   */
  public boolean isActivo() {
    return this.activo;
  }

  /**
   * Construcción de la estructura de datos jerárquica que le pasamos al WS de obtención de
   * visualización del índice.
   * 
   * @param expediente
   * @return
   */
  private Item expedienteInsideToItemVisualizacion(ObjetoExpedienteInside expediente,
      OpcionesVisualizacionIndice opciones) throws InsideServiceVisualizacionException {
    Item padre = new Item();
    padre.setIdentificador(expediente.getIdentificador());
    padre.setNombre(expediente.getIdentificador());
    padre.setPropiedadesItem(getPropiedadesExpediente(expediente));

    fillItemWithIndice(padre, expediente.getIndice().getIndiceContenido().getElementosIndizados(),
        opciones);

    return padre;
  }

  /**
   * Construye la estructura jerárquica de un item a partir de sus hijos.
   * 
   * @param item
   * @param elementosIndice Elementos que serán hijos de "item"
   * @param AtomicInteger Entero que se va incrementando.
   */
  private void fillItemWithIndice(Item item,
      List<ObjetoExpedienteInsideIndiceContenidoElementoIndizado> elementosIndice,
      OpcionesVisualizacionIndice opciones) throws InsideServiceVisualizacionException {

    // Metemos un AtomicInteger como parámetro porque los Integer no se
    // pueden incrementar, y los int se pasan por valor.

    for (ObjetoExpedienteInsideIndiceContenidoElementoIndizado elementoHijo : elementosIndice) {
      Item itemHijo = new Item();
      itemHijo.setPadre(item);
      itemHijo.setIdentificador("ID_" + UUID.randomUUID());

      if (elementoHijo instanceof ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado) {
        ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado docIndizado =
            (ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado) elementoHijo;

        itemHijo.setNombre(getNombreDocumentoIndizado(docIndizado, opciones.isExterno()));
        itemHijo.setPropiedadesItem(getPropiedadesDocumentoIndizado(docIndizado,
            docIndizado.getOrdenDocumentoExpediente()));

      } else if (elementoHijo instanceof ObjetoExpedienteInsideIndiceContenido) {
        ObjetoExpedienteInsideIndiceContenido indiceContenidoIndizado =
            (ObjetoExpedienteInsideIndiceContenido) elementoHijo;
        itemHijo.setNombre("Expediente Electrónico: "
            + indiceContenidoIndizado.getIdentificadorExpedienteAsociado());
        fillItemWithIndice(itemHijo, indiceContenidoIndizado.getElementosIndizados(), opciones);

      } else if (elementoHijo instanceof ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada) {
        ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada carpetaIndizada =
            (ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada) elementoHijo;
        itemHijo.setNombre(carpetaIndizada.getIdentificadorCarpeta());
        fillItemWithIndice(itemHijo, carpetaIndizada.getElementosIndizados(), opciones);
      }

      item.getHijos().getItems().getItems().add(itemHijo);
    }
  }

  private void fillItemWithInfoDocumento(Item item, ObjetoDocumentoInside documento,
      byte[] bytesContenido) throws InsideServiceVisualizacionException {

    byte[] contenidoDocumento = null;
    String mimeContenidoDocumento = null;
    InfoFirmaElectronica infoFirma;
    String identificador = documento != null ? documento.getIdentificador() : "";
    // Obtenemos las firmas que NO son CSV, esto nos valdrá más adelante
    List<FirmaInside> firmasNoCSV = InsideObjectsUtils.getFirmasNoCSV(documento.getFirmas());

    // CASO 1 - El documento se encuentra en el contenido del documento
    // Inside
    if (documento.getContenido().getReferencia() == null) {

      List<FirmaInside> firmasRef = InsideObjectsUtils.getFirmasReferencianNodo(
          documento.getContenido().getIdentificadorEnDocumento(), documento.getFirmas());

      if (CollectionUtils.isNotEmpty(firmasRef)) {

        // Servicio de infofirma para obtener información
        // del contenido y los firmantes
        try {
          // LLamamos por cada una de las firmas de la lista
          int indiceFirma = 0;
          for (FirmaInside firma : firmasRef) {
            byte[] contenido = InsideConverterDocumento.obtenerContenidoFirma(firma,
                documento.getContenido().getValorBinario());

            infoFirma = llamadaInfoFirmaService(contenido, true, true, true, bytesContenido);
            contenidoDocumento = infoFirma.getContenidoFirmado();
            mimeContenidoDocumento = infoFirma.getMimeContenidoFirmado();

            // para añadir solo una vez cada firma
            InfoFirmante infoFirmante = infoFirma.getFirmantes().get(indiceFirma);
            Item itemFirma =
                getItemFirmaNoCSV(item, infoFirma.getTipoFirma().getTipoFirma(), infoFirmante);
            item.getHijos().getItems().getItems().add(itemFirma);

            indiceFirma++;

          }

        } catch (InfoFirmaServiceException e) {
          throw new InsideServiceVisualizacionException(
              "Error al obtener información de la firma a partir del contenido del documento: "
                  + identificador,
              e);
        } catch (InsideConverterException e) {
          throw new InsideServiceVisualizacionException(
              "Error al obtener información de la firma a partir del contenido del documento: "
                  + identificador,
              e);
        }

        // CASO 1.2 El contenido NO es referenciado desde ninguna firma
      } else {

        // Tenemos el contenido
        contenidoDocumento = documento.getContenido().getValorBinario();
        mimeContenidoDocumento = documento.getContenido().getMime();

        if (CollectionUtils.isNotEmpty(firmasNoCSV)) {

          // Obtenemos los firmantes con infofirmaservice de
          int indiceFirma = 0;
          for (FirmaInside firma : firmasNoCSV) {
            if (firma.getContenidoFirma() instanceof ContenidoFirmaCertificadoAlmacenableInside) {
              ContenidoFirmaCertificadoAlmacenableInside contAlm =
                  (ContenidoFirmaCertificadoAlmacenableInside) firma.getContenidoFirma();
              try {

                byte[] contenido = InsideConverterDocumento.obtenerContenidoFirma(firma,
                    contAlm.getValorBinario());

                infoFirma = llamadaInfoFirmaService(contenido, false, true, true, bytesContenido);
              } catch (InfoFirmaServiceException e) {
                throw new InsideServiceVisualizacionException(
                    "Error al obtener información de la firma a partir de una firma que NO está referenciada",
                    e);
              } catch (InsideConverterException e) {
                throw new InsideServiceVisualizacionException(
                    "Error al obtener información de la firma a partir del contenido del documento: "
                        + identificador,
                    e);
              }

              // para añadir solo una vez cada firma
              InfoFirmante infoFirmante = infoFirma.getFirmantes().get(indiceFirma);
              if (firma.getTipoFirma().value().equalsIgnoreCase("TF04"))
                infoFirma.getTipoFirma().setTipoFirma("CADES Explicit");

              Item itemFirma =
                  getItemFirmaNoCSV(item, infoFirma.getTipoFirma().getTipoFirma(), infoFirmante);
              item.getHijos().getItems().getItems().add(itemFirma);

              indiceFirma++;
            }

          }

          // CASO 1.2.2 Sólo hay firmas CSV
        } else {

          // No hacemos nada
        }
      }

      // CASO 2 - El documento se encuentra implícito en alguna firma
    } else {
      // Obtenemos la firma implícita
      FirmaInside firmaImplicita = InsideObjectsUtils.getFirmaInsideByIdentificadorEnDocumento(
          documento.getContenido().getReferencia().replace("#", ""), documento.getFirmas());
      ContenidoFirmaCertificadoAlmacenableInside contAlm =
          (ContenidoFirmaCertificadoAlmacenableInside) firmaImplicita.getContenidoFirma();

      // Llamamos a infofirma para obtener el contenido firmado y los
      // firmantes.
      try {
        infoFirma =
            llamadaInfoFirmaService(contAlm.getValorBinario(), true, true, true, bytesContenido);
        contenidoDocumento = infoFirma.getContenidoFirmado();
        mimeContenidoDocumento = infoFirma.getMimeContenidoFirmado();

      } catch (InfoFirmaServiceException e) {
        throw new InsideServiceVisualizacionException(
            "Error al obtener información de la firma a partir de una firma implícita referenciada desde el documento",
            e);
      }
      fillItemWithFirmasInfo(item, infoFirma);
    }

    // Rellenamos el contenido del documento.
    DocumentoContenido dc = new DocumentoContenido();
    dc.setBytesDocumento(contenidoDocumento);
    dc.setMimeDocumento(mimeContenidoDocumento);

    item.setDocumentoContenido(dc);

    // Por último, recuperamos las firmas CSV para meterlas también el Item
    // de la petición
    List<FirmaInside> firmasCSV = InsideObjectsUtils.getFirmasCSV(documento.getFirmas());
    for (FirmaInside firmaCSV : firmasCSV) {
      Item itemCSV = getItemFirmaCSV(item, firmaCSV);
      item.getHijos().getItems().getItems().add(itemCSV);
    }

  }

  @Override
  public InfoFirmaElectronica llamadaInfoFirmaService(byte[] firma, boolean obtenerDatosFirmados,
      boolean obtenerFirmantes, boolean obtenerTipoFirma, byte[] bytesContenido)
      throws InfoFirmaServiceException {

    FirmaElectronica peticionFirma = new FirmaElectronica();
    peticionFirma.setFirma(firma);

    OpcionesInfoFirma peticionOpciones = new OpcionesInfoFirma();
    peticionOpciones.setObtenerDatosFirmados(obtenerDatosFirmados);
    peticionOpciones.setObtenerFirmantes(obtenerFirmantes);
    peticionOpciones.setObtenerTipoFirma(obtenerTipoFirma);

    return infoFirmaService.getInfoFirma(peticionFirma, peticionOpciones, bytesContenido);

  }

  @Override
  public ResultadoValidacionInfo llamadaValidacionFirmaService(byte[] firma,
      String datosFirmadosBase64) throws InfoFirmaServiceException {

    FirmaElectronica peticionFirma = new FirmaElectronica();
    peticionFirma.setFirma(firma);
    DatosFirmados datosFirmados = null;
    if (datosFirmadosBase64 != null) {
      datosFirmados = new DatosFirmados();
      try {
        datosFirmados.setDocumento(datosFirmadosBase64.getBytes("UTF-8"));
      } catch (UnsupportedEncodingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return infoFirmaService.validacionFirma(peticionFirma, null, datosFirmados);

  }

  private void fillItemWithFirmasInfo(Item item, InfoFirmaElectronica infoFirma) {

    if (infoFirma.getFirmantes() != null) {

      for (InfoFirmante infoFirmante : infoFirma.getFirmantes()) {
        Item itemFirma =
            getItemFirmaNoCSV(item, infoFirma.getTipoFirma().getTipoFirma(), infoFirmante);
        item.getHijos().getItems().getItems().add(itemFirma);
      }
    }

  }

  /**
   * Construye la lista de propiedades de un expediente que aparecerán en la visualización del
   * índice.
   * 
   * @param expediente
   * @return
   */
  private ListaPropiedades getPropiedadesExpediente(ObjetoExpedienteInside expediente) {

    ListaPropiedades lista = new ListaPropiedades();
    ListaPropiedades.Propiedades prop = new ListaPropiedades.Propiedades();
    lista.setPropiedades(prop);

    lista.getPropiedades().getPropiedades()
        .add(createPropiedad(EXPEDIENTE, "versionNTI", expediente.getMetadatos().getVersionNTI()));

    lista.getPropiedades().getPropiedades()
        .add(createPropiedad(EXPEDIENTE, "identificador", expediente.getIdentificador()));
    lista.getPropiedades().getPropiedades()
        .add(createPropiedad(EXPEDIENTE, "organos", InsideUtils.listaToString(
            expediente.getMetadatos().getOrgano().toArray(new String[] {}), "; ", false, true)));
    lista.getPropiedades().getPropiedades().add(
        createPropiedad(EXPEDIENTE, "clasificacion", expediente.getMetadatos().getClasificacion()));
    lista.getPropiedades().getPropiedades()
        .add(createPropiedad(EXPEDIENTE, "fechaAperturaExpediente", InsideUtils
            .calendarToStringISO8601(expediente.getMetadatos().getFechaAperturaExpediente())));

    lista.getPropiedades().getPropiedades()
        .add(createPropiedad(EXPEDIENTE, "estado", expediente.getMetadatos().getEstado().value()));

    lista.getPropiedades().getPropiedades()
        .add(createPropiedad(EXPEDIENTE, "interesados",
            InsideUtils.listaToString(
                expediente.getMetadatos().getInteresado().toArray(new String[] {}), "; ", false,
                true)));

    // listado de metadatos adicionales
    if (CollectionUtils.isNotEmpty(expediente.getMetadatos().getMetadatosAdicionales())) {
      for (ObjetoInsideMetadatoAdicional data : expediente.getMetadatos()
          .getMetadatosAdicionales()) {
        lista.getPropiedades().getPropiedades().add(
            createPropiedad(EXP_METADATO_ADICIONAL, data.getNombre(), data.getValor().toString()));
      }
    }

    return lista;
  }

  private ListaPropiedades getPropiedadesDocumento(ObjetoDocumentoInside documento) {
    ListaPropiedades lista = new ListaPropiedades();
    ListaPropiedades.Propiedades prop = new ListaPropiedades.Propiedades();
    lista.setPropiedades(prop);

    lista.getPropiedades().getPropiedades()
        .add(createPropiedad(DOCUMENTO, "versionNTI", documento.getMetadatos().getVersionNTI()));
    lista.getPropiedades().getPropiedades()
        .add(createPropiedad(DOCUMENTO, "identificador", documento.getIdentificador()));
    lista.getPropiedades().getPropiedades()
        .add(createPropiedad(DOCUMENTO, "organo", InsideUtils.listaToString(
            documento.getMetadatos().getOrgano().toArray(new String[] {}), "; ", false, true)));
    lista.getPropiedades().getPropiedades().add(createPropiedad(DOCUMENTO, "fechaCaptura",
        InsideUtils.calendarToStringISO8601(documento.getMetadatos().getFechaCaptura())));
    lista.getPropiedades().getPropiedades()
        .add(createPropiedad(DOCUMENTO, "origenCiudadanoAdministracion",
            getOrigen(documento.getMetadatos().isOrigenCiudadanoAdministracion())));
    lista.getPropiedades().getPropiedades().add(createPropiedad(DOCUMENTO, "estadoElaboracion",
        documento.getMetadatos().getEstadoElaboracion().getValorEstadoElaboracion().value()));
    lista.getPropiedades().getPropiedades().add(createPropiedad(DOCUMENTO, "tipoDocumental",
        documento.getMetadatos().getTipoDocumental().value()));

    // listado de metadatos adicionales
    if (CollectionUtils.isNotEmpty(documento.getMetadatos().getMetadatosAdicionales())) {
      for (ObjetoInsideMetadatoAdicional data : documento.getMetadatos()
          .getMetadatosAdicionales()) {
        lista.getPropiedades().getPropiedades().add(
            createPropiedad(DOC_METADATO_ADICIONAL, data.getNombre(), data.getValor().toString()));
      }
    }

    return lista;
  }

  private Item getItemFirmaCSV(Item padre, FirmaInside firmaInside) {
    Item firma = new Item();
    firma.setIdentificador("ID_" + UUID.randomUUID());
    firma.setNombre("TF01 - CSV");
    firma.setPadre(padre);

    ListaPropiedades lista = new ListaPropiedades();
    ListaPropiedades.Propiedades prop = new ListaPropiedades.Propiedades();
    lista.setPropiedades(prop);

    ContenidoFirmaCSVInside contenidoFirma =
        (ContenidoFirmaCSVInside) firmaInside.getContenidoFirma();
    lista.getPropiedades().getPropiedades()
        .add(createPropiedad(FIRMA_CSV, "valorCSV", contenidoFirma.getValorCSV()));
    lista.getPropiedades().getPropiedades().add(
        createPropiedad(FIRMA_CSV, "regulacionCSV", contenidoFirma.getRegulacionGeneracionCSV()));

    firma.setPropiedadesItem(lista);

    return firma;
  }

  private Item getItemFirmaNoCSV(Item padre, String tipoFirma, InfoFirmante infoFirmante) {
    Item firma = new Item();

    firma.setIdentificador("ID_" + UUID.randomUUID());
    firma.setNombre(getTipoFirma(tipoFirma));
    firma.setPadre(padre);

    ListaPropiedades lista = new ListaPropiedades();
    ListaPropiedades.Propiedades prop = new ListaPropiedades.Propiedades();
    lista.setPropiedades(prop);

    lista.getPropiedades().getPropiedades()
        .add(createPropiedad(FIRMA_NO_CSV, "descripcion", infoFirmante.getDescripcion()));
    lista.getPropiedades().getPropiedades()
        .add(createPropiedad(FIRMA_NO_CSV, "fecha", infoFirmante.getFecha()));

    firma.setPropiedadesItem(lista);

    return firma;
  }

  private String getTipoFirma(String tipo) {

    String result = "";
    if ("XADES DETACHED".equalsIgnoreCase(tipo)) {
      result = "TF02 - XAdES internally detached";
    } else if ("XADES ENVELOPED".equalsIgnoreCase(tipo)) {
      result = "TF03 - XAdES enveloped";
    } else if ("CADES Explicit".equalsIgnoreCase(tipo)) {
      result = "TF04 - CAdES Explicit";
    } else if ("CADES".equalsIgnoreCase(tipo)) {
      result = "TF05 - CAdES attached";
    } else if ("PADES".equalsIgnoreCase(tipo)) {
      result = "TF06 - PAdES";
    }

    return result;
  }

  private String getOrigen(boolean origen) {
    String sOrigen = "Ciudadano";

    if (origen) {
      sOrigen = "Administración";
    }
    return sOrigen;
  }

  /**
   * Construye la lista de propiedades de un documento que aparecerán en la visualización del
   * índice.
   * 
   * @param documentoIndizado
   * @return
   */
  private ListaPropiedades getPropiedadesDocumentoIndizado(
      ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado documentoIndizado, int orden) {
    ListaPropiedades lista = new ListaPropiedades();
    ListaPropiedades.Propiedades prop = new ListaPropiedades.Propiedades();
    lista.setPropiedades(prop);

    // Queremos que el orden sea absoluto, así que no nos vale el orden del
    // documento, que es relativo a la carpeta donde está.
    lista.getPropiedades().getPropiedades()
        .add(createPropiedad(DOCUMENTO_INDIZADO, "orden", Integer.toString(orden)));
    lista.getPropiedades().getPropiedades()
        .add(createPropiedad(DOCUMENTO_INDIZADO, "huella", documentoIndizado.getValorHuella()));
    return lista;
  }

  /**
   * Obtiene el nombre que se mostrará de un documento Indizado. Si el expediente es externo a
   * InSide (no lo tenemos almacenado), se meterá el identificador del documento. Si el expediente
   * es interno (lo tenemos almacenado): si el documento tiene el Metadato Adicional
   * "DescripcionDocumento" se enviará ese, en caso contrario se enviará el identificador del
   * documento indizado.
   */
  private String getNombreDocumentoIndizado(
      ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado documentoIndizado, boolean externo)
      throws InsideServiceVisualizacionException {

    String nombreDocumento;
    if (externo) {
      nombreDocumento = "[" + documentoIndizado.getIdentificadorDocumento() + "]";
    } else {
      ObjetoDocumentoInsideMetadatos metadatosDocumento = null;
      try {
        metadatosDocumento = insideStore.getObjectMetadatos(ObjetoDocumentoInside.class,
            documentoIndizado.getIdentificadorDocumento());

      } catch (InsideStoreObjectNotFoundException e) {
        throw new InsideServiceVisualizacionException(
            "Error al obtener la descripcion del documento con identificador "
                + documentoIndizado.getIdentificadorDocumento(),
            e);
      } catch (InsideServiceStoreException e) {
        throw new InsideServiceVisualizacionException(
            "Error al obtener la descripcion del documento con identificador "
                + documentoIndizado.getIdentificadorDocumento(),
            e);
      }

      nombreDocumento = InsideObjectsUtils.getMetadatoAdicionalNotNull(metadatosDocumento,
          "DescripcionDocumento");
      nombreDocumento =
          nombreDocumento + " [" + documentoIndizado.getIdentificadorDocumento() + "]";
    }

    return nombreDocumento;

  }

  /**
   * Crea una propiedad con una clave y un valor.
   * 
   * @param tipo
   * @param key
   * @param value
   * @return
   */
  private Propiedad createPropiedad(String tipo, String key, String value) {
    Propiedad p = null;
    boolean addALLMetadatos = false;
    if (StringUtils.equalsIgnoreCase(tipo, DOC_METADATO_ADICIONAL) && StringUtils
        .equalsIgnoreCase(getEnviarVisualizacion(DOCUMENTO, ADD_ALL_METADATOS), VALUE_SI)) {
      addALLMetadatos = true;
    }
    if (StringUtils.equalsIgnoreCase(tipo, EXP_METADATO_ADICIONAL) && StringUtils
        .equalsIgnoreCase(getEnviarVisualizacion(EXPEDIENTE, ADD_ALL_METADATOS), VALUE_SI)) {
      addALLMetadatos = true;
    }
    if (addALLMetadatos) {
      p = new Propiedad();
      p.setClave(getCabeceraVisualizacion(tipo, key));
      p.setValor(value);
    } else {
      if (StringUtils.equalsIgnoreCase(getEnviarVisualizacion(tipo, key), VALUE_SI)) {
        p = new Propiedad();
        p.setClave(getCabeceraVisualizacion(tipo, key));
        p.setValor(value);
      }
    }
    return p;
  }

  /**
   * Crea las opciones de visualización del WS a partir de las opciones de visualizacion de InSide.
   * 
   * @return
   */
  private OpcionesVisualizacion getOpcionesVisualizacionWS(OpcionesVisualizacionIndice opciones,
      String modelo) throws InsideServiceVisualizacionException {

    if (opciones == null) {
      throw new InsideServiceVisualizacionException(
          "No se pueden obtener las opciones de visualizacion del WS porque el parámetro es nulo");
    }
    OpcionesVisualizacion oVisualizacionWS = new OpcionesVisualizacion();
    OpcionesLogo oLogo = new OpcionesLogo();
    oLogo.setEstamparLogo(opciones.isEstamparImagen());
    oLogo.setEstamparNombreOrganismo(opciones.isEstamparNombreOrganismo());

    ListaCadenas listaCadenas = new ListaCadenas();
    ListaCadenas.Cadenas cadenas = new ListaCadenas.Cadenas();
    listaCadenas.setCadenas(cadenas);

    oLogo.setEstamparPie(opciones.isEstamparPie());
    oLogo.setTextoPie(opciones.getTextoPie());

    // Si se ha marcado la opcion de estampar el nombre del organismo y el
    // nombre no se envía, se estampa el nombre por defecto.
    if (opciones.isEstamparNombreOrganismo()) {
      if (opciones.getLineasNombreOrganismo() != null
          && CollectionUtils.isNotEmpty(opciones.getLineasNombreOrganismo())) {
        listaCadenas.getCadenas().getCadenas().addAll(opciones.getLineasNombreOrganismo());
      } else {
        listaCadenas.getCadenas().getCadenas().addAll(filasNombreOrganismo);
      }
    } else {
      // TODO- Hay que cambiar el eeutil para que si el parámetro
      // "estamparNombreOrganismo" es false no estampe nada y no falle.
      listaCadenas.getCadenas().getCadenas().add("");
    }

    oLogo.setListaCadenasNombreOrganismo(listaCadenas);
    oVisualizacionWS.setOpcionesLogo(oLogo);
    oVisualizacionWS.setModelo(modelo);

    return oVisualizacionWS;

  }

  @Override
  public Item documentoInsideToItemVisualizacion(ObjetoDocumentoInside documento,
      byte[] bytesContenido) throws InsideServiceVisualizacionException {
    Item padre = new Item();
    padre.setIdentificador(documento.getIdentificador());
    padre.setNombre(documento.getIdentificador());
    padre.setPropiedadesItem(getPropiedadesDocumento(documento));

    fillItemWithInfoDocumento(padre, documento, bytesContenido);

    return padre;
  }

  public Properties getProperties() {
    return properties;
  }

  @Required
  public void setProperties(Properties properties) {
    this.properties = properties;
  }

  private String getEnviarVisualizacion(String tipo, String campo) {
    String retorno = VALUE_NO;
    StringBuilder tmpBuff = new StringBuilder("visualizacion.");
    tmpBuff.append(tipo);
    tmpBuff.append(".");
    tmpBuff.append(campo);
    tmpBuff.append(".enviar");
    if (StringUtils.isNotEmpty(properties.getProperty(tmpBuff.toString()))) {
      retorno = properties.getProperty(tmpBuff.toString());
    }
    return retorno;
  }

  private String getCabeceraVisualizacion(String tipo, String campo) {
    String retorno = campo;
    StringBuilder tmpBuff = new StringBuilder("visualizacion.");
    tmpBuff.append(tipo);
    tmpBuff.append(".");
    tmpBuff.append(campo);
    tmpBuff.append(".cabecera");
    if (StringUtils.isNotEmpty(properties.getProperty(tmpBuff.toString()))) {
      retorno = properties.getProperty(tmpBuff.toString());
    }
    return retorno;
  }

  @Override
  public byte[] visualizarContenidoOriginal(ObjetoDocumentoInside documento)
      throws InsideServiceVisualizacionException {

    if (!activo) {
      throw new InsideServiceVisualizacionException(
          "El WS de VISUALIZACION no se encuentra activo");
    }

    // Construimos los parámetros de la llamada al servicio.
    Item itemDocumento = documentoInsideToItemVisualizacion(documento, null);

    SalidaVisualizacion salida = null;
    try {
      // Llamada al servicio
      salida = port.visualizarContenidoOriginal(applicationLogin, itemDocumento);
    } catch (Exception t) {
      throw new InsideServiceVisualizacionException("Error al obtener la visualización del índice",
          t);
    }

    return salida.getDocumentoContenido().getBytesDocumento();

  }

  @Override
  public List<String> obtenerPlantillas() throws InsideServiceVisualizacionException {
    List<String> retorno = new ArrayList<String>();
    try {
      retorno.add(Constantes.TEMPLATE_DEFAULT);

      List<Plantilla> plantillasEeutil = port.obtenerPlantillas(applicationLogin);

      if (CollectionUtils.isNotEmpty(plantillasEeutil)) {
        for (Plantilla plantilla : plantillasEeutil) {
          retorno.add(plantilla.getIdenticador());
        }
      }

      return retorno;
    } catch (InSideException e) {
      throw new InsideServiceVisualizacionException(e.getMessage(), e);
    }
  }

  @Override
  public TipoResultadoVisualizacionDocumentoInside visualizacionConPlantilla(String plantilla,
      TipoDocumentoVisualizacionInside tipoDocumentoVisualizacionInside)
      throws InsideServiceVisualizacionException {
    try {
      TipoResultadoVisualizacionDocumentoInside retorno =
          new TipoResultadoVisualizacionDocumentoInside();

      logger.debug("Convirtiendo a documento Inside");
      ObjetoDocumentoInside documentoInside =
          InsideConverterDocumento.documentoVisualizacionToInside(tipoDocumentoVisualizacionInside);

      DocumentoEniConMAdicionales docEniAdicionales =
          InsideConverterDocumento.convertToEeutil(documentoInside);

      SalidaVisualizacion salidaVisualizacion =
          port.visualizarDocumentoConPlantilla(applicationLogin, docEniAdicionales, plantilla);
      retorno.setContenido(salidaVisualizacion.getDocumentoContenido().getBytesDocumento());
      retorno.setMime(salidaVisualizacion.getDocumentoContenido().getMimeDocumento());
      return retorno;
    } catch (InSideException e) {
      throw new InsideServiceVisualizacionException(e.getMessage(), e);
    } catch (InsideConverterException e) {
      throw new InsideServiceVisualizacionException(e.getMessage(), e);
    }
  }
}
