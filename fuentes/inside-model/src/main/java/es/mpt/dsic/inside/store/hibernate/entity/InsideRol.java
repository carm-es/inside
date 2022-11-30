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
@Table(name = "InsideRol")
public class InsideRol implements java.io.Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 2L;
  private Integer id;
  private String descripcion;
  private String descripcionLarga;


  public InsideRol() {}

  public InsideRol(Integer id) {
    this.id = id;
  }

  @Id
  @GenericGenerator(name = "GeneradorPk_InsideRol", strategy = "es.carm.InsideGeneratorID",
      parameters = @Parameter(name = "sequence", value = "GEN_InsideRol"))
  @GeneratedValue(generator = "GeneradorPk_InsideRol")
  @Column(name = "id", unique = true, nullable = false)
  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Column(name = "descripcion", nullable = false)
  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  @Column(name = "descripcionLarga", nullable = false)
  public String getDescripcionLarga() {
    return descripcionLarga;
  }

  public void setDescripcionLarga(String descripcionLarga) {
    this.descripcionLarga = descripcionLarga;
  }

}
