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

package es.mpt.dsic.inside.service.messages.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import es.mpt.dsic.inside.model.objetos.adminMensajes.ObjetoMensajeUsuario;
import es.mpt.dsic.inside.model.objetos.usuario.ObjetoInsideUsuario;
import es.mpt.dsic.inside.service.messages.InsideMessageService;
import es.mpt.dsic.inside.service.store.InsideServiceJta;
import es.mpt.dsic.inside.service.store.impl.hibernate.converter.InsideServiceStoreHibernateConverterMessage;
import es.mpt.dsic.inside.service.store.impl.hibernate.converter.InsideServiceStoreHibernateConverterUsuario;
import es.mpt.dsic.inside.store.hibernate.entity.MensajeUsuario;
import es.mpt.dsic.inside.store.hibernate.entity.UsuarioInside;

@Service
public class InsideMessageServiceImpl<E> implements InsideMessageService {

  @Autowired
  private InsideServiceJta insideServiceJta;

  @Override
  @Transactional
  public List<ObjetoMensajeUsuario> getAllMessages() {
    List<MensajeUsuario> messages = insideServiceJta.getAllObjetos(MensajeUsuario.class);
    return InsideServiceStoreHibernateConverterMessage.toInside(messages);
  }

  @Override
  @Transactional
  public ObjetoMensajeUsuario createMessage(ObjetoMensajeUsuario message) {
    MensajeUsuario entity = InsideServiceStoreHibernateConverterMessage.toEntity(message);
    entity = (MensajeUsuario) insideServiceJta.saveOrUpdate(entity);
    return InsideServiceStoreHibernateConverterMessage.toInside(entity);
  }

  @Override
  @Transactional
  public ObjetoMensajeUsuario updateMessage(ObjetoMensajeUsuario message) {
    MensajeUsuario entity = (MensajeUsuario) insideServiceJta.getObjeto(MensajeUsuario.class,
        message.getIdentificador());
    entity.setTexto(message.getTexto());
    entity.setActivo(true);
    entity.setFechaModificacion(new Date());
    entity = (MensajeUsuario) insideServiceJta.saveOrUpdate(entity);
    return InsideServiceStoreHibernateConverterMessage.toInside(entity);
  }

  @Override
  @Transactional
  public ObjetoMensajeUsuario activateMessage(ObjetoMensajeUsuario message) {
    MensajeUsuario entity = (MensajeUsuario) insideServiceJta.getObjeto(MensajeUsuario.class,
        message.getIdentificador());
    entity.setActivo(true);
    entity.setFechaModificacion(new Date());
    entity = (MensajeUsuario) insideServiceJta.saveOrUpdate(entity);
    return InsideServiceStoreHibernateConverterMessage.toInside(entity);
  }

  @Override
  @Transactional
  public ObjetoMensajeUsuario deactivateMessage(ObjetoMensajeUsuario message) {
    MensajeUsuario entity = (MensajeUsuario) insideServiceJta.getObjeto(MensajeUsuario.class,
        message.getIdentificador());
    entity.setActivo(false);
    entity.setFechaModificacion(new Date());
    entity = (MensajeUsuario) insideServiceJta.saveOrUpdate(entity);
    return InsideServiceStoreHibernateConverterMessage.toInside(entity);
  }

  @Override
  @Transactional
  public void deleteMessage(ObjetoMensajeUsuario message) {
    MensajeUsuario entity = (MensajeUsuario) insideServiceJta.getObjeto(MensajeUsuario.class,
        message.getIdentificador());
    entity = (MensajeUsuario) insideServiceJta.delete(entity);
  }

  @Override
  @Transactional
  public List<ObjetoMensajeUsuario> getMessagesActivos() {
    List<Criterion> criterias = new ArrayList<Criterion>();
    criterias.add(Restrictions.eq("activo", true));
    List<MensajeUsuario> messages =
        insideServiceJta.getAllObjetosCriteria(MensajeUsuario.class, criterias);
    return InsideServiceStoreHibernateConverterMessage.toInside(messages);
  }

  @Override
  @Transactional
  public ObjetoInsideUsuario actualizarUsuarioAdminMensajes(ObjetoInsideUsuario usuario) {
    List<Criterion> criterias = new ArrayList<Criterion>();
    criterias.add(Restrictions.eq("nif", usuario.getNif()));
    UsuarioInside userEntity =
        (UsuarioInside) insideServiceJta.findObjeto(UsuarioInside.class, criterias);
    userEntity.setAdminMensaje(usuario.isAdminMensaje());
    userEntity = (UsuarioInside) insideServiceJta.saveOrUpdate(userEntity);
    return InsideServiceStoreHibernateConverterUsuario.toInside(userEntity);
  }
}
