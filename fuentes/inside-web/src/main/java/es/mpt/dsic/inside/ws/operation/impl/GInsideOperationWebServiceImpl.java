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

package es.mpt.dsic.inside.ws.operation.impl;

import javax.annotation.Resource;
import javax.xml.ws.WebServiceContext;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.mpt.dsic.inside.model.converter.InsideConverterDocumento;
import es.mpt.dsic.inside.model.converter.InsideConverterExpediente;
import es.mpt.dsic.inside.model.converter.InsideConverterTipoExpedienteConversion;
import es.mpt.dsic.inside.model.converter.exception.InsideConverterException;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInside;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteInside;
import es.mpt.dsic.inside.service.InsideUtilService;
import es.mpt.dsic.inside.service.exception.InSideServiceException;
import es.mpt.dsic.inside.service.util.XMLUtils;
import es.mpt.dsic.inside.ws.exception.InsideExceptionConverter;
import es.mpt.dsic.inside.ws.exception.InsideWSException;
import es.mpt.dsic.inside.ws.exception.InsideWsErrors;
import es.mpt.dsic.inside.ws.operation.GInsideOperationWebService;
import es.mpt.dsic.inside.xml.eni.documento.TipoDocumento;
import es.mpt.dsic.inside.xml.eni.expediente.TipoExpediente;
import es.mpt.dsic.inside.xml.inside.TipoDocumentoInsideConMAdicionales;
import es.mpt.dsic.inside.xml.inside.TipoExpedienteInsideConMAdicionales;
import es.mpt.dsic.inside.xml.inside.TipoMetadatosAdicionales;
import es.mpt.dsic.inside.xml.inside.ws.credential.WSCredentialInside;
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

@Service("gInsideOperationWebService")
public class GInsideOperationWebServiceImpl implements GInsideOperationWebService {

	protected static final Log logger = LogFactory.getLog(GInsideOperationWebServiceImpl.class);

	@Autowired
	private InsideUtilService insideUtilService;

	@Resource
	WebServiceContext jaxwsContext;

