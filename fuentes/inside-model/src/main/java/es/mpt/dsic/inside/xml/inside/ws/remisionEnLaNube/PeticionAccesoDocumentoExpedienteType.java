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
 * Esquema que representa una peticion de una descarga de un documentoENI
 * 
 * <p>
 * Java class for PeticionAccesoDocumentoExpedienteType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PeticionAccesoDocumentoExpedienteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="peticion" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/remisionNube}PeticionType"/>
 *         &lt;element name="token" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/remisionNube}StringTokenType"/>
 *         &lt;element name="id_Documento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PeticionAccesoDocumentoExpedienteType",
    propOrder = {"peticion", "token", "idDocumento"})
public class PeticionAccesoDocumentoExpedienteType {

  @XmlElement(required = true)
  protected PeticionType peticion;
  @XmlElement(required = true)
  protected StringTokenType token;
  @XmlElement(name = "id_Documento", required = true)
  protected String idDocumento;

  /**
   * Gets the value of the peticion property.
   * 
   * @return possible object is {@link PeticionType }
   * 
   */
  public PeticionType getPeticion() {
    return peticion;
  }

  /**
   * Sets the value of the peticion property.
   * 
   * @param value allowed object is {@link PeticionType }
   * 
   */
  public void setPeticion(PeticionType value) {
    this.peticion = value;
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

  /**
   * Gets the value of the idDocumento property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getIdDocumento() {
    return idDocumento;
  }

  /**
   * Sets the value of the idDocumento property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setIdDocumento(String value) {
    this.idDocumento = value;
  }

}
