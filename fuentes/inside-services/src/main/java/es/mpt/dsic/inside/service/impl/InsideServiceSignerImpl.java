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

package es.mpt.dsic.inside.service.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import es.mpt.dsic.firma.cliente.model.ApplicationLogin;
import es.mpt.dsic.firma.cliente.model.ContenidoSalida;
import es.mpt.dsic.firma.cliente.model.DatosEntradaFichero;
import es.mpt.dsic.firma.cliente.model.DatosSalida;
import es.mpt.dsic.firma.cliente.model.ResultadoFirmaFichero;
import es.mpt.dsic.firma.cliente.service.EeUtilFirmarService;
import es.mpt.dsic.firma.cliente.service.impl.EeUtilFirmarServiceImplService;
import es.mpt.dsic.inside.service.exception.InSideServiceSignerException;
import es.mpt.dsic.inside.service.object.signer.InsideServiceSigner;
import es.mpt.dsic.inside.xml.inside.ws.credential.WSCredentialInside;

public class InsideServiceSignerImpl implements InsideServiceSigner {

  protected static final Log logger = LogFactory.getLog(InsideServiceSignerImpl.class);

  private static String ACTIVO = "S";

  private Properties properties;
  private EeUtilFirmarService port;
  private ApplicationLogin applicationLogin;

  private boolean activo = false;

  public boolean configureSigner() {
    if (!activo) {
      String firmaActivo = properties.getProperty("firma.activo");

      if (!ACTIVO.contentEquals(firmaActivo)) {

        logger.info("El WS de FIRMA REMOTA no está activo");
      } else {

        URL url = null;
        String urlFirma = null;
        try {
          urlFirma = properties.getProperty("firma.url");
          logger.debug(String.format("El WS de firma remota se encuentra en %s", urlFirma));
          url = new URL(urlFirma);

          EeUtilFirmarServiceImplService ss = new EeUtilFirmarServiceImplService(url);

          port = ss.getEeUtilFirmarServiceImplPort();

          applicationLogin = new ApplicationLogin();
          applicationLogin.setIdaplicacion(properties.getProperty("firma.idaplicacion"));
          applicationLogin.setPassword(properties.getProperty("firma.password"));

          logger.debug(String.format("Utilizando idaplicacion/password : %s/%s",
              properties.getProperty("firma.idaplicacion"),
              properties.getProperty("firma.password")));
          activo = true;
        } catch (MalformedURLException me) {
          logger.error("No se puede crear la URL del servicio de firma " + urlFirma, me);
        }
      }
    }
    return activo;
  }

  @Override
  public byte[] firmarFichero(byte[] bytesFichero, String algoritmoFirma, String formatoFirma,
      String modoFirma, WSCredentialInside info) throws InSideServiceSignerException {

    if (!configureSigner()) {
      throw new InSideServiceSignerException("El servicio de firma remota no está activo");
    }

    byte[] firma = null;

    DatosEntradaFichero de = new DatosEntradaFichero();
    de.setContenido(bytesFichero);
    de.setAlgoritmoFirma(algoritmoFirma);
    de.setFormatoFirma(formatoFirma);
    de.setModoFirma(modoFirma);

    DatosSalida salida = null;
    try {
      ApplicationLogin loginFirma = null;
      if (info != null) {
        loginFirma = new ApplicationLogin();
        loginFirma.setIdaplicacion(info.getIdaplicacion());
        loginFirma.setPassword(info.getPassword());
      } else {
        loginFirma = applicationLogin;
      }

      salida = port.firmaFichero(loginFirma, de);
    } catch (Exception e) {
      throw new InSideServiceSignerException(
          "Se ha producido un error en la llamada al servicio de firma remota ", e);
    }

    ContenidoSalida cont = salida.getDatosResultado();

    if (salida.getEstado().equalsIgnoreCase(properties.getProperty("firma.cadena.ok"))) {
      logger.debug("Fichero firmado correctamente");
      ResultadoFirmaFichero resultado = (ResultadoFirmaFichero) cont;
      firma = resultado.getFirma();
    } else {
      es.mpt.dsic.firma.cliente.model.Error error = (es.mpt.dsic.firma.cliente.model.Error) cont;
      throw new InSideServiceSignerException("No se ha firmado correctamente el fichero: "
          + error.getMensaje() + " " + error.getCausa());
    }
    return firma;
  }


  @Override
  public byte[] firmarFicheroWhitPropertie(byte[] bytesFichero, String algoritmoFirma,
      String formatoFirma, String modoFirma, String nodeToSign, WSCredentialInside info)
      throws InSideServiceSignerException {

    if (!configureSigner()) {
      throw new InSideServiceSignerException("El servicio de firma remota no está activo");
    }

    byte[] firma = null;

    DatosEntradaFichero de = new DatosEntradaFichero();
    de.setContenido(bytesFichero);
    de.setAlgoritmoFirma(algoritmoFirma);
    de.setFormatoFirma(formatoFirma);
    de.setModoFirma(modoFirma);
    de.setNodeToSign(nodeToSign);

    DatosSalida salida = null;
    try {
      ApplicationLogin loginFirma = null;
      if (info != null) {
        loginFirma = new ApplicationLogin();
        loginFirma.setIdaplicacion(info.getIdaplicacion());
        loginFirma.setPassword(info.getPassword());
      } else {
        loginFirma = applicationLogin;
      }

      salida = port.firmaFicheroWithPropertie(loginFirma, de);
    } catch (Exception e) {
      throw new InSideServiceSignerException(
          "Se ha producido un error en la llamada al servicio de firma remota ", e);
    }

    ContenidoSalida cont = salida.getDatosResultado();

    if (salida.getEstado().equalsIgnoreCase(properties.getProperty("firma.cadena.ok"))) {
      logger.debug("Fichero firmado correctamente");
      ResultadoFirmaFichero resultado = (ResultadoFirmaFichero) cont;
      firma = resultado.getFirma();
    } else {
      es.mpt.dsic.firma.cliente.model.Error error = (es.mpt.dsic.firma.cliente.model.Error) cont;
      throw new InSideServiceSignerException("No se ha firmado correctamente el fichero: "
          + error.getMensaje() + " " + error.getCausa());
    }
    return firma;
  }


  public Properties getProperties() {
    return properties;
  }

  @Required
  public void setProperties(Properties properties) {
    this.properties = properties;
  }

  public boolean isActivo() {
    return activo;
  }



}
