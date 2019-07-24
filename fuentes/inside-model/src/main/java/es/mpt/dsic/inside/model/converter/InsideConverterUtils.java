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

package es.mpt.dsic.inside.model.converter;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.xerces.dom.ElementNSImpl;
import org.apache.xerces.dom.TextImpl;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;
import es.mpt.dsic.inside.model.converter.exception.InsideConverterUtilsException;
import es.mpt.dsic.inside.model.converter.mime.InsideMimeUtils;
import es.mpt.dsic.inside.xml.eni.firma.TipoFirma;
import es.mpt.dsic.inside.xml.w3c.SignatureType;
import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicMatch;

public class InsideConverterUtils {

  /**
   * Devuelve el mime que tendría un documento según el nombre del formato de éste.
   * 
   * @param nombreFormato
   * @return
   */
  public static String getMimeByNombreFormato(String nombreFormato) {
    String mime = null;
    // DOCUMENTOS MÁS COMUNES
    if ("PDF".equalsIgnoreCase(nombreFormato)) {
      mime = "application/pdf";
    } else if ("XML".equalsIgnoreCase(nombreFormato)) {
      mime = "text/xml";
    } else if ("TXT".equalsIgnoreCase(nombreFormato)) {
      mime = "text/plain";
    } else if ("HTML".equalsIgnoreCase(nombreFormato)) {
      mime = "text/html";

      // OFFICE Y OPENOFFICE
      // WORD
    } else if ("DOC".equalsIgnoreCase(nombreFormato)) {
      mime = "application/msword";
    } else if ("DOCX".equalsIgnoreCase(nombreFormato)) {
      mime = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
    } else if ("ODT".equalsIgnoreCase(nombreFormato)) {
      mime = "application/vnd.oasis.opendocument.text";
    } else if ("DOTX".equalsIgnoreCase(nombreFormato)) {
      mime = "application/application/vnd.openxmlformats-officedocument.wordprocessingml.template";
    } else if ("DOCM".equalsIgnoreCase(nombreFormato)) {
      mime = "application/vnd.ms-word.document.macroEnabled.12";
    } else if ("DOTM".equalsIgnoreCase(nombreFormato)) {
      mime = "application/vnd.ms-word.template.macroEnabled.12";

      // HOJAS DE CÁLCULO
    } else if ("XLS".equalsIgnoreCase(nombreFormato)) {
      mime = "application/msexcel";
    } else if ("XLS".equalsIgnoreCase(nombreFormato)) {
      mime = "application/x-msexcel";
    } else if ("XLS".equalsIgnoreCase(nombreFormato)) {
      mime = "application/vnd.ms-excel";
    } else if ("XLSX".equalsIgnoreCase(nombreFormato)) {
      mime = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    } else if ("XLTX".equalsIgnoreCase(nombreFormato)) {
      mime = "application/vnd.openxmlformats-officedocument.spreadsheetml.template";
    } else if ("XLSM".equalsIgnoreCase(nombreFormato)) {
      mime = "application/vnd.ms-excel.sheet.macroEnabled.12";
    } else if ("XLTM".equalsIgnoreCase(nombreFormato)) {
      mime = "application/vnd.ms-excel.template.macroEnabled.12";
    } else if ("XLAM".equalsIgnoreCase(nombreFormato)) {
      mime = "application/vnd.ms-excel.addin.macroEnabled.12";
    } else if ("XLSB".equalsIgnoreCase(nombreFormato)) {
      mime = "application/vnd.ms-excel.sheet.binary.macroEnabled.12";

      // PRESENTACIONES
    } else if ("PPT".equalsIgnoreCase(nombreFormato)) {
      mime = "application/mspowerpoint";
    } else if ("PPT".equalsIgnoreCase(nombreFormato)) {
      mime = "application/vnd.ms-powerpoint";
    } else if ("PPTX".equalsIgnoreCase(nombreFormato)) {
      mime = "application/vnd.openxmlformats-officedocument.presentationml.presentation";
    } else if ("POTX".equalsIgnoreCase(nombreFormato)) {
      mime = "application/vnd.openxmlformats-officedocument.presentationml.template";
    } else if ("PPSX".equalsIgnoreCase(nombreFormato)) {
      mime = "application/vnd.openxmlformats-officedocument.presentationml.slideshow";
    } else if ("PPAM".equalsIgnoreCase(nombreFormato)) {
      mime = "application/vnd.ms-powerpoint.addin.macroEnabled.12";
    } else if ("PPTM".equalsIgnoreCase(nombreFormato)) {
      mime = "application/vnd.ms-powerpoint.presentation.macroEnabled.12";
    } else if ("POTM".equalsIgnoreCase(nombreFormato)) {
      mime = "application/vnd.ms-powerpoint.template.macroEnabled.12";
    } else if ("PPSM".equalsIgnoreCase(nombreFormato)) {
      mime = "application/vnd.ms-powerpoint.slideshow.macroEnabled.12";

      // OTROS DOCUMENTOS DE OFFICE
      // Microsoft Project
    } else if ("MPP".equalsIgnoreCase(nombreFormato)) {
      mime = "application/vnd.ms-project";
      // Microsoft Visio
    } else if ("VSD".equalsIgnoreCase(nombreFormato)) {
      mime = "application/vnd.ms-visio";

    } else if ("RTF".equalsIgnoreCase(nombreFormato)) {
      mime = "application/x-rtf";

      // IMÁGENES
    } else if ("JPG".equalsIgnoreCase(nombreFormato) || "JPEG".equalsIgnoreCase(nombreFormato)) {
      mime = "image/jpeg";
    } else if ("PNG".equalsIgnoreCase(nombreFormato)) {
      mime = "image/png";
    } else if ("GIF".equalsIgnoreCase(nombreFormato)) {
      mime = "image/gif";
    } else if ("TIF".equalsIgnoreCase(nombreFormato)) {
      mime = "image/tiff";
    } else if ("SVF".equalsIgnoreCase(nombreFormato)) {
      mime = "image/vnd-dwg";
    } else if ("SVG".equalsIgnoreCase(nombreFormato)) {
      mime = "image/svg+xml";

      // CERTIFICADOS
    } else if ("P12".equalsIgnoreCase(nombreFormato)) {
      mime = "application/pkcs-12";
    } else if ("CERT".equalsIgnoreCase(nombreFormato)) {
      mime = "application/x-x509-ca-cert";

    } else if ("PS".equalsIgnoreCase(nombreFormato)) {
      mime = "application/postscript";

      // COMPRESIÓN DE FICHEROS
    } else if ("BZ".equalsIgnoreCase(mime)) {
      mime = "application/x-bzip";
    } else if ("BZ2".equalsIgnoreCase(nombreFormato)) {
      mime = "application/x-bzip2";
    } else if ("ZIP".equalsIgnoreCase(nombreFormato)) {
      mime = "application/zip";
    } else if ("GZIP".equalsIgnoreCase(nombreFormato)) {
      mime = "application/x-gzip";

      // AUDIO
    } else if ("RM".equalsIgnoreCase(nombreFormato)) {
      mime = "application/vnd.rn-realmedia";
    } else if ("AIF".equalsIgnoreCase(nombreFormato)) {
      mime = "audio/aiff";
    } else if ("AU".equalsIgnoreCase(nombreFormato)) {
      mime = "audio/basic";
    } else if ("MIDI".equalsIgnoreCase(nombreFormato)) {
      mime = "audio/midi";
    } else if ("MPG".equalsIgnoreCase(nombreFormato)) {
      mime = "audio/mpeg";
    } else if ("MP3".equalsIgnoreCase(nombreFormato)) {
      mime = "audio/mpeg3";

      // VIDEO
    } else if ("DVI".equalsIgnoreCase(nombreFormato)) {
      mime = "application/x-dvi";
    } else if ("AVI".equalsIgnoreCase(nombreFormato)) {
      mime = "application/x-troff-msvideo";
    } else if ("MOV".equalsIgnoreCase(nombreFormato)) {
      mime = "video/quicktime";

    } else {
      mime = "application/octect-stream";
    }
    return mime;
  }

