
package es.mpt.dsic.inside.xml.inside.ws.busqueda;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Java class for consultaWsInside complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="consultaWsInside">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="subConsulta" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/busqueda}subConsultaWsInside"/>
 *         &lt;element name="metadato" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/busqueda}metadatoBusquedaWsInside"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultaWsInside", propOrder = {"subConsulta", "metadato"})
public class ConsultaWsInside {

  protected SubConsultaWsInside subConsulta;
  protected MetadatoBusquedaWsInside metadato;

  /**
   * Gets the value of the subConsulta property.
   * 
   * @return possible object is {@link SubConsultaWsInside }
   * 
   */
  public SubConsultaWsInside getSubConsulta() {
    return subConsulta;
  }

  /**
   * Sets the value of the subConsulta property.
   * 
   * @param value allowed object is {@link SubConsultaWsInside }
   * 
   */
  public void setSubConsulta(SubConsultaWsInside value) {
    this.subConsulta = value;
  }

  /**
   * Gets the value of the metadato property.
   * 
   * @return possible object is {@link MetadatoBusquedaWsInside }
   * 
   */
  public MetadatoBusquedaWsInside getMetadato() {
    return metadato;
  }

  /**
   * Sets the value of the metadato property.
   * 
   * @param value allowed object is {@link MetadatoBusquedaWsInside }
   * 
   */
  public void setMetadato(MetadatoBusquedaWsInside value) {
    this.metadato = value;
  }

}
