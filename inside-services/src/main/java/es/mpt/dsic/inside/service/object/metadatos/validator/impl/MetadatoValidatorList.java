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

package es.mpt.dsic.inside.service.object.metadatos.validator.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import es.mpt.dsic.inside.service.object.metadatos.metadato.exception.InsideServiceMetadatoValueExtractionException;
import es.mpt.dsic.inside.service.object.metadatos.validator.MetatadatoValidator;
import es.mpt.dsic.inside.service.object.metadatos.validator.Exception.InSideServiceValidationException;

/**
 * Validador para metadatos de Listas de elementos
 * Cada elemento de la lista se valida con el validador 
 * elementValidator que es de obligada configuración
 * @author miguel.ortega
 *
 */
public class MetadatoValidatorList implements MetatadatoValidator<List<Object>>
{

	protected  MetatadatoValidator<Object> elementValidator;

	@Override
	public List<Object> clean(List<Object> data)
			throws InSideServiceValidationException, InsideServiceMetadatoValueExtractionException {
		List<Object> cleanedList = new ArrayList<Object>();
		for (Object element : data) {
			cleanedList.add(elementValidator.clean(element));
		}
		return cleanedList;
	}

	public MetatadatoValidator<?> getElementValidator() {
		return elementValidator;
	}

	@Required
	public void setElementValidator(MetatadatoValidator<Object> elementValidator) {
		this.elementValidator = elementValidator;
	}

}
