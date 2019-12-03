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

package es.mpt.dsic.integration.cmis.adapter;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.DocumentType;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ObjectId;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.enums.Action;
import org.apache.chemistry.opencmis.commons.enums.BaseTypeId;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;
import org.apache.chemistry.opencmis.commons.exceptions.CmisBaseException;
import org.apache.chemistry.opencmis.commons.exceptions.CmisContentAlreadyExistsException;
import org.apache.chemistry.opencmis.commons.exceptions.CmisObjectNotFoundException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import es.mpt.dsic.integration.cmis.adapter.interceptor.annotation.CmisSession;
import es.mpt.dsic.integration.cmis.exception.RepositoryCmisException;
import es.mpt.dsic.integration.cmis.model.ContenidoDocumento;
import es.mpt.dsic.integration.cmis.session.CmisSessionManager;
import es.mpt.dsic.integration.cmis.utils.GestionErroresUtils;
import es.mpt.dsic.integration.cmis.utils.CmisUtils;
import es.mpt.dsic.integration.cmis.utils.FolderCmisUtils;

public class CmisAdapter implements CmisAdapterInterface {

  // implements RepoDocIntegration {

  protected static final Log logger = LogFactory.getLog(CmisAdapter.class);

  public static final String PROP_DOCUMENTO_FOLDER = "documentoFolder";

  private CmisSessionManager sessionManager;

  private static final String pathSeparator = "/";

  // CARM ### v2.0.8.2
  private String numberRetriesGetFolderWithHalfSecondDelayToAvoidConcurrentCreation;

  public String getNumberRetriesGetFolderWithHalfSecondDelayToAvoidConcurrentCreation() {
    return this.numberRetriesGetFolderWithHalfSecondDelayToAvoidConcurrentCreation;
  }

  public void setNumberRetriesGetFolderWithHalfSecondDelayToAvoidConcurrentCreation(
      final String numberRetriesGetFolderWithHalfSecondDelayToAvoidConcurrentCreation) {
    this.numberRetriesGetFolderWithHalfSecondDelayToAvoidConcurrentCreation =
        numberRetriesGetFolderWithHalfSecondDelayToAvoidConcurrentCreation;
  }
  // CARM 2.0.8.2 ###

  @CmisSession
  /**
   * Da de alta una carpeta en el repositorio
   * 
   * @param path La ruta completa de la carpeta
   * @param objectTypeId Tipo del objeto que va a crearse. Si es nulo se le da el genérico de
   *        carpeta de CMIS.
   * @param metadatos Metadatos de la carpeta. Puede ser nulo si la carpeta no los tiene.
   * @return
   * @throws RepositoryCmisException
   */
  public Folder altaCarpeta(final String[] path, final String objectTypeId,
      final Map<String, Object> metadatos) throws RepositoryCmisException {
    FolderCmisUtils.assertPathIsNotEmpty(path);
    String[] cleanedPath = FolderCmisUtils.cleanPath(path);
    if (cleanedPath.length == 1) {
      return this.altaCarpeta(cleanedPath[0], null, objectTypeId, metadatos, false);
    } else {
      return this.altaCarpeta(cleanedPath[cleanedPath.length - 1],
          Arrays.copyOf(cleanedPath, cleanedPath.length - 1), objectTypeId, metadatos, true);
    }

  }

