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

package es.mpt.dsic.inside.model.objetos.expediente;


import java.util.Date;


public class ObjetoAuditoriaAccesoDocumento {

  private Integer id;
  private String dir3Peticionario;
  private String idPeticion;
  private String usuarioPeticionario;
  private String idDocumento;
  private Date fecha;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDir3Peticionario() {
    return dir3Peticionario;
  }

  public void setDir3Peticionario(String dir3Peticionario) {
    this.dir3Peticionario = dir3Peticionario;
  }

  public String getIdPeticion() {
    return idPeticion;
  }

  public void setIdPeticion(String idPeticion) {
    this.idPeticion = idPeticion;
  }

  public String getUsuarioPeticionario() {
    return usuarioPeticionario;
  }

  public void setUsuarioPeticionario(String usuarioPeticionario) {
    this.usuarioPeticionario = usuarioPeticionario;
  }

  public String getIdDocumento() {
    return idDocumento;
  }

  public void setIdDocumento(String idDocumento) {
    this.idDocumento = idDocumento;
  }

  public Date getFecha() {
    return fecha;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }

}
