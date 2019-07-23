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

import org.hibernate.classic.Session;
import org.springframework.util.Assert;
import es.mpt.dsic.inside.model.objetos.ObjetoInside;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInside;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteInside;
import es.mpt.dsic.inside.service.store.exception.InsideServiceStoreException;
import es.mpt.dsic.inside.store.hibernate.entity.DocumentoInside;
import es.mpt.dsic.inside.store.hibernate.entity.ExpedienteInside;


public class InsideServiceStoreHibernateConverterObjetoInside<T extends ObjetoInside<?>, K extends Object>
    implements InsideServiceStoreHibernateConverter<T, K> {

  @SuppressWarnings("unchecked")
  public K toEntity(T objetoInside, Session session) throws InsideServiceStoreException {
    Assert.notNull(objetoInside);
    if (objetoInside instanceof ObjetoExpedienteInside) {
      return (K) (new InsideServiceStoreHibernateConverterExpediente())
          .toEntity((ObjetoExpedienteInside) objetoInside, session);
    } else if (objetoInside instanceof ObjetoDocumentoInside) {
      return (K) (new InsideServiceStoreHibernateConverterDocumento())
          .toEntity((ObjetoDocumentoInside) objetoInside, session);
    }

    throw new InsideServiceStoreException("No se como convertir un " + objetoInside.getClass());

  }

  @SuppressWarnings("unchecked")
  public T toInside(K entity, Session session) throws InsideServiceStoreException {
    Assert.notNull(entity);
    if (entity instanceof ExpedienteInside) {
      return (T) (new InsideServiceStoreHibernateConverterExpediente())
          .toInside((ExpedienteInside) entity, session);
    } else if (entity instanceof DocumentoInside) {
      return (T) (new InsideServiceStoreHibernateConverterDocumento())
          .toInside((DocumentoInside) entity, session);
    }

    throw new InsideServiceStoreException("No se como convertir un " + entity.getClass());
  }



}
