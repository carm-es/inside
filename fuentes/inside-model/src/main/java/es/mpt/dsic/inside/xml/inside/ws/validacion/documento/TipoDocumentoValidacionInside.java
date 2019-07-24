
package es.mpt.dsic.inside.xml.inside.ws.validacion.documento;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Java class for TipoDocumentoValidacionInside complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TipoDocumentoValidacionInside">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contenido" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="opcionesValidacionDocumento" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/validacion/documento-e}TipoOpcionesValidacionDocumentoInside"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoDocumentoValidacionInside",
    propOrder = {"contenido", "opcionesValidacionDocumento"})
public class TipoDocumentoValidacionInside {

  @XmlElement(required = true)
  protected byte[] contenido;
  @XmlElement(required = true)
  protected TipoOpcionesValidacionDocumentoInside opcionesValidacionDocumento;

  /**
   * Gets the value of the contenido property.
   * 
   * @return possible object is byte[]
   */
  public byte[] getContenido() {
    return contenido;
  }

  /**
   * Sets the value of the contenido property.
   * 
   * @param value allowed object is byte[]
   */
  public void setContenido(byte[] value) {
    this.contenido = ((byte[]) value);
  }

  /**
   * Gets the value of the opcionesValidacionDocumento property.
   * 
   * @return possible object is {@link TipoOpcionesValidacionDocumentoInside }
   * 
   */
  public TipoOpcionesValidacionDocumentoInside getOpcionesValidacionDocumento() {
    return opcionesValidacionDocumento;
  }

  /**
   * Sets the value of the opcionesValidacionDocumento property.
   * 
   * @param value allowed object is {@link TipoOpcionesValidacionDocumentoInside }
   * 
   */
  public void setOpcionesValidacionDocumento(TipoOpcionesValidacionDocumentoInside value) {
    this.opcionesValidacionDocumento = value;
  }

}
