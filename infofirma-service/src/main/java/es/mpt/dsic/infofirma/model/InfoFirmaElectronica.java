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

package es.mpt.dsic.infofirma.model;

import java.util.List;

public class InfoFirmaElectronica {

	// tipo del contenido firmado.
	private TipoContenidoFirmado tipoContenidoFirmado;
	// bytes del contenido firmado.
	private byte[] contenidoFirmado;
	// mime del contenido firmado.
	private String mimeContenidoFirmado;
	
	private List<InfoFirmante> firmantes;
	
	private TipoFirmaFirma tipoFirma;
	
	public TipoContenidoFirmado getTipoContenidoFirmado() {
		return tipoContenidoFirmado;
	}

	public void setTipoContenidoFirmado(TipoContenidoFirmado tipoContenidoFirmado) {
		this.tipoContenidoFirmado = tipoContenidoFirmado;
	}

	public byte[] getContenidoFirmado() {
		return contenidoFirmado;
	}

	public void setContenidoFirmado(byte[] contenidoFirmado) {
		this.contenidoFirmado = contenidoFirmado;
	}

	public List<InfoFirmante> getFirmantes() {
		return firmantes;
	}

	public void setFirmantes(List<InfoFirmante> firmantes) {
		this.firmantes = firmantes;
	}
	public String getMimeContenidoFirmado() {
		return mimeContenidoFirmado;
	}

	public void setMimeContenidoFirmado(String mimeContenidoFirmado) {
		this.mimeContenidoFirmado = mimeContenidoFirmado;
	}

	public TipoFirmaFirma getTipoFirma() {
		return tipoFirma;
	}

	public void setTipoFirma(TipoFirmaFirma tipoFirma) {
		this.tipoFirma = tipoFirma;
	}
	
}
