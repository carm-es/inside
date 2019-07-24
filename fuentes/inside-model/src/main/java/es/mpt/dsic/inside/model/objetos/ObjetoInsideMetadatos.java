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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase base para los Objetos Contenedores de metadatos en Inside
 * 
 * @author miguel.ortega
 *
 */
public abstract class ObjetoInsideMetadatos
    implements InsideMetadatosAdicionales, Cloneable, Serializable {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  protected List<ObjetoInsideMetadatoAdicional> metadatosAdicionales;

  protected String identificadorObjetoInside;

  public List<ObjetoInsideMetadatoAdicional> getMetadatosAdicionales() {
    if (this.metadatosAdicionales == null) {
      this.metadatosAdicionales = new ArrayList<ObjetoInsideMetadatoAdicional>();
    }
    return metadatosAdicionales;
  }

  public void setMetadatosAdicionales(List<ObjetoInsideMetadatoAdicional> metadatosAdicionales) {
    this.metadatosAdicionales = metadatosAdicionales;
  }

  /**
   * Devuelve el identificador que relaciona estos metadatos con el objetoInside a los que pertenece
   * 
   * @return
   */
  public String getIdentificadorObjetoInside() {
    return identificadorObjetoInside;
  }

  /**
   * Establece el identificador que relaciona estos metadatos con el objetoInside a los que
   * pertenece
   * 
   * @param value
   */
  public void setIdentificadorObjetoInside(String value) {
    this.identificadorObjetoInside = value;
  }

  @Override
  public String toString() {
    String coma = ", ";
    StringBuffer sb = new StringBuffer("ObjetoInsideMetadatos=[");
    sb.append("identificadorObjetoInside=" + identificadorObjetoInside + coma);
    sb.append("metadatosAdicionales=");
    if (metadatosAdicionales == null) {
      sb.append("null");
    } else {
      for (ObjetoInsideMetadatoAdicional data : metadatosAdicionales) {
        sb.append("Nombre Met.Adicional=" + data.getNombre() + coma);
        sb.append("Valor Met.Adicional=" + data.getValor().toString() + coma);
      }
    }
    sb.append("]");
    return sb.toString();
  }
}
