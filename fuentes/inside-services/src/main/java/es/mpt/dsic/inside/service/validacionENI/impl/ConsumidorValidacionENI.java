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

package es.mpt.dsic.inside.service.validacionENI.impl;

import java.io.ByteArrayInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.activation.DataHandler;
import javax.annotation.PostConstruct;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceException;
import org.apache.axiom.attachments.ByteArrayDataSource;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideUnidad;
import es.mpt.dsic.inside.model.objetos.enivalidation.OpcionValidacionDocumento;
import es.mpt.dsic.inside.model.objetos.enivalidation.OpcionValidacionExpediente;
import es.mpt.dsic.inside.model.objetos.enivalidation.ResultadoValidacionDocumento;
import es.mpt.dsic.inside.model.objetos.enivalidation.ResultadoValidacionExpediente;
import es.mpt.dsic.inside.service.sia.impl.ConsumidorSIA;
import es.mpt.dsic.inside.service.store.InsideServiceStore;
import es.mpt.dsic.inside.service.store.exception.InsideServiceStoreException;
import es.mpt.dsic.inside.service.validacionENIMtom.model.ApplicationLogin;
import es.mpt.dsic.inside.service.validacionENI.exception.InsideServiceValidacionException;
import es.mpt.dsic.inside.service.validacionENIMtom.model.Detalle;
import es.mpt.dsic.inside.service.validacionENIMtom.model.DocumentoEntradaMtom;
import es.mpt.dsic.inside.service.validacionENIMtom.model.EeUtilValidacionENIServiceMtom;
import es.mpt.dsic.inside.service.validacionENIMtom.model.EeUtilValidacionENIServiceMtomImplService;
import es.mpt.dsic.inside.service.validacionENIMtom.model.RespuestaValidacionENI;
import es.mpt.dsic.inside.service.validacionENIMtom.model.Validaciones;
import es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.TipoOpcionValidacionExpediente;
import es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.resultados.TipoResultadoValidacionDetalleExpedienteInside;
import es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.resultados.TipoResultadoValidacionExpedienteInside;

public class ConsumidorValidacionENI {
	
  // CARM ### v2.0.8.1
  @Value("${ws.validacionENI.activo:S}")
  private String wsValidacionEniActivo;
  private boolean activo;
  private static String activoCadena = "S";
  // CARM 2.0.8.1 ###


  private final String XPATH_RUTA_ORGANOS_DOC = "documento/metadatos/Organo";
  private final String XPATH_RUTA_ORGANOS_EXP = "expediente/metadatosExp/Organo";
  private final String XPATH_RUTA_IDENTIFICADOR_DOC = "documento/metadatos/Identificador";
  private final String XPATH_RUTA_IDENTIFICADOR_EXP = "expediente/metadatosExp/Identificador";
  private final String XPATH_RUTA_CLASIFICACION_EXP = "expediente/metadatosExp/Clasificacion";
  private final String XPATH_RUTA_FECHA_APERTURA_EXP =
      "expediente/metadatosExp/FechaAperturaExpediente";

  private EeUtilValidacionENIServiceMtom scMtom;
  private ConsumidorSIA consumidorSIA;

  @Autowired
  private InsideServiceStore insideStore;

  private String user;
  private String password;
  private String schemasDir;
  private String url;
  private ApplicationLogin aplicacionInfo;


  private String identificadorObjeto;

  protected static final Log logger = LogFactory.getLog(ConsumidorValidacionENI.class);
  
