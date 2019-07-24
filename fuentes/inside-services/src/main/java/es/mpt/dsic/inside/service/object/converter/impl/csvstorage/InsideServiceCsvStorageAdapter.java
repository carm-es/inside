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

package es.mpt.dsic.inside.service.object.converter.impl.csvstorage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.annotation.PostConstruct;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import csvstorage.es.gob.aapp.csvstorage.webservices.bigaDataTransfer.document.v1.model.ObtenerInfoContenidoResponse;
import csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.CSVStorageException;
import csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.model.DocumentoMtomResponse;
import csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.model.EliminarDocumentoResponse;
import csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.model.GuardarDocumentoResponse;
import es.mpt.dsic.inside.model.converter.InsideConverterDocumento;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInside;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInsideContenido;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteInside;
import es.mpt.dsic.inside.model.objetos.expediente.metadatos.ObjetoExpedienteInsideMetadatos;
import es.mpt.dsic.inside.model.objetos.firmas.FirmaInside;
import es.mpt.dsic.inside.model.objetos.firmas.contenido.ContenidoFirmaCertificadoAlmacenableInside;
import es.mpt.dsic.inside.model.objetos.firmas.contenido.ContenidoFirmaInside;
import es.mpt.dsic.inside.service.csvstorage.InsideCsvStorageMtomService;
import es.mpt.dsic.inside.service.csvstorage.impl.InsideCsvStorageBigDataServiceImpl;
import es.mpt.dsic.inside.service.exception.InSideServiceTemporalDataException;
import es.mpt.dsic.inside.service.object.converter.impl.InsideServiceAdapter;
import es.mpt.dsic.inside.service.object.converter.impl.InsideServiceAdapterException;
import es.mpt.dsic.inside.service.store.InsideServiceJta;
import es.mpt.dsic.inside.service.temporalData.TemporalDataBusinessService;
import es.mpt.dsic.inside.service.util.Constantes;
import es.mpt.dsic.inside.service.util.FileUtils;
import es.mpt.dsic.inside.service.util.InsideUtils;
import es.mpt.dsic.inside.service.util.JAXBMarshallerDocument;
import es.mpt.dsic.inside.store.hibernate.entity.InsideWsAplicacion;
import es.mpt.dsic.inside.store.hibernate.entity.UnidadOrganica;
import es.mpt.dsic.inside.store.hibernate.entity.UnidadUsuario;
import es.mpt.dsic.inside.store.hibernate.entity.UnidadWsAplicacion;
import es.mpt.dsic.inside.store.hibernate.entity.UsuarioInside;
import es.mpt.dsic.inside.xml.eni.documento.TipoDocumento;

// TODO: Auto-generated Javadoc
/**
 * The Class InsideServiceCsvStorageAdapter.
 */
public class InsideServiceCsvStorageAdapter implements InsideServiceAdapter {

  /** The Constant logger. */
  protected static final Log logger = LogFactory.getLog(InsideServiceCsvStorageAdapter.class);

  /** The inside csv storage service. */
  @Autowired
  private InsideCsvStorageMtomService insideCsvStorageMtomService;

  @Autowired
  private InsideCsvStorageBigDataServiceImpl insideCsvStorageBigDataServiceImpl;

  @Autowired
  private InsideServiceJta insideServiceJta;

  private SessionFactory sessionFactory;

  private Properties properties;

  private String dir3default;
  private String almacenarFirma;

  @Autowired
  FileUtils fileUtils;

  @Autowired
  TemporalDataBusinessService temporalDataBusinessService;

  @PostConstruct
  void config() {
    dir3default = properties.getProperty("csvstorage.dir3");
    almacenarFirma = properties.getProperty("csvstorage.almacenarFirma");
  }