  /**
   * Devuelve el nombre de formato que tendría un documento según el mime, si no es conocido
   * devolvemos NULL
   * 
   * @param mime
   * @return
   * @throws InsideConverterUtilsException
   */
  public static String getNombreFormatoByMime(String mime) throws InsideConverterUtilsException {
    String nombreFormato = null;

    // DOCUMENTOS MÁS COMUNES
    if ("application/pdf".equalsIgnoreCase(mime)) {
      nombreFormato = "PDF";
    } else if ("text/xml".equalsIgnoreCase(mime) || "application/xml".equalsIgnoreCase(mime)) {
      nombreFormato = "XML";
    } else if ("text/plain".equalsIgnoreCase(mime)) {
      nombreFormato = "TXT";
    } else if ("text/html".equalsIgnoreCase(mime)) {
      nombreFormato = "HTML";

      // OFFICE Y OPENOFFICE
      // WORD
    } else if ("application/msword".equalsIgnoreCase(mime)) {
      nombreFormato = "DOC";
    } else if ("application/vnd.openxmlformats-officedocument.wordprocessingml.document"
        .equalsIgnoreCase(mime)) {
      nombreFormato = "DOCX";
    } else if ("application/vnd.oasis.opendocument.text".equalsIgnoreCase(mime)) {
      nombreFormato = "ODT";
    } else if ("application/vnd.oasis.opendocument.spreadsheet".equalsIgnoreCase(mime)) {
      nombreFormato = "ODS";
    } else if ("application/vnd.oasis.opendocument.presentation".equalsIgnoreCase(mime)) {
      nombreFormato = "ODP";
    } else if ("application/vnd.oasis.opendocument.graphics".equalsIgnoreCase(mime)) {
      nombreFormato = "ODG";
    } else if ("application/application/vnd.openxmlformats-officedocument.wordprocessingml.template"
        .equalsIgnoreCase(mime)) {
      nombreFormato = "DOTX";
    } else if ("application/vnd.ms-word.document.macroEnabled.12".equalsIgnoreCase(mime)) {
      nombreFormato = "DOCM";
    } else if ("application/vnd.ms-word.template.macroEnabled.12".equalsIgnoreCase(mime)) {
      nombreFormato = "DOTM";

      // HOJAS DE CÁLCULO
    } else if ("application/msexcel".equalsIgnoreCase(mime)) {
      nombreFormato = "XLS";
    } else if ("application/x-msexcel".equalsIgnoreCase(mime)) {
      nombreFormato = "XLS";
    } else if ("application/vnd.ms-excel".equalsIgnoreCase(mime)) {
      nombreFormato = "XLS";
    } else if ("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
        .equalsIgnoreCase(mime)) {
      nombreFormato = "XLSX";
    } else if ("application/vnd.openxmlformats-officedocument.spreadsheetml.template"
        .equalsIgnoreCase(mime)) {
      nombreFormato = "XLTX";
    } else if ("application/vnd.ms-excel.sheet.macroEnabled.12".equalsIgnoreCase(mime)) {
      nombreFormato = "XLSM";
    } else if ("application/vnd.ms-excel.template.macroEnabled.12".equalsIgnoreCase(mime)) {
      nombreFormato = "XLTM";
    } else if ("application/vnd.ms-excel.addin.macroEnabled.12".equalsIgnoreCase(mime)) {
      nombreFormato = "XLAM";
    } else if ("application/vnd.ms-excel.sheet.binary.macroEnabled.12".equalsIgnoreCase(mime)) {
      nombreFormato = "XLSB";

      // PRESENTACIONES
    } else if ("application/mspowerpoint".equalsIgnoreCase(mime)) {
      nombreFormato = "PPT";
    } else if ("application/vnd.ms-powerpoint".equalsIgnoreCase(mime)) {
      nombreFormato = "PPT";
    } else if ("application/vnd.openxmlformats-officedocument.presentationml.presentation"
        .equalsIgnoreCase(mime)) {
      nombreFormato = "PPTX";
    } else if ("application/vnd.openxmlformats-officedocument.presentationml.template"
        .equalsIgnoreCase(mime)) {
      nombreFormato = "POTX";
    } else if ("application/vnd.openxmlformats-officedocument.presentationml.slideshow"
        .equalsIgnoreCase(mime)) {
      nombreFormato = "PPSX";
    } else if ("application/vnd.ms-powerpoint.addin.macroEnabled.12".equalsIgnoreCase(mime)) {
      nombreFormato = "PPAM";
    } else if ("application/vnd.ms-powerpoint.presentation.macroEnabled.12"
        .equalsIgnoreCase(mime)) {
      nombreFormato = "PPTM";
    } else if ("application/vnd.ms-powerpoint.template.macroEnabled.12".equalsIgnoreCase(mime)) {
      nombreFormato = "POTM";
    } else if ("application/vnd.ms-powerpoint.slideshow.macroEnabled.12".equalsIgnoreCase(mime)) {
      nombreFormato = "PPSM";

      // OTROS DOCUMENTOS DE OFFICE
      // Microsoft Project
    } else if ("application/vnd.ms-project".equalsIgnoreCase(mime)) {
      nombreFormato = "MPP";
    } else if ("application/vnd.ms-visio".equalsIgnoreCase(mime)) {
      nombreFormato = "VSD";
    } else if ("application/x-rtf".equalsIgnoreCase(mime) || "text/rtf".equalsIgnoreCase(mime)) {
      nombreFormato = "RTF";

      // IMÁGENES
    } else if ("image/jpeg".equalsIgnoreCase(mime) || "image/jpg".equalsIgnoreCase(mime)) {
      nombreFormato = "JPEG";
    } else if ("image/png".equalsIgnoreCase(mime)) {
      nombreFormato = "PNG";
    } else if ("image/gif".equalsIgnoreCase(mime)) {
      nombreFormato = "GIF";
    } else if ("image/tif".equalsIgnoreCase(mime) || "image/tiff".equalsIgnoreCase(mime)
        || "image/x-tiff".equalsIgnoreCase(mime)) {
      nombreFormato = "TIF";
    } else if ("image/vnd-dwg".equalsIgnoreCase(mime)) {
      nombreFormato = "SVF";
    } else if ("image/x-ms-bmp".equalsIgnoreCase(mime)) {
      nombreFormato = "JPEG";
    } else if ("image/svg+xml".equalsIgnoreCase(mime)) {
      nombreFormato = "SVG";

      // CERTIFICADOS
    } else if ("application/pkcs-12".equalsIgnoreCase(mime)) {
      nombreFormato = "P12";
    } else if ("application/x-pkcs12".equalsIgnoreCase(mime)) {
      nombreFormato = "P12";
    } else if ("application/x-x509-ca-cert".equalsIgnoreCase(mime)) {
      nombreFormato = "CERT";

    } else if ("application/postscript".equalsIgnoreCase(mime)) {
      nombreFormato = "PS";

      // COMPRESIÓN DE FICHEROS
    } else if ("application/x-bzip".equalsIgnoreCase(mime)) {
      nombreFormato = "BZ";
    } else if ("application/x-bzip2".equalsIgnoreCase(mime)) {
      nombreFormato = "BZ2";
    } else if ("application/x-compressed".equalsIgnoreCase(mime)
        || "application/zip".equalsIgnoreCase(mime) || "application/x-zip".equalsIgnoreCase(mime)) {
      nombreFormato = "ZIP";
    } else if ("application/x-gzip".equalsIgnoreCase(mime)) {
      nombreFormato = "GZIP";
    } else if ("application/x-rar".equalsIgnoreCase(mime)) {
      nombreFormato = "RAR";

      // AUDIO
    } else if ("application/vnd.rn-realmedia".equalsIgnoreCase(mime)) {
      nombreFormato = "RM";
    } else if ("audio/aiff".equalsIgnoreCase(mime)) {
      nombreFormato = "AIF";
    } else if ("audio/basic".equalsIgnoreCase(mime)) {
      nombreFormato = "AU";
    } else if ("audio/midi".equalsIgnoreCase(mime) || "application/x-midi".equalsIgnoreCase(mime)) {
      nombreFormato = "MIDI";
    } else if ("audio/mpeg".equalsIgnoreCase(mime)) {
      nombreFormato = "MPG";
    } else if ("audio/mpeg3".equalsIgnoreCase(mime) || "audio/x-mpeg-3".equalsIgnoreCase(mime)) {
      nombreFormato = "MP3";

      // VIDEO
    } else if ("application/x-dvi".equalsIgnoreCase(mime)) {
      nombreFormato = "DVI";
    } else if ("application/x-troff-msvideo".equalsIgnoreCase(mime)
        || "audio/avi".equalsIgnoreCase(mime)) {
      nombreFormato = "AVI";
    } else if ("video/quicktime".equalsIgnoreCase(mime)) {
      nombreFormato = "MOV";

    } else if ("application/octect-stream".equalsIgnoreCase(mime)) {
      nombreFormato = "BIN";

    } else {
      throw new InsideConverterUtilsException(
          "No se que nombre de formato le corresponde al mime " + mime);
    }
    // TODO NOMBRE FORMATO GENÉRICO PARA MIMES NO CONOCIDOS.
    return nombreFormato;
  }

