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


package es.mpt.dsic.inside.xml.inside.ws.remisionExpedienteCallback;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Java class for piezaType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="piezaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="clasePieza" type="{http://justicia.es/esb/expedienteElectronico/RemisionExpedienteCallback/xsd-schemas/V1}clasePiezaType" minOccurs="0"/>
 *         &lt;element name="numeroProcedimientoPieza" type="{http://justicia.es/esb/expedienteElectronico/RemisionExpedienteCallback/xsd-schemas/V1}numeroProcedimientoPiezaType" minOccurs="0"/>
 *         &lt;element name="anioPieza" type="{http://justicia.es/esb/expedienteElectronico/RemisionExpedienteCallback/xsd-schemas/V1}anioPiezaType" minOccurs="0"/>
 *         &lt;element name="numeroPieza" type="{http://justicia.es/esb/expedienteElectronico/RemisionExpedienteCallback/xsd-schemas/V1}numeroPiezaType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "piezaType",
    propOrder = {"clasePieza", "numeroProcedimientoPieza", "anioPieza", "numeroPieza"})
public class PiezaType {

  protected String clasePieza;
  protected String numeroProcedimientoPieza;
  protected String anioPieza;
  protected String numeroPieza;

  /**
   * Gets the value of the clasePieza property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getClasePieza() {
    return clasePieza;
  }

  /**
   * Sets the value of the clasePieza property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setClasePieza(String value) {
    this.clasePieza = value;
  }

  /**
   * Gets the value of the numeroProcedimientoPieza property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getNumeroProcedimientoPieza() {
    return numeroProcedimientoPieza;
  }

  /**
   * Sets the value of the numeroProcedimientoPieza property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setNumeroProcedimientoPieza(String value) {
    this.numeroProcedimientoPieza = value;
  }

  /**
   * Gets the value of the anioPieza property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getAnioPieza() {
    return anioPieza;
  }

  /**
   * Sets the value of the anioPieza property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setAnioPieza(String value) {
    this.anioPieza = value;
  }

  /**
   * Gets the value of the numeroPieza property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getNumeroPieza() {
    return numeroPieza;
  }

  /**
   * Sets the value of the numeroPieza property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setNumeroPieza(String value) {
    this.numeroPieza = value;
  }

}
