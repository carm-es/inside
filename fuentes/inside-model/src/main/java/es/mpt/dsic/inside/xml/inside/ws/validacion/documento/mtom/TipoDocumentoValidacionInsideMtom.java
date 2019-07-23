
package es.mpt.dsic.inside.xml.inside.ws.validacion.documento.mtom;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Java class for TipoDocumentoValidacionInsideMtom complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TipoDocumentoValidacionInsideMtom">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contenido" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="opcionesValidacionDocumento" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/validacion/documento-e/mtom}TipoOpcionesValidacionDocumentoInsideMtom"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoDocumentoValidacionInsideMtom",
    propOrder = {"contenido", "opcionesValidacionDocumento"})
public class TipoDocumentoValidacionInsideMtom {

  @XmlElement(required = true)
  @XmlMimeType("application/octet-stream")
  protected DataHandler contenido;
  @XmlElement(required = true)
  protected TipoOpcionesValidacionDocumentoInsideMtom opcionesValidacionDocumento;

  /**
   * Gets the value of the contenido property.
   * 
   * @return possible object is {@link DataHandler }
   * 
   */
  public DataHandler getContenido() {
    return contenido;
  }

  /**
   * Sets the value of the contenido property.
   * 
   * @param value allowed object is {@link DataHandler }
   * 
   */
  public void setContenido(DataHandler value) {
    this.contenido = value;
  }

  /**
   * Gets the value of the opcionesValidacionDocumento property.
   * 
   * @return possible object is {@link TipoOpcionesValidacionDocumentoInsideMtom }
   * 
   */
  public TipoOpcionesValidacionDocumentoInsideMtom getOpcionesValidacionDocumento() {
    return opcionesValidacionDocumento;
  }

  /**
   * Sets the value of the opcionesValidacionDocumento property.
   * 
   * @param value allowed object is {@link TipoOpcionesValidacionDocumentoInsideMtom }
   * 
   */
  public void setOpcionesValidacionDocumento(TipoOpcionesValidacionDocumentoInsideMtom value) {
    this.opcionesValidacionDocumento = value;
  }

}
