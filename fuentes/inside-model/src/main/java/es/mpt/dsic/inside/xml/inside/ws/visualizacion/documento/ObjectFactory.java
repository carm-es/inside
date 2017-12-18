
package es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DocumentoVisualizacion_QNAME = new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/visualizacion/documento-e", "documentoVisualizacion");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TipoOpcionesVisualizacionDocumento.FilasNombreOrganismo }
     * 
     */
    public TipoOpcionesVisualizacionDocumento.FilasNombreOrganismo createTipoOpcionesVisualizacionDocumentoFilasNombreOrganismo() {
        return new TipoOpcionesVisualizacionDocumento.FilasNombreOrganismo();
    }

    /**
     * Create an instance of {@link TipoResultadoVisualizacionDocumentoInside }
     * 
     */
    public TipoResultadoVisualizacionDocumentoInside createTipoResultadoVisualizacionDocumentoInside() {
        return new TipoResultadoVisualizacionDocumentoInside();
    }

    /**
     * Create an instance of {@link TipoDocumentoEniBinarioOTipo }
     * 
     */
    public TipoDocumentoEniBinarioOTipo createTipoDocumentoEniBinarioOTipo() {
        return new TipoDocumentoEniBinarioOTipo();
    }

    /**
     * Create an instance of {@link TipoDocumentoVisualizacionInside }
     * 
     */
    public TipoDocumentoVisualizacionInside createTipoDocumentoVisualizacionInside() {
        return new TipoDocumentoVisualizacionInside();
    }

    /**
     * Create an instance of {@link TipoOpcionesVisualizacionDocumento }
     * 
     */
    public TipoOpcionesVisualizacionDocumento createTipoOpcionesVisualizacionDocumento() {
        return new TipoOpcionesVisualizacionDocumento();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoDocumentoVisualizacionInside }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/visualizacion/documento-e", name = "documentoVisualizacion")
    public JAXBElement<TipoDocumentoVisualizacionInside> createDocumentoVisualizacion(TipoDocumentoVisualizacionInside value) {
        return new JAXBElement<TipoDocumentoVisualizacionInside>(_DocumentoVisualizacion_QNAME, TipoDocumentoVisualizacionInside.class, null, value);
    }

}
