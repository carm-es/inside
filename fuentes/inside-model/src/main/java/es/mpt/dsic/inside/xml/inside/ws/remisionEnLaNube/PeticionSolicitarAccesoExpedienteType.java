
package es.mpt.dsic.inside.xml.inside.ws.remisionEnLaNube;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import es.mpt.dsic.inside.xml.eni.documento.contenido.TipoContenido;
import es.mpt.dsic.inside.xml.inside.TipoMetadatosAdicionales;


/**
 * Esquema que representa una peticion de solicitud de acceso a expediente
 * 
 * <p>
 * Java class for PeticionSolicitarAccesoExpedienteType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PeticionSolicitarAccesoExpedienteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="peticion" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/remisionNube}PeticionType"/>
 *         &lt;element name="asunto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="adjunto" type="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/contenido}TipoContenido" minOccurs="0"/>
 *         &lt;element name="id_exped_pedido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigo_sia" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="metadatos" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/metadatosAdicionales}TipoMetadatosAdicionales" minOccurs="0"/>
 *         &lt;element name="endpoint_peticionario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PeticionSolicitarAccesoExpedienteType", propOrder = {"peticion", "asunto",
    "adjunto", "idExpedPedido", "codigoSia", "metadatos", "endpointPeticionario"})
public class PeticionSolicitarAccesoExpedienteType {

  @XmlElement(required = true)
  protected PeticionType peticion;
  @XmlElement(required = true)
  protected String asunto;
  protected TipoContenido adjunto;
  @XmlElement(name = "id_exped_pedido")
  protected String idExpedPedido;
  @XmlElement(name = "codigo_sia")
  protected int codigoSia;
  protected TipoMetadatosAdicionales metadatos;
  @XmlElement(name = "endpoint_peticionario", required = true)
  protected String endpointPeticionario;

  /**
   * Gets the value of the peticion property.
   * 
   * @return possible object is {@link PeticionType }
   * 
   */
  public PeticionType getPeticion() {
    return peticion;
  }

  /**
   * Sets the value of the peticion property.
   * 
   * @param value allowed object is {@link PeticionType }
   * 
   */
  public void setPeticion(PeticionType value) {
    this.peticion = value;
  }

  /**
   * Gets the value of the asunto property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getAsunto() {
    return asunto;
  }

  /**
   * Sets the value of the asunto property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setAsunto(String value) {
    this.asunto = value;
  }

  /**
   * Gets the value of the adjunto property.
   * 
   * @return possible object is {@link TipoContenido }
   * 
   */
  public TipoContenido getAdjunto() {
    return adjunto;
  }

  /**
   * Sets the value of the adjunto property.
   * 
   * @param value allowed object is {@link TipoContenido }
   * 
   */
  public void setAdjunto(TipoContenido value) {
    this.adjunto = value;
  }

  /**
   * Gets the value of the idExpedPedido property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getIdExpedPedido() {
    return idExpedPedido;
  }

  /**
   * Sets the value of the idExpedPedido property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setIdExpedPedido(String value) {
    this.idExpedPedido = value;
  }

  /**
   * Gets the value of the codigoSia property.
   * 
   */
  public int getCodigoSia() {
    return codigoSia;
  }

  /**
   * Sets the value of the codigoSia property.
   * 
   */
  public void setCodigoSia(int value) {
    this.codigoSia = value;
  }

  /**
   * Gets the value of the metadatos property.
   * 
   * @return possible object is {@link TipoMetadatosAdicionales }
   * 
   */
  public TipoMetadatosAdicionales getMetadatos() {
    return metadatos;
  }

  /**
   * Sets the value of the metadatos property.
   * 
   * @param value allowed object is {@link TipoMetadatosAdicionales }
   * 
   */
  public void setMetadatos(TipoMetadatosAdicionales value) {
    this.metadatos = value;
  }

  /**
   * Gets the value of the endpointPeticionario property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getEndpointPeticionario() {
    return endpointPeticionario;
  }

  /**
   * Sets the value of the endpointPeticionario property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setEndpointPeticionario(String value) {
    this.endpointPeticionario = value;
  }

}
