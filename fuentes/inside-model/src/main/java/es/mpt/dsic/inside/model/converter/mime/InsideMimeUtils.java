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

package es.mpt.dsic.inside.model.converter.mime;

import java.nio.charset.Charset;
import java.text.Normalizer;
import java.util.Collection;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import es.mpt.dsic.inside.model.config.ModelEnhancedConf;
import es.mpt.dsic.inside.model.converter.exception.InsideConverterUtilsException;
import eu.medsea.mimeutil.detector.ExtensionMimeDetector;
import eu.medsea.mimeutil.detector.MagicMimeMimeDetector;
import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatch;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;

public class InsideMimeUtils {

  protected static final Log logger = LogFactory.getLog(InsideMimeUtils.class);

  /**
   * Obtiene el tipo mime en base al contenido de un fichero @param data @return @throws
   * InsideConverterUtilsException @throws
   */
  public static String getMimeType(byte[] data) throws InsideConverterUtilsException {
    String mime = getFirstMimeTypeMimeUtil(new MagicMimeMimeDetector().getMimeTypes(data));
    logger.debug("Mime obtenido con mimeutil: " + mime);
    // Si con mimeutil no hemos podido obtener el mime, lo intentamos con jmimemagic
    if (mime == null) {
      mime = getMimeMimeMagic(data);
    }
    // Si el mime es de un ZIP, hacemos otra pasada por si fuera un documento de Office moderno.
    if ("application/zip".equalsIgnoreCase(mime) || "application/x-zip".equalsIgnoreCase(mime)) {
      mime = OfficeAnalizer.getMimeType(data);
    }

    return mime;
  }

  /**
   * Obtiene la extensión de un fichero. Si no es de Office lo intenta con MagicMatch.
   * 
   * @param data
   * @return
   */
  public static String getExtension(byte[] data) {

    String extension = OfficeAnalizer.getExtension(data);
    return extension != null ? extension : getMagicMatchExtension(data);

  }

  /**
   * Obtiene el tipo mime en base al contenido de un fichero y el nombre de su fichero @param
   * filename @param data @return @throws
   */
  private static String getMimeType(String filename, byte[] data)
      throws InsideConverterUtilsException {
    String mimeType = getFirstMimeTypeMimeUtil(new ExtensionMimeDetector().getMimeTypes(filename));
    return mimeType != null ? mimeType : getMimeType(data);
  }

  /**
   * Dada una colección de tipos mime, da el primero
   * 
   * @param mimeTypes
   * @return
   * @throws InsideConverterUtilsException
   */
  private static String getFirstMimeTypeMimeUtil(Collection<?> mimeTypes)
      throws InsideConverterUtilsException {
    if (mimeTypes.size() >= 1) {
      return mimeTypes.iterator().next() + "";
    } else {
      return null;
      // throw new InsideConverterUtilsException("No se ha detectado ningún tipo mime");
    }
  }

  private static String getMimeMimeMagic(byte[] data) {
    String mime = null;
    Magic parser = new Magic();
    try {
      @SuppressWarnings("static-access")
      MagicMatch match = parser.getMagicMatch(data);
      mime = match.getMimeType();
    } catch (MagicException e) {
      logger.debug("MagicException", e);
    } catch (MagicParseException e) {
      logger.debug("MagicParseException", e);
    } catch (MagicMatchNotFoundException e) {
    	 // CARM ### v2.0.8.1
        if (ModelEnhancedConf.isActivoMagicMatchNotFoundExceptionTryAscii()) {
          String utf8String = new String(data, Charset.defaultCharset());
          String asciiString =
              Normalizer.normalize(utf8String, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
          Magic parserAux = new Magic();
          try {
            byte[] dataAscii = asciiString.getBytes();
            @SuppressWarnings("static-access")
            MagicMatch match = parserAux.getMagicMatch(dataAscii);
            mime = match.getMimeType();
          } catch (MagicException e1) {
            logger.debug("MagicException", e1);
          } catch (MagicParseException e1) {
            logger.debug("MagicParseException", e1);
          } catch (MagicMatchNotFoundException e1) {
            logger.debug("MagicMatchNotFoundException", e);
          }
        } else
          // CARM 2.0.8.1 ###
          logger.debug("MagicMatchNotFoundException", e);
    }
    return mime;
  }

  /**
   * Devuelve la extensión de un fichero
   * 
   * @param data
   * @return
   */
  private static String getMagicMatchExtension(byte[] data) {
    String mime = null;
    Magic parser = new Magic();
    try {
      @SuppressWarnings("static-access")
      MagicMatch match = parser.getMagicMatch(data);
      mime = match.getExtension();
    } catch (MagicException e) {
      logger.debug("MagicException", e);
    } catch (MagicParseException e) {
      logger.debug("MagicParseException", e);
    } catch (MagicMatchNotFoundException e) {
      logger.debug("MagicMatchNotFoundException", e);
    }
    return mime;
  }

  /**
   * Recupera el contenido de un fichero mimeType=text/xml.
   * 
   * entrada: <CONTENT Id="CONTENT-81fe6a7d-1b07-4e9b-b4ea-ced5801a3fb9" MimeType="text/xml"> <raiz>
   * contenido </raiz> </CONTENT>
   * 
   * devuelve: <raiz> contenido </raiz>
   */
  public static String getCONTENTContenidoXml(String datosXML) {

    return datosXML.substring(
        datosXML.indexOf("MimeType=\"text/xml\">") + "MimeType=\"text/xml\">".length(),
        datosXML.indexOf("</CONTENT>"));
  }
}
