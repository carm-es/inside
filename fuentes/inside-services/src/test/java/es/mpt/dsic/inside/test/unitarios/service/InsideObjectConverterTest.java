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

package es.mpt.dsic.inside.test.unitarios.service;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import es.mpt.dsic.inside.model.objetos.ObjetoInside;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInside;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteInside;
import es.mpt.dsic.inside.model.objetos.firmas.FirmaInside;
import es.mpt.dsic.inside.model.objetos.firmas.FirmaInsideTipoFirmaEnum;
import es.mpt.dsic.inside.model.objetos.firmas.contenido.ContenidoFirmaCertificadoContenidoBinarioInside;
import es.mpt.dsic.inside.model.objetos.firmas.contenido.ContenidoFirmaInside;


import es.mpt.dsic.inside.service.exception.InSideServiceException;
import es.mpt.dsic.inside.service.object.converter.InsideServiceObjectConverterException;
import es.mpt.dsic.inside.service.object.definitions.InsideObjectDefinitionsContainer;
import es.mpt.dsic.inside.test.unitarios.InSideServiceTestBase;

public class InsideObjectConverterTest<T extends ObjetoInside<?>> extends InSideServiceTestBase {
		
		protected static final Log logger = LogFactory.getLog(InsideObjectConverterTest.class);
		
		private static String idDocument = "";
		private static String idExpediente = "";
		private static String idFirma = "";
		@Autowired
		@Qualifier("InsideObjectDefinitions")
		private InsideObjectDefinitionsContainer objectDefinitions;
		
