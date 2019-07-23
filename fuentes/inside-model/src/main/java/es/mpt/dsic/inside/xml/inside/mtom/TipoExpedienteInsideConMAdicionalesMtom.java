
package es.mpt.dsic.inside.xml.inside.mtom;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import es.mpt.dsic.inside.xml.eni.expediente.mtom.TipoExpedienteMtom;
import es.mpt.dsic.inside.xml.inside.TipoMetadatosAdicionales;


/**
 * <p>
 * Java class for TipoExpedienteInsideConMAdicionalesMtom complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TipoExpedienteInsideConMAdicionalesMtom">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/mtom}expedienteMtom"/>
 *         &lt;element name="metadatosAdicionales" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/metadatosAdicionales}TipoMetadatosAdicionales" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoExpedienteInsideConMAdicionalesMtom",
    namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e/mtom",
    propOrder = {"expedienteMtom", "metadatosAdicionales"})
public class TipoExpedienteInsideConMAdicionalesMtom {

  @XmlElement(namespace = "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/mtom",
      required = true)
  protected TipoExpedienteMtom expedienteMtom;
  protected TipoMetadatosAdicionales metadatosAdicionales;

  /**
   * Gets the value of the expedienteMtom property.
   * 
   * @return possible object is {@link TipoExpedienteMtom }
   * 
   */
  public TipoExpedienteMtom getExpedienteMtom() {
    return expedienteMtom;
  }

  /**
   * Sets the value of the expedienteMtom property.
   * 
   * @param value allowed object is {@link TipoExpedienteMtom }
   * 
   */
  public void setExpedienteMtom(TipoExpedienteMtom value) {
    this.expedienteMtom = value;
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
