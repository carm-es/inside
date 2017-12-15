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

package es.mpt.dsic.inside.service.eni.impl;

import javax.xml.bind.JAXBException;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.mpt.dsic.inside.service.eni.EniService;
import es.mpt.dsic.inside.service.util.XMLUtils;
import es.mpt.dsic.inside.util.xml.JAXBMarshaller;
import es.mpt.dsic.inside.xml.inside.TipoExpedienteInsideConMAdicionales;

@Service
public class EniServiceImpl implements EniService {
	
	@Autowired
	private JAXBMarshaller marshaller;

	@Override
	public String getXml(TipoExpedienteInsideConMAdicionales tipoExpedienteMA) throws JAXBException {
		String data;
		if (tipoExpedienteMA.getMetadatosAdicionales() != null
				&& CollectionUtils.isNotEmpty(tipoExpedienteMA.getMetadatosAdicionales().getMetadatoAdicional())) {

			data = marshaller.marshallDataExpedient(tipoExpedienteMA);
		} else {
			data = marshaller.marshallDataExpedient(tipoExpedienteMA.getExpediente());
		}

		// Para validar la firma recoger solo el ns7:expediente sin
		// metadatosadicionales
		String dataConFirmaSinIdentar = marshaller.marshallDataExpedient(tipoExpedienteMA.getExpediente());

		// sustituye el nodo <ns7:expediente por el dataConFirmaSinIdentar
		// que esta sin identar y bien formado
		return XMLUtils.construirExpedienteENIValido(data, dataConFirmaSinIdentar);
	}

}
