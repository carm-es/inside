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

import javax.xml.ws.WebServiceContext;
import org.apache.ws.security.WSUsernameTokenPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideAplicacion;
import es.mpt.dsic.inside.service.exception.InSideServiceException;
import es.mpt.dsic.inside.service.users.InsideUsersService;
import es.mpt.dsic.inside.xml.inside.ws.credential.WSCredentialInside;

@Component
public class CredentialUtil {

  @Autowired
  private InsideUsersService insideUsersService;

  public WSCredentialInside getCredentialEeutil() throws InSideServiceException {
    WSCredentialInside credential = new WSCredentialInside();

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    ObjetoInsideAplicacion appEeutil = insideUsersService.getAplicacion(auth.getName());

    credential.setIdaplicacion(appEeutil.getCredencialEeutil().getAplicacion());
    credential.setPassword(appEeutil.getCredencialEeutil().getPassword());

    return credential;
  }

  public WSCredentialInside getCredentialEeutilUserToken(WebServiceContext jaxwsContext)
      throws InSideServiceException {
    WSCredentialInside credential = new WSCredentialInside();

    WSUsernameTokenPrincipal userToken = (WSUsernameTokenPrincipal) jaxwsContext.getUserPrincipal();
    ObjetoInsideAplicacion appEeutil = insideUsersService.getAplicacion(userToken.getName());

    credential.setIdaplicacion(appEeutil.getCredencialEeutil().getAplicacion());
    credential.setPassword(appEeutil.getCredencialEeutil().getPassword());

    return credential;
  }

  public String getAppLogged() throws InSideServiceException {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    return auth.getName();
  }

}
