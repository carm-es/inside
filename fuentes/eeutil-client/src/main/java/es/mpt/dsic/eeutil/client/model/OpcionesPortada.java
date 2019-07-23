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
 * Java class for opcionesPortada complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="opcionesPortada">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tieneTitulo1Portada" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="tieneTitulo2Portada" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="titulo1Portada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="titulo2Portada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "opcionesPortada",
    propOrder = {"tieneTitulo1Portada", "tieneTitulo2Portada", "titulo1Portada", "titulo2Portada"})
public class OpcionesPortada {

  @XmlElement(defaultValue = "true")
  protected boolean tieneTitulo1Portada;
  @XmlElement(defaultValue = "true")
  protected boolean tieneTitulo2Portada;
  protected String titulo1Portada;
  protected String titulo2Portada;

  /**
   * Gets the value of the tieneTitulo1Portada property.
   * 
   */
  public boolean isTieneTitulo1Portada() {
    return tieneTitulo1Portada;
  }

  /**
   * Sets the value of the tieneTitulo1Portada property.
   * 
   */
  public void setTieneTitulo1Portada(boolean value) {
    this.tieneTitulo1Portada = value;
  }

  /**
   * Gets the value of the tieneTitulo2Portada property.
   * 
   */
  public boolean isTieneTitulo2Portada() {
    return tieneTitulo2Portada;
  }

  /**
   * Sets the value of the tieneTitulo2Portada property.
   * 
   */
  public void setTieneTitulo2Portada(boolean value) {
    this.tieneTitulo2Portada = value;
  }

  /**
   * Gets the value of the titulo1Portada property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getTitulo1Portada() {
    return titulo1Portada;
  }

  /**
   * Sets the value of the titulo1Portada property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setTitulo1Portada(String value) {
    this.titulo1Portada = value;
  }

  /**
   * Gets the value of the titulo2Portada property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getTitulo2Portada() {
    return titulo2Portada;
  }

  /**
   * Sets the value of the titulo2Portada property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setTitulo2Portada(String value) {
    this.titulo2Portada = value;
  }

}
