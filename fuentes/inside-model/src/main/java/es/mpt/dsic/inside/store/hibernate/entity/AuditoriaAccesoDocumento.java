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
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "AuditoriaAccesoDocumento")
public class AuditoriaAccesoDocumento implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  private Integer id;
  private String dir3Peticionario;
  private String idPeticion;
  private String usuarioPeticionario;
  private String idDocumento;
  private Date fecha;
  private Integer idDir3;

  @Id
  @GenericGenerator(name = "GeneradorPk_AuditoriaAccesoDocumento",
      strategy = "es.carm.InsideGeneratorID",
      parameters = @Parameter(name = "sequence", value = "GEN_AuditoriaAccesoDocumento"))
  @GeneratedValue(generator = "GeneradorPk_AuditoriaAccesoDocumento")
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

  @Column(name = "DIR3_Peticionario")
  public String getDir3Peticionario() {
    return dir3Peticionario;
  }

  public void setDir3Peticionario(String dir3Peticionario) {
    this.dir3Peticionario = dir3Peticionario;
  }

  @Column(name = "Id_Documento", nullable = false)
  public String getIdDocumento() {
    return idDocumento;
  }

  public void setIdDocumento(String idDocumento) {
    this.idDocumento = idDocumento;
  }

  @Column(name = "Fecha", nullable = false)
  public Date getFecha() {
    return fecha;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }

  @Column(name = "idDir3")
  public Integer getIdDir3() {
    return idDir3;
  }

  public void setIdDir3(Integer idDir3) {
    this.idDir3 = idDir3;
  }

  @Column(name = "Usuario_Peticionario")
  public String getUsuarioPeticionario() {
    return usuarioPeticionario;
  }

  public void setUsuarioPeticionario(String usuarioPeticionario) {
    this.usuarioPeticionario = usuarioPeticionario;
  }

}
