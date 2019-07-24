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
import javax.xml.bind.annotation.XmlType;
import es.mpt.dsic.inside.xml.eni.documento.TipoDocumento;
import es.mpt.dsic.inside.xml.inside.TipoMetadatosAdicionales;


/**
 * Esquema que representa una respuesta con un documentoEni
 * 
 * <p>
 * Java class for RespuestaAccesoDocumentoExpedienteType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RespuestaAccesoDocumentoExpedienteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="respuesta" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/remisionNube}RespuestaType" minOccurs="0"/>
 *         &lt;element name="documento" type="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e}TipoDocumento" minOccurs="0"/>
 *         &lt;element name="metadatosAdicionales" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/metadatosAdicionales}TipoMetadatosAdicionales" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RespuestaAccesoDocumentoExpedienteType",
    propOrder = {"respuesta", "documento", "metadatosAdicionales"})
public class RespuestaAccesoDocumentoExpedienteType {

  protected RespuestaType respuesta;
  protected TipoDocumento documento;
  protected TipoMetadatosAdicionales metadatosAdicionales;

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
   * Gets the value of the documento property.
   * 
   * @return possible object is {@link TipoDocumento }
   * 
   */
  public TipoDocumento getDocumento() {
    return documento;
  }

  /**
   * Sets the value of the documento property.
   * 
   * @param value allowed object is {@link TipoDocumento }
   * 
   */
  public void setDocumento(TipoDocumento value) {
    this.documento = value;
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

}