	@Override
	public DocumentoEniFileInside convertirDocumentoAEni(TipoDocumentoConversionInside documento, byte[] contenido,
			Boolean firmar, WSCredentialInside credentialUtil) throws InsideWSException {
		try {
			logger.debug("Inicio GInSideWebServiceImpl.convertirDocumentoAEni");
			DocumentoEniFileInside retorno = new DocumentoEniFileInside();
			TipoDocumento tDoc = insideUtilService.convertirDocumentoAEni(documento, contenido, BooleanUtils.toBoolean(firmar),
					credentialUtil);

			if (!insideUtilService.esLongitudIdentificadorValido(tDoc.getId())) {
				throw new InsideWSException(InsideWsErrors.IDENTIFICADOR_DOCUMENTO_LONGITUD, null, "");
			}
			
			TipoDocumentoInsideConMAdicionales tDocAdc = new TipoDocumentoInsideConMAdicionales();
			tDocAdc.setDocumento(tDoc);
			retorno.setDocumento(tDoc);
			retorno.setDocumentoEniBytes(insideUtilService.generateDocXml(tDocAdc));
			logger.debug("Fin GInSideWebServiceImpl.convertirDocumentoAEni");
			return retorno;
		} catch (InsideWSException e) {
			throw new InsideWSException(InsideWsErrors.IDENTIFICADOR_DOCUMENTO_LONGITUD, null, "");
		} catch (InSideServiceException e) {
			throw InsideExceptionConverter.convertToException(e);
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	public DocumentoEniFileInsideConMAdicionales convertirDocumentoAEniConMAdicionales(TipoDocumentoConversionInside documento,
			TipoMetadatosAdicionales metadatosAdicionales, byte[] contenido, Boolean firmar, WSCredentialInside credentialUtil)
					throws InsideWSException {
		try {
			logger.debug("Inicio GInSideWebServiceImpl.convertirDocumentoAEniConMAdicionales");
			DocumentoEniFileInsideConMAdicionales retorno = new DocumentoEniFileInsideConMAdicionales();
			TipoDocumentoInsideConMAdicionales tDocAdicionales = insideUtilService.convertirDocumentoAEniConMAdicionales(
					documento, metadatosAdicionales, contenido, BooleanUtils.toBoolean(firmar), credentialUtil);

			if (!insideUtilService.esLongitudIdentificadorValido(tDocAdicionales.getDocumento().getId())) {
				throw new InsideWSException(InsideWsErrors.IDENTIFICADOR_DOCUMENTO_LONGITUD, null, "");
			}
			
			retorno.setDocumento(tDocAdicionales.getDocumento());
			retorno.setMetadatosAdicionales(tDocAdicionales.getMetadatosAdicionales());
			retorno.setDocumentoEniBytes(insideUtilService.generateDocXml(tDocAdicionales));
			logger.debug("Fin GInSideWebServiceImpl.convertirDocumentoAEniConMAdicionales");
			return retorno;
		} catch (InSideServiceException e) {
			throw InsideExceptionConverter.convertToException(e);
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	
	private byte[] transformarExpedienteParaValidarFirma(TipoExpedienteInsideConMAdicionales tExpAdi)throws Exception
	{
		// expediente con nodo firma en base64 hay que transformarlo a ds signature
		byte[] expediente_Antes_Transformacion = insideUtilService.generateExpXml(tExpAdi);
		
		// Para validar la firma recoger solo el ns7:expediente sin metadatosadicionales
		byte[] dataConFirmaSinIdentar = insideUtilService.generateExpXmlParaValidar(tExpAdi.getExpediente());

		// sustituye el nodo <ns7:expediente por el dataConFirmaSinIdentar que esta sin identar y bien formado
		String data = XMLUtils.construirExpedienteENIValido(new String(expediente_Antes_Transformacion), new String(dataConFirmaSinIdentar));
		
		// terminar la transformacion de cambio firmabase64 a dsSignature
		byte[] expediente_Despues_Transformacion =XMLUtils.deFirmaBase64_A_DSSignature(data.getBytes("UTF-8"));
		
		return expediente_Despues_Transformacion;
	}
	
	
	@Override
	public ExpedienteEniFileInside convertirExpedienteAEni(TipoExpedienteConversionInside expediente, String contenidoFirma,
			WSCredentialInside credentialUtil) throws InsideWSException {
		try {
			logger.debug("Inicio GInSideWebServiceImpl.convertirExpedienteAEni");
			ExpedienteEniFileInside retorno = new ExpedienteEniFileInside();
			TipoExpediente tExp = insideUtilService.convertirExpedienteAEni(expediente, contenidoFirma, true, credentialUtil);
			retorno.setExpediente(tExp);

			if (!insideUtilService.esLongitudIdentificadorValido(tExp.getId())) {
				throw new InsideWSException(InsideWsErrors.IDENTIFICADOR_DOCUMENTO_LONGITUD, null, "");
			}
			
			TipoExpedienteInsideConMAdicionales tExpAdi = new TipoExpedienteInsideConMAdicionales();
			tExpAdi.setExpediente(tExp);
			
			byte[] expediente_Despues_Transformacion = transformarExpedienteParaValidarFirma(tExpAdi);			
			retorno.setExpedienteEniBytes(expediente_Despues_Transformacion);
			
			logger.debug("Fin GInSideWebServiceImpl.convertirExpedienteAEni");
			return retorno;
		} catch (InsideWSException e) {
			throw new InsideWSException(InsideWsErrors.IDENTIFICADOR_EXPEDIENTE_LONGITUD, null, "");
		} catch (InSideServiceException e) {
			throw InsideExceptionConverter.convertToException(e);
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	public ExpedienteEniFileInsideConMAdicionales convertirExpedienteAEniConMAdicionales(
			TipoExpedienteConversionInside expediente, TipoMetadatosAdicionales metadatosAdicionales, String contenidoFirma,
			WSCredentialInside credentialUtil) throws InsideWSException {
		try {
			logger.debug("Inicio GInSideWebServiceImpl.convertirExpedienteAEniConMAdicionales");
			ExpedienteEniFileInsideConMAdicionales retorno = new ExpedienteEniFileInsideConMAdicionales();

			
			TipoExpedienteInsideConMAdicionales tExp = insideUtilService.convertirExpedienteAEniConMAdicionales(expediente,
					metadatosAdicionales, contenidoFirma, true, credentialUtil);

			if (!insideUtilService.esLongitudIdentificadorValido(tExp.getExpediente().getId())) {
				throw new InsideWSException(InsideWsErrors.IDENTIFICADOR_DOCUMENTO_LONGITUD, null, "");
			}
			retorno.setExpediente(tExp.getExpediente());
			retorno.setMetadatosAdicionales(tExp.getMetadatosAdicionales());
			//retorno.setExpedienteEniBytes(insideUtilService.generateExpXml(tExp));
			
			
			byte[] expediente_Despues_Transformacion = transformarExpedienteParaValidarFirma(tExp);			
			retorno.setExpedienteEniBytes(expediente_Despues_Transformacion);
			
			
			logger.debug("Fin GInSideWebServiceImpl.convertirExpedienteAEniConMAdicionales");
			return retorno;
		} catch (InsideWSException e) {
			throw new InsideWSException(InsideWsErrors.IDENTIFICADOR_EXPEDIENTE_LONGITUD, null, "");
		} catch (InSideServiceException e) {
			throw InsideExceptionConverter.convertToException(e);
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	public ExpedienteEniFileInside convertirExpedienteAEniAutocontenido(TipoExpedienteConversionInsideWS expediente,
			String contenidoFirma, WSCredentialInside credentialUtil) throws InsideWSException {
		try {
			logger.debug("Inicio GInSideWebServiceImpl.convertirExpedienteAEniAutocontenido");
			ExpedienteEniFileInside retorno = new ExpedienteEniFileInside();
			TipoExpedienteConversionInside expConvert = InsideConverterTipoExpedienteConversion
					.convertTipoExpedienteFromWS(expediente);
			TipoExpediente expedienteEni = insideUtilService.convertirExpedienteAEni(expConvert, contenidoFirma, true,
					credentialUtil);

			if (!insideUtilService.esLongitudIdentificadorValido(expedienteEni.getId())) {
				throw new InsideWSException(InsideWsErrors.IDENTIFICADOR_DOCUMENTO_LONGITUD, null, "");
			}
			retorno.setExpediente(expedienteEni);
			TipoExpedienteInsideConMAdicionales tExpAdi = new TipoExpedienteInsideConMAdicionales();
			tExpAdi.setExpediente(expedienteEni);
			//retorno.setExpedienteEniBytes(insideUtilService.generateExpXml(tExpAdi));
			
			
			
			byte[] expediente_Despues_Transformacion = transformarExpedienteParaValidarFirma(tExpAdi);			
			retorno.setExpedienteEniBytes(expediente_Despues_Transformacion);
			
			
			logger.debug("Fin GInSideWebServiceImpl.convertirExpedienteAEniAutocontenido");
			return retorno;
		} catch (InsideWSException e) {
			throw new InsideWSException(InsideWsErrors.IDENTIFICADOR_EXPEDIENTE_LONGITUD, null, "");
		} catch (InsideConverterException e) {
			throw InsideExceptionConverter.convertToException(e);
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	public ExpedienteEniFileInsideConMAdicionales convertirExpedienteAEniConMAdicionalesAutocontenido(
			TipoExpedienteConversionInsideWS expediente, TipoMetadatosAdicionales metadatosAdicionales, String contenidoFirma,
			WSCredentialInside credentialUtil) throws InsideWSException {
		try {
			logger.debug("Inicio GInSideWebServiceImpl.convertirExpedienteAEniConMAdicionalesAutocontenido");
			ExpedienteEniFileInsideConMAdicionales retorno = new ExpedienteEniFileInsideConMAdicionales();
			TipoExpedienteConversionInside expConvert = InsideConverterTipoExpedienteConversion
					.convertTipoExpedienteFromWS(expediente);
			
		
			TipoExpedienteInsideConMAdicionales expedienteEni = insideUtilService.convertirExpedienteAEniConMAdicionales(
					expConvert, metadatosAdicionales, contenidoFirma, true, credentialUtil);

			if (!insideUtilService.esLongitudIdentificadorValido(expedienteEni.getExpediente().getId())) {
				throw new InsideWSException(InsideWsErrors.IDENTIFICADOR_DOCUMENTO_LONGITUD, null, "");
			}
			retorno.setExpediente(expedienteEni.getExpediente());
			retorno.setMetadatosAdicionales(expedienteEni.getMetadatosAdicionales());
			
			byte[] expediente_Despues_Transformacion = transformarExpedienteParaValidarFirma(expedienteEni);			
			retorno.setExpedienteEniBytes(expediente_Despues_Transformacion);
			
	
			logger.debug("Fin GInSideWebServiceImpl.convertirExpedienteAEniConMAdicionalesAutocontenido");
			return retorno;
		} catch (InsideWSException e) {
			throw new InsideWSException(InsideWsErrors.IDENTIFICADOR_EXPEDIENTE_LONGITUD, null, "");
		} catch (InsideConverterException e) {
			throw InsideExceptionConverter.convertToException(e);
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}
	
	@Override
	public TipoResultadoValidacionExpedienteInside validarExpedienteEniFile(TipoExpedienteValidacionInside expedienteValidacion,
			WSCredentialInside credentialUtil) throws InsideWSException {

		try {
			logger.debug("Iniciado validarDocumentoEniFile en WS");

			TipoResultadoValidacionExpedienteInside expedientiEniFile = insideUtilService
					.validarExpedienteEniFile(expedienteValidacion, false);
			
			ObjetoExpedienteInside expedienteInside = InsideConverterExpediente
					.tipoExpedienteValidacionToInside(expedienteValidacion);
			if (!insideUtilService.esLongitudIdentificadorValido(expedienteInside.getIdentificador())) {
				throw new InsideWSException(InsideWsErrors.IDENTIFICADOR_DOCUMENTO_LONGITUD, null, "");
			}
			logger.debug("Finalizado validarDocumentoEniFile en WS");

			return expedientiEniFile;

		} catch (InsideWSException e) {
			throw new InsideWSException(InsideWsErrors.IDENTIFICADOR_EXPEDIENTE_LONGITUD, null, "");
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	public TipoResultadoValidacionDocumentoInside validarDocumentoEniFile(TipoDocumentoValidacionInside documentoValidacion,
			WSCredentialInside credentialUtil) throws InsideWSException {
		try {
			logger.debug("Iniciado validarDocumentoEniFile en WS");

			TipoResultadoValidacionDocumentoInside documentoEniFile = insideUtilService
					.validarDocumentoEniFile(documentoValidacion);
			ObjetoDocumentoInside documentoInside = InsideConverterDocumento.tipoDocumentoValidacionToInside(documentoValidacion);

			if (!insideUtilService.esLongitudIdentificadorValido(documentoInside.getIdentificador())) {
				throw new InsideWSException(InsideWsErrors.IDENTIFICADOR_DOCUMENTO_LONGITUD, null, "");
			}
			logger.debug("Finalizado validarDocumentoEniFile en WS");

			return documentoEniFile;

		} catch (InsideWSException e) {
			throw new InsideWSException(InsideWsErrors.IDENTIFICADOR_DOCUMENTO_LONGITUD, null, "");
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	public TipoResultadoVisualizacionDocumentoInside visualizarDocumentoEni(
			TipoDocumentoVisualizacionInside documentoVisualizacion, WSCredentialInside credentialUtil) throws InsideWSException {

		try {
			logger.debug("Inicio operación visualizarDocumentoEni");

			TipoResultadoVisualizacionDocumentoInside documentoEni = insideUtilService
					.visualizarDocumentoEni(documentoVisualizacion);

			logger.debug("Fin operación visualizarDocumentoEni");

			return documentoEni;

		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

}
