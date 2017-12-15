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

public class ObjetoSolicitudAccesoExpAppUrl {
	
	private Integer id;
	private String dir3Padre;
	private String urlDestinoPeticion;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDir3Padre() {
		return dir3Padre;
	}
	public void setDir3Padre(String dir3Padre) {
		this.dir3Padre = dir3Padre;
	}
	public String getUrlDestinoPeticion() {
		return urlDestinoPeticion;
	}
	public void setUrlDestinoPeticion(String urlDestinoPeticion) {
		this.urlDestinoPeticion = urlDestinoPeticion;
	}

}
