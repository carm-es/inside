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

package es.mpt.dsic.inside.model.objetos;

import java.util.ArrayList;
import java.util.List;

public class ObjetoInsideAplicacion implements Comparable<ObjetoInsideAplicacion> {
	
	public static String ROLE_ALTA_EXPEDIENTE = "ROLE_ALTA_EXPEDIENTE";	
	public static String ROLE_MODIFICA_EXPEDIENTE = "ROLE_MODIFICA_EXPEDIENTE";
	public static String ROLE_LEER_EXPEDIENTE = "ROLE_LEER_EXPEDIENTE";
	public static String ROLE_ALTA_DOCUMENTO = "ROLE_ALTA_DOCUMENTO";
	public static String ROLE_MODIFICA_DOCUMENTO = "ROLE_MODIFICA_DOCUMENTO";
	public static String ROLE_LEER_DOCUMENTO = "ROLE_LEER_DOCUMENTO";
	public static String ROLE_ADMINISTRAR_PERMISOS = "ROLE_ADMINISTRAR_PERMISOS";
	public static String EEUTIL_IDENTIFICADOR = "EEUTIL_IDENTIFICADOR";
	public static String EEUTIL_PASSWORD = "EEUTIL_PASSWORD";
	
	
	private String aplicacion;
	private String passwordHash;
	private List<String> roles;
	private boolean activa;
	private String email;
	private String telefono;
	private String responsable;
	private String unidadOrganicaActiva;
	private String numeroProcedimiento;
	private String serialNumerCert;
	
	private ObjetoCredencialEeutil credencialEeutil;
	
	public String getAplicacion() {
		return aplicacion;
	}
	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}
	public String getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	public boolean isActiva () {
		return this.activa;
	}
	public void setActiva (boolean activa) {		
		this.activa = activa;
	}
	public List<String> getRoles() {
		if (roles == null) {
			roles = new ArrayList<String> ();
		}
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getResponsable() {
		return responsable;
	}
	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}
	public String getUnidadOrganicaActiva() {
		return unidadOrganicaActiva;
	}
	public void setUnidadOrganicaActiva(String unidadOrganicaActiva) {
		this.unidadOrganicaActiva = unidadOrganicaActiva;
	}
	
	public String getSerialNumerCert() {
		return serialNumerCert;
	}
	public void setSerialNumerCert(String serialNumerCert) {
		this.serialNumerCert = serialNumerCert;
	}
	
	public ObjetoCredencialEeutil getCredencialEeutil() {
		return credencialEeutil;
	}
	public void setCredencialEeutil(ObjetoCredencialEeutil credencialEeutil) {
		this.credencialEeutil = credencialEeutil;
	}
	/**
	 * Devuelve >0 si el orden del argumento es menor que el del "this".
	 */
	public int compareTo (ObjetoInsideAplicacion o) {
		int retorno = 0;
		if (!this.getAplicacion().equals(o.getAplicacion())) {
			retorno = 1;
		}
		return retorno;
	}
	public String getNumeroProcedimiento() {
		return numeroProcedimiento;
	}
	public void setNumeroProcedimiento(String numeroProcedimiento) {
		this.numeroProcedimiento = numeroProcedimiento;
	}
}
