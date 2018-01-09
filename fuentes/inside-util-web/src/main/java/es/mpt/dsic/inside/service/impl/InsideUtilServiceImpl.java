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

package es.mpt.dsic.inside.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.xml.sax.SAXException;

import es.mpt.dsic.infofirma.service.InfoFirmaService;
import es.mpt.dsic.inside.model.converter.InsideConverterDocumento;
import es.mpt.dsic.inside.model.converter.InsideConverterExpediente;
import es.mpt.dsic.inside.model.converter.InsideConverterMetadatos;
import es.mpt.dsic.inside.model.converter.InsideConverterValidacion;
import es.mpt.dsic.inside.model.converter.exception.InsideConverterException;
import es.mpt.dsic.inside.model.objetos.ObjectInsideRespuestaEnvioJusticia;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideUnidad;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInside;
import es.mpt.dsic.inside.model.objetos.enivalidation.ResultadoValidacion;
import es.mpt.dsic.inside.model.objetos.enivalidation.ResultadoValidacionDocumento;
import es.mpt.dsic.inside.model.objetos.enivalidation.ResultadoValidacionExpediente;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteInside;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteToken;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado;
import es.mpt.dsic.inside.model.objetos.firmas.FirmaInside;
import es.mpt.dsic.inside.model.objetos.firmas.FirmaInsideTipoFirmaEnum;
import es.mpt.dsic.inside.model.objetos.firmas.contenido.ContenidoFirmaCertificadoContenidoBinarioInside;
import es.mpt.dsic.inside.service.InSideService;
import es.mpt.dsic.inside.service.InsideUtilService;
import es.mpt.dsic.inside.service.TemporalDataBusinessService;
import es.mpt.dsic.inside.service.exception.InSideServiceException;
import es.mpt.dsic.inside.service.exception.InsideServiceInternalException;
import es.mpt.dsic.inside.service.object.metadatos.validator.Exception.InSideServiceValidationException;
import es.mpt.dsic.inside.service.object.metadatos.validator.impl.MetadatoValidatorStringRegex;
import es.mpt.dsic.inside.service.store.exception.InsideStoreObjectNoActiveException;
import es.mpt.dsic.inside.service.store.exception.InsideStoreObjectNotFoundException;
import es.mpt.dsic.inside.service.visualizacion.ResultadoVisualizacionDocumento;
import es.mpt.dsic.inside.util.XMLUtils;
import es.mpt.dsic.inside.util.xml.JAXBMarshaller;
import es.mpt.dsic.inside.web.object.MessageObject;
import es.mpt.dsic.inside.web.util.InsideWSUtils;
import es.mpt.dsic.inside.web.util.WebConstants;
import es.mpt.dsic.inside.ws.exception.InsideWSException;
import es.mpt.dsic.inside.ws.exception.InsideWsErrors;
import es.mpt.dsic.inside.ws.validation.InsideValidationDataService;
import es.mpt.dsic.inside.ws.validation.exception.InsideValidationDataException;
import es.mpt.dsic.inside.xml.eni.documento.TipoDocumento;
import es.mpt.dsic.inside.xml.eni.expediente.TipoExpediente;
import es.mpt.dsic.inside.xml.inside.MetadatoAdicional;
import es.mpt.dsic.inside.xml.inside.TipoDocumentoInsideConMAdicionales;
import es.mpt.dsic.inside.xml.inside.TipoExpedienteInsideConMAdicionales;
import es.mpt.dsic.inside.xml.inside.TipoMetadatosAdicionales;
import es.mpt.dsic.inside.xml.inside.ws.credential.WSCredentialInside;
import es.mpt.dsic.inside.xml.inside.ws.documento.TipoDocumentoEniFileInside;
import es.mpt.dsic.inside.xml.inside.ws.documento.conversion.TipoDocumentoConversionInside;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoExpedienteConversionInside;
import es.mpt.dsic.inside.xml.inside.ws.validacion.documento.TipoDocumentoValidacionInside;
import es.mpt.dsic.inside.xml.inside.ws.validacion.documento.TipoOpcionValidacionDocumento;
import es.mpt.dsic.inside.xml.inside.ws.validacion.documento.TipoOpcionesValidacionDocumentoInside;
import es.mpt.dsic.inside.xml.inside.ws.validacion.documento.resultados.TipoResultadoValidacionDocumentoInside;
import es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.TipoExpedienteValidacionInside;
import es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.TipoOpcionValidacionExpediente;
import es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.TipoOpcionesValidacionExpedienteInside;
import es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.resultados.TipoResultadoValidacionExpedienteInside;
import es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.TipoDocumentoVisualizacionInside;
import es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.TipoResultadoVisualizacionDocumentoInside;
import es.mpt.dsic.puntoUnicoJusticia.client.modelo.AuditoriaEsbType;
import es.mpt.dsic.puntoUnicoJusticia.client.modelo.EnviarAJusticiaResponse;

@Service("insideUtilService")
public class InsideUtilServiceImpl implements InsideUtilService {

	protected static final Log logger = LogFactory.getLog(InsideUtilServiceImpl.class);

	@Value("${temporalData.path}")
	private String basePath;

	@Autowired
	private InSideService service;

	@Autowired
	private InfoFirmaService infofirmaService;

	@Autowired
	private InsideValidationDataService validationService;

	@Autowired
	private TemporalDataBusinessService temporalDataBusinessService;

	@Autowired
	private JAXBMarshaller marshaller;

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	@Qualifier("MetadatosIdentifierLongitudValidation")
	private MetadatoValidatorStringRegex metadatosIdentifierLongitudValidation;

	private static final String NAMESPACE_DOCUMENTO_ELEC = "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e";
	private static final String NAMESPACE_WEBSERVICE = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService";
	private static final String DOCUMENTO = "documento";
	private static final String ID_INITIAL_PART = "ES_";
	private static final String ID_EXP_PART = "_EXP_";
	private static final String ID_COMMON_PART = "_";

	@Override
	public TipoDocumento convertirDocumentoAEni(TipoDocumentoConversionInside documentoConversion, byte[] bytesContenido,
			boolean firmar, WSCredentialInside info) throws Exception {

		logger.debug("Inicio operación convertirDocumentoAEni en servicio GInside");

		logger.debug("Convirtiendo el documentoConversion a ENI");
		ObjetoDocumentoInside documentoInside = fillConvertirDocumentoEni(documentoConversion, null, bytesContenido, firmar,
				info);

		TipoDocumento documentoEni = InsideConverterDocumento.documentoInsideToEni(documentoInside, bytesContenido);

		logger.info("Convertido Documento de entrada a Eni " + documentoInside.getIdentificador());

		logger.debug("Fin operación convertirDocumentoAEni en servicio GInside");

		return documentoEni;

	}

	@Override
	public TipoDocumentoInsideConMAdicionales convertirDocumentoAEniConMAdicionales(
			TipoDocumentoConversionInside documentoConversion, TipoMetadatosAdicionales metadatosAdicionales,
			byte[] bytesContenido, boolean firmar, WSCredentialInside info) throws Exception {

		logger.debug("Inicio operación convertirDocumentoAEniConMAdicionales en servicio GInside");

		logger.debug("Convirtiendo el documentoConversion a ENI");
		ObjetoDocumentoInside documentoInside = fillConvertirDocumentoEni(documentoConversion, metadatosAdicionales,
				bytesContenido, firmar, info);

		TipoDocumentoInsideConMAdicionales documentoConMAdicionales = InsideConverterDocumento
				.documentoInsideToConMAdicionales(documentoInside, bytesContenido);

		logger.info("Convertido Documento de entrada a TipoDocumentoInsideConMAdicionales " + documentoInside.getIdentificador());

		logger.debug("Fin operación convertirDocumentoAEniConMAdicionales en servicio GInside");

		return documentoConMAdicionales;

	}

