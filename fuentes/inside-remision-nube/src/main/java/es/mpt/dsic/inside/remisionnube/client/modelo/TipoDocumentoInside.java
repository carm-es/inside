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
 * <p>Clase Java para TipoDocumentoInside complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TipoDocumentoInside">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="info" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/documentoInfo}DocumentoInsideInfo"/>
 *         &lt;element name="documentoENI" type="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e}TipoDocumento"/>
 *         &lt;element name="metadatosAdicionales" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/metadatosAdicionales}TipoMetadatosAdicionales"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoDocumentoInside", namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e", propOrder = {
    "info",
    "documentoENI",
    "metadatosAdicionales"
})
public class TipoDocumentoInside {

    @XmlElement(required = true)
    protected DocumentoInsideInfo info;
    @XmlElement(required = true)
    protected TipoDocumento documentoENI;
    @XmlElement(required = true)
    protected TipoMetadatosAdicionales metadatosAdicionales;

    /**
     * Obtiene el valor de la propiedad info.
     * 
     * @return
     *     possible object is
     *     {@link DocumentoInsideInfo }
     *     
     */
    public DocumentoInsideInfo getInfo() {
        return info;
    }

    /**
     * Define el valor de la propiedad info.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentoInsideInfo }
     *     
     */
    public void setInfo(DocumentoInsideInfo value) {
        this.info = value;
    }

    /**
     * Obtiene el valor de la propiedad documentoENI.
     * 
     * @return
     *     possible object is
     *     {@link TipoDocumento }
     *     
     */
    public TipoDocumento getDocumentoENI() {
        return documentoENI;
    }

    /**
     * Define el valor de la propiedad documentoENI.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoDocumento }
     *     
     */
    public void setDocumentoENI(TipoDocumento value) {
        this.documentoENI = value;
    }

    /**
     * Obtiene el valor de la propiedad metadatosAdicionales.
     * 
     * @return
     *     possible object is
     *     {@link TipoMetadatosAdicionales }
     *     
     */
    public TipoMetadatosAdicionales getMetadatosAdicionales() {
        return metadatosAdicionales;
    }

    /**
     * Define el valor de la propiedad metadatosAdicionales.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoMetadatosAdicionales }
     *     
     */
    public void setMetadatosAdicionales(TipoMetadatosAdicionales value) {
        this.metadatosAdicionales = value;
    }

}
