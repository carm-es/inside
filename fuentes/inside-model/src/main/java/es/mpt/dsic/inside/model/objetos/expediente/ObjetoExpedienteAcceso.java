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

public class ObjetoExpedienteAcceso {

  private String nif;
  private String dir3;
  private String claveExpediente;
  private String valorExpediente;
  private String asunto;

  public ObjetoExpedienteAcceso(String nif, String dir3, String claveExpediente,
      String valorExpediente, String asunto) {
    super();
    this.nif = nif;
    this.dir3 = dir3;
    this.claveExpediente = claveExpediente;
    this.valorExpediente = valorExpediente;
    this.asunto = asunto;
  }

  public String getNif() {
    return nif;
  }

  public void setNif(String nif) {
    this.nif = nif;
  }

  public String getDir3() {
    return dir3;
  }

  public void setDir3(String dir3) {
    this.dir3 = dir3;
  }

  public String getClaveExpediente() {
    return claveExpediente;
  }

  public void setClaveExpediente(String claveExpediente) {
    this.claveExpediente = claveExpediente;
  }

  public String getValorExpediente() {
    return valorExpediente;
  }

  public void setValorExpediente(String valorExpediente) {
    this.valorExpediente = valorExpediente;
  }

  public String getAsunto() {
    return asunto;
  }

  public void setAsunto(String asunto) {
    this.asunto = asunto;
  }
}
