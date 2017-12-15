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

package es.gob.afirma.triphase.server.document;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.cert.X509Certificate;
import java.util.Properties;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import es.gob.afirma.core.misc.AOUtil;

/**
 * Fachada simulada de gestor documental.
 * 
 * @author Tom&aacute;s Garc&iacute;a-;er&aacute;s
 */
public final class FakeDocumentManager implements DocumentManager {

	private static final String PDF_DOC = "TEST_PDF.pdf"; //$NON-NLS-1$
	private static Logger LOGGER = Logger.getLogger("es.gob.afirma"); //$NON-NLS-1$

	/** {@inheritDoc} */
	@Override
	public byte[] getDocument(final String id, final X509Certificate[] certChain, final Properties config, HttpSession session)
			throws IOException {
		return AOUtil.getDataFromInputStream(this.getClass().getResourceAsStream(PDF_DOC));
	}

	/** {@inheritDoc} */
	@Override
	public String storeDocument(final String id, final X509Certificate[] certChain, final byte[] data, final Properties config,
			HttpSession session) throws IOException {
		final File tempFile = File.createTempFile("fakeDocumentRetriever-" + id, ".pdf"); //$NON-NLS-1$ //$NON-NLS-2$
		OutputStream fos = null;
		try {
			fos = new FileOutputStream(tempFile);
			fos.write(data);
			fos.close();
		} catch (final IOException e) {
			LOGGER.severe("Error al almacenar los datos en el fichero '" + tempFile.getAbsolutePath() + "': " + e); //$NON-NLS-1$ //$NON-NLS-2$
			if (fos != null) {
				try {
					fos.close();
				} catch (final IOException e2) {
					LOGGER.warning("El fichero queda sin cerrar"); //$NON-NLS-1$
				}
			}
			throw e;
		}
		LOGGER.info("Guardamos la firma generada en: " + tempFile.getAbsolutePath()); //$NON-NLS-1$
		return "id-fake-" + id; //$NON-NLS-1$
	}
}