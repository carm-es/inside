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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import es.mpt.dsic.inside.service.TemporalDataBusinessService;
import es.mpt.dsic.inside.service.exception.ServiceException;
import es.mpt.dsic.inside.web.util.InsideWSUtils;

@Service("temporalDataBusinessService")
@PropertySource("file:${config.path}/temporalData.properties")
public class TemporalDataBusinessServiceImpl implements TemporalDataBusinessService {

  protected static final Log logger = LogFactory.getLog(TemporalDataBusinessServiceImpl.class);

  @Value("${temporalData.path}")
  private String basePath;

  @Override
  public String escribir(byte[] data, String folder, String filename, boolean create)
      throws ServiceException, IOException {
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
  }

  @Override
  public byte[] getFile(String folder, String file) throws FileNotFoundException, IOException {
    logger.debug("Inicio TemporalDataBusinessServiceImpl.leer");

    StringBuilder path = new StringBuilder(basePath);
    path.append(folder);
    path.append(File.separator);
    path.append(file);

    return IOUtils.toByteArray(new FileInputStream(path.toString()));

  }

  @Override
  public byte[] getFilePath(String path) throws FileNotFoundException, IOException {
    logger.debug("Inicio TemporalDataBusinessServiceImpl.getFilePath");

    return IOUtils.toByteArray(new FileInputStream(path));
  }

  @Override
  public String getFileContent(String folder, String file)
      throws FileNotFoundException, IOException {
    logger.debug("Inicio TemporalDataBusinessServiceImpl.leer");

    StringBuilder path = new StringBuilder(basePath);
    path.append(folder);
    path.append(File.separator);
    path.append(file);

    return InsideWSUtils.readFile(path.toString());
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

}
