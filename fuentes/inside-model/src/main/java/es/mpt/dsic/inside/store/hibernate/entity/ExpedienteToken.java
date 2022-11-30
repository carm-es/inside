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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


@Entity
@Table(name = "ExpedienteToken")
public class ExpedienteToken implements java.io.Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;


  private Integer id;
  private UsuarioInside usuario;
  private String identificador;
  private String csv;
  private String uuid;

  private Integer versionExpediente;

  private String dir3;
  private String asunto;
  private String nifs;
  private Date fechaCaducidad;

  private Date fechaCreacion;

  public ExpedienteToken() {}

  public ExpedienteToken(Integer id) {
    this.id = id;
  }

  public ExpedienteToken(Integer id, UsuarioInside usuario, String identificador, String csv,
      String uuid) {
    super();
    this.id = id;
    this.usuario = usuario;
    this.identificador = identificador;
    this.csv = csv;
    this.uuid = uuid;
  }

  public ExpedienteToken(Integer id, UsuarioInside usuario, String identificador, String csv,
      String uuid, Date fechaCaducidad, String dir3, String asunto, String nifs) {
    super();
    this.id = id;
    this.usuario = usuario;
    this.identificador = identificador;
    this.csv = csv;
    this.uuid = uuid;
    this.fechaCaducidad = fechaCaducidad;
    this.dir3 = dir3;
    this.asunto = asunto;
    this.nifs = nifs;
  }

  @Id
  // @GeneratedValue(strategy = IDENTITY)
  @GenericGenerator(name = "GeneradorPk_ExpedienteToken", strategy = "es.carm.InsideGeneratorID",
      parameters = @Parameter(name = "sequence", value = "GEN_ExpedienteToken"))
  @GeneratedValue(generator = "GeneradorPk_ExpedienteToken")
  @Column(name = "id", unique = true, nullable = false)
  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_usuario")
  public UsuarioInside getUsuario() {
    return usuario;
  }

  public void setUsuario(UsuarioInside usuario) {
    this.usuario = usuario;
  }

  @Column(name = "identificador", nullable = false)
  public String getIdentificador() {
    return identificador;
  }

  public void setIdentificador(String identificador) {
    this.identificador = identificador;
  }

  @Column(name = "csv", nullable = false)
  public String getCsv() {
    return csv;
  }

  public void setCsv(String csv) {
    this.csv = csv;
  }

  @Column(name = "uuid", nullable = false)
  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public Integer getVersionExpediente() {
    return versionExpediente;
  }

  public void setVersionExpediente(Integer versionExpediente) {
    this.versionExpediente = versionExpediente;
  }

  @Column(name = "dir3")
  public String getDir3() {
    return dir3;
  }

  public void setDir3(String dir3) {
    this.dir3 = dir3;
  }

  @Column(name = "asunto")
  public String getAsunto() {
    return asunto;
  }

  public void setAsunto(String asunto) {
    this.asunto = asunto;
  }

  @Column(name = "nifs")
  public String getNifs() {
    return nifs;
  }

  public void setNifs(String nifs) {
    this.nifs = nifs;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "fechaCaducidad", length = 19)
  public Date getFechaCaducidad() {
    return fechaCaducidad;
  }

  public void setFechaCaducidad(Date fechaCaducidad) {
    this.fechaCaducidad = fechaCaducidad;
  }

  @Column(name = "fechaCreacion")
  public Date getFechaCreacion() {
    return fechaCreacion;
  }

  public void setFechaCreacion(Date fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  }

}