  @CmisSession
  /**
   * Da de alta una carpeta en el repositorio.
   * 
   * @param folderName Nombre de la carpeta.
   * @param ruta Estructura de directorios en que se quiere crear la carpeta.
   * @param objectTypeId Tipo del objeto que va a crearse. Si es nulo se le da el genérico de
   *        carpeta de CMIS.
   * @param metadatos Metadatos de la carpeta. Puede ser nulo si la carpeta no los tiene.
   * @param crearRuta Indica si debe crearse la estructura de directorios en caso de que ésta no
   *        exista.
   * @throws RepositoryCmisException cuando no pueda darse de alta la carpeta.
   */
  public Folder altaCarpeta(final String folderName, final String[] ruta, final String objectTypeId,
      final Map<String, Object> metadatos, boolean crearRuta) throws RepositoryCmisException {

    if (folderName == null) {
      throw new IllegalArgumentException("Debe especificar el nombre de la carpeta");
    }

    String[] cleanedRuta = (ruta == null) ? null : FolderCmisUtils.cleanPath(ruta);

    try {

      String completePath = sessionManager.getRootFolder().getPath();
      if (cleanedRuta != null && cleanedRuta.length > 0) {
        completePath += pathSeparator + StringUtils.join(cleanedRuta, pathSeparator);
      }
      completePath += pathSeparator + folderName;

      logger.debug(String.format("Intento recuperar la carpeta %s", completePath));
      CmisObject folder = sessionManager.getSession().getObjectByPath(completePath);
      if (!(folder instanceof Folder)) {
        throw new RepositoryCmisException("folder no es un Folder");
      }
      // return CmisUtils.getMetadatos((Folder) folder);
      return (Folder) folder;
    } catch (CmisObjectNotFoundException e) {
      logger.debug(String.format("No existe la carpeta %s en el path %s , la crearé", folderName,
          StringUtils.join(cleanedRuta, pathSeparator)));
    }

    try {

      Folder parent = getFolder(cleanedRuta, crearRuta
      // CARM ### v2.0.8.2
          , Integer.valueOf(this.numberRetriesGetFolderWithHalfSecondDelayToAvoidConcurrentCreation)
      // CARM ### v2.0.8.2
      );


      final Map<String, Object> folderProperties = new HashMap<String, Object>();

      folderProperties.put(PropertyIds.PARENT_ID, parent.getId());

      if (objectTypeId == null) {
        folderProperties.put(PropertyIds.OBJECT_TYPE_ID, BaseTypeId.CMIS_FOLDER.value());
      } else {
        folderProperties.put(PropertyIds.OBJECT_TYPE_ID, objectTypeId);
      }

      folderProperties.put(PropertyIds.NAME, folderName);

      if (metadatos != null) {
        for (final Entry<String, Object> metadato : metadatos.entrySet()) {
          folderProperties.put(metadato.getKey(), metadato.getValue());
        }
      }
      logger.debug(String.format("Creando carpeta '%s' en  '%s'", folderName,
          StringUtils.join(cleanedRuta, pathSeparator)));

      // return CmisUtils.getMetadatos(parent.createFolder(folderProperties));
      return parent.createFolder(folderProperties);

    } catch (final RepositoryCmisException rce) {
      throw rce;
    } catch (final CmisBaseException e) {
      logger.error("Error al dar de alta la carpeta [" + folderName + "]: " + e.getMessage(), e);
      logger.error("ErrorContent" + e.getErrorContent());
      throw new RepositoryCmisException("Error al dar de alta la carpeta [" + folderName + "]:"
          + GestionErroresUtils.getMessageByException(e) + " " + e.getMessage(), e);
    } catch (final Exception e) {
      logger.error(
          "Error no controlado al dar de alta la carpeta [" + folderName + "]: " + e.getMessage(),
          e);
      throw new RepositoryCmisException("Error al dar de alta la carpeta [" + folderName + "]:"
          + GestionErroresUtils.getMessageByException(e) + " " + e.getMessage(), e);
    }
  }


  @CmisSession
  public Folder modificaCarpeta(final String folderId, final Map<String, Object> metadatos)
      throws RepositoryCmisException {
    try {
      Folder folder = (Folder) getCmisObjectById(folderId);
      final Map<String, Object> folderProperties = new HashMap<String, Object>();
      for (final Entry<String, Object> metadato : metadatos.entrySet()) {
        folderProperties.put(metadato.getKey(), metadato.getValue());
      }
      folder = (Folder) folder.updateProperties(folderProperties);
      return folder;
      // return CmisUtils.getMetadatos(folder);
    } catch (final RepositoryCmisException rce) {
      throw rce;
    } catch (final CmisBaseException e) {
      logger.error("Error al modificar la carpeta con Id [" + folderId + "]: " + e.getMessage(), e);
      throw new RepositoryCmisException(
          "Error al modificar la carpeta con Id [" + folderId + "]: " + e.getMessage(), e);
    } catch (final Exception e) {
      logger.error("Error no controlado al modificar la carpeta con Id [" + folderId + "]: "
          + e.getMessage(), e);
      throw new RepositoryCmisException("Error no controlado al modificar la carpeta con Id ["
          + folderId + "]: " + e.getMessage(), e);
    }
  }


