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


package es.mpt.dsic.inside.service.sia.model.messages.v2_4.enviasia;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Clase Java para ORGANISMORESPONSABLE complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ORGANISMORESPONSABLE">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CODCLASETERRITORIAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DESCLASETERRITORIAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CODCOMUNIDADAUTONOMA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DESCOMUNIDADAUTONOMA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CODAMBITO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DESAMBITO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CODTIPOENTIDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DESTIPOENTIDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CODORGANISMORESPONSABLEN1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DESORGANISMORESPONSABLEN1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CODORGANISMORESPONSABLEN2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DESORGANISMORESPONSABLEN2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ORGANISMORESPONSABLE",
    propOrder = {"codclaseterritorial", "desclaseterritorial", "codcomunidadautonoma",
        "descomunidadautonoma", "codambito", "desambito", "codtipoentidad", "destipoentidad",
        "codorganismoresponsablen1", "desorganismoresponsablen1", "codorganismoresponsablen2",
        "desorganismoresponsablen2"})
public class ORGANISMORESPONSABLE {

  @XmlElement(name = "CODCLASETERRITORIAL")
  protected String codclaseterritorial;
  @XmlElement(name = "DESCLASETERRITORIAL")
  protected String desclaseterritorial;
  @XmlElement(name = "CODCOMUNIDADAUTONOMA")
  protected String codcomunidadautonoma;
  @XmlElement(name = "DESCOMUNIDADAUTONOMA")
  protected String descomunidadautonoma;
  @XmlElement(name = "CODAMBITO")
  protected String codambito;
  @XmlElement(name = "DESAMBITO")
  protected String desambito;
  @XmlElement(name = "CODTIPOENTIDAD")
  protected String codtipoentidad;
  @XmlElement(name = "DESTIPOENTIDAD")
  protected String destipoentidad;
  @XmlElement(name = "CODORGANISMORESPONSABLEN1")
  protected String codorganismoresponsablen1;
  @XmlElement(name = "DESORGANISMORESPONSABLEN1")
  protected String desorganismoresponsablen1;
  @XmlElement(name = "CODORGANISMORESPONSABLEN2")
  protected String codorganismoresponsablen2;
  @XmlElement(name = "DESORGANISMORESPONSABLEN2")
  protected String desorganismoresponsablen2;

  /**
   * Obtiene el valor de la propiedad codclaseterritorial.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCODCLASETERRITORIAL() {
    return codclaseterritorial;
  }

  /**
   * Define el valor de la propiedad codclaseterritorial.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCODCLASETERRITORIAL(String value) {
    this.codclaseterritorial = value;
  }

  /**
   * Obtiene el valor de la propiedad desclaseterritorial.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDESCLASETERRITORIAL() {
    return desclaseterritorial;
  }

  /**
   * Define el valor de la propiedad desclaseterritorial.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDESCLASETERRITORIAL(String value) {
    this.desclaseterritorial = value;
  }

  /**
   * Obtiene el valor de la propiedad codcomunidadautonoma.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCODCOMUNIDADAUTONOMA() {
    return codcomunidadautonoma;
  }

  /**
   * Define el valor de la propiedad codcomunidadautonoma.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCODCOMUNIDADAUTONOMA(String value) {
    this.codcomunidadautonoma = value;
  }

  /**
   * Obtiene el valor de la propiedad descomunidadautonoma.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDESCOMUNIDADAUTONOMA() {
    return descomunidadautonoma;
  }

  /**
   * Define el valor de la propiedad descomunidadautonoma.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDESCOMUNIDADAUTONOMA(String value) {
    this.descomunidadautonoma = value;
  }

  /**
   * Obtiene el valor de la propiedad codambito.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCODAMBITO() {
    return codambito;
  }

  /**
   * Define el valor de la propiedad codambito.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCODAMBITO(String value) {
    this.codambito = value;
  }

  /**
   * Obtiene el valor de la propiedad desambito.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDESAMBITO() {
    return desambito;
  }

  /**
   * Define el valor de la propiedad desambito.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDESAMBITO(String value) {
    this.desambito = value;
  }

  /**
   * Obtiene el valor de la propiedad codtipoentidad.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCODTIPOENTIDAD() {
    return codtipoentidad;
  }

  /**
   * Define el valor de la propiedad codtipoentidad.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCODTIPOENTIDAD(String value) {
    this.codtipoentidad = value;
  }

  /**
   * Obtiene el valor de la propiedad destipoentidad.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDESTIPOENTIDAD() {
    return destipoentidad;
  }

  /**
   * Define el valor de la propiedad destipoentidad.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDESTIPOENTIDAD(String value) {
    this.destipoentidad = value;
  }

  /**
   * Obtiene el valor de la propiedad codorganismoresponsablen1.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCODORGANISMORESPONSABLEN1() {
    return codorganismoresponsablen1;
  }

  /**
   * Define el valor de la propiedad codorganismoresponsablen1.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCODORGANISMORESPONSABLEN1(String value) {
    this.codorganismoresponsablen1 = value;
  }

  /**
   * Obtiene el valor de la propiedad desorganismoresponsablen1.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDESORGANISMORESPONSABLEN1() {
    return desorganismoresponsablen1;
  }

  /**
   * Define el valor de la propiedad desorganismoresponsablen1.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDESORGANISMORESPONSABLEN1(String value) {
    this.desorganismoresponsablen1 = value;
  }

  /**
   * Obtiene el valor de la propiedad codorganismoresponsablen2.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCODORGANISMORESPONSABLEN2() {
    return codorganismoresponsablen2;
  }

  /**
   * Define el valor de la propiedad codorganismoresponsablen2.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCODORGANISMORESPONSABLEN2(String value) {
    this.codorganismoresponsablen2 = value;
  }

  /**
   * Obtiene el valor de la propiedad desorganismoresponsablen2.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDESORGANISMORESPONSABLEN2() {
    return desorganismoresponsablen2;
  }

  /**
   * Define el valor de la propiedad desorganismoresponsablen2.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDESORGANISMORESPONSABLEN2(String value) {
    this.desorganismoresponsablen2 = value;
  }

}
