
package es.mpt.dsic.inside.xml.eni.expediente.indice.contenido;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>
 * Java class for TipoIndiceContenido complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TipoIndiceContenido">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FechaIndiceElectronico" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;choice maxOccurs="unbounded">
 *           &lt;element name="DocumentoIndizado" type="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/indice-e/contenido}TipoDocumentoIndizado"/>
 *           &lt;element name="ExpedienteIndizado" type="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/indice-e/contenido}TipoIndiceContenido"/>
 *           &lt;element name="CarpetaIndizada" type="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/indice-e/contenido}TipoCarpetaIndizada"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="Id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoIndiceContenido", propOrder = {"fechaIndiceElectronico",
    "documentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada"})
public class TipoIndiceContenido {

  @XmlElement(name = "FechaIndiceElectronico", required = true)
  @XmlSchemaType(name = "dateTime")
  protected XMLGregorianCalendar fechaIndiceElectronico;
  @XmlElements({@XmlElement(name = "ExpedienteIndizado", type = TipoIndiceContenido.class),
      @XmlElement(name = "DocumentoIndizado", type = TipoDocumentoIndizado.class),
      @XmlElement(name = "CarpetaIndizada", type = TipoCarpetaIndizada.class)})
  protected List<Object> documentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada;
  @XmlAttribute(name = "Id")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlID
  @XmlSchemaType(name = "ID")
  protected String id;

  /**
   * Gets the value of the fechaIndiceElectronico property.
   * 
   * @return possible object is {@link XMLGregorianCalendar }
   * 
   */
  public XMLGregorianCalendar getFechaIndiceElectronico() {
    return fechaIndiceElectronico;
  }

  /**
   * Sets the value of the fechaIndiceElectronico property.
   * 
   * @param value allowed object is {@link XMLGregorianCalendar }
   * 
   */
  public void setFechaIndiceElectronico(XMLGregorianCalendar value) {
    this.fechaIndiceElectronico = value;
  }

  /**
   * Gets the value of the documentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the
   * documentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list {@link TipoIndiceContenido }
   * {@link TipoDocumentoIndizado } {@link TipoCarpetaIndizada }
   * 
   * 
   */
  public List<Object> getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada() {
    if (documentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada == null) {
      documentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada = new ArrayList<Object>();
    }
    return this.documentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada;
  }

  /**
   * Gets the value of the id property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getId() {
    return id;
  }

  /**
   * Sets the value of the id property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setId(String value) {
    this.id = value;
  }

}
