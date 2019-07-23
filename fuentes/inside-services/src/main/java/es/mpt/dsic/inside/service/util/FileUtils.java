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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

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

    if (!f.isDirectory() || !f.canRead() || f.listFiles().length == 0) {
      return files;
    }

    File[] filesIn = f.listFiles();

    for (File file : filesIn) {
      if (file.getName().endsWith(extension)) {
        files.add(file);
      }
    }

    return files;
  }

}
