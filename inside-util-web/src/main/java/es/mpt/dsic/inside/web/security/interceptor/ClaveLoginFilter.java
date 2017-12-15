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

package es.mpt.dsic.inside.web.security.interceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import es.mpt.dsic.inside.configuration.ConstantsClave;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideRol;
import es.mpt.dsic.inside.model.objetos.usuario.ObjetoInsideUsuario;
import es.mpt.dsic.inside.service.InSideService;
import es.mpt.dsic.inside.service.exception.InSideServiceException;
import es.mpt.dsic.inside.service.store.exception.InsideServiceStoreException;
import es.mpt.dsic.inside.service.users.InsideUsersService;
import es.mpt.dsic.inside.service.users.exception.InsideUsersServiceException;
import es.mpt.dsic.inside.service.util.UnidadOrganicaRolPortales;
import es.mpt.dsic.inside.web.security.authentication.UserAuthentication;
import es.mpt.dsic.inside.web.util.RolesUsuarioAsignacionUtils;
import es.mpt.dsic.inside.web.util.WebConstants;
import eu.stork.peps.auth.commons.IPersonalAttributeList;
import eu.stork.peps.auth.commons.PEPSUtil;
import eu.stork.peps.auth.commons.PersonalAttribute;
import eu.stork.peps.auth.commons.STORKAuthnResponse;
import eu.stork.peps.auth.engine.STORKSAMLEngine;
import eu.stork.peps.exceptions.STORKSAMLEngineException;

/**
 * Filtro de acceso cuando la autenticación se hace a través de la plataforma Cl@ve
 * 
 * @author Alfredo Barquero
 *
 */
public class ClaveLoginFilter extends AbstractAuthenticationProcessingFilter {
	private static Logger logger = Logger.getLogger(ClaveLoginFilter.class);

	private Boolean validateUser;
	
	private Boolean validarUsuarioEnPortales;

	@Autowired
	private Environment env;

	@Autowired
	private InsideUsersService insideUsersService;
	
	@Autowired
	private InSideService insideService;
	
	public ClaveLoginFilter() {
		super("/accesoRedirectClave");
	}

