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
 * <p>Clase Java para REDUCCIONES complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="REDUCCIONES">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="REDUCCION" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="CODTIPOREDUCCION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="DESTIPOREDUCCION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "REDUCCIONES", propOrder = {
    "reduccion"
})
public class REDUCCIONES {

    @XmlElement(name = "REDUCCION")
    protected List<REDUCCIONES.REDUCCION> reduccion;

    /**
     * Gets the value of the reduccion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reduccion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getREDUCCION().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link REDUCCIONES.REDUCCION }
     * 
     * 
     */
    public List<REDUCCIONES.REDUCCION> getREDUCCION() {
        if (reduccion == null) {
            reduccion = new ArrayList<REDUCCIONES.REDUCCION>();
        }
        return this.reduccion;
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
     *         &lt;element name="CODTIPOREDUCCION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="DESTIPOREDUCCION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "codtiporeduccion",
        "destiporeduccion"
    })
    public static class REDUCCION {

        @XmlElement(name = "CODTIPOREDUCCION")
        protected String codtiporeduccion;
        @XmlElement(name = "DESTIPOREDUCCION")
        protected String destiporeduccion;

        /**
         * Obtiene el valor de la propiedad codtiporeduccion.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCODTIPOREDUCCION() {
            return codtiporeduccion;
        }

        /**
         * Define el valor de la propiedad codtiporeduccion.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCODTIPOREDUCCION(String value) {
            this.codtiporeduccion = value;
        }

        /**
         * Obtiene el valor de la propiedad destiporeduccion.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDESTIPOREDUCCION() {
            return destiporeduccion;
        }

        /**
         * Define el valor de la propiedad destiporeduccion.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDESTIPOREDUCCION(String value) {
            this.destiporeduccion = value;
        }

    }

}
