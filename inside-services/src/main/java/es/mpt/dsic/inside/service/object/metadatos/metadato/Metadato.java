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

package es.mpt.dsic.inside.service.object.metadatos.metadato;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.ClassUtils;

import es.mpt.dsic.inside.service.object.metadatos.metadato.exception.InsideServiceMetadatoValueExtractionException;
import es.mpt.dsic.inside.service.object.metadatos.validator.MetatadatoValidator;
import es.mpt.dsic.inside.service.object.metadatos.validator.Exception.InSideServiceMetadatoValidationException;
import es.mpt.dsic.inside.service.object.metadatos.validator.Exception.InSideServiceValidationException;
import es.mpt.dsic.inside.service.object.metadatos.validator.Exception.InsideServiceMetadatoValueRequiredException;
import es.mpt.dsic.inside.service.object.validators.impl.InsideMetadatoValidatorDataBase;
import es.mpt.dsic.inside.service.object.validators.impl.InsideMetadatoValidatorService;

/**
 * Clase para exponer las propiedades sobre un determinado metadato
 * @author miguel.ortega
 *
 */
public abstract class Metadato<T> {
	
	private String nombre;

	private boolean almacenable;

	private boolean modificable;

	private boolean requerido;
	
	private boolean nullable = false;
	
	private MetatadatoValidator<T> validator;

	private T default_value;
	
	private Class<?> implementedType;
	
	private InsideMetadatoValidatorDataBase validatorDataBase;
	
	private InsideMetadatoValidatorService validatorService;
	
	private String dependsValidation;
	
	protected static final Log logger = LogFactory.getLog(Metadato.class);
	

	@Required
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Required
	public void setAlmacenable(boolean almacenable) {
		this.almacenable = almacenable;
	}
	@Required
	public void setModificable(boolean modificable) {
		this.modificable = modificable;
	}
	@Required
	public void setRequerido(boolean requerido) {
		this.requerido = requerido;
	}

	public void setValidator(MetatadatoValidator<T> validator) throws InsideServiceMetadatoValueExtractionException {
		this.implementedType = getConcreteType(this);
		Class<?> validatorType = getConcreteType(validator);
		if(implementedType != validatorType){
			throw new InsideServiceMetadatoValueExtractionException("El tipo de datos que implementa el validador '" + validatorType + "' no es complatible con el tipo de datos asignado a este metadato '" + implementedType + "'");
		}
		this.validator = validator;
		logger.debug("Establecido validador " + validator.getClass() + " de tipo " + validatorType + " para metadato " + this.getNombre());
	}
	
	public void setDefaultValue(T default_value) {
		this.default_value = default_value;
	}

	public boolean isAlmacenable() {
		return almacenable;
	}

	public boolean isModificable() {
		return modificable;
	}

	public boolean isRequerido() {
		return requerido;
	}

	public T getDefaultValue() {
		return default_value;
	}

	public String getNombre() {
		return nombre;
	}

	/**
	 * Valida un valor para este metadato
	 * @param value
	 * @return
	 * @throws InSideServiceMetadatoValidationException
	 * @throws InsideServiceMetadatoValueExtractionException
	 */
	@SuppressWarnings("unchecked")
	public T clean (Object value) throws InSideServiceMetadatoValidationException, InsideServiceMetadatoValueExtractionException{
		try {
			if(this.isRequerido() && !this.isNullable() && value == null){
				throw new InsideServiceMetadatoValueRequiredException(this);
			}
			checkIfValueIsAceptableForMe(value);
			return value == null ? null : this.validator.clean((T) value);
		} catch (InsideServiceMetadatoValueExtractionException e) {
			throw e;
		} catch (InSideServiceValidationException e) {
			throw new InSideServiceMetadatoValidationException(this,e);	
		}
		
	}
	
	public boolean isNullable() {
		return nullable;
	}
	
	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}
	
	
	protected void checkIfValueIsAceptableForMe(Object value) throws InSideServiceMetadatoValidationException, InsideServiceMetadatoValueExtractionException{
		Class<?> metadatoConcreteType = getConcreteType(this);
		if(!ClassUtils.isAssignableValue(metadatoConcreteType, value)){
			throw new InSideServiceMetadatoValidationException(this,"Valor no válido para el parámetro " + this.getNombre(),new Error("El tipo del valor de la propiedad " + this.getNombre() + " (" + value.getClass() + " ) no es compatible con la definición del metadato (" + metadatoConcreteType + ")"));
		}
	}
	
	/**
	 * Devuelve el Tipo T con el que se ha instanciado
	 * un objeto parametrizado
	 * 
	 * @return
	 * @throws InsideServiceMetadatoValueExtractionException 
	 */
	public static Class<?> getConcreteType(Object objeto) throws InsideServiceMetadatoValueExtractionException
	{
		Type[] genericInterfaces = objeto.getClass().getGenericInterfaces();
		Type type = null;
		if(genericInterfaces.length > 0){
			type = genericInterfaces[0];
		}else{
			type = objeto.getClass().getGenericSuperclass();
		}
		
		if(type instanceof ParameterizedType){
			//ParameterizedType parameterizedtype = (ParameterizedType) objeto.getClass().getGenericSuperclass();
			type = ((ParameterizedType) type).getActualTypeArguments()[0];
		}else{
			throw new InsideServiceMetadatoValueExtractionException("Error obteniendo el tipo concreto del Objeto : type no es un 'ParameterizedType' es un "+ type.getClass());
		}
		
		
		if(type instanceof ParameterizedType){
			type = ((ParameterizedType) type).getRawType();
		}
		if(type.getClass() != java.lang.Class.class){
			throw new InsideServiceMetadatoValueExtractionException("Error obteniendo el tipo concreto del Objeto : type no es un 'Class' es un "+ type.getClass());
		}
		return (Class<?>) type;
	}

	public MetatadatoValidator<T> getValidator() {
		return validator;
	}
	public InsideMetadatoValidatorDataBase getValidatorDataBase() {
		return validatorDataBase;
	}

	public void setValidatorDataBase(
			InsideMetadatoValidatorDataBase validatorDataBase) {
		this.validatorDataBase = validatorDataBase;
	}
	
	public InsideMetadatoValidatorService getValidatorService() {
		return validatorService;
	}
	
	public void setValidatorService(InsideMetadatoValidatorService validatorService) {
		this.validatorService = validatorService;
	}
	
	public String getDependsValidation() {
		return dependsValidation;
	}
	
	public void setDependsValidation(String dependsValidation) {
		this.dependsValidation = dependsValidation;
	}
	
}
