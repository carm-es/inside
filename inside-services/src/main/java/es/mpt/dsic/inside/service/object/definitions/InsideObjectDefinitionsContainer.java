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
import java.util.Map;
import java.util.Set;

import es.mpt.dsic.inside.model.objetos.ObjetoInside;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideMetadatos;
import es.mpt.dsic.inside.service.object.converter.InsideServiceObjectConverter;
import es.mpt.dsic.inside.service.object.metadatos.metadato.Metadato;
import es.mpt.dsic.inside.service.object.signer.InsideServiceObjectSigner;
import es.mpt.dsic.inside.service.object.validators.InsideServiceObjectValidator;
import es.mpt.dsic.inside.service.store.InsideStoreObjectDef;

public interface InsideObjectDefinitionsContainer {

	public <T extends ObjetoInside<K>, K extends ObjetoInsideMetadatos> InsideStoreObjectDef<T,K> getObjectDefinition(Class<T> tipoObjetoInside) throws InSideServiceObjectDefinitionNotFoundException;
	
	public <T extends ObjetoInside<K>, K extends ObjetoInsideMetadatos> Map<String, Metadato<?>> getMetadatos(Class<T> tipoObjetoInside) throws InSideServiceObjectDefinitionNotFoundException;
	
	public  <T extends ObjetoInside<K>, K extends ObjetoInsideMetadatos> InsideServiceObjectValidator<T,K> getValidator(Class<T> tipoObjetoInside) throws InSideServiceObjectDefinitionNotFoundException;
	
	public <T extends ObjetoInside<K>, K extends ObjetoInsideMetadatos> InsideServiceObjectConverter<T,K> getConverter(Class<T> tipoObjetoInside) throws InSideServiceObjectDefinitionNotFoundException;
	
	public <T extends ObjetoInside<K>, K extends ObjetoInsideMetadatos> InsideServiceObjectSigner<T,K> getSigner(Class<T> tipoObjetoInside) throws InSideServiceObjectDefinitionNotFoundException;

	public <T extends ObjetoInside<K>, K extends ObjetoInsideMetadatos> Set<Class<T>> getObjectDefsClassNames();

	public <T extends ObjetoInside<K>, K extends ObjetoInsideMetadatos> Collection<InsideStoreObjectDef<?, ?>> getInsideStoreObjectDef();
	
}