	@Override
	public TipoDocumentoInsideConMAdicionales convertirDocumentoAEniConMAdicionales(
			TipoDocumentoConversionInside documentoConversion, TipoMetadatosAdicionales metadatosAdicionales,
			byte[] bytesContenido, boolean firmar, WSCredentialInside info, ObjetoDocumentoInside docInside) throws Exception {

		logger.debug("Inicio operación convertirDocumentoAEniConMAdicionales en servicio GInside");
		ObjetoDocumentoInside documentoInside = null;

		if (docInside != null) {
			// Obtenemos el contenido firmado.
			List<FirmaInside> firmaInsideList = docInside.getFirmas();
			byte[] documento = null;
			if (documentoConversion.getContenido() == null && StringUtils.isNotEmpty(documentoConversion.getContenidoId())) {
				documento = IOUtils.toByteArray(new FileInputStream(documentoConversion.getContenidoId()));
			} else {
				documento = documentoConversion.getContenido();
			}

			if (CollectionUtils.isNotEmpty(firmaInsideList)) {
				// Se trata de obtener el contenido a partir de las firmas
				for (FirmaInside firma : firmaInsideList) {
					setDocumentContent(documentoConversion, docInside, firma, documento);
				}

			} else {
				if (bytesContenido != null) {
					documentoConversion.setContenido(bytesContenido);
				}
			}

			// Si ya existe el ObjetoDocumentoInside es porque estamos editando
			// un documento almacenado.
			// En este caso, lo único que hay que actualizar son los metadatos,
			// Las firmas no cambian, porque no estamos permitiendo volver a
			// firmar.
			documentoInside = fillConvertirDocumentoEni(documentoConversion, metadatosAdicionales, docInside);
		} else {
			// Si no existe el ObjetoDocumentoInside se crea y se rellenan sus
			// metadatos.
			// El contenido del documento y las firmas se extraen del objeto
			// documentoConversion.
			documentoInside = fillConvertirDocumentoEni(documentoConversion, metadatosAdicionales, null, firmar, info);
		}

		TipoDocumentoInsideConMAdicionales documentoConMAdicionales = InsideConverterDocumento
				.documentoInsideToConMAdicionales(documentoInside, null);

		logger.info("Convertido Documento de entrada a TipoDocumentoInsideConMAdicionales " + documentoInside.getIdentificador());

		logger.debug("Fin operación convertirDocumentoAEniConMAdicionales en servicio GInside");

		return documentoConMAdicionales;

	}

	public void setDocumentContent(TipoDocumentoConversionInside documentoConversion, ObjetoDocumentoInside docInside,
			FirmaInside firma, byte[] documento) throws InsideConverterException {
		byte[] contenidoFirma = InsideConverterDocumento.obtenerContenidoFirma(firma, documento);
		if (contenidoFirma != null) {
			documentoConversion.setContenido(contenidoFirma);
		}
		if (isFirmadoPreviamente(firma)) {
			documentoConversion.setFirmadoConCertificado(true);
		}
		if (isFirmadoCsv(docInside, firma)) {
			documentoConversion.setContenido(docInside.getContenido().getValorBinario());
		}
	}

	public boolean isFirmadoCsv(ObjetoDocumentoInside docInside, FirmaInside firma) {
		return FirmaInsideTipoFirmaEnum.TF_01.name().equals(firma.getTipoFirma().name()) && docInside != null
				&& docInside.getContenido() != null;
	}

	public boolean isFirmadoPreviamente(FirmaInside firma) {
		return !FirmaInsideTipoFirmaEnum.TF_01.name().equals(firma.getTipoFirma().name());
	}

	private ObjetoDocumentoInside fillConvertirDocumentoEni(TipoDocumentoConversionInside documentoConversion,
			TipoMetadatosAdicionales metadatosAdicionales, byte[] bytesContenido, boolean firmar, WSCredentialInside info)
			throws InSideServiceException {

		logger.debug("Validando documentoConversión en servicio GInside");
		ObjetoDocumentoInside documentoInside = null;
		try {
			if (documentoConversion.getContenido() == null && StringUtils.isNotEmpty(documentoConversion.getContenidoId())) {
				documentoConversion.setContenido(IOUtils.toByteArray(new FileInputStream(documentoConversion.getContenidoId())));
			}

			documentoConversion = validationService.validaTipoDocumentoConversionInside(documentoConversion, true);

			logger.debug("Convirtiendo el documentoConversion a ENI");
			documentoInside = service.documentoConversionToInside(documentoConversion, infofirmaService, bytesContenido, firmar,
					info);

			if (metadatosAdicionales != null) {
				documentoInside.getMetadatos()
						.setMetadatosAdicionales(InsideConverterMetadatos.metadatosAdicionalesXmlToInside(metadatosAdicionales));
			}

			logger.debug("Validando documento Inside a nivel de Inside");
			documentoInside = service.validarDocumento(documentoInside);

		} catch (FileNotFoundException e) {
			throw new InsideServiceInternalException("Error con el elemento: " + documentoConversion != null
					? documentoConversion.toString() : " fillConvertirDocumentoEni ", e);
		} catch (IOException e) {
			throw new InsideServiceInternalException("Error con el elemento: " + documentoConversion != null
					? documentoConversion.toString() : " fillConvertirDocumentoEni ", e);
		} catch (InsideValidationDataException e) {
			throw new InsideServiceInternalException("Error al validar el tipo documento", e);
		} catch (InsideConverterException e) {
			throw new InsideServiceInternalException("Error al convertir el documento a Inside." + e.getMessage(), e);
		}
		return documentoInside;
	}

	private ObjetoDocumentoInside fillConvertirDocumentoEni(TipoDocumentoConversionInside documentoConversion,
			TipoMetadatosAdicionales metadatosAdicionales, ObjetoDocumentoInside docInside) throws InSideServiceException {
		ObjetoDocumentoInside documentoInside = null;
		logger.debug("Validando documentoConversión en servicio GInside");
		try {
			documentoInside = docInside;
			documentoConversion = validationService.validaTipoDocumentoConversionInside(documentoConversion, true);

			InsideConverterDocumento.documentoConversionToInside(documentoConversion, documentoInside);

			if (metadatosAdicionales != null) {
				documentoInside.getMetadatos()
						.setMetadatosAdicionales(InsideConverterMetadatos.metadatosAdicionalesXmlToInside(metadatosAdicionales));
			}

			logger.debug("Validando documento Inside a nivel de Inside");
			documentoInside = service.validarDocumento(documentoInside);
		} catch (InsideValidationDataException e) {
			throw new InsideServiceInternalException("Error al validar el tipo documento", e);
		} catch (InsideConverterException e) {
			throw new InsideServiceInternalException("Error al convertir el documento a Inside", e);
		}
		return documentoInside;
	}

	@Override
	public TipoExpediente convertirExpedienteAEni(TipoExpedienteConversionInside expedienteConversion, String contenidoFirma,
			boolean firmar, WSCredentialInside info) throws Exception {

		logger.debug("Inicio operación convertirExpedienteAEni en servicio GInside");

		logger.debug("Validando expedienteConversion en servicio GInside");
		expedienteConversion = validationService.validaTipoExpedienteConversionInside(expedienteConversion, true);

		logger.debug("Convirtiendo expedienteConversion a Inside");
		ObjetoExpedienteInside expedienteInside = fillConvertirExpedienteEni(expedienteConversion, null, contenidoFirma, firmar,
				info);

		logger.debug("Convirtiendo expediente de Inside a ENI");

		logger.debug("Fin operación convertirExpedienteAEni en servicio GInside");

		return InsideConverterExpediente.expedienteInsideToEni(expedienteInside, null);

	}

	@Override
	public TipoExpedienteInsideConMAdicionales convertirExpedienteAEniConMAdicionales(
			TipoExpedienteConversionInside expedienteConversion, TipoMetadatosAdicionales metadatosAdicionales,
			String contenidoFirma, boolean firmar, WSCredentialInside info) throws Exception {

		logger.debug("Inicio operación convertirExpedienteAEniConMAdicionales en servicio GInside");

		logger.debug("Validando expedienteConversion en servicio GInside");
		expedienteConversion = validationService.validaTipoExpedienteConversionInside(expedienteConversion, true);

		logger.debug("Convirtiendo expedienteConversion a Inside");
		ObjetoExpedienteInside expedienteInside = fillConvertirExpedienteEni(expedienteConversion, metadatosAdicionales,
				contenidoFirma, firmar, info);

		logger.debug("Convertido Expediente Inside a TipoExpedienteInsideConMAdicionales");

		logger.debug("Fin operación convertirExpedienteAEniConMAdicionales en servicio GInside");

		return InsideConverterExpediente.expedienteInsideToConMAdicionales(expedienteInside, null);

	}

