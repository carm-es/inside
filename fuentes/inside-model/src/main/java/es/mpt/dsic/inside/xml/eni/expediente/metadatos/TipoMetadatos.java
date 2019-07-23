
package es.mpt.dsic.inside.xml.eni.expediente.metadatos;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
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
 *         &lt;element name="FechaAperturaExpediente" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Clasificacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Estado">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/metadatos>enumeracionEstados">
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Interesado" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
    "fechaAperturaExpediente", "clasificacion", "estado", "interesado"})
public class TipoMetadatos {

  @XmlElement(name = "VersionNTI", required = true)
  @XmlSchemaType(name = "anyURI")
  protected String versionNTI;
  @XmlElement(name = "Identificador", required = true)
  protected String identificador;
  @XmlElement(name = "Organo", required = true)
  protected List<String> organo;
  @XmlElement(name = "FechaAperturaExpediente", required = true)
  @XmlSchemaType(name = "dateTime")
  protected XMLGregorianCalendar fechaAperturaExpediente;
  @XmlElement(name = "Clasificacion", required = true)
  protected String clasificacion;
  @XmlElement(name = "Estado", required = true)
  protected TipoMetadatos.Estado estado;
  @XmlElement(name = "Interesado")
  protected List<String> interesado;
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
   * Gets the value of the fechaAperturaExpediente property.
   * 
   * @return possible object is {@link XMLGregorianCalendar }
   * 
   */
  public XMLGregorianCalendar getFechaAperturaExpediente() {
    return fechaAperturaExpediente;
  }

  /**
   * Sets the value of the fechaAperturaExpediente property.
   * 
   * @param value allowed object is {@link XMLGregorianCalendar }
   * 
   */
  public void setFechaAperturaExpediente(XMLGregorianCalendar value) {
    this.fechaAperturaExpediente = value;
  }

  /**
   * Gets the value of the clasificacion property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getClasificacion() {
    return clasificacion;
  }

  /**
   * Sets the value of the clasificacion property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setClasificacion(String value) {
    this.clasificacion = value;
  }

  /**
   * Gets the value of the estado property.
   * 
   * @return possible object is {@link TipoMetadatos.Estado }
   * 
   */
  public TipoMetadatos.Estado getEstado() {
    return estado;
  }

  /**
   * Sets the value of the estado property.
   * 
   * @param value allowed object is {@link TipoMetadatos.Estado }
   * 
   */
  public void setEstado(TipoMetadatos.Estado value) {
    this.estado = value;
  }

  /**
   * Gets the value of the interesado property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the interesado property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getInteresado().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list {@link String }
   * 
   * 
   */
  public List<String> getInteresado() {
    if (interesado == null) {
      interesado = new ArrayList<String>();
    }
    return this.interesado;
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


  /**
   * <p>
   * Java class for anonymous complex type.
   * 
   * <p>
   * The following schema fragment specifies the expected content contained within this class.
   * 
   * <pre>
   * &lt;complexType>
   *   &lt;simpleContent>
   *     &lt;extension base="&lt;http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/metadatos>enumeracionEstados">
   *     &lt;/extension>
   *   &lt;/simpleContent>
   * &lt;/complexType>
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = {"value"})
  public static class Estado {

    @XmlValue
    protected EnumeracionEstados value;

    /**
     * Gets the value of the value property.
     * 
     * @return possible object is {@link EnumeracionEstados }
     * 
     */
    public EnumeracionEstados getValue() {
      return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value allowed object is {@link EnumeracionEstados }
     * 
     */
    public void setValue(EnumeracionEstados value) {
      this.value = value;
    }

  }

}