  /**
   * Almacena expediente eni.
   *
   * @param expediente the expediente
   * @param bytesContenido the bytes contenido
   * @throws InsideServiceAdapterException the inside service csv storage adapter exception
   */
  public ObjetoExpedienteInside almacenaObjetoExpedienteInside(ObjetoExpedienteInside expediente,
      byte[] bytesContenido) throws InsideServiceAdapterException {
    String dirusuario = null;
    try {
      logger.info("Entra en almacenaExpedienteEni");

      // Obtenemos el dir3 del usuario logado
      dirusuario = getUserDir();

      if (dirusuario == null) {
        dirusuario = dir3default;
      }

      ObjetoExpedienteInsideMetadatos metadatosEntrada = expediente.getMetadatos();

      if (metadatosEntrada == null) {
        throw new InsideServiceAdapterException(
            "Los metadatos del Expediente de entrada no pueden ser nulos");
      }

      // El expediente no se guarda en la NAS solo se guarda la firma
      if (expediente.getIdentificadorRepositorio() == null) {
        expediente.setIdentificadorRepositorio(expediente.getIdentificador());
      }

      // Almacenaremos la firma si es almacenable
      if (expediente.getIndice() != null && expediente.getIndice().getFirmas() != null
          && Boolean.toString(true).equals(almacenarFirma)) {
        this.almacenaFirmasInside(expediente.getIndice().getFirmas(), expediente.getIdentificador(),
            dirusuario, true);
      }
      logger.info("Sale de almacenaExpedienteEni");

    } catch (InsideServiceAdapterException e) {
      logger.error("Error almacenando el cml del ExpedinteInside: " + expediente != null
          ? expediente.getIdentificador()
          : "" + " Usuario: " + dirusuario);
      throw new InsideServiceAdapterException("Error almacenando el cml del ExpedinteInside", e);
    }
    return expediente;
  }

  /**
   * Recupera from repository.
   *
   * @param expediente the expediente
   * @return the objeto expediente inside
   * @throws InsideServiceAdapterException the inside service csv storage adapter exception
   */
  public ObjetoExpedienteInside recuperaExpedienteInside(ObjetoExpedienteInside expediente)
      throws InsideServiceAdapterException {
    logger.info("Entra en recuperaExpedienteInside");
    if (StringUtils.isEmpty(expediente.getIdentificadorRepositorio())) {
      throw new InsideServiceAdapterException(
          "El identificador del repositorio del expediente no puede ser nulo");
    }

    try {
      logger.debug("IdentificadorRepositorio: " + expediente.getIdentificadorRepositorio());
      // En el almacenamiento en Alfresco no se está recuperando el solo
      // se están recuperando las firmas
      if (expediente.getIndice().getFirmas() != null) {

        for (FirmaInside firma : expediente.getIndice().getFirmas()) {
          ContenidoFirmaInside contenidoFirma = firma.getContenidoFirma();
          if (contenidoFirma != null
              && contenidoFirma instanceof ContenidoFirmaCertificadoAlmacenableInside) {
            ContenidoFirmaCertificadoAlmacenableInside contenidoFirmaAlmacenado =
                (ContenidoFirmaCertificadoAlmacenableInside) contenidoFirma;
            logger.debug("contenidoFirmaAlmacenado IdentificadorRepositorio: "
                + contenidoFirmaAlmacenado.getIdentificadorRepositorio());

            if (contenidoFirmaAlmacenado.getIdentificadorRepositorio() != null
                && contenidoFirmaAlmacenado.getIdentificadorRepositorio().contains("/")) {
              String[] nombreSplit =
                  contenidoFirmaAlmacenado.getIdentificadorRepositorio().split("/");

              String id = null;
              String dir3 = null;

              if (nombreSplit.length > 1) {
                dir3 = nombreSplit[0];
                id = nombreSplit[1];
              } else {
                id = nombreSplit[0];
                dir3 = dir3default;
              }

              DocumentoMtomResponse documentoResponse =
                  insideCsvStorageMtomService.obtenerFirmaCsvStorage(id, dir3);

              if (documentoResponse != null
                  && documentoResponse.getCodigo().equals(Constantes.csvStorage.GUARDADOCORRECTO)) {
                DataHandler bytesFirma = documentoResponse.getContenido().getContenido();
                byte[] inputBytesFirma = IOUtils.toByteArray(bytesFirma.getInputStream());
                contenidoFirmaAlmacenado.setValorBinario(inputBytesFirma);
              }
            }

          }
        }

      }
      logger.info("Sale de recuperaExpedienteInside");

    } catch (CSVStorageException e) {
      logger.error("Error al recuperar las firmas del expediente");
      throw new InsideServiceAdapterException("Error al recuperar las firmas del expediente", e);
    } catch (IOException e) {
      logger.error("Error al recuperar las firmas del expediente");
      throw new InsideServiceAdapterException("Error al recuperar las firmas del expediente", e);
    }

    return expediente;

  }

