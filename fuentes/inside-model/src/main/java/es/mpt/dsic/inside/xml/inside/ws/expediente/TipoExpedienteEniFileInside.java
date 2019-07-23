
package es.mpt.dsic.inside.xml.inside.ws.expediente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Java class for TipoExpedienteEniFileInside complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TipoExpedienteEniFileInside">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
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
@XmlType(name = "TipoExpedienteEniFileInside", propOrder = {"expedienteEniBytes"})
public class TipoExpedienteEniFileInside {

  @XmlElement(required = true)
  protected byte[] expedienteEniBytes;

  /**
   * Gets the value of the expedienteEniBytes property.
   * 
   * @return possible object is byte[]
   */
  public byte[] getExpedienteEniBytes() {
    return expedienteEniBytes;
  }

  /**
   * Sets the value of the expedienteEniBytes property.
   * 
   * @param value allowed object is byte[]
   */
  public void setExpedienteEniBytes(byte[] value) {
    this.expedienteEniBytes = ((byte[]) value);
  }

}
