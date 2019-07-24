
package es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.mtom;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.mtom package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the Java representation
 * for XML content. The Java representation of XML content can consist of schema derived interfaces
 * and classes representing the binding of schema type definitions, element declarations and model
 * groups. Factory methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

  private final static QName _DocumentoVisualizacionMtom_QNAME =
      new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/visualizacion/documento-e/mtom",
          "documentoVisualizacionMtom");

  /**
   * Create a new ObjectFactory that can be used to create new instances of schema derived classes
   * for package: es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.mtom
   * 
   */
  public ObjectFactory() {}

  /**
   * Create an instance of {@link TipoDocumentoVisualizacionInsideMtom }
   * 
   */
  public TipoDocumentoVisualizacionInsideMtom createTipoDocumentoVisualizacionInsideMtom() {
    return new TipoDocumentoVisualizacionInsideMtom();
  }

  /**
   * Create an instance of {@link TipoDocumentoEniBinarioOTipoMtom }
   * 
   */
  public TipoDocumentoEniBinarioOTipoMtom createTipoDocumentoEniBinarioOTipoMtom() {
    return new TipoDocumentoEniBinarioOTipoMtom();
  }

  /**
   * Create an instance of {@link TipoOpcionesVisualizacionDocumentoMtom }
   * 
   */
  public TipoOpcionesVisualizacionDocumentoMtom createTipoOpcionesVisualizacionDocumentoMtom() {
    return new TipoOpcionesVisualizacionDocumentoMtom();
  }

  /**
   * Create an instance of {@link TipoOpcionesVisualizacionDocumentoMtom.FilasNombreOrganismo }
   * 
   */
  public TipoOpcionesVisualizacionDocumentoMtom.FilasNombreOrganismo createTipoOpcionesVisualizacionDocumentoMtomFilasNombreOrganismo() {
    return new TipoOpcionesVisualizacionDocumentoMtom.FilasNombreOrganismo();
  }

  /**
   * Create an instance of {@link TipoResultadoVisualizacionDocumentoInsideMtom }
   * 
   */
  public TipoResultadoVisualizacionDocumentoInsideMtom createTipoResultadoVisualizacionDocumentoInsideMtom() {
    return new TipoResultadoVisualizacionDocumentoInsideMtom();
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link TipoDocumentoVisualizacionInsideMtom
   * }{@code >}}
   * 
   */
  @XmlElementDecl(
      namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/visualizacion/documento-e/mtom",
      name = "documentoVisualizacionMtom")
  public JAXBElement<TipoDocumentoVisualizacionInsideMtom> createDocumentoVisualizacionMtom(
      TipoDocumentoVisualizacionInsideMtom value) {
    return new JAXBElement<TipoDocumentoVisualizacionInsideMtom>(_DocumentoVisualizacionMtom_QNAME,
        TipoDocumentoVisualizacionInsideMtom.class, null, value);
  }

}
