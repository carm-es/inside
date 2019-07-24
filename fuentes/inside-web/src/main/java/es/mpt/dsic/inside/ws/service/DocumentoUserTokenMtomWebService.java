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
import es.mpt.dsic.inside.xml.inside.ExpedienteInsideInfo;
import es.mpt.dsic.inside.xml.inside.ws.documento.conversion.TipoDocumentoConversionInside;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoExpedienteConversionInside;
import es.mpt.dsic.inside.xml.inside.ws.objetoEniXMLBytes.ObjetoEniXMLBytes;



@WebService(name = "DocumentoUserTokenMtomWebService",
    targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService")

@Logging
@GZIP
@OutInterceptors(interceptors = {}, classes = {InsideWsIndiceExpedienteSignerInterceptor.class})
@OutFaultInterceptors(interceptors = {}, classes = {InsideWsErrorInterceptor.class})
@BindingType(SOAPBinding.SOAP11HTTP_MTOM_BINDING)
public interface DocumentoUserTokenMtomWebService {


  @WebMethod(operationName = "crearDocumentoEniFileXML", action = "urn:crearDocumentoEniFileXML")
  @WebResult(name = "documentoEniFileXML", partName = "documentoEniFileXML")
  public ObjetoEniXMLBytes crearDocumentoEniFileXML(
      @WebParam(name = "documento") @XmlElement(required = true,
          name = "expediente") TipoDocumentoConversionInside documento)
      throws InsideWSException;



  @WebMethod(operationName = "recuperarDocumentoEniFileXML",
      action = "urn:recuperarDocumentoEniFileXML")
  @WebResult(name = "documentoEniFileXML", partName = "documentoEniFileXML")
  public ObjetoEniXMLBytes recuperarDocumentoEniFileXML(
      @WebParam(name = "identificador") @XmlElement(required = true,
          name = "identificador") String documentoIdentificador)
      throws InsideWSException;


  @WebMethod(operationName = "insertarDocumentoEniFileXML",
      action = "urn:insertarDocumentoEniFileXML")
  @WebResult(name = "documentoEniFileXML", partName = "documentoEniFileXML")
  public ObjetoEniXMLBytes insertarDocumentoEniFileXML(
      @WebParam(name = "documento") @XmlElement(required = true,
          name = "documento") ObjetoEniXMLBytes documentoMtom)
      throws InsideWSException;


  // eliminardocumentoEniFileXML

  @WebMethod(operationName = "eliminarDocumentoEniFileXML",
      action = "urn:eliminarDocumentoEniFileXML")
  @WebResult(name = "documentoEniFileXML", partName = "documentoEniFileXML")
  public ObjetoEniXMLBytes eliminarDocumentoEniFileXML(@WebParam(name = "documento") @XmlElement(
      required = true, name = "documento") String documentoIdentificador) throws InsideWSException;



  // validardocumentoEniFileXML internamente se fija validar esquema, dir3, firma

  @WebMethod(operationName = "validarDocumentoEniFileXML",
      action = "urn:validarDocumentoEniFileXML")
  @WebResult(name = "resultadoValidacion", partName = "resultadoValidacion")
  public ExpedienteInsideInfo validarDocumentoEniFileXML(
      @WebParam(name = "documento") @XmlElement(required = true,
          name = "documento") ObjetoEniXMLBytes documentoMtom)
      throws InsideWSException;


  @WebMethod(operationName = "visualizarDocumento", action = "urn:visualizarDocumento")
  @WebResult(name = "expedienteEniFileXML", partName = "expedienteEniFileXML")
  public ObjetoEniXMLBytes visualizarDocumento(
      @WebParam(name = "identificador") @XmlElement(required = true,
          name = "identificador") String documentoIdentificador)
      throws InsideWSException;


}
