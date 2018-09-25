
package es.mpt.dsic.inside.xml.inside.ws.credential;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the es.mpt.dsic.inside.xml.inside.ws.credential package. 
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

    private final static QName _Credential_QNAME = new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService/types/credential", "credential");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: es.mpt.dsic.inside.xml.inside.ws.credential
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link WSCredentialInside }
     * 
     */
    public WSCredentialInside createWSCredentialInside() {
        return new WSCredentialInside();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSCredentialInside }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService/types/credential", name = "credential")
    public JAXBElement<WSCredentialInside> createCredential(WSCredentialInside value) {
        return new JAXBElement<WSCredentialInside>(_Credential_QNAME, WSCredentialInside.class, null, value);
    }

}
