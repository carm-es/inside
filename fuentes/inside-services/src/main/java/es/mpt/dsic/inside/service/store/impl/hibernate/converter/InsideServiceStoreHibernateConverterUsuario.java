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
import es.mpt.dsic.inside.model.objetos.usuario.ObjetoInsideUsuario;
import es.mpt.dsic.inside.store.hibernate.entity.UnidadUsuario;
import es.mpt.dsic.inside.store.hibernate.entity.UsuarioInside;

public class InsideServiceStoreHibernateConverterUsuario<E extends Object> {

  protected static final Log logger =
      LogFactory.getLog(InsideServiceStoreHibernateConverterUsuario.class);


  public static UsuarioInside toEntity(ObjetoInsideUsuario objeto) {
    UsuarioInside retorno = new UsuarioInside();
    retorno.setNif(objeto.getNif());
    retorno.setActivo(objeto.isActivo());
    retorno.setAdminMensaje(objeto.isAdminMensaje());
    return retorno;
  }


  public static ObjetoInsideUsuario toInside(UsuarioInside entity) {
    ObjetoInsideUsuario retorno = null;
    if (entity != null) {
      retorno = new ObjetoInsideUsuario();
      retorno.setNif(entity.getNif());
      retorno.setActivo(entity.getActivo().booleanValue());
      retorno.setAdminMensaje(entity.getAdminMensaje());
    }
    return retorno;
  }

  public static ObjetoInsideUsuario toInsideUnidad(UsuarioInside entity, String numProcedimiento) {
    ObjetoInsideUsuario retorno = null;
    if (entity != null) {
      retorno = new ObjetoInsideUsuario();
      retorno.setNif(entity.getNif());
      retorno.setActivo(entity.getActivo().booleanValue());
      retorno.setAdminMensaje(entity.getAdminMensaje());
      if (entity.contieneUnidadActiva()) {
        UnidadUsuario unidadUsuario = entity.getUnidadActiva();
        retorno.setUnidadOrganicaActiva(unidadUsuario.getUnidad().getCodigoUnidadOrganica());
        retorno.setNombreUnidadOrganicaActiva(unidadUsuario.getUnidad().getNombreUnidadOrganica());
        retorno.setNumeroProcedimiento(numProcedimiento);
      }
    }
    return retorno;
  }


}
