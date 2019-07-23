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

package es.mpt.dsic.inside.ws.security.interceptor;

import java.util.Map;
import java.util.Properties;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.log4j.Logger;
import org.apache.ws.security.WSSecurityException;
import org.apache.ws.security.components.crypto.Crypto;
import org.apache.ws.security.components.crypto.CryptoFactory;
import org.apache.ws.security.handler.RequestData;
import es.mpt.dsic.inside.web.security.authentication.InsideAuthenticationSuccessHandler;

public class CustomWSS4JInInterceptor extends WSS4JInInterceptor {

  private static Logger logger = Logger.getLogger(InsideAuthenticationSuccessHandler.class);

  private Properties cryptoProperties;

  public CustomWSS4JInInterceptor(Map<String, Object> properties) {
    super(properties);
    logger.debug("Inicio CustomWSS4JInInterceptor");
  }

  @Override
  protected Crypto loadCrypto(String cryptoPropertyFile, String cryptoPropertyRefId,
      RequestData requestData) throws WSSecurityException {
    logger.debug("Inicio loadCrypto");
    return CryptoFactory.getInstance(cryptoProperties);
  }

  public Properties getCryptoProperties() {
    return cryptoProperties;
  }

  public void setCryptoProperties(Properties cryptoProperties) {
    this.cryptoProperties = cryptoProperties;
  }

}
