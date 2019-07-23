/*
 * Copyright (C) 2016 MINHAP, Gobierno de España This program is licensed and may be used, modified
 * and redistributed under the terms of the European Public License (EUPL), either version 1.1 or
 * (at your option) any later version as soon as they are approved by the European Commission.
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and more details. You
 * should have received a copy of the EUPL1.1 license along with this program; if not, you may find
 * it at http://joinup.ec.europa.eu/software/page/eupl/licence-eupl
 */


package es.mpt.dsic.inside.remisionnube.client.modelo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>
 * Clase Java para TipoDocumentoAltaInside complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TipoDocumentoAltaInside">
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
 *         &lt;element name="asociadoaExpediente" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/alta}TipoDocumentoAsociadoaExpediente" minOccurs="0"/>
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
@XmlType(name = "TipoDocumentoAltaInside",
    namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/alta",
    propOrder = {"contenido", "firmadoConCertificado", "metadatosEni", "metadatosAdicionales",
        "csv", "asociadoaExpediente", "firmadoEnServidor"})
public class TipoDocumentoAltaInside {

  @XmlElement(required = true)
  protected byte[] contenido;
  protected boolean firmadoConCertificado;
  @XmlElement(required = true)
  protected TipoDocumentoAltaInside.MetadatosEni metadatosEni;
  protected TipoMetadatosAdicionales metadatosAdicionales;
  protected TipoDocumentoAltaInside.Csv csv;
  protected TipoDocumentoAsociadoaExpediente asociadoaExpediente;
  protected boolean firmadoEnServidor;

  /**
   * Obtiene el valor de la propiedad contenido.
   * 
   * @return possible object is byte[]
   */
  public byte[] getContenido() {
    return contenido;
  }

  /**
   * Define el valor de la propiedad contenido.
   * 
   * @param value allowed object is byte[]
   */
  public void setContenido(byte[] value) {
    this.contenido = value;
  }

  /**
   * Obtiene el valor de la propiedad firmadoConCertificado.
   * 
   */
  public boolean isFirmadoConCertificado() {
    return firmadoConCertificado;
  }

  /**
   * Define el valor de la propiedad firmadoConCertificado.
   * 
   */
  public void setFirmadoConCertificado(boolean value) {
    this.firmadoConCertificado = value;
  }

  /**
   * Obtiene el valor de la propiedad metadatosEni.
   * 
   * @return possible object is {@link TipoDocumentoAltaInside.MetadatosEni }
   * 
   */
  public TipoDocumentoAltaInside.MetadatosEni getMetadatosEni() {
    return metadatosEni;
  }

  /**
   * Define el valor de la propiedad metadatosEni.
   * 
   * @param value allowed object is {@link TipoDocumentoAltaInside.MetadatosEni }
   * 
   */
  public void setMetadatosEni(TipoDocumentoAltaInside.MetadatosEni value) {
    this.metadatosEni = value;
  }

  /**
   * Obtiene el valor de la propiedad metadatosAdicionales.
   * 
   * @return possible object is {@link TipoMetadatosAdicionales }
   * 
   */
  public TipoMetadatosAdicionales getMetadatosAdicionales() {
    return metadatosAdicionales;
  }

  /**
   * Define el valor de la propiedad metadatosAdicionales.
   * 
   * @param value allowed object is {@link TipoMetadatosAdicionales }
   * 
   */
  public void setMetadatosAdicionales(TipoMetadatosAdicionales value) {
    this.metadatosAdicionales = value;
  }

  /**
   * Obtiene el valor de la propiedad csv.
   * 
   * @return possible object is {@link TipoDocumentoAltaInside.Csv }
   * 
   */
  public TipoDocumentoAltaInside.Csv getCsv() {
    return csv;
  }

  /**
   * Define el valor de la propiedad csv.
   * 
   * @param value allowed object is {@link TipoDocumentoAltaInside.Csv }
   * 
   */
  public void setCsv(TipoDocumentoAltaInside.Csv value) {
    this.csv = value;
  }

  /**
   * Obtiene el valor de la propiedad asociadoaExpediente.
   * 
   * @return possible object is {@link TipoDocumentoAsociadoaExpediente }
   * 
   */
  public TipoDocumentoAsociadoaExpediente getAsociadoaExpediente() {
    return asociadoaExpediente;
  }

  /**
   * Define el valor de la propiedad asociadoaExpediente.
   * 
   * @param value allowed object is {@link TipoDocumentoAsociadoaExpediente }
   * 
   */
  public void setAsociadoaExpediente(TipoDocumentoAsociadoaExpediente value) {
    this.asociadoaExpediente = value;
  }

  /**
   * Obtiene el valor de la propiedad firmadoEnServidor.
   * 
   */
  public boolean isFirmadoEnServidor() {
    return firmadoEnServidor;
  }

  /**
   * Define el valor de la propiedad firmadoEnServidor.
   * 
   */
  public void setFirmadoEnServidor(boolean value) {
    this.firmadoEnServidor = value;
  }


  /**
   * <p>
   * Clase Java para anonymous complex type.
   * 
   * <p>
   * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
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

    @XmlElement(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/alta",
        required = true)
    protected String valorCSV;
    @XmlElement(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/alta",
        required = true)
    protected String regulacionCSV;

    /**
     * Obtiene el valor de la propiedad valorCSV.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getValorCSV() {
      return valorCSV;
    }

    /**
     * Define el valor de la propiedad valorCSV.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setValorCSV(String value) {
      this.valorCSV = value;
    }

    /**
     * Obtiene el valor de la propiedad regulacionCSV.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getRegulacionCSV() {
      return regulacionCSV;
    }

    /**
     * Define el valor de la propiedad regulacionCSV.
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
   * Clase Java para anonymous complex type.
   * 
   * <p>
   * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
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

    @XmlElement(name = "Organo",
        namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/alta",
        required = true)
    protected List<String> organo;
    @XmlElement(name = "FechaCaptura",
        namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/alta",
        required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaCaptura;
    @XmlElement(name = "OrigenCiudadanoAdministracion",
        namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/alta")
    protected boolean origenCiudadanoAdministracion;
    @XmlElement(name = "TipoDocumental",
        namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/alta",
        required = true)
    @XmlSchemaType(name = "string")
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
     * Obtiene el valor de la propiedad fechaCaptura.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     * 
     */
    public XMLGregorianCalendar getFechaCaptura() {
      return fechaCaptura;
    }

    /**
     * Define el valor de la propiedad fechaCaptura.
     * 
     * @param value allowed object is {@link XMLGregorianCalendar }
     * 
     */
    public void setFechaCaptura(XMLGregorianCalendar value) {
      this.fechaCaptura = value;
    }

    /**
     * Obtiene el valor de la propiedad origenCiudadanoAdministracion.
     * 
     */
    public boolean isOrigenCiudadanoAdministracion() {
      return origenCiudadanoAdministracion;
    }

    /**
     * Define el valor de la propiedad origenCiudadanoAdministracion.
     * 
     */
    public void setOrigenCiudadanoAdministracion(boolean value) {
      this.origenCiudadanoAdministracion = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoDocumental.
     * 
     * @return possible object is {@link TipoDocumental }
     * 
     */
    public TipoDocumental getTipoDocumental() {
      return tipoDocumental;
    }

    /**
     * Define el valor de la propiedad tipoDocumental.
     * 
     * @param value allowed object is {@link TipoDocumental }
     * 
     */
    public void setTipoDocumental(TipoDocumental value) {
      this.tipoDocumental = value;
    }

  }

}
