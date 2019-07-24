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
 * Esquema que representa una comunicación de resultado de un envio a MJU
 * 
 * <p>
 * Java class for PeticionComunicacionResultadoEnvioMJUType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
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
 *         &lt;element name="resguardo" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PeticionComunicacionResultadoEnvioMJUType", propOrder = {"codigo", "descripcion",
    "idExpediente", "codigoEnvioMJU", "idPeticion", "resguardo"})
public class PeticionComunicacionResultadoEnvioMJUType {

  @XmlElement(required = true)
  protected String codigo;
  protected String descripcion;
  @XmlElement(required = true)
  protected String idExpediente;
  protected String codigoEnvioMJU;
  @XmlElement(required = true)
  protected String idPeticion;
  protected byte[] resguardo;

  /**
   * Gets the value of the codigo property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCodigo() {
    return codigo;
  }

  /**
   * Sets the value of the codigo property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCodigo(String value) {
    this.codigo = value;
  }

  /**
   * Gets the value of the descripcion property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDescripcion() {
    return descripcion;
  }

  /**
   * Sets the value of the descripcion property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDescripcion(String value) {
    this.descripcion = value;
  }

  /**
   * Gets the value of the idExpediente property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getIdExpediente() {
    return idExpediente;
  }

  /**
   * Sets the value of the idExpediente property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setIdExpediente(String value) {
    this.idExpediente = value;
  }

  /**
   * Gets the value of the codigoEnvioMJU property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCodigoEnvioMJU() {
    return codigoEnvioMJU;
  }

  /**
   * Sets the value of the codigoEnvioMJU property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCodigoEnvioMJU(String value) {
    this.codigoEnvioMJU = value;
  }

  /**
   * Gets the value of the idPeticion property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getIdPeticion() {
    return idPeticion;
  }

  /**
   * Sets the value of the idPeticion property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setIdPeticion(String value) {
    this.idPeticion = value;
  }

  /**
   * Gets the value of the resguardo property.
   * 
   * @return possible object is byte[]
   */
  public byte[] getResguardo() {
    return resguardo;
  }

  /**
   * Sets the value of the resguardo property.
   * 
   * @param value allowed object is byte[]
   */
  public void setResguardo(byte[] value) {
    this.resguardo = ((byte[]) value);
  }

}