  /**
   * Devuelve el tipo de firma con que habría que firmar un documentos según el mime de éste.
   * 
   * @param mime
   * @return
   */
  public static String getTipoFirmaByMimeAFirmar(String mime) {
    String tipoFirma = null;
    if ("application/pdf".equalsIgnoreCase(mime)) {
      tipoFirma = "Adobe PDF";
    } else if ("text/xml".equalsIgnoreCase(mime)) {
      tipoFirma = "XAdES Enveloped";
    } else if (mime != null) {
      tipoFirma = "XAdES Detached";
    }
    return tipoFirma;
  }

  /**
   * Devuelve el mime que tendría un tipo de firma determinado.
   * 
   * @param tipoFirma
   * @return
   */
  public static String getMimeByTipoFirma(String tipoFirma) {
    String mime = null;
    if ("Adobe PDF".contentEquals(tipoFirma) || "TF06".contentEquals(tipoFirma)) {
      mime = "application/pdf";
    } else if (StringUtils.hasText(tipoFirma) && ("TF02".contentEquals(tipoFirma)
        || "TF03".contentEquals(tipoFirma) || tipoFirma.toUpperCase().contains("XADES"))) {
      mime = "text/xml";
    } else if (StringUtils.hasText(tipoFirma) && (("TF04".contentEquals(tipoFirma)
        || "TF05".contentEquals(tipoFirma) || tipoFirma.toUpperCase().contains("CADES")))) {
      mime = "application/octet-stream";
    }
    return mime;
  }

