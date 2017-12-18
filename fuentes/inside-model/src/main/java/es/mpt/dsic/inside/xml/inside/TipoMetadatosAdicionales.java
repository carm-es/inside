
package es.mpt.dsic.inside.xml.inside;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TipoMetadatosAdicionales complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TipoMetadatosAdicionales">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence maxOccurs="unbounded">
 *         &lt;element name="MetadatoAdicional" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/metadatosAdicionales}MetadatoAdicional"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoMetadatosAdicionales", namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/metadatosAdicionales", propOrder = {
    "metadatoAdicional"
})
public class TipoMetadatosAdicionales {

    @XmlElement(name = "MetadatoAdicional", required = true)
    protected List<MetadatoAdicional> metadatoAdicional;

    /**
     * Gets the value of the metadatoAdicional property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the metadatoAdicional property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMetadatoAdicional().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MetadatoAdicional }
     * 
     * 
     */
    public List<MetadatoAdicional> getMetadatoAdicional() {
        if (metadatoAdicional == null) {
            metadatoAdicional = new ArrayList<MetadatoAdicional>();
        }
        return this.metadatoAdicional;
    }

}
