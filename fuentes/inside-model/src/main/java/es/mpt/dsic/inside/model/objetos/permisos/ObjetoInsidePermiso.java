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

package es.mpt.dsic.inside.model.objetos.permisos;

import es.mpt.dsic.inside.model.objetos.ObjetoInside;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideAplicacion;

public class ObjetoInsidePermiso extends ObjetoPermisos {

  private ObjetoInside<?> objetoInside;
  private ObjetoInsideAplicacion aplicacion;

  public ObjetoInsidePermiso() {
    super(false, false);
  }

  public ObjetoInsidePermiso(ObjetoInsideAplicacion app, ObjetoInside<?> objeto, Boolean lectura,
      Boolean escritura) {
    super(lectura, escritura);
    this.objetoInside = objeto;
    this.aplicacion = app;
  }

  public ObjetoInsidePermiso(boolean lectura, boolean escritura) {
    super(lectura, escritura);
  }

  /**
   * @return the aplicacion
   */
  public ObjetoInsideAplicacion getAplicacion() {
    return aplicacion;
  }

  /**
   * @param aplicacion the aplicacion to set
   */
  public void setAplicacion(ObjetoInsideAplicacion aplicacion) {
    this.aplicacion = aplicacion;
  }

  /**
   * @return the objetoInside
   */
  public ObjetoInside<?> getObjetoInside() {
    return objetoInside;
  }

  /**
   * @param objetoInside the objetoInside to set
   */
  public void setObjetoInside(ObjetoInside<?> objetoInside) {
    this.objetoInside = objetoInside;
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer("ObjetoInsidePermiso=[");
    String coma = ", ";
    sb.append(super.toString());

    if (objetoInside == null) {
      sb.append("objetoInside=null" + coma);
    } else {
      sb.append("objetoInside=" + objetoInside.toString() + coma);
    }

    if (aplicacion == null) {
      sb.append("aplicacion=null" + coma);
    } else {
      sb.append("aplicacion=" + aplicacion.toString() + coma);
    }

    sb.append("]");
    return sb.toString();
  }
}
