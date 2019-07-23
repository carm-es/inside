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


package es.mpt.dsic.eeutil.client.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Clase Java para resultadoValidarCertificado complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="resultadoValidarCertificado">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="validado" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="idUsuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroSerie" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="detalleValidacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "resultadoValidarCertificado",
    propOrder = {"validado", "idUsuario", "numeroSerie", "detalleValidacion"})
public class ResultadoValidarCertificado {

  protected boolean validado;
  protected String idUsuario;
  protected String numeroSerie;
  @XmlElement(required = true)
  protected String detalleValidacion;

  /**
   * Obtiene el valor de la propiedad validado.
   * 
   */
  public boolean isValidado() {
    return validado;
  }

  /**
   * Define el valor de la propiedad validado.
   * 
   */
  public void setValidado(boolean value) {
    this.validado = value;
  }

  /**
   * Obtiene el valor de la propiedad idUsuario.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getIdUsuario() {
    return idUsuario;
  }

  /**
   * Define el valor de la propiedad idUsuario.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setIdUsuario(String value) {
    this.idUsuario = value;
  }

  /**
   * Obtiene el valor de la propiedad numeroSerie.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getNumeroSerie() {
    return numeroSerie;
  }

  /**
   * Define el valor de la propiedad numeroSerie.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setNumeroSerie(String value) {
    this.numeroSerie = value;
  }

  /**
   * Obtiene el valor de la propiedad detalleValidacion.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDetalleValidacion() {
    return detalleValidacion;
  }

  /**
   * Define el valor de la propiedad detalleValidacion.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDetalleValidacion(String value) {
    this.detalleValidacion = value;
  }

}
