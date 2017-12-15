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


import es.mpt.dsic.inside.model.objetos.expediente.ObjetoSolicitudAccesoExpAppUrl;
import es.mpt.dsic.inside.service.store.exception.InsideServiceStoreException;
import es.mpt.dsic.inside.store.hibernate.entity.SolicitudAccesoExpAppUrl;


public class InsideServiceStoreHibernateConverterSolicitudAccesoExpAppUrl {
	
	public static SolicitudAccesoExpAppUrl toEntity(ObjetoSolicitudAccesoExpAppUrl objetoSolicitudAccesoExpAppUrl) throws InsideServiceStoreException {
		
		SolicitudAccesoExpAppUrl entity = new SolicitudAccesoExpAppUrl();
		
		entity.setId(objetoSolicitudAccesoExpAppUrl.getId());
		entity.setDir3Padre(objetoSolicitudAccesoExpAppUrl.getDir3Padre());
		entity.setUrlDestinoPeticion(objetoSolicitudAccesoExpAppUrl.getUrlDestinoPeticion());
		
		return entity;

	}

	public static ObjetoSolicitudAccesoExpAppUrl toInside(SolicitudAccesoExpAppUrl entity) throws InsideServiceStoreException {
		
		ObjetoSolicitudAccesoExpAppUrl objetoSolicitudAccesoExpAppUrl = new ObjetoSolicitudAccesoExpAppUrl();
		
		objetoSolicitudAccesoExpAppUrl.setId(entity.getId());
		objetoSolicitudAccesoExpAppUrl.setDir3Padre(entity.getDir3Padre());
		objetoSolicitudAccesoExpAppUrl.setUrlDestinoPeticion(entity.getUrlDestinoPeticion());
				
		return objetoSolicitudAccesoExpAppUrl;
	}

}
