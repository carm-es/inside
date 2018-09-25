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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para InformacionFirma complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="InformacionFirma">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="esFirma" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="tipoDeFirma" type="{http://service.ws.inside.dsic.mpt.es/}TipoDeFirma" minOccurs="0"/>
 *         &lt;element name="documentoFirmado" type="{http://service.ws.inside.dsic.mpt.es/}ContenidoInfo" minOccurs="0"/>
 *         &lt;element name="hashFirmado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="algoritmoHashFirmado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="firmantes" type="{http://service.ws.inside.dsic.mpt.es/}ListaFirmaInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InformacionFirma", propOrder = {
    "esFirma",
    "tipoDeFirma",
    "documentoFirmado",
    "hashFirmado",
    "algoritmoHashFirmado",
    "firmantes"
})
public class InformacionFirma {

    protected boolean esFirma;
    protected TipoDeFirma tipoDeFirma;
    protected ContenidoInfo documentoFirmado;
    protected String hashFirmado;
    protected String algoritmoHashFirmado;
    protected ListaFirmaInfo firmantes;

    /**
     * Obtiene el valor de la propiedad esFirma.
     * 
     */
    public boolean isEsFirma() {
        return esFirma;
    }

    /**
     * Define el valor de la propiedad esFirma.
     * 
     */
    public void setEsFirma(boolean value) {
        this.esFirma = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoDeFirma.
     * 
     * @return
     *     possible object is
     *     {@link TipoDeFirma }
     *     
     */
    public TipoDeFirma getTipoDeFirma() {
        return tipoDeFirma;
    }

    /**
     * Define el valor de la propiedad tipoDeFirma.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoDeFirma }
     *     
     */
    public void setTipoDeFirma(TipoDeFirma value) {
        this.tipoDeFirma = value;
    }

    /**
     * Obtiene el valor de la propiedad documentoFirmado.
     * 
     * @return
     *     possible object is
     *     {@link ContenidoInfo }
     *     
     */
    public ContenidoInfo getDocumentoFirmado() {
        return documentoFirmado;
    }

    /**
     * Define el valor de la propiedad documentoFirmado.
     * 
     * @param value
     *     allowed object is
     *     {@link ContenidoInfo }
     *     
     */
    public void setDocumentoFirmado(ContenidoInfo value) {
        this.documentoFirmado = value;
    }

    /**
     * Obtiene el valor de la propiedad hashFirmado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHashFirmado() {
        return hashFirmado;
    }

    /**
     * Define el valor de la propiedad hashFirmado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHashFirmado(String value) {
        this.hashFirmado = value;
    }

    /**
     * Obtiene el valor de la propiedad algoritmoHashFirmado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlgoritmoHashFirmado() {
        return algoritmoHashFirmado;
    }

    /**
     * Define el valor de la propiedad algoritmoHashFirmado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlgoritmoHashFirmado(String value) {
        this.algoritmoHashFirmado = value;
    }

    /**
     * Obtiene el valor de la propiedad firmantes.
     * 
     * @return
     *     possible object is
     *     {@link ListaFirmaInfo }
     *     
     */
    public ListaFirmaInfo getFirmantes() {
        return firmantes;
    }

    /**
     * Define el valor de la propiedad firmantes.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaFirmaInfo }
     *     
     */
    public void setFirmantes(ListaFirmaInfo value) {
        this.firmantes = value;
    }

}
