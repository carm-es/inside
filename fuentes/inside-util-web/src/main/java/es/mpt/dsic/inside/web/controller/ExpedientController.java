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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import es.mpt.dsic.inside.model.converter.InsideConverterDocumento;
import es.mpt.dsic.inside.model.converter.InsideConverterExpediente;
import es.mpt.dsic.inside.model.converter.InsideConverterFirmas;
import es.mpt.dsic.inside.model.converter.InsideConverterIndice;
import es.mpt.dsic.inside.model.converter.InsideConverterMetadatos;
import es.mpt.dsic.inside.model.converter.exception.InsideConverterException;
import es.mpt.dsic.inside.model.objetos.ObjetoAuditoriaFirmaServidor;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideExpedienteUnidad;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideMetadatoAdicional;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideUnidadAplicacionEeutil;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideVersion;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInside;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoEstructuraCarpetaInside;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteInside;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteToken;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenidoElementoContenedorElementos;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenidoElementoIndizado;
import es.mpt.dsic.inside.model.objetos.firmas.FirmaInsideTipoFirmaEnum;
import es.mpt.dsic.inside.model.objetos.usuario.ObjetoInsideUsuario;
import es.mpt.dsic.inside.service.InSideService;
import es.mpt.dsic.inside.service.InsideUtilService;
import es.mpt.dsic.inside.service.auditoria.InSideAuditoriaService;
import es.mpt.dsic.inside.service.csv.CsvService;
import es.mpt.dsic.inside.service.eni.EniService;
import es.mpt.dsic.inside.service.exception.InSideServiceException;
import es.mpt.dsic.inside.service.exception.InSideServiceTemporalDataException;
import es.mpt.dsic.inside.service.exception.InsideServiceInternalException;
import es.mpt.dsic.inside.service.exception.ServiceException;
import es.mpt.dsic.inside.service.mail.MailService;
import es.mpt.dsic.inside.service.object.metadatos.validator.Exception.InSideServiceValidationException;
import es.mpt.dsic.inside.service.object.metadatos.validator.impl.MetadatoValidatorStringRegex;
import es.mpt.dsic.inside.service.object.signer.InsideServiceSigner;
import es.mpt.dsic.inside.service.store.exception.InsideStoreObjectNotFoundException;
import es.mpt.dsic.inside.service.store.exception.InsideStoreObjectVinculatedException;
import es.mpt.dsic.inside.service.temporalData.TemporalDataBusinessService;
import es.mpt.dsic.inside.service.util.Constantes;
import es.mpt.dsic.inside.service.util.InsideUtils;
import es.mpt.dsic.inside.service.util.InsideWSUtils;
import es.mpt.dsic.inside.service.util.UtilidadDigestInsideImpl;
import es.mpt.dsic.inside.service.util.WebConstants;
import es.mpt.dsic.inside.service.util.XMLUtils;
import es.mpt.dsic.inside.service.utilFirma.exception.InsideServiceUtilFirmaException;
import es.mpt.dsic.inside.service.visualizacion.InsideServiceVisualizacion;
import es.mpt.dsic.inside.service.visualizacion.exception.InsideServiceVisualizacionException;
import es.mpt.dsic.inside.util.xml.JAXBMarshaller;
import es.mpt.dsic.inside.web.index.IndexActions;
import es.mpt.dsic.inside.web.object.ComboItem;
import es.mpt.dsic.inside.web.object.MessageObject;
import es.mpt.dsic.inside.web.util.ComboUtils;
import es.mpt.dsic.inside.web.util.MetadatosEEMGDE;
import es.mpt.dsic.inside.web.util.SignatureUtils;
import es.mpt.dsic.inside.ws.exception.InsideWSException;
import es.mpt.dsic.inside.ws.exception.InsideWsErrors;
import es.mpt.dsic.inside.ws.util.InSideDateAdapter;
import es.mpt.dsic.inside.ws.validation.exception.InsideValidationDataException;
import es.mpt.dsic.inside.xml.eni.documento.TipoDocumento;
import es.mpt.dsic.inside.xml.eni.expediente.TipoExpediente;
import es.mpt.dsic.inside.xml.eni.expediente.indice.TipoIndice;
import es.mpt.dsic.inside.xml.eni.expediente.indice.contenido.TipoCarpetaIndizada;
import es.mpt.dsic.inside.xml.eni.expediente.indice.contenido.TipoDocumentoIndizado;
import es.mpt.dsic.inside.xml.eni.expediente.indice.contenido.TipoIndiceContenido;
import es.mpt.dsic.inside.xml.eni.expediente.metadatos.EnumeracionEstados;
import es.mpt.dsic.inside.xml.eni.expediente.metadatos.TipoMetadatos;
import es.mpt.dsic.inside.xml.inside.MetadatoAdicional;
import es.mpt.dsic.inside.xml.inside.TipoExpedienteInsideConMAdicionales;
import es.mpt.dsic.inside.xml.inside.TipoMetadatosAdicionales;
import es.mpt.dsic.inside.xml.inside.ws.credential.WSCredentialInside;
import es.mpt.dsic.inside.xml.inside.ws.documento.conversion.TipoDocumentoConversionInside;
import es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.TipoExpedienteValidacionInside;
import es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.TipoOpcionValidacionExpediente;
import es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.TipoOpcionesValidacionExpedienteInside;
import es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.resultados.TipoResultadoValidacionExpedienteInside;

@Controller
@PropertySource("classpath:config.properties")
public class ExpedientController {

  protected static final Log logger = LogFactory.getLog(ExpedientController.class);

  @Autowired
  private InsideUtilService insideUtilService;

  @Autowired
  private MessageSource messageSource;

  @Autowired
  private InSideService insideService;

  @Autowired
  private MailService mailService;

  @Autowired
  private InsideServiceVisualizacion serviceVisualizar;

  @Autowired
  private CsvService csvService;

  @Autowired
  private JAXBMarshaller marshaller;

  private List<String> organos;

  @Autowired
  private SignatureUtils signatureUtils;

  @Autowired
  private TemporalDataBusinessService temporalDataBusinessService;

  @Autowired
  private InsideServiceSigner insideSigner;

  @Autowired
  @Qualifier("MetadatosIdentifierExpValidation")
  private MetadatoValidatorStringRegex metadatosIdentifierExpValidation;

  @Autowired
  private InSideAuditoriaService inSideAuditoriaService;

  @Autowired
  EniService eniService;

  @Autowired
  private AutocompleteController autocompleteController;

  @Autowired
  private UtilidadDigestInsideImpl utilidadDigestInsideImpl;

  @Value("${visualizarContenido.pdfDefault}")
  private String pdfDefault;

  private static final String MENSAJE_USU = "mensajeUsuario";
  private static final String EXPEDIENTES = "expedientes";
  private static final String EXPEDIENTES_ALMACENADOS = "expedientesAlmacenados";
  private static final String VIEW_EDIT_EXPEDIENT = "expediente/editarExpediente";
  private static final String VIEW_EXPEDIENTS_STORED = "expediente/expedientes_almacenados";
  private static final String VIEW_SHOW_EXPEDIENT = "expediente/verExpediente";
  private static final String RASTRO_EXPEDIENTE = "rastroExpediente";
  private static final String SIGNATURE_SERVER = "firmaServidor";
  private static final String EXPEDIENT_ENI_CONVERT = "expedienteEniConvert";
  private static final String DATA_FILE_EXP = "dataFileExp";
  private static final String ID_EXPEDIENT = "idExpediente";
  private static final String LIST_IDS_DOCUMENTO = "listaIdsDocumento";
  private static final String DOCS = "docs";
  private static final String NEW_DOCS = "newDocs";
  private static final String SHOW_MESSAGE = "showMessage";
  private static final String INDICE = "indice";
  private static final String EXPEDIENT_ENI = "expedientEni";
  private static final String DOCS_NO_CREATE = "docsNoCreate";
  private static final String ERROR_GUARDAR = "errorGuardar";
  private static final String SHOW_FIRMA_SERVER = "showFirmaServer";
  private static final String ID = "id";
  private static final String EXPEDIENTE_ENI = "expedienteEni";
  private static final String EXPEDIENTE_ENI_INDICE = "expedienteIndiceContenido";
  private static final String NOMBRE_NATURAL_EXP = "nombreNatural";
  private static final String ORDEN_DOC_EXP = "ordenDocExp";
  private static final String FECHA_INCORP_EXP = "fechaIncorpExp";
  private static final String IDENTIFICADOR = "identificador";
  private static final String NAVEGADOR = "navegador";
  private static final String RESGUARDODOCBASE63 = "dataResguardoDocBase64";
  private static final String CONTENT_DISPOSITION = "Content-Disposition";
  private static final String ATTACHMENT_FILENAME = "attachment; filename=\"";

  @RequestMapping(value = "/generarExpediente", method = RequestMethod.GET)
  public ModelAndView generarExpediente(Locale locale, HttpSession session,
      HttpServletRequest request) {
    this.organos = null;
    logger.debug("Iniciado ExpedientController.generarExpediente");
    ModelAndView modelAndView = new ModelAndView("expediente/generarExpediente");

    init(modelAndView, locale);

    // Se instancia el expedienteEni con valores iniciales
    TipoExpedienteInsideConMAdicionales tipoExpedienteMA =
        new TipoExpedienteInsideConMAdicionales();
    tipoExpedienteMA.setExpediente(new TipoExpediente());
    tipoExpedienteMA.getExpediente().setMetadatosExp(new TipoMetadatos());
    tipoExpedienteMA.getExpediente().setIndice(new TipoIndice());
    tipoExpedienteMA.getExpediente().getIndice().setIndiceContenido(new TipoIndiceContenido());
    tipoExpedienteMA.setMetadatosAdicionales(new TipoMetadatosAdicionales());
    tipoExpedienteMA.getExpediente().getMetadatosExp()
        .setIdentificador(generateDefaultId(request, false));

    modelAndView.addObject("expedienteMetadatos", tipoExpedienteMA);
    modelAndView.addObject("metadatoNombreNatural",
        MetadatosEEMGDE.METADATO_NOMBRE_NOMBRE_NATURAL.getValue());
    modelAndView.addObject("metadatoFechaFin",
        MetadatosEEMGDE.METADATO_FECHAS_FECHA_FIN.getValue());
    modelAndView.addObject(INDICE, convertIndex(tipoExpedienteMA.getExpediente().getIndice()
        .getIndiceContenido().getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada(), true));

    session.setAttribute(DOCS, new ArrayList<String>());
    session.setAttribute(NEW_DOCS, new HashMap<String, String>());

    logger.debug("Finalizado ExpedientController.generarExpediente");

    initDocument(modelAndView, locale, request);

    modelAndView.addObject(SHOW_MESSAGE, false);

    ObjetoInsideUsuario usuario =
        (ObjetoInsideUsuario) session.getAttribute(WebConstants.USUARIO_SESSION);
    modelAndView.addObject(SHOW_FIRMA_SERVER, insideService.checkSignatureServerByUser(usuario));

    ///// cargar los documentos de la unidad porque el autocomplete de js no
    ///// funciona en internet Explorer
    ///// ////////////////////////////////////////

    String listaDocumentosJSON = convertListToJSONString(request, "documento");
    String listaExpedientesJSON = convertListToJSONString(request, "expediente");

    modelAndView.addObject("listaDocumentosJSON", listaDocumentosJSON);
    modelAndView.addObject("listaExpedientesJSON", listaExpedientesJSON);

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    return modelAndView;
  }

