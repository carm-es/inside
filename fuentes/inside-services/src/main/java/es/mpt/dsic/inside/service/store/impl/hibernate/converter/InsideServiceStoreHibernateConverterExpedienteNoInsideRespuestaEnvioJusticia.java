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

import es.mpt.dsic.inside.model.objetos.ObjectInsideRespuestaEnvioJusticia;
import es.mpt.dsic.inside.service.store.exception.InsideServiceStoreException;
import es.mpt.dsic.inside.store.hibernate.entity.ExpedienteNoInsideRespuestaEnvioJusticia;



public class InsideServiceStoreHibernateConverterExpedienteNoInsideRespuestaEnvioJusticia {
	
	public ObjectInsideRespuestaEnvioJusticia toInside(ExpedienteNoInsideRespuestaEnvioJusticia entity) throws InsideServiceStoreException {
		ObjectInsideRespuestaEnvioJusticia objetoInside = new ObjectInsideRespuestaEnvioJusticia();
		
		objetoInside.setAuditoriaEsb_aplicacion(entity.getAuditoriaEsb_aplicacion());
		objetoInside.setAuditoriaEsb_marcaTiempo(entity.getAuditoriaEsb_marcaTiempo());
		objetoInside.setAuditoriaEsb_modulo(entity.getAuditoriaEsb_modulo());
		objetoInside.setAuditoriaEsb_servicio(entity.getAuditoriaEsb_servicio());
		objetoInside.setCodigoEnvio(entity.getCodigoEnvio());
		objetoInside.setCodigoUnidadOrganoRemitente(entity.getCodigoUnidadOrganoRemitente());
		objetoInside.setMensaje(entity.getMensaje());
		objetoInside.setAck(entity.getAck());
		
		return objetoInside;
	}

}
