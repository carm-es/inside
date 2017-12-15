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

package es.mpt.dsic.inside.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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

import es.mpt.dsic.inside.model.objetos.adminMensajes.ObjetoMensajeUsuario;
import es.mpt.dsic.inside.service.messages.InsideMessageService;
import es.mpt.dsic.inside.web.object.MessageObject;
import es.mpt.dsic.inside.web.util.WebConstants;

@Controller
@RequestMapping(value = "/adminMensajes")
public class AdminMensajesController {
	
	protected static final Log logger = LogFactory.getLog(AdminMensajesController.class);
	private static final String MENSAJE_USU = "mensajeUsuario";
	
	@Autowired
	private InsideMessageService insideMessageService;
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(value = "/lista", method = RequestMethod.GET)
	public ModelAndView lista(HttpSession session, Map<String, Object> data) {
		ModelAndView retorno = new ModelAndView("adminMensajes/adminMensajes");

		List<ObjetoMensajeUsuario> messages = insideMessageService.getAllMessages();
		retorno.addObject("mensajes", messages);
		
		retorno.addAllObjects(data);
		
		return retorno;
	}
	
	@RequestMapping(value = "/crear", method = RequestMethod.POST)
	public ModelAndView crear(Locale locale, HttpSession session, HttpServletRequest request) {
		ObjetoMensajeUsuario mensaje = new ObjetoMensajeUsuario();
		mensaje.setActivo(true);
		mensaje.setFechaCreacion(new Date());
		mensaje.setTexto(request.getParameter("texto"));
		
		insideMessageService.createMessage(mensaje);
		
		Map<String, Object> datos = new HashMap<>();
		MessageObject mensajeWeb = new MessageObject(WebConstants.MESSAGE_LEVEL_SUCCESS,
				messageSource.getMessage("adminMessages.crear.ok", null, locale));
		datos.put(MENSAJE_USU, mensajeWeb);
		
		return lista(session, datos);
	}
	
	@RequestMapping(value = "/editar", method = RequestMethod.POST)
	public ModelAndView editar(Locale locale, HttpSession session, HttpServletRequest request) {
		ObjetoMensajeUsuario mensaje = new ObjetoMensajeUsuario();
		mensaje.setTexto(request.getParameter("texto"));
		mensaje.setIdentificador(Integer.valueOf(request.getParameter("identificador")));
		
		insideMessageService.updateMessage(mensaje);
		
		Map<String, Object> datos = new HashMap<>();
		MessageObject mensajeWeb = new MessageObject(WebConstants.MESSAGE_LEVEL_SUCCESS,
				messageSource.getMessage("adminMessages.editar.ok", null, locale));
		datos.put(MENSAJE_USU, mensajeWeb);
		
		return lista(session, datos);
	}
	
	@RequestMapping(value = "/activar", method = RequestMethod.POST)
	public ModelAndView activarMensaje(Locale locale, HttpSession session, HttpServletRequest request) {
		ObjetoMensajeUsuario mensaje = new ObjetoMensajeUsuario();
		mensaje.setIdentificador(Integer.valueOf(request.getParameter("identificador")));
		
		insideMessageService.activateMessage(mensaje);
		
		Map<String, Object> datos = new HashMap<>();
		MessageObject mensajeWeb = new MessageObject(WebConstants.MESSAGE_LEVEL_SUCCESS,
				messageSource.getMessage("adminMessages.activar.ok", null, locale));
		datos.put(MENSAJE_USU, mensajeWeb);
		
		return lista(session, datos);
	}
	
	@RequestMapping(value = "/desactivar", method = RequestMethod.POST)
	public ModelAndView desactivarMensaje(Locale locale, HttpSession session, HttpServletRequest request) {
		ObjetoMensajeUsuario mensaje = new ObjetoMensajeUsuario();
		mensaje.setIdentificador(Integer.valueOf(request.getParameter("identificador")));
		
		insideMessageService.deactivateMessage(mensaje);
		
		Map<String, Object> datos = new HashMap<>();
		MessageObject mensajeWeb = new MessageObject(WebConstants.MESSAGE_LEVEL_SUCCESS,
				messageSource.getMessage("adminMessages.desactivar.ok", null, locale));
		datos.put(MENSAJE_USU, mensajeWeb);
		
		return lista(session, datos);
	}
	
	@RequestMapping(value = "/eliminar", method = RequestMethod.POST)
	public ModelAndView eliminar(Locale locale, HttpSession session, HttpServletRequest request) {
		ObjetoMensajeUsuario mensaje = new ObjetoMensajeUsuario();
		mensaje.setIdentificador(Integer.valueOf(request.getParameter("identificador")));
		
		insideMessageService.deleteMessage(mensaje);
		
		Map<String, Object> datos = new HashMap<>();
		MessageObject mensajeWeb = new MessageObject(WebConstants.MESSAGE_LEVEL_SUCCESS,
				messageSource.getMessage("adminMessages.borrar.ok", null, locale));
		datos.put(MENSAJE_USU, mensajeWeb);
		
		return lista(session, datos);
	}

}
