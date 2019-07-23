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

package es.mpt.dsic.inside.service.visualizacion;

public class ResultadoVisualizacionDocumento {

  byte[] visualizacion;
  String mime;

  public byte[] getVisualizacion() {
    return visualizacion;
  }

  public void setVisualizacion(byte[] visualizacion) {
    this.visualizacion = visualizacion;
  }

  public String getMime() {
    return mime;
  }

  public void setMime(String mime) {
    this.mime = mime;
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer("");
    String coma = ", ";
    sb.append("ResultadoVisualizacionDocumento=[");
    if (visualizacion == null) {
      sb.append("visualizacion=null" + coma);
    } else {
      sb.append("visualizacion==[...bytes...]" + coma);
    }
    sb.append("mime=" + mime);
    sb.append("]");
    return sb.toString();
  }


}