  /**
   * Almacenar firma.
   *
   * @param expediente the expediente
   * @throws InsideServiceAdapterException the inside service cmis adapter exception
   */
  private void almacenaFirmasInside(List<FirmaInside> firmas, String idENI, String dir3,
      boolean guardar) throws InsideServiceAdapterException {
    logger.info("Entra en almacenaFirmasInside");
    for (FirmaInside firma : firmas) {
      if (firma.getContenidoFirma() != null) {
        ContenidoFirmaInside contenidoFirma = firma.getContenidoFirma();
        if (contenidoFirma instanceof ContenidoFirmaCertificadoAlmacenableInside) {
          ContenidoFirmaCertificadoAlmacenableInside contenidoFirmaAlmacenado =
              (ContenidoFirmaCertificadoAlmacenableInside) contenidoFirma;
          try {
            if (contenidoFirmaAlmacenado.getMime() != null
                && contenidoFirmaAlmacenado.getMime().equals("application/octect-stream")) {
              contenidoFirmaAlmacenado.setMime("application/octet-stream");
            } else if (contenidoFirmaAlmacenado.getMime() != null
                && contenidoFirmaAlmacenado.getMime().equals("application/xml")) {
              contenidoFirmaAlmacenado.setMime("text/xml");
            }
            // El idENI de la firma que almacenamos en el csvstorage
            // lo componemos con el idENI del expediente +
            // IdentificadorEnDocumento (FIRMA_0, FIRMA_1, etc...)
            String firmaName = idENI + "_" + firma.getIdentificadorEnDocumento();
            logger.debug("firmaName: " + firmaName);
            if (contenidoFirmaAlmacenado.getValorBinario() != null) {

              // El IdentificadorRepositorio se compone del
              // dir3/identificador de la firma
              contenidoFirmaAlmacenado.setIdentificadorRepositorio(dir3 + "/" + firmaName);

              if (guardar) {
                GuardarDocumentoResponse response =
                    insideCsvStorageMtomService.guardarFirmaCsvStorage(dir3, firmaName,
                        contenidoFirmaAlmacenado.getValorBinario(),
                        contenidoFirmaAlmacenado.getMime());

                if (response != null && response.getResponse() != null
                    && response.getResponse().getCodigo().equals(Constantes.csvStorage.YAEXISTE)) {
                  logger.warn("No se ha guardado la firma porque ya existe: "
                      + response.getResponse().getCodigo() + " - "
                      + response.getResponse().getDescripcion());
                  // Llamamos a modificar
                  response = insideCsvStorageMtomService.modificarFirmaCsvStorage(dir3, firmaName,
                      contenidoFirmaAlmacenado.getValorBinario(),
                      contenidoFirmaAlmacenado.getMime());
                }

                if (response != null && response.getResponse() != null && !response.getResponse()
                    .getCodigo().equals(Constantes.csvStorage.GUARDADOCORRECTO)) {
                  logger.error("No se ha guardado la firma porque se ha producido un error: "
                      + response.getResponse().getCodigo() + " - "
                      + response.getResponse().getDescripcion());
                  // Lanzamos excepción
                  throw new CSVStorageException("Se ha producido un error al guardar la firma");
                } else {
                  contenidoFirmaAlmacenado.setValorBinario(null);
                }
              }
            }

          } catch (CSVStorageException e) {
            logger.error("Error almacenando la firma de idENI: " + idENI + " Usuario: " + dir3);
            throw new InsideServiceAdapterException("Error almacenando la firma");
          }
        }

      }
    }
    logger.info("Sale de almacenaFirmasInside");

  }

