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


import es.mpt.dsic.inside.model.objetos.expediente.ObjetoComunicacionTokenExpediente;
import es.mpt.dsic.inside.service.store.exception.InsideServiceStoreException;
import es.mpt.dsic.inside.store.hibernate.entity.ComunicacionTokenExpediente;


public class InsideServiceStoreHibernateConverterComunicacionTokenExpediente {
	
	public static ComunicacionTokenExpediente toEntity(ObjetoComunicacionTokenExpediente objetoComunicacionTokenExpediente) throws InsideServiceStoreException {
		
		ComunicacionTokenExpediente entity = new ComunicacionTokenExpediente();
		
		entity.setId(objetoComunicacionTokenExpediente.getId());
		entity.setEndpointRemitente(objetoComunicacionTokenExpediente.getEndpointRemitente());
		entity.setRJDir3Remitente(objetoComunicacionTokenExpediente.getDir3Remitente());
		entity.setAsunto(objetoComunicacionTokenExpediente.getAsunto());
		entity.setFechaPeticion(objetoComunicacionTokenExpediente.getFechaPeticion());
		entity.setIdPeticion(objetoComunicacionTokenExpediente.getIdPeticion());
		entity.setResultado(objetoComunicacionTokenExpediente.getResultado());
		entity.setRJAnyoProcedimiento(objetoComunicacionTokenExpediente.getRJAnyoProcedimiento());
		entity.setRJClaseProcedimiento(objetoComunicacionTokenExpediente.getRJClaseProcedimiento());
		entity.setRJDescripcion(objetoComunicacionTokenExpediente.getRJDescripcion());
		entity.setDir3Destino(objetoComunicacionTokenExpediente.getDir3Destino());
		entity.setRJNig(objetoComunicacionTokenExpediente.getRJNig());
		entity.setRJNumeroProcedimiento(objetoComunicacionTokenExpediente.getRJNumeroProcedimiento());
		entity.setTokenCSV(objetoComunicacionTokenExpediente.getTokenCSV());
		entity.setTokenUUID(objetoComunicacionTokenExpediente.getTokenUUID());
		entity.setTokenIdExpEni(objetoComunicacionTokenExpediente.getTokenIdExpEni());
		entity.setUsuarioPeticionario(objetoComunicacionTokenExpediente.getUsuarioPeticionario());
		entity.setUrlAccesoWeb(objetoComunicacionTokenExpediente.getUrlAccesoWeb());
		entity.setIndiceExpediente(objetoComunicacionTokenExpediente.getIndiceExpediente().getBytes());
		entity.setCorrecto(objetoComunicacionTokenExpediente.getCorrecto());
		entity.setNIntentos(objetoComunicacionTokenExpediente.getnIntentos());
		entity.setFechaProximoIntento(objetoComunicacionTokenExpediente.getFechaProximoIntento());
		entity.setResultadoNotificado(objetoComunicacionTokenExpediente.getResultadoNotificado());
		
		return entity;

	}

	public static ObjetoComunicacionTokenExpediente toInside(ComunicacionTokenExpediente entity) throws InsideServiceStoreException {
		
		ObjetoComunicacionTokenExpediente objetoComunicacionTokenExpediente = new ObjetoComunicacionTokenExpediente();
		
		objetoComunicacionTokenExpediente.setId(entity.getId());
		objetoComunicacionTokenExpediente.setEndpointRemitente(entity.getEndpointRemitente());
		objetoComunicacionTokenExpediente.setDir3Remitente(entity.getRJDir3Remitente());
		objetoComunicacionTokenExpediente.setAsunto(entity.getAsunto());
		objetoComunicacionTokenExpediente.setFechaPeticion(entity.getFechaPeticion());
		objetoComunicacionTokenExpediente.setIdPeticion(entity.getIdPeticion());
		objetoComunicacionTokenExpediente.setResultado(entity.getResultado());
		objetoComunicacionTokenExpediente.setRJAnyoProcedimiento(entity.getRJAnyoProcedimiento());
		objetoComunicacionTokenExpediente.setRJClaseProcedimiento(entity.getRJClaseProcedimiento());
		objetoComunicacionTokenExpediente.setRJDescripcion(entity.getRJDescripcion());
		objetoComunicacionTokenExpediente.setDir3Destino(entity.getDir3Destino());
		objetoComunicacionTokenExpediente.setRJNig(entity.getRJNig());
		objetoComunicacionTokenExpediente.setRJNumeroProcedimiento(entity.getRJNumeroProcedimiento());
		objetoComunicacionTokenExpediente.setTokenCSV(entity.getTokenCSV());
		objetoComunicacionTokenExpediente.setTokenUUID(entity.getTokenUUID());
		objetoComunicacionTokenExpediente.setTokenIdExpEni(entity.getTokenIdExpEni());
		objetoComunicacionTokenExpediente.setUsuarioPeticionario(entity.getUsuarioPeticionario());
		objetoComunicacionTokenExpediente.setUrlAccesoWeb(entity.getUrlAccesoWeb());
		objetoComunicacionTokenExpediente.setIndiceExpediente(new String(entity.getIndiceExpediente()));
		objetoComunicacionTokenExpediente.setCorrecto(entity.getCorrecto());
		objetoComunicacionTokenExpediente.setnIntentos(entity.getNIntentos());
		objetoComunicacionTokenExpediente.setResultadoNotificado(entity.getResultadoNotificado());
						
		return objetoComunicacionTokenExpediente;
	}

}
