
package es.mpt.dsic.inside.xml.inside.ws.remisionEnLaNube;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import es.mpt.dsic.inside.xml.eni.documento.contenido.TipoContenido;
import es.mpt.dsic.inside.xml.eni.expediente.TipoExpediente;
import es.mpt.dsic.inside.xml.eni.expediente.metadatos.TipoMetadatos;


/**
 * Esquema que representa una comunicación de credenciales o claves de acceso del expediente solicitado
 * 
 * <p>Java class for PeticionComunicacionTokenExpedienteType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PeticionComunicacionTokenExpedienteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="peticion" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/remisionNube}PeticionType"/>
 *         &lt;element name="asunto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="adjunto" type="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/contenido}TipoContenido" minOccurs="0"/>
 *         &lt;element name="metadatosExp" type="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/metadatos}TipoMetadatos" minOccurs="0"/>
 *         &lt;element name="indiceExp" type="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e}TipoExpediente" minOccurs="0"/>
 *         &lt;element name="token" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/remisionNube}StringTokenType"/>
 *         &lt;element name="datosRemisionJusticia" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/remisionNube}DatosRemisionJusticiaType" minOccurs="0"/>
 *         &lt;element name="endpoint_remitente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="url_accesoWeb" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PeticionComunicacionTokenExpedienteType", propOrder = {
    "peticion",
    "asunto",
    "adjunto",
    "metadatosExp",
    "indiceExp",
    "token",
    "datosRemisionJusticia",
    "endpointRemitente",
    "urlAccesoWeb"
})
public class PeticionComunicacionTokenExpedienteType {

    @XmlElement(required = true)
    protected PeticionType peticion;
    protected String asunto;
    protected TipoContenido adjunto;
    protected TipoMetadatos metadatosExp;
    protected TipoExpediente indiceExp;
    @XmlElement(required = true)
    protected StringTokenType token;
    protected DatosRemisionJusticiaType datosRemisionJusticia;
    @XmlElement(name = "endpoint_remitente", required = true)
    protected String endpointRemitente;
    @XmlElement(name = "url_accesoWeb")
    protected String urlAccesoWeb;

    /**
     * Gets the value of the peticion property.
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
     * Sets the value of the peticion property.
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
     * Gets the value of the asunto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAsunto() {
        return asunto;
    }

    /**
     * Sets the value of the asunto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAsunto(String value) {
        this.asunto = value;
    }

    /**
     * Gets the value of the adjunto property.
     * 
     * @return
     *     possible object is
     *     {@link TipoContenido }
     *     
     */
    public TipoContenido getAdjunto() {
        return adjunto;
    }

    /**
     * Sets the value of the adjunto property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoContenido }
     *     
     */
    public void setAdjunto(TipoContenido value) {
        this.adjunto = value;
    }

    /**
     * Gets the value of the metadatosExp property.
     * 
     * @return
     *     possible object is
     *     {@link TipoMetadatos }
     *     
     */
    public TipoMetadatos getMetadatosExp() {
        return metadatosExp;
    }

    /**
     * Sets the value of the metadatosExp property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoMetadatos }
     *     
     */
    public void setMetadatosExp(TipoMetadatos value) {
        this.metadatosExp = value;
    }

    /**
     * Gets the value of the indiceExp property.
     * 
     * @return
     *     possible object is
     *     {@link TipoExpediente }
     *     
     */
    public TipoExpediente getIndiceExp() {
        return indiceExp;
    }

    /**
     * Sets the value of the indiceExp property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoExpediente }
     *     
     */
    public void setIndiceExp(TipoExpediente value) {
        this.indiceExp = value;
    }

    /**
     * Gets the value of the token property.
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
     * Sets the value of the token property.
     * 
     * @param value
     *     allowed object is
     *     {@link StringTokenType }
     *     
     */
    public void setToken(StringTokenType value) {
        this.token = value;
    }

    /**
     * Gets the value of the datosRemisionJusticia property.
     * 
     * @return
     *     possible object is
     *     {@link DatosRemisionJusticiaType }
     *     
     */
    public DatosRemisionJusticiaType getDatosRemisionJusticia() {
        return datosRemisionJusticia;
    }

    /**
     * Sets the value of the datosRemisionJusticia property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatosRemisionJusticiaType }
     *     
     */
    public void setDatosRemisionJusticia(DatosRemisionJusticiaType value) {
        this.datosRemisionJusticia = value;
    }

    /**
     * Gets the value of the endpointRemitente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndpointRemitente() {
        return endpointRemitente;
    }

    /**
     * Sets the value of the endpointRemitente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndpointRemitente(String value) {
        this.endpointRemitente = value;
    }

    /**
     * Gets the value of the urlAccesoWeb property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlAccesoWeb() {
        return urlAccesoWeb;
    }

    /**
     * Sets the value of the urlAccesoWeb property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlAccesoWeb(String value) {
        this.urlAccesoWeb = value;
    }

}
