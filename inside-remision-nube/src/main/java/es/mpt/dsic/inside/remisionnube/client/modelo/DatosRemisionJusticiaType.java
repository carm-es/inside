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


package es.mpt.dsic.inside.remisionnube.client.modelo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Esquema que representa datos comunes en una peticion en Remisión en la nube
 * 
 * <p>Clase Java para DatosRemisionJusticiaType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DatosRemisionJusticiaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dir3Remitente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nig" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="claseProcedimiento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="anyoProcedimiento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numeroProcedimiento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatosRemisionJusticiaType", namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/remisionNube", propOrder = {
    "dir3Remitente",
    "nig",
    "claseProcedimiento",
    "anyoProcedimiento",
    "numeroProcedimiento",
    "descripcion"
})
public class DatosRemisionJusticiaType {

    @XmlElement(required = true)
    protected String dir3Remitente;
    @XmlElement(required = true)
    protected String nig;
    @XmlElement(required = true)
    protected String claseProcedimiento;
    @XmlElement(required = true)
    protected String anyoProcedimiento;
    @XmlElement(required = true)
    protected String numeroProcedimiento;
    @XmlElement(required = true)
    protected String descripcion;

    /**
     * Obtiene el valor de la propiedad dir3Remitente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDir3Remitente() {
        return dir3Remitente;
    }

    /**
     * Define el valor de la propiedad dir3Remitente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDir3Remitente(String value) {
        this.dir3Remitente = value;
    }

    /**
     * Obtiene el valor de la propiedad nig.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNig() {
        return nig;
    }

    /**
     * Define el valor de la propiedad nig.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNig(String value) {
        this.nig = value;
    }

    /**
     * Obtiene el valor de la propiedad claseProcedimiento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaseProcedimiento() {
        return claseProcedimiento;
    }

    /**
     * Define el valor de la propiedad claseProcedimiento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaseProcedimiento(String value) {
        this.claseProcedimiento = value;
    }

    /**
     * Obtiene el valor de la propiedad anyoProcedimiento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnyoProcedimiento() {
        return anyoProcedimiento;
    }

    /**
     * Define el valor de la propiedad anyoProcedimiento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnyoProcedimiento(String value) {
        this.anyoProcedimiento = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroProcedimiento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroProcedimiento() {
        return numeroProcedimiento;
    }

    /**
     * Define el valor de la propiedad numeroProcedimiento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroProcedimiento(String value) {
        this.numeroProcedimiento = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Define el valor de la propiedad descripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

}