	private ObjetoExpedienteInside fillConvertirExpedienteEni(TipoExpedienteConversionInside expedienteConversion,
			TipoMetadatosAdicionales metadatosAdicionales, String contenidoFirma, boolean firmar, WSCredentialInside info)
			throws InSideServiceException, InsideConverterException {
		ObjetoExpedienteInside expedienteInside = InsideConverterExpediente.expedienteConversionToInside(expedienteConversion);
		if (StringUtils.isBlank(contenidoFirma)) {
			if (firmar) {
				service.firmarIndiceExpediente(expedienteInside, info, null);
			}
		} else {
//			service.establecerFirmaExpedienteANodoFirmaBase64(expedienteInside, "FIRMA_0", FirmaInsideTipoFirmaEnum.TF_02,
//					Base64.decodeBase64(contenidoFirma.getBytes()), expedienteConversion.getMetadatosEni().getIdentificador());
			service.establecerFirmaExpedienteANodoFirmaBase64(expedienteInside, "FIRMA_0", FirmaInsideTipoFirmaEnum.TF_03,
					Base64.decodeBase64(contenidoFirma.getBytes()), expedienteConversion.getMetadatosEni().getIdentificador());
		}

		if (metadatosAdicionales != null) {
			expedienteInside.getMetadatos()
					.setMetadatosAdicionales(InsideConverterMetadatos.metadatosAdicionalesXmlToInside(metadatosAdicionales));
		}

		logger.debug("Validando expediente Inside");
		expedienteInside = service.validarExpediente(expedienteInside);

		logger.debug("Obteniendo Visualización del Índice");
		expedienteInside = service.obtenerVisualizacionIndiceSiActivo(expedienteInside,
				expedienteConversion.getOpcionesVisualizacion().isEstamparImagen(),
				expedienteConversion.getOpcionesVisualizacion().isEstamparNombreOrganismo(),
				expedienteConversion.getOpcionesVisualizacion().getFilasNombreOrganismo() != null
						? expedienteConversion.getOpcionesVisualizacion().getFilasNombreOrganismo().getFila() : null,
				expedienteConversion.getOpcionesVisualizacion().isEstamparPie(),
				expedienteConversion.getOpcionesVisualizacion().getTextoPie(), true);

		return expedienteInside;
	}

	@Override
	public ObjetoDocumentoInside validateDocumentImport(byte[] data) throws InSideServiceException {
		TipoOpcionesValidacionDocumentoInside opcionesDoc = new TipoOpcionesValidacionDocumentoInside();
		TipoDocumentoValidacionInside documentoValidacion = new TipoDocumentoValidacionInside();
		TipoDocumentoInsideConMAdicionales tipoDoc;
		TipoResultadoValidacionDocumentoInside validarDoc = null;
		ObjetoDocumentoInside objDoc = null;

		logger.info("Inicio validateDocumentImport");

		opcionesDoc.getOpcionValidacionDocumento().add(TipoOpcionValidacionDocumento.TOVD_01);
		documentoValidacion.setOpcionesValidacionDocumento(opcionesDoc);
		documentoValidacion.setContenido(data);

		try {
			validarDoc = validarDocumentoEniFile(documentoValidacion);
			// CARM ### v2.0.7.1			
			boolean validarActivo=true;
			if (validarDoc.getValidacionDetalle().isEmpty()) {
				logger.warn("validateDocumentImport: El WS de VALIDACION no se encuentra activo");
				validarActivo=false;
			}
			// CARM 2.0.7.1 ###			

			// HAY QUE VALIDAR LA FIRMA DEL DOCUMENTO ANTES DE IMPORTARLO.
			// obtener el indice donde viene la validacion de la firma
			int indiceDondeVieneLaValidacionFirma = 0;
			boolean firmaCSV = false;
			// CARM ### v2.0.7.1	
			if (validarActivo)
			// CARM 2.0.7.1 ###				
			while (!validarDoc.getValidacionDetalle().get(indiceDondeVieneLaValidacionFirma).getTipoValidacion()
					.equals(TipoOpcionValidacionDocumento.fromValue("TOVD03"))) {
				indiceDondeVieneLaValidacionFirma++;

				if (indiceDondeVieneLaValidacionFirma == validarDoc.getValidacionDetalle().size()) {
					firmaCSV = true;
					break;
				}
			}

			// CARM ### v2.0.7.1	
			if (validarActivo)
			// CARM 2.0.7.1 ###				
			if (!firmaCSV && validarDoc != null
					&& !validarDoc.getValidacionDetalle().get(indiceDondeVieneLaValidacionFirma).isResultadoValidacion())
				throw new InsideServiceInternalException(
						validarDoc.getValidacionDetalle().get(indiceDondeVieneLaValidacionFirma).getDetalleValidacion());

			if (// CARM ### v2.0.7.1	
				validarActivo? // CARM 2.0.7.1 ###
				validarDoc != null && validarDoc.getValidacionDetalle().get(0).isResultadoValidacion()
				// CARM ### v2.0.7.1
				:true // CARM 2.0.7.1 ###				
				) {
				tipoDoc = marshaller.unmarshallDataDocumentAditional(data);
				if (tipoDoc.getDocumento() == null) {
					tipoDoc = marshaller.unmarshallDataDocumentoArchive(data);
				}
				objDoc = InsideConverterDocumento.documentoEniAndMetadatosToDocumentoInside(tipoDoc.getDocumento(),
						tipoDoc.getMetadatosAdicionales());
			} else {
				throw new InsideServiceInternalException(validarDoc.getValidacionDetalle().get(0).getDetalleValidacion());
			}
		} catch (InsideServiceInternalException e) {
			String mensaje = messageSource.getMessage(WebConstants.MSG_IMPORTAR_DOC_NO_VALIDO, null, Locale.getDefault());
			logger.warn(mensaje, e);
			if (e.getMessage() != null) {
				mensaje += e.getMessage().indexOf("--") > -1 ? e.getMessage().substring(0, e.getMessage().indexOf("--"))
						: e.getMessage();
			}
			throw new InsideServiceInternalException(mensaje);
		} catch (Exception e) {
			logger.error(e);
			throw new InsideServiceInternalException("Error en la validación del documento.");
		}

		logger.info("Fin validateDocumentImport");

		return objDoc;
	}

	private String recuperarNodoFirma(byte[] expedienteEniBytes) throws InSideServiceException {
		/*
		 * La firma se formatea a base64 si no viene asi, para mantener su
		 * integridad. Cuando se descargue el expedienteEni se vuelve a mostrar
		 * con el nodo ds:signature
		 */
		try {
			String nodofirma;
			String expresionSignature = "//*[local-name()='Signature']";

			try {
				nodofirma = es.mpt.dsic.inside.service.util.XMLUtils.signatureString(expresionSignature, expedienteEniBytes);
			} catch (Exception e) {
				throw new IOException(e.getMessage());
			}

			if ("".equals(nodofirma)) {
				String expresionFirmaBase64 = "//*[local-name()='FirmaBase64']";
				String nodofirmaAUX;

				try {
					nodofirmaAUX = es.mpt.dsic.inside.service.util.XMLUtils.getNodoValue(expresionFirmaBase64,
							expedienteEniBytes);
				} catch (Exception e) {
					throw new IOException(e.getMessage());
				}

				// nos puede llegar varias veces codificado en base64. lo vamos
				// decodificando
				while (Base64.isBase64(nodofirmaAUX)) {
					byte[] uncodificadoB64 = Base64
							.decodeBase64(nodofirmaAUX.getBytes(es.mpt.dsic.inside.service.util.XMLUtils.UTF8_CHARSET));
					nodofirmaAUX = new String(uncodificadoB64);
					if (!Base64.isBase64(nodofirmaAUX)) {
						nodofirma = new String(uncodificadoB64);
						break;
					}
				}

			}

			return nodofirma;

		} catch (Exception e) {
			String mensaje = messageSource.getMessage(WebConstants.MSG_IMPORTAR_EXP_EXP_NO_VALIDO, null, Locale.getDefault());
			logger.warn(mensaje, e);
			throw new InsideServiceInternalException(mensaje + e.getMessage());
		}

	}

