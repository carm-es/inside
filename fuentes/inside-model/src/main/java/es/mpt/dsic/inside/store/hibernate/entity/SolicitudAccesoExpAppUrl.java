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
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "SolicitudAccesoExpediente")
public class SolicitudAccesoExpAppUrl implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  private Integer id;
  private String dir3Padre;
  private String urlDestinoPeticion;

  @Id
  @TableGenerator(name = "GeneradorPk_SolicitudAccesoExpAppUrl", table = "GeneradorClaves",
      pkColumnName = "GenName", valueColumnName = "GenValue",
      pkColumnValue = "GEN_SolicitudAccesoExpAppUrl", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.TABLE,
      generator = "GeneradorPk_SolicitudAccesoExpAppUrl")
  @Column(name = "id", unique = true, nullable = false)
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Column(name = "Dir3Padre", nullable = false)
  public String getDir3Padre() {
    return dir3Padre;
  }

  public void setDir3Padre(String dir3Padre) {
    this.dir3Padre = dir3Padre;
  }

  @Column(name = "UrlDestinoPeticion", nullable = false)
  public String getUrlDestinoPeticion() {
    return urlDestinoPeticion;
  }

  public void setUrlDestinoPeticion(String urlDestinoPeticion) {
    this.urlDestinoPeticion = urlDestinoPeticion;
  }


}
