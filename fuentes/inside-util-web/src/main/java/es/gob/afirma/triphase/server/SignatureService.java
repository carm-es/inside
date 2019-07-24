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

package es.gob.afirma.triphase.server;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.net.HttpURLConnection;
import java.net.URLDecoder;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import es.gob.afirma.core.AOException;
import es.gob.afirma.core.misc.AOUtil;
import es.gob.afirma.core.misc.Base64;
import es.gob.afirma.core.signers.AOSignConstants;
import es.gob.afirma.core.signers.CounterSignTarget;
import es.gob.afirma.triphase.server.document.DocumentManager;
import es.gob.afirma.triphase.signer.processors.CAdESASiCSTriPhasePreProcessor;
import es.gob.afirma.triphase.signer.processors.CAdESTriPhasePreProcessor;
import es.gob.afirma.triphase.signer.processors.FacturaETriPhasePreProcessor;
import es.gob.afirma.triphase.signer.processors.PAdESTriPhasePreProcessor;
import es.gob.afirma.triphase.signer.processors.TriPhasePreProcessor;
import es.gob.afirma.triphase.signer.processors.XAdESASiCSTriPhasePreProcessor;
import es.gob.afirma.triphase.signer.processors.XAdESTriPhasePreProcessor;

/** Servicio de firma electr&oacute;nica en 3 fases. */
@PropertySource("file:${config.path}/afirma-server-triphase-signer.properties")
public final class SignatureService extends HttpServlet {

  private static final long serialVersionUID = 1L;

  protected static final Log logger = LogFactory.getLog(SignatureService.class);

  private static DocumentManager DOC_MANAGER;

  private static final String URL_DEFAULT_CHARSET = "utf-8"; //$NON-NLS-1$

  private static final String PARAM_NAME_OPERATION = "op"; //$NON-NLS-1$

  private static final String PARAM_VALUE_OPERATION_PRESIGN = "pre"; //$NON-NLS-1$
  private static final String PARAM_VALUE_OPERATION_POSTSIGN = "post"; //$NON-NLS-1$

  private static final String PARAM_NAME_SUB_OPERATION = "cop"; //$NON-NLS-1$

  private static final String PARAM_VALUE_SUB_OPERATION_SIGN = "sign"; //$NON-NLS-1$
  private static final String PARAM_VALUE_SUB_OPERATION_COSIGN = "cosign"; //$NON-NLS-1$
  private static final String PARAM_VALUE_SUB_OPERATION_COUNTERSIGN = "countersign"; //$NON-NLS-1$

  // Parametros que necesitamos para la prefirma
  private static final String PARAM_NAME_DOCID = "doc"; //$NON-NLS-1$
  private static final String PARAM_NAME_ALGORITHM = "algo"; //$NON-NLS-1$
  private static final String PARAM_NAME_FORMAT = "format"; //$NON-NLS-1$
  private static final String PARAM_NAME_EXTRA_PARAM = "params"; //$NON-NLS-1$
  private static final String PARAM_NAME_SESSION_DATA = "session"; //$NON-NLS-1$
  private static final String PARAM_NAME_CERT = "cert"; //$NON-NLS-1$

  /**
   * Separador que debe usarse para incluir varios certificados dentro del mismo par&aacute;metro.
   */
  private static final String PARAM_NAME_CERT_SEPARATOR = ","; //$NON-NLS-1$

  /**
   * Nombre del par&aacute;metro que identifica los nodos que deben contrafirmarse.
   */
  private static final String PARAM_NAME_TARGET_TYPE = "target"; //$NON-NLS-1$

  /** Indicador de finalizaci&oacute;n correcta de proceso. */
  private static final String SUCCESS = "OK NEWID="; //$NON-NLS-1$

  private static final String CONFIG_FILE = "afirma-server-triphase-signer.properties"; //$NON-NLS-1$

  private static final String CONFIG_PARAM_DOCUMENT_MANAGER_CLASS = "document.manager"; //$NON-NLS-1$
  private static final String CONFIG_PARAM_ALLOW_ORIGIN = "Access-Control-Allow-Origin"; //$NON-NLS-1$
  private static final String CONFIG_PARAM_INSTALL_XMLDSIG = "alternative.xmldsig"; //$NON-NLS-1$

