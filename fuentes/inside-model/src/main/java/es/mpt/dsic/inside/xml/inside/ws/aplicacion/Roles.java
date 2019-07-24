
package es.mpt.dsic.inside.xml.inside.ws.aplicacion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Java class for Roles complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Roles">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="altaExpediente" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="modificarExpediente" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="leerExpediente" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="altaDocumento" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="modificarDocumento" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="leerDocumento" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="administrarPermisos" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Roles", propOrder = {"altaExpediente", "modificarExpediente", "leerExpediente",
    "altaDocumento", "modificarDocumento", "leerDocumento", "administrarPermisos"})
public class Roles {

  protected boolean altaExpediente;
  protected boolean modificarExpediente;
  protected boolean leerExpediente;
  protected boolean altaDocumento;
  protected boolean modificarDocumento;
  protected boolean leerDocumento;
  protected boolean administrarPermisos;

  /**
   * Gets the value of the altaExpediente property.
   * 
   */
  public boolean isAltaExpediente() {
    return altaExpediente;
  }

  /**
   * Sets the value of the altaExpediente property.
   * 
   */
  public void setAltaExpediente(boolean value) {
    this.altaExpediente = value;
  }

  /**
   * Gets the value of the modificarExpediente property.
   * 
   */
  public boolean isModificarExpediente() {
    return modificarExpediente;
  }

  /**
   * Sets the value of the modificarExpediente property.
   * 
   */
  public void setModificarExpediente(boolean value) {
    this.modificarExpediente = value;
  }

  /**
   * Gets the value of the leerExpediente property.
   * 
   */
  public boolean isLeerExpediente() {
    return leerExpediente;
  }

  /**
   * Sets the value of the leerExpediente property.
   * 
   */
  public void setLeerExpediente(boolean value) {
    this.leerExpediente = value;
  }

  /**
   * Gets the value of the altaDocumento property.
   * 
   */
  public boolean isAltaDocumento() {
    return altaDocumento;
  }

  /**
   * Sets the value of the altaDocumento property.
   * 
   */
  public void setAltaDocumento(boolean value) {
    this.altaDocumento = value;
  }

  /**
   * Gets the value of the modificarDocumento property.
   * 
   */
  public boolean isModificarDocumento() {
    return modificarDocumento;
  }

  /**
   * Sets the value of the modificarDocumento property.
   * 
   */
  public void setModificarDocumento(boolean value) {
    this.modificarDocumento = value;
  }

  /**
   * Gets the value of the leerDocumento property.
   * 
   */
  public boolean isLeerDocumento() {
    return leerDocumento;
  }

  /**
   * Sets the value of the leerDocumento property.
   * 
   */
  public void setLeerDocumento(boolean value) {
    this.leerDocumento = value;
  }

  /**
   * Gets the value of the administrarPermisos property.
   * 
   */
  public boolean isAdministrarPermisos() {
    return administrarPermisos;
  }

  /**
   * Sets the value of the administrarPermisos property.
   * 
   */
  public void setAdministrarPermisos(boolean value) {
    this.administrarPermisos = value;
  }

}
