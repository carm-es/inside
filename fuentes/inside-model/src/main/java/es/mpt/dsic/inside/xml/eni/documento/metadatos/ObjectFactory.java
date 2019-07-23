
package es.mpt.dsic.inside.xml.eni.documento.metadatos;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the es.mpt.dsic.inside.xml.eni.documento.metadatos package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the Java representation
 * for XML content. The Java representation of XML content can consist of schema derived interfaces
 * and classes representing the binding of schema type definitions, element declarations and model
 * groups. Factory methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

  private final static QName _Metadatos_QNAME = new QName(
      "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/metadatos", "metadatos");

  /**
   * Create a new ObjectFactory that can be used to create new instances of schema derived classes
   * for package: es.mpt.dsic.inside.xml.eni.documento.metadatos
   * 
   */
  public ObjectFactory() {}

  /**
   * Create an instance of {@link TipoEstadoElaboracion }
   * 
   */
  public TipoEstadoElaboracion createTipoEstadoElaboracion() {
    return new TipoEstadoElaboracion();
  }

  /**
   * Create an instance of {@link TipoMetadatos }
   * 
   */
  public TipoMetadatos createTipoMetadatos() {
    return new TipoMetadatos();
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link TipoMetadatos }{@code >}}
   * 
   */
  @XmlElementDecl(
      namespace = "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/metadatos",
      name = "metadatos")
  public JAXBElement<TipoMetadatos> createMetadatos(TipoMetadatos value) {
    return new JAXBElement<TipoMetadatos>(_Metadatos_QNAME, TipoMetadatos.class, null, value);
  }

}
