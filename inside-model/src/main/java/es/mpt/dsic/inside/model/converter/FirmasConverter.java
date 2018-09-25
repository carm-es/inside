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

package es.mpt.dsic.inside.model.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import es.mpt.dsic.eeutil.client.visDocExp.model.Firmas;
import es.mpt.dsic.eeutil.client.visDocExp.model.TipoFirma;
import es.mpt.dsic.eeutil.client.visDocExp.model.TipoFirmasElectronicas;
import es.mpt.dsic.eeutil.client.visDocExp.model.TipoFirmasElectronicas.ContenidoFirma;
import es.mpt.dsic.eeutil.client.visDocExp.model.TipoFirmasElectronicas.ContenidoFirma.CSV;
import es.mpt.dsic.eeutil.client.visDocExp.model.TipoFirmasElectronicas.ContenidoFirma.FirmaConCertificado;

public abstract class FirmasConverter {

	public static Firmas firmasInsideToEeutilClient(es.mpt.dsic.inside.xml.eni.firma.Firmas input, byte[] contenidoDocumento) {
		Firmas retorno = new Firmas();
		retorno.getFirma().addAll(tipoFirmasInsideToEeutilClient(input.getFirma(), contenidoDocumento));
		return retorno;
	}

	public static List<TipoFirmasElectronicas> tipoFirmasInsideToEeutilClient(
			List<es.mpt.dsic.inside.xml.eni.firma.TipoFirmasElectronicas> input, byte[] contenidoDocumento) {
		List<TipoFirmasElectronicas> retorno = new ArrayList<TipoFirmasElectronicas>();
		if (CollectionUtils.isNotEmpty(input)) {
			for (es.mpt.dsic.inside.xml.eni.firma.TipoFirmasElectronicas tipoFirma : input) {
				TipoFirmasElectronicas data = new TipoFirmasElectronicas();
				data.setContenidoFirma(contenidoFirmaInsideToEeutilClient(tipoFirma.getContenidoFirma(), contenidoDocumento));
				data.setRef(tipoFirma.getRef());
				data.setTipoFirma(tipoFirmaInsideToEeutilClient(tipoFirma.getTipoFirma()));
				retorno.add(data);
			}
		}
		return retorno;
	}

	public static ContenidoFirma contenidoFirmaInsideToEeutilClient(
			es.mpt.dsic.inside.xml.eni.firma.TipoFirmasElectronicas.ContenidoFirma input, byte[] contenidoDocumento) {
		ContenidoFirma retorno = new ContenidoFirma();
		if (input.getCSV() != null) {
			retorno.setCSV(csvInsideToEeutilClient(input.getCSV()));
		}
		retorno.setFirmaConCertificado(firmaConCertificadoInsideToEeutilClient(input.getFirmaConCertificado(), contenidoDocumento));
		return retorno;
	}

	public static CSV csvInsideToEeutilClient(es.mpt.dsic.inside.xml.eni.firma.TipoFirmasElectronicas.ContenidoFirma.CSV input) {
		CSV retorno = new CSV();
		retorno.setRegulacionGeneracionCSV(input.getRegulacionGeneracionCSV());
		retorno.setValorCSV(input.getValorCSV());
		return retorno;
	}

	public static FirmaConCertificado firmaConCertificadoInsideToEeutilClient(
			es.mpt.dsic.inside.xml.eni.firma.TipoFirmasElectronicas.ContenidoFirma.FirmaConCertificado input, byte[] contenidoDocumento) {
		FirmaConCertificado retorno = new FirmaConCertificado();
		if (input.getFirmaBase64() != null) {
			retorno.setFirmaBase64(input.getFirmaBase64());
		} else {
			retorno.setFirmaBase64(contenidoDocumento);
		}
		return retorno;
	}
	
	public static TipoFirma tipoFirmaInsideToEeutilClient(es.mpt.dsic.inside.xml.eni.firma.TipoFirma input) {
		return TipoFirma.fromValue(input.value());
	}

}
