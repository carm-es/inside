
package es.mpt.dsic.inside.xml.inside;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import es.mpt.dsic.inside.xml.eni.expediente.metadatos.TipoMetadatos;


/**
 * <p>
 * Java class for ExpedienteInsideMetadatos complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExpedienteInsideMetadatos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="metadatosExpedienteENI" type="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/metadatos}TipoMetadatos"/>
 *         &lt;element name="metadatosAdicionales" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/metadatosAdicionales}TipoMetadatosAdicionales"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExpedienteInsideMetadatos",
    namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e/metadatos",
    propOrder = {"metadatosExpedienteENI", "metadatosAdicionales"})
public class ExpedienteInsideMetadatos {

  @XmlElement(required = true)
  protected TipoMetadatos metadatosExpedienteENI;
  @XmlElement(required = true)
  protected TipoMetadatosAdicionales metadatosAdicionales;

  /**
   * Gets the value of the metadatosExpedienteENI property.
   * 
   * @return possible object is {@link TipoMetadatos }
   * 
   */
  public TipoMetadatos getMetadatosExpedienteENI() {
    return metadatosExpedienteENI;
  }

  /**
   * Sets the value of the metadatosExpedienteENI property.
   * 
   * @param value allowed object is {@link TipoMetadatos }
   * 
   */
  public void setMetadatosExpedienteENI(TipoMetadatos value) {
    this.metadatosExpedienteENI = value;
  }

  /**
   * Gets the value of the metadatosAdicionales property.
   * 
   * @return possible object is {@link TipoMetadatosAdicionales }
   * 
   */
  public TipoMetadatosAdicionales getMetadatosAdicionales() {
    return metadatosAdicionales;
  }

  /**
   * Sets the value of the metadatosAdicionales property.
   * 
   * @param value allowed object is {@link TipoMetadatosAdicionales }
   * 
   */
  public void setMetadatosAdicionales(TipoMetadatosAdicionales value) {
    this.metadatosAdicionales = value;
  }

}
