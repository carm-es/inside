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


package es.mpt.dsic.firma.cliente.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Java class for datosSalida complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="datosSalida">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="resultado" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="datosResultado" type="{http://service.ws.inside.dsic.mpt.es/}contenidoSalida"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "datosSalida", propOrder = {"estado", "resultado", "datosResultado"})
public class DatosSalida {

  @XmlElement(required = true)
  protected String estado;
  @XmlElement(required = true)
  protected String resultado;
  @XmlElement(required = true)
  protected ContenidoSalida datosResultado;

  /**
   * Gets the value of the estado property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getEstado() {
    return estado;
  }

  /**
   * Sets the value of the estado property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setEstado(String value) {
    this.estado = value;
  }

  /**
   * Gets the value of the resultado property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getResultado() {
    return resultado;
  }

  /**
   * Sets the value of the resultado property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setResultado(String value) {
    this.resultado = value;
  }

  /**
   * Gets the value of the datosResultado property.
   * 
   * @return possible object is {@link ContenidoSalida }
   * 
   */
  public ContenidoSalida getDatosResultado() {
    return datosResultado;
  }

  /**
   * Sets the value of the datosResultado property.
   * 
   * @param value allowed object is {@link ContenidoSalida }
   * 
   */
  public void setDatosResultado(ContenidoSalida value) {
    this.datosResultado = value;
  }

}
