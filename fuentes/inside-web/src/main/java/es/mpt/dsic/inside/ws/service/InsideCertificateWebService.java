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

package es.mpt.dsic.inside.ws.service;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import org.apache.cxf.annotations.GZIP;
import org.apache.cxf.annotations.Logging;
import org.apache.cxf.interceptor.OutFaultInterceptors;
import org.apache.cxf.interceptor.OutInterceptors;
import es.mpt.dsic.inside.ws.cxf.interceptor.InsideWsErrorInterceptor;
import es.mpt.dsic.inside.ws.cxf.interceptor.InsideWsIndiceExpedienteSignerInterceptor;
import es.mpt.dsic.inside.ws.exception.InsideWSException;
import es.mpt.dsic.inside.xml.eni.documento.TipoDocumento;
import es.mpt.dsic.inside.xml.eni.documento.contenido.TipoContenido;
import es.mpt.dsic.inside.xml.eni.expediente.TipoExpediente;
import es.mpt.dsic.inside.xml.eni.expediente.indice.TipoIndice;
import es.mpt.dsic.inside.xml.eni.expediente.indice.contenido.TipoCarpetaIndizada;
import es.mpt.dsic.inside.xml.eni.expediente.metadatos.EnumeracionEstados;
import es.mpt.dsic.inside.xml.eni.expediente.metadatos.TipoMetadatos;
import es.mpt.dsic.inside.xml.eni.firma.Firmas;
import es.mpt.dsic.inside.xml.inside.DocumentoInsideMetadatos;
import es.mpt.dsic.inside.xml.inside.ExpedienteInsideInfo;
import es.mpt.dsic.inside.xml.inside.ExpedienteInsideMetadatos;
import es.mpt.dsic.inside.xml.inside.TipoDocumentoInside;
import es.mpt.dsic.inside.xml.inside.TipoExpedienteInside;
import es.mpt.dsic.inside.xml.inside.TipoMetadatosAdicionales;
import es.mpt.dsic.inside.xml.inside.TipoVersionInside;
import es.mpt.dsic.inside.xml.inside.ws.busqueda.ConsultaWsInside;
import es.mpt.dsic.inside.xml.inside.ws.busqueda.DocumentoResultadoBusqueda;
import es.mpt.dsic.inside.xml.inside.ws.busqueda.ExpedienteResultadoBusqueda;
import es.mpt.dsic.inside.xml.inside.ws.documento.DocumentoInsideInfo;
import es.mpt.dsic.inside.xml.inside.ws.documento.TipoDocumentoEniFileInside;
import es.mpt.dsic.inside.xml.inside.ws.documento.alta.TipoDocumentoAltaInside;
import es.mpt.dsic.inside.xml.inside.ws.documento.alta.TipoDocumentoAsociadoaExpediente;
import es.mpt.dsic.inside.xml.inside.ws.expediente.TipoExpedienteEniFileInside;
import es.mpt.dsic.inside.xml.inside.ws.expediente.documentos.TipoExpedienteEniFileInsideConDocumentos;
import es.mpt.dsic.inside.xml.inside.ws.expediente.file.RespuestaPdfExpediente;
import es.mpt.dsic.inside.xml.inside.ws.remisionEnLaNube.PeticionRemisionAJusticiaType;
import es.mpt.dsic.inside.xml.inside.ws.remisionEnLaNube.RespuestaRemisionAJusticiaType;
import es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.TipoResultadoVisualizacionDocumentoInside;

