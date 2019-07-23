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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;
import es.mpt.dsic.eeutil.client.operFirma.model.InSideException;
import es.mpt.dsic.eeutil.client.operFirma.model.ResultadoValidarCertificado;
import es.mpt.dsic.infofirma.service.InfoFirmaService;
import es.mpt.dsic.infofirma.service.exception.InfoFirmaServiceException;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideRol;
import es.mpt.dsic.inside.model.objetos.usuario.ObjetoInsideUsuario;
import es.mpt.dsic.inside.service.InSideService;
import es.mpt.dsic.inside.service.exception.InSideServiceException;
import es.mpt.dsic.inside.service.store.exception.InsideServiceStoreException;
import es.mpt.dsic.inside.service.users.InsideUsersService;
import es.mpt.dsic.inside.service.users.exception.InsideUsersServiceException;
import es.mpt.dsic.inside.service.util.UnidadOrganicaRolPortales;
import es.mpt.dsic.inside.util.security.CertificateUtils;
import es.mpt.dsic.inside.web.security.authentication.UserAuthentication;
import es.mpt.dsic.inside.web.util.RolesUsuarioAsignacionUtils;
import es.mpt.dsic.inside.web.util.WebConstants;

@Component
public class MiniappletLoginFilter extends AbstractAuthenticationProcessingFilter {

  private static Logger logger = Logger.getLogger(MiniappletLoginFilter.class);

  private Boolean validateUser;

  private Boolean validarUsuarioEnPortales;

  @Autowired
  private InfoFirmaService infoFirmaService;

  @Autowired
  private InsideUsersService insideUsersService;

  @Autowired
  private InSideService insideService;

  public MiniappletLoginFilter(String defaultFilterProcessesUrl) {
    super(defaultFilterProcessesUrl);
  }

  public MiniappletLoginFilter() {
    super("/accesoCertificado");
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
    try {
      String signature = request.getParameter("signature");
      if (!StringUtils.isBlank(signature)) {
        logger.debug("login by certificado");
        byte[] cert = Base64.decodeBase64(signature.getBytes());
        String certificado;
        try {
          certificado = CertificateUtils.getCertificateFromSignXML(cert);
        } catch (Exception e) {
          throw new AuthenticationServiceException("Error en login");
        }

        ResultadoValidarCertificado validarCertificado =
            infoFirmaService.getInfoCertificate(certificado);

        if (validarCertificado.isValidado()) {
          List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();

          if (validateUser) {
            // LLamar a portales para recibir unidadesorganicas y roles.
            if (validarUsuarioEnPortales) {
              return validarActualizandoCONDatosPortales(request, roles, validarCertificado);
            } else {
              return validarUsuarioDatosTablas(request, roles, validarCertificado);
            }


          } else {
            // G-INSIDE
            roles.add(new SimpleGrantedAuthority("USER_ROLE"));
            return new UserAuthentication(validarCertificado.getIdUsuario(), null, roles,
                validarCertificado.getNumeroSerie(), validarCertificado.getIdUsuario(), null);
          }


        }
      }
    } catch (InfoFirmaServiceException e) {
      logger.error("Error al validar certificado");
    } catch (InSideException e) {
      logger.error("Error al validar certificado");
    } catch (InsideUsersServiceException e) {
      logger.error("Error al validar certificado");
    } catch (InsideServiceStoreException e) {
      logger.error("Error al obtener datos de portales");
    } catch (InSideServiceException e) {
      logger.error("Error al parsear el resultado de portales");
    }
    return null;
  }

  public Boolean getValidateUser() {
    return validateUser;
  }

  public void setValidateUser(Boolean validateUser) {
    this.validateUser = validateUser;
  }


  public Boolean getValidarUsuarioEnPortales() {
    return validarUsuarioEnPortales;
  }

  public void setValidarUsuarioEnPortales(Boolean validarUsuarioEnPortales) {
    this.validarUsuarioEnPortales = validarUsuarioEnPortales;
  }

  private void actualizarUsuarioConDatosUnidadesRolesPortales(
      List<UnidadOrganicaRolPortales> portalesDir3, String nif) throws InSideServiceException {
    // borrar relacion unidad Usuario
    for (int i = 0; i < portalesDir3.size(); i++) {
      // String unidadGuionROL = portalesDir3.get(i);
      // String unidadPortales = unidadGuionROL.split("-")[0];
      // int rolPortales = Integer.parseInt(unidadGuionROL.split("-")[1]);
      UnidadOrganicaRolPortales unidadOrganicaRolPortales = portalesDir3.get(i);
      String unidadPortales = unidadOrganicaRolPortales.getUnidadOrganicaPortales();
      int rolPortales = unidadOrganicaRolPortales.getRolPortales();

      // borrar relacion unidad Usuario si existe usuario
      ObjetoInsideUsuario usuarioExiste = insideUsersService.getUsuario(nif);
      if (usuarioExiste != null)
        insideService.eliminarUnidadUsuario(unidadPortales, null, nif);

      // creo objeto usuario
      ObjetoInsideUsuario usuarioAlta = new ObjetoInsideUsuario();
      usuarioAlta.setUnidadOrganicaActiva(unidadPortales);
      usuarioAlta.setActivo(true);
      usuarioAlta.setNif(nif);
      usuarioAlta.setNumeroProcedimiento(null);
      usuarioAlta.setRol(new ObjetoInsideRol(rolPortales));

      // alta usuario para ese dir3
      ObjetoInsideUsuario usuarioDadoDeAlta = insideService.altaUsuario(usuarioAlta);

    }
  }



