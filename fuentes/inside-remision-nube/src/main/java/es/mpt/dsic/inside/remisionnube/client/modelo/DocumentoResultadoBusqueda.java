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

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Clase Java para DocumentoResultadoBusqueda complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DocumentoResultadoBusqueda">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="paginador" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/busqueda/resultados}InfoResultadosBusquedaInside"/>
 *         &lt;element name="resultados" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Identificador" type="{http://www.w3.org/2001/XMLSchema}ID"/>
 *                   &lt;element ref="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/metadatos}metadatos"/>
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
@XmlType(name = "DocumentoResultadoBusqueda", namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/busqueda/resultados", propOrder = {
    "paginador",
    "resultados"
})
public class DocumentoResultadoBusqueda {

    @XmlElement(required = true)
    protected InfoResultadosBusquedaInside paginador;
    protected List<DocumentoResultadoBusqueda.Resultados> resultados;

    /**
     * Obtiene el valor de la propiedad paginador.
     * 
     * @return
     *     possible object is
     *     {@link InfoResultadosBusquedaInside }
     *     
     */
    public InfoResultadosBusquedaInside getPaginador() {
        return paginador;
    }

    /**
     * Define el valor de la propiedad paginador.
     * 
     * @param value
     *     allowed object is
     *     {@link InfoResultadosBusquedaInside }
     *     
     */
    public void setPaginador(InfoResultadosBusquedaInside value) {
        this.paginador = value;
    }

    /**
     * Gets the value of the resultados property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resultados property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResultados().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentoResultadoBusqueda.Resultados }
     * 
     * 
     */
    public List<DocumentoResultadoBusqueda.Resultados> getResultados() {
        if (resultados == null) {
            resultados = new ArrayList<DocumentoResultadoBusqueda.Resultados>();
        }
        return this.resultados;
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
     *         &lt;element name="Identificador" type="{http://www.w3.org/2001/XMLSchema}ID"/>
     *         &lt;element ref="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/metadatos}metadatos"/>
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
        "identificador",
        "metadatos"
    })
    public static class Resultados {

        @XmlElement(name = "Identificador", namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/busqueda/resultados", required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        @XmlID
        @XmlSchemaType(name = "ID")
        protected String identificador;
        @XmlElement(namespace = "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/metadatos", required = true)
        protected TipoMetadatos metadatos;

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
         * Obtiene el valor de la propiedad metadatos.
         * 
         * @return
         *     possible object is
         *     {@link TipoMetadatos }
         *     
         */
        public TipoMetadatos getMetadatos() {
            return metadatos;
        }

        /**
         * Define el valor de la propiedad metadatos.
         * 
         * @param value
         *     allowed object is
         *     {@link TipoMetadatos }
         *     
         */
        public void setMetadatos(TipoMetadatos value) {
            this.metadatos = value;
        }

    }

}
