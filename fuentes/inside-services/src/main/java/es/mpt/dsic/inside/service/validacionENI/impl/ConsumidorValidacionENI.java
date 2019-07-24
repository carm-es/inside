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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.activation.DataHandler;
import javax.annotation.PostConstruct;
import javax.jws.WebParam;
import javax.xml.ws.BindingProvider;
import org.apache.axiom.attachments.ByteArrayDataSource;
import org.apache.axiom.attachments.utils.DataHandlerUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.springframework.beans.factory.annotation.Value;
import es.mpt.dsic.inside.model.objetos.enivalidation.OpcionValidacionDocumento;
import es.mpt.dsic.inside.model.objetos.enivalidation.OpcionValidacionExpediente;
import es.mpt.dsic.inside.model.objetos.enivalidation.ResultadoValidacionDocumento;
import es.mpt.dsic.inside.model.objetos.enivalidation.ResultadoValidacionExpediente;
import es.mpt.dsic.inside.service.validacionENI.exception.InsideServiceValidacionException;
import es.mpt.dsic.inside.service.validacionENIMtom.model.ApplicationLogin;
import es.mpt.dsic.inside.service.validacionENIMtom.model.Detalle;
import es.mpt.dsic.inside.service.validacionENIMtom.model.EeUtilValidacionENIServiceMtom;
import es.mpt.dsic.inside.service.validacionENIMtom.model.EeUtilValidacionENIServiceMtomImplService;
import es.mpt.dsic.inside.service.validacionENIMtom.model.RespuestaValidacionENI;
import es.mpt.dsic.inside.service.validacionENIMtom.model.DocumentoEntradaMtom;
import es.mpt.dsic.inside.service.validacionENIMtom.model.Validaciones;
import es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.TipoOpcionValidacionExpediente;
import es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.resultados.TipoResultadoValidacionDetalleExpedienteInside;
import es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.resultados.TipoResultadoValidacionExpedienteInside;



public class ConsumidorValidacionENI {

  // CARM ### v2.0.7.1
  @Value("${ws.validacionENI.activo:S}")
  private String wsValidacionEniActivo;
  private boolean activo;
  private static String activoCadena = "S";
  // CARM 2.0.7.1 ###

  // private EeUtilValidacionENIService sc;

  private EeUtilValidacionENIServiceMtom scMtom;

  private String user;
  private String password;
  private String versionSchemas;
  private String url;
  private ApplicationLogin aplicacionInfo;

  protected static final Log logger = LogFactory.getLog(ConsumidorValidacionENI.class);

  // CARM ### v2.0.7.1
  public boolean isActivo() {
    return this.activo;
  }
  // CARM 2.0.7.1 ###

  @PostConstruct
  public void configure() {
    // EeUtilValidacionENIServiceImplService servicio = new EeUtilValidacionENIServiceImplService();
    // sc = servicio.getPort(EeUtilValidacionENIService.class);
    // String endpointURL = url;
    // BindingProvider bp = (BindingProvider)sc;
    // bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointURL);

    // CARM ### v2.0.7.1
    if (wsValidacionEniActivo.equals(activoCadena)) {
      activo = true;
      logger.debug(String.format("El WS de VALIDACION se encuentra en %s", url));
      // CARM 2.0.7.1 ###

      EeUtilValidacionENIServiceMtomImplService servicioMtom = null;
      try {
        servicioMtom = new EeUtilValidacionENIServiceMtomImplService(new URL(url));
      } catch (MalformedURLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      scMtom = servicioMtom.getPort(EeUtilValidacionENIServiceMtom.class);

      disableChunking(ClientProxy.getClient(scMtom));

      String endpointURLMtom = url;
      BindingProvider bpMtom = (BindingProvider) scMtom;
      bpMtom.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointURLMtom);

      // CARM ### v2.0.7.1
    } else {
      logger.info("El WS de VALIDACIÓN no está activo");
      activo = false;
    }
    // CARM 2.0.7.1 ###

    aplicacionInfo = new ApplicationLogin();
    aplicacionInfo.setIdaplicacion(user);
    aplicacionInfo.setPassword(password);

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

    // CARM ### v2.0.7.1
    if (!this.isActivo()) {
      throw new InsideServiceValidacionException("El WS de VALIDACION no se encuentra activo");
    }
    // CARM 2.0.7.1 ###

    logger.debug("Inicio validaDocumentoENI");

    DocumentoEntradaMtom docEntrada = new DocumentoEntradaMtom();
    ByteArrayDataSource dataSource = new ByteArrayDataSource(docuemnto);
    docEntrada.setContenido(new DataHandler(dataSource));

    RespuestaValidacionENI respuesta =
        scMtom.validarDocumentoENI(aplicacionInfo, docEntrada, versionSchemas, validaciones);

    logger.debug("Fin validaDocumentoENI");

    return formatValidacionENIDocumentoInside(respuesta);
  }



  public List<ResultadoValidacionDocumento> validaFirmaDocumentoENI(byte[] docuemnto)
      throws InsideServiceValidacionException {
    // CARM ### v2.0.7.1
    if (!this.isActivo()) {
      throw new InsideServiceValidacionException("El WS de VALIDACION no se encuentra activo");
    }
    // CARM 2.0.7.1 ###
    logger.debug("Inicio validaFirmaDocumentoENI");

    DocumentoEntradaMtom docEntrada = new DocumentoEntradaMtom();
    ByteArrayDataSource dataSource = new ByteArrayDataSource(docuemnto);
    docEntrada.setContenido(new DataHandler(dataSource));

    RespuestaValidacionENI respuesta = scMtom.validarFirmaDocumentoENI(aplicacionInfo, docEntrada);

    logger.debug("Fin validaFirmaDocumentoENI");
    return formatValidacionENIDocumentoInside(respuesta);
  }



