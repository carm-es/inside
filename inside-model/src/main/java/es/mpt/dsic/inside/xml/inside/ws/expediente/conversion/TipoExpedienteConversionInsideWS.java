
package es.mpt.dsic.inside.xml.inside.ws.expediente.conversion;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.datatype.XMLGregorianCalendar;
import es.mpt.dsic.inside.xml.eni.expediente.metadatos.EnumeracionEstados;


/**
 * <p>Java class for TipoExpedienteConversionInsideWS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TipoExpedienteConversionInsideWS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="metadatosEni">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="VersionNTI" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Identificador" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Organo" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *                   &lt;element name="FechaAperturaExpediente" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                   &lt;element name="Clasificacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Estado">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/metadatos>enumeracionEstados">
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="Interesado" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Indice" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e/conversion}TipoIndiceConversionWS"/>
 *         &lt;element name="OpcionesVisualizacion" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e/conversion}TipoOpcionesVisualizacionIndiceWS"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoExpedienteConversionInsideWS", propOrder = {
    "metadatosEni",
    "indice",
    "opcionesVisualizacion"
})
public class TipoExpedienteConversionInsideWS {

    @XmlElement(required = true)
    protected TipoExpedienteConversionInsideWS.MetadatosEni metadatosEni;
    @XmlElement(name = "Indice", required = true)
    protected TipoIndiceConversionWS indice;
    @XmlElement(name = "OpcionesVisualizacion", required = true)
    protected TipoOpcionesVisualizacionIndiceWS opcionesVisualizacion;

    /**
     * Gets the value of the metadatosEni property.
     * 
     * @return
     *     possible object is
     *     {@link TipoExpedienteConversionInsideWS.MetadatosEni }
     *     
     */
    public TipoExpedienteConversionInsideWS.MetadatosEni getMetadatosEni() {
        return metadatosEni;
    }

    /**
     * Sets the value of the metadatosEni property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoExpedienteConversionInsideWS.MetadatosEni }
     *     
     */
    public void setMetadatosEni(TipoExpedienteConversionInsideWS.MetadatosEni value) {
        this.metadatosEni = value;
    }

    /**
     * Gets the value of the indice property.
     * 
     * @return
     *     possible object is
     *     {@link TipoIndiceConversionWS }
     *     
     */
    public TipoIndiceConversionWS getIndice() {
        return indice;
    }

    /**
     * Sets the value of the indice property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoIndiceConversionWS }
     *     
     */
    public void setIndice(TipoIndiceConversionWS value) {
        this.indice = value;
    }

    /**
     * Gets the value of the opcionesVisualizacion property.
     * 
     * @return
     *     possible object is
     *     {@link TipoOpcionesVisualizacionIndiceWS }
     *     
     */
    public TipoOpcionesVisualizacionIndiceWS getOpcionesVisualizacion() {
        return opcionesVisualizacion;
    }

    /**
     * Sets the value of the opcionesVisualizacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoOpcionesVisualizacionIndiceWS }
     *     
     */
    public void setOpcionesVisualizacion(TipoOpcionesVisualizacionIndiceWS value) {
        this.opcionesVisualizacion = value;
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
     *         &lt;element name="VersionNTI" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Identificador" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Organo" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
     *         &lt;element name="FechaAperturaExpediente" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *         &lt;element name="Clasificacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Estado">
     *           &lt;complexType>
     *             &lt;simpleContent>
     *               &lt;extension base="&lt;http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/metadatos>enumeracionEstados">
     *               &lt;/extension>
     *             &lt;/simpleContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="Interesado" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
        "versionNTI",
        "identificador",
        "organo",
        "fechaAperturaExpediente",
        "clasificacion",
        "estado",
        "interesado"
    })
    public static class MetadatosEni {

        @XmlElement(name = "VersionNTI", required = true)
        protected String versionNTI;
        @XmlElement(name = "Identificador", required = true)
        protected String identificador;
        @XmlElement(name = "Organo", required = true)
        protected List<String> organo;
        @XmlElement(name = "FechaAperturaExpediente", required = true)
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar fechaAperturaExpediente;
        @XmlElement(name = "Clasificacion", required = true)
        protected String clasificacion;
        @XmlElement(name = "Estado", required = true)
        protected TipoExpedienteConversionInsideWS.MetadatosEni.Estado estado;
        @XmlElement(name = "Interesado")
        protected List<String> interesado;

        /**
         * Gets the value of the versionNTI property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVersionNTI() {
            return versionNTI;
        }

        /**
         * Sets the value of the versionNTI property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVersionNTI(String value) {
            this.versionNTI = value;
        }

        /**
         * Gets the value of the identificador property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIdentificador() {
            return identificador;
        }

        /**
         * Sets the value of the identificador property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIdentificador(String value) {
            this.identificador = value;
        }

        /**
         * Gets the value of the organo property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the organo property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getOrgano().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getOrgano() {
            if (organo == null) {
                organo = new ArrayList<String>();
            }
            return this.organo;
        }

        /**
         * Gets the value of the fechaAperturaExpediente property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getFechaAperturaExpediente() {
            return fechaAperturaExpediente;
        }

        /**
         * Sets the value of the fechaAperturaExpediente property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setFechaAperturaExpediente(XMLGregorianCalendar value) {
            this.fechaAperturaExpediente = value;
        }

        /**
         * Gets the value of the clasificacion property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getClasificacion() {
            return clasificacion;
        }

        /**
         * Sets the value of the clasificacion property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setClasificacion(String value) {
            this.clasificacion = value;
        }

        /**
         * Gets the value of the estado property.
         * 
         * @return
         *     possible object is
         *     {@link TipoExpedienteConversionInsideWS.MetadatosEni.Estado }
         *     
         */
        public TipoExpedienteConversionInsideWS.MetadatosEni.Estado getEstado() {
            return estado;
        }

        /**
         * Sets the value of the estado property.
         * 
         * @param value
         *     allowed object is
         *     {@link TipoExpedienteConversionInsideWS.MetadatosEni.Estado }
         *     
         */
        public void setEstado(TipoExpedienteConversionInsideWS.MetadatosEni.Estado value) {
            this.estado = value;
        }

        /**
         * Gets the value of the interesado property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the interesado property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getInteresado().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getInteresado() {
            if (interesado == null) {
                interesado = new ArrayList<String>();
            }
            return this.interesado;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;simpleContent>
         *     &lt;extension base="&lt;http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/metadatos>enumeracionEstados">
         *     &lt;/extension>
         *   &lt;/simpleContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class Estado {

            @XmlValue
            protected EnumeracionEstados value;

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link EnumeracionEstados }
             *     
             */
            public EnumeracionEstados getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link EnumeracionEstados }
             *     
             */
            public void setValue(EnumeracionEstados value) {
                this.value = value;
            }

        }

    }

}
