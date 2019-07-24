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
 * Java class for opcionesFoliado complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="opcionesFoliado">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="numeroPaginasBlanco" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="opcionesLogo" type="{http://service.ws.inside.dsic.mpt.es/}opcionesLogo" minOccurs="0"/>
 *         &lt;element name="opcionesNumerosPagina" type="{http://service.ws.inside.dsic.mpt.es/}opcionesNumerosPagina" minOccurs="0"/>
 *         &lt;element name="opcionesPortada" type="{http://service.ws.inside.dsic.mpt.es/}opcionesPortada" minOccurs="0"/>
 *         &lt;element name="opcionesIndice" type="{http://service.ws.inside.dsic.mpt.es/}opcionesIndice" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "opcionesFoliado", propOrder = {"numeroPaginasBlanco", "opcionesLogo",
    "opcionesNumerosPagina", "opcionesPortada", "opcionesIndice"})
public class OpcionesFoliado {

  @XmlElement(defaultValue = "2")
  protected Integer numeroPaginasBlanco;
  protected OpcionesLogo opcionesLogo;
  protected OpcionesNumerosPagina opcionesNumerosPagina;
  protected OpcionesPortada opcionesPortada;
  protected OpcionesIndice opcionesIndice;

  /**
   * Gets the value of the numeroPaginasBlanco property.
   * 
   * @return possible object is {@link Integer }
   * 
   */
  public Integer getNumeroPaginasBlanco() {
    return numeroPaginasBlanco;
  }

  /**
   * Sets the value of the numeroPaginasBlanco property.
   * 
   * @param value allowed object is {@link Integer }
   * 
   */
  public void setNumeroPaginasBlanco(Integer value) {
    this.numeroPaginasBlanco = value;
  }

  /**
   * Gets the value of the opcionesLogo property.
   * 
   * @return possible object is {@link OpcionesLogo }
   * 
   */
  public OpcionesLogo getOpcionesLogo() {
    return opcionesLogo;
  }

  /**
   * Sets the value of the opcionesLogo property.
   * 
   * @param value allowed object is {@link OpcionesLogo }
   * 
   */
  public void setOpcionesLogo(OpcionesLogo value) {
    this.opcionesLogo = value;
  }

  /**
   * Gets the value of the opcionesNumerosPagina property.
   * 
   * @return possible object is {@link OpcionesNumerosPagina }
   * 
   */
  public OpcionesNumerosPagina getOpcionesNumerosPagina() {
    return opcionesNumerosPagina;
  }

  /**
   * Sets the value of the opcionesNumerosPagina property.
   * 
   * @param value allowed object is {@link OpcionesNumerosPagina }
   * 
   */
  public void setOpcionesNumerosPagina(OpcionesNumerosPagina value) {
    this.opcionesNumerosPagina = value;
  }

  /**
   * Gets the value of the opcionesPortada property.
   * 
   * @return possible object is {@link OpcionesPortada }
   * 
   */
  public OpcionesPortada getOpcionesPortada() {
    return opcionesPortada;
  }

  /**
   * Sets the value of the opcionesPortada property.
   * 
   * @param value allowed object is {@link OpcionesPortada }
   * 
   */
  public void setOpcionesPortada(OpcionesPortada value) {
    this.opcionesPortada = value;
  }

  /**
   * Gets the value of the opcionesIndice property.
   * 
   * @return possible object is {@link OpcionesIndice }
   * 
   */
  public OpcionesIndice getOpcionesIndice() {
    return opcionesIndice;
  }

  /**
   * Sets the value of the opcionesIndice property.
   * 
   * @param value allowed object is {@link OpcionesIndice }
   * 
   */
  public void setOpcionesIndice(OpcionesIndice value) {
    this.opcionesIndice = value;
  }

}
