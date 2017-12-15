
package es.mpt.dsic.inside.xml.inside.ws.expediente.file;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RespuestaPdfExpediente complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RespuestaPdfExpediente">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pdfExpediente" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RespuestaPdfExpediente", namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/respuestaPdfExpediente", propOrder = {
    "pdfExpediente"
})
public class RespuestaPdfExpediente {

    @XmlElement(required = true)
    protected byte[] pdfExpediente;

    /**
     * Gets the value of the pdfExpediente property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getPdfExpediente() {
        return pdfExpediente;
    }

    /**
     * Sets the value of the pdfExpediente property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setPdfExpediente(byte[] value) {
        this.pdfExpediente = ((byte[]) value);
    }

}
