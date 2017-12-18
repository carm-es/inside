
package es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.mtom;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlType;
import es.mpt.dsic.inside.xml.eni.documento.mtom.TipoDocumentoMtom;


/**
 * <p>Java class for TipoDocumentoEniBinarioOTipoMtom complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TipoDocumentoEniBinarioOTipoMtom">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="documentoEniBinario" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="documentoEniTipo" type="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/mtom}TipoDocumentoMtom"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoDocumentoEniBinarioOTipoMtom", propOrder = {
    "documentoEniBinario",
    "documentoEniTipo"
})
public class TipoDocumentoEniBinarioOTipoMtom {

    @XmlMimeType("application/octet-stream")
    protected DataHandler documentoEniBinario;
    protected TipoDocumentoMtom documentoEniTipo;

    /**
     * Gets the value of the documentoEniBinario property.
     * 
     * @return
     *     possible object is
     *     {@link DataHandler }
     *     
     */
    public DataHandler getDocumentoEniBinario() {
        return documentoEniBinario;
    }

    /**
     * Sets the value of the documentoEniBinario property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataHandler }
     *     
     */
    public void setDocumentoEniBinario(DataHandler value) {
        this.documentoEniBinario = value;
    }

    /**
     * Gets the value of the documentoEniTipo property.
     * 
     * @return
     *     possible object is
     *     {@link TipoDocumentoMtom }
     *     
     */
    public TipoDocumentoMtom getDocumentoEniTipo() {
        return documentoEniTipo;
    }

    /**
     * Sets the value of the documentoEniTipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoDocumentoMtom }
     *     
     */
    public void setDocumentoEniTipo(TipoDocumentoMtom value) {
        this.documentoEniTipo = value;
    }

}
