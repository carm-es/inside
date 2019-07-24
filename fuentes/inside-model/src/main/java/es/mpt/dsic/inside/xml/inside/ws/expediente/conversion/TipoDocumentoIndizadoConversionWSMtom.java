
package es.mpt.dsic.inside.xml.inside.ws.expediente.conversion;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>
 * Java class for TipoDocumentoIndizadoConversionWSMtom complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TipoDocumentoIndizadoConversionWSMtom">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contenido" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="FechaIncorporacionExpediente" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="OrdenDocumentoExpediente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoDocumentoIndizadoConversionWSMtom",
    propOrder = {"contenido", "fechaIncorporacionExpediente", "ordenDocumentoExpediente"})
public class TipoDocumentoIndizadoConversionWSMtom {

  @XmlElement(required = true)
  @XmlMimeType("application/octet-stream")
  protected DataHandler contenido;
  @XmlElement(name = "FechaIncorporacionExpediente")
  @XmlSchemaType(name = "dateTime")
  protected XMLGregorianCalendar fechaIncorporacionExpediente;
  @XmlElement(name = "OrdenDocumentoExpediente")
  protected String ordenDocumentoExpediente;

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
   * Gets the value of the fechaIncorporacionExpediente property.
   * 
   * @return possible object is {@link XMLGregorianCalendar }
   * 
   */
  public XMLGregorianCalendar getFechaIncorporacionExpediente() {
    return fechaIncorporacionExpediente;
  }

  /**
   * Sets the value of the fechaIncorporacionExpediente property.
   * 
   * @param value allowed object is {@link XMLGregorianCalendar }
   * 
   */
  public void setFechaIncorporacionExpediente(XMLGregorianCalendar value) {
    this.fechaIncorporacionExpediente = value;
  }

  /**
   * Gets the value of the ordenDocumentoExpediente property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getOrdenDocumentoExpediente() {
    return ordenDocumentoExpediente;
  }

  /**
   * Sets the value of the ordenDocumentoExpediente property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setOrdenDocumentoExpediente(String value) {
    this.ordenDocumentoExpediente = value;
  }

}
