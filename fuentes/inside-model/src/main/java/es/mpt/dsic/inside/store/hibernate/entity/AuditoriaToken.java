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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;



@Entity
@Table(name = "AuditoriaToken")
public class AuditoriaToken implements java.io.Serializable {


  private static final long serialVersionUID = 1L;

  private Integer id;
  private ExpedienteToken token;
  private String nifUsuarioUso;
  private byte[] acta;
  private byte[] justificante;
  private Date fechaUso;
  private String dir3UsuarioUso;
  private String nombreDir3UsuarioUso;
  private Integer idRolUso;
  private String descripcionRolUso;

  public AuditoriaToken() {}



  @Id
  // @GeneratedValue(strategy = IDENTITY)
  @TableGenerator(name = "GeneradorPk_AuditoriaToken", table = "GeneradorClaves",
      pkColumnName = "GenName", valueColumnName = "GenValue", pkColumnValue = "GEN_AuditoriaToken",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "GeneradorPk_AuditoriaToken")
  @Column(name = "id", unique = true, nullable = false)
  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }



  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_token")
  public ExpedienteToken getToken() {
    return token;
  }


  public void setToken(ExpedienteToken token) {
    this.token = token;
  }


  @Column(name = "NIFUSUARIOUSO")
  public String getNifUsuarioUso() {
    return nifUsuarioUso;
  }



  public void setNifUsuarioUso(String nifUsuarioUso) {
    this.nifUsuarioUso = nifUsuarioUso;
  }


  @Lob
  @Column(name = "ACTA")
  public byte[] getActa() {
    return acta;
  }



  public void setActa(byte[] acta) {
    this.acta = acta;
  }


  @Lob
  @Column(name = "JUSTIFICANTE")
  public byte[] getJustificante() {
    return justificante;
  }



  public void setJustificante(byte[] justificante) {
    this.justificante = justificante;
  }


  @Column(name = "FechaUso")
  public Date getFechaUso() {
    return fechaUso;
  }



  public void setFechaUso(Date fechaUso) {
    this.fechaUso = fechaUso;
  }


  @Column(name = "Dir3UsuarioUso")
  public String getDir3UsuarioUso() {
    return dir3UsuarioUso;
  }



  public void setDir3UsuarioUso(String dir3UsuarioUso) {
    this.dir3UsuarioUso = dir3UsuarioUso;
  }


  @Column(name = "NombreDir3UsuarioUso")
  public String getNombreDir3UsuarioUso() {
    return nombreDir3UsuarioUso;
  }



  public void setNombreDir3UsuarioUso(String nombreDir3UsuarioUso) {
    this.nombreDir3UsuarioUso = nombreDir3UsuarioUso;
  }


  @Column(name = "IdRolUso")
  public Integer getIdRolUso() {
    return idRolUso;
  }



  public void setIdRolUso(Integer idRolUso) {
    this.idRolUso = idRolUso;
  }


  @Column(name = "DescripcionRolUso")
  public String getDescripcionRolUso() {
    return descripcionRolUso;
  }



  public void setDescripcionRolUso(String descripcionRolUso) {
    this.descripcionRolUso = descripcionRolUso;
  }



}
