
package es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.mtom;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TipoOpcionesValidacionExpedienteInsideMtom complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TipoOpcionesValidacionExpedienteInsideMtom">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="opcionValidacionExpediente" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/validacion/expediente-e/mtom}TipoOpcionValidacionExpedienteMtom" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoOpcionesValidacionExpedienteInsideMtom", propOrder = {
    "opcionValidacionExpediente"
})
public class TipoOpcionesValidacionExpedienteInsideMtom {

    @XmlElement(required = true)
    protected List<TipoOpcionValidacionExpedienteMtom> opcionValidacionExpediente;

    /**
     * Gets the value of the opcionValidacionExpediente property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the opcionValidacionExpediente property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOpcionValidacionExpediente().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TipoOpcionValidacionExpedienteMtom }
     * 
     * 
     */
    public List<TipoOpcionValidacionExpedienteMtom> getOpcionValidacionExpediente() {
        if (opcionValidacionExpediente == null) {
            opcionValidacionExpediente = new ArrayList<TipoOpcionValidacionExpedienteMtom>();
        }
        return this.opcionValidacionExpediente;
    }

}
