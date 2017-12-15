
package es.mpt.dsic.inside.xml.inside.ws.expediente.mtom;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TipoExpedienteEniFileInsideMtom complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TipoExpedienteEniFileInsideMtom">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
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
@XmlType(name = "TipoExpedienteEniFileInsideMtom", propOrder = {
    "expedienteEniBytes"
})
public class TipoExpedienteEniFileInsideMtom {

    @XmlElement(required = true)
    @XmlMimeType("application/octet-stream")
    protected DataHandler expedienteEniBytes;

    /**
     * Gets the value of the expedienteEniBytes property.
     * 
     * @return
     *     possible object is
     *     {@link DataHandler }
     *     
     */
    public DataHandler getExpedienteEniBytes() {
        return expedienteEniBytes;
    }

    /**
     * Sets the value of the expedienteEniBytes property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataHandler }
     *     
     */
    public void setExpedienteEniBytes(DataHandler value) {
        this.expedienteEniBytes = value;
    }

}