  @CmisSession
  public Folder getInfoCarpeta(final String folderId) throws RepositoryCmisException {
    try {
      final Folder folder = (Folder) getCmisObjectById(folderId);
      // return CmisUtils.getMetadatos(folder);
      return folder;
    } catch (final RepositoryCmisException rce) {
      throw rce;
    } catch (final CmisBaseException e) {
      logger.error("Error en getInfoCarpeta, carpeta con Id [" + folderId + "]: " + e.getMessage(),
          e);
      throw new RepositoryCmisException(
          "Error en getInfoCarpeta, carpeta con Id [" + folderId + "]: " + e.getMessage(), e);
    } catch (final Exception e) {
      logger.error("Error no controlado en getInfoCarpeta, carpeta con Id [" + folderId + "]: "
          + e.getMessage(), e);
      throw new RepositoryCmisException("Error no controlado en getInfoCarpeta, carpeta con Id ["
          + folderId + "]: " + e.getMessage(), e);
    }
  }

  @CmisSession
  public List<Map<String, Object>> getDocsCarpeta(final String folderId)
      throws RepositoryCmisException {
    return getObjetosCarpeta(folderId, BaseTypeId.CMIS_DOCUMENT);
  }

  @CmisSession
  public List<Map<String, Object>> getFoldersCarpeta(final String folderId)
      throws RepositoryCmisException {
    return getObjetosCarpeta(folderId, BaseTypeId.CMIS_FOLDER);
  }

  @CmisSession
  private List<Map<String, Object>> getObjetosCarpeta(final String folderId,
      BaseTypeId restriccionTipo) throws RepositoryCmisException {
    try {
      final List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
      final Folder folder = (Folder) getCmisObjectById(folderId);
      for (final CmisObject children : folder.getChildren()) {
        if (restriccionTipo != null
            && !children.getType().getBaseTypeId().equals(restriccionTipo)) {
          continue;
        }
        final Map<String, Object> metadatos = CmisUtils.getMetadatos(children);
        results.add(metadatos);
      }
      return results;
    } catch (final RepositoryCmisException rce) {
      throw rce;
    } catch (final Exception e) {
      throw new RepositoryCmisException("Error no controlado en getDocsCarpeta, carpeta con Id ["
          + folderId + "]: " + e.getMessage(), e);
    }
  }


  @CmisSession
  public Document altaDocumento(final String documentName, final String[] ruta,
      final String objectTypeId, final byte[] contenido, final String tipoMIME,
      final Map<String, Object> metadatos, boolean crearRuta) throws RepositoryCmisException {

    if (documentName == null || documentName.contentEquals("")) {
      throw new IllegalArgumentException("Debe especificar un nombre");
    }

    String[] path;
    if (ruta != null) {
      path = ruta;
    } else {
      path = new String[] {};
    }

    try {
      // Obtenemos un objeto folder, en el que se va a crear el documento.
      Folder folder = getFolder(path, crearRuta
      // CARM ### v2.0.8.2
          , Integer.valueOf(this.numberRetriesGetFolderWithHalfSecondDelayToAvoidConcurrentCreation)
      // CARM ### v2.0.8.2
      );



      return this.altaDocumento(documentName, folder, objectTypeId, contenido, tipoMIME, metadatos,
          crearRuta);

    } catch (final RepositoryCmisException rce) {
      throw rce;
    } catch (final Exception e) {
      logger.error("Error no controlado en altaDocumento, documentName [" + documentName + "]: "
          + e.getMessage(), e);
      throw new RepositoryCmisException("Error no controlado en altaDocumento, documentName ["
          + documentName + "]: " + e.getMessage(), e);
    }
  }


