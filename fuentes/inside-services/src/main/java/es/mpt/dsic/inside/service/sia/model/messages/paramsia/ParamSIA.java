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


package es.mpt.dsic.inside.service.sia.model.messages.paramsia;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="user" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="certificado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="filtro">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="anioVolTramitaciones" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="fechasModificacion" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="fechaInicio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="fechaFin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="organismo" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="codOrganismoN1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="codOrganismoN2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="tipoActuacion" type="{http://www.map.es/sgca/consultar/messages/ParamSIA}tipoActuacion" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "user",
    "password",
    "certificado",
    "filtro"
})
@XmlRootElement(name = "paramSIA")
public class ParamSIA {

    protected String user;
    protected String password;
    protected String certificado;
    @XmlElement(required = true)
    protected ParamSIA.Filtro filtro;

    /**
     * Obtiene el valor de la propiedad user.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUser() {
        return user;
    }

    /**
     * Define el valor de la propiedad user.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUser(String value) {
        this.user = value;
    }

    /**
     * Obtiene el valor de la propiedad password.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Define el valor de la propiedad password.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Obtiene el valor de la propiedad certificado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertificado() {
        return certificado;
    }

    /**
     * Define el valor de la propiedad certificado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertificado(String value) {
        this.certificado = value;
    }

    /**
     * Obtiene el valor de la propiedad filtro.
     * 
     * @return
     *     possible object is
     *     {@link ParamSIA.Filtro }
     *     
     */
    public ParamSIA.Filtro getFiltro() {
        return filtro;
    }

    /**
     * Define el valor de la propiedad filtro.
     * 
     * @param value
     *     allowed object is
     *     {@link ParamSIA.Filtro }
     *     
     */
    public void setFiltro(ParamSIA.Filtro value) {
        this.filtro = value;
    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="anioVolTramitaciones" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="fechasModificacion" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="fechaInicio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="fechaFin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="organismo" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="codOrganismoN1" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="codOrganismoN2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="tipoActuacion" type="{http://www.map.es/sgca/consultar/messages/ParamSIA}tipoActuacion" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "anioVolTramitaciones",
        "fechasModificacion",
        "organismo",
        "tipoActuacion"
    })
    public static class Filtro {

        @XmlElement(required = true)
        protected String anioVolTramitaciones;
        protected ParamSIA.Filtro.FechasModificacion fechasModificacion;
        protected ParamSIA.Filtro.Organismo organismo;
        protected String tipoActuacion;

        /**
         * Obtiene el valor de la propiedad anioVolTramitaciones.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAnioVolTramitaciones() {
            return anioVolTramitaciones;
        }

        /**
         * Define el valor de la propiedad anioVolTramitaciones.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAnioVolTramitaciones(String value) {
            this.anioVolTramitaciones = value;
        }

        /**
         * Obtiene el valor de la propiedad fechasModificacion.
         * 
         * @return
         *     possible object is
         *     {@link ParamSIA.Filtro.FechasModificacion }
         *     
         */
        public ParamSIA.Filtro.FechasModificacion getFechasModificacion() {
            return fechasModificacion;
        }

        /**
         * Define el valor de la propiedad fechasModificacion.
         * 
         * @param value
         *     allowed object is
         *     {@link ParamSIA.Filtro.FechasModificacion }
         *     
         */
        public void setFechasModificacion(ParamSIA.Filtro.FechasModificacion value) {
            this.fechasModificacion = value;
        }

        /**
         * Obtiene el valor de la propiedad organismo.
         * 
         * @return
         *     possible object is
         *     {@link ParamSIA.Filtro.Organismo }
         *     
         */
        public ParamSIA.Filtro.Organismo getOrganismo() {
            return organismo;
        }

        /**
         * Define el valor de la propiedad organismo.
         * 
         * @param value
         *     allowed object is
         *     {@link ParamSIA.Filtro.Organismo }
         *     
         */
        public void setOrganismo(ParamSIA.Filtro.Organismo value) {
            this.organismo = value;
        }

        /**
         * Obtiene el valor de la propiedad tipoActuacion.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTipoActuacion() {
            return tipoActuacion;
        }

        /**
         * Define el valor de la propiedad tipoActuacion.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTipoActuacion(String value) {
            this.tipoActuacion = value;
        }


        /**
         * <p>Clase Java para anonymous complex type.
         * 
         * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="fechaInicio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="fechaFin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "fechaInicio",
            "fechaFin"
        })
        public static class FechasModificacion {

            protected String fechaInicio;
            protected String fechaFin;

            /**
             * Obtiene el valor de la propiedad fechaInicio.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFechaInicio() {
                return fechaInicio;
            }

            /**
             * Define el valor de la propiedad fechaInicio.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFechaInicio(String value) {
                this.fechaInicio = value;
            }

            /**
             * Obtiene el valor de la propiedad fechaFin.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFechaFin() {
                return fechaFin;
            }

            /**
             * Define el valor de la propiedad fechaFin.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFechaFin(String value) {
                this.fechaFin = value;
            }

        }


        /**
         * <p>Clase Java para anonymous complex type.
         * 
         * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="codOrganismoN1" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="codOrganismoN2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "codOrganismoN1",
            "codOrganismoN2"
        })
        public static class Organismo {

            @XmlElement(required = true)
            protected String codOrganismoN1;
            protected String codOrganismoN2;

            /**
             * Obtiene el valor de la propiedad codOrganismoN1.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCodOrganismoN1() {
                return codOrganismoN1;
            }

            /**
             * Define el valor de la propiedad codOrganismoN1.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCodOrganismoN1(String value) {
                this.codOrganismoN1 = value;
            }

            /**
             * Obtiene el valor de la propiedad codOrganismoN2.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCodOrganismoN2() {
                return codOrganismoN2;
            }

            /**
             * Define el valor de la propiedad codOrganismoN2.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCodOrganismoN2(String value) {
                this.codOrganismoN2 = value;
            }

        }

    }

}
