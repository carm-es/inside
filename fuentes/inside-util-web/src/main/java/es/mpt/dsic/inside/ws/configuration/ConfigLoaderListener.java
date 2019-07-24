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

package es.mpt.dsic.inside.ws.configuration;

import java.io.File;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Listener para cargar la variable de sistema "config.path". Si se ha arrancado la aplicación con
 * esta opción no hace nada. En caso contrario lo toma de un parámetro que se establecerá en el
 * web.xml
 * 
 * @author rus
 *
 */
public class ConfigLoaderListener implements ServletContextListener {
  private static String CONFIG_PATH = "config.path";

  // Nombre del fichero para certificados
  public static final String TRUSTSTORE_FILE = "trustStore.jks";


  // Variable de configuración para el uso de Clave
  public static final String CONFIG_PATH_VAR_FOR_CLAVE = "clave.config.path";

  // Variable de configuración para el uso de REGISTRO
  public static final String CONFIG_PATH_VAR_FOR_REGISTRO = "archive.config.path";

  public void contextInitialized(ServletContextEvent event) {
    // Si no encuentra la variable la toma del parámetro establecido en el web.xml
    String configPathProperty = System.getProperty(CONFIG_PATH);
    if (configPathProperty == null || configPathProperty.contentEquals("")) {

      ServletContext servletContext = event.getServletContext();
      configPathProperty = servletContext.getInitParameter(CONFIG_PATH);
      System.setProperty(CONFIG_PATH, configPathProperty);
      System.setProperty(CONFIG_PATH_VAR_FOR_CLAVE, configPathProperty);
      System.setProperty(CONFIG_PATH_VAR_FOR_REGISTRO, configPathProperty);
    } else {
      System.setProperty(CONFIG_PATH_VAR_FOR_CLAVE, configPathProperty);
      System.setProperty(CONFIG_PATH_VAR_FOR_REGISTRO, configPathProperty);
    }

    setKeyStore(configPathProperty);
  }

  public void contextDestroyed(ServletContextEvent event) {

  }

  private static void setKeyStore(String configPathProperty) {
    System.setProperty("javax.net.ssl.trustStore",
        configPathProperty + File.separator + TRUSTSTORE_FILE);
    System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
    System.setProperty("javax.net.ssl.trustStoreType", "JKS");
  }
}
