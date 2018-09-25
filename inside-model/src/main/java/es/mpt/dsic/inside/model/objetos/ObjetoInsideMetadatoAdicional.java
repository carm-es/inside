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

package es.mpt.dsic.inside.model.objetos;

import java.io.Serializable;

import javax.xml.namespace.QName;

public class ObjetoInsideMetadatoAdicional implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private String nombre;
	
	private Object valor;
	
	private String tipo;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String qName) {
		this.tipo = qName;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Object getValor() {
		return valor;
	}

	public void setValor(Object valor) {
		this.valor = valor;
	}
	
	@Override
	public String toString () {
		String coma = ", ";
		StringBuffer sb = new StringBuffer ("MetadatoAdicional[");
		sb.append("Nombre=" + nombre + coma);
		sb.append("Valor=" + valor + coma);
		sb.append("Tipo=" + tipo + coma);
		sb.append("]");
		return sb.toString();
	}

}
