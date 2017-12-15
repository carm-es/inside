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

package es.mpt.dsic.inside.model.objetos.expediente;

import java.util.ArrayList;
import java.util.List;

import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenidoElementoIndizado;

public class ObjetoEstructuraCarpetaInside {

	private String identificadorEstructura;
	private String codigoUnidadOrganica;
	private String numeroProcedimiento;

	protected List<ObjetoExpedienteInsideIndiceContenidoElementoIndizado> elementosIndizados;

	public List<ObjetoExpedienteInsideIndiceContenidoElementoIndizado> getElementosIndizados() {
		if (elementosIndizados == null) {
			elementosIndizados = new ArrayList<ObjetoExpedienteInsideIndiceContenidoElementoIndizado>();
		}
		return elementosIndizados;
	}

	public void setElementosIndizados(List<ObjetoExpedienteInsideIndiceContenidoElementoIndizado> elementoIndizado) {
		this.elementosIndizados = elementoIndizado;
	}

	public String getIdentificadorEstructura() {
		return identificadorEstructura;
	}

	public void setIdentificadorEstructura(String identificadorEstructura) {
		this.identificadorEstructura = identificadorEstructura;
	}

	public String getCodigoUnidadOrganica() {
		return codigoUnidadOrganica;
	}

	public void setCodigoUnidadOrganica(String codigoUnidadOrganica) {
		this.codigoUnidadOrganica = codigoUnidadOrganica;
	}

	public String getNumeroProcedimiento() {
		return numeroProcedimiento;
	}

	public void setNumeroProcedimiento(String numeroProcedimiento) {
		this.numeroProcedimiento = numeroProcedimiento;
	}

}
