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

package es.mpt.dsic.inside.service.object.validators.impl;


import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;

import es.mpt.dsic.inside.model.objetos.ObjetoInside;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideMetadatos;
import es.mpt.dsic.inside.service.exception.InSideServiceException;
import es.mpt.dsic.inside.service.object.metadatos.metadato.Metadato;
import es.mpt.dsic.inside.service.object.metadatos.metadato.exception.InsideServiceMetadatoValueExtractionException;
import es.mpt.dsic.inside.service.object.metadatos.validator.Exception.InSideServiceMetadatoValidationException;
import es.mpt.dsic.inside.service.object.metadatos.validator.Exception.InSideServiceValidationException;
import es.mpt.dsic.inside.service.object.validators.InsideServiceObjectValidator;
import es.mpt.dsic.inside.service.object.validators.exception.InsideServiceMetadatoNotFoundInObjectException;
import es.mpt.dsic.inside.service.object.validators.exception.InsideServiceObjectValidationException;
import es.mpt.dsic.inside.service.util.InsideUtils;

/**
 * Validador para los metadatos de los objetos de Inside
 *
 * Para la validación de los metadatos se busca en los metadatos
 * y en los metadatos adicionales de los objetos inside
 * @author miguel.ortega
 *
 */
public class InsideServiceMetadatosValidator<T extends ObjetoInside<K>, K extends ObjetoInsideMetadatos> implements InsideServiceObjectValidator<T,K> {
	
	protected static final Log logger = LogFactory.getLog(InsideServiceObjectValidator.class);
	

	private Map<String,Metadato<?>> metadatosDefinitions;
	

	@Override
	public T validate(T objeto) throws InSideServiceException, InsideServiceObjectValidationException
	{
		logger.debug("Validando objeto " + objeto.getClass());
		
		List<InSideServiceMetadatoValidationException> validacionErrors = new ArrayList<InSideServiceMetadatoValidationException>();
		
		for (Metadato<?> metadato : this.metadatosDefinitions.values()) {
			logger.debug("Validando metadato " + metadato.getNombre() + " del objeto " + objeto.getClass());
			
			try {
				cleanMetadato(metadato, objeto);
			} catch (InSideServiceMetadatoValidationException e) {
				validacionErrors.add(e);
			} 
			
			logger.debug("Validado correctamente el metadato " + metadato.getNombre() + " del objeto " + objeto.getClass());
			
		}
		
		if(!validacionErrors.isEmpty()){
			throw new InsideServiceObjectValidationException(objeto,validacionErrors);
		}
		
		return objeto;
	}


	public Map<String, Metadato<?>> getMetadatosDefinitions() {
		return metadatosDefinitions;
	}

	@Required
	public void setMetadatosDefinitions(Map<String,Metadato<?>> metadatosDefinitions) {
		this.metadatosDefinitions = metadatosDefinitions;
	}
	
	private void cleanMetadato(Metadato<?> metadato,ObjetoInside<?> objeto) throws InsideServiceMetadatoValueExtractionException, InSideServiceValidationException
	{
		MetadatoPropertyDescriptor desc = findMetadatoPropertyDescriptor(metadato,objeto);
		Object value = getPropertyValue(desc);
		
		Map<String, Object> dataDepends = getMetadataDepends(metadato, objeto);
		
		try {
			if (metadato.getValidator() != null) {
				value = metadato.clean(value);
			}
			if (metadato.getValidatorDataBase() != null) {
				validateDataBase(metadato, value);
			}
			if (metadato.getValidatorService() != null) {
				validateService(metadato, value, dataDepends);
			}
		} catch (InSideServiceMetadatoValidationException e) {
			throw e;
		}
		
		setPropertyValue(desc, value);
	}
	
	
	private Object getPropertyValue(MetadatoPropertyDescriptor desc) throws InsideServiceMetadatoValueExtractionException{
		Method readMethod = desc.getPropertyDescriptor().getReadMethod();
		if(readMethod == null){
			throw new InsideServiceMetadatoValueExtractionException("No se puede acceder en lectura al metadato " + desc.getMetadato().getNombre() + " en el objeto " + desc.getObjeto().getClass());
		}
		
		Object value;
		try {
			value = readMethod.invoke(desc.getObjeto());
		} catch (Exception e) {
			throw new InsideServiceMetadatoValueExtractionException("Error leyendo metadato " + desc.getMetadato().getNombre() + " en el objeto " + desc.getObjeto().getClass() + " : " + e.getMessage(),e);
		}
		
		return value;
	}
	
	private void setPropertyValue(MetadatoPropertyDescriptor desc,Object value) throws InsideServiceMetadatoValueExtractionException{
		Method writeMethod = desc.getPropertyDescriptor().getWriteMethod();
		if(writeMethod == null){
			throw new InsideServiceMetadatoValueExtractionException("No se puede acceder en escritura al metadato " + desc.getMetadato().getNombre() + " en el objeto " + desc.getObjeto().getClass());
		}

		try {
			writeMethod.invoke(desc.getObjeto(),value);
		} catch (Exception e) {
			throw new InsideServiceMetadatoValueExtractionException("Error estableciendo valor de metadato " + desc.getMetadato().getNombre() + " en el objeto " + desc.getObjeto().getClass() + " : " + e.getMessage(),e);
		}
	}
	
