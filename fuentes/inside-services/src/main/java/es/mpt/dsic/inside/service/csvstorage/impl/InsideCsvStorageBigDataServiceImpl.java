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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import csvstorage.es.gob.aapp.csvstorage.webservices.bigaDataTransfer.document.v1.CSVDocumentBigDataService;
import csvstorage.es.gob.aapp.csvstorage.webservices.bigaDataTransfer.document.v1.CSVDocumentBigDataService_Service;
import csvstorage.es.gob.aapp.csvstorage.webservices.bigaDataTransfer.document.v1.CSVStorageException;
import csvstorage.es.gob.aapp.csvstorage.webservices.bigaDataTransfer.document.v1.GuardarDocumento;
import csvstorage.es.gob.aapp.csvstorage.webservices.bigaDataTransfer.document.v1.ModificarDocumento;
import csvstorage.es.gob.aapp.csvstorage.webservices.bigaDataTransfer.document.v1.ObtenerDocumento;
import csvstorage.es.gob.aapp.csvstorage.webservices.bigaDataTransfer.document.v1.ObtenerInfoContenido;
import csvstorage.es.gob.aapp.csvstorage.webservices.bigaDataTransfer.document.v1.model.ContenidoInfoBigData;
import csvstorage.es.gob.aapp.csvstorage.webservices.bigaDataTransfer.document.v1.model.GuardarDocumentoBigDataRequest;
import csvstorage.es.gob.aapp.csvstorage.webservices.bigaDataTransfer.document.v1.model.GuardarDocumentoResponse;
import csvstorage.es.gob.aapp.csvstorage.webservices.bigaDataTransfer.document.v1.model.GuardarDocumentoUuidResponse;
import csvstorage.es.gob.aapp.csvstorage.webservices.bigaDataTransfer.document.v1.model.ObtenerDocumentoBigDataResponse;
import csvstorage.es.gob.aapp.csvstorage.webservices.bigaDataTransfer.document.v1.model.ObtenerDocumentoRequest;
import csvstorage.es.gob.aapp.csvstorage.webservices.bigaDataTransfer.document.v1.model.ObtenerInfoContenidoResponse;
import csvstorage.es.gob.aapp.csvstorage.webservices.bigaDataTransfer.document.v1.model.WSCredential;
import es.gob.aapp.eeutil.bigDataTransfer.exception.BigDataTransferException;
import es.gob.aapp.eeutil.bigDataTransfer.service.BigDataTransferService;

@Service
@PropertySource("file:${config.path}/csvstorage.properties")
public class InsideCsvStorageBigDataServiceImpl {

  protected static final Log logger = LogFactory.getLog(InsideCsvStorageBigDataServiceImpl.class);

  @Value("${csvstorage.bigData.url}")
  private String urlBigDataService;

  @Value("${csvstorage.url}")
  private String urlCsvDocumentBigDataService;

  @Value("${csvstorage.bigData.pathTemp}")
  private String rutaTemp;

  @Value("${csvstorage.idaplicacion}")
  private String idaplicacion;

  @Value("${csvstorage.password}")
  private String password;

  @Value("${csvstorage.imprimirMensaje}")
  private String imprimirMensaje;

  @Value("${csvstorage.recevive.url}")
  private String reciveUrl;

  private CSVDocumentBigDataService csvDocumentBigDataService;

  @Autowired
  BigDataTransferService bigDataTransferService;

  private WSCredential getCredentials() {
    WSCredential wsCredential = new WSCredential();
    wsCredential.setIdaplicacion(idaplicacion);
    wsCredential.setPassword(password);
    return wsCredential;
  }

  @PostConstruct
  private void init() {
    logger.warn("Inicializar ruta temp big data transfer a variable csvstorage.bigData.pathTemp");
    System.setProperty("bigDataTransfer.tmpDir", rutaTemp);
    String TMP_DIR = System.getProperty("bigDataTransfer.tmpDir") != null
        ? System.getProperty("bigDataTransfer.tmpDir")
        : System.getProperty("java.io.tmpdir");
    logger.warn(TMP_DIR);
  }

  @SuppressWarnings("rawtypes")
  public void configure() {
    try {
      if (csvDocumentBigDataService == null) {
        URL url = new URL(urlCsvDocumentBigDataService);

        CSVDocumentBigDataService_Service csvServive = new CSVDocumentBigDataService_Service(url);

        csvDocumentBigDataService = csvServive.getCSVDocumentBigDataServicePort();

        if (Boolean.valueOf(imprimirMensaje)) {
          BindingProvider bindingProvider = (BindingProvider) csvDocumentBigDataService;
          Binding binding = bindingProvider.getBinding();
          List<Handler> handlerChain = binding.getHandlerChain();
          handlerChain.add(new ImprimirPeticionSoap());
          binding.setHandlerChain(handlerChain);
        }
      }

    } catch (MalformedURLException e) {
      logger.error(
          "No se puede crear la URL del servicio de visualizacion " + urlCsvDocumentBigDataService,
          e);
    }
  }

