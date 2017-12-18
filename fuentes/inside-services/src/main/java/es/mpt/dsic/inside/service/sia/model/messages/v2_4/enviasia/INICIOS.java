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
 * <p>Clase Java para INICIOS complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="INICIOS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="INICIO" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="CODFORMAINICIACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="DESFORMAINICIACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="CODEFECTOSILENCIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="DESEFECTOSILENCIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "INICIOS", propOrder = {
    "inicio"
})
public class INICIOS {

    @XmlElement(name = "INICIO")
    protected List<INICIOS.INICIO> inicio;

    /**
     * Gets the value of the inicio property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the inicio property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getINICIO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link INICIOS.INICIO }
     * 
     * 
     */
    public List<INICIOS.INICIO> getINICIO() {
        if (inicio == null) {
            inicio = new ArrayList<INICIOS.INICIO>();
        }
        return this.inicio;
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
     *         &lt;element name="CODFORMAINICIACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="DESFORMAINICIACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="CODEFECTOSILENCIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="DESEFECTOSILENCIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "codformainiciacion",
        "desformainiciacion",
        "codefectosilencio",
        "desefectosilencio"
    })
    public static class INICIO {

        @XmlElement(name = "CODFORMAINICIACION")
        protected String codformainiciacion;
        @XmlElement(name = "DESFORMAINICIACION")
        protected String desformainiciacion;
        @XmlElement(name = "CODEFECTOSILENCIO")
        protected String codefectosilencio;
        @XmlElement(name = "DESEFECTOSILENCIO")
        protected String desefectosilencio;

        /**
         * Obtiene el valor de la propiedad codformainiciacion.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCODFORMAINICIACION() {
            return codformainiciacion;
        }

        /**
         * Define el valor de la propiedad codformainiciacion.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCODFORMAINICIACION(String value) {
            this.codformainiciacion = value;
        }

        /**
         * Obtiene el valor de la propiedad desformainiciacion.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDESFORMAINICIACION() {
            return desformainiciacion;
        }

        /**
         * Define el valor de la propiedad desformainiciacion.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDESFORMAINICIACION(String value) {
            this.desformainiciacion = value;
        }

        /**
         * Obtiene el valor de la propiedad codefectosilencio.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCODEFECTOSILENCIO() {
            return codefectosilencio;
        }

        /**
         * Define el valor de la propiedad codefectosilencio.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCODEFECTOSILENCIO(String value) {
            this.codefectosilencio = value;
        }

        /**
         * Obtiene el valor de la propiedad desefectosilencio.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDESEFECTOSILENCIO() {
            return desefectosilencio;
        }

        /**
         * Define el valor de la propiedad desefectosilencio.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDESEFECTOSILENCIO(String value) {
            this.desefectosilencio = value;
        }

    }

}
