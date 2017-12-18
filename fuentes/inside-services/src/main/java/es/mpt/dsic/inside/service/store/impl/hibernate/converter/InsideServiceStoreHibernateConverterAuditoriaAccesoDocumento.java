/* Copyright (C) 2016 MINHAP, Gobierno de España
   This program is licensed and may be used, modified and redistributed under the terms
   of the European Public License (EUPL), either version 1.1 or (at your
   option) any later version as soon as they are approved by the European Commission.
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
   or implied. See the License for the specific language governing permissions and
   more details.
   You should have received a copy of the EUPL1.1 license
   along with this program; if not, you may find it at
   http://joinup.ec.europa.eu/software/page/eupl/licence-eupl */

package es.mpt.dsic.inside.service.store.impl.hibernate.converter;


import es.mpt.dsic.inside.model.objetos.expediente.ObjetoAuditoriaAccesoDocumento;
import es.mpt.dsic.inside.service.store.exception.InsideServiceStoreException;
import es.mpt.dsic.inside.store.hibernate.entity.AuditoriaAccesoDocumento;


public class InsideServiceStoreHibernateConverterAuditoriaAccesoDocumento {
	
	public static AuditoriaAccesoDocumento toEntity(ObjetoAuditoriaAccesoDocumento objetoAuditoriaAccesoDocumento) throws InsideServiceStoreException {
		
		AuditoriaAccesoDocumento entity = new AuditoriaAccesoDocumento();
		
		entity.setId(objetoAuditoriaAccesoDocumento.getId());
		entity.setDir3Peticionario(objetoAuditoriaAccesoDocumento.getDir3Peticionario());
		entity.setFecha(objetoAuditoriaAccesoDocumento.getFecha());
		entity.setIdDocumento(objetoAuditoriaAccesoDocumento.getIdDocumento());
		entity.setIdPeticion(objetoAuditoriaAccesoDocumento.getIdPeticion());
		entity.setUsuarioPeticionario(objetoAuditoriaAccesoDocumento.getUsuarioPeticionario());
		
		return entity;

	}

	public static ObjetoAuditoriaAccesoDocumento toInside(AuditoriaAccesoDocumento entity) throws InsideServiceStoreException {
		
		ObjetoAuditoriaAccesoDocumento objetoAuditoriaAccesoDocumento = new ObjetoAuditoriaAccesoDocumento();
		
		objetoAuditoriaAccesoDocumento.setId(entity.getId());
		objetoAuditoriaAccesoDocumento.setDir3Peticionario(entity.getDir3Peticionario());
		objetoAuditoriaAccesoDocumento.setFecha(entity.getFecha());
		objetoAuditoriaAccesoDocumento.setIdDocumento(entity.getIdDocumento());
		objetoAuditoriaAccesoDocumento.setIdPeticion(entity.getIdPeticion());
		objetoAuditoriaAccesoDocumento.setUsuarioPeticionario(entity.getUsuarioPeticionario());
				
		return objetoAuditoriaAccesoDocumento;
	}

}
