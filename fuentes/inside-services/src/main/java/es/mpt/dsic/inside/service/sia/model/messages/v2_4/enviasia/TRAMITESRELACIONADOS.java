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
 * <p>Clase Java para TRAMITESRELACIONADOS complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TRAMITESRELACIONADOS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TRAMITERELACIONADO" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="TRCODACTUACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="TRORDEN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "TRAMITESRELACIONADOS", propOrder = {
    "tramiterelacionado"
})
public class TRAMITESRELACIONADOS {

    @XmlElement(name = "TRAMITERELACIONADO")
    protected List<TRAMITESRELACIONADOS.TRAMITERELACIONADO> tramiterelacionado;

    /**
     * Gets the value of the tramiterelacionado property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tramiterelacionado property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTRAMITERELACIONADO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TRAMITESRELACIONADOS.TRAMITERELACIONADO }
     * 
     * 
     */
    public List<TRAMITESRELACIONADOS.TRAMITERELACIONADO> getTRAMITERELACIONADO() {
        if (tramiterelacionado == null) {
            tramiterelacionado = new ArrayList<TRAMITESRELACIONADOS.TRAMITERELACIONADO>();
        }
        return this.tramiterelacionado;
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
     *         &lt;element name="TRCODACTUACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="TRORDEN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "trcodactuacion",
        "trorden"
    })
    public static class TRAMITERELACIONADO {

        @XmlElement(name = "TRCODACTUACION")
        protected String trcodactuacion;
        @XmlElement(name = "TRORDEN")
        protected String trorden;

        /**
         * Obtiene el valor de la propiedad trcodactuacion.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTRCODACTUACION() {
            return trcodactuacion;
        }

        /**
         * Define el valor de la propiedad trcodactuacion.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTRCODACTUACION(String value) {
            this.trcodactuacion = value;
        }

        /**
         * Obtiene el valor de la propiedad trorden.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTRORDEN() {
            return trorden;
        }

        /**
         * Define el valor de la propiedad trorden.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTRORDEN(String value) {
            this.trorden = value;
        }

    }

}
