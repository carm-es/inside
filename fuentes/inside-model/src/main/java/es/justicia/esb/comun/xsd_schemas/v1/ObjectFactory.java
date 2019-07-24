
package es.justicia.esb.comun.xsd_schemas.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the es.justicia.esb.comun.xsd_schemas.v1 package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the Java representation
 * for XML content. The Java representation of XML content can consist of schema derived interfaces
 * and classes representing the binding of schema type definitions, element declarations and model
 * groups. Factory methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

  private final static QName _AuditoriaEsb_QNAME =
      new QName("http://justicia.es/esb/comun/xsd-schemas/V1", "AuditoriaEsb");

  /**
   * Create a new ObjectFactory that can be used to create new instances of schema derived classes
   * for package: es.justicia.esb.comun.xsd_schemas.v1
   * 
   */
  public ObjectFactory() {}

  /**
   * Create an instance of {@link AuditoriaEsbType }
   * 
   */
  public AuditoriaEsbType createAuditoriaEsbType() {
    return new AuditoriaEsbType();
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link AuditoriaEsbType }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "http://justicia.es/esb/comun/xsd-schemas/V1", name = "AuditoriaEsb")
  public JAXBElement<AuditoriaEsbType> createAuditoriaEsb(AuditoriaEsbType value) {
    return new JAXBElement<AuditoriaEsbType>(_AuditoriaEsb_QNAME, AuditoriaEsbType.class, null,
        value);
  }

}
