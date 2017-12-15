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

package es.mpt.dsic.inside.web.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import es.mpt.dsic.inside.model.objetos.usuario.ObjetoInsideUsuario;

public class RolesUsuarioAsignacionUtils {

	private static final String ADMIN_MENSAJE_ROLE = "ADMIN_MENSAJE_ROLE";

	
	public static List<GrantedAuthority> asignarRolesUsuario(String descripcionRol) {
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();

		// se aniade el rol pasado por parametro
		roles.add(new SimpleGrantedAuthority(descripcionRol));

		// si se necesita aniadir alguno mas por el valor del rol pasado por
		// parametro.
		if ("REMISION_JUSTICIA_ROLE".equals(descripcionRol)) {
			roles.add(new SimpleGrantedAuthority("CONSULTA_ROLE"));
		} else if ("ALTA_USUARIOS_ROLE".equals(descripcionRol) || ("REDACTOR_ROLE".equals(descripcionRol))) {
			roles.add(new SimpleGrantedAuthority("USER_ROLE"));
		}

		return roles;
	}

	public static List<GrantedAuthority> asignarRolesUsuario(ObjetoInsideUsuario usuario) {
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();

		// se aniade el rol pasado por parametro
		roles.add(new SimpleGrantedAuthority(usuario.getRol().getDescripcion()));

		// si se necesita aniadir alguno mas por el valor del rol pasado por
		// parametro.
		if ("REMISION_JUSTICIA_ROLE".equals(usuario.getRol().getDescripcion())) {
			roles.add(new SimpleGrantedAuthority("CONSULTA_ROLE"));
		} else if ("ALTA_USUARIOS_ROLE".equals(usuario.getRol().getDescripcion())
				|| ("REDACTOR_ROLE".equals(usuario.getRol().getDescripcion()))) {
			roles.add(new SimpleGrantedAuthority("USER_ROLE"));
		}

		// usuario con permiso escritura mensaje
		if (usuario.isAdminMensaje()) {
			roles.add(new SimpleGrantedAuthority(ADMIN_MENSAJE_ROLE));
		}
		return roles;
	}

}
