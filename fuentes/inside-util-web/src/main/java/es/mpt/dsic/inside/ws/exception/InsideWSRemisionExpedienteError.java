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

package es.mpt.dsic.inside.ws.exception;

public class InsideWSRemisionExpedienteError {

  private String codigo;

  private String descripcion;

  private String causa;

  private String accion;

  private String tipoError;

  public InsideWSRemisionExpedienteError(String codigo, String descripcion, String causa,
      String tipoError) {
    super();
    this.codigo = codigo;
    this.descripcion = descripcion;
    this.causa = causa;
    this.tipoError = tipoError;
  }

  public InsideWSRemisionExpedienteError(String codigo, String descripcion, String causa,
      String accion, String tipoError) {
    super();
    this.codigo = codigo;
    this.descripcion = descripcion;
    this.causa = causa;
    this.accion = accion;
    this.tipoError = tipoError;
  }

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public String getCausa() {
    return causa;
  }

  public void setCausa(String causa) {
    this.causa = causa;
  }

  public String getAccion() {
    return accion;
  }

  public void setAccion(String accion) {
    this.accion = accion;
  }

  public String getTipoError() {
    return tipoError;
  }

  public void setTipoError(String tipoError) {
    this.tipoError = tipoError;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("InsideWSRemisionExpedienteError [codigo=");
    builder.append(codigo);
    builder.append(", descripcion=");
    builder.append(descripcion);
    builder.append(", causa=");
    builder.append(causa);
    builder.append(", accion=");
    builder.append(accion);
    builder.append(", tipoError=");
    builder.append(tipoError);
    builder.append("]");
    return builder.toString();
  }



}
