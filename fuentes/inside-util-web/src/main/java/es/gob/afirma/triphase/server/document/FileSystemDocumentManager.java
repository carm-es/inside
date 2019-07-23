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

package es.gob.afirma.triphase.server.document;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.X509Certificate;
import java.util.Properties;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.ResourceUtils;
import es.gob.afirma.core.misc.AOUtil;
import es.gob.afirma.core.misc.Base64;
import es.gob.afirma.core.signers.AOSignConstants;

/**
 * Implementaci&oacute;n de acceso a gestor documental usando simplemente el sistema de ficheros.
 * 
 * @author Tom&aacute;s Garc&iacute;a-Mer&aacute;s
 */
public final class FileSystemDocumentManager implements DocumentManager {

  private static final String FORMAT_PROPERTY = "format"; //$NON-NLS-1$
  private static final String SESION_POR_PARAMETRO_EXTRA = "SESION_POR_PARAMETRO_EXTRA";

  final File inDir;
  final File outDir;
  final boolean overwrite;

  protected static final Log logger = LogFactory.getLog(FileSystemDocumentManager.class);

  /**
   * Construye la clase de acceso a gestor documental usando sistema de ficheros.
   * 
   * @param config Configuraci&oacute;n del gestor (directorios, etc.)
   * @throws FileNotFoundException
   */
  public FileSystemDocumentManager(final String indir, final String outDir, final String overWrite)
      throws FileNotFoundException {

    this.inDir = ResourceUtils.getFile(indir);
    this.outDir = ResourceUtils.getFile(outDir);
    this.overwrite = Boolean.parseBoolean(overWrite);

    logger.info("Directorio de entrada de ficheros: " + this.inDir); //$NON-NLS-1$
    logger.info("Directorio de salida de ficheros: " + this.outDir); //$NON-NLS-1$
  }

  @Override
  public byte[] getDocument(final String id, final X509Certificate[] certChain,
      final Properties prop, HttpSession session) throws IOException {

    logger.info("Recuperamos el documento con identificador: " + id); //$NON-NLS-1$

    // final File file = new File(this.inDir + System.getProperty("file.separator") +
    // session.getId(), new String(
    // Base64.decode(id)));

    final File file = new File(
        this.inDir + System.getProperty("file.separator")
            + prop.getProperty(FileSystemDocumentManager.SESION_POR_PARAMETRO_EXTRA),
        new String(Base64.decode(id)));


    if (!isParent(this.inDir, file)) {
      throw new IOException(
          "Se ha pedido un fichero fuera del directorio configurado: " + file.getAbsolutePath() //$NON-NLS-1$
      );
    }

    logger.info("Buscamos el fichero: " + file.getAbsolutePath()); //$NON-NLS-1$

    if (!file.exists()) {
      throw new IOException("No se puede cargar el documento, no existe"); //$NON-NLS-1$
    }

    if (!file.isFile()) {
      throw new IOException(
          "No se puede cargar el documento, el elmento existe, pero no es un fichero" //$NON-NLS-1$
      );
    }

    if (!file.canRead()) {
      throw new IOException(
          "No se puede cargar el documento, no se tienen permisos de lectura sobre el" //$NON-NLS-1$
      );
    }

    final byte[] data;
    InputStream fis = null;
    try {
      fis = new FileInputStream(file);
      data = AOUtil.getDataFromInputStream(fis);
      fis.close();
    } catch (final IOException e) {
      logger.warn("Error en la lectura del fichero '" + file.getAbsolutePath() + "': " + e); //$NON-NLS-1$ //$NON-NLS-2$
      if (fis != null) {
        try {
          fis.close();
        } catch (final IOException e2) {
          logger.warn("El fichero queda sin cerrar: " + file.getAbsolutePath()); //$NON-NLS-1$
        }
      }
      throw e;
    }

    return data;
  }

  @Override
  public String storeDocument(final String id, final X509Certificate[] certChain, final byte[] data,
      final Properties prop, HttpSession session) throws IOException {

    final String initialId = id != null ? new String(Base64.decode(id)) : "signature"; //$NON-NLS-1$
    String newId = initialId;
    final int lastDotPos = initialId.lastIndexOf('.');
    if (lastDotPos != -1) {
      newId = initialId.substring(0, lastDotPos);
    }

    final String format = prop.getProperty(FORMAT_PROPERTY);
    if (AOSignConstants.SIGN_FORMAT_CADES.equalsIgnoreCase(format)) {
      newId += ".csig"; //$NON-NLS-1$
    } else if (AOSignConstants.SIGN_FORMAT_XADES.equalsIgnoreCase(format)) {
      newId += ".xsig"; //$NON-NLS-1$
    } else if (lastDotPos < initialId.length() - 1) {
      newId += initialId.substring(lastDotPos);
    }

    // final File file = new File(this.outDir + System.getProperty("file.separator") +
    // session.getId(), newId);

    final File file = new File(this.outDir + System.getProperty("file.separator")
        + prop.getProperty(FileSystemDocumentManager.SESION_POR_PARAMETRO_EXTRA), newId);

    if (file.exists() && !this.overwrite) {
      throw new IOException(
          "Se ha obtenido un nombre de documento existente en el sistema de ficheros."); //$NON-NLS-1$
    }

    FileOutputStream fos = null;
    try {
      fos = new FileOutputStream(file);
      fos.write(data);
      fos.close();
    } catch (final IOException e) {
      logger.error(
          "Error al almacenar los datos en el fichero '" + file.getAbsolutePath() + "': " + e); //$NON-NLS-1$ //$NON-NLS-2$
      if (fos != null) {
        try {
          fos.close();
        } catch (final IOException e2) {
          logger.warn("El fichero queda sin cerrar: " + file.getAbsolutePath()); //$NON-NLS-1$
        }
      }
      throw e;
    }

    logger.info("Escribiendo el fichero: " + file.getAbsolutePath()); //$NON-NLS-1$
    return Base64.encode(newId.getBytes());
  }

  private static boolean isParent(final File p, final File file) {
    File f;
    final File parent;
    try {
      parent = p.getCanonicalFile();
      f = file.getCanonicalFile();
    } catch (final IOException e) {
      return false;
    }

    while (f != null) {
      if (parent.equals(f)) {
        return true;
      }
      f = f.getParentFile();
    }
    return false;
  }

}
