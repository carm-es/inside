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

package es.mpt.dsic.inside.model.objetos.firmas.contenido;

public abstract class ContenidoFirmaCertificadoAlmacenableInside extends ContenidoFirmaCertificadoInside{

	protected byte[] valorBinario;
	protected String mime;
	protected String identificadorRepositorio;
	
	public String getIdentificadorRepositorio() {
		return identificadorRepositorio;
	}

	public void setIdentificadorRepositorio(String identificadorRepositorio) {
		this.identificadorRepositorio = identificadorRepositorio;
	}

	public byte[] getValorBinario() {
		return valorBinario;
	}

	public void setValorBinario(byte[] valorBinario) {
		this.valorBinario = valorBinario;
	}

	public String getMime() {
		return mime;
	}

	public void setMime(String mime) {
		this.mime = mime;
	}
	
	@Override
	public String toString() {
		String coma = ", ";
		StringBuffer sb = new StringBuffer ("ContenidoFirmaCertificadoAlmacenableInside=[");
		sb.append("identificadorRepositorio=" + identificadorRepositorio + coma);
		sb.append("mime=" + mime + coma);
		if (valorBinario == null) {
			sb.append("valorBinario=null");
		} else {
			sb.append("valorBinario=[...bytes...]");
		}		
		sb.append("]");
		return sb.toString();
	}


}