  @CmisSession
  public Document altaDocumento(final String documentName, final Folder folder,
      final String objectTypeId, final byte[] contenido, final String tipoMIME,
      final Map<String, Object> metadatos, boolean crearRuta) throws RepositoryCmisException {

    if (documentName == null || documentName.contentEquals("")) {
      throw new IllegalArgumentException("Debe especificar un nombre");
    }

    try {

      Document document = null;

      // Propiedades del objeto
      final Map<String, Object> properties = new HashMap<String, Object>();

      if (objectTypeId == null) {
        properties.put(PropertyIds.OBJECT_TYPE_ID, BaseTypeId.CMIS_DOCUMENT.value());
      } else {
        properties.put(PropertyIds.OBJECT_TYPE_ID, objectTypeId);
      }
      properties.put(PropertyIds.NAME, documentName);

      if (metadatos != null) {
        for (final Entry<String, Object> metadato : metadatos.entrySet()) {
          properties.put(metadato.getKey(), metadato.getValue());
        }
      }
      ContentStream content = null;
      if (contenido != null) {
        final long length = contenido.length;
        ByteArrayInputStream bis = new ByteArrayInputStream(contenido);

        content = sessionManager.getSession().getObjectFactory().createContentStream(documentName,
            length, ((tipoMIME == null || tipoMIME.length() == 0) ? CmisUtils.getMimeType(contenido)
                : tipoMIME),
            bis);

        bis.close();
        document = folder.createDocument(properties, content, VersioningState.MAJOR);

      }

      return document;
    } catch (final RepositoryCmisException rce) {
      throw rce;
    } catch (final Exception e) {
      logger.error("Error no controlado en altaDocumento, documentName [" + documentName + "]: "
          + e.getMessage(), e);
      throw new RepositoryCmisException("Error no controlado en altaDocumento, documentName ["
          + documentName + "]: " + e.getMessage(), e);
    }
  }



  @CmisSession
  public Map<String, Object> getInfoDocumento(final String documentId)
      throws RepositoryCmisException {
    return getInfoDocumento(documentId, null);
  }


  @CmisSession
  public Map<String, Object> getInfoDocumento(final String documentId, final String version)
      throws RepositoryCmisException {
    try {
      final Document document = (Document) getCmisObject(documentId, version);
      return CmisUtils.getMetadatos(document);
    } catch (final RepositoryCmisException rce) {
      throw rce;
    } catch (final CmisBaseException e) {
      logger.error("Error en getInfoDocumento, documentId [" + documentId + "], version [" + version
          + "]:" + e.getMessage(), e);
      throw new RepositoryCmisException("Error en getInfoDocumento, documentId [" + documentId
          + "], version [" + version + "]:" + e.getMessage(), e);
    } catch (final Exception e) {
      logger.error("Error no getInfoDocumento en getInfoDocumento, documentId [" + documentId
          + "], version [" + version + "]:" + e.getMessage(), e);
      throw new RepositoryCmisException("Error no controlado en getInfoDocumento, documentId ["
          + documentId + "], version [" + version + "]:" + e.getMessage(), e);
    }
  }



  @CmisSession
  public Collection<String> getVersionesDocumento(final String documentId)
      throws RepositoryCmisException {
    try {
      final List<String> versiones = new ArrayList<String>();
      final Document document = (Document) getCmisObjectById(documentId);
      for (final Document docVersion : document.getAllVersions()) {
        versiones.add(docVersion.getId());
      }
      return versiones;
    } catch (final RepositoryCmisException rce) {
      throw rce;
    } catch (final CmisBaseException e) {
      logger.error(
          "Error en getVersionesDocumento, documentId [" + documentId + "]: " + e.getMessage(), e);
      throw new RepositoryCmisException(
          "Error en getVersionesDocumento, documentId [" + documentId + "]: " + e.getMessage(), e);
    } catch (final Exception e) {
      logger.error("Error no controlado en getVersionesDocumento, documentId [" + documentId + "]: "
          + e.getMessage(), e);
      throw new RepositoryCmisException("Error no controlado en getVersionesDocumento, documentId ["
          + documentId + "]: " + e.getMessage(), e);
    }
  }


  @CmisSession
  public ContenidoDocumento getContenidoDoc(final String documentId)
      throws RepositoryCmisException {
    return getContenidoDoc(documentId, null);
  }


