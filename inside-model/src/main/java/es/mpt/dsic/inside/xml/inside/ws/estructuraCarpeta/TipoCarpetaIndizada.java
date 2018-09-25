
package es.mpt.dsic.inside.xml.inside.ws.estructuraCarpeta;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TipoCarpetaIndizada complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TipoCarpetaIndizada">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdentificadorCarpeta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="carpetaIndizada" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/estructuraCarpeta}TipoCarpetaIndizada" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoCarpetaIndizada", propOrder = {
    "identificadorCarpeta",
    "carpetaIndizada"
})
public class TipoCarpetaIndizada {

    @XmlElement(name = "IdentificadorCarpeta", required = true)
    protected String identificadorCarpeta;
    protected List<TipoCarpetaIndizada> carpetaIndizada;

    /**
     * Gets the value of the identificadorCarpeta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificadorCarpeta() {
        return identificadorCarpeta;
    }

    /**
     * Sets the value of the identificadorCarpeta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificadorCarpeta(String value) {
        this.identificadorCarpeta = value;
    }

    /**
     * Gets the value of the carpetaIndizada property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the carpetaIndizada property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCarpetaIndizada().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TipoCarpetaIndizada }
     * 
     * 
     */
    public List<TipoCarpetaIndizada> getCarpetaIndizada() {
        if (carpetaIndizada == null) {
            carpetaIndizada = new ArrayList<TipoCarpetaIndizada>();
        }
        return this.carpetaIndizada;
    }

}
