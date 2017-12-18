
package es.mpt.dsic.inside.xml.inside.ws.aplicacion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Aplicacion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Aplicacion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="activo" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="roles" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/aplicacion}Roles"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="telefono" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="responsable" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigoUnidadOrganica" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numeroProcedimiento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="credencialEeutil" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/aplicacion}CredencialEeutil"/>
 *         &lt;element name="serialNumberCertificado" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Aplicacion", propOrder = {
    "nombre",
    "password",
    "activo",
    "roles",
    "email",
    "telefono",
    "responsable",
    "codigoUnidadOrganica",
    "numeroProcedimiento",
    "credencialEeutil",
    "serialNumberCertificado"
})
public class Aplicacion {

    @XmlElement(required = true)
    protected String nombre;
    @XmlElement(required = true)
    protected String password;
    protected boolean activo;
    @XmlElement(required = true)
    protected Roles roles;
    @XmlElement(required = true)
    protected String email;
    @XmlElement(required = true)
    protected String telefono;
    @XmlElement(required = true)
    protected String responsable;
    @XmlElement(required = true)
    protected String codigoUnidadOrganica;
    @XmlElement(required = true)
    protected String numeroProcedimiento;
    @XmlElement(required = true)
    protected CredencialEeutil credencialEeutil;
    @XmlElement(required = true)
    protected String serialNumberCertificado;

    /**
     * Gets the value of the nombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the value of the nombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
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
     * Gets the value of the roles property.
     * 
     * @return
     *     possible object is
     *     {@link Roles }
     *     
     */
    public Roles getRoles() {
        return roles;
    }

    /**
     * Sets the value of the roles property.
     * 
     * @param value
     *     allowed object is
     *     {@link Roles }
     *     
     */
    public void setRoles(Roles value) {
        this.roles = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the telefono property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Sets the value of the telefono property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefono(String value) {
        this.telefono = value;
    }

    /**
     * Gets the value of the responsable property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponsable() {
        return responsable;
    }

    /**
     * Sets the value of the responsable property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponsable(String value) {
        this.responsable = value;
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
     * Gets the value of the credencialEeutil property.
     * 
     * @return
     *     possible object is
     *     {@link CredencialEeutil }
     *     
     */
    public CredencialEeutil getCredencialEeutil() {
        return credencialEeutil;
    }

    /**
     * Sets the value of the credencialEeutil property.
     * 
     * @param value
     *     allowed object is
     *     {@link CredencialEeutil }
     *     
     */
    public void setCredencialEeutil(CredencialEeutil value) {
        this.credencialEeutil = value;
    }

    /**
     * Gets the value of the serialNumberCertificado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerialNumberCertificado() {
        return serialNumberCertificado;
    }

    /**
     * Sets the value of the serialNumberCertificado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerialNumberCertificado(String value) {
        this.serialNumberCertificado = value;
    }

}