	@Override
	public ObjetoExpedienteInside validateExpedientImport(byte[] expedienteEniBytes) throws InSideServiceException {
		TipoExpedienteValidacionInside expedienteValidacion = new TipoExpedienteValidacionInside();
		TipoOpcionesValidacionExpedienteInside opciones = new TipoOpcionesValidacionExpedienteInside();
		TipoExpedienteInsideConMAdicionales expediente;
		TipoResultadoValidacionExpedienteInside validarExp;
		ObjetoExpedienteInside objExp=null;

		logger.info("Inicio validateExpedientImport");

		opciones.getOpcionValidacionExpediente().add(TipoOpcionValidacionExpediente.TOVE_01);
		expedienteValidacion.setOpcionesValidacionExpediente(opciones);
		expedienteValidacion.setContenido(expedienteEniBytes);

		try {

			validarExp = validarExpedienteEniFile(expedienteValidacion, true, false);
			// CARM ### v2.0.7.1			
			boolean validarActivo=true;
			if (validarExp.getValidacionDetalle().isEmpty()) {
				logger.warn("validateExpedientImport: El WS de VALIDACION no se encuentra activo");
				validarActivo=false;
			}
			// CARM 2.0.7.1 ###

			// HAY QUE VALIDAR LA FIRMA DEL EXPEDIENTE ANTES DE IMPORTARLO.
			// Obtener el indice en la lista donde viene la validacion de la
			// firma
			int indiceDondeVieneLaValidacionFirma = -1;

			// CARM ### v2.0.7.1	
			if (validarActivo)
			// CARM 2.0.7.1 ###				
			for (int i = 0; i < validarExp.getValidacionDetalle().size(); i++) {
				if (validarExp.getValidacionDetalle().get(i).getTipoValidacion()
						.equals(TipoOpcionValidacionExpediente.fromValue("TOVE04"))) {
					indiceDondeVieneLaValidacionFirma = i;
				}
			}

			// Si no encontramos indice de la validacion de la firma lanzamos
			// error.
			// CARM ### v2.0.7.1	
			if (validarActivo)
			// CARM 2.0.7.1 ###			
			if (indiceDondeVieneLaValidacionFirma == -1) {
				throw new InsideServiceInternalException("No se encuentra la firma o contiene errores.");
			}

			// CARM ### v2.0.7.1	
			if (validarActivo)
			// CARM 2.0.7.1 ###				
			if (validarExp != null && CollectionUtils.isNotEmpty(validarExp.getValidacionDetalle())
					&& !validarExp.getValidacionDetalle().get(indiceDondeVieneLaValidacionFirma).isResultadoValidacion())
				throw new InsideServiceInternalException(
						validarExp.getValidacionDetalle().get(indiceDondeVieneLaValidacionFirma).getDetalleValidacion());

			// CARM ### v2.0.7.1	
			if (validarActivo)
			// CARM 2.0.7.1 ###
			if (// CARM ### v2.0.7.1	
				validarActivo? // CARM 2.0.7.1 ###
				validarExp != null && CollectionUtils.isNotEmpty(validarExp.getValidacionDetalle())
					&& validarExp.getValidacionDetalle().get(0).isResultadoValidacion()
				// CARM ### v2.0.7.1
				:true // CARM 2.0.7.1 ###
				) {

				expediente = marshaller.unmarshallDataExpedientAditional(expedienteEniBytes);
				if (expediente.getExpediente() == null) {
					expediente = marshaller.unmarshallDataExpedientArchive(expedienteEniBytes);
				}

				/*******************************************************************************************************************************************/
				/*******************************************************************************************************************************************/
				/*******************************************************************************************************************************************/

				// recuperamos el nodo de firma para meterlo en expedienteENI en
				// base64 y no modificar informacion
				String nodofirma = recuperarNodoFirma(expedienteEniBytes);

				if (!"".equals(nodofirma)) {
					Base64 bs64 = new Base64();

					byte[] firma = nodofirma.getBytes(es.mpt.dsic.inside.service.util.XMLUtils.UTF8_CHARSET);
					if (!Base64.isBase64(nodofirma))
						firma = bs64.encode(nodofirma.getBytes(es.mpt.dsic.inside.service.util.XMLUtils.UTF8_CHARSET));

					/*
					 * El expediente solo tiene una firma. Coger index 0 y
					 * vaciamos la signature para meter en base64 y no identar
					 * asi no se pierde la validez de la firma
					 */
					expediente.getExpediente().getIndice().getFirmas().getFirma().get(0).getContenidoFirma()
							.getFirmaConCertificado().setSignature(null);
					expediente.getExpediente().getIndice().getFirmas().getFirma().get(0).getContenidoFirma()
							.getFirmaConCertificado().setFirmaBase64(firma);
				}

				/*******************************************************************************************************************************************/
				/*******************************************************************************************************************************************/
				/*******************************************************************************************************************************************/

				objExp = InsideConverterExpediente.expedienteEniToInside(expediente.getExpediente(),
						expediente.getMetadatosAdicionales());

			} else {
				throw new InsideServiceInternalException(validarExp.getValidacionDetalle().get(0).getDetalleValidacion());
			}

		} catch (Exception e) {
			String mensaje = messageSource.getMessage(WebConstants.MSG_IMPORTAR_EXP_EXP_NO_VALIDO, null, Locale.getDefault());
			logger.warn(mensaje, e);
			throw new InsideServiceInternalException(mensaje + e.getMessage());
		}

		logger.info("Fin validateExpedientImport");

		return objExp;
	}

	@Override
	public TipoResultadoValidacionDocumentoInside validarDocumentoEniFile(TipoDocumentoValidacionInside documentoValidacion)
			throws InSideServiceException {
		try {
			ObjetoDocumentoInside documentoInside = getDocumentoInside(documentoValidacion);

			documentoValidacion = validationService.validaTipoDocumentoValidacionInside(documentoValidacion, true);

			List<ResultadoValidacionDocumento> resultados = service.validaDocumentoEniFile(documentoValidacion.getContenido(),
					InsideConverterValidacion.tipoOpcionesValidacionDocumentoInsideToListOpcionValidacionDocumento(
							documentoValidacion.getOpcionesValidacionDocumento()),
					documentoInside);

			return InsideConverterValidacion.listResultadoValidacionDocumentoToTipoResultadoValidacionDocumentoInside(resultados);
		} catch (Exception e) {
			throw new InsideServiceInternalException("Error en la validación del documento. " + e.getMessage(), e);
		}
	}

	public ObjetoDocumentoInside getDocumentoInside(TipoDocumentoValidacionInside documentoValidacion)
			throws InsideConverterException, IOException {
		ObjetoDocumentoInside documentoInside;
		try {
			documentoInside = InsideConverterDocumento.tipoDocumentoValidacionToInside(documentoValidacion);
		} catch (InsideConverterException e) {
			logger.debug("Tipo de validacion por Mtom", e);
			documentoInside = InsideConverterDocumento.tipoDocumentoValidacionToInsideToMtom(documentoValidacion);
		}
		return documentoInside;
	}

	@Override
	public String getStringExpedienteMarshall(Object expediente) throws InSideServiceException {
		String data = "";
		TipoExpedienteInsideConMAdicionales tipoExpedienteMA = new TipoExpedienteInsideConMAdicionales();
		try {
			if (expediente instanceof TipoExpedienteInsideConMAdicionales) {
				tipoExpedienteMA = (TipoExpedienteInsideConMAdicionales) expediente;
			} else if (expediente instanceof ObjetoExpedienteInside) {
				tipoExpedienteMA.setExpediente(
						InsideConverterExpediente.expedienteInsideToEni((ObjetoExpedienteInside) expediente, null));
				tipoExpedienteMA.setMetadatosAdicionales(InsideConverterMetadatos.metadatosAdicionalesInsideToXml(
						((ObjetoExpedienteInside) expediente).getMetadatos().getMetadatosAdicionales()));
			}

			if (tipoExpedienteMA.getMetadatosAdicionales() != null
					&& CollectionUtils.isNotEmpty(tipoExpedienteMA.getMetadatosAdicionales().getMetadatoAdicional())) {
				for (MetadatoAdicional adicional : tipoExpedienteMA.getMetadatosAdicionales().getMetadatoAdicional()) {
					adicional.setTipo("string");
				}
				data = marshaller.marshallDataExpedient(tipoExpedienteMA);
			} else {
				data = marshaller.marshallDataExpedient(tipoExpedienteMA.getExpediente());
			}
		} catch (JAXBException e) {
			throw new InsideServiceInternalException(e.getMessage());
		} catch (InsideConverterException e) {
			throw new InsideServiceInternalException(e.getMessage());
		}
		return data;
	}

