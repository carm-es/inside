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


package es.mpt.dsic.inside.xml.inside.ws.remisionExpedienteCallback;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Java class for procedimientoType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="procedimientoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codigoPoblacionProcedimiento" type="{http://justicia.es/esb/expedienteElectronico/RemisionExpedienteCallback/xsd-schemas/V1}codigoPoblacionProcedimientoType" minOccurs="0"/>
 *         &lt;element name="tipoOrganoProcedimiento" type="{http://justicia.es/esb/expedienteElectronico/RemisionExpedienteCallback/xsd-schemas/V1}tipoOrganoProcedimientoType" minOccurs="0"/>
 *         &lt;element name="numeroOrganoProcedimiento" type="{http://justicia.es/esb/expedienteElectronico/RemisionExpedienteCallback/xsd-schemas/V1}numeroOrganoProcedimientoType" minOccurs="0"/>
 *         &lt;element name="ordenProcedimiento" type="{http://justicia.es/esb/expedienteElectronico/RemisionExpedienteCallback/xsd-schemas/V1}ordenProcedimientoType" minOccurs="0"/>
 *         &lt;element name="claseProcedimiento" type="{http://justicia.es/esb/expedienteElectronico/RemisionExpedienteCallback/xsd-schemas/V1}claseProcedimientoType" minOccurs="0"/>
 *         &lt;element name="numeroProcedimiento" type="{http://justicia.es/esb/expedienteElectronico/RemisionExpedienteCallback/xsd-schemas/V1}numeroProcedimientoType" minOccurs="0"/>
 *         &lt;element name="anioProcedimiento" type="{http://justicia.es/esb/expedienteElectronico/RemisionExpedienteCallback/xsd-schemas/V1}anioProcedimientoType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "procedimientoType",
    propOrder = {"codigoPoblacionProcedimiento", "tipoOrganoProcedimiento",
        "numeroOrganoProcedimiento", "ordenProcedimiento", "claseProcedimiento",
        "numeroProcedimiento", "anioProcedimiento"})
public class ProcedimientoType {

  protected String codigoPoblacionProcedimiento;
  protected String tipoOrganoProcedimiento;
  protected String numeroOrganoProcedimiento;
  protected String ordenProcedimiento;
  protected String claseProcedimiento;
  protected String numeroProcedimiento;
  protected String anioProcedimiento;

  /**
   * Gets the value of the codigoPoblacionProcedimiento property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCodigoPoblacionProcedimiento() {
    return codigoPoblacionProcedimiento;
  }

  /**
   * Sets the value of the codigoPoblacionProcedimiento property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCodigoPoblacionProcedimiento(String value) {
    this.codigoPoblacionProcedimiento = value;
  }

  /**
   * Gets the value of the tipoOrganoProcedimiento property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getTipoOrganoProcedimiento() {
    return tipoOrganoProcedimiento;
  }

  /**
   * Sets the value of the tipoOrganoProcedimiento property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setTipoOrganoProcedimiento(String value) {
    this.tipoOrganoProcedimiento = value;
  }

  /**
   * Gets the value of the numeroOrganoProcedimiento property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getNumeroOrganoProcedimiento() {
    return numeroOrganoProcedimiento;
  }

  /**
   * Sets the value of the numeroOrganoProcedimiento property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setNumeroOrganoProcedimiento(String value) {
    this.numeroOrganoProcedimiento = value;
  }

  /**
   * Gets the value of the ordenProcedimiento property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getOrdenProcedimiento() {
    return ordenProcedimiento;
  }

  /**
   * Sets the value of the ordenProcedimiento property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setOrdenProcedimiento(String value) {
    this.ordenProcedimiento = value;
  }

  /**
   * Gets the value of the claseProcedimiento property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getClaseProcedimiento() {
    return claseProcedimiento;
  }

  /**
   * Sets the value of the claseProcedimiento property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setClaseProcedimiento(String value) {
    this.claseProcedimiento = value;
  }

  /**
   * Gets the value of the numeroProcedimiento property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getNumeroProcedimiento() {
    return numeroProcedimiento;
  }

  /**
   * Sets the value of the numeroProcedimiento property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setNumeroProcedimiento(String value) {
    this.numeroProcedimiento = value;
  }

  /**
   * Gets the value of the anioProcedimiento property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getAnioProcedimiento() {
    return anioProcedimiento;
  }

  /**
   * Sets the value of the anioProcedimiento property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setAnioProcedimiento(String value) {
    this.anioProcedimiento = value;
  }

}
