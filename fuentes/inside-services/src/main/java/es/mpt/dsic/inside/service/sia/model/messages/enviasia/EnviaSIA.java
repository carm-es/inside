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


package es.mpt.dsic.inside.service.sia.model.messages.enviasia;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Clase Java para anonymous complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="ERROR" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="DESCERROR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ERROR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ACTUACIONES" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ACTUACION" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="CODIGOACTUACION" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="TIPOSACTUACION" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="TIPOACTUACION" maxOccurs="unbounded">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="CODTIPOACTUACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                                 &lt;element name="DESTIPOACTUACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="DENOMINACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DESCRIPCION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="TITULOSERVICIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DESCRIPCIONSERVICIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ORGANISMORESPONSABLE" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="CODCLASETERRITORIAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                       &lt;element name="DESCLASETERRITORIAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                       &lt;element name="CODCOMUNIDADAUTONOMA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                       &lt;element name="DESCOMUNIDADAUTONOMA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                       &lt;element name="CODAMBITO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                       &lt;element name="DESAMBITO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                       &lt;element name="CODTIPOENTIDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                       &lt;element name="DESTIPOENTIDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                       &lt;element name="CODORGANISMORESPONSABLEN1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                       &lt;element name="DESORGANISMORESPONSABLEN1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                       &lt;element name="CODORGANISMORESPONSABLEN2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                       &lt;element name="DESORGANISMORESPONSABLEN2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="AMBITOSGEOGRAFICOS" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="AMBITOGEOGRAFICO" maxOccurs="unbounded" minOccurs="0">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="CODCCAA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                                 &lt;element name="DESCCAA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                                 &lt;element name="CODPROVINCIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                                 &lt;element name="DESPROVINCIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                                 &lt;element name="CODISLA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                                 &lt;element name="DESISLA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                                 &lt;element name="CODLOCALIDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                                 &lt;element name="DESLOCALIDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="CODTIPOLOGIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DESTIPOLOGIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="AQUIENESVADIRIGIDO" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="AQUIENVADIRIGIDO" maxOccurs="unbounded" minOccurs="0">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="CODAQUIENVADIRIGIDO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                                 &lt;element name="DESAQUIENVADIRIGIDO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="CODNIVELADMINISTRACIONELECTRONICA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DESNIVELADMINISTRACIONELECTRONICA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="URL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="SISTEMASIDENTIFICACION" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="SISTEMAIDENTIFICACION" maxOccurs="unbounded" minOccurs="0">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="CODSISTEMAIDENTIFICACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                                 &lt;element name="DESSISTEMAIDENTIFICACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                                 &lt;element name="OTROSISTEMAIDENTIFICACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="NIVELMAXIMOTRAMITACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PROACTIVO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="CARGASMASIVAS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PUBLICACIONSEDE060" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="VOLTRAMITACIONESACTUAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="VOLTRAMITACIONES" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="VOLTRAMITACION" maxOccurs="unbounded" minOccurs="0">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="ANIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                                 &lt;element name="PERIODO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                                 &lt;element name="VOLTOTAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                                 &lt;element name="VOLELE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                                 &lt;element name="VOLNOELE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                                 &lt;element name="VOLCERTIFICADOELE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                                 &lt;element name="VOLDNIELE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                                 &lt;element name="VOLELEOTRO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="CATALOGACIONES" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="CATALOGACION" maxOccurs="unbounded" minOccurs="0">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="CODCATALOGACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                                 &lt;element name="DESCATALOGACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="CODESTADOACTUACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DESESTADOACTUACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="FECHAULTIMAACTUALIZACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"error", "actuaciones"})
@XmlRootElement(name = "enviaSIA")
public class EnviaSIA {

  @XmlElement(name = "ERROR")
  protected EnviaSIA.ERROR error;
  @XmlElement(name = "ACTUACIONES")
  protected EnviaSIA.ACTUACIONES actuaciones;

  /**
   * Obtiene el valor de la propiedad error.
   * 
   * @return possible object is {@link EnviaSIA.ERROR }
   * 
   */
  public EnviaSIA.ERROR getERROR() {
    return error;
  }

  /**
   * Define el valor de la propiedad error.
   * 
   * @param value allowed object is {@link EnviaSIA.ERROR }
   * 
   */
  public void setERROR(EnviaSIA.ERROR value) {
    this.error = value;
  }

  /**
   * Obtiene el valor de la propiedad actuaciones.
   * 
   * @return possible object is {@link EnviaSIA.ACTUACIONES }
   * 
   */
  public EnviaSIA.ACTUACIONES getACTUACIONES() {
    return actuaciones;
  }

  /**
   * Define el valor de la propiedad actuaciones.
   * 
   * @param value allowed object is {@link EnviaSIA.ACTUACIONES }
   * 
   */
  public void setACTUACIONES(EnviaSIA.ACTUACIONES value) {
    this.actuaciones = value;
  }


