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

package es.mpt.dsic.inside.service.sia.impl;

import javax.xml.ws.BindingProvider;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.mpt.dsic.inside.service.sia.model.ConstantesSIA;
import es.mpt.dsic.inside.service.sia.model.WsSIAConsultarActuacionesIdentificacion;
import es.mpt.dsic.inside.service.sia.model.WsSIAConsultarActuacionesIdentificacion_Service;
import es.mpt.dsic.inside.service.sia.model.messages.v2_4.enviasia.EnviaSIA;
import es.mpt.dsic.inside.service.sia.model.messages.v2_4.enviasia.EnviaSIA.ACTUACIONES.ACTUACION;
import es.mpt.dsic.inside.service.sia.model.messages.v2_4.paramsia.ParamSIA;
import es.mpt.dsic.inside.service.sia.model.messages.v2_4.paramsia.ParamSIA.Filtro;


public class ConsumidorSIA {
	
	private WsSIAConsultarActuacionesIdentificacion sc;
	private String user;
	private String password;
	private String url;
	
	protected static final Log logger = LogFactory
			.getLog(ConsumidorSIA.class);
	
	public void configure() {
		if (sc == null) {
			WsSIAConsultarActuacionesIdentificacion_Service service1 = new WsSIAConsultarActuacionesIdentificacion_Service();
			sc = service1.getPort(WsSIAConsultarActuacionesIdentificacion.class);
			String endpointURL = url;
	        BindingProvider bp = (BindingProvider)sc;
	        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointURL); 
		}
	}
	
	public boolean existClasificacion(String clasificacion, String anio) {
		logger.debug("Inicio existClasificacion: " + clasificacion + "," + anio);
		configure();
		
		boolean retorno = false;
		ParamSIA params = new ParamSIA();
		params.setUser(user);
		params.setPassword(password);
		Filtro filtro = new Filtro();
		filtro.setTipoActuacion(ConstantesSIA.TIPO_ACTUACION_PROCEDIMIENTO);
		filtro.setAnioVolTramitaciones(anio);
		params.setFiltro(filtro);
		EnviaSIA datos = sc.consultarSIAV24(params);
		if (datos != null
			&& datos.getACTUACIONES() != null
			&& datos.getACTUACIONES().getACTUACION() != null) {
			for (ACTUACION actuacion : datos.getACTUACIONES().getACTUACION()) {
				if (actuacion.getCODIGOACTUACION().equals(clasificacion)) {
					retorno = true;
				}
			}
		}
		logger.debug("Fin existClasificacion:" + retorno); 
		return retorno;
	}

	public WsSIAConsultarActuacionesIdentificacion getSc() {
		return sc;
	}

	public void setSc(WsSIAConsultarActuacionesIdentificacion sc) {
		this.sc = sc;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
