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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Clase Java para consultaWsInside complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consultaWsInside">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="subConsulta" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/busqueda}subConsultaWsInside"/>
 *         &lt;element name="metadato" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/busqueda}metadatoBusquedaWsInside"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultaWsInside",
    namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/busqueda",
    propOrder = {"subConsulta", "metadato"})
public class ConsultaWsInside {

  protected SubConsultaWsInside subConsulta;
  protected MetadatoBusquedaWsInside metadato;

  /**
   * Obtiene el valor de la propiedad subConsulta.
   * 
   * @return possible object is {@link SubConsultaWsInside }
   * 
   */
  public SubConsultaWsInside getSubConsulta() {
    return subConsulta;
  }

  /**
   * Define el valor de la propiedad subConsulta.
   * 
   * @param value allowed object is {@link SubConsultaWsInside }
   * 
   */
  public void setSubConsulta(SubConsultaWsInside value) {
    this.subConsulta = value;
  }

  /**
   * Obtiene el valor de la propiedad metadato.
   * 
   * @return possible object is {@link MetadatoBusquedaWsInside }
   * 
   */
  public MetadatoBusquedaWsInside getMetadato() {
    return metadato;
  }

  /**
   * Define el valor de la propiedad metadato.
   * 
   * @param value allowed object is {@link MetadatoBusquedaWsInside }
   * 
   */
  public void setMetadato(MetadatoBusquedaWsInside value) {
    this.metadato = value;
  }

}