@WebService(name = "InsideCertifcateWebService",
    targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService")

@Logging
@GZIP
@OutInterceptors(interceptors = {}, classes = {InsideWsIndiceExpedienteSignerInterceptor.class})
@OutFaultInterceptors(interceptors = {}, classes = {InsideWsErrorInterceptor.class})
public interface InsideCertificateWebService {

  @WebMethod(operationName = "altaExpedienteEni", action = "urn:altaExpedienteEni")
  @WebResult(name = "ExpedienteInsideInfo", partName = "ExpedienteInsideInfo",
      targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e/expedienteInfo")
  public ExpedienteInsideInfo altaExpedienteEni(
      @WebParam(name = "expediente") @XmlElement(required = true,
          name = "expediente") TipoExpediente expediente,
      @WebParam(name = "metadatosAdicionales") @XmlElement(required = false,
          name = "metadatosAdicionales") TipoMetadatosAdicionales metadatosAdicionales)
      throws InsideWSException;

  @WebMethod(operationName = "altaExpedienteEniXml", action = "urn:altaExpedienteEniXml")
  @WebResult(name = "ExpedienteInsideInfo", partName = "ExpedienteInsideInfo",
      targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e/expedienteInfo")
  public ExpedienteInsideInfo altaExpedienteEniXml(
      @WebParam(name = "expedienteEniFile") @XmlElement(required = true,
          name = "expedienteEniFile") TipoExpedienteEniFileInsideConDocumentos expedienteEniXml)
      throws InsideWSException;

  @WebMethod(operationName = "cambiaMetadatosExpediente", action = "urn:cambiaMetadatosExpediente")
  @WebResult(name = "ExpedienteInsideInfo", partName = "ExpedienteInsideInfo",
      targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e/expedienteInfo")
  public ExpedienteInsideInfo cambiaMetadatosExpediente(
      @WebParam(name = "identificador") @XmlElement(required = true,
          name = "identificador") String identificador,
      @WebParam(name = "metadatosEni") @XmlElement(required = true,
          name = "metadatosEni") TipoMetadatos metadatosEni,
      @WebParam(name = "metadatosAdicionales") @XmlElement(required = false,
          name = "metadatosAdicionales") TipoMetadatosAdicionales metadatosAdicionales)
      throws InsideWSException;

  @WebMethod(operationName = "cambiaMetadatosDocumento", action = "urn:cambiaMetadatosDocumento")
  @WebResult(name = "DocumentoInsideInfo", partName = "DocumentoInsideInfo",
      targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/documentoInfo")
  public DocumentoInsideInfo cambiaMetadatosDocumento(
      @WebParam(name = "identificador") @XmlElement(required = true,
          name = "identificador") String identificador,
      @WebParam(name = "metadatosEni") @XmlElement(required = true,
          name = "metadatosEni") es.mpt.dsic.inside.xml.eni.documento.metadatos.TipoMetadatos metadatosEni,
      @WebParam(name = "metadatosAdicionales") @XmlElement(required = false,
          name = "metadatosAdicionales") TipoMetadatosAdicionales metadatosAdicionales)
      throws InsideWSException;

  @WebMethod(operationName = "modificaExpedienteEni", action = "urn:modificaExpedienteEni")
  @WebResult(name = "ExpedienteInsideInfo", partName = "ExpedienteInsideInfo",
      targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e/expedienteInfo")
  public ExpedienteInsideInfo modificaExpedienteEni(
      @WebParam(name = "expediente") @XmlElement(required = true,
          name = "expediente") TipoExpediente expediente,
      @WebParam(name = "metadatosAdicionales") @XmlElement(required = false,
          name = "metadatosAdicionales") TipoMetadatosAdicionales metadatosAdicionales)
      throws InsideWSException;

  @WebMethod(operationName = "altaCarpetaEnExpediente", action = "urn:altaCarpetaEnExpediente")
  @WebResult(name = "ExpedienteInsideInfo", partName = "ExpedienteInsideInfo",
      targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e/expedienteInfo")
  public ExpedienteInsideInfo altaCarpetaEnExpediente(
      @WebParam(name = "identificadorExpediente") @XmlElement(required = true,
          name = "identificadorExpediente") String identificadorExpediente,
      @WebParam(name = "carpetaIndizada") @XmlElement(required = true,
          name = "carpetaIndizada") TipoCarpetaIndizada carpetaIndizada,
      @WebParam(name = "ruta") @XmlElement(required = false, name = "ruta") String ruta)
      throws InsideWSException;

  @WebMethod(operationName = "borrarCarpetaEnExpediente", action = "urn:borrarCarpetaEnExpediente")
  @WebResult(name = "ExpedienteInsideInfo", partName = "ExpedienteInsideInfo",
      targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e/expedienteInfo")
  public ExpedienteInsideInfo borrarCarpetaEnExpediente(
      @WebParam(name = "identificadorExpediente") @XmlElement(required = true,
          name = "identificadorExpediente") String identificadorExpediente,
      @WebParam(name = "identificadorCarpeta") @XmlElement(required = true,
          name = "identificadorCarpeta") String identificadorCarpeta,
      @WebParam(name = "ruta") @XmlElement(required = false, name = "ruta") String ruta)
      throws InsideWSException;

  @WebMethod(operationName = "cambiaEstadoExpediente", action = "urn:cambiaEstadoExpediente")
  @WebResult(name = "ExpedienteInsideInfo", partName = "ExpedienteInsideInfo",
      targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e/expedienteInfo")
  public ExpedienteInsideInfo cambiaEstadoExpediente(
      @WebParam(name = "identificador") @XmlElement(required = true,
          name = "identificador") String identificador,
      @WebParam(name = "estado") @XmlElement(required = true,
          name = "estado") EnumeracionEstados estado)
      throws InsideWSException;

  @WebMethod(operationName = "importarExpediente", action = "urn:importarExpediente")
  @WebResult(name = "ExpedienteInsideInfo", partName = "ExpedienteInsideInfo",
      targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e/expedienteInfo")
  public ExpedienteInsideInfo importarExpediente(
      @WebParam(name = "identificador") @XmlElement(required = true,
          name = "identificador") String identificador,
      @WebParam(name = "identificadorImportado") @XmlElement(required = true,
          name = "identificadorImportado") String identificadorImportado,
      @WebParam(name = "ruta") @XmlElement(required = true, name = "ruta") String ruta)
      throws InsideWSException;

  @WebMethod(operationName = "vincularExpediente", action = "urn:vincularExpediente")
  @WebResult(name = "ExpedienteInsideInfo", partName = "ExpedienteInsideInfo",
      targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e/expedienteInfo")
  public ExpedienteInsideInfo vincularExpediente(
      @WebParam(name = "identificador") @XmlElement(required = true,
          name = "identificador") String identificador,
      @WebParam(name = "identificadorVinculado") @XmlElement(required = true,
          name = "identificadorVinculado") String identificadorVinculado,
      @WebParam(name = "ruta") @XmlElement(required = true, name = "ruta") String ruta)
      throws InsideWSException;

  @WebMethod(operationName = "crearVistaAbiertaExpediente",
      action = "urn:crearVistaAbiertaExpediente")
  @WebResult(name = "ExpedienteInsideInfo", partName = "ExpedienteInsideInfo",
      targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e/expedienteInfo")
  public ExpedienteInsideInfo crearVistaAbiertaExpediente(
      @WebParam(name = "identificador") @XmlElement(required = true,
          name = "identificador") String identificador)
      throws InsideWSException;

  @WebMethod(operationName = "crearVistaCerradaExpediente",
      action = "urn:crearVistaCerradaExpediente")
  @WebResult(name = "ExpedienteInsideInfo", partName = "ExpedienteInsideInfo",
      targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e/expedienteInfo")
  public ExpedienteInsideInfo crearVistaCerradaExpediente(
      @WebParam(name = "identificador") @XmlElement(required = true,
          name = "identificador") String identificador)
      throws InsideWSException;

  @WebMethod(operationName = "vincularDocumentosEnExpediente",
      action = "urn:vincularDocumentosEnExpediente")
  @WebResult(name = "ExpedienteInsideInfo", partName = "ExpedienteInsideInfo",
      targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e/expedienteInfo")
  public ExpedienteInsideInfo vincularDocumentosEnExpediente(
      @WebParam(name = "identificador") @XmlElement(required = true,
          name = "identificador") String identificador,
      @WebParam(name = "identificadorDocumento") @XmlElement(required = true,
          name = "identificadorDocumento") List<String> identificadoresDocumentos,
      @WebParam(name = "ruta") @XmlElement(required = true, name = "ruta") String ruta)
      throws InsideWSException;

  @WebMethod(operationName = "desvincularDocumentosEnExpediente",
      action = "urn:desvincularDocumentosEnExpediente")
  @WebResult(name = "ExpedienteInsideInfo", partName = "ExpedienteInsideInfo",
      targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e/expedienteInfo")
  public ExpedienteInsideInfo desvincularDocumentosEnExpediente(
      @WebParam(name = "identificador") @XmlElement(required = true,
          name = "identificador") String identificador,
      @WebParam(name = "identificadorDocumento") @XmlElement(required = true,
          name = "identificadorDocumento") List<String> identificadoresDocumentos,
      @WebParam(name = "ruta") @XmlElement(required = true, name = "ruta") String ruta)
      throws InsideWSException;

  @WebMethod(operationName = "cambiarUbicacionEnExpediente",
      action = "urn:cambiarUbicacionEnExpediente")
  @WebResult(name = "ExpedienteInsideInfo", partName = "ExpedienteInsideInfo",
      targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e/expedienteInfo")
  public ExpedienteInsideInfo cambiarUbicacionEnExpediente(
      @WebParam(name = "identificadorExpediente") @XmlElement(required = true,
          name = "identificadorExpediente") String identificadorExpediente,
      @WebParam(name = "identificadoresElementos") @XmlElement(required = true,
          name = "identificadoresElementos") List<String> identificadoresElementos,
      @WebParam(name = "rutaOrigen") @XmlElement(required = true,
          name = "rutaOrigen") String rutaOrigen,
      @WebParam(name = "rutaDestino") @XmlElement(required = true,
          name = "rutaDestino") String rutaDestino)
      throws InsideWSException;

  @WebMethod(operationName = "getExpediente", action = "urn:getExpediente")
  @WebResult(name = "expediente", partName = "expediente",
      targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e")
  public TipoExpedienteInside getExpediente(
      @WebParam(name = "identificador") @XmlElement(required = true,
          name = "identificador") String identificador,
      @WebParam(name = "version") @XmlElement(name = "version") Integer version)
      throws InsideWSException;

  @WebMethod(operationName = "getExpedienteEni", action = "urn:getExpedienteEni")
  @WebResult(name = "expediente", partName = "expediente",
      targetNamespace = "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e")
  public TipoExpediente getExpedienteEni(@WebParam(name = "identificador") @XmlElement(
      required = true, name = "identificador") String identificador) throws InsideWSException;

  @WebMethod(operationName = "getExpedienteEniFile", action = "urn:getExpedienteEniFile")
  @WebResult(name = "expedienteEniFile", partName = "expedienteEniFile",
      targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e/expedienteEniFile")
  public TipoExpedienteEniFileInside getExpedienteEniFile(
      @WebParam(name = "identificador") @XmlElement(required = true,
          name = "identificador") String identificador,
      @WebParam(name = "visualizacionIndice") @XmlElement(required = true,
          name = "visualizacionIndice") Boolean visualizacionIndice)
      throws InsideWSException;

  @WebMethod(operationName = "getIndiceExpediente", action = "urn:getIndiceExpediente")
  @WebResult(name = "indice", partName = "indice",
      targetNamespace = "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/indice-e")
  public TipoIndice getIndiceExpediente(@WebParam(name = "identificador") @XmlElement(
      required = true, name = "identificador") String identificador) throws InsideWSException;

  @WebMethod(operationName = "getExpedienteMetadatos", action = "urn:getExpedienteMetadatos")
  @WebResult(name = "metadatos", partName = "metadatos",
      targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e/metadatos")
  public ExpedienteInsideMetadatos getExpedienteMetadatos(
      @WebParam(name = "identificador") @XmlElement(required = true,
          name = "identificador") String identificador,
      @WebParam(name = "version") @XmlElement(name = "version") Integer version)
      throws InsideWSException;

  @WebMethod(operationName = "getVersionesExpediente", action = "urn:getVersionesExpediente")
  @WebResult(name = "versiones", partName = "versiones",
      targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/version")
  public List<TipoVersionInside> getVersionesExpediente(
      @WebParam(name = "identificador") @XmlElement(required = true,
          name = "identificador") String identificador)
      throws InsideWSException;

  @WebMethod(operationName = "altaDocumentoOriginal", action = "urn:altaDocumentoOriginal")
  @WebResult(name = "DocumentoInsideInfo", partName = "DocumentoInsideInfo",
      targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/documentoInfo")
  public DocumentoInsideInfo altaDocumentoOriginal(
      @WebParam(name = "documento") @XmlElement(required = true,
          name = "documento") TipoDocumentoAltaInside documento)
      throws InsideWSException;

  @WebMethod(operationName = "altaDocumentoDigitalizado", action = "urn:altaDocumentoDigitalizado")
  @WebResult(name = "DocumentoInsideInfo", partName = "DocumentoInsideInfo",
      targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/documentoInfo")
  public DocumentoInsideInfo altaDocumentoDigitalizado(
      @WebParam(name = "documento") @XmlElement(required = true,
          name = "documento") TipoDocumentoAltaInside documento)
      throws InsideWSException;

  @WebMethod(operationName = "altaDocumentoCopiaParcial", action = "urn:altaDocumentoCopiaParcial")
  @WebResult(name = "DocumentoInsideInfo", partName = "DocumentoInsideInfo",
      targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/documentoInfo")
  public DocumentoInsideInfo altaDocumentoCopiaParcial(
      @WebParam(name = "documento") @XmlElement(required = true,
          name = "documento") TipoDocumentoAltaInside documento,
      @WebParam(name = "identificadorDocumentoOrigen") @XmlElement(required = true,
          name = "identificadorDocumentoOrigen") String identificadorDocumentoOrigen)
      throws InsideWSException;

  @WebMethod(operationName = "altaDocumentoCambioFormato",
      action = "urn:altaDocumentoCambioFormato")
  @WebResult(name = "DocumentoInsideInfo", partName = "DocumentoInsideInfo",
      targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/documentoInfo")
  public DocumentoInsideInfo altaDocumentoCambioFormato(
      @WebParam(name = "documento") @XmlElement(required = true,
          name = "documento") TipoDocumentoAltaInside documento,
      @WebParam(name = "identificadorDocumentoOrigen") @XmlElement(required = true,
          name = "identificadorDocumentoOrigen") String identificadorDocumentoOrigen)
      throws InsideWSException;

  @WebMethod(operationName = "altaDocumentoOtros", action = "urn:altaDocumentoOtros")
  @WebResult(name = "DocumentoInsideInfo", partName = "DocumentoInsideInfo",
      targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/documentoInfo")
  public DocumentoInsideInfo altaDocumentoOtros(
      @WebParam(name = "documento") @XmlElement(required = true,
          name = "documento") TipoDocumentoAltaInside documento)
      throws InsideWSException;

  @WebMethod(operationName = "altaDocumentoEni", action = "urn:altaDocumentoEni")
  @WebResult(name = "DocumentoInsideInfo", partName = "DocumentoInsideInfo",
      targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/documentoInfo")
  public DocumentoInsideInfo altaDocumentoEni(
      @WebParam(name = "documento") @XmlElement(required = true,
          name = "documento") TipoDocumento documento,
      @WebParam(name = "asociadoaExpediente") @XmlElement(required = false,
          name = "asociadoaExpediente") TipoDocumentoAsociadoaExpediente asociadoaExpediente,
      @WebParam(name = "metadatosAdicionales") @XmlElement(required = false,
          name = "metadatosAdicionales") TipoMetadatosAdicionales metadatosAdicionales,
      @WebParam(name = "firmaServidor") @XmlElement(required = false,
          name = "firmaServidor") boolean firmaServidor)
      throws InsideWSException;

  @WebMethod(operationName = "altaDocumentoEniXml", action = "urn:altaDocumentoEniXml")
  @WebResult(name = "DocumentoInsideInfo", partName = "DocumentoInsideInfo",
      targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/documentoInfo")
  public DocumentoInsideInfo altaDocumentoEniXml(
      @WebParam(name = "documentoEniFile") @XmlElement(required = true,
          name = "documentoEniFile") TipoDocumentoEniFileInside documentoEniFile)
      throws InsideWSException;

  @WebMethod(operationName = "modificaDocumento", action = "urn:modificaDocumento")
  @WebResult(name = "DocumentoInsideInfo", partName = "DocumentoInsideInfo",
      targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/documentoInfo")
  public DocumentoInsideInfo modificaDocumento(
      @WebParam(name = "documento") @XmlElement(required = true,
          name = "documento") TipoDocumento documento,
      @WebParam(name = "metadatosAdicionales") @XmlElement(required = true,
          name = "metadatosAdicionales") TipoMetadatosAdicionales metadatosAdicionales)
      throws InsideWSException;

  @WebMethod(operationName = "getDocumento", action = "urn:getDocumento")
  @WebResult(name = "documento", partName = "documento",
      targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e")
  public TipoDocumentoInside getDocumento(@WebParam(name = "identificador") @XmlElement(
      required = true, name = "identificador") String identificador) throws InsideWSException;

  @WebMethod(operationName = "getDocumentoEni", action = "urn:getDocumentoEni")
  @WebResult(name = "documento", partName = "documento",
      targetNamespace = "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e")
  public TipoDocumento getDocumentoEni(@WebParam(name = "identificador") @XmlElement(
      required = true, name = "identificador") String identificador) throws InsideWSException;

  @WebMethod(operationName = "getDocumentoEniFile", action = "urn:getDocumentoEniFile")
  @WebResult(name = "documentoEniFile", partName = "documentoEniFile",
      targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/documentoEniFile")
  public TipoDocumentoEniFileInside getDocumentoEniFile(
      @WebParam(name = "identificador") @XmlElement(required = true,
          name = "identificador") String identificador)
      throws InsideWSException;

  @WebMethod(operationName = "getDocumentoContenido", action = "urn:getDocumentoContenido")
  @WebResult(name = "documento", partName = "documento",
      targetNamespace = "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/contenido")
  public TipoContenido getDocumentoContenido(@WebParam(name = "identificador") @XmlElement(
      required = true, name = "identificador") String identificador) throws InsideWSException;

  @WebMethod(operationName = "getMetadatosDocumento", action = "urn:getMetadatosDocumento")
  @WebResult(name = "metadatos", partName = "metadatos",
      targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/metadatos")
  public DocumentoInsideMetadatos getMetadatosDocumento(
      @WebParam(name = "identificador") @XmlElement(required = true,
          name = "identificador") String identificador)
      throws InsideWSException;

  @WebMethod(operationName = "getNumeroBytesDocumentoFirmado",
      action = "urn:getNumeroBytesDocumentoFirmado")
  @WebResult(name = "numeroBytes", partName = "numeroBytes",
      targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/version")
  public Integer getNumeroBytesDocumentoFirmado(@WebParam(name = "identificador") @XmlElement(
      required = true, name = "identificador") String identificador) throws InsideWSException;

  @WebMethod(operationName = "getFirmasEniCSVDocumento", action = "urn:getFirmasEniCSVDocumento")
  @WebResult(name = "firmasCSV", partName = "firmasCSV",
      targetNamespace = "http://administracionelectronica.gob.es/ENI/XSD/v1.0/firma")
  public Firmas getFirmasEniCSVDocumento(@WebParam(name = "identificador") @XmlElement(
      required = true, name = "identificador") String identificador) throws InsideWSException;

  @WebMethod(operationName = "buscarExpedientes", action = "urn:buscarExpedientes")
  @WebResult(name = "resultados", partName = "resultados",
      targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/busqueda/resultados")
  public ExpedienteResultadoBusqueda buscarExpedientes(
      @WebParam(name = "consulta") @XmlElement(required = true,
          name = "consulta") ConsultaWsInside consulta,
      @WebParam(name = "limite") @XmlElement(required = false, name = "limite") int limite,
      @WebParam(name = "pagina") @XmlElement(required = false, name = "pagina") int pagina)
      throws InsideWSException;

  @WebMethod(operationName = "buscarDocumentos", action = "urn:buscarDocumentos")
  @WebResult(name = "resultados", partName = "resultados",
      targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/busqueda/resultados")
  public DocumentoResultadoBusqueda buscarDocumentos(
      @WebParam(name = "consulta") @XmlElement(required = true,
          name = "consulta") ConsultaWsInside consulta,
      @WebParam(name = "limite") @XmlElement(required = false, name = "limite") int limite,
      @WebParam(name = "pagina") @XmlElement(required = false, name = "pagina") int pagina)
      throws InsideWSException;

  @WebMethod(operationName = "eliminarDocumento", action = "urn:eliminarDocumento")
  public void eliminarDocumento(@WebParam(name = "identificador") @XmlElement(required = true,
      name = "identificador") String identificador) throws InsideWSException;

  @WebMethod(operationName = "eliminarExpediente", action = "urn:eliminarExpediente")
  public void eliminarExpediente(@WebParam(name = "identificador") @XmlElement(required = true,
      name = "identificador") String identificador) throws InsideWSException;

  @WebMethod(operationName = "expedientesAsociadoDocumento",
      action = "urn:expedientesAsociadoDocumento")
  @WebResult(name = "expediente", partName = "expediente",
      targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e/expedienteInfo")
  public List<ExpedienteInsideInfo> expedientesAsociadoDocumento(
      @WebParam(name = "identificador") @XmlElement(required = true,
          name = "identificador") String identificador)
      throws InsideWSException;

  @WebMethod(operationName = "visualizarDocumento", action = "urn:visualizarDocumento")
  @WebResult(name = "resultadoVisualizacion", partName = "resultadoVisualizacion",
      targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/visualizacion/documento-e")
  public TipoResultadoVisualizacionDocumentoInside visualizarDocumento(
      @WebParam(name = "identificador") @XmlElement(required = true,
          name = "identificador") String identificador)
      throws InsideWSException;

  @WebMethod(operationName = "remisionAJusticia", action = "urn:remisionAJusticia")
  @WebResult(name = "RespuestaRemisionAJusticiaType", partName = "RespuestaRemisionAJusticiaType",
      targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/remisionNube")
  public RespuestaRemisionAJusticiaType remisionAJusticia(
      @WebParam(name = "peticionRemisionAJusticiaType") @XmlElement(required = true,
          name = "peticionRemisionAJusticiaType") PeticionRemisionAJusticiaType peticionRemisionAJusticiaType)
      throws InsideWSException;

  @WebMethod(operationName = "getPdfExpediente", action = "urn:getPdfExpediente")
  @WebResult(name = "RespuestaPdfExpediente", partName = "RespuestaPdfExpediente",
      targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/respuestaPdfExpediente")
  public RespuestaPdfExpediente getPdfExpediente(@WebParam(name = "expedienteEni") @XmlElement(
      required = true, name = "expedienteEni") byte[] expedienteEni) throws InsideWSException;
}
