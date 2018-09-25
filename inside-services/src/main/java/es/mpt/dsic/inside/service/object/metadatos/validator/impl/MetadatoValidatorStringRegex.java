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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Required;

import es.mpt.dsic.inside.service.object.metadatos.validator.MetatadatoValidator;
import es.mpt.dsic.inside.service.object.metadatos.validator.Exception.InSideServiceValidationException;

/**
 * Validador para validar cadenas de caracteres
 * con un patrón regular
 * 
 * @author miguel.ortega
 *
 */
public class MetadatoValidatorStringRegex extends MetadatoValidatorString implements MetatadatoValidator<String> {

	private String expresionRegular;
	
	private Pattern pattern;
	
	@Override
	public String clean(String data) throws InSideServiceValidationException {
		data = super.clean(data);
		Matcher matcher = pattern.matcher(data);
		if(!matcher.matches()){
			throw new InSideServiceValidationException("El dato '" + data +"' no cumple con la expresión regular '" + expresionRegular + "'");
		}
		return data;
		
	}

	public String getExpresionRegular() {
		return expresionRegular;
	}

	@Required
	public void setExpresionRegular(String expresionRegular) {
		pattern = Pattern.compile(expresionRegular);
		this.expresionRegular = expresionRegular;
	}



}
