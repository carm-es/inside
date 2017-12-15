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


package es.mpt.dsic.inside.service.sia.model.messages.v2_4.enviasia;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para DESTINATARIOS complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DESTINATARIOS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DESTINATARIO" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="CODDESTINATARIO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="DESDESTINATARIO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DESTINATARIOS", propOrder = {
    "destinatario"
})
public class DESTINATARIOS {

    @XmlElement(name = "DESTINATARIO")
    protected List<DESTINATARIOS.DESTINATARIO> destinatario;

    /**
     * Gets the value of the destinatario property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the destinatario property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDESTINATARIO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DESTINATARIOS.DESTINATARIO }
     * 
     * 
     */
    public List<DESTINATARIOS.DESTINATARIO> getDESTINATARIO() {
        if (destinatario == null) {
            destinatario = new ArrayList<DESTINATARIOS.DESTINATARIO>();
        }
        return this.destinatario;
    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="CODDESTINATARIO" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="DESDESTINATARIO" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "coddestinatario",
        "desdestinatario"
    })
    public static class DESTINATARIO {

        @XmlElement(name = "CODDESTINATARIO", required = true)
        protected String coddestinatario;
        @XmlElement(name = "DESDESTINATARIO", required = true)
        protected String desdestinatario;

        /**
         * Obtiene el valor de la propiedad coddestinatario.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCODDESTINATARIO() {
            return coddestinatario;
        }

        /**
         * Define el valor de la propiedad coddestinatario.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCODDESTINATARIO(String value) {
            this.coddestinatario = value;
        }

        /**
         * Obtiene el valor de la propiedad desdestinatario.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDESDESTINATARIO() {
            return desdestinatario;
        }

        /**
         * Define el valor de la propiedad desdestinatario.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDESDESTINATARIO(String value) {
            this.desdestinatario = value;
        }

    }

}
