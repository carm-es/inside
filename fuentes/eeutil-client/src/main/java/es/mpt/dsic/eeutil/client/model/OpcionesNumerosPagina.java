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
 * Java class for opcionesNumerosPagina complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="opcionesNumerosPagina">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="estamparNumeroPagina" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="estamparNumeroPaginasTotal" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="posicion" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="estamparMasDatos" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="cadenasMasDatos" type="{http://service.ws.inside.dsic.mpt.es/}listaCadenas" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "opcionesNumerosPagina", propOrder = {"estamparNumeroPagina",
    "estamparNumeroPaginasTotal", "posicion", "estamparMasDatos", "cadenasMasDatos"})
public class OpcionesNumerosPagina {

  @XmlElement(defaultValue = "true")
  protected boolean estamparNumeroPagina;
  @XmlElement(defaultValue = "false")
  protected boolean estamparNumeroPaginasTotal;
  protected Integer posicion;
  @XmlElement(defaultValue = "true")
  protected boolean estamparMasDatos;
  protected ListaCadenas cadenasMasDatos;

  /**
   * Gets the value of the estamparNumeroPagina property.
   * 
   */
  public boolean isEstamparNumeroPagina() {
    return estamparNumeroPagina;
  }

  /**
   * Sets the value of the estamparNumeroPagina property.
   * 
   */
  public void setEstamparNumeroPagina(boolean value) {
    this.estamparNumeroPagina = value;
  }

  /**
   * Gets the value of the estamparNumeroPaginasTotal property.
   * 
   */
  public boolean isEstamparNumeroPaginasTotal() {
    return estamparNumeroPaginasTotal;
  }

  /**
   * Sets the value of the estamparNumeroPaginasTotal property.
   * 
   */
  public void setEstamparNumeroPaginasTotal(boolean value) {
    this.estamparNumeroPaginasTotal = value;
  }

  /**
   * Gets the value of the posicion property.
   * 
   * @return possible object is {@link Integer }
   * 
   */
  public Integer getPosicion() {
    return posicion;
  }

  /**
   * Sets the value of the posicion property.
   * 
   * @param value allowed object is {@link Integer }
   * 
   */
  public void setPosicion(Integer value) {
    this.posicion = value;
  }

  /**
   * Gets the value of the estamparMasDatos property.
   * 
   */
  public boolean isEstamparMasDatos() {
    return estamparMasDatos;
  }

  /**
   * Sets the value of the estamparMasDatos property.
   * 
   */
  public void setEstamparMasDatos(boolean value) {
    this.estamparMasDatos = value;
  }

  /**
   * Gets the value of the cadenasMasDatos property.
   * 
   * @return possible object is {@link ListaCadenas }
   * 
   */
  public ListaCadenas getCadenasMasDatos() {
    return cadenasMasDatos;
  }

  /**
   * Sets the value of the cadenasMasDatos property.
   * 
   * @param value allowed object is {@link ListaCadenas }
   * 
   */
  public void setCadenasMasDatos(ListaCadenas value) {
    this.cadenasMasDatos = value;
  }

}