	@Override
	public TipoResultadoValidacionExpedienteInside validarExpedienteEniFile(TipoExpedienteValidacionInside expedienteValidacion,
			boolean isOnline) throws InSideServiceException {
		try {
			logger.debug("Inicio operación validarExpedienteEniFile en servicio GInside");
			expedienteValidacion = validationService.validaTipoExpedienteValidacionInside(expedienteValidacion, true);

			logger.debug("Convirtiendo el expedienteValidacion a ENI");
			ObjetoExpedienteInside expedienteInside = InsideConverterExpediente
					.tipoExpedienteValidacionToInside(expedienteValidacion);

			List<ResultadoValidacionExpediente> resultados;
			if (isOnline) {
				resultados = service.validaExpedienteEniFile(expedienteValidacion.getContenido(),
						InsideConverterValidacion.tipoOpcionesValidacionExpedienteInsideToListOpcionValidacionExpediente(
								expedienteValidacion.getOpcionesValidacionExpediente()),
						expedienteInside, false);
			} else {
				resultados = service.validaExpedienteEniFile(
						XMLUtils.expedienteAdicionalWsToEni(expedienteValidacion.getContenido()).getBytes(),
						InsideConverterValidacion.tipoOpcionesValidacionExpedienteInsideToListOpcionValidacionExpediente(
								expedienteValidacion.getOpcionesValidacionExpediente()),
						expedienteInside, false);
			}

			logger.debug("Fin operación validarExpedienteEniFile en servicio GInside");

			return InsideConverterValidacion
					.listResultadoValidacionExpedienteToTipoResultadoValidacionExpedienteInside(resultados);
		} catch (Exception e) {
			throw new InsideServiceInternalException("Error en la validación del expediente. ", e);
		}
	}

	@Override
	public TipoResultadoValidacionExpedienteInside validarExpedienteEniFile(TipoExpedienteValidacionInside expedienteValidacion,
			boolean isOnline, boolean validarSIA) throws InSideServiceException {
		try {
			logger.debug("Inicio operación validarExpedienteEniFile en servicio GInside");
			expedienteValidacion = validationService.validaTipoExpedienteValidacionInside(expedienteValidacion, true);

			logger.debug("Convirtiendo el expedienteValidacion a ENI");
			ObjetoExpedienteInside expedienteInside = InsideConverterExpediente
					.tipoExpedienteValidacionToInside(expedienteValidacion);

			List<ResultadoValidacionExpediente> resultados;
			if (isOnline) {
				resultados = service.validaExpedienteEniFile(expedienteValidacion.getContenido(),
						InsideConverterValidacion.tipoOpcionesValidacionExpedienteInsideToListOpcionValidacionExpediente(
								expedienteValidacion.getOpcionesValidacionExpediente()),
						expedienteInside, validarSIA);
			} else {
				resultados = service.validaExpedienteEniFile(
						XMLUtils.expedienteAdicionalWsToEni(expedienteValidacion.getContenido()).getBytes(),
						InsideConverterValidacion.tipoOpcionesValidacionExpedienteInsideToListOpcionValidacionExpediente(
								expedienteValidacion.getOpcionesValidacionExpediente()),
						expedienteInside, validarSIA);
			}

			logger.debug("Fin operación validarExpedienteEniFile en servicio GInside");

			return InsideConverterValidacion
					.listResultadoValidacionExpedienteToTipoResultadoValidacionExpedienteInside(resultados);
		} catch (Exception e) {
			throw new InsideServiceInternalException("Error en la validación del expediente. ", e);
		}
	}

	@Override
	public TipoResultadoVisualizacionDocumentoInside visualizarDocumentoEni(
			TipoDocumentoVisualizacionInside documentoVisualizacion) throws Exception {

		TipoResultadoVisualizacionDocumentoInside resultado = new TipoResultadoVisualizacionDocumentoInside();

		logger.debug("Inicio operación visualizarDocumentoEni en servicio GInside");

		logger.debug("Validando documentoVisualizacion");
		documentoVisualizacion = validationService.validaTipoDocumentoVisualizacionInside(documentoVisualizacion, true);

		logger.debug("Convirtiendo a documento Inside");
		ObjetoDocumentoInside documentoInside = InsideConverterDocumento.documentoVisualizacionToInside(documentoVisualizacion);

		ResultadoVisualizacionDocumento vis = service.visualizaDocumentoInside(documentoInside,
				documentoVisualizacion.getOpcionesVisualizacionDocumento().isEstamparImagen(),
				documentoVisualizacion.getOpcionesVisualizacionDocumento().isEstamparNombreOrganismo(),
				documentoVisualizacion.getOpcionesVisualizacionDocumento().getFilasNombreOrganismo() != null
						? documentoVisualizacion.getOpcionesVisualizacionDocumento().getFilasNombreOrganismo().getFila() : null,
				documentoVisualizacion.getOpcionesVisualizacionDocumento().isEstamparPie(),
				documentoVisualizacion.getOpcionesVisualizacionDocumento().getTextoPie(), null);

		resultado.setContenido(vis.getVisualizacion());
		resultado.setMime(vis.getMime());

		logger.debug("Fin operación visualizarDocumentoEni en servicio GInside");

		return resultado;

	}

	@Override
	public Map<String, Object> crearNuevoExpediente(ObjetoExpedienteInside objExp, List<ObjetoDocumentoInside> documentos,
			String idUsuario, boolean online) throws InSideServiceException {
		logger.debug("Inicio operación createExpedientAndDocuments en servicio GInside");

		Map<String, Object> result = new HashMap<String, Object>();
		comprobarIdExpediente(objExp);
		if (!comprobarContenidoDocInsideIgualContenidoDocIndiceExp(documentos, result, idUsuario, online)) {
			crearNuevosDocumentosEnExpediente(documentos, idUsuario, online);
			result.put("expediente", service.altaExpediente(objExp, false, idUsuario, online));
		}

		logger.debug("Fin operación createExpedientAndDocuments en servicio GInside");
		return result;
	}

	@Override
	public Map<String, Object> actualizarExpediente(ObjetoExpedienteInside objExp, List<ObjetoDocumentoInside> documentos,
			String idUsuario, boolean online) throws InSideServiceException {
		logger.debug("Inicio operación updateExpedientAndDocuments en servicio GInside");

		Map<String, Object> result = new HashMap<String, Object>();
		if (!comprobarContenidoDocInsideIgualContenidoDocIndiceExp(documentos, result, idUsuario, online)) {
			crearNuevosDocumentosEnExpediente(documentos, idUsuario, online);
			result.put("expediente", service.modificaExpedienteInside(objExp, false, idUsuario, online));
		}

		logger.debug("Inicio operación updateExpedientAndDocuments en servicio GInside");
		return result;
	}

	public void crearNuevosDocumentosEnExpediente(List<ObjetoDocumentoInside> documentos, String idUsuario, boolean online)
			throws InSideServiceException {
		for (ObjetoDocumentoInside doc : documentos) {
			service.altaDocumento(doc, idUsuario, online);
		}
	}

	private boolean comprobarContenidoDocInsideIgualContenidoDocIndiceExp(List<ObjetoDocumentoInside> documentos,
			Map<String, Object> result, String idUsuario, boolean online) throws InSideServiceException {
		List<String> docsErrorContenidoDifInside = new ArrayList<String>();
		boolean errorContenidoDistinto = false;
		try {
			Iterator<ObjetoDocumentoInside> iter = documentos.listIterator();
			while (iter.hasNext()) {
				comprobarHashDocAlmacenadosInside(docsErrorContenidoDifInside, iter, idUsuario, online);
			}
		} catch (InsideConverterException e) {
			throw new InsideServiceInternalException(
					"Error al convertir los documentos en comprobarContenidoDocInsideIgualContenidoDocIndiceExp", e);
		}
		if (!docsErrorContenidoDifInside.isEmpty()) {
			result.put("docsNoCreate", docsErrorContenidoDifInside);
			errorContenidoDistinto = true;
		}
		return errorContenidoDistinto;
	}

	public void comprobarHashDocAlmacenadosInside(List<String> docsErrorContenidoDifInside, Iterator<ObjetoDocumentoInside> iter,
			String idUsuario, boolean online) throws InsideConverterException {
		try {
			ObjetoDocumentoInside documentoIndiceExp = iter.next();
			ObjetoDocumentoInside documentoInside = service.getDocumento(documentoIndiceExp.getIdentificador());
			String valorHuellaDocEnInside = DigestUtils.md5DigestAsHex(
					generateDocXml(InsideConverterDocumento.documentoInsideToConMAdicionales(documentoInside, null)));
			String valorHuellaDocContenidoEnInside = getValorHuellaContenido(documentoInside);
			String valorHuellaDocEnExpediente = getValorHuellaContenido(documentoIndiceExp);
			if (valorHuellaDocEnInside.equals(valorHuellaDocEnExpediente)
					|| valorHuellaDocContenidoEnInside.equals(valorHuellaDocEnExpediente)) {
				service.actualizarAsociacionDocumentoUnidad(documentoInside, idUsuario, online);
				iter.remove();
			} else {
				docsErrorContenidoDifInside.add(documentoIndiceExp.getIdentificador());
			}
		} catch (InSideServiceException e) {
			logger.debug("Documento candidato a ser dado de alta en Inside", e);
		}
	}

//	public String getValorHuellaContenido(ObjetoDocumentoInside documentoInside) {
//		byte[] documentoIndiceExpContenido = (documentoInside.getContenido().getValorBinario() != null)
//				? documentoInside.getContenido().getValorBinario() : documentoInside.getContenido().getReferencia().getBytes();
//		return DigestUtils.md5DigestAsHex(documentoIndiceExpContenido);
//	}
	
