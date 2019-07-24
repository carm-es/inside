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
import es.mpt.dsic.inside.model.objetos.usuario.ObjetoInsideUsuario;


public class ObjetoExpedienteToken {

  private ObjetoInsideUsuario usuario;
  private String identificador;
  private String csv;
  private String uuid;
  private Integer versionExpediente;

  private String dir3;
  private String asunto;
  private String nifs;
  private Date fechaCaducidad;

  private String mailEnvioToken;

  private Date fechaCreacion;

  public ObjetoExpedienteToken() {
    super();
  }

  public ObjetoExpedienteToken(ObjetoInsideUsuario usuario, String identificador, String csv,
      String uuid) {
    super();
    this.usuario = usuario;
    this.identificador = identificador;
    this.csv = csv;
    this.uuid = uuid;
  }

  public ObjetoExpedienteToken(ObjetoInsideUsuario usuario, String identificador, String csv,
      String uuid, Date fechaCaducidad, String dir3, String asunto, String nifs) {
    super();
    this.usuario = usuario;
    this.identificador = identificador;
    this.csv = csv;
    this.uuid = uuid;
    this.fechaCaducidad = fechaCaducidad;
    this.dir3 = dir3;
    this.asunto = asunto;
    this.nifs = nifs;
  }

  public ObjetoExpedienteToken(ObjetoInsideUsuario usuario, String identificador, String csv,
      String uuid, Date fechaCaducidad, String dir3, String asunto, String nifs, String mail) {
    super();
    this.usuario = usuario;
    this.identificador = identificador;
    this.csv = csv;
    this.uuid = uuid;
    this.fechaCaducidad = fechaCaducidad;
    this.dir3 = dir3;
    this.asunto = asunto;
    this.nifs = nifs;
    this.mailEnvioToken = mail;
  }

  public ObjetoExpedienteToken(ObjetoInsideUsuario usuario, String identificador, String csv,
      String uuid, Integer versionExpediente, Date fechaCaducidad, String dir3, String asunto,
      String nifs, String mail) {
    super();
    this.usuario = usuario;
    this.identificador = identificador;
    this.csv = csv;
    this.uuid = uuid;
    this.versionExpediente = versionExpediente;
    this.fechaCaducidad = fechaCaducidad;
    this.dir3 = dir3;
    this.asunto = asunto;
    this.nifs = nifs;
    this.mailEnvioToken = mail;
  }

  public String getMailEnvioToken() {
    return mailEnvioToken;
  }

  public void setMailEnvioToken(String mailEnvioToken) {
    this.mailEnvioToken = mailEnvioToken;
  }

  public ObjetoInsideUsuario getUsuario() {
    return usuario;
  }

  public void setUsuario(ObjetoInsideUsuario usuario) {
    this.usuario = usuario;
  }

  public String getIdentificador() {
    return identificador;
  }

  public void setIdentificador(String identificador) {
    this.identificador = identificador;
  }

  public String getCsv() {
    return csv;
  }

  public void setCsv(String csv) {
    this.csv = csv;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public Integer getVersionExpediente() {
    return versionExpediente;
  }

  public void setVersionExpediente(Integer versionExpediente) {
    this.versionExpediente = versionExpediente;
  }

  public String getDir3() {
    return dir3;
  }

  public void setDir3(String dir3) {
    this.dir3 = dir3;
  }

  public String getAsunto() {
    return asunto;
  }

  public void setAsunto(String asunto) {
    this.asunto = asunto;
  }

  public String getNifs() {
    return nifs;
  }

  public void setNifs(String nifs) {
    this.nifs = nifs;
  }

  public Date getFechaCaducidad() {
    return fechaCaducidad;
  }

  public void setFechaCaducidad(Date fechaCaducidad) {
    this.fechaCaducidad = fechaCaducidad;
  }

  public Date getFechaCreacion() {
    return fechaCreacion;
  }

  public void setFechaCreacion(Date fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  }


}
