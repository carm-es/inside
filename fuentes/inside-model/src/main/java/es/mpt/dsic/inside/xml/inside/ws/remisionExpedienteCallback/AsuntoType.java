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
 * Java class for asuntoType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="asuntoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codigoPoblacionAsunto" type="{http://justicia.es/esb/expedienteElectronico/RemisionExpedienteCallback/xsd-schemas/V1}codigoPoblacionAsuntoType" minOccurs="0"/>
 *         &lt;element name="tipoOrganoAsunto" type="{http://justicia.es/esb/expedienteElectronico/RemisionExpedienteCallback/xsd-schemas/V1}tipoOrganoAsuntoType" minOccurs="0"/>
 *         &lt;element name="codigoOrdenAsunto" type="{http://justicia.es/esb/expedienteElectronico/RemisionExpedienteCallback/xsd-schemas/V1}codigoOrdenAsuntoType" minOccurs="0"/>
 *         &lt;element name="numeroAsunto" type="{http://justicia.es/esb/expedienteElectronico/RemisionExpedienteCallback/xsd-schemas/V1}numeroAsuntoType" minOccurs="0"/>
 *         &lt;element name="anioAsunto" type="{http://justicia.es/esb/expedienteElectronico/RemisionExpedienteCallback/xsd-schemas/V1}anioAsuntoType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "asuntoType", propOrder = {"codigoPoblacionAsunto", "tipoOrganoAsunto",
    "codigoOrdenAsunto", "numeroAsunto", "anioAsunto"})
public class AsuntoType {

  protected String codigoPoblacionAsunto;
  protected String tipoOrganoAsunto;
  protected String codigoOrdenAsunto;
  protected String numeroAsunto;
  protected String anioAsunto;

  /**
   * Gets the value of the codigoPoblacionAsunto property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCodigoPoblacionAsunto() {
    return codigoPoblacionAsunto;
  }

  /**
   * Sets the value of the codigoPoblacionAsunto property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCodigoPoblacionAsunto(String value) {
    this.codigoPoblacionAsunto = value;
  }

  /**
   * Gets the value of the tipoOrganoAsunto property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getTipoOrganoAsunto() {
    return tipoOrganoAsunto;
  }

  /**
   * Sets the value of the tipoOrganoAsunto property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setTipoOrganoAsunto(String value) {
    this.tipoOrganoAsunto = value;
  }

  /**
   * Gets the value of the codigoOrdenAsunto property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCodigoOrdenAsunto() {
    return codigoOrdenAsunto;
  }

  /**
   * Sets the value of the codigoOrdenAsunto property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCodigoOrdenAsunto(String value) {
    this.codigoOrdenAsunto = value;
  }

  /**
   * Gets the value of the numeroAsunto property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getNumeroAsunto() {
    return numeroAsunto;
  }

  /**
   * Sets the value of the numeroAsunto property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setNumeroAsunto(String value) {
    this.numeroAsunto = value;
  }

  /**
   * Gets the value of the anioAsunto property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getAnioAsunto() {
    return anioAsunto;
  }

  /**
   * Sets the value of the anioAsunto property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setAnioAsunto(String value) {
    this.anioAsunto = value;
  }

}
