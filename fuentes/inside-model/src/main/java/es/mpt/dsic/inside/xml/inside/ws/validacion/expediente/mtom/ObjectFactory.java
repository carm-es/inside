
package es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.mtom;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.mtom package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the Java representation
 * for XML content. The Java representation of XML content can consist of schema derived interfaces
 * and classes representing the binding of schema type definitions, element declarations and model
 * groups. Factory methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

  private final static QName _ExpedienteValidacionMtom_QNAME =
      new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/validacion/expediente-e/mtom",
          "expedienteValidacionMtom");

  /**
   * Create a new ObjectFactory that can be used to create new instances of schema derived classes
   * for package: es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.mtom
   * 
   */
  public ObjectFactory() {}

  /**
   * Create an instance of {@link TipoExpedienteValidacionInsideMtom }
   * 
   */
  public TipoExpedienteValidacionInsideMtom createTipoExpedienteValidacionInsideMtom() {
    return new TipoExpedienteValidacionInsideMtom();
  }

  /**
   * Create an instance of {@link TipoOpcionesValidacionExpedienteInsideMtom }
   * 
   */
  public TipoOpcionesValidacionExpedienteInsideMtom createTipoOpcionesValidacionExpedienteInsideMtom() {
    return new TipoOpcionesValidacionExpedienteInsideMtom();
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link TipoExpedienteValidacionInsideMtom
   * }{@code >}}
   * 
   */
  @XmlElementDecl(
      namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/validacion/expediente-e/mtom",
      name = "expedienteValidacionMtom")
  public JAXBElement<TipoExpedienteValidacionInsideMtom> createExpedienteValidacionMtom(
      TipoExpedienteValidacionInsideMtom value) {
    return new JAXBElement<TipoExpedienteValidacionInsideMtom>(_ExpedienteValidacionMtom_QNAME,
        TipoExpedienteValidacionInsideMtom.class, null, value);
  }

}
