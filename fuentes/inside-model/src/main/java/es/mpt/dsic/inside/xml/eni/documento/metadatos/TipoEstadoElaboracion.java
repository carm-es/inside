
package es.mpt.dsic.inside.xml.eni.documento.metadatos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Java class for TipoEstadoElaboracion complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TipoEstadoElaboracion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ValorEstadoElaboracion" type="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/metadatos}enumeracionEstadoElaboracion"/>
 *         &lt;element name="IdentificadorDocumentoOrigen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoEstadoElaboracion",
    propOrder = {"valorEstadoElaboracion", "identificadorDocumentoOrigen"})
public class TipoEstadoElaboracion {

  @XmlElement(name = "ValorEstadoElaboracion", required = true)
  protected EnumeracionEstadoElaboracion valorEstadoElaboracion;
  @XmlElement(name = "IdentificadorDocumentoOrigen")
  protected String identificadorDocumentoOrigen;

  /**
   * Gets the value of the valorEstadoElaboracion property.
   * 
   * @return possible object is {@link EnumeracionEstadoElaboracion }
   * 
   */
  public EnumeracionEstadoElaboracion getValorEstadoElaboracion() {
    return valorEstadoElaboracion;
  }

  /**
   * Sets the value of the valorEstadoElaboracion property.
   * 
   * @param value allowed object is {@link EnumeracionEstadoElaboracion }
   * 
   */
  public void setValorEstadoElaboracion(EnumeracionEstadoElaboracion value) {
    this.valorEstadoElaboracion = value;
  }

  /**
   * Gets the value of the identificadorDocumentoOrigen property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getIdentificadorDocumentoOrigen() {
    return identificadorDocumentoOrigen;
  }

  /**
   * Sets the value of the identificadorDocumentoOrigen property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setIdentificadorDocumentoOrigen(String value) {
    this.identificadorDocumentoOrigen = value;
  }

}
