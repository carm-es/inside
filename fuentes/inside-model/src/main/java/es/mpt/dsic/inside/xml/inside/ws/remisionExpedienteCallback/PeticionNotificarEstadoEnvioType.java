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
 * Esquema de petición de recepción de cualquier Evento de Fin de tarea/Error en tarea generado en
 * el Gestor Documental.
 * 
 * <p>
 * Java class for PeticionNotificarEstadoEnvioType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PeticionNotificarEstadoEnvioType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AuditoriaEsb" type="{http://justicia.es/esb/comun/xsd-schemas/V1}AuditoriaEsbType"/>
 *         &lt;element name="Envio" type="{http://justicia.es/esb/expedienteElectronico/RemisionExpedienteCallback/xsd-schemas/V1}DatosIdEnvioType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PeticionNotificarEstadoEnvioType", propOrder = {"auditoriaEsb", "envio"})
public class PeticionNotificarEstadoEnvioType {

  @XmlElement(name = "AuditoriaEsb", required = true)
  protected AuditoriaEsbType auditoriaEsb;
  @XmlElement(name = "Envio")
  protected DatosIdEnvioType envio;

  /**
   * Gets the value of the auditoriaEsb property.
   * 
   * @return possible object is {@link AuditoriaEsbType }
   * 
   */
  public AuditoriaEsbType getAuditoriaEsb() {
    return auditoriaEsb;
  }

  /**
   * Sets the value of the auditoriaEsb property.
   * 
   * @param value allowed object is {@link AuditoriaEsbType }
   * 
   */
  public void setAuditoriaEsb(AuditoriaEsbType value) {
    this.auditoriaEsb = value;
  }

  /**
   * Gets the value of the envio property.
   * 
   * @return possible object is {@link DatosIdEnvioType }
   * 
   */
  public DatosIdEnvioType getEnvio() {
    return envio;
  }

  /**
   * Sets the value of the envio property.
   * 
   * @param value allowed object is {@link DatosIdEnvioType }
   * 
   */
  public void setEnvio(DatosIdEnvioType value) {
    this.envio = value;
  }

}
