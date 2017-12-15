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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Node;

import es.mpt.dsic.inside.model.converter.exception.InsideConverterException;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteInside;
import es.mpt.dsic.inside.model.objetos.firmas.FirmaInside;
import es.mpt.dsic.inside.util.XMLUtils;
import es.mpt.dsic.inside.xml.eni.expediente.ObjectFactory;
import es.mpt.dsic.inside.xml.eni.expediente.TipoExpediente;
import es.mpt.dsic.inside.xml.eni.expediente.indice.TipoIndice;
import es.mpt.dsic.inside.xml.eni.expediente.metadatos.EnumeracionEstados;
import es.mpt.dsic.inside.xml.eni.expediente.metadatos.TipoMetadatos;
import es.mpt.dsic.inside.xml.eni.firma.TipoFirmasElectronicas;
import es.mpt.dsic.inside.xml.inside.TipoExpedienteInside;
import es.mpt.dsic.inside.xml.inside.TipoExpedienteInsideConMAdicionales;
import es.mpt.dsic.inside.xml.inside.TipoMetadatosAdicionales;
import es.mpt.dsic.inside.xml.inside.ws.expediente.TipoExpedienteEniFileInside;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoExpedienteConversionInside;
import es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.TipoExpedienteValidacionInside;
import es.mpt.dsic.inside.xml.w3c.SignatureType;

/**
 *
 * @author miguel.ortega
 *
 */
public abstract class InsideConverterExpediente {
	private static final String EL_XML_INTRODUCIDO_NO_ES_UN_EXPEDIENTE_ENI_VALIDO = "El xml introducido no es un Expediente Eni válido ";
	protected static final Log logger = LogFactory.getLog(InsideConverterExpediente.class);

	/**
	 * Crea un ObjetoExpedienteInside a partir de los metadatos de entrada
	 * 
	 * @param metadatosEni
	 * @param metadatosAdicionales
	 * @return
	 */
	public static ObjetoExpedienteInside fromMetadatos(TipoMetadatos metadatosEni,
			TipoMetadatosAdicionales metadatosAdicionales) {

		ObjetoExpedienteInside expediente = new ObjetoExpedienteInside();

		if (metadatosEni.getIdentificador() != null) {
			expediente.setIdentificador(metadatosEni.getIdentificador());
		}

		expediente.setMetadatos(
				InsideConverterMetadatos.Expediente.metadatosXmlToMetadatosInside(metadatosEni, metadatosAdicionales));

		return expediente;

	}

	/**
	 * Crea un ObjetoExpedienteInside a partir de un TipoExpediente(Eni) de
	 * entrada
	 * 
	 * @param expedienteEni
	 * @return
	 * @throws InsideConverterEniToInsideException
	 * @throws InsideWsRequestErrorException
	 */
	public static ObjetoExpedienteInside expedienteEniToInside(TipoExpediente expedienteEni) throws InsideConverterException {
		ObjetoExpedienteInside expediente = new ObjetoExpedienteInside();
		if (expedienteEni.getMetadatosExp().getIdentificador() == null) {
			throw new InsideConverterException("El identificador de los metadatos del expediente no puede estar vacio", true);
		}
		expediente.setIdentificador(expedienteEni.getMetadatosExp().getIdentificador());

		boolean expedienteCerrado = false;
		if (expedienteEni.getMetadatosExp().getEstado().getValue().equals(EnumeracionEstados.E_02))
			expedienteCerrado = true;

		expediente.setIndice(InsideConverterIndice.indiceEniToInside(expedienteEni.getIndice(), expedienteCerrado));

		if (expedienteEni.getVisualizacionIndice() != null) {
			expediente.setVisualizacionIndice(
					InsideConverterDocumento.Contenido.contenidoEniToInside(expedienteEni.getVisualizacionIndice()));
		}
		expediente
				.setMetadatos(InsideConverterMetadatos.Expediente.metadatosEniToMetadatosInside(expedienteEni.getMetadatosExp()));
		return expediente;
	}

	/**
	 * Crea un ObjetoExpedienteInside a partir de un TipoExpediente(Eni) de
	 * entrada y un listado de metadatos adicionales
	 * 
	 * @param expedienteEni
	 * @param metadatosAdicionales
	 * @return
	 * @throws InsideConverterEniToInsideException
	 * @throws InsideWsRequestErrorException
	 */
	public static ObjetoExpedienteInside expedienteEniToInside(TipoExpediente expedienteEni,
			TipoMetadatosAdicionales metadatosAdicionales) throws InsideConverterException {
		ObjetoExpedienteInside expediente = expedienteEniToInside(expedienteEni);
		expediente.getMetadatos()
				.setMetadatosAdicionales(InsideConverterMetadatos.metadatosAdicionalesXmlToInside(metadatosAdicionales));
		return expediente;
	}

