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

package es.mpt.dsic.inside.web.object;


public class OrganoJusticiaComboItem extends ComboItem {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPoblacion;
	private String tipoOrgano;

	public OrganoJusticiaComboItem(String key, String value, String type) {
		super(key, value, type);
	}
	
	public OrganoJusticiaComboItem(String key, String value, String type, String codigoPoblacion, String tipoOrgano) {
		super(key, value, type);
		this.codigoPoblacion = codigoPoblacion;
		this.tipoOrgano = tipoOrgano;
	}

	public String getCodigoPoblacion() {
		return codigoPoblacion;
	}

	public void setCodigoPoblacion(String codigoPoblacion) {
		this.codigoPoblacion = codigoPoblacion;
	}

	public String getTipoOrgano() {
		return tipoOrgano;
	}

	public void setTipoOrgano(String tipoOrgano) {
		this.tipoOrgano = tipoOrgano;
	}

	
}
