/* Copyright (C) 2016 MINHAP, Gobierno de España
   This program is licensed and may be used, modified and redistributed under the terms
   of the European Public License (EUPL), either version 1.1 or (at your
   option) any later version as soon as they are approved by the European Commission.
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
   or implied. See the License for the specific language governing permissions and
   more details.
   You should have received a copy of the EUPL1.1 license
   along with this program; if not, you may find it at
   http://joinup.ec.europa.eu/software/page/eupl/licence-eupl */


package es.mpt.dsic.eeutil.client.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for item complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="item">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}ID"/>
 *         &lt;element name="padre" type="{http://www.w3.org/2001/XMLSchema}IDREF" minOccurs="0"/>
 *         &lt;element name="hijos" type="{http://service.ws.inside.dsic.mpt.es/}ListaItem" minOccurs="0"/>
 *         &lt;element name="documentoContenido" type="{http://service.ws.inside.dsic.mpt.es/}documentoContenido" minOccurs="0"/>
 *         &lt;element name="propiedadesItem" type="{http://service.ws.inside.dsic.mpt.es/}ListaPropiedades" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "item", propOrder = {
	"identificador",
    "nombre",
    "padre",
    "hijos",
    "documentoContenido",
    "propiedadesItem"
})
public class Item {

	 @XmlElement(required = true)
	 @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	 @XmlID
	 @XmlSchemaType(name = "ID")
	 protected String identificador;
	 
    @XmlElement(required = true)
    protected String nombre;
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object padre;
    protected ListaItem hijos;
    protected DocumentoContenido documentoContenido;
    protected ListaPropiedades propiedadesItem;

    /**
     * Gets the value of the identificador property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * Sets the value of the identificador property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificador(String value) {
        this.identificador = value;
    }
    
    /**
     * Gets the value of the nombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the value of the nombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Gets the value of the padre property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getPadre() {
        return padre;
    }

    /**
     * Sets the value of the padre property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setPadre(Object value) {
        this.padre = value;
    }

    /**
     * Gets the value of the hijos property.
     * 
     * @return
     *     possible object is
     *     {@link ListaItem }
     *     
     */
    public ListaItem getHijos() {
    	if (hijos == null) {
    		hijos = new ListaItem();
    	}
        return hijos;
    }

    /**
     * Sets the value of the hijos property.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaItem }
     *     
     */
    public void setHijos(ListaItem value) {
        this.hijos = value;
    }

    /**
     * Gets the value of the documentoContenido property.
     * 
     * @return
     *     possible object is
     *     {@link DocumentoContenido }
     *     
     */
    public DocumentoContenido getDocumentoContenido() {
        return documentoContenido;
    }

    /**
     * Sets the value of the documentoContenido property.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentoContenido }
     *     
     */
    public void setDocumentoContenido(DocumentoContenido value) {
        this.documentoContenido = value;
    }

    /**
     * Gets the value of the propiedadesItem property.
     * 
     * @return
     *     possible object is
     *     {@link ListaPropiedades }
     *     
     */
    public ListaPropiedades getPropiedadesItem() {
        return propiedadesItem;
    }

    /**
     * Sets the value of the propiedadesItem property.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaPropiedades }
     *     
     */
    public void setPropiedadesItem(ListaPropiedades value) {
        this.propiedadesItem = value;
    }

}
