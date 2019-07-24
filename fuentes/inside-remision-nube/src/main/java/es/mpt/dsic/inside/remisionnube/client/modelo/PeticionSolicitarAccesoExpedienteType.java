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
 * Esquema que representa una peticion de solicitud de acceso a expediente
 * 
 * <p>
 * Clase Java para PeticionSolicitarAccesoExpedienteType complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="PeticionSolicitarAccesoExpedienteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="peticion" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/remisionNube}PeticionType"/>
 *         &lt;element name="asunto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="adjunto" type="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/contenido}TipoContenido" minOccurs="0"/>
 *         &lt;element name="id_exped_pedido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigo_sia" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="metadatos" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/metadatosAdicionales}TipoMetadatosAdicionales" minOccurs="0"/>
 *         &lt;element name="endpoint_peticionario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PeticionSolicitarAccesoExpedienteType",
    namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/remisionNube",
    propOrder = {"peticion", "asunto", "adjunto", "idExpedPedido", "codigoSia", "metadatos",
        "endpointPeticionario"})
public class PeticionSolicitarAccesoExpedienteType {

  @XmlElement(required = true)
  protected PeticionType peticion;
  @XmlElement(required = true)
  protected String asunto;
  protected TipoContenido adjunto;
  @XmlElement(name = "id_exped_pedido")
  protected String idExpedPedido;
  @XmlElement(name = "codigo_sia")
  protected int codigoSia;
  protected TipoMetadatosAdicionales metadatos;
  @XmlElement(name = "endpoint_peticionario", required = true)
  protected String endpointPeticionario;

  /**
   * Obtiene el valor de la propiedad peticion.
   * 
   * @return possible object is {@link PeticionType }
   * 
   */
  public PeticionType getPeticion() {
    return peticion;
  }

  /**
   * Define el valor de la propiedad peticion.
   * 
   * @param value allowed object is {@link PeticionType }
   * 
   */
  public void setPeticion(PeticionType value) {
    this.peticion = value;
  }

  /**
   * Obtiene el valor de la propiedad asunto.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getAsunto() {
    return asunto;
  }

  /**
   * Define el valor de la propiedad asunto.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setAsunto(String value) {
    this.asunto = value;
  }

  /**
   * Obtiene el valor de la propiedad adjunto.
   * 
   * @return possible object is {@link TipoContenido }
   * 
   */
  public TipoContenido getAdjunto() {
    return adjunto;
  }

  /**
   * Define el valor de la propiedad adjunto.
   * 
   * @param value allowed object is {@link TipoContenido }
   * 
   */
  public void setAdjunto(TipoContenido value) {
    this.adjunto = value;
  }

  /**
   * Obtiene el valor de la propiedad idExpedPedido.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getIdExpedPedido() {
    return idExpedPedido;
  }

  /**
   * Define el valor de la propiedad idExpedPedido.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setIdExpedPedido(String value) {
    this.idExpedPedido = value;
  }

  /**
   * Obtiene el valor de la propiedad codigoSia.
   * 
   */
  public int getCodigoSia() {
    return codigoSia;
  }

  /**
   * Define el valor de la propiedad codigoSia.
   * 
   */
  public void setCodigoSia(int value) {
    this.codigoSia = value;
  }

  /**
   * Obtiene el valor de la propiedad metadatos.
   * 
   * @return possible object is {@link TipoMetadatosAdicionales }
   * 
   */
  public TipoMetadatosAdicionales getMetadatos() {
    return metadatos;
  }

  /**
   * Define el valor de la propiedad metadatos.
   * 
   * @param value allowed object is {@link TipoMetadatosAdicionales }
   * 
   */
  public void setMetadatos(TipoMetadatosAdicionales value) {
    this.metadatos = value;
  }

  /**
   * Obtiene el valor de la propiedad endpointPeticionario.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getEndpointPeticionario() {
    return endpointPeticionario;
  }

  /**
   * Define el valor de la propiedad endpointPeticionario.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setEndpointPeticionario(String value) {
    this.endpointPeticionario = value;
  }

}
