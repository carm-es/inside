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

package es.mpt.dsic.inside.service.csvstorage.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.soap.MTOMFeature;
import org.apache.axiom.attachments.ByteArrayDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.springframework.beans.factory.annotation.Required;
import csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.CSVDocumentMtomService;
import csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.CSVDocumentMtomService_Service;
import csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.CSVStorageException;
import csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.EliminarDocumento;
import csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.GuardarDocumento;
import csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.ModificarDocumento;
import csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.model.ContenidoMtomInfo;
import csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.model.DocumentoEniMtomResponse;
import csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.model.DocumentoMtomResponse;
import csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.model.EliminarDocumentoRequest;
import csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.model.EliminarDocumentoResponse;
import csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.model.GuardarDocumentoMtomRequest;
import csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.model.GuardarDocumentoResponse;
import csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.model.ObtenerDocumentoEniRequest;
import csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.model.ObtenerDocumentoRequest;
import csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.model.WSCredential;
import es.mpt.dsic.inside.service.csvstorage.InsideCsvStorageMtomService;

public class InsideCsvStorageMtomImpl implements InsideCsvStorageMtomService {

  protected static final Log logger = LogFactory.getLog(InsideCsvStorageMtomImpl.class);

  private Properties properties;

  private Long connectionTimeout;
  private Long receiveTimeout;

  private CSVDocumentMtomService csvDocumentMtomService;

  public Long getConnectionTimeout() {
    return connectionTimeout;
  }

  public void setConnectionTimeout(Long connectionTimeout) {
    this.connectionTimeout = connectionTimeout;
  }

  public Long getReceiveTimeout() {
    return receiveTimeout;
  }

  public void setReceiveTimeout(Long receiveTimeout) {
    this.receiveTimeout = receiveTimeout;
  }

  public void configure() {
    try {
      if (csvDocumentMtomService == null) {
        URL url = new URL(properties.getProperty("csvstorage.mtom.url"));

        CSVDocumentMtomService_Service csvService = new CSVDocumentMtomService_Service(url);

        csvDocumentMtomService = csvService.getCSVDocumentMtomServicePort(new MTOMFeature());

        Client client = ClientProxy.getClient(csvDocumentMtomService);
        HTTPConduit httpConduit = (HTTPConduit) client.getConduit();
        HTTPClientPolicy policy = null;
        if (this.getConnectionTimeout() != null) {
          policy = httpConduit.getClient();
          policy.setConnectionTimeout(this.getConnectionTimeout());
        }
        if (this.getReceiveTimeout() != null) {
          if (policy == null) {
            policy = httpConduit.getClient();
          }
          policy.setReceiveTimeout(this.getReceiveTimeout());
        }
        if (policy != null) {
          httpConduit.setClient(policy);
        }

        if (Boolean.valueOf((properties.getProperty("csvstorage.imprimirMensaje")))) {
          BindingProvider bindingProvider = (BindingProvider) csvDocumentMtomService;
          Binding binding = bindingProvider.getBinding();
          List<Handler> handlerChain = binding.getHandlerChain();
          handlerChain.add(new ImprimirPeticionSoap());
          binding.setHandlerChain(handlerChain);
        }
      }
    } catch (MalformedURLException e) {
      logger.error("No se puede crear la URL del servicio de visualizacion "
          + properties.getProperty("csvstorage.url"), e);
    }
  }

  @Override
  public GuardarDocumentoResponse guardarDocumentoCsvStorage(String dir3, String idEni,
      byte[] contenido, String mimeType) throws CSVStorageException {

    logger.debug("Entra en guardarDocumentoCsvStorage");
    // Creamos la peticion que vamos a hacerle a csvStorage para guardar el documento ENI
    GuardarDocumentoResponse response = null;
    try {

      configure();
      GuardarDocumentoMtomRequest guardarDocumentoRequest =
          crearPeticionDocumento(dir3, idEni, contenido, mimeType);
      GuardarDocumento guardarDocumento = new GuardarDocumento();
      guardarDocumento.setCredential(getCredentials());
      guardarDocumento.setGuardarDocumentoRequest(guardarDocumentoRequest);

      response = this.csvDocumentMtomService.guardarDocumento(guardarDocumento);


    } catch (Exception e) {
      logger.error(
          "No se puede crear la petición al csvStorage " + properties.getProperty("csvstorage.url"),
          e);
    }
    logger.debug("Sale de guardarDocumentoCsvStorage");
    return response;
  }

