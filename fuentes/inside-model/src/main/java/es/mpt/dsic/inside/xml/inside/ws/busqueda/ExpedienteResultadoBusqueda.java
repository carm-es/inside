
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
import es.mpt.dsic.inside.xml.eni.expediente.metadatos.TipoMetadatos;
import es.mpt.dsic.inside.xml.inside.TipoVersionInside;


/**
 * <p>
 * Java class for ExpedienteResultadoBusqueda complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExpedienteResultadoBusqueda">
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
 *                   &lt;element name="versionActual" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/version}TipoVersionInside"/>
 *                   &lt;element ref="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/metadatos}metadatosExp"/>
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
@XmlType(name = "ExpedienteResultadoBusqueda",
    namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/busqueda/resultados",
    propOrder = {"paginador", "resultados"})
public class ExpedienteResultadoBusqueda {

  @XmlElement(required = true)
  protected InfoResultadosBusquedaInside paginador;
  protected List<ExpedienteResultadoBusqueda.Resultados> resultados;

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
   * {@link ExpedienteResultadoBusqueda.Resultados }
   * 
   * 
   */
  public List<ExpedienteResultadoBusqueda.Resultados> getResultados() {
    if (resultados == null) {
      resultados = new ArrayList<ExpedienteResultadoBusqueda.Resultados>();
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
   *         &lt;element name="versionActual" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/version}TipoVersionInside"/>
   *         &lt;element ref="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/metadatos}metadatosExp"/>
   *       &lt;/sequence>
   *     &lt;/restriction>
   *   &lt;/complexContent>
   * &lt;/complexType>
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = {"identificador", "versionActual", "metadatosExp"})
  public static class Resultados {

    @XmlElement(name = "Identificador",
        namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/busqueda/resultados",
        required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String identificador;
    @XmlElement(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/busqueda/resultados",
        required = true)
    protected TipoVersionInside versionActual;
    @XmlElement(
        namespace = "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/metadatos",
        required = true)
    protected TipoMetadatos metadatosExp;

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
     * Gets the value of the versionActual property.
     * 
     * @return possible object is {@link TipoVersionInside }
     * 
     */
    public TipoVersionInside getVersionActual() {
      return versionActual;
    }

    /**
     * Sets the value of the versionActual property.
     * 
     * @param value allowed object is {@link TipoVersionInside }
     * 
     */
    public void setVersionActual(TipoVersionInside value) {
      this.versionActual = value;
    }

    /**
     * Gets the value of the metadatosExp property.
     * 
     * @return possible object is {@link TipoMetadatos }
     * 
     */
    public TipoMetadatos getMetadatosExp() {
      return metadatosExp;
    }

    /**
     * Sets the value of the metadatosExp property.
     * 
     * @param value allowed object is {@link TipoMetadatos }
     * 
     */
    public void setMetadatosExp(TipoMetadatos value) {
      this.metadatosExp = value;
    }

  }

}
