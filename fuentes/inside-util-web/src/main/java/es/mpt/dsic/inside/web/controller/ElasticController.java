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

import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import es.mpt.dsic.inside.model.objetos.ObjetoElastic;
import es.mpt.dsic.inside.model.objetos.usuario.ObjetoInsideUsuario;
import es.mpt.dsic.inside.service.InSideService;
import es.mpt.dsic.inside.service.exception.InSideServiceException;
import es.mpt.dsic.inside.service.util.WebConstants;
import es.mpt.dsic.inside.web.object.MessageObject;

@Controller
public class ElasticController {

  protected static final Log logger = LogFactory.getLog(ElasticController.class);

  @Autowired
  private InSideService insideService;

  @Autowired
  private MessageSource messageSource;

  private static final String MENSAJE_USU = "mensajeUsuario";
  private static final String OBJETOS_ELASTIC = "objetosElastic";

  @RequestMapping(value = "/busquedaElastic", method = RequestMethod.GET)
  public ModelAndView busquedaElastic(Locale locale, HttpSession session,
      HttpServletRequest request) {

    ModelAndView retorno = new ModelAndView("elastic/busquedaElastic");
    MessageObject mensaje;

    try {
      ObjetoInsideUsuario usuario =
          (ObjetoInsideUsuario) session.getAttribute(WebConstants.USUARIO_SESSION);
      List<ObjetoElastic> datos =
          insideService.getObjetosElastic(usuario.getUnidadOrganicaActiva().split(" - ")[0]);
      retorno.addObject(OBJETOS_ELASTIC, datos);

    } catch (InSideServiceException e) {
      logger.error(
          "ExpedientController.expedientesAlmacenados --> Error al obtener expedientes del usuario",
          e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      retorno.addObject(MENSAJE_USU, mensaje);
    }
    return retorno;
  }

}