  @Override
  public GuardarDocumentoResponse guardarDocumentoCsvStorageCsv(String dir3, String csv,
      byte[] contenido, String mimeType) throws CSVStorageException {

    logger.debug("Entra en guardarDocumentoCsvStorage");
    // Creamos la peticion que vamos a hacerle a csvStorage para guardar el documento ENI
    GuardarDocumentoResponse response = null;
    try {

      configure();
      GuardarDocumentoMtomRequest guardarDocumentoRequest =
          crearPeticionDocumentoCsv(dir3, csv, contenido, mimeType);
      GuardarDocumento guardarDocumento = new GuardarDocumento();
      guardarDocumento.setCredential(getCredentials());
      guardarDocumento.setGuardarDocumentoRequest(guardarDocumentoRequest);

      response = this.csvDocumentMtomService.guardarDocumento(guardarDocumento);


    } catch (Exception e) {
      logger.error(
          "No se puede crear la petición al csvStorage " + properties.getProperty("csvstorage.url"),
          e);
    }
    logger.debug("Sale de guardarDocumentoCsvStorage");
    return response;
  }

  @Override
  public GuardarDocumentoResponse modificarDocumentoCsvStorage(String dir3, String idEni,
      byte[] contenido, String mimeType) throws CSVStorageException {

    logger.debug("Entra en modificarDocumentoCsvStorage");
    GuardarDocumentoResponse response = null;

    // Creamos la peticion que vamos a hacerle a csvStorage para guardar el documento ENI
    try {

      configure();
      GuardarDocumentoMtomRequest guardarDocumentoRequest =
          crearPeticionDocumento(dir3, idEni, contenido, mimeType);
      ModificarDocumento modificarDocumento = new ModificarDocumento();
      modificarDocumento.setCredential(getCredentials());
      modificarDocumento.setModificarDocumentoRequest(guardarDocumentoRequest);

      response = this.csvDocumentMtomService.modificarDocumento(modificarDocumento);

    } catch (Exception e) {
      logger.error(
          "No se puede crear la petición al csvStorage " + properties.getProperty("csvstorage.url"),
          e);
    }
    logger.debug("Sale de modificarDocumentoCsvStorage");

    return response;
  }


  @Override
  public GuardarDocumentoResponse guardarFirmaCsvStorage(String dir3, String idEni,
      byte[] contenido, String mimeType) throws CSVStorageException {
    logger.debug("Entra en guardarFirmaCsvStorage");

    configure();
    GuardarDocumentoMtomRequest guardarDocumentoRequest =
        crearPeticionFirma(idEni, dir3, contenido, mimeType);
    GuardarDocumento guardarDocumento = new GuardarDocumento();
    guardarDocumento.setCredential(getCredentials());
    guardarDocumento.setGuardarDocumentoRequest(guardarDocumentoRequest);

    GuardarDocumentoResponse response =
        this.csvDocumentMtomService.guardarDocumento(guardarDocumento);


    logger.debug("Sale de guardarFirmaCsvStorage");

    return response;
  }

  @Override
  public GuardarDocumentoResponse modificarFirmaCsvStorage(String dir3, String idEni,
      byte[] contenido, String mimeType) throws CSVStorageException {
    logger.debug("Entra en modificarFirmaCsvStorage");
    configure();
    GuardarDocumentoMtomRequest guardarDocumentoRequest =
        crearPeticionFirma(idEni, dir3, contenido, mimeType);
    ModificarDocumento modificarDocumento = new ModificarDocumento();
    modificarDocumento.setCredential(getCredentials());
    modificarDocumento.setModificarDocumentoRequest(guardarDocumentoRequest);

    GuardarDocumentoResponse response =
        this.csvDocumentMtomService.modificarDocumento(modificarDocumento);
    logger.debug("Sale de modificarFirmaCsvStorage");
    return response;
  }


