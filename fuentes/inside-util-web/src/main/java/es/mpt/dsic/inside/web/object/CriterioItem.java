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

package es.mpt.dsic.inside.web.object;

public class CriterioItem {

  private String nombre;
  private String relacion;
  private String valor;
  private String valorDesde;
  private String valorHasta;

  public CriterioItem() {}

  public CriterioItem(String nombre, String relacion, String valor, String valorDesde,
      String valorHasta) {
    super();
    this.nombre = nombre;
    this.relacion = relacion;
    this.valor = valor;
    this.valorDesde = valorDesde;
    this.valorHasta = valorHasta;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getRelacion() {
    return relacion;
  }

  public void setRelacion(String relacion) {
    this.relacion = relacion;
  }

  public String getValor() {
    return valor;
  }

  public void setValor(String valor) {
    this.valor = valor;
  }

  public String getValorDesde() {
    return valorDesde;
  }

  public void setValorDesde(String valorDesde) {
    this.valorDesde = valorDesde;
  }

  public String getValorHasta() {
    return valorHasta;
  }

  public void setValorHasta(String valorHasta) {
    this.valorHasta = valorHasta;
  }

}
