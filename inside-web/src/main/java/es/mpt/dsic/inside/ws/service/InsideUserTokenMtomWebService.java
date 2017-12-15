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

package es.mpt.dsic.inside.ws.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

import org.apache.cxf.annotations.GZIP;
import org.apache.cxf.annotations.Logging;
import org.apache.cxf.interceptor.OutFaultInterceptors;
import org.apache.cxf.interceptor.OutInterceptors;

import es.mpt.dsic.inside.ws.cxf.interceptor.InsideWsErrorInterceptor;
import es.mpt.dsic.inside.ws.cxf.interceptor.InsideWsIndiceExpedienteSignerInterceptor;
import es.mpt.dsic.inside.ws.exception.InsideWSException;
import es.mpt.dsic.inside.xml.eni.documento.contenido.mtom.TipoContenidoMtom;
import es.mpt.dsic.inside.xml.eni.documento.mtom.TipoDocumentoMtom;
import es.mpt.dsic.inside.xml.eni.expediente.mtom.TipoExpedienteMtom;
import es.mpt.dsic.inside.xml.inside.ExpedienteInsideInfo;
import es.mpt.dsic.inside.xml.inside.TipoMetadatosAdicionales;
import es.mpt.dsic.inside.xml.inside.mtom.TipoDocumentoInsideMtom;
import es.mpt.dsic.inside.xml.inside.mtom.TipoExpedienteInsideMtom;
import es.mpt.dsic.inside.xml.inside.ws.documento.DocumentoInsideInfo;
import es.mpt.dsic.inside.xml.inside.ws.documento.alta.mtom.TipoDocumentoAltaInsideMtom;
import es.mpt.dsic.inside.xml.inside.ws.documento.alta.mtom.TipoDocumentoAsociadoaExpedienteMtom;
import es.mpt.dsic.inside.xml.inside.ws.documento.conversion.mtom.TipoDocumentoConversionInsideMtom;
import es.mpt.dsic.inside.xml.inside.ws.documento.mtom.TipoDocumentoEniFileInsideMtom;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoExpedienteConversionInside;
import es.mpt.dsic.inside.xml.inside.ws.expediente.mtom.TipoExpedienteEniFileInsideMtom;
import es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.mtom.TipoResultadoVisualizacionDocumentoInsideMtom;

@WebService(name="InSideUserTokenMtomWebService",
			targetNamespace="https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService")

@Logging
@GZIP
@OutInterceptors (interceptors = {},classes={InsideWsIndiceExpedienteSignerInterceptor.class})
@OutFaultInterceptors(interceptors = {},classes={InsideWsErrorInterceptor.class})
@BindingType(SOAPBinding.SOAP11HTTP_MTOM_BINDING) 
public interface InsideUserTokenMtomWebService {
	
	
	@WebMethod(operationName = "altaDocumentoOriginal", action = "urn:altaDocumentoOriginal")
	@WebResult(name = "DocumentoInsideInfo", partName = "DocumentoInsideInfo",targetNamespace="https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/documentoInfo")
	public DocumentoInsideInfo altaDocumentoOriginal(
			@WebParam(name = "documento") @XmlElement(required = true, name = "documento") TipoDocumentoAltaInsideMtom documentoMtom)
			throws InsideWSException;
	
	@WebMethod(operationName = "altaDocumentoDigitalizado", action = "urn:altaDocumentoDigitalizado")
	@WebResult(name = "DocumentoInsideInfo", partName = "DocumentoInsideInfo",targetNamespace="https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/documentoInfo")
	public DocumentoInsideInfo altaDocumentoDigitalizado(
			@WebParam(name = "documento") @XmlElement(required = true, name = "documento") TipoDocumentoAltaInsideMtom documentoMtom)
			throws InsideWSException;
	
	@WebMethod(operationName = "altaDocumentoCopiaParcial", action = "urn:altaDocumentoCopiaParcial")
	@WebResult(name = "DocumentoInsideInfo", partName = "DocumentoInsideInfo",targetNamespace="https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/documentoInfo")
	public DocumentoInsideInfo altaDocumentoCopiaParcial(
			@WebParam(name = "documento") @XmlElement(required = true, name = "documento") TipoDocumentoAltaInsideMtom documentoMtom,
			@WebParam(name = "identificadorDocumentoOrigen") @XmlElement(required = true, name = "identificadorDocumentoOrigen") String identificadorDocumentoOrigen )
			throws InsideWSException;
	
