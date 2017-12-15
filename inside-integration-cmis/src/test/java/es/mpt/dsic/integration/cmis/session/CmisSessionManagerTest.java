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

package es.mpt.dsic.integration.cmis.session;

import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import es.mpt.dsic.integration.cmis.CmisTestBase;
import es.mpt.dsic.integration.cmis.exception.RepositoryCmisException;


public class CmisSessionManagerTest extends CmisTestBase {

	private static final Log logger = LogFactory.getLog(CmisSessionManagerTest.class);
			
	@Test
	public void testGestSession() throws RepositoryCmisException {
		logger.info("Iniciando el test del método getSession");

		final Session session = sessionManager.getSession();
		Assert.assertNotNull(session);

		logger.info("Finalizando el test del método getSession");
	}

	@Test
	public void testGetRootFolder() throws RepositoryCmisException {
		logger.info("Iniciando el test del método getRootFolder");

		final Folder root = sessionManager.getRootFolder();
		Assert.assertNotNull(root);

		logger.info("Finalizando el test del método getRootFolder");
	}

	@Test
	public void testIsSessionAlive() throws RepositoryCmisException {
		logger.info("Iniciando el test del método isSessionAlive");

		Assert.assertTrue(sessionManager.isSessionAlive());

		logger.info("Finalizando el test del método isSessionAlive");
	}
}
