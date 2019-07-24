
package es.mpt.dsic.inside.xml.inside.ws.busqueda;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import es.mpt.dsic.inside.xml.eni.documento.metadatos.TipoMetadatos;


/**
 * <p>
 * Java class for DocumentoResultadoBusqueda complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DocumentoResultadoBusqueda">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="paginador" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/busqueda/resultados}InfoResultadosBusquedaInside"/>
 *         &lt;element name="resultados" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Identificador" type="{http://www.w3.org/2001/XMLSchema}ID"/>
 *                   &lt;element ref="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/metadatos}metadatos"/>
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
@XmlType(name = "DocumentoResultadoBusqueda",
    namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/busqueda/resultados",
    propOrder = {"paginador", "resultados"})
public class DocumentoResultadoBusqueda {

  @XmlElement(required = true)
  protected InfoResultadosBusquedaInside paginador;
  protected List<DocumentoResultadoBusqueda.Resultados> resultados;

  /**
   * Gets the value of the paginador property.
   * 
   * @return possible object is {@link InfoResultadosBusquedaInside }
   * 
   */
  public InfoResultadosBusquedaInside getPaginador() {
    return paginador;
  }

  /**
   * Sets the value of the paginador property.
   * 
   * @param value allowed object is {@link InfoResultadosBusquedaInside }
   * 
   */
  public void setPaginador(InfoResultadosBusquedaInside value) {
    this.paginador = value;
  }

  /**
   * Gets the value of the resultados property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the resultados property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getResultados().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link DocumentoResultadoBusqueda.Resultados }
   * 
   * 
   */
  public List<DocumentoResultadoBusqueda.Resultados> getResultados() {
    if (resultados == null) {
      resultados = new ArrayList<DocumentoResultadoBusqueda.Resultados>();
    }
    return this.resultados;
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
   *         &lt;element name="Identificador" type="{http://www.w3.org/2001/XMLSchema}ID"/>
   *         &lt;element ref="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/metadatos}metadatos"/>
   *       &lt;/sequence>
   *     &lt;/restriction>
   *   &lt;/complexContent>
   * &lt;/complexType>
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = {"identificador", "metadatos"})
  public static class Resultados {

    @XmlElement(name = "Identificador",
        namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/busqueda/resultados",
        required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String identificador;
    @XmlElement(
        namespace = "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/metadatos",
        required = true)
    protected TipoMetadatos metadatos;

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
     * Gets the value of the metadatos property.
     * 
     * @return possible object is {@link TipoMetadatos }
     * 
     */
    public TipoMetadatos getMetadatos() {
      return metadatos;
    }

    /**
     * Sets the value of the metadatos property.
     * 
     * @param value allowed object is {@link TipoMetadatos }
     * 
     */
    public void setMetadatos(TipoMetadatos value) {
      this.metadatos = value;
    }

  }

}
