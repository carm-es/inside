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
import javax.persistence.Table;

@Entity
@Table(name = "V_DOCUMENTOS_METADATOS_ADICIONALES")
public class DocumentoMetadatosAdicionales implements java.io.Serializable {

  private static final long serialVersionUID = 2L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private String id;
  private Integer idUnidad;
  private String identificador;
  private Date fechaVersion;
  private String nombreValor;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Column(name = "ID_UNIDAD")
  public Integer getIdUnidad() {
    return idUnidad;
  }

  public void setIdUnidad(Integer idUnidad) {
    this.idUnidad = idUnidad;
  }

  @Column(name = "IDENTIFICADOR")
  public String getIdentificador() {
    return identificador;
  }

  public void setIdentificador(String identificador) {
    this.identificador = identificador;
  }

  @Column(name = "FECHAVERSION")
  public Date getFechaVersion() {
    return fechaVersion;
  }

  public void setFechaVersion(Date fechaVersion) {
    this.fechaVersion = fechaVersion;
  }

  @Column(name = "NOMBRE_VALOR")
  public String getNombreValor() {
    return nombreValor;
  }

  public void setNombreValor(String nombreValor) {
    this.nombreValor = nombreValor;
  }


}