  /**
   * <p>
   * Clase Java para anonymous complex type.
   * 
   * <p>
   * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
   * 
   * <pre>
   * &lt;complexType>
   *   &lt;complexContent>
   *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
   *       &lt;sequence>
   *         &lt;element name="ACTUACION" maxOccurs="unbounded" minOccurs="0">
   *           &lt;complexType>
   *             &lt;complexContent>
   *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
   *                 &lt;sequence>
   *                   &lt;element name="CODIGOACTUACION" type="{http://www.w3.org/2001/XMLSchema}string"/>
   *                   &lt;element name="TIPOSACTUACION" minOccurs="0">
   *                     &lt;complexType>
   *                       &lt;complexContent>
   *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
   *                           &lt;sequence>
   *                             &lt;element name="TIPOACTUACION" maxOccurs="unbounded">
   *                               &lt;complexType>
   *                                 &lt;complexContent>
   *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
   *                                     &lt;sequence>
   *                                       &lt;element name="CODTIPOACTUACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                                       &lt;element name="DESTIPOACTUACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                                     &lt;/sequence>
   *                                   &lt;/restriction>
   *                                 &lt;/complexContent>
   *                               &lt;/complexType>
   *                             &lt;/element>
   *                           &lt;/sequence>
   *                         &lt;/restriction>
   *                       &lt;/complexContent>
   *                     &lt;/complexType>
   *                   &lt;/element>
   *                   &lt;element name="DENOMINACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="DESCRIPCION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="TITULOSERVICIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="DESCRIPCIONSERVICIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="ORGANISMORESPONSABLE" minOccurs="0">
   *                     &lt;complexType>
   *                       &lt;complexContent>
   *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
   *                           &lt;sequence>
   *                             &lt;element name="CODCLASETERRITORIAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                             &lt;element name="DESCLASETERRITORIAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                             &lt;element name="CODCOMUNIDADAUTONOMA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                             &lt;element name="DESCOMUNIDADAUTONOMA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                             &lt;element name="CODAMBITO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                             &lt;element name="DESAMBITO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                             &lt;element name="CODTIPOENTIDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                             &lt;element name="DESTIPOENTIDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                             &lt;element name="CODORGANISMORESPONSABLEN1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                             &lt;element name="DESORGANISMORESPONSABLEN1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                             &lt;element name="CODORGANISMORESPONSABLEN2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                             &lt;element name="DESORGANISMORESPONSABLEN2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                           &lt;/sequence>
   *                         &lt;/restriction>
   *                       &lt;/complexContent>
   *                     &lt;/complexType>
   *                   &lt;/element>
   *                   &lt;element name="AMBITOSGEOGRAFICOS" minOccurs="0">
   *                     &lt;complexType>
   *                       &lt;complexContent>
   *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
   *                           &lt;sequence>
   *                             &lt;element name="AMBITOGEOGRAFICO" maxOccurs="unbounded" minOccurs="0">
   *                               &lt;complexType>
   *                                 &lt;complexContent>
   *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
   *                                     &lt;sequence>
   *                                       &lt;element name="CODCCAA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                                       &lt;element name="DESCCAA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                                       &lt;element name="CODPROVINCIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                                       &lt;element name="DESPROVINCIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                                       &lt;element name="CODISLA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                                       &lt;element name="DESISLA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                                       &lt;element name="CODLOCALIDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                                       &lt;element name="DESLOCALIDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                                     &lt;/sequence>
   *                                   &lt;/restriction>
   *                                 &lt;/complexContent>
   *                               &lt;/complexType>
   *                             &lt;/element>
   *                           &lt;/sequence>
   *                         &lt;/restriction>
   *                       &lt;/complexContent>
   *                     &lt;/complexType>
   *                   &lt;/element>
   *                   &lt;element name="CODTIPOLOGIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="DESTIPOLOGIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="AQUIENESVADIRIGIDO" minOccurs="0">
   *                     &lt;complexType>
   *                       &lt;complexContent>
   *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
   *                           &lt;sequence>
   *                             &lt;element name="AQUIENVADIRIGIDO" maxOccurs="unbounded" minOccurs="0">
   *                               &lt;complexType>
   *                                 &lt;complexContent>
   *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
   *                                     &lt;sequence>
   *                                       &lt;element name="CODAQUIENVADIRIGIDO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                                       &lt;element name="DESAQUIENVADIRIGIDO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                                     &lt;/sequence>
   *                                   &lt;/restriction>
   *                                 &lt;/complexContent>
   *                               &lt;/complexType>
   *                             &lt;/element>
   *                           &lt;/sequence>
   *                         &lt;/restriction>
   *                       &lt;/complexContent>
   *                     &lt;/complexType>
   *                   &lt;/element>
   *                   &lt;element name="CODNIVELADMINISTRACIONELECTRONICA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="DESNIVELADMINISTRACIONELECTRONICA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="URL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="SISTEMASIDENTIFICACION" minOccurs="0">
   *                     &lt;complexType>
   *                       &lt;complexContent>
   *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
   *                           &lt;sequence>
   *                             &lt;element name="SISTEMAIDENTIFICACION" maxOccurs="unbounded" minOccurs="0">
   *                               &lt;complexType>
   *                                 &lt;complexContent>
   *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
   *                                     &lt;sequence>
   *                                       &lt;element name="CODSISTEMAIDENTIFICACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                                       &lt;element name="DESSISTEMAIDENTIFICACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                                       &lt;element name="OTROSISTEMAIDENTIFICACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                                     &lt;/sequence>
   *                                   &lt;/restriction>
   *                                 &lt;/complexContent>
   *                               &lt;/complexType>
   *                             &lt;/element>
   *                           &lt;/sequence>
   *                         &lt;/restriction>
   *                       &lt;/complexContent>
   *                     &lt;/complexType>
   *                   &lt;/element>
   *                   &lt;element name="NIVELMAXIMOTRAMITACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="PROACTIVO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="CARGASMASIVAS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="PUBLICACIONSEDE060" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="VOLTRAMITACIONESACTUAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="VOLTRAMITACIONES" minOccurs="0">
   *                     &lt;complexType>
   *                       &lt;complexContent>
   *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
   *                           &lt;sequence>
   *                             &lt;element name="VOLTRAMITACION" maxOccurs="unbounded" minOccurs="0">
   *                               &lt;complexType>
   *                                 &lt;complexContent>
   *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
   *                                     &lt;sequence>
   *                                       &lt;element name="ANIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                                       &lt;element name="PERIODO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                                       &lt;element name="VOLTOTAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                                       &lt;element name="VOLELE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                                       &lt;element name="VOLNOELE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                                       &lt;element name="VOLCERTIFICADOELE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                                       &lt;element name="VOLDNIELE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                                       &lt;element name="VOLELEOTRO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                                     &lt;/sequence>
   *                                   &lt;/restriction>
   *                                 &lt;/complexContent>
   *                               &lt;/complexType>
   *                             &lt;/element>
   *                           &lt;/sequence>
   *                         &lt;/restriction>
   *                       &lt;/complexContent>
   *                     &lt;/complexType>
   *                   &lt;/element>
   *                   &lt;element name="CATALOGACIONES" minOccurs="0">
   *                     &lt;complexType>
   *                       &lt;complexContent>
   *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
   *                           &lt;sequence>
   *                             &lt;element name="CATALOGACION" maxOccurs="unbounded" minOccurs="0">
   *                               &lt;complexType>
   *                                 &lt;complexContent>
   *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
   *                                     &lt;sequence>
   *                                       &lt;element name="CODCATALOGACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                                       &lt;element name="DESCATALOGACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                                     &lt;/sequence>
   *                                   &lt;/restriction>
   *                                 &lt;/complexContent>
   *                               &lt;/complexType>
   *                             &lt;/element>
   *                           &lt;/sequence>
   *                         &lt;/restriction>
   *                       &lt;/complexContent>
   *                     &lt;/complexType>
   *                   &lt;/element>
   *                   &lt;element name="CODESTADOACTUACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="DESESTADOACTUACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="FECHAULTIMAACTUALIZACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                 &lt;/sequence>
   *               &lt;/restriction>
   *             &lt;/complexContent>
   *           &lt;/complexType>
   *         &lt;/element>
   *       &lt;/sequence>
   *     &lt;/restriction>
   *   &lt;/complexContent>
   * &lt;/complexType>
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = {"actuacion"})
  public static class ACTUACIONES {

    @XmlElement(name = "ACTUACION")
    protected List<EnviaSIA.ACTUACIONES.ACTUACION> actuacion;

    /**
     * Gets the value of the actuacion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any
     * modification you make to the returned list will be present inside the JAXB object. This is
     * why there is not a <CODE>set</CODE> method for the actuacion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getACTUACION().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EnviaSIA.ACTUACIONES.ACTUACION }
     * 
     * 
     */
    public List<EnviaSIA.ACTUACIONES.ACTUACION> getACTUACION() {
      if (actuacion == null) {
        actuacion = new ArrayList<EnviaSIA.ACTUACIONES.ACTUACION>();
      }
      return this.actuacion;
    }


