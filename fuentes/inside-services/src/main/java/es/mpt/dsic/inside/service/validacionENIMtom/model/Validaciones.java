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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Clase Java para Validaciones complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Validaciones">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="validaSchema" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="validaDir3" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="validaSIA" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="validaFirma" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Validaciones",
    propOrder = {"validaSchema", "validaDir3", "validaSIA", "validaFirma"})
public class Validaciones {

  protected Boolean validaSchema;
  protected Boolean validaDir3;
  protected Boolean validaSIA;
  protected Boolean validaFirma;

  /**
   * Obtiene el valor de la propiedad validaSchema.
   * 
   * @return possible object is {@link Boolean }
   * 
   */
  public Boolean isValidaSchema() {
    return validaSchema;
  }

  /**
   * Define el valor de la propiedad validaSchema.
   * 
   * @param value allowed object is {@link Boolean }
   * 
   */
  public void setValidaSchema(Boolean value) {
    this.validaSchema = value;
  }

  /**
   * Obtiene el valor de la propiedad validaDir3.
   * 
   * @return possible object is {@link Boolean }
   * 
   */
  public Boolean isValidaDir3() {
    return validaDir3;
  }

  /**
   * Define el valor de la propiedad validaDir3.
   * 
   * @param value allowed object is {@link Boolean }
   * 
   */
  public void setValidaDir3(Boolean value) {
    this.validaDir3 = value;
  }

  /**
   * Obtiene el valor de la propiedad validaSIA.
   * 
   * @return possible object is {@link Boolean }
   * 
   */
  public Boolean isValidaSIA() {
    return validaSIA;
  }

  /**
   * Define el valor de la propiedad validaSIA.
   * 
   * @param value allowed object is {@link Boolean }
   * 
   */
  public void setValidaSIA(Boolean value) {
    this.validaSIA = value;
  }

  /**
   * Obtiene el valor de la propiedad validaFirma.
   * 
   * @return possible object is {@link Boolean }
   * 
   */
  public Boolean isValidaFirma() {
    return validaFirma;
  }

  /**
   * Define el valor de la propiedad validaFirma.
   * 
   * @param value allowed object is {@link Boolean }
   * 
   */
  public void setValidaFirma(Boolean value) {
    this.validaFirma = value;
  }

}
