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

package es.mpt.dsic.inside.model.objetos.documento;

import java.util.ArrayList;
import java.util.List;
import es.mpt.dsic.inside.model.objetos.ObjetoInside;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideVersion;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideVersionable;
import es.mpt.dsic.inside.model.objetos.documento.metadatos.ObjetoDocumentoInsideMetadatos;
import es.mpt.dsic.inside.model.objetos.firmas.FirmaInside;

public class ObjetoDocumentoInside extends ObjetoInside<ObjetoDocumentoInsideMetadatos> {

  private static final long serialVersionUID = 6933262305544703850L;

  protected ObjetoDocumentoInsideContenido contenido;

  protected List<FirmaInside> firmas;

  protected ObjetoInsideVersion version;

  public ObjetoDocumentoInsideContenido getContenido() {
    return contenido;
  }

  public void setContenido(ObjetoDocumentoInsideContenido contenido) {
    this.contenido = contenido;
  }

  public List<FirmaInside> getFirmas() {
    if (firmas == null) {
      firmas = new ArrayList<FirmaInside>();
    }
    return firmas;
  }

  public void setFirmas(List<FirmaInside> firmas) {
    this.firmas = firmas;
  }

  public ObjetoInsideVersion getVersion() {
    return version;
  }

  public void setVersion(ObjetoInsideVersion version) {
    this.version = version;
  }

  @Override
  protected ObjetoDocumentoInsideMetadatos createMetadatos() {
    return new ObjetoDocumentoInsideMetadatos();
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer("ObjetoDocumentoInside=[");
    String coma = ", ";
    sb.append(super.toString() + coma);

    if (version == null) {
      sb.append("version=null" + coma);
    } else {
      sb.append("version=" + this.version.toString() + coma);
    }

    if (firmas == null) {
      sb.append("firmas=null" + coma);
    } else {
      sb.append("firmas=<");
      for (FirmaInside firma : firmas) {
        sb.append("FirmaInside=" + firma.toString() + coma);
      }
      sb.append(">");
    }

    if (contenido == null) {
      sb.append("contenido=null");
    } else {
      sb.append("contenido=" + contenido.toString());
    }

    sb.append("]");
    return sb.toString();
  }
}
