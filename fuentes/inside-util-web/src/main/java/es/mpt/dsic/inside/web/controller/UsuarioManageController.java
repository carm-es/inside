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


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideRol;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideUnidad;
import es.mpt.dsic.inside.model.objetos.ObjetoNumeroProcedimiento;
import es.mpt.dsic.inside.model.objetos.usuario.ObjetoInsideUsuario;
import es.mpt.dsic.inside.service.InSideService;
import es.mpt.dsic.inside.service.exception.InSideServiceException;
import es.mpt.dsic.inside.service.store.exception.InsideServiceStoreException;
import es.mpt.dsic.inside.service.util.WebConstants;
import es.mpt.dsic.inside.web.object.ComboItem;
import es.mpt.dsic.inside.web.object.MessageObject;
import es.mpt.dsic.inside.web.security.authentication.UserAuthentication;
import es.mpt.dsic.inside.web.util.RolesUsuarioAsignacionUtils;


@Controller
public class UsuarioManageController {

  protected static final Log logger = LogFactory.getLog(UsuarioManageController.class);

  @Autowired
  private MessageSource messageSource;

  @Autowired
  private InSideService insideService;

  private List<ComboItem> listadoComboRoles;

  private List<ComboItem> listadoNumeroProcedimientos;


  private static final String MENSAJE_USU = "mensajeUsuario";

