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


package es.mpt.dsic.inside.service.portales.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Clase Java para CargoOrganismoWsObject complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CargoOrganismoWsObject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codigo_dir" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigo_eell" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigo_ine" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="organismo_tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="organismo_descripcion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="organismo_nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cargo_tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cargo_tipo_original" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cargo_nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cif" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ccaa" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="administrador" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CargoOrganismoWsObject",
    propOrder = {"codigoDir", "codigoEell", "codigoIne", "organismoTipo", "organismoDescripcion",
        "organismoNombre", "cargoTipo", "cargoTipoOriginal", "cargoNombre", "estado", "cif", "ccaa",
        "administrador"})
public class CargoOrganismoWsObject {

  @XmlElement(name = "codigo_dir", required = true)
  protected String codigoDir;
  @XmlElement(name = "codigo_eell", required = true)
  protected String codigoEell;
  @XmlElement(name = "codigo_ine", required = true)
  protected String codigoIne;
  @XmlElement(name = "organismo_tipo", required = true)
  protected String organismoTipo;
  @XmlElement(name = "organismo_descripcion", required = true)
  protected String organismoDescripcion;
  @XmlElement(name = "organismo_nombre", required = true)
  protected String organismoNombre;
  @XmlElement(name = "cargo_tipo", required = true)
  protected String cargoTipo;
  @XmlElement(name = "cargo_tipo_original", required = true)
  protected String cargoTipoOriginal;
  @XmlElement(name = "cargo_nombre", required = true)
  protected String cargoNombre;
  @XmlElement(required = true)
  protected String estado;
  @XmlElement(required = true)
  protected String cif;
  @XmlElement(required = true)
  protected String ccaa;
  protected boolean administrador;

  /**
   * Obtiene el valor de la propiedad codigoDir.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCodigoDir() {
    return codigoDir;
  }

  /**
   * Define el valor de la propiedad codigoDir.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCodigoDir(String value) {
    this.codigoDir = value;
  }

  /**
   * Obtiene el valor de la propiedad codigoEell.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCodigoEell() {
    return codigoEell;
  }

  /**
   * Define el valor de la propiedad codigoEell.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCodigoEell(String value) {
    this.codigoEell = value;
  }

  /**
   * Obtiene el valor de la propiedad codigoIne.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCodigoIne() {
    return codigoIne;
  }

  /**
   * Define el valor de la propiedad codigoIne.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCodigoIne(String value) {
    this.codigoIne = value;
  }

  /**
   * Obtiene el valor de la propiedad organismoTipo.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getOrganismoTipo() {
    return organismoTipo;
  }

  /**
   * Define el valor de la propiedad organismoTipo.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setOrganismoTipo(String value) {
    this.organismoTipo = value;
  }

  /**
   * Obtiene el valor de la propiedad organismoDescripcion.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getOrganismoDescripcion() {
    return organismoDescripcion;
  }

  /**
   * Define el valor de la propiedad organismoDescripcion.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setOrganismoDescripcion(String value) {
    this.organismoDescripcion = value;
  }

  /**
   * Obtiene el valor de la propiedad organismoNombre.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getOrganismoNombre() {
    return organismoNombre;
  }

  /**
   * Define el valor de la propiedad organismoNombre.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setOrganismoNombre(String value) {
    this.organismoNombre = value;
  }

  /**
   * Obtiene el valor de la propiedad cargoTipo.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCargoTipo() {
    return cargoTipo;
  }

  /**
   * Define el valor de la propiedad cargoTipo.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCargoTipo(String value) {
    this.cargoTipo = value;
  }

  /**
   * Obtiene el valor de la propiedad cargoTipoOriginal.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCargoTipoOriginal() {
    return cargoTipoOriginal;
  }

  /**
   * Define el valor de la propiedad cargoTipoOriginal.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCargoTipoOriginal(String value) {
    this.cargoTipoOriginal = value;
  }

  /**
   * Obtiene el valor de la propiedad cargoNombre.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCargoNombre() {
    return cargoNombre;
  }

  /**
   * Define el valor de la propiedad cargoNombre.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCargoNombre(String value) {
    this.cargoNombre = value;
  }

  /**
   * Obtiene el valor de la propiedad estado.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getEstado() {
    return estado;
  }

  /**
   * Define el valor de la propiedad estado.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setEstado(String value) {
    this.estado = value;
  }

  /**
   * Obtiene el valor de la propiedad cif.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCif() {
    return cif;
  }

  /**
   * Define el valor de la propiedad cif.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCif(String value) {
    this.cif = value;
  }

  /**
   * Obtiene el valor de la propiedad ccaa.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCcaa() {
    return ccaa;
  }

  /**
   * Define el valor de la propiedad ccaa.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCcaa(String value) {
    this.ccaa = value;
  }

  /**
   * Obtiene el valor de la propiedad administrador.
   * 
   */
  public boolean isAdministrador() {
    return administrador;
  }

  /**
   * Define el valor de la propiedad administrador.
   * 
   */
  public void setAdministrador(boolean value) {
    this.administrador = value;
  }

}
