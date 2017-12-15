
package es.mpt.dsic.inside.xml.inside.mtom;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import es.mpt.dsic.inside.xml.eni.documento.mtom.TipoDocumentoMtom;
import es.mpt.dsic.inside.xml.inside.TipoMetadatosAdicionales;


/**
 * <p>Java class for TipoDocumentoInsideConMAdicionalesMtom complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TipoDocumentoInsideConMAdicionalesMtom">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/mtom}documentoMtom"/>
 *         &lt;element name="metadatosAdicionales" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/metadatosAdicionales}TipoMetadatosAdicionales" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoDocumentoInsideConMAdicionalesMtom", propOrder = {
    "documentoMtom",
    "metadatosAdicionales"
})
public class TipoDocumentoInsideConMAdicionalesMtom {

    @XmlElement(namespace = "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/mtom", required = true)
    protected TipoDocumentoMtom documentoMtom;
    protected TipoMetadatosAdicionales metadatosAdicionales;

    /**
     * Gets the value of the documentoMtom property.
     * 
     * @return
     *     possible object is
     *     {@link TipoDocumentoMtom }
     *     
     */
    public TipoDocumentoMtom getDocumentoMtom() {
        return documentoMtom;
    }

    /**
     * Sets the value of the documentoMtom property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoDocumentoMtom }
     *     
     */
    public void setDocumentoMtom(TipoDocumentoMtom value) {
        this.documentoMtom = value;
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
