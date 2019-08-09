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

import org.apache.cxf.annotations.GZIP;
import org.apache.cxf.annotations.Logging;
import org.apache.cxf.interceptor.OutFaultInterceptors;
import org.apache.cxf.interceptor.OutInterceptors;

import es.mpt.dsic.inside.ws.cxf.interceptor.InsideWsErrorInterceptor;
import es.mpt.dsic.inside.ws.cxf.interceptor.InsideWsIndiceExpedienteSignerInterceptor;
import es.mpt.dsic.inside.ws.exception.InsideWSException;
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

@WebService(name = "GInsideCertificateWebService", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebServiceFiles")
@Logging(limit=-1)
@GZIP
@OutInterceptors(interceptors = {}, classes = { InsideWsIndiceExpedienteSignerInterceptor.class })
@OutFaultInterceptors(interceptors = {}, classes = { InsideWsErrorInterceptor.class })
public interface GInsideCertificateWebService {

	@WebMethod(operationName = "convertirDocumentoAEni", action = "urn:convertirDocumentoAEni")
	@WebResult(name = "documento", partName = "documento", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/file")
	public DocumentoEniFileInside convertirDocumentoAEni(
			@WebParam(name = "documento") @XmlElement(required = true, name = "documento") TipoDocumentoConversionInside documento,
			@WebParam(name = "contenido") @XmlElement(required = false, name = "contenido") byte[] contenido,
			@WebParam(name = "firmar") @XmlElement(required = false, name = "firmar") Boolean firmar) throws InsideWSException;

	@WebMethod(operationName = "convertirDocumentoAEniConMAdicionales", action = "urn:convertirDocumentoAEniConMAdicionales")
	@WebResult(name = "documento", partName = "documento", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/file")
	public DocumentoEniFileInsideConMAdicionales convertirDocumentoAEniConMAdicionales(
			@WebParam(name = "documento") @XmlElement(required = true, name = "documento") TipoDocumentoConversionInside documento,
			@WebParam(name = "metadatosAdicionales") @XmlElement(required = false, name = "metadatosAdicionales") TipoMetadatosAdicionales metadatosAdicionales,
			@WebParam(name = "contenido") @XmlElement(required = false, name = "contenido") byte[] contenido,
			@WebParam(name = "firmar") @XmlElement(required = false, name = "firmar") Boolean firmar) throws InsideWSException;

	@WebMethod(operationName = "convertirExpedienteAEni", action = "urn:convertirExpedienteAEni")
	@WebResult(name = "expediente", partName = "expediente", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e/file")
	public ExpedienteEniFileInside convertirExpedienteAEni(
			@WebParam(name = "expediente") @XmlElement(required = true, name = "expediente") TipoExpedienteConversionInside expediente,
			@WebParam(name = "contenidoFirma") @XmlElement(required = false, name = "contenidoFirma") String contenidoFirma)
			throws InsideWSException;

	@WebMethod(operationName = "convertirExpedienteAEniConMAdicionales", action = "urn:convertirExpedienteAEniConMAdicionales")
	@WebResult(name = "expediente", partName = "expediente", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e/file")
	public ExpedienteEniFileInsideConMAdicionales convertirExpedienteAEniConMAdicionales(
			@WebParam(name = "expediente") @XmlElement(required = true, name = "expediente") TipoExpedienteConversionInside expediente,
			@WebParam(name = "metadatosAdicionales") @XmlElement(required = false, name = "metadatosAdicionales") TipoMetadatosAdicionales metadatosAdicionales,
			@WebParam(name = "contenidoFirma") @XmlElement(required = false, name = "contenidoFirma") String contenidoFirma)
			throws InsideWSException;


	@WebMethod(operationName = "convertirExpedienteAEniAutocontenido", action = "urn:convertirExpedienteAEniAutocontenido")
	@WebResult(name = "expediente", partName = "expediente", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e/file")
	public ExpedienteEniFileInside convertirExpedienteAEniAutocontenido(
			@WebParam(name = "expediente") @XmlElement(required = true, name = "expediente") TipoExpedienteConversionInsideWS expediente,
			@WebParam(name = "contenidoFirma") @XmlElement(required = false, name = "contenidoFirma") String contenidoFirma)
			throws InsideWSException;

	@WebMethod(operationName = "convertirExpedienteAEniConMAdicionalesAutocontenido", action = "urn:convertirExpedienteAEniConMAdicionalesAutocontenido")
	@WebResult(name = "expediente", partName = "expediente", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e/file")
	public ExpedienteEniFileInsideConMAdicionales convertirExpedienteAEniConMAdicionalesAutocontenido(
			@WebParam(name = "expediente") @XmlElement(required = true, name = "expediente") TipoExpedienteConversionInsideWS expediente,
			@WebParam(name = "metadatosAdicionales") @XmlElement(required = false, name = "metadatosAdicionales") TipoMetadatosAdicionales metadatosAdicionales,
			@WebParam(name = "contenidoFirma") @XmlElement(required = false, name = "contenidoFirma") String contenidoFirma)
			throws InsideWSException;
	
	@WebMethod(operationName = "validarExpedienteEniFile", action = "urn:validarExpedienteEniFile")
	@WebResult(name = "resultadoValidacion", partName = "resultadoValidacion", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/validacion")
	public TipoResultadoValidacionExpedienteInside validarExpedienteEniFile(
			@WebParam(name = "expediente") @XmlElement(required = true, name = "expediente") TipoExpedienteValidacionInside expediente)
			throws InsideWSException;

	@WebMethod(operationName = "validarDocumentoEniFile", action = "urn:validarDocumentoEniFile")
	@WebResult(name = "resultadoValidacion", partName = "resultadoValidacion", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/validacion")
	public TipoResultadoValidacionDocumentoInside validarDocumentoEniFile(
			@WebParam(name = "documento") @XmlElement(required = true, name = "documento") TipoDocumentoValidacionInside documento)
			throws InsideWSException;
	
	@WebMethod(operationName = "visualizarDocumentoEni", action = "urn:visualizarDocumentoEni")
	@WebResult(name = "resultadoVisualizacion", partName = "resultadoVisualizacion", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/visualizacion/documento-e")
	public TipoResultadoVisualizacionDocumentoInside visualizarDocumentoEni(
			@WebParam(name = "documento") @XmlElement(required = true, name = "documento") TipoDocumentoVisualizacionInside documento)
			throws InsideWSException;

}
