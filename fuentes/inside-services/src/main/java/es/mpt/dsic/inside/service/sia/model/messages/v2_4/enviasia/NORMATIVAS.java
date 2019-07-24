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
 * Clase Java para NORMATIVAS complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="NORMATIVAS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NORMATIVA" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="CODTIPONORMATIVA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="DESTIPONORMATIVA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="CODRANGO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="DESRANGO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="NUMERODISPOSICION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="TITULO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "NORMATIVAS", propOrder = {"normativa"})
public class NORMATIVAS {

  @XmlElement(name = "NORMATIVA")
  protected List<NORMATIVAS.NORMATIVA> normativa;

  /**
   * Gets the value of the normativa property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the normativa property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getNORMATIVA().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list {@link NORMATIVAS.NORMATIVA }
   * 
   * 
   */
  public List<NORMATIVAS.NORMATIVA> getNORMATIVA() {
    if (normativa == null) {
      normativa = new ArrayList<NORMATIVAS.NORMATIVA>();
    }
    return this.normativa;
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
   *         &lt;element name="CODTIPONORMATIVA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *         &lt;element name="DESTIPONORMATIVA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *         &lt;element name="CODRANGO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *         &lt;element name="DESRANGO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *         &lt;element name="NUMERODISPOSICION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *         &lt;element name="TITULO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *       &lt;/sequence>
   *     &lt;/restriction>
   *   &lt;/complexContent>
   * &lt;/complexType>
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = {"codtiponormativa", "destiponormativa", "codrango", "desrango",
      "numerodisposicion", "titulo"})
  public static class NORMATIVA {

    @XmlElement(name = "CODTIPONORMATIVA")
    protected String codtiponormativa;
    @XmlElement(name = "DESTIPONORMATIVA")
    protected String destiponormativa;
    @XmlElement(name = "CODRANGO")
    protected String codrango;
    @XmlElement(name = "DESRANGO")
    protected String desrango;
    @XmlElement(name = "NUMERODISPOSICION")
    protected String numerodisposicion;
    @XmlElement(name = "TITULO")
    protected String titulo;

    /**
     * Obtiene el valor de la propiedad codtiponormativa.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCODTIPONORMATIVA() {
      return codtiponormativa;
    }

    /**
     * Define el valor de la propiedad codtiponormativa.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setCODTIPONORMATIVA(String value) {
      this.codtiponormativa = value;
    }

    /**
     * Obtiene el valor de la propiedad destiponormativa.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getDESTIPONORMATIVA() {
      return destiponormativa;
    }

    /**
     * Define el valor de la propiedad destiponormativa.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setDESTIPONORMATIVA(String value) {
      this.destiponormativa = value;
    }

    /**
     * Obtiene el valor de la propiedad codrango.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCODRANGO() {
      return codrango;
    }

    /**
     * Define el valor de la propiedad codrango.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setCODRANGO(String value) {
      this.codrango = value;
    }

    /**
     * Obtiene el valor de la propiedad desrango.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getDESRANGO() {
      return desrango;
    }

    /**
     * Define el valor de la propiedad desrango.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setDESRANGO(String value) {
      this.desrango = value;
    }

    /**
     * Obtiene el valor de la propiedad numerodisposicion.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getNUMERODISPOSICION() {
      return numerodisposicion;
    }

    /**
     * Define el valor de la propiedad numerodisposicion.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setNUMERODISPOSICION(String value) {
      this.numerodisposicion = value;
    }

    /**
     * Obtiene el valor de la propiedad titulo.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getTITULO() {
      return titulo;
    }

    /**
     * Define el valor de la propiedad titulo.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setTITULO(String value) {
      this.titulo = value;
    }

  }

}
