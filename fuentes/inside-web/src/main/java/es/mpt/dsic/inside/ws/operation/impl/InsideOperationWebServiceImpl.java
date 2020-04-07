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

package es.mpt.dsic.inside.ws.operation.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import es.mpt.dsic.infofirma.service.InfoFirmaService;
import es.mpt.dsic.inside.model.busqueda.resultado.ResultadoBusquedaInside;
import es.mpt.dsic.inside.model.converter.InsideConverterBusqueda;
import es.mpt.dsic.inside.model.converter.InsideConverterBusquedaResultado;
import es.mpt.dsic.inside.model.converter.InsideConverterDocumento;
import es.mpt.dsic.inside.model.converter.InsideConverterExpediente;
import es.mpt.dsic.inside.model.converter.InsideConverterExpedienteInfo;
import es.mpt.dsic.inside.model.converter.InsideConverterFirmas;
import es.mpt.dsic.inside.model.converter.InsideConverterIndice;
import es.mpt.dsic.inside.model.converter.InsideConverterMetadatos;
import es.mpt.dsic.inside.model.converter.InsideConverterVersion;
import es.mpt.dsic.inside.model.converter.exception.InsideConverterException;
import es.mpt.dsic.inside.model.objetos.ObjetoInside;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInside;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInsideContenido;
import es.mpt.dsic.inside.model.objetos.documento.metadatos.ObjetoDocumentoInsideMetadatos;
import es.mpt.dsic.inside.model.objetos.documento.metadatos.ObjetoDocumentoInsideMetadatosEstadoElaboracion;
import es.mpt.dsic.inside.model.objetos.documento.metadatos.ObjetoDocumentoInsideMetadatosEstadoElaboracionEnumeracion;
import es.mpt.dsic.inside.model.objetos.enivalidation.ResultadoValidacion;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteInside;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteToken;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado;
import es.mpt.dsic.inside.model.objetos.expediente.metadatos.ObjetoExpedienteInsideMetadatos;
import es.mpt.dsic.inside.model.objetos.expediente.metadatos.ObjetoExpedienteInsideMetadatosEnumeracionEstados;
import es.mpt.dsic.inside.model.objetos.usuario.ObjetoInsideUsuario;
import es.mpt.dsic.inside.service.InSideService;
import es.mpt.dsic.inside.service.InSideServicePermisos;
import es.mpt.dsic.inside.service.InsideUtilService;
import es.mpt.dsic.inside.service.csv.CsvService;
import es.mpt.dsic.inside.service.exception.InSideServiceException;
import es.mpt.dsic.inside.service.exception.InsideServiceInternalException;
import es.mpt.dsic.inside.service.mail.MailService;
import es.mpt.dsic.inside.service.store.exception.InsideStoreObjectAlreadyExistsException;
import es.mpt.dsic.inside.service.util.InsideWSUtils;
import es.mpt.dsic.inside.service.util.XMLUtils;
import es.mpt.dsic.inside.service.visualizacion.ResultadoVisualizacionDocumento;
import es.mpt.dsic.inside.ws.config.InsideEnhancedConf;
import es.mpt.dsic.inside.ws.exception.InsideExceptionConverter;
import es.mpt.dsic.inside.ws.exception.InsideWSException;
import es.mpt.dsic.inside.ws.exception.InsideWsErrors;
import es.mpt.dsic.inside.ws.operation.GInsideOperationWebService;
import es.mpt.dsic.inside.ws.operation.InsideOperationWebService;
import es.mpt.dsic.inside.xml.eni.documento.TipoDocumento;
import es.mpt.dsic.inside.xml.eni.documento.contenido.TipoContenido;
import es.mpt.dsic.inside.xml.eni.expediente.TipoExpediente;
import es.mpt.dsic.inside.xml.eni.expediente.indice.TipoIndice;
import es.mpt.dsic.inside.xml.eni.expediente.indice.contenido.TipoCarpetaIndizada;
import es.mpt.dsic.inside.xml.eni.expediente.metadatos.EnumeracionEstados;
import es.mpt.dsic.inside.xml.eni.expediente.metadatos.TipoMetadatos;
import es.mpt.dsic.inside.xml.eni.firma.Firmas;
import es.mpt.dsic.inside.xml.inside.DocumentoInsideMetadatos;
import es.mpt.dsic.inside.xml.inside.ExpedienteInsideInfo;
import es.mpt.dsic.inside.xml.inside.ExpedienteInsideMetadatos;
import es.mpt.dsic.inside.xml.inside.TipoDocumentoInside;
import es.mpt.dsic.inside.xml.inside.TipoDocumentoInsideConMAdicionales;
import es.mpt.dsic.inside.xml.inside.TipoExpedienteInside;
import es.mpt.dsic.inside.xml.inside.TipoExpedienteInsideConMAdicionales;
import es.mpt.dsic.inside.xml.inside.TipoMetadatosAdicionales;
import es.mpt.dsic.inside.xml.inside.TipoVersionInside;
import es.mpt.dsic.inside.xml.inside.TokenExpediente;
import es.mpt.dsic.inside.xml.inside.ws.busqueda.ConsultaWsInside;
import es.mpt.dsic.inside.xml.inside.ws.busqueda.DocumentoResultadoBusqueda;
import es.mpt.dsic.inside.xml.inside.ws.busqueda.ExpedienteResultadoBusqueda;
import es.mpt.dsic.inside.xml.inside.ws.credential.WSCredentialInside;
import es.mpt.dsic.inside.xml.inside.ws.documento.DocumentoInsideInfo;
import es.mpt.dsic.inside.xml.inside.ws.documento.TipoDocumentoEniFileInside;
import es.mpt.dsic.inside.xml.inside.ws.documento.alta.TipoDocumentoAltaInside;
import es.mpt.dsic.inside.xml.inside.ws.documento.alta.TipoDocumentoAsociadoaExpediente;
import es.mpt.dsic.inside.xml.inside.ws.documento.conversion.TipoDocumentoConversionInside;
import es.mpt.dsic.inside.xml.inside.ws.documento.file.DocumentoEniFileInside;
import es.mpt.dsic.inside.xml.inside.ws.expediente.TipoExpedienteEniFileInside;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoExpedienteConversionInside;
import es.mpt.dsic.inside.xml.inside.ws.expediente.documentos.TipoExpedienteEniFileInsideConDocumentos;
import es.mpt.dsic.inside.xml.inside.ws.expediente.file.ExpedienteEniFileInside;
import es.mpt.dsic.inside.xml.inside.ws.expediente.file.RespuestaPdfExpediente;
import es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.TipoResultadoVisualizacionDocumentoInside;

@Service("insideOperationWebService")
public class InsideOperationWebServiceImpl implements InsideOperationWebService {
  protected static final Log logger = LogFactory.getLog(InsideOperationWebServiceImpl.class);

  protected static final String VERSION_NTI_EXP =
      "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e";
  protected static final String VERSION_NTI_DOC =
      "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e";
  final String DIRECCION_EMAIL = "https://inside.carm.es/inside/";
  final String TOKENFILE = "/token";
  final String FORMATO_FECHA = "\\d{4}-\\d{2}-\\d{2}";

  @Autowired
  private InSideService service;

  @Autowired
  private InfoFirmaService infofirmaService;

  @Autowired
  private InSideServicePermisos servicePermisos;

  @Autowired
  private InsideUtilService insideUtilService;

  @Autowired
  GInsideOperationWebService gInsideOperationWebService;

  @Autowired
  private InsideEnhancedConf wsEnhancedConf;

  @Autowired
  private CsvService csvService;

  @Autowired
  private InSideService insideService;

  @Autowired
  private MailService mailService;

  private Properties properties;

  @Value("${clave.auth.fail.fake.nif}")
  private String nifFake;

