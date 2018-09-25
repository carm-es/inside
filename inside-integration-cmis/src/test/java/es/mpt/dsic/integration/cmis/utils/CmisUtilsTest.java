/* Copyright (C) 2016 MINHAP, Gobierno de España
   This program is licensed and may be used, modified and redistributed under the terms
   of the European Public License (EUPL), either version 1.1 or (at your
   option) any later version as soon as they are approved by the European Commission.
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
   or implied. See the License for the specific language governing permissions and
   more details.
   You should have received a copy of the EUPL1.1 license
   along with this program; if not, you may find it at
   http://joinup.ec.europa.eu/software/page/eupl/licence-eupl */

package es.mpt.dsic.integration.cmis.utils;

import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.QueryResult;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import es.mpt.dsic.integration.cmis.CmisTestBase;
import es.mpt.dsic.integration.cmis.exception.RepositoryCmisException;
import es.mpt.dsic.integration.cmis.utils.CmisUtils;

public class CmisUtilsTest extends CmisTestBase {
	
	private static final Log logger = LogFactory.getLog(CmisTestBase.class);

	@Test
	public void testExecuteQuery() throws RepositoryCmisException {
		logger.info("Iniciando el test del método executeQuery");

		final String query = "select * from inside:test_carpeta";
		final Session session = sessionManager.getSession();
		final ItemIterable<QueryResult> results = CmisUtils.executeQuery(session, query);
		Assert.assertNotNull(results);
		Assert.assertTrue(results.getTotalNumItems() > 0);

		logger.info("Finalizando el test del método executeQuery");
	}

	@Test
	public void testGetMetadatos() throws RepositoryCmisException {
		logger.info("Iniciando el test del método getMetadatos");

		final String query = "select * from inside:test_carpeta";
		final Session session = sessionManager.getSession();
		final ItemIterable<QueryResult> results = CmisUtils.executeQuery(session, query);
		Assert.assertNotNull(results);
		for (final QueryResult result : results) {
			final String path = result.getPropertyById("cmis:path").getFirstValue().toString();
			final Folder folder = (Folder) session.getObjectByPath(path);

			final Map<String, Object> metadatos = CmisUtils.getMetadatos(folder);
			Assert.assertNotNull(metadatos);
			Assert.assertTrue(metadatos.size() > 0);
			Assert.assertTrue(metadatos.get("cmis:path") != null);
			break;
		}

		logger.info("Finalizando el test del método getMetadatos");
	}

}
