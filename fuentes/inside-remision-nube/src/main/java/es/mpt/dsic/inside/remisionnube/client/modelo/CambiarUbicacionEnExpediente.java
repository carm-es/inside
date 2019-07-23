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
 * <p>
 * Clase Java para cambiarUbicacionEnExpediente complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="cambiarUbicacionEnExpediente">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="identificadorExpediente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="identificadoresElementos" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="rutaOrigen" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="rutaDestino" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cambiarUbicacionEnExpediente", propOrder = {"identificadorExpediente",
    "identificadoresElementos", "rutaOrigen", "rutaDestino"})
public class CambiarUbicacionEnExpediente {

  @XmlElement(required = true)
  protected String identificadorExpediente;
  @XmlElement(required = true)
  protected List<String> identificadoresElementos;
  @XmlElement(required = true)
  protected String rutaOrigen;
  @XmlElement(required = true)
  protected String rutaDestino;

  /**
   * Obtiene el valor de la propiedad identificadorExpediente.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getIdentificadorExpediente() {
    return identificadorExpediente;
  }

  /**
   * Define el valor de la propiedad identificadorExpediente.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setIdentificadorExpediente(String value) {
    this.identificadorExpediente = value;
  }

  /**
   * Gets the value of the identificadoresElementos property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the identificadoresElementos property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getIdentificadoresElementos().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list {@link String }
   * 
   * 
   */
  public List<String> getIdentificadoresElementos() {
    if (identificadoresElementos == null) {
      identificadoresElementos = new ArrayList<String>();
    }
    return this.identificadoresElementos;
  }

  /**
   * Obtiene el valor de la propiedad rutaOrigen.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getRutaOrigen() {
    return rutaOrigen;
  }

  /**
   * Define el valor de la propiedad rutaOrigen.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setRutaOrigen(String value) {
    this.rutaOrigen = value;
  }

  /**
   * Obtiene el valor de la propiedad rutaDestino.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getRutaDestino() {
    return rutaDestino;
  }

  /**
   * Define el valor de la propiedad rutaDestino.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setRutaDestino(String value) {
    this.rutaDestino = value;
  }

}
