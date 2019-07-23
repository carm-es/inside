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

package es.mpt.dsic.inside.model.busqueda.resultado;

import es.mpt.dsic.inside.model.objetos.ObjetoInsideMetadatos;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideVersion;

public abstract class ItemResultadoBusquedaInside<T extends ObjetoInsideMetadatos> {

  protected String identificador;

  protected ObjetoInsideVersion version;

  protected T metadatos;

  public String getIdentificador() {
    return identificador;
  }

  public void setIdentificador(String identificador) {
    this.identificador = identificador;
  }

  public ObjetoInsideVersion getVersion() {
    return version;
  }

  public void setVersion(ObjetoInsideVersion version) {
    this.version = version;
  }

  public T getMetadatos() {
    return metadatos;
  }

  public void setMetadatos(T metadatos) {
    this.metadatos = metadatos;
  }

}
