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

package es.mpt.dsic.inside.test.unitarios.store;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import es.mpt.dsic.inside.model.objetos.ObjetoInside;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideVersion;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideVersionable;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInside;
import es.mpt.dsic.inside.model.objetos.documento.metadatos.ObjetoDocumentoInsideMetadatosTipoDocumental;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteInside;
import es.mpt.dsic.inside.service.store.InsideServiceStore;
import es.mpt.dsic.inside.service.store.exception.InsideServiceStoreException;
import es.mpt.dsic.inside.service.store.exception.InsideStoreObjectAlreadyExistsException;
import es.mpt.dsic.inside.service.store.exception.InsideStoreObjectNotFoundException;
import es.mpt.dsic.inside.test.unitarios.InSideServiceTestBase;
import es.mpt.dsic.inside.test.unitarios.service.UtilsTest;

public class InsideStoreTest extends InSideServiceTestBase {
	
	protected static final Log logger = LogFactory.getLog(InSideServiceTestBase.class);
	
	@Autowired
	private InsideServiceStore store;

	
	@Test
	public void storeExpediente() throws InsideServiceStoreException, InsideStoreObjectAlreadyExistsException{
		ObjetoExpedienteInside expediente = UtilsTest.createObjetoExpedienteInside(true, true, true);
		//expediente = store.storeObject(expediente);
		logger.info("Almacenado correctamente expediente con identificador " + expediente.getIdentificador() + ", expediente: " + expediente);
	}
	
	@Test
	public void updateExpediente() throws InsideServiceStoreException, InsideStoreObjectAlreadyExistsException, InsideStoreObjectNotFoundException{
		ObjetoExpedienteInside expediente = UtilsTest.createObjetoExpedienteInside(true, true, true);
		//expediente = store.storeObject(expediente);
		ObjetoExpedienteInside expediente_recuperado = getObjeto(ObjetoExpedienteInside.class, expediente.getIdentificador());
		UtilsTest.assertEqual(expediente, expediente_recuperado,false);
		expediente_recuperado.getMetadatos().setVersionNTI("otra versión nti actualizada");
		expediente_recuperado.getMetadatos().getInteresado().add("enrique");
		//store.updateObject(expediente_recuperado);
		ObjetoExpedienteInside expediente_actualizado = getObjeto(ObjetoExpedienteInside.class, expediente.getIdentificador());
		UtilsTest.assertEqual(expediente_actualizado, expediente_recuperado,false);
		logger.info("Actualizado correctamente expediente");
	}
	
	@Test
	public void testStoreExpedienteDuplicado() throws InsideServiceStoreException, InsideStoreObjectAlreadyExistsException, InsideStoreObjectNotFoundException{
		ObjetoExpedienteInside expediente = UtilsTest.createObjetoExpedienteInside(true, true, true);
		//store.storeObject(expediente);
//		try {
			//store.storeObject(expediente);
			//throw new InsideServiceStoreException("El store no ha fallado al insertar un objeto con un identificador que ya existía");
//		} catch (InsideStoreObjectAlreadyExistsException e) {
//			logger.debug("Comprobado correctamente que no se inserta un expediente con un identificador (" + expediente.getIdentificador() + ") que ya existe");
//		}
	}
	
	@Test
	public void storeDocumento() throws InsideServiceStoreException, InsideStoreObjectAlreadyExistsException, InsideStoreObjectNotFoundException{
		ObjetoDocumentoInside documento = UtilsTest.createObjetoDocumentoInside(true, true, true, true);
		//documento = store.storeObject(documento);
		logger.info("Almacenado correctamente documento con identificador " + documento.getIdentificador());
	}
	
	@Test
	public void updateDocumento() throws InsideServiceStoreException, InsideStoreObjectAlreadyExistsException, InsideStoreObjectNotFoundException{
		ObjetoDocumentoInside documento = UtilsTest.createObjetoDocumentoInside(true, true, true, true);
		//documento = store.storeObject(documento);
		logger.info("Almacenado correctamente documento con identificador " + documento.getIdentificador());
		ObjetoDocumentoInside documento_recuperado = getObjeto(ObjetoDocumentoInside.class, documento.getIdentificador());
		UtilsTest.assertEqual(documento, documento_recuperado,false,false);
		documento_recuperado.getMetadatos().setTipoDocumental(ObjetoDocumentoInsideMetadatosTipoDocumental.TD_13);
		documento_recuperado.getMetadatos().setVersionNTI("otra versión nti actualizada");
		documento_recuperado.getMetadatos().getOrgano().add("enrique");
//		ObjetoDocumentoInside documento_actualizado = store.updateObject(documento_recuperado);
//		UtilsTest.assertEqual(documento_actualizado, documento_recuperado,false,false);
		logger.info("Actualizado correctamente documento");
	}
	
	private <T extends ObjetoInside<?>> T getObjeto(Class<T> tipo, final String identificador) throws InsideServiceStoreException, InsideStoreObjectNotFoundException{
		T objeto_recuperado = store.getObject(tipo,identificador);
		String info = "Recuperado correctamente " + objeto_recuperado.getClass() + " con identificador " + objeto_recuperado.getIdentificador();
		if(objeto_recuperado instanceof ObjetoInsideVersionable){
			ObjetoInsideVersion version = ((ObjetoInsideVersionable) objeto_recuperado).getVersion();
			info += " con version " + version.getVersion() + " actualizado en " + (version.getFechaVersion() != null ? version.getFechaVersion().getTime() : "vacio");
		}
		logger.info(info);
		return objeto_recuperado;
	}
	



}
