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


import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoAuditoriaToken;
import es.mpt.dsic.inside.model.objetos.usuario.ObjetoInsideUsuario;
import es.mpt.dsic.inside.service.store.exception.InsideServiceStoreException;
import es.mpt.dsic.inside.store.hibernate.entity.AuditoriaToken;
import es.mpt.dsic.inside.store.hibernate.entity.ExpedienteToken;


public class InsideServiceStoreHibernateConverterAuditoriaToken {

  public AuditoriaToken toEntity(ObjetoAuditoriaToken auditoriatoken, Session session)
      throws InsideServiceStoreException {
    AuditoriaToken entity = new AuditoriaToken();

    Criteria crit = session.createCriteria(ExpedienteToken.class);
    crit.add(Restrictions.eq("csv", auditoriatoken.getToken().getCsv()));
    crit.add(Restrictions.eq("uuid", auditoriatoken.getToken().getUuid()));
    crit.add(Restrictions.eq("identificador", auditoriatoken.getToken().getIdentificador()));
    ExpedienteToken tokenEntity = (ExpedienteToken) crit.uniqueResult();

    entity.setToken(tokenEntity);
    entity.setNifUsuarioUso(auditoriatoken.getUsuario().getNif());
    entity.setActa(auditoriatoken.getActa());
    entity.setJustificante(auditoriatoken.getJustificante());
    entity.setFechaUso(auditoriatoken.getFechaUso());
    entity.setDescripcionRolUso(auditoriatoken.getDescripcionRolUso());
    entity.setDir3UsuarioUso(auditoriatoken.getDir3UsuarioUso());
    entity.setIdRolUso(auditoriatoken.getIdRolUso());
    entity.setNombreDir3UsuarioUso(auditoriatoken.getNombreDir3UsuarioUso());

    return entity;

  }

  public static ObjetoAuditoriaToken toInside(AuditoriaToken entity)
      throws InsideServiceStoreException {
    ObjetoAuditoriaToken auditoriaToken = new ObjetoAuditoriaToken();

    ObjetoInsideUsuario user = new ObjetoInsideUsuario();
    user.setNif(entity.getNifUsuarioUso());
    auditoriaToken.setUsuario(user);

    InsideServiceStoreHibernateConverterExpedienteToken converter =
        new InsideServiceStoreHibernateConverterExpedienteToken();
    auditoriaToken.setToken(converter.toInside(entity.getToken()));

    auditoriaToken.setActa(entity.getActa());
    auditoriaToken.setJustificante(entity.getJustificante());


    return auditoriaToken;
  }

}
