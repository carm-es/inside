
package es.mpt.dsic.inside.xml.eni.documento.contenido;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>
 * Java class for TipoContenido complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TipoContenido">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="DatosXML" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *           &lt;element name="ValorBinario" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *           &lt;element name="referenciaFichero" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;/choice>
 *         &lt;element name="NombreFormato" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "TipoContenido",
    propOrder = {"datosXML", "valorBinario", "referenciaFichero", "nombreFormato"})
public class TipoContenido {

  @XmlElement(name = "DatosXML")
  protected Object datosXML;
  @XmlElement(name = "ValorBinario")
  protected byte[] valorBinario;
  protected String referenciaFichero;
  @XmlElement(name = "NombreFormato", required = true)
  protected String nombreFormato;
  @XmlAttribute(name = "Id")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlID
  @XmlSchemaType(name = "ID")
  protected String id;

  /**
   * Gets the value of the datosXML property.
   * 
   * @return possible object is {@link Object }
   * 
   */
  public Object getDatosXML() {
    return datosXML;
  }

  /**
   * Sets the value of the datosXML property.
   * 
   * @param value allowed object is {@link Object }
   * 
   */
  public void setDatosXML(Object value) {
    this.datosXML = value;
  }

  /**
   * Gets the value of the valorBinario property.
   * 
   * @return possible object is byte[]
   */
  public byte[] getValorBinario() {
    return valorBinario;
  }

  /**
   * Sets the value of the valorBinario property.
   * 
   * @param value allowed object is byte[]
   */
  public void setValorBinario(byte[] value) {
    this.valorBinario = ((byte[]) value);
  }

  /**
   * Gets the value of the referenciaFichero property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getReferenciaFichero() {
    return referenciaFichero;
  }

  /**
   * Sets the value of the referenciaFichero property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setReferenciaFichero(String value) {
    this.referenciaFichero = value;
  }

  /**
   * Gets the value of the nombreFormato property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getNombreFormato() {
    return nombreFormato;
  }

  /**
   * Sets the value of the nombreFormato property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setNombreFormato(String value) {
    this.nombreFormato = value;
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
