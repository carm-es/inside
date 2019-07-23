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

import java.util.ArrayList;
import java.util.List;
import es.mpt.dsic.inside.model.objetos.ObjectInsideRespuestaEnvioJusticia;
import es.mpt.dsic.inside.model.objetos.ObjetoInside;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideMetadatoAdicional;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideVersion;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideVersionable;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInsideContenido;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndice;
import es.mpt.dsic.inside.model.objetos.expediente.metadatos.ObjetoExpedienteInsideMetadatos;

public class ObjetoExpedienteInside extends ObjetoInside<ObjetoExpedienteInsideMetadatos>
    implements ObjetoInsideVersionable {

  private static final long serialVersionUID = 4051002586657376090L;

  protected ObjetoExpedienteInsideIndice indice;

  protected ObjetoDocumentoInsideContenido visualizacionIndice;

  private ObjetoInsideVersion version;

  private List<ObjectInsideRespuestaEnvioJusticia> objectInsideRespuestaEnvioJusticiaLista;


  public List<ObjectInsideRespuestaEnvioJusticia> getObjectInsideRespuestaEnvioJusticiaLista() {
    if (this.objectInsideRespuestaEnvioJusticiaLista == null) {
      this.objectInsideRespuestaEnvioJusticiaLista =
          new ArrayList<ObjectInsideRespuestaEnvioJusticia>();
    }
    return objectInsideRespuestaEnvioJusticiaLista;
  }

  public void setObjectInsideRespuestaEnvioJusticiaLista(
      List<ObjectInsideRespuestaEnvioJusticia> objectInsideRespuestaEnvioJusticiaLista) {
    this.objectInsideRespuestaEnvioJusticiaLista = objectInsideRespuestaEnvioJusticiaLista;
  }

  public ObjetoExpedienteInsideIndice getIndice() {
    if (indice == null) {
      indice = new ObjetoExpedienteInsideIndice();
    }
    return indice;
  }

  public void setIndice(ObjetoExpedienteInsideIndice value) {
    this.indice = value;
  }

  public ObjetoDocumentoInsideContenido getVisualizacionIndice() {
    return visualizacionIndice;
  }

  public void setVisualizacionIndice(ObjetoDocumentoInsideContenido visualizacionIndice) {
    this.visualizacionIndice = visualizacionIndice;
  }

  @Override
  public ObjetoInsideVersion getVersion() {
    return version;
  }

  @Override
  public void setVersion(ObjetoInsideVersion version) {
    this.version = version;
  }

  @Override
  protected ObjetoExpedienteInsideMetadatos createMetadatos() {
    return new ObjetoExpedienteInsideMetadatos();
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer("ObjetoExpedienteInside=[");
    String coma = ", ";
    sb.append(super.toString());

    if (version == null) {
      sb.append("version=null" + coma);
    } else {
      sb.append("version=" + this.version.toString() + coma);
    }

    if (indice == null) {
      sb.append("indice=null" + coma);
    } else {
      sb.append("indice=" + indice.toString() + coma);
    }
    if (this.visualizacionIndice == null) {
      sb.append("visualizacionIndice=null" + coma);
    } else {
      sb.append("visualizacionIndice=" + visualizacionIndice.toString());
    }
    sb.append("]");
    return sb.toString();
  }


}
