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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import es.mpt.dsic.inside.util.ws.security.CredentialUtil;
import es.mpt.dsic.inside.ws.exception.InsideExceptionConverter;
import es.mpt.dsic.inside.ws.exception.InsideWSException;
import es.mpt.dsic.inside.ws.operation.GInsideOperationWebService;
import es.mpt.dsic.inside.ws.service.GInsideCertificateWebService;
import es.mpt.dsic.inside.xml.inside.TipoMetadatosAdicionales;
import es.mpt.dsic.inside.xml.inside.ws.documento.conversion.TipoDocumentoConversionInside;
import es.mpt.dsic.inside.xml.inside.ws.documento.file.DocumentoEniFileInside;
import es.mpt.dsic.inside.xml.inside.ws.documento.file.DocumentoEniFileInsideConMAdicionales;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoExpedienteConversionInside;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoExpedienteConversionInsideWS;
import es.mpt.dsic.inside.xml.inside.ws.expediente.file.ExpedienteEniFileInside;
import es.mpt.dsic.inside.xml.inside.ws.expediente.file.ExpedienteEniFileInsideConMAdicionales;
import es.mpt.dsic.inside.xml.inside.ws.validacion.documento.TipoDocumentoValidacionInside;
import es.mpt.dsic.inside.xml.inside.ws.validacion.documento.resultados.TipoResultadoValidacionDocumentoInside;
import es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.TipoExpedienteValidacionInside;
import es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.resultados.TipoResultadoValidacionExpedienteInside;
import es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.TipoDocumentoVisualizacionInside;
import es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.TipoResultadoVisualizacionDocumentoInside;

@WebService(endpointInterface = "es.mpt.dsic.inside.ws.service.GInsideCertificateWebService", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", portName = "GInsideCertificateWSPort", serviceName = "GInsideCertificateWS")
public class GInsideCertificateWebServiceImpl implements GInsideCertificateWebService {

	protected static final Log logger = LogFactory.getLog(GInsideCertificateWebServiceImpl.class);

	@Autowired
	GInsideOperationWebService gInsideOperationWebService;
	
	@Autowired
	private CredentialUtil credentialUtil;

	@Override
	public DocumentoEniFileInside convertirDocumentoAEni(TipoDocumentoConversionInside documento, byte[] contenido,
			Boolean firmar) throws InsideWSException {
		try {
			return gInsideOperationWebService.convertirDocumentoAEni(documento, contenido, firmar, credentialUtil.getCredentialEeutil());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	public DocumentoEniFileInsideConMAdicionales convertirDocumentoAEniConMAdicionales(TipoDocumentoConversionInside documento,
			TipoMetadatosAdicionales metadatosAdicionales, byte[] contenido, Boolean firmar) throws InsideWSException {
		try {
			return gInsideOperationWebService.convertirDocumentoAEniConMAdicionales(documento, metadatosAdicionales, contenido,
					firmar, credentialUtil.getCredentialEeutil());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	public ExpedienteEniFileInside convertirExpedienteAEni(TipoExpedienteConversionInside expediente, String contenidoFirma)
			throws InsideWSException {
		try {
			return gInsideOperationWebService.convertirExpedienteAEni(expediente, contenidoFirma, credentialUtil.getCredentialEeutil());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	public ExpedienteEniFileInsideConMAdicionales convertirExpedienteAEniConMAdicionales(
			TipoExpedienteConversionInside expediente, TipoMetadatosAdicionales metadatosAdicionales, String contenidoFirma)
					throws InsideWSException {
		try {
			return gInsideOperationWebService.convertirExpedienteAEniConMAdicionales(expediente, metadatosAdicionales,
					contenidoFirma, credentialUtil.getCredentialEeutil());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	public ExpedienteEniFileInside convertirExpedienteAEniAutocontenido(TipoExpedienteConversionInsideWS expediente,
			String contenidoFirma) throws InsideWSException {
		try {
			return gInsideOperationWebService.convertirExpedienteAEniAutocontenido(expediente, contenidoFirma, credentialUtil.getCredentialEeutil());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	public ExpedienteEniFileInsideConMAdicionales convertirExpedienteAEniConMAdicionalesAutocontenido(
			TipoExpedienteConversionInsideWS expediente, TipoMetadatosAdicionales metadatosAdicionales, String contenidoFirma)
					throws InsideWSException {
		try {
			return gInsideOperationWebService.convertirExpedienteAEniConMAdicionalesAutocontenido(expediente,
					metadatosAdicionales, contenidoFirma, credentialUtil.getCredentialEeutil());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	public TipoResultadoValidacionExpedienteInside validarExpedienteEniFile(TipoExpedienteValidacionInside expedienteValidacion)
			throws InsideWSException {
		try {
			return gInsideOperationWebService.validarExpedienteEniFile(expedienteValidacion, credentialUtil.getCredentialEeutil());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	public TipoResultadoValidacionDocumentoInside validarDocumentoEniFile(TipoDocumentoValidacionInside documentoValidacion)
			throws InsideWSException {
		try {
			return gInsideOperationWebService.validarDocumentoEniFile(documentoValidacion, credentialUtil.getCredentialEeutil());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	public TipoResultadoVisualizacionDocumentoInside visualizarDocumentoEni(
			TipoDocumentoVisualizacionInside documentoVisualizacion) throws InsideWSException {
		try {
			return gInsideOperationWebService.visualizarDocumentoEni(documentoVisualizacion, credentialUtil.getCredentialEeutil());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}
}
