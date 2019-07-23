
package es.mpt.dsic.inside.xml.eni.documento.metadatos;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>
 * Java class for TipoMetadatos complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TipoMetadatos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="VersionNTI" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *         &lt;element name="Identificador" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Organo" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="FechaCaptura" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="OrigenCiudadanoAdministracion" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="EstadoElaboracion" type="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/metadatos}TipoEstadoElaboracion"/>
 *         &lt;element name="TipoDocumental" type="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/metadatos}tipoDocumental"/>
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
@XmlType(name = "TipoMetadatos", propOrder = {"versionNTI", "identificador", "organo",
    "fechaCaptura", "origenCiudadanoAdministracion", "estadoElaboracion", "tipoDocumental"})
public class TipoMetadatos {

  @XmlElement(name = "VersionNTI", required = true)
  @XmlSchemaType(name = "anyURI")
  protected String versionNTI;
  @XmlElement(name = "Identificador", required = true)
  protected String identificador;
  @XmlElement(name = "Organo", required = true)
  protected List<String> organo;
  @XmlElement(name = "FechaCaptura", required = true)
  @XmlSchemaType(name = "dateTime")
  protected XMLGregorianCalendar fechaCaptura;
  @XmlElement(name = "OrigenCiudadanoAdministracion")
  protected boolean origenCiudadanoAdministracion;
  @XmlElement(name = "EstadoElaboracion", required = true)
  protected TipoEstadoElaboracion estadoElaboracion;
  @XmlElement(name = "TipoDocumental", required = true)
  protected TipoDocumental tipoDocumental;
  @XmlAttribute(name = "Id")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlID
  @XmlSchemaType(name = "ID")
  protected String id;

  /**
   * Gets the value of the versionNTI property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getVersionNTI() {
    return versionNTI;
  }

  /**
   * Sets the value of the versionNTI property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setVersionNTI(String value) {
    this.versionNTI = value;
  }

  /**
   * Gets the value of the identificador property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getIdentificador() {
    return identificador;
  }

  /**
   * Sets the value of the identificador property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setIdentificador(String value) {
    this.identificador = value;
  }

  /**
   * Gets the value of the organo property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the organo property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getOrgano().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list {@link String }
   * 
   * 
   */
  public List<String> getOrgano() {
    if (organo == null) {
      organo = new ArrayList<String>();
    }
    return this.organo;
  }

  /**
   * Gets the value of the fechaCaptura property.
   * 
   * @return possible object is {@link XMLGregorianCalendar }
   * 
   */
  public XMLGregorianCalendar getFechaCaptura() {
    return fechaCaptura;
  }

  /**
   * Sets the value of the fechaCaptura property.
   * 
   * @param value allowed object is {@link XMLGregorianCalendar }
   * 
   */
  public void setFechaCaptura(XMLGregorianCalendar value) {
    this.fechaCaptura = value;
  }

  /**
   * Gets the value of the origenCiudadanoAdministracion property.
   * 
   */
  public boolean isOrigenCiudadanoAdministracion() {
    return origenCiudadanoAdministracion;
  }

  /**
   * Sets the value of the origenCiudadanoAdministracion property.
   * 
   */
  public void setOrigenCiudadanoAdministracion(boolean value) {
    this.origenCiudadanoAdministracion = value;
  }

  /**
   * Gets the value of the estadoElaboracion property.
   * 
   * @return possible object is {@link TipoEstadoElaboracion }
   * 
   */
  public TipoEstadoElaboracion getEstadoElaboracion() {
    return estadoElaboracion;
  }

  /**
   * Sets the value of the estadoElaboracion property.
   * 
   * @param value allowed object is {@link TipoEstadoElaboracion }
   * 
   */
  public void setEstadoElaboracion(TipoEstadoElaboracion value) {
    this.estadoElaboracion = value;
  }

  /**
   * Gets the value of the tipoDocumental property.
   * 
   * @return possible object is {@link TipoDocumental }
   * 
   */
  public TipoDocumental getTipoDocumental() {
    return tipoDocumental;
  }

  /**
   * Sets the value of the tipoDocumental property.
   * 
   * @param value allowed object is {@link TipoDocumental }
   * 
   */
  public void setTipoDocumental(TipoDocumental value) {
    this.tipoDocumental = value;
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
