/*
 * Copyright (C) 2016 MINHAP, Gobierno de España This program is licensed and may be used, modified
 * and redistributed under the terms of the European Public License (EUPL), either version 1.1 or
 * (at your option) any later version as soon as they are approved by the European Commission.
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and more details. You
 * should have received a copy of the EUPL1.1 license along with this program; if not, you may find
 * it at http://joinup.ec.europa.eu/software/page/eupl/licence-eupl
 */


package es.mpt.dsic.inside.remisionnube.client.modelo;

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


/**
 * <p>
 * Clase Java para ExpedienteResultadoBusqueda complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
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
   * Obtiene el valor de la propiedad paginador.
   * 
   * @return possible object is {@link InfoResultadosBusquedaInside }
   * 
   */
  public InfoResultadosBusquedaInside getPaginador() {
    return paginador;
  }

  /**
   * Define el valor de la propiedad paginador.
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
   * Clase Java para anonymous complex type.
   * 
   * <p>
   * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
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
    protected TipoMetadatos2 metadatosExp;

    /**
     * Obtiene el valor de la propiedad identificador.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getIdentificador() {
      return identificador;
    }

    /**
     * Define el valor de la propiedad identificador.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setIdentificador(String value) {
      this.identificador = value;
    }

    /**
     * Obtiene el valor de la propiedad versionActual.
     * 
     * @return possible object is {@link TipoVersionInside }
     * 
     */
    public TipoVersionInside getVersionActual() {
      return versionActual;
    }

    /**
     * Define el valor de la propiedad versionActual.
     * 
     * @param value allowed object is {@link TipoVersionInside }
     * 
     */
    public void setVersionActual(TipoVersionInside value) {
      this.versionActual = value;
    }

    /**
     * Obtiene el valor de la propiedad metadatosExp.
     * 
     * @return possible object is {@link TipoMetadatos2 }
     * 
     */
    public TipoMetadatos2 getMetadatosExp() {
      return metadatosExp;
    }

    /**
     * Define el valor de la propiedad metadatosExp.
     * 
     * @param value allowed object is {@link TipoMetadatos2 }
     * 
     */
    public void setMetadatosExp(TipoMetadatos2 value) {
      this.metadatosExp = value;
    }

  }

}
