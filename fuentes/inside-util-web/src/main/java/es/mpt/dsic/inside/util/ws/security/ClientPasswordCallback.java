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

package es.mpt.dsic.inside.util.ws.security;

import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.ws.security.WSPasswordCallback;

/**
 * Clase que implemente la interfaz CallbackHandler para poder usar la clave privada del certificado
 * a fin de firmar las peticiones. Genera un callback de salida.
 * 
 */
public class ClientPasswordCallback implements CallbackHandler {

  /** Cadena para guardar el password a settear. */
  private String password;

  /** Constructor con par�metros. */
  public ClientPasswordCallback(String pass) {
    password = pass;
  }

  /** Implementa handle. */
  public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
    WSPasswordCallback pwdCallback = (WSPasswordCallback) callbacks[0];
    pwdCallback.setPassword(password);
  }

}
