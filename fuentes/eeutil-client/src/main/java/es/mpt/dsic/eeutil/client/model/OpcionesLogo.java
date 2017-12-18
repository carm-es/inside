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
 * <p>Java class for opcionesLogo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="opcionesLogo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="estamparLogo" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="estamparNombreOrganismo" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="listaCadenasNombreOrganismo" type="{http://service.ws.inside.dsic.mpt.es/}listaCadenas" minOccurs="0"/>
 *         &lt;element name="posicion" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="estamparPie" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="textoPie" type="{http://www.w3.org/2001/XMLSchema}string minOccurs=0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "opcionesLogo", propOrder = {
    "estamparLogo",
    "estamparNombreOrganismo",
    "listaCadenasNombreOrganismo",
    "posicion",
    "estamparPie",
    "textoPie"
})
public class OpcionesLogo {

    @XmlElement(defaultValue = "true")
    protected boolean estamparLogo;
    @XmlElement(defaultValue = "true")
    protected boolean estamparNombreOrganismo;
    protected ListaCadenas listaCadenasNombreOrganismo;
    protected Integer posicion;
    protected boolean estamparPie;
    protected String textoPie;
    

    /**
     * Gets the value of the estamparLogo property.
     * 
     */
    public boolean isEstamparLogo() {
        return estamparLogo;
    }

    /**
     * Sets the value of the estamparLogo property.
     * 
     */
    public void setEstamparLogo(boolean value) {
        this.estamparLogo = value;
    }

    /**
     * Gets the value of the estamparNombreOrganismo property.
     * 
     */
    public boolean isEstamparNombreOrganismo() {
        return estamparNombreOrganismo;
    }

    /**
     * Sets the value of the estamparNombreOrganismo property.
     * 
     */
    public void setEstamparNombreOrganismo(boolean value) {
        this.estamparNombreOrganismo = value;
    }

    /**
     * Gets the value of the listaCadenasNombreOrganismo property.
     * 
     * @return
     *     possible object is
     *     {@link ListaCadenas }
     *     
     */
    public ListaCadenas getListaCadenasNombreOrganismo() {
        return listaCadenasNombreOrganismo;
    }

    /**
     * Sets the value of the listaCadenasNombreOrganismo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaCadenas }
     *     
     */
    public void setListaCadenasNombreOrganismo(ListaCadenas value) {
        this.listaCadenasNombreOrganismo = value;
    }

    /**
     * Gets the value of the posicion property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPosicion() {
        return posicion;
    }

    /**
     * Sets the value of the posicion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPosicion(Integer value) {
        this.posicion = value;
    }

    /**
     * Gets the value of the estamparPie property.
     *   
     */
	public boolean isEstamparPie() {
		return estamparPie;
	}

	/**
     * Sets the value of the estamparPie property.
     *   
     */
	public void setEstamparPie(boolean estamparPie) {
		this.estamparPie = estamparPie;
	}

	 /**
     * Gets the value of the textoPie property.
     *   
     */
	public String getTextoPie() {
		return textoPie;
	}

	 /**
     * Sets the value of the textoPie property.
     *   
     */
	public void setTextoPie(String textoPie) {
		this.textoPie = textoPie;
	}
    
    

}