	public ClaveLoginFilter(String defaultFilterProcessesUrl) {
		super(defaultFilterProcessesUrl);
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {

		logger.debug("login by clave");

		STORKAuthnResponse authnResponse = null;
		IPersonalAttributeList personalAttributeList = null;

		try {
			/* Recuperamos la respuesta de la aplicación */
			String samlResponse = request.getParameter(ConstantsClave.ATRIBUTO_SAML_RESPONSE);
			if (!StringUtils.isBlank(samlResponse)) {
				/* Decodificamos la respuesta SAML */
				byte[] decSamlToken = PEPSUtil.decodeSAMLToken(samlResponse);

				/* Obtenemos la instancia de SAMLEngine */
				STORKSAMLEngine engine = STORKSAMLEngine.getInstance(ConstantsClave.SP_CONF);

				/* Validamos el token SAML */

				authnResponse = engine.validateSTORKAuthnResponse(decSamlToken, request.getRemoteHost());
				/* CARM ### v2.0.7.1
				if (authnResponse.isFail()) {
					throw new BadCredentialsException("Error en la autenticacion");
				*/
				if (authnResponse.isFail()) { // falseamos autenticación clave para invitado
					return validarUsuarioDatosTablas(request, null, "01234567L");
				// CARM 2.0.7.1 ###	
				} else {
					List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
					

					/* Recuperamos los atributos */
					personalAttributeList = authnResponse.getPersonalAttributeList();

					PersonalAttribute identificadorAttribute = personalAttributeList.get(env.getProperty(ConstantsClave.PROPERTY_IDENTIFICADOR));
					List<String> identificadores = identificadorAttribute.getValue();
					for (String identificador : identificadores) {
						String id = identificador.split("/")[2];
						if (validateUser) 
						{
							// LLamar a portales para recibir unidadesorganicas y roles.
							if(validarUsuarioEnPortales)
							{
								return validarActualizandoCONDatosPortales(request, roles, id);
							}
							else
							{
								return validarUsuarioDatosTablas(request, roles, id);
							}
	

						} 
						else 
						{
							// G-INSIDE
							roles.add(new SimpleGrantedAuthority("USER_ROLE"));
							return new UserAuthentication(id, null, roles, null, id, null);
						}
					}
				}

			}
		} catch (InsideUsersServiceException e) {
			logger.error(e.getMessage());
			throw new BadCredentialsException("Error al validar usuario", e);
		} catch (STORKSAMLEngineException e) {
			logger.error(e.getMessage());
			throw new BadCredentialsException("Error al validar usuario", e);
		} catch (Exception e) {
			logger.error("ERROR");
			throw new BadCredentialsException("Error al validar usuario", e);
		}
		return null; // TODAVÍA NO HEMOS PASADO POR CLAVE
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

			
	private void actualizarUsuarioConDatosUnidadesRolesPortales(List<UnidadOrganicaRolPortales> portalesDir3, String nif) throws InSideServiceException
	{
		//borrar relacion unidad Usuario
		for (int i = 0; i < portalesDir3.size(); i++) 
		{
//			String unidadGuionROL = portalesDir3.get(i);
//			String unidadPortales = unidadGuionROL.split("-")[0];
//			int rolPortales = Integer.parseInt(unidadGuionROL.split("-")[1]);
			UnidadOrganicaRolPortales unidadOrganicaRolPortales = portalesDir3.get(i);
			String unidadPortales = unidadOrganicaRolPortales.getUnidadOrganicaPortales();
			int rolPortales = unidadOrganicaRolPortales.getRolPortales();
			
			//borrar relacion unidad Usuario si existe usuario
			ObjetoInsideUsuario usuarioExiste = insideUsersService.getUsuario(nif);	
			if(usuarioExiste!=null)
				insideService.eliminarUnidadUsuario(unidadPortales, null, nif);
			
			// creo objeto usuario
			ObjetoInsideUsuario usuarioAlta = new ObjetoInsideUsuario();
			usuarioAlta.setUnidadOrganicaActiva(unidadPortales);
			usuarioAlta.setActivo(true);
			usuarioAlta.setNif(nif);
			usuarioAlta.setNumeroProcedimiento(null);
			usuarioAlta.setRol(new ObjetoInsideRol(rolPortales));
			 
			//alta usuario para ese dir3
			ObjetoInsideUsuario usuarioDadoDeAlta = insideService.altaUsuario(usuarioAlta);
			
		}
	}
	
	
	
	private UserAuthentication validarUsuarioDatosTablas(HttpServletRequest request, List<GrantedAuthority> roles, String id) throws InsideUsersServiceException 
	{
		// INSIDE 
		ObjetoInsideUsuario usuario = insideUsersService.getUsuario(id);
		if (usuario != null) {								
			logger.debug("Usuario:" + id + " registrado en BD");
			request.getSession().setAttribute(WebConstants.USUARIO_SESSION, usuario);
			logger.info("ALTA_USUARIOS_ROLE : Alta y Modifica Exp y Docs. Alta usuarios. Remite a MJU.");
			logger.info("USER_ROLE : Alta y Modifica Exp y Docs. Remite a MJU.");
			logger.info("REMISION_JUSTICIA_ROLE : Consulta Exp y Docs. Remite a MJU.");
			logger.info("CONSULTA_ROLE : Consulta Exp y Docs.");
			logger.info("Se asigna rol: " + usuario.getRol().getDescripcion());
			
			roles = RolesUsuarioAsignacionUtils.asignarRolesUsuario(usuario);
			
//			roles.add(new SimpleGrantedAuthority(usuario.getRol().getDescripcion()));
//			if ("REMISION_JUSTICIA_ROLE".equals(usuario.getRol().getDescripcion())) {
//				roles.add(new SimpleGrantedAuthority("CONSULTA_ROLE"));
//			} else if ("ALTA_USUARIOS_ROLE".equals(usuario.getRol().getDescripcion()) || ("REDACTOR_ROLE".equals(usuario.getRol().getDescripcion())) ) {
//				roles.add(new SimpleGrantedAuthority("USER_ROLE"));
//			} 
			
			return new UserAuthentication(id, null, roles, null, id, usuario.getUnidadOrganicaActiva());
		} else {
			logger.debug("Usuario:" + id + " no registrado en BD");
			ObjetoInsideUsuario user = new ObjetoInsideUsuario();
			user.setActivo(true);
			user.setNif(id);
			request.getSession().setAttribute(WebConstants.USUARIO_SESSION, user);
			logger.debug("Usuario:" + id + "NO registrado en BD");
			
			roles = RolesUsuarioAsignacionUtils.asignarRolesUsuario("GUEST_ROLE");
			//roles.add(new SimpleGrantedAuthority("GUEST_ROLE"));
			return new UserAuthentication(id, null, roles, null, id, null);

		}
	}
	
	
	
	

	private UserAuthentication validarActualizandoCONDatosPortales(HttpServletRequest request, List<GrantedAuthority> roles, String id) throws InSideServiceException 
	{
		// llamar a portales para obtener datos del usuario sus dir3 (unidadesorganicas y roles)
		List<UnidadOrganicaRolPortales> portalesDir3 = insideService.getlistaUnidadesRolesPortales(id);
		if (portalesDir3!=null) 
		{
			// Actualiza los datos de las tablas con los datos recogidos de Portales
			actualizarUsuarioConDatosUnidadesRolesPortales(portalesDir3, id);
			return validarUsuarioDatosTablas(request, roles, id);
		}
		else
		{
			// SI PORTALES NO LO TIENE REGISTRADO ENTRAR MODO GUEST_ROLE
			logger.debug("Usuario:" + id + " no registrado en BD");
			ObjetoInsideUsuario user = new ObjetoInsideUsuario();
			user.setActivo(true);
			user.setNif(id);
			request.getSession().setAttribute(WebConstants.USUARIO_SESSION, user);
			logger.debug("Usuario:" + id + "NO registrado en BD");
			
			roles = RolesUsuarioAsignacionUtils.asignarRolesUsuario("GUEST_ROLE");
			//roles.add(new SimpleGrantedAuthority("GUEST_ROLE"));
			return new UserAuthentication(id, null, roles, null, id, null);
		
		}
		
	}
	

}
