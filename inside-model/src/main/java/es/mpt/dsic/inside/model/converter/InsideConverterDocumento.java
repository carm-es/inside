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
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Node;

import es.mpt.dsic.eeutil.client.visDocExp.model.DocumentoEniConMAdicionales;
import es.mpt.dsic.infofirma.model.FirmaElectronica;
import es.mpt.dsic.infofirma.model.InfoFirmaElectronica;
import es.mpt.dsic.infofirma.model.InfoFirmante;
import es.mpt.dsic.infofirma.model.TipoContenidoFirmado;
import es.mpt.dsic.infofirma.service.InfoFirmaService;
import es.mpt.dsic.infofirma.service.exception.InfoFirmaServiceException;
import es.mpt.dsic.infofirma.service.exception.InfoFirmaServiceNoEsFirmaException;
import es.mpt.dsic.inside.model.converter.exception.InsideConverterException;
import es.mpt.dsic.inside.model.converter.exception.InsideConverterUtilsException;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInside;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInsideContenido;
import es.mpt.dsic.inside.model.objetos.firmas.FirmaInside;
import es.mpt.dsic.inside.model.objetos.firmas.FirmaInsideTipoFirmaEnum;
import es.mpt.dsic.inside.model.objetos.firmas.contenido.ContenidoFirmaCSVInside;
import es.mpt.dsic.inside.model.objetos.firmas.contenido.ContenidoFirmaCertificadoAlmacenableInside;
import es.mpt.dsic.inside.model.objetos.firmas.contenido.ContenidoFirmaCertificadoContenidoBinarioInside;
import es.mpt.dsic.inside.model.objetos.firmas.contenido.ContenidoFirmaCertificadoDsSignatureInside;
import es.mpt.dsic.inside.model.objetos.firmas.contenido.ContenidoFirmaCertificadoReferenciaInside;
import es.mpt.dsic.inside.util.XMLUtils;
import es.mpt.dsic.inside.xml.eni.documento.ObjectFactory;
import es.mpt.dsic.inside.xml.eni.documento.TipoDocumento;
import es.mpt.dsic.inside.xml.eni.documento.contenido.TipoContenido;
import es.mpt.dsic.inside.xml.eni.documento.contenido.mtom.TipoContenidoMtom;
import es.mpt.dsic.inside.xml.eni.documento.mtom.TipoDocumentoMtom;
import es.mpt.dsic.inside.xml.eni.firma.Firmas;
import es.mpt.dsic.inside.xml.inside.TipoDocumentoInside;
import es.mpt.dsic.inside.xml.inside.TipoDocumentoInsideConMAdicionales;
import es.mpt.dsic.inside.xml.inside.TipoMetadatosAdicionales;
import es.mpt.dsic.inside.xml.inside.ws.documento.DocumentoInsideInfo;
import es.mpt.dsic.inside.xml.inside.ws.documento.TipoDocumentoEniFileInside;
import es.mpt.dsic.inside.xml.inside.ws.documento.alta.TipoDocumentoAltaInside;
import es.mpt.dsic.inside.xml.inside.ws.documento.conversion.TipoDocumentoConversionInside;
import es.mpt.dsic.inside.xml.inside.ws.documento.conversion.TipoDocumentoConversionInside.Csv;
import es.mpt.dsic.inside.xml.inside.ws.validacion.documento.TipoDocumentoValidacionInside;
import es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.TipoDocumentoEniBinarioOTipo;
import es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.TipoDocumentoVisualizacionInside;

public abstract class InsideConverterDocumento {

	private static final String EL_XML_INTRODUCIDO_NO_ES_UN_DOCUMENTO_ENI_VALIDO = "El xml introducido no es un Documento Eni válido ";
	protected static final Log logger = LogFactory.getLog(InsideConverterDocumento.class);

	public static ObjetoDocumentoInside documentoEniAndMetadatosToDocumentoInside(TipoDocumento documentoEni,
			TipoMetadatosAdicionales metadatosAdicionales) throws InsideConverterException {
		ObjetoDocumentoInside documentoInside = new ObjetoDocumentoInside();

		if (documentoEni.getContenido() == null) {
			throw new InsideConverterException("El contenido del documento no puede estar vacío", true);
		}

		documentoInside.setContenido(Contenido.contenidoEniToInside(documentoEni.getContenido()));
		documentoInside.setFirmas(InsideConverterFirmas.firmasEniToInside(documentoEni.getFirmas()));

		if (documentoEni.getMetadatos().getIdentificador() != null) {
			documentoInside.setIdentificador(documentoEni.getMetadatos().getIdentificador());
		}

		documentoInside.setMetadatos(
				InsideConverterMetadatos.Documento.metadatosEniToInside(documentoEni.getMetadatos(), metadatosAdicionales));

		return documentoInside;
	}