  /**
   * Almacena objeto documento inside.
   * 
   * @param documento the documento
   * @throws InsideServiceAdapterException the inside service cmis adapter exception
   */
  public ObjetoDocumentoInside almacenaObjetoDocumentoInside(ObjetoDocumentoInside documento)
      throws InsideServiceAdapterException {
    String dirusuario = "";
    try {
      logger.info("Entra en almacenaObjetoDocumentoInside");

      // Obtenemos el dir3 del usuario logado
      dirusuario = getUserDir();

      if (dirusuario == null) {
        dirusuario = dir3default;
      }

      logger.debug("IdentificadorRepositorio: " + documento.getIdentificadorRepositorio());

      guardarContenidoDocumento(documento, dirusuario);

      TipoDocumento tipoDocumento = InsideConverterDocumento.documentoInsideToEni(documento, null);

      byte[] contentMarshall = null;
      JAXBMarshallerDocument marshaller = new JAXBMarshallerDocument();
      String docMarshall = marshaller.marshallDataDocument(tipoDocumento,
          "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e", "documento",
          "enidoc");
      contentMarshall = docMarshall.getBytes();

      String mimeType = "application/xml";

      if (documento.getIdentificadorRepositorio() != null) {

        String[] identifiadorSplit = documento.getIdentificadorRepositorio().split("/");

        String dir3 = dir3default;

        if (identifiadorSplit.length > 1) {
          dir3 = identifiadorSplit[0];
        } else {
          dir3 = dir3default;
        }

        GuardarDocumentoResponse responseMtom =
            insideCsvStorageMtomService.modificarDocumentoCsvStorage(dir3,
                documento.getIdentificador(), contentMarshall, mimeType);

        if (responseMtom == null || (responseMtom.getResponse() != null && !responseMtom
            .getResponse().getCodigo().equals(Constantes.csvStorage.GUARDADOCORRECTO))) {
          documento.setIdentificadorRepositorio(null);
          throw new InsideServiceAdapterException("No se ha guardado el documento");
        }
      } else {
        guardarDocumentoEni(documento, contentMarshall, dirusuario, mimeType);
      }

      logger.info("Sale de almacenaObjetoDocumentoInside");
    } catch (csvstorage.es.gob.aapp.csvstorage.webservices.bigaDataTransfer.document.v1.CSVStorageException e) {
      logger.error("Error almacenando el documento: "
          + (documento != null ? documento.getIdentificador() : "") + " Usuario: " + dirusuario);
      throw new InsideServiceAdapterException("Error almacenando el documento");
    } catch (Exception e) {
      logger.error("Error almacenando el documento: "
          + (documento != null ? documento.getIdentificador() : "") + " Usuario: " + dirusuario);
      throw new InsideServiceAdapterException("Error almacenando el documento");
    }
    return documento;
  }

  /**
   * Recupera documento inside.
   *
   * @param documento the documento
   * @return the objeto documento inside
   * @throws InsideServiceAdapterException the inside service cmis adapter exception
   */
  public ObjetoDocumentoInside recuperaDocumentoInside(ObjetoDocumentoInside documento)
      throws InsideServiceAdapterException {
    logger.info("Entra en recuperaDocumentoInside");

    if (documento.getIdentificadorRepositorio() != null) {

      String[] identifiadorSplit = documento.getIdentificadorRepositorio().split("/");

      String id = null;
      String dir3 = dir3default;

      if (identifiadorSplit.length > 1) {
        dir3 = identifiadorSplit[0];
        id = identifiadorSplit[1];
      } else {
        id = identifiadorSplit[0];
        dir3 = dir3default;
      }

      byte[] contenido = null;

      try {
        DocumentoMtomResponse documentoRespone =
            insideCsvStorageMtomService.obtenerDocumentoCsvStorage(id, dir3);
        if (documentoRespone.getCodigo().equals("0")
            && documentoRespone.getContenido().getContenido() != null) {
          contenido =
              IOUtils.toByteArray(documentoRespone.getContenido().getContenido().getInputStream());
        }

        if (documento.getContenido() == null) {
          ObjetoDocumentoInsideContenido contenidoInside = new ObjetoDocumentoInsideContenido();
          documento.setContenido(contenidoInside);
        }

        if (contenido != null) {
          this.convertDocumentResponseToObjetoDocumentoInside(contenido, documento);
        }
      } catch (CSVStorageException | IOException e) {
        logger.error("Error al recuperar el contenido");
        throw new InsideServiceAdapterException("Error al recuperar el documento");
      }
    }

    logger.info("Sale de recuperaDocumentoInside");

    return documento;

  }

