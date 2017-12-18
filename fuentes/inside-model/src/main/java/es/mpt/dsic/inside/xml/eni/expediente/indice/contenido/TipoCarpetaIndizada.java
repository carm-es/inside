
package es.mpt.dsic.inside.xml.eni.expediente.indice.contenido;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for TipoCarpetaIndizada complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TipoCarpetaIndizada">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdentificadorCarpeta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;choice maxOccurs="unbounded">
 *           &lt;element name="DocumentoIndizado" type="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/indice-e/contenido}TipoDocumentoIndizado"/>
 *           &lt;element name="ExpedienteIndizado" type="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/indice-e/contenido}TipoIndiceContenido"/>
 *           &lt;element name="CarpetaIndizada" type="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/indice-e/contenido}TipoCarpetaIndizada"/>
 *         &lt;/choice>
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
@XmlType(name = "TipoCarpetaIndizada", propOrder = {
    "identificadorCarpeta",
    "documentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada"
})
public class TipoCarpetaIndizada {

    @XmlElement(name = "IdentificadorCarpeta", required = true)
    protected String identificadorCarpeta;
    @XmlElements({
        @XmlElement(name = "ExpedienteIndizado", type = TipoIndiceContenido.class),
        @XmlElement(name = "CarpetaIndizada", type = TipoCarpetaIndizada.class),
        @XmlElement(name = "DocumentoIndizado", type = TipoDocumentoIndizado.class)
    })
    protected List<Object> documentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada;
    @XmlAttribute(name = "Id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Gets the value of the identificadorCarpeta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificadorCarpeta() {
        return identificadorCarpeta;
    }

    /**
     * Sets the value of the identificadorCarpeta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificadorCarpeta(String value) {
        this.identificadorCarpeta = value;
    }

    /**
     * Gets the value of the documentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the documentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TipoIndiceContenido }
     * {@link TipoCarpetaIndizada }
     * {@link TipoDocumentoIndizado }
     * 
     * 
     */
    public List<Object> getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada() {
        if (documentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada == null) {
            documentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada = new ArrayList<Object>();
        }
        return this.documentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada;
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
