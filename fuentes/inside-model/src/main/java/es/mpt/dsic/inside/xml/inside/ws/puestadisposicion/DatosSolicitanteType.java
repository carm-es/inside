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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Esquema que representa los datos de un solicitante
 * 
 * <p>
 * Java class for DatosSolicitanteType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DatosSolicitanteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NIF" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/puestaDisposicion}String9NifType"/>
 *         &lt;element name="DIR3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatosSolicitanteType", propOrder = {"nif", "dir3"})
public class DatosSolicitanteType {

  @XmlElement(name = "NIF", required = true)
  protected String nif;
  @XmlElement(name = "DIR3", required = true)
  protected String dir3;

  /**
   * Gets the value of the nif property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getNIF() {
    return nif;
  }

  /**
   * Sets the value of the nif property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setNIF(String value) {
    this.nif = value;
  }

  /**
   * Gets the value of the dir3 property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDIR3() {
    return dir3;
  }

  /**
   * Sets the value of the dir3 property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDIR3(String value) {
    this.dir3 = value;
  }

}
