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


package es.mpt.dsic.inside.xml.inside.ws.expediente.documentos.mtom;

import java.util.ArrayList;
import java.util.List;
import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlType;
import es.mpt.dsic.inside.xml.inside.ws.documento.mtom.TipoDocumentoEniFileInsideMtom;


/**
 * <p>
 * Java class for TipoExpedienteEniFileInsideMtomConDocumentos complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TipoExpedienteEniFileInsideMtomConDocumentos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="expedienteEniBytes" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="documentosEniFileMtom" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/documentoEniFile/mtom}TipoDocumentoEniFileInsideMtom" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoExpedienteEniFileInsideMtomConDocumentos",
    propOrder = {"expedienteEniBytes", "documentosEniFileMtom"})
public class TipoExpedienteEniFileInsideMtomConDocumentos {

  @XmlElement(required = true)
  @XmlMimeType("application/octet-stream")
  protected DataHandler expedienteEniBytes;
  @XmlElement(required = true)
  protected List<TipoDocumentoEniFileInsideMtom> documentosEniFileMtom;

  /**
   * Gets the value of the expedienteEniBytes property.
   * 
   * @return possible object is {@link DataHandler }
   * 
   */
  public DataHandler getExpedienteEniBytes() {
    return expedienteEniBytes;
  }

  /**
   * Sets the value of the expedienteEniBytes property.
   * 
   * @param value allowed object is {@link DataHandler }
   * 
   */
  public void setExpedienteEniBytes(DataHandler value) {
    this.expedienteEniBytes = value;
  }

  /**
   * Gets the value of the documentosEniFileMtom property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the documentosEniFileMtom property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getDocumentosEniFileMtom().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list {@link TipoDocumentoEniFileInsideMtom
   * }
   * 
   * 
   */
  public List<TipoDocumentoEniFileInsideMtom> getDocumentosEniFileMtom() {
    if (documentosEniFileMtom == null) {
      documentosEniFileMtom = new ArrayList<TipoDocumentoEniFileInsideMtom>();
    }
    return this.documentosEniFileMtom;
  }

}
