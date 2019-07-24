
package es.mpt.dsic.inside.xml.inside.ws.documento;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Java class for TipoDocumentoEniFileInside complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TipoDocumentoEniFileInside">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="documentoEniBytes" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoDocumentoEniFileInside",
    namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/documentoEniFile",
    propOrder = {"documentoEniBytes"})
public class TipoDocumentoEniFileInside {

  @XmlElement(required = true)
  protected byte[] documentoEniBytes;

  /**
   * Gets the value of the documentoEniBytes property.
   * 
   * @return possible object is byte[]
   */
  public byte[] getDocumentoEniBytes() {
    return documentoEniBytes;
  }

  /**
   * Sets the value of the documentoEniBytes property.
   * 
   * @param value allowed object is byte[]
   */
  public void setDocumentoEniBytes(byte[] value) {
    this.documentoEniBytes = ((byte[]) value);
  }

}
