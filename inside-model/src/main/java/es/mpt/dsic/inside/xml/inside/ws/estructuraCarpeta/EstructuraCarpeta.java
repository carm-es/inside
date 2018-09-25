
package es.mpt.dsic.inside.xml.inside.ws.estructuraCarpeta;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EstructuraCarpeta complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EstructuraCarpeta">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="identificadorEstructura" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigoUnidadOrganica" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numeroProcedimiento" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "EstructuraCarpeta", propOrder = {
    "identificadorEstructura",
    "codigoUnidadOrganica",
    "numeroProcedimiento",
    "carpetaIndizada"
})
public class EstructuraCarpeta {

    @XmlElement(required = true)
    protected String identificadorEstructura;
    @XmlElement(required = true)
    protected String codigoUnidadOrganica;
    @XmlElement(required = true)
    protected String numeroProcedimiento;
    protected List<TipoCarpetaIndizada> carpetaIndizada;

    /**
     * Gets the value of the identificadorEstructura property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificadorEstructura() {
        return identificadorEstructura;
    }

    /**
     * Sets the value of the identificadorEstructura property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificadorEstructura(String value) {
        this.identificadorEstructura = value;
    }

    /**
     * Gets the value of the codigoUnidadOrganica property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoUnidadOrganica() {
        return codigoUnidadOrganica;
    }

    /**
     * Sets the value of the codigoUnidadOrganica property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoUnidadOrganica(String value) {
        this.codigoUnidadOrganica = value;
    }

    /**
     * Gets the value of the numeroProcedimiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroProcedimiento() {
        return numeroProcedimiento;
    }

    /**
     * Sets the value of the numeroProcedimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroProcedimiento(String value) {
        this.numeroProcedimiento = value;
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