    /**
     * <p>
     * Clase Java para anonymous complex type.
     * 
     * <p>
     * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta
     * clase.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="CODIGOACTUACION" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="TIPOSACTUACION" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="TIPOACTUACION" maxOccurs="unbounded">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="CODTIPOACTUACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="DESTIPOACTUACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="DENOMINACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="DESCRIPCION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="TITULOSERVICIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="DESCRIPCIONSERVICIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="ORGANISMORESPONSABLE" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="CODCLASETERRITORIAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DESCLASETERRITORIAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="CODCOMUNIDADAUTONOMA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DESCOMUNIDADAUTONOMA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="CODAMBITO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DESAMBITO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="CODTIPOENTIDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DESTIPOENTIDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="CODORGANISMORESPONSABLEN1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DESORGANISMORESPONSABLEN1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="CODORGANISMORESPONSABLEN2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DESORGANISMORESPONSABLEN2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="AMBITOSGEOGRAFICOS" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="AMBITOGEOGRAFICO" maxOccurs="unbounded" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="CODCCAA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="DESCCAA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="CODPROVINCIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="DESPROVINCIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="CODISLA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="DESISLA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="CODLOCALIDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="DESLOCALIDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="CODTIPOLOGIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="DESTIPOLOGIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="AQUIENESVADIRIGIDO" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="AQUIENVADIRIGIDO" maxOccurs="unbounded" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="CODAQUIENVADIRIGIDO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="DESAQUIENVADIRIGIDO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="CODNIVELADMINISTRACIONELECTRONICA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="DESNIVELADMINISTRACIONELECTRONICA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="URL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="SISTEMASIDENTIFICACION" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="SISTEMAIDENTIFICACION" maxOccurs="unbounded" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="CODSISTEMAIDENTIFICACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="DESSISTEMAIDENTIFICACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="OTROSISTEMAIDENTIFICACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="NIVELMAXIMOTRAMITACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="PROACTIVO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="CARGASMASIVAS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="PUBLICACIONSEDE060" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="VOLTRAMITACIONESACTUAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="VOLTRAMITACIONES" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="VOLTRAMITACION" maxOccurs="unbounded" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="ANIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="PERIODO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="VOLTOTAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="VOLELE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="VOLNOELE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="VOLCERTIFICADOELE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="VOLDNIELE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="VOLELEOTRO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="CATALOGACIONES" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="CATALOGACION" maxOccurs="unbounded" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="CODCATALOGACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="DESCATALOGACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="CODESTADOACTUACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="DESESTADOACTUACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="FECHAULTIMAACTUALIZACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "",
        propOrder = {"codigoactuacion", "tiposactuacion", "denominacion", "descripcion",
            "tituloservicio", "descripcionservicio", "organismoresponsable", "ambitosgeograficos",
            "codtipologia", "destipologia", "aquienesvadirigido",
            "codniveladministracionelectronica", "desniveladministracionelectronica", "url",
            "sistemasidentificacion", "nivelmaximotramitacion", "proactivo", "cargasmasivas",
            "publicacionsede060", "voltramitacionesactual", "voltramitaciones", "catalogaciones",
            "codestadoactuacion", "desestadoactuacion", "fechaultimaactualizacion"})
    public static class ACTUACION {

      @XmlElement(name = "CODIGOACTUACION", required = true)
      protected String codigoactuacion;
      @XmlElement(name = "TIPOSACTUACION")
      protected EnviaSIA.ACTUACIONES.ACTUACION.TIPOSACTUACION tiposactuacion;
      @XmlElement(name = "DENOMINACION")
      protected String denominacion;
      @XmlElement(name = "DESCRIPCION")
      protected String descripcion;
      @XmlElement(name = "TITULOSERVICIO")
      protected String tituloservicio;
      @XmlElement(name = "DESCRIPCIONSERVICIO")
      protected String descripcionservicio;
      @XmlElement(name = "ORGANISMORESPONSABLE")
      protected EnviaSIA.ACTUACIONES.ACTUACION.ORGANISMORESPONSABLE organismoresponsable;
      @XmlElement(name = "AMBITOSGEOGRAFICOS")
      protected EnviaSIA.ACTUACIONES.ACTUACION.AMBITOSGEOGRAFICOS ambitosgeograficos;
      @XmlElement(name = "CODTIPOLOGIA")
      protected String codtipologia;
      @XmlElement(name = "DESTIPOLOGIA")
      protected String destipologia;
      @XmlElement(name = "AQUIENESVADIRIGIDO")
      protected EnviaSIA.ACTUACIONES.ACTUACION.AQUIENESVADIRIGIDO aquienesvadirigido;
      @XmlElement(name = "CODNIVELADMINISTRACIONELECTRONICA")
      protected String codniveladministracionelectronica;
      @XmlElement(name = "DESNIVELADMINISTRACIONELECTRONICA")
      protected String desniveladministracionelectronica;
      @XmlElement(name = "URL")
      protected String url;
      @XmlElement(name = "SISTEMASIDENTIFICACION")
      protected EnviaSIA.ACTUACIONES.ACTUACION.SISTEMASIDENTIFICACION sistemasidentificacion;
      @XmlElement(name = "NIVELMAXIMOTRAMITACION")
      protected String nivelmaximotramitacion;
      @XmlElement(name = "PROACTIVO")
      protected String proactivo;
      @XmlElement(name = "CARGASMASIVAS")
      protected String cargasmasivas;
      @XmlElement(name = "PUBLICACIONSEDE060")
      protected String publicacionsede060;
      @XmlElement(name = "VOLTRAMITACIONESACTUAL")
      protected String voltramitacionesactual;
      @XmlElement(name = "VOLTRAMITACIONES")
      protected EnviaSIA.ACTUACIONES.ACTUACION.VOLTRAMITACIONES voltramitaciones;
      @XmlElement(name = "CATALOGACIONES")
      protected EnviaSIA.ACTUACIONES.ACTUACION.CATALOGACIONES catalogaciones;
      @XmlElement(name = "CODESTADOACTUACION")
      protected String codestadoactuacion;
      @XmlElement(name = "DESESTADOACTUACION")
      protected String desestadoactuacion;
      @XmlElement(name = "FECHAULTIMAACTUALIZACION")
      protected String fechaultimaactualizacion;

      /**
       * Obtiene el valor de la propiedad codigoactuacion.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getCODIGOACTUACION() {
        return codigoactuacion;
      }

      /**
       * Define el valor de la propiedad codigoactuacion.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setCODIGOACTUACION(String value) {
        this.codigoactuacion = value;
      }

      /**
       * Obtiene el valor de la propiedad tiposactuacion.
       * 
       * @return possible object is {@link EnviaSIA.ACTUACIONES.ACTUACION.TIPOSACTUACION }
       * 
       */
      public EnviaSIA.ACTUACIONES.ACTUACION.TIPOSACTUACION getTIPOSACTUACION() {
        return tiposactuacion;
      }

      /**
       * Define el valor de la propiedad tiposactuacion.
       * 
       * @param value allowed object is {@link EnviaSIA.ACTUACIONES.ACTUACION.TIPOSACTUACION }
       * 
       */
      public void setTIPOSACTUACION(EnviaSIA.ACTUACIONES.ACTUACION.TIPOSACTUACION value) {
        this.tiposactuacion = value;
      }

      /**
       * Obtiene el valor de la propiedad denominacion.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getDENOMINACION() {
        return denominacion;
      }

      /**
       * Define el valor de la propiedad denominacion.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setDENOMINACION(String value) {
        this.denominacion = value;
      }

      /**
       * Obtiene el valor de la propiedad descripcion.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getDESCRIPCION() {
        return descripcion;
      }

      /**
       * Define el valor de la propiedad descripcion.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setDESCRIPCION(String value) {
        this.descripcion = value;
      }

      /**
       * Obtiene el valor de la propiedad tituloservicio.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getTITULOSERVICIO() {
        return tituloservicio;
      }

      /**
       * Define el valor de la propiedad tituloservicio.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setTITULOSERVICIO(String value) {
        this.tituloservicio = value;
      }

      /**
       * Obtiene el valor de la propiedad descripcionservicio.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getDESCRIPCIONSERVICIO() {
        return descripcionservicio;
      }

      /**
       * Define el valor de la propiedad descripcionservicio.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setDESCRIPCIONSERVICIO(String value) {
        this.descripcionservicio = value;
      }

      /**
       * Obtiene el valor de la propiedad organismoresponsable.
       * 
       * @return possible object is {@link EnviaSIA.ACTUACIONES.ACTUACION.ORGANISMORESPONSABLE }
       * 
       */
      public EnviaSIA.ACTUACIONES.ACTUACION.ORGANISMORESPONSABLE getORGANISMORESPONSABLE() {
        return organismoresponsable;
      }

      /**
       * Define el valor de la propiedad organismoresponsable.
       * 
       * @param value allowed object is {@link EnviaSIA.ACTUACIONES.ACTUACION.ORGANISMORESPONSABLE }
       * 
       */
      public void setORGANISMORESPONSABLE(
          EnviaSIA.ACTUACIONES.ACTUACION.ORGANISMORESPONSABLE value) {
        this.organismoresponsable = value;
      }

