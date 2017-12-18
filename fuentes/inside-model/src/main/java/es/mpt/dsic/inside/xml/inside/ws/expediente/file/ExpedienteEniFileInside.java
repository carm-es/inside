
package es.mpt.dsic.inside.xml.inside.ws.expediente.file;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import es.mpt.dsic.inside.xml.eni.expediente.TipoExpediente;


/**
 * Para el intercambio de un expediente electrónico, se envía en primer lugar, el índice del expediente. Posteriormente, se enviarán los documentos que lo componen , uno a uno,  y siguiendo la distribución reflejada en el contenido del índice.
 * 
 * <p>Java class for ExpedienteEniFileInside complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExpedienteEniFileInside">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e}expediente"/>
 *         &lt;element name="expedienteEniBytes" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExpedienteEniFileInside", propOrder = {
    "expediente",
    "expedienteEniBytes"
})
public class ExpedienteEniFileInside {

    @XmlElement(namespace = "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e", required = true)
    protected TipoExpediente expediente;
    @XmlElement(required = true)
    protected byte[] expedienteEniBytes;

    /**
     * Gets the value of the expediente property.
     * 
     * @return
     *     possible object is
     *     {@link TipoExpediente }
     *     
     */
    public TipoExpediente getExpediente() {
        return expediente;
    }

    /**
     * Sets the value of the expediente property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoExpediente }
     *     
     */
    public void setExpediente(TipoExpediente value) {
        this.expediente = value;
    }

    /**
     * Gets the value of the expedienteEniBytes property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getExpedienteEniBytes() {
        return expedienteEniBytes;
    }

    /**
     * Sets the value of the expedienteEniBytes property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setExpedienteEniBytes(byte[] value) {
        this.expedienteEniBytes = ((byte[]) value);
    }

}
