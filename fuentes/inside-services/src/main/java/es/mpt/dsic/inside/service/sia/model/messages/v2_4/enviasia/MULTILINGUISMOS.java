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
 * Clase Java para MULTILINGUISMOS complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="MULTILINGUISMOS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MULTILINGUISMO" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="IDIOMA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="DESIDIOMA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="OTRO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="TIPOCONTENIDO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "MULTILINGUISMOS", propOrder = {"multilinguismo"})
public class MULTILINGUISMOS {

  @XmlElement(name = "MULTILINGUISMO")
  protected List<MULTILINGUISMOS.MULTILINGUISMO> multilinguismo;

  /**
   * Gets the value of the multilinguismo property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the multilinguismo property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getMULTILINGUISMO().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link MULTILINGUISMOS.MULTILINGUISMO }
   * 
   * 
   */
  public List<MULTILINGUISMOS.MULTILINGUISMO> getMULTILINGUISMO() {
    if (multilinguismo == null) {
      multilinguismo = new ArrayList<MULTILINGUISMOS.MULTILINGUISMO>();
    }
    return this.multilinguismo;
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
   *         &lt;element name="IDIOMA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *         &lt;element name="DESIDIOMA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *         &lt;element name="OTRO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *         &lt;element name="TIPOCONTENIDO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *       &lt;/sequence>
   *     &lt;/restriction>
   *   &lt;/complexContent>
   * &lt;/complexType>
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = {"idioma", "desidioma", "otro", "tipocontenido"})
  public static class MULTILINGUISMO {

    @XmlElement(name = "IDIOMA")
    protected String idioma;
    @XmlElement(name = "DESIDIOMA")
    protected String desidioma;
    @XmlElement(name = "OTRO")
    protected String otro;
    @XmlElement(name = "TIPOCONTENIDO")
    protected String tipocontenido;

    /**
     * Obtiene el valor de la propiedad idioma.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getIDIOMA() {
      return idioma;
    }

    /**
     * Define el valor de la propiedad idioma.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setIDIOMA(String value) {
      this.idioma = value;
    }

    /**
     * Obtiene el valor de la propiedad desidioma.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getDESIDIOMA() {
      return desidioma;
    }

    /**
     * Define el valor de la propiedad desidioma.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setDESIDIOMA(String value) {
      this.desidioma = value;
    }

    /**
     * Obtiene el valor de la propiedad otro.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getOTRO() {
      return otro;
    }

    /**
     * Define el valor de la propiedad otro.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setOTRO(String value) {
      this.otro = value;
    }

    /**
     * Obtiene el valor de la propiedad tipocontenido.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getTIPOCONTENIDO() {
      return tipocontenido;
    }

    /**
     * Define el valor de la propiedad tipocontenido.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setTIPOCONTENIDO(String value) {
      this.tipocontenido = value;
    }

  }

}
