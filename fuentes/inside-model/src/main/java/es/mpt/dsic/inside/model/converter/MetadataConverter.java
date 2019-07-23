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

import es.mpt.dsic.eeutil.client.visDocExp.model.EnumeracionEstadoElaboracion;
import es.mpt.dsic.eeutil.client.visDocExp.model.TipoDocumental;
import es.mpt.dsic.eeutil.client.visDocExp.model.TipoEstadoElaboracion;
import es.mpt.dsic.eeutil.client.visDocExp.model.TipoMetadatos;

public abstract class MetadataConverter {

  public static TipoMetadatos tipoMetadatosInsideToEeutilClient(
      es.mpt.dsic.inside.xml.eni.documento.metadatos.TipoMetadatos input) {
    TipoMetadatos retorno = new TipoMetadatos();
    retorno
        .setEstadoElaboracion(estadoElaboracionInsideToEeutilClient(input.getEstadoElaboracion()));
    retorno.setFechaCaptura(input.getFechaCaptura());
    retorno.setIdentificador(input.getIdentificador());
    retorno.setOrigenCiudadanoAdministracion(input.isOrigenCiudadanoAdministracion());
    retorno.setTipoDocumental(tipoDocumentalInsideToEeutilClient(input.getTipoDocumental()));
    retorno.setVersionNTI(input.getVersionNTI());
    retorno.getOrgano().addAll(input.getOrgano());
    return retorno;
  }

  public static TipoEstadoElaboracion estadoElaboracionInsideToEeutilClient(
      es.mpt.dsic.inside.xml.eni.documento.metadatos.TipoEstadoElaboracion input) {
    TipoEstadoElaboracion retorno = new TipoEstadoElaboracion();
    retorno.setIdentificadorDocumentoOrigen(input.getIdentificadorDocumentoOrigen());
    retorno.setValorEstadoElaboracion(
        EnumeracionEstadoElaboracion.valueOf(input.getValorEstadoElaboracion().name()));
    return retorno;
  }

  public static TipoDocumental tipoDocumentalInsideToEeutilClient(
      es.mpt.dsic.inside.xml.eni.documento.metadatos.TipoDocumental input) {
    return TipoDocumental.valueOf(input.name());
  }

}