  /**
   * Convert document eni response to objeto documento inside.
   *
   * @param documentoENIrespone the documento en irespone
   * @param documento the documento
   * @return the objeto documento inside
   * @throws InsideServiceAdapterException error
   */
  private void convertDocumentResponseToObjetoDocumentoInside(byte[] contenido,
      ObjetoDocumentoInside documento) throws InsideServiceAdapterException {
    try {
      boolean esValido = false;
      if (contenido != null) {
        Object contenidoDeserializado = null;
        try {
          JAXBMarshallerDocument marshaller = new JAXBMarshallerDocument();
          TipoDocumento tipoDocumento = marshaller.unmarshallDataDocument(contenido);

          ObjetoDocumentoInside documentoAux = InsideConverterDocumento
              .documentoEniAndMetadatosToDocumentoInside(tipoDocumento, null);
          documento.setContenido(documentoAux.getContenido());
          documento.setFirmas(documentoAux.getFirmas());

          esValido = true;
        } catch (Exception e) {
          logger.info("El contenido no es un documeto ENI");
          // Para recuperar documentos de versiones anteriores
          // almacenados como ObjetoDocumentoInside
          contenidoDeserializado = SerializationUtils.deserialize(contenido);
          if (contenidoDeserializado instanceof ObjetoDocumentoInside) {

            ObjetoDocumentoInside documentoAux = (ObjetoDocumentoInside) contenidoDeserializado;
            documento.setContenido(documentoAux.getContenido());
            documento.setFirmas(documentoAux.getFirmas());

            esValido = true;

          }

        }

        if (!esValido) {
          logger.error("Error al convertir el documento a ENI o deserializarlo");
          throw new InsideServiceAdapterException("Error recuperar el documento");
        }
      }
    } catch (Exception e) {
      logger.error("Error al convertir el documento", e);
      throw new InsideServiceAdapterException("Error recuperar el documento");
    }

  }

  private String getUserDir() {

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String nif = auth.getName();
    String dir3 = null;
    Integer idUnidad = null;

    List<Criterion> criterias = new ArrayList<Criterion>();
    criterias.add(Restrictions.eq("nif", nif));
    UsuarioInside usuario =
        (UsuarioInside) insideServiceJta.findObjeto(UsuarioInside.class, criterias);

    if (usuario != null) {

      criterias = new ArrayList<Criterion>();
      criterias.add(Restrictions.eq("idUsuario", usuario.getId()));
      criterias.add(Restrictions.eq("activo", true));
      List<UnidadUsuario> usuariosUnidad =
          insideServiceJta.getAllObjetosCriteria(UnidadUsuario.class, criterias);

      if (usuariosUnidad != null && usuariosUnidad.size() > 0) {
        idUnidad = usuariosUnidad.get(0).getUnidad().getId();
      }

    } else {
      criterias = new ArrayList<Criterion>();
      criterias.add(Restrictions.eq("nombre", nif));
      InsideWsAplicacion aplicacion =
          (InsideWsAplicacion) insideServiceJta.findObjeto(InsideWsAplicacion.class, criterias);

      criterias = new ArrayList<Criterion>();
      criterias.add(Restrictions.eq("idAplicacion", aplicacion.getId()));
      criterias.add(Restrictions.eq("activo", true));
      List<UnidadWsAplicacion> aplicacionesUnidad =
          insideServiceJta.getAllObjetosCriteria(UnidadWsAplicacion.class, criterias);

      if (aplicacionesUnidad != null && aplicacionesUnidad.size() > 0) {
        idUnidad = aplicacionesUnidad.get(0).getIdUnidad();
      }
    }

    if (idUnidad != null) {
      Session session = null;
      try {
        session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(UnidadOrganica.class);
        criteria.add(Restrictions.eq("id", idUnidad));
        Object value = criteria.uniqueResult();

        if (value != null) {
          dir3 = ((UnidadOrganica) value).getCodigoUnidadOrganica();
        }
      } catch (HibernateException e) {
        logger.error(e);
      } finally {
        session.close();
      }

    }

    return dir3;
  }

