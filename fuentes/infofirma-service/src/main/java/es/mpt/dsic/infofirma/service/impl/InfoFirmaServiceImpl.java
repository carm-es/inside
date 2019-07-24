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

package es.mpt.dsic.infofirma.service.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.soap.SOAPBinding;
import org.apache.axiom.attachments.ByteArrayDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import es.mpt.dsic.eeutil.client.model.DatosFirmados;
import es.mpt.dsic.eeutil.client.operFirma.model.ApplicationLogin;
import es.mpt.dsic.eeutil.client.operFirma.model.ConfiguracionAmpliarFirma;
import es.mpt.dsic.eeutil.client.operFirma.model.DatosFirmadosMtom;
import es.mpt.dsic.eeutil.client.operFirma.model.EeUtilServiceMtom;
import es.mpt.dsic.eeutil.client.operFirma.model.EeUtilServiceMtomImplService;
import es.mpt.dsic.eeutil.client.operFirma.model.InSideException;
import es.mpt.dsic.eeutil.client.operFirma.model.InformacionFirma;
import es.mpt.dsic.eeutil.client.operFirma.model.OpcionesObtenerInformacionFirma;
import es.mpt.dsic.eeutil.client.operFirma.model.ResultadoAmpliarFirmaMtom;
import es.mpt.dsic.eeutil.client.operFirma.model.ResultadoValidacionInfo;
import es.mpt.dsic.eeutil.client.operFirma.model.ResultadoValidarCertificado;
import es.mpt.dsic.infofirma.converter.InfoFirmaConverter;
import es.mpt.dsic.infofirma.model.FirmaElectronica;
import es.mpt.dsic.infofirma.model.InfoFirmaElectronica;
import es.mpt.dsic.infofirma.model.OpcionesInfoFirma;
import es.mpt.dsic.infofirma.service.InfoFirmaService;
import es.mpt.dsic.infofirma.service.exception.InfoFirmaServiceException;
import es.mpt.dsic.infofirma.service.exception.InfoFirmaServiceNoEsFirmaException;

public class InfoFirmaServiceImpl implements InfoFirmaService {

  protected static final Log logger = LogFactory.getLog(InfoFirmaServiceImpl.class);

  private boolean activo;
  private static String ACTIVO = "S";

  private Properties properties;
  private EeUtilServiceMtom port;
  private ApplicationLogin applicationLogin;

