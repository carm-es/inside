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


package csvstorage.es.gob.aapp.csvstorage.webservices.bigaDataTransfer.document.v1.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Clase Java para contenidoUuidInfo complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="contenidoUuidInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoMIME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "contenidoUuidInfo", propOrder = {"nombre", "tipoMIME", "url"})
public class ContenidoUuidInfo {

  @XmlElement(required = true)
  protected String nombre;
  @XmlElement(required = true)
  protected String tipoMIME;
  @XmlElement(required = true)
  protected String url;

  /**
   * Obtiene el valor de la propiedad nombre.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getNombre() {
    return nombre;
  }

  /**
   * Define el valor de la propiedad nombre.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setNombre(String value) {
    this.nombre = value;
  }

  /**
   * Obtiene el valor de la propiedad tipoMIME.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getTipoMIME() {
    return tipoMIME;
  }

  /**
   * Define el valor de la propiedad tipoMIME.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setTipoMIME(String value) {
    this.tipoMIME = value;
  }

  /**
   * Obtiene el valor de la propiedad url.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getUrl() {
    return url;
  }

  /**
   * Define el valor de la propiedad url.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setUrl(String value) {
    this.url = value;
  }

}
