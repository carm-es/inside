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
 * Clase Java para SISTEMASIDENTIFICACION complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="SISTEMASIDENTIFICACION">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SISTEMAIDENTIFICACION" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="CODSISTEMAIDENTIFICACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="DESSISTEMAIDENTIFICACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="OTROSISTEMAIDENTIFICACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "SISTEMASIDENTIFICACION", propOrder = {"sistemaidentificacion"})
public class SISTEMASIDENTIFICACION {

  @XmlElement(name = "SISTEMAIDENTIFICACION")
  protected List<SISTEMASIDENTIFICACION.SISTEMAIDENTIFICACION> sistemaidentificacion;

  /**
   * Gets the value of the sistemaidentificacion property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the sistemaidentificacion property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getSISTEMAIDENTIFICACION().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link SISTEMASIDENTIFICACION.SISTEMAIDENTIFICACION }
   * 
   * 
   */
  public List<SISTEMASIDENTIFICACION.SISTEMAIDENTIFICACION> getSISTEMAIDENTIFICACION() {
    if (sistemaidentificacion == null) {
      sistemaidentificacion = new ArrayList<SISTEMASIDENTIFICACION.SISTEMAIDENTIFICACION>();
    }
    return this.sistemaidentificacion;
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
   *         &lt;element name="CODSISTEMAIDENTIFICACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *         &lt;element name="DESSISTEMAIDENTIFICACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *         &lt;element name="OTROSISTEMAIDENTIFICACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *       &lt;/sequence>
   *     &lt;/restriction>
   *   &lt;/complexContent>
   * &lt;/complexType>
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = {"codsistemaidentificacion", "dessistemaidentificacion",
      "otrosistemaidentificacion"})
  public static class SISTEMAIDENTIFICACION {

    @XmlElement(name = "CODSISTEMAIDENTIFICACION")
    protected String codsistemaidentificacion;
    @XmlElement(name = "DESSISTEMAIDENTIFICACION")
    protected String dessistemaidentificacion;
    @XmlElement(name = "OTROSISTEMAIDENTIFICACION")
    protected String otrosistemaidentificacion;

    /**
     * Obtiene el valor de la propiedad codsistemaidentificacion.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCODSISTEMAIDENTIFICACION() {
      return codsistemaidentificacion;
    }

    /**
     * Define el valor de la propiedad codsistemaidentificacion.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setCODSISTEMAIDENTIFICACION(String value) {
      this.codsistemaidentificacion = value;
    }

    /**
     * Obtiene el valor de la propiedad dessistemaidentificacion.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getDESSISTEMAIDENTIFICACION() {
      return dessistemaidentificacion;
    }

    /**
     * Define el valor de la propiedad dessistemaidentificacion.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setDESSISTEMAIDENTIFICACION(String value) {
      this.dessistemaidentificacion = value;
    }

    /**
     * Obtiene el valor de la propiedad otrosistemaidentificacion.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getOTROSISTEMAIDENTIFICACION() {
      return otrosistemaidentificacion;
    }

    /**
     * Define el valor de la propiedad otrosistemaidentificacion.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setOTROSISTEMAIDENTIFICACION(String value) {
      this.otrosistemaidentificacion = value;
    }

  }

}
