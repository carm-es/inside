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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>
 * Java class for presentadorType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="presentadorType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="identificadorUsuarioPresentacion" type="{http://justicia.es/esb/expedienteElectronico/RemisionExpedienteCallback/xsd-schemas/V1}identificadorUsuarioPresentacionType" minOccurs="0"/>
 *         &lt;element name="fechaPresentacion" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="formaPresentacion" type="{http://justicia.es/esb/expedienteElectronico/RemisionExpedienteCallback/xsd-schemas/V1}formaPresentacionType" minOccurs="0"/>
 *         &lt;element name="tipoPresentador" type="{http://justicia.es/esb/expedienteElectronico/RemisionExpedienteCallback/xsd-schemas/V1}tipoPresentadorType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "presentadorType", propOrder = {"identificadorUsuarioPresentacion",
    "fechaPresentacion", "formaPresentacion", "tipoPresentador"})
public class PresentadorType {

  protected BigInteger identificadorUsuarioPresentacion;
  @XmlSchemaType(name = "dateTime")
  protected XMLGregorianCalendar fechaPresentacion;
  protected String formaPresentacion;
  protected String tipoPresentador;

  /**
   * Gets the value of the identificadorUsuarioPresentacion property.
   * 
   * @return possible object is {@link BigInteger }
   * 
   */
  public BigInteger getIdentificadorUsuarioPresentacion() {
    return identificadorUsuarioPresentacion;
  }

  /**
   * Sets the value of the identificadorUsuarioPresentacion property.
   * 
   * @param value allowed object is {@link BigInteger }
   * 
   */
  public void setIdentificadorUsuarioPresentacion(BigInteger value) {
    this.identificadorUsuarioPresentacion = value;
  }

  /**
   * Gets the value of the fechaPresentacion property.
   * 
   * @return possible object is {@link XMLGregorianCalendar }
   * 
   */
  public XMLGregorianCalendar getFechaPresentacion() {
    return fechaPresentacion;
  }

  /**
   * Sets the value of the fechaPresentacion property.
   * 
   * @param value allowed object is {@link XMLGregorianCalendar }
   * 
   */
  public void setFechaPresentacion(XMLGregorianCalendar value) {
    this.fechaPresentacion = value;
  }

  /**
   * Gets the value of the formaPresentacion property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getFormaPresentacion() {
    return formaPresentacion;
  }

  /**
   * Sets the value of the formaPresentacion property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setFormaPresentacion(String value) {
    this.formaPresentacion = value;
  }

  /**
   * Gets the value of the tipoPresentador property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getTipoPresentador() {
    return tipoPresentador;
  }

  /**
   * Sets the value of the tipoPresentador property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setTipoPresentador(String value) {
    this.tipoPresentador = value;
  }

}