  private UserAuthentication validarUsuarioDatosTablas(HttpServletRequest request,
      List<GrantedAuthority> roles, ResultadoValidarCertificado validarCertificado)
      throws InsideUsersServiceException {
    // RECUPERAMOS EL USUARIO CON LOS DATOS ACTUALIZADOS DE PORTALES
    ObjetoInsideUsuario usuario = insideUsersService.getUsuario(validarCertificado.getIdUsuario());
    if (usuario != null) {
      logger.debug("Usuario:" + validarCertificado.getIdUsuario() + " registrado en BD");
      request.getSession().setAttribute(WebConstants.USUARIO_SESSION, usuario);
      logger.info("ALTA_USUARIOS_ROLE : Alta y Modifica Exp y Docs. Alta usuarios. Remite a MJU.");
      logger.info("USER_ROLE : Alta y Modifica Exp y Docs. Remite a MJU.");
      logger.info("REMISION_JUSTICIA_ROLE : Consulta Exp y Docs. Remite a MJU.");
      logger.info("CONSULTA_ROLE : Consulta Exp y Docs.");
      logger.info("Se asigna rol: " + usuario.getRol().getDescripcion());

      roles = RolesUsuarioAsignacionUtils.asignarRolesUsuario(usuario);

      // roles.add(new SimpleGrantedAuthority(usuario.getRol().getDescripcion()));
      // if ("REMISION_JUSTICIA_ROLE".equals(usuario.getRol().getDescripcion())) {
      // roles.add(new SimpleGrantedAuthority("CONSULTA_ROLE"));
      // } else if ("ALTA_USUARIOS_ROLE".equals(usuario.getRol().getDescripcion()) ||
      // ("REDACTOR_ROLE".equals(usuario.getRol().getDescripcion())) ) {
      // roles.add(new SimpleGrantedAuthority("USER_ROLE"));
      // }

      return new UserAuthentication(validarCertificado.getIdUsuario(), null, roles,
          validarCertificado.getNumeroSerie(), validarCertificado.getIdUsuario(),
          usuario.getUnidadOrganicaActiva());
    } else {
      logger.debug("Usuario:" + validarCertificado.getIdUsuario() + " no registrado en BD");
      ObjetoInsideUsuario user = new ObjetoInsideUsuario();
      user.setActivo(true);
      user.setNif(validarCertificado.getIdUsuario());
      request.getSession().setAttribute(WebConstants.USUARIO_SESSION, user);
      logger.debug("Usuario:" + validarCertificado.getIdUsuario() + "NO registrado en BD");
      roles = RolesUsuarioAsignacionUtils.asignarRolesUsuario("GUEST_ROLE");
      // roles.add(new SimpleGrantedAuthority("GUEST_ROLE"));
      return new UserAuthentication(validarCertificado.getIdUsuario(), null, roles, null,
          validarCertificado.getIdUsuario(), null);
    }
  }


  private UserAuthentication validarActualizandoCONDatosPortales(HttpServletRequest request,
      List<GrantedAuthority> roles, ResultadoValidarCertificado validarCertificado)
      throws InSideServiceException {

    List<UnidadOrganicaRolPortales> portalesDir3 =
        insideService.getlistaUnidadesRolesPortales(validarCertificado.getIdUsuario());
    if (portalesDir3 != null) {
      // Actualiza los datos de las tablas con los datos recogidos de Portales
      actualizarUsuarioConDatosUnidadesRolesPortales(portalesDir3,
          validarCertificado.getIdUsuario());
      return validarUsuarioDatosTablas(request, roles, validarCertificado);
    } else {
      logger.debug("Usuario:" + validarCertificado.getIdUsuario() + " no registrado en BD");
      ObjetoInsideUsuario user = new ObjetoInsideUsuario();
      user.setActivo(true);
      user.setNif(validarCertificado.getIdUsuario());
      request.getSession().setAttribute(WebConstants.USUARIO_SESSION, user);
      logger.debug("Usuario:" + validarCertificado.getIdUsuario() + "NO registrado en BD");
      roles = RolesUsuarioAsignacionUtils.asignarRolesUsuario("GUEST_ROLE");
      // roles.add(new SimpleGrantedAuthority("GUEST_ROLE"));
      return new UserAuthentication(validarCertificado.getIdUsuario(), null, roles, null,
          validarCertificado.getIdUsuario(), null);

    }

  }

}
