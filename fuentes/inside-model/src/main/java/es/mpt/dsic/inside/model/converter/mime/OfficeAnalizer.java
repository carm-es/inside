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



/*
 * Copyright (C) 2011 [Gobierno de Espana] This file is part of "Cliente @Firma". "Cliente @Firma"
 * is free software; you can redistribute it and/or modify it under the terms of: - the GNU General
 * Public License as published by the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version. - or The European Software License; either version 1.1 or (at
 * your option) any later version. Date: 11/01/11 You may contact the copyright holder at:
 * soporte.afirma5@mpt.es
 */

package es.mpt.dsic.inside.model.converter.mime;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



/** Clase para el an&aacute;lisis de ficheros OOXML, ODF y Microsoft Office 97/2003. */
public final class OfficeAnalizer {

  protected final static Log logger = LogFactory.getLog(OfficeAnalizer.class);

  private OfficeAnalizer() {
    // No permitimos la instanciacion
  }

  private static final String ZIP_MIMETYPE = "application/zip"; //$NON-NLS-1$

  // private static final Logger LOGGER = Logger.getLogger("es.gob.afirma"); //$NON-NLS-1$

  /** MimeTypes reconocidos del formato OOXML. */
  private static final Set<String> OOXML_MIMETYPES = new HashSet<String>(17);

  /** MimeTypes reconocidos del formato ODF. */
  private static final Set<String> ODF_MIMETYPES = new HashSet<String>(15);

  /** Extensiones de fichero asignadas a cada uno de los mimetypes. */
  private static final Map<String, String> FILE_EXTENSIONS = new HashMap<String, String>();

