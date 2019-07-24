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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Java class for DatosIdEnvioType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DatosIdEnvioType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://justicia.es/esb/expedienteElectronico/RemisionExpedienteCallback/xsd-schemas/V1}DatosEnvioType">
 *       &lt;sequence>
 *         &lt;element name="identificadorEstado" type="{http://justicia.es/esb/expedienteElectronico/RemisionExpedienteCallback/xsd-schemas/V1}estadoEnvioType"/>
 *         &lt;element name="descripcionEstado" type="{http://justicia.es/esb/expedienteElectronico/RemisionExpedienteCallback/xsd-schemas/V1}descEstadoType" minOccurs="0"/>
 *         &lt;element name="comentario" type="{http://justicia.es/esb/expedienteElectronico/RemisionExpedienteCallback/xsd-schemas/V1}comentarioType" minOccurs="0"/>
 *         &lt;element name="resguardo" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatosIdEnvioType",
    propOrder = {"identificadorEstado", "descripcionEstado", "comentario", "resguardo"})
public class DatosIdEnvioType extends DatosEnvioType {

  @XmlElement(required = true)
  protected EstadoEnvioType identificadorEstado;
  protected String descripcionEstado;
  protected String comentario;
  protected byte[] resguardo;

  /**
   * Gets the value of the identificadorEstado property.
   * 
   * @return possible object is {@link EstadoEnvioType }
   * 
   */
  public EstadoEnvioType getIdentificadorEstado() {
    return identificadorEstado;
  }

  /**
   * Sets the value of the identificadorEstado property.
   * 
   * @param value allowed object is {@link EstadoEnvioType }
   * 
   */
  public void setIdentificadorEstado(EstadoEnvioType value) {
    this.identificadorEstado = value;
  }

  /**
   * Gets the value of the descripcionEstado property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDescripcionEstado() {
    return descripcionEstado;
  }

  /**
   * Sets the value of the descripcionEstado property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDescripcionEstado(String value) {
    this.descripcionEstado = value;
  }

  /**
   * Gets the value of the comentario property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getComentario() {
    return comentario;
  }

  /**
   * Sets the value of the comentario property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setComentario(String value) {
    this.comentario = value;
  }

  /**
   * Gets the value of the resguardo property.
   * 
   * @return possible object is byte[]
   */
  public byte[] getResguardo() {
    return resguardo;
  }

  /**
   * Sets the value of the resguardo property.
   * 
   * @param value allowed object is byte[]
   */
  public void setResguardo(byte[] value) {
    this.resguardo = ((byte[]) value);
  }

}
