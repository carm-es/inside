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
 * <p>Clase Java para CopiaInfo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CopiaInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idAplicacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="csv" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fecha" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="expediente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nif" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="urlSede" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contenido" type="{http://service.ws.inside.dsic.mpt.es/}ContenidoInfo"/>
 *         &lt;element name="tituloAplicacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tituloCSV" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tituloFecha" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tituloExpediente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tituloNif" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tituloURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estamparLogo" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="lateral" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="urlQR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="opcionesPagina" type="{http://service.ws.inside.dsic.mpt.es/}opcionesPagina" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CopiaInfo", propOrder = {
    "idAplicacion",
    "csv",
    "fecha",
    "expediente",
    "nif",
    "urlSede",
    "contenido",
    "tituloAplicacion",
    "tituloCSV",
    "tituloFecha",
    "tituloExpediente",
    "tituloNif",
    "tituloURL",
    "estamparLogo",
    "lateral",
    "urlQR",
    "opcionesPagina"
})
public class CopiaInfo {

    @XmlElement(required = true)
    protected String idAplicacion;
    @XmlElement(required = true)
    protected String csv;
    @XmlElement(required = true)
    protected String fecha;
    @XmlElement(required = true)
    protected String expediente;
    protected String nif;
    @XmlElement(defaultValue = "https://sede.administracionespublicas.gob.es/valida")
    protected String urlSede;
    @XmlElement(required = true)
    protected ContenidoInfo contenido;
    protected String tituloAplicacion;
    protected String tituloCSV;
    protected String tituloFecha;
    protected String tituloExpediente;
    protected String tituloNif;
    protected String tituloURL;
    protected boolean estamparLogo;
    protected String lateral;
    protected String urlQR;
    protected OpcionesPagina opcionesPagina;

    /**
     * Obtiene el valor de la propiedad idAplicacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdAplicacion() {
        return idAplicacion;
    }

    /**
     * Define el valor de la propiedad idAplicacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdAplicacion(String value) {
        this.idAplicacion = value;
    }

    /**
     * Obtiene el valor de la propiedad csv.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCsv() {
        return csv;
    }

    /**
     * Define el valor de la propiedad csv.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCsv(String value) {
        this.csv = value;
    }

    /**
     * Obtiene el valor de la propiedad fecha.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Define el valor de la propiedad fecha.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFecha(String value) {
        this.fecha = value;
    }

    /**
     * Obtiene el valor de la propiedad expediente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpediente() {
        return expediente;
    }

    /**
     * Define el valor de la propiedad expediente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpediente(String value) {
        this.expediente = value;
    }

    /**
     * Obtiene el valor de la propiedad nif.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNif() {
        return nif;
    }

    /**
     * Define el valor de la propiedad nif.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNif(String value) {
        this.nif = value;
    }

    /**
     * Obtiene el valor de la propiedad urlSede.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlSede() {
        return urlSede;
    }

    /**
     * Define el valor de la propiedad urlSede.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlSede(String value) {
        this.urlSede = value;
    }

    /**
     * Obtiene el valor de la propiedad contenido.
     * 
     * @return
     *     possible object is
     *     {@link ContenidoInfo }
     *     
     */
    public ContenidoInfo getContenido() {
        return contenido;
    }

    /**
     * Define el valor de la propiedad contenido.
     * 
     * @param value
     *     allowed object is
     *     {@link ContenidoInfo }
     *     
     */
    public void setContenido(ContenidoInfo value) {
        this.contenido = value;
    }

    /**
     * Obtiene el valor de la propiedad tituloAplicacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTituloAplicacion() {
        return tituloAplicacion;
    }

    /**
     * Define el valor de la propiedad tituloAplicacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTituloAplicacion(String value) {
        this.tituloAplicacion = value;
    }

    /**
     * Obtiene el valor de la propiedad tituloCSV.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTituloCSV() {
        return tituloCSV;
    }

    /**
     * Define el valor de la propiedad tituloCSV.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTituloCSV(String value) {
        this.tituloCSV = value;
    }

    /**
     * Obtiene el valor de la propiedad tituloFecha.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTituloFecha() {
        return tituloFecha;
    }

    /**
     * Define el valor de la propiedad tituloFecha.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTituloFecha(String value) {
        this.tituloFecha = value;
    }

    /**
     * Obtiene el valor de la propiedad tituloExpediente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTituloExpediente() {
        return tituloExpediente;
    }

    /**
     * Define el valor de la propiedad tituloExpediente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTituloExpediente(String value) {
        this.tituloExpediente = value;
    }

    /**
     * Obtiene el valor de la propiedad tituloNif.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTituloNif() {
        return tituloNif;
    }

    /**
     * Define el valor de la propiedad tituloNif.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTituloNif(String value) {
        this.tituloNif = value;
    }

    /**
     * Obtiene el valor de la propiedad tituloURL.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTituloURL() {
        return tituloURL;
    }

    /**
     * Define el valor de la propiedad tituloURL.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTituloURL(String value) {
        this.tituloURL = value;
    }

    /**
     * Obtiene el valor de la propiedad estamparLogo.
     * 
     */
    public boolean isEstamparLogo() {
        return estamparLogo;
    }

    /**
     * Define el valor de la propiedad estamparLogo.
     * 
     */
    public void setEstamparLogo(boolean value) {
        this.estamparLogo = value;
    }

    /**
     * Obtiene el valor de la propiedad lateral.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLateral() {
        return lateral;
    }

    /**
     * Define el valor de la propiedad lateral.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLateral(String value) {
        this.lateral = value;
    }

    /**
     * Obtiene el valor de la propiedad urlQR.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlQR() {
        return urlQR;
    }

    /**
     * Define el valor de la propiedad urlQR.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlQR(String value) {
        this.urlQR = value;
    }

    /**
     * Obtiene el valor de la propiedad opcionesPagina.
     * 
     * @return
     *     possible object is
     *     {@link OpcionesPagina }
     *     
     */
    public OpcionesPagina getOpcionesPagina() {
        return opcionesPagina;
    }

    /**
     * Define el valor de la propiedad opcionesPagina.
     * 
     * @param value
     *     allowed object is
     *     {@link OpcionesPagina }
     *     
     */
    public void setOpcionesPagina(OpcionesPagina value) {
        this.opcionesPagina = value;
    }

}
