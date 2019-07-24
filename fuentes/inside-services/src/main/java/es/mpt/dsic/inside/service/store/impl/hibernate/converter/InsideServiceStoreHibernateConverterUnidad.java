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

package es.mpt.dsic.inside.service.store.impl.hibernate.converter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideUnidad;
import es.mpt.dsic.inside.store.hibernate.entity.UnidadOrganica;

public class InsideServiceStoreHibernateConverterUnidad<E extends Object> {

  protected static final Log logger =
      LogFactory.getLog(InsideServiceStoreHibernateConverterUnidad.class);

  public static ObjetoInsideUnidad toInside(UnidadOrganica entity, String numeroProcedimiento,
      boolean activo) {
    ObjetoInsideUnidad retorno = null;
    if (entity != null) {
      retorno = new ObjetoInsideUnidad();
      retorno.setId(entity.getId());
      retorno.setCodigo(entity.getCodigoUnidadOrganica());
      retorno.setNombre(entity.getNombreUnidadOrganica());
      retorno.setNumeroProcedimiento(numeroProcedimiento);
      retorno.setActivo(activo);
    }
    return retorno;
  }
}
