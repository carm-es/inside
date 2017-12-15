
package es.mpt.dsic.inside.xml.eni.firma;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import es.mpt.dsic.inside.xml.w3c.SignatureType;


/**
 * <p>Java class for TipoFirmasElectronicas complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TipoFirmasElectronicas">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TipoFirma" type="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/firma}tipoFirma"/>
 *         &lt;element name="ContenidoFirma">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice>
 *                   &lt;element name="CSV">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="ValorCSV" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="RegulacionGeneracionCSV" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="FirmaConCertificado">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;choice>
 *                             &lt;element name="FirmaBase64" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *                             &lt;element ref="{http://www.w3.org/2000/09/xmldsig#}Signature"/>
 *                             &lt;element name="ReferenciaFirma" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                           &lt;/choice>
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
 *       &lt;attribute name="Id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="ref" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoFirmasElectronicas", propOrder = {
    "tipoFirma",
    "contenidoFirma"
})
public class TipoFirmasElectronicas {

    @XmlElement(name = "TipoFirma", required = true)
    protected TipoFirma tipoFirma;
    @XmlElement(name = "ContenidoFirma", required = true)
    protected TipoFirmasElectronicas.ContenidoFirma contenidoFirma;
    @XmlAttribute(name = "Id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    @XmlAttribute(name = "ref")
    protected String ref;

    /**
     * Gets the value of the tipoFirma property.
     * 
     * @return
     *     possible object is
     *     {@link TipoFirma }
     *     
     */
    public TipoFirma getTipoFirma() {
        return tipoFirma;
    }

    /**
     * Sets the value of the tipoFirma property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoFirma }
     *     
     */
    public void setTipoFirma(TipoFirma value) {
        this.tipoFirma = value;
    }

    /**
     * Gets the value of the contenidoFirma property.
     * 
     * @return
     *     possible object is
     *     {@link TipoFirmasElectronicas.ContenidoFirma }
     *     
     */
    public TipoFirmasElectronicas.ContenidoFirma getContenidoFirma() {
        return contenidoFirma;
    }

    /**
     * Sets the value of the contenidoFirma property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoFirmasElectronicas.ContenidoFirma }
     *     
     */
    public void setContenidoFirma(TipoFirmasElectronicas.ContenidoFirma value) {
        this.contenidoFirma = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the ref property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRef() {
        return ref;
    }

    /**
     * Sets the value of the ref property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRef(String value) {
        this.ref = value;
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
     *         &lt;element name="CSV">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="ValorCSV" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="RegulacionGeneracionCSV" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="FirmaConCertificado">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;choice>
     *                   &lt;element name="FirmaBase64" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
     *                   &lt;element ref="{http://www.w3.org/2000/09/xmldsig#}Signature"/>
     *                   &lt;element name="ReferenciaFirma" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *                 &lt;/choice>
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
        "csv",
        "firmaConCertificado"
    })
    public static class ContenidoFirma {

        @XmlElement(name = "CSV")
        protected TipoFirmasElectronicas.ContenidoFirma.CSV csv;
        @XmlElement(name = "FirmaConCertificado")
        protected TipoFirmasElectronicas.ContenidoFirma.FirmaConCertificado firmaConCertificado;

        /**
         * Gets the value of the csv property.
         * 
         * @return
         *     possible object is
         *     {@link TipoFirmasElectronicas.ContenidoFirma.CSV }
         *     
         */
        public TipoFirmasElectronicas.ContenidoFirma.CSV getCSV() {
            return csv;
        }

        /**
         * Sets the value of the csv property.
         * 
         * @param value
         *     allowed object is
         *     {@link TipoFirmasElectronicas.ContenidoFirma.CSV }
         *     
         */
        public void setCSV(TipoFirmasElectronicas.ContenidoFirma.CSV value) {
            this.csv = value;
        }

        /**
         * Gets the value of the firmaConCertificado property.
         * 
         * @return
         *     possible object is
         *     {@link TipoFirmasElectronicas.ContenidoFirma.FirmaConCertificado }
         *     
         */
        public TipoFirmasElectronicas.ContenidoFirma.FirmaConCertificado getFirmaConCertificado() {
            return firmaConCertificado;
        }

        /**
         * Sets the value of the firmaConCertificado property.
         * 
         * @param value
         *     allowed object is
         *     {@link TipoFirmasElectronicas.ContenidoFirma.FirmaConCertificado }
         *     
         */
        public void setFirmaConCertificado(TipoFirmasElectronicas.ContenidoFirma.FirmaConCertificado value) {
            this.firmaConCertificado = value;
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
         *         &lt;element name="ValorCSV" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="RegulacionGeneracionCSV" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
            "valorCSV",
            "regulacionGeneracionCSV"
        })
        public static class CSV {

            @XmlElement(name = "ValorCSV", required = true)
            protected String valorCSV;
            @XmlElement(name = "RegulacionGeneracionCSV", required = true)
            protected String regulacionGeneracionCSV;

            /**
             * Gets the value of the valorCSV property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValorCSV() {
                return valorCSV;
            }

            /**
             * Sets the value of the valorCSV property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValorCSV(String value) {
                this.valorCSV = value;
            }

            /**
             * Gets the value of the regulacionGeneracionCSV property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRegulacionGeneracionCSV() {
                return regulacionGeneracionCSV;
            }

            /**
             * Sets the value of the regulacionGeneracionCSV property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRegulacionGeneracionCSV(String value) {
                this.regulacionGeneracionCSV = value;
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
         *       &lt;choice>
         *         &lt;element name="FirmaBase64" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
         *         &lt;element ref="{http://www.w3.org/2000/09/xmldsig#}Signature"/>
         *         &lt;element name="ReferenciaFirma" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
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
            "firmaBase64",
            "signature",
            "referenciaFirma"
        })
        public static class FirmaConCertificado {

            @XmlElement(name = "FirmaBase64")
            protected byte[] firmaBase64;
            @XmlElement(name = "Signature", namespace = "http://www.w3.org/2000/09/xmldsig#")
            protected SignatureType signature;
            @XmlElement(name = "ReferenciaFirma")
            protected Object referenciaFirma;

            /**
             * Gets the value of the firmaBase64 property.
             * 
             * @return
             *     possible object is
             *     byte[]
             */
            public byte[] getFirmaBase64() {
                return firmaBase64;
            }

            /**
             * Sets the value of the firmaBase64 property.
             * 
             * @param value
             *     allowed object is
             *     byte[]
             */
            public void setFirmaBase64(byte[] value) {
                this.firmaBase64 = ((byte[]) value);
            }

            /**
             * Gets the value of the signature property.
             * 
             * @return
             *     possible object is
             *     {@link SignatureType }
             *     
             */
            public SignatureType getSignature() {
                return signature;
            }

            /**
             * Sets the value of the signature property.
             * 
             * @param value
             *     allowed object is
             *     {@link SignatureType }
             *     
             */
            public void setSignature(SignatureType value) {
                this.signature = value;
            }

            /**
             * Gets the value of the referenciaFirma property.
             * 
             * @return
             *     possible object is
             *     {@link Object }
             *     
             */
            public Object getReferenciaFirma() {
                return referenciaFirma;
            }

            /**
             * Sets the value of the referenciaFirma property.
             * 
             * @param value
             *     allowed object is
             *     {@link Object }
             *     
             */
            public void setReferenciaFirma(Object value) {
                this.referenciaFirma = value;
            }

        }

    }

}
