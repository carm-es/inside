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


package es.mpt.dsic.eeutil.client.operFirma.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Clase Java para FirmaInfo complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="FirmaInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nifcif" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="apellido1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="apellido2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fecha" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="extras" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FirmaInfo",
    propOrder = {"nifcif", "nombre", "apellido1", "apellido2", "fecha", "extras"})
public class FirmaInfo {

  @XmlElement(required = true)
  protected String nifcif;
  @XmlElement(required = true)
  protected String nombre;
  @XmlElement(required = true)
  protected String apellido1;
  @XmlElement(required = true)
  protected String apellido2;
  @XmlElement(required = true)
  protected String fecha;
  protected String extras;

  /**
   * Obtiene el valor de la propiedad nifcif.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getNifcif() {
    return nifcif;
  }

  /**
   * Define el valor de la propiedad nifcif.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setNifcif(String value) {
    this.nifcif = value;
  }

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
   * Obtiene el valor de la propiedad apellido1.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getApellido1() {
    return apellido1;
  }

  /**
   * Define el valor de la propiedad apellido1.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setApellido1(String value) {
    this.apellido1 = value;
  }

  /**
   * Obtiene el valor de la propiedad apellido2.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getApellido2() {
    return apellido2;
  }

  /**
   * Define el valor de la propiedad apellido2.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setApellido2(String value) {
    this.apellido2 = value;
  }

  /**
   * Obtiene el valor de la propiedad fecha.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getFecha() {
    return fecha;
  }

  /**
   * Define el valor de la propiedad fecha.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setFecha(String value) {
    this.fecha = value;
  }

  /**
   * Obtiene el valor de la propiedad extras.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getExtras() {
    return extras;
  }

  /**
   * Define el valor de la propiedad extras.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setExtras(String value) {
    this.extras = value;
  }

}
