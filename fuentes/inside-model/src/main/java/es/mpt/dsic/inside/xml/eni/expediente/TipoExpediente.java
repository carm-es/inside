
package es.mpt.dsic.inside.xml.eni.expediente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import es.mpt.dsic.inside.xml.eni.documento.contenido.TipoContenido;
import es.mpt.dsic.inside.xml.eni.expediente.indice.TipoIndice;
import es.mpt.dsic.inside.xml.eni.expediente.metadatos.TipoMetadatos;


/**
 * Para el intercambio de un expediente electrónico, se envía en primer lugar, el índice del expediente. Posteriormente, se enviarán los documentos que lo componen , uno a uno,  y siguiendo la distribución reflejada en el contenido del índice.
 * 
 * <p>Java class for TipoExpediente complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TipoExpediente">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/indice-e}indice"/>
 *         &lt;element ref="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/metadatos}metadatosExp"/>
 *         &lt;element name="VisualizacionIndice" type="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/contenido}TipoContenido" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoExpediente", propOrder = {
    "indice",
    "metadatosExp",
    "visualizacionIndice"
})
public class TipoExpediente {

    @XmlElement(namespace = "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/indice-e", required = true)
    protected TipoIndice indice;
    @XmlElement(namespace = "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/metadatos", required = true)
    protected TipoMetadatos metadatosExp;
    @XmlElement(name = "VisualizacionIndice")
    protected TipoContenido visualizacionIndice;
    @XmlAttribute(name = "Id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Gets the value of the indice property.
     * 
     * @return
     *     possible object is
     *     {@link TipoIndice }
     *     
     */
    public TipoIndice getIndice() {
        return indice;
    }

    /**
     * Sets the value of the indice property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoIndice }
     *     
     */
    public void setIndice(TipoIndice value) {
        this.indice = value;
    }

    /**
     * Gets the value of the metadatosExp property.
     * 
     * @return
     *     possible object is
     *     {@link TipoMetadatos }
     *     
     */
    public TipoMetadatos getMetadatosExp() {
        return metadatosExp;
    }

    /**
     * Sets the value of the metadatosExp property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoMetadatos }
     *     
     */
    public void setMetadatosExp(TipoMetadatos value) {
        this.metadatosExp = value;
    }

    /**
     * Gets the value of the visualizacionIndice property.
     * 
     * @return
     *     possible object is
     *     {@link TipoContenido }
     *     
     */
    public TipoContenido getVisualizacionIndice() {
        return visualizacionIndice;
    }

    /**
     * Sets the value of the visualizacionIndice property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoContenido }
     *     
     */
    public void setVisualizacionIndice(TipoContenido value) {
        this.visualizacionIndice = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}
