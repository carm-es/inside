
package es.mpt.dsic.inside.xml.inside.ws.remisionEnLaNube;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Java class for PeticionRemisionAJusticiaType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PeticionRemisionAJusticiaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idexp_eni" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dir3Juzgado" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="datosRemisionJusticia" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/remisionNube}DatosRemisionJusticiaType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PeticionRemisionAJusticiaType",
    propOrder = {"idexpEni", "dir3Juzgado", "datosRemisionJusticia"})
public class PeticionRemisionAJusticiaType {

  @XmlElement(name = "idexp_eni", required = true)
  protected String idexpEni;
  @XmlElement(required = true)
  protected String dir3Juzgado;
  @XmlElement(required = true)
  protected DatosRemisionJusticiaType datosRemisionJusticia;

  /**
   * Gets the value of the idexpEni property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getIdexpEni() {
    return idexpEni;
  }

  /**
   * Sets the value of the idexpEni property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setIdexpEni(String value) {
    this.idexpEni = value;
  }

  /**
   * Gets the value of the dir3Juzgado property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDir3Juzgado() {
    return dir3Juzgado;
  }

  /**
   * Sets the value of the dir3Juzgado property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDir3Juzgado(String value) {
    this.dir3Juzgado = value;
  }

  /**
   * Gets the value of the datosRemisionJusticia property.
   * 
   * @return possible object is {@link DatosRemisionJusticiaType }
   * 
   */
  public DatosRemisionJusticiaType getDatosRemisionJusticia() {
    return datosRemisionJusticia;
  }

  /**
   * Sets the value of the datosRemisionJusticia property.
   * 
   * @param value allowed object is {@link DatosRemisionJusticiaType }
   * 
   */
  public void setDatosRemisionJusticia(DatosRemisionJusticiaType value) {
    this.datosRemisionJusticia = value;
  }

}