  /**
   * Elimina documento inside.
   *
   * @param documento the documento
   * @return the objeto documento inside
   * @throws InsideServiceAdapterException the inside service cmis adapter exception
   */
  public ObjetoDocumentoInside eliminaDocumentoInside(ObjetoDocumentoInside documento)
      throws InsideServiceAdapterException {
    logger.info("Entra en eliminaDocumentoInside");

    if (documento.getIdentificadorRepositorio() != null) {

      String[] identifiadorSplit = documento.getIdentificadorRepositorio().split("/");

      String id = null;
      String dir3 = dir3default;

      if (identifiadorSplit.length > 1) {
        dir3 = identifiadorSplit[0];
        id = identifiadorSplit[1];
      } else {
        id = identifiadorSplit[0];
        dir3 = dir3default;
      }

      try {
        EliminarDocumentoResponse eliminarDocumentoResponse =
            insideCsvStorageMtomService.eliminarDocumento(id, dir3);

        if (eliminarDocumentoResponse.getResponse() == null
            || !eliminarDocumentoResponse.getResponse().getCodigo().equals("0")) {
          logger.warn("El documento " + id + " no se ha eliminado del repositorio: "
              + eliminarDocumentoResponse.getResponse().getCodigo() + " - "
              + eliminarDocumentoResponse.getResponse().getDescripcion());
        }

      } catch (CSVStorageException e) {
        logger.error("Error al eliminar el documento");
        throw new InsideServiceAdapterException("Error al eliminar el documento");
      }
    }

    logger.info("Sale de eliminaDocumentoInside");

    return documento;

  }

  /**
   * Delete from repository.
   *
   * @param expediente the expediente
   * @return the objeto expediente inside
   * @throws InsideServiceAdapterException the inside service csv storage adapter exception
   */
  public ObjetoExpedienteInside eliminaExpedienteInside(ObjetoExpedienteInside expediente)
      throws InsideServiceAdapterException {
    logger.info("Entra en recuperaExpedienteInside");
    if (StringUtils.isEmpty(expediente.getIdentificadorRepositorio())) {
      throw new InsideServiceAdapterException(
          "El identificador del repositorio del expediente no puede ser nulo");
    }

    try {
      logger.debug("IdentificadorRepositorio: " + expediente.getIdentificadorRepositorio());
      // Se están recuperando las firmas que son las que se almacenan
      if (expediente.getIndice().getFirmas() != null) {

        for (FirmaInside firma : expediente.getIndice().getFirmas()) {
          ContenidoFirmaInside contenidoFirma = firma.getContenidoFirma();
          if (contenidoFirma != null
              && contenidoFirma instanceof ContenidoFirmaCertificadoAlmacenableInside) {
            ContenidoFirmaCertificadoAlmacenableInside contenidoFirmaAlmacenado =
                (ContenidoFirmaCertificadoAlmacenableInside) contenidoFirma;
            logger.debug("contenidoFirmaAlmacenado IdentificadorRepositorio: "
                + contenidoFirmaAlmacenado.getIdentificadorRepositorio());

            if (StringUtils.isNotEmpty(contenidoFirmaAlmacenado.getIdentificadorRepositorio())) {
              String[] nombreSplit =
                  contenidoFirmaAlmacenado.getIdentificadorRepositorio().split("/");

              String id = null;
              String dir3 = null;

              if (nombreSplit.length > 1) {
                dir3 = nombreSplit[0];
                id = nombreSplit[1];
              } else {
                id = nombreSplit[0];
                dir3 = dir3default;
              }

              EliminarDocumentoResponse eliminarDocumentoResponse =
                  insideCsvStorageMtomService.eliminarDocumento(id, dir3);

              if (eliminarDocumentoResponse.getResponse() == null
                  || !eliminarDocumentoResponse.getResponse().getCodigo().equals("0")) {
                logger
                    .warn("La firma del expediente " + id + " no se ha eliminado del repositorio: "
                        + eliminarDocumentoResponse.getResponse().getCodigo() + " - "
                        + eliminarDocumentoResponse.getResponse().getDescripcion());
              }
            }
          }
        }

      }
      logger.info("Sale de recuperaExpedienteInside");

    } catch (CSVStorageException e) {
      logger.error("Error al recuperar las firmas del expediente");
      throw new InsideServiceAdapterException("Error al recuperar las firmas del expediente", e);
    }

    return expediente;

  }

