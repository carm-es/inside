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

package es.mpt.dsic.inside.store.hibernate.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "ComunicacionTokenExpediente")
public class ComunicacionTokenExpediente implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;	
	
	private Integer id;
	private String dir3Destino;
	private String rjDir3Remitente;
	private String idPeticion;
	private String usuarioPeticionario;
	private String asunto;
	private String tokenIdExpEni;
	private String tokenCSV;
	private String tokenUUID;
	private Date fechaPeticion;
	private String resultado;
	private String endpointRemitente;
	private byte[] indiceExpediente;
	private String urlAccesoWeb;
	private String rjNig;
	private String rjClaseProcedimiento;
	private String rjAnyoProcedimiento;
	private String rjNumeroProcedimiento;
	private String rjDescripcion;
	private String correcto;
	private Integer nIntentos;
	private Date fechaProximoIntento;
	private String resultadoNotificado;
	
	@Id
	@TableGenerator(name = "GeneradorPk_ComunicacionTokenExpediente",
    		table = "GeneradorClaves",
    		pkColumnName = "GenName",
    		valueColumnName = "GenValue",
    		pkColumnValue = "GEN_ComunicacionTokenExpediente",
    		allocationSize=1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "GeneradorPk_ComunicacionTokenExpediente")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "EndpointRemitente", nullable = false)
	public String getEndpointRemitente() {
		return endpointRemitente;
	}

	public void setEndpointRemitente(String endpointRemitente) {
		this.endpointRemitente = endpointRemitente;
	}

	@Column(name = "Dir3Destino", nullable = false)
	public String getDir3Destino() {
		return dir3Destino;
	}

	public void setDir3Destino(String dir3Destino) {
		this.dir3Destino = dir3Destino;
	}

	@Column(name = "IdPeticion", nullable = false)
	public String getIdPeticion() {
		return idPeticion;
	}

	public void setIdPeticion(String idPeticion) {
		this.idPeticion = idPeticion;
	}
	
	@Column(name = "Usuario_Peticionario", nullable = false)
	public String getUsuarioPeticionario() {
		return usuarioPeticionario;
	}

	public void setUsuarioPeticionario(String usuarioPeticionario) {
		this.usuarioPeticionario = usuarioPeticionario;
	}

	@Column(name = "Asunto")
	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	@Column(name = "TokenIdExpEni", nullable = false)
	public String getTokenIdExpEni() {
		return tokenIdExpEni;
	}

	public void setTokenIdExpEni(String tokenIdExpEni) {
		this.tokenIdExpEni = tokenIdExpEni;
	}

	@Column(name = "Token_CSV", nullable = false)
	public String getTokenCSV() {
		return tokenCSV;
	}

	public void setTokenCSV(String tokenCSV) {
		this.tokenCSV = tokenCSV;
	}

	@Column(name = "Token_UUID", nullable = false)
	public String getTokenUUID() {
		return tokenUUID;
	}

	public void setTokenUUID(String tokenUUID) {
		this.tokenUUID = tokenUUID;
	}

	@Column(name = "FechaPeticion", nullable = false)
	public Date getFechaPeticion() {
		return fechaPeticion;
	}

	public void setFechaPeticion(Date fechaPeticion) {
		this.fechaPeticion = fechaPeticion;
	}

	@Column(name = "Resultado")
	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	
	@Column(name = "UrlAccesoWeb")
	public String getUrlAccesoWeb() {
		return urlAccesoWeb;
	}

	public void setUrlAccesoWeb(String urlAccesoWeb) {
		this.urlAccesoWeb = urlAccesoWeb;
	}

	@Lob
	@Column(name = "IndiceExpediente", nullable = false)
	public byte[] getIndiceExpediente() {
		return indiceExpediente;
	}

	public void setIndiceExpediente(byte[] indiceExpediente) {
		this.indiceExpediente = indiceExpediente;
	}

	@Column(name = "RJNig")
	public String getRJNig() {
		return rjNig;
	}

	public void setRJNig(String rjNig) {
		this.rjNig = rjNig;
	}

	@Column(name = "RJClaseProcedimiento")
	public String getRJClaseProcedimiento() {
		return rjClaseProcedimiento;
	}

	public void setRJClaseProcedimiento(String rjClaseProcedimiento) {
		this.rjClaseProcedimiento = rjClaseProcedimiento;
	}

	@Column(name = "RJAnyoProcedimiento")
	public String getRJAnyoProcedimiento() {
		return rjAnyoProcedimiento;
	}

	public void setRJAnyoProcedimiento(String rjAnyoProcedimiento) {
		this.rjAnyoProcedimiento = rjAnyoProcedimiento;
	}

	@Column(name = "RJNumeroProcedimiento")
	public String getRJNumeroProcedimiento() {
		return rjNumeroProcedimiento;
	}

	public void setRJNumeroProcedimiento(String rjNumeroProcedimiento) {
		this.rjNumeroProcedimiento = rjNumeroProcedimiento;
	}

	@Column(name = "RJDescripcion")
	public String getRJDescripcion() {
		return rjDescripcion;
	}

	public void setRJDescripcion(String rjDescripcion) {
		this.rjDescripcion = rjDescripcion;
	}

	@Column(name = "RJDir3Remitente")
	public String getRJDir3Remitente() {
		return rjDir3Remitente;
	}

	public void setRJDir3Remitente(String rJdir3Remitente) {
		this.rjDir3Remitente = rJdir3Remitente;
	}

	@Column(name = "NIntentos")
	public Integer getNIntentos() {
		return nIntentos;
	}

	public void setNIntentos(Integer nIntentos) {
		this.nIntentos = nIntentos;
	}

	@Column(name = "Correcto")
	public String getCorrecto() {
		return correcto;
	}

	public void setCorrecto(String correcto) {
		this.correcto = correcto;
	}

	@Column(name = "FechaProximoIntento")
	public Date getFechaProximoIntento() {
		return fechaProximoIntento;
	}

	public void setFechaProximoIntento(Date fechaProximoIntento) {
		this.fechaProximoIntento = fechaProximoIntento;
	}

	@Column(name = "ResultadoNotificado")
	public String getResultadoNotificado() {
		return resultadoNotificado;
	}

	public void setResultadoNotificado(String resultadoNotificado) {
		this.resultadoNotificado = resultadoNotificado;
	}

}
