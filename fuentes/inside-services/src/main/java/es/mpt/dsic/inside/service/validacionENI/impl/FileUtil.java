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

package es.mpt.dsic.inside.service.validacionENI.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipFile;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FileUtil {

  protected final static Log logger = LogFactory.getLog(FileUtil.class);

  private static String lastName;

  /**
   * Método para generar un nombre único de fichero En principio será el milisegundo actual más el
   * identificador de thread. Si ese nombre coincide con el último que se generó, entonces se le
   * añade un sufijo.
   * 
   * @return
   */
  private static synchronized String uniqueName() {
    String currentName = System.currentTimeMillis() + "" + Thread.currentThread().getId();
    if (lastName != null && lastName.contentEquals(currentName)) {
      lastName = lastName + "suffix";
    } else {
      lastName = currentName;
    }
    return lastName;

  }

  /**
   * Devuelve la ruta del directorio temporal.
   * 
   * @return
   */
  public static String getTmpDir() {
    return System.getProperty("java.io.tmpdir");
  }

  /**
   * Devuelve un nombre de fichero aleatorio en el directorio temporal, según el patrón:
   * currentTimeMillis () + id-thread-actual.
   * 
   * @return
   */
  public static String createFilePath() {
    return createFilePath(null);
  }

  /**
   * Devuelve un nombre de fichero aleatorio en el directorio temporal, según el patrón: prefix +
   * uniqueName().
   * 
   * @param prefix Si es nulo, no se mete en el nombre.
   * @return
   */
  public static String createFilePath(String prefix) {

    String s = getTmpDir();
    s += System.getProperty("file.separator");
    s += prefix != null ? prefix : "";
    s += uniqueName();

    /*
     * String s = getTmpDir() + System.getProperty("file.separator") + prefix != null ? prefix : ""
     * + System.currentTimeMillis() + Thread.currentThread().getId();
     */
    return s;
  }

  public static ZipFile createTempZipFile(byte[] bytesFile) throws IOException {
    FileOutputStream fout = null;
    try {
      String tmpDir = getTmpDir();
      String tmpFilePath =
          tmpDir + System.getProperty("file.separator") + "zip" + System.currentTimeMillis();
      logger.debug("Fichero temporal: " + tmpFilePath);
      fout = new FileOutputStream(tmpFilePath);
      fout.write(bytesFile);

      logger.debug("Fichero temporal: " + tmpFilePath + " creado ");
      ZipFile f = new ZipFile(tmpFilePath);
      return f;
    } finally {
      close(fout);
    }

  }

  /**
   * Crea un directorio en el directorio temporal y devuelve el objeto File
   * 
   * @param dirName Nombre del directorio
   * @return
   */
  public static File createTempDir(String dirName) {

    String tempPath = getTmpDir();
    logger.debug("tempPath " + tempPath);
    String sep = System.getProperty("file.separator");

    String dirSalida = tempPath + sep + dirName;
    logger.debug("dirSalida " + dirSalida);

    File dir = new File(dirSalida);
    if (!dir.exists() || !dir.isDirectory()) {
      dir.mkdir();
    }

    return dir;
  }

  /**
   * Borra un directorio y lo que hay dentro
   * 
   * @param dir
   */
  public static void deleteDirRecursively(File dir) {
    if (dir != null) {
      File[] files = dir.listFiles();
      if (files != null) {
        for (File f : files) {
          if (f.isDirectory()) {
            deleteDirRecursively(f);
          } else {
            try {
              f.delete();
            } catch (Exception e) {
              logger.warn("No se ha podido eliminar el fichero:" + f.getAbsolutePath());
            }
          }
        }
      }
      try {
        dir.delete();
      } catch (Exception e) {
        logger.warn("No se ha podido eliminar el fichero:" + dir.getAbsolutePath());
      }
    }
  }

  /**
   * Obtiene una lista de los ficheros con una extensión dentro de un directorio. Si el directorio
   * no se puede acceder, no existe o no contiene ficheros devuelve una lista vacía.
   * 
   * @param f Directorio donde se desea buscar
   * @param extension extensión de los ficheros.
   * @return
   */
  public static List<File> getFilesInFolder(File f, String extension) {
    List<File> files = new ArrayList<File>();

    if (f != null) {
      if (!f.isDirectory() || !f.canRead()) {
        return files;
      }

      File[] filesIn = f.listFiles();

      if (filesIn != null) {
        for (File file : filesIn) {
          if (file.getName().endsWith(extension)) {
            files.add(file);
          }
        }
      }
    }

    return files;
  }

  /**
   * Devuelve un nombre de fichero aleatorio en el directorio temporal, según el patrón: prefix +
   * uniqueName().
   * 
   * @param prefix Si es nulo, no se mete en el nombre.
   * @return
   * @throws IOException
   */
  public static String createFilePath(String prefix, byte[] contenido) throws IOException {
    FileOutputStream fos = null;
    String inputPathFile = null;
    try {
      inputPathFile = createFilePath(prefix);
      fos = new FileOutputStream(inputPathFile);
      fos.write(contenido);
    } finally {
      close(fos);
    }
    return inputPathFile;
  }

  /**
   * Devuelve un nombre de fichero aleatorio en el directorio temporal, según el patrón: prefix +
   * uniqueName().
   * 
   * @param prefix Si es nulo, no se mete en el nombre.
   * @return
   * @throws IOException
   */
  public static String createFilePath(String prefix, byte[] contenido, String suffix)
      throws IOException {
    FileOutputStream fos = null;
    String inputPathFile = null;
    try {
      inputPathFile = createFilePath(prefix) + suffix;
      fos = new FileOutputStream(inputPathFile);
      fos.write(contenido);
    } finally {
      close(fos);
    }
    return inputPathFile;
  }

  public static void close(FileInputStream input) throws IOException {
    if (input != null) {
      input.close();
    }
  }

  public static void close(FileOutputStream input) throws IOException {
    if (input != null) {
      input.close();
    }
  }

  public static void close(ByteArrayOutputStream input) throws IOException {
    if (input != null) {
      input.close();
    }
  }

  public static void close(ByteArrayInputStream input) throws IOException {
    if (input != null) {
      input.close();
    }
  }

  public static void deleteFilesStartWidth(String path, String prefix) {
    File tmpdir = new File(path);
    if (tmpdir.listFiles() != null) {
      for (File tmpfile : tmpdir.listFiles()) {
        if (tmpfile.getName().startsWith(prefix)) {
          try {
            tmpfile.delete();
          } catch (Exception e) {
            logger.warn("No se ha podido eliminar el fichero:" + tmpfile.getAbsolutePath());
          }
        }
      }
    }
  }
}
