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

package es.mpt.dsic.inside.web.security.interceptor;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Punto de entrada para peticiones http definido en inside-security-context como entry-point-ref.
 * En el caso de ser petición ajax se envía código 403.
 */
public class AuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

  @Deprecated
  public AuthenticationEntryPoint() {
    super();
  }

  public AuthenticationEntryPoint(String loginUrl) {
    super(loginUrl);
  }

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException, ServletException {


    boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));

    if (isAjax) {
      response.sendError(403, "Forbidden");
    } else {
      super.commence(request, response, authException);
    }
  }
}
