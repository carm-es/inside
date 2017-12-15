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

import java.util.Calendar;
import java.util.Date;

public class ObjetoComunicacionTokenExpediente {

	private Integer id;
	private String rJdir3Remitente;
	private String idPeticion;
	private String usuarioPeticionario;
	private String asunto;
	private String tokenIdExpEni;
	private String tokenCSV;
	private String tokenUUID;
	private Date fechaPeticion;
	private String resultado;
	private String endpointRemitente;
	private String indiceExpediente;
	private String urlAccesoWeb;
	private String dir3Destino;
	private String rJNig;
	private String rJClaseProcedimiento;
	private String rJAnyoProcedimiento;
	private String rJNumeroProcedimiento;
	private String rJDescripcion;
	private String correcto;
	private Integer nIntentos;
	private String resultadoNotificado;
	private String codigoErrorInside;

	public String getCodigoErrorInside() {
		return codigoErrorInside;
	}

	public void setCodigoErrorInside(String codigoErrorInside) {
		this.codigoErrorInside = codigoErrorInside;
	}

	/**
	 * La fecha del próximo intento lo determina el número de intento en el que
	 * se encuentre. En caso de no haber intentos por tratarse de una
	 * comunicación no tratada por el job la fecha será la misma que el de la
	 * petición. En caso contrario se añaden minutos de modo exponencial 5.
	 */
	public Date getFechaProximoIntento() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getFechaPeticion());
		if (getnIntentos() != null) {
			calendar.add(Calendar.MINUTE, (int) Math.pow(getnIntentos(), 5));
		}
		return calendar.getTime();
	}

	/**
	 * Se limita a la definición de la columna en BBDD
	 */
	public void setResultado(String resultado) {
		if (resultado != null && resultado.length() > 255) {
			resultado = resultado.substring(0, 255);
		}
		this.resultado = resultado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDir3Remitente() {
		return rJdir3Remitente;
	}

	public void setDir3Remitente(String dir3Remitente) {
		this.rJdir3Remitente = dir3Remitente;
	}

	public String getIdPeticion() {
		return idPeticion;
	}

	public void setIdPeticion(String idPeticion) {
		this.idPeticion = idPeticion;
	}

	public String getUsuarioPeticionario() {
		return usuarioPeticionario;
	}

	public void setUsuarioPeticionario(String usuarioPeticionario) {
		this.usuarioPeticionario = usuarioPeticionario;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getTokenIdExpEni() {
		return tokenIdExpEni;
	}

	public void setTokenIdExpEni(String tokenIdExpEni) {
		this.tokenIdExpEni = tokenIdExpEni;
	}

	public String getTokenCSV() {
		return tokenCSV;
	}

	public void setTokenCSV(String tokenCSV) {
		this.tokenCSV = tokenCSV;
	}

	public String getTokenUUID() {
		return tokenUUID;
	}

	public void setTokenUUID(String tokenUUID) {
		this.tokenUUID = tokenUUID;
	}

	public Date getFechaPeticion() {
		return fechaPeticion;
	}

	public void setFechaPeticion(Date fechaPeticion) {
		this.fechaPeticion = fechaPeticion;
	}

	public String getEndpointRemitenteCorto() {
		return endpointRemitente != null && endpointRemitente.length() > 40 ? endpointRemitente.substring(0, 40)
				: endpointRemitente;
	}

	public String getEndpointRemitente() {
		return endpointRemitente;
	}

	public void setEndpointRemitente(String endpointRemitente) {
		this.endpointRemitente = endpointRemitente;
	}

	public String getIndiceExpediente() {
		return indiceExpediente;
	}

	public void setIndiceExpediente(String indiceExpediente) {
		this.indiceExpediente = indiceExpediente;
	}

	public String getUrlAccesoWeb() {
		return urlAccesoWeb;
	}

	public void setUrlAccesoWeb(String urlAccesoWeb) {
		this.urlAccesoWeb = urlAccesoWeb;
	}

	public String getDir3Destino() {
		return dir3Destino;
	}

	public void setDir3Destino(String dir3Destino) {
		this.dir3Destino = dir3Destino;
	}

	public String getRJNig() {
		return rJNig;
	}

	public void setRJNig(String rJNig) {
		this.rJNig = rJNig;
	}

	public String getRJClaseProcedimiento() {
		return rJClaseProcedimiento;
	}

	public void setRJClaseProcedimiento(String rJClaseProcedimiento) {
		this.rJClaseProcedimiento = rJClaseProcedimiento;
	}

	public String getRJAnyoProcedimiento() {
		return rJAnyoProcedimiento;
	}

	public void setRJAnyoProcedimiento(String rJAnyoProcedimiento) {
		this.rJAnyoProcedimiento = rJAnyoProcedimiento;
	}

	public String getRJNumeroProcedimiento() {
		return rJNumeroProcedimiento;
	}

	public void setRJNumeroProcedimiento(String rJNumeroProcedimiento) {
		this.rJNumeroProcedimiento = rJNumeroProcedimiento;
	}

	public String getRJDescripcion() {
		return rJDescripcion;
	}

	public void setRJDescripcion(String rJDescripcion) {
		this.rJDescripcion = rJDescripcion;
	}

	public String getResultado() {
		return resultado;
	}

	public String getCorrecto() {
		return correcto;
	}

	public void setCorrecto(String correcto) {
		this.correcto = correcto;
	}

	public Integer getnIntentos() {
		return nIntentos;
	}

	public void setnIntentos(Integer nIntentos) {
		this.nIntentos = nIntentos;
	}

	public String getResultadoNotificado() {
		return resultadoNotificado;
	}

	public void setResultadoNotificado(String resultadoNotificado) {
		this.resultadoNotificado = resultadoNotificado;
	}

}
