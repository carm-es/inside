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


public class ObjetoInsideUnidad implements Comparable<ObjetoInsideUnidad> {

  private Integer id;
  private String codigo;
  private String nombre;
  private String numeroProcedimiento;
  private boolean activo;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public boolean isActivo() {
    return activo;
  }

  public void setActivo(boolean activo) {
    this.activo = activo;
  }

  /**
   * Devuelve >0 si el orden del argumento es menor que el del "this".
   */
  public int compareTo(ObjetoInsideUnidad o) {
    int retorno = 0;
    if (!this.getCodigo().equals(o.getCodigo())) {
      retorno = 1;
    }
    return retorno;
  }

  public String getNumeroProcedimiento() {
    return numeroProcedimiento;
  }

  public void setNumeroProcedimiento(String numeroProcedimiento) {
    this.numeroProcedimiento = numeroProcedimiento;
  }
}
