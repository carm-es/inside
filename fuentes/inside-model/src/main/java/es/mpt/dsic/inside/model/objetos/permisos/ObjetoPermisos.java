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

package es.mpt.dsic.inside.model.objetos.permisos;

public class ObjetoPermisos {

	private boolean lectura;
	private boolean escritura;
	
	public ObjetoPermisos(boolean lectura, boolean escritura) {
		this.lectura = lectura;
		this.escritura = escritura;
	}

	/**
	 * @return the lectura
	 */
	public boolean isLectura() {
		return lectura;
	}

	/**
	 * @param lectura
	 *            the lectura to set
	 */
	public void setLectura(boolean lectura) {
		this.lectura = lectura;
	}

	/**
	 * @return the escritura
	 */
	public boolean isEscritura() {
		return escritura;
	}

	/**
	 * @param escritura
	 *            the escritura to set
	 */
	public void setEscritura(boolean escritura) {
		this.escritura = escritura;
	}

}
