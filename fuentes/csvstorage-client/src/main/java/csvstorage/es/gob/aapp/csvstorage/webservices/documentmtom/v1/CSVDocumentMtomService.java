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

package csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.0.1 2016-01-14T11:39:44.146+01:00 Generated source
 * version: 3.0.1
 * 
 */
@WebService(targetNamespace = "urn:es:gob:aapp:csvstorage:webservices:documentmtom:v1.0",
    name = "CSVDocumentMtomService")
@XmlSeeAlso({
    csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.model.documento.ObjectFactory.class,
    csvstorage.es.mpt.dsic.csvstorage.credential.ObjectFactory.class,
    csvstorage.es.mpt.dsic.csvstorage.firma.ObjectFactory.class,
    csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.model.ObjectFactory.class,
    csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.model.contenido.ObjectFactory.class,
    csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.model.metadatos.ObjectFactory.class,
    ObjectFactory.class})
public interface CSVDocumentMtomService {

  @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
  @WebResult(name = "guardarDocumentoResponse",
      targetNamespace = "urn:es:gob:aapp:csvstorage:webservices:document:model:v1.0",
      partName = "parameters")
  @WebMethod(action = "urn:guardarDocumento")
  public csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.model.GuardarDocumentoResponse guardarDocumento(
      @WebParam(partName = "parameters", name = "guardarDocumento",
          targetNamespace = "urn:es:gob:aapp:csvstorage:webservices:documentmtom:v1.0") GuardarDocumento parameters)
      throws CSVStorageException;

  @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
  @WebResult(name = "modificarDocumentoEniResponse",
      targetNamespace = "urn:es:gob:aapp:csvstorage:webservices:document:model:v1.0",
      partName = "parameters")
  @WebMethod(action = "urn:modificarDocumentoENI")
  public csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.model.GuardarDocumentoEniResponse modificarDocumentoENI(
      @WebParam(partName = "parameters", name = "modificarDocumentoENI",
          targetNamespace = "urn:es:gob:aapp:csvstorage:webservices:documentmtom:v1.0") ModificarDocumentoENI parameters)
      throws CSVStorageException;

  @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
  @WebResult(name = "guardarDocumentoEniResponse",
      targetNamespace = "urn:es:gob:aapp:csvstorage:webservices:document:model:v1.0",
      partName = "parameters")
  @WebMethod(action = "urn:guardarDocumentoENI")
  public csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.model.GuardarDocumentoEniResponse guardarDocumentoENI(
      @WebParam(partName = "parameters", name = "guardarDocumentoENI",
          targetNamespace = "urn:es:gob:aapp:csvstorage:webservices:documentmtom:v1.0") GuardarDocumentoENI parameters)
      throws CSVStorageException;

  @WebResult(name = "documentoEniResponse", targetNamespace = "")
  @RequestWrapper(localName = "obtenerDocumentoENI",
      targetNamespace = "urn:es:gob:aapp:csvstorage:webservices:documentmtom:v1.0",
      className = "csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.ObtenerDocumentoENI")
  @WebMethod(action = "urn:obtenerDocumentoENI")
  @ResponseWrapper(localName = "obtenerDocumentoEniResponse",
      targetNamespace = "urn:es:gob:aapp:csvstorage:webservices:document:model:v1.0",
      className = "csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.model.ObtenerDocumentoEniResponse")
  public csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.model.DocumentoEniMtomResponse obtenerDocumentoENI(
      @WebParam(name = "credential",
          targetNamespace = "") csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.model.WSCredential credential,
      @WebParam(name = "obtenerDocumentoEniRequest",
          targetNamespace = "") csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.model.ObtenerDocumentoEniRequest obtenerDocumentoEniRequest)
      throws CSVStorageException;

  @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
  @WebResult(name = "consultarDocumentoResponse",
      targetNamespace = "urn:es:gob:aapp:csvstorage:webservices:document:model:v1.0",
      partName = "parameters")
  @WebMethod(action = "urn:consultarDocumento")
  public csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.model.ConsultarDocumentoResponse consultarDocumento(
      @WebParam(partName = "parameters", name = "consultarDocumento",
          targetNamespace = "urn:es:gob:aapp:csvstorage:webservices:documentmtom:v1.0") ConsultarDocumento parameters)
      throws CSVStorageException;

  @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
  @WebResult(name = "modificarDocumentoResponse",
      targetNamespace = "urn:es:gob:aapp:csvstorage:webservices:document:model:v1.0",
      partName = "parameters")
  @WebMethod(action = "urn:modificarDocumento")
  public csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.model.GuardarDocumentoResponse modificarDocumento(
      @WebParam(partName = "parameters", name = "modificarDocumento",
          targetNamespace = "urn:es:gob:aapp:csvstorage:webservices:documentmtom:v1.0") ModificarDocumento parameters)
      throws CSVStorageException;

  @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
  @WebResult(name = "eliminarDocumentoResponse",
      targetNamespace = "urn:es:gob:aapp:csvstorage:webservices:document:model:v1.0",
      partName = "parameters")
  @WebMethod(action = "urn:eliminarDocumento")
  public csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.model.EliminarDocumentoResponse eliminarDocumento(
      @WebParam(partName = "parameters", name = "eliminarDocumento",
          targetNamespace = "urn:es:gob:aapp:csvstorage:webservices:documentmtom:v1.0") EliminarDocumento parameters)
      throws CSVStorageException;

  @WebResult(name = "documentoResponse", targetNamespace = "")
  @RequestWrapper(localName = "obtenerDocumento",
      targetNamespace = "urn:es:gob:aapp:csvstorage:webservices:documentmtom:v1.0",
      className = "csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.ObtenerDocumento")
  @WebMethod(action = "urn:obtenerDocumento")
  @ResponseWrapper(localName = "obtenerDocumentoMtomResponse",
      targetNamespace = "urn:es:gob:aapp:csvstorage:webservices:document:model:v1.0",
      className = "csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.model.ObtenerDocumentoMtomResponse")
  public csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.model.DocumentoMtomResponse obtenerDocumento(
      @WebParam(name = "credential",
          targetNamespace = "") csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.model.WSCredential credential,
      @WebParam(name = "obtenerDocumentoRequest",
          targetNamespace = "") csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.model.ObtenerDocumentoRequest obtenerDocumentoRequest)
      throws CSVStorageException;

  @WebResult(name = "documentoTamanioResponse", targetNamespace = "")
  @WebMethod(operationName = "obtenerTamanioDocumento", action = "urn:obtenerTamanioDocumento")
  public Long obtenerTamanioDocumento(@WebParam(name = "credential",
      targetNamespace = "") csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.model.WSCredential credential,
      @WebParam(name = "obtenerDocumentoRequest",
          targetNamespace = "") csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.model.ObtenerDocumentoRequest obtenerDocumentoRequest)
      throws CSVStorageException;
}