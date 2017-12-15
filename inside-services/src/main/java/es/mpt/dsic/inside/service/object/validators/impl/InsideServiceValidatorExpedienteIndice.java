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

import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import es.mpt.dsic.inside.model.objetos.ObjetoInside;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideMetadatos;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteInside;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndice;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenidoElementoIndizado;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceElementoIndizadoOrdenComparator;
import es.mpt.dsic.inside.service.exception.InSideServiceException;
import es.mpt.dsic.inside.service.exception.InsideServiceInternalException;
import es.mpt.dsic.inside.service.object.validators.InsideServiceObjectValidator;
import es.mpt.dsic.inside.service.object.validators.exception.InsideServiceObjectValidationException;

/**
 * Validador para el índice del expediente
 * @author miguel.ortega
 *
 */
public class InsideServiceValidatorExpedienteIndice  <T extends ObjetoInside<K>, K extends ObjetoInsideMetadatos> implements
		InsideServiceObjectValidator<T,K>{

	@SuppressWarnings("unchecked")
	@Override
	public T validate(T objeto)
		throws InsideServiceObjectValidationException,InSideServiceException 
	{
		if(objeto instanceof ObjetoExpedienteInside){
			ObjetoExpedienteInside expediente = (ObjetoExpedienteInside) objeto;
			if(expediente.getIndice() == null){
				expediente.setIndice(new ObjetoExpedienteInsideIndice());
			}
			if(expediente.getIndice().getIndiceContenido().getFechaIndiceElectronico() == null){
				expediente.getIndice().getIndiceContenido().setFechaIndiceElectronico(GregorianCalendar.getInstance());
			}
			
			//comprobamos que el orden no de los elemetos del indice esté correcto
			List<ObjetoExpedienteInsideIndiceContenidoElementoIndizado> elementosIndice = expediente.getIndice().getIndiceContenido().getElementosIndizados();
			Collections.sort(elementosIndice, new ObjetoExpedienteInsideIndiceElementoIndizadoOrdenComparator());
			if(elementosIndice.size() > 0){
				if(elementosIndice.get(0).getOrden() != 1){
					throw new InsideServiceInternalException("El orden del primer elemento del indice tiene un orden " + elementosIndice.get(0).getOrden() + " distinto a 1");
				}
				ObjetoExpedienteInsideIndiceContenidoElementoIndizado ultimoElemento = elementosIndice.get(elementosIndice.size() - 1);
				if(ultimoElemento.getOrden() != elementosIndice.size()){
					throw new InsideServiceInternalException("El último  elemento del indice tiene un orden " + ultimoElemento.getOrden() + " distinto a " + elementosIndice.size());
				}
			}
			
			//TODO validar orden de los hijos
			
			return (T) expediente;
		}else{
			throw new InsideServiceInternalException("El objeto no es un expediente, es un " + objeto.getClass());
		}
	}

}
