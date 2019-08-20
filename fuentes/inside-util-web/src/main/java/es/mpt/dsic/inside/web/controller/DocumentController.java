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

package es.mpt.dsic.inside.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.SocketException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import es.mpt.dsic.eeutil.client.visDocExp.model.Item;
import es.mpt.dsic.inside.model.converter.InsideConverterDocumento;
import es.mpt.dsic.inside.model.converter.InsideConverterExpediente;
import es.mpt.dsic.inside.model.converter.InsideConverterFirmas;
import es.mpt.dsic.inside.model.converter.InsideConverterMetadatos;
import es.mpt.dsic.inside.model.converter.exception.InsideConverterUtilsException;
import es.mpt.dsic.inside.model.converter.mime.InsideMimeUtils;
import es.mpt.dsic.inside.model.objetos.ObjetoAuditoriaFirmaServidor;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideDocumentoUnidad;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideMetadatoAdicional;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideUnidadAplicacionEeutil;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInside;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInsideContenido;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteInside;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndice;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenido;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenidoElementoIndizado;
import es.mpt.dsic.inside.model.objetos.firmas.FirmaInside;
import es.mpt.dsic.inside.model.objetos.firmas.FirmaInsideTipoFirmaEnum;
import es.mpt.dsic.inside.model.objetos.usuario.ObjetoInsideUsuario;
import es.mpt.dsic.inside.service.InSideService;
import es.mpt.dsic.inside.service.InsideUtilService;
import es.mpt.dsic.inside.service.exception.InSideServiceException;
import es.mpt.dsic.inside.service.exception.InSideServiceTemporalDataException;
import es.mpt.dsic.inside.service.exception.InsideServiceInternalException;
import es.mpt.dsic.inside.service.object.converter.impl.InsideServiceAdapterException;
import es.mpt.dsic.inside.service.object.converter.impl.csvstorage.InsideServiceCsvStorageAdapter;
import es.mpt.dsic.inside.service.object.metadatos.validator.Exception.InSideServiceValidationException;
import es.mpt.dsic.inside.service.object.signer.InsideServiceSigner;
import es.mpt.dsic.inside.service.object.validators.exception.InsideServiceObjectValidationException;
import es.mpt.dsic.inside.service.store.exception.InsideStoreObjectNotFoundException;
import es.mpt.dsic.inside.service.store.exception.InsideStoreObjectVinculatedException;
import es.mpt.dsic.inside.service.temporalData.TemporalDataBusinessService;
import es.mpt.dsic.inside.service.util.Constantes;
import es.mpt.dsic.inside.service.util.InsideUtils;
import es.mpt.dsic.inside.service.util.InsideWSUtils;
import es.mpt.dsic.inside.service.util.WebConstants;
import es.mpt.dsic.inside.service.util.XMLUtils;
import es.mpt.dsic.inside.service.utilFirma.InsideServiceUtilFirma;
import es.mpt.dsic.inside.service.visualizacion.InsideServiceVisualizacion;
import es.mpt.dsic.inside.service.visualizacion.exception.InsideServiceVisualizacionException;
import es.mpt.dsic.inside.util.xml.JAXBMarshaller;
import es.mpt.dsic.inside.web.object.ComboItem;
import es.mpt.dsic.inside.web.object.MessageObject;
import es.mpt.dsic.inside.web.object.VisualizacionItem;
import es.mpt.dsic.inside.web.util.ComboUtils;
import es.mpt.dsic.inside.web.util.MessageUtils;
import es.mpt.dsic.inside.web.util.MetadatosEEMGDE;
import es.mpt.dsic.inside.web.util.SignatureUtils;
import es.mpt.dsic.inside.web.util.VisualizacionUtils;
import es.mpt.dsic.inside.ws.exception.InsideWSException;
import es.mpt.dsic.inside.ws.exception.InsideWsErrors;
import es.mpt.dsic.inside.ws.util.InSideDateAdapter;
import es.mpt.dsic.inside.ws.validation.exception.InsideValidationDataException;
import es.mpt.dsic.inside.xml.eni.documento.TipoDocumento;
import es.mpt.dsic.inside.xml.eni.documento.metadatos.TipoDocumental;
import es.mpt.dsic.inside.xml.eni.expediente.TipoExpediente;
import es.mpt.dsic.inside.xml.inside.MetadatoAdicional;
import es.mpt.dsic.inside.xml.inside.TipoDocumentoInsideConMAdicionales;
import es.mpt.dsic.inside.xml.inside.TipoMetadatosAdicionales;
import es.mpt.dsic.inside.xml.inside.ws.credential.WSCredentialInside;
import es.mpt.dsic.inside.xml.inside.ws.documento.conversion.TipoDocumentoConversionInside;
import es.mpt.dsic.inside.xml.inside.ws.documento.conversion.TipoDocumentoConversionInside.Csv;
import es.mpt.dsic.inside.xml.inside.ws.validacion.documento.TipoDocumentoValidacionInside;
import es.mpt.dsic.inside.xml.inside.ws.validacion.documento.TipoOpcionValidacionDocumento;
import es.mpt.dsic.inside.xml.inside.ws.validacion.documento.TipoOpcionesValidacionDocumentoInside;
import es.mpt.dsic.inside.xml.inside.ws.validacion.documento.resultados.TipoResultadoValidacionDocumentoInside;
import es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.TipoDocumentoEniBinarioOTipo;
import es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.TipoDocumentoVisualizacionInside;
import es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.TipoOpcionesVisualizacionDocumento;
import es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.TipoResultadoVisualizacionDocumentoInside;

/**
 * @author juan-jose.sevillano
 *
 */
@Controller
public class DocumentController {

  protected static final Log logger = LogFactory.getLog(DocumentController.class);

  @Autowired
  private MessageSource messageSource;

  @Autowired
  private InsideUtilService insideUtilService;

  @Autowired
  private InSideService insideService;

  @Autowired
  private InsideServiceVisualizacion insideServiceVisualizacion;

  @Autowired
  private JAXBMarshaller marshaller;

  private List<String> organos;

  @Autowired
  private SignatureUtils signatureUtils;

  @Autowired
  private TemporalDataBusinessService temporalDataBusinessService;

  @Autowired
  private AutocompleteController autocompleteController;

  @Autowired
  private JAXBMarshaller jAXBMarshaller;

  @Autowired
  private InsideServiceSigner serviceSigner;

  @Autowired
  VisualizacionUtils visualizacionUtils;

  @Autowired
  private InsideServiceUtilFirma utilFirmaService;


  @Autowired
  InsideServiceCsvStorageAdapter csvStorageAdapter;

  private boolean existDocumentIndex;

  private static final String MENSAJE_USU = "mensajeUsuario";
  private static final String MNJ_ERROR_SAVE_DOCU = "Error al guardar documento";
  private static final String MNJ_ERROR_DOCU = "Error al importar documento";
  private static final String FIRMA = "firma";
  private static final String SIGNATURE_SERVER = "firmaServidor";
  private static final String FORMAT_FIRM = "formatoFirma";
  private static final String DOCUMENT_ID = "documentId";
  private static final String REGULACION_CSV = "BOE-A-2014-3729";
  private static final String CONTENT_DISPOSITION = "Content-Disposition";
  private static final String FIRMA_RESULTADO = "firmaResultado";
  private static final String SHOW_MESSAGE = "showMessage";
  private static final String SHIW_FIRMA_SERVER = "showFirmaServer";
  private static final String ERROR_GUARDAR = "errorGuardar";
  private static final String INDEX_EXP_B64 = "indexExpB64";
  private static final String VIEW_GENERATE_DOC = "documento/generarDocumento";
  private static final String NAMESPACE_DOCUMENTO_ELEC =
      "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e";
  private static final String NAMESPACE_WEBSERVICE =
      "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService";
  private static final String IDENTIFICADOR = "identificador";
  private static final String NAVEGADOR = "navegador";
  private static final String DOCUMENTO = "documento";
  private static final String DATA_DOC_BASE64 = "dataDocBase64";
  private static final String DOCUMENTO_ENI = "documentoEni";
  private static final String METADATO_ADICIONALES = "metadatoAdicionales";
  private static final String FECHA_CAPTURA = "fechaCaptura";
  private static final String DOCUMENTOS = "documentos";
  private static final String ATTACHMENT_FILENAME = "attachment; filename=\"";
  private static final String ERROR_AL_DESCARGAR_DOCUMENTO = "Error al descargar documento";
  private static final String DOCUMENT_CONTROLLER_GENERAR_DOCUMENTO_POST =
      "DocumentController.generarDocumento";
  private static final String ERROR_AL_VALIDAR_DOCUMENTO = "Error al validar documento";

  public static final BigInteger UN_KB = BigInteger.valueOf(1024);
  public static final BigInteger DOS_MB = UN_KB.multiply(UN_KB).multiply(BigInteger.valueOf(2));

  @RequestMapping(value = "/generarDocumento", method = RequestMethod.GET)
  public ModelAndView generarDocumento(Locale locale, HttpServletRequest request) {
    this.organos = null;
    ModelAndView retorno = new ModelAndView(VIEW_GENERATE_DOC);
    init(retorno, locale, request);

    TipoDocumentoConversionInside documentoEni = new TipoDocumentoConversionInside();
    TipoDocumentoConversionInside.MetadatosEni metadatos =
        new TipoDocumentoConversionInside.MetadatosEni();
    metadatos.setIdentificador(generateDefaultId(request));
    documentoEni.setMetadatosEni(metadatos);
    retorno.addObject(DOCUMENTO_ENI, documentoEni);

    retorno.addObject(FIRMA, "0");
    // retorno.addObject(FORMAT_FIRM, WebConstants.TYPE_CERTIFICATE_PADES);
    retorno.addObject(FORMAT_FIRM, WebConstants.TYPE_CERTIFICATE_DEFAULT);
    retorno.addObject(METADATO_ADICIONALES, new TipoMetadatosAdicionales());
    retorno.addObject(SHOW_MESSAGE, true);
    retorno.addObject("metadatoNombreNatural",
        MetadatosEEMGDE.METADATO_NOMBRE_NOMBRE_NATURAL.getValue());

    return retorno;
  }

