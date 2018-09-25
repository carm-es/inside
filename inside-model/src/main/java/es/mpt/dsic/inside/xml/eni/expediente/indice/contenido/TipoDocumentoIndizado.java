
package es.mpt.dsic.inside.xml.eni.expediente.indice.contenido;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for TipoDocumentoIndizado complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TipoDocumentoIndizado">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdentificadorDocumento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ValorHuella" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FuncionResumen" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FechaIncorporacionExpediente" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="OrdenDocumentoExpediente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoDocumentoIndizado", propOrder = {
    "identificadorDocumento",
    "valorHuella",
    "funcionResumen",
    "fechaIncorporacionExpediente",
    "ordenDocumentoExpediente"
})
public class TipoDocumentoIndizado {

    @XmlElement(name = "IdentificadorDocumento", required = true)
    protected String identificadorDocumento;
    @XmlElement(name = "ValorHuella", required = true)
    protected String valorHuella;
    @XmlElement(name = "FuncionResumen", required = true)
    protected String funcionResumen;
    @XmlElement(name = "FechaIncorporacionExpediente")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaIncorporacionExpediente;
    @XmlElement(name = "OrdenDocumentoExpediente")
    protected String ordenDocumentoExpediente;
    @XmlAttribute(name = "Id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Gets the value of the identificadorDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificadorDocumento() {
        return identificadorDocumento;
    }

    /**
     * Sets the value of the identificadorDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificadorDocumento(String value) {
        this.identificadorDocumento = value;
    }

    /**
     * Gets the value of the valorHuella property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValorHuella() {
        return valorHuella;
    }

    /**
     * Sets the value of the valorHuella property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValorHuella(String value) {
        this.valorHuella = value;
    }

    /**
     * Gets the value of the funcionResumen property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFuncionResumen() {
        return funcionResumen;
    }

    /**
     * Sets the value of the funcionResumen property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFuncionResumen(String value) {
        this.funcionResumen = value;
    }

    /**
     * Gets the value of the fechaIncorporacionExpediente property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaIncorporacionExpediente() {
        return fechaIncorporacionExpediente;
    }

    /**
     * Sets the value of the fechaIncorporacionExpediente property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaIncorporacionExpediente(XMLGregorianCalendar value) {
        this.fechaIncorporacionExpediente = value;
    }

    /**
     * Gets the value of the ordenDocumentoExpediente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrdenDocumentoExpediente() {
        return ordenDocumentoExpediente;
    }

    /**
     * Sets the value of the ordenDocumentoExpediente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrdenDocumentoExpediente(String value) {
        this.ordenDocumentoExpediente = value;
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

}