  @Override
  @Secured("ROLE_ALTA_EXPEDIENTE")
  public ExpedienteInsideInfo altaExpedienteEni(TipoExpediente expedienteEni,
      TipoMetadatosAdicionales metadatosAdicionales, String aplicacion) throws InsideWSException {
    ObjetoExpedienteInside expediente = null;
    ExpedienteInsideInfo expedienteInfo;

    try {
      expediente = service.getExpediente(expedienteEni.getMetadatosExp().getIdentificador(), false);
    } catch (Exception e) {
      logger.debug("El expediente no existe", e);
    }

    try {

      if (expediente == null) {
        expediente = validarExpedienteRecibido(expedienteEni, metadatosAdicionales, aplicacion);

        if (!insideUtilService.esLongitudIdentificadorValido(expediente.getIdentificador())) {
          throw new InsideWSException(InsideWsErrors.IDENTIFICADOR_DOCUMENTO_LONGITUD, null, "");
        }
        List<ObjetoDocumentoInside> listaDocumentos = new ArrayList<ObjetoDocumentoInside>();
        ResultadoValidacion resultado =
            insideUtilService.comprobarDocumentosIndiceExpediente(expediente, listaDocumentos);
        // CARM ### v2.0.8.1
        boolean flagAltaConDocuVacio = false;
        if (wsEnhancedConf.isActivoAltaExpedientEniDocumentoIndizadoAceptaVacio()
            && expediente.getIndice() != null && expediente.getIndice().getIndiceContenido() != null
            && expediente.getIndice().getIndiceContenido().getElementosIndizados() != null
            && expediente.getIndice().getIndiceContenido().getElementosIndizados().size() == 1
            && expediente.getIndice().getIndiceContenido().getElementosIndizados()
                .get(0) instanceof ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado
            && ((ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado) expediente.getIndice()
                .getIndiceContenido().getElementosIndizados().get(0)).getIdentificadorDocumento()
                    .isEmpty()
            && ((ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado) expediente.getIndice()
                .getIndiceContenido().getElementosIndizados().get(0)).getValorHuella().isEmpty()) {
          flagAltaConDocuVacio = true;
          expediente.getIndice().getIndiceContenido().getElementosIndizados().clear();
        }
        // CARM 2.0.8.1 ###
        if (!resultado.isValido())
          // CARM ### v2.0.8.1
          if (!flagAltaConDocuVacio)
            // CARM 2.0.8.1 ###
            throw new InsideWSException(InsideWsErrors.ERRORES_EN_EL_INDICE_EXPEDIENTE, null,
                resultado.getMensaje());

        expediente = service.altaExpediente(expediente, aplicacion, false);
        for (ObjetoDocumentoInside documento : listaDocumentos) {
          service.actualizarAsociacionDocumentoUnidad(documento, aplicacion, false);
        }

        expedienteInfo = InsideConverterExpedienteInfo.fromExpediente(expediente);
      } else {
        logger.debug("El expediente ya existe");
        throw new InsideStoreObjectAlreadyExistsException(expediente);
      }

      return expedienteInfo;
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  private byte[] transformarExpedienteParaValidarFirma(TipoExpedienteInsideConMAdicionales tExpAdi)
      throws Exception {
    // expediente con nodo firma en base64 hay que transformarlo a ds signature
    byte[] expediente_Antes_Transformacion = insideUtilService.generateExpXml(tExpAdi);

    // Para validar la firma recoger solo el ns7:expediente sin metadatosadicionales
    byte[] dataConFirmaSinIdentar =
        insideUtilService.generateExpXmlParaValidar(tExpAdi.getExpediente());

    // sustituye el nodo <ns7:expediente por el dataConFirmaSinIdentar que esta sin identar y bien
    // formado
    String data = XMLUtils.construirExpedienteENIValido(new String(expediente_Antes_Transformacion),
        new String(dataConFirmaSinIdentar));

    // terminar la transformacion de cambio firmabase64 a dsSignature
    byte[] expediente_Despues_Transformacion =
        XMLUtils.deFirmaBase64_A_DSSignature(data.getBytes("UTF-8"));

    return expediente_Despues_Transformacion;
  }

  public ObjetoExpedienteInside validarExpedienteRecibido(TipoExpediente expedienteEni,
      TipoMetadatosAdicionales metadatosAdicionales, String aplicacion)
      throws InSideServiceException {
    try {
      TipoExpedienteInsideConMAdicionales expAdic;
      ObjetoExpedienteInside expediente;
      checkVersionNTI(expedienteEni.getMetadatosExp());

      expediente =
          InsideConverterExpediente.expedienteEniToInside(expedienteEni, metadatosAdicionales);
      // CARM ### v2.0.8.1
      if (wsEnhancedConf.isActivoAltaExpedientEniAutogenerarIdentificador()
          && expediente.getIdentificador() != null && expediente.getIdentificador().isEmpty()) {
        expediente.setNullIdentificador(); // Forzar id autogenerado si se pasa vacío
        service.validateObjetoInsideIdentificador(expediente);
      }
      // CARM 2.0.8.1 ###
      if (CollectionUtils.isEmpty(expediente.getIndice().getFirmas())) {
        service.firmarIndiceExpediente(expediente, null, aplicacion);
        expAdic = InsideConverterExpediente.expedienteInsideToConMAdicionales(expediente, null);
      } else {
        expAdic = new TipoExpedienteInsideConMAdicionales();
        expAdic.setExpediente(expedienteEni);
        expAdic.setMetadatosAdicionales(metadatosAdicionales);
      }
      // expediente =
      // insideUtilService.validateExpedientImport(insideUtilService.generateExpXml(expAdic));

      // construir correctamente exp para poder validar
      byte[] expediente_Despues_Transformacion = transformarExpedienteParaValidarFirma(expAdic);
      expediente = insideUtilService.validateExpedientImport(expediente_Despues_Transformacion);

      return expediente;
    } catch (Exception e) {
      throw new InsideServiceInternalException("Error validando expediente recibido", e);
    }
  }

  @Override
  @Secured("ROLE_ALTA_EXPEDIENTE")
  public ExpedienteInsideInfo altaExpedienteEniXml(
      TipoExpedienteEniFileInsideConDocumentos expedienteEniFile, String aplicacion)
      throws InsideWSException {

    try {
      String info;
      ObjetoExpedienteInside objExp =
          insideUtilService.validateExpedientImport(expedienteEniFile.getExpedienteEniBytes());

      if (!insideUtilService.esLongitudIdentificadorValido(objExp.getIdentificador())) {
        throw new InsideWSException(InsideWsErrors.IDENTIFICADOR_DOCUMENTO_LONGITUD, null, "");
      }
      List<ObjetoDocumentoInside> documentos =
          insideUtilService.recoverDocumentXml(expedienteEniFile.getDocumentosEniFile());
      Collection<String> resultado =
          service.correspondencia(objExp, service.getListIdentifierDocsInside(documentos));
      if (resultado == null) {
        Map<String, Object> result =
            insideUtilService.crearNuevoExpediente(objExp, documentos, aplicacion, false);
        objExp = (ObjetoExpedienteInside) result.get("expediente");
        info =
            result.get("docsNoCreate") != null
                ? InsideWsErrors.DOCS_SIN_ACTUALIZAR.getValue().getDescripcion()
                    + result.get("docsNoCreate")
                : "";
      } else {
        throw new InsideWSException(InsideWsErrors.CORRESPONDENCIA_DOCUMENTOS_EXPEDIENTE, null,
            resultado.toString());
      }

      return InsideConverterExpedienteInfo.fromExpedienteMasInfo(objExp, info);
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_MODIFICA_EXPEDIENTE")
  public ExpedienteInsideInfo cambiaMetadatosExpediente(String identificador,
      TipoMetadatos metadatosEni, TipoMetadatosAdicionales metadatosAdicionales, String aplicacion)
      throws InsideWSException {
    try {
      // Control Permisos
      servicePermisos.checkPermisos(identificador, null, ObjetoExpedienteInside.class, aplicacion);

      checkVersionNTI(metadatosEni);

      logger.debug("Convirtiendo los metadatosEni a Inside");

      ObjetoExpedienteInsideMetadatos metadatos = InsideConverterMetadatos.Expediente
          .metadatosXmlToMetadatosInside(metadatosEni, metadatosAdicionales);
      ObjetoExpedienteInside expediente =
          service.cambiaMetadatosExpediente(identificador, metadatos, aplicacion, false);

      logger.debug("Convirtiendo el expediente Inside a InfoInside");

      return InsideConverterExpedienteInfo.fromExpediente(expediente);

    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_MODIFICA_DOCUMENTO")
  public DocumentoInsideInfo cambiaMetadatosDocumento(String identificador,
      es.mpt.dsic.inside.xml.eni.documento.metadatos.TipoMetadatos metadatosEni,
      TipoMetadatosAdicionales metadatosAdicionales, String aplicacion) throws InsideWSException {
    try {

      // Control Permisos
      servicePermisos.checkPermisos(identificador, null, ObjetoDocumentoInside.class, aplicacion);

      checkVersionNTI(metadatosEni);

      logger.debug("Convirtiendo los metadatosEni a Inside");

      ObjetoDocumentoInsideMetadatos metadatos = InsideConverterMetadatos.Documento
          .metadatosXmlToMetadatosInside(metadatosEni, metadatosAdicionales);
      ObjetoDocumentoInside documento =
          service.cambiaMetadatosDocumento(identificador, metadatos, aplicacion, false);

      logger.debug("Convirtiendo el documento Inside a InfoInside");

      return InsideConverterDocumento.documentoInsideToDocumentoInfo(documento);

    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_MODIFICA_EXPEDIENTE")
  public ExpedienteInsideInfo modificaExpedienteEni(TipoExpediente expedienteEni,
      TipoMetadatosAdicionales metadatosAdicionales, String aplicacion) throws InsideWSException {
    try {
      // Control Permisos
      servicePermisos.checkPermisos(expedienteEni.getMetadatosExp().getIdentificador(), null,
          ObjetoExpedienteInside.class, aplicacion);

      checkVersionNTI(expedienteEni.getMetadatosExp());
      validaIdentificador(expedienteEni);

      logger.debug("Convirtiendo el expedienteEni a Inside");
      ObjetoExpedienteInside expediente =
          InsideConverterExpediente.expedienteEniToInside(expedienteEni, metadatosAdicionales);

      expediente = service.modificaExpedienteInside(expediente, aplicacion, false);

      logger.debug("Convirtiendo el expediente Inside a InfoInside");

      return InsideConverterExpedienteInfo.fromExpediente(expediente);

    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  public ExpedienteInsideInfo altaCarpetaEnExpediente(String identificadorExpediente,
      TipoCarpetaIndizada carpetaIndizada, String ruta, String aplicacion)
      throws InsideWSException {
    try {
      if (identificadorExpediente == null) {
        throw new InsideWSException(InsideWsErrors.IDENTIFICADOR_EXPEDIENTE_VACIO);
      }
      if (carpetaIndizada == null || org.apache.commons.lang.StringUtils
          .isBlank(carpetaIndizada.getIdentificadorCarpeta())) {
        throw new InsideWSException(InsideWsErrors.IDENTIFICADOR_CARPETA_VACIO);
      }

      if (ruta == null) {
        ruta = "";
      }

      checkPermissionsAltaCarpeta(identificadorExpediente, carpetaIndizada, aplicacion);
      ObjetoExpedienteInside expediente =
          checkNoRepeatIds(identificadorExpediente, carpetaIndizada);
      expediente = service.altaCarpeta(expediente,
          InsideConverterIndice.carpetaIndizadaConversionToInside(carpetaIndizada), ruta,
          aplicacion, false);
      return InsideConverterExpedienteInfo.fromExpediente(expediente);
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  private ObjetoExpedienteInside checkNoRepeatIds(String identificadorExpediente,
      TipoCarpetaIndizada carpetaIndizada) throws InsideWSException {
    try {
      ObjetoExpedienteInside expediente = service.getExpediente(identificadorExpediente, false);

      TipoExpediente tipoExpediente =
          InsideConverterExpediente.expedienteInsideToEni(expediente, null);

      List<String> listRepeatIds = service.checkRepeatDocsExpTipo(
          tipoExpediente.getIndice().getIndiceContenido()
              .getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada(),
          carpetaIndizada.getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada());

      if (CollectionUtils.isNotEmpty(listRepeatIds)) {
        StringBuilder sb = new StringBuilder();
        for (String id : listRepeatIds)
          sb.append(id + ".");
        throw new InsideWSException(InsideWsErrors.IDENTIFICADOR_CARPETA_VACIO, null,
            sb.toString());
      }
      return expediente;
    } catch (InSideServiceException e) {
      throw new InsideWSException(InsideWsErrors.OBJECT_NOT_FOUND, e);
    } catch (InsideConverterException e) {
      throw new InsideWSException(InsideWsErrors.OBJECT_VALIDATION_ERROR, e);
    }
  }

  public void checkPermissionsAltaCarpeta(String identificadorExpediente,
      TipoCarpetaIndizada carpetaIndizada, String aplicacion) throws InSideServiceException {
    servicePermisos.checkPermisos(identificadorExpediente, null, ObjetoExpedienteInside.class,
        aplicacion);

    Map<String, String> listId = service.obtenerDocsExpTipo(
        carpetaIndizada.getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada(), "/");
    Iterator it = listId.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry elemento = (Map.Entry) it.next();
      servicePermisos.checkPermisos((String) elemento.getKey(), null, ObjetoDocumentoInside.class,
          aplicacion);
    }
  }

  @Override
  public ExpedienteInsideInfo borrarCarpetaEnExpediente(String identificadorExpediente,
      String identificadorCarpeta, String ruta, String aplicacion) throws InsideWSException {
    try {
      if (identificadorExpediente == null) {
        throw new InsideWSException(InsideWsErrors.IDENTIFICADOR_EXPEDIENTE_VACIO);
      }
      if (identificadorCarpeta == null) {
        throw new InsideWSException(InsideWsErrors.IDENTIFICADOR_CARPETA_VACIO);
      }

      if (ruta == null) {
        ruta = "";
      }

      logger.debug("llamando a borrar carpeta para expediente " + identificadorExpediente
          + " con identificador de carpeta " + identificadorCarpeta + " en ruta " + ruta);

      // Control Permisos
      servicePermisos.checkPermisos(identificadorExpediente, null, ObjetoExpedienteInside.class,
          aplicacion);

      ObjetoExpedienteInside expediente =
          service.borrarCarpeta(identificadorExpediente, identificadorCarpeta, ruta);
      insideUtilService.validateExpedientImport(insideUtilService.generateExpXml(
          InsideConverterExpediente.expedienteInsideToConMAdicionales(expediente, null)));
      service.modificaExpedienteInside(expediente, aplicacion, false);
      logger.debug("Convirtiendo el expediente Inside a InfoInside");

      return InsideConverterExpedienteInfo.fromExpediente(expediente);

    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_MODIFICA_EXPEDIENTE")
  public ExpedienteInsideInfo cambiaEstadoExpediente(String identificador,
      EnumeracionEstados estado, String aplicacion) throws InsideWSException {
    try {
      if (estado == null) {
        throw new InsideWSException(InsideWsErrors.ESTADO_VACIO);
      }

      // Control Permisos
      servicePermisos.checkPermisos(identificador, null, ObjetoExpedienteInside.class, aplicacion);

      ObjetoExpedienteInsideMetadatosEnumeracionEstados estadoInside =
          ObjetoExpedienteInsideMetadatosEnumeracionEstados.fromValue(estado.value());

      ObjetoExpedienteInside expediente =
          service.setEstadoExpediente(identificador, estadoInside, false, aplicacion, false);

      ExpedienteInsideInfo expedienteInfo =
          InsideConverterExpedienteInfo.fromExpediente(expediente);

      logger.info("Cambiado estado del Expediente con identificador " + identificador + " a "
          + estado.value());
      return expedienteInfo;

    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_MODIFICA_EXPEDIENTE")
  public ExpedienteInsideInfo importarExpediente(String identificador,
      String identificadorImportado, String ruta, String aplicacion) throws InsideWSException {
    try {
      // Control Permisos
      servicePermisos.checkPermisos(identificador, null, ObjetoExpedienteInside.class, aplicacion);

      if (!insideUtilService.esLongitudIdentificadorValido(identificador)) {
        throw new InsideWSException(InsideWsErrors.IDENTIFICADOR_DOCUMENTO_LONGITUD, null, "");
      }
      if (!insideUtilService.esLongitudIdentificadorValido(identificadorImportado)) {
        throw new InsideWSException(InsideWsErrors.IDENTIFICADOR_DOCUMENTO_LONGITUD, null, "");
      }
      ObjetoExpedienteInside expediente = service.importarExpediente(identificador,
          identificadorImportado, ruta, aplicacion, false);

      ExpedienteInsideInfo expedienteInfo =
          InsideConverterExpedienteInfo.fromExpediente(expediente);

      logger.info("importado Expediente con identificador " + identificadorImportado
          + " a expediente" + identificador);
      return expedienteInfo;

    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_MODIFICA_EXPEDIENTE")
  public ExpedienteInsideInfo vincularExpediente(String identificador,
      String identificadorVinculado, String ruta, String aplicacion) throws InsideWSException {
    try {
      // Control Permisos
      servicePermisos.checkPermisos(identificador, null, ObjetoExpedienteInside.class, aplicacion);
      servicePermisos.checkPermisos(identificadorVinculado, null, ObjetoExpedienteInside.class,
          aplicacion);

      ObjetoExpedienteInside expediente = service.vincularExpediente(identificador,
          identificadorVinculado, ruta, aplicacion, false);

      ExpedienteInsideInfo expedienteInfo =
          InsideConverterExpedienteInfo.fromExpediente(expediente);

      logger.info("Vinculado Expediente con identificador " + identificadorVinculado
          + " a expediente" + identificador);
      return expedienteInfo;

    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_MODIFICA_EXPEDIENTE")
  public ExpedienteInsideInfo crearVistaAbiertaExpediente(String identificador, String aplicacion)
      throws InsideWSException {
    try {
      // Control Permisos
      servicePermisos.checkPermisos(identificador, null, ObjetoExpedienteInside.class, aplicacion);

      ObjetoExpedienteInside expediente =
          service.createOpenViewExpedient(identificador, null, false, aplicacion, false);

      ExpedienteInsideInfo expedienteInfo =
          InsideConverterExpedienteInfo.fromExpediente(expediente);

      logger.info("Se ha creado una vista abierta del Expediente " + identificador
          + ". El identificador del nuevo expediente es: " + expedienteInfo.getIdentificador());
      return expedienteInfo;

    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_MODIFICA_EXPEDIENTE")
  public ExpedienteInsideInfo crearVistaCerradaExpediente(String identificador, String aplicacion)
      throws InsideWSException {
    try {
      // Control Permisos
      servicePermisos.checkPermisos(identificador, null, ObjetoExpedienteInside.class, aplicacion);

      ObjetoExpedienteInside expediente =
          service.createClosedViewExpedient(identificador, null, false, aplicacion, false);

      ExpedienteInsideInfo expedienteInfo =
          InsideConverterExpedienteInfo.fromExpediente(expediente);

      logger.info("Se ha creado una vista cerrada del Expediente " + identificador
          + ". El identificador del nuevo expediente es: " + expedienteInfo.getIdentificador());
      return expedienteInfo;

    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_MODIFICA_EXPEDIENTE")
  public ExpedienteInsideInfo vincularDocumentosEnExpediente(String identificador,
      List<String> identificadoresDocumentos, String ruta, String aplicacion)
      throws InsideWSException {
    try {
      // Control Permisos
      servicePermisos.checkPermisos(identificador, null, ObjetoExpedienteInside.class, aplicacion);
      servicePermisos.checkPermisos(identificadoresDocumentos, null, ObjetoDocumentoInside.class,
          aplicacion);

      ObjetoExpedienteInside expediente = service.vincularDocumentosEnExpediente(identificador,
          identificadoresDocumentos, ruta, aplicacion, false);

      ExpedienteInsideInfo expedienteInfo =
          InsideConverterExpedienteInfo.fromExpediente(expediente);

      logger.info("importados Documentos " + identificadoresDocumentos + " a expediente"
          + identificador + " en ruta " + ruta);
      return expedienteInfo;

    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_MODIFICA_EXPEDIENTE")
  public ExpedienteInsideInfo desvincularDocumentosEnExpediente(String identificador,
      List<String> identificadoresDocumentos, String ruta, String aplicacion)
      throws InsideWSException {
    try {
      // Control Permisos
      servicePermisos.checkPermisos(identificador, null, ObjetoExpedienteInside.class, aplicacion);
      servicePermisos.checkPermisos(identificadoresDocumentos, null, ObjetoDocumentoInside.class,
          aplicacion);

      ObjetoExpedienteInside expediente = service.desvincularDocumentosEnExpediente(identificador,
          identificadoresDocumentos, ruta, aplicacion, false);
      service.modificaExpedienteInsideWS(expediente, aplicacion, false);
      insideUtilService.validateExpedientImport(
          (insideUtilService.getStringExpedienteMarshall(expediente)).getBytes());

      ExpedienteInsideInfo expedienteInfo =
          InsideConverterExpedienteInfo.fromExpediente(expediente);

      logger.info("desvinculados Documentos " + identificadoresDocumentos + " a expediente"
          + identificador + " en ruta " + ruta);
      return expedienteInfo;

    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_MODIFICA_EXPEDIENTE")
  public ExpedienteInsideInfo cambiarUbicacionEnExpediente(String identificadorExpediente,
      List<String> identificadoresElementos, String rutaOrigen, String rutaDestino,
      String aplicacion) throws InsideWSException {
    try {
      servicePermisos.checkPermisos(identificadorExpediente, null, ObjetoExpedienteInside.class,
          aplicacion);

      ObjetoExpedienteInside expediente =
          service.cambiarUbicacionEnExpediente(identificadorExpediente, identificadoresElementos,
              rutaOrigen, rutaDestino, aplicacion, false);
      insideUtilService.validateExpedientImport(
          (insideUtilService.getStringExpedienteMarshall(expediente)).getBytes());
      service.modificaExpedienteInside(expediente, aplicacion, false);
      return InsideConverterExpedienteInfo.fromExpediente(expediente);
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_LEER_EXPEDIENTE")
  public TipoExpedienteInside getExpediente(String identificador, Integer version,
      String aplicacion) throws InsideWSException {
    try {
      // Control Permisos
      servicePermisos.checkPermisos(identificador, version, ObjetoExpedienteInside.class,
          aplicacion);
      ObjetoExpedienteInside expedienteInside = service.getExpediente(identificador, version, true);
      return InsideConverterExpediente.expedienteInsideToXml(expedienteInside, null);
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_LEER_EXPEDIENTE")
  public TipoExpediente getExpedienteEni(String identificador, String aplicacion)
      throws InsideWSException {
    try {
      // Control Permisos
      servicePermisos.checkPermisos(identificador, null, ObjetoExpedienteInside.class, aplicacion);

      ObjetoExpedienteInside expediente = service.getExpediente(identificador, true);

      TipoExpediente expedienteEni =
          InsideConverterExpediente.expedienteInsideToEni(expediente, null);
      logger.info("Devuelto Expediente ENI con identificador " + identificador);
      return expedienteEni;

    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_LEER_EXPEDIENTE")
  public TipoExpedienteEniFileInside getExpedienteEniFile(String identificador,
      Boolean visualizacionIndice, String aplicacion) throws InsideWSException {
    try {
      // Control Permisos
      servicePermisos.checkPermisos(identificador, null, ObjetoExpedienteInside.class, aplicacion);

      ObjetoExpedienteInside expediente = service.getExpediente(identificador, visualizacionIndice);

      TipoExpediente expedienteEni =
          InsideConverterExpediente.expedienteInsideToEni(expediente, null);
      logger.info("Devuelto Expediente ENI con identificador " + identificador);

      TipoExpedienteEniFileInside expedienteEniFile =
          InsideConverterExpediente.expedienteEniToTipoExpedienteEniFileInside(expedienteEni, true);

      logger.info("Convertido expediente ENI a array de bytes");

      return expedienteEniFile;

    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }

  }

  @Override
  @Secured("ROLE_LEER_EXPEDIENTE")
  public TipoIndice getIndiceExpediente(String identificador, String aplicacion)
      throws InsideWSException {
    try {
      // Control Permisos
      servicePermisos.checkPermisos(identificador, null, ObjetoExpedienteInside.class, aplicacion);

      ObjetoExpedienteInside expedienteInside = service.getExpediente(identificador, true);

      logger.info("Devuelto índice de Expediente ENI con identificador " + identificador);

      return InsideConverterIndice.indiceInsideToEni(expedienteInside.getIndice(), identificador);

    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_LEER_EXPEDIENTE")
  public ExpedienteInsideMetadatos getExpedienteMetadatos(String identificador, Integer version,
      String aplicacion) throws InsideWSException {
    try {
      // Control Permisos
      servicePermisos.checkPermisos(identificador, version, ObjetoExpedienteInside.class,
          aplicacion);

      ObjetoExpedienteInsideMetadatos metadatos =
          service.getMetadatosExpediente(identificador, version);

      logger.info("Devuelto Metadatos del Expediente con identificador " + identificador);
      return InsideConverterMetadatos.Expediente.metadatosInsideToXml(metadatos);

    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_LEER_EXPEDIENTE")
  public List<TipoVersionInside> getVersionesExpediente(String identificador, String aplicacion)
      throws InsideWSException {
    try {
      // Control Permisos
      servicePermisos.checkPermisos(identificador, null, ObjetoExpedienteInside.class, aplicacion);

      return InsideConverterVersion
          .versionesInsideToXml(service.getVersionesExpediente(identificador));

    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_ALTA_DOCUMENTO")
  public DocumentoInsideInfo altaDocumentoOriginal(TipoDocumentoAltaInside documentoAlta,
      String aplicacion) throws InsideWSException {
    try {
      if (documentoAlta.getAsociadoaExpediente() != null && StringUtils
          .hasText(documentoAlta.getAsociadoaExpediente().getIdentificadorExpediente())) {
        // Control Permisos si viene informado el expediente
        servicePermisos.checkPermisos(
            documentoAlta.getAsociadoaExpediente().getIdentificadorExpediente(), null,
            ObjetoExpedienteInside.class, aplicacion);
      }

      ObjetoDocumentoInside documentoInside = this.altaDocumento(documentoAlta,
          ObjetoDocumentoInsideMetadatosEstadoElaboracionEnumeracion.EE_01, null, aplicacion);

      DocumentoInsideInfo documentoInfo =
          InsideConverterDocumento.documentoInsideToDocumentoInfo(documentoInside);

      logger.info("Dado de alta Documento original con identificador "
          + documentoInside.getIdentificador());
      return documentoInfo;

    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }

  }

  @Override
  @Secured("ROLE_ALTA_DOCUMENTO")
  public DocumentoInsideInfo altaDocumentoDigitalizado(TipoDocumentoAltaInside documentoAlta,
      String aplicacion) throws InsideWSException {
    try {
      if (documentoAlta.getAsociadoaExpediente() != null && StringUtils
          .hasText(documentoAlta.getAsociadoaExpediente().getIdentificadorExpediente())) {
        servicePermisos.checkPermisos(
            documentoAlta.getAsociadoaExpediente().getIdentificadorExpediente(), null,
            ObjetoExpedienteInside.class, aplicacion);
      }

      ObjetoDocumentoInside documentoInside = this.altaDocumento(documentoAlta,
          ObjetoDocumentoInsideMetadatosEstadoElaboracionEnumeracion.EE_03, null, aplicacion);

      DocumentoInsideInfo documentoInfo =
          InsideConverterDocumento.documentoInsideToDocumentoInfo(documentoInside);

      logger.info("Dado de alta Documento digitalizado con identificador "
          + documentoInside.getIdentificador());
      return documentoInfo;

    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_ALTA_DOCUMENTO")
  public DocumentoInsideInfo altaDocumentoCopiaParcial(TipoDocumentoAltaInside documentoAlta,
      String identificadorDocumentoOrigen, String aplicacion) throws InsideWSException {
    try {
      // Control Permisos
      servicePermisos.checkPermisos(identificadorDocumentoOrigen, null, ObjetoDocumentoInside.class,
          aplicacion);

      if (documentoAlta.getAsociadoaExpediente() != null && StringUtils
          .hasText(documentoAlta.getAsociadoaExpediente().getIdentificadorExpediente())) {
        servicePermisos.checkPermisos(
            documentoAlta.getAsociadoaExpediente().getIdentificadorExpediente(), null,
            ObjetoExpedienteInside.class, aplicacion);
      }

      ObjetoDocumentoInside documentoInside = this.altaDocumento(documentoAlta,
          ObjetoDocumentoInsideMetadatosEstadoElaboracionEnumeracion.EE_04,
          identificadorDocumentoOrigen, aplicacion);

      DocumentoInsideInfo documentoInfo =
          InsideConverterDocumento.documentoInsideToDocumentoInfo(documentoInside);

      logger.info("Dado de alta Documento con identificador " + documentoInside.getIdentificador()
          + " proviniente de copia parcial de documento origen con identificador "
          + identificadorDocumentoOrigen);
      return documentoInfo;

    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_ALTA_DOCUMENTO")
  public DocumentoInsideInfo altaDocumentoCambioFormato(TipoDocumentoAltaInside documentoAlta,
      String identificadorDocumentoOrigen, String aplicacion) throws InsideWSException {
    try {
      // Control Permisos
      servicePermisos.checkPermisos(identificadorDocumentoOrigen, null, ObjetoDocumentoInside.class,
          aplicacion);

      if (documentoAlta.getAsociadoaExpediente() != null && StringUtils
          .hasText(documentoAlta.getAsociadoaExpediente().getIdentificadorExpediente())) {
        servicePermisos.checkPermisos(
            documentoAlta.getAsociadoaExpediente().getIdentificadorExpediente(), null,
            ObjetoExpedienteInside.class, aplicacion);
      }

      ObjetoDocumentoInside documentoInside = this.altaDocumento(documentoAlta,
          ObjetoDocumentoInsideMetadatosEstadoElaboracionEnumeracion.EE_02,
          identificadorDocumentoOrigen, aplicacion);

      DocumentoInsideInfo documentoInfo =
          InsideConverterDocumento.documentoInsideToDocumentoInfo(documentoInside);

      logger.info("Dado de alta Documento con identificador " + documentoInside.getIdentificador()
          + " proviniente de cambio de formato de documento origen con identificador "
          + identificadorDocumentoOrigen);
      return documentoInfo;

    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_ALTA_DOCUMENTO")
  public DocumentoInsideInfo altaDocumentoOtros(TipoDocumentoAltaInside documentoAlta,
      String aplicacion) throws InsideWSException {
    try {
      if (documentoAlta.getAsociadoaExpediente() != null && StringUtils
          .hasText(documentoAlta.getAsociadoaExpediente().getIdentificadorExpediente())) {
        servicePermisos.checkPermisos(
            documentoAlta.getAsociadoaExpediente().getIdentificadorExpediente(), null,
            ObjetoExpedienteInside.class, aplicacion);
      }

      ObjetoDocumentoInside documentoInside = this.altaDocumento(documentoAlta,
          ObjetoDocumentoInsideMetadatosEstadoElaboracionEnumeracion.EE_99, null, aplicacion);

      DocumentoInsideInfo documentoInfo =
          InsideConverterDocumento.documentoInsideToDocumentoInfo(documentoInside);

      logger.info("Dado de alta Documento con Estado Elaboracion -otros- con identificador "
          + documentoInside.getIdentificador());
      return documentoInfo;

    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_ALTA_DOCUMENTO")
  public DocumentoInsideInfo altaDocumentoEni(TipoDocumento documentoEni,
      TipoDocumentoAsociadoaExpediente asociadoAExpediente,
      TipoMetadatosAdicionales metadatosAdicionales, boolean firmaServidor, String aplicacion)
      throws InsideWSException {
    try {
      checkVersionNTI(documentoEni.getMetadatos());

      logger.debug("Convirtiendo el documentoEni a Inside");

      if (asociadoAExpediente != null
          && StringUtils.hasText(asociadoAExpediente.getIdentificadorExpediente())) {
        servicePermisos.checkPermisos(asociadoAExpediente.getIdentificadorExpediente(), null,
            ObjetoExpedienteInside.class, aplicacion);
      }

      ObjetoDocumentoInside documento;
      TipoDocumentoInsideConMAdicionales docAdic;
      if (firmaServidor) {
        logger.debug("Se firmará en servidor el documento");
        documento = InsideConverterDocumento.documentoEniAndMetadatosToDocumentoInside(documentoEni,
            metadatosAdicionales);
        documento = service.firmaServidorDocumento(documento, aplicacion);
      } else {
        documento = InsideConverterDocumento.documentoEniAndMetadatosToDocumentoInside(documentoEni,
            metadatosAdicionales);
      }

      // CARM ### v2.0.8.1
      if (wsEnhancedConf.isActivoAltaDocumentoEniAutogenerarIdentificador()
          && documento.getIdentificador() != null && documento.getIdentificador().isEmpty()) {
        documento.setNullIdentificador(); // Forzar id autogenerado si se pasa vacío
        service.validateObjetoInsideIdentificador(documento);
      }
      // CARM 2.0.8.1 ###

      docAdic = InsideConverterDocumento.documentoInsideToConMAdicionales(documento, null);
      documento =
          insideUtilService.validateDocumentImport(insideUtilService.generateDocXml(docAdic));
      insideUtilService.checkNoExistIdDocumentInside(documento.getIdentificador(), true);

      if (asociadoAExpediente != null
          && StringUtils.hasText(asociadoAExpediente.getIdentificadorExpediente())) {
        documento =
            service.altaDocumento(documento, asociadoAExpediente.getIdentificadorExpediente(),
                asociadoAExpediente.getIdentificadorCarpeta(), aplicacion, false);
      } else {
        documento = service.altaDocumento(documento, aplicacion, false);
      }

      logger.debug("Convirtiendo el documentoInside a InfoInside");
      DocumentoInsideInfo documentoInfo =
          InsideConverterDocumento.documentoInsideToDocumentoInfo(documento);

      logger
          .info("Importado Documento desde ENI con identificador " + documento.getIdentificador());

      return documentoInfo;

    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_ALTA_DOCUMENTO")
  public DocumentoInsideInfo altaDocumentoEniXml(TipoDocumentoEniFileInside documentoEniFile,
      String aplicacion) throws InsideWSException {
    try {

      ObjetoDocumentoInside objDoc =
          insideUtilService.validateDocumentImport(documentoEniFile.getDocumentoEniBytes());

      insideUtilService.checkNoExistIdDocumentInside(objDoc.getIdentificador(), true);

      objDoc = service.altaDocumento(objDoc, aplicacion, false);

      return InsideConverterDocumento.documentoInsideToDocumentoInfo(objDoc);
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_MODIFICA_DOCUMENTO")
  public DocumentoInsideInfo modificaDocumento(TipoDocumento documentoEni,
      TipoMetadatosAdicionales metadatosAdicionales, String aplicacion) throws InsideWSException {
    try {
      // Control Permisos
      servicePermisos.checkPermisos(documentoEni.getMetadatos().getIdentificador(), null,
          ObjetoDocumentoInside.class, aplicacion);

      checkVersionNTI(documentoEni.getMetadatos());

      logger.debug("Convirtiendo el documentoEni a Inside");
      TipoDocumentoInsideConMAdicionales docAdic = new TipoDocumentoInsideConMAdicionales();
      docAdic.setDocumento(documentoEni);
      docAdic.setMetadatosAdicionales(metadatosAdicionales);

      ObjetoDocumentoInside documento =
          insideUtilService.validateDocumentImport(insideUtilService.generateDocXml(docAdic));

      documento = service.modificaDocumento(documento, aplicacion, false);

      logger.debug("Convirtiendo el documentoInside a InfoInside");
      DocumentoInsideInfo documentoInfo =
          InsideConverterDocumento.documentoInsideToDocumentoInfo(documento);

      logger.info("Modificado Documento ENI con identificador " + documento.getIdentificador());
      return documentoInfo;
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_LEER_DOCUMENTO")
  public TipoDocumentoInside getDocumento(String identificador, String aplicacion)
      throws InsideWSException {
    try {
      // Control Permisos
      servicePermisos.checkPermisos(identificador, null, ObjetoDocumentoInside.class, aplicacion);

      ObjetoDocumentoInside documento = service.getDocumento(identificador);

      TipoDocumentoInside documentoXml =
          InsideConverterDocumento.documentoInsideToXml(documento, null);

      logger.info("Devuelto Documento con identificador " + documento.getIdentificador());

      return documentoXml;

    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_LEER_DOCUMENTO")
  public TipoDocumento getDocumentoEni(String identificador, String aplicacion)
      throws InsideWSException {
    try {
      // Control Permisos
      servicePermisos.checkPermisos(identificador, null, ObjetoDocumentoInside.class, aplicacion);

      ObjetoDocumentoInside documento = service.getDocumento(identificador);

      TipoDocumento documentoEni = InsideConverterDocumento.documentoInsideToEni(documento, null);

      logger.info("Devuelto Documento ENI con identificador " + documento.getIdentificador());

      return documentoEni;

    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_LEER_DOCUMENTO")
  public TipoDocumentoEniFileInside getDocumentoEniFile(String identificador, String aplicacion)
      throws InsideWSException {
    try {
      // Control Permisos
      servicePermisos.checkPermisos(identificador, null, ObjetoDocumentoInside.class, aplicacion);

      ObjetoDocumentoInside documento = service.getDocumento(identificador);

      TipoDocumento documentoEni = InsideConverterDocumento.documentoInsideToEni(documento, null);

      logger.info("Obtenido Documento ENI con identificador " + documento.getIdentificador());

      TipoDocumentoEniFileInside documentoEniFile =
          InsideConverterDocumento.documentoEniToTipoDocumentoEniFileInside(documentoEni, true);

      logger.info("Convertido documento ENI a array de bytes");

      return documentoEniFile;

    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_LEER_DOCUMENTO")
  public TipoContenido getDocumentoContenido(String identificador, String aplicacion)
      throws InsideWSException {
    try {
      // Control Permisos
      servicePermisos.checkPermisos(identificador, null, ObjetoDocumentoInside.class, aplicacion);

      ObjetoDocumentoInsideContenido contenidoInside =
          service.getDocumentoContenido(identificador, null);

      TipoContenido contenido =
          InsideConverterDocumento.Contenido.contenidoInsideToEni(contenidoInside, null);

      logger.info("Devuelto Contenido de Documento ENI con identificador " + identificador);

      return contenido;

    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_LEER_DOCUMENTO")
  public DocumentoInsideMetadatos getMetadatosDocumento(String identificador, Integer version,
      String aplicacion) throws InsideWSException {
    try {
      // Control Permisos
      servicePermisos.checkPermisos(identificador, version, ObjetoDocumentoInside.class,
          aplicacion);

      ObjetoDocumentoInsideMetadatos metadatosInside = service.getMetadatosDocumento(identificador);

      DocumentoInsideMetadatos metadatos =
          InsideConverterMetadatos.Documento.metadatosInsideToXml(metadatosInside);

      logger.info("Devueltos Metadatos de Documento ENI con identificador " + identificador);

      return metadatos;

    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_LEER_DOCUMENTO")
  public Integer getNumeroBytesDocumentoFirmado(String identificador, String aplicacion)
      throws InsideWSException {
    try {
      // Control Permisos
      servicePermisos.checkPermisos(identificador, null, ObjetoDocumentoInside.class, aplicacion);

      return service.getNumeroBytesDocumento(identificador);

    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }

  }

  @Override
  @Secured("ROLE_LEER_DOCUMENTO")
  public Firmas getFirmasEniCSVDocumento(String identificador, String aplicacion)
      throws InsideWSException {
    try {
      // Control Permisos
      servicePermisos.checkPermisos(identificador, null, ObjetoDocumentoInside.class, aplicacion);

      return InsideConverterFirmas
          .firmasInsideToEni(service.getFirmasCSVDocumentoInside(identificador), true);

    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }

  }

  @Override
  @Secured("ROLE_LEER_EXPEDIENTE")
  public ExpedienteResultadoBusqueda buscarExpedientes(ConsultaWsInside consulta, int limite,
      int pagina, String aplicacion) throws InsideWSException {
    try {
      ResultadoBusquedaInside<ObjetoExpedienteInsideMetadatos> res = service
          .buscarExpedientes(InsideConverterBusqueda.consultaXmlToInside(consulta), limite, pagina);

      // Control Permisos
      servicePermisos.checkPermisos(res, aplicacion);

      return InsideConverterBusquedaResultado.respuestaBusquedaExpedienteInsideToXml(res);
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }

  }

  @Override
  @Secured("ROLE_LEER_DOCUMENTO")
  public DocumentoResultadoBusqueda buscarDocumentos(ConsultaWsInside consulta, int limite,
      int pagina, String aplicacion) throws InsideWSException {
    try {
      ResultadoBusquedaInside<ObjetoDocumentoInsideMetadatos> res = service
          .buscarDocumentos(InsideConverterBusqueda.consultaXmlToInside(consulta), limite, pagina);

      // Control Permisos
      servicePermisos.checkPermisos(res, aplicacion);

      return InsideConverterBusquedaResultado.respuestaBusquedaDocumentoInsideToXml(res);
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  public DocumentoEniFileInside convertirDocumentoAEni(
      TipoDocumentoConversionInside documentoConversion, byte[] bytesContenido, Boolean firmar,
      WSCredentialInside aplicacion) throws InsideWSException {
    try {
      return gInsideOperationWebService.convertirDocumentoAEni(documentoConversion, bytesContenido,
          firmar, aplicacion);
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }

  }

  @Override
  public ExpedienteEniFileInside convertirExpedienteAEni(
      TipoExpedienteConversionInside expedienteConversion, String contenidoFirma,
      WSCredentialInside aplicacion) throws InsideWSException {
    try {
      return gInsideOperationWebService.convertirExpedienteAEni(expedienteConversion,
          contenidoFirma, aplicacion);
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  private ObjetoDocumentoInside altaDocumento(TipoDocumentoAltaInside documentoAlta,
      ObjetoDocumentoInsideMetadatosEstadoElaboracionEnumeracion estadoElaboracion,
      String identificadorDocumentoOrigen, String aplicacion)
      throws InSideServiceException, InsideConverterException {
    logger.debug("Convirtiendo el documentoAlta a Inside");

    ObjetoDocumentoInside documentoInside =
        InsideConverterDocumento.documentoAltaXmlToInside(documentoAlta, infofirmaService, null);

    ObjetoDocumentoInsideMetadatosEstadoElaboracion estadoElaboracionCambioFormato =
        new ObjetoDocumentoInsideMetadatosEstadoElaboracion();
    if (identificadorDocumentoOrigen != null) {
      estadoElaboracionCambioFormato.setIdentificadorDocumentoOrigen(identificadorDocumentoOrigen);
    }
    estadoElaboracionCambioFormato.setValorEstadoElaboracion(estadoElaboracion);
    documentoInside.getMetadatos().setEstadoElaboracion(estadoElaboracionCambioFormato);
    documentoInside.getMetadatos().setVersionNTI(VERSION_NTI_DOC);

    TipoDocumentoInsideConMAdicionales docAdic;
    service.validateObjetoInsideIdentificador(documentoInside);
    if (documentoAlta.isFirmadoEnServidor()) {
      logger.debug("Se firmará en servidor el documento");
      documentoInside = service.firmaServidorDocumento(documentoInside, aplicacion);
    }
    docAdic = InsideConverterDocumento.documentoInsideToConMAdicionales(documentoInside, null);

    documentoInside =
        insideUtilService.validateDocumentImport(insideUtilService.generateDocXml(docAdic));
    insideUtilService.checkNoExistIdDocumentInside(documentoInside.getIdentificador(), true);

    if (documentoAlta.getAsociadoaExpediente() != null && StringUtils
        .hasText(documentoAlta.getAsociadoaExpediente().getIdentificadorExpediente())) {
      logger.debug("Llamando a altaDocumento asociado a Expediente "
          + documentoAlta.getAsociadoaExpediente().getIdentificadorExpediente());
      String carpeta = documentoAlta.getAsociadoaExpediente().getIdentificadorCarpeta() != null
          ? documentoAlta.getAsociadoaExpediente().getIdentificadorCarpeta()
          : "";
      documentoInside = service.altaDocumento(documentoInside,
          documentoAlta.getAsociadoaExpediente().getIdentificadorExpediente(), carpeta, aplicacion,
          false);
    } else {
      logger.debug("Llamando a altaDocumento");
      documentoInside = service.altaDocumento(documentoInside, aplicacion, false);
    }

    return documentoInside;

  }

  /**
   * 
   * @param expedienteEni
   * @throws InsideWSException
   */
  private void validaIdentificador(TipoExpediente expedienteEni) throws InsideWSException {

    if (expedienteEni == null) {
      throw new InsideWSException(InsideWsErrors.EXPEDIENTE_ENI_VACIO);
    }
    if (expedienteEni.getMetadatosExp() == null) {
      throw new InsideWSException(InsideWsErrors.METADATOS_EXPEDIENTE_ENI_VACIO);
    }

    if (expedienteEni.getMetadatosExp().getIdentificador() == null) {
      throw new InsideWSException(InsideWsErrors.IDENTIFICADOR_EXPEDIENTE_ENI_VACIO);
    }

  }

  private void checkVersionNTI(TipoMetadatos metadatosEni) throws InsideWSException {
    metadatosEni.setVersionNTI(checkVersionNTIExpediente(metadatosEni.getVersionNTI()));
  }

  private void checkVersionNTI(
      es.mpt.dsic.inside.xml.eni.documento.metadatos.TipoMetadatos metadatosEni)
      throws InsideWSException {
    metadatosEni.setVersionNTI(checkVersionNTIDocumento(metadatosEni.getVersionNTI()));
  }

  /**
   * Nos aseguramos que el valor del metadato versionNTI siempre sea la 1.0 (de momento no
   * soportamos otra cosa)
   * 
   * @param versionNTI
   * @return
   * @throws InsideWSException
   */
  private String checkVersionNTIDocumento(String versionNTI) throws InsideWSException {
    if (versionNTI != null && !versionNTI.equalsIgnoreCase(VERSION_NTI_DOC)) {
      throw new InsideWSException(InsideWsErrors.VERSION_NTI_DOC_10_ERRONEA);
    }
    return VERSION_NTI_DOC;
  }

  /**
   * Nos aseguramos que el valor del metadato versionNTI siempre sea la 1.0 (de momento no
   * soportamos otra cosa)
   * 
   * @param versionNTI
   * @return
   * @throws InsideWSException
   */
  private String checkVersionNTIExpediente(String versionNTI) throws InsideWSException {
    if (versionNTI != null && !versionNTI.equalsIgnoreCase(VERSION_NTI_EXP)) {
      throw new InsideWSException(InsideWsErrors.VERSION_NTI_EXP_10_ERRONEA);
    }
    return VERSION_NTI_EXP;
  }

  @Override
  @Secured("ROLE_MODIFICA_DOCUMENTO")
  public void eliminarDocumento(String identificador, String aplicacion) throws InsideWSException {
    try {
      logger.debug("Inicio eliminarDocumento:" + identificador);

      servicePermisos.checkPermisos(identificador, null, ObjetoDocumentoInside.class, aplicacion);
      service.deleteDocument(identificador);

      logger.debug("Fin eliminarDocumento.");
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_MODIFICA_EXPEDIENTE")
  public void eliminarExpediente(String identificador, String aplicacion) throws InsideWSException {
    try {
      logger.debug("Inicio eliminarExpediente:" + identificador);

      servicePermisos.checkPermisos(identificador, null, ObjetoExpedienteInside.class, aplicacion);
      service.deleteExpedient(identificador);

      logger.debug("Fin eliminarExpediente.");
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_LEER_EXPEDIENTE")
  public List<ExpedienteInsideInfo> expedientesAsociadoDocumento(String identificador,
      String aplicacion) throws InsideWSException {
    try {
      logger.debug("Inicio expedientesAsociadoDocumento:" + identificador);
      List<ExpedienteInsideInfo> retorno = new ArrayList<ExpedienteInsideInfo>();

      // Control Permisos
      servicePermisos.checkPermisos(identificador, null, ObjetoDocumentoInside.class, aplicacion);

      List<ObjetoExpedienteInside> expedientesInside =
          service.getExpedientesAsociadosDocumento(ObjetoDocumentoInside.class, identificador);

      for (ObjetoExpedienteInside objeto : expedientesInside) {
        retorno.add(InsideConverterExpedienteInfo.fromExpediente(objeto));
      }

      logger.debug("Fin expedientesAsociadoDocumento.");
      return retorno;
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_LEER_DOCUMENTO")
  public TipoResultadoVisualizacionDocumentoInside visualizarDocumento(String identificador,
      String aplicacion) throws InsideWSException {
    logger.debug("Inicio visualizarDocumento:" + identificador);
    TipoResultadoVisualizacionDocumentoInside resultado =
        new TipoResultadoVisualizacionDocumentoInside();
    try {
      // Control Permisos
      servicePermisos.checkPermisos(identificador, null, ObjetoDocumentoInside.class, aplicacion);

      ObjetoDocumentoInside documento = service.getDocumento(identificador);

      ResultadoVisualizacionDocumento vis = service.visualizaDocumentoInside(documento, null);

      resultado.setContenido(vis.getVisualizacion());
      resultado.setMime(vis.getMime());
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }

    logger.debug("Fin visualizarDocumento:" + identificador);
    return resultado;
  }

  @Override
  @Secured("ROLE_LEER_EXPEDIENTE")
  public RespuestaPdfExpediente visualizarExpediente(byte[] expedienteEni)
      throws InsideWSException {

    RespuestaPdfExpediente respuestaPdfExpediente;
    try {
      ObjetoExpedienteInside objExp = insideUtilService.validateExpedientImport(expedienteEni);

      if (objExp.getVisualizacionIndice() == null) {
        objExp = service.obtenerVisualizacionIndiceSiActivo(objExp, false, false, null, false, null,
            true);
      }

      TipoExpedienteInsideConMAdicionales tipoExpedienteMA =
          new TipoExpedienteInsideConMAdicionales();
      tipoExpedienteMA.setExpediente(InsideConverterExpediente.expedienteInsideToEni(objExp, null));
      tipoExpedienteMA.setMetadatosAdicionales(InsideConverterMetadatos
          .metadatosAdicionalesInsideToXml(objExp.getMetadatos().getMetadatosAdicionales()));

      respuestaPdfExpediente = new RespuestaPdfExpediente();
      respuestaPdfExpediente.setPdfExpediente(
          tipoExpedienteMA.getExpediente().getVisualizacionIndice().getValorBinario());

      return respuestaPdfExpediente;
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }

  }



  public Properties getProperties() {
    return properties;
  }

  public void setProperties(Properties properties) {
    this.properties = properties;
  }


  @Override
  @Secured("ROLE_LEER_EXPEDIENTE")
  public RespuestaPdfExpediente getPdfExpediente(byte[] expedienteEni) throws InsideWSException {

    RespuestaPdfExpediente respuestaPdfExpediente;
    try {
      ObjetoExpedienteInside objExp = insideUtilService.validateExpedientImport(expedienteEni);

      TipoExpedienteInsideConMAdicionales tipoExpedienteMA =
          new TipoExpedienteInsideConMAdicionales();
      tipoExpedienteMA.setExpediente(InsideConverterExpediente.expedienteInsideToEni(objExp, null));
      tipoExpedienteMA.setMetadatosAdicionales(InsideConverterMetadatos
          .metadatosAdicionalesInsideToXml(objExp.getMetadatos().getMetadatosAdicionales()));

      respuestaPdfExpediente = new RespuestaPdfExpediente();
      respuestaPdfExpediente.setPdfExpediente(
          tipoExpedienteMA.getExpediente().getVisualizacionIndice().getValorBinario());
      return respuestaPdfExpediente;
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }

  }

  @Override
  public RespuestaPdfExpediente getPdfExpedientePorId(String identificador, String version)
      throws InsideWSException {

    RespuestaPdfExpediente respuestaPdfExpediente;
    try {

      ObjetoExpedienteInside objExp;
      if (version != null && !"".equals(version)) {
        objExp = service.getExpediente(identificador, Integer.parseInt(version), true);
      } else {
        objExp = service.getExpediente(identificador, true);
      }

      TipoExpedienteInsideConMAdicionales tipoExpedienteMA =
          new TipoExpedienteInsideConMAdicionales();
      tipoExpedienteMA.setExpediente(InsideConverterExpediente.expedienteInsideToEni(objExp, null));
      tipoExpedienteMA.setMetadatosAdicionales(InsideConverterMetadatos
          .metadatosAdicionalesInsideToXml(objExp.getMetadatos().getMetadatosAdicionales()));

      respuestaPdfExpediente = new RespuestaPdfExpediente();
      respuestaPdfExpediente.setPdfExpediente(
          tipoExpedienteMA.getExpediente().getVisualizacionIndice().getValorBinario());
      return respuestaPdfExpediente;
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_MODIFICA_EXPEDIENTE")
  public TokenExpediente getCredencialesAccesoExpediente(String aplicacion, String identificador,
      String emails, String asuntoMail, String dir3, String nifs, String fechaCaducidad)
      throws InsideWSException {

    TokenExpediente tokenExpediente = new TokenExpediente();
    ObjetoInsideUsuario usuario = new ObjetoInsideUsuario();
    ObjetoInside<?> expediente = null;
    String csv = "";
    String uuid = "";
    boolean hayCorreo = true;
    String resultadoEnvioCorreo = "";
    String fichero = "";

    try {
      // control fecha
      Date objetoFechaCaducidad = null;
      if (org.apache.commons.lang.StringUtils.isNotEmpty(fechaCaducidad)) {
        if (fechaCaducidad.matches(FORMATO_FECHA))
          objetoFechaCaducidad = InsideWSUtils.stringToJavaDateToken(fechaCaducidad);
        else
          throw new InsideWSException(InsideWsErrors.FECHA_MALFORMATO, null, fechaCaducidad);
      }
      // control NIF y DIR3
      if (nifs == null)
        nifs = "";
      if (dir3 == null)
        dir3 = "";
      insideUtilService.controlNIFyDIR3(nifs, dir3);

      // control emails
      if (emails != null && !"".equals(emails.trim())) {
        insideUtilService.controlMail(emails);
      } else {
        hayCorreo = false;
      }

      // Control Permisos
      servicePermisos.checkPermisos(identificador, null, ObjetoExpedienteInside.class, aplicacion);

      // Obtencion de credenciales
      expediente = service.getExpediente(identificador, null, false);
      usuario.setNif(nifFake);
      csv = csvService.generarCSV((ObjetoExpedienteInside) expediente);
      uuid = UUID.randomUUID().toString();

      ObjetoExpedienteToken objetoExpedienteToken = new ObjetoExpedienteToken(usuario,
          identificador, csv, uuid, ((ObjetoExpedienteInside) expediente).getVersion().getVersion(),
          objetoFechaCaducidad, dir3, asuntoMail, nifs, emails);

      // devolver fichero token
      fichero = insideUtilService.tokenXmlBase64(objetoExpedienteToken);

      // guardar en base de datos
      insideService.saveToken(objetoExpedienteToken);

      // envio correo
      if (hayCorreo) {
        try {
          resultadoEnvioCorreo =
              mailService.sendToken(objetoExpedienteToken, TOKENFILE, DIRECCION_EMAIL);
        } catch (Exception e) {
          logger.error(
              "expedienteGenerarTokenDescarga: Error en el envio de correo de Credenciales de Acceso"
                  + e);
          throw InsideExceptionConverter.convertToException(e);
        }
      }
    } catch (InSideServiceException e) {
      throw InsideExceptionConverter.convertToException(e);
    } catch (ParseException e) {
      logger.error("Se he producido un error al generar el Credencial de Acceso de descarga:"
          + e.getMessage());
      throw InsideExceptionConverter.convertToException(e);
    }

    tokenExpediente.setIdentificador(identificador);
    tokenExpediente.setCsv(csv);
    tokenExpediente.setUuid(uuid);
    tokenExpediente.setResultadoEnvioCorreo(resultadoEnvioCorreo);
    tokenExpediente.setFichero(fichero);
    return tokenExpediente;
  }

}
