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
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "ExpedienteUnidad")
public class ExpedienteUnidad implements java.io.Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private Integer id;
  private String identificador;
  private Integer idUnidad;
  private Integer idProcedimiento;
  private String estadoExpediente;
  private Date fechaVersion;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id")
  private ExpedienteInside expediente;

  public ExpedienteUnidad() {}

  @Id
  @GenericGenerator(name = "GeneradorPk_ExpedienteUnidad", strategy = "es.carm.InsideGeneratorID",
      parameters = @Parameter(name = "sequence", value = "GEN_ExpedienteUnidad"))
  @GeneratedValue(generator = "GeneradorPk_ExpedienteUnidad")
  @Column(name = "id", unique = true, nullable = false)
  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * @return the unidad
   */
  @Column(name = "id_unidad", nullable = false)
  public Integer getIdUnidad() {
    return idUnidad;
  }

  /**
   * @param unidad the unidad to set
   */
  public void setIdUnidad(Integer idUnidad) {
    this.idUnidad = idUnidad;
  }

  @Column(name = "identificador", nullable = false)
  public String getIdentificador() {
    return identificador;
  }

  public void setIdentificador(String identificador) {
    this.identificador = identificador;
  }

  public String getEstadoExpediente() {
    return estadoExpediente;
  }

  public void setEstadoExpediente(String estadoExpediente) {
    this.estadoExpediente = estadoExpediente;
  }

  public Date getFechaVersion() {
    return fechaVersion;
  }

  public void setFechaVersion(Date fechaVersion) {
    this.fechaVersion = fechaVersion;
  }

  @Column(name = "id_procedimiento", nullable = true)
  public Integer getIdProcedimiento() {
    return idProcedimiento;
  }

  public void setIdProcedimiento(Integer idProcedimiento) {
    this.idProcedimiento = idProcedimiento;
  }

}
