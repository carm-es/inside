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


package es.mpt.dsic.inside.service.validacionENIMtom.model;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Clase Java para DocumentoEntradaMtom complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DocumentoEntradaMtom">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contenido" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="mime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentoEntradaMtom", propOrder = {"contenido", "mime", "password"})
public class DocumentoEntradaMtom {

  @XmlElement(required = true)
  @XmlMimeType("application/octet-stream")
  protected DataHandler contenido;
  protected String mime;
  protected String password;

  /**
   * Obtiene el valor de la propiedad contenido.
   * 
   * @return possible object is {@link DataHandler }
   * 
   */
  public DataHandler getContenido() {
    return contenido;
  }

  /**
   * Define el valor de la propiedad contenido.
   * 
   * @param value allowed object is {@link DataHandler }
   * 
   */
  public void setContenido(DataHandler value) {
    this.contenido = value;
  }

  /**
   * Obtiene el valor de la propiedad mime.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getMime() {
    return mime;
  }

  /**
   * Define el valor de la propiedad mime.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setMime(String value) {
    this.mime = value;
  }

  /**
   * Obtiene el valor de la propiedad password.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getPassword() {
    return password;
  }

  /**
   * Define el valor de la propiedad password.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setPassword(String value) {
    this.password = value;
  }

}
