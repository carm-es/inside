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

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import es.mpt.dsic.inside.model.objetos.ObjetoCredencialEeutil;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideAplicacion;
import es.mpt.dsic.inside.store.hibernate.entity.CredencialesEeutil;
import es.mpt.dsic.inside.store.hibernate.entity.InsideWsAplicacion;

public class InsideServiceStoreHibernateConverterAplicacion {

  protected static final Log logger =
      LogFactory.getLog(InsideServiceStoreHibernateConverterAplicacion.class);

  public static InsideWsAplicacion toEntity(ObjetoInsideAplicacion objeto) {
    InsideWsAplicacion retorno = new InsideWsAplicacion();
    retorno.setNombre(objeto.getAplicacion());
    retorno.setPasswordHash(objeto.getPasswordHash());
    retorno.setActivo(objeto.isActiva());
    retorno.setEmail(objeto.getEmail());
    retorno.setResponsable(objeto.getResponsable());
    retorno.setTelefono(objeto.getTelefono());
    retorno.setSerialNumberCertificado(objeto.getSerialNumerCert());
    if (CollectionUtils.isNotEmpty(objeto.getRoles())) {
      retorno.setAltaExpediente(
          objeto.getRoles().contains(ObjetoInsideAplicacion.ROLE_ALTA_EXPEDIENTE));
      retorno.setAdministrarPermisos(
          objeto.getRoles().contains(ObjetoInsideAplicacion.ROLE_ADMINISTRAR_PERMISOS));
      retorno
          .setAltaDocumento(objeto.getRoles().contains(ObjetoInsideAplicacion.ROLE_ALTA_DOCUMENTO));
      retorno
          .setLeerDocumento(objeto.getRoles().contains(ObjetoInsideAplicacion.ROLE_LEER_DOCUMENTO));
      retorno.setLeerExpediente(
          objeto.getRoles().contains(ObjetoInsideAplicacion.ROLE_LEER_EXPEDIENTE));
      retorno.setModificarDocumento(
          objeto.getRoles().contains(ObjetoInsideAplicacion.ROLE_MODIFICA_DOCUMENTO));
      retorno.setModificarExpediente(
          objeto.getRoles().contains(ObjetoInsideAplicacion.ROLE_MODIFICA_EXPEDIENTE));
    } else {
      retorno.setAltaExpediente(false);
      retorno.setAdministrarPermisos(false);
      retorno.setAltaDocumento(false);
      retorno.setLeerDocumento(false);
      retorno.setLeerExpediente(false);
      retorno.setModificarDocumento(false);
      retorno.setModificarExpediente(false);
    }

    if (objeto.getCredencialEeutil() != null) {
      CredencialesEeutil credencial = new CredencialesEeutil();
      credencial.setIdAplicacion(objeto.getCredencialEeutil().getAplicacion());
      credencial.setPassword(objeto.getCredencialEeutil().getPassword());
      retorno.setCredencialEeeutil(credencial);
    }


    return retorno;
  }

  public static ObjetoInsideAplicacion toInside(InsideWsAplicacion entity) {
    ObjetoInsideAplicacion aplicacion = null;
    if (entity != null) {
      aplicacion = new ObjetoInsideAplicacion();
      aplicacion.setAplicacion(entity.getNombre());
      aplicacion.setPasswordHash(entity.getPasswordHash());
      aplicacion.setActiva(entity.getActivo().booleanValue());
      aplicacion.setEmail(entity.getEmail());
      aplicacion.setResponsable(entity.getResponsable());
      aplicacion.setTelefono(entity.getTelefono());
      aplicacion.setSerialNumerCert(entity.getSerialNumberCertificado());
      if (entity.getAltaExpediente()) {
        aplicacion.getRoles().add(ObjetoInsideAplicacion.ROLE_ALTA_EXPEDIENTE);
      }
      if (entity.getLeerExpediente()) {
        aplicacion.getRoles().add(ObjetoInsideAplicacion.ROLE_LEER_EXPEDIENTE);
      }
      if (entity.getModificarExpediente()) {
        aplicacion.getRoles().add(ObjetoInsideAplicacion.ROLE_MODIFICA_EXPEDIENTE);
      }
      if (entity.getAltaDocumento()) {
        aplicacion.getRoles().add(ObjetoInsideAplicacion.ROLE_ALTA_DOCUMENTO);
      }
      if (entity.getLeerDocumento()) {
        aplicacion.getRoles().add(ObjetoInsideAplicacion.ROLE_LEER_DOCUMENTO);
      }
      if (entity.getModificarDocumento()) {
        aplicacion.getRoles().add(ObjetoInsideAplicacion.ROLE_MODIFICA_DOCUMENTO);
      }
      if (entity.getAdministrarPermisos()) {
        aplicacion.getRoles().add(ObjetoInsideAplicacion.ROLE_ADMINISTRAR_PERMISOS);
      }

      if (entity.getCredencialEeeutil() != null) {
        ObjetoCredencialEeutil credential = new ObjetoCredencialEeutil();
        credential.setAplicacion(entity.getCredencialEeeutil().getIdAplicacion());
        credential.setPassword(entity.getCredencialEeeutil().getPassword());
        aplicacion.setCredencialEeutil(credential);
      }
    }
    return aplicacion;
  }

}