  public static String getMimeFirmaByTipoFirmaEni(TipoFirma tipoFirma) {
    String mime = null;
    switch (tipoFirma) {
      case TF_02:
        mime = "text/xml";
        break;
      case TF_03:
        mime = "text/xml";
        break;
      case TF_04:
        mime = "application/octet-stream";
        break;
      case TF_05:
        mime = "application/octet-stream";
        break;
      case TF_06:
        mime = "application/pdf";
        break;
      default:
        mime = null;
    }
    return mime;
  }

  /**
   * Obtiene el tipo mime en base al contenido de un fichero @param data @return @throws
   * InsideConverterUtilsException @throws
   */
  public static String getMimeType(byte[] data) throws InsideConverterUtilsException {
    String mime = InsideMimeUtils.getMimeType(data);
    if (mime == null) {
      throw new InsideConverterUtilsException("No se ha detectado ningún tipo mime");
    }
    return mime;
  }

  public static String getMimeTypeMagic(byte[] data) throws InsideConverterUtilsException {
    String mime = InsideMimeUtils.getMimeType(data);
    try {
      if (mime == null) {
        MagicMatch match = Magic.getMagicMatch(data);
        mime = match.getMimeType();
      }
    } catch (Exception e) {
      throw new InsideConverterUtilsException("No se ha detectado ningún tipo mime");
    }
    return mime;
  }

