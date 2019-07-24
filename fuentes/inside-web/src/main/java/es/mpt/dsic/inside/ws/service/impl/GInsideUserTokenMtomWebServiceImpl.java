/*
 * Copyright (C) 2016 MINHAP, Gobierno de España This program is licensed and may be used, modified
 * and redistributed under the terms of the European Public License (EUPL), either version 1.1 or
 * (at your option) any later version as soon as they are approved by the European Commission.
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and more details. You
 * should have received a copy of the EUPL1.1 license along with this program; if not, you may find
 * it at http://joinup.ec.europa.eu/software/page/eupl/licence-eupl
 */

package es.mpt.dsic.inside.ws.service.impl;

import java.io.IOException;
import javax.activation.DataHandler;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import es.mpt.dsic.inside.model.converter.InsideConverterMtom;
import es.mpt.dsic.inside.model.converter.InsideConverterTipoExpedienteMtomConversion;
import es.mpt.dsic.inside.model.converter.exception.InsideConverterException;
import es.mpt.dsic.inside.service.exception.InSideServiceException;
import es.mpt.dsic.inside.service.exception.InsideServiceInternalException;
import es.mpt.dsic.inside.util.ws.security.CredentialUtil;
import es.mpt.dsic.inside.ws.exception.InsideExceptionConverter;
import es.mpt.dsic.inside.ws.exception.InsideWSException;
import es.mpt.dsic.inside.ws.operation.GInsideOperationWebService;
import es.mpt.dsic.inside.ws.service.GInsideUserTokenMtomWebService;
import es.mpt.dsic.inside.xml.inside.TipoMetadatosAdicionales;
import es.mpt.dsic.inside.xml.inside.ws.documento.conversion.TipoDocumentoConversionInside;
import es.mpt.dsic.inside.xml.inside.ws.documento.conversion.mtom.TipoDocumentoConversionInsideMtom;
import es.mpt.dsic.inside.xml.inside.ws.documento.file.DocumentoEniFileInside;
import es.mpt.dsic.inside.xml.inside.ws.documento.file.DocumentoEniFileInsideConMAdicionales;
import es.mpt.dsic.inside.xml.inside.ws.documento.fileMtom.DocumentoEniFileInsideConMAdicionalesMtom;
import es.mpt.dsic.inside.xml.inside.ws.documento.fileMtom.DocumentoEniFileInsideMtom;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoExpedienteConversionInside;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoExpedienteConversionInsideWSMtom;
import es.mpt.dsic.inside.xml.inside.ws.expediente.file.ExpedienteEniFileInside;
import es.mpt.dsic.inside.xml.inside.ws.expediente.file.ExpedienteEniFileInsideConMAdicionales;
import es.mpt.dsic.inside.xml.inside.ws.expediente.fileMtom.ExpedienteEniFileInsideConMAdicionalesMtom;
import es.mpt.dsic.inside.xml.inside.ws.expediente.fileMtom.ExpedienteEniFileInsideMtom;
import es.mpt.dsic.inside.xml.inside.ws.validacion.documento.TipoDocumentoValidacionInside;
import es.mpt.dsic.inside.xml.inside.ws.validacion.documento.mtom.TipoDocumentoValidacionInsideMtom;
import es.mpt.dsic.inside.xml.inside.ws.validacion.documento.resultados.TipoResultadoValidacionDocumentoInside;
import es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.TipoExpedienteValidacionInside;
import es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.mtom.TipoExpedienteValidacionInsideMtom;
import es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.resultados.TipoResultadoValidacionExpedienteInside;
import es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.TipoDocumentoVisualizacionInside;
import es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.mtom.TipoDocumentoVisualizacionInsideMtom;
import es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.mtom.TipoResultadoVisualizacionDocumentoInsideMtom;

@WebService(endpointInterface = "es.mpt.dsic.inside.ws.service.GInsideUserTokenMtomWebService",
    targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
    portName = "GInsideUserTokenMtomWSPort", serviceName = "GInsideUserTokenMtomWS")
public class GInsideUserTokenMtomWebServiceImpl implements GInsideUserTokenMtomWebService {

  protected static final Log logger = LogFactory.getLog(GInsideUserTokenMtomWebServiceImpl.class);

  @Autowired
  private CredentialUtil credentialUtil;

