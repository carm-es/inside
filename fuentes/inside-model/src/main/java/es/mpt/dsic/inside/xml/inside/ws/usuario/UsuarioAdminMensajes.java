
package es.mpt.dsic.inside.xml.inside.ws.usuario;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Java class for UsuarioAdminMensajes complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UsuarioAdminMensajes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nif" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="adminMensajes" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UsuarioAdminMensajes", propOrder = {"nif", "adminMensajes"})
public class UsuarioAdminMensajes {

  @XmlElement(required = true)
  protected String nif;
  protected boolean adminMensajes;

  /**
   * Gets the value of the nif property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getNif() {
    return nif;
  }

  /**
   * Sets the value of the nif property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setNif(String value) {
    this.nif = value;
  }

  /**
   * Gets the value of the adminMensajes property.
   * 
   */
  public boolean isAdminMensajes() {
    return adminMensajes;
  }

  /**
   * Sets the value of the adminMensajes property.
   * 
   */
  public void setAdminMensajes(boolean value) {
    this.adminMensajes = value;
  }

}
