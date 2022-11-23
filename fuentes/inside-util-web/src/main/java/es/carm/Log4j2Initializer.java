
package es.carm;

import java.io.File;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

public class Log4j2Initializer implements ServletContextListener {
  private static String CONFIG_PATH = "config.path";
  private static String MSG_WELLCOME = "Log inicializado!";


  @Override
  public void contextInitialized(ServletContextEvent sce) {

    System.err.println("IBM78M PATCH - Inicializando log4j2");
    String configPathProperty = System.getProperty(CONFIG_PATH);
    if (configPathProperty == null || configPathProperty.contentEquals("")) {
      ServletContext servletContext = sce.getServletContext();
      configPathProperty = servletContext.getInitParameter(CONFIG_PATH);
    }
    if ((null != configPathProperty) && (0 < configPathProperty.length())) {
      String pathFileConfig =
          configPathProperty + System.getProperty("file.separator") + "log4j2.xml";
      File fileConfig = new File(pathFileConfig);
      if (fileConfig.exists()) {
        System.err.println("IBM78M PATCH - OK, Se encontró el fichero '" + pathFileConfig + "'");
        if (fileConfig.canRead()) {
          LoggerContext context = (LoggerContext) LogManager.getContext(false);
          context.setConfigLocation(fileConfig.toURI());

          System.err.println("IBM78M PATCH - OK, se configuró el LogManager");

          Logger log = LogManager.getLogger(this.getClass());
          log.info(MSG_WELLCOME);
          log.debug(MSG_WELLCOME);
          log.trace(MSG_WELLCOME);

        } else {
          System.err
              .println("IBM78M PATCH - ERROR, no se pudo leer el fichero '" + pathFileConfig + "'");
        }
      } else {
        System.err
            .println("IBM78M PATCH - ERROR, no se encontró el fichero '" + pathFileConfig + "'");
      }
    } else {
      System.err.println("IBM78M PATCH - ERROR, no se pudo obtener el parámetro '" + CONFIG_PATH
          + "' ni del entorno como propiedad, ni del contexto (en el web.xml)");
    }
    System.err.println("IBM78M PATCH - Fin de la inicialización de log4j2");

  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {}
}
