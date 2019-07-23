
package es.mpt.dsic.inside.xml.inside.ws.busqueda;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Java class for subConsultaWsInside complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="subConsultaWsInside">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="subConsulta" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/busqueda}subConsultaWsInside" maxOccurs="unbounded"/>
 *         &lt;element name="metadato" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/busqueda}metadatoBusquedaWsInside" maxOccurs="unbounded"/>
 *       &lt;/choice>
 *       &lt;attribute name="tipo" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/busqueda}tipoSubconsulta" default="AND" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "subConsultaWsInside", propOrder = {"subConsulta", "metadato"})
public class SubConsultaWsInside {

  protected List<SubConsultaWsInside> subConsulta;
  protected List<MetadatoBusquedaWsInside> metadato;
  @XmlAttribute(name = "tipo")
  protected TipoSubconsulta tipo;

  /**
   * Gets the value of the subConsulta property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the subConsulta property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getSubConsulta().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list {@link SubConsultaWsInside }
   * 
   * 
   */
  public List<SubConsultaWsInside> getSubConsulta() {
    if (subConsulta == null) {
      subConsulta = new ArrayList<SubConsultaWsInside>();
    }
    return this.subConsulta;
  }

  /**
   * Gets the value of the metadato property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the metadato property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getMetadato().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list {@link MetadatoBusquedaWsInside }
   * 
   * 
   */
  public List<MetadatoBusquedaWsInside> getMetadato() {
    if (metadato == null) {
      metadato = new ArrayList<MetadatoBusquedaWsInside>();
    }
    return this.metadato;
  }

  /**
   * Gets the value of the tipo property.
   * 
   * @return possible object is {@link TipoSubconsulta }
   * 
   */
  public TipoSubconsulta getTipo() {
    if (tipo == null) {
      return TipoSubconsulta.AND;
    } else {
      return tipo;
    }
  }

  /**
   * Sets the value of the tipo property.
   * 
   * @param value allowed object is {@link TipoSubconsulta }
   * 
   */
  public void setTipo(TipoSubconsulta value) {
    this.tipo = value;
  }

}
