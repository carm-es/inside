
package es.mpt.dsic.inside.xml.inside.ws.documento.conversion.mtom;

import java.util.ArrayList;
import java.util.List;
import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import es.mpt.dsic.inside.xml.eni.documento.metadatos.TipoDocumental;
import es.mpt.dsic.inside.xml.eni.documento.metadatos.TipoEstadoElaboracion;


/**
 * <p>
 * Java class for TipoDocumentoConversionInsideMtom complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TipoDocumentoConversionInsideMtom">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contenido" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="firmadoConCertificado" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="metadatosEni">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="VersionNTI" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Identificador" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Organo" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *                   &lt;element name="FechaCaptura" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                   &lt;element name="OrigenCiudadanoAdministracion" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                   &lt;element name="EstadoElaboracion" type="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/metadatos}TipoEstadoElaboracion"/>
 *                   &lt;element name="TipoDocumental" type="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/metadatos}tipoDocumental"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="csv" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="valorCSV" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="regulacionCSV" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoDocumentoConversionInsideMtom",
    propOrder = {"contenido", "firmadoConCertificado", "metadatosEni", "csv"})
public class TipoDocumentoConversionInsideMtom {

  @XmlElement(required = true)
  @XmlMimeType("application/octet-stream")
  protected DataHandler contenido;
  protected boolean firmadoConCertificado;
  @XmlElement(required = true)
  protected TipoDocumentoConversionInsideMtom.MetadatosEni metadatosEni;
  protected TipoDocumentoConversionInsideMtom.Csv csv;

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
   * Gets the value of the firmadoConCertificado property.
   * 
   */
  public boolean isFirmadoConCertificado() {
    return firmadoConCertificado;
  }

  /**
   * Sets the value of the firmadoConCertificado property.
   * 
   */
  public void setFirmadoConCertificado(boolean value) {
    this.firmadoConCertificado = value;
  }

  /**
   * Gets the value of the metadatosEni property.
   * 
   * @return possible object is {@link TipoDocumentoConversionInsideMtom.MetadatosEni }
   * 
   */
  public TipoDocumentoConversionInsideMtom.MetadatosEni getMetadatosEni() {
    return metadatosEni;
  }

  /**
   * Sets the value of the metadatosEni property.
   * 
   * @param value allowed object is {@link TipoDocumentoConversionInsideMtom.MetadatosEni }
   * 
   */
  public void setMetadatosEni(TipoDocumentoConversionInsideMtom.MetadatosEni value) {
    this.metadatosEni = value;
  }

  /**
   * Gets the value of the csv property.
   * 
   * @return possible object is {@link TipoDocumentoConversionInsideMtom.Csv }
   * 
   */
  public TipoDocumentoConversionInsideMtom.Csv getCsv() {
    return csv;
  }

  /**
   * Sets the value of the csv property.
   * 
   * @param value allowed object is {@link TipoDocumentoConversionInsideMtom.Csv }
   * 
   */
  public void setCsv(TipoDocumentoConversionInsideMtom.Csv value) {
    this.csv = value;
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
   *   &lt;complexContent>
   *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
   *       &lt;sequence>
   *         &lt;element name="valorCSV" type="{http://www.w3.org/2001/XMLSchema}string"/>
   *         &lt;element name="regulacionCSV" type="{http://www.w3.org/2001/XMLSchema}string"/>
   *       &lt;/sequence>
   *     &lt;/restriction>
   *   &lt;/complexContent>
   * &lt;/complexType>
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = {"valorCSV", "regulacionCSV"})
  public static class Csv {

    @XmlElement(required = true)
    protected String valorCSV;
    @XmlElement(required = true)
    protected String regulacionCSV;

    /**
     * Gets the value of the valorCSV property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getValorCSV() {
      return valorCSV;
    }

    /**
     * Sets the value of the valorCSV property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setValorCSV(String value) {
      this.valorCSV = value;
    }

    /**
     * Gets the value of the regulacionCSV property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getRegulacionCSV() {
      return regulacionCSV;
    }

    /**
     * Sets the value of the regulacionCSV property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setRegulacionCSV(String value) {
      this.regulacionCSV = value;
    }

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
   *   &lt;complexContent>
   *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
   *       &lt;sequence>
   *         &lt;element name="VersionNTI" type="{http://www.w3.org/2001/XMLSchema}string"/>
   *         &lt;element name="Identificador" type="{http://www.w3.org/2001/XMLSchema}string"/>
   *         &lt;element name="Organo" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
   *         &lt;element name="FechaCaptura" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
   *         &lt;element name="OrigenCiudadanoAdministracion" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
   *         &lt;element name="EstadoElaboracion" type="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/metadatos}TipoEstadoElaboracion"/>
   *         &lt;element name="TipoDocumental" type="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/metadatos}tipoDocumental"/>
   *       &lt;/sequence>
   *     &lt;/restriction>
   *   &lt;/complexContent>
   * &lt;/complexType>
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = {"versionNTI", "identificador", "organo", "fechaCaptura",
      "origenCiudadanoAdministracion", "estadoElaboracion", "tipoDocumental"})
  public static class MetadatosEni {

    @XmlElement(name = "VersionNTI", required = true)
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
     * modification you make to the returned list will be present inside the JAXB object. This is
     * why there is not a <CODE>set</CODE> method for the organo property.
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

  }

}
