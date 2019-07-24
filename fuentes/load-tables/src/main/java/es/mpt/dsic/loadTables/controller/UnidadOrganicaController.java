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

package es.mpt.dsic.loadTables.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import es.mpt.dsic.loadTables.exception.UnidadOrganicaException;
import es.mpt.dsic.loadTables.handler.HandlerUnidadOrganica;
import es.mpt.dsic.loadTables.hibernate.service.impl.UnidadOrganicaServiceImpl;
import es.mpt.dsic.loadTables.objects.Organismo;
import es.mpt.dsic.loadTables.service.impl.ConsumidorDir;
import es.mpt.dsic.loadTables.utils.Constantes;
import es.mpt.dsic.loadTables.utils.Utils;

public class UnidadOrganicaController extends QuartzJobBean {

  protected static final Log logger = LogFactory.getLog(UnidadOrganicaController.class);

  @Autowired
  private UnidadOrganicaServiceImpl unidadOrganicaService;

  @Autowired
  private ConsumidorDir consumer;

  private String rutaTemp;
  private String ficheroTxtTemporal;
  private String ficheroZipTemporal;
  private String pathDescompresion;

  /**
   * 
   * Este job comprueba la fecha de la última actualización de dir3 (campo created_at de
   * UnidadOrganica). Con esta fecha se pregunta a sw de dir3 por las novedades para esa fecha. Si
   * las hay las carga.
   */
  @Override
  protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
    try {
      SchedulerContext schedCtx = context.getScheduler().getContext();

      ApplicationContext appCtx = (ApplicationContext) schedCtx.get("applicationContext");

      UnidadOrganicaController controller =
          (UnidadOrganicaController) appCtx.getBean("unidadOrganicaController");
      controller.loadUnidadOrganica(null);
    } catch (SchedulerException e) {
      logger.error("Error al cargar datos");
    }
  }

  /**
   * El job llamará a este método con el parámetro fechaInicio a nulo. La operación
   * actualizarUnidadesDir3NovedadesDesdeFecha lo llama para preguntar al sw de dir3 por novedades
   * para una fecha en concreto. Esto sirve por si se quedó algun dir3 sin cargar. Se ha comprobado
   * que si se intenta cargar una unidad orgánica ya existente se actualiza. Se mantiene la
   * integridad de la BBDD.
   */
  public void loadUnidadOrganica(Date fechaInicio) {
    try {
      // Comprueba que finalice en barra, si no, se la pone
      if (!rutaTemp.endsWith(Constantes.FILE_SEPARATOR)) {
        rutaTemp = rutaTemp + Constantes.FILE_SEPARATOR;
      }
      String base64;
      Date unidadOrganicaSyncDate;
      String file = rutaTemp + pathDescompresion + Constantes.FILE_SEPARATOR + "Unidades.xml";
      SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
      if (fechaInicio != null) {
        logger.debug("Inicio Load Tablas UnidadOrganica cargamos novedades desde "
            + dt.format(fechaInicio) + " hasta el dia de hoy");
        unidadOrganicaSyncDate = fechaInicio;
      } else {
        logger.debug("Inicio Load Tablas UnidadOrganica");

        // obtener fecha ultima sincronizacion
        unidadOrganicaSyncDate = unidadOrganicaService.geLastSync();

      }
      if (unidadOrganicaSyncDate == null) {
        base64 = consumer.volcadoDatosBasicos();
      } else {
        base64 = consumer.incrementalDatosBasicos(unidadOrganicaSyncDate);
      }

      // si es distinto de null y vacio
      if (!StringUtils.isEmpty(base64)) {
        inserData(file, base64);
      }

    } catch (FileNotFoundException e) {
      logger.error("Error al cargar datos: " + e.getMessage());
      e.printStackTrace();
    } catch (IOException e) {
      logger.error("Error al cargar datos: " + e.getMessage());
      e.printStackTrace();
    } catch (UnidadOrganicaException e) {
      logger.error("Error al cargar datos: " + e.getMessage());
      e.printStackTrace();
    }
  }

  private void inserData(String file, String base64) throws IOException, UnidadOrganicaException {
    // borrado de datos anteriores
    Utils.deleteBefore(rutaTemp, ficheroTxtTemporal, ficheroZipTemporal, pathDescompresion);

    // relleno fichero txt con base 64
    Utils.writeDataBase64(base64, rutaTemp, ficheroTxtTemporal);

    // convertir fichero txt base64 a zip
    Utils.getUnZippedFile(new FileInputStream(rutaTemp + ficheroTxtTemporal),
        rutaTemp + ficheroZipTemporal);

    // descomprimir fichero zip
    Utils.unZipFile(rutaTemp + ficheroZipTemporal, rutaTemp + pathDescompresion);

    logger.debug("Fichero a cargar: " + file);

    // paseo de ficheros
    HandlerUnidadOrganica handler = new HandlerUnidadOrganica(file);
    List<Organismo> organismos = handler.getOrganimos();

    // guardado de datos
    unidadOrganicaService.saveList(organismos, new Date());
  }

  public UnidadOrganicaServiceImpl getUnidadOrganicaService() {
    return unidadOrganicaService;
  }

  public void setUnidadOrganicaService(UnidadOrganicaServiceImpl unidadOrganicaService) {
    this.unidadOrganicaService = unidadOrganicaService;
  }

  public String getRutaTemp() {
    return rutaTemp;
  }

  public void setRutaTemp(String rutaTemp) {
    this.rutaTemp = rutaTemp;
  }

  public String getFicheroTxtTemporal() {
    return ficheroTxtTemporal;
  }

  public void setFicheroTxtTemporal(String ficheroTxtTemporal) {
    this.ficheroTxtTemporal = ficheroTxtTemporal;
  }

  public String getFicheroZipTemporal() {
    return ficheroZipTemporal;
  }

  public void setFicheroZipTemporal(String ficheroZipTemporal) {
    this.ficheroZipTemporal = ficheroZipTemporal;
  }

  public String getPathDescompresion() {
    return pathDescompresion;
  }

  public void setPathDescompresion(String pathDescompresion) {
    this.pathDescompresion = pathDescompresion;
  }

  public ConsumidorDir getConsumer() {
    return consumer;
  }

  public void setConsumer(ConsumidorDir consumer) {
    this.consumer = consumer;
  }

}