	public static ObjetoDocumentoInside documentoEniAndMetadatosMtomToDocumentoInside(TipoDocumentoMtom documentoEni,
			TipoMetadatosAdicionales metadatosAdicionales) throws InsideConverterException, IOException {
		ObjetoDocumentoInside documentoInside = new ObjetoDocumentoInside();

		if (documentoEni.getContenidoMtom() == null) {
			throw new InsideConverterException("El contenido del documento no puede estar vacío", true);
		}

		documentoInside.setContenido(Contenido.contenidoEniToInside(documentoEni.getContenidoMtom()));
		documentoInside.setFirmas(InsideConverterFirmas.firmasEniToInside(documentoEni.getFirmas()));

		if (documentoEni.getMetadatos().getIdentificador() != null) {
			documentoInside.setIdentificador(documentoEni.getMetadatos().getIdentificador());
		}

		documentoInside.setMetadatos(
				InsideConverterMetadatos.Documento.metadatosEniToInside(documentoEni.getMetadatos(), metadatosAdicionales));

		return documentoInside;
	}

	public static ObjetoDocumentoInsideContenido documentoInsideToObjetoDocumentoInsideContenido(
			ObjetoDocumentoInside documentoInside, InfoFirmaService infoFirmaService, byte[] bytesContenido)
					throws InsideConverterException {
		ObjetoDocumentoInsideContenido documentoInsideContenido = new ObjetoDocumentoInsideContenido();

		FirmaElectronica fe = new FirmaElectronica();
		InfoFirmaElectronica infoFirma = null;
		boolean contenidoEnContenido = documentoInside.getContenido().getValorBinario() != null ? true : false;

		fe.setFirma(getFirmaElectronica(documentoInside));

		try {
			infoFirma = infoFirmaService.getInfoFirma(fe, null, bytesContenido);
		} catch (InfoFirmaServiceNoEsFirmaException e) {
			checkContenidoNoEsFirma(documentoInside, documentoInsideContenido, contenidoEnContenido, e);
		} catch (InfoFirmaServiceException e) {
			checkContenidoNoEsFirma(documentoInside, documentoInsideContenido, contenidoEnContenido, e);
		}

		// Si la llamada ha tenido éxito, significa que el contenido de la
		// petición era una firma, por lo que extraemos información del
		// documento firmado de la respuesta.
		if (infoFirma != null) {
			if (infoFirma.getTipoContenidoFirmado().equals(TipoContenidoFirmado.TC_H)) {
				throw new InsideConverterException("La firma es explícita. No se pueden obtener los bytes del documento", true);
			}

			documentoInsideContenido.setValorBinario(infoFirma.getContenidoFirmado());
			documentoInsideContenido.setMime(infoFirma.getMimeContenidoFirmado());
			try {
				documentoInsideContenido
						.setNombreFormato(InsideConverterUtils.getNombreFormatoByMime(infoFirma.getMimeContenidoFirmado()));
			} catch (InsideConverterUtilsException e) {
				throw new InsideConverterException("No se puede obtener el formato del contenido firmado.", e, true);
			}
		}

		return documentoInsideContenido;
	}

	public static byte[] getFirmaElectronica(ObjetoDocumentoInside documentoInside) throws InsideConverterException {
		byte[] contenido;
		if (documentoInside.getContenido().getValorBinario() != null) {
			contenido = documentoInside.getContenido().getValorBinario();
		} else {
			String idReferencia = documentoInside.getContenido().getReferencia().substring(1);

			int i = 0;
			boolean found = false;
			ContenidoFirmaCertificadoContenidoBinarioInside contenidoFirma = null;
			while (i < documentoInside.getFirmas().size() && !found) {
				if (idReferencia.equalsIgnoreCase(documentoInside.getFirmas().get(i).getIdentificadorEnDocumento())) {
					contenidoFirma = (ContenidoFirmaCertificadoContenidoBinarioInside) documentoInside.getFirmas().get(i)
							.getContenidoFirma();
					found = true;
				}
				i++;
			}

			if (!found) {
				throw new InsideConverterException("No se ha encontrado la firma a la que hace referencia el documento.");
			}
			contenido = contenidoFirma.getValorBinario();
		}
		return contenido;
	}

	public static byte[] getFicheroFirmado(ObjetoDocumentoInside documentoInside) throws InsideConverterException {
		byte[] contenido;
		if (documentoInside.getContenido().getValorBinario() != null) {
			contenido = documentoInside.getContenido().getValorBinario();
		} else {
			String idReferencia = documentoInside.getContenido().getReferencia().substring(1);

			int i = 0;
			boolean found = false;
			ContenidoFirmaCertificadoContenidoBinarioInside contenidoFirma = null;
			while (i < documentoInside.getFirmas().size() && !found) {
				if (idReferencia.equalsIgnoreCase(documentoInside.getFirmas().get(i).getIdentificadorEnDocumento())) {
					contenidoFirma = (ContenidoFirmaCertificadoContenidoBinarioInside) documentoInside.getFirmas().get(i)
							.getContenidoFirma();
					found = true;
				}
				i++;
			}

			if (!found) {
				throw new InsideConverterException("No se ha encontrado la firma a la que hace referencia el documento.");
			}
			contenido = contenidoFirma.getValorBinario();
		}
		return contenido;
	}

