
package es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.resultados;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.TipoOpcionValidacionExpediente;


/**
 * <p>
 * Java class for TipoResultadoValidacionDetalleExpedienteInside complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TipoResultadoValidacionDetalleExpedienteInside">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tipoValidacion" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/validacion/expediente-e}TipoOpcionValidacionExpediente"/>
 *         &lt;element name="resultadoValidacion" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="detalleValidacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoResultadoValidacionDetalleExpedienteInside",
    propOrder = {"tipoValidacion", "resultadoValidacion", "detalleValidacion"})
public class TipoResultadoValidacionDetalleExpedienteInside {

  @XmlElement(required = true)
  protected TipoOpcionValidacionExpediente tipoValidacion;
  protected boolean resultadoValidacion;
  @XmlElement(required = true)
  protected String detalleValidacion;

  /**
   * Gets the value of the tipoValidacion property.
   * 
   * @return possible object is {@link TipoOpcionValidacionExpediente }
   * 
   */
  public TipoOpcionValidacionExpediente getTipoValidacion() {
    return tipoValidacion;
  }

  /**
   * Sets the value of the tipoValidacion property.
   * 
   * @param value allowed object is {@link TipoOpcionValidacionExpediente }
   * 
   */
  public void setTipoValidacion(TipoOpcionValidacionExpediente value) {
    this.tipoValidacion = value;
  }

  /**
   * Gets the value of the resultadoValidacion property.
   * 
   */
  public boolean isResultadoValidacion() {
    return resultadoValidacion;
  }

  /**
   * Sets the value of the resultadoValidacion property.
   * 
   */
  public void setResultadoValidacion(boolean value) {
    this.resultadoValidacion = value;
  }

  /**
   * Gets the value of the detalleValidacion property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDetalleValidacion() {
    return detalleValidacion;
  }

  /**
   * Sets the value of the detalleValidacion property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDetalleValidacion(String value) {
    this.detalleValidacion = value;
  }

}
