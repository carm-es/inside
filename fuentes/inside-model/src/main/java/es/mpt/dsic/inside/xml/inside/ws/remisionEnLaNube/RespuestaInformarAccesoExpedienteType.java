
package es.mpt.dsic.inside.xml.inside.ws.remisionEnLaNube;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Esquema que representa una respuesta de informar el acceso a documento
 * 
 * <p>
 * Java class for RespuestaInformarAccesoExpedienteType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RespuestaInformarAccesoExpedienteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="respuesta" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/remisionNube}RespuestaType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RespuestaInformarAccesoExpedienteType", propOrder = {"respuesta"})
public class RespuestaInformarAccesoExpedienteType {

  @XmlElement(required = true)
  protected RespuestaType respuesta;

  /**
   * Gets the value of the respuesta property.
   * 
   * @return possible object is {@link RespuestaType }
   * 
   */
  public RespuestaType getRespuesta() {
    return respuesta;
  }

  /**
   * Sets the value of the respuesta property.
   * 
   * @param value allowed object is {@link RespuestaType }
   * 
   */
  public void setRespuesta(RespuestaType value) {
    this.respuesta = value;
  }

}
