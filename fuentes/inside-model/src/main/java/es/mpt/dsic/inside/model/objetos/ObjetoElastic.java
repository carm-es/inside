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

import java.util.Map;

public class ObjetoElastic {
	
	private String tipoObjeto;
	private String identificador;
	private Map<String, String> metadatos;
	private Map<String, String> metadatosAdicionales;
	
	public String getTipoObjeto() {
		return tipoObjeto;
	}
	public void setTipoObjeto(String tipoObjeto) {
		this.tipoObjeto = tipoObjeto;
	}
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public Map<String, String> getMetadatos() {
		return metadatos;
	}
	public void setMetadatos(Map<String, String> metadatos) {
		this.metadatos = metadatos;
	}
	public Map<String, String> getMetadatosAdicionales() {
		return metadatosAdicionales;
	}
	public void setMetadatosAdicionales(Map<String, String> metadatosAdicionales) {
		this.metadatosAdicionales = metadatosAdicionales;
	}
	
	public String getCadenaMetadatos() {
		String resultado = "";
		for (Map.Entry<String, String> entry : getMetadatos().entrySet())
		{
		    resultado += " " + entry.getValue() + " ";
		}
		return resultado;
	}
	
	public String getCadenaMetadatosJson() {
		String resultado = "{";
		for (Map.Entry<String, String> entry : getMetadatos().entrySet())
		{
		    resultado += "\"" + entry.getKey() + "\" : \"" + entry.getValue() + "\" ,";
		}
		return resultado.substring(0, resultado.length() - 1) + "}";
	}
	
	public String getCadenaMetadatosAdicionales() {
		String resultado = "";
		for (Map.Entry<String, String> entry : getMetadatosAdicionales().entrySet())
		{
		    resultado += " " + entry.getValue() + " ";
		}
		return resultado;
	}
	
	public String getCadenaMetadatosAdicionalesJson() {
		String resultado = "{";
		for (Map.Entry<String, String> entry : getMetadatosAdicionales().entrySet())
		{
		    resultado += "\"" + entry.getKey() + "\" : \"" + entry.getValue() + "\" ,";
		}
		return getMetadatosAdicionales().size() > 0 ?  resultado.substring(0, resultado.length() - 1) + "}" : resultado + "}";
	}
	
	public String getEnlaceElemento() {
		
		if (tipoObjeto.equals("Expediente")) {
			return "/editarExpediente?identificador=" + this.getIdentificador();
		} else {
			return "/editarDocumento?identificador=" + this.getIdentificador();
		}
	}


}
