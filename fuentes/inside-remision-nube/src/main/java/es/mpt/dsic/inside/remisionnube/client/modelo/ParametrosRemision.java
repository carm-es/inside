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
 * Esquema que representa parametros de entrada
 * 
 * <p>
 * Clase Java para ParametrosRemision complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ParametrosRemision">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="parametro" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "ParametrosRemision",
    namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/remisionNube",
    propOrder = {"parametro", "valor"})
public class ParametrosRemision {

  @XmlElement(required = true)
  protected String parametro;
  @XmlElement(required = true)
  protected String valor;

  /**
   * Obtiene el valor de la propiedad parametro.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getParametro() {
    return parametro;
  }

  /**
   * Define el valor de la propiedad parametro.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setParametro(String value) {
    this.parametro = value;
  }

  /**
   * Obtiene el valor de la propiedad valor.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getValor() {
    return valor;
  }

  /**
   * Define el valor de la propiedad valor.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setValor(String value) {
    this.valor = value;
  }

}