  @Override
  public DocumentoMtomResponse obtenerDocumentoCsvStorage(String identificador, String dir3)
      throws CSVStorageException {
    logger.debug("Entra en obtenerDocumentoEniCsvStorage");
    configure();
    ObtenerDocumentoRequest peticion =
        this.crearPeticionObtenerDocumentoByDocumento(identificador, dir3);

    DocumentoMtomResponse documentoRecuperado =
        this.csvDocumentMtomService.obtenerDocumento(getCredentials(), peticion);
    logger.debug("Sale de obtenerDocumentoEniCsvStorage");
    return documentoRecuperado;
  }

  @Override
  public DocumentoMtomResponse obtenerDocumentoCsvStorageCsv(String csv, String dir3)
      throws CSVStorageException {
    logger.debug("Entra en obtenerDocumentoEniCsvStorage");
    configure();
    ObtenerDocumentoRequest peticion = this.crearPeticionObtenerDocumentoByDocumentoCsv(csv, dir3);

    DocumentoMtomResponse documentoRecuperado =
        this.csvDocumentMtomService.obtenerDocumento(getCredentials(), peticion);
    logger.debug("Sale de obtenerDocumentoEniCsvStorage");
    return documentoRecuperado;
  }


  @Override
  public DocumentoMtomResponse obtenerFirmaCsvStorage(String nombreFirma, String dir3)
      throws CSVStorageException {
    logger.debug("Entra en obtenerFirmaCsvStorage");
    configure();
    ObtenerDocumentoRequest peticion = this.crearPeticionObtenerFirma(nombreFirma, dir3);
    DocumentoMtomResponse documentoRecuperado =
        this.csvDocumentMtomService.obtenerDocumento(getCredentials(), peticion);
    logger.debug("Sale de obtenerFirmaCsvStorage");
    return documentoRecuperado;
  }

  @Override
  public DocumentoEniMtomResponse obtenerDocumentoEniCsvStorage(String identificador, String dir3)
      throws CSVStorageException {
    logger.debug("Entra en obtenerDocumentoEniCsvStorage");
    configure();
    ObtenerDocumentoEniRequest peticion =
        this.crearPeticionObtenerDocumentoEniByDocumento(identificador, dir3);

    DocumentoEniMtomResponse documentoEniRecuperado =
        this.csvDocumentMtomService.obtenerDocumentoENI(getCredentials(), peticion);
    logger.debug("Sale de obtenerDocumentoEniCsvStorage");
    return documentoEniRecuperado;
  }


  @Override
  public EliminarDocumentoResponse eliminarDocumento(String identificador, String dir3)
      throws CSVStorageException {
    logger.debug("Entra en eliminarDocumento");
    configure();
    EliminarDocumentoRequest eliminarDocumentoRequest = new EliminarDocumentoRequest();
    eliminarDocumentoRequest.setDir3(dir3);
    eliminarDocumentoRequest.setIdENI(identificador);

    EliminarDocumento eliminarDocumento = new EliminarDocumento();
    eliminarDocumento.setCredential(getCredentials());
    eliminarDocumento.setEliminarDocumentoRequest(eliminarDocumentoRequest);

    EliminarDocumentoResponse eliminarDocumentoResponse =
        this.csvDocumentMtomService.eliminarDocumento(eliminarDocumento);

    logger.debug("Sale de eliminarDocumento");
    return eliminarDocumentoResponse;
  }

  private WSCredential getCredentials() {
    WSCredential wsCredential = new WSCredential();
    wsCredential.setIdaplicacion(properties.getProperty("csvstorage.idaplicacion"));
    wsCredential.setPassword(properties.getProperty("csvstorage.password"));

    return wsCredential;
  }

  private ObtenerDocumentoRequest crearPeticionObtenerFirma(String nombreFirma, String dir3) {

    ObtenerDocumentoRequest obtenerDocumentoRequest = new ObtenerDocumentoRequest();
    obtenerDocumentoRequest.setDir3(dir3);
    obtenerDocumentoRequest.setIdEni(nombreFirma);

    return obtenerDocumentoRequest;
  }


  private ObtenerDocumentoRequest crearPeticionObtenerDocumentoByDocumento(String idEni,
      String dir3) {

    ObtenerDocumentoRequest obtenerDocumentoRequest = new ObtenerDocumentoRequest();
    obtenerDocumentoRequest.setDir3(dir3);
    obtenerDocumentoRequest.setIdEni(idEni);

    return obtenerDocumentoRequest;
  }