  public GuardarDocumentoUuidResponse guardarDocumentoCsvStorage(String dir3, String idEni,
      byte[] contenido, String mimeType) throws CSVStorageException {

    logger.debug("Entra en guardarDocumentoCsvStorage");
    // Creamos la peticion que vamos a hacerle a csvStorage para guardar el
    // documento ENI
    GuardarDocumentoUuidResponse response = null;
    try {
      configure();

      List<String> idFiles = sendFiles(contenido);

      GuardarDocumento guardarDocumento = new GuardarDocumento();
      guardarDocumento.setCredential(getCredentials());
      guardarDocumento
          .setGuardarDocumentoRequest(crearPeticionDocumento(dir3, idEni, idFiles, mimeType));
      response = this.csvDocumentBigDataService.guardarDocumento(guardarDocumento);

    } catch (Exception e) {
      logger.error("No se puede crear la petición al csvStorage " + urlCsvDocumentBigDataService,
          e);
    }
    logger.debug("Sale de guardarDocumentoCsvStorage");
    return response;
  }

  public GuardarDocumentoUuidResponse guardarDocumentoCsvStorage(String dir3, String idEni,
      File contenido, String mimeType) throws CSVStorageException {

    logger.debug("Entra en guardarDocumentoCsvStorage");
    // Creamos la peticion que vamos a hacerle a csvStorage para guardar el
    // documento ENI
    GuardarDocumentoUuidResponse response = null;
    try {
      configure();

      List<String> idFiles = sendFiles(contenido);

      GuardarDocumento guardarDocumento = new GuardarDocumento();
      guardarDocumento.setCredential(getCredentials());
      guardarDocumento
          .setGuardarDocumentoRequest(crearPeticionDocumento(dir3, idEni, idFiles, mimeType));
      response = this.csvDocumentBigDataService.guardarDocumento(guardarDocumento);

    } catch (Exception e) {
      logger.error("No se puede crear la petición al csvStorage " + urlCsvDocumentBigDataService,
          e);
      throw new CSVStorageException(e.getMessage(), e);
    }
    logger.debug("Sale de guardarDocumentoCsvStorage");
    return response;
  }

  private GuardarDocumentoBigDataRequest crearPeticionDocumento(String dir3, String idEni,
      List<String> idFiles, String mimeType) {
    GuardarDocumentoBigDataRequest guardarDocumentoRequest = new GuardarDocumentoBigDataRequest();
    guardarDocumentoRequest.setDir3(dir3);
    guardarDocumentoRequest.setIdEni(idEni);
    ContenidoInfoBigData contenidoInfoBigData = new ContenidoInfoBigData();
    contenidoInfoBigData.getFiles().addAll(idFiles);
    contenidoInfoBigData.setTipoMIME(mimeType);
    guardarDocumentoRequest.setContenido(contenidoInfoBigData);
    return guardarDocumentoRequest;
  }

  private List<String> sendFiles(byte[] contenido) throws IOException, BigDataTransferException {
    String path = UUID.randomUUID().toString();
    FileOutputStream fout = new FileOutputStream(path);
    fout.write(contenido);
    fout.flush();
    fout.close();
    List<String> idFiles = bigDataTransferService.sendFile(path, urlBigDataService);
    File file = new File(path);
    file.delete();
    return idFiles;
  }

  private List<String> sendFiles(File contenido) throws IOException, BigDataTransferException {
    List<String> idFiles =
        bigDataTransferService.sendFile(contenido.getAbsolutePath(), urlBigDataService);
    return idFiles;
  }

  public GuardarDocumentoResponse modificarDocumentoCsvStorage(String dir3, String idEni,
      byte[] contenido, String mimeType) throws CSVStorageException {
    logger.debug("Entra en modificarDocumentoCsvStorage");
    GuardarDocumentoResponse response = null;
    // Creamos la peticion que vamos a hacerle a csvStorage para guardar el documento ENI
    try {
      configure();

      List<String> idFiles = sendFiles(contenido);

      ModificarDocumento modificarDocumento = new ModificarDocumento();
      modificarDocumento.setCredential(getCredentials());
      modificarDocumento
          .setModificarDocumentoRequest(crearPeticionDocumento(dir3, idEni, idFiles, mimeType));
      response = this.csvDocumentBigDataService.modificarDocumento(modificarDocumento);
    } catch (Exception e) {
      logger.error("No se puede crear la petición al csvStorage " + urlCsvDocumentBigDataService,
          e);
    }
    logger.debug("Sale de modificarDocumentoCsvStorage");

    return response;
  }

