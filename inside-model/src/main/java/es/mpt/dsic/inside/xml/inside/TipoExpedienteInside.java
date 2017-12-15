
package es.mpt.dsic.inside.xml.inside;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import es.mpt.dsic.inside.xml.eni.expediente.TipoExpediente;


/**
 * <p>Java class for TipoExpedienteInside complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TipoExpedienteInside">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="info" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e/expedienteInfo}ExpedienteInsideInfo"/>
 *         &lt;element name="expedienteENI" type="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e}TipoExpediente"/>
 *         &lt;element name="metadatosAdicionales" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/metadatosAdicionales}TipoMetadatosAdicionales"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoExpedienteInside", propOrder = {
    "info",
    "expedienteENI",
    "metadatosAdicionales"
})
public class TipoExpedienteInside {

    @XmlElement(required = true)
    protected ExpedienteInsideInfo info;
    @XmlElement(required = true)
    protected TipoExpediente expedienteENI;
    @XmlElement(required = true)
    protected TipoMetadatosAdicionales metadatosAdicionales;

    /**
     * Gets the value of the info property.
     * 
     * @return
     *     possible object is
     *     {@link ExpedienteInsideInfo }
     *     
     */
    public ExpedienteInsideInfo getInfo() {
        return info;
    }

    /**
     * Sets the value of the info property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExpedienteInsideInfo }
     *     
     */
    public void setInfo(ExpedienteInsideInfo value) {
        this.info = value;
    }

    /**
     * Gets the value of the expedienteENI property.
     * 
     * @return
     *     possible object is
     *     {@link TipoExpediente }
     *     
     */
    public TipoExpediente getExpedienteENI() {
        return expedienteENI;
    }

    /**
     * Sets the value of the expedienteENI property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoExpediente }
     *     
     */
    public void setExpedienteENI(TipoExpediente value) {
        this.expedienteENI = value;
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

}