  @CmisSession
  public ContenidoDocumento getContenidoDoc(final String documentId, final String version)
      throws RepositoryCmisException {
    try {
      final Document document = (Document) getCmisObject(documentId, version);

      final ContenidoDocumento contenido = new ContenidoDocumento();
      contenido.setTipoMIME(document.getContentStreamMimeType());
      if (document.getContentStreamLength() > 0) {
        contenido.setContenido(IOUtils.toByteArray(document.getContentStream().getStream()));
      }
      return contenido;
    } catch (final RepositoryCmisException rce) {
      throw rce;
    } catch (final CmisBaseException e) {
      logger.error("Error en getContenidoDoc, documentId [" + documentId + "], version [" + version
          + "]:" + e.getMessage(), e);
      throw new RepositoryCmisException("Error en getContenidoDoc, documentId [" + documentId
          + "], version [" + version + "]:" + e.getMessage(), e);
    } catch (final Exception e) {
      logger.error("Error no controlado en getContenidoDoc, documentId [" + documentId
          + "], version [" + version + "]:" + e.getMessage(), e);
      throw new RepositoryCmisException("Error no controlado en getContenidoDoc, documentId ["
          + documentId + "], version [" + version + "]:" + e.getMessage(), e);
    }
  }


  @CmisSession
  public Map<String, Object> modificarDocumento(final String documentId,
      final Map<String, Object> metadatos, final ContenidoDocumento contenido)
      throws RepositoryCmisException {
    try {
      final Document document = (Document) getCmisObjectById(documentId);

      final Map<String, Object> properties = new HashMap<String, Object>();
      for (final Entry<String, Object> metadato : metadatos.entrySet()) {
        properties.put(metadato.getKey(), metadato.getValue());
      }
      ContentStream content = null;
      if (contenido != null && contenido.getContenido() != null) {
        final Session session = sessionManager.getSession();
        final String fileName = document.getContentStreamFileName();
        final long length = contenido.getContenido().length;

        ByteArrayInputStream bis = new ByteArrayInputStream(contenido.getContenido());
        content = session.getObjectFactory().createContentStream(fileName, length,
            contenido.getTipoMIME(), bis);
        bis.close();
      }

      if (((DocumentType) document.getType()).isVersionable()
          && document.getAllowableActions().getAllowableActions().contains(Action.CAN_CHECK_OUT)) {
        final ObjectId idDocCheckOut = document.checkOut();
        final Document pwc = (Document) getCmisObjectById(idDocCheckOut.getId());
        try {
          pwc.checkIn(true, properties, content, "major version");
        } catch (final CmisBaseException e) {
          pwc.cancelCheckOut();
          throw e;
        }
      } else {
        document.updateProperties(properties);
        if (content != null) {
          document.setContentStream(content, true);
        }
      }
      document.refresh();
      return CmisUtils.getMetadatos(document);
    } catch (final RepositoryCmisException rce) {
      throw rce;
    } catch (final CmisBaseException e) {
      logger.error(
          "Error en modificarDocumento, documentId [" + documentId + "]: " + e.getMessage(), e);
      throw new RepositoryCmisException(
          "Error en modificarDocumento, documentId [" + documentId + "]: " + e.getMessage(), e);
    } catch (final Exception e) {
      logger.error("Error no controlado en modificarDocumento, documentId [" + documentId + "]: "
          + e.getMessage(), e);
      throw new RepositoryCmisException("Error no controlado en modificarDocumento, documentId ["
          + documentId + "]: " + e.getMessage(), e);
    }
  }


  @CmisSession
  public void borrarDocumento(final String documentId, final boolean allVersions)
      throws RepositoryCmisException {
    try {
      final Document document = (Document) getCmisObjectById(documentId);
      if (allVersions) {
        document.deleteAllVersions();
      } else {
        for (final Document docVersion : document.getAllVersions()) {
          if (docVersion.isLatestVersion()) {
            docVersion.delete(false);
            break;
          }
        }
      }
    } catch (final RepositoryCmisException rce) {
      throw rce;
    } catch (final CmisBaseException e) {
      logger.error("Error en borrarDocumento, documentId [" + documentId + "]: " + e.getMessage(),
          e);
      throw new RepositoryCmisException(
          "Error en borrarDocumento, documentId [" + documentId + "]: " + e.getMessage(), e);
    } catch (final Exception e) {
      logger.error("Error no controlado en borrarDocumento, documentId [" + documentId + "]: "
          + e.getMessage(), e);
      throw new RepositoryCmisException("Error no controlado en borrarDocumento, documentId ["
          + documentId + "]: " + e.getMessage(), e);
    }
  }


  public void setSessionManager(final CmisSessionManager sessionManager) {
    this.sessionManager = sessionManager;
  }


