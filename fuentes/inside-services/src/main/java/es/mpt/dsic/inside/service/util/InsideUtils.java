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

package es.mpt.dsic.inside.service.util;

import java.beans.PropertyDescriptor;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class InsideUtils {

  public static final String PERMISO_LECTURA = "lectura";
  public static final String PERMISO_ESCRITURA = "escritura";

  /**
   * Convierte un String delimitado por guiones bajos a su equivalente "camelizado" Ej: version_nti
   * => versionNti
   * 
   * @param underscored_string
   * @return
   */
  public static String camelize(String underscored_string) {
    return camelize(underscored_string, true);
  }

  /**
   * Convierte un String delimitado por guiones bajos a su equivalente "camelizado" Ej: version_nti
   * => versionNti
   * 
   * @param underscored_string
   * @param capitalizeFirstChar
   * @return
   */
  public static String camelize(String underscored_string, boolean capitalizeFirstChar) {
    String result = "";
    StringTokenizer st = new StringTokenizer(underscored_string, "_");
    while (st.hasMoreTokens()) {
      result += StringUtils.capitalize(st.nextToken());
    }
    return capitalizeFirstChar ? result
        : result.substring(0, 1).toLowerCase() + result.substring(1);
  }

  /**
   * Convierte un String "camelizado" a su equivalente delimitado por guiones bajos Ej versionNti =>
   * version_nti
   * 
   * @param camelized_string
   * @return
   */
  public static String underscore(String camelized_string) {

    List<Integer> upper = new ArrayList<Integer>();
    byte[] bytes = camelized_string.getBytes();
    for (int i = 0; i < bytes.length; i++) {
      byte b = bytes[i];
      if (b < 97 || b > 122) {
        upper.add(i);
      }
    }

    StringBuffer b = new StringBuffer(camelized_string);
    for (int i = upper.size() - 1; i >= 0; i--) {
      Integer index = upper.get(i);
      if (index != 0)
        b.insert(index, "_");
    }

    return b.toString().toLowerCase();
  }

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
   */
  public static String getNombreFormatoByMime(String mime) {
    String nombreFormato = null;

    // DOCUMENTOS MÁS COMUNES
    if ("application/pdf".equalsIgnoreCase(mime)) {
      nombreFormato = "PDF";
    } else if ("application/msword".equalsIgnoreCase(mime)) {
      nombreFormato = "DOC";
    } else if ("text/xml".equalsIgnoreCase(mime) || "application/xml".equalsIgnoreCase(mime)) {
      nombreFormato = "XML";
    } else if ("application/vnd.openxmlformats-officedocument.wordprocessingml.document"
        .equalsIgnoreCase(mime)) {
      nombreFormato = "DOCX";
    } else if ("application/vnd.oasis.opendocument.text".equalsIgnoreCase(mime)) {
      nombreFormato = "ODT";
    } else if ("application/vnd.oasis.opendocument.spreadsheet".equalsIgnoreCase(mime)) {
      nombreFormato = "ODS";
    } else if ("application/vnd.oasis.opendocument.graphics".equalsIgnoreCase(mime)) {
      nombreFormato = "ODG";
    } else if ("text/rtf".equalsIgnoreCase(mime)) {
      nombreFormato = "RTF";
    } else if ("text/plain".equalsIgnoreCase(mime)
        || "application/octet-stream".equalsIgnoreCase(mime)) {
      nombreFormato = "TXT";
    } else if ("text/html".equalsIgnoreCase(mime)) {
      nombreFormato = "HTML";

    } else if ("application/vnd.oasis.opendocument.presentation".equalsIgnoreCase(mime)) {
      nombreFormato = "ODP";
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

      // DOCUMENTOS DE OFFICE
    } else if ("application/mspowerpoint".equalsIgnoreCase(mime)) {
      nombreFormato = "PPT";
    } else if ("application/msexcel".equalsIgnoreCase(mime)) {
      nombreFormato = "XLS";
    } else if ("application/x-msexcel".equalsIgnoreCase(mime)) {
      nombreFormato = "XLS";
    } else if ("application/vnd.ms-excel".equalsIgnoreCase(mime)) {
      nombreFormato = "XLS";
    } else if ("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
        .equalsIgnoreCase(mime)) {
      nombreFormato = "XLSX";
    } else if ("application/x-rtf".equalsIgnoreCase(mime)) {
      nombreFormato = "RTF";

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

    }
    // TODO NOMBRE FORMATO GENÉRICO PARA MIMES NO CONOCIDOS.
    return nombreFormato;
  }

  /**
   * Devuelve el tipo de firma con que habría que firmar un documentos según el mime de éste.
   * Ficheros .pdf firma PADES Ficheros .xml firma XADES Enveloped Resto ficheros firma CADES
   * Attached.
   * 
   * @param mime
   * @return
   */
  public static String getTipoFirmaByMimeAFirmar(String mime) {
    Assert.notNull(mime);

    String tipoFirma = "CAdES";

    if ("application/pdf".equalsIgnoreCase(mime)) {
      tipoFirma = "Adobe PDF";
    } else if ("text/xml".equalsIgnoreCase(mime) || ("application/xml".equals(mime))) {
      tipoFirma = "XAdES Enveloped";
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
   * Devuelve una cadena formada por la concatenación de los elementos de la lista, pasados a
   * String. Cada elemento estará separado del siguiente por la cadena que se indique en el
   * parámetro "sep". El parámetro sepFinal indica si se quiere meter también la cadena de
   * separación al final. El parámetro notNull tomará valor true cuando se quiera que si la lista es
   * vacía se devuelva "" en lugar de null
   * 
   * @param <T>
   * @param lista
   * @param cadenaSep
   * @param sepFinal
   * @param notNull
   * @return
   */
  public static <T> String listaToString(T[] lista, String cadenaSep, boolean sepFinal,
      boolean notNull) {

    String concat = null;
    if (lista != null && lista.length > 0) {

      concat = lista[0].toString();

      for (int i = 1; i < lista.length; i++) {
        concat += cadenaSep + lista[i].toString();
      }

      if (sepFinal) {
        concat += cadenaSep;
      }

    }
    if (concat == null && notNull) {
      concat = "";
    }
    return concat;

  }

  /**
   * Devuelve la cadena correspondiente a un objeto Calendar según el estándar ISO8601
   * 
   * @param calendar
   * @return
   */
  public static String calendarToStringISO8601(Calendar calendar) {

    DateTime dt = new DateTime(calendar);
    DateTimeFormatter fmt = ISODateTimeFormat.dateTime();
    return dt.toString(fmt);

  }

  /**
   * Devuelve la cadena correspondiente a un objeto Calendar según el estándar ISO8601
   * 
   * @param calendar
   * @return
   */
  public static String dateToStringISO8601(Date date) {
    String retorno = "";
    if (date != null) {
      DateTime dt = new DateTime(date.getTime());
      DateTimeFormatter fmt = ISODateTimeFormat.dateTime();
      retorno = dt.toString(fmt);
    }
    return retorno;
  }

  /**
   * Se divide la lista en trozos (paginas), de un número de elementos concreto
   * (elementosPorPagina). Se devuelve el "trozo" correspondiente al número de página indicado en el
   * parámetro "pagina".
   * 
   * @param <T> Tipo de los objetos de la lista
   * @param lista Lista de la que se quiere obtener una sublista.
   * @param elementosPorPagina Número de elementos por página
   * @param pagina Número de página que se quiere obtener.
   * @return Si la lista es vacía, se devuelve la lista vacía. Si la página pedida es mayor que el
   *         número real de página se devuelve la lista vacía.
   */
  public static <T> List<T> getSublista(List<T> lista, int elementosPorPagina, int pagina) {
    // numero de páginas que habrá en total
    int numeroPaginas;

    // si el resto de la division no es 0, habra una pagina mas que el
    // resultado devuelto por la division entera.
    int mod = lista.size() % elementosPorPagina;
    if (mod == 0) {
      numeroPaginas = lista.size() / elementosPorPagina;
    } else {
      numeroPaginas = lista.size() / elementosPorPagina + 1;
    }

    // Si la página pedida es mayor que el número de páginas devolvemos una
    // lista vacía.
    if (pagina > numeroPaginas) {
      return lista.subList(0, 0);
    }

    // Indice inicial en el array que contiene todos los resultados.
    int indiceInicial = (pagina - 1) * elementosPorPagina;

    // Indice final en el array que contiene todos los resultados.
    int indiceFinal;

    // Si es la última pagina, o el número de páginas es 0 (porque la lista
    // está vacía)
    if (pagina == numeroPaginas || numeroPaginas == 0) {
      indiceFinal = lista.size();
    } else {
      indiceFinal = indiceInicial + elementosPorPagina;
    }

    return lista.subList(indiceInicial, indiceFinal);
  }

  /**
   * Comprueba si en la clase de un objeto existe el atributo que indique el parámetro "fieldName".
   * Si no lo encuentra lo cameliza. Si no lo encuentra compara sin tener en cuenta mayúsculas y
   * minúsculas.
   * 
   * @param objeto
   * @param fieldName
   * @return Devuelve el propertyDescriptor asociado al objeto y el atributo. Null si ninguno
   *         coincide con el nombre.
   */
  public static PropertyDescriptor findPropertyDescriptor(Object objeto, String fieldName) {
    // probamos primero a encontrar la propiedad tal como viene en el
    // parámetro
    PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(objeto.getClass(), fieldName);
    String fieldNameCam = camelize(fieldName);
    // Si no se llama así y como esto es Java :) , puede que se haya escrito
    // de forma "camelizada"
    if (pd == null) {
      pd = BeanUtils.getPropertyDescriptor(objeto.getClass(), fieldNameCam);
    }
    // Si tampoco se encuentra, se busca sin tener en cuenta mayúsculas y
    // minúsculas
    if (pd == null) {
      PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(objeto.getClass());
      int i = 0;
      boolean found = false;
      while (i < pds.length && !found) {
        PropertyDescriptor pdesc = pds[i];
        if (pdesc.getName().equalsIgnoreCase(fieldName)
            || pdesc.getName().equalsIgnoreCase(fieldNameCam)) {
          pd = pdesc;
          found = true;
        }
        i++;
      }
    }

    return pd;
  }

  @SuppressWarnings("rawtypes")
  public static byte[] generateZip(Map<String, byte[]> ficheros, String extension)
      throws IOException {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    ZipOutputStream zos = new ZipOutputStream(outputStream);
    Iterator it = ficheros.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry e = (Map.Entry) it.next();
      ZipEntry zipEntry;
      if (org.apache.commons.lang.StringUtils.isNotBlank(extension)) {
        zipEntry = new ZipEntry((String) e.getKey() + extension);
      } else {
        zipEntry = new ZipEntry((String) e.getKey());
      }
      zos.putNextEntry(zipEntry);
      zos.write((byte[]) e.getValue());
      zos.closeEntry();
    }
    zos.close();
    return outputStream.toByteArray();
  }


  public static String remplazaCaracteres(String cadena) {
    // Cadena de caracteres original a sustituir.
    String original = "áàäéèëíìïóòöúùÁÀÄÉÈËÍÌÏÓÒÖÚÙÜ";
    // Cadena de caracteres ASCII que reemplazarán los originales.
    String ascii = "aaaeeeiiiooouuAAAEEEIIIOOOUUU";
    String salida = cadena;
    for (int i = 0; i < original.length(); i++) {
      // Reemplazamos los caracteres especiales.
      salida = salida.replace(original.charAt(i), ascii.charAt(i));
    } // for i
    return salida;
  }
}
