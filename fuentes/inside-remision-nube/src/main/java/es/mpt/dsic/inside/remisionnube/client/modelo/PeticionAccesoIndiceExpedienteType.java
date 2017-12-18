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
 * Esquema que representa una peticion de acceso al indice del expediente
 * 
 * <p>Clase Java para PeticionAccesoIndiceExpedienteType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="PeticionAccesoIndiceExpedienteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="peticion" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/remisionNube}PeticionType"/>
 *         &lt;element name="token" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/remisionNube}StringTokenType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PeticionAccesoIndiceExpedienteType", namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/remisionNube", propOrder = {
    "peticion",
    "token"
})
public class PeticionAccesoIndiceExpedienteType {

    @XmlElement(required = true)
    protected PeticionType peticion;
    @XmlElement(required = true)
    protected StringTokenType token;

    /**
     * Obtiene el valor de la propiedad peticion.
     * 
     * @return
     *     possible object is
     *     {@link PeticionType }
     *     
     */
    public PeticionType getPeticion() {
        return peticion;
    }

    /**
     * Define el valor de la propiedad peticion.
     * 
     * @param value
     *     allowed object is
     *     {@link PeticionType }
     *     
     */
    public void setPeticion(PeticionType value) {
        this.peticion = value;
    }

    /**
     * Obtiene el valor de la propiedad token.
     * 
     * @return
     *     possible object is
     *     {@link StringTokenType }
     *     
     */
    public StringTokenType getToken() {
        return token;
    }

    /**
     * Define el valor de la propiedad token.
     * 
     * @param value
     *     allowed object is
     *     {@link StringTokenType }
     *     
     */
    public void setToken(StringTokenType value) {
        this.token = value;
    }

}
