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

package es.mpt.dsic.inside.service.utilFirma.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.mpt.dsic.eeutil.client.model.ApplicationLogin;
import es.mpt.dsic.eeutil.client.model.CSVInfo;
import es.mpt.dsic.eeutil.client.model.InSideException;
import es.mpt.dsic.eeutil.client.utilFirma.EeUtilService;
import es.mpt.dsic.eeutil.client.utilFirma.EeUtilServiceImplService;
import es.mpt.dsic.inside.service.util.XMLUtils;
import es.mpt.dsic.inside.service.utilFirma.InsideServiceUtilFirma;
import es.mpt.dsic.inside.service.utilFirma.exception.InsideServiceUtilFirmaException;

public class InsideServiceUtilFirmaImpl implements
		InsideServiceUtilFirma {

	protected static final Log logger = LogFactory
			.getLog(InsideServiceUtilFirmaImpl.class);

	private Properties properties;
	private EeUtilService port;
	private ApplicationLogin applicationLogin;

	private boolean activo;
	private static String ACTIVO = "S";

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	@PostConstruct
	public void configure() throws InsideServiceUtilFirmaException {
		String utilFirmaActivo = properties
				.getProperty("utilFirma.activo");

		if (!ACTIVO.contentEquals(utilFirmaActivo)) {
			logger.info("El WS de Util-Firma no está activo");
			activo = false;
		} else {
			String urlUtilFirma = null;
			try {
				urlUtilFirma = properties.getProperty("utilFirma.url");
				logger.debug(String.format(
						"El WS de Util-Firma se encuentra en %s",
						urlUtilFirma));

				EeUtilServiceImplService ss = new EeUtilServiceImplService(new URL(urlUtilFirma));

				port = ss.getEeUtilServiceImplPort();

				applicationLogin = new ApplicationLogin();
				applicationLogin.setIdaplicacion(properties
						.getProperty("utilFirma.idaplicacion"));
				applicationLogin.setPassword(properties
						.getProperty("utilFirma.password"));

				activo = true;
			} catch (MalformedURLException me) {
				logger.error(
						"No se puede crear la URL del servicio de Util-Firma "
								+ urlUtilFirma, me);
				throw new InsideServiceUtilFirmaException("No se ha podido conectar al servicio de Util-Firma", me);
			}
		}
	}

	
	@Override
	public String generarCSV(byte[] data, String mime) throws InsideServiceUtilFirmaException {
				
		String retorno;
		if (activo) {
			try {
				byte[] dataFirma = XMLUtils.deFirmaBase64_A_DSSignature(data);
				CSVInfo csvInfo = new CSVInfo();
				csvInfo.setContenido(dataFirma);
				csvInfo.setMime(mime);
				
				retorno = port.generarCSV(applicationLogin, csvInfo);
			} catch (InSideException e) {
				logger.error("Se ha producido un error al generar el csv:" + e.getMessage());
				throw new InsideServiceUtilFirmaException("Se ha producido un error al generar el csv", e);
			} catch (IOException e) {
				logger.error("Se ha producido un error al generar el csv:" + e.getMessage());
				throw new InsideServiceUtilFirmaException("Se ha producido un error al generar el csv", e);
			}
		} else {
			throw new InsideServiceUtilFirmaException("El servicio de Util-Firma no se encuentra activo");
		}
		return retorno;
	}
	
}
