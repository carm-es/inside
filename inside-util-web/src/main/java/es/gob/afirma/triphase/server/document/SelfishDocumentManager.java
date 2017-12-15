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

import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.Properties;

import javax.servlet.http.HttpSession;

import es.gob.afirma.core.misc.Base64;

/**
 * Simulador de gestor documental, el identificador de documento es el propio
 * documento en Base64.
 * 
 * @author Tom&aacute;s Garc&iacute;a-Mer&aacute;s.
 */
public final class SelfishDocumentManager implements DocumentManager {

	/**
	 * Constructor vac&iacute;o.
	 * 
	 * @param config
	 *            No se usa.
	 */
	public SelfishDocumentManager(final Properties config) {
		// No hacemos nada
	}

	@Override
	public byte[] getDocument(final String id, final X509Certificate[] certChain, final Properties config, HttpSession session)
			throws IOException {
		return Base64.decode(
		// Por si acaso deshacemos un posible URL Safe
				id.replace("-", "+").replace("_", "/") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				);
	}

	@Override
	public String storeDocument(final String id, final X509Certificate[] certChain, final byte[] data, final Properties config,
			HttpSession session) throws IOException {
		return Base64.encode(data, true);
	}

}