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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para validarExpedienteENI complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="validarExpedienteENI">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aplicacionInfo" type="{http://service.ws.inside.dsic.mpt.es/}applicationLogin"/>
 *         &lt;element name="DocumentoEntradaMtom" type="{http://service.ws.inside.dsic.mpt.es/}DocumentoEntradaMtom"/>
 *         &lt;element name="versionENI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="validaciones" type="{http://service.ws.inside.dsic.mpt.es/}Validaciones"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "validarExpedienteENI", propOrder = {
    "aplicacionInfo",
    "documentoEntradaMtom",
    "versionENI",
    "validaciones"
})
public class ValidarExpedienteENI {

    @XmlElement(required = true)
    protected ApplicationLogin aplicacionInfo;
    @XmlElement(name = "DocumentoEntradaMtom", required = true)
    protected DocumentoEntradaMtom documentoEntradaMtom;
    protected String versionENI;
    @XmlElement(required = true)
    protected Validaciones validaciones;

    /**
     * Obtiene el valor de la propiedad aplicacionInfo.
     * 
     * @return
     *     possible object is
     *     {@link ApplicationLogin }
     *     
     */
    public ApplicationLogin getAplicacionInfo() {
        return aplicacionInfo;
    }

    /**
     * Define el valor de la propiedad aplicacionInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link ApplicationLogin }
     *     
     */
    public void setAplicacionInfo(ApplicationLogin value) {
        this.aplicacionInfo = value;
    }

    /**
     * Obtiene el valor de la propiedad documentoEntradaMtom.
     * 
     * @return
     *     possible object is
     *     {@link DocumentoEntradaMtom }
     *     
     */
    public DocumentoEntradaMtom getDocumentoEntradaMtom() {
        return documentoEntradaMtom;
    }

    /**
     * Define el valor de la propiedad documentoEntradaMtom.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentoEntradaMtom }
     *     
     */
    public void setDocumentoEntradaMtom(DocumentoEntradaMtom value) {
        this.documentoEntradaMtom = value;
    }

    /**
     * Obtiene el valor de la propiedad versionENI.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersionENI() {
        return versionENI;
    }

    /**
     * Define el valor de la propiedad versionENI.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersionENI(String value) {
        this.versionENI = value;
    }

    /**
     * Obtiene el valor de la propiedad validaciones.
     * 
     * @return
     *     possible object is
     *     {@link Validaciones }
     *     
     */
    public Validaciones getValidaciones() {
        return validaciones;
    }

    /**
     * Define el valor de la propiedad validaciones.
     * 
     * @param value
     *     allowed object is
     *     {@link Validaciones }
     *     
     */
    public void setValidaciones(Validaciones value) {
        this.validaciones = value;
    }

}