      /**
       * Obtiene el valor de la propiedad ambitosgeograficos.
       * 
       * @return possible object is {@link EnviaSIA.ACTUACIONES.ACTUACION.AMBITOSGEOGRAFICOS }
       * 
       */
      public EnviaSIA.ACTUACIONES.ACTUACION.AMBITOSGEOGRAFICOS getAMBITOSGEOGRAFICOS() {
        return ambitosgeograficos;
      }

      /**
       * Define el valor de la propiedad ambitosgeograficos.
       * 
       * @param value allowed object is {@link EnviaSIA.ACTUACIONES.ACTUACION.AMBITOSGEOGRAFICOS }
       * 
       */
      public void setAMBITOSGEOGRAFICOS(EnviaSIA.ACTUACIONES.ACTUACION.AMBITOSGEOGRAFICOS value) {
        this.ambitosgeograficos = value;
      }

      /**
       * Obtiene el valor de la propiedad codtipologia.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getCODTIPOLOGIA() {
        return codtipologia;
      }

      /**
       * Define el valor de la propiedad codtipologia.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setCODTIPOLOGIA(String value) {
        this.codtipologia = value;
      }

      /**
       * Obtiene el valor de la propiedad destipologia.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getDESTIPOLOGIA() {
        return destipologia;
      }

      /**
       * Define el valor de la propiedad destipologia.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setDESTIPOLOGIA(String value) {
        this.destipologia = value;
      }

      /**
       * Obtiene el valor de la propiedad aquienesvadirigido.
       * 
       * @return possible object is {@link EnviaSIA.ACTUACIONES.ACTUACION.AQUIENESVADIRIGIDO }
       * 
       */
      public EnviaSIA.ACTUACIONES.ACTUACION.AQUIENESVADIRIGIDO getAQUIENESVADIRIGIDO() {
        return aquienesvadirigido;
      }

      /**
       * Define el valor de la propiedad aquienesvadirigido.
       * 
       * @param value allowed object is {@link EnviaSIA.ACTUACIONES.ACTUACION.AQUIENESVADIRIGIDO }
       * 
       */
      public void setAQUIENESVADIRIGIDO(EnviaSIA.ACTUACIONES.ACTUACION.AQUIENESVADIRIGIDO value) {
        this.aquienesvadirigido = value;
      }

      /**
       * Obtiene el valor de la propiedad codniveladministracionelectronica.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getCODNIVELADMINISTRACIONELECTRONICA() {
        return codniveladministracionelectronica;
      }

      /**
       * Define el valor de la propiedad codniveladministracionelectronica.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setCODNIVELADMINISTRACIONELECTRONICA(String value) {
        this.codniveladministracionelectronica = value;
      }

      /**
       * Obtiene el valor de la propiedad desniveladministracionelectronica.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getDESNIVELADMINISTRACIONELECTRONICA() {
        return desniveladministracionelectronica;
      }

      /**
       * Define el valor de la propiedad desniveladministracionelectronica.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setDESNIVELADMINISTRACIONELECTRONICA(String value) {
        this.desniveladministracionelectronica = value;
      }

      /**
       * Obtiene el valor de la propiedad url.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getURL() {
        return url;
      }

      /**
       * Define el valor de la propiedad url.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setURL(String value) {
        this.url = value;
      }

      /**
       * Obtiene el valor de la propiedad sistemasidentificacion.
       * 
       * @return possible object is {@link EnviaSIA.ACTUACIONES.ACTUACION.SISTEMASIDENTIFICACION }
       * 
       */
      public EnviaSIA.ACTUACIONES.ACTUACION.SISTEMASIDENTIFICACION getSISTEMASIDENTIFICACION() {
        return sistemasidentificacion;
      }

      /**
       * Define el valor de la propiedad sistemasidentificacion.
       * 
       * @param value allowed object is
       *        {@link EnviaSIA.ACTUACIONES.ACTUACION.SISTEMASIDENTIFICACION }
       * 
       */
      public void setSISTEMASIDENTIFICACION(
          EnviaSIA.ACTUACIONES.ACTUACION.SISTEMASIDENTIFICACION value) {
        this.sistemasidentificacion = value;
      }

      /**
       * Obtiene el valor de la propiedad nivelmaximotramitacion.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getNIVELMAXIMOTRAMITACION() {
        return nivelmaximotramitacion;
      }

      /**
       * Define el valor de la propiedad nivelmaximotramitacion.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setNIVELMAXIMOTRAMITACION(String value) {
        this.nivelmaximotramitacion = value;
      }

      /**
       * Obtiene el valor de la propiedad proactivo.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getPROACTIVO() {
        return proactivo;
      }

      /**
       * Define el valor de la propiedad proactivo.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setPROACTIVO(String value) {
        this.proactivo = value;
      }

      /**
       * Obtiene el valor de la propiedad cargasmasivas.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getCARGASMASIVAS() {
        return cargasmasivas;
      }

      /**
       * Define el valor de la propiedad cargasmasivas.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setCARGASMASIVAS(String value) {
        this.cargasmasivas = value;
      }

      /**
       * Obtiene el valor de la propiedad publicacionsede060.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getPUBLICACIONSEDE060() {
        return publicacionsede060;
      }

      /**
       * Define el valor de la propiedad publicacionsede060.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setPUBLICACIONSEDE060(String value) {
        this.publicacionsede060 = value;
      }

      /**
       * Obtiene el valor de la propiedad voltramitacionesactual.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getVOLTRAMITACIONESACTUAL() {
        return voltramitacionesactual;
      }

      /**
       * Define el valor de la propiedad voltramitacionesactual.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setVOLTRAMITACIONESACTUAL(String value) {
        this.voltramitacionesactual = value;
      }

      /**
       * Obtiene el valor de la propiedad voltramitaciones.
       * 
       * @return possible object is {@link EnviaSIA.ACTUACIONES.ACTUACION.VOLTRAMITACIONES }
       * 
       */
      public EnviaSIA.ACTUACIONES.ACTUACION.VOLTRAMITACIONES getVOLTRAMITACIONES() {
        return voltramitaciones;
      }

      /**
       * Define el valor de la propiedad voltramitaciones.
       * 
       * @param value allowed object is {@link EnviaSIA.ACTUACIONES.ACTUACION.VOLTRAMITACIONES }
       * 
       */
      public void setVOLTRAMITACIONES(EnviaSIA.ACTUACIONES.ACTUACION.VOLTRAMITACIONES value) {
        this.voltramitaciones = value;
      }

      /**
       * Obtiene el valor de la propiedad catalogaciones.
       * 
       * @return possible object is {@link EnviaSIA.ACTUACIONES.ACTUACION.CATALOGACIONES }
       * 
       */
      public EnviaSIA.ACTUACIONES.ACTUACION.CATALOGACIONES getCATALOGACIONES() {
        return catalogaciones;
      }

      /**
       * Define el valor de la propiedad catalogaciones.
       * 
       * @param value allowed object is {@link EnviaSIA.ACTUACIONES.ACTUACION.CATALOGACIONES }
       * 
       */
      public void setCATALOGACIONES(EnviaSIA.ACTUACIONES.ACTUACION.CATALOGACIONES value) {
        this.catalogaciones = value;
      }

