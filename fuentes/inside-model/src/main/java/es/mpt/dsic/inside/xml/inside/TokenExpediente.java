
package es.mpt.dsic.inside.xml.inside;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>
 * Java class for tokenExpediente complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tokenExpediente">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Identificador" type="{http://www.w3.org/2001/XMLSchema}ID"/>
 *         &lt;element name="csv" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="uuid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fichero" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="resultadoEnvioCorreo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tokenExpediente",
    namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e/tokenExpediente",
    propOrder = {"identificador", "csv", "uuid", "fichero", "resultadoEnvioCorreo"})
public class TokenExpediente {

  @XmlElement(name = "Identificador", required = true)
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlID
  @XmlSchemaType(name = "ID")
  protected String identificador;
  @XmlElement(required = true)
  protected String csv;
  @XmlElement(required = true)
  protected String uuid;
  @XmlElement(required = true)
  protected String fichero;
  @XmlElement(required = true)
  protected String resultadoEnvioCorreo;

  /**
   * Gets the value of the identificador property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getIdentificador() {
    return identificador;
  }

  /**
   * Sets the value of the identificador property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setIdentificador(String value) {
    this.identificador = value;
  }

  /**
   * Gets the value of the csv property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCsv() {
    return csv;
  }

  /**
   * Sets the value of the csv property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCsv(String value) {
    this.csv = value;
  }

  /**
   * Gets the value of the uuid property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getUuid() {
    return uuid;
  }

  /**
   * Sets the value of the uuid property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setUuid(String value) {
    this.uuid = value;
  }

  /**
   * Gets the value of the fichero property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getFichero() {
    return fichero;
  }

  /**
   * Sets the value of the fichero property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setFichero(String value) {
    this.fichero = value;
  }

  /**
   * Gets the value of the resultadoEnvioCorreo property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getResultadoEnvioCorreo() {
    return resultadoEnvioCorreo;
  }

  /**
   * Sets the value of the resultadoEnvioCorreo property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setResultadoEnvioCorreo(String value) {
    this.resultadoEnvioCorreo = value;
  }

}
