
package es.mpt.dsic.inside.xml.inside.ws.expediente.fileMtom;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlType;
import es.mpt.dsic.inside.xml.eni.expediente.mtom.TipoExpedienteMtom;
import es.mpt.dsic.inside.xml.inside.TipoMetadatosAdicionales;


/**
 * <p>Java class for ExpedienteEniFileInsideConMAdicionalesMtom complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExpedienteEniFileInsideConMAdicionalesMtom">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/mtom}expedienteMtom"/>
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
@XmlType(name = "ExpedienteEniFileInsideConMAdicionalesMtom", propOrder = {
    "expedienteMtom",
    "metadatosAdicionales",
    "expedienteEniBytes"
})
public class ExpedienteEniFileInsideConMAdicionalesMtom {

    @XmlElement(namespace = "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/mtom", required = true)
    protected TipoExpedienteMtom expedienteMtom;
    protected TipoMetadatosAdicionales metadatosAdicionales;
    @XmlElement(required = true)
    @XmlMimeType("application/octet-stream")
    protected DataHandler expedienteEniBytes;

    /**
     * Gets the value of the expedienteMtom property.
     * 
     * @return
     *     possible object is
     *     {@link TipoExpedienteMtom }
     *     
     */
    public TipoExpedienteMtom getExpedienteMtom() {
        return expedienteMtom;
    }

    /**
     * Sets the value of the expedienteMtom property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoExpedienteMtom }
     *     
     */
    public void setExpedienteMtom(TipoExpedienteMtom value) {
        this.expedienteMtom = value;
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