  @Resource
  WebServiceContext jaxwsContext;

  @Autowired
  GInsideOperationWebService gInsideOperationWebService;

  @Override
  public DocumentoEniFileInsideMtom convertirDocumentoAEni(
      TipoDocumentoConversionInsideMtom documento, DataHandler contenido, Boolean firmar)
      throws InsideWSException {
    try {
      logger.debug("Inicio GInSideMtomWebServiceImpl.convertirDocumentoAEni");
      TipoDocumentoConversionInside documentoConversion =
          InsideConverterMtom.tipoDocumentoConversionToInside(documento);
      DocumentoEniFileInside tDoc = gInsideOperationWebService.convertirDocumentoAEni(
          documentoConversion, getContent(contenido), firmar,
          credentialUtil.getCredentialEeutilUserToken(jaxwsContext));
      logger.debug("Fin GInSideMtomWebServiceImpl.convertirDocumentoAEni");
      return InsideConverterMtom.documentoEniFileToMtom(tDoc);
    } catch (InSideServiceException e) {
      throw InsideExceptionConverter.convertToException(e);
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  public DocumentoEniFileInsideConMAdicionalesMtom convertirDocumentoAEniConMAdicionales(
      TipoDocumentoConversionInsideMtom documento, TipoMetadatosAdicionales metadatosAdicionales,
      DataHandler contenido, Boolean firmar) throws InsideWSException {
    try {
      logger.debug("Inicio GInSideMtomWebServiceImpl.convertirDocumentoAEniConMAdicionales");
      TipoDocumentoConversionInside documentoConversion =
          InsideConverterMtom.tipoDocumentoConversionToInside(documento);
      DocumentoEniFileInsideConMAdicionales tDocAdicionales =
          gInsideOperationWebService.convertirDocumentoAEniConMAdicionales(documentoConversion,
              metadatosAdicionales, getContent(contenido), firmar,
              credentialUtil.getCredentialEeutilUserToken(jaxwsContext));
      logger.debug("Fin GInSideMtomWebServiceImpl.convertirDocumentoAEniConMAdicionales");
      return InsideConverterMtom.documentoEniFileToMtomConAdicionales(tDocAdicionales);
    } catch (InSideServiceException e) {
      throw InsideExceptionConverter.convertToException(e);
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  public ExpedienteEniFileInsideMtom convertirExpedienteAEni(
      TipoExpedienteConversionInside expediente, String contenidoFirma) throws InsideWSException {
    try {
      logger.debug("Inicio GInSideMtomWebServiceImpl.convertirExpedienteAEni");
      ExpedienteEniFileInside expedienteEniFile =
          gInsideOperationWebService.convertirExpedienteAEni(expediente, contenidoFirma,
              credentialUtil.getCredentialEeutilUserToken(jaxwsContext));
      logger.debug("Fin GInSideMtomWebServiceImpl.convertirExpedienteAEni");
      return InsideConverterMtom.expedienteEniFileToMtom(expedienteEniFile);
    } catch (InSideServiceException e) {
      throw InsideExceptionConverter.convertToException(e);
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  public ExpedienteEniFileInsideConMAdicionalesMtom convertirExpedienteAEniConMAdicionales(
      TipoExpedienteConversionInside expediente, TipoMetadatosAdicionales metadatosAdicionales,
      String contenidoFirma) throws InsideWSException {
    try {
      logger.debug("Inicio GInSideMtomWebServiceImpl.convertirExpedienteAEniConMAdicionales");
      ExpedienteEniFileInsideConMAdicionales expedienteEniFile = gInsideOperationWebService
          .convertirExpedienteAEniConMAdicionales(expediente, metadatosAdicionales, contenidoFirma,
              credentialUtil.getCredentialEeutilUserToken(jaxwsContext));
      logger.debug("Fin GInSideMtomWebServiceImpl.convertirExpedienteAEniConMAdicionales");
      return InsideConverterMtom.expedienteEniFileToMtomConAdicionales(expedienteEniFile);
    } catch (InSideServiceException e) {
      throw InsideExceptionConverter.convertToException(e);
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  public ExpedienteEniFileInsideMtom convertirExpedienteAEniAutocontenido(
      TipoExpedienteConversionInsideWSMtom expediente, String contenidoFirma)
      throws InsideWSException {
    try {
      logger.debug("Inicio GInSideMtomWebServiceImpl.convertirExpedienteAEniAutocontenido");
      TipoExpedienteConversionInside expConvert =
          InsideConverterTipoExpedienteMtomConversion.convertTipoExpedienteFromWS(expediente);
      ExpedienteEniFileInside expedienteEniFile =
          gInsideOperationWebService.convertirExpedienteAEni(expConvert, contenidoFirma,
              credentialUtil.getCredentialEeutilUserToken(jaxwsContext));
      logger.debug("Fin GInSideMtomWebServiceImpl.convertirExpedienteAEniAutocontenido");
      return InsideConverterMtom.expedienteEniFileToMtom(expedienteEniFile);
    } catch (InsideConverterException e) {
      throw InsideExceptionConverter.convertToException(e);
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  public ExpedienteEniFileInsideConMAdicionalesMtom convertirExpedienteAEniConMAdicionalesAutocontenido(
      TipoExpedienteConversionInsideWSMtom expediente,
      TipoMetadatosAdicionales metadatosAdicionales, String contenidoFirma)
      throws InsideWSException {
    try {
      logger.debug(
          "Inicio GInSideMtomWebServiceImpl.convertirExpedienteAEniConMAdicionalesAutocontenido");
      TipoExpedienteConversionInside expConvert =
          InsideConverterTipoExpedienteMtomConversion.convertTipoExpedienteFromWS(expediente);
      ExpedienteEniFileInsideConMAdicionales expedienteEniFile = gInsideOperationWebService
          .convertirExpedienteAEniConMAdicionales(expConvert, metadatosAdicionales, contenidoFirma,
              credentialUtil.getCredentialEeutilUserToken(jaxwsContext));
      logger.debug(
          "Fin GInSideMtomWebServiceImpl.convertirExpedienteAEniConMAdicionalesAutocontenido");
      return InsideConverterMtom.expedienteEniFileToMtomConAdicionales(expedienteEniFile);
    } catch (InsideConverterException e) {
      throw InsideExceptionConverter.convertToException(e);
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  private byte[] getContent(DataHandler data) throws InsideServiceInternalException {
    byte[] retorno = null;
    try {
      if (data != null && data.getInputStream() != null) {
        retorno = IOUtils.toByteArray(data.getInputStream());
      }
    } catch (IOException e) {
      throw new InsideServiceInternalException(e.getMessage(), e);
    }
    return retorno;
  }

  @Override
  public TipoResultadoValidacionDocumentoInside validarDocumentoEniFile(
      TipoDocumentoValidacionInsideMtom documentoMtom) throws InsideWSException {
    try {
      logger.debug("Iniciado validarDocumentoEniFile en WS");
      TipoDocumentoValidacionInside documentoValidacion =
          InsideConverterMtom.tipoDocumentoValidacionToInside(documentoMtom);
      return gInsideOperationWebService.validarDocumentoEniFile(documentoValidacion,
          credentialUtil.getCredentialEeutilUserToken(jaxwsContext));
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  public TipoResultadoValidacionExpedienteInside validarExpedienteEniFile(
      TipoExpedienteValidacionInsideMtom expedienteMtom) throws InsideWSException {
    try {
      logger.debug("Iniciado validarDocumentoEniFile en WS");
      TipoExpedienteValidacionInside expedienteValidacion =
          InsideConverterMtom.tipoExpedienteValidacionToInside(expedienteMtom);
      return gInsideOperationWebService.validarExpedienteEniFile(expedienteValidacion,
          credentialUtil.getCredentialEeutilUserToken(jaxwsContext));
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  public TipoResultadoVisualizacionDocumentoInsideMtom visualizarDocumentoEni(
      TipoDocumentoVisualizacionInsideMtom documentoMtom) throws InsideWSException {
    try {
      logger.debug("Inicio operación visualizarDocumentoEni");
      TipoDocumentoVisualizacionInside documentoVisualizacion =
          InsideConverterMtom.tipoDocumentoVisualizacionToInside(documentoMtom);
      return InsideConverterMtom.tipoResultadoVisualizacionDocumentoToMtom(
          gInsideOperationWebService.visualizarDocumentoEni(documentoVisualizacion,
              credentialUtil.getCredentialEeutilUserToken(jaxwsContext)));
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

}
