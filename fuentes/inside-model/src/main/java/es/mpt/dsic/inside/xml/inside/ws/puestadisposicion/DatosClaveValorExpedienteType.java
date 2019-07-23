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


package es.mpt.dsic.inside.xml.inside.ws.puestadisposicion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Esquema que representa los par clave/valor de expediente
 * 
 * <p>
 * Java class for DatosClaveValorExpedienteType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DatosClaveValorExpedienteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="clave" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/puestaDisposicion}ClaveEnumType"/>
 *         &lt;element name="valor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatosClaveValorExpedienteType", propOrder = {"clave", "valor"})
public class DatosClaveValorExpedienteType {

  @XmlElement(required = true)
  protected ClaveEnumType clave;
  @XmlElement(required = true)
  protected String valor;

  /**
   * Gets the value of the clave property.
   * 
   * @return possible object is {@link ClaveEnumType }
   * 
   */
  public ClaveEnumType getClave() {
    return clave;
  }

  /**
   * Sets the value of the clave property.
   * 
   * @param value allowed object is {@link ClaveEnumType }
   * 
   */
  public void setClave(ClaveEnumType value) {
    this.clave = value;
  }

  /**
   * Gets the value of the valor property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getValor() {
    return valor;
  }

  /**
   * Sets the value of the valor property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setValor(String value) {
    this.valor = value;
  }

}
