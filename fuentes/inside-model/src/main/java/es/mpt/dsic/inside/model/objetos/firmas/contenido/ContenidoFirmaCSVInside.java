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

package es.mpt.dsic.inside.model.objetos.firmas.contenido;

public class ContenidoFirmaCSVInside extends ContenidoFirmaInside {


  protected String valorCSV;

  protected String regulacionGeneracionCSV;

  public String getValorCSV() {
    return valorCSV;
  }

  public void setValorCSV(String value) {
    this.valorCSV = value;
  }

  public String getRegulacionGeneracionCSV() {
    return regulacionGeneracionCSV;
  }

  public void setRegulacionGeneracionCSV(String value) {
    this.regulacionGeneracionCSV = value;
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    ContenidoFirmaCSVInside csv = (ContenidoFirmaCSVInside) super.clone();
    csv.regulacionGeneracionCSV = regulacionGeneracionCSV;
    csv.valorCSV = valorCSV;
    return csv;
  }

  @Override
  public String toString() {
    String coma = ", ";
    StringBuffer sb = new StringBuffer("ContenidoFirmaCSVInside=[");
    sb.append("valorCSV=" + valorCSV + coma);
    sb.append("regulacionGeneracionCSV=" + regulacionGeneracionCSV);
    sb.append("]");
    return sb.toString();
  }

}
