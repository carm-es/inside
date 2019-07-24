
package es.justicia.esb.error.xsd_schemas.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Java class for ErrorEsbType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ErrorEsbType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="Codigo" type="{http://justicia.es/esb/error/xsd-schemas/V1}CodigoErrorType"/>
 *         &lt;element name="Descripcion" type="{http://justicia.es/esb/error/xsd-schemas/V1}LiteralErrorType"/>
 *         &lt;element name="Causa" type="{http://justicia.es/esb/error/xsd-schemas/V1}LiteralErrorType" minOccurs="0"/>
 *         &lt;element name="Accion" type="{http://justicia.es/esb/error/xsd-schemas/V1}LiteralErrorType" minOccurs="0"/>
 *         &lt;element name="TipoError" type="{http://justicia.es/esb/error/xsd-schemas/V1}TipoErrorType"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ErrorEsbType", propOrder = {

})
public class ErrorEsbType {

  @XmlElement(name = "Codigo", required = true)
  protected String codigo;
  @XmlElement(name = "Descripcion", required = true)
  protected String descripcion;
  @XmlElement(name = "Causa")
  protected String causa;
  @XmlElement(name = "Accion")
  protected String accion;
  @XmlElement(name = "TipoError", required = true)
  protected String tipoError;

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
   * Gets the value of the causa property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCausa() {
    return causa;
  }

  /**
   * Sets the value of the causa property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCausa(String value) {
    this.causa = value;
  }

  /**
   * Gets the value of the accion property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getAccion() {
    return accion;
  }

  /**
   * Sets the value of the accion property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setAccion(String value) {
    this.accion = value;
  }

  /**
   * Gets the value of the tipoError property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getTipoError() {
    return tipoError;
  }

  /**
   * Sets the value of the tipoError property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setTipoError(String value) {
    this.tipoError = value;
  }

}
