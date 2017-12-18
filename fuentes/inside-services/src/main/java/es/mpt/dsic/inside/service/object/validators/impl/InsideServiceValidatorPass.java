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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.mpt.dsic.inside.model.objetos.ObjetoInside;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideMetadatos;
import es.mpt.dsic.inside.service.exception.InSideServiceException;
import es.mpt.dsic.inside.service.object.validators.InsideServiceObjectValidator;

/**
 * Un validador que siempre funciona
 * @author miguel.ortega
 *
 */
public class InsideServiceValidatorPass <T extends ObjetoInside<K>, K extends ObjetoInsideMetadatos> implements InsideServiceObjectValidator<T,K>{
	
	protected static final Log logger = LogFactory.getLog(InsideServiceObjectValidator.class);

	@Override
	public T validate(T objeto)throws InSideServiceException
	{
		logger.debug("Voy a decir que el objeto " + objeto + " es válido");
		return objeto;
	}

}
