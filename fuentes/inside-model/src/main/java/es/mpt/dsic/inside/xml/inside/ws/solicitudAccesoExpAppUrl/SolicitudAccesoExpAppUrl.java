
package es.mpt.dsic.inside.xml.inside.ws.solicitudAccesoExpAppUrl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Url destino de peticiones donde solicitar token o indices de expedientes según dir3 padre
 * 
 * <p>
 * Java class for SolicitudAccesoExpAppUrl complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SolicitudAccesoExpAppUrl">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dir3Padre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="urlDestinoPeticion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SolicitudAccesoExpAppUrl", propOrder = {"dir3Padre", "urlDestinoPeticion"})
public class SolicitudAccesoExpAppUrl {

  @XmlElement(required = true)
  protected String dir3Padre;
  @XmlElement(required = true)
  protected String urlDestinoPeticion;

  /**
   * Gets the value of the dir3Padre property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDir3Padre() {
    return dir3Padre;
  }

  /**
   * Sets the value of the dir3Padre property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDir3Padre(String value) {
    this.dir3Padre = value;
  }

  /**
   * Gets the value of the urlDestinoPeticion property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getUrlDestinoPeticion() {
    return urlDestinoPeticion;
  }

  /**
   * Sets the value of the urlDestinoPeticion property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setUrlDestinoPeticion(String value) {
    this.urlDestinoPeticion = value;
  }

}
