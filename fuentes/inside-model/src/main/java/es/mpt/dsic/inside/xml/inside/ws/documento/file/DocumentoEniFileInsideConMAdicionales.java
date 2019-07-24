
package es.mpt.dsic.inside.xml.inside.ws.documento.file;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import es.mpt.dsic.inside.xml.eni.documento.TipoDocumento;
import es.mpt.dsic.inside.xml.inside.TipoMetadatosAdicionales;


/**
 * <p>
 * Java class for DocumentoEniFileInsideConMAdicionales complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DocumentoEniFileInsideConMAdicionales">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e}documento"/>
 *         &lt;element name="metadatosAdicionales" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/metadatosAdicionales}TipoMetadatosAdicionales" minOccurs="0"/>
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
@XmlType(name = "DocumentoEniFileInsideConMAdicionales",
    propOrder = {"documento", "metadatosAdicionales", "documentoEniBytes"})
public class DocumentoEniFileInsideConMAdicionales {

  @XmlElement(namespace = "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e",
      required = true)
  protected TipoDocumento documento;
  protected TipoMetadatosAdicionales metadatosAdicionales;
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
