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

package es.mpt.dsic.inside.web.security.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import es.mpt.dsic.inside.web.util.WebConstants;

public class InsideAuthenticationSuccessHandler implements
		AuthenticationSuccessHandler {

	private static Logger logger = Logger
			.getLogger(InsideAuthenticationSuccessHandler.class);
	
	private String url;
	private String urlNoRegistrado;

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		logger.debug("onAuthenticationSuccess");
		logger.debug(request.getSession().getAttribute(WebConstants.USUARIO_SESSION));
				
		String targetUrl;
		if ( ((UserAuthentication)authentication).getAuthorities().contains(new SimpleGrantedAuthority("GUEST_ROLE")) ) {
			targetUrl = urlNoRegistrado;
		} else {
			targetUrl = url;
		}

		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlNoRegistrado() {
		return urlNoRegistrado;
	}

	public void setUrlNoRegistrado(String urlNoRegistrado) {
		this.urlNoRegistrado = urlNoRegistrado;
	}

	
}
