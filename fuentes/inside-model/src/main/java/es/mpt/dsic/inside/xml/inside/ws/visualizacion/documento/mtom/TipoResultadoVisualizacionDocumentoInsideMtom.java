
package es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.mtom;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Java class for TipoResultadoVisualizacionDocumentoInsideMtom complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TipoResultadoVisualizacionDocumentoInsideMtom">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contenido" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="mime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoResultadoVisualizacionDocumentoInsideMtom", propOrder = {"contenido", "mime"})
public class TipoResultadoVisualizacionDocumentoInsideMtom {

  @XmlElement(required = true)
  @XmlMimeType("application/octet-stream")
  protected DataHandler contenido;
  @XmlElement(required = true)
  protected String mime;

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
   * Gets the value of the mime property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getMime() {
    return mime;
  }

  /**
   * Sets the value of the mime property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setMime(String value) {
    this.mime = value;
  }

}