  /**
   * Obtiene el tipo mime en base al contenido de un fichero y el nombre de su fichero @param
   * filename @param data @return @throws
   */
  /*
   * public static String getMimeType(String filename,byte[] data) throws
   * InsideConverterUtilsException{ String mimeType = getFirstMimeTypeMimeUtil(new
   * ExtensionMimeDetector().getMimeTypes(filename)); return mimeType != null ? mimeType :
   * getMimeType(data); }
   */

  /**
   * Dada una colección de tipos mime, da el primero
   * 
   * @param mimeTypes
   * @return
   * @throws InsideConverterUtilsException
   */
  /*
   * private static String getFirstMimeTypeMimeUtil(Collection<?> mimeTypes) throws
   * InsideConverterUtilsException{ if(mimeTypes.size() >= 1){ return mimeTypes.iterator().next() +
   * ""; }else{ return null; //throw new
   * InsideConverterUtilsException("No se ha detectado ningún tipo mime"); } }
   */

  /**
   * Devuelve la cadena de una huella, pasada en bytes como parámetro.
   * 
   * @param digest
   * @return
   */
  public static String getStringDigest(byte[] digest) {
    StringBuffer hexString = new StringBuffer();
    for (int i = 0; i < digest.length; i++) {
      String hex = Integer.toHexString(0xFF & digest[i]);
      if (hex.length() == 1)
        hexString.append('0');

      hexString.append(hex);
    }
    return hexString.toString();
  }

