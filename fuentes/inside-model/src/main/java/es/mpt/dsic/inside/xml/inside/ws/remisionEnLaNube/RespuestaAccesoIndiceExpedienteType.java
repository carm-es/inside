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

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import es.mpt.dsic.inside.xml.eni.documento.metadatos.TipoMetadatos;
import es.mpt.dsic.inside.xml.eni.expediente.TipoExpediente;
import es.mpt.dsic.inside.xml.inside.TipoMetadatosAdicionales;


/**
 * Esquema que representa una respuesta al acceso al indice del expediente
 * 
 * <p>
 * Java class for RespuestaAccesoIndiceExpedienteType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RespuestaAccesoIndiceExpedienteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="respuesta" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/remisionNube}RespuestaType" minOccurs="0"/>
 *         &lt;element name="indiceExp" type="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e}TipoExpediente" minOccurs="0"/>
 *         &lt;element name="expedienteEni" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
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
@XmlType(name = "RespuestaAccesoIndiceExpedienteType", propOrder = {"respuesta", "indiceExp",
    "expedienteEni", "metadatosAdicionales", "metadatosDocumento", "endpointRemitente"})
public class RespuestaAccesoIndiceExpedienteType {

  protected RespuestaType respuesta;
  protected TipoExpediente indiceExp;
  protected byte[] expedienteEni;
  protected TipoMetadatosAdicionales metadatosAdicionales;
  @XmlElement(name = "metadatos_documento")
  protected List<TipoMetadatos> metadatosDocumento;
  @XmlElement(name = "endpoint_remitente")
  protected String endpointRemitente;

  /**
   * Gets the value of the respuesta property.
   * 
   * @return possible object is {@link RespuestaType }
   * 
   */
  public RespuestaType getRespuesta() {
    return respuesta;
  }

  /**
   * Sets the value of the respuesta property.
   * 
   * @param value allowed object is {@link RespuestaType }
   * 
   */
  public void setRespuesta(RespuestaType value) {
    this.respuesta = value;
  }

  /**
   * Gets the value of the indiceExp property.
   * 
   * @return possible object is {@link TipoExpediente }
   * 
   */
  public TipoExpediente getIndiceExp() {
    return indiceExp;
  }

  /**
   * Sets the value of the indiceExp property.
   * 
   * @param value allowed object is {@link TipoExpediente }
   * 
   */
  public void setIndiceExp(TipoExpediente value) {
    this.indiceExp = value;
  }

  /**
   * Gets the value of the expedienteEni property.
   * 
   * @return possible object is byte[]
   */
  public byte[] getExpedienteEni() {
    return expedienteEni;
  }

  /**
   * Sets the value of the expedienteEni property.
   * 
   * @param value allowed object is byte[]
   */
  public void setExpedienteEni(byte[] value) {
    this.expedienteEni = ((byte[]) value);
  }

  /**
   * Gets the value of the metadatosAdicionales property.
   * 
   * @return possible object is {@link TipoMetadatosAdicionales }
   * 
   */
  public TipoMetadatosAdicionales getMetadatosAdicionales() {
    return metadatosAdicionales;
  }

  /**
   * Sets the value of the metadatosAdicionales property.
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
   * Gets the value of the endpointRemitente property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getEndpointRemitente() {
    return endpointRemitente;
  }

  /**
   * Sets the value of the endpointRemitente property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setEndpointRemitente(String value) {
    this.endpointRemitente = value;
  }

}
