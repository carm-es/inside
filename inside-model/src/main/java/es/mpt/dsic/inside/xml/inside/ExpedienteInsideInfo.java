
package es.mpt.dsic.inside.xml.inside;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import es.mpt.dsic.inside.xml.eni.expediente.metadatos.EnumeracionEstados;


/**
 * <p>Java class for ExpedienteInsideInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExpedienteInsideInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Identificador" type="{http://www.w3.org/2001/XMLSchema}ID"/>
 *         &lt;element name="Estado" type="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/metadatos}enumeracionEstados"/>
 *         &lt;element name="versionActual" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/version}TipoVersionInside"/>
 *         &lt;element name="infoExpediente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExpedienteInsideInfo", namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e/expedienteInfo", propOrder = {
    "identificador",
    "estado",
    "versionActual",
    "infoExpediente"
})
public class ExpedienteInsideInfo {

    @XmlElement(name = "Identificador", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String identificador;
    @XmlElement(name = "Estado", required = true)
    protected EnumeracionEstados estado;
    @XmlElement(required = true)
    protected TipoVersionInside versionActual;
    @XmlElement(required = true)
    protected String infoExpediente;

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
     * Gets the value of the estado property.
     * 
     * @return
     *     possible object is
     *     {@link EnumeracionEstados }
     *     
     */
    public EnumeracionEstados getEstado() {
        return estado;
    }

    /**
     * Sets the value of the estado property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumeracionEstados }
     *     
     */
    public void setEstado(EnumeracionEstados value) {
        this.estado = value;
    }

    /**
     * Gets the value of the versionActual property.
     * 
     * @return
     *     possible object is
     *     {@link TipoVersionInside }
     *     
     */
    public TipoVersionInside getVersionActual() {
        return versionActual;
    }

    /**
     * Sets the value of the versionActual property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoVersionInside }
     *     
     */
    public void setVersionActual(TipoVersionInside value) {
        this.versionActual = value;
    }

    /**
     * Gets the value of the infoExpediente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInfoExpediente() {
        return infoExpediente;
    }

    /**
     * Sets the value of the infoExpediente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInfoExpediente(String value) {
        this.infoExpediente = value;
    }

}