  static {
    // MimeTypes reconocidos del formato OOXML
    OOXML_MIMETYPES.add("application/vnd.ms-word.document.macroEnabled.12"); //$NON-NLS-1$
    OOXML_MIMETYPES.add("application/vnd.openxmlformats-officedocument.wordprocessingml.document"); //$NON-NLS-1$
    OOXML_MIMETYPES.add("application/vnd.ms-word.template.macroEnabled.12"); //$NON-NLS-1$
    OOXML_MIMETYPES.add("application/vnd.openxmlformats-officedocument.wordprocessingml.template"); //$NON-NLS-1$
    OOXML_MIMETYPES.add("application/vnd.ms-powerpoint.template.macroEnabled.12"); //$NON-NLS-1$
    OOXML_MIMETYPES.add("application/vnd.openxmlformats-officedocument.presentationml.template"); //$NON-NLS-1$
    OOXML_MIMETYPES.add("application/vnd.ms-powerpoint.addin.macroEnabled.12"); //$NON-NLS-1$
    OOXML_MIMETYPES.add("application/vnd.ms-powerpoint.slideshow.macroEnabled.12"); //$NON-NLS-1$
    OOXML_MIMETYPES.add("application/vnd.openxmlformats-officedocument.presentationml.slideshow"); //$NON-NLS-1$
    OOXML_MIMETYPES.add("application/vnd.ms-powerpoint.presentation.macroEnabled.12"); //$NON-NLS-1$
    OOXML_MIMETYPES
        .add("application/vnd.openxmlformats-officedocument.presentationml.presentation"); //$NON-NLS-1$
    OOXML_MIMETYPES.add("application/vnd.ms-excel.addin.macroEnabled.12"); //$NON-NLS-1$
    OOXML_MIMETYPES.add("application/vnd.ms-excel.sheet.binary.macroEnabled.12"); //$NON-NLS-1$
    OOXML_MIMETYPES.add("application/vnd.ms-excel.sheet.macroEnabled.12"); //$NON-NLS-1$
    OOXML_MIMETYPES.add("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); //$NON-NLS-1$
    OOXML_MIMETYPES.add("application/vnd.ms-excel.template.macroEnabled.12"); //$NON-NLS-1$
    OOXML_MIMETYPES.add("application/vnd.openxmlformats-officedocument.spreadsheetml.template"); //$NON-NLS-1$

    // MimeTypes reconocidos del formato ODF
    ODF_MIMETYPES.add("application/vnd.oasis.opendocument.text"); //$NON-NLS-1$
    ODF_MIMETYPES.add("application/vnd.oasis.opendocument.text-template"); //$NON-NLS-1$
    ODF_MIMETYPES.add("application/vnd.oasis.opendocument.text-web"); //$NON-NLS-1$
    ODF_MIMETYPES.add("application/vnd.oasis.opendocument.text-master"); //$NON-NLS-1$
    ODF_MIMETYPES.add("application/vnd.oasis.opendocument.graphics"); //$NON-NLS-1$
    ODF_MIMETYPES.add("application/vnd.oasis.opendocument.graphics-template"); //$NON-NLS-1$
    ODF_MIMETYPES.add("application/vnd.oasis.opendocument.presentation"); //$NON-NLS-1$
    ODF_MIMETYPES.add("application/vnd.oasis.opendocument.presentation-template"); //$NON-NLS-1$
    ODF_MIMETYPES.add("application/vnd.oasis.opendocument.spreadsheet"); //$NON-NLS-1$
    ODF_MIMETYPES.add("application/vnd.oasis.opendocument.spreadsheet-template"); //$NON-NLS-1$
    ODF_MIMETYPES.add("application/vnd.oasis.opendocument.chart"); //$NON-NLS-1$
    ODF_MIMETYPES.add("application/vnd.oasis.opendocument.formula"); //$NON-NLS-1$
    ODF_MIMETYPES.add("application/vnd.oasis.opendocument.database"); //$NON-NLS-1$
    ODF_MIMETYPES.add("application/vnd.oasis.opendocument.image"); //$NON-NLS-1$
    ODF_MIMETYPES.add("application/vnd.openofficeorg.extension"); //$NON-NLS-1$

    // Extensiones de fichero
    FILE_EXTENSIONS.put("application/zip", "zip"); //$NON-NLS-1$ //$NON-NLS-2$

    FILE_EXTENSIONS.put("application/vnd.openxmlformats-officedocument.wordprocessingml.document", //$NON-NLS-1$
        "docx"); //$NON-NLS-1$
    FILE_EXTENSIONS.put("application/vnd.openxmlformats-officedocument.presentationml.presentation", //$NON-NLS-1$
        "pptx"); //$NON-NLS-1$
    FILE_EXTENSIONS.put("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", //$NON-NLS-1$
        "xlsx"); //$NON-NLS-1$

    FILE_EXTENSIONS.put("application/vnd.oasis.opendocument.text", "odt"); //$NON-NLS-1$ //$NON-NLS-2$
    FILE_EXTENSIONS.put("application/vnd.oasis.opendocument.presentation", "odp"); //$NON-NLS-1$ //$NON-NLS-2$
    FILE_EXTENSIONS.put("application/vnd.oasis.opendocument.spreadsheet", "ods"); //$NON-NLS-1$ //$NON-NLS-2$
    FILE_EXTENSIONS.put("application/vnd.oasis.opendocument.graphics", "odg"); //$NON-NLS-1$ //$NON-NLS-2$
    FILE_EXTENSIONS.put("application/vnd.oasis.opendocument.chart", "odc"); //$NON-NLS-1$ //$NON-NLS-2$
    FILE_EXTENSIONS.put("application/vnd.oasis.opendocument.formula", "odf"); //$NON-NLS-1$ //$NON-NLS-2$
    FILE_EXTENSIONS.put("application/vnd.oasis.opendocument.database", "odb"); //$NON-NLS-1$ //$NON-NLS-2$
    FILE_EXTENSIONS.put("application/vnd.oasis.opendocument.image", "odi"); //$NON-NLS-1$ //$NON-NLS-2$
    FILE_EXTENSIONS.put("application/vnd.oasis.opendocument.text-master", "odm"); //$NON-NLS-1$ //$NON-NLS-2$
  }

  /**
   * Devuelve el MimeType correspondiente al documento ofim&aacute;tico proporcionado (ODF u OOXML).
   * Si el fichero no se corresponde con ninguno de ellos pero es un Zip se devolver&aacute; el
   * MimeType del Zip (application/zip) y si no es Zip se devolver&aacute; {@code null}.
   * 
   * @param data Fichero ODF, OOXML o Microsoft Office 97/2003
   * @return MimeType.
   */
  static String getMimeType(final byte[] data) {

    final String testString = new String(data);

    if (testString.contains("Microsoft Excel")) { //$NON-NLS-1$
      return "application/vnd.ms-excel"; //$NON-NLS-1$
    }
    if (testString.contains("Microsoft Office Word")) { //$NON-NLS-1$
      return "application/msword"; //$NON-NLS-1$
    }
    if (testString.contains("Microsoft Office PowerPoint")) { //$NON-NLS-1$
      return "application/vnd.ms-powerpoint"; //$NON-NLS-1$
    }
    if (testString.contains("Microsoft Project")) { //$NON-NLS-1$
      return "application/vnd.ms-project"; //$NON-NLS-1$
    }
    if (testString.contains("Microsoft Visio")) { //$NON-NLS-1$
      return "application/vnd.visio"; //$NON-NLS-1$
    }

    final ZipFile zipFile;

    // final ZipInputStream zIs;


    try {
      zipFile = createTempZipFile(data);
      // zipFile = AOFileUtils.createTempZipFile(data);
      // zIs = new ZipInputStream(new ByteArrayInputStream(data));
    } catch (final ZipException e1) {
      logger.debug("El fichero indicado no es un ZIP"); //$NON-NLS-1$
      return null;
    } catch (final IOException e1) {
      logger.error("No se pudo leer el fichero ", e1); //$NON-NLS-1$
      return null;
    } catch (final Exception e) {
      logger.debug("Error leyendo el fichero, se considerará que es un ZIP", e);
      return ZIP_MIMETYPE;
    }

    String mimetype = ZIP_MIMETYPE;
    // final List<ZipEntry> listEntrys = getZipEntryList(zIs);
    try {
      String tempMimetype = null;
      if (isODFFile(zipFile)) {
        tempMimetype = getODFMimeType(zipFile.getInputStream(zipFile.getEntry("mimetype"))); //$NON-NLS-1$
      } else if (isOOXMLFile(zipFile)) {
        tempMimetype =
            getOOXMLMimeType(zipFile.getInputStream(zipFile.getEntry("[Content_Types].xml"))); //$NON-NLS-1$
      }
      if (tempMimetype != null) {
        mimetype = tempMimetype;
      }

    } catch (final Exception e) {
      // Se ignora, es porque no es ni ODF ni OOXML
    }

    return mimetype;
  }

  /**
   * Devuelve la extensi&oacute;n correspondiente al documento ofim&aacute;tico proporcionado (ODF u
   * OOXML). Si el fichero no se corresponde con ninguno de ellos pero es un Zip se devolver&aacute;
   * la extensi&oacute;n "zip" y si no es Zip se devolver&aacute; {@code null}.
   * 
   * @param zipData Fichero ODF u OOXML
   * @return Extensi&oacute;n.
   */
  static String getExtension(final byte[] zipData) {
    final String mimetype = getMimeType(zipData);
    if (mimetype == null) {
      return null;
    }

    return FILE_EXTENSIONS.get(mimetype);
  }

  /**
   * Indica si un fichero tiene la estructura de un documento OOXML.
   * 
   * @param document Fichero a analizar
   * @return Devuelve <code>true</code> si el fichero era un OOXML, <code>false</code> en caso
   *         contrario.
   */
  public static boolean isOOXMLDocument(final byte[] document) {
    final ZipFile zipFile;
    try {
      zipFile = createTempZipFile(document);
    } catch (final Exception e1) {
      logger.debug("No se pudo leer el fichero, se considerara que no es un documento OOXML"); //$NON-NLS-1$
      return false;
    }
    return isOOXMLFile(zipFile);
  }

  /**
   * Indica si un fichero Zip tiene la estructura de un documento OOXML soportado.
   * 
   * @param zipFile Fichero zip que deseamos comprobar.
   * @return Devuelve <code>true</code> si el fichero era un OOXML soportado, <code>false</code> en
   *         caso contrario.
   */
  private static boolean isOOXMLFile(final ZipFile zipFile) {
    // Comprobamos si estan todos los ficheros principales del documento
    return zipFile.getEntry("[Content_Types].xml") != null //$NON-NLS-1$
        && zipFile.getEntry("_rels/.rels") != null //$NON-NLS-1$
        && zipFile.getEntry("docProps/app.xml") != null //$NON-NLS-1$
        && zipFile.getEntry("docProps/core.xml") != null; //$NON-NLS-1$
  }

  /**
   * Recupera el MimeType del XML "[Content_Type].xml" de un OOXML. Si el documento no es correcto o
   * no se reconoce el Mimetype se devuelve null.
   * 
   * @param contentTypeIs XML "[Content_Type].xml".
   * @return Devuelve el MimeType del OOXML o, si no es un OOXML reconocido, devuelve {@code null}.
   */
  public static String getOOXMLMimeType(final InputStream contentTypeIs) {

    final Document doc;
    try {
      doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(contentTypeIs);
    } catch (final Exception e) {
      return null;
    }

    // Obtenemos la raiz
    final Element root = doc.getDocumentElement();
    if (!root.getNodeName().equalsIgnoreCase("Types")) { //$NON-NLS-1$
      return null;
    }

    Node node = null;
    final NodeList nodes = root.getChildNodes();
    for (int i = 0; i < nodes.getLength(); i++) {
      node = nodes.item(i);
      if (node.getNodeName().equalsIgnoreCase("Override")) { //$NON-NLS-1$
        final NamedNodeMap nodeAttributes = node.getAttributes();
        Node nodeAttribute = null;
        for (int j = 0; j < nodeAttributes.getLength(); j++) {
          if (nodeAttributes.item(j).getNodeName().equalsIgnoreCase("ContentType")) { //$NON-NLS-1$
            nodeAttribute = nodeAttributes.item(j);
            break;
          }
        }

        if (nodeAttribute != null) {
          String value = nodeAttribute.getNodeValue();
          if (value.indexOf('.') != -1) {
            value = value.substring(0, value.lastIndexOf('.'));
          }
          if (OOXML_MIMETYPES.contains(value)) {
            return value;
          }
        }
      }
    }
    return null;
  }

  /**
   * Indica si un fichero tiene la estructura de un documento ODF.
   * 
   * @param document Fichero a analizar
   * @return Devuelve <code>true</code> si el fichero era un ODF, <code>false</code> en caso
   *         contrario.
   */
  public static boolean isODFDocument(final byte[] document) {
    final ZipFile zipFile;
    try {
      zipFile = createTempZipFile(document);
    } catch (final Exception e1) {
      logger.debug("No se pudo leer el fichero, se considerara que no es un documento ODF"); //$NON-NLS-1$
      return false;
    }
    return isODFFile(zipFile);
  }

  /**
   * Indica si un fichero Zip tiene la estructura de un documento ODF soportado.
   * 
   * @param zipFile Fichero zip que deseamos comprobar.
   * @return Devuelve <code>true</code> si el fichero era un ODF soportado, <code>false</code> en
   *         caso contrario.
   */
  private static boolean isODFFile(final ZipFile zipFile) {

    // Comprobamos si estan todos los ficheros principales del documento
    return zipFile.getEntry("mimetype") != null && zipFile.getEntry("content.xml") != null //$NON-NLS-1$ //$NON-NLS-2$
        && zipFile.getEntry("meta.xml") != null //$NON-NLS-1$
        && zipFile.getEntry("settings.xml") != null //$NON-NLS-1$
        && zipFile.getEntry("styles.xml") != null //$NON-NLS-1$
        && zipFile.getEntry("META-INF/manifest.xml") != null; //$NON-NLS-1$
  }



  /**
   * Recupera la extensi&oacute;n apropiada para un documento ODF. Si el fichero no era un documento
   * ODF soportado, se devolver&aacute; <code>null</code>.
   * 
   * @param contentTypeIs Fichero del que deseamos obtener la extensi&oacute;n.
   * @return Extensi&oacute;n del documento.
   */
  private static String getODFMimeType(final InputStream contentTypeIs) {
    final String contentTypeData;
    try {
      contentTypeData = new String(IOUtils.toByteArray(contentTypeIs));
    } catch (final Exception e) {
      return null;
    }
    if (ODF_MIMETYPES.contains(contentTypeData)) {
      return contentTypeData;
    }
    return null;
  }


  /**
   * Crea un fichero zip temporal con los bytes pasados como parámetro
   * 
   * @param bytesFile
   * @return
   * @throws IOException
   */
  private static ZipFile createTempZipFile(byte[] bytesFile) throws IOException {

    String tmpDir = System.getProperty("java.io.tmpdir");
    String tmpFilePath =
        tmpDir + System.getProperty("file.separator") + "zip" + System.currentTimeMillis();
    logger.debug("Fichero temporal: " + tmpFilePath);
    FileOutputStream fout = new FileOutputStream(tmpFilePath);
    fout.write(bytesFile);
    fout.close();
    logger.debug("Fichero temporal: " + tmpFilePath + " creado ");
    ZipFile f = new ZipFile(tmpFilePath);
    return f;

  }


}