  @RequestMapping(value = "/altaUsuarioWebInside", method = RequestMethod.GET)
  public ModelAndView altaUsuarioWebInside(Locale locale, HttpSession session,
      HttpServletRequest request) {

    logger.debug("Iniciado UsuarioManageController.altaUsuarioWebInside");

    ModelAndView retorno = new ModelAndView("altaUsuarioWebInside");
    MessageObject mensaje;

    try {
      this.setListadoComboRoles(cargaComboRoles(locale));
      this.setListadoNumeroProcedimientos(cargaNumeroProcedimientos(locale));
      ObjetoInsideUsuario usuario =
          (ObjetoInsideUsuario) session.getAttribute(WebConstants.USUARIO_SESSION);
      String unidadOrganicaActiva = usuario.getUnidadOrganicaActiva().split(" - ")[0];
      String descripcion = usuario.getUnidadOrganicaActiva().split(" - ")[1];

      ObjetoInsideUsuario usuarioAlta = new ObjetoInsideUsuario();
      usuarioAlta.setUnidadOrganicaActiva(unidadOrganicaActiva);
      usuarioAlta.setNombreUnidadOrganicaActiva(descripcion);
      retorno.addObject("usuarioAlta", usuarioAlta);
      retorno.addObject("rolesDisponibles", cargaComboRoles(locale));
      retorno.addObject("numeroProcedimientosDisponibles", cargaNumeroProcedimientos(locale));

    } catch (Exception e) {
      logger.error(
          "UsuarioManageController.altaUsuarioWebInside --> Error alta usuario web inside.", e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      retorno.addObject(MENSAJE_USU, mensaje);
    }
    return retorno;
  }


  public List<ComboItem> cargaComboRoles(Locale locale) throws InsideServiceStoreException {

    logger.info("[INI] Entramos en cargaComboRoles. ");

    List<ObjetoInsideRol> insideRoles = insideService.getInsideRoles();

    logger.info("Transformamos en tipo SelectIntemVO para mostrar lista de autocompletado...");
    List<ComboItem> listaReturn = new ArrayList<ComboItem>();
    for (ObjetoInsideRol insideRol : insideRoles) {
      if (insideRol.getId() != 1) {
        listaReturn.add(new ComboItem(insideRol.getId() + "",
            messageSource.getMessage(insideRol.getDescripcionLarga(), null, locale), null));
      }
    }

    logger.info("[FIN] Salimos de cargaComboRoles. Total a mostrar: " + listaReturn.size());

    return listaReturn;
  }

  public List<ComboItem> cargaNumeroProcedimientos(Locale locale)
      throws InsideServiceStoreException {

    logger.info("[INI] Entramos en cargaNumeroProcedimientos. ");

    List<ObjetoNumeroProcedimiento> listaNumeroProcedimiento =
        insideService.getNumeroProcedimientoList();

    logger.info("Transformamos en tipo SelectIntemVO para mostrar lista de autocompletado...");
    List<ComboItem> listaReturn = new ArrayList<ComboItem>();
    listaReturn.add(new ComboItem("0", "", null));
    for (ObjetoNumeroProcedimiento objetoNumProc : listaNumeroProcedimiento) {
      listaReturn.add(
          new ComboItem(objetoNumProc.getId() + "", objetoNumProc.getNumeroProcedimiento(), null));
    }

    logger
        .info("[FIN] Salimos de cargaNumeroProcedimientos. Total a mostrar: " + listaReturn.size());

    return listaReturn;
  }

  @RequestMapping(value = "/altaUsuarioWebInsidePost/altaUsuarioExiteNif",
      method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> existeNifAlta(@RequestParam("nif") final String nif) {
    logger.info("[INI] Entramos en existeNifAlta. ");

    Map<String, Object> retorno = new HashMap<String, Object>();
    MessageObject mensaje = null;

    boolean existeUsuario = false;

    try {
      insideService.getUsuario(nif);
      existeUsuario = true;
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_INFO,
          "El NIF " + nif + " ya existe en Inside. ");
      retorno.put("mensajeIdentificador", mensaje);
      retorno.put("existeUsuario", existeUsuario);

    } catch (InSideServiceException e) {
      // Si no existe salta desde getUsuario aqui
      existeUsuario = false;
      retorno.put("existeUsuario", existeUsuario);

    }

    logger.info("[FIN] Salimos de existeNifAlta.");

    return retorno;
  }



  @RequestMapping(value = "/altaUsuarioWebInsidePost", method = RequestMethod.POST)
  public ModelAndView altaUsuarioWebInsidePost(ObjetoInsideUsuario usuarioAlta) {

    logger.debug("Iniciado UsuarioManageController.altaUsuarioWebInsidePost");

    ModelAndView retorno = new ModelAndView("altaUsuarioWebInside");
    MessageObject mensaje;

    try {
      if (StringUtils.isEmpty(usuarioAlta.getNif())) {
        logger.debug(
            "ERROR en altaUsuarioWebInsidePost: El NIF no viene informado y es obligatorio.");
        throw new Exception();
      }


      ObjetoInsideUsuario usuarioDadoDeAlta = insideService.altaUsuario(usuarioAlta);

      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_INFO,
          "El NIF: " + usuarioDadoDeAlta.getNif() + " ha sido dado de alta correctamente.");
      retorno.addObject(MENSAJE_USU, mensaje);

      // vaciar el nif y procedimiento para mostrarlos vacios
      usuarioAlta.setNif("");
      usuarioAlta.setNumeroProcedimiento("");
      retorno.addObject("rolesDisponibles", this.getListadoComboRoles());
      retorno.addObject("numeroProcedimientosDisponibles", this.getListadoNumeroProcedimientos());

      retorno.addObject("usuarioAlta", usuarioAlta);

    } catch (Exception e) {
      logger.debug(e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
          "Error en el alta. Compruebe que los valores obligatorios son los correctos y están informados.");
      retorno.addObject(MENSAJE_USU, mensaje);
      usuarioAlta.setNif("");
      usuarioAlta.setNumeroProcedimiento("");
      retorno.addObject("rolesDisponibles", this.getListadoComboRoles());
      retorno.addObject("numeroProcedimientosDisponibles", this.getListadoNumeroProcedimientos());
      retorno.addObject("usuarioAlta", usuarioAlta);

    }

    logger.debug("Finalizado UsuarioManageController.altaUsuarioWebInsidePost");
    return retorno;
  }


  public List<ComboItem> getListadoNumeroProcedimientos() {
    return listadoNumeroProcedimientos;
  }


  public void setListadoNumeroProcedimientos(List<ComboItem> listadoNumeroProcedimientos) {
    this.listadoNumeroProcedimientos = listadoNumeroProcedimientos;
  }


  public List<ComboItem> getListadoComboRoles() {
    return listadoComboRoles;
  }


  public void setListadoComboRoles(List<ComboItem> listadoComboRoles) {
    this.listadoComboRoles = listadoComboRoles;
  }

