/* Copyright (C) 2016 MINHAP, Gobierno de España
   This program is licensed and may be used, modified and redistributed under the terms
   of the European Public License (EUPL), either version 1.1 or (at your
   option) any later version as soon as they are approved by the European Commission.
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
   or implied. See the License for the specific language governing permissions and
   more details.
   You should have received a copy of the EUPL1.1 license
   along with this program; if not, you may find it at
   http://joinup.ec.europa.eu/software/page/eupl/licence-eupl */


package es.mpt.dsic.inside.service.sia.model.messages.v2_4.enviasia;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
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
 *                             &lt;element name="TIPOTRAMITE" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}TIPOTRAMITE"/>
 *                             &lt;element name="INTERNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="COMUN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DENOMINACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="FINALIDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ORGANISMORESPONSABLE" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}ORGANISMORESPONSABLE" minOccurs="0"/>
 *                             &lt;element name="DESTINATARIOS" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}DESTINATARIOS" minOccurs="0"/>
 *                             &lt;element name="CODNIVELADMINISTRACIONELECTRONICA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DESNIVELADMINISTRACIONELECTRONICA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="URL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="TITULOCIUDADANO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DESCRIPCIONCIUDADANO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="NIVELMAXIMOTRAMITACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="SISTEMASIDENTIFICACION" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}SISTEMASIDENTIFICACION" minOccurs="0"/>
 *                             &lt;element name="IMPULSOADMINISTRACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PUBLICACIONSEDE060" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="CODPORTAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="SEGSERVICIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="CODACTUACIONSEG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="CODTIPOENCUESTA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="MULTILINGUISMOS" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}MULTILINGUISMOS" minOccurs="0"/>
 *                             &lt;element name="PRESTACIONES" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}PRESTACIONES" minOccurs="0"/>
 *                             &lt;element name="VOLTRAMITACIONESACTUAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="VOLUMENESTRAMITACIONES" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}VOLUMENESTRAMITACIONES" minOccurs="0"/>
 *                             &lt;element name="TEMATICAS" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}TEMATICAS" minOccurs="0"/>
 *                             &lt;element name="CODTIPOLOGIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DESTIPOLOGIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PERFILES" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}PERFILES" minOccurs="0"/>
 *                             &lt;element name="TRAMITESRELACIONADOS" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}TRAMITESRELACIONADOS" minOccurs="0"/>
 *                             &lt;element name="REQUISITOS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PRESENTACIONPRESENCIAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="LUGARPRESENTACION" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}LUGARPRESENTACION" minOccurs="0"/>
 *                             &lt;element name="ORGANOINICIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="INICIOS" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}INICIOS" minOccurs="0"/>
 *                             &lt;element name="PLAZORESOLUCION" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}PLAZORESOLUCION" minOccurs="0"/>
 *                             &lt;element name="FINVIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="NORMATIVAS" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}NORMATIVAS" minOccurs="0"/>
 *                             &lt;element name="REDUCCIONES" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}REDUCCIONES" minOccurs="0"/>
 *                             &lt;element name="DOCUMENTACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DOCUMENTOSCATALOGO" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}DOCUMENTOSCATALOGO" minOccurs="0"/>
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
@XmlType(name = "", propOrder = {
    "error",
    "actuaciones"
})
@XmlRootElement(name = "enviaSIA")
public class EnviaSIA {

    @XmlElement(name = "ERROR")
    protected EnviaSIA.ERROR error;
    @XmlElement(name = "ACTUACIONES")
    protected EnviaSIA.ACTUACIONES actuaciones;

    /**
     * Obtiene el valor de la propiedad error.
     * 
     * @return
     *     possible object is
     *     {@link EnviaSIA.ERROR }
     *     
     */
    public EnviaSIA.ERROR getERROR() {
        return error;
    }

    /**
     * Define el valor de la propiedad error.
     * 
     * @param value
     *     allowed object is
     *     {@link EnviaSIA.ERROR }
     *     
     */
    public void setERROR(EnviaSIA.ERROR value) {
        this.error = value;
    }

    /**
     * Obtiene el valor de la propiedad actuaciones.
     * 
     * @return
     *     possible object is
     *     {@link EnviaSIA.ACTUACIONES }
     *     
     */
    public EnviaSIA.ACTUACIONES getACTUACIONES() {
        return actuaciones;
    }

