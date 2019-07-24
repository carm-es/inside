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
 * Clase Java para CSVInfoAmbito complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CSVInfoAmbito">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contenido" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="mime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contenidoFirmado" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="ambito" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CSVInfoAmbito", propOrder = {"contenido", "mime", "contenidoFirmado", "ambito"})
public class CSVInfoAmbito {

  @XmlElement(required = true)
  protected byte[] contenido;
  @XmlElement(required = true)
  protected String mime;
  protected byte[] contenidoFirmado;
  @XmlElement(required = true)
  protected String ambito;

  /**
   * Obtiene el valor de la propiedad contenido.
   * 
   * @return possible object is byte[]
   */
  public byte[] getContenido() {
    return contenido;
  }

  /**
   * Define el valor de la propiedad contenido.
   * 
   * @param value allowed object is byte[]
   */
  public void setContenido(byte[] value) {
    this.contenido = value;
  }

  /**
   * Obtiene el valor de la propiedad mime.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getMime() {
    return mime;
  }

  /**
   * Define el valor de la propiedad mime.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setMime(String value) {
    this.mime = value;
  }

  /**
   * Obtiene el valor de la propiedad contenidoFirmado.
   * 
   * @return possible object is byte[]
   */
  public byte[] getContenidoFirmado() {
    return contenidoFirmado;
  }

  /**
   * Define el valor de la propiedad contenidoFirmado.
   * 
   * @param value allowed object is byte[]
   */
  public void setContenidoFirmado(byte[] value) {
    this.contenidoFirmado = value;
  }

  /**
   * Obtiene el valor de la propiedad ambito.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getAmbito() {
    return ambito;
  }

  /**
   * Define el valor de la propiedad ambito.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setAmbito(String value) {
    this.ambito = value;
  }

}
