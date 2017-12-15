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

package es.mpt.dsic.integration.cmis;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import es.mpt.dsic.integration.cmis.exception.RepositoryCmisException;
import es.mpt.dsic.integration.cmis.session.CmisSessionManager;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
@ContextConfiguration(locations = { "context/cmis-context-test.xml" })
public class CmisTestBase extends AbstractJUnit4SpringContextTests {
	
	private static final Log logger = LogFactory.getLog(CmisTestBase.class);
	
	private static final String testDocsRelativePath = "/src/test/docs";
	
	@Test
	public void testLogger(){
		logger.info("Probando logger!");
	}
	
	@Autowired
	@Qualifier("cmis-test")
	protected Properties properties;

	@Autowired
	protected CmisSessionManager sessionManager;

	protected static File rutaDocsPruebasDir = null;

	@PostConstruct
	public void init() throws RepositoryCmisException, URISyntaxException {
		
		if(rutaDocsPruebasDir == null){
			File rutaDocsDir = new File(new File(new File(CmisTestBase.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParent()).getParent() + testDocsRelativePath);
			
			
			Assert.assertTrue("No existe la ruta de los ficheros de prueba " + rutaDocsDir, rutaDocsDir.exists());
			Assert.assertTrue("La ruta de los ficheros de prueba no es un directorio" + rutaDocsDir, rutaDocsDir.isDirectory());
			logger.info("Ruta de los documentos de prueba " + rutaDocsDir);
			
			File[] files = rutaDocsDir.listFiles();
			Assert.assertTrue("La ruta de los ficheros de prueba no contiene ningún fichero" + rutaDocsDir, files.length > 0);
			
			rutaDocsPruebasDir = rutaDocsDir;
			
			sessionManager.authenticate();
		
		}
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
	protected byte[] fileToByteArray(String rutaDocDisco) throws RepositoryCmisException{
		try {
			return FileUtils.readFileToByteArray(new File(rutaDocDisco));
		} catch (IOException e) {
			throw new RepositoryCmisException(e);
		}
	}
	
}