	public String getValorHuellaContenido(ObjetoDocumentoInside documentoInside) {
		byte[] documentoIndiceExpContenido = null;
		
		//Si tiene valorbinario
		if(documentoInside.getContenido().getValorBinario() != null)
		{
			documentoIndiceExpContenido =  documentoInside.getContenido().getValorBinario();
		}
		else
		{//si devuelve la referencia. Hay que ir a la referencia a buscar el contenido para hacer el hash, no hacer el hash de la cadena de la referencia
			
			ContenidoFirmaCertificadoContenidoBinarioInside firmaBase64 = (ContenidoFirmaCertificadoContenidoBinarioInside)documentoInside.getFirmas().get(0).getContenidoFirma();
			documentoIndiceExpContenido = Base64.encodeBase64(firmaBase64.getValorBinario());
		}
		
				
		return DigestUtils.md5DigestAsHex(documentoIndiceExpContenido);
	}

	private void comprobarIdExpediente(ObjetoExpedienteInside objExp) throws InSideServiceException {
		try {
			service.getExpediente(objExp.getIdentificador(), false);
			throw new InsideServiceInternalException(
					messageSource.getMessage(WebConstants.MSG_IMPORTAR_EXP_IMPORTAR_EXP_EXISTE, null, Locale.getDefault()) + " "
							+ objExp.getIdentificador());
		} catch (InsideStoreObjectNotFoundException e) {
			logger.debug("No existe en BBDD un expediente con el identificador" + objExp.getIdentificador(), e);
		}
	}

	@Override
	public List<ObjetoDocumentoInside> recoverDocumentXml(List<TipoDocumentoEniFileInside> list) throws InSideServiceException {
		List<ObjetoDocumentoInside> documentos = new ArrayList<ObjetoDocumentoInside>();
		if (CollectionUtils.isNotEmpty(list)) {
			for (TipoDocumentoEniFileInside fichero : list) {
				ObjetoDocumentoInside doc = validateDocumentImport(fichero.getDocumentoEniBytes());

				if (doc != null)
					documentos.add(doc);
			}
		}
		return documentos;
	}

	@Override
	public boolean checkNoExistIdDocumentInside(String identificador, boolean throwException) throws InSideServiceException {
		try {
			if (!esLongitudIdentificadorValido(identificador)) {
				throw new InsideWSException(InsideWsErrors.IDENTIFICADOR_DOCUMENTO_LONGITUD, null, "");
			}
			
			service.getDocumento(identificador);
			if (throwException) {
				throw new InsideServiceInternalException(
						messageSource.getMessage(WebConstants.MSG_IMPORTAR_DOC_IMPORTAR_DOC_EXISTE, null, Locale.getDefault())
								+ " " + identificador);
			} else {
				return false;
			}
		} catch (InsideStoreObjectNotFoundException e) {
			logger.debug("Elemento no existe en BBDD" + identificador, e);
		} catch (InsideStoreObjectNoActiveException e) {
			logger.debug("Elemento no existe en BBDD" + identificador, e);
		} catch (InsideWSException e) {
			throw new InsideServiceInternalException(
					messageSource.getMessage(WebConstants.MSG_IMPORTAR_DOC_ID_NO_VALIDO, null, Locale.getDefault())
							+ " " + identificador);
		} 
		return true;
	}
	
	public boolean esLongitudIdentificadorValido(String identificador) throws InsideWSException {
		try {
			metadatosIdentifierLongitudValidation.clean(identificador);
			return true;
		} catch (InSideServiceValidationException e) {
			return false;
		} 		
	}

	@Override
	public Map<String, Object> checkToken(String nifConsultaPuestaDisposicion, String identificador, String csv, String uuid) throws InSideServiceException {
		Map<String, Object> result = new HashMap<String, Object>();
		ObjetoExpedienteToken token = new ObjetoExpedienteToken(null, identificador, csv, uuid);
		List<ObjetoInsideUnidad> listaDir3Usuario = null;
		String errorToken = "errorToken";
		token = service.getTokenByData(token);
		if (token == null) {
			result.put(errorToken, WebConstants.MSG_EXP_PUESTADISPOSICION_NO_VALIDO);
		} else {
			boolean tokenCaducado = false;
			boolean tokenInvalidoNif = false;
			
			if (StringUtils.isNotEmpty(token.getDir3())) {
				try {
					listaDir3Usuario = service.getUnidadesUsuario(nifConsultaPuestaDisposicion, true);
				} catch (Exception e) {
					result.put(errorToken, WebConstants.MSG_EXP_PUESTADISPOSICION_NO_VALIDO_USUARIO_NO_ALTA);
					tokenInvalidoNif = true;
				}
				
			}

			// ver si el token esta caducado
			if (token.getFechaCaducidad() != null && !InsideWSUtils.todayBeforDate(new Date(), token.getFechaCaducidad())) {
				result.put(errorToken, WebConstants.MSG_EXP_PUESTADISPOSICION_NO_VALIDO_FECHACADUCIDAD);
				tokenCaducado = true;
			}

			if (token.getNifs() != null && StringUtils.isNotBlank(token.getNifs()) && !tokenCaducado
					&& !InsideWSUtils.containsString1ToString2(token.getNifs(), nifConsultaPuestaDisposicion)) {
				result.put(errorToken, WebConstants.MSG_EXP_PUESTADISPOSICION_NO_VALIDO_NIFNOPERMITIDO);
				tokenInvalidoNif = true;
			}

			if (StringUtils.isNotBlank(token.getDir3()) && listaDir3Usuario != null && !listaDir3Usuario.isEmpty()
					&& !compruebaUnidadesUsuarioToken(listaDir3Usuario, token.getDir3().trim())) {
				result.put(errorToken, WebConstants.MSG_EXP_PUESTADISPOSICION_NO_VALIDO_DIR3NOPERMITIDO);
				tokenInvalidoNif = true;
			}
			result.put("tokenCaducado", tokenCaducado);
			result.put("tokenInvalidoNif", tokenInvalidoNif);
			result.put("token", token);
		}
		return result;
	}

	private boolean compruebaUnidadesUsuarioToken(List<ObjetoInsideUnidad> unidadesUsuario, String dir3Buscado) {

		for (ObjetoInsideUnidad objetoInsideUnidad : unidadesUsuario) {
			if (objetoInsideUnidad.getCodigo().equals(dir3Buscado)) {
				return true;
			}
		}
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Recupera un documento según los parámetros. Si no se encuentra en la
	 * carpeta temporal se busca en base de datos. Tiene en cuenta si el
	 * documento incluye metadatos adicionales.
	 * 
	 * @param idSession
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public byte[] lazyLoadXmlFile(String idSession, String id, boolean addXmlExtension) throws Exception {

		byte[] result = null;

		try {
			String fileName = addXmlExtension ? id + WebConstants.FORMAT_XML : id;
			result = temporalDataBusinessService.getFile(idSession, fileName);
		} catch (FileNotFoundException e) {
			ObjetoDocumentoInside objetoDocumentoInside = service.getDocumento(id);
			TipoDocumentoInsideConMAdicionales tDocMetadatosAdicionales = InsideConverterDocumento
					.documentoInsideToConMAdicionales(objetoDocumentoInside, null);
			String docMarshall;
			if (tDocMetadatosAdicionales.getMetadatosAdicionales() != null
					&& CollectionUtils.isNotEmpty(tDocMetadatosAdicionales.getMetadatosAdicionales().getMetadatoAdicional())) {
				docMarshall = marshaller.marshallDataDocument(tDocMetadatosAdicionales,
						"https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", DOCUMENTO, "enidoc");
			} else {
				docMarshall = marshaller.marshallDataDocument(tDocMetadatosAdicionales.getDocumento(), NAMESPACE_DOCUMENTO_ELEC,
						DOCUMENTO, "enidoc");
			}

			result = docMarshall.getBytes();
		}

		return result;
	}

	@Override
	public Map<String, byte[]> unzip(String carpeta, String fichero) throws IOException {

		Map<String, byte[]> respuesta = new HashMap<String, byte[]>();

		ZipInputStream zipIn = new ZipInputStream(new FileInputStream(basePath + carpeta + File.separator + fichero));
		ZipEntry entry = zipIn.getNextEntry();

		while (entry != null) {
			String filePath = basePath + carpeta + File.separator + entry.getName();
			if (!entry.isDirectory()) {
				extractFile(zipIn, filePath + "unzipped");
			}
			zipIn.closeEntry();

			byte[] ficheroBytes = this.temporalDataBusinessService.getFile(carpeta, entry.getName() + "unzipped");
			respuesta.put(entry.getName(), ficheroBytes);
			entry = zipIn.getNextEntry();

		}
		zipIn.close();
		return respuesta;

	}

	private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
		byte[] bytesIn = new byte[1024];
		int read;
		while ((read = zipIn.read(bytesIn)) != -1) {
			bos.write(bytesIn, 0, read);
		}
		bos.close();
	}

