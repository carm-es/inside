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
 * Esquema que representa una comunicación de credenciales o claves de acceso del expediente
 * solicitado
 * 
 * <p>
 * Clase Java para PeticionComunicacionTokenExpedienteType complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="PeticionComunicacionTokenExpedienteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="peticion" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/remisionNube}PeticionType"/>
 *         &lt;element name="asunto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="adjunto" type="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/contenido}TipoContenido" minOccurs="0"/>
 *         &lt;element name="metadatosExp" type="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/metadatos}TipoMetadatos" minOccurs="0"/>
 *         &lt;element name="indiceExp" type="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e}TipoExpediente" minOccurs="0"/>
 *         &lt;element name="token" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/remisionNube}StringTokenType"/>
 *         &lt;element name="datosRemisionJusticia" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/remisionNube}DatosRemisionJusticiaType" minOccurs="0"/>
 *         &lt;element name="endpoint_remitente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="url_accesoWeb" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PeticionComunicacionTokenExpedienteType",
    namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/remisionNube",
    propOrder = {"peticion", "asunto", "adjunto", "metadatosExp", "indiceExp", "token",
        "datosRemisionJusticia", "endpointRemitente", "urlAccesoWeb"})
public class PeticionComunicacionTokenExpedienteType {

  @XmlElement(required = true)
  protected PeticionType peticion;
  protected String asunto;
  protected TipoContenido adjunto;
  protected TipoMetadatos2 metadatosExp;
  protected TipoExpediente indiceExp;
  @XmlElement(required = true)
  protected StringTokenType token;
  protected DatosRemisionJusticiaType datosRemisionJusticia;
  @XmlElement(name = "endpoint_remitente", required = true)
  protected String endpointRemitente;
  @XmlElement(name = "url_accesoWeb")
  protected String urlAccesoWeb;

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
   * Obtiene el valor de la propiedad metadatosExp.
   * 
   * @return possible object is {@link TipoMetadatos2 }
   * 
   */
  public TipoMetadatos2 getMetadatosExp() {
    return metadatosExp;
  }

  /**
   * Define el valor de la propiedad metadatosExp.
   * 
   * @param value allowed object is {@link TipoMetadatos2 }
   * 
   */
  public void setMetadatosExp(TipoMetadatos2 value) {
    this.metadatosExp = value;
  }

  /**
   * Obtiene el valor de la propiedad indiceExp.
   * 
   * @return possible object is {@link TipoExpediente }
   * 
   */
  public TipoExpediente getIndiceExp() {
    return indiceExp;
  }

  /**
   * Define el valor de la propiedad indiceExp.
   * 
   * @param value allowed object is {@link TipoExpediente }
   * 
   */
  public void setIndiceExp(TipoExpediente value) {
    this.indiceExp = value;
  }

  /**
   * Obtiene el valor de la propiedad token.
   * 
   * @return possible object is {@link StringTokenType }
   * 
   */
  public StringTokenType getToken() {
    return token;
  }

  /**
   * Define el valor de la propiedad token.
   * 
   * @param value allowed object is {@link StringTokenType }
   * 
   */
  public void setToken(StringTokenType value) {
    this.token = value;
  }

  /**
   * Obtiene el valor de la propiedad datosRemisionJusticia.
   * 
   * @return possible object is {@link DatosRemisionJusticiaType }
   * 
   */
  public DatosRemisionJusticiaType getDatosRemisionJusticia() {
    return datosRemisionJusticia;
  }

  /**
   * Define el valor de la propiedad datosRemisionJusticia.
   * 
   * @param value allowed object is {@link DatosRemisionJusticiaType }
   * 
   */
  public void setDatosRemisionJusticia(DatosRemisionJusticiaType value) {
    this.datosRemisionJusticia = value;
  }

  /**
   * Obtiene el valor de la propiedad endpointRemitente.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getEndpointRemitente() {
    return endpointRemitente;
  }

  /**
   * Define el valor de la propiedad endpointRemitente.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setEndpointRemitente(String value) {
    this.endpointRemitente = value;
  }

  /**
   * Obtiene el valor de la propiedad urlAccesoWeb.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getUrlAccesoWeb() {
    return urlAccesoWeb;
  }

  /**
   * Define el valor de la propiedad urlAccesoWeb.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setUrlAccesoWeb(String value) {
    this.urlAccesoWeb = value;
  }

}
