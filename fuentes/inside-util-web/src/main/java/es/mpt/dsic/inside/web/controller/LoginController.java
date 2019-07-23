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

package es.mpt.dsic.inside.web.controller;

import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import es.mpt.dsic.inside.configuration.ConstantsClave;
import es.mpt.dsic.inside.service.exception.ServiceException;
import es.mpt.dsic.inside.service.impl.LoginBusinessServiceImpl;
import es.mpt.dsic.inside.web.util.WebConstants;
import eu.stork.peps.auth.commons.PEPSUtil;

@Controller
@PropertySource("classpath:config.properties")
public class LoginController {

  protected static final Log logger = LogFactory.getLog(LoginController.class);

  @Autowired
  private ApplicationContext context;

  @Autowired
  private Environment env;

  @Autowired
  private LoginBusinessServiceImpl loginBusinessService;

  @Bean
  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }

  @Value("${versionNumber}")
  private String versionNumber;

  @Value("${puntounicojusticia}")
  private boolean puntounicojusticia;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public ModelAndView init(HttpSession session) {
    ModelAndView retorno = new ModelAndView("login");
    if (session.getAttribute(WebConstants.USUARIO_SESSION) != null) {
      retorno = new ModelAndView("principal");
    }
    session.setAttribute("versionNumber", versionNumber);
    session.setAttribute("puntounicojusticia", puntounicojusticia);
    return retorno;
  }

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public ModelAndView loginGet(@RequestParam(value = "error", required = false) String error,
      HttpServletRequest request, Locale locale, HttpSession session) {
    ModelAndView retorno = new ModelAndView("login");
    if (error != null) {
      ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
      retorno.addObject("error", bundle.getString(WebConstants.KEY_ERROR_LOGIN));
    } else if (session.getAttribute(WebConstants.USUARIO_SESSION) != null) {
      retorno = new ModelAndView("principal");
    }
    session.setAttribute("versionNumber", versionNumber);
    session.setAttribute("puntounicojusticia", puntounicojusticia);
    return retorno;
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public ModelAndView loginPost(@RequestParam(value = "error", required = false) String error,
      HttpServletRequest request, Locale locale, HttpSession session) {
    ModelAndView retorno = new ModelAndView("login");
    if (error != null) {
      ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
      retorno.addObject("error", bundle.getString(WebConstants.KEY_ERROR_LOGIN));
    } else if (session.getAttribute(WebConstants.USUARIO_SESSION) != null) {
      retorno = new ModelAndView("principal");
    }
    return retorno;
  }

  @RequestMapping(value = "/loginRedirectClave", method = RequestMethod.POST)
  public String loginClave(Model model, HttpSession session, HttpServletRequest request,
      RedirectAttributes redirectAttributes, Locale locale) {
    logger.debug("loginRedirectClave");

    ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
    try {

      String claveServiceUrl = env.getProperty(ConstantsClave.PROPERTY_URL_CLAVE);
      String excludedIdPList = env.getProperty(ConstantsClave.PROPERTY_EXCLUDED_IDPLIST);
      String forcedIdP = env.getProperty(ConstantsClave.PROPERTY_FORCED_IDP);

      byte[] token = null;

      token = loginBusinessService.generaTokenClave(claveServiceUrl);

      String samlRequest = PEPSUtil.encodeSAMLToken(token);
      String samlRequestXML = new String(token);
      if (logger.isInfoEnabled()) {
        logger.debug(samlRequestXML);
      }

      model.addAttribute("claveServiceUrl", claveServiceUrl);
      model.addAttribute(ConstantsClave.ATRIBUTO_EXCLUDED_IDPLIST, excludedIdPList);
      model.addAttribute(ConstantsClave.ATRIBUTO_FORCED_IDP, forcedIdP);
      model.addAttribute(ConstantsClave.ATRIBUTO_SAML_REQUEST, samlRequest);

      return "login-redirect";
    } catch (ServiceException e) {
      model.addAttribute("errorMsg", context
          .getMessage(bundle.getString(WebConstants.KEY_ERROR_LOGIN), null, request.getLocale()));
      return "login";

    } catch (Exception e) {
      logger.debug("Error: " + e.toString());
      model.addAttribute("errorMsg", context
          .getMessage(bundle.getString(WebConstants.KEY_ERROR_LOGIN), null, request.getLocale()));
      return "login";
    }
  }

}
