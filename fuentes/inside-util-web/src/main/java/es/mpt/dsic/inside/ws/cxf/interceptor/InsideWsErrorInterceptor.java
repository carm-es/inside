/* Copyright (C) 2016 MINHAP, Gobierno de España
   This program is licensed and may be used, modified and redistributed under the terms
   of the European Public License (EUPL), either version 1.1 or (at your
   option) any later version as soon as they are approved by the European Commission.
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
   or implied. See the License for the specific language governing permissions and
   more details.
   You should have received a copy of the EUPL1.1 license
   along with this program; if not, you may find it at
   http://joinup.ec.europa.eu/software/page/eupl/licence-eupl */

package es.mpt.dsic.inside.ws.cxf.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.binding.soap.interceptor.Soap11FaultOutInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.apache.ws.security.WSSecurityException;

import com.ctc.wstx.exc.WstxParsingException;

import es.mpt.dsic.inside.ws.exception.InsideWSException;
import es.mpt.dsic.inside.ws.exception.InsideWsErrors;

public class InsideWsErrorInterceptor extends AbstractSoapInterceptor {

	protected static final Log logger = LogFactory.getLog(InsideWsErrorInterceptor.class);

	public InsideWsErrorInterceptor() {
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

		InsideWSException ex;

		logger.debug("Convirtiendo SoapFault:  " + cause.getClass().getName() + " ; causa: " + cause);
		if (cause instanceof InsideWSException) {
			return;
			// }else if(cause instanceof
			// org.springframework.security.core.AuthenticationException){
		} else if (cause instanceof org.springframework.security.authentication.BadCredentialsException) {
			ex = new InsideWSException(InsideWsErrors.CREDENTIALS_ERROR, cause);
		} else if (cause instanceof org.springframework.security.access.AccessDeniedException) {
			ex = new InsideWSException(InsideWsErrors.UNAUTHORIZED_OPERATION_ERROR, cause);
		} else if (cause instanceof org.springframework.security.authentication.DisabledException) {
			ex = new InsideWSException(InsideWsErrors.DISABLED_ERROR, cause);
		} else if (cause instanceof javax.xml.bind.UnmarshalException) {
			ex = new InsideWSException(InsideWsErrors.UNMARSHALL_ERROR, cause);
		} else if (cause instanceof WstxParsingException) {
			// TODO esto no funciona porque al no parsear el xml no encuentra el
			// binding y no sabe como pintar la InsideWSExceptio
			// ver org.apache.cxf.jaxws.interceptors.WebFaultOutInterceptor :
			// 113
			ex = new InsideWSException(InsideWsErrors.XML_PARSING_ERROR, cause, cause.getMessage());
		} else if (cause instanceof WSSecurityException) {
			if (((WSSecurityException) cause).getErrorCode() == WSSecurityException.INVALID_SECURITY_TOKEN) {
				ex = new InsideWSException(InsideWsErrors.CREDENTIALS_ERROR_FORMAT, cause);
			} else if (((WSSecurityException) cause).getErrorCode() == WSSecurityException.FAILED_AUTHENTICATION) {
				ex = new InsideWSException(InsideWsErrors.CREDENTIALS_ERROR, cause);
			} else if (((WSSecurityException) cause).getErrorCode() == WSSecurityException.INVALID_SECURITY) {
				ex = new InsideWSException(InsideWsErrors.CREDENTIALS_INVALID, cause);
			}else {
				ex = new InsideWSException(InsideWsErrors.INTERNAL_SERVICE_ERROR, cause);
			}
		} else {
			logger.warn("Excepción no prevista en el manejador de errores ", cause);
			ex = new InsideWSException(InsideWsErrors.INTERNAL_SERVICE_ERROR, cause);
		}

		message.setContent(Exception.class, new Fault(ex));

	}
}