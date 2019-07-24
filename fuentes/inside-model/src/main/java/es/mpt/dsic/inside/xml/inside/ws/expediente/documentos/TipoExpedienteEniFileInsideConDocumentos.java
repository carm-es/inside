
package es.mpt.dsic.inside.xml.inside.ws.expediente.documentos;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import es.mpt.dsic.inside.xml.inside.ws.documento.TipoDocumentoEniFileInside;


/**
 * <p>
 * Java class for TipoExpedienteEniFileInsideConDocumentos complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TipoExpedienteEniFileInsideConDocumentos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="expedienteEniBytes" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="documentosEniFile" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/documentoEniFile}TipoDocumentoEniFileInside" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoExpedienteEniFileInsideConDocumentos",
    propOrder = {"expedienteEniBytes", "documentosEniFile"})
public class TipoExpedienteEniFileInsideConDocumentos {

  @XmlElement(required = true)
  protected byte[] expedienteEniBytes;
  @XmlElement(required = true)
  protected List<TipoDocumentoEniFileInside> documentosEniFile;

  /**
   * Gets the value of the expedienteEniBytes property.
   * 
   * @return possible object is byte[]
   */
  public byte[] getExpedienteEniBytes() {
    return expedienteEniBytes;
  }

  /**
   * Sets the value of the expedienteEniBytes property.
   * 
   * @param value allowed object is byte[]
   */
  public void setExpedienteEniBytes(byte[] value) {
    this.expedienteEniBytes = ((byte[]) value);
  }

  /**
   * Gets the value of the documentosEniFile property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the documentosEniFile property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getDocumentosEniFile().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list {@link TipoDocumentoEniFileInside }
   * 
   * 
   */
  public List<TipoDocumentoEniFileInside> getDocumentosEniFile() {
    if (documentosEniFile == null) {
      documentosEniFile = new ArrayList<TipoDocumentoEniFileInside>();
    }
    return this.documentosEniFile;
  }

}