  private static final String IN_DIR_PARAM = "indir"; //$NON-NLS-1$
  private static final String OUT_DIR_PARAM = "outdir"; //$NON-NLS-1$
  private static final String OVERWRITE_PARAM = "overwrite"; //$NON-NLS-1$

  /**
   * Or&iacute;genes permitidos por defecto desde los que se pueden realizar peticiones al servicio.
   */
  private static final String ALL_ORIGINS_ALLOWED = "*"; //$NON-NLS-1$

  private Properties configData;

  @Autowired
  private Environment env;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
        config.getServletContext());
    try {
      final InputStream configIs = new FileInputStream(
          System.getProperty("config.path") + System.getProperty("file.separator") + CONFIG_FILE);
      configData = new Properties();
      configData.load(configIs);
      configIs.close();
    } catch (final Exception e) {
      throw new RuntimeException("Error en la carga del fichero de propiedades: " + e, e); //$NON-NLS-1$
    }

    if (!configData.containsKey(CONFIG_PARAM_DOCUMENT_MANAGER_CLASS)) {
      throw new IllegalArgumentException("No se ha indicado el document manager (" //$NON-NLS-1$
          + CONFIG_PARAM_DOCUMENT_MANAGER_CLASS + ") en el fichero de propiedades" //$NON-NLS-1$
      );
    }

    Class<?> docManagerClass;
    try {
      docManagerClass = Class.forName(configData.getProperty(CONFIG_PARAM_DOCUMENT_MANAGER_CLASS));
    } catch (final ClassNotFoundException e) {
      throw new RuntimeException("La clase DocumentManager indicada no existe (" //$NON-NLS-1$
          + configData.getProperty(CONFIG_PARAM_DOCUMENT_MANAGER_CLASS) + "): " + e, e //$NON-NLS-1$
      );
    }

    try {
      final Constructor<?> docManagerConstructor =
          docManagerClass.getConstructor(String.class, String.class, String.class);
      String indir = env.resolvePlaceholders(configData.getProperty(IN_DIR_PARAM));
      String outdir = env.resolvePlaceholders(configData.getProperty(OUT_DIR_PARAM));
      DOC_MANAGER = (DocumentManager) docManagerConstructor.newInstance(indir, outdir,
          configData.getProperty(OVERWRITE_PARAM));
    } catch (final Exception e) {
      try {
        DOC_MANAGER = (DocumentManager) docManagerClass.newInstance();
      } catch (final Exception e2) {
        throw new RuntimeException(
            "No se ha podido inicializar el DocumentManager. Debe tener un constructor vacio o que reciba un Properties: " //$NON-NLS-1$
                + e2,
            e);
      }
    }
  }

  @Override
  protected void service(final HttpServletRequest request, final HttpServletResponse response) {

    logger.info("== INICIO FIRMA TRIFASICA =="); //$NON-NLS-1$

    final Map<String, String> parameters = new HashMap<String, String>();
    final String[] params;
    try {
      params = new String(AOUtil.getDataFromInputStream(request.getInputStream())).split("&"); //$NON-NLS-1$
    } catch (final Exception e) {
      logger.error("No se pudieron leer los parametros de la peticion: " + e); //$NON-NLS-1$
      return;
    }

    for (final String param : params) {
      if (param.indexOf('=') != -1) {
        try {
          parameters.put(param.substring(0, param.indexOf('=')),
              URLDecoder.decode(param.substring(param.indexOf('=') + 1), URL_DEFAULT_CHARSET));
        } catch (final Exception e) {
          logger.warn("Error al decodificar un parametro de la peticion: " + e); //$NON-NLS-1$
        }
      }
    }

    final String allowOrigin =
        configData.getProperty(CONFIG_PARAM_ALLOW_ORIGIN, ALL_ORIGINS_ALLOWED);

    response.setHeader("Access-Control-Allow-Origin", allowOrigin); //$NON-NLS-1$
    response.setContentType("text/plain"); //$NON-NLS-1$
    response.setCharacterEncoding("utf-8"); //$NON-NLS-1$

    // Obtenemos el codigo de operacion
    PrintWriter out = null;
    try {
      out = response.getWriter();
    } catch (final Exception e) {
      logger.error("No se pudo contestar a la peticion: " + e); //$NON-NLS-1$
      try {
        response.sendError(HttpURLConnection.HTTP_INTERNAL_ERROR,
            "No se pude contestar a la peticion: " + e); //$NON-NLS-1$
      } catch (final IOException e1) {
        logger.error("No se pudo enviar un error HTTP 500: " + e1); //$NON-NLS-1$
      }
      return;
    }

    final String operation = parameters.get(PARAM_NAME_OPERATION);
    if (operation == null) {
      logger.error("No se ha indicado la operacion trifasica a realizar"); //$NON-NLS-1$
      out.print(ErrorManager.getErrorMessage(1));
      out.flush();
      return;
    }

    // Obtenemos el codigo de operacion
    final String subOperation = parameters.get(PARAM_NAME_SUB_OPERATION);
    if (subOperation == null || !PARAM_VALUE_SUB_OPERATION_SIGN.equalsIgnoreCase(subOperation)
        && !PARAM_VALUE_SUB_OPERATION_COSIGN.equalsIgnoreCase(subOperation)
        && !PARAM_VALUE_SUB_OPERATION_COUNTERSIGN.equalsIgnoreCase(subOperation)) {
      out.print(ErrorManager.getErrorMessage(13));
      out.flush();
      return;
    }

    // Obtenemos los parametros adicionales para la firma
    final Properties extraParams = new Properties();
    try {
      if (parameters.containsKey(PARAM_NAME_EXTRA_PARAM)) {
        extraParams.load(new ByteArrayInputStream(
            Base64.decode(parameters.get(PARAM_NAME_EXTRA_PARAM).trim(), true)));
      }
    } catch (final Exception e) {
      logger.error("El formato de los parametros adicionales suministrado es erroneo: " + e); //$NON-NLS-1$
      out.print(ErrorManager.getErrorMessage(6) + ": " + e); //$NON-NLS-1$ );
      out.flush();
      return;
    }

    // Obtenemos los parametros adicionales para la firma
    byte[] sessionData = null;
    try {
      if (parameters.containsKey(PARAM_NAME_SESSION_DATA)) {
        sessionData = Base64.decode(parameters.get(PARAM_NAME_SESSION_DATA).trim(), true);
      }
    } catch (final Exception e) {
      logger.error("El formato de los datos de sesion suministrados es erroneo: " + e); //$NON-NLS-1$
      out.print(ErrorManager.getErrorMessage(6) + ": " + e); //$NON-NLS-1$
      out.flush();
      return;
    }

    if (sessionData != null) {
      logger.info("Recibidos los siguientes datos de sesion para '" + operation + "':\n" //$NON-NLS-1$ //$NON-NLS-2$
          + new String(sessionData));
    }

    // Obtenemos el certificado
    final String cert = parameters.get(PARAM_NAME_CERT);
    if (cert == null) {
      logger.warn("No se ha indicado certificado de firma"); //$NON-NLS-1$
      out.print(ErrorManager.getErrorMessage(5));
      out.flush();
      return;
    }

    final String[] receivedCerts = cert.split(PARAM_NAME_CERT_SEPARATOR);
    final X509Certificate[] signerCertChain = new X509Certificate[receivedCerts.length];
    for (int i = 0; i < receivedCerts.length; i++) {
      try {
        signerCertChain[i] =
            (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate( //$NON-NLS-1$
                new ByteArrayInputStream(Base64.decode(cert, true)));
      } catch (final Exception e) {
        logger.error("Error al decodificar el certificado: " + e); //$NON-NLS-1$
        out.print(ErrorManager.getErrorMessage(7));
        out.flush();
        return;
      }
    }

    byte[] docBytes = null;
    final String docId = parameters.get(PARAM_NAME_DOCID);
    if (docId != null) {
      try {
        logger.info("Recuperamos el documento mediante el DocumentManager"); //$NON-NLS-1$
        docBytes =
            DOC_MANAGER.getDocument(docId, signerCertChain, extraParams, request.getSession());
        logger.info("Recuperado documento de " + docBytes.length + " octetos" //$NON-NLS-1$ //$NON-NLS-2$
        );
      } catch (final IOException e) {
        logger.warn("Error al recuperar el documento: " + e); //$NON-NLS-1$
        out.print(ErrorManager.getErrorMessage(14) + ": " + e); //$NON-NLS-1$
        out.flush();
        return;
      }
    }

    // Obtenemos el algoritmo de firma
    final String algorithm = parameters.get(PARAM_NAME_ALGORITHM);
    if (algorithm == null) {
      logger.warn("No se ha indicado algoritmo de firma"); //$NON-NLS-1$
      out.print(ErrorManager.getErrorMessage(3));
      out.flush();
      return;
    }

    // Obtenemos el formato de firma
    final String format = parameters.get(PARAM_NAME_FORMAT);
    logger.info("Formato de firma seleccionado: " + format); //$NON-NLS-1$
    if (format == null) {
      logger.warn("No se ha indicado formato de firma"); //$NON-NLS-1$
      out.print(ErrorManager.getErrorMessage(4));
      out.flush();
      return;
    }

    // Instanciamos el preprocesador adecuado
    final TriPhasePreProcessor prep;
    if (AOSignConstants.SIGN_FORMAT_PADES.equalsIgnoreCase(format)
        || AOSignConstants.SIGN_FORMAT_PADES_TRI.equalsIgnoreCase(format)) {
      prep = new PAdESTriPhasePreProcessor();
    } else if (AOSignConstants.SIGN_FORMAT_CADES.equalsIgnoreCase(format)
        || AOSignConstants.SIGN_FORMAT_CADES_TRI.equalsIgnoreCase(format)) {
      prep = new CAdESTriPhasePreProcessor();
    } else if (AOSignConstants.SIGN_FORMAT_XADES.equalsIgnoreCase(format)
        || AOSignConstants.SIGN_FORMAT_XADES_TRI.equalsIgnoreCase(format)) {
      final boolean installXmlDSig = Boolean.parseBoolean(
          configData.getProperty(CONFIG_PARAM_INSTALL_XMLDSIG, Boolean.FALSE.toString()));
      prep = new XAdESTriPhasePreProcessor(installXmlDSig);
    } else if (AOSignConstants.SIGN_FORMAT_CADES_ASIC_S.equalsIgnoreCase(format)
        || AOSignConstants.SIGN_FORMAT_CADES_ASIC_S_TRI.equalsIgnoreCase(format)) {
      prep = new CAdESASiCSTriPhasePreProcessor();
    } else if (AOSignConstants.SIGN_FORMAT_XADES_ASIC_S.equalsIgnoreCase(format)
        || AOSignConstants.SIGN_FORMAT_XADES_ASIC_S_TRI.equalsIgnoreCase(format)) {
      final boolean installXmlDSig = Boolean.parseBoolean(
          configData.getProperty(CONFIG_PARAM_INSTALL_XMLDSIG, Boolean.FALSE.toString()));
      prep = new XAdESASiCSTriPhasePreProcessor(installXmlDSig);
    } else if (AOSignConstants.SIGN_FORMAT_FACTURAE.equalsIgnoreCase(format)
        || AOSignConstants.SIGN_FORMAT_FACTURAE_TRI.equalsIgnoreCase(format)
        || AOSignConstants.SIGN_FORMAT_FACTURAE_ALT1.equalsIgnoreCase(format)) {
      final boolean installXmlDSig = Boolean.parseBoolean(
          configData.getProperty(CONFIG_PARAM_INSTALL_XMLDSIG, Boolean.FALSE.toString()));
      prep = new FacturaETriPhasePreProcessor(installXmlDSig);
    } else {
      logger.error("Formato de firma no soportado: " + format); //$NON-NLS-1$
      out.print(ErrorManager.getErrorMessage(8));
      out.flush();
      return;
    }

    if (PARAM_VALUE_OPERATION_PRESIGN.equalsIgnoreCase(operation)) {

      logger.info(" == PREFIRMA en servidor"); //$NON-NLS-1$

      final byte[] preRes;
      try {
        if (PARAM_VALUE_SUB_OPERATION_SIGN.equalsIgnoreCase(subOperation)) {
          preRes = prep.preProcessPreSign(docBytes, algorithm, signerCertChain, extraParams);
        } else if (PARAM_VALUE_SUB_OPERATION_COSIGN.equalsIgnoreCase(subOperation)) {
          preRes = prep.preProcessPreCoSign(docBytes, algorithm, signerCertChain, extraParams);
        } else if (PARAM_VALUE_SUB_OPERATION_COUNTERSIGN.equalsIgnoreCase(subOperation)) {

          CounterSignTarget target = CounterSignTarget.LEAFS;
          if (extraParams.containsKey(PARAM_NAME_TARGET_TYPE)) {
            final String targetValue = extraParams.getProperty(PARAM_NAME_TARGET_TYPE).trim();
            if (CounterSignTarget.TREE.toString().equalsIgnoreCase(targetValue)) {
              target = CounterSignTarget.TREE;
            }
          }

          preRes = prep.preProcessPreCounterSign(docBytes, algorithm, signerCertChain, extraParams,
              target);
        } else {
          throw new AOException("No se reconoce el codigo de sub-operacion: " + subOperation); //$NON-NLS-1$
        }

        logger.info("Se ha calculado el resultado de la prefirma y se devuelve. Numero de bytes: " //$NON-NLS-1$
            + preRes.length);
      } catch (final Exception e) {
        logger.error("Error en la prefirma: " + e, e); //$NON-NLS-1$
        out.print(ErrorManager.getErrorMessage(9) + ": " + e); //$NON-NLS-1$
        out.flush();
        return;
      }

      out.print(Base64.encode(preRes, true));

      out.flush();

      logger.info("== FIN PREFIRMA"); //$NON-NLS-1$
    } else if (PARAM_VALUE_OPERATION_POSTSIGN.equalsIgnoreCase(operation)) {

      logger.info(" == POSTFIRMA en servidor"); //$NON-NLS-1$

      final byte[] signedDoc;
      try {
        if (PARAM_VALUE_SUB_OPERATION_SIGN.equals(subOperation)) {
          signedDoc = prep.preProcessPostSign(docBytes, algorithm, signerCertChain, extraParams,
              sessionData);
        } else if (PARAM_VALUE_SUB_OPERATION_COSIGN.equals(subOperation)) {
          signedDoc = prep.preProcessPostCoSign(docBytes, algorithm, signerCertChain, extraParams,
              sessionData);
        } else if (PARAM_VALUE_SUB_OPERATION_COUNTERSIGN.equals(subOperation)) {

          CounterSignTarget target = CounterSignTarget.LEAFS;
          if (extraParams.containsKey(PARAM_NAME_TARGET_TYPE)) {
            final String targetValue = extraParams.getProperty(PARAM_NAME_TARGET_TYPE).trim();
            if (CounterSignTarget.TREE.toString().equalsIgnoreCase(targetValue)) {
              target = CounterSignTarget.TREE;
            }
          }

          signedDoc = prep.preProcessPostCounterSign(docBytes, algorithm, signerCertChain,
              extraParams, sessionData, target);
        } else {
          throw new AOException("No se reconoce el codigo de sub-operacion: " + subOperation); //$NON-NLS-1$
        }
      } catch (final Exception e) {
        logger.error("Error en la postfirma: " + e, e); //$NON-NLS-1$
        out.print(ErrorManager.getErrorMessage(12) + ": " + e); //$NON-NLS-1$
        out.flush();
        return;
      }

      // Establecemos parametros adicionales que se pueden utilizar para
      // guardar el documento
      extraParams.setProperty(PARAM_NAME_FORMAT, format);

      logger.info(" Se ha calculado el resultado de la postfirma y se devuelve. Numero de bytes: " //$NON-NLS-1$
          + signedDoc.length);

      // Devolvemos al servidor documental el documento firmado
      logger.info("Almacenamos la firma mediante el DocumentManager"); //$NON-NLS-1$
      final String newDocId;
      try {
        newDocId = DOC_MANAGER.storeDocument(docId, signerCertChain, signedDoc, extraParams,
            request.getSession());
      } catch (final IOException e) {
        logger.error("Error al almacenar el documento: " + e); //$NON-NLS-1$
        out.print(ErrorManager.getErrorMessage(10) + ": " + e); //$NON-NLS-1$
        out.flush();
        return;
      }
      logger.info("Documento almacenado"); //$NON-NLS-1$

      out.println(new StringBuilder(newDocId.length() + SUCCESS.length()).append(SUCCESS)
          .append(newDocId).toString());
      out.flush();

      logger.info("== FIN POSTFIRMA"); //$NON-NLS-1$
    } else {
      out.println(ErrorManager.getErrorMessage(11));
    }
  }
}
