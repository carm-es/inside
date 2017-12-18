
package es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.mtom;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TipoExpedienteValidacionInsideMtom complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TipoExpedienteValidacionInsideMtom">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contenido" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="opcionesValidacionExpediente" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/validacion/expediente-e/mtom}TipoOpcionesValidacionExpedienteInsideMtom"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoExpedienteValidacionInsideMtom", propOrder = {
    "contenido",
    "opcionesValidacionExpediente"
})
public class TipoExpedienteValidacionInsideMtom {

    @XmlElement(required = true)
    @XmlMimeType("application/octet-stream")
    protected DataHandler contenido;
    @XmlElement(required = true)
    protected TipoOpcionesValidacionExpedienteInsideMtom opcionesValidacionExpediente;

    /**
     * Gets the value of the contenido property.
     * 
     * @return
     *     possible object is
     *     {@link DataHandler }
     *     
     */
    public DataHandler getContenido() {
        return contenido;
    }

    /**
     * Sets the value of the contenido property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataHandler }
     *     
     */
    public void setContenido(DataHandler value) {
        this.contenido = value;
    }

    /**
     * Gets the value of the opcionesValidacionExpediente property.
     * 
     * @return
     *     possible object is
     *     {@link TipoOpcionesValidacionExpedienteInsideMtom }
     *     
     */
    public TipoOpcionesValidacionExpedienteInsideMtom getOpcionesValidacionExpediente() {
        return opcionesValidacionExpediente;
    }

    /**
     * Sets the value of the opcionesValidacionExpediente property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoOpcionesValidacionExpedienteInsideMtom }
     *     
     */
    public void setOpcionesValidacionExpediente(TipoOpcionesValidacionExpedienteInsideMtom value) {
        this.opcionesValidacionExpediente = value;
    }

}
