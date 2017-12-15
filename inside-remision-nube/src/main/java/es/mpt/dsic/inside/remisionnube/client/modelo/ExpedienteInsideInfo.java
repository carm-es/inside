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


package es.mpt.dsic.inside.remisionnube.client.modelo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Clase Java para ExpedienteInsideInfo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ExpedienteInsideInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Identificador" type="{http://www.w3.org/2001/XMLSchema}ID"/>
 *         &lt;element name="Estado" type="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/metadatos}enumeracionEstados"/>
 *         &lt;element name="versionActual" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/version}TipoVersionInside"/>
 *         &lt;element name="infoExpediente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExpedienteInsideInfo", namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e/expedienteInfo", propOrder = {
    "identificador",
    "estado",
    "versionActual",
    "infoExpediente"
})
public class ExpedienteInsideInfo {

    @XmlElement(name = "Identificador", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String identificador;
    @XmlElement(name = "Estado", required = true)
    @XmlSchemaType(name = "string")
    protected EnumeracionEstados estado;
    @XmlElement(required = true)
    protected TipoVersionInside versionActual;
    @XmlElement(required = true)
    protected String infoExpediente;

    /**
     * Obtiene el valor de la propiedad identificador.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * Define el valor de la propiedad identificador.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificador(String value) {
        this.identificador = value;
    }

    /**
     * Obtiene el valor de la propiedad estado.
     * 
     * @return
     *     possible object is
     *     {@link EnumeracionEstados }
     *     
     */
    public EnumeracionEstados getEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumeracionEstados }
     *     
     */
    public void setEstado(EnumeracionEstados value) {
        this.estado = value;
    }

    /**
     * Obtiene el valor de la propiedad versionActual.
     * 
     * @return
     *     possible object is
     *     {@link TipoVersionInside }
     *     
     */
    public TipoVersionInside getVersionActual() {
        return versionActual;
    }

    /**
     * Define el valor de la propiedad versionActual.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoVersionInside }
     *     
     */
    public void setVersionActual(TipoVersionInside value) {
        this.versionActual = value;
    }

    /**
     * Obtiene el valor de la propiedad infoExpediente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInfoExpediente() {
        return infoExpediente;
    }

    /**
     * Define el valor de la propiedad infoExpediente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInfoExpediente(String value) {
        this.infoExpediente = value;
    }

}
