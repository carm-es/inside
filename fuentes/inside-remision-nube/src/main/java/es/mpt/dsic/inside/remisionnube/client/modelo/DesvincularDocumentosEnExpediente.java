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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Clase Java para desvincularDocumentosEnExpediente complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="desvincularDocumentosEnExpediente">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="identificador" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="identificadorDocumento" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="ruta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "desvincularDocumentosEnExpediente",
    propOrder = {"identificador", "identificadorDocumento", "ruta"})
public class DesvincularDocumentosEnExpediente {

  @XmlElement(required = true)
  protected String identificador;
  @XmlElement(required = true)
  protected List<String> identificadorDocumento;
  @XmlElement(required = true)
  protected String ruta;

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
   * Gets the value of the identificadorDocumento property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the identificadorDocumento property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getIdentificadorDocumento().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list {@link String }
   * 
   * 
   */
  public List<String> getIdentificadorDocumento() {
    if (identificadorDocumento == null) {
      identificadorDocumento = new ArrayList<String>();
    }
    return this.identificadorDocumento;
  }

  /**
   * Obtiene el valor de la propiedad ruta.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getRuta() {
    return ruta;
  }

  /**
   * Define el valor de la propiedad ruta.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setRuta(String value) {
    this.ruta = value;
  }

}
