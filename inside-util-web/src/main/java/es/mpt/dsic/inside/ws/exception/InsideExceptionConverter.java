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

package es.mpt.dsic.inside.ws.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.mpt.dsic.inside.model.converter.exception.InsideConverterException;
import es.mpt.dsic.inside.service.exception.InSideServiceException;
import es.mpt.dsic.inside.ws.validation.exception.InsideValidationDataException;

public class InsideExceptionConverter {

	protected static final Log logger = LogFactory.getLog(InsideExceptionConverter.class);
	
	public static InsideWSException convertToException(Exception e)
	{
		logger.info("Error de tipo " + e.getClass().getSimpleName() + " procesando petición en Inside : " + e.getMessage(),e);
		
		InsideWSException result;
		if(e instanceof InsideWSException){			
			result = (InsideWSException) e;
			logger.warn(construyeMensaje(result), result);
		}else if(e instanceof InSideServiceException){			
			result = new InsideWSException((InSideServiceException)e);
			logger.warn(construyeMensaje(result), result);
		}else if (e instanceof InsideValidationDataException) {			
			result = new InsideWSException((InsideValidationDataException)e);
		} else if (e instanceof InsideConverterException) {
			result = new InsideWSException ((InsideConverterException) e);
		}else{
			result =  new InsideWSException(InsideWsErrors.INTERNAL_SERVICE_ERROR,e);
			logger.error(construyeMensaje(result), result);
		}
		
		return result;
	}
	
	/**
	 * Construye un mensaje descriptivo del error a partir de una excepción.
	 * @param e
	 * @return
	 */
	private static String construyeMensaje (InsideWSException e) {
				
		String ls = System.getProperty("line.separator");
		
		String mensaje = "CODIGO: " + e.getFaultInfo().getCodigo() + ls;
		mensaje += "DESCRIPCION: " + e.getFaultInfo().getDescripcion() + ls;
		
		return mensaje;
		
	}
}
