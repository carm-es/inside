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
 * <p>Clase Java para ObjectCopiaFirmaCSV complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ObjectCopiaFirmaCSV">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ambito" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="generarCSV" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="normalizado" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="simple" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="dir3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObjectCopiaFirmaCSV", propOrder = {
    "ambito",
    "generarCSV",
    "normalizado",
    "simple",
    "dir3"
})
public class ObjectCopiaFirmaCSV {

    @XmlElement(required = true)
    protected String ambito;
    protected boolean generarCSV;
    protected boolean normalizado;
    protected boolean simple;
    @XmlElement(required = true)
    protected String dir3;

    /**
     * Obtiene el valor de la propiedad ambito.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAmbito() {
        return ambito;
    }

    /**
     * Define el valor de la propiedad ambito.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAmbito(String value) {
        this.ambito = value;
    }

    /**
     * Obtiene el valor de la propiedad generarCSV.
     * 
     */
    public boolean isGenerarCSV() {
        return generarCSV;
    }

    /**
     * Define el valor de la propiedad generarCSV.
     * 
     */
    public void setGenerarCSV(boolean value) {
        this.generarCSV = value;
    }

    /**
     * Obtiene el valor de la propiedad normalizado.
     * 
     */
    public boolean isNormalizado() {
        return normalizado;
    }

    /**
     * Define el valor de la propiedad normalizado.
     * 
     */
    public void setNormalizado(boolean value) {
        this.normalizado = value;
    }

    /**
     * Obtiene el valor de la propiedad simple.
     * 
     */
    public boolean isSimple() {
        return simple;
    }

    /**
     * Define el valor de la propiedad simple.
     * 
     */
    public void setSimple(boolean value) {
        this.simple = value;
    }

    /**
     * Obtiene el valor de la propiedad dir3.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDir3() {
        return dir3;
    }

    /**
     * Define el valor de la propiedad dir3.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDir3(String value) {
        this.dir3 = value;
    }

}
