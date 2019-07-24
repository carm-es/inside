
package es.mpt.dsic.inside.xml.eni.expediente.indice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import es.mpt.dsic.inside.xml.eni.expediente.indice.contenido.TipoIndiceContenido;
import es.mpt.dsic.inside.xml.eni.firma.Firmas;


/**
 * <p>
 * Java class for TipoIndice complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TipoIndice">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IndiceContenido" type="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/indice-e/contenido}TipoIndiceContenido"/>
 *         &lt;element ref="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/firma}firmas"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoIndice", propOrder = {"indiceContenido", "firmas"})
public class TipoIndice {

  @XmlElement(name = "IndiceContenido", required = true)
  protected TipoIndiceContenido indiceContenido;
  @XmlElement(namespace = "http://administracionelectronica.gob.es/ENI/XSD/v1.0/firma",
      required = true)
  protected Firmas firmas;
  @XmlAttribute(name = "Id")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlID
  @XmlSchemaType(name = "ID")
  protected String id;

  /**
   * Gets the value of the indiceContenido property.
   * 
   * @return possible object is {@link TipoIndiceContenido }
   * 
   */
  public TipoIndiceContenido getIndiceContenido() {
    return indiceContenido;
  }

  /**
   * Sets the value of the indiceContenido property.
   * 
   * @param value allowed object is {@link TipoIndiceContenido }
   * 
   */
  public void setIndiceContenido(TipoIndiceContenido value) {
    this.indiceContenido = value;
  }

  /**
   * Existirá, al menos, una firma del contenido del índice del expediente electrónico.
   * 
   * @return possible object is {@link Firmas }
   * 
   */
  public Firmas getFirmas() {
    return firmas;
  }

  /**
   * Sets the value of the firmas property.
   * 
   * @param value allowed object is {@link Firmas }
   * 
   */
  public void setFirmas(Firmas value) {
    this.firmas = value;
  }

  /**
   * Gets the value of the id property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getId() {
    return id;
  }

  /**
   * Sets the value of the id property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setId(String value) {
    this.id = value;
  }

}
