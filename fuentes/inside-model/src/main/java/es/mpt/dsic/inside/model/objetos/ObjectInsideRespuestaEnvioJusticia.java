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

package es.mpt.dsic.inside.model.objetos;


public class ObjectInsideRespuestaEnvioJusticia {


  private String AuditoriaEsb_aplicacion;
  private String AuditoriaEsb_modulo;
  private String AuditoriaEsb_servicio;
  private String AuditoriaEsb_marcaTiempo;
  private String ack;
  private String codigoEnvio;
  private String mensaje;
  private String codigoUnidadOrganoRemitente;

  public ObjectInsideRespuestaEnvioJusticia() {}

  public ObjectInsideRespuestaEnvioJusticia(String AuditoriaEsb_aplicacion,
      String AuditoriaEsb_modulo, String AuditoriaEsb_servicio, String AuditoriaEsb_marcaTiempo,
      String ack, String codigoEnvio, String mensaje) {

    this.AuditoriaEsb_aplicacion = AuditoriaEsb_aplicacion;
    this.AuditoriaEsb_modulo = AuditoriaEsb_modulo;
    this.AuditoriaEsb_servicio = AuditoriaEsb_servicio;
    this.AuditoriaEsb_marcaTiempo = AuditoriaEsb_marcaTiempo;
    this.ack = ack;
    this.codigoEnvio = codigoEnvio;
    this.mensaje = mensaje;

  }



  public String getAuditoriaEsb_aplicacion() {
    return AuditoriaEsb_aplicacion;
  }

  public void setAuditoriaEsb_aplicacion(String auditoriaEsb_aplicacion) {
    AuditoriaEsb_aplicacion = auditoriaEsb_aplicacion;
  }

  public String getAuditoriaEsb_modulo() {
    return AuditoriaEsb_modulo;
  }

  public void setAuditoriaEsb_modulo(String auditoriaEsb_modulo) {
    AuditoriaEsb_modulo = auditoriaEsb_modulo;
  }


  public String getAuditoriaEsb_servicio() {
    return AuditoriaEsb_servicio;
  }

  public void setAuditoriaEsb_servicio(String auditoriaEsb_servicio) {
    AuditoriaEsb_servicio = auditoriaEsb_servicio;
  }


  public String getAuditoriaEsb_marcaTiempo() {
    return AuditoriaEsb_marcaTiempo;
  }

  public void setAuditoriaEsb_marcaTiempo(String auditoriaEsb_marcaTiempo) {
    AuditoriaEsb_marcaTiempo = auditoriaEsb_marcaTiempo;
  }


  public String getAck() {
    return ack;
  }

  public void setAck(String ack) {
    this.ack = ack;
  }


  public String getCodigoEnvio() {
    return codigoEnvio;
  }

  public void setCodigoEnvio(String codigoEnvio) {
    this.codigoEnvio = codigoEnvio;
  }


  public String getMensaje() {
    return mensaje;
  }

  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }


  public String getCodigoUnidadOrganoRemitente() {
    return codigoUnidadOrganoRemitente;
  }

  public void setCodigoUnidadOrganoRemitente(String codigoUnidadOrganoRemitente) {
    this.codigoUnidadOrganoRemitente = codigoUnidadOrganoRemitente;
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer("Entity ObjectInsideRespuestaEnvioJusticia=[");
    String coma = ", ";

    sb.append("AuditoriaEsb_aplicacion=" + AuditoriaEsb_aplicacion + coma);
    sb.append("AuditoriaEsb_modulo=" + AuditoriaEsb_modulo + coma);
    sb.append("AuditoriaEsb_servicio=" + AuditoriaEsb_servicio + coma);
    sb.append("AuditoriaEsb_marcaTiempo=" + AuditoriaEsb_marcaTiempo + coma);
    sb.append("ack=" + ack + coma);
    sb.append("codigoEnvio=" + codigoEnvio + coma);
    sb.append("mensaje=" + mensaje + coma);
    sb.append("codigoUnidadOrganoRemitente=" + codigoUnidadOrganoRemitente + coma);

    sb.append("]");
    return sb.toString();
  }

}
