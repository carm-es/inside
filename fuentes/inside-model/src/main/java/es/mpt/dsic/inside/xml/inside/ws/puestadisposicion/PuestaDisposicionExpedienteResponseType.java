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


package es.mpt.dsic.inside.xml.inside.ws.puestadisposicion;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import es.mpt.dsic.inside.xml.eni.documento.TipoDocumento;
import es.mpt.dsic.inside.xml.eni.documento.metadatos.TipoMetadatos;
import es.mpt.dsic.inside.xml.eni.expediente.TipoExpediente;


/**
 * Esquema que representa una respuesta con expedienteENI
 * 
 * <p>
 * Java class for PuestaDisposicionExpedienteResponseType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PuestaDisposicionExpedienteResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="expediente" type="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e}TipoExpediente"/>
 *         &lt;element ref="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/metadatos}metadatos" maxOccurs="unbounded"/>
 *         &lt;element name="actaAcceso" type="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e}TipoDocumento"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PuestaDisposicionExpedienteResponseType",
    propOrder = {"expediente", "metadatos", "actaAcceso"})
public class PuestaDisposicionExpedienteResponseType {

  @XmlElement(required = true)
  protected TipoExpediente expediente;
  @XmlElement(
      namespace = "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/metadatos",
      required = true)
  protected List<TipoMetadatos> metadatos;
  @XmlElement(required = true)
  protected TipoDocumento actaAcceso;

  /**
   * Gets the value of the expediente property.
   * 
   * @return possible object is {@link TipoExpediente }
   * 
   */
  public TipoExpediente getExpediente() {
    return expediente;
  }

  /**
   * Sets the value of the expediente property.
   * 
   * @param value allowed object is {@link TipoExpediente }
   * 
   */
  public void setExpediente(TipoExpediente value) {
    this.expediente = value;
  }

  /**
   * Gets the value of the metadatos property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the metadatos property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getMetadatos().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list {@link TipoMetadatos }
   * 
   * 
   */
  public List<TipoMetadatos> getMetadatos() {
    if (metadatos == null) {
      metadatos = new ArrayList<TipoMetadatos>();
    }
    return this.metadatos;
  }

  /**
   * Gets the value of the actaAcceso property.
   * 
   * @return possible object is {@link TipoDocumento }
   * 
   */
  public TipoDocumento getActaAcceso() {
    return actaAcceso;
  }

  /**
   * Sets the value of the actaAcceso property.
   * 
   * @param value allowed object is {@link TipoDocumento }
   * 
   */
  public void setActaAcceso(TipoDocumento value) {
    this.actaAcceso = value;
  }

}
