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

package es.mpt.dsic.inside.model.converter;

import es.mpt.dsic.inside.model.converter.exception.InsideConverterException;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteInside;
import es.mpt.dsic.inside.xml.eni.expediente.metadatos.EnumeracionEstados;
import es.mpt.dsic.inside.xml.inside.ExpedienteInsideInfo;

public abstract class InsideConverterExpedienteInfo {

  public static ExpedienteInsideInfo fromExpediente(ObjetoExpedienteInside expediente)
      throws InsideConverterException {
    ExpedienteInsideInfo expedienteInfo = new ExpedienteInsideInfo();
    expedienteInfo
        .setEstado(EnumeracionEstados.fromValue(expediente.getMetadatos().getEstado().value()));
    expedienteInfo.setIdentificador(expediente.getMetadatos().getIdentificadorObjetoInside());
    expedienteInfo
        .setVersionActual(InsideConverterVersion.versionInsideToXml(expediente.getVersion()));
    return expedienteInfo;
  }

  public static ExpedienteInsideInfo fromExpedienteMasInfo(ObjetoExpedienteInside expediente,
      String info) throws InsideConverterException {
    ExpedienteInsideInfo expedienteInfo = new ExpedienteInsideInfo();
    if (expediente != null) {
      expedienteInfo
          .setEstado(EnumeracionEstados.fromValue(expediente.getMetadatos().getEstado().value()));
      expedienteInfo.setIdentificador(expediente.getMetadatos().getIdentificadorObjetoInside());
      expedienteInfo
          .setVersionActual(InsideConverterVersion.versionInsideToXml(expediente.getVersion()));
    }
    expedienteInfo.setInfoExpediente(info);
    return expedienteInfo;
  }

  public static String expedienteInsideInfoToString(ExpedienteInsideInfo expedienteInsideInfo) {
    String coma = ", ";
    StringBuffer sb = new StringBuffer("ExpedienteInsideInfo=[");
    if (expedienteInsideInfo == null) {
      sb.append("null");
    } else {
      sb.append("Identificador=" + expedienteInsideInfo.getIdentificador() + coma);
      sb.append("Estado=" + expedienteInsideInfo.getEstado().toString() + coma);
      sb.append("VersionActual=" + expedienteInsideInfo.getVersionActual());
    }
    sb.append("]");
    return sb.toString();
  }

}
