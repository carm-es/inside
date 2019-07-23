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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Clase Java para LUGARPRESENTACION complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="LUGARPRESENTACION">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CODLUGARPRESENTACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DESLUGARPRESENTACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LUGARPRESENTACION", propOrder = {"codlugarpresentacion", "deslugarpresentacion"})
public class LUGARPRESENTACION {

  @XmlElement(name = "CODLUGARPRESENTACION")
  protected String codlugarpresentacion;
  @XmlElement(name = "DESLUGARPRESENTACION")
  protected String deslugarpresentacion;

  /**
   * Obtiene el valor de la propiedad codlugarpresentacion.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCODLUGARPRESENTACION() {
    return codlugarpresentacion;
  }

  /**
   * Define el valor de la propiedad codlugarpresentacion.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCODLUGARPRESENTACION(String value) {
    this.codlugarpresentacion = value;
  }

  /**
   * Obtiene el valor de la propiedad deslugarpresentacion.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDESLUGARPRESENTACION() {
    return deslugarpresentacion;
  }

  /**
   * Define el valor de la propiedad deslugarpresentacion.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDESLUGARPRESENTACION(String value) {
    this.deslugarpresentacion = value;
  }

}
