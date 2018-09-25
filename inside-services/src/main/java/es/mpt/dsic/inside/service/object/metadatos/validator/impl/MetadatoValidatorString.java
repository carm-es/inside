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

import es.mpt.dsic.inside.service.object.metadatos.validator.MetatadatoValidator;
import es.mpt.dsic.inside.service.object.metadatos.validator.Exception.InSideServiceValidationException;

/**
 * Validador para cadenas de caracteres.
 * Se puede configurar una longitud máxima
 * @author miguel.ortega
 *
 */
public class MetadatoValidatorString implements MetatadatoValidator<String> {

	private int max_size;
	
	@Override
	public String clean(String data) throws InSideServiceValidationException {
		if(max_size > 0 && data.length() > max_size ){
			throw new InSideServiceValidationException("La longitud del valor supera " + max_size + " caracteres");
		}
		return data.trim();
	}

	public int getMaxSize() {
		return max_size;
	}

	public void setMaxSize(int max_size) {
		this.max_size = max_size;
	}

}
