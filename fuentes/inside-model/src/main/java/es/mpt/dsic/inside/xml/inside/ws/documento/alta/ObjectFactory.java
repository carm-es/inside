
package es.mpt.dsic.inside.xml.inside.ws.documento.alta;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the es.mpt.dsic.inside.xml.inside.ws.documento.alta package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the Java representation
 * for XML content. The Java representation of XML content can consist of schema derived interfaces
 * and classes representing the binding of schema type definitions, element declarations and model
 * groups. Factory methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

  private final static QName _DocumentoAlta_QNAME =
      new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/alta", "documentoAlta");

  /**
   * Create a new ObjectFactory that can be used to create new instances of schema derived classes
   * for package: es.mpt.dsic.inside.xml.inside.ws.documento.alta
   * 
   */
  public ObjectFactory() {}

  /**
   * Create an instance of {@link TipoDocumentoAltaInside.Csv }
   * 
   */
  public TipoDocumentoAltaInside.Csv createTipoDocumentoAltaInsideCsv() {
    return new TipoDocumentoAltaInside.Csv();
  }

  /**
   * Create an instance of {@link TipoDocumentoAltaInside.MetadatosEni }
   * 
   */
  public TipoDocumentoAltaInside.MetadatosEni createTipoDocumentoAltaInsideMetadatosEni() {
    return new TipoDocumentoAltaInside.MetadatosEni();
  }

  /**
   * Create an instance of {@link TipoDocumentoAltaInside }
   * 
   */
  public TipoDocumentoAltaInside createTipoDocumentoAltaInside() {
    return new TipoDocumentoAltaInside();
  }

  /**
   * Create an instance of {@link TipoDocumentoAsociadoaExpediente }
   * 
   */
  public TipoDocumentoAsociadoaExpediente createTipoDocumentoAsociadoaExpediente() {
    return new TipoDocumentoAsociadoaExpediente();
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link TipoDocumentoAltaInside }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/alta",
      name = "documentoAlta")
  public JAXBElement<TipoDocumentoAltaInside> createDocumentoAlta(TipoDocumentoAltaInside value) {
    return new JAXBElement<TipoDocumentoAltaInside>(_DocumentoAlta_QNAME,
        TipoDocumentoAltaInside.class, null, value);
  }

}
