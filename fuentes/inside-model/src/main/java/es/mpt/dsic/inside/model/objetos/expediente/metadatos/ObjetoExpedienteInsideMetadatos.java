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

package es.mpt.dsic.inside.model.objetos.expediente.metadatos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import es.mpt.dsic.inside.model.converter.InsideConverterUtils;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideMetadatos;

public class ObjetoExpedienteInsideMetadatos extends ObjetoInsideMetadatos {

  protected String versionNTI;

  protected List<String> organo;

  protected Calendar fechaAperturaExpediente;

  protected String clasificacion;

  protected ObjetoExpedienteInsideMetadatosEnumeracionEstados estado;

  protected List<String> interesado;

  public String getVersionNTI() {
    return versionNTI;
  }

  public void setVersionNTI(String value) {
    this.versionNTI = value;
  }

  public List<String> getOrgano() {
    if (this.organo == null) {
      this.organo = new ArrayList<String>();
    }
    return this.organo;
  }

  public void setOrgano(List<String> organo) {
    this.organo = organo;
  }

  public Calendar getFechaAperturaExpediente() {
    return fechaAperturaExpediente;
  }

  public void setFechaAperturaExpediente(Calendar calendar) {
    this.fechaAperturaExpediente = calendar;
  }

  public String getClasificacion() {
    return clasificacion;
  }

  public void setClasificacion(String value) {
    this.clasificacion = value;
  }

  public ObjetoExpedienteInsideMetadatosEnumeracionEstados getEstado() {
    return estado;
  }

  public void setEstado(ObjetoExpedienteInsideMetadatosEnumeracionEstados value) {
    this.estado = value;
  }

  public List<String> getInteresado() {
    if (this.interesado == null) {
      this.interesado = new ArrayList<String>();
    }
    return this.interesado;
  }

  public void setInteresado(List<String> interesados) {
    this.interesado = interesados;
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    ObjetoExpedienteInsideMetadatos objetoExpedienteInsideMetadatos =
        (ObjetoExpedienteInsideMetadatos) super.clone();

    objetoExpedienteInsideMetadatos.versionNTI = versionNTI;
    objetoExpedienteInsideMetadatos.identificadorObjetoInside = identificadorObjetoInside;
    objetoExpedienteInsideMetadatos.getOrgano().addAll(organo);
    if (fechaAperturaExpediente != null) {
      objetoExpedienteInsideMetadatos.fechaAperturaExpediente =
          (GregorianCalendar) fechaAperturaExpediente.clone();
    }
    objetoExpedienteInsideMetadatos.clasificacion = clasificacion;
    objetoExpedienteInsideMetadatos.estado = estado;

    return objetoExpedienteInsideMetadatos;
  }

  @Override
  public String toString() {
    String coma = ", ";
    StringBuffer sb = new StringBuffer("ObjetoExpedienteInsideMetadatos=[");
    sb.append("Clasificacion=" + this.clasificacion + coma);
    sb.append("VersionNTI=" + this.versionNTI + coma);
    sb.append("Estado=" + this.estado + coma);
    sb.append("Fecha Apertura Exp="
        + InsideConverterUtils.calendarToStringISO8601(this.fechaAperturaExpediente) + coma);
    sb.append("Organo=" + InsideConverterUtils.listaToString(this.organo) + coma);
    sb.append("Interesado=" + InsideConverterUtils.listaToString(this.interesado) + coma);
    sb.append(super.toString());
    sb.append("]");
    return sb.toString();
  }



}
