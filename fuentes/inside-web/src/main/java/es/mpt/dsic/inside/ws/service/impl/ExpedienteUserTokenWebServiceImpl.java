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

package es.mpt.dsic.inside.ws.service.impl;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import es.mpt.dsic.inside.util.ws.security.CredentialUtil;
import es.mpt.dsic.inside.ws.exception.InsideExceptionConverter;
import es.mpt.dsic.inside.ws.exception.InsideWSException;
import es.mpt.dsic.inside.ws.operation.ExpedienteOperationWebService;
import es.mpt.dsic.inside.ws.service.ExpedienteUserTokenWebService;
import es.mpt.dsic.inside.xml.inside.TipoMetadatosAdicionales;
import es.mpt.dsic.inside.xml.inside.ws.credential.WSCredentialInside;
import es.mpt.dsic.inside.xml.inside.ws.expediente.TipoExpedienteEniFileInside;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoExpedienteConversionInside;
import es.mpt.dsic.inside.xml.inside.ws.objetoEniXMLBytes.ObjetoEniXMLBytes;
import es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.resultados.TipoResultadoValidacionExpedienteInside;



@WebService(endpointInterface = "es.mpt.dsic.inside.ws.service.ExpedienteUserTokenWebService",
    targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
    portName = "ExpedienteUserTokenWSPort", serviceName = "ExpedienteUserTokenWS")
public class ExpedienteUserTokenWebServiceImpl implements ExpedienteUserTokenWebService {

  protected static final Log logger = LogFactory.getLog(ExpedienteUserTokenWebServiceImpl.class);

  @Autowired
  ExpedienteOperationWebService expedienteOperationWebService;

  @Autowired
  private CredentialUtil credentialUtil;

  @Resource
  private WebServiceContext wsContext;


  @Override
  @Secured("ROLE_LEER_EXPEDIENTE")
  public ObjetoEniXMLBytes crearExpedienteEniFileXML(TipoExpedienteConversionInside expediente,
      TipoMetadatosAdicionales metadatosAdicionales) throws InsideWSException {
    logger.debug("Iniciando crearExpedienteEniFileXML");
    final String OPERACION = "Expediente creado.";

    try {
      // recuperar las credenciales dadas de alta si es el caso
      WSCredentialInside wsCredentialInside =
          credentialUtil.getCredentialEeutilUserToken(wsContext);

      // evoluciona de insideOperationWebService.convertirExpedienteAEni(.....) Crea el
      // expedienteEni.xml y lo devuelve.
      TipoExpedienteEniFileInside tipoExpedienteEniFileInside = expedienteOperationWebService
          .crearExpedienteEniFileXMLServicio(expediente, metadatosAdicionales, wsCredentialInside);

      // Forma la respuesta del metodo para su devolucion
      ObjetoEniXMLBytes respuesta = formarRespuesta(tipoExpedienteEniFileInside, OPERACION);

      return respuesta;

    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }



  }



  @Override
  @Secured("ROLE_LEER_EXPEDIENTE")
  public ObjetoEniXMLBytes recuperarExpedienteEniFileXML(String expedienteIdentificador)
      throws InsideWSException {
    logger.debug("Iniciando recuperarExpedienteEniFileXML");
    final String OPERACION = "Expediente recuoperado.";

    try {
      // recuperar las credenciales dadas de alta si es el caso
      WSCredentialInside wsCredentialInside =
          credentialUtil.getCredentialEeutilUserToken(wsContext);

      // evoluciona de insideOperationWebService.getExpedienteEniFile(.....)
      // Crea el expedienteEni.xml y lo devuelve
      TipoExpedienteEniFileInside tipoExpedienteEniFileInside = expedienteOperationWebService
          .recuperarExpedienteEniFileXMLServicio(expedienteIdentificador, wsCredentialInside);

      // Forma la respuesta del metodo para su devolucion
      ObjetoEniXMLBytes respuesta = formarRespuesta(tipoExpedienteEniFileInside, OPERACION);

      return respuesta;
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }


  }



  @Override
  @Secured("ROLE_ALTA_EXPEDIENTE")
  public ObjetoEniXMLBytes insertarExpedienteEniFileXML(
      TipoExpedienteEniFileInside expedienteEniFile) throws InsideWSException {
    logger.debug("Iniciando insertarExpedienteEniFileXML");
    final String OPERACION = "Expediente insertado.";


    try {
      // recuperar las credenciales dadas de alta si es el caso
      WSCredentialInside wsCredentialInside =
          credentialUtil.getCredentialEeutilUserToken(wsContext);

      // evoluciona de insideOperationWebService.altaExpedienteEniXml(.....) Crea el
      // expedienteEni.xml y lo devuelve
      TipoExpedienteEniFileInside tipoExpedienteEniFileInside = expedienteOperationWebService
          .insertarExpedienteEniFileXMLServicio(expedienteEniFile, wsCredentialInside);

      // Forma la respuesta del metodo para su devolucion
      ObjetoEniXMLBytes respuesta = formarRespuesta(tipoExpedienteEniFileInside, OPERACION);

      return respuesta;
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }


  }



  @Override
  @Secured("ROLE_MODIFICA_EXPEDIENTE")
  public ObjetoEniXMLBytes eliminarExpedienteEniFileXML(String expedienteIdentificador)
      throws InsideWSException {
    logger.debug("Iniciando eliminarExpedienteEniFileXML");
    final String OPERACION = "Expediente eliminado.";


    try {
      // recuperar las credenciales dadas de alta si es el caso
      WSCredentialInside wsCredentialInside =
          credentialUtil.getCredentialEeutilUserToken(wsContext);

      // evoluciona de eliminar del online
      TipoExpedienteEniFileInside tipoExpedienteEniFileInside = expedienteOperationWebService
          .eliminarExpedienteEniFileXMLServicio(expedienteIdentificador, wsCredentialInside);

      // Forma la respuesta del metodo para su devolucion
      ObjetoEniXMLBytes respuesta = formarRespuesta(tipoExpedienteEniFileInside, OPERACION);

      return respuesta;

    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }


  }



  @Override
  public TipoResultadoValidacionExpedienteInside validarExpedienteEniFileXML(
      TipoExpedienteEniFileInside expedienteEniFile) throws InsideWSException {
    logger.debug("Iniciando validarExpedienteEniFileXML");
    final String OPERACION = "Validación correcta.";

    try {
      // recuperar las credenciales dadas de alta si es el caso
      WSCredentialInside wsCredentialInside =
          credentialUtil.getCredentialEeutilUserToken(wsContext);

      // evoluciona del validar
      TipoResultadoValidacionExpedienteInside tipoResultadoValidacionExpedienteInside =
          expedienteOperationWebService.validarExpedienteEniFileXMLServicio(expedienteEniFile,
              wsCredentialInside);

      return tipoResultadoValidacionExpedienteInside;
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }

  }



  /*
   * formar la respuesta a devolver en caso de exito
   */
  private ObjetoEniXMLBytes formarRespuesta(TipoExpedienteEniFileInside expedienteByte,
      String operacion) {
    String MENSAJE_OPERACION_SUCCSES = "Operación realizada con éxito: " + operacion;

    ObjetoEniXMLBytes respuesta = new ObjetoEniXMLBytes();
    respuesta.setMensaje(MENSAJE_OPERACION_SUCCSES);
    respuesta.setAtributoObjetoEniXMLBytes(expedienteByte.getExpedienteEniBytes());

    return respuesta;

  }


}