	@WebMethod(operationName = "altaDocumentoCambioFormato", action = "urn:altaDocumentoCambioFormato")
	@WebResult(name = "DocumentoInsideInfo", partName = "DocumentoInsideInfo",targetNamespace="https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/documentoInfo")
	public DocumentoInsideInfo altaDocumentoCambioFormato(
			@WebParam(name = "documento") @XmlElement(required = true, name = "documento") TipoDocumentoAltaInsideMtom documentoMtom,
			@WebParam(name = "identificadorDocumentoOrigen") @XmlElement(required = true, name = "identificadorDocumentoOrigen") String identificadorDocumentoOrigen )
			throws InsideWSException;
	
	@WebMethod(operationName = "altaDocumentoOtros", action = "urn:altaDocumentoOtros")
	@WebResult(name = "DocumentoInsideInfo", partName = "DocumentoInsideInfo",targetNamespace="https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/documentoInfo")
	public DocumentoInsideInfo altaDocumentoOtros(
			@WebParam(name = "documento") @XmlElement(required = true, name = "documento") TipoDocumentoAltaInsideMtom documentoMtom)
			throws InsideWSException;
	
	@WebMethod(operationName = "convertirDocumentoAEni", action = "urn:convertirDocumentoAEni")
	@WebResult(name = "documento", partName = "documento",targetNamespace="http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e")
	public TipoDocumentoMtom convertirDocumentoAEni(
			@WebParam(name = "documento") @XmlElement(required = true, name = "documento") TipoDocumentoConversionInsideMtom documentoMtom,
			@WebParam(name = "contenido") @XmlElement(required = false, name = "contenido") byte[] contenido,
			@WebParam(name = "firmar") @XmlElement(required = false, name = "firmar") Boolean firmar)
			throws InsideWSException;
	
	@WebMethod(operationName = "altaDocumentoEni", action = "urn:altaDocumentoEni")
	@WebResult(name = "DocumentoInsideInfo", partName = "DocumentoInsideInfo",targetNamespace="https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/documentoInfo")
	public DocumentoInsideInfo altaDocumentoEni(		
			@WebParam(name = "documento") @XmlElement(required = true, name = "documento") TipoDocumentoMtom documentoMtom,
			@WebParam(name = "asociadoaExpediente") @XmlElement(required = false, name = "asociadoaExpediente") TipoDocumentoAsociadoaExpedienteMtom asociadoaExpediente,
			@WebParam(name = "metadatosAdicionales") @XmlElement(required = false, name = "metadatosAdicionales") TipoMetadatosAdicionales metadatosAdicionales,
			@WebParam(name = "firmaServidor") @XmlElement(required = false, name = "firmaServidor") boolean firmaServidor)
			throws InsideWSException;
	
	@WebMethod(operationName = "modificaDocumento", action = "urn:modificaDocumento")
	@WebResult(name = "DocumentoInsideInfo", partName = "DocumentoInsideInfo",targetNamespace="https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/documentoInfo")
	public DocumentoInsideInfo modificaDocumento(
			@WebParam(name = "documento") @XmlElement(required = true, name = "documento") TipoDocumentoMtom documentoMtom,
			@WebParam(name = "metadatosAdicionales") @XmlElement(required = true, name = "metadatosAdicionales") TipoMetadatosAdicionales metadatosAdicionales) 
			throws InsideWSException;
	
	@WebMethod(operationName = "getDocumentoEni", action = "urn:getDocumentoEni")
	@WebResult(name = "documento", partName = "documento",targetNamespace="http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e")
	public TipoDocumentoMtom getDocumentoEni(
			@WebParam(name = "identificador") @XmlElement(required = true, name = "identificador") String identificador)
			throws InsideWSException;
	
	@WebMethod(operationName = "getDocumentoEniFile", action = "urn:getDocumentoEniFile")
	@WebResult(name = "documentoEniFile", partName = "documentoEniFile",targetNamespace="https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/documentoEniFile")	
	public TipoDocumentoEniFileInsideMtom getDocumentoEniFile(
			@WebParam(name = "identificador") @XmlElement(required = true, name = "identificador") String identificador)
			throws InsideWSException;
	
