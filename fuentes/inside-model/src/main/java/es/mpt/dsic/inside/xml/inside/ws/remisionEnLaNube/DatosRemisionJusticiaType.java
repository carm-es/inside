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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Esquema que representa datos comunes en una peticion en Remisión en la nube
 * 
 * <p>
 * Java class for DatosRemisionJusticiaType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DatosRemisionJusticiaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dir3Remitente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nig" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="claseProcedimiento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="anyoProcedimiento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numeroProcedimiento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatosRemisionJusticiaType", propOrder = {"dir3Remitente", "nig",
    "claseProcedimiento", "anyoProcedimiento", "numeroProcedimiento", "descripcion"})
public class DatosRemisionJusticiaType {

  @XmlElement(required = true)
  protected String dir3Remitente;
  @XmlElement(required = true)
  protected String nig;
  @XmlElement(required = true)
  protected String claseProcedimiento;
  @XmlElement(required = true)
  protected String anyoProcedimiento;
  @XmlElement(required = true)
  protected String numeroProcedimiento;
  @XmlElement(required = true)
  protected String descripcion;

  /**
   * Gets the value of the dir3Remitente property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDir3Remitente() {
    return dir3Remitente;
  }

  /**
   * Sets the value of the dir3Remitente property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDir3Remitente(String value) {
    this.dir3Remitente = value;
  }

  /**
   * Gets the value of the nig property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getNig() {
    return nig;
  }

  /**
   * Sets the value of the nig property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setNig(String value) {
    this.nig = value;
  }

  /**
   * Gets the value of the claseProcedimiento property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getClaseProcedimiento() {
    return claseProcedimiento;
  }

  /**
   * Sets the value of the claseProcedimiento property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setClaseProcedimiento(String value) {
    this.claseProcedimiento = value;
  }

  /**
   * Gets the value of the anyoProcedimiento property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getAnyoProcedimiento() {
    return anyoProcedimiento;
  }

  /**
   * Sets the value of the anyoProcedimiento property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setAnyoProcedimiento(String value) {
    this.anyoProcedimiento = value;
  }

  /**
   * Gets the value of the numeroProcedimiento property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getNumeroProcedimiento() {
    return numeroProcedimiento;
  }

  /**
   * Sets the value of the numeroProcedimiento property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setNumeroProcedimiento(String value) {
    this.numeroProcedimiento = value;
  }

  /**
   * Gets the value of the descripcion property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDescripcion() {
    return descripcion;
  }

  /**
   * Sets the value of the descripcion property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDescripcion(String value) {
    this.descripcion = value;
  }

}
