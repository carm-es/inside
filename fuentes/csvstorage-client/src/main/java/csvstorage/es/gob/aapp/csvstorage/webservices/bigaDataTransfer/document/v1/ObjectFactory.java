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


package csvstorage.es.gob.aapp.csvstorage.webservices.bigaDataTransfer.document.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the csvstorage.es.gob.aapp.csvstorage.webservices.bigaDataTransfer.document.v1
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

  private final static QName _GuardarDocumento_QNAME =
      new QName("urn:es:gob:aapp:csvstorage:webservices:document:v1.0", "guardarDocumento");
  private final static QName _ObtenerTamanioDocumentoResponse_QNAME = new QName(
      "urn:es:gob:aapp:csvstorage:webservices:document:v1.0", "obtenerTamanioDocumentoResponse");
  private final static QName _ObtenerTamanioDocumento_QNAME =
      new QName("urn:es:gob:aapp:csvstorage:webservices:document:v1.0", "obtenerTamanioDocumento");
  private final static QName _ObtenerHashDocumento_QNAME =
      new QName("urn:es:gob:aapp:csvstorage:webservices:document:v1.0", "obtenerHashDocumento");
  private final static QName _ModificarDocumento_QNAME =
      new QName("urn:es:gob:aapp:csvstorage:webservices:document:v1.0", "modificarDocumento");
  private final static QName _ObtenerDocumento_QNAME =
      new QName("urn:es:gob:aapp:csvstorage:webservices:document:v1.0", "obtenerDocumento");
  private final static QName _ObtenerInfoContenido_QNAME =
      new QName("urn:es:gob:aapp:csvstorage:webservices:document:v1.0", "obtenerInfoContenido");
  private final static QName _ObtenerHashDocumentoResponse_QNAME = new QName(
      "urn:es:gob:aapp:csvstorage:webservices:document:v1.0", "obtenerHashDocumentoResponse");

  /**
   * Create a new ObjectFactory that can be used to create new instances of schema derived classes
   * for package: csvstorage.es.gob.aapp.csvstorage.webservices.bigaDataTransfer.document.v1
   * 
   */
  public ObjectFactory() {}

  /**
   * Create an instance of {@link ObtenerInfoContenido }
   * 
   */
  public ObtenerInfoContenido createObtenerInfoContenido() {
    return new ObtenerInfoContenido();
  }

  /**
   * Create an instance of {@link ObtenerTamanioDocumentoResponse }
   * 
   */
  public ObtenerTamanioDocumentoResponse createObtenerTamanioDocumentoResponse() {
    return new ObtenerTamanioDocumentoResponse();
  }

  /**
   * Create an instance of {@link GuardarDocumento }
   * 
   */
  public GuardarDocumento createGuardarDocumento() {
    return new GuardarDocumento();
  }

  /**
   * Create an instance of {@link ObtenerHashDocumentoResponse }
   * 
   */
  public ObtenerHashDocumentoResponse createObtenerHashDocumentoResponse() {
    return new ObtenerHashDocumentoResponse();
  }

  /**
   * Create an instance of {@link ObtenerDocumento }
   * 
   */
  public ObtenerDocumento createObtenerDocumento() {
    return new ObtenerDocumento();
  }

  /**
   * Create an instance of {@link ObtenerTamanioDocumento }
   * 
   */
  public ObtenerTamanioDocumento createObtenerTamanioDocumento() {
    return new ObtenerTamanioDocumento();
  }

  /**
   * Create an instance of {@link ObtenerHashDocumento }
   * 
   */
  public ObtenerHashDocumento createObtenerHashDocumento() {
    return new ObtenerHashDocumento();
  }

  /**
   * Create an instance of {@link ModificarDocumento }
   * 
   */
  public ModificarDocumento createModificarDocumento() {
    return new ModificarDocumento();
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link GuardarDocumento }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "urn:es:gob:aapp:csvstorage:webservices:document:v1.0",
      name = "guardarDocumento")
  public JAXBElement<GuardarDocumento> createGuardarDocumento(GuardarDocumento value) {
    return new JAXBElement<GuardarDocumento>(_GuardarDocumento_QNAME, GuardarDocumento.class, null,
        value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerTamanioDocumentoResponse
   * }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "urn:es:gob:aapp:csvstorage:webservices:document:v1.0",
      name = "obtenerTamanioDocumentoResponse")
  public JAXBElement<ObtenerTamanioDocumentoResponse> createObtenerTamanioDocumentoResponse(
      ObtenerTamanioDocumentoResponse value) {
    return new JAXBElement<ObtenerTamanioDocumentoResponse>(_ObtenerTamanioDocumentoResponse_QNAME,
        ObtenerTamanioDocumentoResponse.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerTamanioDocumento }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "urn:es:gob:aapp:csvstorage:webservices:document:v1.0",
      name = "obtenerTamanioDocumento")
  public JAXBElement<ObtenerTamanioDocumento> createObtenerTamanioDocumento(
      ObtenerTamanioDocumento value) {
    return new JAXBElement<ObtenerTamanioDocumento>(_ObtenerTamanioDocumento_QNAME,
        ObtenerTamanioDocumento.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerHashDocumento }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "urn:es:gob:aapp:csvstorage:webservices:document:v1.0",
      name = "obtenerHashDocumento")
  public JAXBElement<ObtenerHashDocumento> createObtenerHashDocumento(ObtenerHashDocumento value) {
    return new JAXBElement<ObtenerHashDocumento>(_ObtenerHashDocumento_QNAME,
        ObtenerHashDocumento.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ModificarDocumento }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "urn:es:gob:aapp:csvstorage:webservices:document:v1.0",
      name = "modificarDocumento")
  public JAXBElement<ModificarDocumento> createModificarDocumento(ModificarDocumento value) {
    return new JAXBElement<ModificarDocumento>(_ModificarDocumento_QNAME, ModificarDocumento.class,
        null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerDocumento }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "urn:es:gob:aapp:csvstorage:webservices:document:v1.0",
      name = "obtenerDocumento")
  public JAXBElement<ObtenerDocumento> createObtenerDocumento(ObtenerDocumento value) {
    return new JAXBElement<ObtenerDocumento>(_ObtenerDocumento_QNAME, ObtenerDocumento.class, null,
        value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerInfoContenido }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "urn:es:gob:aapp:csvstorage:webservices:document:v1.0",
      name = "obtenerInfoContenido")
  public JAXBElement<ObtenerInfoContenido> createObtenerInfoContenido(ObtenerInfoContenido value) {
    return new JAXBElement<ObtenerInfoContenido>(_ObtenerInfoContenido_QNAME,
        ObtenerInfoContenido.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerHashDocumentoResponse
   * }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "urn:es:gob:aapp:csvstorage:webservices:document:v1.0",
      name = "obtenerHashDocumentoResponse")
  public JAXBElement<ObtenerHashDocumentoResponse> createObtenerHashDocumentoResponse(
      ObtenerHashDocumentoResponse value) {
    return new JAXBElement<ObtenerHashDocumentoResponse>(_ObtenerHashDocumentoResponse_QNAME,
        ObtenerHashDocumentoResponse.class, null, value);
  }

}
