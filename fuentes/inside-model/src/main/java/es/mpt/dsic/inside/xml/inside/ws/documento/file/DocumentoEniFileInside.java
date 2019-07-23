
package es.mpt.dsic.inside.xml.inside.ws.documento.file;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import es.mpt.dsic.inside.xml.eni.documento.TipoDocumento;


/**
 * <p>
 * Java class for DocumentoEniFileInside complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DocumentoEniFileInside">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e}documento"/>
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
@XmlType(name = "DocumentoEniFileInside", propOrder = {"documento", "documentoEniBytes"})
public class DocumentoEniFileInside {

  @XmlElement(namespace = "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e",
      required = true)
  protected TipoDocumento documento;
  @XmlElement(required = true)
  protected byte[] documentoEniBytes;

  /**
   * Gets the value of the documento property.
   * 
   * @return possible object is {@link TipoDocumento }
   * 
   */
  public TipoDocumento getDocumento() {
    return documento;
  }

  /**
   * Sets the value of the documento property.
   * 
   * @param value allowed object is {@link TipoDocumento }
   * 
   */
  public void setDocumento(TipoDocumento value) {
    this.documento = value;
  }

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