  public SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  /**
   * @return the properties
   */
  public Properties getProperties() {
    return properties;
  }

  /**
   * @param properties the properties to set
   */
  public void setProperties(Properties properties) {
    this.properties = properties;
  }

  private void guardarContenidoDocumento(ObjetoDocumentoInside documento, String dirusuario)
      throws csvstorage.es.gob.aapp.csvstorage.webservices.bigaDataTransfer.document.v1.CSVStorageException,
      InsideServiceAdapterException {
    csvstorage.es.gob.aapp.csvstorage.webservices.bigaDataTransfer.document.v1.model.GuardarDocumentoUuidResponse responsebigData =
        null;
    if (StringUtils.isNotEmpty(documento.getContenido().getReferencia())) {
      File contenido = new File(documento.getContenido().getReferencia());
      if (contenido.exists()) {
        String urlReferencia = properties.getProperty("csvstorage.bigData.urlcontent");

        responsebigData = insideCsvStorageBigDataServiceImpl.guardarDocumentoCsvStorage(dirusuario,
            documento.getIdentificador() + "_CONTENIDO", contenido,
            documento.getContenido().getMime());

        documento.getContenido()
            .setReferencia(urlReferencia + responsebigData.getResponseUuid().getUuid());

        // Si el documento ya existe se modifica
        if (responsebigData != null && responsebigData.getResponseUuid() != null && responsebigData
            .getResponseUuid().getCodigo().equals(Constantes.csvStorage.YAEXISTE)) {

          csvstorage.es.gob.aapp.csvstorage.webservices.bigaDataTransfer.document.v1.model.GuardarDocumentoResponse responseModif;
          responseModif = insideCsvStorageBigDataServiceImpl.modificarDocumentoCsvStorage(
              dirusuario, documento.getIdentificador() + "_CONTENIDO", contenido,
              documento.getContenido().getMime());

          if (responseModif == null || (responseModif.getResponse() != null && !responseModif
              .getResponse().getCodigo().equals(Constantes.csvStorage.GUARDADOCORRECTO))) {
            documento.setIdentificadorRepositorio(null);
            throw new InsideServiceAdapterException("No se ha guardado el contenido del documento");
          }
        }
      }
    }
  }

