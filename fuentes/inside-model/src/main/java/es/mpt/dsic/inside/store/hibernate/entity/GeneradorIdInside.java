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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "GeneradorIdInside")
public class GeneradorIdInside implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  private Integer id;
  private String elemento;
  private Integer idUnidad;
  private Integer numeroId;

  public GeneradorIdInside() {}

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

  @Column(name = "numeroId", nullable = false)
  public Integer getNumeroId() {
    return numeroId;
  }

  public void setNumeroId(Integer numeroId) {
    this.numeroId = numeroId;
  }

  @Column(name = "id_unidad", nullable = false)
  public Integer getIdUnidad() {
    return idUnidad;
  }

  public void setIdUnidad(Integer idUnidad) {
    this.idUnidad = idUnidad;
  }
}