	/*
	 * Devuelve un mapa con un mapa de expediente y otro mapa de los documentos
	 */
	@Override
	public Map<String, Map<String, String>> getMapaExpedienteYDocumentos(String identificadorExpediente, String versionExpediente)
			throws InSideServiceException, InsideConverterException, JAXBException, XPathExpressionException,
			ParserConfigurationException, SAXException, IOException, TransformerException {

		Map<String, Map<String, String>> mapaExpYDocs = new HashMap<String, Map<String, String>>();

		// id de docuemnto y eni de documento
		Map<String, String> docsParaMJU = new HashMap<String, String>();

		// id expediente y eni de expediente
		Map<String, String> expParaMJU = new HashMap<String, String>();

		ObjetoExpedienteInside exp;
		if (versionExpediente != null && !"".equals(versionExpediente))
			exp = service.getExpediente(identificadorExpediente, Integer.parseInt(versionExpediente), false);
		else
			exp = service.getExpediente(identificadorExpediente, false);

		// Se instancia el expedienteEni con valores iniciales
		TipoExpedienteInsideConMAdicionales tipoExpedienteMA = new TipoExpedienteInsideConMAdicionales();
		tipoExpedienteMA.setExpediente(InsideConverterExpediente.expedienteInsideToEni(exp, null));
		tipoExpedienteMA.setMetadatosAdicionales(
				InsideConverterMetadatos.metadatosAdicionalesInsideToXml(exp.getMetadatos().getMetadatosAdicionales()));

		String dataExpedienteMJU = marshaller.marshallDataExpedient(tipoExpedienteMA.getExpediente());
		expParaMJU.put(exp.getIdentificador(), dataExpedienteMJU);

		mapaExpYDocs.put("MAPAEXPEDIENTE", expParaMJU);

		Map<String, String> docsExp = service.obtenerDocsExpTipo(tipoExpedienteMA.getExpediente().getIndice().getIndiceContenido()
				.getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada(), "/");

		if (CollectionUtils.isNotEmpty(docsExp.entrySet())) {
			for (String idDoc : docsExp.keySet()) {
				ObjetoDocumentoInside doc = service.getDocumento(idDoc);
				TipoDocumento tDoc = InsideConverterDocumento.documentoInsideToEni(doc, null);
				String docMarshall = marshaller.marshallDataDocument(tDoc, NAMESPACE_DOCUMENTO_ELEC, DOCUMENTO, "enidoc");
				docsParaMJU.put(idDoc, docMarshall);

			}
			mapaExpYDocs.put("MAPADOCUMENTOS", docsParaMJU);
		}

		return mapaExpYDocs;

	}

