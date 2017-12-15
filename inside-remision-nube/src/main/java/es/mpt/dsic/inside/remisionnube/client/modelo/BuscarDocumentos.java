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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para buscarDocumentos complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="buscarDocumentos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="consulta" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/busqueda}consultaWsInside"/>
 *         &lt;element name="limite" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="pagina" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "buscarDocumentos", propOrder = {
    "consulta",
    "limite",
    "pagina"
})
public class BuscarDocumentos {

    @XmlElement(required = true)
    protected ConsultaWsInside consulta;
    protected int limite;
    protected int pagina;

    /**
     * Obtiene el valor de la propiedad consulta.
     * 
     * @return
     *     possible object is
     *     {@link ConsultaWsInside }
     *     
     */
    public ConsultaWsInside getConsulta() {
        return consulta;
    }

    /**
     * Define el valor de la propiedad consulta.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsultaWsInside }
     *     
     */
    public void setConsulta(ConsultaWsInside value) {
        this.consulta = value;
    }

    /**
     * Obtiene el valor de la propiedad limite.
     * 
     */
    public int getLimite() {
        return limite;
    }

    /**
     * Define el valor de la propiedad limite.
     * 
     */
    public void setLimite(int value) {
        this.limite = value;
    }

    /**
     * Obtiene el valor de la propiedad pagina.
     * 
     */
    public int getPagina() {
        return pagina;
    }

    /**
     * Define el valor de la propiedad pagina.
     * 
     */
    public void setPagina(int value) {
        this.pagina = value;
    }

}
