
package es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.mtom;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Java class for TipoOpcionesVisualizacionDocumentoMtom complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TipoOpcionesVisualizacionDocumentoMtom">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EstamparImagen" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="EstamparNombreOrganismo" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="FilasNombreOrganismo" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Fila" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoOpcionesVisualizacionDocumentoMtom",
    propOrder = {"estamparImagen", "estamparNombreOrganismo", "filasNombreOrganismo"})
public class TipoOpcionesVisualizacionDocumentoMtom {

  @XmlElement(name = "EstamparImagen")
  protected boolean estamparImagen;
  @XmlElement(name = "EstamparNombreOrganismo")
  protected boolean estamparNombreOrganismo;
  @XmlElement(name = "FilasNombreOrganismo")
  protected TipoOpcionesVisualizacionDocumentoMtom.FilasNombreOrganismo filasNombreOrganismo;

  /**
   * Gets the value of the estamparImagen property.
   * 
   */
  public boolean isEstamparImagen() {
    return estamparImagen;
  }

  /**
   * Sets the value of the estamparImagen property.
   * 
   */
  public void setEstamparImagen(boolean value) {
    this.estamparImagen = value;
  }

  /**
   * Gets the value of the estamparNombreOrganismo property.
   * 
   */
  public boolean isEstamparNombreOrganismo() {
    return estamparNombreOrganismo;
  }

  /**
   * Sets the value of the estamparNombreOrganismo property.
   * 
   */
  public void setEstamparNombreOrganismo(boolean value) {
    this.estamparNombreOrganismo = value;
  }

  /**
   * Gets the value of the filasNombreOrganismo property.
   * 
   * @return possible object is {@link TipoOpcionesVisualizacionDocumentoMtom.FilasNombreOrganismo }
   * 
   */
  public TipoOpcionesVisualizacionDocumentoMtom.FilasNombreOrganismo getFilasNombreOrganismo() {
    return filasNombreOrganismo;
  }

  /**
   * Sets the value of the filasNombreOrganismo property.
   * 
   * @param value allowed object is
   *        {@link TipoOpcionesVisualizacionDocumentoMtom.FilasNombreOrganismo }
   * 
   */
  public void setFilasNombreOrganismo(
      TipoOpcionesVisualizacionDocumentoMtom.FilasNombreOrganismo value) {
    this.filasNombreOrganismo = value;
  }


  /**
   * <p>
   * Java class for anonymous complex type.
   * 
   * <p>
   * The following schema fragment specifies the expected content contained within this class.
   * 
   * <pre>
   * &lt;complexType>
   *   &lt;complexContent>
   *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
   *       &lt;sequence>
   *         &lt;element name="Fila" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
   *       &lt;/sequence>
   *     &lt;/restriction>
   *   &lt;/complexContent>
   * &lt;/complexType>
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = {"fila"})
  public static class FilasNombreOrganismo {

    @XmlElement(name = "Fila", required = true)
    protected List<String> fila;

    /**
     * Gets the value of the fila property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any
     * modification you make to the returned list will be present inside the JAXB object. This is
     * why there is not a <CODE>set</CODE> method for the fila property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getFila().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link String }
     * 
     * 
     */
    public List<String> getFila() {
      if (fila == null) {
        fila = new ArrayList<String>();
      }
      return this.fila;
    }

  }

}
