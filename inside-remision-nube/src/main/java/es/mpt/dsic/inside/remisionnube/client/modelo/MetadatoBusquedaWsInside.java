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


package es.mpt.dsic.inside.remisionnube.client.modelo;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para metadatoBusquedaWsInside complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="metadatoBusquedaWsInside">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="valor">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice>
 *                   &lt;element name="equal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="like" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="minusThan" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                   &lt;element name="moreThan" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                   &lt;element name="between">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="infRange" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                             &lt;element name="supRange" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="dateRange">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="from" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                             &lt;element name="to" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/choice>
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
@XmlType(name = "metadatoBusquedaWsInside", namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/busqueda", propOrder = {
    "nombre",
    "valor"
})
public class MetadatoBusquedaWsInside {

    @XmlElement(required = true)
    protected String nombre;
    @XmlElement(required = true)
    protected MetadatoBusquedaWsInside.Valor valor;

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
     * Obtiene el valor de la propiedad valor.
     * 
     * @return
     *     possible object is
     *     {@link MetadatoBusquedaWsInside.Valor }
     *     
     */
    public MetadatoBusquedaWsInside.Valor getValor() {
        return valor;
    }

    /**
     * Define el valor de la propiedad valor.
     * 
     * @param value
     *     allowed object is
     *     {@link MetadatoBusquedaWsInside.Valor }
     *     
     */
    public void setValor(MetadatoBusquedaWsInside.Valor value) {
        this.valor = value;
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
     *       &lt;choice>
     *         &lt;element name="equal" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="like" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="minusThan" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *         &lt;element name="moreThan" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *         &lt;element name="between">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="infRange" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *                   &lt;element name="supRange" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="dateRange">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="from" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *                   &lt;element name="to" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/choice>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "equal",
        "like",
        "minusThan",
        "moreThan",
        "between",
        "dateRange"
    })
    public static class Valor {

        @XmlElement(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/busqueda")
        protected String equal;
        @XmlElement(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/busqueda")
        protected String like;
        @XmlElement(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/busqueda")
        protected BigDecimal minusThan;
        @XmlElement(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/busqueda")
        protected BigDecimal moreThan;
        @XmlElement(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/busqueda")
        protected MetadatoBusquedaWsInside.Valor.Between between;
        @XmlElement(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/busqueda")
        protected MetadatoBusquedaWsInside.Valor.DateRange dateRange;

        /**
         * Obtiene el valor de la propiedad equal.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEqual() {
            return equal;
        }

        /**
         * Define el valor de la propiedad equal.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEqual(String value) {
            this.equal = value;
        }

        /**
         * Obtiene el valor de la propiedad like.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLike() {
            return like;
        }

        /**
         * Define el valor de la propiedad like.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLike(String value) {
            this.like = value;
        }

        /**
         * Obtiene el valor de la propiedad minusThan.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getMinusThan() {
            return minusThan;
        }

        /**
         * Define el valor de la propiedad minusThan.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setMinusThan(BigDecimal value) {
            this.minusThan = value;
        }

        /**
         * Obtiene el valor de la propiedad moreThan.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getMoreThan() {
            return moreThan;
        }

        /**
         * Define el valor de la propiedad moreThan.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setMoreThan(BigDecimal value) {
            this.moreThan = value;
        }

        /**
         * Obtiene el valor de la propiedad between.
         * 
         * @return
         *     possible object is
         *     {@link MetadatoBusquedaWsInside.Valor.Between }
         *     
         */
        public MetadatoBusquedaWsInside.Valor.Between getBetween() {
            return between;
        }

        /**
         * Define el valor de la propiedad between.
         * 
         * @param value
         *     allowed object is
         *     {@link MetadatoBusquedaWsInside.Valor.Between }
         *     
         */
        public void setBetween(MetadatoBusquedaWsInside.Valor.Between value) {
            this.between = value;
        }

        /**
         * Obtiene el valor de la propiedad dateRange.
         * 
         * @return
         *     possible object is
         *     {@link MetadatoBusquedaWsInside.Valor.DateRange }
         *     
         */
        public MetadatoBusquedaWsInside.Valor.DateRange getDateRange() {
            return dateRange;
        }

        /**
         * Define el valor de la propiedad dateRange.
         * 
         * @param value
         *     allowed object is
         *     {@link MetadatoBusquedaWsInside.Valor.DateRange }
         *     
         */
        public void setDateRange(MetadatoBusquedaWsInside.Valor.DateRange value) {
            this.dateRange = value;
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
         *         &lt;element name="infRange" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
         *         &lt;element name="supRange" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
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
            "infRange",
            "supRange"
        })
        public static class Between {

            @XmlElement(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/busqueda", required = true)
            protected BigDecimal infRange;
            @XmlElement(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/busqueda", required = true)
            protected BigDecimal supRange;

            /**
             * Obtiene el valor de la propiedad infRange.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getInfRange() {
                return infRange;
            }

            /**
             * Define el valor de la propiedad infRange.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setInfRange(BigDecimal value) {
                this.infRange = value;
            }

            /**
             * Obtiene el valor de la propiedad supRange.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getSupRange() {
                return supRange;
            }

            /**
             * Define el valor de la propiedad supRange.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setSupRange(BigDecimal value) {
                this.supRange = value;
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
         *         &lt;element name="from" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
         *         &lt;element name="to" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
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
            "from",
            "to"
        })
        public static class DateRange {

            @XmlElement(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/busqueda", required = true)
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar from;
            @XmlElement(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/busqueda", required = true)
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar to;

            /**
             * Obtiene el valor de la propiedad from.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getFrom() {
                return from;
            }

            /**
             * Define el valor de la propiedad from.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setFrom(XMLGregorianCalendar value) {
                this.from = value;
            }

            /**
             * Obtiene el valor de la propiedad to.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getTo() {
                return to;
            }

            /**
             * Define el valor de la propiedad to.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setTo(XMLGregorianCalendar value) {
                this.to = value;
            }

        }

    }

}
