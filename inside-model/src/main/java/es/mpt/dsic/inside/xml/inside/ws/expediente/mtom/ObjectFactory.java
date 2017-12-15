
package es.mpt.dsic.inside.xml.inside.ws.expediente.mtom;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the es.mpt.dsic.inside.xml.inside.ws.expediente.mtom package. 
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

    private final static QName _ExpedienteEniFileMtom_QNAME = new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e/expedienteEniFile/mtom", "expedienteEniFileMtom");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: es.mpt.dsic.inside.xml.inside.ws.expediente.mtom
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TipoExpedienteEniFileInsideMtom }
     * 
     */
    public TipoExpedienteEniFileInsideMtom createTipoExpedienteEniFileInsideMtom() {
        return new TipoExpedienteEniFileInsideMtom();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoExpedienteEniFileInsideMtom }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e/expedienteEniFile/mtom", name = "expedienteEniFileMtom")
    public JAXBElement<TipoExpedienteEniFileInsideMtom> createExpedienteEniFileMtom(TipoExpedienteEniFileInsideMtom value) {
        return new JAXBElement<TipoExpedienteEniFileInsideMtom>(_ExpedienteEniFileMtom_QNAME, TipoExpedienteEniFileInsideMtom.class, null, value);
    }

}
