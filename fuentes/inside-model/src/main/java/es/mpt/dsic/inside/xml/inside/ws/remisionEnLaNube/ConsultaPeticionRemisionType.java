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


package es.mpt.dsic.inside.xml.inside.ws.remisionEnLaNube;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>
 * Java class for ConsultaPeticionRemisionType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConsultaPeticionRemisionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idPeticion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idExpediente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="remitente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fecha" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultaPeticionRemisionType",
    propOrder = {"idPeticion", "idExpediente", "remitente", "fecha"})
public class ConsultaPeticionRemisionType {

  @XmlElement(required = true)
  protected String idPeticion;
  @XmlElement(required = true)
  protected String idExpediente;
  @XmlElement(required = true)
  protected String remitente;
  @XmlElement(required = true)
  @XmlSchemaType(name = "dateTime")
  protected XMLGregorianCalendar fecha;

  /**
   * Gets the value of the idPeticion property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getIdPeticion() {
    return idPeticion;
  }

  /**
   * Sets the value of the idPeticion property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setIdPeticion(String value) {
    this.idPeticion = value;
  }

  /**
   * Gets the value of the idExpediente property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getIdExpediente() {
    return idExpediente;
  }

  /**
   * Sets the value of the idExpediente property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setIdExpediente(String value) {
    this.idExpediente = value;
  }

  /**
   * Gets the value of the remitente property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getRemitente() {
    return remitente;
  }

  /**
   * Sets the value of the remitente property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setRemitente(String value) {
    this.remitente = value;
  }

  /**
   * Gets the value of the fecha property.
   * 
   * @return possible object is {@link XMLGregorianCalendar }
   * 
   */
  public XMLGregorianCalendar getFecha() {
    return fecha;
  }

  /**
   * Sets the value of the fecha property.
   * 
   * @param value allowed object is {@link XMLGregorianCalendar }
   * 
   */
  public void setFecha(XMLGregorianCalendar value) {
    this.fecha = value;
  }

}
