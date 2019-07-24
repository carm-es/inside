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

package es.mpt.dsic.integration.cmis.utils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import es.mpt.dsic.integration.cmis.CmisTestBase;
import es.mpt.dsic.integration.cmis.utils.FolderCmisUtils;

public class FolderCmisUtilsTest extends CmisTestBase {

  private static final Log logger = LogFactory.getLog(FolderCmisUtilsTest.class);

  private static final String DEFAULT_PATH[] = {"test1", "test2", "test"};
  private static final String DEFAULT_ID = "testFolder";


  @Test
  public void testDefaultPath() {
    logger.info("Iniciando el test del método FolderCmisUtils.defaultPath");
    final Calendar calendar = Calendar.getInstance();

    final String defaultPath[] = FolderCmisUtils.defaultPath();
    Assert.assertNotNull(defaultPath);
    Assert.assertEquals(defaultPath.length, 3);
    Assert.assertEquals(defaultPath[0], String.valueOf(calendar.get(Calendar.YEAR)));
    Assert.assertEquals(defaultPath[1], String.valueOf(calendar.get(Calendar.MONTH) + 1));
    Assert.assertEquals(defaultPath[2], String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));

    FolderCmisUtils.defaultPath();
    logger.info("Finalizando el test del método FolderCmisUtils.defaultPath");
  }

  @Test
  public void testCreateFolder() {
    logger.info("Iniciando el test del método FolderCmisUtils.createFolder");
    try {
      Folder folder = sessionManager.getRootFolder();
      for (final String path : DEFAULT_PATH) {
        folder = FolderCmisUtils.createFolder(folder, path);
        Assert.assertNotNull(folder);
      }
    } catch (final Throwable e) {
      logger.error(e.getMessage(), e);
      Assert.fail(e.getMessage());
    }
    logger.info("Finalizando el test del método FolderCmisUtils.createFolder");
  }

  @Test
  public void testCreateFolderExpediente() {
    logger.info("Iniciando el test del método FolderCmisUtils.createFolder");
    try {
      final Folder root = sessionManager.getRootFolder();
      final Map<String, Object> properties = new HashMap<String, Object>();
      properties.put(PropertyIds.OBJECT_TYPE_ID, "F:inside:test_carpeta");
      properties.put(PropertyIds.NAME, DEFAULT_ID);
      final Folder folder = FolderCmisUtils.createFolder(root, properties);
      Assert.assertNotNull(folder);
    } catch (final Throwable e) {
      logger.error(e.getMessage(), e);
      Assert.fail(e.getMessage());
    }
    logger.info("Finalizando el test del método FolderCmisUtils.createFolder");
  }

  @Test
  public void testGetFolderInPath() {
    logger.info("Iniciando el test del método FolderCmisUtils.getFolderInPath");
    try {
      final Folder root = sessionManager.getRootFolder();
      final Folder folder1 = FolderCmisUtils.getFolderByPath(root, DEFAULT_PATH[0]);
      Assert.assertNotNull(folder1);
      final Folder folder2 = FolderCmisUtils.getFolderByPath(folder1, DEFAULT_PATH[1]);
      Assert.assertNotNull(folder2);
      final Folder folder3 = FolderCmisUtils.getFolderByPath(folder2, DEFAULT_PATH[2]);
      Assert.assertNotNull(folder3);
    } catch (final Throwable e) {
      logger.error(e.getMessage(), e);
      Assert.fail(e.getMessage());
    }
    logger.info("Finalizando el test del método FolderCmisUtils.getFolderInPath");
  }

  @Test
  public void testDeleteFolder() {
    logger.info("Iniciando el test del método FolderCmisUtils.deleteFolder");
    try {
      final Folder root = sessionManager.getRootFolder();
      final Folder folder = FolderCmisUtils.getFolderByPath(root, DEFAULT_ID);
      FolderCmisUtils.deleteFolder(folder);
    } catch (final Throwable e) {
      logger.error(e.getMessage(), e);
      Assert.fail(e.getMessage());
    }
    logger.info("Finalizando el test del método FolderCmisUtils.deleteFolder");
  }

  @Test
  public void testDeleteTreeFolder() {
    logger.info("Iniciando el test del método FolderCmisUtils.deleteTreeFolder");
    try {
      final Folder root = sessionManager.getRootFolder();
      final Folder folder1 = FolderCmisUtils.getFolderByPath(root, DEFAULT_PATH[0]);
      FolderCmisUtils.deleteTreeFolder(folder1);
    } catch (final Throwable e) {
      logger.error(e.getMessage(), e);
      Assert.fail(e.getMessage());
    }
    logger.info("Finalizando el test del método FolderCmisUtils.deleteTreeFolder");
  }
}
