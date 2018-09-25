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

package es.mpt.dsic.inside.util.ws.security;

import java.util.HashMap;
import java.util.Map;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.log4j.Logger;
import org.apache.ws.security.handler.WSHandlerConstants;

/**
 * Clase de gesti�n de seguridad peticiones a clientes de WS a trav�s de
 * interceptor.
 * 
 */
public class WSSecurityManager {

	/** Logger de la clase. */
	private static Logger logger = Logger.getLogger(WSSecurityManager.class);

	/**
	 * M�todo para configurar la seguridad para un cliente de WS.
	 * 
	 * @param client
	 *            Client Devuelve el interceptor de salida para a�adir las
	 *            cabeceras de WS-Security correspondiente a la autenticaci�n
	 *            con usernametoken.
	 * @param user
	 *            Cadena con el usuario.
	 * @param passwordType
	 *            Cadena con el tipo de Password para el interceptor.
	 * @param passwordCallback
	 *            Objeto ClientPasswordCallback
	 */
	public static void setupWSSecurity(Client client, String user, String passwordType, ClientPasswordCallback passwordCallback) {
		logger.debug("[INI] Entramos en setupWSSecurity. ");

		// Autenticaci�n con usuario/contrase�a
		logger.debug("Creamos el interceptor... ");
		WSS4JOutInterceptor wssOut = interceptorUsernameToken(user, passwordType, passwordCallback);
		client.getEndpoint().getOutInterceptors().add(wssOut);

		logger.debug("[FIN] Salimos de setupWSSecurity. ");

	}// fin setupWSSecurity

	/**
	 * M�todo que configura el interceptor con par�metros de seguridad.
	 * 
	 * @param user
	 *            Cadena con el usuario.
	 * @param passwordType
	 *            Cadena con el tipo de Password para el interceptor.
	 * @param passwordCallback
	 *            Objeto ClientPasswordCallback
	 * @return WSS4JOutInterceptor Devuelve el interceptor de salida para a�adir
	 *         las cabeceras de WS-Security correspondiente a la autenticaci�n
	 *         con usuario/contrase�a.
	 */
	private static WSS4JOutInterceptor interceptorUsernameToken(String user, String passwordType,
			ClientPasswordCallback passwordCallback) {

		logger.debug("[INI] Entramos en interceptorUsernameToken. ");

		// Creamos un HashMap para almacenar las propiedades del interceptor
		Map<String, Object> outProps = new HashMap<String, Object>();

		// A�adimos la acci�n que vamos a realizar
		outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);

		// Alias del certificado a usar como USER
		outProps.put(WSHandlerConstants.USER, user);

		// Password callback y tipo
		outProps.put(WSHandlerConstants.PASSWORD_TYPE, passwordType);
		outProps.put(WSHandlerConstants.PW_CALLBACK_REF, passwordCallback);

		// Creamos el interceptor
		WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);

		logger.debug("[FIN] Salimos de interceptorUsernameToken. Interceptor creado.");

		return wssOut;

	}// fin interceptorUsernameToken

}
