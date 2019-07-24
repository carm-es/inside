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


package csvstorage.es.gob.aapp.csvstorage.webservices.document.v1.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the csvstorage.es.gob.aapp.csvstorage.webservices.document.v1.model package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the Java representation
 * for XML content. The Java representation of XML content can consist of schema derived interfaces
 * and classes representing the binding of schema type definitions, element declarations and model
 * groups. Factory methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

  private final static QName _GuardarDocumentoEniResponse_QNAME = new QName(
      "urn:es:gob:aapp:csvstorage:webservices:document:model:v1.0", "guardarDocumentoEniResponse");
  private final static QName _ModificarDocumentoEniResponse_QNAME =
      new QName("urn:es:gob:aapp:csvstorage:webservices:document:model:v1.0",
          "modificarDocumentoEniResponse");
  private final static QName _EliminarDocumentoResponse_QNAME = new QName(
      "urn:es:gob:aapp:csvstorage:webservices:document:model:v1.0", "eliminarDocumentoResponse");
  private final static QName _ModificarDocumentoResponse_QNAME = new QName(
      "urn:es:gob:aapp:csvstorage:webservices:document:model:v1.0", "modificarDocumentoResponse");
  private final static QName _ObtenerDocumentoResponse_QNAME = new QName(
      "urn:es:gob:aapp:csvstorage:webservices:document:model:v1.0", "obtenerDocumentoResponse");
  private final static QName _ConsultarDocumentoResponse_QNAME = new QName(
      "urn:es:gob:aapp:csvstorage:webservices:document:model:v1.0", "consultarDocumentoResponse");
  private final static QName _ErrorInfo_QNAME =
      new QName("urn:es:gob:aapp:csvstorage:webservices:document:model:v1.0", "errorInfo");
  private final static QName _ObtenerDocumentoEniResponse_QNAME = new QName(
      "urn:es:gob:aapp:csvstorage:webservices:document:model:v1.0", "obtenerDocumentoEniResponse");
  private final static QName _CSVStorageException_QNAME = new QName(
      "urn:es:gob:aapp:csvstorage:webservices:document:model:v1.0", "CSVStorageException");
  private final static QName _GuardarDocumentoResponse_QNAME = new QName(
      "urn:es:gob:aapp:csvstorage:webservices:document:model:v1.0", "guardarDocumentoResponse");

  /**
   * Create a new ObjectFactory that can be used to create new instances of schema derived classes
   * for package: csvstorage.es.gob.aapp.csvstorage.webservices.document.v1.model
   * 
   */
  public ObjectFactory() {}

  /**
   * Create an instance of {@link GuardarDocumentoResponse }
   * 
   */
  public GuardarDocumentoResponse createGuardarDocumentoResponse() {
    return new GuardarDocumentoResponse();
  }

  /**
   * Create an instance of {@link EliminarDocumentoResponse }
   * 
   */
  public EliminarDocumentoResponse createEliminarDocumentoResponse() {
    return new EliminarDocumentoResponse();
  }

  /**
   * Create an instance of {@link CSVStorageException }
   * 
   */
  public CSVStorageException createCSVStorageException() {
    return new CSVStorageException();
  }

  /**
   * Create an instance of {@link GuardarDocumentoEniResponse }
   * 
   */
  public GuardarDocumentoEniResponse createGuardarDocumentoEniResponse() {
    return new GuardarDocumentoEniResponse();
  }

  /**
   * Create an instance of {@link ConsultarDocumentoResponse }
   * 
   */
  public ConsultarDocumentoResponse createConsultarDocumentoResponse() {
    return new ConsultarDocumentoResponse();
  }

  /**
   * Create an instance of {@link ObtenerDocumentoEniResponse }
   * 
   */
  public ObtenerDocumentoEniResponse createObtenerDocumentoEniResponse() {
    return new ObtenerDocumentoEniResponse();
  }

  /**
   * Create an instance of {@link ObtenerDocumentoResponse }
   * 
   */
  public ObtenerDocumentoResponse createObtenerDocumentoResponse() {
    return new ObtenerDocumentoResponse();
  }

  /**
   * Create an instance of {@link WSCredential }
   * 
   */
  public WSCredential createWSCredential() {
    return new WSCredential();
  }

  /**
   * Create an instance of {@link ConsultarDocumentoRequest }
   * 
   */
  public ConsultarDocumentoRequest createConsultarDocumentoRequest() {
    return new ConsultarDocumentoRequest();
  }

  /**
   * Create an instance of {@link ObtenerDocumentoRequest }
   * 
   */
  public ObtenerDocumentoRequest createObtenerDocumentoRequest() {
    return new ObtenerDocumentoRequest();
  }

  /**
   * Create an instance of {@link GuardarDocumentoRequest }
   * 
   */
  public GuardarDocumentoRequest createGuardarDocumentoRequest() {
    return new GuardarDocumentoRequest();
  }

  /**
   * Create an instance of {@link GuardarDocumentoEniRequest }
   * 
   */
  public GuardarDocumentoEniRequest createGuardarDocumentoEniRequest() {
    return new GuardarDocumentoEniRequest();
  }

  /**
   * Create an instance of {@link DocumentoResponse }
   * 
   */
  public DocumentoResponse createDocumentoResponse() {
    return new DocumentoResponse();
  }

  /**
   * Create an instance of {@link EliminarDocumentoRequest }
   * 
   */
  public EliminarDocumentoRequest createEliminarDocumentoRequest() {
    return new EliminarDocumentoRequest();
  }

  /**
   * Create an instance of {@link Response }
   * 
   */
  public Response createResponse() {
    return new Response();
  }

  /**
   * Create an instance of {@link ObtenerDocumentoEniRequest }
   * 
   */
  public ObtenerDocumentoEniRequest createObtenerDocumentoEniRequest() {
    return new ObtenerDocumentoEniRequest();
  }

  /**
   * Create an instance of {@link DocumentoEniResponse }
   * 
   */
  public DocumentoEniResponse createDocumentoEniResponse() {
    return new DocumentoEniResponse();
  }

  /**
   * Create an instance of {@link ContenidoInfo }
   * 
   */
  public ContenidoInfo createContenidoInfo() {
    return new ContenidoInfo();
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link GuardarDocumentoEniResponse
   * }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "urn:es:gob:aapp:csvstorage:webservices:document:model:v1.0",
      name = "guardarDocumentoEniResponse")
  public JAXBElement<GuardarDocumentoEniResponse> createGuardarDocumentoEniResponse(
      GuardarDocumentoEniResponse value) {
    return new JAXBElement<GuardarDocumentoEniResponse>(_GuardarDocumentoEniResponse_QNAME,
        GuardarDocumentoEniResponse.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link GuardarDocumentoEniResponse
   * }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "urn:es:gob:aapp:csvstorage:webservices:document:model:v1.0",
      name = "modificarDocumentoEniResponse")
  public JAXBElement<GuardarDocumentoEniResponse> createModificarDocumentoEniResponse(
      GuardarDocumentoEniResponse value) {
    return new JAXBElement<GuardarDocumentoEniResponse>(_ModificarDocumentoEniResponse_QNAME,
        GuardarDocumentoEniResponse.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link EliminarDocumentoResponse }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "urn:es:gob:aapp:csvstorage:webservices:document:model:v1.0",
      name = "eliminarDocumentoResponse")
  public JAXBElement<EliminarDocumentoResponse> createEliminarDocumentoResponse(
      EliminarDocumentoResponse value) {
    return new JAXBElement<EliminarDocumentoResponse>(_EliminarDocumentoResponse_QNAME,
        EliminarDocumentoResponse.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link GuardarDocumentoResponse }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "urn:es:gob:aapp:csvstorage:webservices:document:model:v1.0",
      name = "modificarDocumentoResponse")
  public JAXBElement<GuardarDocumentoResponse> createModificarDocumentoResponse(
      GuardarDocumentoResponse value) {
    return new JAXBElement<GuardarDocumentoResponse>(_ModificarDocumentoResponse_QNAME,
        GuardarDocumentoResponse.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerDocumentoResponse }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "urn:es:gob:aapp:csvstorage:webservices:document:model:v1.0",
      name = "obtenerDocumentoResponse")
  public JAXBElement<ObtenerDocumentoResponse> createObtenerDocumentoResponse(
      ObtenerDocumentoResponse value) {
    return new JAXBElement<ObtenerDocumentoResponse>(_ObtenerDocumentoResponse_QNAME,
        ObtenerDocumentoResponse.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarDocumentoResponse
   * }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "urn:es:gob:aapp:csvstorage:webservices:document:model:v1.0",
      name = "consultarDocumentoResponse")
  public JAXBElement<ConsultarDocumentoResponse> createConsultarDocumentoResponse(
      ConsultarDocumentoResponse value) {
    return new JAXBElement<ConsultarDocumentoResponse>(_ConsultarDocumentoResponse_QNAME,
        ConsultarDocumentoResponse.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "urn:es:gob:aapp:csvstorage:webservices:document:model:v1.0",
      name = "errorInfo")
  public JAXBElement<Object> createErrorInfo(Object value) {
    return new JAXBElement<Object>(_ErrorInfo_QNAME, Object.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerDocumentoEniResponse
   * }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "urn:es:gob:aapp:csvstorage:webservices:document:model:v1.0",
      name = "obtenerDocumentoEniResponse")
  public JAXBElement<ObtenerDocumentoEniResponse> createObtenerDocumentoEniResponse(
      ObtenerDocumentoEniResponse value) {
    return new JAXBElement<ObtenerDocumentoEniResponse>(_ObtenerDocumentoEniResponse_QNAME,
        ObtenerDocumentoEniResponse.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link CSVStorageException }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "urn:es:gob:aapp:csvstorage:webservices:document:model:v1.0",
      name = "CSVStorageException")
  public JAXBElement<CSVStorageException> createCSVStorageException(CSVStorageException value) {
    return new JAXBElement<CSVStorageException>(_CSVStorageException_QNAME,
        CSVStorageException.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link GuardarDocumentoResponse }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "urn:es:gob:aapp:csvstorage:webservices:document:model:v1.0",
      name = "guardarDocumentoResponse")
  public JAXBElement<GuardarDocumentoResponse> createGuardarDocumentoResponse(
      GuardarDocumentoResponse value) {
    return new JAXBElement<GuardarDocumentoResponse>(_GuardarDocumentoResponse_QNAME,
        GuardarDocumentoResponse.class, null, value);
  }

}
