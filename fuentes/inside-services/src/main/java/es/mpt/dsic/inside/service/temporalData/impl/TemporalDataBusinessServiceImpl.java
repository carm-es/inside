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

package es.mpt.dsic.inside.service.temporalData.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import es.mpt.dsic.inside.service.exception.InSideServiceTemporalDataException;
import es.mpt.dsic.inside.service.temporalData.TemporalDataBusinessService;
import es.mpt.dsic.inside.service.util.InsideWSUtils;

@Service("temporalDataBusinessService")
@PropertySource("file:${config.path}/temporalData.properties")
public class TemporalDataBusinessServiceImpl implements TemporalDataBusinessService {

  protected static final Log logger = LogFactory.getLog(TemporalDataBusinessServiceImpl.class);

  @Value("${temporalData.path}")
  private String basePath;

  @Override
  public String escribir(byte[] data, String folder, String filename, boolean create)
      throws InSideServiceTemporalDataException {
    try {
      logger.debug("Inicio TemporalDataBusinessServiceImpl.escribir");

      // creamos el path
      StringBuilder path = new StringBuilder(basePath);
      path.append(folder);
      path.append(File.separator);
      path.append(filename);

      FileOutputStream output = null;
      try {
        File dir = new File(basePath + folder);
        File file = new File(path.toString());
        if (dir.exists()) {
          if (create && file.exists()) {
            file.delete();
          }
        } else {
          dir.mkdir();
        }

        if (create) {
          file.createNewFile();
          output = new FileOutputStream(file);
          output.write(data);
        } else {
          output = new FileOutputStream(path.toString(), true);
          output.write(data);
        }
      } finally {
        output.close();
      }

      logger.debug("Fin TemporalDataBusinessServiceImpl.escribir");
      return path.toString();
    } catch (IOException e) {
      logger.warn("Error al escribir fichero en temporal: " + folder + filename);
      throw new InSideServiceTemporalDataException(e.getMessage(), e);
    }
  }

  @Override
  public byte[] getFile(String folder, String file) throws InSideServiceTemporalDataException {
    try {
      logger.debug("Inicio TemporalDataBusinessServiceImpl.leer");

      StringBuilder path = new StringBuilder(basePath);
      path.append(folder);
      path.append(File.separator);
      path.append(file);

      return IOUtils.toByteArray(new FileInputStream(path.toString()));
    } catch (IOException e) {
      logger.warn("Error al obtener fichero temporal: " + folder + file);
      throw new InSideServiceTemporalDataException(e.getMessage(), e);
    }
  }

  @Override
  public byte[] getFilePath(String path) throws InSideServiceTemporalDataException {
    try {
      logger.debug("Inicio TemporalDataBusinessServiceImpl.getFilePath");

      return IOUtils.toByteArray(new FileInputStream(path));
    } catch (IOException e) {
      logger.warn("Error al obtener path temporal: " + path);
      throw new InSideServiceTemporalDataException(e.getMessage(), e);
    }
  }

  @Override
  public String getFileContent(String folder, String file)
      throws InSideServiceTemporalDataException {
    try {
      logger.debug("Inicio TemporalDataBusinessServiceImpl.leer");

      StringBuilder path = new StringBuilder(basePath);
      path.append(folder);
      path.append(File.separator);
      path.append(file);

      return InsideWSUtils.readFile(path.toString());
    } catch (Exception e) {
      logger.warn("Error al obtener contenido de fichero temporal:" + folder + file);
      throw new InSideServiceTemporalDataException(e.getMessage(), e);
    }
  }

  @Override
  public String getPath(String folder, String file) {
    StringBuilder path = new StringBuilder(basePath);
    path.append(folder);
    path.append(File.separator);
    path.append(file);

    return path.toString();
  }

  @Override
  public void cleanDirectory() {
    StringBuilder path = new StringBuilder(basePath);
    File folder = new File(path.toString());
    for (final File fileEntry : folder.listFiles()) {
      deleteDir(fileEntry);
      fileEntry.delete();
    }
  }

  private void deleteDir(File folder) {
    for (final File fileEntry : folder.listFiles()) {
      fileEntry.delete();
    }
  }

  @Override
  public long getFreeSpace() {
    File folder = new File(basePath);
    return folder.getUsableSpace();
  }

  @Override
  public long getTotalSpace() {
    File folder = new File(basePath);
    return folder.getTotalSpace();
  }

  @Override
  public void createDirectory(String folder, String filename) {
    // creamos el path
    StringBuilder path = new StringBuilder(basePath);
    path.append(folder);
    path.append(File.separator);
    path.append(filename);

    File dir = new File(basePath + folder);
    if (!dir.exists()) {
      dir.mkdir();
    }
  }

  @Override
  public void cleanDirectory(String name) {
    StringBuilder path = new StringBuilder(basePath);
    path.append(name);
    File folder = new File(path.toString());
    if (folder.exists()) {
      for (final File fileEntry : folder.listFiles()) {
        fileEntry.delete();
      }
      folder.delete();
    }
  }

  @Override
  public String createEmptyFile(String folder, String filename)
      throws InSideServiceTemporalDataException {
    try {
      // creamos el path
      StringBuilder path = new StringBuilder(basePath);
      path.append(folder);
      path.append(File.separator);
      path.append(filename);

      FileOutputStream output = null;
      try {
        File dir = new File(basePath + folder);
        File file = new File(path.toString());
        if (dir.exists()) {
          if (file.exists()) {
            file.delete();
          }
        } else {
          dir.mkdir();
        }

        file.createNewFile();
        output = new FileOutputStream(file);
      } finally {
        output.close();
      }

      logger.debug("Fin TemporalDataBusinessServiceImpl.escribir");
      return path.toString();
    } catch (IOException e) {
      logger.warn("Error al crear fichero temporal vacio:" + folder + filename);
      throw new InSideServiceTemporalDataException(e.getMessage(), e);
    }
  }

  @Override
  public void fillFile(String path, InputStream in) throws InSideServiceTemporalDataException {
    FileOutputStream output = null;
    try {
      File file = new File(path);
      if (!file.exists()) {
        throw new InSideServiceTemporalDataException(
            "No se puede rellenar el fichero temporal, no exite");
      } else {
        output = new FileOutputStream(file, true);

        int FILE_CHUNK_SIZE = 1024 * 4;
        byte[] chunk = new byte[FILE_CHUNK_SIZE];
        int n = 0;
        while ((n = in.read(chunk)) != -1) {
          output.write(chunk, 0, n);
        }
      }
    } catch (IOException e) {
      logger.warn("Error al rellenar fichero temporal:" + path);
      throw new InSideServiceTemporalDataException(e.getMessage(), e);
    } finally {
      try {
        if (output != null) {
          output.close();
        }
      } catch (IOException e) {
        logger.warn("Error al rellenar fichero temporal:" + path);
        throw new InSideServiceTemporalDataException(e.getMessage(), e);
      }
    }
  }

}
