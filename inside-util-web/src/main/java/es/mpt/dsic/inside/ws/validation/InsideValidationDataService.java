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

package es.mpt.dsic.inside.ws.validation;

import es.mpt.dsic.inside.ws.validation.exception.InsideValidationDataException;
import es.mpt.dsic.inside.xml.inside.ws.documento.conversion.TipoDocumentoConversionInside;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoExpedienteConversionInside;
import es.mpt.dsic.inside.xml.inside.ws.validacion.documento.TipoDocumentoValidacionInside;
import es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.TipoExpedienteValidacionInside;
import es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.TipoDocumentoVisualizacionInside;

/**
 * Interfaz para validar objetos.
 * @author rus
 *
 */
public interface InsideValidationDataService {

	/**
	 * Valida un objeto TipoDocumentoConversionInside
	 * @param documento documento que desea validarse
	 * @param fillEmptyFields indica si se desea que se rellenen los campos vacíos con el valor por defecto
	 * @return El objeto validado y con los campos rellenos (en caso de así se haya indicado).
	 * @throws InsideValidationDataException si existe algún error en la validación
	 */
	public TipoDocumentoConversionInside validaTipoDocumentoConversionInside (TipoDocumentoConversionInside documento, boolean fillEmptyFields) throws InsideValidationDataException;
	
	
	/**
	 * Valida un objeto TipoExpedienteConversionInside
	 * @param expediente expediente que desea validarse
	 * @param fillEmptyFields indica si se desea que se rellenen los campos vacíos con el valor por defecto
	 * @return El objeto validado y con los campos rellenos (en caso de así se haya indicado).
	 * @throws InsideValidationDataException si existe algún error en la validación
	 */	
	public TipoExpedienteConversionInside validaTipoExpedienteConversionInside (TipoExpedienteConversionInside expediente, boolean fillEmptyFields) throws InsideValidationDataException;
	
	
	/**
	 * Valida un tipo TipoDocumentoValidacionInside
	 * @param documento documento que desea validarse
	 * @param fillEmptFileds indica si se desea que se rellenen los campos vacíos con el valor por defecto
	 * @return El objeto validado y con los campos rellenos (en caso de así se haya indicado).
	 * @throws InsideValidationDataException si existe algún error en la validación
	 */
	public TipoDocumentoValidacionInside validaTipoDocumentoValidacionInside (TipoDocumentoValidacionInside documento, boolean fillEmptFileds) throws InsideValidationDataException;
	
	/**
	 * Valida un tipo TipoExpedienteValidacionInside
	 * @param documento documento que desea validarse
	 * @param fillEmptFileds indica si se desea que se rellenen los campos vacíos con el valor por defecto
	 * @return El objeto validado y con los campos rellenos (en caso de así se haya indicado).
	 * @throws InsideValidationDataException si existe algún error en la validación
	 */
	public TipoExpedienteValidacionInside validaTipoExpedienteValidacionInside (TipoExpedienteValidacionInside expediente, boolean fillEmptFileds) throws InsideValidationDataException;
		
	
	/**
	 * Valida un TipoDocumentoVisualizacionInside
	 * @param documento documento que desea validarse
	 * @param fillEmptFileds indica si se desea que se rellenen los campos vacíos con el valor por defecto
	 * @return El objeto validado y con los campos rellenos (en caso de así se haya indicado).
	 * @throws InsideValidationDataException si existe algún error en la validación
	 */
	public TipoDocumentoVisualizacionInside validaTipoDocumentoVisualizacionInside (TipoDocumentoVisualizacionInside documento, boolean fillEmptyFields) throws InsideValidationDataException;
	
}
