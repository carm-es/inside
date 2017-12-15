
package es.mpt.dsic.inside.xml.inside.ws.respuestaEnvioJusticia;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RespuestaEnvioJusticia complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RespuestaEnvioJusticia">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AuditoriaEsbAplicacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AuditoriaEsbModulo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AuditoriaEsbServicio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AuditoriaEsbMarcaTiempo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ack" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigoEnvio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigoUnidadOrganoRemitente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mensaje" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RespuestaEnvioJusticia", propOrder = {
    "auditoriaEsbAplicacion",
    "auditoriaEsbModulo",
    "auditoriaEsbServicio",
    "auditoriaEsbMarcaTiempo",
    "ack",
    "codigoEnvio",
    "codigoUnidadOrganoRemitente",
    "mensaje"
})
public class RespuestaEnvioJusticia {

    @XmlElement(name = "AuditoriaEsbAplicacion", required = true)
    protected String auditoriaEsbAplicacion;
    @XmlElement(name = "AuditoriaEsbModulo", required = true)
    protected String auditoriaEsbModulo;
    @XmlElement(name = "AuditoriaEsbServicio", required = true)
    protected String auditoriaEsbServicio;
    @XmlElement(name = "AuditoriaEsbMarcaTiempo", required = true)
    protected String auditoriaEsbMarcaTiempo;
    @XmlElement(required = true)
    protected String ack;
    @XmlElement(required = true)
    protected String codigoEnvio;
    @XmlElement(required = true)
    protected String codigoUnidadOrganoRemitente;
    @XmlElement(required = true)
    protected String mensaje;

    /**
     * Gets the value of the auditoriaEsbAplicacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuditoriaEsbAplicacion() {
        return auditoriaEsbAplicacion;
    }

    /**
     * Sets the value of the auditoriaEsbAplicacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuditoriaEsbAplicacion(String value) {
        this.auditoriaEsbAplicacion = value;
    }

    /**
     * Gets the value of the auditoriaEsbModulo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuditoriaEsbModulo() {
        return auditoriaEsbModulo;
    }

    /**
     * Sets the value of the auditoriaEsbModulo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuditoriaEsbModulo(String value) {
        this.auditoriaEsbModulo = value;
    }

    /**
     * Gets the value of the auditoriaEsbServicio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuditoriaEsbServicio() {
        return auditoriaEsbServicio;
    }

    /**
     * Sets the value of the auditoriaEsbServicio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuditoriaEsbServicio(String value) {
        this.auditoriaEsbServicio = value;
    }

    /**
     * Gets the value of the auditoriaEsbMarcaTiempo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuditoriaEsbMarcaTiempo() {
        return auditoriaEsbMarcaTiempo;
    }

    /**
     * Sets the value of the auditoriaEsbMarcaTiempo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuditoriaEsbMarcaTiempo(String value) {
        this.auditoriaEsbMarcaTiempo = value;
    }

    /**
     * Gets the value of the ack property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAck() {
        return ack;
    }

    /**
     * Sets the value of the ack property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAck(String value) {
        this.ack = value;
    }

    /**
     * Gets the value of the codigoEnvio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoEnvio() {
        return codigoEnvio;
    }

    /**
     * Sets the value of the codigoEnvio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoEnvio(String value) {
        this.codigoEnvio = value;
    }

    /**
     * Gets the value of the codigoUnidadOrganoRemitente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoUnidadOrganoRemitente() {
        return codigoUnidadOrganoRemitente;
    }

    /**
     * Sets the value of the codigoUnidadOrganoRemitente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoUnidadOrganoRemitente(String value) {
        this.codigoUnidadOrganoRemitente = value;
    }

    /**
     * Gets the value of the mensaje property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Sets the value of the mensaje property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMensaje(String value) {
        this.mensaje = value;
    }

}
