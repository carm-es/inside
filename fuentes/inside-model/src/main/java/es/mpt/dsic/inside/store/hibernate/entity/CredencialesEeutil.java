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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * InsideWsAplicacion generated by hbm2java
 */
@Entity
@Table(name = "CredencialesEeutil")
public class CredencialesEeutil implements java.io.Serializable {

  /**
   * serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  private Integer id;
  private Integer idAplicacionInterna;
  private String idAplicacion;
  private String password;

  public CredencialesEeutil() {}

  public CredencialesEeutil(Integer id) {
    this.id = id;
  }

  public CredencialesEeutil(Integer id, Integer idAplicacionInterna, String idAplicacion,
      String password) {
    this.id = id;
    this.idAplicacionInterna = idAplicacionInterna;
    this.idAplicacion = idAplicacion;
    this.password = password;
  }

  @Id
  @TableGenerator(name = "GeneradorPk_CredencialesEeutil", table = "GeneradorClaves",
      pkColumnName = "GenName", valueColumnName = "GenValue",
      pkColumnValue = "GEN_CredencialesEeutil", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "GeneradorPk_CredencialesEeutil")
  @Column(name = "id", unique = true, nullable = false)
  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Column(name = "idAplicacionInterna", nullable = false)
  public Integer getIdAplicacionInterna() {
    return idAplicacionInterna;
  }

  public void setIdAplicacionInterna(Integer idAplicacionInterna) {
    this.idAplicacionInterna = idAplicacionInterna;
  }

  @Column(name = "idAplicacion", nullable = false)
  public String getIdAplicacion() {
    return idAplicacion;
  }

  public void setIdAplicacion(String idAplicacion) {
    this.idAplicacion = idAplicacion;
  }

  @Column(name = "password", nullable = false)
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
