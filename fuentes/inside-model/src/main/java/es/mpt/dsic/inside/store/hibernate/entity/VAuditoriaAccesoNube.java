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
@Table(name = "V_AUDITORIA_ACCESO_NUBE_IDS")
public class VAuditoriaAccesoNube implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private String id;
  private String nifPeticionario;
  private String nifRemitente;
  private String nifUsuarioAcceso;
  private String identificador;
  private Date fechaAcceso;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Column(name = "NIF_PETICIONARIO")
  public String getNifPeticionario() {
    return nifPeticionario;
  }

  public void setNifPeticionario(String nifPeticionario) {
    this.nifPeticionario = nifPeticionario;
  }

  @Column(name = "NIF_REMITENTE")
  public String getNifRemitente() {
    return nifRemitente;
  }

  public void setNifRemitente(String nifRemitente) {
    this.nifRemitente = nifRemitente;
  }

  @Column(name = "NIF_USUARIO_ACCESO")
  public String getNifUsuarioAcceso() {
    return nifUsuarioAcceso;
  }

  public void setNifUsuarioAcceso(String nifUsuarioAcceso) {
    this.nifUsuarioAcceso = nifUsuarioAcceso;
  }

  @Column(name = "ID_EXPEDIENTE")
  public String getIdentificador() {
    return identificador;
  }

  public void setIdentificador(String identificador) {
    this.identificador = identificador;
  }

  @Column(name = "FECHA_ACCESO")
  public Date getFechaAcceso() {
    return fechaAcceso;
  }

  public void setFechaAcceso(Date fechaAcceso) {
    this.fechaAcceso = fechaAcceso;
  }

}
