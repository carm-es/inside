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

package es.mpt.dsic.inside.model.objetos.expediente;


import java.util.Date;

import es.mpt.dsic.inside.model.objetos.usuario.ObjetoInsideUsuario;


public class ObjetoAuditoriaToken {
	
	private ObjetoInsideUsuario usuario;
	private ObjetoExpedienteToken token;
	private byte[] acta;
	private byte[] justificante;
	private Date fechaUso;
	private String dir3UsuarioUso;
	private String nombreDir3UsuarioUso;
	private Integer idRolUso;
	private String descripcionRolUso;


	public ObjetoAuditoriaToken(ObjetoInsideUsuario usuario, ObjetoExpedienteToken token, byte[] acta, byte[] justificante,
			Date fechaUso, String dir3UsuarioUso, String nombreDir3UsuarioUso, Integer idRolUso, String descripcionRolUso) {
		super();
		this.usuario = usuario;
		this.token = token;
		this.acta = acta;
		this.justificante = justificante;
		this.fechaUso = fechaUso;
		this.dir3UsuarioUso = dir3UsuarioUso;
		this.nombreDir3UsuarioUso = nombreDir3UsuarioUso;
		this.idRolUso = idRolUso;
		this.descripcionRolUso = descripcionRolUso;
	}


	public ObjetoAuditoriaToken() {
		super();
	}


	public ObjetoInsideUsuario getUsuario() {
		return usuario;
	}



	public void setUsuario(ObjetoInsideUsuario usuario) {
		this.usuario = usuario;
	}



	public ObjetoExpedienteToken getToken() {
		return token;
	}



	public void setToken(ObjetoExpedienteToken token) {
		this.token = token;
	}



	public byte[] getActa() {
		return acta;
	}



	public void setActa(byte[] acta) {
		this.acta = acta;
	}



	public byte[] getJustificante() {
		return justificante;
	}



	public void setJustificante(byte[] justificante) {
		this.justificante = justificante;
	}


	public Date getFechaUso() {
		return fechaUso;
	}


	public void setFechaUso(Date fechaUso) {
		this.fechaUso = fechaUso;
	}


	public String getDir3UsuarioUso() {
		return dir3UsuarioUso;
	}


	public void setDir3UsuarioUso(String dir3UsuarioUso) {
		this.dir3UsuarioUso = dir3UsuarioUso;
	}


	public String getNombreDir3UsuarioUso() {
		return nombreDir3UsuarioUso;
	}


	public void setNombreDir3UsuarioUso(String nombreDir3UsuarioUso) {
		this.nombreDir3UsuarioUso = nombreDir3UsuarioUso;
	}


	public Integer getIdRolUso() {
		return idRolUso;
	}


	public void setIdRolUso(Integer idRolUso) {
		this.idRolUso = idRolUso;
	}


	public String getDescripcionRolUso() {
		return descripcionRolUso;
	}


	public void setDescripcionRolUso(String descripcionRolUso) {
		this.descripcionRolUso = descripcionRolUso;
	}


}
