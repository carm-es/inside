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
 * Clase Java para CargoUnidadDirWsObject complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CargoUnidadDirWsObject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codigo_dir" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="unidad_dir_nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cargo_tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cargo_tipo_original" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cargo_descripcion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="activo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="recursivo" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "CargoUnidadDirWsObject", propOrder = {"codigoDir", "unidadDirNombre", "estado",
    "cargoTipo", "cargoTipoOriginal", "cargoDescripcion", "activo", "recursivo", "administrador"})
public class CargoUnidadDirWsObject {

  @XmlElement(name = "codigo_dir", required = true)
  protected String codigoDir;
  @XmlElement(name = "unidad_dir_nombre", required = true)
  protected String unidadDirNombre;
  @XmlElement(required = true)
  protected String estado;
  @XmlElement(name = "cargo_tipo", required = true)
  protected String cargoTipo;
  @XmlElement(name = "cargo_tipo_original", required = true)
  protected String cargoTipoOriginal;
  @XmlElement(name = "cargo_descripcion", required = true)
  protected String cargoDescripcion;
  @XmlElement(required = true)
  protected String activo;
  @XmlElement(required = true)
  protected String recursivo;
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
   * Obtiene el valor de la propiedad unidadDirNombre.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getUnidadDirNombre() {
    return unidadDirNombre;
  }

  /**
   * Define el valor de la propiedad unidadDirNombre.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setUnidadDirNombre(String value) {
    this.unidadDirNombre = value;
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
   * Obtiene el valor de la propiedad cargoDescripcion.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCargoDescripcion() {
    return cargoDescripcion;
  }

  /**
   * Define el valor de la propiedad cargoDescripcion.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCargoDescripcion(String value) {
    this.cargoDescripcion = value;
  }

  /**
   * Obtiene el valor de la propiedad activo.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getActivo() {
    return activo;
  }

  /**
   * Define el valor de la propiedad activo.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setActivo(String value) {
    this.activo = value;
  }

  /**
   * Obtiene el valor de la propiedad recursivo.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getRecursivo() {
    return recursivo;
  }

  /**
   * Define el valor de la propiedad recursivo.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setRecursivo(String value) {
    this.recursivo = value;
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
