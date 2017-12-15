
package es.mpt.dsic.inside.xml.inside.ws.filter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FilterPageRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FilterPageRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="filtro" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pagina" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="limite" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FilterPageRequest", propOrder = {
    "filtro",
    "pagina",
    "limite"
})
public class FilterPageRequest {

    @XmlElement(required = true)
    protected String filtro;
    protected int pagina;
    protected int limite;

    /**
     * Gets the value of the filtro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFiltro() {
        return filtro;
    }

    /**
     * Sets the value of the filtro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFiltro(String value) {
        this.filtro = value;
    }

    /**
     * Gets the value of the pagina property.
     * 
     */
    public int getPagina() {
        return pagina;
    }

    /**
     * Sets the value of the pagina property.
     * 
     */
    public void setPagina(int value) {
        this.pagina = value;
    }

    /**
     * Gets the value of the limite property.
     * 
     */
    public int getLimite() {
        return limite;
    }

    /**
     * Sets the value of the limite property.
     * 
     */
    public void setLimite(int value) {
        this.limite = value;
    }

}
