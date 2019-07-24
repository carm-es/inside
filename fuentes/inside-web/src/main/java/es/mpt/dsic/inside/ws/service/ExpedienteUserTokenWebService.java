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
import org.apache.cxf.annotations.GZIP;
import org.apache.cxf.annotations.Logging;
import org.apache.cxf.interceptor.OutFaultInterceptors;
import org.apache.cxf.interceptor.OutInterceptors;
import es.mpt.dsic.inside.ws.cxf.interceptor.InsideWsErrorInterceptor;
import es.mpt.dsic.inside.ws.cxf.interceptor.InsideWsIndiceExpedienteSignerInterceptor;
import es.mpt.dsic.inside.ws.exception.InsideWSException;
import es.mpt.dsic.inside.xml.inside.ExpedienteInsideInfo;
import es.mpt.dsic.inside.xml.inside.TipoMetadatosAdicionales;
import es.mpt.dsic.inside.xml.inside.ws.expediente.TipoExpedienteEniFileInside;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoExpedienteConversionInside;
import es.mpt.dsic.inside.xml.inside.ws.objetoEniXMLBytes.ObjetoEniXMLBytes;
import es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.resultados.TipoResultadoValidacionExpedienteInside;



@WebService(name = "ExpedienteUserTokenWebService",
    targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService")

@Logging
@GZIP
@OutInterceptors(interceptors = {}, classes = {InsideWsIndiceExpedienteSignerInterceptor.class})
@OutFaultInterceptors(interceptors = {}, classes = {InsideWsErrorInterceptor.class})
public interface ExpedienteUserTokenWebService {


  @WebMethod(operationName = "crearExpedienteEniFileXML", action = "urn:crearExpedienteEniFileXML")
  @WebResult(name = "expedienteEniFileXML", partName = "expedienteEniFileXML",
      targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/objetoEniXMLBytes")
  public ObjetoEniXMLBytes crearExpedienteEniFileXML(
      @WebParam(name = "expediente") @XmlElement(required = true,
          name = "expediente") TipoExpedienteConversionInside expediente,
      @WebParam(name = "metadatosAdicionales") @XmlElement(required = false,
          name = "metadatosAdicionales") TipoMetadatosAdicionales metadatosAdicionales)
      throws InsideWSException;



  @WebMethod(operationName = "recuperarExpedienteEniFileXML",
      action = "urn:recuperarExpedienteEniFileXML")
  @WebResult(name = "expedienteEniFileXML", partName = "expedienteEniFileXML",
      targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/objetoEniXMLBytes")
  public ObjetoEniXMLBytes recuperarExpedienteEniFileXML(
      @WebParam(name = "identificador") @XmlElement(required = true,
          name = "identificador") String expedienteIdentificador)
      throws InsideWSException;


  @WebMethod(operationName = "insertarExpedienteEniFileXML",
      action = "urn:insertarExpedienteEniFileXML")
  @WebResult(name = "expedienteEniFileXML", partName = "expedienteEniFileXML",
      targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/objetoEniXMLBytes")
  public ObjetoEniXMLBytes insertarExpedienteEniFileXML(
      @WebParam(name = "expediente") @XmlElement(required = true,
          name = "expediente") TipoExpedienteEniFileInside expedienteEniFile)
      throws InsideWSException;


  // eliminarExpedienteEniFileXML

  @WebMethod(operationName = "eliminarExpedienteEniFileXML",
      action = "urn:eliminarExpedienteEniFileXML")
  @WebResult(name = "expedienteEniFileXML", partName = "expedienteEniFileXML",
      targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/objetoEniXMLBytes")
  public ObjetoEniXMLBytes eliminarExpedienteEniFileXML(
      @WebParam(name = "expediente") @XmlElement(required = true,
          name = "expediente") String expedienteIdentificador)
      throws InsideWSException;



  // validarExpedienteEniFileXML internamente se fija validar esquema, dir3, firma

  @WebMethod(operationName = "validarExpedienteEniFileXML",
      action = "urn:validarExpedienteEniFileXML")
  @WebResult(name = "resultadoValidacion", partName = "resultadoValidacion",
      targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/validacion")
  public TipoResultadoValidacionExpedienteInside validarExpedienteEniFileXML(
      @WebParam(name = "expediente") @XmlElement(required = true,
          name = "expediente") TipoExpedienteEniFileInside expedienteEniFile)
      throws InsideWSException;



}
