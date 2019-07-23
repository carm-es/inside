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
@Table(name = "SolicitudAccesoExpediente")
public class SolicitudAccesoExpediente implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  private Integer id;
  private String dir3Peticionario;
  private String idPeticion;
  private String usuarioPeticionario;
  private String asunto;
  private String idExpedienteSolicitado;
  private String dir3ExpedienteSolicitado;
  private Integer codigoSia;
  private String endpointRemitente;
  private Date fechaPeticion;
  private String resultado;
  private String envioCorrecto;
  private String solicitudTokenDenegada;
  private byte[] metadatos;
  private String nifUsuarioGeneraToken;

  @Id
  @TableGenerator(name = "GeneradorPk_SolicitudAccesoExpediente", table = "GeneradorClaves",
      pkColumnName = "GenName", valueColumnName = "GenValue",
      pkColumnValue = "GEN_SolicitudAccesoExpediente", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.TABLE,
      generator = "GeneradorPk_SolicitudAccesoExpediente")
  @Column(name = "id", unique = true, nullable = false)
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Column(name = "Id_Peticion", nullable = false)
  public String getIdPeticion() {
    return idPeticion;
  }

  public void setIdPeticion(String idPeticion) {
    this.idPeticion = idPeticion;
  }

  @Column(name = "Usuario_Peticionario")
  public String getUsuarioPeticionario() {
    return usuarioPeticionario;
  }

  public void setUsuarioPeticionario(String usuarioPeticionario) {
    this.usuarioPeticionario = usuarioPeticionario;
  }

  @Column(name = "Asunto", nullable = false)
  public String getAsunto() {
    return asunto;
  }

  public void setAsunto(String asunto) {
    this.asunto = asunto;
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

  @Column(name = "DIR3_Peticionario", nullable = false)
  public String getDir3Peticionario() {
    return dir3Peticionario;
  }

  public void setDir3Peticionario(String dir3Peticionario) {
    this.dir3Peticionario = dir3Peticionario;
  }

  @Column(name = "IdExpedienteSolicitado")
  public String getIdExpedienteSolicitado() {
    return idExpedienteSolicitado;
  }

  public void setIdExpedienteSolicitado(String idExpedienteSolicitado) {
    this.idExpedienteSolicitado = idExpedienteSolicitado;
  }

  @Column(name = "CodigoSia", nullable = false)
  public Integer getCodigoSia() {
    return codigoSia;
  }

  public void setCodigoSia(Integer codigoSia) {
    this.codigoSia = codigoSia;
  }

  @Column(name = "EndpointRemitente", nullable = false)
  public String getEndpointRemitente() {
    return endpointRemitente;
  }

  public void setEndpointRemitente(String endpointRemitente) {
    this.endpointRemitente = endpointRemitente;
  }

  @Column(name = "EnvioCorrecto")
  public String getEnvioCorrecto() {
    return envioCorrecto;
  }

  public void setEnvioCorrecto(String envioCorrecto) {
    this.envioCorrecto = envioCorrecto;
  }

  @Column(name = "SolicitudTokenDenegada")
  public String getSolicitudTokenDenegada() {
    return solicitudTokenDenegada;
  }

  public void setSolicitudTokenDenegada(String solicitudTokenDenegada) {
    this.solicitudTokenDenegada = solicitudTokenDenegada;
  }

  @Lob
  @Column(name = "Metadatos", nullable = false)
  public byte[] getMetadatos() {
    return metadatos;
  }

  public void setMetadatos(byte[] metadatos) {
    this.metadatos = metadatos;
  }

  @Column(name = "Dir3ExpedienteSolicitado")
  public String getDir3ExpedienteSolicitado() {
    return dir3ExpedienteSolicitado;
  }

  public void setDir3ExpedienteSolicitado(String dir3ExpedienteSolicitado) {
    this.dir3ExpedienteSolicitado = dir3ExpedienteSolicitado;
  }

  @Column(name = "NifUsuarioGeneraToken")
  public String getNifUsuarioGeneraToken() {
    return nifUsuarioGeneraToken;
  }

  public void setNifUsuarioGeneraToken(String nifUsuarioGeneraToken) {
    this.nifUsuarioGeneraToken = nifUsuarioGeneraToken;
  }


}