  /**
   * Este método tiene que desaparecer cuando se haga la implementación de obtener lo firmado a
   * partir de una firma.
   * 
   * @return
   */
  /*
   * private static byte[] obtenerContenidoGenerico (String nombreFormato) {//throws
   * DataExtractorException { byte[] contenidoFichero = null; try {
   * 
   * if ("PDF".equalsIgnoreCase(nombreFormato)) { contenidoFichero = IOUtils.toByteArray(new
   * FileInputStream ("$config.path/ficherosTemporales/PEDEFE.pdf")); } else if
   * ("DOC".equalsIgnoreCase(nombreFormato)) { contenidoFichero = IOUtils.toByteArray(new
   * FileInputStream ("$config.path/ficherosTemporales/WORD.doc")); } else if
   * ("XML".equalsIgnoreCase(nombreFormato)) { contenidoFichero = IOUtils.toByteArray(new
   * FileInputStream ("$config.path/ficherosTemporales/XML.xml")); } } catch (Exception e) {
   * e.printStackTrace(); //throw new DataExtractorException (
   * "No se pueden obtener los bytes del documento firmado " + e.getMessage(), e); }
   * 
   * return contenidoFichero; }
   */

  /**
   * Decodifica un contenido en base64, si es necesario lo decodifica recursivamente (hasta 10
   * veces) hasta que el contenido ya no está en base64
   * 
   * @param base64Content
   * @return
   */
  protected static byte[] base64Decode(byte[] base64Content) {
    int maxDecodes = 10;
    int decodes = 0;
    while (Base64.isBase64(base64Content)) {
      Assert.isTrue(decodes <= maxDecodes, "Superado máximo número de decodes");
      base64Content = Base64.decode(base64Content);
      decodes++;
    }
    return base64Content;
  }

