
package es.mpt.dsic.inside.xml.inside.ws.busqueda;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for metadatoBusquedaWsInside complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
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
@XmlType(name = "metadatoBusquedaWsInside", propOrder = {
    "nombre",
    "valor"
})
public class MetadatoBusquedaWsInside {

    @XmlElement(required = true)
    protected String nombre;
    @XmlElement(required = true)
    protected MetadatoBusquedaWsInside.Valor valor;

    /**
     * Gets the value of the nombre property.
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
     * Sets the value of the nombre property.
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
     * Gets the value of the valor property.
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
     * Sets the value of the valor property.
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
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
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

        protected String equal;
        protected String like;
        protected BigDecimal minusThan;
        protected BigDecimal moreThan;
        protected MetadatoBusquedaWsInside.Valor.Between between;
        protected MetadatoBusquedaWsInside.Valor.DateRange dateRange;

        /**
         * Gets the value of the equal property.
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
         * Sets the value of the equal property.
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
         * Gets the value of the like property.
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
         * Sets the value of the like property.
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
         * Gets the value of the minusThan property.
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
         * Sets the value of the minusThan property.
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
         * Gets the value of the moreThan property.
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
         * Sets the value of the moreThan property.
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
         * Gets the value of the between property.
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
         * Sets the value of the between property.
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
         * Gets the value of the dateRange property.
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
         * Sets the value of the dateRange property.
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
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
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

            @XmlElement(required = true)
            protected BigDecimal infRange;
            @XmlElement(required = true)
            protected BigDecimal supRange;

            /**
             * Gets the value of the infRange property.
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
             * Sets the value of the infRange property.
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
             * Gets the value of the supRange property.
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
             * Sets the value of the supRange property.
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
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
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

            @XmlElement(required = true)
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar from;
            @XmlElement(required = true)
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar to;

            /**
             * Gets the value of the from property.
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
             * Sets the value of the from property.
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
             * Gets the value of the to property.
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
             * Sets the value of the to property.
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
