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

package es.mpt.dsic.inside.service.store.exception;

import es.mpt.dsic.inside.service.exception.InSideServiceRequestErrorException;

public class InsideStoreObjectPermisosException extends InSideServiceRequestErrorException {

	private static final long serialVersionUID = -3456408278660900974L;

	public InsideStoreObjectPermisosException(String nombreAplicacion, String identificador) {
		super(generateMessage(nombreAplicacion, identificador,""));
	}
	
	public InsideStoreObjectPermisosException(String nombreAplicacion,String identificador,String message) {
		super(generateMessage(nombreAplicacion, identificador,message));
	}
	
	protected static String generateMessage(String nombreAplicacion,String identificador,String message){
		return "nombreAplicacion:" + nombreAplicacion + " identificador: " + identificador + " " + message;
	}
	
}
