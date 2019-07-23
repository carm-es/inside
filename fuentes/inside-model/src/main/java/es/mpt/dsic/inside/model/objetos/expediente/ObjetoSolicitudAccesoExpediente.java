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

package es.mpt.dsic.inside.model.objetos.expediente;


import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.StringUtils;


public class ObjetoSolicitudAccesoExpediente {

  private Integer id;
  private String dir3Peticionario;
  private String idPeticion;
  private String usuarioPeticionario;
  private String asunto;
  private String idExpedienteSolicitado;
  private String dir3ExpedienteSolicitado;
  private int codigoSia;
  private String endpointRemitente;
  private Date fechaPeticion;
  private String resultado;
  private String envioCorrecto;
  private String metadatos;
  private String solicitudTokenDenegada;
  private String pintarMetadatos;
  private String pintarMetadatosTitle;
  private String nifUsuarioGeneraToken;

  public boolean isResultadoCorrecto() {
    return StringUtils.equals(getResultado(), "OK");
  }

  public boolean isPendienteEnviar() {
    return StringUtils.equals(getResultado(), null);
  }

  public boolean isIdExpedienteSolicitadoVacio() {
    return StringUtils.isBlank(getIdExpedienteSolicitado());
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDir3Peticionario() {
    return dir3Peticionario;
  }

  public void setDir3Peticionario(String dir3Peticionario) {
    this.dir3Peticionario = dir3Peticionario;
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

  public String getIdExpedienteSolicitado() {
    return idExpedienteSolicitado;
  }

  public void setIdExpedienteSolicitado(String idExpedienteSolicitado) {
    this.idExpedienteSolicitado = idExpedienteSolicitado;
  }

  public int getCodigoSia() {
    return codigoSia;
  }

  public void setCodigoSia(int codigoSia) {
    this.codigoSia = codigoSia;
  }

  public String getEndpointRemitente() {
    return endpointRemitente;
  }

  public void setEndpointRemitente(String endpointRemitente) {
    this.endpointRemitente = endpointRemitente;
  }

  public Date getFechaPeticion() {
    return fechaPeticion;
  }

  public String getFechaPeticionCorta() {
    return new SimpleDateFormat("dd-MM").format(getFechaPeticion());
  }

  public void setFechaPeticion(Date fechaPeticion) {
    this.fechaPeticion = fechaPeticion;
  }

  public String getResultado() {
    return resultado;
  }

  public String getResultadoCorto() {
    return StringUtils.isNotEmpty(resultado) && resultado.length() > 20
        ? resultado.substring(0, 20) + "..."
        : resultado;
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

  public String getEnvioCorrecto() {
    return envioCorrecto;
  }

  public void setEnvioCorrecto(String envioCorrecto) {
    this.envioCorrecto = envioCorrecto;
  }

  public String getSolicitudTokenDenegada() {
    return solicitudTokenDenegada;
  }

  public void setSolicitudTokenDenegada(String solicitudTokenDenegada) {
    this.solicitudTokenDenegada = solicitudTokenDenegada;
  }

  public String getMetadatos() {
    return metadatos;
  }

  public void setMetadatos(String metadatos) {
    this.metadatos = metadatos;
  }

  public String getDir3ExpedienteSolicitado() {
    return dir3ExpedienteSolicitado;
  }

  public void setDir3ExpedienteSolicitado(String dir3ExpedienteSolicitado) {
    this.dir3ExpedienteSolicitado = dir3ExpedienteSolicitado;
  }

  public String getPintarMetadatos() {
    return pintarMetadatos;
  }

  public void setPintarMetadatos(String pintarMetadatos) {
    this.pintarMetadatos = pintarMetadatos;
  }

  public String getPintarMetadatosTitle() {
    return pintarMetadatosTitle;
  }

  public void setPintarMetadatosTitle(String pintarMetadatosTitle) {
    this.pintarMetadatosTitle = pintarMetadatosTitle;
  }

  public String getNifUsuarioGeneraToken() {
    return nifUsuarioGeneraToken;
  }

  public void setNifUsuarioGeneraToken(String nifUsuarioGeneraToken) {
    this.nifUsuarioGeneraToken = nifUsuarioGeneraToken;
  }



}