  @RequestMapping(value = "/getUnidadesOrganicas", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> getUnidadesOrganicas(HttpServletRequest request) {
    Map<String, Object> retorno = new HashMap<String, Object>();
    MessageObject mensaje = null;
    String identificadorUsuario = request.getParameter("identificadorUsuario");
    try {
      List<ObjetoInsideUnidad> unidades =
          insideService.getUnidadesUsuario(identificadorUsuario, true);
      retorno.put("unidades", unidades);
    } catch (InSideServiceException e) {
      logger.error("ExpedientController.guardarExpediente --> Error al guardar expediente", e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      retorno.put(MENSAJE_USU, mensaje);
    }
    return retorno;
  }

  @RequestMapping(value = "/updateUnidadActiva", method = RequestMethod.POST)
  public ModelAndView updateUnidadActiva(HttpSession session, HttpServletRequest request) {
    ModelAndView retorno = new ModelAndView("/principal");
    MessageObject mensaje;
    ObjetoInsideUsuario usuario =
        (ObjetoInsideUsuario) session.getAttribute(WebConstants.USUARIO_SESSION);
    String[] elementos = request.getParameter("unidadProcedimiento").split("-", 2);
    String codigoUnidad = elementos[0];
    String numeroProcedimiento = elementos.length > 1 ? elementos[1] : null;
    try {
      insideService.asociarUnidadUsuario(codigoUnidad, numeroProcedimiento, usuario.getNif());
      ObjetoInsideUnidad unidadUsuarioActiva =
          insideService.getUnidadesUsuario(usuario.getNif(), false).get(0);

      UserAuthentication auth =
          (UserAuthentication) SecurityContextHolder.getContext().getAuthentication();
      String unidadOrganica =
          unidadUsuarioActiva.getCodigo() + " - " + unidadUsuarioActiva.getNombre();
      auth.setUnidadOrganicaActiva(unidadOrganica);

      usuario.setUnidadOrganicaActiva(unidadOrganica);

      // actualizar rol al que tiene en la unidad
      ObjetoInsideRol objetoInsideRol =
          insideService.getRolUnidadUsuario(codigoUnidad, usuario.getNif());
      usuario.setRol(objetoInsideRol);
      // se actualiza el user guardado en sesion
      session.setAttribute(WebConstants.USUARIO_SESSION, usuario);

      // actualiza la lista de roles y crea una nueva autoridad
      List<GrantedAuthority> roles =
          RolesUsuarioAsignacionUtils.asignarRolesUsuario(objetoInsideRol.getDescripcion());

      // List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
      // roles.add(new SimpleGrantedAuthority(objetoInsideRol.getDescripcion()));
      // if ("REMISION_JUSTICIA_ROLE".equals(objetoInsideRol.getDescripcion())) {
      // roles.add(new SimpleGrantedAuthority("CONSULTA_ROLE"));
      // }else if ("ALTA_USUARIOS_ROLE".equals(objetoInsideRol.getDescripcion()) ||
      // ("REDACTOR_ROLE".equals(objetoInsideRol.getDescripcion())) ) {
      // roles.add(new SimpleGrantedAuthority("USER_ROLE"));
      // }

      UserAuthentication authActualizado =
          new UserAuthentication(auth.getNif(), null, roles, null, auth.getNif(), null);
      authActualizado.setUnidadOrganicaActiva(unidadOrganica);
      SecurityContextHolder.getContext().setAuthentication(authActualizado);



    } catch (InSideServiceException e) {
      logger.error("ExpedientController.guardarExpediente --> Error al guardar expediente", e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      retorno.addObject(MENSAJE_USU, mensaje);
    }
    return retorno;
  }


  @RequestMapping(value = "/listaUsuariosEnUnidadOrganica", method = RequestMethod.GET)
  public ModelAndView listaUsuariosEnUnidadOrganica(HttpServletRequest request, Locale locale) {
    ModelAndView retorno = new ModelAndView("listaUsuariosEnUnidadOrganica");

    // Usuario en sesion para extraer su unidadOrganica y buscar en ella los usuarios
    ObjetoInsideUsuario usuarioEnSesion =
        (ObjetoInsideUsuario) request.getSession().getAttribute(WebConstants.USUARIO_SESSION);
    String unidadOrganicaActivaConDescripcionDelUsuarioLogado =
        usuarioEnSesion.getUnidadOrganicaActiva();
    String unidadOrganicaActivaDelUsuarioLogadoCodigo =
        unidadOrganicaActivaConDescripcionDelUsuarioLogado.split("-")[0].trim();

    try {
      // Devuelve la lista de usuarios con la unidadOrganicaActiva en su lista de unidades organicas
      List<ObjetoInsideUsuario> usuariosEnUnidadOrganica =
          insideService.getUsuariosUnidadOrganica(usuarioEnSesion, locale);

      retorno.addObject("data", usuariosEnUnidadOrganica);
      retorno.addObject("unidadOrganicaActiva", unidadOrganicaActivaDelUsuarioLogadoCodigo);

      int lengthArray = 1;
      String[] option = new String[1];
      option[lengthArray - 1] = unidadOrganicaActivaDelUsuarioLogadoCodigo;
      retorno.addObject("titulo", messageSource.getMessage("listaUsuarios.title", option, locale)); // listaUsuarios.title=Usuarios
                                                                                                    // en
                                                                                                    // la
                                                                                                    // Unidad
                                                                                                    // Organica
                                                                                                    // {0}

    } catch (InSideServiceException e) {
      logger.error("Error al obtener usuarios");
    }
    return retorno;
  }


  @RequestMapping(value = "/borrarUsuario", method = RequestMethod.POST)
  public ModelAndView borrarUsuario(HttpServletRequest request, Locale locale) {
    ModelAndView retorno = new ModelAndView("listaUsuariosEnUnidadOrganica");

    String idUsuario = request.getParameter("nif");
    String numeroProcedimiento = request.getParameter("numeroProcedimiento");

    // Usuario en sesion para extraer su unidadOrganica y buscar en ella los usuarios
    ObjetoInsideUsuario usuarioEnSesion =
        (ObjetoInsideUsuario) request.getSession().getAttribute(WebConstants.USUARIO_SESSION);
    String unidadOrganicaActivaConDescripcionDelUsuarioLogado =
        usuarioEnSesion.getUnidadOrganicaActiva();
    String unidadOrganicaActivaDelUsuarioLogadoCodigo =
        unidadOrganicaActivaConDescripcionDelUsuarioLogado.split("-")[0].trim();

    try {
      // borrar relacion unidad Usuario
      insideService.eliminarUnidadUsuario(unidadOrganicaActivaDelUsuarioLogadoCodigo,
          numeroProcedimiento, idUsuario);


      // Devuelve la lista de usuarios con la unidadOrganicaActiva en su lista de unidades organicas
      List<ObjetoInsideUsuario> usuariosEnUnidadOrganica =
          insideService.getUsuariosUnidadOrganica(usuarioEnSesion, locale);

      retorno.addObject("data", usuariosEnUnidadOrganica);
      retorno.addObject("unidadOrganicaActiva", unidadOrganicaActivaDelUsuarioLogadoCodigo);

      int lengthArray = 1;
      String[] option = new String[1];
      option[lengthArray - 1] = unidadOrganicaActivaDelUsuarioLogadoCodigo;
      retorno.addObject("titulo", messageSource.getMessage("listaUsuarios.title", option, locale)); // listaUsuarios.title=Usuarios
                                                                                                    // en
                                                                                                    // la
                                                                                                    // Unidad
                                                                                                    // Organica
                                                                                                    // {0}

    } catch (InSideServiceException e) {
      logger.error("Error al obtener usuarios");
    }
    return retorno;

  }



  @RequestMapping(value = "/modificarUsuarioWebInside", method = RequestMethod.POST)
  public ModelAndView modificarUsuarioWebInside(Locale locale, HttpSession session,
      HttpServletRequest request) {

    logger.debug("Iniciado UsuarioManageController.modificarUsuarioWebInside");

    String nif = request.getParameter("nif");
    String numeroProcedimiento = request.getParameter("numeroProcedimiento");
    String rol = request.getParameter("rol");


    ModelAndView retorno = new ModelAndView("modificarUsuarioWebInside");
    MessageObject mensaje;

    try {
      this.setListadoComboRoles(cargaComboRoles(locale));
      ObjetoInsideUsuario usuario =
          (ObjetoInsideUsuario) session.getAttribute(WebConstants.USUARIO_SESSION);
      String unidadOrganicaActiva = usuario.getUnidadOrganicaActiva().split(" - ")[0];
      String descripcion = usuario.getUnidadOrganicaActiva().split(" - ")[1];

      ObjetoInsideUsuario usuarioAlta = new ObjetoInsideUsuario();
      usuarioAlta.setUnidadOrganicaActiva(unidadOrganicaActiva);
      usuarioAlta.setNombreUnidadOrganicaActiva(descripcion);

      usuarioAlta.setNif(nif);
      usuarioAlta.setNumeroProcedimiento(numeroProcedimiento);
      List<ObjetoInsideRol> insideRoles = insideService.getInsideRoles();
      for (int i = 0; i < insideRoles.size(); i++) {
        if (insideRoles.get(i).getId() == Integer.parseInt(rol)) {
          usuarioAlta.setRol(insideRoles.get(i));
          break;
        }
      }


      List<ObjetoNumeroProcedimiento> listaNumeroProcedimiento =
          insideService.getNumeroProcedimientoList();

      // crear un objeto vacio de procedimiento
      ObjetoNumeroProcedimiento vacio = new ObjetoNumeroProcedimiento();
      vacio.setId(0);
      vacio.setNumeroProcedimiento("");

      usuarioAlta.setObjNumeroProcedimiento(vacio);
      for (int i = 0; i < listaNumeroProcedimiento.size(); i++) {
        if (listaNumeroProcedimiento.get(i).getNumeroProcedimiento().equals(numeroProcedimiento)) {
          usuarioAlta.setObjNumeroProcedimiento(listaNumeroProcedimiento.get(i));
          break;
        }
      }


      retorno.addObject("usuarioAlta", usuarioAlta);
      retorno.addObject("rolesDisponibles", cargaComboRoles(locale));
      retorno.addObject("numeroProcedimientosDisponibles", cargaNumeroProcedimientos(locale));

    } catch (Exception e) {
      logger.error(
          "UsuarioManageController.altaUsuarioWebInside --> Error alta usuario web inside.", e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      retorno.addObject(MENSAJE_USU, mensaje);
    }
    return retorno;
  }


  @RequestMapping(value = "/guardarModificacionUsuarioWebInsidePost", method = RequestMethod.POST)
  public ModelAndView guardarModificacionUsuarioWebInsidePost(ObjetoInsideUsuario usuarioAlta,
      HttpServletRequest request, Locale locale) {

    logger.debug("Iniciado UsuarioManageController.guardarModificacionUsuarioWebInsidePost");

    ModelAndView retorno = new ModelAndView("listaUsuariosEnUnidadOrganica");
    MessageObject mensaje;

    try {
      // borrar relacion unidad Usuario
      insideService.eliminarUnidadUsuario(usuarioAlta.getUnidadOrganicaActiva(),
          usuarioAlta.getNumeroProcedimiento(), usuarioAlta.getNif());

      ObjetoInsideUsuario usuarioDadoDeAlta = insideService.altaUsuario(usuarioAlta);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_INFO,
          "El NIF: " + usuarioDadoDeAlta.getNif() + " ha sido modificado correctamente.");
      retorno.addObject(MENSAJE_USU, mensaje);

      // volvemos a la lista de usuarios
      ObjetoInsideUsuario usuarioEnSesion =
          (ObjetoInsideUsuario) request.getSession().getAttribute(WebConstants.USUARIO_SESSION);
      List<ObjetoInsideUsuario> usuariosEnUnidadOrganica =
          insideService.getUsuariosUnidadOrganica(usuarioEnSesion, locale);

      retorno.addObject("data", usuariosEnUnidadOrganica);

    } catch (Exception e) {
      logger.debug(e);
      mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, "Error al modificar usuario.");
      retorno.addObject(MENSAJE_USU, mensaje);
      usuarioAlta.setNif("");
      usuarioAlta.setNumeroProcedimiento("");
      retorno.addObject("rolesDisponibles", this.getListadoComboRoles());
      retorno.addObject("numeroProcedimientosDisponibles", this.getListadoNumeroProcedimientos());
      retorno.addObject("usuarioAlta", usuarioAlta);

    }

    logger.debug("Finalizado UsuarioManageController.guardarModificacionUsuarioWebInsidePost");
    return retorno;
  }


}
