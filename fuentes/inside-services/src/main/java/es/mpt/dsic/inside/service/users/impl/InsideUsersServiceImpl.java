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

package es.mpt.dsic.inside.service.users.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideAplicacion;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideRol;
import es.mpt.dsic.inside.model.objetos.usuario.ObjetoInsideUsuario;
import es.mpt.dsic.inside.service.store.InsideServiceJta;
import es.mpt.dsic.inside.service.store.InsideServiceStore;
import es.mpt.dsic.inside.service.store.exception.InsideServiceStoreException;
import es.mpt.dsic.inside.service.store.impl.hibernate.converter.InsideServiceStoreHibernateConverterInsideRol;
import es.mpt.dsic.inside.service.store.impl.hibernate.converter.InsideServiceStoreHibernateConverterUsuario;
import es.mpt.dsic.inside.service.users.InsideUsersService;
import es.mpt.dsic.inside.service.users.exception.InsideUsersServiceException;
import es.mpt.dsic.inside.store.hibernate.entity.InsideRol;
import es.mpt.dsic.inside.store.hibernate.entity.UnidadOrganica;
import es.mpt.dsic.inside.store.hibernate.entity.UnidadUsuario;
import es.mpt.dsic.inside.store.hibernate.entity.UsuarioInside;

public class InsideUsersServiceImpl implements InsideUsersService {

  protected static final Log logger = LogFactory.getLog(InsideUsersServiceImpl.class);

  @Autowired
  private InsideServiceStore insideStore;

  @Autowired
  private InsideServiceJta insideServiceJta;

  public ObjetoInsideAplicacion getAplicacion(String nombre) throws InsideUsersServiceException {
    try {
      return insideStore.getAplicacion(nombre);
    } catch (InsideServiceStoreException e) {
      throw new InsideUsersServiceException(e.getMessage(), e, false);
    }
  }

  @Override
  public ObjetoInsideAplicacion getAplicacionBySerialNumber(String serialNumberCertificado)
      throws InsideUsersServiceException {

    try {
      return insideStore.getAplicacionBySerialNumber(serialNumberCertificado);
    } catch (InsideServiceStoreException e) {
      throw new InsideUsersServiceException(e.getMessage(), e, false);
    }
  }

  public List<ObjetoInsideAplicacion> getAplicacionesActivas() throws InsideUsersServiceException {
    try {
      return insideStore.getAplicacionesActivas();
    } catch (InsideServiceStoreException e) {
      throw new InsideUsersServiceException("Error al recuperar la aplicaciones", e, true);
    }
  }

  @Override
  public ObjetoInsideUsuario getUsuario(String nif) throws InsideUsersServiceException {
    List<Criterion> criterias = new ArrayList<Criterion>();
    ObjetoInsideUsuario usuarioInside = null;
    criterias.add(Restrictions.eq("nif", nif));
    UsuarioInside usuario =
        (UsuarioInside) insideServiceJta.findObjeto(UsuarioInside.class, criterias);

    if (usuario != null && usuario.getActivo()
        && CollectionUtils.isNotEmpty(usuario.getListaUnidades())) {
      usuarioInside = InsideServiceStoreHibernateConverterUsuario.toInside(usuario);

      // busca el codigo de la unidad organica activa para rellenar el objeto usuario de sesion
      List<Criterion> criteriasUnidadOrganica = new ArrayList<Criterion>();
      UnidadUsuario unidadUsuarioActiva = usuario.getUnidadActiva();

      // busca el objeto insiderol de la unidadorganica activa para meterlo en usuario sesion
      List<Criterion> criteriasRol = new ArrayList<Criterion>();
      criteriasRol.add(Restrictions.eq("id", unidadUsuarioActiva.getIdRol()));
      InsideRol insideRolEntity =
          (InsideRol) insideServiceJta.findObjeto(InsideRol.class, criteriasRol);
      ObjetoInsideRol objetoInsideRol =
          InsideServiceStoreHibernateConverterInsideRol.toInside(insideRolEntity);
      usuarioInside.setRol(objetoInsideRol);

      criteriasUnidadOrganica.add(Restrictions.eq("id", unidadUsuarioActiva.getUnidad().getId()));
      UnidadOrganica unidadOrganicaActiva = (UnidadOrganica) insideServiceJta
          .findObjeto(UnidadOrganica.class, criteriasUnidadOrganica);
      usuarioInside.setUnidadOrganicaActiva(unidadOrganicaActiva.getCodigoUnidadOrganica() + " - "
          + unidadOrganicaActiva.getNombreUnidadOrganica());
    }
    return usuarioInside;
  }
}
