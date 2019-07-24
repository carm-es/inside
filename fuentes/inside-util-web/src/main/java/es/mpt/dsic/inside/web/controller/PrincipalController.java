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
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import es.mpt.dsic.inside.model.objetos.adminMensajes.ObjetoMensajeUsuario;
import es.mpt.dsic.inside.service.messages.InsideMessageService;
import es.mpt.dsic.inside.service.util.WebConstants;
import es.mpt.dsic.inside.web.object.MessageObject;

@Controller
public class PrincipalController {

  protected static final Log logger = LogFactory.getLog(PrincipalController.class);

  private static final String MENSAJE_USU = "mensajeUsuario";

  @Autowired
  private InsideMessageService insideMessageService;

  @RequestMapping(value = "/principal", method = RequestMethod.GET)
  public ModelAndView principal() {
    logger.debug("Iniciado GInsidePrincipalController.principal");
    ModelAndView modelAndView = new ModelAndView("principal");

    // buscamos los mensajes a mostrar a los usuarios
    List<ObjetoMensajeUsuario> messages = insideMessageService.getMessagesActivos();
    if (CollectionUtils.isNotEmpty(messages)) {
      MessageObject mensajeWeb = new MessageObject();
      mensajeWeb.setLevel(WebConstants.MESSAGE_LEVEL_INFO);
      for (ObjetoMensajeUsuario message : messages) {
        mensajeWeb.getErrors().add(message.getTexto());
      }
      modelAndView.addObject(MENSAJE_USU, mensajeWeb);
    }

    return modelAndView;
  }

}
