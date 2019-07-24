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


package csvstorage.es.gob.aapp.csvstorage.webservices.bigaDataTransfer.document.v1.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the csvstorage.es.gob.aapp.csvstorage.webservices.bigaDataTransfer.document.v1.model
 * package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the Java representation
 * for XML content. The Java representation of XML content can consist of schema derived interfaces
 * and classes representing the binding of schema type definitions, element declarations and model
 * groups. Factory methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

  private final static QName _GuardarDocumentoUuidResponse_QNAME = new QName(
      "urn:es:gob:aapp:csvstorage:webservices:document:model:v1.0", "guardarDocumentoUuidResponse");
  private final static QName _CSVStorageException_QNAME = new QName(
      "urn:es:gob:aapp:csvstorage:webservices:document:model:v1.0", "CSVStorageException");
  private final static QName _ModificarDocumentoResponse_QNAME = new QName(
      "urn:es:gob:aapp:csvstorage:webservices:document:model:v1.0", "modificarDocumentoResponse");
  private final static QName _ObtenerDocumentoResponse_QNAME = new QName(
      "urn:es:gob:aapp:csvstorage:webservices:document:model:v1.0", "obtenerDocumentoResponse");
  private final static QName _ObtenerInfoContenidoResponse_QNAME = new QName(
      "urn:es:gob:aapp:csvstorage:webservices:document:model:v1.0", "obtenerInfoContenidoResponse");
  private final static QName _ErrorInfo_QNAME =
      new QName("urn:es:gob:aapp:csvstorage:webservices:document:model:v1.0", "errorInfo");

  /**
   * Create a new ObjectFactory that can be used to create new instances of schema derived classes
   * for package: csvstorage.es.gob.aapp.csvstorage.webservices.bigaDataTransfer.document.v1.model
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
   * Create an instance of {@link CSVStorageException }
   * 
   */
  public CSVStorageException createCSVStorageException() {
    return new CSVStorageException();
  }

  /**
   * Create an instance of {@link ObtenerInfoContenidoResponse }
   * 
   */
  public ObtenerInfoContenidoResponse createObtenerInfoContenidoResponse() {
    return new ObtenerInfoContenidoResponse();
  }

  /**
   * Create an instance of {@link GuardarDocumentoUuidResponse }
   * 
   */
  public GuardarDocumentoUuidResponse createGuardarDocumentoUuidResponse() {
    return new GuardarDocumentoUuidResponse();
  }

  /**
   * Create an instance of {@link ObtenerDocumentoBigDataResponse }
   * 
   */
  public ObtenerDocumentoBigDataResponse createObtenerDocumentoBigDataResponse() {
    return new ObtenerDocumentoBigDataResponse();
  }

  /**
   * Create an instance of {@link ConsultarDocumentoResponse }
   * 
   */
  public ConsultarDocumentoResponse createConsultarDocumentoResponse() {
    return new ConsultarDocumentoResponse();
  }

  /**
   * Create an instance of {@link ContenidoUuidInfo }
   * 
   */
  public ContenidoUuidInfo createContenidoUuidInfo() {
    return new ContenidoUuidInfo();
  }

  /**
   * Create an instance of {@link ResponseUuid }
   * 
   */
  public ResponseUuid createResponseUuid() {
    return new ResponseUuid();
  }

  /**
   * Create an instance of {@link WSCredential }
   * 
   */
  public WSCredential createWSCredential() {
    return new WSCredential();
  }

  /**
   * Create an instance of {@link DocumentoRequest }
   * 
   */
  public DocumentoRequest createDocumentoRequest() {
    return new DocumentoRequest();
  }

  /**
   * Create an instance of {@link ObtenerDocumentoRequest }
   * 
   */
  public ObtenerDocumentoRequest createObtenerDocumentoRequest() {
    return new ObtenerDocumentoRequest();
  }

  /**
   * Create an instance of {@link ContenidoInfoBigData }
   * 
   */
  public ContenidoInfoBigData createContenidoInfoBigData() {
    return new ContenidoInfoBigData();
  }

  /**
   * Create an instance of {@link Response }
   * 
   */
  public Response createResponse() {
    return new Response();
  }

  /**
   * Create an instance of {@link Nifs }
   * 
   */
  public Nifs createNifs() {
    return new Nifs();
  }

  /**
   * Create an instance of {@link DocumentoBigDataResponse }
   * 
   */
  public DocumentoBigDataResponse createDocumentoBigDataResponse() {
    return new DocumentoBigDataResponse();
  }

  /**
   * Create an instance of {@link GuardarDocumentoBigDataRequest }
   * 
   */
  public GuardarDocumentoBigDataRequest createGuardarDocumentoBigDataRequest() {
    return new GuardarDocumentoBigDataRequest();
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link GuardarDocumentoUuidResponse
   * }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "urn:es:gob:aapp:csvstorage:webservices:document:model:v1.0",
      name = "guardarDocumentoUuidResponse")
  public JAXBElement<GuardarDocumentoUuidResponse> createGuardarDocumentoUuidResponse(
      GuardarDocumentoUuidResponse value) {
    return new JAXBElement<GuardarDocumentoUuidResponse>(_GuardarDocumentoUuidResponse_QNAME,
        GuardarDocumentoUuidResponse.class, null, value);
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
      name = "modificarDocumentoResponse")
  public JAXBElement<GuardarDocumentoResponse> createModificarDocumentoResponse(
      GuardarDocumentoResponse value) {
    return new JAXBElement<GuardarDocumentoResponse>(_ModificarDocumentoResponse_QNAME,
        GuardarDocumentoResponse.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerDocumentoBigDataResponse
   * }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "urn:es:gob:aapp:csvstorage:webservices:document:model:v1.0",
      name = "obtenerDocumentoResponse")
  public JAXBElement<ObtenerDocumentoBigDataResponse> createObtenerDocumentoResponse(
      ObtenerDocumentoBigDataResponse value) {
    return new JAXBElement<ObtenerDocumentoBigDataResponse>(_ObtenerDocumentoResponse_QNAME,
        ObtenerDocumentoBigDataResponse.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerInfoContenidoResponse
   * }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "urn:es:gob:aapp:csvstorage:webservices:document:model:v1.0",
      name = "obtenerInfoContenidoResponse")
  public JAXBElement<ObtenerInfoContenidoResponse> createObtenerInfoContenidoResponse(
      ObtenerInfoContenidoResponse value) {
    return new JAXBElement<ObtenerInfoContenidoResponse>(_ObtenerInfoContenidoResponse_QNAME,
        ObtenerInfoContenidoResponse.class, null, value);
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

}
