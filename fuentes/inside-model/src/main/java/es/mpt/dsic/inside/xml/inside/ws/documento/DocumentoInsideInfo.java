
package es.mpt.dsic.inside.xml.inside.ws.documento;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>
 * Java class for DocumentoInsideInfo complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DocumentoInsideInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Identificador" type="{http://www.w3.org/2001/XMLSchema}ID"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentoInsideInfo", propOrder = {"identificador"})
public class DocumentoInsideInfo {

  @XmlElement(name = "Identificador", required = true)
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlID
  @XmlSchemaType(name = "ID")
  protected String identificador;

  /**
   * Gets the value of the identificador property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getIdentificador() {
    return identificador;
  }

  /**
   * Sets the value of the identificador property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setIdentificador(String value) {
    this.identificador = value;
  }

}
