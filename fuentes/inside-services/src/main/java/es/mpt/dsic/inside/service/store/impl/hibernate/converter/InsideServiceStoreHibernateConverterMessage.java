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

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import es.mpt.dsic.inside.model.objetos.adminMensajes.ObjetoMensajeUsuario;
import es.mpt.dsic.inside.store.hibernate.entity.MensajeUsuario;

public abstract class InsideServiceStoreHibernateConverterMessage {

  protected static final Log logger =
      LogFactory.getLog(InsideServiceStoreHibernateConverterMessage.class);


  public static MensajeUsuario toEntity(ObjetoMensajeUsuario objeto) {
    MensajeUsuario retorno = new MensajeUsuario();
    retorno.setId(objeto.getIdentificador());
    retorno.setActivo(objeto.isActivo());
    retorno.setFechaCreacion(objeto.getFechaCreacion());
    retorno.setFechaModificacion(objeto.getFechaModificacion());
    retorno.setTexto(objeto.getTexto());
    return retorno;
  }


  public static ObjetoMensajeUsuario toInside(MensajeUsuario entity) {
    ObjetoMensajeUsuario retorno = null;
    if (entity != null) {
      retorno = new ObjetoMensajeUsuario();
      retorno.setIdentificador(entity.getId());
      retorno.setActivo(entity.getActivo());
      retorno.setFechaCreacion(entity.getFechaCreacion());
      retorno.setFechaModificacion(entity.getFechaModificacion());
      retorno.setTexto(entity.getTexto());
    }
    return retorno;
  }

  public static List<ObjetoMensajeUsuario> toInside(List<MensajeUsuario> entities) {
    List<ObjetoMensajeUsuario> retorno = new ArrayList<>();
    if (CollectionUtils.isNotEmpty(entities)) {
      for (MensajeUsuario entity : entities) {
        retorno.add(toInside(entity));
      }
    }
    return retorno;
  }

}
