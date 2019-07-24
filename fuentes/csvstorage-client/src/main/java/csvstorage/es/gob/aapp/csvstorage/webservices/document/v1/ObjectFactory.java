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


package csvstorage.es.gob.aapp.csvstorage.webservices.document.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the csvstorage.es.gob.aapp.csvstorage.webservices.document.v1 package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the Java representation
 * for XML content. The Java representation of XML content can consist of schema derived interfaces
 * and classes representing the binding of schema type definitions, element declarations and model
 * groups. Factory methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

  private final static QName _EliminarDocumento_QNAME =
      new QName("urn:es:gob:aapp:csvstorage:webservices:document:v1.0", "eliminarDocumento");
  private final static QName _ObtenerDocumentoENI_QNAME =
      new QName("urn:es:gob:aapp:csvstorage:webservices:document:v1.0", "obtenerDocumentoENI");
  private final static QName _ConsultarDocumento_QNAME =
      new QName("urn:es:gob:aapp:csvstorage:webservices:document:v1.0", "consultarDocumento");
  private final static QName _ObtenerDocumento_QNAME =
      new QName("urn:es:gob:aapp:csvstorage:webservices:document:v1.0", "obtenerDocumento");
  private final static QName _GuardarDocumentoENI_QNAME =
      new QName("urn:es:gob:aapp:csvstorage:webservices:document:v1.0", "guardarDocumentoENI");
  private final static QName _ModificarDocumentoENI_QNAME =
      new QName("urn:es:gob:aapp:csvstorage:webservices:document:v1.0", "modificarDocumentoENI");
  private final static QName _GuardarDocumento_QNAME =
      new QName("urn:es:gob:aapp:csvstorage:webservices:document:v1.0", "guardarDocumento");
  private final static QName _ModificarDocumento_QNAME =
      new QName("urn:es:gob:aapp:csvstorage:webservices:document:v1.0", "modificarDocumento");

  /**
   * Create a new ObjectFactory that can be used to create new instances of schema derived classes
   * for package: csvstorage.es.gob.aapp.csvstorage.webservices.document.v1
   * 
   */
  public ObjectFactory() {}

  /**
   * Create an instance of {@link ModificarDocumentoENI }
   * 
   */
  public ModificarDocumentoENI createModificarDocumentoENI() {
    return new ModificarDocumentoENI();
  }

  /**
   * Create an instance of {@link EliminarDocumento }
   * 
   */
  public EliminarDocumento createEliminarDocumento() {
    return new EliminarDocumento();
  }

  /**
   * Create an instance of {@link GuardarDocumento }
   * 
   */
  public GuardarDocumento createGuardarDocumento() {
    return new GuardarDocumento();
  }

  /**
   * Create an instance of {@link ObtenerDocumentoENI }
   * 
   */
  public ObtenerDocumentoENI createObtenerDocumentoENI() {
    return new ObtenerDocumentoENI();
  }

  /**
   * Create an instance of {@link GuardarDocumentoENI }
   * 
   */
  public GuardarDocumentoENI createGuardarDocumentoENI() {
    return new GuardarDocumentoENI();
  }

  /**
   * Create an instance of {@link ObtenerDocumento }
   * 
   */
  public ObtenerDocumento createObtenerDocumento() {
    return new ObtenerDocumento();
  }

  /**
   * Create an instance of {@link ModificarDocumento }
   * 
   */
  public ModificarDocumento createModificarDocumento() {
    return new ModificarDocumento();
  }

  /**
   * Create an instance of {@link ConsultarDocumento }
   * 
   */
  public ConsultarDocumento createConsultarDocumento() {
    return new ConsultarDocumento();
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link EliminarDocumento }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "urn:es:gob:aapp:csvstorage:webservices:document:v1.0",
      name = "eliminarDocumento")
  public JAXBElement<EliminarDocumento> createEliminarDocumento(EliminarDocumento value) {
    return new JAXBElement<EliminarDocumento>(_EliminarDocumento_QNAME, EliminarDocumento.class,
        null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerDocumentoENI }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "urn:es:gob:aapp:csvstorage:webservices:document:v1.0",
      name = "obtenerDocumentoENI")
  public JAXBElement<ObtenerDocumentoENI> createObtenerDocumentoENI(ObtenerDocumentoENI value) {
    return new JAXBElement<ObtenerDocumentoENI>(_ObtenerDocumentoENI_QNAME,
        ObtenerDocumentoENI.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarDocumento }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "urn:es:gob:aapp:csvstorage:webservices:document:v1.0",
      name = "consultarDocumento")
  public JAXBElement<ConsultarDocumento> createConsultarDocumento(ConsultarDocumento value) {
    return new JAXBElement<ConsultarDocumento>(_ConsultarDocumento_QNAME, ConsultarDocumento.class,
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
   * Create an instance of {@link JAXBElement }{@code <}{@link GuardarDocumentoENI }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "urn:es:gob:aapp:csvstorage:webservices:document:v1.0",
      name = "guardarDocumentoENI")
  public JAXBElement<GuardarDocumentoENI> createGuardarDocumentoENI(GuardarDocumentoENI value) {
    return new JAXBElement<GuardarDocumentoENI>(_GuardarDocumentoENI_QNAME,
        GuardarDocumentoENI.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ModificarDocumentoENI }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "urn:es:gob:aapp:csvstorage:webservices:document:v1.0",
      name = "modificarDocumentoENI")
  public JAXBElement<ModificarDocumentoENI> createModificarDocumentoENI(
      ModificarDocumentoENI value) {
    return new JAXBElement<ModificarDocumentoENI>(_ModificarDocumentoENI_QNAME,
        ModificarDocumentoENI.class, null, value);
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
   * Create an instance of {@link JAXBElement }{@code <}{@link ModificarDocumento }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "urn:es:gob:aapp:csvstorage:webservices:document:v1.0",
      name = "modificarDocumento")
  public JAXBElement<ModificarDocumento> createModificarDocumento(ModificarDocumento value) {
    return new JAXBElement<ModificarDocumento>(_ModificarDocumento_QNAME, ModificarDocumento.class,
        null, value);
  }

}