	@WebMethod(operationName = "getExpedienteEniFile", action = "urn:getExpedienteEniFile")	
	@WebResult(name = "expedienteEniFile", partName = "expedienteEniFile",targetNamespace="https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e/expedienteEniFile")
	public TipoExpedienteEniFileInsideMtom getExpedienteEniFile(
			@WebParam(name = "identificador") @XmlElement(required = true, name = "identificador") String identificador,
			@WebParam(name = "visualizacionIndice") @XmlElement(required = true, name = "visualizacionIndice") Boolean visualizacionIndice)
			throws InsideWSException;
	
	@WebMethod(operationName = "altaExpedienteEni", action = "urn:altaExpedienteEni")
	@WebResult(name = "ExpedienteInsideInfo", partName = "ExpedienteInsideInfo",targetNamespace="https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e/expedienteInfo")
	public ExpedienteInsideInfo altaExpedienteEni(
			@WebParam(name = "expediente") @XmlElement(required = true, name = "expediente") TipoExpedienteMtom expedienteMtom,
			@WebParam(name = "metadatosAdicionales") @XmlElement(required = false, name = "metadatosAdicionales") TipoMetadatosAdicionales metadatosAdicionales)
			throws InsideWSException;
	
	@WebMethod(operationName = "modificaExpedienteEni", action = "urn:modificaExpedienteEni")
	@WebResult(name = "ExpedienteInsideInfo", partName = "ExpedienteInsideInfo",targetNamespace="https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e/expedienteInfo")
	public ExpedienteInsideInfo modificaExpedienteEni(
			@WebParam(name = "expediente") @XmlElement(required = true, name = "expediente") TipoExpedienteMtom expedienteMtom,
			@WebParam(name = "metadatosAdicionales") @XmlElement(required = false, name = "metadatosAdicionales") TipoMetadatosAdicionales metadatosAdicionales)
			throws InsideWSException;
	
	@WebMethod(operationName = "getExpedienteEni", action = "urn:getExpedienteEni")
	@WebResult(name = "expediente", partName = "expediente",targetNamespace="http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e")
	public TipoExpedienteMtom getExpedienteEni(
			@WebParam(name = "identificador") @XmlElement(required = true, name = "identificador") String identificador)
			throws InsideWSException;
	
	@WebMethod(operationName = "convertirExpedienteAEni", action = "urn:convertirExpedienteAEni")
	@WebResult(name = "expediente", partName = "expediente",targetNamespace="http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e")
	public TipoExpedienteMtom convertirExpedienteAEni (
			@WebParam(name = "expediente") @XmlElement(required = true, name = "expediente") TipoExpedienteConversionInside expediente,
			@WebParam(name = "contenidoFirma") @XmlElement(required = false, name = "contenidoFirma") String contenidoFirma) throws InsideWSException;
	
	@WebMethod(operationName = "visualizarDocumento", action = "urn:visualizarDocumento")
	@WebResult(name = "resultadoVisualizacion", partName = "resultadoVisualizacion",targetNamespace="https://ssweb.seap.minhap.es/Inside/XSD/v1.0/visualizacion/documento-e")
	public TipoResultadoVisualizacionDocumentoInsideMtom visualizarDocumento (
			@WebParam(name = "identificador") @XmlElement(required = true, name = "identificador") String identificador) throws InsideWSException;
	
	@WebMethod(operationName = "getExpediente", action = "urn:getExpediente")
	@WebResult(name = "expediente", partName = "expediente",targetNamespace="https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e")
	public TipoExpedienteInsideMtom getExpediente(
			@WebParam(name = "identificador") @XmlElement(required = true, name = "identificador") String identificador,
			@WebParam(name = "version") @XmlElement(name = "version") Integer version)
			throws InsideWSException;
	
	@WebMethod(operationName = "getDocumento", action = "urn:getDocumento")
	@WebResult(name = "documento", partName = "documento",targetNamespace="https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e")
	public TipoDocumentoInsideMtom getDocumento(
			@WebParam(name = "identificador") @XmlElement(required = true, name = "identificador") String identificador)
			throws InsideWSException;
	
	@WebMethod(operationName = "getDocumentoContenido", action = "urn:getDocumentoContenido")
	@WebResult(name = "documento", partName = "documento",targetNamespace="http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/contenido")
	public TipoContenidoMtom getDocumentoContenido(
			@WebParam(name = "identificador") @XmlElement(required = true, name = "identificador") String identificador)
			throws InsideWSException;

}
