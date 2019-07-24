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


package es.mpt.dsic.eeutil.client.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Java class for foliar complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="foliar">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aplicacionInfo" type="{http://service.ws.inside.dsic.mpt.es/}applicationLogin"/>
 *         &lt;element name="atributos" type="{http://service.ws.inside.dsic.mpt.es/}atributos"/>
 *         &lt;element name="item" type="{http://service.ws.inside.dsic.mpt.es/}item"/>
 *         &lt;element name="opcionesFoliado" type="{http://service.ws.inside.dsic.mpt.es/}opcionesFoliado"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "foliar", propOrder = {"aplicacionInfo", "atributos", "item", "opcionesFoliado"})
public class Foliar {

  @XmlElement(required = true)
  protected ApplicationLogin aplicacionInfo;
  @XmlElement(required = true)
  protected Atributos atributos;
  @XmlElement(required = true)
  protected Item item;
  @XmlElement(required = true)
  protected OpcionesFoliado opcionesFoliado;

  /**
   * Gets the value of the aplicacionInfo property.
   * 
   * @return possible object is {@link ApplicationLogin }
   * 
   */
  public ApplicationLogin getAplicacionInfo() {
    return aplicacionInfo;
  }

  /**
   * Sets the value of the aplicacionInfo property.
   * 
   * @param value allowed object is {@link ApplicationLogin }
   * 
   */
  public void setAplicacionInfo(ApplicationLogin value) {
    this.aplicacionInfo = value;
  }

  /**
   * Gets the value of the atributos property.
   * 
   * @return possible object is {@link Atributos }
   * 
   */
  public Atributos getAtributos() {
    return atributos;
  }

  /**
   * Sets the value of the atributos property.
   * 
   * @param value allowed object is {@link Atributos }
   * 
   */
  public void setAtributos(Atributos value) {
    this.atributos = value;
  }

  /**
   * Gets the value of the item property.
   * 
   * @return possible object is {@link Item }
   * 
   */
  public Item getItem() {
    return item;
  }

  /**
   * Sets the value of the item property.
   * 
   * @param value allowed object is {@link Item }
   * 
   */
  public void setItem(Item value) {
    this.item = value;
  }

  /**
   * Gets the value of the opcionesFoliado property.
   * 
   * @return possible object is {@link OpcionesFoliado }
   * 
   */
  public OpcionesFoliado getOpcionesFoliado() {
    return opcionesFoliado;
  }

  /**
   * Sets the value of the opcionesFoliado property.
   * 
   * @param value allowed object is {@link OpcionesFoliado }
   * 
   */
  public void setOpcionesFoliado(OpcionesFoliado value) {
    this.opcionesFoliado = value;
  }

}