      /**
       * Obtiene el valor de la propiedad codestadoactuacion.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getCODESTADOACTUACION() {
        return codestadoactuacion;
      }

      /**
       * Define el valor de la propiedad codestadoactuacion.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setCODESTADOACTUACION(String value) {
        this.codestadoactuacion = value;
      }

      /**
       * Obtiene el valor de la propiedad desestadoactuacion.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getDESESTADOACTUACION() {
        return desestadoactuacion;
      }

      /**
       * Define el valor de la propiedad desestadoactuacion.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setDESESTADOACTUACION(String value) {
        this.desestadoactuacion = value;
      }

      /**
       * Obtiene el valor de la propiedad fechaultimaactualizacion.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getFECHAULTIMAACTUALIZACION() {
        return fechaultimaactualizacion;
      }

      /**
       * Define el valor de la propiedad fechaultimaactualizacion.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setFECHAULTIMAACTUALIZACION(String value) {
        this.fechaultimaactualizacion = value;
      }


      /**
       * <p>
       * Clase Java para anonymous complex type.
       * 
       * <p>
       * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta
       * clase.
       * 
       * <pre>
       * &lt;complexType>
       *   &lt;complexContent>
       *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
       *       &lt;sequence>
       *         &lt;element name="AMBITOGEOGRAFICO" maxOccurs="unbounded" minOccurs="0">
       *           &lt;complexType>
       *             &lt;complexContent>
       *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
       *                 &lt;sequence>
       *                   &lt;element name="CODCCAA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
       *                   &lt;element name="DESCCAA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
       *                   &lt;element name="CODPROVINCIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
       *                   &lt;element name="DESPROVINCIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
       *                   &lt;element name="CODISLA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
       *                   &lt;element name="DESISLA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
       *                   &lt;element name="CODLOCALIDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
       *                   &lt;element name="DESLOCALIDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
       *                 &lt;/sequence>
       *               &lt;/restriction>
       *             &lt;/complexContent>
       *           &lt;/complexType>
       *         &lt;/element>
       *       &lt;/sequence>
       *     &lt;/restriction>
       *   &lt;/complexContent>
       * &lt;/complexType>
       * </pre>
       * 
       * 
       */
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name = "", propOrder = {"ambitogeografico"})
      public static class AMBITOSGEOGRAFICOS {

        @XmlElement(name = "AMBITOGEOGRAFICO")
        protected List<EnviaSIA.ACTUACIONES.ACTUACION.AMBITOSGEOGRAFICOS.AMBITOGEOGRAFICO> ambitogeografico;

        /**
         * Gets the value of the ambitogeografico property.
         * 
         * <p>
         * This accessor method returns a reference to the live list, not a snapshot. Therefore any
         * modification you make to the returned list will be present inside the JAXB object. This
         * is why there is not a <CODE>set</CODE> method for the ambitogeografico property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * 
         * <pre>
         * getAMBITOGEOGRAFICO().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link EnviaSIA.ACTUACIONES.ACTUACION.AMBITOSGEOGRAFICOS.AMBITOGEOGRAFICO }
         * 
         * 
         */
        public List<EnviaSIA.ACTUACIONES.ACTUACION.AMBITOSGEOGRAFICOS.AMBITOGEOGRAFICO> getAMBITOGEOGRAFICO() {
          if (ambitogeografico == null) {
            ambitogeografico =
                new ArrayList<EnviaSIA.ACTUACIONES.ACTUACION.AMBITOSGEOGRAFICOS.AMBITOGEOGRAFICO>();
          }
          return this.ambitogeografico;
        }


        /**
         * <p>
         * Clase Java para anonymous complex type.
         * 
         * <p>
         * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta
         * clase.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="CODCCAA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DESCCAA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="CODPROVINCIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DESPROVINCIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="CODISLA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DESISLA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="CODLOCALIDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DESLOCALIDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {"codccaa", "desccaa", "codprovincia", "desprovincia",
            "codisla", "desisla", "codlocalidad", "deslocalidad"})
        public static class AMBITOGEOGRAFICO {

          @XmlElement(name = "CODCCAA")
          protected String codccaa;
          @XmlElement(name = "DESCCAA")
          protected String desccaa;
          @XmlElement(name = "CODPROVINCIA")
          protected String codprovincia;
          @XmlElement(name = "DESPROVINCIA")
          protected String desprovincia;
          @XmlElement(name = "CODISLA")
          protected String codisla;
          @XmlElement(name = "DESISLA")
          protected String desisla;
          @XmlElement(name = "CODLOCALIDAD")
          protected String codlocalidad;
          @XmlElement(name = "DESLOCALIDAD")
          protected String deslocalidad;

          /**
           * Obtiene el valor de la propiedad codccaa.
           * 
           * @return possible object is {@link String }
           * 
           */
          public String getCODCCAA() {
            return codccaa;
          }

          /**
           * Define el valor de la propiedad codccaa.
           * 
           * @param value allowed object is {@link String }
           * 
           */
          public void setCODCCAA(String value) {
            this.codccaa = value;
          }

          /**
           * Obtiene el valor de la propiedad desccaa.
           * 
           * @return possible object is {@link String }
           * 
           */
          public String getDESCCAA() {
            return desccaa;
          }

          /**
           * Define el valor de la propiedad desccaa.
           * 
           * @param value allowed object is {@link String }
           * 
           */
          public void setDESCCAA(String value) {
            this.desccaa = value;
          }

          /**
           * Obtiene el valor de la propiedad codprovincia.
           * 
           * @return possible object is {@link String }
           * 
           */
          public String getCODPROVINCIA() {
            return codprovincia;
          }

          /**
           * Define el valor de la propiedad codprovincia.
           * 
           * @param value allowed object is {@link String }
           * 
           */
          public void setCODPROVINCIA(String value) {
            this.codprovincia = value;
          }

          /**
           * Obtiene el valor de la propiedad desprovincia.
           * 
           * @return possible object is {@link String }
           * 
           */
          public String getDESPROVINCIA() {
            return desprovincia;
          }

          /**
           * Define el valor de la propiedad desprovincia.
           * 
           * @param value allowed object is {@link String }
           * 
           */
          public void setDESPROVINCIA(String value) {
            this.desprovincia = value;
          }

          /**
           * Obtiene el valor de la propiedad codisla.
           * 
           * @return possible object is {@link String }
           * 
           */
          public String getCODISLA() {
            return codisla;
          }

          /**
           * Define el valor de la propiedad codisla.
           * 
           * @param value allowed object is {@link String }
           * 
           */
          public void setCODISLA(String value) {
            this.codisla = value;
          }

          /**
           * Obtiene el valor de la propiedad desisla.
           * 
           * @return possible object is {@link String }
           * 
           */
          public String getDESISLA() {
            return desisla;
          }

          /**
           * Define el valor de la propiedad desisla.
           * 
           * @param value allowed object is {@link String }
           * 
           */
          public void setDESISLA(String value) {
            this.desisla = value;
          }

          /**
           * Obtiene el valor de la propiedad codlocalidad.
           * 
           * @return possible object is {@link String }
           * 
           */
          public String getCODLOCALIDAD() {
            return codlocalidad;
          }

          /**
           * Define el valor de la propiedad codlocalidad.
           * 
           * @param value allowed object is {@link String }
           * 
           */
          public void setCODLOCALIDAD(String value) {
            this.codlocalidad = value;
          }

          /**
           * Obtiene el valor de la propiedad deslocalidad.
           * 
           * @return possible object is {@link String }
           * 
           */
          public String getDESLOCALIDAD() {
            return deslocalidad;
          }

          /**
           * Define el valor de la propiedad deslocalidad.
           * 
           * @param value allowed object is {@link String }
           * 
           */
          public void setDESLOCALIDAD(String value) {
            this.deslocalidad = value;
          }

        }

      }


      /**
       * <p>
       * Clase Java para anonymous complex type.
       * 
       * <p>
       * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta
       * clase.
       * 
       * <pre>
       * &lt;complexType>
       *   &lt;complexContent>
       *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
       *       &lt;sequence>
       *         &lt;element name="AQUIENVADIRIGIDO" maxOccurs="unbounded" minOccurs="0">
       *           &lt;complexType>
       *             &lt;complexContent>
       *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
       *                 &lt;sequence>
       *                   &lt;element name="CODAQUIENVADIRIGIDO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
       *                   &lt;element name="DESAQUIENVADIRIGIDO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
       *                 &lt;/sequence>
       *               &lt;/restriction>
       *             &lt;/complexContent>
       *           &lt;/complexType>
       *         &lt;/element>
       *       &lt;/sequence>
       *     &lt;/restriction>
       *   &lt;/complexContent>
       * &lt;/complexType>
       * </pre>
       * 
       * 
       */
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name = "", propOrder = {"aquienvadirigido"})
      public static class AQUIENESVADIRIGIDO {

        @XmlElement(name = "AQUIENVADIRIGIDO")
        protected List<EnviaSIA.ACTUACIONES.ACTUACION.AQUIENESVADIRIGIDO.AQUIENVADIRIGIDO> aquienvadirigido;

        /**
         * Gets the value of the aquienvadirigido property.
         * 
         * <p>
         * This accessor method returns a reference to the live list, not a snapshot. Therefore any
         * modification you make to the returned list will be present inside the JAXB object. This
         * is why there is not a <CODE>set</CODE> method for the aquienvadirigido property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * 
         * <pre>
         * getAQUIENVADIRIGIDO().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link EnviaSIA.ACTUACIONES.ACTUACION.AQUIENESVADIRIGIDO.AQUIENVADIRIGIDO }
         * 
         * 
         */
        public List<EnviaSIA.ACTUACIONES.ACTUACION.AQUIENESVADIRIGIDO.AQUIENVADIRIGIDO> getAQUIENVADIRIGIDO() {
          if (aquienvadirigido == null) {
            aquienvadirigido =
                new ArrayList<EnviaSIA.ACTUACIONES.ACTUACION.AQUIENESVADIRIGIDO.AQUIENVADIRIGIDO>();
          }
          return this.aquienvadirigido;
        }


        /**
         * <p>
         * Clase Java para anonymous complex type.
         * 
         * <p>
         * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta
         * clase.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="CODAQUIENVADIRIGIDO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DESAQUIENVADIRIGIDO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {"codaquienvadirigido", "desaquienvadirigido"})
        public static class AQUIENVADIRIGIDO {

          @XmlElement(name = "CODAQUIENVADIRIGIDO")
          protected String codaquienvadirigido;
          @XmlElement(name = "DESAQUIENVADIRIGIDO")
          protected String desaquienvadirigido;

          /**
           * Obtiene el valor de la propiedad codaquienvadirigido.
           * 
           * @return possible object is {@link String }
           * 
           */
          public String getCODAQUIENVADIRIGIDO() {
            return codaquienvadirigido;
          }

          /**
           * Define el valor de la propiedad codaquienvadirigido.
           * 
           * @param value allowed object is {@link String }
           * 
           */
          public void setCODAQUIENVADIRIGIDO(String value) {
            this.codaquienvadirigido = value;
          }

          /**
           * Obtiene el valor de la propiedad desaquienvadirigido.
           * 
           * @return possible object is {@link String }
           * 
           */
          public String getDESAQUIENVADIRIGIDO() {
            return desaquienvadirigido;
          }

          /**
           * Define el valor de la propiedad desaquienvadirigido.
           * 
           * @param value allowed object is {@link String }
           * 
           */
          public void setDESAQUIENVADIRIGIDO(String value) {
            this.desaquienvadirigido = value;
          }

        }

      }


      /**
       * <p>
       * Clase Java para anonymous complex type.
       * 
       * <p>
       * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta
       * clase.
       * 
       * <pre>
       * &lt;complexType>
       *   &lt;complexContent>
       *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
       *       &lt;sequence>
       *         &lt;element name="CATALOGACION" maxOccurs="unbounded" minOccurs="0">
       *           &lt;complexType>
       *             &lt;complexContent>
       *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
       *                 &lt;sequence>
       *                   &lt;element name="CODCATALOGACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
       *                   &lt;element name="DESCATALOGACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
       *                 &lt;/sequence>
       *               &lt;/restriction>
       *             &lt;/complexContent>
       *           &lt;/complexType>
       *         &lt;/element>
       *       &lt;/sequence>
       *     &lt;/restriction>
       *   &lt;/complexContent>
       * &lt;/complexType>
       * </pre>
       * 
       * 
       */
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name = "", propOrder = {"catalogacion"})
      public static class CATALOGACIONES {

        @XmlElement(name = "CATALOGACION")
        protected List<EnviaSIA.ACTUACIONES.ACTUACION.CATALOGACIONES.CATALOGACION> catalogacion;

        /**
         * Gets the value of the catalogacion property.
         * 
         * <p>
         * This accessor method returns a reference to the live list, not a snapshot. Therefore any
         * modification you make to the returned list will be present inside the JAXB object. This
         * is why there is not a <CODE>set</CODE> method for the catalogacion property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * 
         * <pre>
         * getCATALOGACION().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link EnviaSIA.ACTUACIONES.ACTUACION.CATALOGACIONES.CATALOGACION }
         * 
         * 
         */
        public List<EnviaSIA.ACTUACIONES.ACTUACION.CATALOGACIONES.CATALOGACION> getCATALOGACION() {
          if (catalogacion == null) {
            catalogacion =
                new ArrayList<EnviaSIA.ACTUACIONES.ACTUACION.CATALOGACIONES.CATALOGACION>();
          }
          return this.catalogacion;
        }


        /**
         * <p>
         * Clase Java para anonymous complex type.
         * 
         * <p>
         * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta
         * clase.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="CODCATALOGACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DESCATALOGACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {"codcatalogacion", "descatalogacion"})
        public static class CATALOGACION {

          @XmlElement(name = "CODCATALOGACION")
          protected String codcatalogacion;
          @XmlElement(name = "DESCATALOGACION")
          protected String descatalogacion;

          /**
           * Obtiene el valor de la propiedad codcatalogacion.
           * 
           * @return possible object is {@link String }
           * 
           */
          public String getCODCATALOGACION() {
            return codcatalogacion;
          }

          /**
           * Define el valor de la propiedad codcatalogacion.
           * 
           * @param value allowed object is {@link String }
           * 
           */
          public void setCODCATALOGACION(String value) {
            this.codcatalogacion = value;
          }

          /**
           * Obtiene el valor de la propiedad descatalogacion.
           * 
           * @return possible object is {@link String }
           * 
           */
          public String getDESCATALOGACION() {
            return descatalogacion;
          }

          /**
           * Define el valor de la propiedad descatalogacion.
           * 
           * @param value allowed object is {@link String }
           * 
           */
          public void setDESCATALOGACION(String value) {
            this.descatalogacion = value;
          }

        }

      }


      /**
       * <p>
       * Clase Java para anonymous complex type.
       * 
       * <p>
       * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta
       * clase.
       * 
       * <pre>
       * &lt;complexType>
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
      @XmlType(name = "",
          propOrder = {"codclaseterritorial", "desclaseterritorial", "codcomunidadautonoma",
              "descomunidadautonoma", "codambito", "desambito", "codtipoentidad", "destipoentidad",
              "codorganismoresponsablen1", "desorganismoresponsablen1", "codorganismoresponsablen2",
              "desorganismoresponsablen2"})
      public static class ORGANISMORESPONSABLE {

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


      /**
       * <p>
       * Clase Java para anonymous complex type.
       * 
       * <p>
       * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta
       * clase.
       * 
       * <pre>
       * &lt;complexType>
       *   &lt;complexContent>
       *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
       *       &lt;sequence>
       *         &lt;element name="SISTEMAIDENTIFICACION" maxOccurs="unbounded" minOccurs="0">
       *           &lt;complexType>
       *             &lt;complexContent>
       *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
       *                 &lt;sequence>
       *                   &lt;element name="CODSISTEMAIDENTIFICACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
       *                   &lt;element name="DESSISTEMAIDENTIFICACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
       *                   &lt;element name="OTROSISTEMAIDENTIFICACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
       *                 &lt;/sequence>
       *               &lt;/restriction>
       *             &lt;/complexContent>
       *           &lt;/complexType>
       *         &lt;/element>
       *       &lt;/sequence>
       *     &lt;/restriction>
       *   &lt;/complexContent>
       * &lt;/complexType>
       * </pre>
       * 
       * 
       */
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name = "", propOrder = {"sistemaidentificacion"})
      public static class SISTEMASIDENTIFICACION {

        @XmlElement(name = "SISTEMAIDENTIFICACION")
        protected List<EnviaSIA.ACTUACIONES.ACTUACION.SISTEMASIDENTIFICACION.SISTEMAIDENTIFICACION> sistemaidentificacion;

        /**
         * Gets the value of the sistemaidentificacion property.
         * 
         * <p>
         * This accessor method returns a reference to the live list, not a snapshot. Therefore any
         * modification you make to the returned list will be present inside the JAXB object. This
         * is why there is not a <CODE>set</CODE> method for the sistemaidentificacion property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * 
         * <pre>
         * getSISTEMAIDENTIFICACION().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link EnviaSIA.ACTUACIONES.ACTUACION.SISTEMASIDENTIFICACION.SISTEMAIDENTIFICACION }
         * 
         * 
         */
        public List<EnviaSIA.ACTUACIONES.ACTUACION.SISTEMASIDENTIFICACION.SISTEMAIDENTIFICACION> getSISTEMAIDENTIFICACION() {
          if (sistemaidentificacion == null) {
            sistemaidentificacion =
                new ArrayList<EnviaSIA.ACTUACIONES.ACTUACION.SISTEMASIDENTIFICACION.SISTEMAIDENTIFICACION>();
          }
          return this.sistemaidentificacion;
        }


        /**
         * <p>
         * Clase Java para anonymous complex type.
         * 
         * <p>
         * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta
         * clase.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="CODSISTEMAIDENTIFICACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DESSISTEMAIDENTIFICACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="OTROSISTEMAIDENTIFICACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {"codsistemaidentificacion", "dessistemaidentificacion",
            "otrosistemaidentificacion"})
        public static class SISTEMAIDENTIFICACION {

          @XmlElement(name = "CODSISTEMAIDENTIFICACION")
          protected String codsistemaidentificacion;
          @XmlElement(name = "DESSISTEMAIDENTIFICACION")
          protected String dessistemaidentificacion;
          @XmlElement(name = "OTROSISTEMAIDENTIFICACION")
          protected String otrosistemaidentificacion;

          /**
           * Obtiene el valor de la propiedad codsistemaidentificacion.
           * 
           * @return possible object is {@link String }
           * 
           */
          public String getCODSISTEMAIDENTIFICACION() {
            return codsistemaidentificacion;
          }

          /**
           * Define el valor de la propiedad codsistemaidentificacion.
           * 
           * @param value allowed object is {@link String }
           * 
           */
          public void setCODSISTEMAIDENTIFICACION(String value) {
            this.codsistemaidentificacion = value;
          }

          /**
           * Obtiene el valor de la propiedad dessistemaidentificacion.
           * 
           * @return possible object is {@link String }
           * 
           */
          public String getDESSISTEMAIDENTIFICACION() {
            return dessistemaidentificacion;
          }

          /**
           * Define el valor de la propiedad dessistemaidentificacion.
           * 
           * @param value allowed object is {@link String }
           * 
           */
          public void setDESSISTEMAIDENTIFICACION(String value) {
            this.dessistemaidentificacion = value;
          }

          /**
           * Obtiene el valor de la propiedad otrosistemaidentificacion.
           * 
           * @return possible object is {@link String }
           * 
           */
          public String getOTROSISTEMAIDENTIFICACION() {
            return otrosistemaidentificacion;
          }

          /**
           * Define el valor de la propiedad otrosistemaidentificacion.
           * 
           * @param value allowed object is {@link String }
           * 
           */
          public void setOTROSISTEMAIDENTIFICACION(String value) {
            this.otrosistemaidentificacion = value;
          }

        }

      }


      /**
       * <p>
       * Clase Java para anonymous complex type.
       * 
       * <p>
       * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta
       * clase.
       * 
       * <pre>
       * &lt;complexType>
       *   &lt;complexContent>
       *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
       *       &lt;sequence>
       *         &lt;element name="TIPOACTUACION" maxOccurs="unbounded">
       *           &lt;complexType>
       *             &lt;complexContent>
       *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
       *                 &lt;sequence>
       *                   &lt;element name="CODTIPOACTUACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
       *                   &lt;element name="DESTIPOACTUACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
       *                 &lt;/sequence>
       *               &lt;/restriction>
       *             &lt;/complexContent>
       *           &lt;/complexType>
       *         &lt;/element>
       *       &lt;/sequence>
       *     &lt;/restriction>
       *   &lt;/complexContent>
       * &lt;/complexType>
       * </pre>
       * 
       * 
       */
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name = "", propOrder = {"tipoactuacion"})
      public static class TIPOSACTUACION {

        @XmlElement(name = "TIPOACTUACION", required = true)
        protected List<EnviaSIA.ACTUACIONES.ACTUACION.TIPOSACTUACION.TIPOACTUACION> tipoactuacion;

        /**
         * Gets the value of the tipoactuacion property.
         * 
         * <p>
         * This accessor method returns a reference to the live list, not a snapshot. Therefore any
         * modification you make to the returned list will be present inside the JAXB object. This
         * is why there is not a <CODE>set</CODE> method for the tipoactuacion property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * 
         * <pre>
         * getTIPOACTUACION().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link EnviaSIA.ACTUACIONES.ACTUACION.TIPOSACTUACION.TIPOACTUACION }
         * 
         * 
         */
        public List<EnviaSIA.ACTUACIONES.ACTUACION.TIPOSACTUACION.TIPOACTUACION> getTIPOACTUACION() {
          if (tipoactuacion == null) {
            tipoactuacion =
                new ArrayList<EnviaSIA.ACTUACIONES.ACTUACION.TIPOSACTUACION.TIPOACTUACION>();
          }
          return this.tipoactuacion;
        }


        /**
         * <p>
         * Clase Java para anonymous complex type.
         * 
         * <p>
         * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta
         * clase.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="CODTIPOACTUACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DESTIPOACTUACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {"codtipoactuacion", "destipoactuacion"})
        public static class TIPOACTUACION {

          @XmlElement(name = "CODTIPOACTUACION")
          protected String codtipoactuacion;
          @XmlElement(name = "DESTIPOACTUACION")
          protected String destipoactuacion;

          /**
           * Obtiene el valor de la propiedad codtipoactuacion.
           * 
           * @return possible object is {@link String }
           * 
           */
          public String getCODTIPOACTUACION() {
            return codtipoactuacion;
          }

          /**
           * Define el valor de la propiedad codtipoactuacion.
           * 
           * @param value allowed object is {@link String }
           * 
           */
          public void setCODTIPOACTUACION(String value) {
            this.codtipoactuacion = value;
          }

          /**
           * Obtiene el valor de la propiedad destipoactuacion.
           * 
           * @return possible object is {@link String }
           * 
           */
          public String getDESTIPOACTUACION() {
            return destipoactuacion;
          }

          /**
           * Define el valor de la propiedad destipoactuacion.
           * 
           * @param value allowed object is {@link String }
           * 
           */
          public void setDESTIPOACTUACION(String value) {
            this.destipoactuacion = value;
          }

        }

      }


      /**
       * <p>
       * Clase Java para anonymous complex type.
       * 
       * <p>
       * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta
       * clase.
       * 
       * <pre>
       * &lt;complexType>
       *   &lt;complexContent>
       *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
       *       &lt;sequence>
       *         &lt;element name="VOLTRAMITACION" maxOccurs="unbounded" minOccurs="0">
       *           &lt;complexType>
       *             &lt;complexContent>
       *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
       *                 &lt;sequence>
       *                   &lt;element name="ANIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
       *                   &lt;element name="PERIODO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
       *                   &lt;element name="VOLTOTAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
       *                   &lt;element name="VOLELE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
       *                   &lt;element name="VOLNOELE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
       *                   &lt;element name="VOLCERTIFICADOELE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
       *                   &lt;element name="VOLDNIELE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
       *                   &lt;element name="VOLELEOTRO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
       *                 &lt;/sequence>
       *               &lt;/restriction>
       *             &lt;/complexContent>
       *           &lt;/complexType>
       *         &lt;/element>
       *       &lt;/sequence>
       *     &lt;/restriction>
       *   &lt;/complexContent>
       * &lt;/complexType>
       * </pre>
       * 
       * 
       */
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name = "", propOrder = {"voltramitacion"})
      public static class VOLTRAMITACIONES {

        @XmlElement(name = "VOLTRAMITACION")
        protected List<EnviaSIA.ACTUACIONES.ACTUACION.VOLTRAMITACIONES.VOLTRAMITACION> voltramitacion;

        /**
         * Gets the value of the voltramitacion property.
         * 
         * <p>
         * This accessor method returns a reference to the live list, not a snapshot. Therefore any
         * modification you make to the returned list will be present inside the JAXB object. This
         * is why there is not a <CODE>set</CODE> method for the voltramitacion property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * 
         * <pre>
         * getVOLTRAMITACION().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link EnviaSIA.ACTUACIONES.ACTUACION.VOLTRAMITACIONES.VOLTRAMITACION }
         * 
         * 
         */
        public List<EnviaSIA.ACTUACIONES.ACTUACION.VOLTRAMITACIONES.VOLTRAMITACION> getVOLTRAMITACION() {
          if (voltramitacion == null) {
            voltramitacion =
                new ArrayList<EnviaSIA.ACTUACIONES.ACTUACION.VOLTRAMITACIONES.VOLTRAMITACION>();
          }
          return this.voltramitacion;
        }


        /**
         * <p>
         * Clase Java para anonymous complex type.
         * 
         * <p>
         * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta
         * clase.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="ANIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PERIODO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="VOLTOTAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="VOLELE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="VOLNOELE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="VOLCERTIFICADOELE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="VOLDNIELE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="VOLELEOTRO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {"anio", "periodo", "voltotal", "volele", "volnoele",
            "volcertificadoele", "voldniele", "voleleotro"})
        public static class VOLTRAMITACION {

          @XmlElement(name = "ANIO")
          protected String anio;
          @XmlElement(name = "PERIODO")
          protected String periodo;
          @XmlElement(name = "VOLTOTAL")
          protected String voltotal;
          @XmlElement(name = "VOLELE")
          protected String volele;
          @XmlElement(name = "VOLNOELE")
          protected String volnoele;
          @XmlElement(name = "VOLCERTIFICADOELE")
          protected String volcertificadoele;
          @XmlElement(name = "VOLDNIELE")
          protected String voldniele;
          @XmlElement(name = "VOLELEOTRO")
          protected String voleleotro;

          /**
           * Obtiene el valor de la propiedad anio.
           * 
           * @return possible object is {@link String }
           * 
           */
          public String getANIO() {
            return anio;
          }

          /**
           * Define el valor de la propiedad anio.
           * 
           * @param value allowed object is {@link String }
           * 
           */
          public void setANIO(String value) {
            this.anio = value;
          }

          /**
           * Obtiene el valor de la propiedad periodo.
           * 
           * @return possible object is {@link String }
           * 
           */
          public String getPERIODO() {
            return periodo;
          }

          /**
           * Define el valor de la propiedad periodo.
           * 
           * @param value allowed object is {@link String }
           * 
           */
          public void setPERIODO(String value) {
            this.periodo = value;
          }

          /**
           * Obtiene el valor de la propiedad voltotal.
           * 
           * @return possible object is {@link String }
           * 
           */
          public String getVOLTOTAL() {
            return voltotal;
          }

          /**
           * Define el valor de la propiedad voltotal.
           * 
           * @param value allowed object is {@link String }
           * 
           */
          public void setVOLTOTAL(String value) {
            this.voltotal = value;
          }

          /**
           * Obtiene el valor de la propiedad volele.
           * 
           * @return possible object is {@link String }
           * 
           */
          public String getVOLELE() {
            return volele;
          }

          /**
           * Define el valor de la propiedad volele.
           * 
           * @param value allowed object is {@link String }
           * 
           */
          public void setVOLELE(String value) {
            this.volele = value;
          }

          /**
           * Obtiene el valor de la propiedad volnoele.
           * 
           * @return possible object is {@link String }
           * 
           */
          public String getVOLNOELE() {
            return volnoele;
          }

          /**
           * Define el valor de la propiedad volnoele.
           * 
           * @param value allowed object is {@link String }
           * 
           */
          public void setVOLNOELE(String value) {
            this.volnoele = value;
          }

          /**
           * Obtiene el valor de la propiedad volcertificadoele.
           * 
           * @return possible object is {@link String }
           * 
           */
          public String getVOLCERTIFICADOELE() {
            return volcertificadoele;
          }

          /**
           * Define el valor de la propiedad volcertificadoele.
           * 
           * @param value allowed object is {@link String }
           * 
           */
          public void setVOLCERTIFICADOELE(String value) {
            this.volcertificadoele = value;
          }

          /**
           * Obtiene el valor de la propiedad voldniele.
           * 
           * @return possible object is {@link String }
           * 
           */
          public String getVOLDNIELE() {
            return voldniele;
          }

          /**
           * Define el valor de la propiedad voldniele.
           * 
           * @param value allowed object is {@link String }
           * 
           */
          public void setVOLDNIELE(String value) {
            this.voldniele = value;
          }

          /**
           * Obtiene el valor de la propiedad voleleotro.
           * 
           * @return possible object is {@link String }
           * 
           */
          public String getVOLELEOTRO() {
            return voleleotro;
          }

          /**
           * Define el valor de la propiedad voleleotro.
           * 
           * @param value allowed object is {@link String }
           * 
           */
          public void setVOLELEOTRO(String value) {
            this.voleleotro = value;
          }

        }

      }

    }

  }


  /**
   * <p>
   * Clase Java para anonymous complex type.
   * 
   * <p>
   * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
   * 
   * <pre>
   * &lt;complexType>
   *   &lt;complexContent>
   *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
   *       &lt;sequence>
   *         &lt;element name="DESCERROR" type="{http://www.w3.org/2001/XMLSchema}string"/>
   *         &lt;element name="ERROR" type="{http://www.w3.org/2001/XMLSchema}string"/>
   *       &lt;/sequence>
   *     &lt;/restriction>
   *   &lt;/complexContent>
   * &lt;/complexType>
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = {"descerror", "error"})
  public static class ERROR {

    @XmlElement(name = "DESCERROR", required = true)
    protected String descerror;
    @XmlElement(name = "ERROR", required = true)
    protected String error;

    /**
     * Obtiene el valor de la propiedad descerror.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getDESCERROR() {
      return descerror;
    }

    /**
     * Define el valor de la propiedad descerror.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setDESCERROR(String value) {
      this.descerror = value;
    }

    /**
     * Obtiene el valor de la propiedad error.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getERROR() {
      return error;
    }

    /**
     * Define el valor de la propiedad error.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setERROR(String value) {
      this.error = value;
    }

  }

}
