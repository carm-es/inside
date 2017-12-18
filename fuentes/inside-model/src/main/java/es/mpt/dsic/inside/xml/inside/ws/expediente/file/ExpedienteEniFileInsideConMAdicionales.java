
package es.mpt.dsic.inside.xml.inside.ws.expediente.file;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import es.mpt.dsic.inside.xml.eni.expediente.TipoExpediente;
import es.mpt.dsic.inside.xml.inside.TipoMetadatosAdicionales;


/**
 * <p>Java class for ExpedienteEniFileInsideConMAdicionales complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExpedienteEniFileInsideConMAdicionales">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e}expediente"/>
 *         &lt;element name="metadatosAdicionales" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/metadatosAdicionales}TipoMetadatosAdicionales" minOccurs="0"/>
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
@XmlType(name = "ExpedienteEniFileInsideConMAdicionales", propOrder = {
    "expediente",
    "metadatosAdicionales",
    "expedienteEniBytes"
})
public class ExpedienteEniFileInsideConMAdicionales {

    @XmlElement(namespace = "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e", required = true)
    protected TipoExpediente expediente;
    protected TipoMetadatosAdicionales metadatosAdicionales;
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
     * Gets the value of the metadatosAdicionales property.
     * 
     * @return
     *     possible object is
     *     {@link TipoMetadatosAdicionales }
     *     
     */
    public TipoMetadatosAdicionales getMetadatosAdicionales() {
        return metadatosAdicionales;
    }

    /**
     * Sets the value of the metadatosAdicionales property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoMetadatosAdicionales }
     *     
     */
    public void setMetadatosAdicionales(TipoMetadatosAdicionales value) {
        this.metadatosAdicionales = value;
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