		@Test
		public void testObjetoDocumentoInsideToRepository() throws InSideServiceException{
			
			logger.info("Iniciando test testObjetoDocumentoInsideToRepository");
			
			ObjetoDocumentoInside documento = UtilsTest.createObjetoDocumentoInside(true, true, true, true);
			
			
			try {
				ObjetoDocumentoInside salida = (ObjetoDocumentoInside) convierteObjetoInside(documento, "to");
				Assert.assertNotNull(salida.getIdentificadorRepositorio());
				ContenidoFirmaInside contenidoFirma = salida.getFirmas().get(0).getContenidoFirma();
				if(contenidoFirma instanceof ContenidoFirmaCertificadoContenidoBinarioInside){
					Assert.assertNotNull(((ContenidoFirmaCertificadoContenidoBinarioInside)contenidoFirma).getIdentificadorRepositorio());
					idFirma = ((ContenidoFirmaCertificadoContenidoBinarioInside)contenidoFirma).getIdentificadorRepositorio();
				}else{
					throw new AssertionError("El contenido de la firma no es de tipo ContenidoFirmaCertificadoContenidoBinarioInside");
				}
				idDocument = salida.getIdentificadorRepositorio();
				
			} catch (Exception e) {
				e.printStackTrace();
				Assert.assertTrue(false);
			}
			
			
			logger.debug("Identificador del objeto de entrada: " + documento.getIdentificador());
			logger.info("Finalizando test testObjetoDocumentoInsideToRepository");
		}
		
		
		@Test
		public void testObjetoDocumentoInsideFromRepository() throws InSideServiceException {
			logger.info("Iniciando test testObjetoDocumentoInsideFromRepository");
			ObjetoDocumentoInside entrada = new ObjetoDocumentoInside ();
			entrada.setIdentificadorRepositorio(idDocument);
			
			FirmaInside firma = new FirmaInside();
			firma.setTipoFirma(FirmaInsideTipoFirmaEnum.TF_06);			
			ContenidoFirmaCertificadoContenidoBinarioInside contenidoFirma = new ContenidoFirmaCertificadoContenidoBinarioInside();
			contenidoFirma.setIdentificadorRepositorio(idFirma);
			firma.setContenidoFirma(contenidoFirma);
			
			entrada.getFirmas().add(firma);
			
			try {
				ObjetoDocumentoInside salida = (ObjetoDocumentoInside) convierteObjetoInside (entrada, "from");
			
				Assert.assertEquals("version 1.0", salida.getMetadatos().getVersionNTI());
			
				List<String> organos = new ArrayList<String> ();
				organos.add("organo1");
				organos.add("organo2");
			
				Assert.assertArrayEquals(organos.toArray(), salida.getMetadatos().getOrgano().toArray());
				
				Assert.assertNotNull(salida.getContenido().getValorBinario());
				ContenidoFirmaInside contenidoFirmaSalida = salida.getFirmas().get(0).getContenidoFirma();
				if(contenidoFirmaSalida instanceof ContenidoFirmaCertificadoContenidoBinarioInside){
					Assert.assertNotNull(((ContenidoFirmaCertificadoContenidoBinarioInside)contenidoFirmaSalida).getValorBinario());
				}else{
					throw new AssertionError("El contenido de la firma no es de tipo ContenidoFirmaCertificadoContenidoBinarioInside");
				}
			} catch (Exception e) {
				e.printStackTrace();
				Assert.assertTrue(false);
			}
			logger.info("Finalizando test testObjetoDocumentoInsideFromRepository");
		}
		
		
		@Test
		public void testExpedienteDocumentoInsideToRepository() throws InSideServiceException{
			logger.info("Iniciando test testExpedienteDocumentoInsideToRepository");
			ObjetoExpedienteInside objeto = UtilsTest.createObjetoExpedienteInside(true, true, false);
		
			try {
			
				ObjetoExpedienteInside salida = (ObjetoExpedienteInside) convierteObjetoInside(objeto, "to");
				Assert.assertNotNull(salida.getIdentificadorRepositorio());
				idExpediente = salida.getIdentificadorRepositorio();
				
			} catch (Exception e) {
				e.printStackTrace();
				Assert.assertTrue(false);
			}
			logger.info("Finalizando test testExpedienteDocumentoInsideToRepository");
		}
		
		
		@Test
		public void testObjetoExpedienteInsideFromRepository() throws InSideServiceException {
			logger.info("Iniciando test testObjetoExpedienteInsideFromRepository");
			ObjetoExpedienteInside entrada = new ObjetoExpedienteInside ();
			entrada.setIdentificadorRepositorio(idExpediente);
			
			try {
				ObjetoExpedienteInside salida = (ObjetoExpedienteInside) convierteObjetoInside (entrada, "from");
			
				Assert.assertEquals("version 1.0", salida.getMetadatos().getVersionNTI());
			
				List<String> interesados = new ArrayList<String> ();
				interesados.add("12345678Z");
				interesados.add("99999999R");
			
				Assert.assertArrayEquals(interesados.toArray(), salida.getMetadatos().getInteresado().toArray());
			} catch (Exception e) {
				e.printStackTrace();
				Assert.assertTrue(false);
			}
			logger.info("Finalizando test testObjetoExpedienteInsideFromRepository");
		}
		
	
		
		@SuppressWarnings("unchecked")
		private ObjetoInside<?> convierteObjetoInside (@SuppressWarnings("rawtypes") ObjetoInside objetoInside, String fromTo) throws InSideServiceException{
			@SuppressWarnings("rawtypes")
			ObjetoInside salida = null;
			try {
				if (StringUtils.equalsIgnoreCase("from", fromTo)) {
					salida = objectDefinitions.getConverter(objetoInside.getClass()).fromRepository((T) objetoInside);
				} else if (StringUtils.equalsIgnoreCase("to", fromTo)) {
					salida = objectDefinitions.getConverter(objetoInside.getClass()).toRepository((T) objetoInside);
				}
				logger.info("Objeto " + objetoInside.getClass() + " convertido correctamente");
			} catch (InsideServiceObjectConverterException e) {
				logger.error("InsideServiceObjectConverterException: " + e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return salida;
		}
		
}
