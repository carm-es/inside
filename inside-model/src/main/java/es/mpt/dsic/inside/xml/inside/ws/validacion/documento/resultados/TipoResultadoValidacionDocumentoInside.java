
package es.mpt.dsic.inside.xml.inside.ws.validacion.documento.resultados;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TipoResultadoValidacionDocumentoInside complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TipoResultadoValidacionDocumentoInside">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="validacionDetalle" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/validacion/documento-e/resultados}TipoResultadoValidacionDetalleDocumentoInside" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoResultadoValidacionDocumentoInside", propOrder = {
    "validacionDetalle"
})
public class TipoResultadoValidacionDocumentoInside {

    @XmlElement(required = true)
    protected List<TipoResultadoValidacionDetalleDocumentoInside> validacionDetalle;

    /**
     * Gets the value of the validacionDetalle property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the validacionDetalle property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValidacionDetalle().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TipoResultadoValidacionDetalleDocumentoInside }
     * 
     * 
     */
    public List<TipoResultadoValidacionDetalleDocumentoInside> getValidacionDetalle() {
        if (validacionDetalle == null) {
            validacionDetalle = new ArrayList<TipoResultadoValidacionDetalleDocumentoInside>();
        }
        return this.validacionDetalle;
    }

}
