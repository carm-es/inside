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

package es.mpt.dsic.loadTables.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import es.mpt.dsic.inside.store.hibernate.entity.UnidadOrganica;
import es.mpt.dsic.loadTables.objects.Organismo;

public class UnidadOrganicaConverter {

  public static List<UnidadOrganica> toEntities(List<Organismo> datos, Date syncDate) {
    List<UnidadOrganica> retorno = new ArrayList<UnidadOrganica>();
    for (Organismo organimo : datos) {
      retorno.add(toEntity(organimo, syncDate));
    }
    return retorno;
  }

  public static UnidadOrganica toEntity(Organismo datos, Date syncDate) {
    UnidadOrganica retorno = new UnidadOrganica();

    // datos identficativos
    retorno
        .setCodigoUnidadOrganica(datos.getDatosIdentificativos().getUnidadOrganica().getCodigo());
    retorno.setNombreUnidadOrganica(
        datos.getDatosIdentificativos().getUnidadOrganica().getDescripcion());
    retorno.setNivelAdministracion(datos.getDatosIdentificativos().getNivelAdministracion());
    retorno.setEntidadDerechoPublico(
        datos.getDatosIdentificativos().getIndicadorEntidadDerechoPublico());
    retorno.setCodigoExterno(datos.getDatosIdentificativos().getCodigoExterno());

    // datos dependencia
    retorno.setCodigoUnidadSuperior(
        datos.getDatosDependencia().getUnidadSuperiorJerarquica().getCodigo());
    retorno.setNombreUnidadSuperior(
        datos.getDatosDependencia().getUnidadSuperiorJerarquica().getDescripcion());
    retorno.setCodigoUnidadRaiz(datos.getDatosDependencia().getUnidadOrganicaRaiz().getCodigo());
    retorno
        .setNombreUnidadRaiz(datos.getDatosDependencia().getUnidadOrganicaRaiz().getDescripcion());
    retorno.setCodigoRaizDerechoPublico(
        datos.getDatosDependencia().getUnidadRaizEntidadDerechoPublico().getCodigo());
    retorno.setNombreRaizDerechoPublico(
        datos.getDatosDependencia().getUnidadRaizEntidadDerechoPublico().getDescripcion());
    retorno.setNivelJerarquico(datos.getDatosDependencia().getNivelJerarquico());

    // datos vigencia
    retorno.setEstado(datos.getDatosVigencia().getEstado());
    retorno.setFechaAlta(datos.getDatosVigencia().getFechaAlta());
    retorno.setFechaBaja(datos.getDatosVigencia().getFechaBaja());
    retorno.setFechaAnulacion(datos.getDatosVigencia().getFechaAnulacion());
    retorno.setFechaExtincion(datos.getDatosVigencia().getFechaExtincion());

    // timestamp
    retorno.setTimestamp(syncDate);

    return retorno;
  }

}