	public static TipoExpediente expedienteInsideToEni(ObjetoExpedienteInside expediente, byte[] bytesContenido)
			throws InsideConverterException {
		TipoExpediente expedienteEni = new TipoExpediente();

		expedienteEni.setId(expediente.getIdentificador());
		// try {
		expedienteEni.setIndice(InsideConverterIndice.indiceInsideToEni(expediente.getIndice(), expediente.getIdentificador()));
		expedienteEni.setMetadatosExp(InsideConverterMetadatos.Expediente.metadatosInsideToEni(expediente.getMetadatos()));
		/*
		 * } catch (InsideConverterException e) { throw new
		 * InsideConverterExpedienteInsideToEniException(e); }
		 */
		if (expediente.getVisualizacionIndice() != null) {
			expedienteEni.setVisualizacionIndice(
					InsideConverterDocumento.Contenido.contenidoInsideToEni(expediente.getVisualizacionIndice(), bytesContenido));
		}
		return expedienteEni;
	}

	public static TipoExpedienteInside expedienteInsideToXml(ObjetoExpedienteInside expedienteInside, byte[] bytesContenido)
			throws InsideConverterException {
		TipoExpedienteInside expedienteXml = new TipoExpedienteInside();
		expedienteXml.setExpedienteENI(expedienteInsideToEni(expedienteInside, bytesContenido));
		expedienteXml.setMetadatosAdicionales(InsideConverterMetadatos
				.metadatosAdicionalesInsideToXml(expedienteInside.getMetadatos().getMetadatosAdicionales()));
		expedienteXml.setInfo(InsideConverterExpedienteInfo.fromExpediente(expedienteInside));
		return expedienteXml;
	}

	public static ObjetoExpedienteInside expedienteConversionToInside(TipoExpedienteConversionInside expedienteConversion)
			throws InsideConverterException {

		ObjetoExpedienteInside expedienteInside = new ObjetoExpedienteInside();
		expedienteInside.setIdentificador(expedienteConversion.getMetadatosEni().getIdentificador());

		expedienteInside.setMetadatos(InsideConverterMetadatos.Expediente.metadatosPrimitivoToInside(
				expedienteConversion.getMetadatosEni().getClasificacion(),
				expedienteConversion.getMetadatosEni().getEstado().getValue().value(),
				expedienteConversion.getMetadatosEni().getFechaAperturaExpediente(),
				expedienteConversion.getMetadatosEni().getInteresado(), expedienteConversion.getMetadatosEni().getOrgano(),
				expedienteConversion.getMetadatosEni().getVersionNTI(),
				expedienteConversion.getMetadatosEni().getIdentificador()));
		expedienteInside.setIndice(InsideConverterIndice.indiceConversionToInside(expedienteConversion.getIndice()));
		expedienteInside.getIndice().setFirmas(new ArrayList<FirmaInside>());

		return expedienteInside;

	}

	public static ObjetoExpedienteInside expedienteConversionToInside(TipoExpediente expedienteConversion)
			throws InsideConverterException {

		ObjetoExpedienteInside expedienteInside = new ObjetoExpedienteInside();
		expedienteInside.setIdentificador(expedienteConversion.getMetadatosExp().getIdentificador());

		expedienteInside.setMetadatos(InsideConverterMetadatos.Expediente.metadatosPrimitivoToInside(
				expedienteConversion.getMetadatosExp().getClasificacion(),
				expedienteConversion.getMetadatosExp().getEstado().getValue().value(),
				expedienteConversion.getMetadatosExp().getFechaAperturaExpediente(),
				expedienteConversion.getMetadatosExp().getInteresado(), expedienteConversion.getMetadatosExp().getOrgano(),
				expedienteConversion.getMetadatosExp().getVersionNTI(),
				expedienteConversion.getMetadatosExp().getIdentificador()));
		expedienteInside.setIndice(InsideConverterIndice.indiceConversionToInside(expedienteConversion.getIndice()));
		expedienteInside.getIndice().setFirmas(new ArrayList<FirmaInside>());

		return expedienteInside;

	}

	public static TipoExpedienteInsideConMAdicionales expedienteInsideToConMAdicionales(ObjetoExpedienteInside expedienteInside,
			byte[] bytesContenido) throws InsideConverterException {
		TipoExpedienteInsideConMAdicionales expedienteConMAdicionales = new TipoExpedienteInsideConMAdicionales();
		expedienteConMAdicionales.setExpediente(expedienteInsideToEni(expedienteInside, bytesContenido));
		expedienteConMAdicionales.setMetadatosAdicionales(InsideConverterMetadatos
				.metadatosAdicionalesInsideToXml(expedienteInside.getMetadatos().getMetadatosAdicionales()));
		return expedienteConMAdicionales;
	}

