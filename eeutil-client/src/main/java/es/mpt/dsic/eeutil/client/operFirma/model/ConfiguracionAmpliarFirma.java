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


package es.mpt.dsic.eeutil.client.operFirma.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para configuracionAmpliarFirma complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="configuracionAmpliarFirma">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="formatoAmpliacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ignorarPeriodoDeGracia" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="certificadosFirmantes" type="{http://service.ws.inside.dsic.mpt.es/}certificateList" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "configuracionAmpliarFirma", propOrder = {
    "formatoAmpliacion",
    "ignorarPeriodoDeGracia",
    "certificadosFirmantes"
})
public class ConfiguracionAmpliarFirma {

    @XmlElement(required = true)
    protected String formatoAmpliacion;
    protected boolean ignorarPeriodoDeGracia;
    protected CertificateList certificadosFirmantes;

    /**
     * Obtiene el valor de la propiedad formatoAmpliacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormatoAmpliacion() {
        return formatoAmpliacion;
    }

    /**
     * Define el valor de la propiedad formatoAmpliacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormatoAmpliacion(String value) {
        this.formatoAmpliacion = value;
    }

    /**
     * Obtiene el valor de la propiedad ignorarPeriodoDeGracia.
     * 
     */
    public boolean isIgnorarPeriodoDeGracia() {
        return ignorarPeriodoDeGracia;
    }

    /**
     * Define el valor de la propiedad ignorarPeriodoDeGracia.
     * 
     */
    public void setIgnorarPeriodoDeGracia(boolean value) {
        this.ignorarPeriodoDeGracia = value;
    }

    /**
     * Obtiene el valor de la propiedad certificadosFirmantes.
     * 
     * @return
     *     possible object is
     *     {@link CertificateList }
     *     
     */
    public CertificateList getCertificadosFirmantes() {
        return certificadosFirmantes;
    }

    /**
     * Define el valor de la propiedad certificadosFirmantes.
     * 
     * @param value
     *     allowed object is
     *     {@link CertificateList }
     *     
     */
    public void setCertificadosFirmantes(CertificateList value) {
        this.certificadosFirmantes = value;
    }

}