	/**
	 * Devuelve el código de envío general. Si el envío es correcto contiene el
	 * propio códico de envío. Si el envío es incorrecto contiene el código de
	 * error@descripción del error.
	 */
	@Override
	public String guardarResultadoEnvioJusticia(EnviarAJusticiaResponse enviarAJusticiaResponse, String identificadorExpediente,
			String versionExpediente, MessageObject mensaje, String codigoUnidadOrganoRemitente) throws InSideServiceException {
		// ESTE CAMBIO NECESITA VERSION NUEVA DE PUNTOUNICOJUSTICIA Y SUBIRLA A
		// HOMOVIVUS
		// abrir proyecto puntounicojusticia y hacer package
		// codigoEnvioGeneral si ha habido error viene con un codigo inicial ,
		// una arroba y el mensaje de error
		// si no hay error viene un codigo de envio valido solamente
		String codigoEnvioGeneral = enviarAJusticiaResponse.getResultadoEnvioToJusticia().getCodigoEnvio();
		String codigoEnvio;
		String msg;
		if (!enviarAJusticiaResponse.getResultadoEnvioToJusticia().isACK()) {
			logger.error("JusticiaController.remitirExpediente --> Error al remitir expediente a justicia ACK falso. "
					+ codigoEnvioGeneral);
			String[] partesDelMensajeError = codigoEnvioGeneral.split("@");
			codigoEnvio = partesDelMensajeError[0];
			if (partesDelMensajeError.length > 1)
				msg = "ERROR: " + partesDelMensajeError[1];
			else
				msg = "ERROR en el envío a justicia. JusticiaController.remitirExpediente";
			mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
					"Error en envío. Código interno inside de envío erroneo:  Contacte con el administrador.");
		} else {
			msg = "Envío realizado correctamente. Código de envío: " + codigoEnvioGeneral;
			mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_SUCCESS, msg);
			codigoEnvio = codigoEnvioGeneral;
		}

		// Guardar la respuesta del envio asociada al expediente. Se recupera el
		// objeto expediente y se le actualiza los ResultadosEnvioJusticia
		AuditoriaEsbType auditoriaESB = enviarAJusticiaResponse.getResultadoEnvioToJusticia().getAuditoriaEsb();
		if (auditoriaESB == null) {
			auditoriaESB = new AuditoriaEsbType();
			auditoriaESB.setAplicacion("ERROR");
			auditoriaESB.setModulo("ERROR");
			auditoriaESB.setServicio("ERROR");
			SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			auditoriaESB.setMarcaTiempo(dt.format(new Date()));
		}
		ObjectInsideRespuestaEnvioJusticia objectInsideRespuestaEnvioJusticia = new ObjectInsideRespuestaEnvioJusticia(
				auditoriaESB.getAplicacion(), auditoriaESB.getModulo(), auditoriaESB.getServicio(), auditoriaESB.getMarcaTiempo(),
				String.valueOf(enviarAJusticiaResponse.getResultadoEnvioToJusticia().isACK()), codigoEnvio, msg);

		objectInsideRespuestaEnvioJusticia.setCodigoUnidadOrganoRemitente(codigoUnidadOrganoRemitente);

		service.guardarRespuestaRemisionJusticiaExpediente(objectInsideRespuestaEnvioJusticia, identificadorExpediente,
				versionExpediente);

		return codigoEnvioGeneral;
	}

	@Override
	public String guardarResultadoEnvioJusticiaExpedienteNoInside(EnviarAJusticiaResponse enviarAJusticiaResponse,
			String identificadorExpediente, String versionExpediente, MessageObject mensaje, String codigoUnidadOrganoRemitente)
			throws InSideServiceException {
		// ESTE CAMBIO NECESITA VERSION NUEVA DE PUNTOUNICOJUSTICIA Y SUBIRLA A
		// HOMOVIVUS
		// abrir proyecto puntounicojusticia y hacer package
		// codigoEnvioGeneral si ha habido error viene con un codigo inicial ,
		// una arroba y el mensaje de error
		// si no hay error viene un codigo de envio valido solamente
		String codigoEnvioGeneral = enviarAJusticiaResponse.getResultadoEnvioToJusticia().getCodigoEnvio();
		String codigoEnvio;
		String msg;
		if (!enviarAJusticiaResponse.getResultadoEnvioToJusticia().isACK()) {
			logger.error("JusticiaController.remitirExpediente --> Error al remitir expediente a justicia ACK falso. "
					+ codigoEnvioGeneral);
			String[] partesDelMensajeError = codigoEnvioGeneral.split("@");
			codigoEnvio = partesDelMensajeError[0];
			if (partesDelMensajeError.length > 1)
				msg = "ERROR: " + partesDelMensajeError[1];
			else
				msg = "ERROR en el envío a justicia. JusticiaController.remitirExpediente";
			mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR,
					"Error en envío. Código interno inside de envío erroneo:  Contacte con el administrador.");
		} else {
			msg = "Envío realizado correctamente. Código de envío: " + codigoEnvioGeneral;
			mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_SUCCESS, msg);
			codigoEnvio = codigoEnvioGeneral;
		}

		// Guardar la respuesta del envio asociada al expediente. Se recupera el
		// objeto expediente y se le actualiza los ResultadosEnvioJusticia
		AuditoriaEsbType auditoriaESB = enviarAJusticiaResponse.getResultadoEnvioToJusticia().getAuditoriaEsb();
		if (auditoriaESB == null) {
			auditoriaESB = new AuditoriaEsbType();
			auditoriaESB.setAplicacion("ERROR");
			auditoriaESB.setModulo("ERROR");
			auditoriaESB.setServicio("ERROR");
			SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			auditoriaESB.setMarcaTiempo(dt.format(new Date()));
		}
		ObjectInsideRespuestaEnvioJusticia objectInsideRespuestaEnvioJusticia = new ObjectInsideRespuestaEnvioJusticia(
				auditoriaESB.getAplicacion(), auditoriaESB.getModulo(), auditoriaESB.getServicio(), auditoriaESB.getMarcaTiempo(),
				String.valueOf(enviarAJusticiaResponse.getResultadoEnvioToJusticia().isACK()), codigoEnvio, msg);

		objectInsideRespuestaEnvioJusticia.setCodigoUnidadOrganoRemitente(codigoUnidadOrganoRemitente);

		service.guardarRespuestaRemisionJusticiaExpedienteNoInside(objectInsideRespuestaEnvioJusticia, identificadorExpediente,
				versionExpediente);

		return codigoEnvioGeneral;
	}

	@Override
	public byte[] generateDocXml(TipoDocumentoInsideConMAdicionales docInside) throws InSideServiceException {
		String xml;

		try {
			if (docInside.getMetadatosAdicionales() != null
					&& CollectionUtils.isNotEmpty(docInside.getMetadatosAdicionales().getMetadatoAdicional())) {
				xml = marshaller.marshallDataDocument(docInside, NAMESPACE_WEBSERVICE, DOCUMENTO, "insidews");
			} else {
				xml = marshaller.marshallDataDocument(docInside.getDocumento(), NAMESPACE_DOCUMENTO_ELEC, DOCUMENTO, "enidoc");
			}
		} catch (Exception e3) {
			logger.error(messageSource.getMessage(WebConstants.MSG_GENERAR_DOC_ERROR_XML, null, null), e3);
			throw new InsideServiceInternalException(
					messageSource.getMessage(WebConstants.MSG_GENERAR_DOC_ERROR_XML, null, null));
		}
		return xml.getBytes();
	}

	@Override
	public byte[] generateExpXml(TipoExpedienteInsideConMAdicionales expInside) throws InSideServiceException {
		String xml;

		try {
			if (expInside.getMetadatosAdicionales() != null
					&& CollectionUtils.isNotEmpty(expInside.getMetadatosAdicionales().getMetadatoAdicional())) {
				for (MetadatoAdicional adicional : expInside.getMetadatosAdicionales().getMetadatoAdicional()) {
					adicional.setTipo("string");
				}
			}
			xml = marshaller.marshallDataExpedient(expInside);
		} catch (Exception e3) {
			logger.error(messageSource.getMessage(WebConstants.MSG_GENERAR_DOC_ERROR_XML, null, null), e3);
			throw new InsideServiceInternalException(
					messageSource.getMessage(WebConstants.MSG_GENERAR_DOC_ERROR_XML, null, null));
		}
		return xml.getBytes();
	}

	@Override
	public byte[] generateExpXmlParaValidar(TipoExpediente expInside) throws InSideServiceException {
		String xml;

		try {

			xml = marshaller.marshallDataExpedient(expInside);
		} catch (Exception e3) {
			logger.error(messageSource.getMessage(WebConstants.MSG_GENERAR_DOC_ERROR_XML, null, null), e3);
			throw new InsideServiceInternalException(
					messageSource.getMessage(WebConstants.MSG_GENERAR_DOC_ERROR_XML, null, null));
		}
		return xml.getBytes();
	}

	@Override
	public ResultadoValidacion comprobarDocumentosIndiceExpediente(ObjetoExpedienteInside expediente,
			List<ObjetoDocumentoInside> listaDocumentos) throws InSideServiceException {
		try {
			ResultadoValidacion resultado = new ResultadoValidacion();
			resultado.setValido(true);
			comprobarDocumentosExistenEnInside(resultado, listaDocumentos, expediente);
			if (resultado.isValido()) {
				comprobarCoincidenHashDocumento(resultado, listaDocumentos, expediente);
			}
			return resultado;
		} catch (InsideConverterException e) {
			logger.debug(e);
			throw new InsideServiceInternalException("Error al convertir el documento para calcular hash" + e.getMessage());
		}
	}

	private void comprobarCoincidenHashDocumento(ResultadoValidacion resultado, List<ObjetoDocumentoInside> listaDocumentos,
			ObjetoExpedienteInside expediente) throws InSideServiceException, InsideConverterException {
		for (ObjetoDocumentoInside documento : listaDocumentos) {
			ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado contenidoDoc = new ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado();
			service.getDocumentoEnIndiceExpediente(expediente.getIndice().getIndiceContenido().getElementosIndizados(),
					documento.getIdentificador(), contenidoDoc);
			String valorHuellaExpediente = contenidoDoc.getValorHuella();
			String valorHuellaInside = DigestUtils
					.md5DigestAsHex(generateDocXml(InsideConverterDocumento.documentoInsideToConMAdicionales(documento, null)));
			String valorHuellaDocContenidoEnInside = getValorHuellaContenido(documento);
			if (!valorHuellaInside.equals(valorHuellaExpediente)
					&& !valorHuellaDocContenidoEnInside.equals(valorHuellaExpediente)) {
				resultado.setValido(false);
				generarMensajeErrorNoCoincideHash(resultado, documento.getIdentificador());
			}
		}
	}

	public void generarMensajeErrorNoCoincideHash(ResultadoValidacion resultado, String idDocumento) {
		if (StringUtils.isEmpty(resultado.getMensaje())) {
			resultado.setMensaje(
					messageSource.getMessage(WebConstants.MSG_IMPORTAR_DOCUMENTO_DISTINTO_HASH, null, null) + " " + idDocumento);
		} else {
			resultado.setMensaje(resultado.getMensaje() + " " + idDocumento);
		}
	}

	private void comprobarDocumentosExistenEnInside(ResultadoValidacion resultado, List<ObjetoDocumentoInside> listaDocumentos,
			ObjetoExpedienteInside expediente) {
		Map<String, String> documentos = service
				.obtenerDocsExpInside(expediente.getIndice().getIndiceContenido().getElementosIndizados(), "/");
		for (Map.Entry<String, String> entry : documentos.entrySet()) {
			ObjetoDocumentoInside documento;
			try {
				documento = service.getDocumento(entry.getKey());
				listaDocumentos.add(documento);
			} catch (InSideServiceException e) {
				logger.debug("Documento no encontrado en Inside" + e);
				resultado.setValido(false);
				generarMensajeErrorNoExisteDoc(resultado, entry.getKey());
			}
		}
	}

	private void generarMensajeErrorNoExisteDoc(ResultadoValidacion resultado, String idDocumento) {
		if (StringUtils.isEmpty(resultado.getMensaje())) {
			resultado.setMensaje(
					messageSource.getMessage(WebConstants.MSG_IMPORTAR_DOCUMENTO_NO_EXISTE, null, null) + " " + idDocumento);
		} else {
			resultado.setMensaje(resultado.getMensaje() + " " + idDocumento);
		}
	}

	@Override
	public String generateDefaultId(String unidadOrganica, boolean isDocument) {
		String idDefault = "";
		try {
			StringBuilder idCompose = new StringBuilder(ID_INITIAL_PART);
			idCompose.append(unidadOrganica);
			idCompose.append(ID_COMMON_PART);
			Calendar c1 = Calendar.getInstance();
			int anio = c1.get(Calendar.YEAR);
			idCompose.append(anio);

			if (isDocument) {
				idCompose.append(ID_COMMON_PART);
			} else {
				idCompose.append(ID_EXP_PART);
			}
			int numeroId = service.getDefaultIdByDir3(unidadOrganica, isDocument);
			idCompose.append(numeroId);
			idDefault = idCompose.toString();
		} catch (Exception e) {
			logger.debug("No se ha podido cargar el Id por defecto", e);
		}
		return idDefault;
	}
}
