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


package es.mpt.dsic.inside.service.validacionENIMtom.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para RespuestaValidacionENI complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="RespuestaValidacionENI">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="global" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="detalle" type="{http://service.ws.inside.dsic.mpt.es/}Detalle" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RespuestaValidacionENI", propOrder = {
    "global",
    "detalle"
})
public class RespuestaValidacionENI {

    @XmlElement(required = true)
    protected String global;
    @XmlElement(required = true)
    protected List<Detalle> detalle;

    /**
     * Obtiene el valor de la propiedad global.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGlobal() {
        return global;
    }

    /**
     * Define el valor de la propiedad global.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGlobal(String value) {
        this.global = value;
    }

    /**
     * Gets the value of the detalle property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the detalle property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDetalle().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Detalle }
     * 
     * 
     */
    public List<Detalle> getDetalle() {
        if (detalle == null) {
            detalle = new ArrayList<Detalle>();
        }
        return this.detalle;
    }

}
