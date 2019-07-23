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

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Esquema que representa una respuesta al acceso al indice del expediente
 * 
 * <p>
 * Clase Java para RespuestaAccesoIndiceExpedienteType complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="RespuestaAccesoIndiceExpedienteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="respuesta" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/remisionNube}RespuestaType" minOccurs="0"/>
 *         &lt;element name="expedienteEni" type="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e}TipoExpediente" minOccurs="0"/>
 *         &lt;element name="metadatosAdicionales" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/metadatosAdicionales}TipoMetadatosAdicionales" minOccurs="0"/>
 *         &lt;element name="metadatos_documento" type="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/metadatos}TipoMetadatos" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="endpoint_remitente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RespuestaAccesoIndiceExpedienteType",
    namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/remisionNube",
    propOrder = {"respuesta", "expedienteEni", "metadatosAdicionales", "metadatosDocumento",
        "endpointRemitente"})
public class RespuestaAccesoIndiceExpedienteType {

  protected RespuestaType respuesta;
  protected TipoExpediente expedienteEni;
  protected TipoMetadatosAdicionales metadatosAdicionales;
  @XmlElement(name = "metadatos_documento")
  protected List<TipoMetadatos> metadatosDocumento;
  @XmlElement(name = "endpoint_remitente")
  protected String endpointRemitente;

  /**
   * Obtiene el valor de la propiedad respuesta.
   * 
   * @return possible object is {@link RespuestaType }
   * 
   */
  public RespuestaType getRespuesta() {
    return respuesta;
  }

  /**
   * Define el valor de la propiedad respuesta.
   * 
   * @param value allowed object is {@link RespuestaType }
   * 
   */
  public void setRespuesta(RespuestaType value) {
    this.respuesta = value;
  }

  /**
   * Obtiene el valor de la propiedad expedienteEni.
   * 
   * @return possible object is {@link TipoExpediente }
   * 
   */
  public TipoExpediente getExpedienteEni() {
    return expedienteEni;
  }

  /**
   * Define el valor de la propiedad expedienteEni.
   * 
   * @param value allowed object is {@link TipoExpediente }
   * 
   */
  public void setExpedienteEni(TipoExpediente value) {
    this.expedienteEni = value;
  }

  /**
   * Obtiene el valor de la propiedad metadatosAdicionales.
   * 
   * @return possible object is {@link TipoMetadatosAdicionales }
   * 
   */
  public TipoMetadatosAdicionales getMetadatosAdicionales() {
    return metadatosAdicionales;
  }

  /**
   * Define el valor de la propiedad metadatosAdicionales.
   * 
   * @param value allowed object is {@link TipoMetadatosAdicionales }
   * 
   */
  public void setMetadatosAdicionales(TipoMetadatosAdicionales value) {
    this.metadatosAdicionales = value;
  }

  /**
   * Gets the value of the metadatosDocumento property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the metadatosDocumento property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getMetadatosDocumento().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list {@link TipoMetadatos }
   * 
   * 
   */
  public List<TipoMetadatos> getMetadatosDocumento() {
    if (metadatosDocumento == null) {
      metadatosDocumento = new ArrayList<TipoMetadatos>();
    }
    return this.metadatosDocumento;
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

}
