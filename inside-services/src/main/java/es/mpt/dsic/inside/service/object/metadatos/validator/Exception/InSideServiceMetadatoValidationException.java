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

package es.mpt.dsic.inside.service.object.metadatos.validator.Exception;

import es.mpt.dsic.inside.service.object.metadatos.metadato.Metadato;

/**
 * Excepción provocada por un error en la validación del 
 * valor de un metadato
 * @author miguel.ortega
 *
 */
public class InSideServiceMetadatoValidationException extends InSideServiceValidationException {

	private static final long serialVersionUID = -3631737487029291828L;
	
	private Metadato<?> metadato;
	
	public InSideServiceMetadatoValidationException(Metadato<?> metadato, String mensaje) {
		super(generateMessage(metadato, mensaje));
		this.metadato = metadato;
	}
	
	public InSideServiceMetadatoValidationException(Metadato<?> metadato, String mensaje,Throwable e) {
		super(generateMessage(metadato, mensaje) + " ; " + e.getMessage(),e);
		this.metadato = metadato;
	}
	
	public InSideServiceMetadatoValidationException(Metadato<?> metadato,Throwable e) {
		super(generateMessage(metadato) + " ; " + e.getMessage(),e);
		this.metadato = metadato;
	}
	
	public Metadato<?> getMetadato(){
		return this.metadato;
	}

	private static String generateMessage(Metadato<?> metadato) {
		return "Error en la validación del metadato " + metadato.getNombre();
	}
	
	private static String generateMessage(Metadato<?> metadato, String mensaje) {
		return generateMessage(metadato) + " : " + mensaje;
	}


}