	public static void checkContenidoNoEsFirma(ObjetoDocumentoInside documentoInside,
			ObjetoDocumentoInsideContenido documentoInsideContenido, boolean contenidoEnContenido, InfoFirmaServiceException e)
					throws InsideConverterException {
		// En este caso puede que el contenido del documento-e no sea una
		// firma, sino que sea simplemente el contenido del documento
		if (contenidoEnContenido) {
			documentoInsideContenido.setValorBinario(documentoInside.getContenido().getValorBinario());
			documentoInsideContenido.setMime(documentoInside.getContenido().getMime());
			documentoInsideContenido.setNombreFormato(documentoInside.getContenido().getNombreFormato());
		} else {
			throw new InsideConverterException("No se ha podido obtener información de la firma", e, true);
		}
	}

	public static ObjetoDocumentoInside documentoConversionToInside(TipoDocumentoConversionInside documentoConversion,
			InfoFirmaService infoFirmaService, byte[] contenido) throws InsideConverterException {

		ObjetoDocumentoInside documentoInside = new ObjetoDocumentoInside();
		documentoInside.setMetadatos(
				InsideConverterMetadatos.Documento.metadatosConversionToInside(documentoConversion.getMetadatosEni()));
		documentoInside.setIdentificador(documentoConversion.getMetadatosEni().getIdentificador());

		documentoInside = fillContenidoAndFirmasObjetoDocumentoInside(documentoInside, documentoConversion.getContenido(),
				contenido, documentoConversion.isFirmadoConCertificado(),
				documentoConversion.getCsv() != null ? documentoConversion.getCsv().getValorCSV() : null,
				documentoConversion.getCsv() != null ? documentoConversion.getCsv().getRegulacionCSV() : null, infoFirmaService);

		return documentoInside;

	}

	public static ObjetoDocumentoInside documentoConversionToInside(TipoDocumentoConversionInside documentoConversion,
			ObjetoDocumentoInside documentoInside) throws InsideConverterException {
		documentoInside.setMetadatos(
				InsideConverterMetadatos.Documento.metadatosConversionToInside(documentoConversion.getMetadatosEni()));
		documentoInside.setIdentificador(documentoConversion.getMetadatosEni().getIdentificador());
		return documentoInside;

	}

	public static TipoDocumentoConversionInside documentoConversionToInside(ObjetoDocumentoInside docInside)
			throws InsideConverterException {
		TipoDocumentoConversionInside retorno = new TipoDocumentoConversionInside();
		retorno.setCsv(getCsvObjetoConversionInside(docInside.getFirmas()));
		retorno.setMetadatosEni(InsideConverterMetadatos.Documento.metadatosInsideToPrimitivo(docInside.getMetadatos()));
		return retorno;
	}

	private static Csv getCsvObjetoConversionInside(List<FirmaInside> firmas) {
		Csv csv = null;
		for (FirmaInside firma : firmas) {
			if ("TF01".equals(firma.getTipoFirma().value())) {
				csv = new Csv();
				csv.setValorCSV(((ContenidoFirmaCSVInside) firma.getContenidoFirma()).getValorCSV());
				csv.setRegulacionCSV(((ContenidoFirmaCSVInside) firma.getContenidoFirma()).getRegulacionGeneracionCSV());
			}
		}
		return csv;
	}

	public static ObjetoDocumentoInside documentoAltaXmlToInside(TipoDocumentoAltaInside documentoAlta,
			InfoFirmaService infoFirmaService, byte[] contenido) throws InsideConverterException {

		ObjetoDocumentoInside documentoInside = new ObjetoDocumentoInside();

		documentoInside.setMetadatos(InsideConverterMetadatos.Documento.metadatosEniToInside(documentoAlta.getMetadatosEni(),
				documentoAlta.getMetadatosAdicionales()));

		documentoInside = fillContenidoAndFirmasObjetoDocumentoInside(documentoInside, documentoAlta.getContenido(), contenido,
				documentoAlta.isFirmadoConCertificado(),
				documentoAlta.getCsv() != null ? documentoAlta.getCsv().getValorCSV() : null,
				documentoAlta.getCsv() != null ? documentoAlta.getCsv().getRegulacionCSV() : null, infoFirmaService);

		return documentoInside;

	}

