
package es.mpt.dsic.inside.xml.inside;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import es.mpt.dsic.inside.xml.eni.documento.metadatos.TipoMetadatos;


/**
 * <p>
 * Java class for DocumentoInsideMetadatos complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DocumentoInsideMetadatos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="metadatosDocumentoENI" type="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/metadatos}TipoMetadatos"/>
 *         &lt;element name="metadatosAdicionales" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/metadatosAdicionales}TipoMetadatosAdicionales"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentoInsideMetadatos",
    namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/metadatos",
    propOrder = {"metadatosDocumentoENI", "metadatosAdicionales"})
public class DocumentoInsideMetadatos {

  @XmlElement(required = true)
  protected TipoMetadatos metadatosDocumentoENI;
  @XmlElement(required = true)
  protected TipoMetadatosAdicionales metadatosAdicionales;

  /**
   * Gets the value of the metadatosDocumentoENI property.
   * 
   * @return possible object is {@link TipoMetadatos }
   * 
   */
  public TipoMetadatos getMetadatosDocumentoENI() {
    return metadatosDocumentoENI;
  }

  /**
   * Sets the value of the metadatosDocumentoENI property.
   * 
   * @param value allowed object is {@link TipoMetadatos }
   * 
   */
  public void setMetadatosDocumentoENI(TipoMetadatos value) {
    this.metadatosDocumentoENI = value;
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

}