  /**
   * @param objectId
   * @return
   * @throws CmisBaseException
   * @throws RepositoryCmisException
   */
  public CmisObject getCmisObjectById(final String objectId)
      throws CmisBaseException, RepositoryCmisException {
    return getCmisObject(objectId, null);
  }


  @CmisSession
  /**
   * @param objectId
   * @param version
   * @return
   * @throws CmisBaseException
   * @throws RepositoryCmisException
   */
  public CmisObject getCmisObject(final String objectId, final String version)
      throws CmisBaseException, RepositoryCmisException {
    final Session session = sessionManager.getSession();
    CmisObject cmisObject = session.getObject(objectId);
    if (StringUtils.isNotBlank(version)) {
      if (((DocumentType) cmisObject.getType()).isVersionable()) {
        final Document document = (Document) cmisObject;
        for (final Document docVersion : document.getAllVersions()) {
          if (StringUtils.equals(version, docVersion.getVersionSeriesId())) {
            cmisObject = docVersion;
            break;
          }
        }
      }
    }
    return cmisObject;
  }

  // CARM ### v2.0.8.2
  public Folder getFolder(final String[] ruta, boolean crearRuta,
      int numberRetriesGetFolderWithHalfSecondDelayToAvoidConcurrentCreation)
      throws RepositoryCmisException {
    int currentAttemps = 0;
    while (true) {
      try {
        return getFolder(ruta, crearRuta);
      } catch (CmisContentAlreadyExistsException cas) {
        if (currentAttemps < numberRetriesGetFolderWithHalfSecondDelayToAvoidConcurrentCreation) {
          currentAttemps++;
          try {
            Thread.sleep(500);
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
          }
          continue;
        }
        throw cas;
      }
    }
  }
  // CARM 2.0.8.2 ###

  @CmisSession
  /**
   * Devuelve el objeto que representa el Folder indicado en ruta Si el segundo parámetro es true,
   * si la carpeta no existía, se crea
   * 
   * @param ruta
   * @param crearRuta
   * @return
   * @throws RepositoryCmisException
   */
  public Folder getFolder(final String[] ruta, boolean crearRuta) throws RepositoryCmisException {
    if (ruta == null || ruta.length == 0) {
      return sessionManager.getRootFolder();
    }
    String path = sessionManager.getRootFolder().getPath() + pathSeparator
        + StringUtils.join(ruta, pathSeparator);

    logger.debug("Recuperando carpeta " + path);

    try {
      CmisObject folder = sessionManager.getSession().getObjectByPath(path);
      if (!(folder instanceof Folder)) {
        throw new RepositoryCmisException("folder no es un Folder");
      }
      return (Folder) folder;
    } catch (CmisObjectNotFoundException e) {
      if (crearRuta) {
        return createFolder(ruta);
      }
      throw e;
    }
  }


  @CmisSession
  /**
   * Crea un Folder en base a una ruta
   * 
   * @param ruta
   * @return
   * @throws RepositoryCmisException
   */
  public Folder createFolder(final String[] ruta) throws RepositoryCmisException {
    logger.debug("Creando carpeta " + StringUtils.join(ruta, pathSeparator));
    String[] path = FolderCmisUtils.cleanPath(ruta);

    Folder parent = null;
    if (path.length == 0) {
      throw new RepositoryCmisException("La ruta no puede estar vacía " + ruta);
    } else if (path.length == 1) {
      parent = sessionManager.getRootFolder();
    } else {
      parent = getFolder(Arrays.copyOf(path, (path.length - 1)), true);
    }
    final Map<String, Object> folderProperties = new HashMap<String, Object>();

    folderProperties.put(PropertyIds.OBJECT_TYPE_ID, BaseTypeId.CMIS_FOLDER.value());
    folderProperties.put(PropertyIds.NAME, path[path.length - 1]);
    folderProperties.put(PropertyIds.PARENT_ID, parent.getId());

    return parent.createFolder(folderProperties);


  }


  /*
   * private static String buildMessageException (final Exception e) { StringBuffer sb = new
   * StringBuffer ("");
   * 
   * if (e instanceof CmisBaseException e) { CmisBaseException cmisExc = (CmisBaseException) e;
   * cmisExc.getCode() } }
   */


}
