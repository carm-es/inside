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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Clase Java para getIndiceExpedienteResponse complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="getIndiceExpedienteResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="indice" type="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/indice-e}TipoIndice" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getIndiceExpedienteResponse", propOrder = {"indice"})
public class GetIndiceExpedienteResponse {

  @XmlElement(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService")
  protected TipoIndice indice;

  /**
   * Obtiene el valor de la propiedad indice.
   * 
   * @return possible object is {@link TipoIndice }
   * 
   */
  public TipoIndice getIndice() {
    return indice;
  }

  /**
   * Define el valor de la propiedad indice.
   * 
   * @param value allowed object is {@link TipoIndice }
   * 
   */
  public void setIndice(TipoIndice value) {
    this.indice = value;
  }

}
