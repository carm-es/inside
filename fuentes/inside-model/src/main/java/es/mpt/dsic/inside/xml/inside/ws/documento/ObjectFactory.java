
package es.mpt.dsic.inside.xml.inside.ws.documento;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the es.mpt.dsic.inside.xml.inside.ws.documento package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the Java representation
 * for XML content. The Java representation of XML content can consist of schema derived interfaces
 * and classes representing the binding of schema type definitions, element declarations and model
 * groups. Factory methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

  private final static QName _DocumentoEniFile_QNAME =
      new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/documentoEniFile",
          "documentoEniFile");

  /**
   * Create a new ObjectFactory that can be used to create new instances of schema derived classes
   * for package: es.mpt.dsic.inside.xml.inside.ws.documento
   * 
   */
  public ObjectFactory() {}

  /**
   * Create an instance of {@link TipoDocumentoEniFileInside }
   * 
   */
  public TipoDocumentoEniFileInside createTipoDocumentoEniFileInside() {
    return new TipoDocumentoEniFileInside();
  }

  /**
   * Create an instance of {@link DocumentoInsideInfo }
   * 
   */
  public DocumentoInsideInfo createDocumentoInsideInfo() {
    return new DocumentoInsideInfo();
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link TipoDocumentoEniFileInside
   * }{@code >}}
   * 
   */
  @XmlElementDecl(
      namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/documentoEniFile",
      name = "documentoEniFile")
  public JAXBElement<TipoDocumentoEniFileInside> createDocumentoEniFile(
      TipoDocumentoEniFileInside value) {
    return new JAXBElement<TipoDocumentoEniFileInside>(_DocumentoEniFile_QNAME,
        TipoDocumentoEniFileInside.class, null, value);
  }

}
