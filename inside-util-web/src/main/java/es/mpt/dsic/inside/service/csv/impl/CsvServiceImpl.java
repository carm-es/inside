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

package es.mpt.dsic.inside.service.csv.impl;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.mpt.dsic.inside.model.converter.InsideConverterExpediente;
import es.mpt.dsic.inside.model.converter.InsideConverterMetadatos;
import es.mpt.dsic.inside.model.converter.exception.InsideConverterException;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteInside;
import es.mpt.dsic.inside.service.csv.CsvService;
import es.mpt.dsic.inside.service.eni.EniService;
import es.mpt.dsic.inside.service.exception.InSideServiceException;
import es.mpt.dsic.inside.service.exception.InsideServiceInternalException;
import es.mpt.dsic.inside.service.util.XMLUtils;
import es.mpt.dsic.inside.service.utilFirma.InsideServiceUtilFirma;
import es.mpt.dsic.inside.xml.inside.TipoExpedienteInsideConMAdicionales;

@Service
public class CsvServiceImpl implements CsvService {

	@Autowired
	InsideServiceUtilFirma utilFirmaService;
	
	@Autowired
	EniService eniService;

	@Override
	public String generarCSV(ObjetoExpedienteInside expediente)
			throws InSideServiceException {

		try {
			TipoExpedienteInsideConMAdicionales tipoExpedienteMA = new TipoExpedienteInsideConMAdicionales();
			tipoExpedienteMA.setExpediente(InsideConverterExpediente.expedienteInsideToEni(expediente, null));
			tipoExpedienteMA.setMetadatosAdicionales(
					InsideConverterMetadatos.metadatosAdicionalesInsideToXml(expediente.getMetadatos().getMetadatosAdicionales()));

			String xml = eniService.getXml(tipoExpedienteMA);
		
			return utilFirmaService.generarCSV(xml.getBytes(XMLUtils.UTF8_CHARSET), "application/xml");
		} catch (InsideConverterException e) {
			throw new InsideServiceInternalException(e.getMessage(), e);
		} catch (JAXBException e) {
			throw new InsideServiceInternalException(e.getMessage(), e);
		} 
	}

}
