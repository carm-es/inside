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

package es.mpt.dsic.integration.cmis.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.Property;
import org.apache.chemistry.opencmis.client.api.QueryResult;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.exceptions.CmisBaseException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.mpt.dsic.integration.cmis.exception.RepositoryCmisException;
import eu.medsea.mimeutil.detector.ExtensionMimeDetector;
import eu.medsea.mimeutil.detector.MagicMimeMimeDetector;


public class CmisUtils {

	private static final Log logger = LogFactory.getLog(CmisUtils.class);

	private CmisUtils() {
	}
	
	/**
	 * Obtiene el tipo mime en base al contenido de un fichero
	 * @param data
	 * @return
	 * @throws RepositoryCmisException 
	 * @throws  
	 */
	public static String getMimeType(byte[] data) throws  RepositoryCmisException{
		return getFirstMimeType(new MagicMimeMimeDetector().getMimeTypes(data));
	}
	
	/**
	 * Obtiene el tipo mime en base al contenido de un fichero
	 * y el nombre de su fichero
	 * @param filename
	 * @param data
	 * @return
	 * @throws  
	 */
	public static String getMimeType(String filename,byte[] data) throws RepositoryCmisException{
		String mimeType = getFirstMimeType(new ExtensionMimeDetector().getMimeTypes(filename));
		return mimeType != null ? mimeType : getMimeType(data);
	}
	
	/**
	 * Dada una colección de tipos mime, da el primero
	 * @param mimeTypes
	 * @return
	 * @throws RepositoryCmisException
	 */
	private static String getFirstMimeType(Collection<?> mimeTypes) throws RepositoryCmisException{
		if(mimeTypes.size() >= 1){
			return mimeTypes.iterator().next() + "";
		}else{
			throw new RepositoryCmisException("No se ha detectado ningún tipo mime");
		}
	}
	

	/**
	 * @param session
	 * @param query
	 * @return
	 * @throws CmisBaseException
	 */
	public static ItemIterable<QueryResult> executeQuery(final Session session, final String query)
			throws CmisBaseException {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Ejecutando la consulta: %s", query));
		}
		return session.query(query, false);
	}

	/**
	 * @param object
	 * @return
	 * @throws CmisBaseException
	 */
	/*public static Map<String, Metadato> getMetadatos(final CmisObject object) throws CmisBaseException {
		Map<String, Metadato> metadatos = null;
		if (object != null) {
			metadatos = new HashMap<String, Metadato>();
			for (final Property<?> property : object.getProperties()) {
				Metadato metadato = null;
				if (property.isMultiValued()) {
					metadato = getMetadatoMulti(property);
				} else {
					metadato = getMetadatoSimple(property);
				}
				if (metadato != null) {
					metadatos.put(property.getId(), metadato);
				}
			}
		}
		return metadatos;
	}
*/
	/*private static Metadato getMetadatoSimple(final Property<?> property) {
		Metadato metadato = new MetadatoSimple();
		metadato.setClave(property.getId());
		metadato.setMultivalor(false);

		final Object value = property.getValue();
		switch (property.getType()) {
			case DATETIME:
				metadato.setTipo(TIPO_DATE);
				metadato.setValor(value != null ? ((Calendar) value).getTime() : null);
			break;

			case INTEGER:
			case DECIMAL:
				metadato.setTipo(TIPO_INT);
				metadato.setValor(value != null ? Integer.valueOf(value.toString()) : null);
			break;

			case STRING:
			case BOOLEAN:
			case ID:
			case URI:
				metadato.setTipo(TIPO_STRING);
				metadato.setValor(value != null ? value.toString() : null);
			break;

			default:
				metadato = null;
			break;
		}

		return metadato;
	}

	private static Metadato getMetadatoMulti(final Property<?> property) {
		Metadato metadato = new MetadatoMulti();
		metadato.setClave(property.getId());
		metadato.setMultivalor(true);

		final List<Object> valores = new ArrayList<Object>();
		for (final Object value : property.getValues()) {
			switch (property.getType()) {
				case DATETIME:
					metadato.setTipo(TIPO_DATE);
					valores.add(value != null ? ((Calendar) value).getTime() : null);
				break;

				case INTEGER:
				case DECIMAL:
					metadato.setTipo(TIPO_INT);
					valores.add(value != null ? Integer.valueOf(value.toString()) : null);
				break;

				case STRING:
				case BOOLEAN:
				case ID:
				case URI:
					metadato.setTipo(TIPO_STRING);
					valores.add(value != null ? value.toString() : null);
				break;

				default:
					metadato = null;
				break;
			}
		}
		if (metadato != null) {
			metadato.setValor(valores);
		}
		return metadato;
	}*/
	
	public static Map<String, Object> getMetadatos (final CmisObject cmisObject) {
		Map<String, Object> metadatos = new HashMap<String, Object> ();
		
		List<Property<?>> propiedades = cmisObject.getProperties();
		
		for (Property<?> propiedad : propiedades) {			
			String nombre = propiedad.getId();
			Object valor = propiedad.getValue();			
			metadatos.put(nombre, valor);
		}
		
		return metadatos;
	}
}
