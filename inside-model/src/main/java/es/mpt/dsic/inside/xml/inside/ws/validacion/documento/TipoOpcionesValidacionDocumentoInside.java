
package es.mpt.dsic.inside.xml.inside.ws.validacion.documento;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TipoOpcionesValidacionDocumentoInside complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TipoOpcionesValidacionDocumentoInside">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="opcionValidacionDocumento" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/validacion/documento-e}TipoOpcionValidacionDocumento" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoOpcionesValidacionDocumentoInside", propOrder = {
    "opcionValidacionDocumento"
})
public class TipoOpcionesValidacionDocumentoInside {

    @XmlElement(required = true)
    protected List<TipoOpcionValidacionDocumento> opcionValidacionDocumento;

    /**
     * Gets the value of the opcionValidacionDocumento property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the opcionValidacionDocumento property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOpcionValidacionDocumento().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TipoOpcionValidacionDocumento }
     * 
     * 
     */
    public List<TipoOpcionValidacionDocumento> getOpcionValidacionDocumento() {
        if (opcionValidacionDocumento == null) {
            opcionValidacionDocumento = new ArrayList<TipoOpcionValidacionDocumento>();
        }
        return this.opcionValidacionDocumento;
    }

}