  public List<ResultadoValidacionExpediente> validaExpedienteENI(byte[] expediente,
      Validaciones validaciones) throws InsideServiceValidacionException {
    // CARM ### v2.0.7.1
    if (!this.isActivo()) {
      throw new InsideServiceValidacionException("El WS de VALIDACION no se encuentra activo");
    }
    // CARM 2.0.7.1 ###
    logger.debug("Inicio validaExpedienteENI");

    DocumentoEntradaMtom expEntrada = new DocumentoEntradaMtom();
    ByteArrayDataSource dataSource = new ByteArrayDataSource(expediente);
    expEntrada.setContenido(new DataHandler(dataSource));

    RespuestaValidacionENI respuesta =
        scMtom.validarExpedienteENI(aplicacionInfo, expEntrada, versionSchemas, validaciones);

    logger.debug("Fin validaExpedienteENI");
    return formatValidacionENIExpedienteInside(respuesta);
  }



  public List<ResultadoValidacionExpediente> validaFirmaExpedienteENI(byte[] expediente)
      throws InsideServiceValidacionException {
    // CARM ### v2.0.7.1
    if (!this.isActivo()) {
      throw new InsideServiceValidacionException("El WS de VALIDACION no se encuentra activo");
    }
    // CARM 2.0.7.1 ###
    logger.debug("Inicio validaFirmaExpedienteENI");

    DocumentoEntradaMtom expEntrada = new DocumentoEntradaMtom();
    ByteArrayDataSource dataSource = new ByteArrayDataSource(expediente);
    expEntrada.setContenido(new DataHandler(dataSource));

    RespuestaValidacionENI respuesta = scMtom.validarFirmaExpedienteENI(aplicacionInfo, expEntrada);

    logger.debug("Fin validaFirmaExpedienteENI");
    return formatValidacionENIExpedienteInside(respuesta);
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
          rve.setTipoValidacion(OpcionValidacionExpediente.TOVE_01);// Validacion esquemas
                                                                    // espedientes
        } else if (elemento.getCodigoRespuesta().contains("[E.D.")) {
          rve.setTipoValidacion(OpcionValidacionExpediente.TOVE_02);// Validacion unidades organicas
        } else if (elemento.getCodigoRespuesta().contains("[E.S.")) {
          rve.setTipoValidacion(OpcionValidacionExpediente.TOVE_03);// Validacion clasificacion
        } else if (elemento.getCodigoRespuesta().contains("[E.F.")) {
          rve.setTipoValidacion(OpcionValidacionExpediente.TOVE_04);// Validacion firma
        }

        // por si es error generico
        if (elemento.getCodigoRespuesta().contains("[G.E")) {
          rve.setTipoValidacion(OpcionValidacionExpediente.TOVE_01);// Validacion esquemas
                                                                    // espedientes
        } else if (elemento.getCodigoRespuesta().contains("[G.D")) {
          rve.setTipoValidacion(OpcionValidacionExpediente.TOVE_02);// Validacion unidades organicas
        } else if (elemento.getCodigoRespuesta().contains("[G.S")) {
          rve.setTipoValidacion(OpcionValidacionExpediente.TOVE_03);// Validacion clasificacion
        } else if (elemento.getCodigoRespuesta().contains("[G.F")) {
          rve.setTipoValidacion(OpcionValidacionExpediente.TOVE_04);// Validacion firma
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
          rve.setTipoValidacion(OpcionValidacionDocumento.TOVD_01);// Validacion esquemas documento
        } else if (elemento.getCodigoRespuesta().contains("[D.D.")) {
          rve.setTipoValidacion(OpcionValidacionDocumento.TOVD_02);// Validacion unidades organicas
        } else if (elemento.getCodigoRespuesta().contains("[D.F.")) {
          rve.setTipoValidacion(OpcionValidacionDocumento.TOVD_03);// Validacion firma
        }

        // por si es error generico
        if (elemento.getCodigoRespuesta().contains("[G.E")) {
          rve.setTipoValidacion(OpcionValidacionDocumento.TOVD_01);// Validacion esquemas documento
        } else if (elemento.getCodigoRespuesta().contains("[G.D")) {
          rve.setTipoValidacion(OpcionValidacionDocumento.TOVD_02);// Validacion unidades organicas
        } else if (elemento.getCodigoRespuesta().contains("[G.F")) {
          rve.setTipoValidacion(OpcionValidacionDocumento.TOVD_03);// Validacion firma
        }


        resultadosValidacion.add(rve);
      }
    }

    return resultadosValidacion;

  }


  // public EeUtilValidacionENIService getSc() {
  // return sc;
  // }
  //
  // public void setSc(EeUtilValidacionENIService sc) {
  // this.sc = sc;
  // }


  // Atributos desde properties pasando por xml
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

  public String getVersionSchemas() {
    return versionSchemas;
  }

  public void setVersionSchemas(String versionSchemas) {
    this.versionSchemas = versionSchemas;
  }

}
