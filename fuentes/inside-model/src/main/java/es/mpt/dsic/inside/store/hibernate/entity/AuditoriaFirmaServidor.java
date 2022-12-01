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
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "AuditoriaFirmaServidor")
public class AuditoriaFirmaServidor implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  private Integer id;
  private String elemento;
  private String identificador;
  private String usuario;
  private Date createdAt;

  public AuditoriaFirmaServidor() {}

  @Id
  @GenericGenerator(name = "GeneradorPk_UnidadUsuario", strategy = "es.carm.InsideGeneratorID",
      parameters = @Parameter(name = "sequence", value = "GEN_UnidadUsuario"))
  @GeneratedValue(generator = "GeneradorPk_UnidadUsuario")
  @Column(name = "id", unique = true, nullable = false)
  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Column(name = "elemento", nullable = false)
  public String getElemento() {
    return elemento;
  }

  public void setElemento(String elemento) {
    this.elemento = elemento;
  }

  @Column(name = "identificador", nullable = false)
  public String getIdentificador() {
    return identificador;
  }

  public void setIdentificador(String identificador) {
    this.identificador = identificador;
  }

  @Column(name = "usuario", nullable = false)
  public String getUsuario() {
    return usuario;
  }

  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_at", length = 19)
  public Date getCreatedAt() {
    return this.createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }
}
