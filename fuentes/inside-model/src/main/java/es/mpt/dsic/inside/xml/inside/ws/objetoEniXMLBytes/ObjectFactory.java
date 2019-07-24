
package es.mpt.dsic.inside.xml.inside.ws.objetoEniXMLBytes;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the es.mpt.dsic.inside.xml.inside.ws.objetoEniXMLBytes package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the Java representation
 * for XML content. The Java representation of XML content can consist of schema derived interfaces
 * and classes representing the binding of schema type definitions, element declarations and model
 * groups. Factory methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

  private final static QName _ExpedienteEniFileXML_QNAME = new QName(
      "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/objetoEniXMLBytes", "expedienteEniFileXML");

  /**
   * Create a new ObjectFactory that can be used to create new instances of schema derived classes
   * for package: es.mpt.dsic.inside.xml.inside.ws.objetoEniXMLBytes
   * 
   */
  public ObjectFactory() {}

  /**
   * Create an instance of {@link ObjetoEniXMLBytes }
   * 
   */
  public ObjetoEniXMLBytes createObjetoEniXMLBytes() {
    return new ObjetoEniXMLBytes();
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ObjetoEniXMLBytes }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/objetoEniXMLBytes",
      name = "expedienteEniFileXML")
  public JAXBElement<ObjetoEniXMLBytes> createExpedienteEniFileXML(ObjetoEniXMLBytes value) {
    return new JAXBElement<ObjetoEniXMLBytes>(_ExpedienteEniFileXML_QNAME, ObjetoEniXMLBytes.class,
        null, value);
  }

}