	private MetadatoPropertyDescriptor findMetadatoPropertyDescriptor(Metadato<?> metadato,ObjetoInside<?> objeto) throws InsideServiceMetadatoNotFoundInObjectException{
		Object metadatos = objeto.getMetadatos();
		PropertyDescriptor pd = findMetadatoPropertyDescriptorInMetadatos(metadato,metadatos);
		//Si no está en los metadatos "normales", buscar en los adicionales
		if(pd == null){
			metadatos = objeto.getMetadatos().getMetadatosAdicionales();
			pd = findMetadatoPropertyDescriptorInMetadatos(metadato,metadatos);
		}
		if(pd != null){
			logger.debug("Encontrado el metadato " + metadato.getNombre() + " en los metadatos de tipo '" + metadatos.getClass() + "' dentro del objeto " + objeto.getClass());
			return new MetadatoPropertyDescriptor(metadato,metadatos, pd);
		}else{
			throw new InsideServiceMetadatoNotFoundInObjectException(metadato,objeto);
		}
	}
	
	private static PropertyDescriptor findMetadatoPropertyDescriptorInMetadatos(Metadato<?> metadato,Object metadatos)
	{
		String fieldName = metadato.getNombre();
		
		PropertyDescriptor pd = InsideUtils.findPropertyDescriptor (metadatos, fieldName);
		
		//probamos primero a encontrar la propiedad tal como se llama el metadato
		/*PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(metadatos.getClass(),fieldName);
		
		//Si no se llama así y como esto es Java :) , puede que se haya escrito de forma "camelizada"
		if(pd == null){
			fieldName = InsideUtils.camelize(fieldName);
			logger.debug("No existe el metadato " + metadato.getNombre() + " en las propiedades del objeto " + metadatos.getClass() + ", probaré con la versión camelizada: " + fieldName);
			pd = BeanUtils.getPropertyDescriptor(metadatos.getClass(),fieldName);			
		}
		
		// Si tampoco se encuentra, se mirará si coincide ignorando mayúsculas y minúsculas
		if (pd == null) {
			PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(metadatos.getClass());
			boolean found = false;			
			for (PropertyDescriptor propd : pds && !found) {
				
			}
		}*/
		
		if(pd != null){
			logger.debug("Encontrado el metadato " + metadato.getNombre() + " el el campo " + fieldName + " dentro del objeto " + metadatos.getClass());
			
		}
		return pd;
	}
	
	/**
	 * POJO para tener la referencia al objeto metadatos o metadatosAdicionales
	 * y el descriptor de la propiedad sobre la que estamos trabajando al manipular 
	 * un metadato en un objeto Inside
	 * @author miguel.ortega
	 *
	 */
	protected class MetadatoPropertyDescriptor{
		
		private PropertyDescriptor pd;
		
		private Object objeto;

		private Metadato<?> metadato;

		public PropertyDescriptor getPropertyDescriptor() {
			return pd;
		}

		public Object getObjeto() {
			return objeto;
		}

		public MetadatoPropertyDescriptor(Metadato<?> metadato,Object objeto, PropertyDescriptor pd) {
			this.metadato = metadato;
			this.objeto = objeto;
			this.pd = pd;
		}

		public Metadato<?> getMetadato() {
			return metadato;
		}

	}
	
	private void validateDataBase(Metadato<?> metadato, Object dato)
			throws InSideServiceMetadatoValidationException {
		try {
			Method miMetodo = metadato.getValidatorDataBase().getClass().getMethod(metadato.getValidatorDataBase().getMetodo(), Object.class);
			miMetodo.invoke(metadato.getValidatorDataBase(), dato);
		} catch (SecurityException e) {
			throw new InSideServiceMetadatoValidationException(metadato, e);	
		} catch (NoSuchMethodException e) {
			throw new InSideServiceMetadatoValidationException(metadato , e);	
		} catch (IllegalArgumentException e) {
			throw new InSideServiceMetadatoValidationException(metadato , e);	
		} catch (IllegalAccessException e) {
			throw new InSideServiceMetadatoValidationException(metadato , e);	
		} catch (InvocationTargetException e) {
			throw new InSideServiceMetadatoValidationException(metadato , e.getTargetException().getMessage(), e);	
		}
	}
	
	private void validateService(Metadato<?> metadato, Object dato, Map<String, Object> datosAux)
			throws InSideServiceMetadatoValidationException {
		try {
			Method miMetodo = metadato.getValidatorService().getClass().getMethod(metadato.getValidatorService().getMetodo(), Object.class, Map.class);
			miMetodo.invoke(metadato.getValidatorService(), dato, datosAux);
		} catch (SecurityException e) {
			throw new InSideServiceMetadatoValidationException(metadato, e);	
		} catch (NoSuchMethodException e) {
			throw new InSideServiceMetadatoValidationException(metadato , e);	
		} catch (IllegalArgumentException e) {
			throw new InSideServiceMetadatoValidationException(metadato , e);	
		} catch (IllegalAccessException e) {
			throw new InSideServiceMetadatoValidationException(metadato , e);	
		} catch (InvocationTargetException e) {
			throw new InSideServiceMetadatoValidationException(metadato , e.getTargetException().getMessage(), e);	
		}
	}
	
	private Map<String, Object> getMetadataDepends(Metadato<?> metadato, ObjetoInside<?> objeto) throws InsideServiceMetadatoValueExtractionException, InSideServiceValidationException {
		Map<String, Object> retorno = new HashMap<String, Object>();
		if (metadato.getDependsValidation() != null
			&& !metadato.getDependsValidation().equals("")) {
			StringTokenizer tmpToken = new StringTokenizer(metadato.getDependsValidation() , ",");
			while (tmpToken.hasMoreTokens()) {
				String dataDef = tmpToken.nextToken();
				for (Metadato<?> metadatoAux : this.metadatosDefinitions.values()) {
					if (metadatoAux.getNombre().equals(dataDef)) {
						MetadatoPropertyDescriptor desc2 = findMetadatoPropertyDescriptor(metadatoAux,objeto);
						retorno.put(dataDef, getPropertyValue(desc2));
					}
				}
			}
		}
		return retorno;
	}
}
