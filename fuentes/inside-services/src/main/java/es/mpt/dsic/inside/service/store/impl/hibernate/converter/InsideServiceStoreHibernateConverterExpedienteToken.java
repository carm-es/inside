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

import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteToken;
import es.mpt.dsic.inside.service.store.exception.InsideServiceStoreException;
import es.mpt.dsic.inside.store.hibernate.entity.ExpedienteToken;
import es.mpt.dsic.inside.store.hibernate.entity.UsuarioInside;

public class InsideServiceStoreHibernateConverterExpedienteToken {

  public ExpedienteToken toEntity(ObjetoExpedienteToken token, Session session)
      throws InsideServiceStoreException {
    ExpedienteToken entity = new ExpedienteToken();
    entity.setIdentificador(token.getIdentificador());

    if (token.getUsuario() != null && StringUtils.isNotBlank(token.getUsuario().getNif())) {
      Criteria critUsuario = session.createCriteria(UsuarioInside.class);
      critUsuario.add(Restrictions.eq("nif", token.getUsuario().getNif()));
      UsuarioInside usuarioEntity = (UsuarioInside) critUsuario.uniqueResult();
      entity.setUsuario(usuarioEntity);
    }

    entity.setCsv(token.getCsv());
    entity.setUuid(token.getUuid());

    entity.setDir3(token.getDir3());
    entity.setAsunto(token.getAsunto());
    entity.setNifs(token.getNifs());
    entity.setFechaCaducidad(token.getFechaCaducidad());
    entity.setFechaCreacion(new Date());


    return entity;

  }

  public ObjetoExpedienteToken toInside(ExpedienteToken entity) throws InsideServiceStoreException {
    ObjetoExpedienteToken expediente = new ObjetoExpedienteToken();

    expediente.setIdentificador(entity.getIdentificador());
    expediente.setCsv(entity.getCsv());
    expediente.setUuid(entity.getUuid());
    expediente
        .setUsuario(InsideServiceStoreHibernateConverterUsuario.toInside(entity.getUsuario()));

    expediente.setDir3(entity.getDir3());
    expediente.setAsunto(entity.getAsunto());
    expediente.setNifs(entity.getNifs());
    expediente.setFechaCaducidad(entity.getFechaCaducidad());
    expediente.setFechaCreacion(entity.getFechaCreacion());

    return expediente;
  }

}
