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
 * Esquema que representa una peticion de puesta a disposicion de expediente
 * 
 * <p>
 * Java class for PeticionPuestaDisposicionExpedienteType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PeticionPuestaDisposicionExpedienteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="datosSolicitante" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/puestaDisposicion}DatosSolicitanteType"/>
 *         &lt;element name="token" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/puestaDisposicion}StringTokenType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PeticionPuestaDisposicionExpedienteType",
    propOrder = {"datosSolicitante", "token"})
public class PeticionPuestaDisposicionExpedienteType {

  @XmlElement(required = true)
  protected DatosSolicitanteType datosSolicitante;
  @XmlElement(required = true)
  protected StringTokenType token;

  /**
   * Gets the value of the datosSolicitante property.
   * 
   * @return possible object is {@link DatosSolicitanteType }
   * 
   */
  public DatosSolicitanteType getDatosSolicitante() {
    return datosSolicitante;
  }

  /**
   * Sets the value of the datosSolicitante property.
   * 
   * @param value allowed object is {@link DatosSolicitanteType }
   * 
   */
  public void setDatosSolicitante(DatosSolicitanteType value) {
    this.datosSolicitante = value;
  }

  /**
   * Gets the value of the token property.
   * 
   * @return possible object is {@link StringTokenType }
   * 
   */
  public StringTokenType getToken() {
    return token;
  }

  /**
   * Sets the value of the token property.
   * 
   * @param value allowed object is {@link StringTokenType }
   * 
   */
  public void setToken(StringTokenType value) {
    this.token = value;
  }

}
