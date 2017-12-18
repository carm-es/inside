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

public class ObjetoInsideRol {
	
	private int id;
	private String descripcion;
	private String descripcionLarga;
	private String descripcionLargaTraducida;
	

	public ObjetoInsideRol() {
		
	}
	
	public ObjetoInsideRol(int idRol) {
		this.id=idRol;
		
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripcionLarga() {
		return descripcionLarga;
	}
	public void setDescripcionLarga(String descripcionLarga) {
		this.descripcionLarga = descripcionLarga;
	}
	public String getDescripcionLargaTraducida() {
		return descripcionLargaTraducida;
	}
	public void setDescripcionLargaTraducida(String descripcionLargaTraducida) {
		this.descripcionLargaTraducida = descripcionLargaTraducida;
	}

	
}
