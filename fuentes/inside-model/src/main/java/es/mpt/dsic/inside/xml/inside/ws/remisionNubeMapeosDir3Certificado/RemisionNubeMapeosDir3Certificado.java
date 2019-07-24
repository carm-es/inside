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


package es.mpt.dsic.inside.xml.inside.ws.remisionNubeMapeosDir3Certificado;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * mapeos de dir3 padre con certificado por serial number
 * 
 * <p>
 * Java class for RemisionNubeMapeosDir3Certificado complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RemisionNubeMapeosDir3Certificado">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dir3Padre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="serialNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="apellidos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telefono" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RemisionNubeMapeosDir3Certificado",
    propOrder = {"dir3Padre", "serialNumber", "nombre", "apellidos", "email", "telefono"})
public class RemisionNubeMapeosDir3Certificado {

  @XmlElement(required = true)
  protected String dir3Padre;
  @XmlElement(required = true)
  protected String serialNumber;
  protected String nombre;
  protected String apellidos;
  protected String email;
  protected String telefono;

  /**
   * Gets the value of the dir3Padre property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDir3Padre() {
    return dir3Padre;
  }

  /**
   * Sets the value of the dir3Padre property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDir3Padre(String value) {
    this.dir3Padre = value;
  }

  /**
   * Gets the value of the serialNumber property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getSerialNumber() {
    return serialNumber;
  }

  /**
   * Sets the value of the serialNumber property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setSerialNumber(String value) {
    this.serialNumber = value;
  }

  /**
   * Gets the value of the nombre property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getNombre() {
    return nombre;
  }

  /**
   * Sets the value of the nombre property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setNombre(String value) {
    this.nombre = value;
  }

  /**
   * Gets the value of the apellidos property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getApellidos() {
    return apellidos;
  }

  /**
   * Sets the value of the apellidos property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setApellidos(String value) {
    this.apellidos = value;
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

  /**
   * Gets the value of the telefono property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getTelefono() {
    return telefono;
  }

  /**
   * Sets the value of the telefono property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setTelefono(String value) {
    this.telefono = value;
  }

}