  /**
   * Convierte un array de bytes en un SignatureType
   * 
   * @param contenidoSignature
   * @return
   * @throws JAXBException
   */
  public static SignatureType arrayOfBytesToSignature(byte[] contenidoSignature)
      throws JAXBException {
    JAXBContext jc = JAXBContext.newInstance(SignatureType.class.getPackage().getName());

    Unmarshaller unmarshaller = jc.createUnmarshaller(); //
    JAXBElement<?> e =
        (JAXBElement<?>) unmarshaller.unmarshal(new ByteArrayInputStream(contenidoSignature));
    return (SignatureType) e.getValue();

    /*
     * JAXBContext jc = JAXBContext.newInstance(SignatureType.class); Unmarshaller unMarshaller =
     * jc.createUnmarshaller(); SignatureType jaxbElement = (SignatureType)
     * unMarshaller.unmarshal(new ByteArrayInputStream(contenidoSignature));
     * 
     * return jaxbElement;
     */
  }

  /**
   * Convierte un SignatureType en un array de bytes
   * 
   * @param signature
   * @return
   * @throws JAXBException
   */
  public static byte[] signatureToArrayOfBytes(SignatureType signature) throws JAXBException {
    es.mpt.dsic.inside.xml.w3c.ObjectFactory of = new es.mpt.dsic.inside.xml.w3c.ObjectFactory();
    JAXBElement<SignatureType> jaxbElement = of.createSignature(signature);
    JAXBContext jc = JAXBContext.newInstance(SignatureType.class);
    Marshaller marshaller = jc.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    marshaller.marshal(jaxbElement, outputStream);

    return outputStream.toByteArray();
  }

  public static String objectXMLToString(Object obj) {
    String s = null;
    if (obj instanceof String) {
      s = (String) obj;
    } else if (obj instanceof ElementNSImpl) {
      Element e = (Element) obj;
      try {
        javax.xml.transform.Transformer transformer =
            TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

        StreamResult result = new StreamResult(new StringWriter());
        DOMSource source = new DOMSource(e.getFirstChild());
        transformer.transform(source, result);

        s = result.getWriter().toString();
        s = checkIfNoSpecialCharacter(s, e);
      } catch (TransformerException ex) {
      }
    } else {
      s = obj.toString();
    }
    return s;
  }

  public static String checkIfNoSpecialCharacter(String s, Element e) {
    String resultado = s;
    if (s != null && s.contains("?&gt")) {
      if (e.getFirstChild() instanceof TextImpl) {
        TextImpl text = (TextImpl) e.getFirstChild();
        resultado = text.getData();
      }
    }
    return resultado;
  }

  public static String listaToString(List<String> lista) {
    StringBuffer sb = new StringBuffer("");
    if (lista != null) {
      for (String str : lista) {
        sb.append(str + " , ");
      }
    }
    return sb.toString();
  }

  /**
   * Devuelve la cadena correspondiente a un objeto Calendar según el estándar ISO8601
   * 
   * @param calendar
   * @return
   */
  public static String calendarToStringISO8601(Calendar calendar) {
    String retorno = "";
    if (calendar != null) {
      DateTime dt = new DateTime(calendar);
      DateTimeFormatter fmt = ISODateTimeFormat.dateTime();
      retorno = dt.toString(fmt);
    }
    return retorno;
  }

  public static boolean isBooleanValue(String value) {
    if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {
      return true;
    } else {
      return false;
    }

  }
}