  // CARM ### v2.0.8.1
  public boolean isActivo() {
    return this.activo;
  }
  // CARM 2.0.8.1 ###

//CARM ### v2.0.8.1
  @PostConstruct
//CARM 2.0.8.1 ###
  public void configure() {
    if (wsValidacionEniActivo.equals(activoCadena)) {
      EeUtilValidacionENIServiceMtomImplService servicioMtom = null;
      try {
        servicioMtom = new EeUtilValidacionENIServiceMtomImplService(new URL(url));

        scMtom = servicioMtom.getPort(EeUtilValidacionENIServiceMtom.class);

        disableChunking(ClientProxy.getClient(scMtom));

        String endpointURLMtom = url;
        BindingProvider bpMtom = (BindingProvider) scMtom;
        bpMtom.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointURLMtom);

        aplicacionInfo = new ApplicationLogin();
        aplicacionInfo.setIdaplicacion(user);
        aplicacionInfo.setPassword(password);
        activo = true;
      } catch (MalformedURLException | WebServiceException e) {
        logger.error("No se ha podido conectar al servicio :", e);
      }
   // CARM ### v2.0.8.1
    } else {
      logger.info("El WS de VALIDACIÓN no está activo");
      activo = false;
    }
    // CARM 2.0.8.1 ###
  }

  private void disableChunking(Client client) {
    HTTPConduit httpConduit = (HTTPConduit) client.getConduit();
    HTTPClientPolicy policy = httpConduit.getClient();
    policy.setAllowChunking(false);
    policy.setChunkingThreshold(0);
    logger.debug("AllowChunking:" + policy.isAllowChunking());
    logger.debug("ChunkingThreshold:" + policy.getChunkingThreshold());
  }

  public List<ResultadoValidacionDocumento> validaDocumentoENI(byte[] docuemnto,
      Validaciones validaciones) throws InsideServiceValidacionException {

	// CARM ### v2.0.8.1
    if (!this.isActivo()) {
      throw new InsideServiceValidacionException("El WS de VALIDACION no se encuentra activo");
    }
    // CARM 2.0.8.1 ###
    logger.debug("Inicio validaDocumentoENI");

    DocumentoEntradaMtom documentoENI = new DocumentoEntradaMtom();
    ByteArrayDataSource dataSource = new ByteArrayDataSource(docuemnto);
    documentoENI.setContenido(new DataHandler(dataSource));
    RespuestaValidacionENI respuesta;
    List<Detalle> listaDetalles = new ArrayList<Detalle>();
    String dataDocumentoXml = null;
    try {
      byte[] documento = IOUtils.toByteArray(documentoENI.getContenido().getInputStream());
      dataDocumentoXml = XMLUtil.obtenerDocumentoENIXML(XMLUtil.decodeUTF8(documento));

    } catch (Exception e) {
      respuesta = new RespuestaValidacionENI();
      respuesta.setGlobal(GlobalValidacionENICodigosRespuestaValidacion.G_XXX_CODIGO + " - "
          + GlobalValidacionENICodigosRespuestaValidacion.G_XXX_DETALLE);
      respuesta.setDetalle(listaDetalles);
    }

    if (validaciones.isValidaSchema()) {
      try {
        identificadorObjeto =
            XMLUtil.getvalorNodoDatosXML(dataDocumentoXml, this.XPATH_RUTA_IDENTIFICADOR_DOC);
        validarSchema(dataDocumentoXml.getBytes(XMLUtil.UTF8_CHARSET));

      } catch (SAXException e) {

        Detalle detalleValidacion =
            getDetalleValidacion(DocumentoENICodigosRespuestaValidacion.D_E_999_CODIGO,
                DocumentoENICodigosRespuestaValidacion.D_E_999_DETALLE);
        listaDetalles.add(detalleValidacion);

      } catch (Exception e) {

        Detalle detalleValidacion =
            getDetalleValidacion(GlobalValidacionENICodigosRespuestaValidacion.G_EXX_CODIGO,
                GlobalValidacionENICodigosRespuestaValidacion.G_XXX_DETALLE);
        listaDetalles.add(detalleValidacion);

      }

      listaDetalles.add(getDetalleValidacion(DocumentoENICodigosRespuestaValidacion.D_E_000_CODIGO,
          DocumentoENICodigosRespuestaValidacion.D_E_000_DETALLE));
    }

    if (validaciones.isValidaDir3()) {

      try {
        identificadorObjeto =
            XMLUtil.getvalorNodoDatosXML(dataDocumentoXml, this.XPATH_RUTA_IDENTIFICADOR_DOC);

        List<String> listaOrganos = XMLUtil.getContentNodeList(
            dataDocumentoXml.getBytes(XMLUtil.UTF8_CHARSET), this.XPATH_RUTA_ORGANOS_DOC);
        String dir3Erroneo = validateOrganos(listaOrganos);
        if (!dir3Erroneo.equals("")) {
          listaDetalles
              .add(getDetalleValidacion(DocumentoENICodigosRespuestaValidacion.D_D_999_CODIGO,
                  DocumentoENICodigosRespuestaValidacion.D_D_999_DETALLE + " -- (" + dir3Erroneo
                      + ")"));
        } else {
          listaDetalles
              .add(getDetalleValidacion(DocumentoENICodigosRespuestaValidacion.D_D_000_CODIGO,
                  DocumentoENICodigosRespuestaValidacion.D_D_000_DETALLE));
        }
      } catch (Exception e) {
        listaDetalles
            .add(getDetalleValidacion(GlobalValidacionENICodigosRespuestaValidacion.G_DXX_CODIGO,
                GlobalValidacionENICodigosRespuestaValidacion.G_XXX_DETALLE));
      }

    }

    if (validaciones.isValidaFirma()) {
      if (isActivo()) {
        RespuestaValidacionENI respuestValidarFirma =
            scMtom.validarFirmaDocumentoENI(aplicacionInfo, documentoENI);
        listaDetalles.addAll(respuestValidarFirma.getDetalle());
      } else {
        logger.error("El servicio no se encuentra activo");
      }

    }

    respuesta = getRespuestaGlobal(listaDetalles);

    logger.debug("Fin validaDocumentoENI");

    return formatValidacionENIDocumentoInside(respuesta);

  }

  private void validarSchema(byte[] xmlBytes) throws SAXException, Exception {
    ByteArrayInputStream bin = null;
    try {
      bin = new ByteArrayInputStream(xmlBytes);

      XMLReader parser = XMLUtil.createParserForValidation(XMLUtil.getSchemasSources(schemasDir));

      InputSource xmlSource = new InputSource(bin);
      parser.parse(xmlSource);
    } catch (Exception e) {
      throw e;
    } finally {
      if (bin != null) {
        bin.close();
      }
    }
  }

  private Detalle getDetalleValidacion(String codigo, String Detalle) {
    Detalle detalleValidacion = new Detalle();
    detalleValidacion.setIdObjeto(identificadorObjeto);
    detalleValidacion.setCodigoRespuesta(codigo);
    detalleValidacion.setDetalleRespuesta(Detalle);
    return detalleValidacion;

  }

  private String validateOrganos(List<String> organos) {
    StringBuffer tmpBuff = new StringBuffer("");
    for (String organo : organos) {
      ObjetoInsideUnidad unidadOrganica;
      try {
        unidadOrganica = (ObjetoInsideUnidad) insideStore.getUnidadOrganica(organo);

        if (unidadOrganica == null) {
          if (!tmpBuff.toString().equals("")) {
            tmpBuff.append(",");
          }
          tmpBuff.append(organo);
        }
      } catch (InsideServiceStoreException e) {
      }
    }

    return tmpBuff.toString();
  }

  public List<ResultadoValidacionDocumento> validaFirmaDocumentoENI(byte[] docuemnto) 
	  throws InsideServiceValidacionException {
    // CARM ### v2.0.8.1
    if (!this.isActivo()) {
      throw new InsideServiceValidacionException("El WS de VALIDACION no se encuentra activo");
    }
    // CARM 2.0.8.1 ###
    if (isActivo()) {
      logger.debug("Inicio validaFirmaDocumentoENI");

      DocumentoEntradaMtom docEntrada = new DocumentoEntradaMtom();
      ByteArrayDataSource dataSource = new ByteArrayDataSource(docuemnto);
      docEntrada.setContenido(new DataHandler(dataSource));

      RespuestaValidacionENI respuesta =
          scMtom.validarFirmaDocumentoENI(aplicacionInfo, docEntrada);

      logger.debug("Fin validaFirmaDocumentoENI");
      return formatValidacionENIDocumentoInside(respuesta);
    } else {
      logger.error("El servicio no se encuentra activo");
      return null;
    }
  }

  public List<ResultadoValidacionExpediente> validaExpedienteENI(byte[] expediente,
      Validaciones validaciones) throws InsideServiceValidacionException{
	// CARM ### v2.0.8.1
    if (!this.isActivo()) {
      throw new InsideServiceValidacionException("El WS de VALIDACION no se encuentra activo");
    }
    // CARM 2.0.8.1 ###
    logger.debug("Inicio validaExpedienteENI");

    DocumentoEntradaMtom expEntrada = new DocumentoEntradaMtom();
    ByteArrayDataSource dataSource = new ByteArrayDataSource(expediente);
    expEntrada.setContenido(new DataHandler(dataSource));

    RespuestaValidacionENI respuesta = null;

    List<Detalle> listaDetalles = new ArrayList<Detalle>();

    String dataExpedienteENIXml = null;

    byte[] expedienteRecibidoOriginalSinTocar = null;
    try {
      expedienteRecibidoOriginalSinTocar =
          IOUtils.toByteArray(expEntrada.getContenido().getInputStream());
      dataExpedienteENIXml =
          XMLUtil.obtenerExpedienteENIXML(XMLUtil.decodeUTF8(expedienteRecibidoOriginalSinTocar));

    } catch (Exception e) {
      respuesta = new RespuestaValidacionENI();
      respuesta.setGlobal(GlobalValidacionENICodigosRespuestaValidacion.G_XXX_CODIGO + " - "
          + GlobalValidacionENICodigosRespuestaValidacion.G_XXX_DETALLE);
      respuesta.setDetalle(listaDetalles);
    }

    if (validaciones.isValidaSchema()) {
      try {
        identificadorObjeto =
            XMLUtil.getvalorNodoDatosXML(dataExpedienteENIXml, this.XPATH_RUTA_IDENTIFICADOR_EXP);
        validarSchema(dataExpedienteENIXml.getBytes(XMLUtil.UTF8_CHARSET));

      } catch (SAXException e) {

        Detalle detalleValidacion =
            getDetalleValidacion(ExpedienteENICodigosRespuestaValidacion.E_E_999_CODIGO,
                ExpedienteENICodigosRespuestaValidacion.E_E_999_DETALLE);
        listaDetalles.add(detalleValidacion);

      } catch (Exception e) {

        Detalle detalleValidacion =
            getDetalleValidacion(GlobalValidacionENICodigosRespuestaValidacion.G_EXX_CODIGO,
                GlobalValidacionENICodigosRespuestaValidacion.G_XXX_DETALLE);
        listaDetalles.add(detalleValidacion);

      }

      listaDetalles.add(getDetalleValidacion(ExpedienteENICodigosRespuestaValidacion.E_E_000_CODIGO,
          ExpedienteENICodigosRespuestaValidacion.E_E_000_DETALLE));
    }

    if (validaciones.isValidaDir3()) {
      try {
        identificadorObjeto =
            XMLUtil.getvalorNodoDatosXML(dataExpedienteENIXml, this.XPATH_RUTA_IDENTIFICADOR_EXP);

        List<String> listaOrganos = XMLUtil.getContentNodeList(
            dataExpedienteENIXml.getBytes(XMLUtil.UTF8_CHARSET), this.XPATH_RUTA_ORGANOS_EXP);
        String dir3Erroneo = validateOrganos(listaOrganos);
        if (!dir3Erroneo.equals("")) {
          listaDetalles
              .add(getDetalleValidacion(ExpedienteENICodigosRespuestaValidacion.E_D_999_CODIGO,
                  ExpedienteENICodigosRespuestaValidacion.E_D_999_DETALLE + " -- (" + dir3Erroneo
                      + ")"));
        } else {
          listaDetalles
              .add(getDetalleValidacion(ExpedienteENICodigosRespuestaValidacion.E_D_000_CODIGO,
                  ExpedienteENICodigosRespuestaValidacion.E_D_000_DETALLE));
        }
      } catch (Exception e) {
        listaDetalles
            .add(getDetalleValidacion(GlobalValidacionENICodigosRespuestaValidacion.G_DXX_CODIGO,
                GlobalValidacionENICodigosRespuestaValidacion.G_XXX_DETALLE));
      }
    }

    if (validaciones.isValidaSIA()) {
      // VALIDACION 4 VALIDACIONCODIGOSIA
      // Detalle detalleSIA = eeutilEniValidationENIService
      // .validarCodigoSIAExpedienteEniFile(dataExpedienteENIXml);
      try {
        identificadorObjeto =
            XMLUtil.getvalorNodoDatosXML(dataExpedienteENIXml, this.XPATH_RUTA_IDENTIFICADOR_EXP);

        String clasificacion_SIA = XMLUtil.getContentNode(
            dataExpedienteENIXml.getBytes(XMLUtil.UTF8_CHARSET), XPATH_RUTA_CLASIFICACION_EXP);
        String fechaAperturaExpediente = XMLUtil.getContentNode(
            dataExpedienteENIXml.getBytes(XMLUtil.UTF8_CHARSET), XPATH_RUTA_FECHA_APERTURA_EXP);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date feApExFormateada = sdf.parse(fechaAperturaExpediente);
        Calendar calendario = GregorianCalendar.getInstance();
        calendario.setTime(feApExFormateada);

        boolean correcto = validateClasificacionSIA(clasificacion_SIA, calendario);
        if (!correcto) {
          listaDetalles
              .add(getDetalleValidacion(ExpedienteENICodigosRespuestaValidacion.E_S_999_CODIGO,
                  ExpedienteENICodigosRespuestaValidacion.E_S_999_DETALLE + " -- ("
                      + clasificacion_SIA + ")"));
        } else {
          listaDetalles
              .add(getDetalleValidacion(ExpedienteENICodigosRespuestaValidacion.E_S_000_CODIGO,
                  ExpedienteENICodigosRespuestaValidacion.E_S_000_DETALLE));
        }

      } catch (Exception e) {
        listaDetalles
            .add(getDetalleValidacion(GlobalValidacionENICodigosRespuestaValidacion.G_SXX_CODIGO,
                GlobalValidacionENICodigosRespuestaValidacion.G_XXX_DETALLE));
      }

    }

    if (validaciones.isValidaFirma()) {
      if (isActivo()) {
        RespuestaValidacionENI detalleFirma =
            scMtom.validarFirmaExpedienteENI(aplicacionInfo, expEntrada);
        listaDetalles.addAll(detalleFirma.getDetalle());
      } else {
        logger.error("El servicio no se encuentra activo");
      }
    }

    logger.debug("Fin validaExpedienteENI");
    return formatValidacionENIExpedienteInside(getRespuestaGlobal(listaDetalles));

  }

  private boolean validateClasificacionSIA(String clasificacionSIA, Calendar fecha) {
    String clasificacionPattern = ".*_PRO_.*";
    boolean retorno = true;
    Integer anio = fecha.get(Calendar.YEAR);
    boolean exists = true;

    try {
      exists = consumidorSIA.existClasificacion(clasificacionSIA, anio.toString());
    } catch (Exception e) {
      retorno = false;
      logger.error("Error en la llamada al servicio de SIA para validar");
    }

    try {
      if (!exists) {
        logger.debug("Valor de clasificación no corresponde en SIA");
        Pattern pattern = Pattern.compile(clasificacionPattern);
        Matcher matcher = pattern.matcher(clasificacionSIA);
        if (!matcher.matches()) {
          retorno = false;
        } else {

          StringTokenizer tmpToken = new StringTokenizer(clasificacionSIA, "_PRO_");
          String data = (String) tmpToken.nextElement();
          ObjetoInsideUnidad unidadOrganica = insideStore.getUnidadOrganica(data);

          if (unidadOrganica == null) {
            retorno = false;
          }
        }
      }
    } catch (Exception e) {
      retorno = false;
      logger.error("Error en la busqueda de SIA en tabla para validarlo");
    }

    return retorno;

  }

  public List<ResultadoValidacionExpediente> validaFirmaExpedienteENI(byte[] expediente) throws InsideServiceValidacionException{
	// CARM ### v2.8.7.1
    if (!this.isActivo()) {
      throw new InsideServiceValidacionException("El WS de VALIDACION no se encuentra activo");
    }
    // CARM 2.0.8.1 ###
    if (isActivo()) {
      logger.debug("Inicio validaFirmaExpedienteENI");

      DocumentoEntradaMtom expEntrada = new DocumentoEntradaMtom();
      ByteArrayDataSource dataSource = new ByteArrayDataSource(expediente);
      expEntrada.setContenido(new DataHandler(dataSource));

      RespuestaValidacionENI respuesta =
          scMtom.validarFirmaExpedienteENI(aplicacionInfo, expEntrada);

      logger.debug("Fin validaFirmaExpedienteENI");
      return formatValidacionENIExpedienteInside(respuesta);
    } else {
      logger.error("El servicio no se encuentra activo");
      return formatValidacionENIExpedienteInside(null);
    }
  }

  public static TipoResultadoValidacionExpedienteInside listResultadoValidacionExpedienteToTipoResultadoValidacionExpedienteInside(
      List<Detalle> resultados) {

    TipoResultadoValidacionExpedienteInside res = new TipoResultadoValidacionExpedienteInside();

    if (resultados != null) {
      for (Detalle elemento : resultados) {
        TipoResultadoValidacionDetalleExpedienteInside detalle =
            new TipoResultadoValidacionDetalleExpedienteInside();
        detalle
            .setResultadoValidacion(elemento.getCodigoRespuesta().contains("000]") ? true : false);
        detalle.setDetalleValidacion(elemento.getDetalleRespuesta());
        detalle.setTipoValidacion(TipoOpcionValidacionExpediente.TOVE_01);
        res.getValidacionDetalle().add(detalle);
      }
    }
    return res;
  }

  private List<ResultadoValidacionExpediente> formatValidacionENIExpedienteInside(
      RespuestaValidacionENI respuestaWSValidacionEN) {
    List<ResultadoValidacionExpediente> resultadosValidacion =
        new ArrayList<ResultadoValidacionExpediente>();

    if (respuestaWSValidacionEN != null) {
      for (Detalle elemento : respuestaWSValidacionEN.getDetalle()) {
        ResultadoValidacionExpediente rve = new ResultadoValidacionExpediente();
        rve.setMensaje(elemento.getDetalleRespuesta());
        rve.setValido(elemento.getCodigoRespuesta().contains("000]") ? true : false);

        if (elemento.getCodigoRespuesta().contains("[E.E.")) {
          rve.setTipoValidacion(OpcionValidacionExpediente.TOVE_01);
        } else if (elemento.getCodigoRespuesta().contains("[E.D.")) {
          rve.setTipoValidacion(OpcionValidacionExpediente.TOVE_02);
        } else if (elemento.getCodigoRespuesta().contains("[E.S.")) {
          rve.setTipoValidacion(OpcionValidacionExpediente.TOVE_03);
        } else if (elemento.getCodigoRespuesta().contains("[E.F.")) {
          rve.setTipoValidacion(OpcionValidacionExpediente.TOVE_04);
        }

        if (elemento.getCodigoRespuesta().contains("[G.E")) {
          rve.setTipoValidacion(OpcionValidacionExpediente.TOVE_01);
        } else if (elemento.getCodigoRespuesta().contains("[G.D")) {
          rve.setTipoValidacion(OpcionValidacionExpediente.TOVE_02);
        } else if (elemento.getCodigoRespuesta().contains("[G.S")) {
          rve.setTipoValidacion(OpcionValidacionExpediente.TOVE_03);
        } else if (elemento.getCodigoRespuesta().contains("[G.F")) {
          rve.setTipoValidacion(OpcionValidacionExpediente.TOVE_04);
        }

        resultadosValidacion.add(rve);
      }
    }

    return resultadosValidacion;
  }

  private List<ResultadoValidacionDocumento> formatValidacionENIDocumentoInside(
      RespuestaValidacionENI respuestaWSValidacionEN) {
    List<ResultadoValidacionDocumento> resultadosValidacion =
        new ArrayList<ResultadoValidacionDocumento>();

    if (respuestaWSValidacionEN != null) {
      for (Detalle elemento : respuestaWSValidacionEN.getDetalle()) {
        ResultadoValidacionDocumento rve = new ResultadoValidacionDocumento();
        rve.setMensaje(elemento.getDetalleRespuesta());
        rve.setValido(elemento.getCodigoRespuesta().contains("000]") ? true : false);

        if (elemento.getCodigoRespuesta().contains("[D.E.")) {
          rve.setTipoValidacion(OpcionValidacionDocumento.TOVD_01);
        } else if (elemento.getCodigoRespuesta().contains("[D.D.")) {
          rve.setTipoValidacion(OpcionValidacionDocumento.TOVD_02);
        } else if (elemento.getCodigoRespuesta().contains("[D.F.")) {
          rve.setTipoValidacion(OpcionValidacionDocumento.TOVD_03);
        }

        // por si es error generico
        if (elemento.getCodigoRespuesta().contains("[G.E")) {
          rve.setTipoValidacion(OpcionValidacionDocumento.TOVD_01);
        } else if (elemento.getCodigoRespuesta().contains("[G.D")) {
          rve.setTipoValidacion(OpcionValidacionDocumento.TOVD_02);
        } else if (elemento.getCodigoRespuesta().contains("[G.F")) {
          rve.setTipoValidacion(OpcionValidacionDocumento.TOVD_03);
        }

        resultadosValidacion.add(rve);
      }
    }

    return resultadosValidacion;

  }

  public String getUser() {
    return user;
  }

  public EeUtilValidacionENIServiceMtom getScMtom() {
    return scMtom;
  }

  public void setScMtom(EeUtilValidacionENIServiceMtom scMtom) {
    this.scMtom = scMtom;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getSchemasDir() {
    return schemasDir;
  }

  public void setSchemasDir(String schemasDir) {
    this.schemasDir = schemasDir;
  }

  public ConsumidorSIA getConsumidorSIA() {
    return consumidorSIA;
  }

  public void setConsumidorSIA(ConsumidorSIA consumidorSIA) {
    this.consumidorSIA = consumidorSIA;
  }

  private RespuestaValidacionENI getRespuestaGlobal(List<Detalle> listaDetalles) {

    RespuestaValidacionENI respuesta = new RespuestaValidacionENI();
    respuesta.setGlobal(GlobalValidacionENICodigosRespuestaValidacion.G_000_CODIGO + " - "
        + GlobalValidacionENICodigosRespuestaValidacion.G_000_DETALLE);
    respuesta.setDetalle(listaDetalles);

    if (listaDetalles.isEmpty()) {
      Detalle detalleVaciasValidaciones = new Detalle();
      detalleVaciasValidaciones
          .setCodigoRespuesta(GlobalValidacionENICodigosRespuestaValidacion.G_999_CODIGO);
      detalleVaciasValidaciones
          .setDetalleRespuesta(GlobalValidacionENICodigosRespuestaValidacion.G_999_DETALLE);
      detalleVaciasValidaciones.setIdObjeto(
          GlobalValidacionENICodigosRespuestaValidacion.G_ZZZ_ID_OBJETO_ERROR_VALIDACIONES);
      respuesta.getDetalle().add(detalleVaciasValidaciones);
      respuesta.setGlobal(GlobalValidacionENICodigosRespuestaValidacion.G_999_CODIGO + " - "
          + GlobalValidacionENICodigosRespuestaValidacion.G_999_DETALLE);

    } else {
      for (Detalle detalle : listaDetalles) {

        if (detalle.getCodigoRespuesta().endsWith("999]")) {
          respuesta.setGlobal(GlobalValidacionENICodigosRespuestaValidacion.G_999_CODIGO + " - "
              + GlobalValidacionENICodigosRespuestaValidacion.G_999_DETALLE);
          break;
        }
      }
    }

    return respuesta;
  }

}