  private void guardarDocumentoEni(ObjetoDocumentoInside documento, byte[] contentMarshall,
      String dirusuario, String mimeType)
      throws CSVStorageException, InsideServiceAdapterException {
    // El IdentificadorRepositorio se compone del dir3/identificador
    // del documento
    documento.setIdentificadorRepositorio(dirusuario + "/" + documento.getIdentificador());

    GuardarDocumentoResponse responseMtom = insideCsvStorageMtomService.guardarDocumentoCsvStorage(
        dirusuario, documento.getIdentificador(), contentMarshall, mimeType);

    // Si el documento ya existe se modifica
    if (responseMtom != null && responseMtom.getResponse() != null
        && responseMtom.getResponse().getCodigo().equals(Constantes.csvStorage.YAEXISTE)) {
      responseMtom = insideCsvStorageMtomService.modificarDocumentoCsvStorage(dirusuario,
          documento.getIdentificador(), contentMarshall, mimeType);
    }

    if (responseMtom == null || (responseMtom.getResponse() != null && !responseMtom.getResponse()
        .getCodigo().equals(Constantes.csvStorage.GUARDADOCORRECTO))) {
      documento.setIdentificadorRepositorio(null);
      throw new InsideServiceAdapterException("No se ha guardado el documento");
    }
  }

  @Override
  public ObjetoDocumentoInsideContenido getcontenidoByUuid(String uuid, String idSession)
      throws InsideServiceAdapterException,
      csvstorage.es.gob.aapp.csvstorage.webservices.bigaDataTransfer.document.v1.CSVStorageException {
    ObjetoDocumentoInsideContenido retorno = new ObjetoDocumentoInsideContenido();
    InputStream in = null;
    URL urlStorage = null;
    HttpURLConnection urlConnection = null;
    try {
      ObtenerInfoContenidoResponse data =
          insideCsvStorageBigDataServiceImpl.obtenerContenidoUuid(uuid);
      retorno.setMime(data.getContenidoUuidInfo().getTipoMIME());
      retorno.setNombreFormato(
          InsideUtils.getNombreFormatoByMime(data.getContenidoUuidInfo().getTipoMIME()));
      retorno.setIdentificadorEnDocumento(data.getContenidoUuidInfo().getNombre());

      // abrimos la conexion url
      urlStorage = new URL(data.getContenidoUuidInfo().getUrl());
      urlConnection = (HttpURLConnection) urlStorage.openConnection();

      urlConnection.setConnectTimeout(1200000);

      String userpass = properties.getProperty("csvstorage.idaplicacion") + ":"
          + properties.getProperty("csvstorage.password");
      String basicAuth = "Basic " + new String(new Base64().encode(userpass.getBytes()));
      urlConnection.setRequestProperty("Authorization", basicAuth);

      // conectacmos al contenido del storage
      in = urlConnection.getInputStream();

      String path = temporalDataBusinessService.createEmptyFile(idSession, uuid);

      temporalDataBusinessService.fillFile(path, in);
      retorno.setReferencia(path);
    } catch (IOException | InSideServiceTemporalDataException e) {
      throw new InsideServiceAdapterException(e.getMessage(), e);
    } finally {
      try {
        if (in != null) {
          in.close();
        }
        if (urlConnection != null) {
          urlConnection.disconnect();
        }
      } catch (IOException e) {
        logger.error("No se ha podido descargar el contenido externo:" + e);
      }
    }

    return retorno;
  }

  @Override
  public long getContentSizeByUuid(String uuid) throws InsideServiceAdapterException {
    try {
      return insideCsvStorageBigDataServiceImpl.getContentSizeByUuid(uuid);
    } catch (csvstorage.es.gob.aapp.csvstorage.webservices.bigaDataTransfer.document.v1.CSVStorageException e) {
      logger.error("Error al obtener tamanio de documento:" + uuid);
      throw new InsideServiceAdapterException(e.getMessage(), e);
    }
  }

  @Override
  public String getValorHuellaDocumento(String uuid, String algoritmo)
      throws InsideServiceAdapterException {
    try {
      logger.debug("Entra en getContentSizeByUuid");
      return insideCsvStorageBigDataServiceImpl.getValorHuellaDocumento(uuid, algoritmo);
    } catch (csvstorage.es.gob.aapp.csvstorage.webservices.bigaDataTransfer.document.v1.CSVStorageException e) {
      throw new InsideServiceAdapterException(e.getMessage(), e);
    }
  }

}