  private boolean configureInfoFirma() {
    String infoFirmaActivo = properties.getProperty("infofirma.activo");
    try {
      if (!activo) {
        if (!ACTIVO.contentEquals(infoFirmaActivo)) {
          logger
              .info("El WS de INFOFIRMA para la obtención de información de firmas no está activo");
          activo = false;
        } else {
          String urlInfoFirma = properties.getProperty("infofirma.url");
          logger.debug(String.format("El WS de INFOFIRMA se encuentra en %s", urlInfoFirma));

          EeUtilServiceMtomImplService service1 =
              new EeUtilServiceMtomImplService(new URL(properties.getProperty("infofirma.url")));
          port = service1.getPort(EeUtilServiceMtom.class);
          BindingProvider bp = (BindingProvider) port;
          // Habilitar MTOM en cliente
          // ****************************************************************************************
          SOAPBinding soapBinding = (SOAPBinding) bp.getBinding();
          soapBinding.setMTOMEnabled(true);
          // ****************************************************************************************
          bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, urlInfoFirma);

          applicationLogin = new ApplicationLogin();
          applicationLogin.setIdaplicacion(properties.getProperty("infofirma.idaplicacion"));
          applicationLogin.setPassword(properties.getProperty("infofirma.password"));

          logger.debug(String.format("Utilizando para infofirma idaplicacion/password : %s/%s",
              properties.getProperty("infofirma.idaplicacion"),
              properties.getProperty("infofirma.password")));
          activo = true;
        }
      }
    } catch (MalformedURLException e) {
      activo = false;
    }
    return activo;
  }

  public DataHandler ampliarFirma(DataHandler firmaMtom,
      ConfiguracionAmpliarFirma configuracionAmpliarFirma) throws InfoFirmaServiceException {

    ResultadoAmpliarFirmaMtom resultado;
    try {
      resultado = port.ampliarFirma(applicationLogin, firmaMtom, configuracionAmpliarFirma);
    } catch (InSideException e) {
      logger.debug(e);
      throw new InfoFirmaServiceException(
          "Error " + e.getFaultInfo().getCodigo() + " " + e.getFaultInfo().getDescripcion());

    }

    return resultado.getFirma();

  }

  /**
   * Obtiene la información de una firma electrónica.
   * 
   * @throws InfoFirmaServiceNoEsFirmaException cuando el contenido aportado no se corresponde con
   *         una firma electrónica en un formato de los admitidos.
   * @throws InfoFirmaServiceException cuando no se pueda obtener la información de la firma
   */
  @Override
  public InfoFirmaElectronica getInfoFirma(FirmaElectronica firma, OpcionesInfoFirma opciones,
      byte[] bytesContenido) throws InfoFirmaServiceNoEsFirmaException, InfoFirmaServiceException {
    if (!configureInfoFirma()) {
      throw new InfoFirmaServiceException(
          "El WS de INFOFIRMA para la obtención de información de firmas no está activo");
    }

    if (firma == null || firma.getFirma() == null) {
      throw new InfoFirmaServiceException(
          new IllegalArgumentException("La firma no puede ser nula"));
    }

    try {
      OpcionesObtenerInformacionFirma opcionesWS = getOpcionesInfoFirma(opciones);

      DataSource dataSourceFirma = new ByteArrayDataSource(firma.getFirma());
      DataHandler dataHanlerFirma = new DataHandler(dataSourceFirma);

      DataHandler dataHanlerFirmaContenido = null;
      if (bytesContenido != null) {
        DataSource dataSourceFirmaContenido = new ByteArrayDataSource(bytesContenido);
        dataHanlerFirmaContenido = new DataHandler(dataSourceFirmaContenido);
      }

      InformacionFirma infoFirma = port.obtenerInformacionFirma(applicationLogin, dataHanlerFirma,
          opcionesWS, dataHanlerFirmaContenido);

      if (!infoFirma.isEsFirma()) {
        throw new InfoFirmaServiceNoEsFirmaException(
            "El contenido aportado no se corresponde con una firma electrónica de un formato de los admitidos");
      }

      return InfoFirmaConverter.informacionFirmaToInfoFirmaElectronica(infoFirma);

    } catch (InSideException e) {
      logger.debug(e);
      if ("COD_0001".equalsIgnoreCase(e.getFaultInfo().getCodigo())
          || "COD_0002".equalsIgnoreCase(e.getFaultInfo().getCodigo())
          || "COD_0003".equalsIgnoreCase(e.getFaultInfo().getCodigo())) {

        if ("COD_0002".equalsIgnoreCase(e.getFaultInfo().getCodigo())) {
          throw new InfoFirmaServiceException("El fomato de firma presentado no está admitido.");
        }
        throw new InfoFirmaServiceNoEsFirmaException(
            "El contenido aportado no se corresponde con una firma electrónica de un formato de los admitidos");
      } else {
        logger.warn(
            "El servicio de obtención de información de firma está devolviendo un error: CODIGO: "
                + e.getFaultInfo().getCodigo() + " DESCRIPCION: "
                + e.getFaultInfo().getDescripcion());
        throw new InfoFirmaServiceException(
            "El servicio de obtención de información de firma está devolviendo un error");
      }

    } catch (Exception t) {
      logger.error("Excepción en la llamada al WS de obtención de Información de Firma", t);
      throw new InfoFirmaServiceException(
          "Excepción en la llamada al WS de obtención de Información de Firma", t);
    }

  }

  public OpcionesObtenerInformacionFirma getOpcionesInfoFirma(OpcionesInfoFirma opciones) {
    OpcionesObtenerInformacionFirma opcionesWS;
    if (opciones == null) {
      opcionesWS = new OpcionesObtenerInformacionFirma();
      opcionesWS.setObtenerDatosFirmados(true);
      opcionesWS.setObtenerFirmantes(true);
      opcionesWS.setObtenerTipoFirma(true);
    } else {
      opcionesWS = InfoFirmaConverter.OpcionesInfoFirmaToOpcionesObtenerInformacionFirma(opciones);
    }
    return opcionesWS;
  }

  public Properties getProperties() {
    return properties;
  }

  @Required
  public void setProperties(Properties properties) {
    this.properties = properties;
  }

  @Override
  public ResultadoValidarCertificado getInfoCertificate(String certificate)
      throws InfoFirmaServiceException, InSideException {
    if (!configureInfoFirma()) {
      throw new InfoFirmaServiceException(
          "El WS de INFOFIRMA para la obtención de información de certificados no está activo");
    }

    if (certificate == null || "".equals(certificate)) {
      throw new InfoFirmaServiceException(
          new IllegalArgumentException("El certificado no puede ser vacio"));
    }

    return port.validarCertificado(applicationLogin, certificate);
  }

  @Override
  public ResultadoValidacionInfo validacionFirma(FirmaElectronica firma, String tipoFirma,
      DatosFirmados datosFirmados) throws InfoFirmaServiceException {

    try {
      if (!configureInfoFirma()) {
        throw new InfoFirmaServiceException(
            "El WS de INFOFIRMA para la obtención de información de firmas no está activo");
      }

      if (firma == null || firma.getFirma() == null) {
        throw new InfoFirmaServiceException(
            new IllegalArgumentException("La firma no puede ser nula"));
      }

      DataSource dataSourceFirma = new ByteArrayDataSource(firma.getFirma());
      DataHandler dataHanlerFirma = new DataHandler(dataSourceFirma);

      DatosFirmadosMtom dataHanlerDatosFirmados = null;
      if (datosFirmados != null) {
        dataHanlerDatosFirmados = new DatosFirmadosMtom();
        dataHanlerDatosFirmados.setAlgoritmo(datosFirmados.getAlgoritmo());
        DataSource dataSourceDatosFirmados = new ByteArrayDataSource(datosFirmados.getDocumento());
        dataHanlerDatosFirmados.setDocumento(new DataHandler(dataSourceDatosFirmados));
      }

      ResultadoValidacionInfo validacionInfo;

      validacionInfo = port.validacionFirma(applicationLogin, dataHanlerFirma, tipoFirma,
          dataHanlerDatosFirmados);
      return validacionInfo;
    } catch (InSideException e) {
      logger.debug("Error de validación de firma" + e);
      throw new InfoFirmaServiceException(
          "El servicio de validación de firma está devolviendo un error");
    }

  }

}