  public GuardarDocumentoResponse modificarDocumentoCsvStorage(String dir3, String idEni,
      File contenido, String mimeType) throws CSVStorageException {
    logger.debug("Entra en modificarDocumentoCsvStorage");
    GuardarDocumentoResponse response = null;
    // Creamos la peticion que vamos a hacerle a csvStorage para guardar el documento ENI
    try {
      configure();

      List<String> idFiles = sendFiles(contenido);

      ModificarDocumento modificarDocumento = new ModificarDocumento();
      modificarDocumento.setCredential(getCredentials());
      modificarDocumento
          .setModificarDocumentoRequest(crearPeticionDocumento(dir3, idEni, idFiles, mimeType));
      response = this.csvDocumentBigDataService.modificarDocumento(modificarDocumento);
    } catch (Exception e) {
      logger.error("No se puede crear la petición al csvStorage " + urlCsvDocumentBigDataService,
          e);
    }
    logger.debug("Sale de modificarDocumentoCsvStorage");

    return response;
  }

  public byte[] obtenerDocumentoCsvStorage(String identificador, String dir3)
      throws CSVStorageException {
    try {
      logger.debug("Entra en obtenerDocumentoCsvStorage");
      configure();
      ObtenerDocumento peticion = new ObtenerDocumento();
      peticion.setCredential(getCredentials());
      peticion.setObtenerDocumentoRequest(
          crearPeticionObtenerDocumentoByDocumento(identificador, dir3));
      peticion.setUrl(reciveUrl);

      ObtenerDocumentoBigDataResponse documentoRecuperado =
          this.csvDocumentBigDataService.obtenerDocumento(peticion);

      logger.warn("recibido lista documento");
      if (documentoRecuperado.getDocumentoResponse().getContenido().getFiles() != null) {
        for (String file : documentoRecuperado.getDocumentoResponse().getContenido().getFiles()) {
          logger.warn("documento recibido: " + rutaTemp + file);
        }
      }

      File file = new File(bigDataTransferService
          .reciveFile(documentoRecuperado.getDocumentoResponse().getContenido().getFiles()));
      logger.debug("Sale de obtenerDocumentoCsvStorage");
      logger.warn("Fichero unido:" + file.getAbsolutePath());
      byte[] data = FileUtils.readFileToByteArray(file);
      file.delete();
      return data;
    } catch (BigDataTransferException | IOException e) {
      logger.error(e.getMessage(), e);
      throw new CSVStorageException(e.getMessage(), e);
    }
  }

  private ObtenerDocumentoRequest crearPeticionObtenerDocumentoByDocumento(String idEni,
      String dir3) {

    ObtenerDocumentoRequest obtenerDocumentoRequest = new ObtenerDocumentoRequest();
    obtenerDocumentoRequest.setDir3(dir3);
    obtenerDocumentoRequest.setIdEni(idEni);

    return obtenerDocumentoRequest;
  }

  public ObtenerInfoContenidoResponse obtenerContenidoUuid(String uuid) throws CSVStorageException {
    try {
      logger.debug("Entra en obtenerContenidoUuid");
      configure();

      ObtenerInfoContenido infoContenido = new ObtenerInfoContenido();
      infoContenido.setCredential(getCredentials());
      infoContenido.setUuid(uuid);

      return this.csvDocumentBigDataService.obtenerInfoContenido(infoContenido);
    } catch (CSVStorageException e) {
      logger.error(e.getMessage(), e);
      throw new CSVStorageException(e.getMessage(), e);
    }
  }

  public long getContentSizeByUuid(String uuid) throws CSVStorageException {
    logger.debug("Entra en getContentSizeByUuid");
    configure();

    return this.csvDocumentBigDataService.obtenerTamanioDocumento(getCredentials(), uuid) * 1024
        * 1024;
  }

  public String getValorHuellaDocumento(String uuid, String algoritmo) throws CSVStorageException {
    logger.debug("Entra en getValorHuellaDocumento");
    configure();
    return this.csvDocumentBigDataService.obtenerHashDocumento(getCredentials(), uuid, algoritmo);
  }

}