  private ObtenerDocumentoRequest crearPeticionObtenerDocumentoByDocumentoCsv(String csv,
      String dir3) {

    ObtenerDocumentoRequest obtenerDocumentoRequest = new ObtenerDocumentoRequest();
    obtenerDocumentoRequest.setDir3(dir3);
    obtenerDocumentoRequest.setCsv(csv);

    return obtenerDocumentoRequest;
  }

  private GuardarDocumentoMtomRequest crearPeticionDocumento(String dir3, String idEni,
      byte[] contenido, String mimeType) {

    GuardarDocumentoMtomRequest guardarDocumentoRequest = new GuardarDocumentoMtomRequest();

    guardarDocumentoRequest.setDir3(dir3);
    guardarDocumentoRequest.setIdEni(idEni);

    ContenidoMtomInfo contenidoMtomInfo = new ContenidoMtomInfo();
    contenidoMtomInfo.setContenido(new DataHandler(new ByteArrayDataSource(contenido)));
    contenidoMtomInfo.setTipoMIME(mimeType);

    guardarDocumentoRequest.setContenido(contenidoMtomInfo);

    return guardarDocumentoRequest;
  }

  private GuardarDocumentoMtomRequest crearPeticionDocumentoCsv(String dir3, String csv,
      byte[] contenido, String mimeType) {

    GuardarDocumentoMtomRequest guardarDocumentoRequest = new GuardarDocumentoMtomRequest();

    guardarDocumentoRequest.setDir3(dir3);
    guardarDocumentoRequest.setCsv(csv);

    ContenidoMtomInfo contenidoMtomInfo = new ContenidoMtomInfo();
    contenidoMtomInfo.setContenido(new DataHandler(new ByteArrayDataSource(contenido)));
    contenidoMtomInfo.setTipoMIME(mimeType);

    guardarDocumentoRequest.setContenido(contenidoMtomInfo);

    return guardarDocumentoRequest;
  }


  private GuardarDocumentoMtomRequest crearPeticionFirma(String id, String dir3, byte[] firma,
      String mimetype) {

    GuardarDocumentoMtomRequest guardarDocumentoRequest = new GuardarDocumentoMtomRequest();

    guardarDocumentoRequest.setDir3(dir3);
    guardarDocumentoRequest.setIdEni(id);

    ContenidoMtomInfo contenido = new ContenidoMtomInfo();
    contenido.setContenido(new DataHandler(new ByteArrayDataSource(firma, mimetype)));
    contenido.setTipoMIME(mimetype);

    guardarDocumentoRequest.setContenido(contenido);

    return guardarDocumentoRequest;
  }


  private ObtenerDocumentoEniRequest crearPeticionObtenerDocumentoEniByDocumento(
      String identificador, String dir3) {

    ObtenerDocumentoEniRequest obtenerDocumentoEniRequest = new ObtenerDocumentoEniRequest();
    obtenerDocumentoEniRequest.setIdENI(identificador);
    obtenerDocumentoEniRequest.setDir3(dir3);

    return obtenerDocumentoEniRequest;
  }



  public static XMLGregorianCalendar dateToXMLGregorianCalendar(Date fecha)
      throws DatatypeConfigurationException {

    GregorianCalendar gcal = new GregorianCalendar();
    gcal.setTime(fecha);
    XMLGregorianCalendar xmlGregCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
    return xmlGregCal;

  }


  public Properties getProperties() {
    return properties;
  }

  @Required
  public void setProperties(Properties properties) {
    this.properties = properties;
  }

  @Override
  public Long obtenerTamanioDocumento(String dir3, String idEni) throws CSVStorageException {
    logger.debug("Entra en guardarDocumentoCsvStorage");
    // Creamos la peticion que vamos a hacerle a csvStorage para guardar el documento ENI
    Long response = null;
    try {

      configure();
      ObtenerDocumentoRequest obtenerDocumentoRequest =
          crearPeticionObtenerDocumentoByDocumento(idEni, dir3);

      response = this.csvDocumentMtomService.obtenerTamanioDocumento(getCredentials(),
          obtenerDocumentoRequest);


    } catch (Exception e) {
      logger.error(
          "No se puede crear la petición al csvStorage " + properties.getProperty("csvstorage.url"),
          e);
    }
    logger.debug("Sale de guardarDocumentoCsvStorage");
    return response;
  }
}
