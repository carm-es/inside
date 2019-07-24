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

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Contiene los datos del envio
 * 
 * <p>
 * Java class for DatosEnvioType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DatosEnvioType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="identificadorEnvio" type="{http://justicia.es/esb/expedienteElectronico/RemisionExpedienteCallback/xsd-schemas/V1}idEnvioType"/>
 *         &lt;element name="codigoEnvio" type="{http://justicia.es/esb/expedienteElectronico/RemisionExpedienteCallback/xsd-schemas/V1}codigoEnvioType" minOccurs="0"/>
 *         &lt;element name="descripcionExpediente" type="{http://justicia.es/esb/expedienteElectronico/RemisionExpedienteCallback/xsd-schemas/V1}descripcionExpedienteType" minOccurs="0"/>
 *         &lt;element name="ordenJurisdiccional" type="{http://justicia.es/esb/expedienteElectronico/RemisionExpedienteCallback/xsd-schemas/V1}ordenJurisdiccionalType" minOccurs="0"/>
 *         &lt;element name="tipoDocumental" type="{http://justicia.es/esb/expedienteElectronico/RemisionExpedienteCallback/xsd-schemas/V1}tipoDocumentalType" minOccurs="0"/>
 *         &lt;element name="valorDocumental" type="{http://justicia.es/esb/expedienteElectronico/RemisionExpedienteCallback/xsd-schemas/V1}valorDocumentalType" minOccurs="0"/>
 *         &lt;element name="Presentador" type="{http://justicia.es/esb/expedienteElectronico/RemisionExpedienteCallback/xsd-schemas/V1}presentadorType" minOccurs="0"/>
 *         &lt;element name="Asunto" type="{http://justicia.es/esb/expedienteElectronico/RemisionExpedienteCallback/xsd-schemas/V1}asuntoType" minOccurs="0"/>
 *         &lt;element name="Procedimiento" type="{http://justicia.es/esb/expedienteElectronico/RemisionExpedienteCallback/xsd-schemas/V1}procedimientoType" minOccurs="0"/>
 *         &lt;element name="Pieza" type="{http://justicia.es/esb/expedienteElectronico/RemisionExpedienteCallback/xsd-schemas/V1}piezaType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatosEnvioType",
    propOrder = {"identificadorEnvio", "codigoEnvio", "descripcionExpediente",
        "ordenJurisdiccional", "tipoDocumental", "valorDocumental", "presentador", "asunto",
        "procedimiento", "pieza"})
@XmlSeeAlso({DatosIdEnvioType.class})
public class DatosEnvioType {

  @XmlElement(required = true)
  protected BigInteger identificadorEnvio;
  protected String codigoEnvio;
  protected String descripcionExpediente;
  protected String ordenJurisdiccional;
  protected String tipoDocumental;
  protected String valorDocumental;
  @XmlElement(name = "Presentador")
  protected PresentadorType presentador;
  @XmlElement(name = "Asunto")
  protected AsuntoType asunto;
  @XmlElement(name = "Procedimiento")
  protected ProcedimientoType procedimiento;
  @XmlElement(name = "Pieza")
  protected PiezaType pieza;

  /**
   * Gets the value of the identificadorEnvio property.
   * 
   * @return possible object is {@link BigInteger }
   * 
   */
  public BigInteger getIdentificadorEnvio() {
    return identificadorEnvio;
  }

  /**
   * Sets the value of the identificadorEnvio property.
   * 
   * @param value allowed object is {@link BigInteger }
   * 
   */
  public void setIdentificadorEnvio(BigInteger value) {
    this.identificadorEnvio = value;
  }

  /**
   * Gets the value of the codigoEnvio property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCodigoEnvio() {
    return codigoEnvio;
  }

  /**
   * Sets the value of the codigoEnvio property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCodigoEnvio(String value) {
    this.codigoEnvio = value;
  }

  /**
   * Gets the value of the descripcionExpediente property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDescripcionExpediente() {
    return descripcionExpediente;
  }

  /**
   * Sets the value of the descripcionExpediente property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDescripcionExpediente(String value) {
    this.descripcionExpediente = value;
  }

  /**
   * Gets the value of the ordenJurisdiccional property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getOrdenJurisdiccional() {
    return ordenJurisdiccional;
  }

  /**
   * Sets the value of the ordenJurisdiccional property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setOrdenJurisdiccional(String value) {
    this.ordenJurisdiccional = value;
  }

  /**
   * Gets the value of the tipoDocumental property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getTipoDocumental() {
    return tipoDocumental;
  }

  /**
   * Sets the value of the tipoDocumental property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setTipoDocumental(String value) {
    this.tipoDocumental = value;
  }

  /**
   * Gets the value of the valorDocumental property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getValorDocumental() {
    return valorDocumental;
  }

  /**
   * Sets the value of the valorDocumental property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setValorDocumental(String value) {
    this.valorDocumental = value;
  }

  /**
   * Gets the value of the presentador property.
   * 
   * @return possible object is {@link PresentadorType }
   * 
   */
  public PresentadorType getPresentador() {
    return presentador;
  }

  /**
   * Sets the value of the presentador property.
   * 
   * @param value allowed object is {@link PresentadorType }
   * 
   */
  public void setPresentador(PresentadorType value) {
    this.presentador = value;
  }

  /**
   * Gets the value of the asunto property.
   * 
   * @return possible object is {@link AsuntoType }
   * 
   */
  public AsuntoType getAsunto() {
    return asunto;
  }

  /**
   * Sets the value of the asunto property.
   * 
   * @param value allowed object is {@link AsuntoType }
   * 
   */
  public void setAsunto(AsuntoType value) {
    this.asunto = value;
  }

  /**
   * Gets the value of the procedimiento property.
   * 
   * @return possible object is {@link ProcedimientoType }
   * 
   */
  public ProcedimientoType getProcedimiento() {
    return procedimiento;
  }

  /**
   * Sets the value of the procedimiento property.
   * 
   * @param value allowed object is {@link ProcedimientoType }
   * 
   */
  public void setProcedimiento(ProcedimientoType value) {
    this.procedimiento = value;
  }

  /**
   * Gets the value of the pieza property.
   * 
   * @return possible object is {@link PiezaType }
   * 
   */
  public PiezaType getPieza() {
    return pieza;
  }

  /**
   * Sets the value of the pieza property.
   * 
   * @param value allowed object is {@link PiezaType }
   * 
   */
  public void setPieza(PiezaType value) {
    this.pieza = value;
  }

}
