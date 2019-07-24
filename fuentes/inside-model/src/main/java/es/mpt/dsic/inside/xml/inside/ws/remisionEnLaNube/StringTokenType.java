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


package es.mpt.dsic.inside.xml.inside.ws.remisionEnLaNube;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Esquema que representa un token
 * 
 * <p>
 * Java class for StringTokenType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StringTokenType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idexp_eni" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="csv" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="uuid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StringTokenType", propOrder = {"idexpEni", "csv", "uuid"})
public class StringTokenType {

  @XmlElement(name = "idexp_eni", required = true)
  protected String idexpEni;
  @XmlElement(required = true)
  protected String csv;
  @XmlElement(required = true)
  protected String uuid;

  /**
   * Gets the value of the idexpEni property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getIdexpEni() {
    return idexpEni;
  }

  /**
   * Sets the value of the idexpEni property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setIdexpEni(String value) {
    this.idexpEni = value;
  }

  /**
   * Gets the value of the csv property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCsv() {
    return csv;
  }

  /**
   * Sets the value of the csv property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCsv(String value) {
    this.csv = value;
  }

  /**
   * Gets the value of the uuid property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getUuid() {
    return uuid;
  }

  /**
   * Sets the value of the uuid property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setUuid(String value) {
    this.uuid = value;
  }

}
