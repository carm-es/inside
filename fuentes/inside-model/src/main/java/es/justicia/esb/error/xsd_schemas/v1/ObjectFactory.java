
package es.justicia.esb.error.xsd_schemas.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the es.justicia.esb.error.xsd_schemas.v1 package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the Java representation
 * for XML content. The Java representation of XML content can consist of schema derived interfaces
 * and classes representing the binding of schema type definitions, element declarations and model
 * groups. Factory methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

  private final static QName _ErrorEsb_QNAME =
      new QName("http://justicia.es/esb/error/xsd-schemas/V1", "ErrorEsb");

  /**
   * Create a new ObjectFactory that can be used to create new instances of schema derived classes
   * for package: es.justicia.esb.error.xsd_schemas.v1
   * 
   */
  public ObjectFactory() {}

  /**
   * Create an instance of {@link ErrorEsbType }
   * 
   */
  public ErrorEsbType createErrorEsbType() {
    return new ErrorEsbType();
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ErrorEsbType }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "http://justicia.es/esb/error/xsd-schemas/V1", name = "ErrorEsb")
  public JAXBElement<ErrorEsbType> createErrorEsb(ErrorEsbType value) {
    return new JAXBElement<ErrorEsbType>(_ErrorEsb_QNAME, ErrorEsbType.class, null, value);
  }

}
