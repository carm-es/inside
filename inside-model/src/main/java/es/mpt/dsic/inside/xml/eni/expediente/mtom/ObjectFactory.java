
package es.mpt.dsic.inside.xml.eni.expediente.mtom;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the es.mpt.dsic.inside.xml.eni.expediente.mtom package. 
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

    private final static QName _ExpedienteMtom_QNAME = new QName("http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/mtom", "expedienteMtom");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: es.mpt.dsic.inside.xml.eni.expediente.mtom
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TipoExpedienteMtom }
     * 
     */
    public TipoExpedienteMtom createTipoExpedienteMtom() {
        return new TipoExpedienteMtom();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoExpedienteMtom }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/mtom", name = "expedienteMtom")
    public JAXBElement<TipoExpedienteMtom> createExpedienteMtom(TipoExpedienteMtom value) {
        return new JAXBElement<TipoExpedienteMtom>(_ExpedienteMtom_QNAME, TipoExpedienteMtom.class, null, value);
    }

}
