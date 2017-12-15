/* Copyright (C) 2016 MINHAP, Gobierno de España
   This program is licensed and may be used, modified and redistributed under the terms
   of the European Public License (EUPL), either version 1.1 or (at your
   option) any later version as soon as they are approved by the European Commission.
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
   or implied. See the License for the specific language governing permissions and
   more details.
   You should have received a copy of the EUPL1.1 license
   along with this program; if not, you may find it at
   http://joinup.ec.europa.eu/software/page/eupl/licence-eupl */


package es.mpt.dsic.inside.service.portales.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para CargoInstitucionWsObject complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CargoInstitucionWsObject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codigo_dir" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nombre_unidad" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="estado_unidad" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigo_localidad" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="provincia" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="comunidad_autonoma" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="desc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="perfil" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="administrador" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="recursivo" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CargoInstitucionWsObject", propOrder = {
    "codigoDir",
    "nombreUnidad",
    "estadoUnidad",
    "codigoLocalidad",
    "provincia",
    "comunidadAutonoma",
    "tipo",
    "desc",
    "perfil",
    "administrador",
    "recursivo"
})
public class CargoInstitucionWsObject {

    @XmlElement(name = "codigo_dir", required = true)
    protected String codigoDir;
    @XmlElement(name = "nombre_unidad", required = true)
    protected String nombreUnidad;
    @XmlElement(name = "estado_unidad", required = true)
    protected String estadoUnidad;
    @XmlElement(name = "codigo_localidad", required = true)
    protected String codigoLocalidad;
    @XmlElement(required = true)
    protected String provincia;
    @XmlElement(name = "comunidad_autonoma", required = true)
    protected String comunidadAutonoma;
    @XmlElement(required = true)
    protected String tipo;
    @XmlElement(required = true)
    protected String desc;
    @XmlElement(required = true)
    protected String perfil;
    protected boolean administrador;
    protected boolean recursivo;

    /**
     * Obtiene el valor de la propiedad codigoDir.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoDir() {
        return codigoDir;
    }

    /**
     * Define el valor de la propiedad codigoDir.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoDir(String value) {
        this.codigoDir = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreUnidad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreUnidad() {
        return nombreUnidad;
    }

    /**
     * Define el valor de la propiedad nombreUnidad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreUnidad(String value) {
        this.nombreUnidad = value;
    }

    /**
     * Obtiene el valor de la propiedad estadoUnidad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstadoUnidad() {
        return estadoUnidad;
    }

    /**
     * Define el valor de la propiedad estadoUnidad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstadoUnidad(String value) {
        this.estadoUnidad = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoLocalidad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoLocalidad() {
        return codigoLocalidad;
    }

    /**
     * Define el valor de la propiedad codigoLocalidad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoLocalidad(String value) {
        this.codigoLocalidad = value;
    }

    /**
     * Obtiene el valor de la propiedad provincia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Define el valor de la propiedad provincia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvincia(String value) {
        this.provincia = value;
    }

    /**
     * Obtiene el valor de la propiedad comunidadAutonoma.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComunidadAutonoma() {
        return comunidadAutonoma;
    }

    /**
     * Define el valor de la propiedad comunidadAutonoma.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComunidadAutonoma(String value) {
        this.comunidadAutonoma = value;
    }

    /**
     * Obtiene el valor de la propiedad tipo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Define el valor de la propiedad tipo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipo(String value) {
        this.tipo = value;
    }

    /**
     * Obtiene el valor de la propiedad desc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Define el valor de la propiedad desc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesc(String value) {
        this.desc = value;
    }

    /**
     * Obtiene el valor de la propiedad perfil.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPerfil() {
        return perfil;
    }

    /**
     * Define el valor de la propiedad perfil.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPerfil(String value) {
        this.perfil = value;
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

    /**
     * Obtiene el valor de la propiedad recursivo.
     * 
     */
    public boolean isRecursivo() {
        return recursivo;
    }

    /**
     * Define el valor de la propiedad recursivo.
     * 
     */
    public void setRecursivo(boolean value) {
        this.recursivo = value;
    }

}
