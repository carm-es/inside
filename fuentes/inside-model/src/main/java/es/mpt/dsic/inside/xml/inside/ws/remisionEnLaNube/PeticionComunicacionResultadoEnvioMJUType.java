
package es.mpt.dsic.inside.xml.inside.ws.remisionEnLaNube;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Esquema que representa una comunicación de resultado de un envio a MJU
 * 
 * <p>
 * Java class for PeticionComunicacionResultadoEnvioMJUType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PeticionComunicacionResultadoEnvioMJUType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codigo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idExpediente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigoEnvioMJU" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idPeticion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PeticionComunicacionResultadoEnvioMJUType",
    propOrder = {"codigo", "descripcion", "idExpediente", "codigoEnvioMJU", "idPeticion"})
public class PeticionComunicacionResultadoEnvioMJUType {

  @XmlElement(required = true)
  protected String codigo;
  protected String descripcion;
  @XmlElement(required = true)
  protected String idExpediente;
  protected String codigoEnvioMJU;
  @XmlElement(required = true)
  protected String idPeticion;

  /**
   * Gets the value of the codigo property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCodigo() {
    return codigo;
  }

  /**
   * Sets the value of the codigo property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCodigo(String value) {
    this.codigo = value;
  }

  /**
   * Gets the value of the descripcion property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDescripcion() {
    return descripcion;
  }

  /**
   * Sets the value of the descripcion property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDescripcion(String value) {
    this.descripcion = value;
  }

  /**
   * Gets the value of the idExpediente property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getIdExpediente() {
    return idExpediente;
  }

  /**
   * Sets the value of the idExpediente property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setIdExpediente(String value) {
    this.idExpediente = value;
  }

  /**
   * Gets the value of the codigoEnvioMJU property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCodigoEnvioMJU() {
    return codigoEnvioMJU;
  }

  /**
   * Sets the value of the codigoEnvioMJU property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCodigoEnvioMJU(String value) {
    this.codigoEnvioMJU = value;
  }

  /**
   * Gets the value of the idPeticion property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getIdPeticion() {
    return idPeticion;
  }

  /**
   * Sets the value of the idPeticion property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setIdPeticion(String value) {
    this.idPeticion = value;
  }

}
