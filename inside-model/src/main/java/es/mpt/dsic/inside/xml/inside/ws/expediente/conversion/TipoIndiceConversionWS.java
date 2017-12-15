
package es.mpt.dsic.inside.xml.inside.ws.expediente.conversion;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for TipoIndiceConversionWS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TipoIndiceConversionWS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FechaIndiceElectronico" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;choice maxOccurs="unbounded">
 *           &lt;element name="DocumentoIndizado" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e/conversion}TipoDocumentoIndizadoConversionWS"/>
 *           &lt;element name="ExpedienteIndizado" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e/conversion}TipoIndiceConversionWS"/>
 *           &lt;element name="CarpetaIndizada" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e/conversion}TipoCarpetaIndizadaConversionWS"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoIndiceConversionWS", propOrder = {
    "fechaIndiceElectronico",
    "documentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada"
})
public class TipoIndiceConversionWS {

    @XmlElement(name = "FechaIndiceElectronico", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaIndiceElectronico;
    @XmlElements({
        @XmlElement(name = "ExpedienteIndizado", type = TipoIndiceConversionWS.class),
        @XmlElement(name = "CarpetaIndizada", type = TipoCarpetaIndizadaConversionWS.class),
        @XmlElement(name = "DocumentoIndizado", type = TipoDocumentoIndizadoConversionWS.class)
    })
    protected List<Object> documentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada;

    /**
     * Gets the value of the fechaIndiceElectronico property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaIndiceElectronico() {
        return fechaIndiceElectronico;
    }

    /**
     * Sets the value of the fechaIndiceElectronico property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaIndiceElectronico(XMLGregorianCalendar value) {
        this.fechaIndiceElectronico = value;
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
     * {@link TipoIndiceConversionWS }
     * {@link TipoCarpetaIndizadaConversionWS }
     * {@link TipoDocumentoIndizadoConversionWS }
     * 
     * 
     */
    public List<Object> getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada() {
        if (documentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada == null) {
            documentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada = new ArrayList<Object>();
        }
        return this.documentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada;
    }

}
