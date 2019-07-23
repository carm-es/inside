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


package es.mpt.dsic.inside.remisionnube.client.modelo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Clase Java para altaDocumentoEni complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="altaDocumentoEni">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="documento" type="{http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e}TipoDocumento"/>
 *         &lt;element name="asociadoaExpediente" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/alta}TipoDocumentoAsociadoaExpediente" minOccurs="0"/>
 *         &lt;element name="metadatosAdicionales" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/metadatosAdicionales}TipoMetadatosAdicionales" minOccurs="0"/>
 *         &lt;element name="firmaServidor" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "altaDocumentoEni",
    propOrder = {"documento", "asociadoaExpediente", "metadatosAdicionales", "firmaServidor"})
public class AltaDocumentoEni {

  @XmlElement(required = true)
  protected TipoDocumento documento;
  protected TipoDocumentoAsociadoaExpediente asociadoaExpediente;
  protected TipoMetadatosAdicionales metadatosAdicionales;
  protected boolean firmaServidor;

  /**
   * Obtiene el valor de la propiedad documento.
   * 
   * @return possible object is {@link TipoDocumento }
   * 
   */
  public TipoDocumento getDocumento() {
    return documento;
  }

  /**
   * Define el valor de la propiedad documento.
   * 
   * @param value allowed object is {@link TipoDocumento }
   * 
   */
  public void setDocumento(TipoDocumento value) {
    this.documento = value;
  }

  /**
   * Obtiene el valor de la propiedad asociadoaExpediente.
   * 
   * @return possible object is {@link TipoDocumentoAsociadoaExpediente }
   * 
   */
  public TipoDocumentoAsociadoaExpediente getAsociadoaExpediente() {
    return asociadoaExpediente;
  }

  /**
   * Define el valor de la propiedad asociadoaExpediente.
   * 
   * @param value allowed object is {@link TipoDocumentoAsociadoaExpediente }
   * 
   */
  public void setAsociadoaExpediente(TipoDocumentoAsociadoaExpediente value) {
    this.asociadoaExpediente = value;
  }

  /**
   * Obtiene el valor de la propiedad metadatosAdicionales.
   * 
   * @return possible object is {@link TipoMetadatosAdicionales }
   * 
   */
  public TipoMetadatosAdicionales getMetadatosAdicionales() {
    return metadatosAdicionales;
  }

  /**
   * Define el valor de la propiedad metadatosAdicionales.
   * 
   * @param value allowed object is {@link TipoMetadatosAdicionales }
   * 
   */
  public void setMetadatosAdicionales(TipoMetadatosAdicionales value) {
    this.metadatosAdicionales = value;
  }

  /**
   * Obtiene el valor de la propiedad firmaServidor.
   * 
   */
  public boolean isFirmaServidor() {
    return firmaServidor;
  }

  /**
   * Define el valor de la propiedad firmaServidor.
   * 
   */
  public void setFirmaServidor(boolean value) {
    this.firmaServidor = value;
  }

}