  @RequestMapping(value = "/generarDocumento", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> generarDocumentoPost(
      @ModelAttribute TipoDocumentoConversionInside documentoEni, HttpServletRequest request,
      Locale locale, @RequestParam("metadatosAdicionalesDoc") String metadatosAdicionales,
      HttpSession session) {
    Map<String, Object> retorno = new HashMap<String, Object>();

    MessageObject mensaje = null;
    TipoMetadatosAdicionales aditionalData = new TipoMetadatosAdicionales();
    try {
      retorno.put(ERROR_GUARDAR, true);
      TipoDocumentoInsideConMAdicionales docConvertido = generateDocument(documentoEni, request,
          locale, metadatosAdicionales, session, retorno, aditionalData, true);
      if (!insideUtilService.esLongitudIdentificadorValido(docConvertido.getDocumento().getId())) {
        throw new InsideWSException(InsideWsErrors.IDENTIFICADOR_DOCUMENTO_LONGITUD, null, "");
      }
      setXmlTemporal(documentoEni, session, aditionalData, docConvertido);

      if (StringUtils.isNotEmpty(request.getParameter(SHOW_MESSAGE))
          && Boolean.valueOf(request.getParameter(SHOW_MESSAGE))) {
        String mensajeOk;
        if (docConvertido.getDocumento().getFirmas() != null
            && CollectionUtils.isNotEmpty(docConvertido.getDocumento().getFirmas().getFirma()))
          mensajeOk = WebConstants.MSG_GENERAR_DOC_FIRMADO_OK;
        else
          mensajeOk = WebConstants.MSG_GENERAR_DOC_OK;
        mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_SUCCESS,
            messageSource.getMessage(mensajeOk, null, locale));
        retorno.put(MENSAJE_USU, mensaje);
      }

      ObjetoInsideUsuario usuario =
          (ObjetoInsideUsuario) request.getSession().getAttribute(WebConstants.USUARIO_SESSION);
      retorno.put(SHIW_FIRMA_SERVER, insideService.checkSignatureServerByUser(usuario));
    } catch (InsideWSException e) {
      logger.error("DocumentController.generarDocumentoPost --> Error al guardar expediente", e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
          messageSource.getMessage(WebConstants.MSG_IMPORTAR_DOC_ID_NO_VALIDO, null, locale));
      retorno.put(MENSAJE_USU, mensaje);
    } catch (InsideServiceInternalException e) {
      logger.warn(DOCUMENT_CONTROLLER_GENERAR_DOCUMENTO_POST, e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      retorno.put(MENSAJE_USU, mensaje);
    } catch (Exception e) {
      logger.error("Error al generar documento", e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
          messageSource.getMessage(WebConstants.MSG_KEY_ERROR_GENERIC, null, locale));
      retorno.put(MENSAJE_USU, mensaje);
    }

    return retorno;
  }

  public TipoDocumentoInsideConMAdicionales generateDocument(
      TipoDocumentoConversionInside documentoEni, HttpServletRequest request, Locale locale,
      String metadatosAdicionales, HttpSession session, Map<String, Object> retorno,
      TipoMetadatosAdicionales aditionalData, boolean validateContent)
      throws InsideServiceInternalException {
    TipoDocumentoInsideConMAdicionales tipoDoc = null;
    try {

      validateData(documentoEni, locale, request.getParameter(DOCUMENT_ID), validateContent);
      String documentId = null;
      if (validateContent) {
        documentId =
            temporalDataBusinessService.getPath(session.getId(), request.getParameter(DOCUMENT_ID));
      } else {
        documentId = documentoEni.getContenidoId();
      }
      String signId = extraerFirmaResultado(request, session);

      boolean firmaCsv = false;
      if (StringUtils.isNotEmpty(request.getParameter("firmaCsv"))) {
        firmaCsv = true;
      }

      setData(documentoEni, request, documentId, session, signId, firmaCsv);
      setAdicionales(metadatosAdicionales, aditionalData);
      ObjetoDocumentoInside docInside = getDataSigned(documentoEni, session, signId);
      retorno.put(IDENTIFICADOR, documentoEni.getMetadatosEni().getIdentificador());

      // le paso el getvalorbinario del contenido
      byte[] contenidoParam = null;
      if (docInside != null && docInside.getContenido() != null)
        contenidoParam = docInside.getContenido().getValorBinario();
      tipoDoc = insideUtilService.convertirDocumentoAEniConMAdicionales(documentoEni, aditionalData,
          contenidoParam, false, null, docInside);

    } catch (InsideServiceObjectValidationException e1) {
      logger.warn(DOCUMENT_CONTROLLER_GENERAR_DOCUMENTO_POST, e1);
      throw new InsideServiceInternalException(
          MessageUtils.contruirMensaje(locale, e1, messageSource).getMessage());
    } catch (InsideValidationDataException e2) {
      logger.warn(DOCUMENT_CONTROLLER_GENERAR_DOCUMENTO_POST, e2);
      MessageObject mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e2.getMessage());
      throw new InsideServiceInternalException(mensaje.getMessage());
    } catch (InsideServiceInternalException e3) {
      logger.warn(DOCUMENT_CONTROLLER_GENERAR_DOCUMENTO_POST, e3);
      MessageObject mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e3.getMessage());
      throw new InsideServiceInternalException(mensaje.getMessage());
    } catch (Exception e3) {
      logger.error(DOCUMENT_CONTROLLER_GENERAR_DOCUMENTO_POST, e3);
      MessageObject mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e3.getMessage());
      throw new InsideServiceInternalException(mensaje.getMessage());
    }
    return tipoDoc;
  }

  public void setXmlTemporal(TipoDocumentoConversionInside documentoEni, HttpSession session,
      TipoMetadatosAdicionales aditionalData, TipoDocumentoInsideConMAdicionales docConvertido)
      throws InsideServiceInternalException {
    try {
      if (CollectionUtils.isNotEmpty(aditionalData.getMetadatoAdicional())) {
        String pathFicheroDocENIConAdicionales =
            temporalDataBusinessService.getPath(session.getId(),
                documentoEni.getMetadatosEni().getIdentificador() + WebConstants.FORMAT_XML);

        marshaller.marshallDataDocumentAndWriteFile(docConvertido, pathFicheroDocENIConAdicionales,
            NAMESPACE_WEBSERVICE, DOCUMENTO, "insidews");
      } else {
        String pathFicheroDocENI = temporalDataBusinessService.getPath(session.getId(),
            documentoEni.getMetadatosEni().getIdentificador() + WebConstants.FORMAT_XML);

        marshaller.marshallDataDocumentAndWriteFile(docConvertido.getDocumento(), pathFicheroDocENI,
            NAMESPACE_DOCUMENTO_ELEC, DOCUMENTO, "enidoc");
      }
    } catch (Exception e3) {
      logger.error(messageSource.getMessage(WebConstants.MSG_GENERAR_DOC_ERROR_XML, null, null),
          e3);
      throw new InsideServiceInternalException(
          messageSource.getMessage(WebConstants.MSG_GENERAR_DOC_ERROR_XML, null, null));
    }
  }

  private String generateDefaultId(HttpServletRequest request) {
    String defaultId = "";
    if (request.getContextPath().contains(WebConstants.CONTEXT_PATH_INSIDE)) {
      ObjetoInsideUsuario usuario =
          (ObjetoInsideUsuario) request.getSession().getAttribute(WebConstants.USUARIO_SESSION);
      if (usuario != null && usuario.getUnidadOrganicaActiva() != null) {
        String unidadOrganica = usuario.getUnidadOrganicaActiva();
        unidadOrganica = unidadOrganica.substring(0, unidadOrganica.indexOf(" "));
        defaultId = insideUtilService.generateDefaultId(unidadOrganica, true);
      }
    }
    return defaultId;
  }

  @RequestMapping(value = "/comprobarIdentificadorDocumento", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> comprobarIdentificadorDocumentoPost(
      @RequestParam("identificadorDocumento") String identificadorDocumento) {

    logger.debug("Iniciado DocumentController.comprobarIdentificadorDocumentoPos");

    Map<String, Object> retorno = new HashMap<String, Object>();
    MessageObject mensaje = null;
    ObjetoDocumentoInside doc = null;

    try {

      doc = insideService.getDocumento(identificadorDocumento);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_INFO,
          "El identificador " + doc.getIdentificador() + " de documento ya existe");
      retorno.put("mensajeIdentificador", mensaje);
      retorno.put("identificadorDocumento", identificadorDocumento);

    } catch (InSideServiceException e) {
      logger.debug("No existe el identificador", e);
      // Salta a la excepcion si no existe el identificador.
      if (StringUtils.isBlank(identificadorDocumento)) {
        mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
            "Identificador vacio o blanco no válido");
        retorno.put(MENSAJE_USU, mensaje);
      }

    }

    logger.debug("Finalizado DocumentController.comprobarIdentificadorDocumentoPost");
    return retorno;
  }

  @RequestMapping(value = "/descargarDocumentoEni", method = RequestMethod.POST)
  public ModelAndView descargarDocumentoEni(@RequestParam(IDENTIFICADOR) String identificador,
      HttpServletResponse response, Locale locale, HttpSession session) {
    try {

      OutputStream pr = response.getOutputStream();
      response.setContentType("application/text");
      response.addHeader(CONTENT_DISPOSITION, ATTACHMENT_FILENAME + identificador + ".xml\"");

      pr.write(insideUtilService.lazyLoadXmlFile(session.getId(), identificador, true));
      pr.close();

      return null;
    } catch (SocketException e) {
      logger.warn(ERROR_AL_DESCARGAR_DOCUMENTO + ": " + identificador, e);
      return null;
    } catch (Exception e) {
      logger.error(ERROR_AL_DESCARGAR_DOCUMENTO + ": " + identificador, e);
      MessageObject mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
          messageSource.getMessage(WebConstants.MSG_KEY_ERROR_GENERIC, null, locale));
      ModelAndView retorno = new ModelAndView(VIEW_GENERATE_DOC);
      retorno.addObject(MENSAJE_USU, mensaje);
      return retorno;
    }

  }



  @RequestMapping(value = "/descargarContenidoDocumento", method = RequestMethod.POST)
  public ModelAndView descargarContenidoDocumento(@RequestParam(IDENTIFICADOR) String identificador,
      HttpServletResponse response, Locale locale, HttpSession session) {
    FileInputStream fin = null;
    ObjetoDocumentoInsideContenido contenido = null;
    try {
      // realizar nuevo metodo lazyLoadXmlContentFile que retorne ObjetoDocumentoInsideContenido
      contenido = insideUtilService.lazyLoadContentFile(session.getId(), identificador);

      if (contenido.getValorBinario() != null) {
        String identificadorCompleto = identificador + "." + contenido.getNombreFormato();

        OutputStream pr = response.getOutputStream();
        response.setContentType(contenido.getMime());
        response.addHeader(CONTENT_DISPOSITION, ATTACHMENT_FILENAME + identificadorCompleto);

        pr.write(contenido.getValorBinario());
        pr.close();
      } else {
        OutputStream pr = response.getOutputStream();

        contenido = insideUtilService.getExternalContent(identificador, session.getId());

        response.setContentType(contenido.getMime());
        response.addHeader(CONTENT_DISPOSITION, ATTACHMENT_FILENAME
            + contenido.getIdentificadorEnDocumento() + "." + contenido.getNombreFormato());
        File filedownload = new File(contenido.getReferencia());
        response.setContentLength((int) filedownload.length());

        fin = new FileInputStream(filedownload);

        byte[] buffer = new byte[4096]; // To hold file contents
        int bytes_read; // How many bytes in buffer
        while ((bytes_read = fin.read(buffer)) != -1)
          // Read until EOF
          pr.write(buffer, 0, bytes_read); // write

        pr.close();
      }

      return null;
    } catch (SocketException e) {
      logger.warn(ERROR_AL_DESCARGAR_DOCUMENTO + ": " + identificador, e);
      return null;
    } catch (Exception e) {
      logger.error(ERROR_AL_DESCARGAR_DOCUMENTO + ": " + identificador, e);
      MessageObject mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
          messageSource.getMessage(WebConstants.MSG_KEY_ERROR_GENERIC, null, locale));
      ModelAndView retorno = new ModelAndView(VIEW_GENERATE_DOC);
      retorno.addObject(MENSAJE_USU, mensaje);
      return retorno;
    } finally {
      try {
        if (fin != null) {
          fin.close();
          // borramos el fichero temporal descargado
          FileUtils.forceDelete(new File(contenido.getReferencia()));
        }
      } catch (IOException e) {
        logger.error(ERROR_AL_DESCARGAR_DOCUMENTO + ": " + identificador, e);
        MessageObject mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
            messageSource.getMessage(WebConstants.MSG_KEY_ERROR_GENERIC, null, locale));
        ModelAndView retorno = new ModelAndView(VIEW_GENERATE_DOC);
        retorno.addObject(MENSAJE_USU, mensaje);
        return retorno;
      }
    }
  }

  public boolean isFormatTif(String extension, String mime) {
    return StringUtils.isEmpty(extension)
        && StringUtils.containsIgnoreCase("." + mime, WebConstants.FORMAT_TIF);
  }

  public boolean isPades(byte[] dataBytes) {
    return dataBytes != null;
  }

  @RequestMapping(value = "/descargarVisualizar", method = RequestMethod.POST)
  public ModelAndView descargarVisualizar(@RequestParam(IDENTIFICADOR) String identificador,
      HttpServletResponse response, Locale locale, HttpSession session,
      HttpServletRequest request) {
    try {
      TipoDocumentoVisualizacionInside visualizar = new TipoDocumentoVisualizacionInside();
      TipoDocumentoEniBinarioOTipo docEniBinario = new TipoDocumentoEniBinarioOTipo();
      byte[] docEniBytes = insideUtilService.lazyLoadXmlFile(session.getId(), identificador, true);
      docEniBinario.setDocumentoEniBinario(docEniBytes);
      visualizar.setDocumentoEni(docEniBinario);
      TipoOpcionesVisualizacionDocumento opciones = new TipoOpcionesVisualizacionDocumento();
      opciones.setEstamparImagen(false);
      opciones.setEstamparNombreOrganismo(false);
      opciones.setEstamparPie(false);
      opciones.setFilasNombreOrganismo(null);
      opciones.setTextoPie(null);
      visualizar.setOpcionesVisualizacionDocumento(opciones);

      TipoDocumentoInsideConMAdicionales tDoc =
          marshaller.unmarshallDataDocumentAditional(docEniBytes);
      visualizar.setMetadatosAdicionales(tDoc.getMetadatosAdicionales());

      if (tDoc.getDocumento().getContenido().getValorBinario() != null) {
        TipoResultadoVisualizacionDocumentoInside resultado =
            insideUtilService.visualizarDocumentoEni(visualizar);

        byte[] data = resultado.getContenido();

        OutputStream pr = response.getOutputStream();
        response.setContentType("application/pdf");
        response.addHeader(CONTENT_DISPOSITION, ATTACHMENT_FILENAME + identificador + ".pdf\"");

        pr.write(data);
        pr.close();

        return null;
      } else {
        logger.warn("Error al descargar visualizar documento:" + ": " + identificador);
        MessageObject mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
            messageSource.getMessage(WebConstants.MSG_KEY_ERROR_GENERIC, null, locale));
        ModelAndView retorno = new ModelAndView(VIEW_GENERATE_DOC);
        retorno.addObject(MENSAJE_USU, mensaje);
        return editarDocumento(locale, identificador, "false", session, request);
      }
    } catch (Exception e) {
      logger.error(ERROR_AL_DESCARGAR_DOCUMENTO + ": " + identificador, e);
      MessageObject mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
          messageSource.getMessage(WebConstants.MSG_KEY_ERROR_GENERIC, null, locale));
      ModelAndView retorno = new ModelAndView(VIEW_GENERATE_DOC);
      retorno.addObject(MENSAJE_USU, mensaje);
      return retorno;
    }

  }

  @RequestMapping(value = "/validarDocumento", method = RequestMethod.GET)
  public ModelAndView validarDocumento() {
    return new ModelAndView("documento/validarDocumento");
  }

  @RequestMapping(value = "/validarDocumento", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> validarDocumentoPost(Locale locale, HttpServletRequest request,
      HttpSession session) {
    Map<String, Object> retorno = new HashMap<String, Object>();
    MessageObject mensaje;

    List<String> opcionesValidacion = new ArrayList<String>();
    if (request.getParameterValues("opcionesValidacion") != null)
      opcionesValidacion = Arrays.asList(request.getParameterValues("opcionesValidacion"));

    String documentId = request.getParameter(DOCUMENT_ID);
    TipoDocumentoValidacionInside documentoValidacion = new TipoDocumentoValidacionInside();
    TipoResultadoValidacionDocumentoInside resultado = null;

    documentoValidacion.setOpcionesValidacionDocumento(getOpcionesValidacion(opcionesValidacion));
    byte[] data = null;

    try {
      data = temporalDataBusinessService.getFile(session.getId(), documentId);
      documentoValidacion.setContenido(data);
      ObjetoDocumentoInside documentoInside =
          InsideConverterDocumento.tipoDocumentoValidacionToInside(documentoValidacion);
      if (!insideUtilService.esLongitudIdentificadorValido(documentoInside.getIdentificador())) {
        throw new InsideWSException(InsideWsErrors.IDENTIFICADOR_DOCUMENTO_LONGITUD, null, "");
      }
      resultado = insideUtilService.validarDocumentoEniFile(documentoValidacion);

    } catch (InsideWSException e) {
      logger.error("DocumentController.validarDocumentoPost --> Error al validar documento", e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
          messageSource.getMessage(WebConstants.MSG_IMPORTAR_DOC_ID_NO_VALIDO, null, locale));
      retorno.put(MENSAJE_USU, mensaje);
    } catch (InsideServiceInternalException e) {
      logger.warn(ERROR_AL_VALIDAR_DOCUMENTO, e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      retorno.put(MENSAJE_USU, mensaje);
    } catch (Exception e1) {
      logger.error(ERROR_AL_VALIDAR_DOCUMENTO, e1);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
          messageSource.getMessage(WebConstants.MSG_KEY_DOC_DOCENI_NO_VALIDO, null, locale));
      retorno.put(MENSAJE_USU, mensaje);
    }

    retorno.put("resultado", resultado);
    return retorno;
  }

  public TipoOpcionesValidacionDocumentoInside getOpcionesValidacion(
      List<String> opcionesValidacion) {
    TipoOpcionesValidacionDocumentoInside opciones = new TipoOpcionesValidacionDocumentoInside();
    if (opcionesValidacion.contains(TipoOpcionValidacionDocumento.TOVD_01.value())) {
      opciones.getOpcionValidacionDocumento().add(TipoOpcionValidacionDocumento.TOVD_01);
    }
    if (opcionesValidacion.contains(TipoOpcionValidacionDocumento.TOVD_02.value())) {
      opciones.getOpcionValidacionDocumento().add(TipoOpcionValidacionDocumento.TOVD_02);
    }
    if (opcionesValidacion.contains(TipoOpcionValidacionDocumento.TOVD_03.value())) {
      opciones.getOpcionValidacionDocumento().add(TipoOpcionValidacionDocumento.TOVD_03);
    }
    return opciones;
  }

  @RequestMapping(value = "/visualizarDocumento", method = RequestMethod.GET)
  public ModelAndView visualizarDocumento() {
    MessageObject mensaje = null;
    ModelAndView retorno = new ModelAndView("documento/visualizarDocumento");
    try {
      retorno.addObject("templates", insideServiceVisualizacion.obtenerPlantillas());
    } catch (InsideServiceVisualizacionException e) {
      logger.error("Error al obtener plantillas", e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      retorno.addObject(MENSAJE_USU, mensaje);
    }
    return retorno;
  }

  @RequestMapping(value = "/visualizarDocumento", method = RequestMethod.POST)
  public ModelAndView visualizarDocumentoPost(Locale locale,
      @RequestParam(NAVEGADOR) String navegador, HttpServletRequest request, HttpSession session) {
    MessageObject mensaje = null;
    ModelAndView retorno = new ModelAndView("documento/visualizarDocumento");
    try {
      retorno.addObject(NAVEGADOR, navegador);
      retorno.addObject("templates", insideServiceVisualizacion.obtenerPlantillas());
      visualizacionUtils.visualizarDocumento(retorno, session, request);
    } catch (IOException e) {
      logger.error("Error al visualizar documento", e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
          messageSource.getMessage(WebConstants.MSG_KEY_ERROR_GENERIC, null, locale));
      retorno.addObject(MENSAJE_USU, mensaje);
    } catch (InsideServiceVisualizacionException e) {
      logger.warn("Error al generar documento", e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getLocalizedMessage());
      retorno.addObject(MENSAJE_USU, mensaje);
    } catch (Exception e) {
      logger.error("Error al generar documento", e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
          messageSource.getMessage(WebConstants.MSG_KEY_ERROR_GENERIC, null, locale));
      retorno.addObject(MENSAJE_USU, mensaje);
    }
    return retorno;
  }


  @RequestMapping(value = "/visualizaDescargarContenidoDocumento", method = RequestMethod.POST)
  public ModelAndView visualizaDescargarContenidoDocumento(
      @RequestParam(IDENTIFICADOR) String identificador, HttpServletResponse response,
      Locale locale, HttpSession session) {
    try {

      byte[] data = insideUtilService.lazyLoadXmlFile(session.getId(), identificador, true);
      TipoDocumento docEni = jAXBMarshaller.unmarshallDataDocument(data);
      ObjetoDocumentoInsideContenido contenido = insideService
          .getDocumentoContenido(docEni.getMetadatos().getIdentificador(), null, session.getId());
      byte[] dataBytes = contenido.getValorBinario();
      String mime = contenido.getMime();
      String extension = "." + contenido.getNombreFormato();

      OutputStream pr = response.getOutputStream();
      response.setContentType(mime);
      String identificadorCompleto =
          StringUtils.isNotBlank(extension) ? identificador + extension : identificador;
      response.addHeader(CONTENT_DISPOSITION, ATTACHMENT_FILENAME + identificadorCompleto);

      pr.write(dataBytes);
      pr.close();

      return null;
    } catch (Exception e) {
      logger.error(ERROR_AL_DESCARGAR_DOCUMENTO + ": " + identificador, e);
      MessageObject mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
          messageSource.getMessage(WebConstants.MSG_KEY_ERROR_GENERIC, null, locale));
      ModelAndView retorno = new ModelAndView(VIEW_GENERATE_DOC);
      retorno.addObject(MENSAJE_USU, mensaje);
      return retorno;
    }

  }


  @RequestMapping(value = "/importarDocumento", method = RequestMethod.GET)
  public ModelAndView importarDocumento(HttpSession session) {
    ModelAndView retorno = new ModelAndView("documento/importarDocumento");
    ObjetoInsideUsuario usuario =
        (ObjetoInsideUsuario) session.getAttribute(WebConstants.USUARIO_SESSION);
    retorno.addObject(SHIW_FIRMA_SERVER, insideService.checkSignatureServerByUser(usuario));

    ///// cargar los documentos de la unidad porque el autocomplete de js no funciona en internet
    ///// Explorer ////////////////////////////////////////

    String listaExpedientesJSON = convertListToJSONString(usuario);

    retorno.addObject("listaExpedientesJSON", listaExpedientesJSON);

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    return retorno;
  }

  private String convertListToJSONString(ObjetoInsideUsuario usuario) {

    List<ComboItem> lista = autocompleteController.autocompleteExpedientes(usuario, true, true, "");


    ObjectMapper mapper = new ObjectMapper();
    SerializationConfig config = mapper.getSerializationConfig();
    config.setSerializationInclusion(Inclusion.NON_NULL);
    mapper.setSerializationConfig(config);

    String resultadoJSON = "";
    try {
      resultadoJSON = mapper.writeValueAsString(lista);

    } catch (JsonGenerationException e) {
      logger.error("Error convirtiendo lista a String JSON: " + e.getMessage());
    } catch (JsonMappingException e) {
      logger.error("Error convirtiendo lista a String JSON: " + e.getMessage());
    } catch (IOException e) {
      logger.error("Error convirtiendo lista a String JSON: " + e.getMessage());
    }


    return resultadoJSON;

  }


  @RequestMapping(value = "/updateIndexExp", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> updateIndexExp(Locale locale, HttpServletRequest request)
      throws InSideServiceException {
    Map<String, Object> retorno = new HashMap<String, Object>();
    ObjectMapper mapper = new ObjectMapper();

    try {
      MessageObject mensaje;
      String usuario =
          ((ObjetoInsideUsuario) request.getSession().getAttribute(WebConstants.USUARIO_SESSION))
              .getNif();
      if (StringUtils.isEmpty(request.getParameter(INDEX_EXP_B64))) {
        mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
            messageSource.getMessage(WebConstants.MSG_IMPORTAR_DOC_NO_VALIDO, null, locale));
        retorno.put(MENSAJE_USU, mensaje);
        retorno.put(ERROR_GUARDAR, true);
      } else {
        String indexExpB64 = mapper.readValue(request.getParameter(INDEX_EXP_B64), String.class);
        byte[] firmaIndice = Base64.decodeBase64(indexExpB64);

        String expedienteEniString =
            mapper.readValue(request.getParameter("expedienteEni"), String.class);
        TipoExpediente expedienteEni =
            marshaller.unmarshallDataExpedient(expedienteEniString.getBytes());
        ObjetoExpedienteInside expediente =
            InsideConverterExpediente.expedienteEniToInside(expedienteEni);

        expediente.getIndice().setFirmas(InsideConverterFirmas.firmasEniToInside(
            signatureUtils.setSignaturesExp(firmaIndice, expediente.getIdentificador())));
        // insideService.modificaExpedienteInside(expediente,
        // usuario, true);

        // por este camino siempre es false la firmaenservidor
        insideService.modificaExpedienteInside(expediente, false, usuario, true);
        mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_SUCCESS,
            messageSource.getMessage(WebConstants.MSG_IMPORTAR_DOC_EXP_OK, null, locale));
        retorno.put(MENSAJE_USU, mensaje);
      }
    } catch (Exception e) {
      throw new InsideServiceInternalException("Error al actualizar el indice del expediente", e);
    }

    return retorno;
  }

  private ObjetoExpedienteInside validarExpedienteUnidad(HttpServletRequest request,
      Map<String, Object> retorno, Locale locale) throws InsideServiceInternalException {
    boolean validar = true;
    String idJsonExpediente = request.getParameter("identificadorExpediente");
    String identificadorExpediente = null;
    ObjetoExpedienteInside expediente = null;

    try {
      ObjectMapper mapper = new ObjectMapper();
      if (StringUtils.isNotBlank(idJsonExpediente)) {
        identificadorExpediente = mapper.readValue(idJsonExpediente, String.class);
        expediente = insideService.getExpediente(identificadorExpediente, false);
        // permisos del usuario sobre el expediente
        validar = insideService.permisoObjetoUnidad(expediente,
            ((ObjetoInsideUsuario) request.getSession().getAttribute(WebConstants.USUARIO_SESSION))
                .getNif(),
            true);
      }
    } catch (InsideStoreObjectNotFoundException e) {
      retorno.put(MENSAJE_USU,
          getErrorMessage(WebConstants.MSG_IMPORTAR_DOC_EXP_NO_ENCONTRADO, null, locale));
      throw new InsideServiceInternalException(
          "Error al recuperar el expediente: " + identificadorExpediente, e);
    } catch (IOException e) {
      retorno.put(MENSAJE_USU,
          getErrorMessage(WebConstants.MSG_IMPORTAR_EXP_EXP_NO_VALIDO, null, locale));
      throw new InsideServiceInternalException("Error al recuperar el JSON: " + idJsonExpediente,
          e);
    } catch (InSideServiceException e) {
      retorno.put(MENSAJE_USU, getErrorMessage(WebConstants.MSG_IMPORTAR_DOC_ERROR, null, locale));
      throw new InsideServiceInternalException("Error al acceder a BBDD", e);
    }
    if (!validar) {
      retorno.put(MENSAJE_USU,
          getErrorMessage(WebConstants.MSG_IMPORTAR_DOC_USUARIO_NO_ASOCIADO_EXP, null, locale));
      throw new InsideServiceInternalException("Usuario no asociado al expediente");
    }
    return expediente;
  }

  @RequestMapping(value = "/saveDocument", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> saveDocumentPost(
      @ModelAttribute TipoDocumentoConversionInside documentoEni, HttpServletRequest request,
      Locale locale, @RequestParam("metadatosAdicionalesDoc") String metadatosAdicionales,
      HttpSession session) {
    Map<String, Object> retorno = new HashMap<String, Object>();
    TipoMetadatosAdicionales aditionalData = new TipoMetadatosAdicionales();
    try {

      // Si el fichero es mayor de 8 megas y tiene firma(esto es haberlo firmado o ser
      // previamentefirmado)
      // se rechaza porque @firma no puede validar las firmas de esos tamanios
      String rutaDocumentId =
          temporalDataBusinessService.getPath(session.getId(), request.getParameter(DOCUMENT_ID));
      String signId = extraerFirmaResultado(request, session);
      String firmapreviamente = request.getParameter("firma");

      String firmarLongeva = request.getParameter("firmarLongeva");
      String firmado = null;
      if ("1".equals(firmapreviamente) || signId != null)
        firmado = "si";
      // por defecto tratarlo como fichero grande etc.. si
      // aceptarFicheroTamanioYFirma(....)devuelkve true se trata como fichero normal
      boolean tratarComoFicheroNormal = false;
      tratarComoFicheroNormal =
          insideUtilService.aceptarFicheroTamanioYFirma(rutaDocumentId, firmado,
              documentoEni.getMetadatosEni().getEstadoElaboracion().getValorEstadoElaboracion());


      TipoDocumentoInsideConMAdicionales docConvertido = generateDocument(documentoEni, request,
          locale, metadatosAdicionales, session, retorno, aditionalData, true);

      insideUtilService.checkNoExistIdDocumentInside(docConvertido.getDocumento().getId(), true);
      if (StringUtils.isNotBlank(firmarLongeva) && firmarLongeva.trim().equals("true")) {
        try {
          insideUtilService.trataAmpliacionFirmaDocumento(docConvertido);
        } catch (Exception e) {
          logger.warn("Error al intentar amliar firma " + e.getLocalizedMessage() + e.getMessage());
          retorno.put(MENSAJE_USU, new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, messageSource
              .getMessage(WebConstants.MSG_GENERAR_FIRMA_LONGEVA_ERROR, null, locale)));
          throw new InsideServiceInternalException("Error al ampliar firma");
        }
      }
      ObjetoDocumentoInside docInside = null;
      if (tratarComoFicheroNormal) {
        // se aniade parametros por tema laura fichero firmado previamente con certificado caducado
        // hay que dejarle pasar
        docInside = insideUtilService.validateDocumentImport(retorno, firmapreviamente,
            insideUtilService.generateDocXml(docConvertido));
      } else {
        docInside = insideUtilService.convertXmlDocumentToDocumentObject(docConvertido);
      }



      ObjetoInsideUsuario usuario =
          (ObjetoInsideUsuario) request.getSession().getAttribute(WebConstants.USUARIO_SESSION);

      ObjetoDocumentoInside doc = insideService.altaDocumento(docInside, usuario.getNif(), true);

      // si no se ha incluido el mensaje antes se mete este
      if (retorno.containsKey("mensajeUsuarioPreviamenteFirmado")) {
        MessageObject mensObjet = (MessageObject) retorno.get("mensajeUsuarioPreviamenteFirmado");
        String mens = messageSource.getMessage(WebConstants.MSG_GUARDAR_DOC_OK, null, locale) + ". "
            + mensObjet.getMessage();
        retorno.put(MENSAJE_USU, new MessageObject(WebConstants.MESSAGE_LEVEL_WARNING, mens));
      } else if (doc.getContenido().getReferencia() != null
          && doc.getContenido().getReferencia().contains("csvstorage")) {
        String mens = messageSource.getMessage(WebConstants.MSG_GUARDAR_DOC_OK, null, locale) + ". "
            + "Expedientes con este documento no se pueden enviar a justicia.";
        retorno.put(MENSAJE_USU, new MessageObject(WebConstants.MESSAGE_LEVEL_WARNING, mens));
      } else {
        retorno.put(MENSAJE_USU, getSuccessMessage(WebConstants.MSG_GUARDAR_DOC_OK, null, locale));
      }

    } catch (InSideServiceValidationException e) {
      logger.warn(MNJ_ERROR_SAVE_DOCU, e);
      retorno.put(ERROR_GUARDAR, true);
      if (!retorno.containsKey(MENSAJE_USU))
        retorno.put(MENSAJE_USU, getErrorMessage(e.getMessage()));
    } catch (InSideServiceException e3) {
      logger.warn(MNJ_ERROR_SAVE_DOCU, e3);
      retorno.put(ERROR_GUARDAR, true);
      if (!retorno.containsKey(MENSAJE_USU))
        retorno.put(MENSAJE_USU, getErrorMessage(e3.getMessage()));
    } catch (Exception e1) {
      logger.error(MNJ_ERROR_SAVE_DOCU, e1);
      retorno.put(ERROR_GUARDAR, true);
      if (!retorno.containsKey(MENSAJE_USU))
        retorno.put(MENSAJE_USU,
            getErrorMessage(WebConstants.MSG_IMPORTAR_DOC_ERROR, null, locale));
    }
    return retorno;
  }

  public ObjetoDocumentoInside getDataSigned(TipoDocumentoConversionInside documentoEni,
      HttpSession session, String signId) {
    ObjetoDocumentoInside docInside = null;
    if (StringUtils.isEmpty(signId)) {
      docInside =
          obtenerDatosFirma(session.getId(), documentoEni.getMetadatosEni().getIdentificador());
    }
    return docInside;
  }

  public String extraerFirmaResultado(HttpServletRequest request, HttpSession session) {
    String signId = null;
    if (FIRMA.equals(request.getParameter(FIRMA))
        && StringUtils.isNotEmpty(request.getParameter(FIRMA_RESULTADO))) {
      signId = temporalDataBusinessService.getPath(session.getId(),
          request.getParameter(FIRMA_RESULTADO));
    }
    return signId;
  }

  public void setAdicionales(String metadatosAdicionales, TipoMetadatosAdicionales aditionalData)
      throws InsideServiceInternalException {
    try {
      List<MetadatoAdicional> listAditionalData;
      ObjectMapper mapper = new ObjectMapper();
      listAditionalData =
          mapper.readValue(metadatosAdicionales, new TypeReference<List<MetadatoAdicional>>() {});
      aditionalData.getMetadatoAdicional().addAll(listAditionalData);
    } catch (Exception e3) {
      logger.error(
          messageSource.getMessage(WebConstants.MSG_GENERAR_DOC_ERROR_METADATOS, null, null), e3);
      throw new InsideServiceInternalException(
          messageSource.getMessage(WebConstants.MSG_GENERAR_DOC_ERROR_METADATOS, null, null));
    }
  }


  @RequestMapping(value = "/editarDocumento/updateDocument", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> updateDocumentPost(
      @ModelAttribute TipoDocumentoConversionInside documentoEni, HttpServletRequest request,
      Locale locale, @RequestParam("metadatosAdicionalesDoc") String metadatosAdicionales,
      HttpSession session) {
    Map<String, Object> retorno = new HashMap<String, Object>();
    TipoMetadatosAdicionales aditionalData = new TipoMetadatosAdicionales();
    try {
      // obtenemos el fichero eni descargado al entrar al modificar
      byte[] eniBytes = temporalDataBusinessService.getFile(session.getId(),
          documentoEni.getMetadatosEni().getIdentificador() + ".xml");
      TipoDocumentoInsideConMAdicionales tipoDoc =
          marshaller.unmarshallDataDocumentAditional(eniBytes);
      byte[] bytesContenido;
      if (tipoDoc.getDocumento().getContenido().getValorBinario() != null) {
        documentoEni.setContenido(tipoDoc.getDocumento().getContenido().getValorBinario());
      } else if (StringUtils
          .isNotEmpty(tipoDoc.getDocumento().getContenido().getReferenciaFichero())
          && "#FIRMA_0"
              .equalsIgnoreCase(tipoDoc.getDocumento().getContenido().getReferenciaFichero())) {
        ObjetoDocumentoInsideContenido contenido = insideService.getDocumentoContenido(
            documentoEni.getMetadatosEni().getIdentificador(), null, session.getId());
        documentoEni.setContenido(contenido.getValorBinario());
      } else {

        // descargamos el contenido del documento
        ObjetoDocumentoInsideContenido contenido = insideUtilService
            .getExternalContent(documentoEni.getMetadatosEni().getIdentificador(), session.getId());
        documentoEni.setContenidoId(contenido.getReferencia());
      }

      TipoDocumentoInsideConMAdicionales docConvertido = generateDocument(documentoEni, request,
          locale, metadatosAdicionales, session, retorno, aditionalData, false);
      ObjetoDocumentoInside docInside =
          insideUtilService.validateDocumentImport(insideUtilService.generateDocXml(docConvertido));
      ObjetoInsideUsuario usuario =
          (ObjetoInsideUsuario) request.getSession().getAttribute(WebConstants.USUARIO_SESSION);

      insideService.modificaDocumento(docInside, usuario.getNif(), true);
      retorno.put(MENSAJE_USU, getSuccessMessage(WebConstants.MSG_ACTUALIZAR_DOC_OK, null, locale));
    } catch (InSideServiceException e3) {
      logger.warn(MNJ_ERROR_DOCU, e3);
      retorno.put(ERROR_GUARDAR, true);
      if (!retorno.containsKey(MENSAJE_USU))
        retorno.put(MENSAJE_USU, getErrorMessage(e3.getMessage()));
    } catch (Exception e1) {
      logger.error(MNJ_ERROR_DOCU, e1);
      retorno.put(ERROR_GUARDAR, true);
      if (!retorno.containsKey(MENSAJE_USU))
        retorno.put(MENSAJE_USU,
            getErrorMessage(WebConstants.MSG_IMPORTAR_DOC_ERROR, null, locale));
    }

    return retorno;
  }

  @RequestMapping(value = "/importNewDocument", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> importNewDocumentPost(Locale locale, HttpServletRequest request,
      HttpSession session) {
    Map<String, Object> retorno = new HashMap<String, Object>();
    ObjectMapper mapper = new ObjectMapper();
    try {
      ObjetoInsideUsuario usuario =
          (ObjetoInsideUsuario) request.getSession().getAttribute(WebConstants.USUARIO_SESSION);
      ObjetoExpedienteInside expediente = validarExpedienteUnidad(request, retorno, locale);
      ObjetoDocumentoInside objDoc = getDocumentRequest(locale, request, session, retorno);
      boolean firmaServidor =
          mapper.readValue(request.getParameter(SIGNATURE_SERVER), Boolean.class);

      if (firmaServidor) {
        insideService.altaDocumento(objDoc, expediente.getIdentificador(), null, usuario.getNif(),
            true);
        addExpReturn(locale, retorno, expediente);
      } else {
        insideService.altaDocumento(objDoc, usuario.getNif(), true);
        List<ObjetoDocumentoInside> docs = new ArrayList<ObjetoDocumentoInside>();
        docs.add(objDoc);
        expediente = insideService.vincularDocumentosEnExpedienteWithoutSave(expediente, docs, null,
            usuario.getNif(), true);
        addExpReturn(locale, retorno, expediente);
      }

      retorno.put(MENSAJE_USU, getSuccessMessage(WebConstants.MSG_IMPORTAR_DOC_OK, null, locale));
    } catch (InSideServiceException e3) {
      logger.warn(MNJ_ERROR_SAVE_DOCU, e3);
      if (!retorno.containsKey(MENSAJE_USU))
        retorno.put(MENSAJE_USU, getErrorMessage(e3.getMessage()));
    } catch (Exception e1) {
      logger.error(MNJ_ERROR_SAVE_DOCU, e1);
      if (!retorno.containsKey(MENSAJE_USU))
        retorno.put(MENSAJE_USU,
            getErrorMessage(WebConstants.MSG_IMPORTAR_DOC_ERROR, null, locale));
    }
    return retorno;
  }

  @RequestMapping(value = "/importDocument", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> importDocumentPost(Locale locale, HttpServletRequest request,
      HttpSession session) {
    Map<String, Object> retorno = new HashMap<String, Object>();
    ObjectMapper mapper = new ObjectMapper();
    try {
      ObjetoInsideUsuario usuario =
          (ObjetoInsideUsuario) request.getSession().getAttribute(WebConstants.USUARIO_SESSION);
      ObjetoExpedienteInside expediente = validarExpedienteUnidad(request, retorno, locale);
      ObjetoDocumentoInside objDoc = getDocumentRequest(locale, request, session, retorno);
      Boolean firmaServidor =
          mapper.readValue(request.getParameter(SIGNATURE_SERVER), Boolean.class);

      checkExistDocumentInIndex(objDoc.getIdentificador(), expediente.getIndice(), retorno, locale);
      if (firmaServidor) {
        insideService.modificaDocumento(objDoc, expediente.getIdentificador(), null,
            usuario.getNif(), true);
        addExpReturn(locale, retorno, expediente);
      } else {
        insideService.modificaDocumento(objDoc, usuario.getNif(), true);
        List<ObjetoDocumentoInside> docs = new ArrayList<ObjetoDocumentoInside>();
        docs.add(objDoc);
        expediente = insideService.vincularDocumentosEnExpedienteWithoutSave(expediente, docs, null,
            usuario.getNif(), true);
        addExpReturn(locale, retorno, expediente);
      }

      // retorno.put(MENSAJE_USU, getSuccessMessage(WebConstants.MSG_IMPORTAR_DOC_OK, null,
      // locale));

      // si no se ha incluido el mensaje antes se mete este
      if (retorno.containsKey("mensajeUsuarioPreviamenteFirmado")) {
        MessageObject mensObjet = (MessageObject) retorno.get("mensajeUsuarioPreviamenteFirmado");
        String mens = messageSource.getMessage(WebConstants.MSG_IMPORTAR_DOC_OK, null, locale)
            + ". " + mensObjet.getMessage();
        retorno.put(MENSAJE_USU, new MessageObject(WebConstants.MESSAGE_LEVEL_WARNING, mens));
      } else if (objDoc.getContenido().getReferencia() != null
          && objDoc.getContenido().getReferencia().contains("csvstorage")) {
        String mens = messageSource.getMessage(WebConstants.MSG_GUARDAR_DOC_OK, null, locale) + ". "
            + "Expedientes con este documento no se pueden enviar a justicia.";
        retorno.put(MENSAJE_USU, new MessageObject(WebConstants.MESSAGE_LEVEL_WARNING, mens));
      } else {
        retorno.put(MENSAJE_USU, getSuccessMessage(WebConstants.MSG_IMPORTAR_DOC_OK, null, locale));
      }

    } catch (InSideServiceException e3) {
      logger.warn(MNJ_ERROR_DOCU, e3);
      retorno.put(ERROR_GUARDAR, true);
      if (!retorno.containsKey(MENSAJE_USU))
        retorno.put(MENSAJE_USU, getErrorMessage(e3.getMessage()));
    } catch (Exception e1) {
      logger.error(MNJ_ERROR_DOCU, e1);
      retorno.put(ERROR_GUARDAR, true);
      if (!retorno.containsKey(MENSAJE_USU))
        retorno.put(MENSAJE_USU,
            getErrorMessage(WebConstants.MSG_IMPORTAR_DOC_ERROR, null, locale));

    }
    return retorno;
  }

  @RequestMapping(value = "/importarDocumento/importNewDocumentXml", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> importNewDocumentXmlPost(Locale locale, HttpServletRequest request,
      HttpSession session) {
    Map<String, Object> retorno = new HashMap<String, Object>();
    ObjectMapper mapper = new ObjectMapper();
    try {
      ObjetoInsideUsuario usuario =
          (ObjetoInsideUsuario) request.getSession().getAttribute(WebConstants.USUARIO_SESSION);
      ObjetoExpedienteInside expediente = validarExpedienteUnidad(request, retorno, locale);
      ObjetoDocumentoInside objDoc = getDocumentRequest(locale, request, session, retorno);
      insideUtilService.checkNoExistIdDocumentInside(objDoc.getIdentificador(), true);

      if (expediente != null) {
        Boolean firmaServidor =
            mapper.readValue(request.getParameter(SIGNATURE_SERVER), Boolean.class);
        if (firmaServidor) {
          insideService.altaDocumento(objDoc, expediente.getIdentificador(), null, usuario.getNif(),
              true);
          addExpReturn(locale, retorno, expediente);
        } else {
          insideService.altaDocumento(objDoc, usuario.getNif(), true);
          List<ObjetoDocumentoInside> docs = new ArrayList<ObjetoDocumentoInside>();
          docs.add(objDoc);
          expediente = insideService.vincularDocumentosEnExpedienteWithoutSave(expediente, docs,
              null, usuario.getNif(), true);
          addExpReturn(locale, retorno, expediente);
        }
      } else {
        insideService.altaDocumento(objDoc, usuario.getNif(), true);
      }

      // retorno.put(MENSAJE_USU, getSuccessMessage(WebConstants.MSG_IMPORTAR_DOC_OK, null,
      // locale));

      // si no se ha incluido el mensaje antes se mete este
      if (retorno.containsKey("mensajeUsuarioPreviamenteFirmado")) {
        MessageObject mensObjet = (MessageObject) retorno.get("mensajeUsuarioPreviamenteFirmado");
        String mens = messageSource.getMessage(WebConstants.MSG_IMPORTAR_DOC_OK, null, locale)
            + ". " + mensObjet.getMessage();
        retorno.put(MENSAJE_USU, new MessageObject(WebConstants.MESSAGE_LEVEL_WARNING, mens));
      } else {
        retorno.put(MENSAJE_USU, getSuccessMessage(WebConstants.MSG_IMPORTAR_DOC_OK, null, locale));
      }

    } catch (InSideServiceException e3) {
      logger.warn(MNJ_ERROR_DOCU, e3);
      if (!retorno.containsKey(MENSAJE_USU))
        retorno.put(MENSAJE_USU, getErrorMessage(e3.getMessage()));
    } catch (Exception e1) {
      logger.error(MNJ_ERROR_DOCU, e1);
      if (!retorno.containsKey(MENSAJE_USU))
        retorno.put(MENSAJE_USU,
            getErrorMessage(WebConstants.MSG_IMPORTAR_DOC_ERROR, null, locale));

    }
    return retorno;
  }

  public ObjetoDocumentoInside getDocumentRequest(Locale locale, HttpServletRequest request,
      HttpSession session, Map<String, Object> retorno) throws InSideServiceException {
    ObjetoDocumentoInside doc = null;
    try {
      ObjectMapper mapper = new ObjectMapper();
      String identificadorDoc = mapper.readValue(request.getParameter(DOCUMENT_ID), String.class);
      doc = insideUtilService.validateDocumentImport(retorno, null,
          temporalDataBusinessService.getFile(session.getId(), identificadorDoc));
    } catch (IOException e2) {
      logger.warn(MNJ_ERROR_SAVE_DOCU, e2);
      retorno.put(MENSAJE_USU, getErrorMessage(WebConstants.MSG_FILE_UPLOAD_ERROR, null, locale));
    }
    return doc;
  }

  public void addExpReturn(Locale locale, Map<String, Object> retorno,
      ObjetoExpedienteInside expediente) throws InSideServiceException {
    try {
      TipoExpediente tExp = InsideConverterExpediente.expedienteInsideToEni(expediente, null);
      tExp.getIndice().setFirmas(null);
      String data = marshaller.marshallDataExpedient(tExp);
      data = XMLUtils.incluirNamespacesParaValidarFirma(data);
      retorno.put("expedienteEni", data);
    } catch (Exception e) {
      retorno.put(MENSAJE_USU,
          getErrorMessage(WebConstants.MSG_IMPORTAR_DOC_NO_VALIDO, null, locale));
      retorno.put(ERROR_GUARDAR, true);
      throw new InsideServiceInternalException("Error al convertir el expediente a Eni", e);
    }
  }

  private void checkExistDocumentInIndex(String identifierDoc,
      ObjetoExpedienteInsideIndice indiceContenido, Map<String, Object> retorno, Locale locale)
      throws InsideServiceInternalException {
    existDocumentIndex = false;
    if (comprobarSubcarpeta(identifierDoc,
        indiceContenido.getIndiceContenido().getElementosIndizados())) {
      retorno.put(MENSAJE_USU,
          getErrorMessage(WebConstants.MSG_IMPORTAR_DOC_EXISTE_EN_INDICE, null, locale));
      throw new InsideServiceInternalException(
          "Error en checkExistDocumentInIndex. Documento repetido en el indice");
    }
  }

  private boolean comprobarSubcarpeta(String identifierDoc,
      List<ObjetoExpedienteInsideIndiceContenidoElementoIndizado> list) {
    for (ObjetoExpedienteInsideIndiceContenidoElementoIndizado elemento : list) {
      if (elemento instanceof ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada) {
        existDocumentIndex = comprobarSubcarpeta(identifierDoc,
            ((ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada) elemento)
                .getElementosIndizados());
      } else if (elemento instanceof ObjetoExpedienteInsideIndiceContenido) {
        existDocumentIndex = comprobarSubcarpeta(identifierDoc,
            ((ObjetoExpedienteInsideIndiceContenido) elemento).getElementosIndizados());
      } else if (elemento instanceof ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado
          && ((ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado) elemento)
              .getIdentificadorDocumento().equals(identifierDoc)) {
        existDocumentIndex = true;
        break;
      }
    }

    return existDocumentIndex;
  }

  public MessageObject getSuccessMessage(String tipo, Object[] args, Locale locale) {
    return new MessageObject(WebConstants.MESSAGE_LEVEL_SUCCESS,
        messageSource.getMessage(tipo, args, locale));
  }

  public MessageObject getErrorMessage(String tipo, Object[] args, Locale locale) {
    return new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
        messageSource.getMessage(tipo, args, locale));
  }

  public MessageObject getErrorMessage(String message) {
    return new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, message);
  }

  @RequestMapping(value = "/borrarDocumento", method = RequestMethod.POST)
  public ModelAndView borrarDocumentoPost(HttpSession session,
      @RequestParam(IDENTIFICADOR) String identificador, Locale locale)
      throws InSideServiceException {
    ModelAndView retorno = new ModelAndView("documento/documentosAlmacenados");
    MessageObject mensaje = null;
    try {
      insideService.deleteDocument(identificador);
      retorno.addObject(DOCUMENTOS, getDocumentosMetadatosAlmacenados(session));
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_SUCCESS,
          messageSource.getMessage(WebConstants.MSG_BAJA_DOC_OK, null, locale));
      retorno.addObject(MENSAJE_USU, mensaje);
    } catch (InsideStoreObjectVinculatedException e) {
      logger.warn(
          "DocumentController.borrarDocumento --> Error al borrar el documento: " + identificador,
          e);
      Object[] args = new String[1];
      args[0] = e.getMessage();
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
          messageSource.getMessage(WebConstants.MSG_BAJA_DOC_VINCULADO, args, locale));
      retorno.addObject(MENSAJE_USU, mensaje);
      retorno.addObject(DOCUMENTOS, getDocumentosMetadatosAlmacenados(session));
    } catch (InSideServiceException e) {
      logger.error(
          "DocumentController.borrarDocumento --> Error al borrar el documento: " + identificador,
          e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      retorno.addObject(MENSAJE_USU, mensaje);
      retorno.addObject(DOCUMENTOS, getDocumentosMetadatosAlmacenados(session));
    }
    return retorno;
  }

  @RequestMapping(value = "/documentosAlmacenados", method = RequestMethod.GET)
  public ModelAndView documentosAlmacenados(HttpSession session,
      @RequestParam(value = "textoMensajeUsuario", required = false) String textoMensajeUsuario,
      @RequestParam(value = "nivelMensajeUsuario", required = false) String nivelMensajeUsuario) {
    ModelAndView retorno = new ModelAndView("documento/documentosAlmacenados");
    MessageObject mensaje;
    if (StringUtils.isNotEmpty(textoMensajeUsuario)
        && StringUtils.isNotEmpty(nivelMensajeUsuario)) {
      retorno.addObject(MENSAJE_USU,
          new MessageObject(Integer.parseInt(nivelMensajeUsuario), textoMensajeUsuario));
    }
    try {
      retorno.addObject(DOCUMENTOS, getDocumentosMetadatosAlmacenados(session));
    } catch (InSideServiceException e) {
      logger.error(
          "InsideDocumentController.documentosAlmacenados --> Error al obtener los documentos del usuario",
          e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      retorno.addObject(MENSAJE_USU, mensaje);
    }
    return retorno;
  }

  @RequestMapping(value = "/editarDocumento", method = RequestMethod.POST)
  public ModelAndView editarDocumento(Locale locale,
      @RequestParam(IDENTIFICADOR) String identificador, @RequestParam(NAVEGADOR) String navegador,
      HttpSession session, HttpServletRequest request) {
    this.organos = null;
    MessageObject mensaje = null;
    ModelAndView retorno = new ModelAndView("documento/editarDocumento");
    TipoMetadatosAdicionales aditionalData = new TipoMetadatosAdicionales();

    try {
      init(retorno, locale, request);

      // Este objetoDocumentoInside se usa para que en setDocumentContentTemporalBigFile no se
      // vuelva a pedir
      ObjetoDocumentoInside docInside = insideService.getDocumento(identificador);
      aditionalData = InsideConverterMetadatos
          .metadatosAdicionalesInsideToXml(docInside.getMetadatos().getMetadatosAdicionales());

      TipoDocumentoConversionInside documentoEni =
          InsideConverterDocumento.documentoConversionToInside(docInside);
      setDocumentContent(documentoEni, docInside);

      boolean firmaSoloCSV = false;
      if (contentCSVSignature(docInside)) {
        firmaSoloCSV = true;
      }

      TipoDocumentoInsideConMAdicionales docConvertido =
          insideUtilService.convertirDocumentoAEniConMAdicionales(documentoEni, aditionalData, null,
              false, null, docInside);
      if (documentoEni.getMetadatosEni().getTipoDocumental().equals(TipoDocumental.TD_62)
          || documentoEni.getMetadatosEni().getTipoDocumental().equals(TipoDocumental.TD_67)
          || documentoEni.getMetadatosEni().getTipoDocumental().equals(TipoDocumental.TD_64)) {
        documentoEni.getMetadatosEni().setTipoDocumental(TipoDocumental.TD_20);
      }
      getComboOrganos(documentoEni);
      getDocumentoCertificado(retorno, docInside);

      setDocumentEniInTemporal(session, documentoEni, docConvertido);
      setSignatureType(retorno, docInside, firmaSoloCSV);
      setMetadatosDocument(session, retorno, documentoEni, docInside);
      getDisplayDocument(locale, retorno, docInside);

      retorno.addObject(NAVEGADOR, navegador);
      // Listado de firmas
      Item itemDocumento = insideServiceVisualizacion.documentoInsideToItemVisualizacion(
          InsideConverterDocumento.documentoEniAndMetadatosToDocumentoInside(
              docConvertido.getDocumento(), docConvertido.getMetadatosAdicionales()),
          null);
      List<VisualizacionItem> visItems =
          visualizacionUtils.obtenerVisualizacionItems(itemDocumento);
      visualizacionUtils.getFirmas(visItems, retorno);

      ///// cargar los documentos de la unidad porque el autocomplete de js no funciona en internet
      ///// Explorer ////////////////////////////////////////
      ObjetoInsideUsuario usuario =
          (ObjetoInsideUsuario) session.getAttribute(WebConstants.USUARIO_SESSION);
      String listaExpedientesJSON = convertListToJSONString(usuario);

      retorno.addObject("metadatoNombreNatural",
          MetadatosEEMGDE.METADATO_NOMBRE_NOMBRE_NATURAL.getValue());
      retorno.addObject("listaExpedientesJSON", listaExpedientesJSON);

      ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    } catch (InsideServiceObjectValidationException e) {
      logger.warn("DocumentController.editarDocumento.validacion: " + identificador);
      mensaje = MessageUtils.contruirMensaje(locale, e, messageSource);
      retorno.addObject(MENSAJE_USU, mensaje);
      /*
       * CARM ### v2.0.8.1
       * retorno.setViewName("redirect:/documentosAlmacenados?textoMensajeUsuario=" +
       * mensaje.getMessage() + "&nivelMensajeUsuario=" + mensaje.getLevel());
       */
      retorno.addObject("textoMensajeUsuario", mensaje.getMessage());
      retorno
          .setViewName("redirect:/documentosAlmacenados?nivelMensajeUsuario=" + mensaje.getLevel());
      // CARM 2.0.8.1 ###
    } catch (Exception e) {
      logger.error("DocumentController.editarDocumento: " + identificador, e);
      String mensajeError = e.getMessage() != null ? e.getMessage()
          : messageSource.getMessage(WebConstants.MSG_KEY_ERROR_GENERIC, null, locale);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, mensajeError);
      retorno.addObject(MENSAJE_USU, mensaje);
      /*
       * CARM ### v2.0.8.1
       * retorno.setViewName("redirect:/documentosAlmacenados?textoMensajeUsuario=" +
       * mensaje.getMessage() + "&nivelMensajeUsuario=" + mensaje.getLevel());
       */
      retorno.addObject("textoMensajeUsuario", mensaje.getMessage());
      retorno
          .setViewName("redirect:/documentosAlmacenados?nivelMensajeUsuario=" + mensaje.getLevel());
      // CARM 2.0.8.1 ###
    }

    return retorno;
  }

  public void setMetadatosDocument(HttpSession session, ModelAndView retorno,
      TipoDocumentoConversionInside documentoEni, ObjetoDocumentoInside docInside) {
    retorno.addObject(FECHA_CAPTURA, getFechaCaptura(documentoEni));
    retorno.addObject(DOCUMENTO_ENI, documentoEni);
    setMetadatosAdicionalesDocument(docInside.getMetadatos().getMetadatosAdicionales(), retorno);
    retorno.addObject(SHOW_MESSAGE, true);
    ObjetoInsideUsuario usuario =
        (ObjetoInsideUsuario) session.getAttribute(WebConstants.USUARIO_SESSION);
    retorno.addObject(SHIW_FIRMA_SERVER, insideService.checkSignatureServerByUser(usuario));
  }

  private void setMetadatosAdicionalesDocument(
      List<ObjetoInsideMetadatoAdicional> metadatosAdicionales, ModelAndView retorno) {
    List<ObjetoInsideMetadatoAdicional> listMetadatosAdicional =
        new ArrayList<ObjetoInsideMetadatoAdicional>();
    for (ObjetoInsideMetadatoAdicional metadatoAdicional : metadatosAdicionales) {
      if (MetadatosEEMGDE.METADATO_NOMBRE_NOMBRE_NATURAL.getValue()
          .equals(metadatoAdicional.getNombre())) {
        retorno.addObject("keyMetadatoAdicionalNatural", metadatoAdicional.getNombre());
        retorno.addObject("valueMetadatoAdicionalNatural", metadatoAdicional.getValor());
      } else {
        listMetadatosAdicional.add(metadatoAdicional);
      }
    }
    retorno.addObject(METADATO_ADICIONALES, listMetadatosAdicional);
  }

  public boolean contentCSVSignature(ObjetoDocumentoInside docInside) {
    return CollectionUtils.isNotEmpty(docInside.getFirmas()) && docInside.getFirmas().size() == 1
        && FirmaInsideTipoFirmaEnum.TF_01.name()
            .equals(docInside.getFirmas().get(0).getTipoFirma().name());
  }

  public void setDocumentContent(TipoDocumentoConversionInside documentoEni,
      ObjetoDocumentoInside docInside) {
    if (docInside.getContenido().getValorBinario() != null) {
      documentoEni.setContenido(docInside.getContenido().getValorBinario());
    }
  }

  public void setSignatureType(ModelAndView retorno, ObjetoDocumentoInside docInside,
      boolean firmaSoloCSV) {
    if (CollectionUtils.isNotEmpty(docInside.getFirmas())) {
      if (firmaSoloCSV) {
        retorno.addObject(FIRMA, "0");
      } else {
        retorno.addObject(FIRMA, "1");
      }
    } else {
      retorno.addObject(FIRMA, "0");
    }
  }

  public void setDocumentEniInTemporal(HttpSession session,
      TipoDocumentoConversionInside documentoEni, TipoDocumentoInsideConMAdicionales docInside)
      throws InsideServiceInternalException {
    try {
      temporalDataBusinessService.escribir(insideUtilService.generateDocXml(docInside),
          session.getId(),
          documentoEni.getMetadatosEni().getIdentificador() + WebConstants.FORMAT_XML, true);
    } catch (Exception e) {
      logger.error("Error al editar documento en setDocumentEniInTemporal", e);
      throw new InsideServiceInternalException(
          "Error al editar documento en setDocumentEniInTemporal", e);
    }
  }

  public void getComboOrganos(TipoDocumentoConversionInside documentoEni) {
    if (CollectionUtils.isNotEmpty(documentoEni.getMetadatosEni().getOrgano())) {
      List<String> loadOrganos = new ArrayList<String>(documentoEni.getMetadatosEni().getOrgano());
      documentoEni.getMetadatosEni().getOrgano().clear();
      for (String organo : loadOrganos) {
        List<ComboItem> data = autocompleteController.autocompleteCodigoDIR(organo);
        if (CollectionUtils.isNotEmpty(data)) {
          documentoEni.getMetadatosEni().getOrgano()
              .add(data.get(0).getKey() + " - " + data.get(0).getValue());
        }
      }
    }
  }

  public void getDisplayDocument(Locale locale, ModelAndView retorno,
      ObjetoDocumentoInside docInside) {
    MessageObject mensaje;
    try {
      // Tamanio de fichero mayor de 2MB
      if (isFileTooLarge(docInside)) {
        String tamanio = "0";
        if (docInside.getContenido().getValorBinario() != null) {
          tamanio =
              FileUtils.byteCountToDisplaySize(docInside.getContenido().getValorBinario().length);
          retorno.addObject("isBigFile", false);
        } else {
          String[] referencia = docInside.getContenido().getReferencia().split("/");
          String uuid = referencia[referencia.length - 1];
          tamanio = FileUtils.byteCountToDisplaySize(csvStorageAdapter.getContentSizeByUuid(uuid));
          retorno.addObject("isBigFile", true);
        }

        if ("0 bytes".equalsIgnoreCase(tamanio)) {

          logger.info("Error al recibir tamanio desde el csvStorage");
          mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_INFO,
              "El documento supera los 2 MB. No es posible previsualizar");
        } else {

          String[] argumentos = {tamanio};
          logger.info("El tamaño del documento es: " + tamanio
              + " y supera los 2 MB. No es posible previsualizar");
          mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_INFO,
              messageSource.getMessage(WebConstants.MSG_TAMANIO_MAS_2_MB, argumentos, locale));

        }
        retorno.addObject(MENSAJE_USU, mensaje);
        retorno.addObject(DATA_DOC_BASE64, null);

      } else {
        retorno.addObject("isBigFile", false);
        retorno.addObject(DATA_DOC_BASE64, Base64
            .encodeBase64String(insideServiceVisualizacion.visualizarContenidoOriginal(docInside)));
      }
    } catch (InsideServiceVisualizacionException | InsideServiceAdapterException e) {
      logger.info("No es posible previsualizar", e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_INFO,
          messageSource.getMessage(WebConstants.MSG_PREVISUALIZACION_NO_DISPONIBLE, null, locale)
              + "." + e.getMessage());
      retorno.addObject(MENSAJE_USU, mensaje);
      retorno.addObject(DATA_DOC_BASE64, null);
      retorno.addObject("isBigFile", true);
    }
  }

  public boolean isFileTooLarge(ObjetoDocumentoInside docInside) {
    if (docInside.getContenido().getValorBinario() != null
        && (BigInteger.valueOf(docInside.getContenido().getValorBinario().length))
            .compareTo(DOS_MB) >= 0) {
      return true;
    } else if (StringUtils.isNotEmpty(docInside.getContenido().getReferencia())
        && !"#FIRMA_0".equals(docInside.getContenido().getReferencia())) {
      return true;
    } else {
      return false;
    }
  }

  private GregorianCalendar getFechaCaptura(TipoDocumentoConversionInside documentoEni) {
    GregorianCalendar fecha = null;
    if (documentoEni != null && documentoEni.getMetadatosEni() != null
        && documentoEni.getMetadatosEni().getFechaCaptura() != null)
      fecha = documentoEni.getMetadatosEni().getFechaCaptura().toGregorianCalendar();
    return fecha;
  }

  private void getDocumentoCertificado(ModelAndView retorno, ObjetoDocumentoInside docInside) {
    // valores por defecto para la Firma
    if (CollectionUtils.isEmpty(docInside.getFirmas())) {
      retorno.addObject(FIRMA, "0");
      retorno.addObject(FORMAT_FIRM, "");
    } else {
      FirmaInside firma = docInside.getFirmas().get(docInside.getFirmas().size() - 1);
      if (FirmaInsideTipoFirmaEnum.TF_05.value().equals(firma.getTipoFirma().value())) {
        retorno.addObject(FIRMA, FIRMA);
        retorno.addObject(FORMAT_FIRM, WebConstants.TYPE_CERTIFICATE_CADES);
      }
      if (FirmaInsideTipoFirmaEnum.TF_06.value().equals(firma.getTipoFirma().value())) {
        retorno.addObject(FIRMA, FIRMA);
        retorno.addObject(FORMAT_FIRM, WebConstants.TYPE_CERTIFICATE_PADES);
      }
      if (FirmaInsideTipoFirmaEnum.TF_02.value().equals(firma.getTipoFirma().value())) {
        retorno.addObject(FIRMA, FIRMA);
        retorno.addObject(FORMAT_FIRM, WebConstants.TYPE_CERTIFICATE_XADES_DETACHED);
      }
      if (FirmaInsideTipoFirmaEnum.TF_03.value().equals(firma.getTipoFirma().value())) {
        retorno.addObject(FIRMA, FIRMA);
        retorno.addObject(FORMAT_FIRM, WebConstants.TYPE_CERTIFICATE_XADES_ENVELOPED);
      }
    }
  }

  private List<ObjetoInsideDocumentoUnidad> getDocumentosMetadatosAlmacenados(HttpSession session)
      throws InSideServiceException {
    ObjetoInsideUsuario usuario =
        (ObjetoInsideUsuario) session.getAttribute(WebConstants.USUARIO_SESSION);
    return insideService.getDocumentosMetadatosUnidad(usuario, true);
  }


  private void init(ModelAndView model, Locale locale, HttpServletRequest request) {
    // Obtener fecha de captura
    MessageObject mensaje = null;
    String fechaCaptura = "";
    try {
      InSideDateAdapter adapter = new InSideDateAdapter();
      fechaCaptura = adapter.marshalChrome(new Date());
    } catch (Exception e) {
      logger.error(
          "DocumentController.generarDocumento --> Error al convertir la fecha captura documento",
          e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
          messageSource.getMessage(WebConstants.MSG_KEY_EXPED_ERROR_FECHA_INICIO, null, locale));
      model.addObject(MENSAJE_USU, mensaje);
    }

    model.addObject(FECHA_CAPTURA, fechaCaptura);
    model.addObject("estadosElaboracion", ComboUtils.getEstadosElaboracion(messageSource, locale));
    model.addObject("tiposDocumentales", ComboUtils.getTiposDocumentales(messageSource, locale));
    model.addObject("origen", ComboUtils.getOrigen(messageSource, locale));
    model.addObject("tiposCertificado", ComboUtils.getTiposCertificados(messageSource, locale));

    model.addObject(SIGNATURE_SERVER, insideService.checkSignatureServerByUser(
        (ObjetoInsideUsuario) request.getSession().getAttribute(WebConstants.USUARIO_SESSION)));
  }

  private void setData(TipoDocumentoConversionInside documentoEni, HttpServletRequest request,
      String documento, HttpSession session, String signPath, Boolean firmaCsv)
      throws ParseException, DatatypeConfigurationException, IOException,
      InsideConverterUtilsException, InSideServiceException {
    this.organos = new ArrayList<String>();
    this.organos.addAll(documentoEni.getMetadatosEni().getOrgano());

    String fechaCaptura = request.getParameter(FECHA_CAPTURA);

    documentoEni.getMetadatosEni().setFechaCaptura(
        InsideWSUtils.stringToXmlCalendar(fechaCaptura, WebConstants.FORMATO_FECHA_DEFECTO));

    setSignatureData(documentoEni, request, documento, session, signPath);

    if (documentoEni.getCsv() == null) {
      documentoEni.setCsv(new Csv());
    }


    if (StringUtils.isEmpty(documentoEni.getCsv().getRegulacionCSV())
        || StringUtils.isEmpty(documentoEni.getCsv().getValorCSV())) {
      documentoEni.getCsv().setRegulacionCSV(null);
      documentoEni.getCsv().setValorCSV(null);
    }

    if (firmaCsv) {
      boolean generarCSV = false;
      byte[] data = null;
      if (StringUtils.isNotEmpty(documentoEni.getContenidoId())) {
        generarCSV = true;
        data = IOUtils.toByteArray(new FileInputStream(documentoEni.getContenidoId()));
      } else if (documentoEni.getContenido() != null) {
        generarCSV = true;
        data = documentoEni.getContenido();
      }
      if (generarCSV) {
        documentoEni.getCsv().setRegulacionCSV(REGULACION_CSV);
        documentoEni.getCsv().setValorCSV(
            utilFirmaService.generarCSVAmbito("INS", data, InsideMimeUtils.getMimeType(data)));
      }
    }

    List<String> listOrganos = new ArrayList<String>(documentoEni.getMetadatosEni().getOrgano());
    documentoEni.getMetadatosEni().getOrgano().clear();
    documentoEni.getMetadatosEni().getOrgano()
        .addAll(ComboUtils.getOrganosKeys(listOrganos, request));
  }

  public void setSignatureData(TipoDocumentoConversionInside documentoEni,
      HttpServletRequest request, String documento, HttpSession session, String signPath)
      throws InSideServiceException {
    try {
      if (FIRMA.equals(request.getParameter(FIRMA))) {
        documentoEni.setFirmadoConCertificado(true);
        // Si viene la ruta de la firma, el contenido será el documento
        // firmando
        if (signPath != null) {
          documentoEni.setContenidoId(signPath);
        } else {
          documentoEni.setContenidoId(documento);
        }
      } else if (SIGNATURE_SERVER.equals(request.getParameter(FIRMA))) {
        documentoEni.setFirmadoConCertificado(true);
        documentoEni.setContenido(getSignatureServer(
            documentoEni.getMetadatosEni().getIdentificador(), documento, session));
      } else {
        if ("1".equals(request.getParameter(FIRMA))) {
          documentoEni.setFirmadoConCertificado(true);
        }
        documentoEni.setContenidoId(documento);
      }
    } catch (Exception e) {
      logger.error("Error al cargar la firma del documento: ", e);
      throw new InsideServiceInternalException(
          "Error al cargar la firma del documento: " + e.getMessage());
    }
  }

  private byte[] getSignatureServer(String idDocumento, String documento, HttpSession session) {
    byte[] base64ByteFirmado = null;

    try {
      byte[] base64Byte = temporalDataBusinessService.getFilePath(documento);
      String ext = FilenameUtils.getExtension(documento);
      String mime = InsideUtils.getMimeByNombreFormato(ext);
      String sTipoFirma = InsideUtils.getTipoFirmaByMimeAFirmar(mime);
      ObjetoInsideUsuario usuario =
          (ObjetoInsideUsuario) session.getAttribute(WebConstants.USUARIO_SESSION);
      base64ByteFirmado = serviceSigner.firmarFichero(base64Byte, null, sTipoFirma, null,
          getSignatureServerByUser(usuario));
      ObjetoAuditoriaFirmaServidor auditoria = new ObjetoAuditoriaFirmaServidor(
          Constantes.ELEMENTO_DOCUMENTO, idDocumento, usuario.getNif());
      insideService.saveAuditoriaFirmaServidor(auditoria);
    } catch (Exception e) {
      logger.error("Error al cargar el documento para la firma en Servidor", e);
    }

    return base64ByteFirmado;
  }

  private WSCredentialInside getSignatureServerByUser(ObjetoInsideUsuario objetoInsideUsuario) {
    WSCredentialInside signatureServerByUser = new WSCredentialInside();
    try {
      ObjetoInsideUnidadAplicacionEeutil aplicationEeutil =
          insideService.getApplicationEeutilByUser(objetoInsideUsuario);

      if (aplicationEeutil != null) {
        signatureServerByUser.setIdaplicacion(aplicationEeutil.getIdAplicacion());
        signatureServerByUser.setPassword(aplicationEeutil.getPassword());
      }
    } catch (InSideServiceException e) {
      logger.debug("El usuario no tiene asociada firma en servidor", e);
    }
    return signatureServerByUser;
  }

  private void validateData(TipoDocumentoConversionInside tipoDocumentoConversion, Locale locale,
      String documento, boolean validateContent) throws InsideValidationDataException {
    try {
      logger.debug("Inicio GInsideExpedientController.validateData");

      String error = null;

      if (CollectionUtils.isEmpty(tipoDocumentoConversion.getMetadatosEni().getOrgano())) {
        error = "No ha incluido un órgano en la lista";
      }

      if (StringUtils.isBlank(tipoDocumentoConversion.getMetadatosEni().getIdentificador())) {
        if (error == null) {
          error = "No ha incluido un identificador";
        } else {
          error += ", no ha incluido un identificador";
        }
      }

      if (validateContent && StringUtils.isEmpty(documento)) {
        throw new InsideValidationDataException(
            messageSource.getMessage(WebConstants.MSG_CONTENT_DOC_NO_VALID, null, locale));
      }

      if (!StringUtils.isEmpty(error)) {
        Object[] args = new String[1];
        args[0] = error;
        throw new InsideValidationDataException(messageSource
            .getMessage(WebConstants.KEY_ERROR_DATOS_NO_VALIDOS_PARAMETERS, args, locale));
      }
    } catch (NoSuchMessageException e) {
      throw new InsideValidationDataException(
          messageSource.getMessage(WebConstants.KEY_ERROR_DATOS_NO_VALIDOS, null, locale), e);
    }
  }

  private ObjetoDocumentoInside obtenerDatosFirma(String folder, String fileName) {

    ObjetoDocumentoInside docInside = null;

    try {
      byte[] fichTemp =
          temporalDataBusinessService.getFile(folder, fileName + WebConstants.FORMAT_XML);

      if (isPades(fichTemp) && fichTemp.length > 0) {
        TipoDocumento docTemp = jAXBMarshaller.unmarshallDataDocument(fichTemp);
        docInside =
            InsideConverterDocumento.documentoEniAndMetadatosToDocumentoInside(docTemp, null);

      }

    } catch (TransformerFactoryConfigurationError e) {
      logger.debug("No es un documento ENI", e);
    } catch (FileNotFoundException e) {
      logger.debug("El fichero no existe", e);
    } catch (InSideServiceTemporalDataException e) {
      logger.debug("Error al obtener fichero:" + folder + fileName);
    } catch (Exception e) {
      logger.debug("Se ha producido un error al hacer el unmarshall del documento ENI", e);
    }
    return docInside;
  }

  @RequestMapping(value = " /descargarContenidoDocumento/{sistema}/{uuid}",
      method = RequestMethod.GET)
  public ModelAndView descargarContenidoDocumentoGet(@PathVariable("sistema") String sistema,
      @PathVariable("uuid") String uuid, HttpServletResponse response, HttpSession session,
      Locale locale) {
    FileInputStream fin = null;
    ObjetoDocumentoInsideContenido contenido = null;
    try {
      OutputStream pr = response.getOutputStream();

      contenido = insideUtilService.getExternalContent(sistema, uuid, session.getId());

      response.setContentType(contenido.getMime());
      response.addHeader(CONTENT_DISPOSITION, ATTACHMENT_FILENAME
          + contenido.getIdentificadorEnDocumento() + "." + contenido.getNombreFormato());
      File filedownload = new File(contenido.getReferencia());
      response.setContentLength((int) filedownload.length());

      fin = new FileInputStream(filedownload);

      byte[] buffer = new byte[4096]; // To hold file contents
      int bytes_read; // How many bytes in buffer
      while ((bytes_read = fin.read(buffer)) != -1)
        // Read until EOF
        pr.write(buffer, 0, bytes_read); // write

      pr.close();

    } catch (IOException e) {
      logger.error("No se ha podido descargar el contenido externo:" + e);
    }
    try {
      if (fin != null) {
        fin.close();
        // borramos el fichero temporal descargado
        FileUtils.forceDelete(new File(contenido.getReferencia()));
      }
    } catch (IOException e) {
      logger.error(ERROR_AL_DESCARGAR_DOCUMENTO + ": " + uuid, e);
      MessageObject mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
          messageSource.getMessage(WebConstants.MSG_KEY_ERROR_GENERIC, null, locale));
      ModelAndView retorno = new ModelAndView(VIEW_GENERATE_DOC);
      retorno.addObject(MENSAJE_USU, mensaje);
      return retorno;
    }

    return null;
  }

}
