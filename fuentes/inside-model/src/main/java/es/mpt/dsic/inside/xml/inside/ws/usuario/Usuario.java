
package es.mpt.dsic.inside.xml.inside.ws.usuario;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Usuario complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Usuario">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nif" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigoUnidadOrganica" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nombreUnidadOrganica" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numeroProcedimiento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="activo" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="idRol" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Usuario", propOrder = {
    "nif",
    "codigoUnidadOrganica",
    "nombreUnidadOrganica",
    "numeroProcedimiento",
    "activo",
    "idRol"
})
public class Usuario {

    @XmlElement(required = true)
    protected String nif;
    @XmlElement(required = true)
    protected String codigoUnidadOrganica;
    @XmlElement(required = true)
    protected String nombreUnidadOrganica;
    @XmlElement(required = true)
    protected String numeroProcedimiento;
    protected boolean activo;
    @XmlElement(required = true)
    protected String idRol;

    /**
     * Gets the value of the nif property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNif() {
        return nif;
    }

    /**
     * Sets the value of the nif property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNif(String value) {
        this.nif = value;
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
     * Gets the value of the nombreUnidadOrganica property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreUnidadOrganica() {
        return nombreUnidadOrganica;
    }

    /**
     * Sets the value of the nombreUnidadOrganica property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreUnidadOrganica(String value) {
        this.nombreUnidadOrganica = value;
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
     * Gets the value of the activo property.
     * 
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * Sets the value of the activo property.
     * 
     */
    public void setActivo(boolean value) {
        this.activo = value;
    }

    /**
     * Gets the value of the idRol property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdRol() {
        return idRol;
    }

    /**
     * Sets the value of the idRol property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdRol(String value) {
        this.idRol = value;
    }

}