	public static TipoExpedienteEniFileInside expedienteEniToTipoExpedienteEniFileInside(TipoExpediente expedienteEni,
			boolean cabeceraXml) throws InsideConverterException {
		ObjectFactory of = new ObjectFactory();
		JAXBElement<TipoExpediente> jaxbElement = of.createExpediente(expedienteEni);

		TipoExpedienteEniFileInside expedienteEniFile = new TipoExpedienteEniFileInside();

		try {
			JAXBContext jc = JAXBContext.newInstance(TipoExpediente.class);
			Marshaller marshaller = jc.createMarshaller();
			if (!cabeceraXml) {
				marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
			}
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			marshaller.marshal(jaxbElement, bos);
			expedienteEniFile.setExpedienteEniBytes(bos.toByteArray());
		} catch (JAXBException e) {
			throw new InsideConverterException("No se puede parsear el  expediente ENI", e, true);
		}

		return expedienteEniFile;
	}

	public static String expedienteInsideToString(ObjetoExpedienteInside expedienteInside) {
		StringBuffer sb = new StringBuffer("ObjetoExpedienteInside:[");
		if (expedienteInside == null) {
			sb.append("null");
		} else {
			sb.append(expedienteInside.toString());
		}
		sb.append("]");
		return sb.toString();
	}

	@SuppressWarnings("deprecation")
	public static ObjetoExpedienteInside tipoExpedienteValidacionToInside(TipoExpedienteValidacionInside expedienteValidacion)
			throws InsideConverterException {
		// Si se recibe el documento en base64 hacemos unmarshall para
		// obtenerloç
		TipoExpediente exp = null;
		Unmarshaller unmarshaller = null;
		JAXBElement<?> element = null;
		try {
			JAXBContext jc = JAXBContext.newInstance(TipoExpediente.class.getPackage().getName());

			unmarshaller = jc.createUnmarshaller(); //
			element = (JAXBElement<?>) unmarshaller.unmarshal(new ByteArrayInputStream(expedienteValidacion.getContenido()));
			exp = (TipoExpediente) element.getValue();

		} catch (JAXBException e) {
			try {
				Node nodoEni = XMLUtils.getNode(expedienteValidacion.getContenido(), "ns7:expediente");
				if (nodoEni != null) {
					String nodoEniString = XMLUtils.expedienteAdicionalWebToEni(expedienteValidacion.getContenido());
					element = (JAXBElement<?>) unmarshaller
							.unmarshal(new ByteArrayInputStream(IOUtils.toByteArray(nodoEniString)));
					exp = (TipoExpediente) element.getValue();
				} else {
					String nodoEniString = XMLUtils.expedienteAdicionalWsToEni(expedienteValidacion.getContenido());
					element = (JAXBElement<?>) unmarshaller
							.unmarshal(new ByteArrayInputStream(IOUtils.toByteArray(nodoEniString)));
					exp = (TipoExpediente) element.getValue();
				}

			} catch (Exception e2) {
				logger.debug(e2.getMessage());
				throw new InsideConverterException(EL_XML_INTRODUCIDO_NO_ES_UN_EXPEDIENTE_ENI_VALIDO, e2, true);
			}
		}

		return expedienteEniToInside(exp);
	}

	public static byte[] getFirmaElectronica(ObjetoExpedienteInside expedienteInside) throws InsideConverterException {
		byte[] contenido;
		TipoIndice indiceEni = InsideConverterIndice.indiceInsideToEni(expedienteInside.getIndice(),
				expedienteInside.getIdentificador());
		TipoFirmasElectronicas tipoFirma = indiceEni.getFirmas().getFirma().get(0);
		if (tipoFirma.getContenidoFirma().getFirmaConCertificado().getFirmaBase64() != null) {
			contenido = tipoFirma.getContenidoFirma().getFirmaConCertificado().getFirmaBase64();
		} else {
			SignatureType signaturaType = tipoFirma.getContenidoFirma().getFirmaConCertificado().getSignature();
			String indice = indiceEni.getIndiceContenido().toString();
			StringBuilder firma = new StringBuilder("<AFIRMA>");
			firma.append("<CONTENT Id=\"EXP_INDICE_CONTENIDO" + expedienteInside.getIdentificador() + "\">");
		}

		contenido = null;
		return contenido;
	}

}
