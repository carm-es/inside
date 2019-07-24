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


public class ObjetoAuditoriaAcceso {

  private String id;
  private String usuario;
  private String unidadOrganicaUsuario;
  private String identificador;
  private String unidadOrganica;
  private Date fechaAcceso;
  private String idUnidadOrganica;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUsuario() {
    return usuario;
  }

  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }

  public String getUnidadOrganicaUsuario() {
    return unidadOrganicaUsuario;
  }

  public void setUnidadOrganicaUsuario(String unidadOrganicaUsuario) {
    this.unidadOrganicaUsuario = unidadOrganicaUsuario;
  }

  public String getIdentificador() {
    return identificador;
  }

  public void setIdentificador(String identificador) {
    this.identificador = identificador;
  }

  public String getUnidadOrganica() {
    return unidadOrganica;
  }

  public void setUnidadOrganica(String unidadOrganica) {
    this.unidadOrganica = unidadOrganica;
  }

  public Date getFechaAcceso() {
    return fechaAcceso;
  }

  public void setFechaAcceso(Date fechaAcceso) {
    this.fechaAcceso = fechaAcceso;
  }

  public String getIdUnidadOrganica() {
    return idUnidadOrganica;
  }

  public void setIdUnidadOrganica(String idUnidadOrganica) {
    this.idUnidadOrganica = idUnidadOrganica;
  }

}
