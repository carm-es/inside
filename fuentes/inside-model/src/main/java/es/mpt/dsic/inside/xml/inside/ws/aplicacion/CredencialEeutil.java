
package es.mpt.dsic.inside.xml.inside.ws.aplicacion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Java class for CredencialEeutil complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CredencialEeutil">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aplicacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CredencialEeutil", propOrder = {"aplicacion", "password"})
public class CredencialEeutil {

  @XmlElement(required = true)
  protected String aplicacion;
  @XmlElement(required = true)
  protected String password;

  /**
   * Gets the value of the aplicacion property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getAplicacion() {
    return aplicacion;
  }

  /**
   * Sets the value of the aplicacion property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setAplicacion(String value) {
    this.aplicacion = value;
  }

  /**
   * Gets the value of the password property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the value of the password property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setPassword(String value) {
    this.password = value;
  }

}
