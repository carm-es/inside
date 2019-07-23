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
import es.mpt.dsic.inside.model.objetos.ObjetoInsideUnidadUsuario;
import es.mpt.dsic.inside.store.hibernate.entity.UnidadUsuario;
import es.mpt.dsic.inside.store.hibernate.entity.UnidadWsAplicacion;

public class InsideServiceStoreHibernateConverterUnidadUsuario<E extends Object> {

  protected static final Log logger =
      LogFactory.getLog(InsideServiceStoreHibernateConverterUnidadUsuario.class);

  public static ObjetoInsideUnidadUsuario toInside(UnidadUsuario entity) {
    ObjetoInsideUnidadUsuario retorno = null;
    if (entity != null) {
      retorno = new ObjetoInsideUnidadUsuario();
      retorno.setActivo(entity.isActivo());
      retorno.setIdUnidad(entity.getUnidad().getId());
      retorno.setIdUsuario(entity.getIdUsuario());
      if (entity.getProcedimiento() != null) {
        retorno.setIdProcedimiento(entity.getProcedimiento().getId());
      }
    }
    return retorno;
  }

  public static ObjetoInsideUnidadUsuario toInside(UnidadWsAplicacion entity) {
    ObjetoInsideUnidadUsuario retorno = null;
    if (entity != null) {
      retorno = new ObjetoInsideUnidadUsuario();
      retorno.setActivo(entity.isActivo());
      retorno.setIdUnidad(entity.getIdUnidad());
      retorno.setIdUsuario(entity.getIdAplicacion());
      retorno.setIdProcedimiento(entity.getIdProcedimiento());
    }
    return retorno;
  }
}