    /**
     * Define el valor de la propiedad actuaciones.
     * 
     * @param value
     *     allowed object is
     *     {@link EnviaSIA.ACTUACIONES }
     *     
     */
    public void setACTUACIONES(EnviaSIA.ACTUACIONES value) {
        this.actuaciones = value;
    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
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
     *                   &lt;element name="TIPOTRAMITE" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}TIPOTRAMITE"/>
     *                   &lt;element name="INTERNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="COMUN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DENOMINACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="FINALIDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ORGANISMORESPONSABLE" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}ORGANISMORESPONSABLE" minOccurs="0"/>
     *                   &lt;element name="DESTINATARIOS" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}DESTINATARIOS" minOccurs="0"/>
     *                   &lt;element name="CODNIVELADMINISTRACIONELECTRONICA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DESNIVELADMINISTRACIONELECTRONICA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="URL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="TITULOCIUDADANO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DESCRIPCIONCIUDADANO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="NIVELMAXIMOTRAMITACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="SISTEMASIDENTIFICACION" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}SISTEMASIDENTIFICACION" minOccurs="0"/>
     *                   &lt;element name="IMPULSOADMINISTRACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PUBLICACIONSEDE060" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="CODPORTAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="SEGSERVICIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="CODACTUACIONSEG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="CODTIPOENCUESTA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="MULTILINGUISMOS" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}MULTILINGUISMOS" minOccurs="0"/>
     *                   &lt;element name="PRESTACIONES" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}PRESTACIONES" minOccurs="0"/>
     *                   &lt;element name="VOLTRAMITACIONESACTUAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="VOLUMENESTRAMITACIONES" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}VOLUMENESTRAMITACIONES" minOccurs="0"/>
     *                   &lt;element name="TEMATICAS" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}TEMATICAS" minOccurs="0"/>
     *                   &lt;element name="CODTIPOLOGIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DESTIPOLOGIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PERFILES" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}PERFILES" minOccurs="0"/>
     *                   &lt;element name="TRAMITESRELACIONADOS" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}TRAMITESRELACIONADOS" minOccurs="0"/>
     *                   &lt;element name="REQUISITOS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PRESENTACIONPRESENCIAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="LUGARPRESENTACION" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}LUGARPRESENTACION" minOccurs="0"/>
     *                   &lt;element name="ORGANOINICIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="INICIOS" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}INICIOS" minOccurs="0"/>
     *                   &lt;element name="PLAZORESOLUCION" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}PLAZORESOLUCION" minOccurs="0"/>
     *                   &lt;element name="FINVIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="NORMATIVAS" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}NORMATIVAS" minOccurs="0"/>
     *                   &lt;element name="REDUCCIONES" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}REDUCCIONES" minOccurs="0"/>
     *                   &lt;element name="DOCUMENTACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DOCUMENTOSCATALOGO" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}DOCUMENTOSCATALOGO" minOccurs="0"/>
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
    @XmlType(name = "", propOrder = {
        "actuacion"
    })
    public static class ACTUACIONES {

        @XmlElement(name = "ACTUACION")
        protected List<EnviaSIA.ACTUACIONES.ACTUACION> actuacion;

        /**
         * Gets the value of the actuacion property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the actuacion property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getACTUACION().add(newItem);
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
         * <p>Clase Java para anonymous complex type.
         * 
         * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="CODIGOACTUACION" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="TIPOTRAMITE" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}TIPOTRAMITE"/>
         *         &lt;element name="INTERNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="COMUN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DENOMINACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="FINALIDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ORGANISMORESPONSABLE" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}ORGANISMORESPONSABLE" minOccurs="0"/>
         *         &lt;element name="DESTINATARIOS" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}DESTINATARIOS" minOccurs="0"/>
         *         &lt;element name="CODNIVELADMINISTRACIONELECTRONICA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DESNIVELADMINISTRACIONELECTRONICA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="URL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="TITULOCIUDADANO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DESCRIPCIONCIUDADANO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="NIVELMAXIMOTRAMITACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="SISTEMASIDENTIFICACION" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}SISTEMASIDENTIFICACION" minOccurs="0"/>
         *         &lt;element name="IMPULSOADMINISTRACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PUBLICACIONSEDE060" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="CODPORTAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="SEGSERVICIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="CODACTUACIONSEG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="CODTIPOENCUESTA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="MULTILINGUISMOS" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}MULTILINGUISMOS" minOccurs="0"/>
         *         &lt;element name="PRESTACIONES" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}PRESTACIONES" minOccurs="0"/>
         *         &lt;element name="VOLTRAMITACIONESACTUAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="VOLUMENESTRAMITACIONES" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}VOLUMENESTRAMITACIONES" minOccurs="0"/>
         *         &lt;element name="TEMATICAS" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}TEMATICAS" minOccurs="0"/>
         *         &lt;element name="CODTIPOLOGIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DESTIPOLOGIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PERFILES" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}PERFILES" minOccurs="0"/>
         *         &lt;element name="TRAMITESRELACIONADOS" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}TRAMITESRELACIONADOS" minOccurs="0"/>
         *         &lt;element name="REQUISITOS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PRESENTACIONPRESENCIAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="LUGARPRESENTACION" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}LUGARPRESENTACION" minOccurs="0"/>
         *         &lt;element name="ORGANOINICIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="INICIOS" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}INICIOS" minOccurs="0"/>
         *         &lt;element name="PLAZORESOLUCION" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}PLAZORESOLUCION" minOccurs="0"/>
         *         &lt;element name="FINVIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="NORMATIVAS" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}NORMATIVAS" minOccurs="0"/>
         *         &lt;element name="REDUCCIONES" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}REDUCCIONES" minOccurs="0"/>
         *         &lt;element name="DOCUMENTACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DOCUMENTOSCATALOGO" type="{http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA}DOCUMENTOSCATALOGO" minOccurs="0"/>
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
        @XmlType(name = "", propOrder = {
            "codigoactuacion",
            "tipotramite",
            "interno",
            "comun",
            "denominacion",
            "finalidad",
            "organismoresponsable",
            "destinatarios",
            "codniveladministracionelectronica",
            "desniveladministracionelectronica",
            "url",
            "titulociudadano",
            "descripcionciudadano",
            "nivelmaximotramitacion",
            "sistemasidentificacion",
            "impulsoadministracion",
            "publicacionsede060",
            "codportal",
            "segservicio",
            "codactuacionseg",
            "codtipoencuesta",
            "multilinguismos",
            "prestaciones",
            "voltramitacionesactual",
            "volumenestramitaciones",
            "tematicas",
            "codtipologia",
            "destipologia",
            "perfiles",
            "tramitesrelacionados",
            "requisitos",
            "presentacionpresencial",
            "lugarpresentacion",
            "organoinicia",
            "inicios",
            "plazoresolucion",
            "finvia",
            "normativas",
            "reducciones",
            "documentacion",
            "documentoscatalogo",
            "codestadoactuacion",
            "desestadoactuacion",
            "fechaultimaactualizacion"
        })
        public static class ACTUACION {

            @XmlElement(name = "CODIGOACTUACION", required = true)
            protected String codigoactuacion;
            @XmlElement(name = "TIPOTRAMITE", required = true)
            protected TIPOTRAMITE tipotramite;
            @XmlElement(name = "INTERNO")
            protected String interno;
            @XmlElement(name = "COMUN")
            protected String comun;
            @XmlElement(name = "DENOMINACION")
            protected String denominacion;
            @XmlElement(name = "FINALIDAD")
            protected String finalidad;
            @XmlElement(name = "ORGANISMORESPONSABLE")
            protected ORGANISMORESPONSABLE organismoresponsable;
            @XmlElement(name = "DESTINATARIOS")
            protected DESTINATARIOS destinatarios;
            @XmlElement(name = "CODNIVELADMINISTRACIONELECTRONICA")
            protected String codniveladministracionelectronica;
            @XmlElement(name = "DESNIVELADMINISTRACIONELECTRONICA")
            protected String desniveladministracionelectronica;
            @XmlElement(name = "URL")
            protected String url;
            @XmlElement(name = "TITULOCIUDADANO")
            protected String titulociudadano;
            @XmlElement(name = "DESCRIPCIONCIUDADANO")
            protected String descripcionciudadano;
            @XmlElement(name = "NIVELMAXIMOTRAMITACION")
            protected String nivelmaximotramitacion;
            @XmlElement(name = "SISTEMASIDENTIFICACION")
            protected SISTEMASIDENTIFICACION sistemasidentificacion;
            @XmlElement(name = "IMPULSOADMINISTRACION")
            protected String impulsoadministracion;
            @XmlElement(name = "PUBLICACIONSEDE060")
            protected String publicacionsede060;
            @XmlElement(name = "CODPORTAL")
            protected String codportal;
            @XmlElement(name = "SEGSERVICIO")
            protected String segservicio;
            @XmlElement(name = "CODACTUACIONSEG")
            protected String codactuacionseg;
            @XmlElement(name = "CODTIPOENCUESTA")
            protected String codtipoencuesta;
            @XmlElement(name = "MULTILINGUISMOS")
            protected MULTILINGUISMOS multilinguismos;
            @XmlElement(name = "PRESTACIONES")
            protected PRESTACIONES prestaciones;
            @XmlElement(name = "VOLTRAMITACIONESACTUAL")
            protected String voltramitacionesactual;
            @XmlElement(name = "VOLUMENESTRAMITACIONES")
            protected VOLUMENESTRAMITACIONES volumenestramitaciones;
            @XmlElement(name = "TEMATICAS")
            protected TEMATICAS tematicas;
            @XmlElement(name = "CODTIPOLOGIA")
            protected String codtipologia;
            @XmlElement(name = "DESTIPOLOGIA")
            protected String destipologia;
            @XmlElement(name = "PERFILES")
            protected PERFILES perfiles;
            @XmlElement(name = "TRAMITESRELACIONADOS")
            protected TRAMITESRELACIONADOS tramitesrelacionados;
            @XmlElement(name = "REQUISITOS")
            protected String requisitos;
            @XmlElement(name = "PRESENTACIONPRESENCIAL")
            protected String presentacionpresencial;
            @XmlElement(name = "LUGARPRESENTACION")
            protected LUGARPRESENTACION lugarpresentacion;
            @XmlElement(name = "ORGANOINICIA")
            protected String organoinicia;
            @XmlElement(name = "INICIOS")
            protected INICIOS inicios;
            @XmlElement(name = "PLAZORESOLUCION")
            protected PLAZORESOLUCION plazoresolucion;
            @XmlElement(name = "FINVIA")
            protected String finvia;
            @XmlElement(name = "NORMATIVAS")
            protected NORMATIVAS normativas;
            @XmlElement(name = "REDUCCIONES")
            protected REDUCCIONES reducciones;
            @XmlElement(name = "DOCUMENTACION")
            protected String documentacion;
            @XmlElement(name = "DOCUMENTOSCATALOGO")
            protected DOCUMENTOSCATALOGO documentoscatalogo;
            @XmlElement(name = "CODESTADOACTUACION")
            protected String codestadoactuacion;
            @XmlElement(name = "DESESTADOACTUACION")
            protected String desestadoactuacion;
            @XmlElement(name = "FECHAULTIMAACTUALIZACION")
            protected String fechaultimaactualizacion;

            /**
             * Obtiene el valor de la propiedad codigoactuacion.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCODIGOACTUACION() {
                return codigoactuacion;
            }

            /**
             * Define el valor de la propiedad codigoactuacion.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCODIGOACTUACION(String value) {
                this.codigoactuacion = value;
            }

            /**
             * Obtiene el valor de la propiedad tipotramite.
             * 
             * @return
             *     possible object is
             *     {@link TIPOTRAMITE }
             *     
             */
            public TIPOTRAMITE getTIPOTRAMITE() {
                return tipotramite;
            }

            /**
             * Define el valor de la propiedad tipotramite.
             * 
             * @param value
             *     allowed object is
             *     {@link TIPOTRAMITE }
             *     
             */
            public void setTIPOTRAMITE(TIPOTRAMITE value) {
                this.tipotramite = value;
            }

            /**
             * Obtiene el valor de la propiedad interno.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getINTERNO() {
                return interno;
            }

            /**
             * Define el valor de la propiedad interno.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setINTERNO(String value) {
                this.interno = value;
            }

            /**
             * Obtiene el valor de la propiedad comun.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCOMUN() {
                return comun;
            }

            /**
             * Define el valor de la propiedad comun.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCOMUN(String value) {
                this.comun = value;
            }

            /**
             * Obtiene el valor de la propiedad denominacion.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDENOMINACION() {
                return denominacion;
            }

            /**
             * Define el valor de la propiedad denominacion.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDENOMINACION(String value) {
                this.denominacion = value;
            }

            /**
             * Obtiene el valor de la propiedad finalidad.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFINALIDAD() {
                return finalidad;
            }

            /**
             * Define el valor de la propiedad finalidad.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFINALIDAD(String value) {
                this.finalidad = value;
            }

            /**
             * Obtiene el valor de la propiedad organismoresponsable.
             * 
             * @return
             *     possible object is
             *     {@link ORGANISMORESPONSABLE }
             *     
             */
            public ORGANISMORESPONSABLE getORGANISMORESPONSABLE() {
                return organismoresponsable;
            }

            /**
             * Define el valor de la propiedad organismoresponsable.
             * 
             * @param value
             *     allowed object is
             *     {@link ORGANISMORESPONSABLE }
             *     
             */
            public void setORGANISMORESPONSABLE(ORGANISMORESPONSABLE value) {
                this.organismoresponsable = value;
            }

            /**
             * Obtiene el valor de la propiedad destinatarios.
             * 
             * @return
             *     possible object is
             *     {@link DESTINATARIOS }
             *     
             */
            public DESTINATARIOS getDESTINATARIOS() {
                return destinatarios;
            }

            /**
             * Define el valor de la propiedad destinatarios.
             * 
             * @param value
             *     allowed object is
             *     {@link DESTINATARIOS }
             *     
             */
            public void setDESTINATARIOS(DESTINATARIOS value) {
                this.destinatarios = value;
            }

            /**
             * Obtiene el valor de la propiedad codniveladministracionelectronica.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCODNIVELADMINISTRACIONELECTRONICA() {
                return codniveladministracionelectronica;
            }

            /**
             * Define el valor de la propiedad codniveladministracionelectronica.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCODNIVELADMINISTRACIONELECTRONICA(String value) {
                this.codniveladministracionelectronica = value;
            }

            /**
             * Obtiene el valor de la propiedad desniveladministracionelectronica.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDESNIVELADMINISTRACIONELECTRONICA() {
                return desniveladministracionelectronica;
            }

            /**
             * Define el valor de la propiedad desniveladministracionelectronica.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDESNIVELADMINISTRACIONELECTRONICA(String value) {
                this.desniveladministracionelectronica = value;
            }

            /**
             * Obtiene el valor de la propiedad url.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getURL() {
                return url;
            }

            /**
             * Define el valor de la propiedad url.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setURL(String value) {
                this.url = value;
            }

            /**
             * Obtiene el valor de la propiedad titulociudadano.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTITULOCIUDADANO() {
                return titulociudadano;
            }

            /**
             * Define el valor de la propiedad titulociudadano.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTITULOCIUDADANO(String value) {
                this.titulociudadano = value;
            }

            /**
             * Obtiene el valor de la propiedad descripcionciudadano.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDESCRIPCIONCIUDADANO() {
                return descripcionciudadano;
            }

            /**
             * Define el valor de la propiedad descripcionciudadano.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDESCRIPCIONCIUDADANO(String value) {
                this.descripcionciudadano = value;
            }

            /**
             * Obtiene el valor de la propiedad nivelmaximotramitacion.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNIVELMAXIMOTRAMITACION() {
                return nivelmaximotramitacion;
            }

            /**
             * Define el valor de la propiedad nivelmaximotramitacion.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNIVELMAXIMOTRAMITACION(String value) {
                this.nivelmaximotramitacion = value;
            }

            /**
             * Obtiene el valor de la propiedad sistemasidentificacion.
             * 
             * @return
             *     possible object is
             *     {@link SISTEMASIDENTIFICACION }
             *     
             */
            public SISTEMASIDENTIFICACION getSISTEMASIDENTIFICACION() {
                return sistemasidentificacion;
            }

            /**
             * Define el valor de la propiedad sistemasidentificacion.
             * 
             * @param value
             *     allowed object is
             *     {@link SISTEMASIDENTIFICACION }
             *     
             */
            public void setSISTEMASIDENTIFICACION(SISTEMASIDENTIFICACION value) {
                this.sistemasidentificacion = value;
            }

            /**
             * Obtiene el valor de la propiedad impulsoadministracion.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getIMPULSOADMINISTRACION() {
                return impulsoadministracion;
            }

            /**
             * Define el valor de la propiedad impulsoadministracion.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setIMPULSOADMINISTRACION(String value) {
                this.impulsoadministracion = value;
            }

            /**
             * Obtiene el valor de la propiedad publicacionsede060.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPUBLICACIONSEDE060() {
                return publicacionsede060;
            }

            /**
             * Define el valor de la propiedad publicacionsede060.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPUBLICACIONSEDE060(String value) {
                this.publicacionsede060 = value;
            }

            /**
             * Obtiene el valor de la propiedad codportal.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCODPORTAL() {
                return codportal;
            }

            /**
             * Define el valor de la propiedad codportal.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCODPORTAL(String value) {
                this.codportal = value;
            }

            /**
             * Obtiene el valor de la propiedad segservicio.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSEGSERVICIO() {
                return segservicio;
            }

            /**
             * Define el valor de la propiedad segservicio.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSEGSERVICIO(String value) {
                this.segservicio = value;
            }

            /**
             * Obtiene el valor de la propiedad codactuacionseg.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCODACTUACIONSEG() {
                return codactuacionseg;
            }

            /**
             * Define el valor de la propiedad codactuacionseg.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCODACTUACIONSEG(String value) {
                this.codactuacionseg = value;
            }

            /**
             * Obtiene el valor de la propiedad codtipoencuesta.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCODTIPOENCUESTA() {
                return codtipoencuesta;
            }

            /**
             * Define el valor de la propiedad codtipoencuesta.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCODTIPOENCUESTA(String value) {
                this.codtipoencuesta = value;
            }

            /**
             * Obtiene el valor de la propiedad multilinguismos.
             * 
             * @return
             *     possible object is
             *     {@link MULTILINGUISMOS }
             *     
             */
            public MULTILINGUISMOS getMULTILINGUISMOS() {
                return multilinguismos;
            }

            /**
             * Define el valor de la propiedad multilinguismos.
             * 
             * @param value
             *     allowed object is
             *     {@link MULTILINGUISMOS }
             *     
             */
            public void setMULTILINGUISMOS(MULTILINGUISMOS value) {
                this.multilinguismos = value;
            }

            /**
             * Obtiene el valor de la propiedad prestaciones.
             * 
             * @return
             *     possible object is
             *     {@link PRESTACIONES }
             *     
             */
            public PRESTACIONES getPRESTACIONES() {
                return prestaciones;
            }

            /**
             * Define el valor de la propiedad prestaciones.
             * 
             * @param value
             *     allowed object is
             *     {@link PRESTACIONES }
             *     
             */
            public void setPRESTACIONES(PRESTACIONES value) {
                this.prestaciones = value;
            }

            /**
             * Obtiene el valor de la propiedad voltramitacionesactual.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVOLTRAMITACIONESACTUAL() {
                return voltramitacionesactual;
            }

            /**
             * Define el valor de la propiedad voltramitacionesactual.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVOLTRAMITACIONESACTUAL(String value) {
                this.voltramitacionesactual = value;
            }

            /**
             * Obtiene el valor de la propiedad volumenestramitaciones.
             * 
             * @return
             *     possible object is
             *     {@link VOLUMENESTRAMITACIONES }
             *     
             */
            public VOLUMENESTRAMITACIONES getVOLUMENESTRAMITACIONES() {
                return volumenestramitaciones;
            }

            /**
             * Define el valor de la propiedad volumenestramitaciones.
             * 
             * @param value
             *     allowed object is
             *     {@link VOLUMENESTRAMITACIONES }
             *     
             */
            public void setVOLUMENESTRAMITACIONES(VOLUMENESTRAMITACIONES value) {
                this.volumenestramitaciones = value;
            }

            /**
             * Obtiene el valor de la propiedad tematicas.
             * 
             * @return
             *     possible object is
             *     {@link TEMATICAS }
             *     
             */
            public TEMATICAS getTEMATICAS() {
                return tematicas;
            }

            /**
             * Define el valor de la propiedad tematicas.
             * 
             * @param value
             *     allowed object is
             *     {@link TEMATICAS }
             *     
             */
            public void setTEMATICAS(TEMATICAS value) {
                this.tematicas = value;
            }

            /**
             * Obtiene el valor de la propiedad codtipologia.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCODTIPOLOGIA() {
                return codtipologia;
            }

            /**
             * Define el valor de la propiedad codtipologia.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCODTIPOLOGIA(String value) {
                this.codtipologia = value;
            }

            /**
             * Obtiene el valor de la propiedad destipologia.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDESTIPOLOGIA() {
                return destipologia;
            }

            /**
             * Define el valor de la propiedad destipologia.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDESTIPOLOGIA(String value) {
                this.destipologia = value;
            }

            /**
             * Obtiene el valor de la propiedad perfiles.
             * 
             * @return
             *     possible object is
             *     {@link PERFILES }
             *     
             */
            public PERFILES getPERFILES() {
                return perfiles;
            }

            /**
             * Define el valor de la propiedad perfiles.
             * 
             * @param value
             *     allowed object is
             *     {@link PERFILES }
             *     
             */
            public void setPERFILES(PERFILES value) {
                this.perfiles = value;
            }

            /**
             * Obtiene el valor de la propiedad tramitesrelacionados.
             * 
             * @return
             *     possible object is
             *     {@link TRAMITESRELACIONADOS }
             *     
             */
            public TRAMITESRELACIONADOS getTRAMITESRELACIONADOS() {
                return tramitesrelacionados;
            }

            /**
             * Define el valor de la propiedad tramitesrelacionados.
             * 
             * @param value
             *     allowed object is
             *     {@link TRAMITESRELACIONADOS }
             *     
             */
            public void setTRAMITESRELACIONADOS(TRAMITESRELACIONADOS value) {
                this.tramitesrelacionados = value;
            }

            /**
             * Obtiene el valor de la propiedad requisitos.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getREQUISITOS() {
                return requisitos;
            }

            /**
             * Define el valor de la propiedad requisitos.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setREQUISITOS(String value) {
                this.requisitos = value;
            }

            /**
             * Obtiene el valor de la propiedad presentacionpresencial.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPRESENTACIONPRESENCIAL() {
                return presentacionpresencial;
            }

            /**
             * Define el valor de la propiedad presentacionpresencial.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPRESENTACIONPRESENCIAL(String value) {
                this.presentacionpresencial = value;
            }

            /**
             * Obtiene el valor de la propiedad lugarpresentacion.
             * 
             * @return
             *     possible object is
             *     {@link LUGARPRESENTACION }
             *     
             */
            public LUGARPRESENTACION getLUGARPRESENTACION() {
                return lugarpresentacion;
            }

            /**
             * Define el valor de la propiedad lugarpresentacion.
             * 
             * @param value
             *     allowed object is
             *     {@link LUGARPRESENTACION }
             *     
             */
            public void setLUGARPRESENTACION(LUGARPRESENTACION value) {
                this.lugarpresentacion = value;
            }

            /**
             * Obtiene el valor de la propiedad organoinicia.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getORGANOINICIA() {
                return organoinicia;
            }

            /**
             * Define el valor de la propiedad organoinicia.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setORGANOINICIA(String value) {
                this.organoinicia = value;
            }

            /**
             * Obtiene el valor de la propiedad inicios.
             * 
             * @return
             *     possible object is
             *     {@link INICIOS }
             *     
             */
            public INICIOS getINICIOS() {
                return inicios;
            }

            /**
             * Define el valor de la propiedad inicios.
             * 
             * @param value
             *     allowed object is
             *     {@link INICIOS }
             *     
             */
            public void setINICIOS(INICIOS value) {
                this.inicios = value;
            }

            /**
             * Obtiene el valor de la propiedad plazoresolucion.
             * 
             * @return
             *     possible object is
             *     {@link PLAZORESOLUCION }
             *     
             */
            public PLAZORESOLUCION getPLAZORESOLUCION() {
                return plazoresolucion;
            }

            /**
             * Define el valor de la propiedad plazoresolucion.
             * 
             * @param value
             *     allowed object is
             *     {@link PLAZORESOLUCION }
             *     
             */
            public void setPLAZORESOLUCION(PLAZORESOLUCION value) {
                this.plazoresolucion = value;
            }

            /**
             * Obtiene el valor de la propiedad finvia.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFINVIA() {
                return finvia;
            }

            /**
             * Define el valor de la propiedad finvia.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFINVIA(String value) {
                this.finvia = value;
            }

            /**
             * Obtiene el valor de la propiedad normativas.
             * 
             * @return
             *     possible object is
             *     {@link NORMATIVAS }
             *     
             */
            public NORMATIVAS getNORMATIVAS() {
                return normativas;
            }

            /**
             * Define el valor de la propiedad normativas.
             * 
             * @param value
             *     allowed object is
             *     {@link NORMATIVAS }
             *     
             */
            public void setNORMATIVAS(NORMATIVAS value) {
                this.normativas = value;
            }

            /**
             * Obtiene el valor de la propiedad reducciones.
             * 
             * @return
             *     possible object is
             *     {@link REDUCCIONES }
             *     
             */
            public REDUCCIONES getREDUCCIONES() {
                return reducciones;
            }

            /**
             * Define el valor de la propiedad reducciones.
             * 
             * @param value
             *     allowed object is
             *     {@link REDUCCIONES }
             *     
             */
            public void setREDUCCIONES(REDUCCIONES value) {
                this.reducciones = value;
            }

            /**
             * Obtiene el valor de la propiedad documentacion.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDOCUMENTACION() {
                return documentacion;
            }

            /**
             * Define el valor de la propiedad documentacion.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDOCUMENTACION(String value) {
                this.documentacion = value;
            }

            /**
             * Obtiene el valor de la propiedad documentoscatalogo.
             * 
             * @return
             *     possible object is
             *     {@link DOCUMENTOSCATALOGO }
             *     
             */
            public DOCUMENTOSCATALOGO getDOCUMENTOSCATALOGO() {
                return documentoscatalogo;
            }

            /**
             * Define el valor de la propiedad documentoscatalogo.
             * 
             * @param value
             *     allowed object is
             *     {@link DOCUMENTOSCATALOGO }
             *     
             */
            public void setDOCUMENTOSCATALOGO(DOCUMENTOSCATALOGO value) {
                this.documentoscatalogo = value;
            }

            /**
             * Obtiene el valor de la propiedad codestadoactuacion.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCODESTADOACTUACION() {
                return codestadoactuacion;
            }

            /**
             * Define el valor de la propiedad codestadoactuacion.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCODESTADOACTUACION(String value) {
                this.codestadoactuacion = value;
            }

            /**
             * Obtiene el valor de la propiedad desestadoactuacion.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDESESTADOACTUACION() {
                return desestadoactuacion;
            }

            /**
             * Define el valor de la propiedad desestadoactuacion.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDESESTADOACTUACION(String value) {
                this.desestadoactuacion = value;
            }

            /**
             * Obtiene el valor de la propiedad fechaultimaactualizacion.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFECHAULTIMAACTUALIZACION() {
                return fechaultimaactualizacion;
            }

            /**
             * Define el valor de la propiedad fechaultimaactualizacion.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFECHAULTIMAACTUALIZACION(String value) {
                this.fechaultimaactualizacion = value;
            }

        }

    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
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
    @XmlType(name = "", propOrder = {
        "descerror",
        "error"
    })
    public static class ERROR {

        @XmlElement(name = "DESCERROR", required = true)
        protected String descerror;
        @XmlElement(name = "ERROR", required = true)
        protected String error;

        /**
         * Obtiene el valor de la propiedad descerror.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDESCERROR() {
            return descerror;
        }

        /**
         * Define el valor de la propiedad descerror.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDESCERROR(String value) {
            this.descerror = value;
        }

        /**
         * Obtiene el valor de la propiedad error.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getERROR() {
            return error;
        }

        /**
         * Define el valor de la propiedad error.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setERROR(String value) {
            this.error = value;
        }

    }

}
