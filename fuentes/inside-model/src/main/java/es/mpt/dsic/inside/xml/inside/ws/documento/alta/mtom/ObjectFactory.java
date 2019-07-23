
package es.mpt.dsic.inside.xml.inside.ws.documento.alta.mtom;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the es.mpt.dsic.inside.xml.inside.ws.documento.alta.mtom package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the Java representation
 * for XML content. The Java representation of XML content can consist of schema derived interfaces
 * and classes representing the binding of schema type definitions, element declarations and model
 * groups. Factory methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

  private final static QName _DocumentoAltaMtom_QNAME = new QName(
      "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/alta/mtom", "documentoAltaMtom");

  /**
   * Create a new ObjectFactory that can be used to create new instances of schema derived classes
   * for package: es.mpt.dsic.inside.xml.inside.ws.documento.alta.mtom
   * 
   */
  public ObjectFactory() {}

  /**
   * Create an instance of {@link TipoDocumentoAsociadoaExpedienteMtom }
   * 
   */
  public TipoDocumentoAsociadoaExpedienteMtom createTipoDocumentoAsociadoaExpedienteMtom() {
    return new TipoDocumentoAsociadoaExpedienteMtom();
  }

  /**
   * Create an instance of {@link TipoDocumentoAltaInsideMtom.Csv }
   * 
   */
  public TipoDocumentoAltaInsideMtom.Csv createTipoDocumentoAltaInsideMtomCsv() {
    return new TipoDocumentoAltaInsideMtom.Csv();
  }

  /**
   * Create an instance of {@link TipoDocumentoAltaInsideMtom.MetadatosEni }
   * 
   */
  public TipoDocumentoAltaInsideMtom.MetadatosEni createTipoDocumentoAltaInsideMtomMetadatosEni() {
    return new TipoDocumentoAltaInsideMtom.MetadatosEni();
  }

  /**
   * Create an instance of {@link TipoDocumentoAltaInsideMtom }
   * 
   */
  public TipoDocumentoAltaInsideMtom createTipoDocumentoAltaInsideMtom() {
    return new TipoDocumentoAltaInsideMtom();
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link TipoDocumentoAltaInsideMtom
   * }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/alta/mtom",
      name = "documentoAltaMtom")
  public JAXBElement<TipoDocumentoAltaInsideMtom> createDocumentoAltaMtom(
      TipoDocumentoAltaInsideMtom value) {
    return new JAXBElement<TipoDocumentoAltaInsideMtom>(_DocumentoAltaMtom_QNAME,
        TipoDocumentoAltaInsideMtom.class, null, value);
  }

}
