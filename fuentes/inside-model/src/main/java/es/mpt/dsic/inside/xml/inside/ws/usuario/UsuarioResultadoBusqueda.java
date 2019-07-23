
package es.mpt.dsic.inside.xml.inside.ws.usuario;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import es.mpt.dsic.inside.xml.inside.ws.filter.FilterPageRequestResponse;


/**
 * <p>
 * Java class for UsuarioResultadoBusqueda complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UsuarioResultadoBusqueda">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="paginador" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/filter}FilterPageRequestResponse"/>
 *         &lt;element name="resultados" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/usuario}Usuario" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UsuarioResultadoBusqueda", propOrder = {"paginador", "resultados"})
public class UsuarioResultadoBusqueda {

  @XmlElement(required = true)
  protected FilterPageRequestResponse paginador;
  protected List<Usuario> resultados;

  /**
   * Gets the value of the paginador property.
   * 
   * @return possible object is {@link FilterPageRequestResponse }
   * 
   */
  public FilterPageRequestResponse getPaginador() {
    return paginador;
  }

  /**
   * Sets the value of the paginador property.
   * 
   * @param value allowed object is {@link FilterPageRequestResponse }
   * 
   */
  public void setPaginador(FilterPageRequestResponse value) {
    this.paginador = value;
  }

  /**
   * Gets the value of the resultados property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the resultados property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getResultados().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list {@link Usuario }
   * 
   * 
   */
  public List<Usuario> getResultados() {
    if (resultados == null) {
      resultados = new ArrayList<Usuario>();
    }
    return this.resultados;
  }

}