	public static ObjetoDocumentoInside fillContenidoAndFirmasObjetoDocumentoInside(ObjetoDocumentoInside documentoInside,
			byte[] bytesFirma, byte[] bytesContenido, boolean firmadoConCertificado, String csv, String regulacionCSV,
			InfoFirmaService infoFirmaService) throws InsideConverterException {
		ObjetoDocumentoInsideContenido contenido = new ObjetoDocumentoInsideContenido();
		String idDocumento = documentoInside != null ? documentoInside.getIdentificador() : "Sin identificador";
		contenido.setIdentificadorEnDocumento("CONTENIDO_DOCUMENTO");
		byte[] bytesContenidoFirma;
		// Si el documento está firmado obtenemos algunos datos de la firma
		if (firmadoConCertificado) {
			bytesContenidoFirma = convertirContenidoAFirma(bytesFirma);
			FirmaElectronica fe = new FirmaElectronica();
			fe.setFirma(bytesContenidoFirma);
			InfoFirmaElectronica infoFirma = null;
			try {
				infoFirma = infoFirmaService.getInfoFirma(fe, null, bytesContenido);
			} catch (InfoFirmaServiceNoEsFirmaException e) {
				logger.debug(e);
				throw new InsideConverterException(
						"No se ha detectado que el Contenido del documento proporcionado sea una firma o esté firmado", true);
			} catch (InfoFirmaServiceException e) {
				throw new InsideConverterException("El documento contiene una firma inválida o desconocida", e, true);
			}

			List<InfoFirmante> firmantes = infoFirma.getFirmantes();

			if ("XADES DETACHED".equalsIgnoreCase(infoFirma.getTipoFirma().getTipoFirma())
					|| "XADES ENVELOPED".equalsIgnoreCase(infoFirma.getTipoFirma().getTipoFirma())) {
				byte[] firma = bytesContenidoFirma;
				contenido.setValorBinario(firma);
				contenido.setMime("application/xml");
				try {
					contenido.setNombreFormato(InsideConverterUtils.getNombreFormatoByMime(contenido.getMime()));
				} catch (InsideConverterUtilsException e) {
					throw new InsideConverterException("Del documento: " + idDocumento
							+ ". No se puede extraer el nombre del formato a partir del Mime: " + contenido.getMime(), e, true);
				}

				for (InfoFirmante firmante2 : firmantes) {
					FirmaInside firmaInside = new FirmaInside();
					if ("XADES DETACHED".equalsIgnoreCase(infoFirma.getTipoFirma().getTipoFirma())) {
						firmaInside.setTipoFirma(FirmaInsideTipoFirmaEnum.TF_02);
					} else {
						firmaInside.setTipoFirma(FirmaInsideTipoFirmaEnum.TF_03);
					}
					ContenidoFirmaCertificadoReferenciaInside contenidoFirma = new ContenidoFirmaCertificadoReferenciaInside();
					contenidoFirma.setReferenciaFirma("#" + contenido.getIdentificadorEnDocumento());
					firmaInside.setContenidoFirma(contenidoFirma);
					firmaInside.setOrden(documentoInside.getFirmas().size());
					firmaInside.setIdentificadorEnDocumento("FIRMA_" + firmaInside.getOrden());
					documentoInside.getFirmas().add(firmaInside);
				}

				// CADES, ponemos la firma en el primer nodo de las firmas, el
				// contenido referenciará a éste.
			} else if ("CADES".equalsIgnoreCase(infoFirma.getTipoFirma().getTipoFirma())) {

				for (int i = 0; i < firmantes.size(); i++) {
					FirmaInside firmaInside = new FirmaInside();
					firmaInside.setTipoFirma(FirmaInsideTipoFirmaEnum.TF_05);
					if (i == 0) {
						ContenidoFirmaCertificadoContenidoBinarioInside contenidoFirma = new ContenidoFirmaCertificadoContenidoBinarioInside();
						contenidoFirma.setValorBinario(bytesFirma);
						contenidoFirma.setMime("application/octect-stream");
						firmaInside.setContenidoFirma(contenidoFirma);
					} else {
						ContenidoFirmaCertificadoReferenciaInside contenidoFirma = new ContenidoFirmaCertificadoReferenciaInside();
						contenidoFirma.setReferenciaFirma("#FIRMA_0");
						firmaInside.setContenidoFirma(contenidoFirma);
					}
					firmaInside.setOrden(documentoInside.getFirmas().size());
					firmaInside.setIdentificadorEnDocumento("FIRMA_" + firmaInside.getOrden());
					documentoInside.getFirmas().add(firmaInside);
				}

				contenido.setReferencia("#" + documentoInside.getFirmas().get(0).getIdentificadorEnDocumento());
				
				//a partir del mime hay que traducirlo al formato aceptado
				try {
					contenido.setNombreFormato(InsideConverterUtils.getNombreFormatoByMime(infoFirma.getMimeContenidoFirmado()));
				} catch (InsideConverterUtilsException e) {
					throw new InsideConverterException("Del documento: " + idDocumento
							+ ". No se puede extraer el nombre del formato a partir del Mime: " + infoFirma.getMimeContenidoFirmado(), e, true);
				}

				// PADES, ponemos la firma en el contenido, y desde las firmas
				// referenciamos a este contenido.
			} else if ("PADES".equalsIgnoreCase(infoFirma.getTipoFirma().getTipoFirma())) {
				contenido.setValorBinario(bytesFirma);
				contenido.setMime("application/pdf");
				try {
					contenido.setNombreFormato(InsideConverterUtils.getNombreFormatoByMime(contenido.getMime()));
				} catch (InsideConverterUtilsException e) {
					throw new InsideConverterException("Del documento: " + idDocumento
							+ ". No se puede extraer el nombre del formato a partir del Mime: " + contenido.getMime(), e, true);
				}

				for (InfoFirmante firmante : firmantes) {
					FirmaInside firmaInside = new FirmaInside();
					firmaInside.setTipoFirma(FirmaInsideTipoFirmaEnum.TF_06);

					ContenidoFirmaCertificadoReferenciaInside contenidoFirma = new ContenidoFirmaCertificadoReferenciaInside();
					contenidoFirma.setReferenciaFirma("#" + contenido.getIdentificadorEnDocumento());
					firmaInside.setContenidoFirma(contenidoFirma);
					firmaInside.setOrden(documentoInside.getFirmas().size());
					firmaInside.setIdentificadorEnDocumento("FIRMA_" + firmaInside.getOrden());
					documentoInside.getFirmas().add(firmaInside);
				}
			}
		} else {
			contenido.setValorBinario(bytesFirma);
			getMimeTypeByBytes(bytesFirma, contenido);
			try {
				contenido.setNombreFormato(InsideConverterUtils.getNombreFormatoByMime(contenido.getMime()));
			} catch (InsideConverterUtilsException e) {
				throw new InsideConverterException("Del documento: " + idDocumento
						+ ". No se puede extraer el nombre del formato a partir del Mime: " + contenido.getMime(), e, true);
			}
		}

		if (csv != null) {
			FirmaInside firmaCsv = new FirmaInside();
			firmaCsv.setTipoFirma(FirmaInsideTipoFirmaEnum.TF_01);
			ContenidoFirmaCSVInside contenidoFirmaCsv = new ContenidoFirmaCSVInside();
			contenidoFirmaCsv.setRegulacionGeneracionCSV(regulacionCSV);
			contenidoFirmaCsv.setValorCSV(csv);
			firmaCsv.setContenidoFirma(contenidoFirmaCsv);
			firmaCsv.setOrden(documentoInside.getFirmas().size());
			firmaCsv.setIdentificadorEnDocumento("FIRMA_" + firmaCsv.getOrden());
			documentoInside.getFirmas().add(firmaCsv);
		}

		documentoInside.setContenido(contenido);

		return documentoInside;

	}

