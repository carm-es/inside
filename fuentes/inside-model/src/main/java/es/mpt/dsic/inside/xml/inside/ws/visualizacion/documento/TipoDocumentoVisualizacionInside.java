
package es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import es.mpt.dsic.inside.xml.inside.TipoMetadatosAdicionales;


/**
 * <p>
 * Java class for TipoDocumentoVisualizacionInside complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TipoDocumentoVisualizacionInside">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="documentoEni" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/visualizacion/documento-e}TipoDocumentoEniBinarioOTipo"/>
 *         &lt;element name="metadatosAdicionales" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/metadatosAdicionales}TipoMetadatosAdicionales" minOccurs="0"/>
 *         &lt;element name="opcionesVisualizacionDocumento" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/visualizacion/documento-e}TipoOpcionesVisualizacionDocumento"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoDocumentoVisualizacionInside",
    propOrder = {"documentoEni", "metadatosAdicionales", "opcionesVisualizacionDocumento"})
public class TipoDocumentoVisualizacionInside {

  @XmlElement(required = true)
  protected TipoDocumentoEniBinarioOTipo documentoEni;
  protected TipoMetadatosAdicionales metadatosAdicionales;
  @XmlElement(required = true)
  protected TipoOpcionesVisualizacionDocumento opcionesVisualizacionDocumento;

  /**
   * Gets the value of the documentoEni property.
   * 
   * @return possible object is {@link TipoDocumentoEniBinarioOTipo }
   * 
   */
  public TipoDocumentoEniBinarioOTipo getDocumentoEni() {
    return documentoEni;
  }

  /**
   * Sets the value of the documentoEni property.
   * 
   * @param value allowed object is {@link TipoDocumentoEniBinarioOTipo }
   * 
   */
  public void setDocumentoEni(TipoDocumentoEniBinarioOTipo value) {
    this.documentoEni = value;
  }

  /**
   * Gets the value of the metadatosAdicionales property.
   * 
   * @return possible object is {@link TipoMetadatosAdicionales }
   * 
   */
  public TipoMetadatosAdicionales getMetadatosAdicionales() {
    return metadatosAdicionales;
  }

  /**
   * Sets the value of the metadatosAdicionales property.
   * 
   * @param value allowed object is {@link TipoMetadatosAdicionales }
   * 
   */
  public void setMetadatosAdicionales(TipoMetadatosAdicionales value) {
    this.metadatosAdicionales = value;
  }

  /**
   * Gets the value of the opcionesVisualizacionDocumento property.
   * 
   * @return possible object is {@link TipoOpcionesVisualizacionDocumento }
   * 
   */
  public TipoOpcionesVisualizacionDocumento getOpcionesVisualizacionDocumento() {
    return opcionesVisualizacionDocumento;
  }

  /**
   * Sets the value of the opcionesVisualizacionDocumento property.
   * 
   * @param value allowed object is {@link TipoOpcionesVisualizacionDocumento }
   * 
   */
  public void setOpcionesVisualizacionDocumento(TipoOpcionesVisualizacionDocumento value) {
    this.opcionesVisualizacionDocumento = value;
  }

}
