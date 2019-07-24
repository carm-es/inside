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

package es.mpt.dsic.inside.ws.cxf.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.binding.soap.interceptor.Soap11FaultOutInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.apache.ws.security.WSSecurityException;
import es.mpt.dsic.inside.ws.exception.InsideWSRemisionExpedienteException;
import es.mpt.dsic.inside.ws.exception.InsideWsRemisionExpedienteErrors;

public class InsideWsRemisionExpedienteErrorInterceptor extends AbstractSoapInterceptor {

  protected static final Log logger =
      LogFactory.getLog(InsideWsRemisionExpedienteErrorInterceptor.class);

  public InsideWsRemisionExpedienteErrorInterceptor() {
    super(Phase.PREPARE_SEND);
    getBefore().add(Soap11FaultOutInterceptor.class.getName());
  }

  public void handleMessage(SoapMessage message) throws Fault {
    Fault f = (Fault) message.getContent(Exception.class);
    if (f == null) {
      return;
    }

    Throwable cause = f.getCause();
    if (cause == null) {
      return;
    }

    InsideWSRemisionExpedienteException ex;

    logger.debug("Convirtiendo SoapFault:  " + cause.getClass().getName() + " ; causa: " + cause);
    if (cause instanceof InsideWSRemisionExpedienteException) {
      return;
    } else if (cause instanceof org.springframework.security.authentication.BadCredentialsException) {
      ex = new InsideWSRemisionExpedienteException(InsideWsRemisionExpedienteErrors.REM_0000,
          "Credenciales erróneas");
    } else if (cause instanceof org.springframework.security.access.AccessDeniedException) {
      ex = new InsideWSRemisionExpedienteException(InsideWsRemisionExpedienteErrors.REM_0000,
          "Acceso denegado");
    } else if (cause instanceof org.springframework.security.authentication.DisabledException) {
      ex = new InsideWSRemisionExpedienteException(InsideWsRemisionExpedienteErrors.REM_0000,
          "Deshabilitado");
    } else if (cause instanceof javax.xml.bind.UnmarshalException) {
      ex = new InsideWSRemisionExpedienteException(InsideWsRemisionExpedienteErrors.REM_0001,
          cause);
    } else if (cause instanceof WSSecurityException) {
      if (((WSSecurityException) cause)
          .getErrorCode() == WSSecurityException.INVALID_SECURITY_TOKEN) {
        ex = new InsideWSRemisionExpedienteException(InsideWsRemisionExpedienteErrors.REM_0000,
            "Credenciales erróneas. Token erróneo");
      } else if (((WSSecurityException) cause)
          .getErrorCode() == WSSecurityException.FAILED_AUTHENTICATION) {
        ex = new InsideWSRemisionExpedienteException(InsideWsRemisionExpedienteErrors.REM_0000,
            "Credenciales erróneas. Fallo autenticación");
      } else if (((WSSecurityException) cause)
          .getErrorCode() == WSSecurityException.INVALID_SECURITY) {
        ex = new InsideWSRemisionExpedienteException(InsideWsRemisionExpedienteErrors.REM_0000,
            "Credenciales erróneas. Invalid security");
      } else {
        ex = new InsideWSRemisionExpedienteException(InsideWsRemisionExpedienteErrors.REM_0000,
            "Error no localizado");
      }
    } else {
      logger.warn("Excepción no prevista en el manejador de errores ", cause);
      ex = new InsideWSRemisionExpedienteException(InsideWsRemisionExpedienteErrors.REM_0000,
          "Error no previsto");
    }

    message.setContent(Exception.class, new Fault(ex));

  }
}
