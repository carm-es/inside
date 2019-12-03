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

package es.mpt.dsic.integration.cmis.session;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Repository;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import es.mpt.dsic.integration.cmis.exception.RepositoryCmisException;

public class CmisSessionManagerImpl implements CmisSessionManager {

  private static final Log logger = LogFactory.getLog(CmisSessionManagerImpl.class);


  /**
   * Tiempo máximo (milisegundos) por defecto para intentar conectarse al servidor
   */
  private static final int DEFAULT_CONNECT_TIMEOUT = 1 * 60 * 1000;

  /**
   * Tiempo máximo (milisegundos) por defecto para que caduque la sesión del usuario con el servidor
   */
  private static final int DEFAULT_SESSION_TIMEOUT = -1;

  private Session session;
  private Folder root;


  public Session authenticate() throws RepositoryCmisException {
    if (!isSessionAlive()) {
      if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
        throw new RepositoryCmisException("Debe especificarse nombre de usuario y contraseña");
      }

      try {
        if (logger.isDebugEnabled()) {
          logger.debug("Autenticando usuario " + username + " en servidor " + url);
        }

        final Map<String, String> parameters = new HashMap<String, String>();
        if (bindingType != null) {
          bindingType = bindingType.trim();
        }

        // connection settings
        if (BindingType.ATOMPUB.value().equals(bindingType)) {
          parameters.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());
          parameters.put(SessionParameter.ATOMPUB_URL, url);

        } else if (BindingType.WEBSERVICES.value().equals(bindingType)) {
          if (!url.endsWith("/")) {
            url += "/";
          }
          parameters.put(SessionParameter.BINDING_TYPE, BindingType.WEBSERVICES.value());
          parameters.put(SessionParameter.WEBSERVICES_ACL_SERVICE, url + "ACLService?wsdl");
          parameters.put(SessionParameter.WEBSERVICES_DISCOVERY_SERVICE,
              url + "DiscoveryService?wsdl");
          parameters.put(SessionParameter.WEBSERVICES_MULTIFILING_SERVICE,
              url + "MultiFilingService?wsdl");
          parameters.put(SessionParameter.WEBSERVICES_NAVIGATION_SERVICE,
              url + "NavigationService?wsdl");
          parameters.put(SessionParameter.WEBSERVICES_OBJECT_SERVICE, url + "ObjectService?wsdl");
          parameters.put(SessionParameter.WEBSERVICES_POLICY_SERVICE, url + "PolicyService?wsdl");
          parameters.put(SessionParameter.WEBSERVICES_RELATIONSHIP_SERVICE,
              url + "RelationshipService?wsdl");
          parameters.put(SessionParameter.WEBSERVICES_REPOSITORY_SERVICE,
              url + "RepositoryService?wsdl");
          parameters.put(SessionParameter.WEBSERVICES_VERSIONING_SERVICE,
              url + "VersioningService?wsdl");

        } else if (BindingType.BROWSER.value().equals(bindingType)) {
          parameters.put(SessionParameter.BROWSER_URL, url);
          parameters.put(SessionParameter.BINDING_TYPE, BindingType.BROWSER.value());

        } else {
          logger.error("El bindingType " + bindingType + " no está soportado ");
          throw new RepositoryCmisException(
              "El bindingType " + bindingType + " no está soportado ");
        }

        parameters.put(SessionParameter.USER, username);
        parameters.put(SessionParameter.PASSWORD, password);

        parameters.put(SessionParameter.READ_TIMEOUT, String.valueOf(connectTimeout));
        parameters.put(SessionParameter.CONNECT_TIMEOUT, String.valueOf(sessionTimeout));

        final SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
        final String id = getRepositoryId(sessionFactory, parameters);
        parameters.put(SessionParameter.REPOSITORY_ID, id);
        session = sessionFactory.createSession(parameters);


        if (logger.isDebugEnabled()) {
          logger.debug("Usuario " + username + " autenticado correctamente en " + url);
        }
      } catch (final Throwable t) {
        throw new RepositoryCmisException(t.getMessage(), t);
      }
    }
    return session;
  }

  private String getRepositoryId(final SessionFactory sessionFactory,
      final Map<String, String> parameters) throws RepositoryCmisException {
    String id = null;
    if (StringUtils.isBlank(repositoryId)) {
      final List<Repository> repositories = sessionFactory.getRepositories(parameters);
      if (CollectionUtils.isEmpty(repositories)) {
        throw new RepositoryCmisException(
            "Error en la conexión CMIS, no se puede obtener el repositorio principal.");
      }
      id = repositories.get(0).getId();
    } else {
      id = repositoryId;
    }
    return id;
  }


  public Session getSession() throws RepositoryCmisException {
    if (session == null) {
      throw new RepositoryCmisException(
          "Debe autenticarse mediante el método authenticate() antes de obtener una sesión");
    }
    return session;
  }


  public Folder getRootFolder() throws RepositoryCmisException {
    if (session == null) {
      throw new RepositoryCmisException(
          "Debe autenticarse mediante el método authenticate() antes de obtener el directorio root");
    }

    /*
     * CARM ### v2.0.8.1 El conector CMIS busca la carpeta server.cmis.pathRootFolder en la ra�z del
     * repositorio documental. Cuando no la encuentra, si se ha proporcionado el property
     * complementario server.cmis.complementary.root.defaultUserHomeSpacesFolder, la b�squeda se
     * ampl�a a todas las carpetas que cuelguen de la ra�z y contengan el nombre de la ra�z
     * complementaria
     */
    if (this.root == null && this.defaultUserHomeSpacesFolder != null
        && !this.defaultUserHomeSpacesFolder.isEmpty()) {
      for (final CmisObject children : session.getRootFolder().getChildren()) {
        if (children.getName().contains(this.defaultUserHomeSpacesFolder)) {
          Folder userHomeFolder = (Folder) children;
          for (final CmisObject uhc : userHomeFolder.getChildren()) {
            if (StringUtils.equals(uhc.getName(), pathRootFolder)) {
              root = (Folder) uhc;
              break;
            }
          }
          if (root != null)
            break;
        }
      }
    }
    // CARM 2.0.8.1 ###

    if (root == null) {
      for (final CmisObject children : session.getRootFolder().getChildren()) {
        if (StringUtils.equals(children.getName(), pathRootFolder)) {
          root = (Folder) children;
          break;
        }
      }
      if (root == null) {
        final Map<String, String> newFolderProps = new HashMap<String, String>();
        newFolderProps.put(PropertyIds.OBJECT_TYPE_ID, "cmis:folder");
        newFolderProps.put(PropertyIds.NAME, pathRootFolder);
        root = session.getRootFolder().createFolder(newFolderProps);
      }
    }
    return root;
  }


  public void close() throws RepositoryCmisException {
    session = null;
    root = null;
  }


  public boolean isSessionAlive() {
    return session != null;
  }

  /**
   * URL del servidor EMC que recibe las peticiones CMIS
   */
  private String url;

  /**
   * Nombre de usuario para acceder al servidor
   */
  private String username;

  /**
   * Contraseña para acceder al servidor
   */
  private String password;

  /**
   * 
   */
  private String repositoryId;

  /**
   * 
   */

  private String pathRootFolder;

  /**
   * 
   */
  private String bindingType;

  // CARM ### v2.0.8.1
  private String defaultUserHomeSpacesFolder;
  // CARM 2.0.8.1 ###

  /**
   * Duración máxima (milisegundos) para permitir conectarse al servidor
   */
  private int connectTimeout = DEFAULT_CONNECT_TIMEOUT;

  /**
   * Duración máxima (milisegundos) de la sesión conectado al servidor
   */
  private int sessionTimeout = DEFAULT_SESSION_TIMEOUT;

  @Required
  public void setUrl(final String url) {
    this.url = url;
  }

  @Required
  public void setUsername(final String username) {
    this.username = username;
  }

  @Required
  public void setPassword(final String password) {
    this.password = password;
  }

  public void setRepositoryId(final String repositoryId) {
    this.repositoryId = repositoryId;
  }

  @Required
  public void setPathRootFolder(final String pathRootFolder) {
    this.pathRootFolder = pathRootFolder;
  }

  public void setConnectTimeout(final int connectTimeout) {
    this.connectTimeout = connectTimeout;
  }

  public void setSessionTimeout(final int sessionTimeout) {
    this.sessionTimeout = sessionTimeout;
  }

  public void setBindingType(String bindingType) {
    this.bindingType = bindingType;
  }

  // CARM ### v2.0.8.1
  public void setDefaultUserHomeSpacesFolder(final String defaultUserHomeSpacesFolder) {
    this.defaultUserHomeSpacesFolder = defaultUserHomeSpacesFolder;
  }
  // CARM 2.0.8.1 ###


}
