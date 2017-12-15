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

import java.io.FileInputStream;
import java.net.URL;

import javax.xml.namespace.QName;

import junit.framework.Assert;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.mpt.dsic.firma.cliente.model.ApplicationLogin;
import es.mpt.dsic.firma.cliente.model.DatosEntradaFichero;
import es.mpt.dsic.firma.cliente.model.DatosSalida;
import es.mpt.dsic.firma.cliente.model.Error;
import es.mpt.dsic.firma.cliente.model.ResultadoFirmaFichero;
import es.mpt.dsic.firma.cliente.service.EeUtilFirmarService;
import es.mpt.dsic.firma.cliente.service.impl.EeUtilFirmarServiceImplService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:clientefirma-context-test.xml" })
public class FirmaTest {

    private static String urlService = "http://desappjava.seap.minhap.es/eeutil/ws/EeUtilFirmarService?wsdl";
    private static final QName SERVICE_NAME = new QName("http://impl.service.ws.inside.dsic.mpt.es/", "EeUtilFirmarServiceImplService");
    private static String algoritmo = "SHA1withRSA";
    private static String formato = "XAdES Detached";
    private static String modo = "implicit";
    
	
	
	@Test 
	public void firmaFichero () {
		try  {
			EeUtilFirmarService port = getPort ();
			
			ApplicationLogin appLogin = createAppLogin ();
			DatosEntradaFichero datosEntrada = new DatosEntradaFichero ();
			datosEntrada.setAlgoritmoFirma(algoritmo);
			datosEntrada.setFormatoFirma(formato);
			datosEntrada.setModoFirma(modo);
			datosEntrada.setContenido(IOUtils.toByteArray(new FileInputStream ("src/test/resources/PEDEFE.pdf")));
			
			DatosSalida salida = port.firmaFichero(appLogin, datosEntrada);
			
			if (salida.getEstado().contentEquals("OK")) {
				ResultadoFirmaFichero res = (ResultadoFirmaFichero) salida.getDatosResultado();
				Assert.assertNotNull(res);
			} else {
				Error e = (Error) salida.getDatosResultado();
				String mensaje = e.getMensaje() + ", " + e.getCausa();
				System.out.println(mensaje);
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		
	}
	
	private static EeUtilFirmarService getPort () {
		URL url = null;
		EeUtilFirmarService port = null;
		try {
			url = new URL (urlService);
			EeUtilFirmarServiceImplService ss = new EeUtilFirmarServiceImplService(url, SERVICE_NAME);
	        port = ss.getEeUtilFirmarServiceImplPort();  
			
		} catch (Exception e) {
			Assert.assertTrue(false);
			e.printStackTrace();
		}
		
		return port;
	}
	
	private static ApplicationLogin createAppLogin () {
		ApplicationLogin appLogin = new ApplicationLogin ();
		appLogin.setIdaplicacion("portafirmas");
		appLogin.setPassword("portafirmas");
		return appLogin;
			
	}
}
