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

package es.mpt.dsic.integration.cmis.utils;

import org.apache.chemistry.opencmis.commons.exceptions.*;

public class GestionErroresUtils {

		
	public static String getMessageByException (final Exception e) {
		StringBuffer sb = new StringBuffer ("");
		if (e instanceof CmisConnectionException) {
			sb.append("No se puede establecer la conexión");			
		} else if (e instanceof CmisConstraintException) {
			sb.append("Constraint violada");
		} else if (e instanceof CmisContentAlreadyExistsException) {
			sb.append("El contenido ya existe");
		} else if (e instanceof CmisFilterNotValidException) {
			sb.append("El filtro no es valido");
		} else if (e instanceof CmisInvalidArgumentException) {
			sb.append("Alguno de los argumentos no es valido");
		} else if (e instanceof CmisNameConstraintViolationException) {
			sb.append("Nombre de constraint violado");
		} else if (e instanceof CmisNotSupportedException) {
			sb.append("Cmis no soportado");
		} else if (e instanceof CmisObjectNotFoundException) {
			sb.append("Objeto no encontrado");
		} else if (e instanceof CmisPermissionDeniedException) {
			sb.append("Permiso denegado");
		} else if (e instanceof CmisProxyAuthenticationException) {
			sb.append("Error de autenticación en el proxy");
		} else if (e instanceof CmisUnauthorizedException) {
			sb.append("Operación no autorizada");			
		} else if (e instanceof CmisStorageException) {
			sb.append("Error de almacenamiento");
		} else if (e instanceof CmisStreamNotSupportedException) {
			sb.append("Stream no soportado");
		} else if (e instanceof CmisUpdateConflictException) {
			sb.append("Conflicto en actualizacion");
		} else if (e instanceof CmisVersioningException) {
			sb.append("Error de versionado");
		}
		
		return sb.toString();
	}
}
