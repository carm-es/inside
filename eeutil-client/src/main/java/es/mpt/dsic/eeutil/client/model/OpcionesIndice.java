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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for opcionesIndice complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="opcionesIndice">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tieneTitulo1Indice" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="tieneTitulo2Indice" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="titulo1Indice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="titulo2Indice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numFilasHoja" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "opcionesIndice", propOrder = {
    "tieneTitulo1Indice",
    "tieneTitulo2Indice",
    "titulo1Indice",
    "titulo2Indice",
    "numFilasHoja"
})
public class OpcionesIndice {

    @XmlElement(defaultValue = "true")
    protected Boolean tieneTitulo1Indice;
    @XmlElement(defaultValue = "true")
    protected Boolean tieneTitulo2Indice;
    protected String titulo1Indice;
    protected String titulo2Indice;
    protected Integer numFilasHoja;

    /**
     * Gets the value of the tieneTitulo1Indice property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTieneTitulo1Indice() {
        return tieneTitulo1Indice;
    }

    /**
     * Sets the value of the tieneTitulo1Indice property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTieneTitulo1Indice(Boolean value) {
        this.tieneTitulo1Indice = value;
    }

    /**
     * Gets the value of the tieneTitulo2Indice property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTieneTitulo2Indice() {
        return tieneTitulo2Indice;
    }

    /**
     * Sets the value of the tieneTitulo2Indice property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTieneTitulo2Indice(Boolean value) {
        this.tieneTitulo2Indice = value;
    }

    /**
     * Gets the value of the titulo1Indice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitulo1Indice() {
        return titulo1Indice;
    }

    /**
     * Sets the value of the titulo1Indice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitulo1Indice(String value) {
        this.titulo1Indice = value;
    }

    /**
     * Gets the value of the titulo2Indice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitulo2Indice() {
        return titulo2Indice;
    }

    /**
     * Sets the value of the titulo2Indice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitulo2Indice(String value) {
        this.titulo2Indice = value;
    }

    /**
     * Gets the value of the numFilasHoja property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumFilasHoja() {
        return numFilasHoja;
    }

    /**
     * Sets the value of the numFilasHoja property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumFilasHoja(Integer value) {
        this.numFilasHoja = value;
    }

}
