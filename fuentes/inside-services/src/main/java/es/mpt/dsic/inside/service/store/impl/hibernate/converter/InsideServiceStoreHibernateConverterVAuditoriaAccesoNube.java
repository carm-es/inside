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


import es.mpt.dsic.inside.model.objetos.expediente.ObjetoAuditoriaAcceso;
import es.mpt.dsic.inside.service.store.exception.InsideServiceStoreException;
import es.mpt.dsic.inside.store.hibernate.entity.VAuditoriaAccesoNube;


public class InsideServiceStoreHibernateConverterVAuditoriaAccesoNube {

  public static ObjetoAuditoriaAcceso toInside(VAuditoriaAccesoNube entity)
      throws InsideServiceStoreException {

    ObjetoAuditoriaAcceso objetoAuditoriaAcceso = new ObjetoAuditoriaAcceso();

    objetoAuditoriaAcceso.setFechaAcceso(entity.getFechaAcceso());
    objetoAuditoriaAcceso.setIdentificador(entity.getIdentificador());
    objetoAuditoriaAcceso.setNifPeticionario(entity.getNifPeticionario());
    objetoAuditoriaAcceso.setNifRemitente(entity.getNifRemitente());
    objetoAuditoriaAcceso.setNifUsuarioAcceso(entity.getNifUsuarioAcceso());

    return objetoAuditoriaAcceso;
  }

}
