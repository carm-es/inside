
package es.mpt.dsic.inside.xml.eni.expediente.indice.contenido;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the es.mpt.dsic.inside.xml.eni.expediente.indice.contenido package. 
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

    private final static QName _IndiceContenido_QNAME = new QName("http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/indice-e/contenido", "IndiceContenido");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: es.mpt.dsic.inside.xml.eni.expediente.indice.contenido
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TipoCarpetaIndizada }
     * 
     */
    public TipoCarpetaIndizada createTipoCarpetaIndizada() {
        return new TipoCarpetaIndizada();
    }

    /**
     * Create an instance of {@link TipoIndiceContenido }
     * 
     */
    public TipoIndiceContenido createTipoIndiceContenido() {
        return new TipoIndiceContenido();
    }

    /**
     * Create an instance of {@link TipoDocumentoIndizado }
     * 
     */
    public TipoDocumentoIndizado createTipoDocumentoIndizado() {
        return new TipoDocumentoIndizado();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoIndiceContenido }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/indice-e/contenido", name = "IndiceContenido")
    public JAXBElement<TipoIndiceContenido> createIndiceContenido(TipoIndiceContenido value) {
        return new JAXBElement<TipoIndiceContenido>(_IndiceContenido_QNAME, TipoIndiceContenido.class, null, value);
    }

}
