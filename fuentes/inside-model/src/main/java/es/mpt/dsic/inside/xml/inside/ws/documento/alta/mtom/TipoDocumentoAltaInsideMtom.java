
package es.mpt.dsic.inside.xml.inside.ws.documento.alta.mtom;

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
import es.mpt.dsic.inside.xml.inside.TipoMetadatosAdicionales;


/**
 * <p>
 * Java class for TipoDocumentoAltaInsideMtom complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TipoDocumentoAltaInsideMtom">
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
 *                   &lt;element name="Organo" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *                   &lt;element name="FechaCaptura" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                   &lt;element name="OrigenCiudadanoAdministracion" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                   &lt;element name="TipoDocumental" type="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/metadatos}tipoDocumental"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="metadatosAdicionales" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/metadatosAdicionales}TipoMetadatosAdicionales" minOccurs="0"/>
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
 *         &lt;element name="asociadoaExpediente" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/alta/mtom}TipoDocumentoAsociadoaExpedienteMtom" minOccurs="0"/>
 *         &lt;element name="firmadoEnServidor" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoDocumentoAltaInsideMtom", propOrder = {"contenido", "firmadoConCertificado",
    "metadatosEni", "metadatosAdicionales", "csv", "asociadoaExpediente", "firmadoEnServidor"})
public class TipoDocumentoAltaInsideMtom {

  @XmlElement(required = true)
  @XmlMimeType("application/octet-stream")
  protected DataHandler contenido;
  protected boolean firmadoConCertificado;
  @XmlElement(required = true)
  protected TipoDocumentoAltaInsideMtom.MetadatosEni metadatosEni;
  protected TipoMetadatosAdicionales metadatosAdicionales;
  protected TipoDocumentoAltaInsideMtom.Csv csv;
  protected TipoDocumentoAsociadoaExpedienteMtom asociadoaExpediente;
  protected boolean firmadoEnServidor;

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
   * @return possible object is {@link TipoDocumentoAltaInsideMtom.MetadatosEni }
   * 
   */
  public TipoDocumentoAltaInsideMtom.MetadatosEni getMetadatosEni() {
    return metadatosEni;
  }

  /**
   * Sets the value of the metadatosEni property.
   * 
   * @param value allowed object is {@link TipoDocumentoAltaInsideMtom.MetadatosEni }
   * 
   */
  public void setMetadatosEni(TipoDocumentoAltaInsideMtom.MetadatosEni value) {
    this.metadatosEni = value;
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

  /**
   * Gets the value of the csv property.
   * 
   * @return possible object is {@link TipoDocumentoAltaInsideMtom.Csv }
   * 
   */
  public TipoDocumentoAltaInsideMtom.Csv getCsv() {
    return csv;
  }

  /**
   * Sets the value of the csv property.
   * 
   * @param value allowed object is {@link TipoDocumentoAltaInsideMtom.Csv }
   * 
   */
  public void setCsv(TipoDocumentoAltaInsideMtom.Csv value) {
    this.csv = value;
  }

  /**
   * Gets the value of the asociadoaExpediente property.
   * 
   * @return possible object is {@link TipoDocumentoAsociadoaExpedienteMtom }
   * 
   */
  public TipoDocumentoAsociadoaExpedienteMtom getAsociadoaExpediente() {
    return asociadoaExpediente;
  }

  /**
   * Sets the value of the asociadoaExpediente property.
   * 
   * @param value allowed object is {@link TipoDocumentoAsociadoaExpedienteMtom }
   * 
   */
  public void setAsociadoaExpediente(TipoDocumentoAsociadoaExpedienteMtom value) {
    this.asociadoaExpediente = value;
  }

  /**
   * Gets the value of the firmadoEnServidor property.
   * 
   */
  public boolean isFirmadoEnServidor() {
    return firmadoEnServidor;
  }

  /**
   * Sets the value of the firmadoEnServidor property.
   * 
   */
  public void setFirmadoEnServidor(boolean value) {
    this.firmadoEnServidor = value;
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
   *         &lt;element name="Organo" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
   *         &lt;element name="FechaCaptura" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
   *         &lt;element name="OrigenCiudadanoAdministracion" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
  @XmlType(name = "",
      propOrder = {"organo", "fechaCaptura", "origenCiudadanoAdministracion", "tipoDocumental"})
  public static class MetadatosEni {

    @XmlElement(name = "Organo", required = true)
    protected List<String> organo;
    @XmlElement(name = "FechaCaptura", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaCaptura;
    @XmlElement(name = "OrigenCiudadanoAdministracion")
    protected boolean origenCiudadanoAdministracion;
    @XmlElement(name = "TipoDocumental", required = true)
    protected TipoDocumental tipoDocumental;

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
