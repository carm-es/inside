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

package es.mpt.dsic.inside.ws.service.impl;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import es.mpt.dsic.inside.model.converter.InsideConverterMtom;
import es.mpt.dsic.inside.ws.exception.InsideExceptionConverter;
import es.mpt.dsic.inside.ws.exception.InsideWSException;
import es.mpt.dsic.inside.ws.operation.InsideOperationWebService;
import es.mpt.dsic.inside.ws.service.InsideCredentialMtomWebService;
import es.mpt.dsic.inside.xml.eni.documento.TipoDocumento;
import es.mpt.dsic.inside.xml.eni.documento.contenido.mtom.TipoContenidoMtom;
import es.mpt.dsic.inside.xml.eni.documento.mtom.TipoDocumentoMtom;
import es.mpt.dsic.inside.xml.eni.expediente.TipoExpediente;
import es.mpt.dsic.inside.xml.eni.expediente.mtom.TipoExpedienteMtom;
import es.mpt.dsic.inside.xml.inside.ExpedienteInsideInfo;
import es.mpt.dsic.inside.xml.inside.TipoMetadatosAdicionales;
import es.mpt.dsic.inside.xml.inside.mtom.TipoDocumentoInsideMtom;
import es.mpt.dsic.inside.xml.inside.mtom.TipoExpedienteInsideMtom;
import es.mpt.dsic.inside.xml.inside.ws.credential.WSCredentialInside;
import es.mpt.dsic.inside.xml.inside.ws.documento.DocumentoInsideInfo;
import es.mpt.dsic.inside.xml.inside.ws.documento.alta.TipoDocumentoAltaInside;
import es.mpt.dsic.inside.xml.inside.ws.documento.alta.TipoDocumentoAsociadoaExpediente;
import es.mpt.dsic.inside.xml.inside.ws.documento.alta.mtom.TipoDocumentoAltaInsideMtom;
import es.mpt.dsic.inside.xml.inside.ws.documento.alta.mtom.TipoDocumentoAsociadoaExpedienteMtom;
import es.mpt.dsic.inside.xml.inside.ws.documento.conversion.TipoDocumentoConversionInside;
import es.mpt.dsic.inside.xml.inside.ws.documento.conversion.mtom.TipoDocumentoConversionInsideMtom;
import es.mpt.dsic.inside.xml.inside.ws.documento.mtom.TipoDocumentoEniFileInsideMtom;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoExpedienteConversionInside;
import es.mpt.dsic.inside.xml.inside.ws.expediente.mtom.TipoExpedienteEniFileInsideMtom;
import es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.mtom.TipoResultadoVisualizacionDocumentoInsideMtom;

