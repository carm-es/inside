
package es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import es.mpt.dsic.inside.xml.eni.documento.TipoDocumento;


/**
 * <p>
 * Java class for TipoDocumentoEniBinarioOTipo complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TipoDocumentoEniBinarioOTipo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="documentoEniBinario" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="documentoEniTipo" type="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e}TipoDocumento"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoDocumentoEniBinarioOTipo",
    propOrder = {"documentoEniBinario", "documentoEniTipo"})
public class TipoDocumentoEniBinarioOTipo {

  protected byte[] documentoEniBinario;
  protected TipoDocumento documentoEniTipo;

  /**
   * Gets the value of the documentoEniBinario property.
   * 
   * @return possible object is byte[]
   */
  public byte[] getDocumentoEniBinario() {
    return documentoEniBinario;
  }

  /**
   * Sets the value of the documentoEniBinario property.
   * 
   * @param value allowed object is byte[]
   */
  public void setDocumentoEniBinario(byte[] value) {
    this.documentoEniBinario = ((byte[]) value);
  }

  /**
   * Gets the value of the documentoEniTipo property.
   * 
   * @return possible object is {@link TipoDocumento }
   * 
   */
  public TipoDocumento getDocumentoEniTipo() {
    return documentoEniTipo;
  }

  /**
   * Sets the value of the documentoEniTipo property.
   * 
   * @param value allowed object is {@link TipoDocumento }
   * 
   */
  public void setDocumentoEniTipo(TipoDocumento value) {
    this.documentoEniTipo = value;
  }

}
