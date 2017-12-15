
package es.mpt.dsic.inside.xml.inside.ws.documento.mtom;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TipoDocumentoEniFileInsideMtom complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TipoDocumentoEniFileInsideMtom">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="documentoEniBytes" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoDocumentoEniFileInsideMtom", propOrder = {
    "documentoEniBytes"
})
public class TipoDocumentoEniFileInsideMtom {

    @XmlElement(required = true)
    @XmlMimeType("application/octet-stream")
    protected DataHandler documentoEniBytes;

    /**
     * Gets the value of the documentoEniBytes property.
     * 
     * @return
     *     possible object is
     *     {@link DataHandler }
     *     
     */
    public DataHandler getDocumentoEniBytes() {
        return documentoEniBytes;
    }

    /**
     * Sets the value of the documentoEniBytes property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataHandler }
     *     
     */
    public void setDocumentoEniBytes(DataHandler value) {
        this.documentoEniBytes = value;
    }

}
