
package es.mpt.dsic.inside.xml.eni.firma;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for firmas complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="firmas">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="firma" type="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/firma}TipoFirmasElectronicas" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "firmas", propOrder = {
    "firma"
})
public class Firmas {

    @XmlElement(required = true)
    protected List<TipoFirmasElectronicas> firma;

    /**
     * Gets the value of the firma property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the firma property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFirma().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TipoFirmasElectronicas }
     * 
     * 
     */
    public List<TipoFirmasElectronicas> getFirma() {
        if (firma == null) {
            firma = new ArrayList<TipoFirmasElectronicas>();
        }
        return this.firma;
    }

}
