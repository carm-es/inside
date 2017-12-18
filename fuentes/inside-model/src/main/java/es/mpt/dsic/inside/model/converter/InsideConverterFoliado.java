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

package es.mpt.dsic.inside.model.converter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.mpt.dsic.inside.model.converter.exception.InsideConverterException;
import es.mpt.dsic.inside.model.converter.exception.InsideConverterUtilsException;
import es.mpt.dsic.inside.model.objetos.foliado.FoliadoInside;
import es.mpt.dsic.inside.xml.eni.documento.contenido.TipoContenido;

public abstract class InsideConverterFoliado {

	protected static final Log logger = LogFactory.getLog(InsideConverterFoliado.class);
	
	public static TipoContenido contenidoFoliadoInsideToEni (FoliadoInside foliadoInside) throws InsideConverterException {
		TipoContenido contenidoEni = new TipoContenido();
		contenidoEni.setValorBinario(foliadoInside.getContenido());
		try {
			contenidoEni.setNombreFormato(InsideConverterUtils.getNombreFormatoByMime(foliadoInside.getMime()));
		} catch (InsideConverterUtilsException e) {
			throw new InsideConverterException("Error obteniendo el nombre del formato desde el mime " + foliadoInside.getMime(),e);
		}
		return contenidoEni;
	}
}
