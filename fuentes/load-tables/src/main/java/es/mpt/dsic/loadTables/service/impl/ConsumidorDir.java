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

package es.mpt.dsic.loadTables.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import es.mpt.dsic.loadTables.service.model.FormatoFichero;
import es.mpt.dsic.loadTables.service.model.RespuestaWS;
import es.mpt.dsic.loadTables.service.model.SD01UNDescargaUnidades;
import es.mpt.dsic.loadTables.service.model.SD01UNDescargaUnidadesService;
import es.mpt.dsic.loadTables.service.model.TipoConsultaUO;
import es.mpt.dsic.loadTables.service.model.UnidadesWs;
import es.mpt.dsic.loadTables.utils.Constantes;

public class ConsumidorDir {

  private String user;
  private String password;
  private String url;
  private SD01UNDescargaUnidades sd01;

  protected static final Log logger = LogFactory.getLog(ConsumidorDir.class);

  public void configure() {
    if (sd01 == null) {
      URL urlsc01 = null;
      try {
        logger.debug(String.format("El WS de Consumidor se encuentra en %s", url));
        urlsc01 = new URL(url);
      } catch (MalformedURLException me) {
        logger.error("No se puede crear la URL del servicio de DIR3 " + url, me);
      }

      SD01UNDescargaUnidadesService service1 = new SD01UNDescargaUnidadesService(urlsc01);
      sd01 = service1.getSD01UNDescargaUnidades();
    }
  }

  public String volcadoDatosBasicos() throws IOException {
    configure();

    UnidadesWs unidadesRequest = new UnidadesWs();
    unidadesRequest.setUsuario(user);
    unidadesRequest.setClave(password);
    unidadesRequest.setFormatoFichero(FormatoFichero.XML);
    unidadesRequest.setTipoConsulta(TipoConsultaUO.COMPLETO);
    setTimeouts();

    RespuestaWS datos = sd01.exportar(unidadesRequest);
    return datos.getFichero();
  }

  private void setTimeouts() {
    org.apache.cxf.endpoint.Client client = ClientProxy.getClient(sd01);
    HTTPConduit http = (HTTPConduit) client.getConduit();
    HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
    httpClientPolicy.setConnectionTimeout(0);
    httpClientPolicy.setReceiveTimeout(0);
    http.setClient(httpClientPolicy);
  }

  public String incrementalDatosBasicos(Date fecha) throws IOException {
    configure();

    SimpleDateFormat format = new SimpleDateFormat(Constantes.WS_FORMATO_FECHA);
    UnidadesWs unidadesRequest = new UnidadesWs();
    unidadesRequest.setUsuario(user);
    unidadesRequest.setClave(password);
    unidadesRequest.setFormatoFichero(FormatoFichero.XML);
    unidadesRequest.setTipoConsulta(TipoConsultaUO.COMPLETO);
    unidadesRequest.setFechaInicio(format.format(fecha));
    setTimeouts();
    RespuestaWS datos = sd01.exportar(unidadesRequest);

    return datos.getFichero();
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

}
