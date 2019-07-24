
package es.mpt.dsic.inside.xml.inside.ws.error;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the es.mpt.dsic.inside.xml.inside.ws.error package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the Java representation
 * for XML content. The Java representation of XML content can consist of schema derived interfaces
 * and classes representing the binding of schema type definitions, element declarations and model
 * groups. Factory methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

  private final static QName _ErrorInside_QNAME = new QName(
      "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService/types/error", "errorInside");

  /**
   * Create a new ObjectFactory that can be used to create new instances of schema derived classes
   * for package: es.mpt.dsic.inside.xml.inside.ws.error
   * 
   */
  public ObjectFactory() {}

  /**
   * Create an instance of {@link WSErrorInside }
   * 
   */
  public WSErrorInside createWSErrorInside() {
    return new WSErrorInside();
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link WSErrorInside }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService/types/error",
      name = "errorInside")
  public JAXBElement<WSErrorInside> createErrorInside(WSErrorInside value) {
    return new JAXBElement<WSErrorInside>(_ErrorInside_QNAME, WSErrorInside.class, null, value);
  }

}
