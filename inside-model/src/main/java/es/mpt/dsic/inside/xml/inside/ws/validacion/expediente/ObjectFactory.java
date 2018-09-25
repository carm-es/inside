
package es.mpt.dsic.inside.xml.inside.ws.validacion.expediente;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the es.mpt.dsic.inside.xml.inside.ws.validacion.expediente package. 
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

    private final static QName _ExpedienteValidacion_QNAME = new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/validacion/expediente-e", "expedienteValidacion");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: es.mpt.dsic.inside.xml.inside.ws.validacion.expediente
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TipoOpcionesValidacionExpedienteInside }
     * 
     */
    public TipoOpcionesValidacionExpedienteInside createTipoOpcionesValidacionExpedienteInside() {
        return new TipoOpcionesValidacionExpedienteInside();
    }

    /**
     * Create an instance of {@link TipoExpedienteValidacionInside }
     * 
     */
    public TipoExpedienteValidacionInside createTipoExpedienteValidacionInside() {
        return new TipoExpedienteValidacionInside();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoExpedienteValidacionInside }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/validacion/expediente-e", name = "expedienteValidacion")
    public JAXBElement<TipoExpedienteValidacionInside> createExpedienteValidacion(TipoExpedienteValidacionInside value) {
        return new JAXBElement<TipoExpedienteValidacionInside>(_ExpedienteValidacion_QNAME, TipoExpedienteValidacionInside.class, null, value);
    }

}
