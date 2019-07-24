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


package csvstorage.es.gob.aapp.csvstorage.webservices.bigaDataTransfer.document.v1.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Clase Java para documentoRequest complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="documentoRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dir3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="csv" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idEni" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoPermiso" type="{urn:es:gob:aapp:csvstorage:webservices:document:model:v1.0}tipoPermiso" minOccurs="0"/>
 *         &lt;element name="nifs" type="{urn:es:gob:aapp:csvstorage:webservices:document:model:v1.0}nifs" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "documentoRequest", propOrder = {"dir3", "csv", "idEni", "tipoPermiso", "nifs"})
@XmlSeeAlso({GuardarDocumentoBigDataRequest.class})
public class DocumentoRequest {

  @XmlElement(required = true)
  protected String dir3;
  protected String csv;
  protected String idEni;
  @XmlSchemaType(name = "string")
  protected TipoPermiso tipoPermiso;
  protected Nifs nifs;

  /**
   * Obtiene el valor de la propiedad dir3.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDir3() {
    return dir3;
  }

  /**
   * Define el valor de la propiedad dir3.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDir3(String value) {
    this.dir3 = value;
  }

  /**
   * Obtiene el valor de la propiedad csv.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCsv() {
    return csv;
  }

  /**
   * Define el valor de la propiedad csv.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCsv(String value) {
    this.csv = value;
  }

  /**
   * Obtiene el valor de la propiedad idEni.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getIdEni() {
    return idEni;
  }

  /**
   * Define el valor de la propiedad idEni.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setIdEni(String value) {
    this.idEni = value;
  }

  /**
   * Obtiene el valor de la propiedad tipoPermiso.
   * 
   * @return possible object is {@link TipoPermiso }
   * 
   */
  public TipoPermiso getTipoPermiso() {
    return tipoPermiso;
  }

  /**
   * Define el valor de la propiedad tipoPermiso.
   * 
   * @param value allowed object is {@link TipoPermiso }
   * 
   */
  public void setTipoPermiso(TipoPermiso value) {
    this.tipoPermiso = value;
  }

  /**
   * Obtiene el valor de la propiedad nifs.
   * 
   * @return possible object is {@link Nifs }
   * 
   */
  public Nifs getNifs() {
    return nifs;
  }

  /**
   * Define el valor de la propiedad nifs.
   * 
   * @param value allowed object is {@link Nifs }
   * 
   */
  public void setNifs(Nifs value) {
    this.nifs = value;
  }

}
