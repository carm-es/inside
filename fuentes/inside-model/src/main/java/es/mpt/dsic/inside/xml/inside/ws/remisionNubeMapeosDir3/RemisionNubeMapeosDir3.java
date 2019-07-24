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


package es.mpt.dsic.inside.xml.inside.ws.remisionNubeMapeosDir3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * mapeos de diferentes urls y email para un dir3
 * 
 * <p>
 * Java class for RemisionNubeMapeosDir3 complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RemisionNubeMapeosDir3">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dir3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="urlComunicacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="urlSolicitudAcceso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RemisionNubeMapeosDir3",
    propOrder = {"dir3", "urlComunicacion", "urlSolicitudAcceso", "email"})
public class RemisionNubeMapeosDir3 {

  @XmlElement(required = true)
  protected String dir3;
  protected String urlComunicacion;
  protected String urlSolicitudAcceso;
  protected String email;

  /**
   * Gets the value of the dir3 property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDir3() {
    return dir3;
  }

  /**
   * Sets the value of the dir3 property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDir3(String value) {
    this.dir3 = value;
  }

  /**
   * Gets the value of the urlComunicacion property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getUrlComunicacion() {
    return urlComunicacion;
  }

  /**
   * Sets the value of the urlComunicacion property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setUrlComunicacion(String value) {
    this.urlComunicacion = value;
  }

  /**
   * Gets the value of the urlSolicitudAcceso property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getUrlSolicitudAcceso() {
    return urlSolicitudAcceso;
  }

  /**
   * Sets the value of the urlSolicitudAcceso property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setUrlSolicitudAcceso(String value) {
    this.urlSolicitudAcceso = value;
  }

  /**
   * Gets the value of the email property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the value of the email property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setEmail(String value) {
    this.email = value;
  }

}
