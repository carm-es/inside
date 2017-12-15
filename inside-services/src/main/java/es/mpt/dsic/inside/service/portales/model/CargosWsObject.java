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
 * <p>Clase Java para CargosWsObject complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CargosWsObject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="entidades" type="{https://ssweb.seap.minhap.es/portalEELL/}CargoEntidadWsObjectArray"/>
 *         &lt;element name="organismos" type="{https://ssweb.seap.minhap.es/portalEELL/}CargoOrganismoWsObjectArray"/>
 *         &lt;element name="unidades" type="{https://ssweb.seap.minhap.es/portalEELL/}CargoUnidadDirWsObjectArray"/>
 *         &lt;element name="instituciones" type="{https://ssweb.seap.minhap.es/portalEELL/}CargoInstitucionWsObjectArray"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="apellido1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="apellido2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nif" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CargosWsObject", propOrder = {
    "entidades",
    "organismos",
    "unidades",
    "instituciones",
    "nombre",
    "apellido1",
    "apellido2",
    "nif",
    "email"
})
public class CargosWsObject {

    @XmlElement(required = true)
    protected CargoEntidadWsObjectArray entidades;
    @XmlElement(required = true)
    protected CargoOrganismoWsObjectArray organismos;
    @XmlElement(required = true)
    protected CargoUnidadDirWsObjectArray unidades;
    @XmlElement(required = true)
    protected CargoInstitucionWsObjectArray instituciones;
    @XmlElement(required = true)
    protected String nombre;
    @XmlElement(required = true)
    protected String apellido1;
    @XmlElement(required = true)
    protected String apellido2;
    @XmlElement(required = true)
    protected String nif;
    @XmlElement(required = true)
    protected String email;

    /**
     * Obtiene el valor de la propiedad entidades.
     * 
     * @return
     *     possible object is
     *     {@link CargoEntidadWsObjectArray }
     *     
     */
    public CargoEntidadWsObjectArray getEntidades() {
        return entidades;
    }

    /**
     * Define el valor de la propiedad entidades.
     * 
     * @param value
     *     allowed object is
     *     {@link CargoEntidadWsObjectArray }
     *     
     */
    public void setEntidades(CargoEntidadWsObjectArray value) {
        this.entidades = value;
    }

    /**
     * Obtiene el valor de la propiedad organismos.
     * 
     * @return
     *     possible object is
     *     {@link CargoOrganismoWsObjectArray }
     *     
     */
    public CargoOrganismoWsObjectArray getOrganismos() {
        return organismos;
    }

    /**
     * Define el valor de la propiedad organismos.
     * 
     * @param value
     *     allowed object is
     *     {@link CargoOrganismoWsObjectArray }
     *     
     */
    public void setOrganismos(CargoOrganismoWsObjectArray value) {
        this.organismos = value;
    }

    /**
     * Obtiene el valor de la propiedad unidades.
     * 
     * @return
     *     possible object is
     *     {@link CargoUnidadDirWsObjectArray }
     *     
     */
    public CargoUnidadDirWsObjectArray getUnidades() {
        return unidades;
    }

    /**
     * Define el valor de la propiedad unidades.
     * 
     * @param value
     *     allowed object is
     *     {@link CargoUnidadDirWsObjectArray }
     *     
     */
    public void setUnidades(CargoUnidadDirWsObjectArray value) {
        this.unidades = value;
    }

    /**
     * Obtiene el valor de la propiedad instituciones.
     * 
     * @return
     *     possible object is
     *     {@link CargoInstitucionWsObjectArray }
     *     
     */
    public CargoInstitucionWsObjectArray getInstituciones() {
        return instituciones;
    }

    /**
     * Define el valor de la propiedad instituciones.
     * 
     * @param value
     *     allowed object is
     *     {@link CargoInstitucionWsObjectArray }
     *     
     */
    public void setInstituciones(CargoInstitucionWsObjectArray value) {
        this.instituciones = value;
    }

    /**
     * Obtiene el valor de la propiedad nombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el valor de la propiedad nombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Obtiene el valor de la propiedad apellido1.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellido1() {
        return apellido1;
    }

    /**
     * Define el valor de la propiedad apellido1.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellido1(String value) {
        this.apellido1 = value;
    }

    /**
     * Obtiene el valor de la propiedad apellido2.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellido2() {
        return apellido2;
    }

    /**
     * Define el valor de la propiedad apellido2.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellido2(String value) {
        this.apellido2 = value;
    }

    /**
     * Obtiene el valor de la propiedad nif.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNif() {
        return nif;
    }

    /**
     * Define el valor de la propiedad nif.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNif(String value) {
        this.nif = value;
    }

    /**
     * Obtiene el valor de la propiedad email.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define el valor de la propiedad email.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

}
