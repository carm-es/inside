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


package es.mpt.dsic.inside.service.sia.model.messages.v2_4.enviasia;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Clase Java para VOLUMENESTRAMITACIONES complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="VOLUMENESTRAMITACIONES">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="VOLUMENTRAMITACION" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ANIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="PERIODO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="VOLTOTAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="VOLELE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="VOLNOELE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="VOLCERTIFICADOELE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="VOLDNIELE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="VOLELEOTRO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "VOLUMENESTRAMITACIONES", propOrder = {"volumentramitacion"})
public class VOLUMENESTRAMITACIONES {

  @XmlElement(name = "VOLUMENTRAMITACION")
  protected List<VOLUMENESTRAMITACIONES.VOLUMENTRAMITACION> volumentramitacion;

  /**
   * Gets the value of the volumentramitacion property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the volumentramitacion property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getVOLUMENTRAMITACION().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link VOLUMENESTRAMITACIONES.VOLUMENTRAMITACION }
   * 
   * 
   */
  public List<VOLUMENESTRAMITACIONES.VOLUMENTRAMITACION> getVOLUMENTRAMITACION() {
    if (volumentramitacion == null) {
      volumentramitacion = new ArrayList<VOLUMENESTRAMITACIONES.VOLUMENTRAMITACION>();
    }
    return this.volumentramitacion;
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
   *         &lt;element name="ANIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *         &lt;element name="PERIODO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *         &lt;element name="VOLTOTAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *         &lt;element name="VOLELE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *         &lt;element name="VOLNOELE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *         &lt;element name="VOLCERTIFICADOELE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *         &lt;element name="VOLDNIELE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *         &lt;element name="VOLELEOTRO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *       &lt;/sequence>
   *     &lt;/restriction>
   *   &lt;/complexContent>
   * &lt;/complexType>
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = {"anio", "periodo", "voltotal", "volele", "volnoele",
      "volcertificadoele", "voldniele", "voleleotro"})
  public static class VOLUMENTRAMITACION {

    @XmlElement(name = "ANIO")
    protected String anio;
    @XmlElement(name = "PERIODO")
    protected String periodo;
    @XmlElement(name = "VOLTOTAL")
    protected String voltotal;
    @XmlElement(name = "VOLELE")
    protected String volele;
    @XmlElement(name = "VOLNOELE")
    protected String volnoele;
    @XmlElement(name = "VOLCERTIFICADOELE")
    protected String volcertificadoele;
    @XmlElement(name = "VOLDNIELE")
    protected String voldniele;
    @XmlElement(name = "VOLELEOTRO")
    protected String voleleotro;

    /**
     * Obtiene el valor de la propiedad anio.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getANIO() {
      return anio;
    }

    /**
     * Define el valor de la propiedad anio.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setANIO(String value) {
      this.anio = value;
    }

    /**
     * Obtiene el valor de la propiedad periodo.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getPERIODO() {
      return periodo;
    }

    /**
     * Define el valor de la propiedad periodo.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setPERIODO(String value) {
      this.periodo = value;
    }

    /**
     * Obtiene el valor de la propiedad voltotal.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getVOLTOTAL() {
      return voltotal;
    }

    /**
     * Define el valor de la propiedad voltotal.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setVOLTOTAL(String value) {
      this.voltotal = value;
    }

    /**
     * Obtiene el valor de la propiedad volele.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getVOLELE() {
      return volele;
    }

    /**
     * Define el valor de la propiedad volele.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setVOLELE(String value) {
      this.volele = value;
    }

    /**
     * Obtiene el valor de la propiedad volnoele.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getVOLNOELE() {
      return volnoele;
    }

    /**
     * Define el valor de la propiedad volnoele.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setVOLNOELE(String value) {
      this.volnoele = value;
    }

    /**
     * Obtiene el valor de la propiedad volcertificadoele.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getVOLCERTIFICADOELE() {
      return volcertificadoele;
    }

    /**
     * Define el valor de la propiedad volcertificadoele.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setVOLCERTIFICADOELE(String value) {
      this.volcertificadoele = value;
    }

    /**
     * Obtiene el valor de la propiedad voldniele.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getVOLDNIELE() {
      return voldniele;
    }

    /**
     * Define el valor de la propiedad voldniele.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setVOLDNIELE(String value) {
      this.voldniele = value;
    }

    /**
     * Obtiene el valor de la propiedad voleleotro.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getVOLELEOTRO() {
      return voleleotro;
    }

    /**
     * Define el valor de la propiedad voleleotro.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setVOLELEOTRO(String value) {
      this.voleleotro = value;
    }

  }

}
