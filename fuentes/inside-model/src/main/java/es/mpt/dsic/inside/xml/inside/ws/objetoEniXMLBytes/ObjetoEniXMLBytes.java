
package es.mpt.dsic.inside.xml.inside.ws.objetoEniXMLBytes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Java class for ObjetoEniXMLBytes complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ObjetoEniXMLBytes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mensaje" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="atributoObjetoEniXMLBytes" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObjetoEniXMLBytes", propOrder = {"mensaje", "atributoObjetoEniXMLBytes"})
public class ObjetoEniXMLBytes {

  @XmlElement(required = true)
  protected String mensaje;
  @XmlElement(required = true)
  protected byte[] atributoObjetoEniXMLBytes;

  /**
   * Gets the value of the mensaje property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getMensaje() {
    return mensaje;
  }

  /**
   * Sets the value of the mensaje property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setMensaje(String value) {
    this.mensaje = value;
  }

  /**
   * Gets the value of the atributoObjetoEniXMLBytes property.
   * 
   * @return possible object is byte[]
   */
  public byte[] getAtributoObjetoEniXMLBytes() {
    return atributoObjetoEniXMLBytes;
  }

  /**
   * Sets the value of the atributoObjetoEniXMLBytes property.
   * 
   * @param value allowed object is byte[]
   */
  public void setAtributoObjetoEniXMLBytes(byte[] value) {
    this.atributoObjetoEniXMLBytes = ((byte[]) value);
  }

}
