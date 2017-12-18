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

package es.mpt.dsic.inside.service;

import java.util.List;

import es.mpt.dsic.inside.model.busqueda.resultado.ResultadoBusquedaInside;
import es.mpt.dsic.inside.model.objetos.ObjetoInside;
import es.mpt.dsic.inside.service.exception.InSideServiceException;

public interface InSideServicePermisos {

	public <T extends ObjetoInside<?>> void checkPermisos(String identificador, Integer version, Class<T> tipo,
			String idAplicacion) throws InSideServiceException;
	
	public <T extends ObjetoInside<?>> void checkPermisosWithValidateFlags(String identificador, Integer version, Class<T> tipo,
			String idAplicacion, boolean validateFlags) throws InSideServiceException;

	public <T extends ObjetoInside<?>> void checkPermisos(List<String> identificadores, Integer version, Class<T> tipo,
			String idAplicacion) throws InSideServiceException;

	public <T extends ObjetoInside<?>> void checkPermisos(ResultadoBusquedaInside<?> datos, String idAplicacion)
			throws InSideServiceException;

}
