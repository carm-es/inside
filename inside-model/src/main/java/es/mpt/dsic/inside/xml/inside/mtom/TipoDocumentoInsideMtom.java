
package es.mpt.dsic.inside.xml.inside.mtom;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import es.mpt.dsic.inside.xml.eni.documento.mtom.TipoDocumentoMtom;
import es.mpt.dsic.inside.xml.inside.TipoMetadatosAdicionales;
import es.mpt.dsic.inside.xml.inside.ws.documento.DocumentoInsideInfo;


/**
 * <p>Java class for TipoDocumentoInsideMtom complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TipoDocumentoInsideMtom">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="info" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/documentoInfo}DocumentoInsideInfo"/>
 *         &lt;element name="documentoENI" type="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/mtom}TipoDocumentoMtom"/>
 *         &lt;element name="metadatosAdicionales" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/metadatosAdicionales}TipoMetadatosAdicionales"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoDocumentoInsideMtom", propOrder = {
    "info",
    "documentoENI",
    "metadatosAdicionales"
})
public class TipoDocumentoInsideMtom {

    @XmlElement(required = true)
    protected DocumentoInsideInfo info;
    @XmlElement(required = true)
    protected TipoDocumentoMtom documentoENI;
    @XmlElement(required = true)
    protected TipoMetadatosAdicionales metadatosAdicionales;

    /**
     * Gets the value of the info property.
     * 
     * @return
     *     possible object is
     *     {@link DocumentoInsideInfo }
     *     
     */
    public DocumentoInsideInfo getInfo() {
        return info;
    }

    /**
     * Sets the value of the info property.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentoInsideInfo }
     *     
     */
    public void setInfo(DocumentoInsideInfo value) {
        this.info = value;
    }

    /**
     * Gets the value of the documentoENI property.
     * 
     * @return
     *     possible object is
     *     {@link TipoDocumentoMtom }
     *     
     */
    public TipoDocumentoMtom getDocumentoENI() {
        return documentoENI;
    }

    /**
     * Sets the value of the documentoENI property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoDocumentoMtom }
     *     
     */
    public void setDocumentoENI(TipoDocumentoMtom value) {
        this.documentoENI = value;
    }

    /**
     * Gets the value of the metadatosAdicionales property.
     * 
     * @return
     *     possible object is
     *     {@link TipoMetadatosAdicionales }
     *     
     */
    public TipoMetadatosAdicionales getMetadatosAdicionales() {
        return metadatosAdicionales;
    }

    /**
     * Sets the value of the metadatosAdicionales property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoMetadatosAdicionales }
     *     
     */
    public void setMetadatosAdicionales(TipoMetadatosAdicionales value) {
        this.metadatosAdicionales = value;
    }

}
