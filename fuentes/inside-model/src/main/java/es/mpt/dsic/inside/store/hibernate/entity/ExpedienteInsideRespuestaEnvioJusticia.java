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

// Generated 28-ago-2012 18:17:29 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.TableGenerator;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "ExpedienteInsideRespuestaEnvioJusticia")
public class ExpedienteInsideRespuestaEnvioJusticia implements java.io.Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private Integer id;
  private ExpedienteInside expedienteInside;
  private String AuditoriaEsb_aplicacion;
  private String AuditoriaEsb_modulo;
  private String AuditoriaEsb_servicio;
  private String AuditoriaEsb_marcaTiempo;
  private String ack;
  private String codigoEnvio;
  private String mensaje;
  private String codigoUnidadOrganoRemitente; // ALTER TABLE ExpedienteInsideRespuestaEnvioJusticia
                                              // ADD codigoUnidadOrganoRemitente VARCHAR(10);


  public ExpedienteInsideRespuestaEnvioJusticia() {}

  public ExpedienteInsideRespuestaEnvioJusticia(ExpedienteInside expedienteInside,
      String AuditoriaEsb_aplicacion, String AuditoriaEsb_modulo, String AuditoriaEsb_servicio,
      String AuditoriaEsb_marcaTiempo, String ack, String codigoEnvio, String mensaje,
      String codigoUnidadOrganoRemitente) {
    this.expedienteInside = expedienteInside;
    this.AuditoriaEsb_aplicacion = AuditoriaEsb_aplicacion;
    this.AuditoriaEsb_modulo = AuditoriaEsb_modulo;
    this.AuditoriaEsb_servicio = AuditoriaEsb_servicio;
    this.AuditoriaEsb_marcaTiempo = AuditoriaEsb_marcaTiempo;
    this.ack = ack;
    this.codigoEnvio = codigoEnvio;
    this.mensaje = mensaje;
    this.codigoUnidadOrganoRemitente = codigoUnidadOrganoRemitente;

  }

  @Id
  @TableGenerator(name = "GeneradorPk_ExpedienteInsideRespuestaEnvioJusticia",
      table = "GeneradorClaves", pkColumnName = "GenName", valueColumnName = "GenValue",
      pkColumnValue = "GEN_ExpedienteInsideRespuestaEnvioJusticia", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.TABLE,
      generator = "GeneradorPk_ExpedienteInsideRespuestaEnvioJusticia")
  @Column(name = "id", unique = true, nullable = false)
  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_expediente_envio")
  public ExpedienteInside getExpedienteInside() {
    return this.expedienteInside;
  }

  public void setExpedienteInside(ExpedienteInside expedienteInside) {
    this.expedienteInside = expedienteInside;
  }



  @Column(name = "AuditoriaEsb_aplicacion")
  public String getAuditoriaEsb_aplicacion() {
    return AuditoriaEsb_aplicacion;
  }

  public void setAuditoriaEsb_aplicacion(String auditoriaEsb_aplicacion) {
    AuditoriaEsb_aplicacion = auditoriaEsb_aplicacion;
  }

  @Column(name = "AuditoriaEsb_modulo")
  public String getAuditoriaEsb_modulo() {
    return AuditoriaEsb_modulo;
  }

  public void setAuditoriaEsb_modulo(String auditoriaEsb_modulo) {
    AuditoriaEsb_modulo = auditoriaEsb_modulo;
  }

  @Column(name = "AuditoriaEsb_servicio")
  public String getAuditoriaEsb_servicio() {
    return AuditoriaEsb_servicio;
  }

  public void setAuditoriaEsb_servicio(String auditoriaEsb_servicio) {
    AuditoriaEsb_servicio = auditoriaEsb_servicio;
  }

  @Column(name = "AuditoriaEsb_marcaTiempo")
  public String getAuditoriaEsb_marcaTiempo() {
    return AuditoriaEsb_marcaTiempo;
  }

  public void setAuditoriaEsb_marcaTiempo(String auditoriaEsb_marcaTiempo) {
    AuditoriaEsb_marcaTiempo = auditoriaEsb_marcaTiempo;
  }

  @Column(name = "ack")
  public String getAck() {
    return ack;
  }

  public void setAck(String ack) {
    this.ack = ack;
  }

  @Column(name = "codigoEnvio")
  public String getCodigoEnvio() {
    return codigoEnvio;
  }

  public void setCodigoEnvio(String codigoEnvio) {
    this.codigoEnvio = codigoEnvio;
  }

  @Column(name = "mensaje")
  public String getMensaje() {
    return mensaje;
  }

  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }

  @Column(name = "codigoUnidadOrganoRemitente")
  public String getCodigoUnidadOrganoRemitente() {
    return codigoUnidadOrganoRemitente;
  }

  public void setCodigoUnidadOrganoRemitente(String codigoUnidadOrganoRemitente) {
    this.codigoUnidadOrganoRemitente = codigoUnidadOrganoRemitente;
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer("Entity ExpedienteInsideRespuestaEnvioJusticia=[");
    String coma = ", ";

    sb.append("id=" + id + coma);
    sb.append("AuditoriaEsb_aplicacion=" + AuditoriaEsb_aplicacion + coma);
    sb.append("AuditoriaEsb_modulo=" + AuditoriaEsb_modulo + coma);
    sb.append("AuditoriaEsb_servicio=" + AuditoriaEsb_servicio + coma);
    sb.append("AuditoriaEsb_marcaTiempo=" + AuditoriaEsb_marcaTiempo + coma);
    sb.append("ack=" + ack + coma);
    sb.append("codigoEnvio=" + codigoEnvio + coma);
    sb.append("mensaje=" + mensaje);

    sb.append("]");
    return sb.toString();
  }

}