  private String convertListToJSONString(HttpServletRequest request,
      String Expediente_o_Documento) {
    List<ComboItem> lista = null;

    if ("documento".equalsIgnoreCase(Expediente_o_Documento)) {
      lista = autocompleteController.autocompleteDocumentos(request, "");
    }

    if ("expediente".equals(Expediente_o_Documento)) {
      lista = autocompleteController.autocompleteExpedientes(request, true, true, "");
    }

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

  private String generateDefaultId(HttpServletRequest request, boolean isDocument) {
    String defaultId = "";
    if (request.getContextPath().contains(WebConstants.CONTEXT_PATH_INSIDE)) {
      ObjetoInsideUsuario usuario =
          (ObjetoInsideUsuario) request.getSession().getAttribute(WebConstants.USUARIO_SESSION);
      if (usuario != null && usuario.getUnidadOrganicaActiva() != null) {
        String unidadOrganica = usuario.getUnidadOrganicaActiva();
        unidadOrganica = unidadOrganica.substring(0, unidadOrganica.indexOf(" "));
        defaultId = insideUtilService.generateDefaultId(unidadOrganica, isDocument);
      }
    }
    return defaultId;
  }

  @RequestMapping(value = "/obtenerIdDocumentoPorDefecto", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> obtenerIdDocumentoPorDefecto(HttpServletRequest request) {
    Map<String, Object> retorno = new HashMap<String, Object>();
    retorno.put("identificadorDocumento", generateDefaultId(request, true));
    return retorno;
  }

  @RequestMapping(value = "/comprobarIdentificadorExpediente", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> comprobarIdentificadorExpedientePost(
      @RequestParam("identificadorExpediente") String identificadorExpediente) {

    logger.debug("Iniciado ExpedientController.comprobarIdentificadorExpedientePost");

    Map<String, Object> retorno = new HashMap<String, Object>();
    MessageObject mensaje = null;
    ObjetoExpedienteInside exp = null;

    try {
      exp = insideService.getExpediente(identificadorExpediente, false);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_INFO,
          "El identificador " + exp.getIdentificador() + " de expediente ya existe");
      retorno.put("mensajeIdentificador", mensaje);
      retorno.put("identificadorExpediente", identificadorExpediente);
    } catch (InSideServiceException e) {
      logger.debug(e);
      // Salta a la excepcion si no existe el identificador.
      if (StringUtils.isBlank(identificadorExpediente)) {
        mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
            "Identificador vacio o blanco no válido");
        retorno.put(MENSAJE_USU, mensaje);
      }
    }
    logger.debug("Finalizado ExpedientController.comprobarIdentificadorExpedientePost");
    return retorno;
  }

  @RequestMapping(value = "/generarExpediente", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> generarExpedientePost(
      @ModelAttribute TipoExpedienteInsideConMAdicionales tipoExpedienteMA, Locale locale,
      HttpServletRequest request, @RequestParam("contenidoExp") String contenidoExp) {
    logger.debug("Iniciado ExpedientController.generarExpedientePost");
    Map<String, Object> retorno = new HashMap<String, Object>();
    MessageObject mensaje = null;

    try {
      retorno.put(ERROR_GUARDAR, true);
      generateExpedient(tipoExpedienteMA, locale, request, contenidoExp, retorno);
    } catch (InsideServiceInternalException e) {
      logger
          .warn("ExpedientController.generarExpedientePost --> Error al generar expediente: " + e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      retorno.put(MENSAJE_USU, mensaje);
    } catch (Exception e) {
      if (!retorno.containsKey(MENSAJE_USU)) {
        logger.error(
            "ExpedientController.generarExpedientePost --> Error al generar expediente: " + e);
        mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
            messageSource.getMessage(WebConstants.MSG_KEY_ERROR_GENERIC, null, locale));
        retorno.put(MENSAJE_USU, mensaje);
      }
    }

    logger.debug("Finalizado ExpedientController.generarExpedientePost");
    return retorno;
  }

  public ObjetoExpedienteInside generateExpedient(
      TipoExpedienteInsideConMAdicionales tipoExpedienteMA, Locale locale,
      HttpServletRequest request, String contenidoExp, Map<String, Object> retorno)
      throws InsideServiceInternalException {
    TipoExpediente expAux;

    try {

      String firmarLongeva = request.getParameter("firmarLongevaExpediente");

      setData(tipoExpedienteMA.getExpediente(), request);
      validateData(tipoExpedienteMA.getExpediente(), locale);

      // debo recoger el id del expediente para rellenarlo y se usara para
      // el id del indiceContenido
      String idExpediente = tipoExpedienteMA.getExpediente().getMetadatosExp().getIdentificador();
      expAux = getExpedient(contenidoExp.getBytes(), idExpediente);
      tipoExpedienteMA.getExpediente().setIndice(expAux.getIndice());

      String idIndiceContenido = "EXP_INDICE_CONTENIDO"
          + tipoExpedienteMA.getExpediente().getMetadatosExp().getIdentificador();
      tipoExpedienteMA.getExpediente().getIndice().getIndiceContenido().setId(idIndiceContenido);

      byte[] firmaIndice = Base64.decodeBase64(request.getParameter("expedienteBytes"));
      // si no rellena el indice vacio y hay que lanzar mensaje
      if (tipoExpedienteMA.getExpediente().getIndice().getIndiceContenido()
          .getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada() == null
          || CollectionUtils.isEmpty(tipoExpedienteMA.getExpediente().getIndice()
              .getIndiceContenido().getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada())) {
        generateValidateMessage(locale, retorno, new InsideServiceInternalException(messageSource
            .getMessage(WebConstants.MSG_VALIDACION_EXP_INDICE_INCOMPLETO, null, locale)));
      }

      if ("server".equalsIgnoreCase(request.getParameter("firmaExp"))) {
        WSCredentialInside credential = new WSCredentialInside();

        ObjetoInsideUsuario usuario =
            (ObjetoInsideUsuario) request.getSession().getAttribute(WebConstants.USUARIO_SESSION);
        ObjetoInsideUnidadAplicacionEeutil app = insideService.getApplicationEeutilByUser(usuario);

        credential.setIdaplicacion(app.getIdAplicacion());
        credential.setPassword(app.getPassword());

        // se necesita parar el id del nodo a firmar y en destino lo
        // recogemos en PeticionFirmaFichero
        String idNodeToSign = "EXP_INDICE_CONTENIDO" + idExpediente;
        firmaIndice = insideSigner.firmarFicheroWhitPropertie(firmaIndice, null, "XAdES Enveloped",
            "IMPLICIT", idNodeToSign, credential);
        ObjetoAuditoriaFirmaServidor objetoAuditoriaFirmaServidor =
            new ObjetoAuditoriaFirmaServidor(Constantes.ELEMENTO_EXPEDIENTE,
                tipoExpedienteMA.getExpediente().getMetadatosExp().getIdentificador(),
                usuario.getNif());
        insideService.saveAuditoriaFirmaServidor(objetoAuditoriaFirmaServidor);
      }
      // Tratar firma longeva
      if (StringUtils.isNotBlank(firmarLongeva) && firmarLongeva.trim().equals("true")) {
        try {
          firmaIndice = insideUtilService.tratarFirmaLongevaExpediente(firmaIndice);
        } catch (Exception e) {
          logger
              .warn("Error al intentar ampliar firma " + e.getLocalizedMessage() + e.getMessage());
          MessageObject mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
              messageSource.getMessage(WebConstants.MSG_GENERAR_FIRMA_LONGEVA_ERROR, null, locale));
          throw new InsideServiceInternalException(mensaje.getMessage());
        }
      }

      tipoExpedienteMA.getExpediente().getIndice().setFirmas(signatureUtils.setSignaturesExp(
          firmaIndice, tipoExpedienteMA.getExpediente().getMetadatosExp().getIdentificador()));

      ObjetoExpedienteInside expedienteInside =
          InsideConverterExpediente.expedienteEniToInside(tipoExpedienteMA.getExpediente());

      expedienteInside = insideService.validarExpediente(expedienteInside);

      if (tipoExpedienteMA.getMetadatosAdicionales() != null) {
        // Si contiene metadatos adicionales limpiamos los que puedan
        // venir nulos por haber sido eliminados en la edición
        this.eliminaMetadatosAdicionalesVacios(
            tipoExpedienteMA.getMetadatosAdicionales().getMetadatoAdicional());
        expedienteInside.getMetadatos().getMetadatosAdicionales()
            .addAll(metadatosAdicionalesToInside(tipoExpedienteMA.getMetadatosAdicionales()));
      }

      expedienteInside = insideService.obtenerVisualizacionIndiceSiActivo(expedienteInside, false,
          false, null, false, null, true);
      tipoExpedienteMA.getExpediente().setVisualizacionIndice(InsideConverterDocumento.Contenido
          .contenidoInsideToEni(expedienteInside.getVisualizacionIndice(), null));

      String data = incluirAdicionales(tipoExpedienteMA, retorno);

      retorno.put("visualizar", Base64.encodeBase64String(
          tipoExpedienteMA.getExpediente().getVisualizacionIndice().getValorBinario()));

      // Para validar la firma recoger solo el ns7:expediente sin
      // metadatosadicionales
      String dataConFirmaSinIdentar =
          marshaller.marshallDataExpedient(tipoExpedienteMA.getExpediente());

      // sustituye el nodo <ns7:expediente por el dataConFirmaSinIdentar
      // que esta sin identar y bien formado
      data = XMLUtils.construirExpedienteENIValido(data, dataConFirmaSinIdentar);

      validateGeneratedExpediente(locale, retorno, data);

      return expedienteInside;
    } catch (InSideServiceValidationException e1) {
      logger.warn(
          "ExpedientController.generarExpedientePost --> Error en las validaciones del expediente: "
              + e1);
      MessageObject mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e1.getMessage());
      throw new InsideServiceInternalException(mensaje.getMessage());
    } catch (InsideValidationDataException e1) {
      logger.warn(
          "ExpedientController.generarExpedientePost --> Error en las validaciones del expediente: "
              + e1);
      MessageObject mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e1.getMessage());
      throw new InsideServiceInternalException(mensaje.getMessage());
    } catch (Exception e3) {
      logger
          .warn("ExpedientController.generarExpedientePost --> Error al generar expediente: " + e3);
      throw new InsideServiceInternalException(e3.getMessage());
    }
  }

  private void cleanSpecialCaractersInFolders(
      List<ObjetoExpedienteInsideIndiceContenidoElementoIndizado> list) {
    for (ObjetoExpedienteInsideIndiceContenidoElementoIndizado elem : list) {
      if (elem instanceof ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada) {
        ((ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada) elem)
            .setIdentificadorCarpeta(InsideUtils
                .remplazaCaracteres(((ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada) elem)
                    .getIdentificadorCarpeta()));
        cleanSpecialCaractersInFolders(
            ((ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada) elem).getElementosIndizados());
      } else if (elem instanceof ObjetoExpedienteInsideIndiceContenidoElementoContenedorElementos) {
        cleanSpecialCaractersInFolders(
            ((ObjetoExpedienteInsideIndiceContenidoElementoContenedorElementos) elem)
                .getElementosIndizados());
      }
    }
  }

  public void validateGeneratedExpediente(Locale locale, Map<String, Object> retorno, String data)
      throws InsideServiceInternalException {
    MessageObject mensaje;
    try {
      insideUtilService.validateExpedientImport(data.getBytes());

      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_SUCCESS,
          messageSource.getMessage(WebConstants.MSG_GENERAR_EXP_OK, null, locale));
      retorno.put(MENSAJE_USU, mensaje);
    } catch (InSideServiceException e) {
      logger.warn("Error en la validación del expediente", e);
      generateValidateMessage(locale, retorno, e);
      MessageObject mensajeObject = (MessageObject) retorno.get(MENSAJE_USU);
      throw new InsideServiceInternalException(mensajeObject.getMessage());
    }
  }

  private void generateValidateMessage(Locale locale, Map<String, Object> retorno,
      InSideServiceException e) {
    MessageObject mensaje;
    String mensajeException = e.getMessage() != null ? e.getMessage() : "";
    if (esIndiceIncompleto(locale, mensajeException)) {
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, messageSource
          .getMessage(WebConstants.MSG_VALIDACION_EXP_INDICE_INCOMPLETO_MENSAJE, null, locale));
    } else if (esIndiceConCarpetaIncompleta(locale, mensajeException)) {
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, messageSource
          .getMessage(WebConstants.MSG_VALIDACION_EXP_CARPETA_INCOMPLETA_MENSAJE, null, locale));
    } else {
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
          messageSource.getMessage(WebConstants.MSG_VALIDACION_EXP_FORMATO_ERROR, null, locale)
              + ". " + e.getMessage());
    }
    retorno.put(MENSAJE_USU, mensaje);
  }

  private boolean esIndiceIncompleto(Locale locale, String mensajeException) {
    return mensajeException.contains(
        messageSource.getMessage(WebConstants.MSG_VALIDACION_EXP_INDICE_INCOMPLETO, null, locale));
  }

  private boolean esIndiceConCarpetaIncompleta(Locale locale, String mensajeException) {
    return mensajeException.contains(
        messageSource.getMessage(WebConstants.MSG_VALIDACION_EXP_CARPETA_INCOMPLETA, null, locale));
  }

  private List<ObjetoInsideMetadatoAdicional> metadatosAdicionalesToInside(
      TipoMetadatosAdicionales metadatosAdicionales) {
    List<ObjetoInsideMetadatoAdicional> listaMetadatos = new ArrayList();
    for (MetadatoAdicional metadato : metadatosAdicionales.getMetadatoAdicional()) {
      ObjetoInsideMetadatoAdicional insideMetadato = new ObjetoInsideMetadatoAdicional();
      insideMetadato.setNombre(metadato.getNombre());
      insideMetadato.setValor(metadato.getValor());
      listaMetadatos.add(insideMetadato);
    }
    return listaMetadatos;
  }

  public String incluirAdicionales(TipoExpedienteInsideConMAdicionales tipoExpedienteMA,
      Map<String, Object> retorno) throws InSideServiceException, JAXBException {
    String data = insideUtilService.getStringExpedienteMarshall(tipoExpedienteMA);

    String dataConFirmaSinIdentar =
        marshaller.marshallDataExpedient(tipoExpedienteMA.getExpediente());
    // Nodo <ns7:expediente por el dataConFirmaSinIdentar
    data = XMLUtils.construirExpedienteENIValido(data, dataConFirmaSinIdentar);

    // aniadir los namespaces para que firma y valida
    retorno.put(EXPEDIENT_ENI_CONVERT, data);
    return data;
  }

  @RequestMapping(value = "/descargarExpedienteEni", method = RequestMethod.POST)
  public ModelAndView descargarExpedienteEni(@RequestParam("contenido") String contenido,
      @RequestParam(IDENTIFICADOR) String identificador, HttpServletResponse response,
      Locale locale, @RequestParam("viewName") String viewName) {
    try {
      byte[] data = XMLUtils.deFirmaBase64_A_DSSignature(contenido.getBytes());

      OutputStream pr = response.getOutputStream();
      response.setContentType("application/text");
      response.addHeader("Content-Disposition",
          "attachment; filename=\"" + identificador + ".xml\"");

      pr.write(data);
      pr.close();

      return null;
    } catch (IOException e) {
      logger.error("ExpedientController.descargarExpedienteEni: " + identificador, e);
      MessageObject mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
          messageSource.getMessage(WebConstants.MSG_KEY_ERROR_GENERIC, null, locale));
      ModelAndView retorno = new ModelAndView(viewName);
      retorno.addObject(MENSAJE_USU, mensaje);
      return retorno;
    }
  }

  @RequestMapping(value = "/descargarExpedienteEniValidarFirma", method = RequestMethod.POST)
  public ModelAndView descargarExpedienteEniValidarFirma(
      @RequestParam("contenidoValidarFirma") String contenidoValidarFirma,
      @RequestParam(IDENTIFICADOR) String identificador, HttpServletResponse response,
      Locale locale, @RequestParam("viewName") String viewName) {
    try {
      byte[] data = XMLUtils.deFirmaBase64_A_DSSignature(contenidoValidarFirma.getBytes());

      OutputStream pr = response.getOutputStream();
      response.setContentType("application/text");
      response.addHeader("Content-Disposition",
          "attachment; filename=\"" + identificador + ".xml\"");

      pr.write(data);
      pr.close();

      return null;
    } catch (IOException e) {
      logger.error("ExpedientController.descargarExpedienteEniValidarFirma: " + identificador, e);
      MessageObject mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
          messageSource.getMessage(WebConstants.MSG_KEY_ERROR_GENERIC, null, locale));
      ModelAndView retorno = new ModelAndView(viewName);
      retorno.addObject(MENSAJE_USU, mensaje);
      return retorno;
    }
  }

  @RequestMapping(value = "/descargarExpedienteEniCompleto", method = RequestMethod.POST)
  public ModelAndView descargarExpedienteEniCompleto(HttpSession session,
      @RequestParam("contenido") String contenido,
      @RequestParam(IDENTIFICADOR) String identificador, HttpServletResponse response,
      Locale locale, @RequestParam("viewName") String viewName) {
    try {
      TipoExpediente tipoExpediente = getExpedient(contenido.getBytes(), identificador);
      Map<String, String> indice = insideService.obtenerDocsExpTipo(tipoExpediente.getIndice()
          .getIndiceContenido().getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada(), "");

      Map<String, byte[]> ficheros = this.getMapFicherosIndice(indice, false, session);

      // Necesario poner la firma en claro en el expENI para que valide
      byte[] expedienteENIListoParaValidar = insideUtilService
          .transformarExpedienteDescargaCompletoParaValidarFirma(tipoExpediente, contenido);
      ficheros.put(identificador, expedienteENIListoParaValidar);

      byte[] ficheroZip = InsideUtils.generateZip(ficheros, WebConstants.FORMAT_XML);

      OutputStream pr = response.getOutputStream();
      response.setContentType("application/zip");
      response.addHeader("Content-Disposition",
          "attachment; filename=\"" + identificador + ".zip\"");

      pr.write(ficheroZip);
      pr.close();
      return null;

    } catch (Exception e) {
      logger.error("ExpedientController.descargarExpedienteEniCompleto: " + identificador, e);
      MessageObject mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
          messageSource.getMessage(WebConstants.MSG_KEY_ERROR_GENERIC, null, locale));
      ModelAndView retorno = new ModelAndView(viewName);
      retorno.addObject(MENSAJE_USU, mensaje);
      return retorno;
    }

  }

  @RequestMapping(value = "/descargarExpedienteEniCompletoFisico", method = RequestMethod.POST)
  public ModelAndView descargarExpedienteEniCompletoFisico(HttpSession session,
      @RequestParam("contenido") String contenido,
      @RequestParam(IDENTIFICADOR) String identificador, HttpServletResponse response,
      Locale locale, @RequestParam("viewName") String viewName) {
    try {
      TipoExpediente tipoExpediente = getExpedient(contenido.getBytes(), identificador);
      Map<String, String> indice = insideService.obtenerDocsExpTipo(tipoExpediente.getIndice()
          .getIndiceContenido().getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada(), "");

      Map<String, byte[]> ficheros = this.getMapFicherosIndice(indice, true, session);

      byte[] ficheroZip = insideUtilService.generateZipFicherosFisicos(ficheros, marshaller,
          insideService, session.getId());

      OutputStream pr = response.getOutputStream();
      response.setContentType("application/zip");
      response.addHeader("Content-Disposition",
          "attachment; filename=\"" + identificador + ".zip\"");

      pr.write(ficheroZip);
      pr.close();
      return null;

    } catch (Exception e) {
      logger.error("ExpedientController.descargarExpedienteEniCompletoFisico: " + identificador, e);
      MessageObject mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
          messageSource.getMessage(WebConstants.MSG_KEY_ERROR_GENERIC, null, locale));
      ModelAndView retorno = new ModelAndView(VIEW_EXPEDIENTS_STORED);
      retorno.addObject(MENSAJE_USU, mensaje);
      retorno.addObject(EXPEDIENTES, new ArrayList<ObjetoInsideExpedienteUnidad>());
      retorno.addObject(EXPEDIENTES_ALMACENADOS, false);
      return retorno;
    }

  }

  /**
   * Devuelve en un Map los ficheros que componen un índice. Se tienen en cuenta las carpetas según
   * parámetro conCarpetas. Se tiene en cuenta si contienen metadatos. Los ficheros del índice se
   * buscan en primer lugar en la carpeta temporal (session). Si no se encuentran se obtienen de la
   * base de datos.
   */
  private Map<String, byte[]> getMapFicherosIndice(Map<String, String> indice, boolean conCarpetas,
      HttpSession session) throws InsideServiceInternalException {
    Map<String, byte[]> ficheros = new HashMap<String, byte[]>();
    try {
      for (Map.Entry<String, String> entry : indice.entrySet()) {
        byte[] fichero = insideUtilService.lazyLoadXmlFile(session.getId(), entry.getKey(), true);
        String llave = conCarpetas ? entry.getValue() + "/" + entry.getKey() : entry.getKey();
        ficheros.put(llave, fichero);
      }
    } catch (Exception e) {
      throw new InsideServiceInternalException("Error al obtener los ficheros del indice", e);
    }
    return ficheros;
  }

  @RequestMapping(value = "/validarExpediente", method = RequestMethod.GET)
  public ModelAndView validarExpediente() {
    return new ModelAndView("expediente/validarExpediente");
  }

  @RequestMapping(value = "/validarExpediente", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> validarExpedientePost(Locale locale, HttpSession session,
      HttpServletRequest request) {
    logger.debug("Iniciado ExpedientController.validarExpedientePost");

    Map<String, Object> retorno = new HashMap<String, Object>();
    MessageObject mensaje = null;
    TipoResultadoValidacionExpedienteInside resultado = null;
    TipoExpedienteValidacionInside expedienteValidacion = new TipoExpedienteValidacionInside();
    try {
      List<String> opcionesValidacion = new ArrayList<String>();
      if (request.getParameterValues("opcionesValidacion") != null)
        opcionesValidacion = Arrays.asList(request.getParameterValues("opcionesValidacion"));
      String idExpediente = request.getParameter("expedientId");
      byte[] data = temporalDataBusinessService.getFile(session.getId(), idExpediente);
      expedienteValidacion
          .setOpcionesValidacionExpediente(getOpcionesValidacion(opcionesValidacion));
      validaLongitudIdentificador(expedienteValidacion, data);
      resultado = validarExpediente(expedienteValidacion, data);
      if (resultado.getValidacionDetalle() == null) {
        logger.error("ExpedientController.validarExpedientePost --> Error al generar expediente");
        mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
            messageSource.getMessage(WebConstants.MSG_KEY_EXP_EXPENI_NO_VALIDO, null, locale));
        retorno.put(MENSAJE_USU, mensaje);
      }

      retorno.put("resultado", resultado);
    } catch (InsideWSException e) {
      logger.error("ExpedientController.validarExpedientePost --> Error al validar expediente", e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
          messageSource.getMessage(WebConstants.MSG_IMPORTAR_EXP_ID_NO_VALIDO, null, locale));
      retorno.put(MENSAJE_USU, mensaje);
    } catch (InSideServiceException e) {
      logger.warn("ExpedientController.validarExpedientePost --> Error interno generar expediente",
          e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      retorno.put(MENSAJE_USU, mensaje);
    } catch (Exception e) {
      logger.error("ExpedientController.validarExpedientePost --> Error al generar expediente", e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
          messageSource.getMessage(WebConstants.MSG_KEY_EXP_EXPENI_NO_VALIDO, null, locale));
      retorno.put(MENSAJE_USU, mensaje);
    }
    logger.debug("Finalizado ExpedientController.validarExpedientePost");

    return retorno;
  }

  private void validaLongitudIdentificador(TipoExpedienteValidacionInside expedienteValidacion,
      byte[] data) throws InsideConverterException, InSideServiceException, InsideWSException {
    expedienteValidacion.setContenido(data);
    ObjetoExpedienteInside expedienteInside =
        InsideConverterExpediente.tipoExpedienteValidacionToInside(expedienteValidacion);

    if (!insideUtilService.esLongitudIdentificadorValido(expedienteInside.getIdentificador())) {
      throw new InsideWSException(InsideWsErrors.IDENTIFICADOR_DOCUMENTO_LONGITUD, null, "");
    }

  }

  public TipoOpcionesValidacionExpedienteInside getOpcionesValidacion(
      List<String> opcionesValidacion) {
    TipoOpcionesValidacionExpedienteInside opciones = new TipoOpcionesValidacionExpedienteInside();
    if (opcionesValidacion.contains(TipoOpcionValidacionExpediente.TOVE_01.value())) {
      opciones.getOpcionValidacionExpediente().add(TipoOpcionValidacionExpediente.TOVE_01);
    }
    if (opcionesValidacion.contains(TipoOpcionValidacionExpediente.TOVE_02.value())) {
      opciones.getOpcionValidacionExpediente().add(TipoOpcionValidacionExpediente.TOVE_02);
    }
    if (opcionesValidacion.contains(TipoOpcionValidacionExpediente.TOVE_03.value())) {
      opciones.getOpcionValidacionExpediente().add(TipoOpcionValidacionExpediente.TOVE_03);
    }
    if (opcionesValidacion.contains(TipoOpcionValidacionExpediente.TOVE_04.value())) {
      opciones.getOpcionValidacionExpediente().add(TipoOpcionValidacionExpediente.TOVE_04);
    }
    return opciones;
  }

  private TipoResultadoValidacionExpedienteInside validarExpediente(
      TipoExpedienteValidacionInside expedienteValidacion, byte[] data)
      throws InSideServiceException {
    expedienteValidacion.setContenido(data);
    return insideUtilService.validarExpedienteEniFile(expedienteValidacion, true);
  }

  @RequestMapping(value = "/visualizarExpediente", method = RequestMethod.GET)
  public ModelAndView visualizarExpediente() {
    return new ModelAndView("expediente/visualizarExpediente");
  }

  @RequestMapping(value = "/visualizarExpediente", method = RequestMethod.POST)
  public ModelAndView visualizarExpedientePost(@RequestParam("visualizar") String visualizar,
      @RequestParam(IDENTIFICADOR) String identificador, HttpServletResponse response,
      Locale locale, @RequestParam("viewName") String viewName) {
    MessageObject mensaje = null;
    ModelAndView retorno = new ModelAndView(viewName);
    try {
      byte[] data = Base64.decodeBase64(visualizar.getBytes());
      if ((identificador == null) || (identificador.length() <= 0)) {
        identificador = messageSource.getMessage(WebConstants.MSG_KEY_DOC_VISUALIZAR_NOMBRE_DOCENI,
            null, locale);
      }
      OutputStream pr = response.getOutputStream();
      response.setContentType("application/pdf");
      response.addHeader("Content-Disposition",
          "attachment; filename=\"" + identificador + ".pdf\"");

      pr.write(data);
      pr.close();

      return null;
    } catch (NoSuchMessageException e) {
      logger.error("Error al generar documento", e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
          messageSource.getMessage(WebConstants.MSG_KEY_ERROR_GENERIC, null, locale));
      retorno.addObject(MENSAJE_USU, mensaje);
    } catch (IOException e) {
      logger.error("Error al generar documento", e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
          messageSource.getMessage(WebConstants.MSG_KEY_ERROR_GENERIC, null, locale));
      retorno.addObject(MENSAJE_USU, mensaje);
    }
    return retorno;
  }

  private void init(ModelAndView modelAndView, Locale locale) {
    MessageObject mensaje = null;

    String fechaApertura = "";
    try {
      InSideDateAdapter adapter = new InSideDateAdapter();
      fechaApertura = adapter.marshalChrome(new Date());
    } catch (Exception e) {
      logger.error("ExpedientController.init --> Error al convertir la fecha apertura expediente",
          e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
          messageSource.getMessage(WebConstants.MSG_KEY_EXPED_ERROR_FECHA_INICIO, null, locale));
      modelAndView.addObject(MENSAJE_USU, mensaje);
    }

    modelAndView.addObject("fechaApertura", fechaApertura);
    modelAndView.addObject("estadosExpediente",
        ComboUtils.getEstadosExpediente(messageSource, locale));
  }

  private void setData(TipoExpediente tipoExpediente, HttpServletRequest request)
      throws ParseException, DatatypeConfigurationException, IOException {
    this.organos = new ArrayList<String>();
    this.organos.addAll(tipoExpediente.getMetadatosExp().getOrgano());

    String fechaApertura = request.getParameter("fechaApertura");
    tipoExpediente.getMetadatosExp().setFechaAperturaExpediente(
        InsideWSUtils.stringToXmlCalendar(fechaApertura, WebConstants.FORMATO_FECHA_DEFECTO));

    if (tipoExpediente.getIndice() == null) {
      tipoExpediente.setIndice(new TipoIndice());
    }

    List<String> organosData = new ArrayList<String>(tipoExpediente.getMetadatosExp().getOrgano());
    tipoExpediente.getMetadatosExp().getOrgano().clear();
    tipoExpediente.getMetadatosExp().getOrgano()
        .addAll(ComboUtils.getOrganosKeys(organosData, request));

    List<String> interesados =
        new ArrayList<String>(tipoExpediente.getMetadatosExp().getInteresado());
    tipoExpediente.getMetadatosExp().getInteresado().clear();
    tipoExpediente.getMetadatosExp().getInteresado()
        .addAll(ComboUtils.getInteresadosKeys(interesados, request));

    tipoExpediente.getMetadatosExp().setVersionNTI(WebConstants.VERSION_NTI);
  }

  @RequestMapping(value = "/crearCarpetaIndice", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> crearCarpetaIndice(HttpServletRequest request, final ModelMap model) {
    logger.debug("Iniciado ExpedientController.crearCarpetaIndice");
    Map<String, Object> retorno = new HashMap<String, Object>();
    MessageObject mensaje = null;
    try {
      // debo recoger el id del expediente para rellenarlo y se usara para
      // el id del indiceContenido
      String idExpediente = request.getParameter(ID_EXPEDIENT);

      TipoExpediente expedienteEniResultante =
          getExpedient(request.getParameter(EXPEDIENT_ENI).getBytes(), idExpediente);

      // añadimos la carpeta en la ruta adecuada
      String path = request.getParameter("path");
      String name = request.getParameter("name");
      expedienteEniResultante = IndexActions.createFolder(expedienteEniResultante, path, name);

      retorno = setDataIndex(expedienteEniResultante, retorno);

    } catch (Exception e) {
      logger.error("ExpedientController.crearCarpetaIndice --> Error al generar indice", e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      model.addAttribute(MENSAJE_USU, mensaje);
    }

    logger.debug("Finalizado ExpedientController.crearCarpetaIndice");
    return retorno;
  }

  @RequestMapping(value = "/crearEstructuraCarpetaIndice", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> crearEstructuraCarpetaIndice(HttpServletRequest request,
      Locale locale) {
    logger.debug("Iniciado ExpedientController.crearCarpetaIndice");
    Map<String, Object> retorno = new HashMap<String, Object>();
    MessageObject mensaje = null;
    try {
      ObjetoInsideUsuario usuario =
          (ObjetoInsideUsuario) request.getSession().getAttribute(WebConstants.USUARIO_SESSION);

      // debo recoger el id del expediente para rellenarlo y se usara para
      // el id del indiceContenido
      if (usuario != null) {
        String idExpediente = request.getParameter(ID_EXPEDIENT);

        TipoExpediente expedienteEniResultante =
            getExpedient(request.getParameter(EXPEDIENT_ENI).getBytes(), idExpediente);
        ObjetoEstructuraCarpetaInside estructuraCarpeta =
            insideService.getEstructuraCarpeta(usuario);
        TipoIndiceContenido tipoIndice =
            InsideConverterIndice.estructuraCarpetasInsideToEni(estructuraCarpeta);
        String path = request.getParameter("path");

        boolean correctMove =
            checkMoveElementNoRepeatInLevel(expedienteEniResultante, estructuraCarpeta, path);

        if (correctMove) {
          retorno = incluirEstructuraCarpetaEnIndiceExpediente(retorno, expedienteEniResultante,
              tipoIndice, path);
        } else {
          mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, messageSource
              .getMessage(WebConstants.MSG_GENERAR_EXP_CARPETAS_MISMO_ID, null, locale));
          retorno.put(MENSAJE_USU, mensaje);
        }
      } else {
        mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
            messageSource.getMessage(WebConstants.USUARIO_SESSION_EXPIRED, null, locale));
        retorno.put(MENSAJE_USU, mensaje);
      }
    } catch (Exception e) {
      logger.error("ExpedientController.crearCarpetaIndice --> Error al generar indice", e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      retorno.put(MENSAJE_USU, mensaje);
    }

    logger.debug("Finalizado ExpedientController.crearCarpetaIndice");
    return retorno;
  }

  public Map<String, Object> incluirEstructuraCarpetaEnIndiceExpediente(Map<String, Object> retorno,
      TipoExpediente expedienteEniResultante, TipoIndiceContenido tipoIndice, String path)
      throws InsideServiceInternalException {
    try {
      TipoExpediente expedienteConEstructuraCarpeta =
          IndexActions.createFolderComplete(expedienteEniResultante, tipoIndice, path);

      List<Object> newElementsIndex = new ArrayList<Object>();
      for (Object objeto : tipoIndice.getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada()) {
        newElementsIndex.add(objeto);
      }
      retorno.put("newElementsIndex", newElementsIndex);
      return setDataIndex(expedienteConEstructuraCarpeta, retorno);
    } catch (Exception e) {
      throw new InsideServiceInternalException(
          "Error en incluirEstructuraCarpetaEnIndiceExpediente", e);
    }
  }

  public boolean checkMoveElementNoRepeatInLevel(TipoExpediente expedienteEniResultante,
      ObjetoEstructuraCarpetaInside estructuraCarpeta, String path) {
    boolean correctMove = true;
    for (ObjetoExpedienteInsideIndiceContenidoElementoIndizado elemIndizado : estructuraCarpeta
        .getElementosIndizados()) {
      correctMove = IndexActions.checkMoveElementNoRepeatInLevel(expedienteEniResultante,
          ((ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada) elemIndizado)
              .getIdentificadorCarpeta(),
          path);
      if (!correctMove)
        break;
    }
    return correctMove;
  }

  @SuppressWarnings("unchecked")
  @RequestMapping(value = "/crearDocumentoIndice", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> crearDocumentoIndice(HttpServletRequest request, Locale locale,
      HttpSession session) {
    Map<String, Object> retorno = new HashMap<String, Object>();
    MessageObject mensaje = null;
    try {

      validateElementsCrearDocumentoIndice(request, locale);

      // debo recoger el id del expediente para rellenarlo y se usara para
      // el id del indiceContenido
      String idExpediente = request.getParameter(ID_EXPEDIENT);

      TipoExpediente expedienteEniResultante =
          getExpedient(request.getParameter(EXPEDIENT_ENI).getBytes(), idExpediente);

      // se añade el documento en la ruta adecuada
      String path = request.getParameter("path");
      String id = request.getParameter(ID);
      byte[] data;
      String fileName;
      ObjetoDocumentoInside docInside;
      if (id != null) {
        data = insideUtilService.lazyLoadXmlFile(session.getId(), id, true);
        docInside = insideUtilService.validateDocumentImport(data);
        fileName = id + WebConstants.FORMAT_XML;
      } else {
        fileName = request.getParameter("fileName");
        data = insideUtilService.lazyLoadXmlFile(session.getId(), fileName, false);
        docInside = insideUtilService.validateDocumentImport(data);
        id = docInside.getIdentificador();
        temporalDataBusinessService.escribir(data, session.getId(), id + WebConstants.FORMAT_XML,
            true);
      }

      boolean resultValidateIdDocument = validateIdDocument(session, locale, id, retorno);
      if (resultValidateIdDocument) {

        // se cambia la funcion resumen y se necesita un mapa con id del
        // doc y el algoritmo para la nueva
        // getValorHuellaContenidoAlgoritmo
        String funcionResumen = es.mpt.dsic.inside.util.InsideUtils.HUELLA_SHA512_LITERAL_URI;
        Map<String, String> mapaIdAlgoritmo = new HashMap<String, String>();
        mapaIdAlgoritmo.put(docInside.getIdentificador(), funcionResumen);
        String valorHuella =
            utilidadDigestInsideImpl.getValorHuellaContenidoAlgoritmo(docInside, mapaIdAlgoritmo);

        // String funcionResumen = "md5";
        // String valorHuella =
        // insideUtilService.getValorHuellaContenido(docInside);

        String ordenDocExp = "1";
        String fechaIncorpExp = request.getParameter(FECHA_INCORP_EXP);

        XMLGregorianCalendar fechaIncorpExpXML =
            InsideWSUtils.stringToXmlCalendar(fechaIncorpExp, WebConstants.FORMATO_FECHA_DEFECTO2);

        expedienteEniResultante = IndexActions.createDocument(expedienteEniResultante, path, id,
            valorHuella, funcionResumen, fechaIncorpExpXML, ordenDocExp);

        retorno.put(ID, id);
        retorno.put(NOMBRE_NATURAL_EXP,
            getNombreNatural(docInside.getMetadatos().getMetadatosAdicionales()));
        retorno = setDataIndex(expedienteEniResultante, retorno);

        ((List<String>) session.getAttribute(DOCS)).add(id);
        ((HashMap<String, String>) session.getAttribute(NEW_DOCS)).put(id, fileName);
      }
    } catch (InSideServiceException e) {
      logger.warn("InsideExpdientController.crearDocumentoIndice. Error: ", e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      retorno.put(MENSAJE_USU, mensaje);
    } catch (Exception e) {
      logger.error("InsideExpdientController.crearDocumentoIndice. Error", e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      retorno.put(MENSAJE_USU, mensaje);
    }
    logger.debug("Finalizado ExpedientController.crearDocumentoIndice");
    return retorno;
  }

  @RequestMapping(value = "/createSubExpedient", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> createSubExpedient(HttpSession session, HttpServletRequest request,
      Locale locale) {
    Map<String, Object> retorno = new HashMap<String, Object>();
    MessageObject msg = null;
    try {
      // debo recoger el id del expediente para rellenarlo y se usara para
      // el id del indiceContenido
      String idExpediente = request.getParameter(ID_EXPEDIENT);

      TipoExpediente expedienteEniResultante =
          getExpedient(request.getParameter(EXPEDIENT_ENI).getBytes(), idExpediente);

      String identifier = request.getParameter("identifierSubExpedient");
      if (checkIdentifierExpedient(session, locale, retorno, identifier))
        retorno =
            includeSubExpedient(session, locale, retorno, expedienteEniResultante, identifier);
    } catch (Exception e) {
      logger.error("ExpedientController.createSubExpedient --> Error al crear subExpediente", e);
      msg = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      retorno.put(MENSAJE_USU, msg);
    }
    return retorno;
  }

  private boolean checkIdentifierExpedient(HttpSession session, Locale locale,
      Map<String, Object> retorno, String identifier) throws InSideServiceException {
    MessageObject msg = null;
    boolean checkIdentifierExpedient = false;
    try {
      ObjetoExpedienteInside exp = insideService.getExpediente(identifier, false);
      ObjetoInsideUsuario usuario =
          (ObjetoInsideUsuario) session.getAttribute(WebConstants.USUARIO_SESSION);
      if (insideService.permisoObjetoUnidad(exp, usuario.getNif(), true)) {
        checkIdentifierExpedient = true;
      } else {
        msg = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, messageSource
            .getMessage(WebConstants.MSG_IMPORTAR_DOC_USUARIO_NO_ASOCIADO_EXP, null, locale));
        retorno.put(MENSAJE_USU, msg);
      }
    } catch (InsideStoreObjectNotFoundException e) {
      logger.debug("Expediente a incluir como subExpediente no existe:" + identifier, e);
      msg = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
          messageSource.getMessage(WebConstants.MSG_IMPORTAR_EXP_EXP_NO_VALIDO, null, locale));
      retorno.put(MENSAJE_USU, msg);
    }
    return checkIdentifierExpedient;
  }

  @RequestMapping(value = "/createSubExpedientToken", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> createSubExpedientToken(HttpSession session,
      HttpServletRequest request, Locale locale) {
    Map<String, Object> retorno = new HashMap<String, Object>();
    MessageObject msg = null;
    try {

      // debo recoger el id del expediente para rellenarlo y se usara para
      // el id del indiceContenido
      String idExpediente = request.getParameter(ID_EXPEDIENT);

      TipoExpediente expedienteEniResultante =
          getExpedient(request.getParameter(EXPEDIENT_ENI).getBytes(), idExpediente);

      String identifierExpedient = request.getParameter("identifierSubExpedient");
      String csv = request.getParameter("tokenCsv");
      String uuid = request.getParameter("tokenUuid");

      if (StringUtils.isNotBlank(identifierExpedient) && StringUtils.isNotBlank(csv)
          && StringUtils.isNotBlank(uuid)) {

        String nifConsultaPuestaDisposicion = getUsuarioConsultaPuestaDisposicion(session);
        Map<String, Object> result = insideUtilService.checkToken(nifConsultaPuestaDisposicion,
            identifierExpedient, csv, uuid);

        ObjetoExpedienteToken token = (ObjetoExpedienteToken) result.get("token");
        Boolean tokenCaducado = (Boolean) result.get("tokenCaducado");
        Boolean tokenInvalidoNif = (Boolean) result.get("tokenInvalidoNif");

        // si no esta caducado y es nif permitido
        if (token != null && !tokenCaducado && !tokenInvalidoNif) {
          retorno = includeSubExpedient(session, locale, retorno, expedienteEniResultante,
              identifierExpedient);
        } else {
          String errorToken = (String) result.get("errorToken");
          msg = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
              messageSource.getMessage(errorToken, null, locale));
          retorno.put(MENSAJE_USU, msg);
        }
      } else {
        msg = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, messageSource
            .getMessage(WebConstants.MSG_EXP_PUESTADISPOSICION_NO_VALIDO, null, locale));
        retorno.put(MENSAJE_USU, msg);
      }
    } catch (Exception e) {
      logger.error("ExpedientController.createSubExpedientToken --> Error al crear subExpediente",
          e);
      msg = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      retorno.put(MENSAJE_USU, msg);
    }
    return retorno;
  }

  public Map<String, Object> includeSubExpedient(HttpSession session, Locale locale,
      Map<String, Object> retorno, TipoExpediente expedienteEniResultante,
      String identifierExpedient) {
    Map<String, Object> resultado = new HashMap<String, Object>();
    MessageObject msg = null;
    try {
      ObjetoExpedienteInside exp = getExpedienteInside(identifierExpedient, null);
      cleanSpecialCaractersInFolders(exp.getIndice().getIndiceContenido().getElementosIndizados());
      TipoExpediente tExp = InsideConverterExpediente.expedienteInsideToEni(exp, null);
      List<String> listRepeatIds = insideService.checkRepeatDocsExpTipo(
          expedienteEniResultante.getIndice().getIndiceContenido()
              .getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada(),
          tExp.getIndice().getIndiceContenido()
              .getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada());

      if (CollectionUtils.isNotEmpty(listRepeatIds)) {
        Object[] args = new String[listRepeatIds.size()];
        for (int i = 0; i < listRepeatIds.size(); i++)
          args[i] = listRepeatIds.get(0);

        msg = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
            messageSource.getMessage(WebConstants.MSG_KEY_DOC_DOCENI_ID_REPETIDO, args, locale));
        retorno.put(MENSAJE_USU, msg);
      } else {
        List<Object> newElementsIndex = new ArrayList<Object>();
        TipoCarpetaIndizada newFolder = new TipoCarpetaIndizada();
        newFolder.setIdentificadorCarpeta(tExp.getMetadatosExp().getIdentificador());

        for (Object objeto : tExp.getIndice().getIndiceContenido()
            .getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada()) {
          newFolder.getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada().add(objeto);
        }

        expedienteEniResultante.getIndice().getIndiceContenido()
            .getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada().add(newFolder);
        newElementsIndex.add(newFolder);
        includeDocsInSession(session, exp);
        retorno.put("newElementsIndex", newElementsIndex);
      }
      resultado = setDataIndex(expedienteEniResultante, retorno);
    } catch (Exception e) {
      logger.error(
          "ExpedientController.crearVistaCerradaExpediente --> Error al guardar expediente", e);
      msg = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      retorno.put(MENSAJE_USU, msg);
    }
    return resultado;
  }

  @RequestMapping(value = "/crearDocumentoInside", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> createDocumentInside(HttpSession session, HttpServletRequest request,
      Locale locale) {
    Map<String, Object> retorno = new HashMap<String, Object>();
    MessageObject msg = null;
    String idDocumento = request.getParameter(ID);
    try {
      String idExpediente = request.getParameter(ID_EXPEDIENT);

      TipoExpediente expedienteEniResultante =
          getExpedient(request.getParameter(EXPEDIENT_ENI).getBytes(), idExpediente);

      String path = request.getParameter("path");
      idDocumento = request.getParameter(ID);

      if (checkIdentifierDocument(session, locale, retorno, idDocumento)) {

        byte[] data = insideUtilService.lazyLoadXmlFile(session.getId(), idDocumento, true);
        ObjetoDocumentoInside docInside = insideUtilService.validateDocumentImport(data);
        retorno.put("idDocumento", idDocumento);
        retorno.put(NOMBRE_NATURAL_EXP,
            getNombreNatural(docInside.getMetadatos().getMetadatosAdicionales()));

        // se cambia la funcion resumen y se necesita un mapa con id del
        // doc y el algoritmo para la nueva
        // getValorHuellaContenidoAlgoritmo
        String funcionResumen = es.mpt.dsic.inside.util.InsideUtils.HUELLA_SHA512_LITERAL_URI;
        Map<String, String> mapaIdAlgoritmo = new HashMap<String, String>();
        mapaIdAlgoritmo.put(idDocumento, funcionResumen);
        String valorHuella =
            utilidadDigestInsideImpl.getValorHuellaContenidoAlgoritmo(docInside, mapaIdAlgoritmo);
        String ordenDocExp = "1";
        String fechaIncorpExp = request.getParameter(FECHA_INCORP_EXP);

        XMLGregorianCalendar fechaIncorpExpXML =
            InsideWSUtils.stringToXmlCalendar(fechaIncorpExp, WebConstants.FORMATO_FECHA_DEFECTO2);

        expedienteEniResultante = IndexActions.createDocument(expedienteEniResultante, path,
            idDocumento, valorHuella, funcionResumen, fechaIncorpExpXML, ordenDocExp);

        retorno = setDataIndex(expedienteEniResultante, retorno);
        ((List<String>) session.getAttribute(DOCS)).add(idDocumento);
      }
    } catch (InsideServiceInternalException e) {
      logger.warn("ExpedientController.createDocumentInside --> Error al asociar Documento Inside: "
          + idDocumento, e);
      msg = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      retorno.put(MENSAJE_USU, msg);
    } catch (Exception e) {
      logger
          .error("ExpedientController.createDocumentInside --> Error al asociar Documento Inside: "
              + idDocumento, e);
      msg = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      retorno.put(MENSAJE_USU, msg);
    }
    return retorno;
  }

  private String getNombreNatural(List<ObjetoInsideMetadatoAdicional> list) {
    String nombreNatural = null;
    if (CollectionUtils.isNotEmpty(list)) {
      for (ObjetoInsideMetadatoAdicional adicional : list) {
        if (MetadatosEEMGDE.METADATO_NOMBRE_NOMBRE_NATURAL.getValue()
            .equals(adicional.getNombre())) {
          nombreNatural = (String) adicional.getValor();
        }
      }
    }
    return nombreNatural;
  }

  private boolean checkIdentifierDocument(HttpSession session, Locale locale,
      Map<String, Object> retorno, String id) throws InSideServiceException {
    MessageObject msg = null;
    boolean checkIdentifierDocument = false;
    try {
      ObjetoDocumentoInside doc = insideService.getDocumento(id);
      ObjetoInsideUsuario usuario =
          (ObjetoInsideUsuario) session.getAttribute(WebConstants.USUARIO_SESSION);
      if (insideService.permisoObjetoUnidad(doc, usuario.getNif(), true)) {
        if (validateIdDocument(session, locale, id, retorno)) {
          checkIdentifierDocument = true;
        }
      } else {
        msg = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, messageSource
            .getMessage(WebConstants.MSG_IMPORTAR_DOC_USUARIO_NO_ASOCIADO_DOC, null, locale));
        retorno.put(MENSAJE_USU, msg);
      }
    } catch (InsideStoreObjectNotFoundException e) {
      logger.debug("Documento a incluir no existe:" + id, e);
      msg = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
          messageSource.getMessage(WebConstants.MSG_IMPORTAR_EXP_EXP_NO_VALIDO, null, locale));
      retorno.put(MENSAJE_USU, msg);
    }
    return checkIdentifierDocument;
  }

  @SuppressWarnings("rawtypes")
  private void includeDocsInSession(HttpSession session, ObjetoExpedienteInside exp) {
    Map<String, String> docs = insideService
        .obtenerDocsExpInside(exp.getIndice().getIndiceContenido().getElementosIndizados(), "/");
    Iterator it = docs.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry pair = (Map.Entry) it.next();
      ((List<String>) session.getAttribute(DOCS)).add((String) pair.getKey());
    }
  }

  public void validateElementsCrearDocumentoIndice(HttpServletRequest request, Locale locale)
      throws InsideValidationDataException {
    if ((StringUtils.isBlank(request.getParameter("fileName"))
        && StringUtils.isBlank(request.getParameter(ID)))
        || StringUtils.isBlank(request.getParameter(ORDEN_DOC_EXP))
        || StringUtils.isBlank(request.getParameter(FECHA_INCORP_EXP))) {
      throw new InsideValidationDataException(
          messageSource.getMessage(WebConstants.MSG_KEY_DOC_DOCENI_MAX_SIZE, null, locale));
    }
  }

  private boolean validateIdDocument(HttpSession session, Locale locale, String id,
      Map<String, Object> retorno) {
    MessageObject mensaje;
    boolean validate = false;
    if (StringUtils.isNotBlank(id)) {
      if (((List<String>) session.getAttribute(DOCS)).contains(id)) {
        Object[] args = new String[1];
        args[0] = id;
        mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
            messageSource.getMessage(WebConstants.MSG_KEY_DOC_DOCENI_ID_REPETIDO, args, locale));
        retorno.put(MENSAJE_USU, mensaje);
      } else {
        validate = true;
      }
    } else {
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
          messageSource.getMessage(WebConstants.MSG_KEY_DOC_DOCENI_NO_VALIDO, null, locale));
      retorno.put(MENSAJE_USU, mensaje);
    }
    return validate;
  }

  private Map<String, Object> setDataIndex(TipoExpediente expedienteEniResultante,
      Map<String, Object> map) throws JsonParseException, IOException, JAXBException,
      ParserConfigurationException, SAXException, InsideConverterException, InSideServiceException,
      ServiceException, DatatypeConfigurationException {
    Map<String, Object> retorno = map;

    // necesito meter los milisegundos a cero porque al descargar eni
    // se hace con milisegundos a cero
    GregorianCalendar c = new GregorianCalendar();
    c.setTime(new Date());
    XMLGregorianCalendar xmlCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
    xmlCal.setMillisecond(0);

    expedienteEniResultante.getIndice().getIndiceContenido().setFechaIndiceElectronico(xmlCal);

    // incluir el id del nodo indiceContenido porque lo usamos a la hora de
    // firmar
    if (expedienteEniResultante.getMetadatosExp() != null)
      expedienteEniResultante.getIndice().getIndiceContenido().setId(
          "EXP_INDICE_CONTENIDO" + expedienteEniResultante.getMetadatosExp().getIdentificador());

    String data = marshaller.marshallDataExpedient(expedienteEniResultante);

    // aniadir los namespaces para que firma y validar valgan para
    // expediente con y sin metadatos adicionales
    data = XMLUtils.incluirNamespacesParaValidarFirma(data);

    retorno.put(EXPEDIENT_ENI_CONVERT, data);

    retorno.put(INDICE, convertIndex(expedienteEniResultante.getIndice().getIndiceContenido()
        .getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada(), true));

    return retorno;
  }

  @RequestMapping(value = "/moverElementoIndice", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> moverElementoIndice(HttpServletRequest request, final ModelMap model,
      HttpSession session, Locale locale) {
    logger.debug("Iniciado ExpedientController.moverElementoIndice");
    Map<String, Object> retorno = new HashMap<String, Object>();
    MessageObject mensaje = null;
    try {
      // debo recoger el id del expediente para rellenarlo y se usara para
      // el id del indiceContenido
      String idExpediente = request.getParameter(ID_EXPEDIENT);

      TipoExpediente expedienteEniResultante =
          getExpedient(request.getParameter(EXPEDIENT_ENI).getBytes(), idExpediente);

      String pathOrigen = request.getParameter("pathOrigen");
      String identificador = request.getParameter(IDENTIFICADOR);
      String pathDestino = request.getParameter("pathDestino");
      int orden = Integer.parseInt(request.getParameter("orden"));

      boolean correctMove = pathOrigen.equals(pathDestino) ? true
          : IndexActions.checkMoveElementNoRepeatInLevel(expedienteEniResultante, identificador,
              pathDestino);

      if (!esPadre(pathOrigen, pathDestino, identificador)) {

        if (correctMove) {
          expedienteEniResultante = IndexActions.moveElement(expedienteEniResultante, pathOrigen,
              identificador, pathDestino, false, orden, session);
        } else {
          mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, messageSource
              .getMessage(WebConstants.MSG_GENERAR_EXP_CARPETAS_MISMO_ID, null, locale));
          retorno.put(MENSAJE_USU, mensaje);
        }

      } else {

        mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, messageSource
            .getMessage(WebConstants.MSG_GENERAR_EXP_CARPETAS_PADRE_DENTRO_HIJO, null, locale));
        retorno.put(MENSAJE_USU, mensaje);

      }

      retorno = setDataIndex(expedienteEniResultante, retorno);

    } catch (Exception e) {
      logger.error("ExpedientController.moverElementoIndice --> Error al generar indice", e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      model.addAttribute(MENSAJE_USU, mensaje);
    }

    logger.debug("Finalizado ExpedientController.moverElementoIndice");
    return retorno;
  }

  /**
   * Comprueba si el path origen es padre del path destino al mover carpetas del indice del
   * expediente
   */
  private boolean esPadre(String pathOrigen, String pathDestino, String identificador) {

    String splitOrigenAux = pathOrigen + identificador;

    String[] splitPathOrigen = splitOrigenAux.split("/");
    String[] splitPathDestino = pathDestino.split("/");

    boolean esPadre = false;
    if (splitPathOrigen.length < splitPathDestino.length) {
      esPadre = true;
      for (int i = 0; i < splitPathOrigen.length; i++) {
        if (!splitPathOrigen[i].equals(splitPathDestino[i])) {
          esPadre = false;
        }
      }
    }

    return esPadre;
  }

  @RequestMapping(value = "/borrarElementoIndice", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> borrarElementoIndice(HttpServletRequest request, final ModelMap model,
      HttpSession session) {
    logger.debug("Iniciado ExpedientController.borrarElementoIndice");
    Map<String, Object> retorno = new HashMap<String, Object>();
    MessageObject mensaje = null;
    TipoExpediente expedienteEniResultante = null;
    String pathOrigen = request.getParameter("pathOrigen");
    String identificador = request.getParameter(IDENTIFICADOR);

    try {
      // debo recoger el id del expediente para rellenarlo y se usara para
      // el id del indiceContenido
      String idExpediente = request.getParameter(ID_EXPEDIENT);

      expedienteEniResultante =
          getExpedient(request.getParameter(EXPEDIENT_ENI).getBytes(), idExpediente);
      expedienteEniResultante = IndexActions.moveElement(expedienteEniResultante, pathOrigen,
          identificador, null, true, null, session);

      retorno = setDataIndex(expedienteEniResultante, retorno);
    } catch (Exception e) {
      logger.error(
          "ExpedientController.borrarElementoIndice --> Exp: " + expedienteEniResultante != null
              ? expedienteEniResultante.getMetadatosExp().getIdentificador()
              : "" + ". identificador: " + identificador + ". pathOrigen: ",
          e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      model.addAttribute(MENSAJE_USU, mensaje);
    }

    logger.debug("Finalizado ExpedientController.borrarElementoIndice");
    return retorno;
  }

  @RequestMapping(value = "/renombrarElementoIndice", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> renombrarElementoIndice(HttpServletRequest request,
      final ModelMap model, Locale locale) {
    logger.debug("Iniciado ExpedientController.renombrarElementoIndice");
    Map<String, Object> retorno = new HashMap<String, Object>();
    MessageObject mensaje = null;
    try {
      // debo recoger el id del expediente para rellenarlo y se usara para
      // el id del indiceContenido
      String idExpediente = request.getParameter(ID_EXPEDIENT);

      TipoExpediente expedienteEniResultante =
          getExpedient(request.getParameter(EXPEDIENT_ENI).getBytes(), idExpediente);

      boolean correctRename = IndexActions.checkNewNameNoRepeatInLevel(expedienteEniResultante,
          request.getParameter("path"), request.getParameter("actualName"),
          request.getParameter("newName"));

      if (correctRename) {
        expedienteEniResultante =
            IndexActions.renameElement(expedienteEniResultante, request.getParameter("path"),
                request.getParameter("actualName"), request.getParameter("newName"));
      } else {
        mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
            messageSource.getMessage(WebConstants.MSG_GENERAR_EXP_CARPETAS_MISMO_ID, null, locale));
        retorno.put(MENSAJE_USU, mensaje);
      }

      retorno = setDataIndex(expedienteEniResultante, retorno);

    } catch (Exception e) {
      logger.error("ExpedientController.renombrarElementoIndice --> Error al generar indice", e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      model.addAttribute(MENSAJE_USU, mensaje);
    }

    logger.debug("Finalizado ExpedientController.renombrarElementoIndice");
    return retorno;
  }

  @RequestMapping(value = "/actualizarIndice", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> actualizarIndice(HttpServletRequest request, final ModelMap model) {
    logger.debug("Iniciado ExpedientController.actualizarIndice");
    Map<String, Object> retorno = new HashMap<String, Object>();
    MessageObject mensaje = null;
    try {
      // debo recoger el id del expediente para rellenarlo y se usara para
      // el id del indiceContenido
      String idExpediente = request.getParameter(ID_EXPEDIENT);

      TipoExpediente expedienteEniResultante =
          getExpedient(request.getParameter(EXPEDIENT_ENI).getBytes(), idExpediente);

      retorno = setDataIndex(expedienteEniResultante, retorno);

    } catch (Exception e) {
      logger.error("ExpedientController.crearCarpetaIndice --> Error al generar indice", e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      model.addAttribute(MENSAJE_USU, mensaje);
    }

    logger.debug("Finalizado ExpedientController.crearCarpetaIndice");
    return retorno;
  }

  private TipoExpediente getExpedient(byte[] data, String idExpedienteInput)
      throws InsideServiceInternalException {
    TipoExpediente retorno = null;
    try {
      if (data != null && data.length > 0) {
        retorno = marshaller.unmarshallDataExpedient(data);
        if (retorno.getMetadatosExp() != null && StringUtils.isNotBlank(idExpedienteInput))
          retorno.getMetadatosExp().setIdentificador(idExpedienteInput);
      } else {
        retorno = new TipoExpediente();
        retorno.setIndice(new TipoIndice());
        retorno.getIndice().setIndiceContenido(new TipoIndiceContenido());
        retorno.setMetadatosExp(new TipoMetadatos());
        retorno.getMetadatosExp().setIdentificador(idExpedienteInput);
        TipoMetadatos.Estado estado = new TipoMetadatos.Estado();
        estado.setValue(EnumeracionEstados.fromValue("E01"));
        retorno.getMetadatosExp().setEstado(estado);
        InSideDateAdapter adapter = new InSideDateAdapter();
        String fechaApertura;
        fechaApertura = adapter.marshalChrome(new Date());
        retorno.getMetadatosExp().setFechaAperturaExpediente(
            InsideWSUtils.stringToXmlCalendar(fechaApertura, WebConstants.FORMATO_FECHA_DEFECTO));
      }
    } catch (Exception e) {
      logger.debug("Error getExpedient. ", e);
      throw new InsideServiceInternalException("Error getExpedient. ", e);
    }
    return retorno;
  }

  private void validateData(TipoExpediente tipoExpediente, Locale locale)
      throws InsideValidationDataException {
    logger.debug("Inicio ExpedientController.validateData");

    String error = null;

    if (CollectionUtils.isEmpty(tipoExpediente.getMetadatosExp().getOrgano())) {
      error = "No ha incluido un órgano en la lista";
    }

    if (StringUtils.isBlank(tipoExpediente.getMetadatosExp().getIdentificador())) {
      if (error == null) {
        error = "No ha incluido un identificador";
      } else {
        error += ", no ha incluido identificador";
      }
    }

    if (StringUtils.isBlank(tipoExpediente.getMetadatosExp().getClasificacion())) {
      if (error == null) {
        error = "No ha incluido una clasificación";
      } else {
        error += ", ni una clasificación";
      }
    }

    if (!StringUtils.isEmpty(error)) {
      Object[] args = new String[1];
      args[0] = error;
      throw new InsideValidationDataException(messageSource
          .getMessage(WebConstants.KEY_ERROR_DATOS_NO_VALIDOS_PARAMETERS, args, locale));
    }
  }

  private List<Object> convertIndex(List<Object> data, boolean initializeDate) {
    List<Object> retorno = new ArrayList<Object>();
    if (data != null) {
      for (Object element : data) {
        if (element instanceof TipoDocumentoIndizado) {
          if (initializeDate) {
            ((TipoDocumentoIndizado) element).setFechaIncorporacionExpediente(null);
          }
          includeNombreNaturalDocumento((TipoDocumentoIndizado) element);
          retorno.add(element);
        } else if (element instanceof TipoCarpetaIndizada) {
          List<Object> dataConvert = convertIndex(((TipoCarpetaIndizada) element)
              .getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada(), initializeDate);
          ((TipoCarpetaIndizada) element)
              .getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada().clear();
          ((TipoCarpetaIndizada) element)
              .getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada().addAll(dataConvert);
          includeNombreNaturalSubExp((TipoCarpetaIndizada) element);
          retorno.add(element);
        } else if (element instanceof TipoIndiceContenido) {
          List<Object> dataConvert = convertIndex(((TipoIndiceContenido) element)
              .getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada(), initializeDate);
          ((TipoIndiceContenido) element)
              .getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada().clear();
          ((TipoIndiceContenido) element)
              .getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada().addAll(dataConvert);
          retorno.add(element);
        }
      }
    }
    return retorno;
  }

  private void includeNombreNaturalSubExp(TipoCarpetaIndizada element) {
    ObjetoExpedienteInside expInside;
    if (element != null && element.getIdentificadorCarpeta() != null) {
      element.setId(element.getIdentificadorCarpeta());
      if (element.getIdentificadorCarpeta().contains("_EXP_")) {
        expInside = getExpedienteInside(element.getIdentificadorCarpeta());
        if (expInside != null) {
          String natural = getNombreNatural(expInside.getMetadatos().getMetadatosAdicionales());
          natural = natural != null ? natural
              : element.getIdentificadorCarpeta().substring(13,
                  element.getIdentificadorCarpeta().length());
          element.setId(natural);
        }
      }
    }
  }

  private ObjetoExpedienteInside getExpedienteInside(String idExpediente) {
    ObjetoExpedienteInside expInside = null;
    try {
      expInside = insideService.getExpediente(idExpediente, false);
    } catch (InsideStoreObjectNotFoundException e) {
      logger.debug("Error al recuperar información del Expediente en Inside", e);
    } catch (InSideServiceException e) {
      logger.debug("Error al recuperar información del Expediente en Inside", e);
    }
    return expInside;
  }

  private void includeNombreNaturalDocumento(TipoDocumentoIndizado element) {
    ObjetoDocumentoInside docInside;
    if (element != null && element.getIdentificadorDocumento() != null) {
      element.setId(element.getIdentificadorDocumento());
      docInside = getDocumentoInside(element.getIdentificadorDocumento());
      if (docInside != null) {
        String natural = getNombreNatural(docInside.getMetadatos().getMetadatosAdicionales());
        natural = natural != null ? natural
            : element.getIdentificadorDocumento().substring(13,
                element.getIdentificadorDocumento().length());
        element.setId(natural);
      }
    }
  }

  private ObjetoDocumentoInside getDocumentoInside(String idDocumento) {
    ObjetoDocumentoInside docInside = null;
    try {
      docInside = insideService.getDocumento(idDocumento);
    } catch (InsideStoreObjectNotFoundException e) {
      logger.debug("Error al recuperar información del Documento en Inside", e);
    } catch (InSideServiceException e) {
      logger.debug("Error al recuperar información del Documento en Inside", e);
    }
    return docInside;
  }

  public String validarDocumentoEni(String session, String documento, String format)
      throws IOException, InSideServiceTemporalDataException {
    String retorno = null;
    byte[] data = temporalDataBusinessService.getFile(session, documento);

    TipoDocumento tdoc;

    logger.debug("Inicio validarDocumentoEni");

    if ("text/xml".equals(InsideUtils.getMimeByNombreFormato(format))) {
      try {
        tdoc = convertirDocAEni(data);
        retorno = tdoc.getId();
        if (StringUtils.isEmpty(retorno)) {
          retorno = tdoc.getMetadatos().getIdentificador();
        }

        String docMarshall = marshaller.marshallDataDocument(tdoc,
            "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e", "documento",
            "enidoc");

        temporalDataBusinessService.escribir(docMarshall.getBytes(), session,
            tdoc.getMetadatos().getIdentificador() + WebConstants.FORMAT_XML, true);

      } catch (Exception e1) {
        logger.error("Error al generar documento mime no valido", e1);
      }
    }
    return retorno;
  }

  @RequestMapping(value = "/expedientesAlmacenados", method = RequestMethod.GET)
  public ModelAndView expedientesAlmacenados(HttpSession session,
      @RequestParam(value = "textoMensajeUsuario", required = false) String textoMensajeUsuario,
      @RequestParam(value = "nivelMensajeUsuario", required = false) String nivelMensajeUsuario) {
    ModelAndView retorno = new ModelAndView(VIEW_EXPEDIENTS_STORED);
    MessageObject mensaje;
    if (StringUtils.isNotEmpty(textoMensajeUsuario)
        && StringUtils.isNotEmpty(nivelMensajeUsuario)) {
      retorno.addObject(MENSAJE_USU,
          new MessageObject(Integer.parseInt(nivelMensajeUsuario), textoMensajeUsuario));
    }
    try {
      ObjetoInsideUsuario usuario =
          (ObjetoInsideUsuario) session.getAttribute(WebConstants.USUARIO_SESSION);
      List<ObjetoInsideExpedienteUnidad> datos = insideService.getExpedientesUnidad(usuario, true);
      retorno.addObject("expresionRegular", metadatosIdentifierExpValidation.getExpresionRegular());
      retorno.addObject(EXPEDIENTES, datos);
      retorno.addObject(EXPEDIENTES_ALMACENADOS, true);
      retorno.addObject(SHOW_FIRMA_SERVER, insideService.checkSignatureServerByUser(usuario));

      cleanBreadCrumbExpedients(session);
    } catch (InSideServiceException e) {
      logger.error(
          "ExpedientController.expedientesAlmacenados --> Error al obtener expedientes del usuario",
          e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      retorno.addObject(MENSAJE_USU, mensaje);
      retorno.addObject(EXPEDIENTES, new ArrayList<ObjetoInsideExpedienteUnidad>());
      retorno.addObject(EXPEDIENTES_ALMACENADOS, false);
    }
    return retorno;
  }

  @RequestMapping(value = "/cargarVistasExpediente", method = RequestMethod.POST)
  public ModelAndView cargarVistasExpediente(HttpSession session,
      @RequestParam(IDENTIFICADOR) String identificador, @RequestParam("version") String version) {
    ModelAndView retorno = new ModelAndView(VIEW_EXPEDIENTS_STORED);
    MessageObject mensaje = null;
    try {
      getViewsExpedient(retorno, identificador);
      saveBreadCrumbExpedients(identificador, version, session);
    } catch (InSideServiceException e) {
      logger.error(
          "ExpedientController.cargarVistasExpediente --> Error al obtener las vistas del expediente",
          e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      retorno.addObject(MENSAJE_USU, mensaje);
    }
    return retorno;
  }

  @RequestMapping(value = "/volverExpedienteAsociado", method = RequestMethod.GET)
  public ModelAndView volverExpedienteAsociado(Locale locale, HttpSession session,
      HttpServletRequest request) {
    ModelAndView retorno = null;
    MessageObject mensaje = null;
    try {
      Entry lastElement = recoverBreadCrumbExpedient(session, true);

      if (lastElement != null) {
        ObjetoExpedienteInside exp =
            getExpedienteInside((String) lastElement.getKey(), (String) lastElement.getValue());

        retorno = new ModelAndView(VIEW_EDIT_EXPEDIENT);
        retorno.addObject("showAssociates", true);

        loadData(retorno, exp, locale, session);

        initDocument(retorno, locale, request);

        retorno.addObject(SHOW_MESSAGE, false);

      } else {
        retorno = expedientesAlmacenados(session, null, null);
      }
    } catch (InSideServiceException e) {
      logger.error(
          "ExpedientController.volverExpedienteAsociado --> Error al volver al expediente asociado",
          e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      retorno.addObject(MENSAJE_USU, mensaje);
    }
    return retorno;
  }

  @RequestMapping(value = "/volverVistasAsociadas", method = RequestMethod.GET)
  public ModelAndView volverVistasAsociadas(HttpSession session) {
    ModelAndView retorno = new ModelAndView(VIEW_EXPEDIENTS_STORED);
    MessageObject mensaje = null;
    try {
      Entry lastElement = recoverBreadCrumbExpedient(session, false);

      if (lastElement != null)
        getViewsExpedient(retorno, (String) lastElement.getKey());
      else
        retorno = expedientesAlmacenados(session, null, null);
    } catch (InSideServiceException e) {
      logger.error(
          "ExpedientController.volverExpedienteAsociado --> Error al obtener expedientes del usuario",
          e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      retorno.addObject(MENSAJE_USU, mensaje);
    }
    return retorno;
  }

  public void getViewsExpedient(ModelAndView retorno, String identificador)
      throws InSideServiceException {
    List<ObjetoInsideExpedienteUnidad> datos = insideService.getViewsExpedient(identificador);
    retorno.addObject(EXPEDIENTES, datos);
    retorno.addObject(EXPEDIENTES_ALMACENADOS, false);
  }

  @SuppressWarnings("unchecked")
  private Object checkBreadCrumbExpedients(HttpSession session) {
    LinkedHashMap<String, String> listExpedients =
        (LinkedHashMap<String, String>) session.getAttribute(RASTRO_EXPEDIENTE);
    boolean checkBreadCrumb = false;

    if (listExpedients != null && !listExpedients.isEmpty())
      checkBreadCrumb = true;

    return checkBreadCrumb;
  }

  private void cleanBreadCrumbExpedients(HttpSession session) {
    session.removeAttribute(RASTRO_EXPEDIENTE);
  }

  @SuppressWarnings("unchecked")
  public Entry<String, String> recoverBreadCrumbExpedient(HttpSession session,
      boolean deleteBreadCrumbExpedient) {
    LinkedHashMap<String, String> listExpedients =
        (LinkedHashMap<String, String>) session.getAttribute(RASTRO_EXPEDIENTE);
    Entry<String, String> expedientAssociated = null;
    if (listExpedients != null && !listExpedients.isEmpty()) {

      Iterator<Entry<String, String>> iterador = listExpedients.entrySet().iterator();
      while (iterador.hasNext()) {
        expedientAssociated = iterador.next();
      }

      if (deleteBreadCrumbExpedient && expedientAssociated != null)
        listExpedients.remove(expedientAssociated.getKey());

      session.setAttribute(RASTRO_EXPEDIENTE, listExpedients);
    }
    return expedientAssociated;
  }

  private void saveBreadCrumbExpedients(String identificador, String version, HttpSession session) {
    LinkedHashMap<String, String> listExpedients =
        (LinkedHashMap<String, String>) session.getAttribute(RASTRO_EXPEDIENTE);
    if (listExpedients != null) {
      listExpedients.put(identificador, version);
    } else {
      listExpedients = new LinkedHashMap<String, String>();
      listExpedients.put(identificador, version);
    }
    session.setAttribute(RASTRO_EXPEDIENTE, listExpedients);
  }

  @RequestMapping(value = "/visualizarContenido", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> visualizarContenido(Locale locale, HttpSession session,
      @RequestParam("idDocumento") String idDocumento, HttpServletResponse response)
      throws InsideValidationDataException {
    Map<String, Object> retorno = new HashMap<String, Object>();
    MessageObject mensaje = null;
    try {
      logger.debug("documento eni valido");

      ObjetoDocumentoInside documento;
      try {
        documento = insideService.getDocumento(idDocumento);
      } catch (InsideStoreObjectNotFoundException e) {
        logger.debug("No es posible previsualizar, objeto no encontrado", e);
        byte[] data = temporalDataBusinessService.getFile(session.getId(),
            idDocumento + WebConstants.FORMAT_XML);
        TipoDocumento tDoc = convertirDocAEni(data);
        documento = InsideConverterDocumento.documentoEniAndMetadatosToDocumentoInside(tDoc, null);
      }
      TipoDocumento tDoc = InsideConverterDocumento.documentoInsideToEni(documento, null);
      retorno.put(IDENTIFICADOR, tDoc.getId());
      try {
        retorno.put("contenidoVisualizar",
            serviceVisualizar.visualizarContenidoOriginal(documento));
      } catch (InsideServiceVisualizacionException e) {
        if (StringUtils.isNotEmpty(documento.getContenido().getReferencia())
            && (CollectionUtils.isEmpty(documento.getFirmas()) || FirmaInsideTipoFirmaEnum.TF_01
                .value().equals(documento.getFirmas().get(0).getTipoFirma().value()))) {
          // pdf default
          retorno.put("contenidoVisualizar", pdfDefault);

        }
      }
      retorno.put("organos", tDoc.getMetadatos().getOrgano());
      retorno.put("desEstado",
          messageSource.getMessage(
              "estadoElaboracion."
                  + tDoc.getMetadatos().getEstadoElaboracion().getValorEstadoElaboracion().value(),
              null, locale));

      String docMarshall = marshaller.marshallDataDocument(tDoc,
          "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e", "documento",
          "enidoc");
      retorno.put("documentoEni", Base64.encodeBase64String(docMarshall.getBytes()));
    } catch (NoSuchMessageException e) {
      logger.error(
          "ExpedientController.informacionVisualizarContenido --> Error al obtener información de visualizar",
          e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      retorno.put(MENSAJE_USU, mensaje);
    } catch (Exception e) {
      logger.error(
          "ExpedientController.informacionVisualizarContenido --> Error al obtener información de visualizar",
          e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      retorno.put(MENSAJE_USU, mensaje);
    }

    return retorno;
  }

  @SuppressWarnings("unchecked")
  private TipoDocumento convertirDocAEni(byte[] data)
      throws ParserConfigurationException, SAXException, IOException, JAXBException,
      TransformerFactoryConfigurationError, TransformerException {
    TipoDocumento retorno = null;
    try {

      logger.debug("mime valido, parseando a TipoDocumento");

      JAXBContext jc = JAXBContext.newInstance(TipoDocumento.class.getPackage().getName());
      Unmarshaller unmarshaller = jc.createUnmarshaller();
      retorno =
          ((JAXBElement<TipoDocumento>) unmarshaller.unmarshal(new ByteArrayInputStream(data)))
              .getValue();

    } catch (JAXBException e1) { // NOSONAR
      Node nodoAdicionales = XMLUtils.getNode(data, "ns7:metadatosAdicionales");
      if (nodoAdicionales != null) {
        byte[] nodoEniString = XMLUtils.documentoAdicionalToEni(data);

        JAXBContext jc = JAXBContext.newInstance(TipoDocumento.class.getPackage().getName());
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        retorno = ((JAXBElement<TipoDocumento>) unmarshaller
            .unmarshal(new ByteArrayInputStream(nodoEniString))).getValue();

      }
    }
    return retorno;
  }

  @RequestMapping(value = "/importarExpediente", method = RequestMethod.GET)
  public ModelAndView importarExpediente() {
    return new ModelAndView("expediente/importarExpediente");
  }

  @RequestMapping(value = "/importarExpediente/importExpedient", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> importExpedientPost(Locale locale, HttpServletRequest request,
      HttpSession session) {
    Map<String, Object> retorno = new HashMap<String, Object>();
    MessageObject mensaje = null;
    ObjetoExpedienteInside objExp;
    try {
      ObjetoInsideUsuario usuario =
          (ObjetoInsideUsuario) request.getSession().getAttribute(WebConstants.USUARIO_SESSION);
      objExp = getExpedientRequest(locale, request, session, retorno, ID_EXPEDIENT);

      if (!insideUtilService.esLongitudIdentificadorValido(objExp.getIdentificador())) {
        throw new InsideWSException(InsideWsErrors.IDENTIFICADOR_DOCUMENTO_LONGITUD, null, "");
      }

      List<ObjetoDocumentoInside> listDocuments =
          getListNewDocumentsRequest(request, session, retorno, LIST_IDS_DOCUMENTO);

      if (checKCorrecpondenciaExpDocument(locale, session, retorno, objExp, LIST_IDS_DOCUMENTO,
          getListIdDocuments(listDocuments))) {
        retorno = createExpedientAndDocument(usuario, objExp, listDocuments);
      }

      if (!retorno.containsKey(MENSAJE_USU))
        getUserMessage(locale, retorno, WebConstants.MSG_IMPORTAR_EXP_OK);
    } catch (InsideWSException e) {
      logger.error("ExpedientController.importExpedient --> Error al importar expediente", e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
          messageSource.getMessage(WebConstants.MSG_IMPORTAR_EXP_ID_NO_VALIDO, null, locale));
      retorno.put(MENSAJE_USU, mensaje);
    } catch (Exception e) {
      if (!retorno.containsKey(MENSAJE_USU)) {
        logger.error("ExpedientController.importExpedient --> Error al importar expediente", e);
        mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
        retorno.put(MENSAJE_USU, mensaje);
      }
    }
    return retorno;
  }

  private List<String> getListIdDocuments(List<ObjetoDocumentoInside> listDocuments) {
    List<String> listIdDocs = new ArrayList<String>();
    for (ObjetoDocumentoInside docInside : listDocuments) {
      listIdDocs.add(docInside.getIdentificador());
    }
    return listIdDocs;
  }

  private boolean esUnExpediente(byte[] data) {
    try {
      TipoExpedienteInsideConMAdicionales texpma =
          marshaller.unmarshallDataExpedientAditional(data);
      if (texpma != null && texpma.getExpediente() != null) {
        return true;
      }
    } catch (Exception e) {
      logger.warn("No es un expediente Eni");
      return false;
    }
    return false;
  }

  @RequestMapping(value = "/importarExpediente/importarExpedienteFormatoSIP",
      method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> importExpedienteFormatoSIPPost(Locale locale,
      HttpServletRequest request, HttpSession session) {
    Map<String, Object> retorno = new HashMap<String, Object>();
    MessageObject mensaje = null;
    try {
      ObjetoInsideUsuario usuario =
          (ObjetoInsideUsuario) request.getSession().getAttribute(WebConstants.USUARIO_SESSION);
      ObjetoExpedienteInside objetoExpedienteInside = null;
      List<ObjetoDocumentoInside> listaObjetoDocumentoInside =
          new ArrayList<ObjetoDocumentoInside>();
      List<String> listaIdsDocumentoInside = new ArrayList<String>();
      Map<String, byte[]> ficheros =
          this.insideUtilService.unzip(session.getId(), request.getParameter(ID_EXPEDIENT));
      for (Map.Entry<String, byte[]> entry : ficheros.entrySet()) {
        if (esUnExpediente(entry.getValue())) {
          objetoExpedienteInside = insideUtilService.validateExpedientImport(entry.getValue());
          if (!insideUtilService
              .esLongitudIdentificadorValido(objetoExpedienteInside.getIdentificador())) {
            throw new InsideWSException(InsideWsErrors.IDENTIFICADOR_DOCUMENTO_LONGITUD, null, "");
          }
        } else {
          ObjetoDocumentoInside objetoDocumentoInside =
              insideUtilService.validateDocumentImport(entry.getValue());
          listaObjetoDocumentoInside.add(objetoDocumentoInside);
          listaIdsDocumentoInside.add(entry.getKey());
        }
      }
      // Validamos la correspondencia de expediente y documentos
      Collection<String> resultado = insideService.correspondencia(objetoExpedienteInside,
          cleanExtension(listaIdsDocumentoInside));
      if (resultado != null) {
        String[] aditonal = new String[1];
        aditonal[0] =
            org.springframework.util.StringUtils.collectionToCommaDelimitedString(resultado);
        mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, messageSource
            .getMessage(WebConstants.MSG_IMPORTAR_EXP_CORRESP_NO_VALIDA, aditonal, locale));
        retorno.put(MENSAJE_USU, mensaje);
      } else {
        retorno =
            createExpedientAndDocument(usuario, objetoExpedienteInside, listaObjetoDocumentoInside);
      }

      if (!retorno.containsKey(MENSAJE_USU))
        getUserMessage(locale, retorno, WebConstants.MSG_IMPORTAR_EXP_OK);
    } catch (InsideWSException e) {
      logger.error(
          "ExpedientController.importarExpedienteFormatoSIP --> Error al importar expediente", e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
          messageSource.getMessage(WebConstants.MSG_IMPORTAR_EXP_ID_NO_VALIDO, null, locale));
      retorno.put(MENSAJE_USU, mensaje);
    } catch (InsideServiceInternalException e) {
      logger.warn(
          "ExpedientController.importarExpedienteFormatoSIP --> Error al importar expediente", e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      retorno.put(MENSAJE_USU, mensaje);
    } catch (Exception e) {
      if (!retorno.containsKey(MENSAJE_USU)) {
        logger.error(
            "ExpedientController.importarExpedienteFormatoSIP --> Error al importar expediente", e);
        mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
        retorno.put(MENSAJE_USU, mensaje);
      }
    }
    return retorno;
  }

  @RequestMapping(value = "/editarExpediente/saveExpedient", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> saveExpedientPost(
      @ModelAttribute TipoExpedienteInsideConMAdicionales tipoExpedienteMA, Locale locale,
      HttpServletRequest request, HttpSession session,
      @RequestParam("contenidoExp") String contenidoExp) {
    Map<String, Object> retorno = new HashMap<String, Object>();

    try {
      ObjetoInsideUsuario usuario =
          (ObjetoInsideUsuario) request.getSession().getAttribute(WebConstants.USUARIO_SESSION);
      ObjetoExpedienteInside objExp =
          generateExpedient(tipoExpedienteMA, locale, request, contenidoExp, retorno);

      if (!insideUtilService.esLongitudIdentificadorValido(objExp.getIdentificador())) {
        throw new InsideWSException(InsideWsErrors.IDENTIFICADOR_DOCUMENTO_LONGITUD, null, "");
      }

      if (checKCorrecpondenciaExpDocument(locale, session, retorno, objExp, DOCS, null)) {
        List<ObjetoDocumentoInside> newDocuments =
            getListNewDocumentsRequest(request, session, retorno, DOCS);
        retorno = createExpedientAndDocument(usuario, objExp, newDocuments);
      }

      if (!retorno.containsKey(MENSAJE_USU))
        getUserMessage(locale, retorno, WebConstants.MSG_IMPORTAR_EXP_GUARDAR_OK);
    } catch (InsideWSException e) {
      logger.error("ExpedientController.saveExpedient --> Error al guardar expediente", e);
      MessageObject mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
          messageSource.getMessage(WebConstants.MSG_IMPORTAR_EXP_ID_NO_VALIDO, null, locale));
      retorno.put(MENSAJE_USU, mensaje);
    } catch (InsideServiceInternalException e) {
      logger.warn("ExpedientController.saveExpedient --> Error validaciones al guardar expediente",
          e);
      getErrorMessage(retorno, e);
    } catch (Exception e) {
      logger.error("ExpedientController.saveExpedient --> Error al guardar expediente", e);
      getErrorMessage(retorno, e);
    }
    return retorno;
  }

  public void getErrorMessage(Map<String, Object> retorno, Exception e) {
    MessageObject mensaje;
    retorno.put(ERROR_GUARDAR, true);
    if (!retorno.containsKey(MENSAJE_USU)) {
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      retorno.put(MENSAJE_USU, mensaje);
    }
  }

  @RequestMapping(value = "/editarExpediente/updateExpedient", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> updateExpedientPost(
      @ModelAttribute TipoExpedienteInsideConMAdicionales tipoExpedienteMA, Locale locale,
      HttpServletRequest request, HttpSession session,
      @RequestParam("contenidoExp") String contenidoExp) {
    Map<String, Object> retorno = new HashMap<String, Object>();

    try {
      ObjetoInsideUsuario usuario =
          (ObjetoInsideUsuario) request.getSession().getAttribute(WebConstants.USUARIO_SESSION);
      ObjetoExpedienteInside objExp =
          generateExpedient(tipoExpedienteMA, locale, request, contenidoExp, retorno);

      if (checKCorrecpondenciaExpDocument(locale, session, retorno, objExp, DOCS, null)) {
        List<ObjetoDocumentoInside> newDocuments =
            getListNewDocumentsRequest(request, session, retorno, DOCS);
        retorno = updateExpedientAndDocument(usuario, objExp, newDocuments);
      }

      if (!retorno.containsKey(MENSAJE_USU))
        getUserMessage(locale, retorno, WebConstants.MSG_IMPORTAR_EXP_ACTUALIZAR_OK);
    } catch (InsideServiceInternalException e) {
      logger.warn("ExpedientController.saveExpedient --> Error validaciones al guardar expediente",
          e);
      getErrorMessage(retorno, e);
    } catch (Exception e) {
      logger.error("ExpedientController.saveExpedient --> Error al guardar expediente", e);
      getErrorMessage(retorno, e);
    }
    return retorno;
  }

  private ObjetoExpedienteInside getExpedientRequest(Locale locale, HttpServletRequest request,
      HttpSession session, Map<String, Object> retorno, String element)
      throws InsideServiceInternalException {
    ObjetoExpedienteInside exp = null;
    ObjectMapper mapper = new ObjectMapper();
    byte[] fileExpBase64 = null;
    try {
      if (ID_EXPEDIENT.equals(element)) {
        String idExp = mapper.readValue(request.getParameter(ID_EXPEDIENT), String.class);
        if (StringUtils.isBlank(idExp)) {
          throw new InsideServiceInternalException(
              messageSource.getMessage(WebConstants.MSG_IMPORTAR_EXP_FALTA_EXP, null, locale));
        }
        fileExpBase64 = temporalDataBusinessService.getFile(session.getId(), idExp);
      } else if (DATA_FILE_EXP.equals(element)) {
        String dataExp = mapper.readValue(request.getParameter(DATA_FILE_EXP), String.class);
        fileExpBase64 = dataExp.getBytes();
      }

      exp = insideUtilService.validateExpedientImport(fileExpBase64);
    } catch (Exception e) {
      MessageObject mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      retorno.put(MENSAJE_USU, mensaje);
      throw new InsideServiceInternalException("Error al obtener el expediente", e);
    }
    return exp;
  }

  @SuppressWarnings("unchecked")
  public List<ObjetoDocumentoInside> getListNewDocumentsRequest(HttpServletRequest request,
      HttpSession session, Map<String, Object> retorno, String element)
      throws InSideServiceException {
    List<ObjetoDocumentoInside> documentos = new ArrayList<ObjetoDocumentoInside>();
    List<String> listIdFiles = null;
    ObjectMapper mapper = new ObjectMapper();

    try {
      if (LIST_IDS_DOCUMENTO.equals(element)) {
        listIdFiles = mapper.readValue(request.getParameter(LIST_IDS_DOCUMENTO), List.class);
      } else if (DOCS.equals(element)) {
        listIdFiles = getNewDocumentsSession(session, (List<String>) session.getAttribute(DOCS));
      }

      if (CollectionUtils.isNotEmpty(listIdFiles)) {
        for (String fichero : listIdFiles) {
          byte[] base64Byte = insideUtilService.lazyLoadXmlFile(session.getId(), fichero, false);
          documentos.add(insideUtilService.validateDocumentImport(base64Byte));
        }
      }
    } catch (Exception e) {
      MessageObject mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      retorno.put(MENSAJE_USU, mensaje);
      throw new InsideServiceInternalException("Error al obtener los documentos", e);
    }
    return documentos;
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  public List<String> getNewDocumentsSession(HttpSession session, List<String> fileNameNewDocs) {
    // List<String> fileNameNewDocs = new ArrayList<String>();

    // Comprobaremos que los nuevos documentos aún estan en en la lista de
    // docs de Session
    List<String> retorno = new ArrayList<String>();
    Iterator it = ((HashMap<String, String>) session.getAttribute(NEW_DOCS)).entrySet().iterator();

    while (it.hasNext()) {
      Map.Entry element = (Map.Entry) it.next();
      if (fileNameNewDocs.contains(element.getKey())) {
        retorno.add((String) element.getValue());
      }
    }
    return retorno;
  }

  @SuppressWarnings("unchecked")
  private boolean checKCorrecpondenciaExpDocument(Locale locale, HttpSession session,
      Map<String, Object> retorno, ObjetoExpedienteInside objExp, String element,
      List<String> listIdDocImports) throws IOException {
    MessageObject mensaje;
    List<String> listIdDocs = null;

    if (LIST_IDS_DOCUMENTO.equals(element)) {
      listIdDocs = listIdDocImports;
    } else if (DOCS.equals(element)) {
      listIdDocs = (List<String>) session.getAttribute(DOCS);
    }

    Collection<String> resultado =
        insideService.correspondencia(objExp, cleanExtension(listIdDocs));
    boolean correct = true;
    if (resultado != null) {
      String[] aditonal = new String[1];
      aditonal[0] =
          org.springframework.util.StringUtils.collectionToCommaDelimitedString(resultado);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, messageSource
          .getMessage(WebConstants.MSG_IMPORTAR_EXP_CORRESP_NO_VALIDA, aditonal, locale));
      retorno.put(MENSAJE_USU, mensaje);
      correct = false;
    }
    return correct;
  }

  private List<String> cleanExtension(List<String> listIdDocs) {
    List<String> listIdDocsWithOutExtension = new ArrayList<String>();
    for (String doc : listIdDocs) {
      if (doc.contains(WebConstants.FORMAT_XML))
        listIdDocsWithOutExtension.add(doc.substring(0, doc.indexOf(WebConstants.FORMAT_XML)));
      else
        listIdDocsWithOutExtension.add(doc);
    }
    return listIdDocsWithOutExtension;
  }

  @SuppressWarnings("unchecked")
  public void getUserMessage(Locale locale, Map<String, Object> retorno, String msg) {
    MessageObject mensaje;
    if (retorno.get(DOCS_NO_CREATE) != null) {
      // Documentos que no han sido creados en la generación de expediente
      String[] aditonal = new String[1];
      aditonal[0] = org.springframework.util.StringUtils
          .collectionToCommaDelimitedString((Collection<String>) retorno.get(DOCS_NO_CREATE));
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_INFO,
          messageSource.getMessage(msg + ".infoDocs", aditonal, locale));
      retorno.put(MENSAJE_USU, mensaje);
      retorno.put(ERROR_GUARDAR, true);
    } else {
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_SUCCESS,
          messageSource.getMessage(msg, null, locale));
      retorno.put(MENSAJE_USU, mensaje);
    }
  }

  private Map<String, Object> createExpedientAndDocument(ObjetoInsideUsuario usuario,
      ObjetoExpedienteInside objExp, List<ObjetoDocumentoInside> documentos)
      throws TransformerFactoryConfigurationError, InSideServiceException {
    Map<String, Object> retorno = new HashMap<String, Object>();

    MessageObject mensaje;
    try {
      Map<String, Object> result =
          insideUtilService.crearNuevoExpediente(objExp, documentos, usuario.getNif(), true);
      retorno.put(DOCS_NO_CREATE, result.get(DOCS_NO_CREATE));
    } catch (Exception e) {
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      throw new InsideServiceInternalException(mensaje.getMessage(), e);
    }

    return retorno;
  }

  private Map<String, Object> updateExpedientAndDocument(ObjetoInsideUsuario usuario,
      ObjetoExpedienteInside objExp, List<ObjetoDocumentoInside> documentos)
      throws TransformerFactoryConfigurationError, InSideServiceException {
    Map<String, Object> retorno = new HashMap<String, Object>();

    MessageObject mensaje;
    try {
      Map<String, Object> result =
          insideUtilService.actualizarExpediente(objExp, documentos, usuario.getNif(), true);
      retorno.put(DOCS_NO_CREATE, result.get(DOCS_NO_CREATE));
    } catch (Exception e) {
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      throw new InsideServiceInternalException(mensaje.getMessage(), e);
    }

    return retorno;
  }

  @RequestMapping(value = "/expedientesAlmacenados/getVersionesExpediente",
      method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> getVersionesExpediente(HttpServletRequest request) {
    Map<String, Object> retorno = new HashMap<String, Object>();
    MessageObject mensaje = null;
    String identificadorExp = request.getParameter("expediente");
    try {
      List<ObjetoInsideVersion> versiones =
          insideService.getListaVersionesExpediente(identificadorExp);
      retorno.put("versiones", versiones);

      ObjetoInsideUsuario usuario =
          (ObjetoInsideUsuario) request.getSession().getAttribute(WebConstants.USUARIO_SESSION);
      String codigoYNombre = usuario.getUnidadOrganicaActiva();
      retorno.put("DIR3ParaEnvioJusticia", codigoYNombre);

    } catch (InSideServiceException e) {
      logger.error("ExpedientController.guardarExpediente --> Error al guardar expediente", e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      retorno.put(MENSAJE_USU, mensaje);
    }
    return retorno;
  }

  @RequestMapping(value = "/expedientesAlmacenados/compruebaMimeDocumentosParaMJU",
      method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> compruebaMimeDocumentosParaMJU(Locale locale,
      HttpServletRequest request) {
    Map<String, Object> retorno = new HashMap<String, Object>();
    MessageObject mensaje = null;
    String identificadorExp = request.getParameter("expediente");
    String[] valoresPermitidosMJU =
        new String[] {"PDF", "JPG", "TIFF", "DOC", "XLS", "XLSX", "RTF", "ODT", "ODS"};
    String versionExp = request.getParameter("version");
    try {
      ObjetoExpedienteInside objetoExpediente =
          insideService.getExpediente(identificadorExp, Integer.valueOf(versionExp), false);
      List<ObjetoExpedienteInsideIndiceContenidoElementoIndizado> listaObjetosIndice =
          objetoExpediente.getIndice().getIndiceContenido().getElementosIndizados();
      Map<String, String> mapaDocumentos =
          insideService.obtenerDocsExpInside(listaObjetosIndice, "/");

      for (Map.Entry<String, String> entry : mapaDocumentos.entrySet()) {
        ObjetoDocumentoInside objetoDocumento = insideService.getDocumento(entry.getKey());
        if (!Arrays.asList(valoresPermitidosMJU)
            .contains(objetoDocumento.getContenido().getNombreFormato())) {
          retorno.put("formatosNoValidos", true);
          mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, messageSource
              .getMessage(WebConstants.MSG_FORMATO_DOCUMENTO_NO_PERMITIDO_MJU, null, locale));
          retorno.put(MENSAJE_USU, mensaje);
          return retorno;
        }
      }
      retorno.put("formatosNoValidos", false);

    } catch (InSideServiceException e) {
      logger.error(
          "ExpedientController.compruebaMimeDocumentosParaMJU --> Error al comprobar extensiones documentos",
          e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      retorno.put(MENSAJE_USU, mensaje);
    }
    return retorno;
  }

  @RequestMapping(value = "/expedientesAlmacenados/expedienteGenerarTokenDescarga",
      method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> expedienteGenerarTokenDescarga(Locale locale,
      HttpServletRequest request, HttpSession session) {
    Map<String, Object> retorno = new HashMap<String, Object>();
    MessageObject msg = null;
    try {
      ObjetoInsideUsuario usuario =
          (ObjetoInsideUsuario) session.getAttribute(WebConstants.USUARIO_SESSION);

      String identificadorExp = request.getParameter("expediente");
      ObjetoExpedienteInside expediente = insideService.getExpediente(identificadorExp, true);
      String csv = csvService.generarCSV(expediente);
      String uuid = UUID.randomUUID().toString();

      // recoge los parametros
      String mailEnvioToken = request.getParameter("mailEnvioTokenDesdeModal");

      String dir3DesdeModal = request.getParameter("dir3DesdeModal");

      String asuntoDesdeModal = request.getParameter("asuntoDesdeModal");

      String nifsDesdeModal = request.getParameter("nifsDesdeModal");

      String fechaCaducidadDesdeModal = request.getParameter("fCadDesdeModal");
      try {

        Date fechaCaducidad = null;
        if (StringUtils.isNotEmpty(fechaCaducidadDesdeModal))
          fechaCaducidad = InsideWSUtils.stringToJavaDateToken(fechaCaducidadDesdeModal);
        else {
          fechaCaducidadDesdeModal = "Este Credencial de Acceso no caduca";
        }

        ObjetoExpedienteToken objetoExpedienteToken = new ObjetoExpedienteToken(usuario,
            identificadorExp, csv, uuid, expediente.getVersion().getVersion(), fechaCaducidad,
            dir3DesdeModal, asuntoDesdeModal, nifsDesdeModal, mailEnvioToken);
        insideService.saveToken(objetoExpedienteToken);

        String resultadoEnvioCorreo = null;
        if (mailEnvioToken != null && !"".equals(mailEnvioToken.trim())) {
          try {
            resultadoEnvioCorreo = mailService.sendToken(objetoExpedienteToken, session.getId(),
                request.getRequestURL().toString());

          } catch (Exception e) {
            logger.error(
                "expedienteGenerarTokenDescarga: Error en el envio de correo de Credenciales de Acceso"
                    + e);
          }
        } else {
          resultadoEnvioCorreo = "No se ha informado de mail para enviar.";
        }

        retorno.put("resultadoEnvioCorreo", resultadoEnvioCorreo);
        retorno.put(IDENTIFICADOR, identificadorExp);
        retorno.put("uuid", uuid);
        retorno.put("csv", csv);

        retorno.put("fechaCaducidad", fechaCaducidadDesdeModal);

      } catch (ParseException e) {
        logger.error("Se he producido un error al generar el Credencial de Acceso de descarga:"
            + e.getMessage());
        msg = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, messageSource
            .getMessage(WebConstants.MSG_EXP_ALMACENADOS_GENERATE_TOKEN, null, locale));
        retorno.put(MENSAJE_USU, msg);
      }

    } catch (InsideServiceUtilFirmaException e) {
      logger.error("Se he producido un error al generar el Credencial de Acceso de descarga:" + e);
      msg = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
          messageSource.getMessage(WebConstants.MSG_EXP_ALMACENADOS_GENERATE_TOKEN, null, locale));
      retorno.put(MENSAJE_USU, msg);
    } catch (InSideServiceException e) {
      logger.error("Se he producido un error al generar el Credencial de Acceso de descarga:" + e);
      msg = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
          messageSource.getMessage(WebConstants.MSG_EXP_ALMACENADOS_GENERATE_TOKEN, null, locale));
      retorno.put(MENSAJE_USU, msg);
    }

    return retorno;
  }

  @RequestMapping(value = "/verExpediente", method = RequestMethod.POST)
  public ModelAndView verExpediente(Locale locale, HttpSession session,
      @RequestParam(IDENTIFICADOR) String identificador,
      @RequestParam("navegador") String navegador) {
    this.organos = null;
    logger.debug("Iniciado ExpedientController.verExpediente");
    ModelAndView retorno = new ModelAndView(VIEW_SHOW_EXPEDIENT);
    MessageObject msg = null;

    retorno.addObject(NAVEGADOR, navegador);

    try {
      ObjetoExpedienteInside exp = getExpedienteInside(identificador, null);
      retorno.addObject("showAssociates", true);
      retorno = loadData(retorno, exp, locale, session);

      logger.debug("Finalizado ExpedientController.verExpediente");
    } catch (InSideServiceException e) {
      logger.error("Se he producido un error al obtener el expediente almacenado:" + e);
      msg = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
          messageSource.getMessage(WebConstants.MSG_KEY_ERROR_GENERIC, null, locale));
      retorno.addObject(MENSAJE_USU, msg);
    }
    return retorno;
  }

  @RequestMapping(value = "/editarExpediente", method = RequestMethod.POST)
  public ModelAndView editarExpediente(Locale locale, HttpSession session,
      HttpServletRequest request, @RequestParam(IDENTIFICADOR) String identificador,
      @RequestParam("version") String version, @RequestParam("navegador") String navegador) {
    this.organos = null;
    logger.debug("Iniciado ExpedientController.editarExpediente");
    ModelAndView retorno = new ModelAndView(VIEW_EDIT_EXPEDIENT);
    MessageObject msg = null;

    retorno.addObject(NAVEGADOR, navegador);

    try {
      session.setAttribute(NEW_DOCS, new HashMap<String, String>());
      ObjetoExpedienteInside exp = getExpedienteInside(identificador, version);
      cleanSpecialCaractersInFolders(exp.getIndice().getIndiceContenido().getElementosIndizados());
      retorno = loadData(retorno, exp, locale, session);

      initDocument(retorno, locale, request);

      retorno.addObject(SHOW_MESSAGE, false);

      ObjetoInsideUsuario usuario =
          (ObjetoInsideUsuario) session.getAttribute(WebConstants.USUARIO_SESSION);
      retorno.addObject(SHOW_FIRMA_SERVER, insideService.checkSignatureServerByUser(usuario));

      retorno.addObject("metadatoNombreNatural",
          MetadatosEEMGDE.METADATO_NOMBRE_NOMBRE_NATURAL.getValue());
      retorno.addObject("metadatoFechaFin", MetadatosEEMGDE.METADATO_FECHAS_FECHA_FIN.getValue());

      ///// cargar los documentos / expedientes de la unidad porque el
      ///// autocomplete de js no funciona en internet Explorer
      ///// ////////////////////////////////////////

      String listaDocumentosJSON = convertListToJSONString(request, "documento");
      String listaExpedientesJSON = convertListToJSONString(request, "expediente");

      retorno.addObject("listaDocumentosJSON", listaDocumentosJSON);
      retorno.addObject("listaExpedientesJSON", listaExpedientesJSON);

      ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

      logger.debug("Finalizado ExpedientController.editarExpediente");
    } catch (Exception e) {
      logger.error("Se he producido un error al obtener el expediente almacenado: " + identificador
          + ". " + e);
      String msgError = e.getMessage() != null ? e.getMessage()
          : messageSource.getMessage(WebConstants.MSG_KEY_ERROR_GENERIC, null, locale);
      msg = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, msgError);
      retorno.addObject(MENSAJE_USU, msg);
      retorno.setViewName("redirect:/expedientesAlmacenados?textoMensajeUsuario=" + msg.getMessage()
          + "&nivelMensajeUsuario=" + msg.getLevel());
    }
    return retorno;
  }

  @RequestMapping(value = "/visualizaDescargarResguardoJusticia", method = RequestMethod.POST)
  public ModelAndView visualizaDescargarContenidoDocumento(
      @RequestParam(RESGUARDODOCBASE63) String resguardoDocBase64,
      @RequestParam("codigoEnvioResguardo") String codigoEnvioResguardo,
      HttpServletResponse response, Locale locale, HttpSession session) {
    try {

      OutputStream pr = response.getOutputStream();
      response.setContentType("application/pdf");
      String identificadorCompleto = codigoEnvioResguardo + ".pdf";
      response.addHeader(CONTENT_DISPOSITION, ATTACHMENT_FILENAME + identificadorCompleto);

      byte[] resguardoDecodificado = Base64.decodeBase64(resguardoDocBase64.getBytes());
      pr.write(resguardoDecodificado);
      pr.close();

      return null;
    } catch (Exception e) {
      logger.error("Error en la descarga con Código de Envío: " + codigoEnvioResguardo, e);
      MessageObject mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
          messageSource.getMessage(WebConstants.MSG_KEY_ERROR_GENERIC, null, locale));
      ModelAndView retorno = new ModelAndView(VIEW_EDIT_EXPEDIENT);
      retorno.addObject(MENSAJE_USU, mensaje);
      return retorno;
    }

  }

  @RequestMapping(value = "/expedientesAlmacenados/crearVistaAbiertaExpediente",
      method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> crearVistaAbiertaExpediente(HttpServletRequest request) {
    Map<String, Object> retorno = new HashMap<String, Object>();
    MessageObject msg = null;
    try {
      ObjetoInsideUsuario usuario =
          (ObjetoInsideUsuario) request.getSession().getAttribute(WebConstants.USUARIO_SESSION);
      ObjectMapper mapper = new ObjectMapper();
      String identifier = mapper.readValue(request.getParameter("identifier"), String.class);
      String identifierView =
          mapper.readValue(request.getParameter("identifierView"), String.class);
      boolean firmaServidor =
          mapper.readValue(request.getParameter(SIGNATURE_SERVER), Boolean.class);

      ObjetoExpedienteInside exp = insideService.createOpenViewExpedient(identifier, identifierView,
          firmaServidor, usuario.getNif(), true);

      if (firmaServidor)
        exp = insideService.getExpediente(exp.getIdentificador(), null, false);

      TipoExpediente tExp = InsideConverterExpediente.expedienteInsideToEni(exp, null);
      String data = marshaller.marshallDataExpedient(tExp);
      retorno.put(EXPEDIENTE_ENI, data);

      // solo queremos el indice
      tExp.setMetadatosExp(null);
      String dataIndice = marshaller.marshallDataExpedient(tExp);
      dataIndice = XMLUtils.incluirNamespacesParaValidarFirma(dataIndice);
      retorno.put(EXPEDIENTE_ENI_INDICE, dataIndice);

    } catch (Exception e) {
      logger.error(
          "ExpedientController.crearVistaAbiertaExpediente --> Error al recuperar expediente", e);
      msg = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      retorno.put(MENSAJE_USU, msg);
    }
    return retorno;
  }

  @RequestMapping(value = "/expedientesAlmacenados/crearVistaCerradaExpediente",
      method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> crearVistaCerradaExpediente(HttpServletRequest request) {
    Map<String, Object> retorno = new HashMap<String, Object>();
    MessageObject msg = null;
    try {
      ObjetoInsideUsuario usuario =
          (ObjetoInsideUsuario) request.getSession().getAttribute(WebConstants.USUARIO_SESSION);
      ObjectMapper mapper = new ObjectMapper();
      String identifier = mapper.readValue(request.getParameter("identifier"), String.class);
      String identifierView =
          mapper.readValue(request.getParameter("identifierView"), String.class);
      boolean firmaServidor =
          mapper.readValue(request.getParameter(SIGNATURE_SERVER), Boolean.class);

      ObjetoExpedienteInside exp = insideService.createClosedViewExpedient(identifier,
          identifierView, firmaServidor, usuario.getNif(), true);

      if (firmaServidor)
        exp = insideService.getExpediente(exp.getIdentificador(), null, false);

      TipoExpediente tExp = InsideConverterExpediente.expedienteInsideToEni(exp, null);

      String data = marshaller.marshallDataExpedient(tExp);
      retorno.put(EXPEDIENTE_ENI, data);
      // solo queremos el indice
      tExp.setMetadatosExp(null);
      String dataIndice = marshaller.marshallDataExpedient(tExp);
      dataIndice = XMLUtils.incluirNamespacesParaValidarFirma(dataIndice);
      retorno.put(EXPEDIENTE_ENI_INDICE, dataIndice);

    } catch (Exception e) {
      logger.error(
          "ExpedientController.crearVistaCerradaExpediente --> Error al guardar expediente", e);
      msg = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      retorno.put(MENSAJE_USU, msg);
    }
    return retorno;
  }

  @RequestMapping(value = "/updateIndexExpedient", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> updateIndexExp(Locale locale, HttpServletRequest request)
      throws InSideServiceException {
    Map<String, Object> retorno = new HashMap<String, Object>();
    MessageObject mensaje;
    String identificadorExpediente;

    try {
      ObjetoInsideUsuario usuario =
          (ObjetoInsideUsuario) request.getSession().getAttribute(WebConstants.USUARIO_SESSION);
      String idJsonExpediente = request.getParameter("identificadorExpediente");

      ObjectMapper mapper = new ObjectMapper();
      if (StringUtils.isNotBlank(idJsonExpediente)) {
        identificadorExpediente = mapper.readValue(idJsonExpediente, String.class);

        if (StringUtils.isEmpty(request.getParameter("indexExpB64"))) {
          mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
              messageSource.getMessage(WebConstants.MSG_IMPORTAR_DOC_NO_VALIDO, null, locale));
          retorno.put(MENSAJE_USU, mensaje);
        } else {
          saveOrUpdateIndexExp(request, identificadorExpediente, usuario);

          mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_SUCCESS,
              messageSource.getMessage(WebConstants.MSG_IMPORTAR_DOC_OK, null, locale));
          retorno.put(MENSAJE_USU, mensaje);
        }
      }
    } catch (Exception e) {
      throw new InsideServiceInternalException("Error al actualizar el indice del expediente", e);
    }

    return retorno;
  }

  public void saveOrUpdateIndexExp(HttpServletRequest request, String identificadorExpediente,
      ObjetoInsideUsuario usuario) throws InsideServiceInternalException {
    ObjectMapper mapper = new ObjectMapper();
    ObjetoExpedienteInside expediente;

    try {
      String indexExpB64 = mapper.readValue(request.getParameter("indexExpB64"), String.class);
      byte[] firmaIndice = Base64.decodeBase64(indexExpB64);
      Boolean isNewView = mapper.readValue(request.getParameter("isNewView"), Boolean.class);

      if (isNewView != null && isNewView.booleanValue()) {
        String expedienteEniString =
            mapper.readValue(request.getParameter(EXPEDIENTE_ENI), String.class);
        TipoExpediente expedienteEni =
            marshaller.unmarshallDataExpedient(expedienteEniString.getBytes());
        expediente = InsideConverterExpediente.expedienteEniToInside(expedienteEni);

        expediente.getIndice().setFirmas(InsideConverterFirmas.firmasEniToInside(
            signatureUtils.setSignaturesExp(firmaIndice, expediente.getIdentificador())));

        insideService.altaExpediente(expediente, false, usuario.getNif(), true);
      } else {
        expediente = insideService.getExpediente(identificadorExpediente, false);

        expediente.getIndice().setFirmas(InsideConverterFirmas.firmasEniToInside(
            signatureUtils.setSignaturesExp(firmaIndice, expediente.getIdentificador())));

        insideService.modificaExpedienteInside(expediente, false, usuario.getNif(), true);
      }
    } catch (Exception e) {
      throw new InsideServiceInternalException("Error al actualizar el indice del expediente", e);
    }
  }

  private ModelAndView loadData(ModelAndView model, ObjetoExpedienteInside exp, Locale locale,
      HttpSession session) throws InSideServiceException {

    ModelAndView retorno = model;
    MessageObject msg;
    try {
      init(retorno, locale);

      retorno.addObject("version", exp.getVersion().getVersion());
      retorno.addObject("fechaVersion", exp.getVersion().getFechaVersion());

      // Comprobamos si el expediente tiene vistas asociadas
      retorno.addObject("vistasAsociadas", checkAssociatedViewsExpedient(exp.getIdentificador()));

      retorno.addObject(RASTRO_EXPEDIENTE, checkBreadCrumbExpedients(session));

      // Se instancia el expedienteEni con valores iniciales
      TipoExpedienteInsideConMAdicionales tipoExpedienteMA =
          new TipoExpedienteInsideConMAdicionales();
      tipoExpedienteMA.setExpediente(InsideConverterExpediente.expedienteInsideToEni(exp, null));
      tipoExpedienteMA.setMetadatosAdicionales(InsideConverterMetadatos
          .metadatosAdicionalesInsideToXml(exp.getMetadatos().getMetadatosAdicionales()));

      retorno.addObject("fechaApertura", getFechaApertura(tipoExpedienteMA));

      Map<String, String> docsExp = insideService
          .obtenerDocsExpTipo(tipoExpedienteMA.getExpediente().getIndice().getIndiceContenido()
              .getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada(), "/");
      ArrayList<String> docs = new ArrayList<String>();
      if (CollectionUtils.isNotEmpty(docsExp.entrySet())) {
        Map<String, String> keyDocs = new HashMap<String, String>();
        for (String idDoc : docsExp.keySet()) {
          docs.add(idDoc);
          keyDocs.put(idDoc, idDoc);
        }
        retorno.addObject("keyDocs", keyDocs);
        session.setAttribute(DOCS, docs);
      }

      if (tipoExpedienteMA.getExpediente().getVisualizacionIndice() == null) {
        ObjetoExpedienteInside expedienteInside = insideService
            .obtenerVisualizacionIndiceSiActivo(exp, false, false, null, false, null, true);
        tipoExpedienteMA.getExpediente().setVisualizacionIndice(InsideConverterDocumento.Contenido
            .contenidoInsideToEni(expedienteInside.getVisualizacionIndice(), null));
      }

      retorno.addObject("visualizar", Base64.encodeBase64String(
          tipoExpedienteMA.getExpediente().getVisualizacionIndice().getValorBinario()));

      // Contruimos el contenido del expediente editado
      TipoExpediente expAuxiliar = new TipoExpediente();
      expAuxiliar.setIndice(new TipoIndice());
      expAuxiliar.getIndice()
          .setIndiceContenido(tipoExpedienteMA.getExpediente().getIndice().getIndiceContenido());
      String contenido = marshaller.marshallDataExpedient(expAuxiliar);

      // aniadir los namespaces para que firma y validar valgan para
      // expediente con y sin metadatos adicionales
      contenido = XMLUtils.incluirNamespacesParaValidarFirma(contenido);
      retorno.addObject("contenidoExp", contenido);

      // obtengo el string del expediente al que tengo que sustituir el
      // ns7:expediente por el dataConFirmaSinIdentar por identarlo
      // demasiado
      String data = eniService.getXml(tipoExpedienteMA);

      retorno.addObject(EXPEDIENT_ENI_CONVERT, data);

      retorno.addObject(INDICE,
          convertIndex(tipoExpedienteMA.getExpediente().getIndice().getIndiceContenido()
              .getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada(), false));

      cleanMetadatosEspeciales(retorno, tipoExpedienteMA);
      retorno.addObject("expedienteMetadatos", tipoExpedienteMA);
    } catch (JAXBException e) {
      logger.error(
          "ExpedientController.crearVistaAbiertaExpediente --> Error al guardar expediente", e);
      msg = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      retorno.addObject(MENSAJE_USU, msg);
    } catch (InsideConverterException e) {
      logger.error(
          "ExpedientController.crearVistaAbiertaExpediente --> Error al guardar expediente", e);
      msg = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      retorno.addObject(MENSAJE_USU, msg);
    }
    return retorno;
  }

  private void cleanMetadatosEspeciales(ModelAndView retorno,
      TipoExpedienteInsideConMAdicionales tipoExpedienteMA) {
    List<MetadatoAdicional> finalListAdicionales = new ArrayList<MetadatoAdicional>();
    if (tipoExpedienteMA.getMetadatosAdicionales() != null && CollectionUtils
        .isNotEmpty(tipoExpedienteMA.getMetadatosAdicionales().getMetadatoAdicional())) {
      for (MetadatoAdicional adicional : tipoExpedienteMA.getMetadatosAdicionales()
          .getMetadatoAdicional()) {
        if (MetadatosEEMGDE.METADATO_NOMBRE_NOMBRE_NATURAL.getValue()
            .equals(adicional.getNombre())) {
          retorno.addObject("valorNombreNatural", adicional.getValor());
        } else if (MetadatosEEMGDE.METADATO_FECHAS_FECHA_FIN.getValue()
            .equals(adicional.getNombre())) {
          retorno.addObject("valorFechaFin", adicional.getValor());
        } else {
          finalListAdicionales.add(adicional);
        }
      }
      tipoExpedienteMA.getMetadatosAdicionales().getMetadatoAdicional().clear();
      tipoExpedienteMA.getMetadatosAdicionales().getMetadatoAdicional()
          .addAll(finalListAdicionales);
    }
  }

  private boolean checkAssociatedViewsExpedient(String identificadorExpediente)
      throws InSideServiceException {
    return insideService.checkAssociatedViewsExpedient(identificadorExpediente);
  }

  public ObjetoExpedienteInside getExpedienteInside(String identificador, String version)
      throws InSideServiceException {
    ObjetoExpedienteInside exp;
    if (version != null && !"".equals(version))
      exp = insideService.getExpediente(identificador, Integer.parseInt(version), true);
    else
      exp = insideService.getExpediente(identificador, true);
    return exp;
  }

  private GregorianCalendar getFechaApertura(TipoExpedienteInsideConMAdicionales tipoExpedienteMA) {
    GregorianCalendar fecha = null;
    if (tipoExpedienteMA != null && tipoExpedienteMA.getExpediente() != null
        && tipoExpedienteMA.getExpediente().getMetadatosExp() != null && tipoExpedienteMA
            .getExpediente().getMetadatosExp().getFechaAperturaExpediente() != null) {
      fecha = tipoExpedienteMA.getExpediente().getMetadatosExp().getFechaAperturaExpediente()
          .toGregorianCalendar();
    }
    return fecha;
  }

  @RequestMapping(value = "/busqueda", method = RequestMethod.GET)
  public ModelAndView buscarExpediente() {
    return new ModelAndView("busqueda");
  }

  @RequestMapping(value = "/puestaDisposicion", method = RequestMethod.POST)
  public ModelAndView puestaDisposicionPost(Locale locale, HttpSession session,
      @RequestParam("tokenIdentificador") String identificador,
      @RequestParam("tokenCsv") String csv, @RequestParam("tokenUuid") String uuid,
      @RequestParam("navegador") String navegador) {
    ModelAndView retorno = new ModelAndView("busqueda");
    MessageObject msg = null;

    try {

      // caso nuevo de validacion de token con fechacaducidad y nifs
      if (StringUtils.isNotBlank(identificador) && StringUtils.isNotBlank(csv)
          && StringUtils.isNotBlank(uuid)) {
        String nifConsultaPuestaDisposicion = getUsuarioConsultaPuestaDisposicion(session);

        Map<String, Object> result =
            insideUtilService.checkToken(nifConsultaPuestaDisposicion, identificador, csv, uuid);

        ObjetoExpedienteToken token = (ObjetoExpedienteToken) result.get("token");
        Boolean tokenCaducado = (Boolean) result.get("tokenCaducado");
        Boolean tokenInvalidoNif = (Boolean) result.get("tokenInvalidoNif");

        if (token != null && !tokenCaducado && !tokenInvalidoNif) {
          retorno = new ModelAndView(VIEW_SHOW_EXPEDIENT);
          ObjetoExpedienteInside exp = getExpedienteInside(identificador,
              token.getVersionExpediente() != null ? token.getVersionExpediente() + "" : null);
          retorno = loadData(retorno, exp, locale, session);
          retorno.addObject("navegador", navegador);

        } else {
          String errorToken = (String) result.get("errorToken");
          msg = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
              messageSource.getMessage(errorToken, null, locale));
          retorno.addObject(MENSAJE_USU, msg);
        }
      } else {
        msg = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, messageSource
            .getMessage(WebConstants.MSG_EXP_PUESTADISPOSICION_NO_VALIDO, null, locale));
        retorno.addObject(MENSAJE_USU, msg);
      }
    } catch (NoSuchMessageException e) {
      logger.error("Error en la puestaDisposicion", e);
      msg = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
          messageSource.getMessage(WebConstants.MSG_KEY_ERROR_GENERIC, null, locale));
      retorno.addObject(MENSAJE_USU, msg);
    } catch (InSideServiceException e) {
      logger.error("Error en la puestaDisposicion", e);
      msg = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
          messageSource.getMessage(WebConstants.MSG_KEY_ERROR_GENERIC, null, locale));
      retorno.addObject(MENSAJE_USU, msg);
    }
    return retorno;
  }

  public void auditAccessWithToken(HttpSession session, ObjetoExpedienteToken token) {
    // proceso de auditar los accesos mediante token
    // (auditoriaRemisionNube)
    try {
      ObjetoInsideUsuario user =
          (ObjetoInsideUsuario) session.getAttribute(WebConstants.USUARIO_SESSION);
      inSideAuditoriaService.auditoriaRemisionNube(user, token, session.getId());
    } catch (Exception e) {
      logger.error(
          "Error en la puestaDisposicion: Fallo al auditar el acceso en auditoriaRemisionNube", e);
    }
  }

  @RequestMapping(value = "/borrarExpediente", method = RequestMethod.POST)
  public ModelAndView borrarExpedientePost(HttpSession session,
      @RequestParam(IDENTIFICADOR) String identificador, Locale locale) {
    ModelAndView retorno = null;
    MessageObject mensaje = null;
    try {
      insideService.deleteExpedient(identificador);
      retorno = expedientesAlmacenados(session, null, null);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_SUCCESS,
          messageSource.getMessage(WebConstants.MSG_BAJA_EXP_OK, null, locale));
      retorno.addObject(MENSAJE_USU, mensaje);
    } catch (InsideStoreObjectVinculatedException e) {
      retorno = expedientesAlmacenados(session, null, null);
      logger.error(
          "ExpedienteController.borrarExpedientePost --> Error al borrar los expediente del usuario -"
              + identificador,
          e);
      Object[] args = new String[1];
      args[0] = e.getMessage();
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
          messageSource.getMessage(WebConstants.MSG_BAJA_DOC_VINCULADO, args, locale));
      retorno.addObject(MENSAJE_USU, mensaje);
    } catch (InSideServiceException e) {
      retorno = expedientesAlmacenados(session, null, null);
      logger.error(
          "ExpedienteController.borrarExpedientePost --> Error al borrar los expediente del usuario -"
              + identificador,
          e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      retorno.addObject(MENSAJE_USU, mensaje);
    }
    return retorno;
  }

  private void initDocument(ModelAndView retorno, Locale locale, HttpServletRequest request) {
    // Obtener fecha de captura
    MessageObject mensaje = null;
    String fechaCaptura = "";
    String fechaModal = "";
    try {
      InSideDateAdapter adapter = new InSideDateAdapter();
      fechaCaptura = adapter.marshalChrome(new Date());

      // fecha para la modal y chrome
      fechaModal = adapter.marshalChrome(new Date());
    } catch (Exception e) {
      logger.error(
          "DocumentController.generarDocumento --> Error al convertir la fecha captura documento",
          e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
          messageSource.getMessage(WebConstants.MSG_KEY_EXPED_ERROR_FECHA_INICIO, null, locale));
      retorno.addObject(MENSAJE_USU, mensaje);
    }

    retorno.addObject("fechaModal", fechaModal);
    retorno.addObject("fechaCaptura", fechaCaptura);
    retorno.addObject("estadosElaboracion",
        ComboUtils.getEstadosElaboracion(messageSource, locale));
    retorno.addObject("tiposDocumentales", ComboUtils.getTiposDocumentales(messageSource, locale));
    retorno.addObject("origen", ComboUtils.getOrigen(messageSource, locale));
    retorno.addObject("tiposCertificado", ComboUtils.getTiposCertificados(messageSource, locale));

    TipoDocumentoConversionInside documentoEni = new TipoDocumentoConversionInside();
    TipoDocumentoConversionInside.MetadatosEni metadatos =
        new TipoDocumentoConversionInside.MetadatosEni();

    documentoEni.setMetadatosEni(metadatos);
    retorno.addObject("documentoEni", documentoEni);

    retorno.addObject("firma", "0");
    retorno.addObject("formatoFirma", "CAdES");
    retorno.addObject("metadatoAdicionales", new TipoMetadatosAdicionales());
    retorno.addObject(SIGNATURE_SERVER, insideService.checkSignatureServerByUser(
        (ObjetoInsideUsuario) request.getSession().getAttribute(WebConstants.USUARIO_SESSION)));
  }

  private String getUsuarioConsultaPuestaDisposicion(HttpSession session) {
    // usuario que ha entrado en la aplicacion que entrara con clave
    ObjetoInsideUsuario usuarioLogado =
        (ObjetoInsideUsuario) session.getAttribute(WebConstants.USUARIO_SESSION);

    String nifConsultaPuestaDisposicion = "";

    if (usuarioLogado != null) {
      nifConsultaPuestaDisposicion = usuarioLogado.getNif();
      logger.debug("Usuario:" + nifConsultaPuestaDisposicion + " registrado en BD");
    } else {
      SecurityContextImpl sci =
          (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
      if (sci != null) {
        nifConsultaPuestaDisposicion = (String) sci.getAuthentication().getPrincipal();
        logger.debug("Usuario:" + nifConsultaPuestaDisposicion + "NO registrado en BD");
      }

    }

    return nifConsultaPuestaDisposicion;
  }

  private void eliminaMetadatosAdicionalesVacios(
      List<MetadatoAdicional> listaMetadatosAdicionales) {
    for (Iterator<MetadatoAdicional> iterator = listaMetadatosAdicionales.iterator(); iterator
        .hasNext();) {
      MetadatoAdicional metadatoAdicional = iterator.next();
      if (StringUtils.isEmpty((String) metadatoAdicional.getValor())) {
        iterator.remove();
      }
    }
  }

}