	public static void getMimeTypeByBytes(byte[] bytesFirma, ObjetoDocumentoInsideContenido contenido)
			throws InsideConverterException {
		try {
			contenido.setMime(InsideConverterUtils.getMimeTypeMagic(bytesFirma));
		} catch (InsideConverterUtilsException e2) {
			throw new InsideConverterException("No se puede extraer el Mime del contenido", e2, true);
		}
	}

	public static DocumentoInsideInfo documentoInsideToDocumentoInfo(ObjetoDocumentoInside documento)
			throws InsideConverterException {
		logger.debug("Convirtiendo el ObjetoDocumentoInside a DocumentoInsideInfo");
		DocumentoInsideInfo documentoInfo = new DocumentoInsideInfo();
		documentoInfo.setIdentificador(documento.getIdentificador());
		return documentoInfo;
	}

	public static TipoDocumentoInsideConMAdicionales documentoInsideToConMAdicionales(ObjetoDocumentoInside documentoInside,
			byte[] bytesContenido) throws InsideConverterException {
		TipoDocumentoInsideConMAdicionales documentoConMAdicionales = new TipoDocumentoInsideConMAdicionales();
		documentoConMAdicionales.setDocumento(documentoInsideToEni(documentoInside, bytesContenido));
		documentoConMAdicionales.setMetadatosAdicionales(InsideConverterMetadatos
				.metadatosAdicionalesInsideToXml(documentoInside.getMetadatos().getMetadatosAdicionales()));
		return documentoConMAdicionales;
	}

	public static TipoDocumentoInside documentoInsideToXml(ObjetoDocumentoInside documentoInside, byte[] bytesContenido)
			throws InsideConverterException {
		TipoDocumentoInside documentoXml = new TipoDocumentoInside();
		documentoXml.setDocumentoENI(documentoInsideToEni(documentoInside, bytesContenido));
		documentoXml.setInfo(documentoInsideToDocumentoInfo(documentoInside));
		documentoXml.setMetadatosAdicionales(InsideConverterMetadatos
				.metadatosAdicionalesInsideToXml(documentoInside.getMetadatos().getMetadatosAdicionales()));
		return documentoXml;
	}

	public static TipoDocumento documentoInsideToEni(ObjetoDocumentoInside documentoInside, byte[] bytesContenido)
			throws InsideConverterException {
		TipoDocumento documentoEni = new TipoDocumento();
		TipoContenido contenidoEni = Contenido.contenidoInsideToEni(documentoInside.getContenido(), bytesContenido);
		documentoEni.setContenido(contenidoEni);

		Firmas firmasEni = new Firmas();

		// Documento Firmado
		if (documentoInside.getFirmas() != null) {
			firmasEni = InsideConverterFirmas.firmasInsideToEni(documentoInside.getFirmas(), false);
		}

		documentoEni.setFirmas(firmasEni);
		documentoEni.setId(documentoInside.getIdentificador());
		documentoEni.setMetadatos(InsideConverterMetadatos.Documento.metadatosInsideToEni(documentoInside.getMetadatos()));
		return documentoEni;
	}

	public static String documentoInsideToString(ObjetoDocumentoInside documentoInside) {
		StringBuilder sb = new StringBuilder("ObjetoDocumentoInside:[");
		if (documentoInside == null) {
			sb.append("null");
		} else {
			sb.append(documentoInside.toString());
		}
		sb.append("]");
		return sb.toString();
	}

