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
 * <p>Clase Java para PRESTACIONES complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="PRESTACIONES">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PRESTACION" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="CODTIPOPRESTACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="DESTIPOPRESTACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="OTRO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "PRESTACIONES", propOrder = {
    "prestacion"
})
public class PRESTACIONES {

    @XmlElement(name = "PRESTACION")
    protected List<PRESTACIONES.PRESTACION> prestacion;

    /**
     * Gets the value of the prestacion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the prestacion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPRESTACION().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PRESTACIONES.PRESTACION }
     * 
     * 
     */
    public List<PRESTACIONES.PRESTACION> getPRESTACION() {
        if (prestacion == null) {
            prestacion = new ArrayList<PRESTACIONES.PRESTACION>();
        }
        return this.prestacion;
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
     *         &lt;element name="CODTIPOPRESTACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="DESTIPOPRESTACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="OTRO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "codtipoprestacion",
        "destipoprestacion",
        "otro"
    })
    public static class PRESTACION {

        @XmlElement(name = "CODTIPOPRESTACION")
        protected String codtipoprestacion;
        @XmlElement(name = "DESTIPOPRESTACION")
        protected String destipoprestacion;
        @XmlElement(name = "OTRO")
        protected String otro;

        /**
         * Obtiene el valor de la propiedad codtipoprestacion.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCODTIPOPRESTACION() {
            return codtipoprestacion;
        }

        /**
         * Define el valor de la propiedad codtipoprestacion.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCODTIPOPRESTACION(String value) {
            this.codtipoprestacion = value;
        }

        /**
         * Obtiene el valor de la propiedad destipoprestacion.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDESTIPOPRESTACION() {
            return destipoprestacion;
        }

        /**
         * Define el valor de la propiedad destipoprestacion.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDESTIPOPRESTACION(String value) {
            this.destipoprestacion = value;
        }

        /**
         * Obtiene el valor de la propiedad otro.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOTRO() {
            return otro;
        }

        /**
         * Define el valor de la propiedad otro.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOTRO(String value) {
            this.otro = value;
        }

    }

}
