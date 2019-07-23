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


package es.mpt.dsic.inside.remisionnube.client.modelo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Esquema que representa una comunicación de resultado de un envio a MJU
 * 
 * <p>
 * Clase Java para PeticionComunicacionResultadoEnvioMJUType complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="PeticionComunicacionResultadoEnvioMJUType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codigo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idExpediente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigoEnvioMJU" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idPeticion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PeticionComunicacionResultadoEnvioMJUType",
    namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/remisionNube",
    propOrder = {"codigo", "descripcion", "idExpediente", "codigoEnvioMJU", "idPeticion"})
public class PeticionComunicacionResultadoEnvioMJUType {

  @XmlElement(required = true)
  protected String codigo;
  protected String descripcion;
  @XmlElement(required = true)
  protected String idExpediente;
  protected String codigoEnvioMJU;
  @XmlElement(required = true)
  protected String idPeticion;

  /**
   * Obtiene el valor de la propiedad codigo.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCodigo() {
    return codigo;
  }

  /**
   * Define el valor de la propiedad codigo.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCodigo(String value) {
    this.codigo = value;
  }

  /**
   * Obtiene el valor de la propiedad descripcion.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDescripcion() {
    return descripcion;
  }

  /**
   * Define el valor de la propiedad descripcion.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDescripcion(String value) {
    this.descripcion = value;
  }

  /**
   * Obtiene el valor de la propiedad idExpediente.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getIdExpediente() {
    return idExpediente;
  }

  /**
   * Define el valor de la propiedad idExpediente.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setIdExpediente(String value) {
    this.idExpediente = value;
  }

  /**
   * Obtiene el valor de la propiedad codigoEnvioMJU.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCodigoEnvioMJU() {
    return codigoEnvioMJU;
  }

  /**
   * Define el valor de la propiedad codigoEnvioMJU.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCodigoEnvioMJU(String value) {
    this.codigoEnvioMJU = value;
  }

  /**
   * Obtiene el valor de la propiedad idPeticion.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getIdPeticion() {
    return idPeticion;
  }

  /**
   * Define el valor de la propiedad idPeticion.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setIdPeticion(String value) {
    this.idPeticion = value;
  }

}
