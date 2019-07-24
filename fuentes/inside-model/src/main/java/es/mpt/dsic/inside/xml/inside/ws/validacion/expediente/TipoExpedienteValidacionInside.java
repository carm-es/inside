
package es.mpt.dsic.inside.xml.inside.ws.validacion.expediente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Java class for TipoExpedienteValidacionInside complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TipoExpedienteValidacionInside">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contenido" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="opcionesValidacionExpediente" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/validacion/expediente-e}TipoOpcionesValidacionExpedienteInside"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoExpedienteValidacionInside",
    propOrder = {"contenido", "opcionesValidacionExpediente"})
public class TipoExpedienteValidacionInside {

  @XmlElement(required = true)
  protected byte[] contenido;
  @XmlElement(required = true)
  protected TipoOpcionesValidacionExpedienteInside opcionesValidacionExpediente;

  /**
   * Gets the value of the contenido property.
   * 
   * @return possible object is byte[]
   */
  public byte[] getContenido() {
    return contenido;
  }

  /**
   * Sets the value of the contenido property.
   * 
   * @param value allowed object is byte[]
   */
  public void setContenido(byte[] value) {
    this.contenido = ((byte[]) value);
  }

  /**
   * Gets the value of the opcionesValidacionExpediente property.
   * 
   * @return possible object is {@link TipoOpcionesValidacionExpedienteInside }
   * 
   */
  public TipoOpcionesValidacionExpedienteInside getOpcionesValidacionExpediente() {
    return opcionesValidacionExpediente;
  }

  /**
   * Sets the value of the opcionesValidacionExpediente property.
   * 
   * @param value allowed object is {@link TipoOpcionesValidacionExpedienteInside }
   * 
   */
  public void setOpcionesValidacionExpediente(TipoOpcionesValidacionExpedienteInside value) {
    this.opcionesValidacionExpediente = value;
  }

}
