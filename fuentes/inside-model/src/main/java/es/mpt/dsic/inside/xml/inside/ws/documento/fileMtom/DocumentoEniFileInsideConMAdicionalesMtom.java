
package es.mpt.dsic.inside.xml.inside.ws.documento.fileMtom;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlType;
import es.mpt.dsic.inside.xml.eni.documento.mtom.TipoDocumentoMtom;
import es.mpt.dsic.inside.xml.inside.TipoMetadatosAdicionales;


/**
 * <p>
 * Java class for DocumentoEniFileInsideConMAdicionalesMtom complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DocumentoEniFileInsideConMAdicionalesMtom">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/mtom}documentoMtom"/>
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
@XmlType(name = "DocumentoEniFileInsideConMAdicionalesMtom",
    propOrder = {"documentoMtom", "metadatosAdicionales", "documentoEniBytes"})
public class DocumentoEniFileInsideConMAdicionalesMtom {

  @XmlElement(namespace = "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/mtom",
      required = true)
  protected TipoDocumentoMtom documentoMtom;
  protected TipoMetadatosAdicionales metadatosAdicionales;
  @XmlElement(required = true)
  @XmlMimeType("application/octet-stream")
  protected DataHandler documentoEniBytes;

  /**
   * Gets the value of the documentoMtom property.
   * 
   * @return possible object is {@link TipoDocumentoMtom }
   * 
   */
  public TipoDocumentoMtom getDocumentoMtom() {
    return documentoMtom;
  }

  /**
   * Sets the value of the documentoMtom property.
   * 
   * @param value allowed object is {@link TipoDocumentoMtom }
   * 
   */
  public void setDocumentoMtom(TipoDocumentoMtom value) {
    this.documentoMtom = value;
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
   * @return possible object is {@link DataHandler }
   * 
   */
  public DataHandler getDocumentoEniBytes() {
    return documentoEniBytes;
  }

  /**
   * Sets the value of the documentoEniBytes property.
   * 
   * @param value allowed object is {@link DataHandler }
   * 
   */
  public void setDocumentoEniBytes(DataHandler value) {
    this.documentoEniBytes = value;
  }

}