@WebService(endpointInterface = "es.mpt.dsic.inside.ws.service.InsideCredentialMtomWebService", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", portName = "InsideMtomWSPort", serviceName = "InsideMtomWS")
public class InsideCredentialMtomWebServiceImpl implements InsideCredentialMtomWebService {

	@Autowired
	InsideOperationWebService insideOperationWebService;

	@Override
	@Secured("ROLE_ALTA_EXPEDIENTE")
	public ExpedienteInsideInfo altaExpedienteEni(WSCredentialInside info, TipoExpedienteMtom expedienteEniMtom,
			TipoMetadatosAdicionales metadatosAdicionales) throws InsideWSException {
		try {
			TipoExpediente expedienteEni = InsideConverterMtom.mtomTipoExpedienteToInside(expedienteEniMtom);
			return insideOperationWebService.altaExpedienteEni(expedienteEni, metadatosAdicionales, info.getIdaplicacion());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_MODIFICA_EXPEDIENTE")
	public ExpedienteInsideInfo modificaExpedienteEni(WSCredentialInside info, TipoExpedienteMtom expedienteEniMtom,
			TipoMetadatosAdicionales metadatosAdicionales) throws InsideWSException {
		try {
			TipoExpediente expedienteEni = InsideConverterMtom.mtomTipoExpedienteToInside(expedienteEniMtom);
			return insideOperationWebService.modificaExpedienteEni(expedienteEni, metadatosAdicionales, info.getIdaplicacion());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_LEER_EXPEDIENTE")
	public TipoExpedienteInsideMtom getExpediente(WSCredentialInside info, String identificador, Integer version)
			throws InsideWSException {
		try {
			return InsideConverterMtom.tipoExpedienteInsideToMtom(
					insideOperationWebService.getExpediente(identificador, version, info.getIdaplicacion()));
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_LEER_EXPEDIENTE")
	public TipoExpedienteMtom getExpedienteEni(WSCredentialInside info, String identificador) throws InsideWSException {
		try {
			return InsideConverterMtom
					.tipoExpedienteToMtom(insideOperationWebService.getExpedienteEni(identificador, info.getIdaplicacion()));
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_LEER_EXPEDIENTE")
	public TipoExpedienteEniFileInsideMtom getExpedienteEniFile(WSCredentialInside info, String identificador,
			Boolean visualizacionIndice) throws InsideWSException {
		try {
			return InsideConverterMtom.tipoExpedienteEniFileToMtom(
					insideOperationWebService.getExpedienteEniFile(identificador, visualizacionIndice, info.getIdaplicacion()));
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}

	}

	@Override
	@Secured("ROLE_ALTA_DOCUMENTO")
	public DocumentoInsideInfo altaDocumentoOriginal(WSCredentialInside info, TipoDocumentoAltaInsideMtom documento)
			throws InsideWSException {
		try {
			TipoDocumentoAltaInside documentoAlta = InsideConverterMtom.mtomTipoDocumentoAltaToInside(documento);
			return insideOperationWebService.altaDocumentoOriginal(documentoAlta, info.getIdaplicacion());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}

	}

	@Override
	@Secured("ROLE_ALTA_DOCUMENTO")
	public DocumentoInsideInfo altaDocumentoDigitalizado(WSCredentialInside info, TipoDocumentoAltaInsideMtom documento)
			throws InsideWSException {
		try {
			TipoDocumentoAltaInside documentoAlta = InsideConverterMtom.mtomTipoDocumentoAltaToInside(documento);
			return insideOperationWebService.altaDocumentoDigitalizado(documentoAlta, info.getIdaplicacion());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_ALTA_DOCUMENTO")
	public DocumentoInsideInfo altaDocumentoCopiaParcial(WSCredentialInside info, TipoDocumentoAltaInsideMtom documento,
			String identificadorDocumentoOrigen) throws InsideWSException {
		try {
			TipoDocumentoAltaInside documentoAlta = InsideConverterMtom.mtomTipoDocumentoAltaToInside(documento);
			return insideOperationWebService.altaDocumentoCopiaParcial(documentoAlta, identificadorDocumentoOrigen,
					info.getIdaplicacion());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_ALTA_DOCUMENTO")
	public DocumentoInsideInfo altaDocumentoCambioFormato(WSCredentialInside info, TipoDocumentoAltaInsideMtom documento,
			String identificadorDocumentoOrigen) throws InsideWSException {
		try {
			TipoDocumentoAltaInside documentoAlta = InsideConverterMtom.mtomTipoDocumentoAltaToInside(documento);
			return insideOperationWebService.altaDocumentoCambioFormato(documentoAlta, identificadorDocumentoOrigen,
					info.getIdaplicacion());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_ALTA_DOCUMENTO")
	public DocumentoInsideInfo altaDocumentoOtros(WSCredentialInside info, TipoDocumentoAltaInsideMtom documento)
			throws InsideWSException {
		try {
			TipoDocumentoAltaInside documentoAlta = InsideConverterMtom.mtomTipoDocumentoAltaToInside(documento);
			return insideOperationWebService.altaDocumentoOtros(documentoAlta, info.getIdaplicacion());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_ALTA_DOCUMENTO")
	public DocumentoInsideInfo altaDocumentoEni(WSCredentialInside info, TipoDocumentoMtom documento,
			TipoDocumentoAsociadoaExpedienteMtom asociadoAExpedienteMtom, TipoMetadatosAdicionales metadatosAdicionales,
			boolean firmaServidor) throws InsideWSException {
		try {
			TipoDocumentoAsociadoaExpediente expediente = null;
			TipoDocumento documentoEni = InsideConverterMtom.mtomTipoDocumentoToInside(documento);
			if (asociadoAExpedienteMtom != null) {
				expediente = new TipoDocumentoAsociadoaExpediente();
				expediente.setIdentificadorExpediente(asociadoAExpedienteMtom.getIdentificadorExpediente());
				expediente.setIdentificadorCarpeta(asociadoAExpedienteMtom.getIdentificadorCarpeta());
			}
			return insideOperationWebService.altaDocumentoEni(documentoEni, expediente, metadatosAdicionales, firmaServidor,
					info.getIdaplicacion());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_MODIFICA_DOCUMENTO")
	public DocumentoInsideInfo modificaDocumento(WSCredentialInside info, TipoDocumentoMtom documentoMtom,
			TipoMetadatosAdicionales metadatosAdicionales) throws InsideWSException {
		try {
			TipoDocumento documentoEni = InsideConverterMtom.mtomTipoDocumentoToInside(documentoMtom);
			return insideOperationWebService.modificaDocumento(documentoEni, metadatosAdicionales, info.getIdaplicacion());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_LEER_DOCUMENTO")
	public TipoDocumentoInsideMtom getDocumento(WSCredentialInside info, String identificador) throws InsideWSException {
		try {
			return InsideConverterMtom
					.tipoDocumentoInsideToMtom(insideOperationWebService.getDocumento(identificador, info.getIdaplicacion()));
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_LEER_DOCUMENTO")
	public TipoDocumentoMtom getDocumentoEni(WSCredentialInside info, String identificador) throws InsideWSException {
		try {
			return InsideConverterMtom
					.tipoDocumentoToMtom(insideOperationWebService.getDocumentoEni(identificador, info.getIdaplicacion()));
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_LEER_DOCUMENTO")
	public TipoDocumentoEniFileInsideMtom getDocumentoEniFile(WSCredentialInside info, String identificador)
			throws InsideWSException {
		try {
			return InsideConverterMtom.tipoDocumentoEniFileToMtom(
					insideOperationWebService.getDocumentoEniFile(identificador, info.getIdaplicacion()));
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_LEER_DOCUMENTO")
	public TipoContenidoMtom getDocumentoContenido(WSCredentialInside info, String identificador) throws InsideWSException {
		try {
			return InsideConverterMtom
					.tipoContenidoToMtom(insideOperationWebService.getDocumentoContenido(identificador, info.getIdaplicacion()));
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	public TipoDocumentoMtom convertirDocumentoAEni(WSCredentialInside info,
			TipoDocumentoConversionInsideMtom documentoConversionMtom, byte[] bytesContenido, Boolean firmar)
					throws InsideWSException {
		try {
			TipoDocumentoConversionInside documentoConversion = InsideConverterMtom
					.mtomTipoDocumentoConversionToInside(documentoConversionMtom);
			return InsideConverterMtom.tipoDocumentoToMtom(
					(insideOperationWebService.convertirDocumentoAEni(documentoConversion, bytesContenido, firmar, info))
							.getDocumento());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	public TipoExpedienteMtom convertirExpedienteAEni(WSCredentialInside info,
			TipoExpedienteConversionInside expedienteConversion, String contenidoFirma) throws InsideWSException {
		try {
			return InsideConverterMtom.tipoExpedienteToMtom(
					(insideOperationWebService.convertirExpedienteAEni(expedienteConversion, contenidoFirma, info))
							.getExpediente());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_LEER_DOCUMENTO")
	public TipoResultadoVisualizacionDocumentoInsideMtom visualizarDocumento(WSCredentialInside info, String identificador)
			throws InsideWSException {
		try {
			return InsideConverterMtom.tipoResultadoVisualizacionDocumentoToMtom(
					insideOperationWebService.visualizarDocumento(identificador, info.getIdaplicacion()));
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

}
