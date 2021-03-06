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


package csvstorage.es.gob.aapp.csvstorage.webservices.document.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import csvstorage.es.gob.aapp.csvstorage.webservices.document.v1.model.ObtenerDocumentoEniRequest;
import csvstorage.es.gob.aapp.csvstorage.webservices.document.v1.model.WSCredential;


/**
 * <p>
 * Clase Java para obtenerDocumentoENI complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="obtenerDocumentoENI">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="credential" type="{urn:es:gob:aapp:csvstorage:webservices:document:model:v1.0}WSCredential"/>
 *         &lt;element name="obtenerDocumentoEniRequest" type="{urn:es:gob:aapp:csvstorage:webservices:document:model:v1.0}obtenerDocumentoEniRequest"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "obtenerDocumentoENI", propOrder = {"credential", "obtenerDocumentoEniRequest"})
public class ObtenerDocumentoENI {

  @XmlElement(required = true)
  protected WSCredential credential;
  @XmlElement(required = true)
  protected ObtenerDocumentoEniRequest obtenerDocumentoEniRequest;

  /**
   * Obtiene el valor de la propiedad credential.
   * 
   * @return possible object is {@link WSCredential }
   * 
   */
  public WSCredential getCredential() {
    return credential;
  }

  /**
   * Define el valor de la propiedad credential.
   * 
   * @param value allowed object is {@link WSCredential }
   * 
   */
  public void setCredential(WSCredential value) {
    this.credential = value;
  }

  /**
   * Obtiene el valor de la propiedad obtenerDocumentoEniRequest.
   * 
   * @return possible object is {@link ObtenerDocumentoEniRequest }
   * 
   */
  public ObtenerDocumentoEniRequest getObtenerDocumentoEniRequest() {
    return obtenerDocumentoEniRequest;
  }

  /**
   * Define el valor de la propiedad obtenerDocumentoEniRequest.
   * 
   * @param value allowed object is {@link ObtenerDocumentoEniRequest }
   * 
   */
  public void setObtenerDocumentoEniRequest(ObtenerDocumentoEniRequest value) {
    this.obtenerDocumentoEniRequest = value;
  }

}