	public static TipoDocumentoEniFileInside documentoEniToTipoDocumentoEniFileInside(TipoDocumento documentoEni,
			boolean cabeceraXml) throws InsideConverterException {
		ObjectFactory of = new ObjectFactory();
		JAXBElement<TipoDocumento> jaxbElement = of.createDocumento(documentoEni);

		TipoDocumentoEniFileInside documentoEniFile = new TipoDocumentoEniFileInside();

		try {
			JAXBContext jc = JAXBContext.newInstance(TipoDocumento.class);
			Marshaller marshaller = jc.createMarshaller();
			if (cabeceraXml) {
				marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.FALSE);
			} else {
				marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
			}
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			marshaller.marshal(jaxbElement, bos);
			documentoEniFile.setDocumentoEniBytes(bos.toByteArray());
		} catch (JAXBException e) {
			throw new InsideConverterException("al hacer marshal sobre documento ENI", e, false);
		}

		return documentoEniFile;
	}

	public static class Contenido {

		public static ObjetoDocumentoInsideContenido contenidoEniToInside(TipoContenido contenidoEni) {

			ObjetoDocumentoInsideContenido contenidoInside = new ObjetoDocumentoInsideContenido();
			contenidoInside.setNombreFormato(contenidoEni.getNombreFormato());

			if (contenidoEni.getDatosXML() != null) {
				String s = InsideConverterUtils.objectXMLToString(contenidoEni.getDatosXML());
				contenidoInside.setValorBinario(s.getBytes());
				contenidoInside.setMime("application/xml");
			} else if (contenidoEni.getValorBinario() != null) {
				contenidoInside.setValorBinario(contenidoEni.getValorBinario());
				contenidoInside.setMime(InsideConverterUtils.getMimeByNombreFormato(contenidoEni.getNombreFormato()));
			} else if (contenidoEni.getReferenciaFichero() != null) {
				contenidoInside.setReferencia(contenidoEni.getReferenciaFichero());
			}

			contenidoInside.setIdentificadorEnDocumento(contenidoEni.getId());
			return contenidoInside;
		}

		public static ObjetoDocumentoInsideContenido contenidoEniToInside(TipoContenidoMtom contenidoEni) throws IOException {

			ObjetoDocumentoInsideContenido contenidoInside = new ObjetoDocumentoInsideContenido();
			contenidoInside.setNombreFormato(contenidoEni.getNombreFormato());

			if (contenidoEni.getDatosXML() != null) {
				String s = InsideConverterUtils.objectXMLToString(contenidoEni.getDatosXML());
				contenidoInside.setValorBinario(s.getBytes());
				contenidoInside.setMime("application/xml");
			} else if (contenidoEni.getValorBinario() != null) {
				contenidoInside.setValorBinario(IOUtils.toByteArray(contenidoEni.getValorBinario().getInputStream()));
				contenidoInside.setMime(InsideConverterUtils.getMimeByNombreFormato(contenidoEni.getNombreFormato()));
			} else if (contenidoEni.getReferenciaFichero() != null) {
				contenidoInside.setReferencia(contenidoEni.getReferenciaFichero());
			}

			contenidoInside.setIdentificadorEnDocumento(contenidoEni.getId());
			return contenidoInside;
		}

		public static TipoContenido contenidoInsideToEni(ObjetoDocumentoInsideContenido contenidoInside, byte[] bytesContenido) {
			TipoContenido contenido = new TipoContenido();
			if (bytesContenido == null) {
				extraerContenidoFirma(contenidoInside, contenido);
			} else {
				contenido.setValorBinario(bytesContenido);
				contenido.setId(contenidoInside.getReferencia());
				contenido.setNombreFormato(contenidoInside.getNombreFormato());
			}
			return contenido;
		}

		public static void extraerContenidoFirma(ObjetoDocumentoInsideContenido contenidoInside, TipoContenido contenido) {
			if (contenidoInside.getValorBinario() != null) {
				if (esFirmaEnXml(contenidoInside)) {
					String datosXml = new String(contenidoInside.getValorBinario());
					if (datosXml.contains("<![CDATA[")) {
						datosXml = datosXml.replace("<![CDATA[", "").replace("]]>", "");
					}
					contenido.setDatosXML(datosXml);
				} else {
					contenido.setValorBinario(contenidoInside.getValorBinario());
				}
			} else if (contenidoInside.getReferencia() != null) {
				contenido.setReferenciaFichero(contenidoInside.getReferencia());
			}
			contenido.setId(contenidoInside.getIdentificadorEnDocumento());
			contenido.setNombreFormato(contenidoInside.getNombreFormato());
		}

		public static boolean esFirmaEnXml(ObjetoDocumentoInsideContenido contenidoInside) {
			return contenidoInside.getMime().contentEquals("text/xml")
					|| contenidoInside.getMime().contains("application/xml");
		}

	}

	@SuppressWarnings("deprecation")
	public static ObjetoDocumentoInside documentoVisualizacionToInside(TipoDocumentoVisualizacionInside documentoVisualizacion)
			throws InsideConverterException {
		ObjetoDocumentoInside documentoInside;
		TipoDocumentoEniBinarioOTipo docEniBinarioOTipo = documentoVisualizacion.getDocumentoEni();
		TipoDocumento docEni;
		JAXBElement<?> e;
		Unmarshaller unmarshaller = null;
		// Si se recibe el documento Eni en base64 hacemos unmarshall para
		// obtenerlo
		if (docEniBinarioOTipo.getDocumentoEniBinario() != null) {
			try {
				JAXBContext jc = JAXBContext.newInstance(TipoDocumento.class.getPackage().getName());
				unmarshaller = jc.createUnmarshaller(); //
				e = (JAXBElement<?>) unmarshaller
						.unmarshal(new ByteArrayInputStream(docEniBinarioOTipo.getDocumentoEniBinario()));
				docEni = (TipoDocumento) e.getValue();

			} catch (JAXBException e1) {
				try {
					Node nodoEni = XMLUtils.getNode(docEniBinarioOTipo.getDocumentoEniBinario(), "ns5:documento");
					if (nodoEni != null) {
						String nodoEniString = XMLUtils.documentoAdicionalWebToEni(docEniBinarioOTipo.getDocumentoEniBinario());
						e = (JAXBElement<?>) unmarshaller.unmarshal(new ByteArrayInputStream(IOUtils.toByteArray(nodoEniString)));
						docEni = (TipoDocumento) e.getValue();
					} else {
						String nodoEniString = XMLUtils.documentoAdicionalWsToEni(docEniBinarioOTipo.getDocumentoEniBinario());
						e = (JAXBElement<?>) unmarshaller.unmarshal(new ByteArrayInputStream(IOUtils.toByteArray(nodoEniString)));
						docEni = (TipoDocumento) e.getValue();
					}

				} catch (Exception e2) {
					throw new InsideConverterException(EL_XML_INTRODUCIDO_NO_ES_UN_DOCUMENTO_ENI_VALIDO, e2, true);
				}
			}
		} else {
			docEni = docEniBinarioOTipo.getDocumentoEniTipo();
		}

		TipoMetadatosAdicionales metadatosAdicionales = documentoVisualizacion.getMetadatosAdicionales();
		documentoInside = documentoEniAndMetadatosToDocumentoInside(docEni, metadatosAdicionales);
		return documentoInside;
	}

	public static ObjetoDocumentoInside tipoDocumentoValidacionToInside(TipoDocumentoValidacionInside documentoValidacion)
			throws InsideConverterException {
		// Si se recibe el documento en base64 hacemos unmarshall para
		// obtenerloç
		TipoDocumento doc = null;
		JAXBElement<?> element = null;
		Unmarshaller unmarshaller = null;
		try {
			JAXBContext jc = JAXBContext.newInstance(TipoDocumento.class.getPackage().getName());

			unmarshaller = jc.createUnmarshaller(); //
			element = (JAXBElement<?>) unmarshaller.unmarshal(new ByteArrayInputStream(documentoValidacion.getContenido()));
			doc = (TipoDocumento) element.getValue();

		} catch (JAXBException e) {
			try {
				Node nodoEni = XMLUtils.getNode(documentoValidacion.getContenido(), "ns5:documento");
				if (nodoEni != null) {
					String nodoEniString = XMLUtils.documentoAdicionalWebToEni(documentoValidacion.getContenido());
					element = (JAXBElement<?>) unmarshaller
							.unmarshal(new ByteArrayInputStream(IOUtils.toByteArray(nodoEniString)));
					doc = (TipoDocumento) element.getValue();
				} else {
					String nodoEniString = XMLUtils.documentoAdicionalWsToEni(documentoValidacion.getContenido());
					element = (JAXBElement<?>) unmarshaller
							.unmarshal(new ByteArrayInputStream(IOUtils.toByteArray(nodoEniString)));
					doc = (TipoDocumento) element.getValue();
				}

			} catch (Exception e2) {
				throw new InsideConverterException(EL_XML_INTRODUCIDO_NO_ES_UN_DOCUMENTO_ENI_VALIDO, e2, true);
			}
		}

		return documentoEniAndMetadatosToDocumentoInside(doc, null);
	}

	public static ObjetoDocumentoInside tipoDocumentoValidacionToInsideToMtom(TipoDocumentoValidacionInside documentoValidacion)
			throws InsideConverterException, IOException {
		// Si se recibe el documento en base64 hacemos unmarshall para
		// obtenerloç
		TipoDocumentoMtom doc = null;
		JAXBElement<?> element = null;
		Unmarshaller unmarshaller = null;
		try {
			JAXBContext jc = JAXBContext.newInstance(TipoDocumentoMtom.class.getPackage().getName());

			unmarshaller = jc.createUnmarshaller(); //
			element = (JAXBElement<?>) unmarshaller.unmarshal(new ByteArrayInputStream(documentoValidacion.getContenido()));
			doc = (TipoDocumentoMtom) element.getValue();

		} catch (JAXBException e) {
			try {
				Node nodoEni = XMLUtils.getNode(documentoValidacion.getContenido(), "n23:documentoMtom");
				if (nodoEni != null) {
					String nodoEniString = XMLUtils.documentoAdicionalWebToEni(documentoValidacion.getContenido());
					element = (JAXBElement<?>) unmarshaller
							.unmarshal(new ByteArrayInputStream(IOUtils.toByteArray(nodoEniString)));
					doc = (TipoDocumentoMtom) element.getValue();
				} else {
					String nodoEniString = XMLUtils.documentoAdicionalWsToEni(documentoValidacion.getContenido());
					element = (JAXBElement<?>) unmarshaller
							.unmarshal(new ByteArrayInputStream(IOUtils.toByteArray(nodoEniString)));
					doc = (TipoDocumentoMtom) element.getValue();
				}

			} catch (Exception e2) {
				throw new InsideConverterException(EL_XML_INTRODUCIDO_NO_ES_UN_DOCUMENTO_ENI_VALIDO, e2, true);
			}
		}

		return documentoEniAndMetadatosMtomToDocumentoInside(doc, null);
	}

	/**
	 * Obtiene el contenido de la firma dependiento del TipoFirma
	 * 
	 * @param firmaInside
	 *            objeto FirmaInside
	 * @param contenido
	 *            Contenido del documento
	 * @return contenido de la firma InsideConverterException error.
	 */
	public static byte[] obtenerContenidoFirma(FirmaInside firmaInside, byte[] contenido) throws InsideConverterException {
		byte[] contenidoFirma = contenido;

		// Si el documento está firmado por una firma XAdES internally detached
		// signature.
		if (FirmaInsideTipoFirmaEnum.TF_02.name().equals(firmaInside.getTipoFirma().name())
				|| FirmaInsideTipoFirmaEnum.TF_03.name().equals(firmaInside.getTipoFirma().name())) {
			if (firmaInside.getContenidoFirma() instanceof ContenidoFirmaCertificadoReferenciaInside) {
				contenidoFirma = contenido;

			} else if (firmaInside.getContenidoFirma() instanceof ContenidoFirmaCertificadoDsSignatureInside) {
				contenidoFirma = ((ContenidoFirmaCertificadoDsSignatureInside) firmaInside.getContenidoFirma()).getValorBinario();

			} else if (firmaInside.getContenidoFirma() instanceof ContenidoFirmaCertificadoAlmacenableInside) {
				contenidoFirma = ((ContenidoFirmaCertificadoAlmacenableInside) firmaInside.getContenidoFirma()).getValorBinario();

			}

			contenidoFirma = convertirContenidoAFirma(contenidoFirma);

			// Si el documento está firmado por una firma CAdES
			// attached/implicit signature
		} else if (FirmaInsideTipoFirmaEnum.TF_05.name().equals(firmaInside.getTipoFirma().name())) {
			if (firmaInside.getContenidoFirma() instanceof ContenidoFirmaCertificadoAlmacenableInside) {
				ContenidoFirmaCertificadoAlmacenableInside contenidoFirmaCertificado = (ContenidoFirmaCertificadoAlmacenableInside) firmaInside
						.getContenidoFirma();

				contenidoFirma = contenidoFirmaCertificado.getValorBinario();
			}
		} else if (FirmaInsideTipoFirmaEnum.TF_01.name().equals(firmaInside.getTipoFirma().name())
				&& firmaInside.getContenidoFirma() instanceof ContenidoFirmaCSVInside) {
			contenidoFirma = null;
		}

		return contenidoFirma;

	}

	/**
	 * Convierte el contenido en una firma para que sea válida para @firma. En
	 * el caso de la firma XAdES Detached añadiendo las etiquetas
	 * <AFIRMA></AFIRMA> si no las tiene.
	 * 
	 * @param contenidoFirma
	 *            Contenido de la firma
	 * @return firma convertida.
	 * @throws InsideConverterException
	 *             error
	 */
	public static byte[] convertirContenidoAFirma(byte[] contenidoFirma) throws InsideConverterException {
		byte[] firma = null;

		try {
			String contenidoBinarioStr = org.apache.commons.io.IOUtils.toString(new ByteArrayInputStream(contenidoFirma));
			if (contenidoBinarioStr.indexOf("Signature") > -1 && contenidoBinarioStr.indexOf("SignedInfo") > -1) {
				// Infofirma no reconoce la firma si contiene <![CDATA[
				if (contenidoBinarioStr.startsWith("<![CDATA[")) {
					contenidoBinarioStr = contenidoBinarioStr.substring("<![CDATA[".length());
					contenidoBinarioStr = contenidoBinarioStr.substring(0, contenidoBinarioStr.lastIndexOf("]]>"));
				}

				firma = contenidoBinarioStr.getBytes();
			} else {
				firma = contenidoFirma;
			}

		} catch (IOException e) {
			throw new InsideConverterException("Se ha producido un error al convertir el contenido a firma: ", e, true);
		}

		return firma;

	}

	public static DocumentoEniConMAdicionales convertToEeutil(ObjetoDocumentoInside objetoDocumentoInside)
			throws InsideConverterException {
		DocumentoEniConMAdicionales retorno = new DocumentoEniConMAdicionales();

		TipoDocumentoInsideConMAdicionales docAdicionales = documentoInsideToConMAdicionales(objetoDocumentoInside,
				objetoDocumentoInside.getContenido().getValorBinario());

		es.mpt.dsic.eeutil.client.visDocExp.model.TipoDocumento tipoDocumento = TipoDocumentoConverter
				.tipoDocumentoInsideToEeutilClient(docAdicionales.getDocumento());
		es.mpt.dsic.eeutil.client.visDocExp.model.TipoMetadatosAdicionales adicionales = MetadatoAdicionalConverter
				.metadatoAdicionalInsideToEeutilClient(docAdicionales.getMetadatosAdicionales());

		retorno.setDocumento(tipoDocumento);
		retorno.setMetadatosAdicionales(adicionales);
		return retorno;
	}

}
