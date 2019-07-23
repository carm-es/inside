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


package es.mpt.dsic.inside.service.validacionENIMtom.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Clase Java para Detalle complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Detalle">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idObjeto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigoRespuesta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="detalleRespuesta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Detalle", propOrder = {"idObjeto", "codigoRespuesta", "detalleRespuesta"})
public class Detalle {

  @XmlElement(required = true)
  protected String idObjeto;
  @XmlElement(required = true)
  protected String codigoRespuesta;
  @XmlElement(required = true)
  protected String detalleRespuesta;

  /**
   * Obtiene el valor de la propiedad idObjeto.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getIdObjeto() {
    return idObjeto;
  }

  /**
   * Define el valor de la propiedad idObjeto.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setIdObjeto(String value) {
    this.idObjeto = value;
  }

  /**
   * Obtiene el valor de la propiedad codigoRespuesta.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCodigoRespuesta() {
    return codigoRespuesta;
  }

  /**
   * Define el valor de la propiedad codigoRespuesta.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCodigoRespuesta(String value) {
    this.codigoRespuesta = value;
  }

  /**
   * Obtiene el valor de la propiedad detalleRespuesta.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDetalleRespuesta() {
    return detalleRespuesta;
  }

  /**
   * Define el valor de la propiedad detalleRespuesta.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDetalleRespuesta(String value) {
    this.detalleRespuesta = value;
  }

}
