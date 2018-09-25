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

package es.mpt.dsic.inside.service.object.definitions;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;

import es.mpt.dsic.inside.model.objetos.ObjetoInside;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideMetadatos;
import es.mpt.dsic.inside.service.object.converter.InsideServiceObjectConverter;
import es.mpt.dsic.inside.service.object.metadatos.metadato.Metadato;
import es.mpt.dsic.inside.service.object.signer.InsideServiceObjectSigner;
import es.mpt.dsic.inside.service.object.validators.InsideServiceObjectValidator;
import es.mpt.dsic.inside.service.store.InsideStoreObjectDef;

/**
 * Contenedor de las definiciones de objetos 
 * de Inside
 * Las definiciones están almacenadas en un mapa
 * cuya clave es la clase a la que pertenece el objeto
 * de Inside
 * @author miguel.ortega
 *
 */
public class InsideObjectDefinitionsContainerMap implements InsideObjectDefinitionsContainer {
	
	protected static final Log logger = LogFactory.getLog(InsideObjectDefinitionsContainerMap.class);
	
	private Map<Class<? extends ObjetoInside<?>>, InsideStoreObjectDef<?, ?>> objectDefinitions;

	/*public Map<Class<? extends ObjetoInside<?>>,InsideStoreObjectDef<?>> getObjectDefinitions() {
		return objectDefinitions;
	}*/
	
	@SuppressWarnings("unused")
	private Set<InsideStoreObjectDef<?,?>> objectDefs;

	@Required
	public <T extends ObjetoInside<K>, K extends ObjetoInsideMetadatos> void setObjectDefs(Set<InsideStoreObjectDef<T,K>> objectDefinitions)
	{
		//objectDefs = objectDefinitions;
		Iterator<InsideStoreObjectDef<T, K>> it = objectDefinitions.iterator();
		this.objectDefinitions = new HashMap<Class<? extends ObjetoInside<?>>, InsideStoreObjectDef<?,?>>();
		while (it.hasNext()) {
			InsideStoreObjectDef<?,?> insideStoreObjectDef = it.next();
			logger.info("Añadida definición de objeto " + insideStoreObjectDef.getJavaClass());
			this.objectDefinitions.put(insideStoreObjectDef.getJavaClass(), insideStoreObjectDef);
			
		}
	}
	
	/**
	 * Devuelve la definción del objeto Inside
	 * 
	 * @param objetoInside
	 * @return
	 * @throws InSideServiceObjectDefinitionNotFoundException	Si el objeto no se encuentra en el
	 * 												contenedor de definiciones
	 */
	@Override
	public <T extends ObjetoInside<K>, K extends ObjetoInsideMetadatos> InsideStoreObjectDef<T,K> getObjectDefinition(Class<T> tipoObjetoInside) throws InSideServiceObjectDefinitionNotFoundException{
		if(!this.objectDefinitions.containsKey(tipoObjetoInside)){
			throw new InSideServiceObjectDefinitionNotFoundException("No se encuentra definicion para el objeto " + tipoObjetoInside);
		}
		return  (InsideStoreObjectDef<T, K>) this.objectDefinitions.get(tipoObjetoInside);
	}

	@Override
	public <T extends ObjetoInside<K>, K extends ObjetoInsideMetadatos>Map<String, Metadato<?>> getMetadatos(Class<T> tipoObjetoInside) throws InSideServiceObjectDefinitionNotFoundException {
		return this.getObjectDefinition(tipoObjetoInside).getMetadatos();
	}

	@Override
	public <T extends ObjetoInside<K>, K extends ObjetoInsideMetadatos>InsideServiceObjectValidator<T,K> getValidator(Class<T> tipoObjetoInside) throws InSideServiceObjectDefinitionNotFoundException {
		return this.getObjectDefinition(tipoObjetoInside).getValidator();
	}

	@Override
	public <T extends ObjetoInside<K>, K extends ObjetoInsideMetadatos>InsideServiceObjectConverter<T,K> getConverter(Class<T> tipoObjetoInside) throws InSideServiceObjectDefinitionNotFoundException {
		return this.getObjectDefinition(tipoObjetoInside).getConverter();
	}
	
	@Override
	public <T extends ObjetoInside<K>, K extends ObjetoInsideMetadatos>InsideServiceObjectSigner<T,K> getSigner(Class<T> tipoObjetoInside) throws InSideServiceObjectDefinitionNotFoundException {
		return this.getObjectDefinition(tipoObjetoInside).getSigner();
	}
	
	@Override
	public Set<Class<? extends ObjetoInside<?>>> getObjectDefsClassNames(){
		return this.objectDefinitions.keySet();
	}
	
	@Override
	public <T extends ObjetoInside<K>, K extends ObjetoInsideMetadatos> Collection<InsideStoreObjectDef<?, ?>> getInsideStoreObjectDef(){
		return this.objectDefinitions.values();
	}
}
