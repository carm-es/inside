
package es.mpt.dsic.inside.xml.inside.ws.respuestaConsultaEnvioJusticia;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RespuestaConsultaEnvioJusticia complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RespuestaConsultaEnvioJusticia">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="identificadorEstado" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descripcionEstado" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="comentario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="resguardo" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RespuestaConsultaEnvioJusticia", propOrder = {
    "identificadorEstado",
    "descripcionEstado",
    "comentario",
    "resguardo"
})
public class RespuestaConsultaEnvioJusticia {

    @XmlElement(required = true)
    protected String identificadorEstado;
    @XmlElement(required = true)
    protected String descripcionEstado;
    @XmlElement(required = true)
    protected String comentario;
    @XmlElement(required = true)
    protected byte[] resguardo;

    /**
     * Gets the value of the identificadorEstado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificadorEstado() {
        return identificadorEstado;
    }

    /**
     * Sets the value of the identificadorEstado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificadorEstado(String value) {
        this.identificadorEstado = value;
    }

    /**
     * Gets the value of the descripcionEstado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionEstado() {
        return descripcionEstado;
    }

    /**
     * Sets the value of the descripcionEstado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionEstado(String value) {
        this.descripcionEstado = value;
    }

    /**
     * Gets the value of the comentario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Sets the value of the comentario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComentario(String value) {
        this.comentario = value;
    }

    /**
     * Gets the value of the resguardo property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getResguardo() {
        return resguardo;
    }

    /**
     * Sets the value of the resguardo property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setResguardo(byte[] value) {
        this.resguardo = ((byte[]) value);
    }

}
