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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para PLAZORESOLUCION complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="PLAZORESOLUCION">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NUMEROPLAZORESOLUCION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CODTIPOPLAZORESOLUCION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DESTIPOPLAZORESOLUCION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PLAZORESOLUCION", propOrder = {
    "numeroplazoresolucion",
    "codtipoplazoresolucion",
    "destipoplazoresolucion"
})
public class PLAZORESOLUCION {

    @XmlElement(name = "NUMEROPLAZORESOLUCION")
    protected String numeroplazoresolucion;
    @XmlElement(name = "CODTIPOPLAZORESOLUCION")
    protected String codtipoplazoresolucion;
    @XmlElement(name = "DESTIPOPLAZORESOLUCION")
    protected String destipoplazoresolucion;

    /**
     * Obtiene el valor de la propiedad numeroplazoresolucion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNUMEROPLAZORESOLUCION() {
        return numeroplazoresolucion;
    }

    /**
     * Define el valor de la propiedad numeroplazoresolucion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNUMEROPLAZORESOLUCION(String value) {
        this.numeroplazoresolucion = value;
    }

    /**
     * Obtiene el valor de la propiedad codtipoplazoresolucion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCODTIPOPLAZORESOLUCION() {
        return codtipoplazoresolucion;
    }

    /**
     * Define el valor de la propiedad codtipoplazoresolucion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCODTIPOPLAZORESOLUCION(String value) {
        this.codtipoplazoresolucion = value;
    }

    /**
     * Obtiene el valor de la propiedad destipoplazoresolucion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDESTIPOPLAZORESOLUCION() {
        return destipoplazoresolucion;
    }

    /**
     * Define el valor de la propiedad destipoplazoresolucion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDESTIPOPLAZORESOLUCION(String value) {
        this.destipoplazoresolucion = value;
    }

}
