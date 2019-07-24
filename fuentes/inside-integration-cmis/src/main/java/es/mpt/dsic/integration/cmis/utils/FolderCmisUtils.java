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

package es.mpt.dsic.integration.cmis.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.enums.BaseTypeId;
import org.apache.chemistry.opencmis.commons.enums.UnfileObject;
import org.apache.chemistry.opencmis.commons.exceptions.CmisBaseException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;
import es.mpt.dsic.integration.cmis.exception.RepositoryCmisException;

// TODO Puede que no haga falta esto ya
public class FolderCmisUtils {

  private static final Log logger = LogFactory.getLog(FolderCmisUtils.class);

  private FolderCmisUtils() {}

  /**
   * @return
   */
  public static String[] defaultPath() {
    final Calendar calendar = Calendar.getInstance();
    final String[] defaultPath = new String[3];
    defaultPath[0] = String.valueOf(calendar.get(Calendar.YEAR));
    defaultPath[1] = String.valueOf(calendar.get(Calendar.MONTH) + 1);
    defaultPath[2] = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
    if (logger.isDebugEnabled()) {
      logger.debug(String.format("Default path: %s", StringUtils.join(defaultPath, "/")));
    }
    return defaultPath;
  }

  /**
   * Comprueba si existe un folder "path" dentro de "folder"
   * 
   * @param folder
   * @param path
   * @return
   * @throws CmisBaseException
   */
  public static boolean existPathInFolder(final Folder folder, final String path)
      throws CmisBaseException {
    return getFolderByPath(folder, path) != null;
  }

  /**
   * Devuelve un folder con nombre igual al parámetro path, que esté dentro de la carpeta definida
   * en el parámetro folder
   * 
   * @param folder carpeta en la que queremos buscar un folder con nombre path.
   * @param path Nombre de la carpeta que queremos buscar
   * @return null si no se encuentra.
   * @throws CmisBaseException
   */
  public static Folder getFolderByPath(final Folder folder, final String path)
      throws CmisBaseException {
    if (logger.isDebugEnabled()) {
      logger.debug(String.format("Buscando '%s' en el folder %s", path, folder.getName()));
    }
    Folder folderSearch = null;

    for (final CmisObject children : folder.getChildren()) {
      if (StringUtils.equals(path, children.getName())) {
        folderSearch = (Folder) children;
        break;
      }
    }
    return folderSearch;
  }

  /**
   * Crea un folder con nombre "name", dentro de la "folder"
   * 
   * @param folder
   * @param name
   * @return objeto folder creado
   * @throws CmisBaseException
   */
  public static Folder createFolder(final Folder folder, final String name)
      throws CmisBaseException {
    final Map<String, Object> folderProperties = new HashMap<String, Object>();
    folderProperties.put(PropertyIds.OBJECT_TYPE_ID, BaseTypeId.CMIS_FOLDER.value());
    folderProperties.put(PropertyIds.NAME, name);
    return createFolder(folder, folderProperties);
  }

  /**
   * @param folder
   * @param folderProperties
   * @return
   * @throws CmisBaseException
   */
  public static Folder createFolder(final Folder folder, final Map<String, Object> folderProperties)
      throws CmisBaseException {
    if (logger.isDebugEnabled()) {
      logger.debug(
          String.format("Creando '%s' (%s) en el folder %s", folderProperties.get(PropertyIds.NAME),
              folderProperties.get(PropertyIds.OBJECT_TYPE_ID), folder.getName()));
    }
    folderProperties.put(PropertyIds.PARENT_ID, folder.getId());
    return folder.createFolder(folderProperties);
  }

  /**
   * 
   * @param folder
   * @param folderId
   * @param objectTypeId
   * @param folderProperties
   * @return
   * @throws CmisBaseException
   */
  public static Folder createFolder(final Folder folder, final String folderId,
      final String objectTypeId, final Map<String, Object> folderProperties)
      throws CmisBaseException {

    folderProperties.put(PropertyIds.OBJECT_TYPE_ID, objectTypeId);
    folderProperties.put(PropertyIds.NAME, folderId);

    return createFolder(folder, folderProperties);
  }

  /**
   * @param folder
   * @throws CmisBaseException
   */
  public static void deleteFolder(final Folder folder) throws CmisBaseException {
    if (logger.isDebugEnabled()) {
      logger.debug(String.format("Borrando el folder '%s'", folder.getName()));
    }
    folder.delete(true);
  }

  /**
   * @param folder
   * @throws CmisBaseException
   */
  public static void deleteTreeFolder(final Folder folder) throws CmisBaseException {
    if (logger.isDebugEnabled()) {
      logger.debug(String.format("Borrando el folder '%s'", folder.getName()));
    }
    folder.deleteTree(true, UnfileObject.DELETE, true);
  }

  /**
   * Limpia un path de posibles "huecos" en blanco
   * 
   * @param path
   * @return
   */
  public static String[] cleanPath(String[] path) {
    Assert.notNull(path);
    List<String> tmp = new ArrayList<String>();
    for (String part : path) {
      if (path == null || path.length == 0) {
        continue;
      }
      tmp.add(part);
    }
    return tmp.toArray(path);
  }

  /**
   * Comprueba que una ruta no este vacía
   * 
   * @param path
   * @throws RepositoryCmisException
   */
  public static void assertPathIsNotEmpty(String[] path) throws RepositoryCmisException {
    if (path == null || cleanPath(path).length < 1) {
      throw new RepositoryCmisException("El path no puede estar vacío");
    }
  }
}
