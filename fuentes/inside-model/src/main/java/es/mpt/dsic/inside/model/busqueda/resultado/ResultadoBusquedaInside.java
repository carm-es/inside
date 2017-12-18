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

package es.mpt.dsic.inside.model.busqueda.resultado;

import java.util.List;

import es.mpt.dsic.inside.model.objetos.ObjetoInsideMetadatos;

public class ResultadoBusquedaInside<K extends ObjetoInsideMetadatos> {

	private List<ItemResultadoBusquedaInside<K>> resultados;
	
	private int totales;
	
	private int pagina;

	private int porPagina;
	
//	public ResultadoBusquedaInside(List<ItemResultadoBusquedaInside<K>> resultados, int totales,int pagina, int porPagina) {
//		this.resultados = resultados;
//		this.totales = totales;
//		this.pagina = pagina;
//		this.porPagina = porPagina;
//	}

	public List<ItemResultadoBusquedaInside<K>> getResultados() {
		return resultados;
	}

	public void setResultados(List<ItemResultadoBusquedaInside<K>> resultados) {
		this.resultados = resultados;
	}
	
	public int getTotales() {
		return totales;
	}

	public void setTotales(int totales) {
		this.totales = totales;
	}

	public int getPagina() {
		return pagina;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}

	public int getPorPagina() {
		return porPagina;
	}

	public void setPorPagina(int porPagina) {
		this.porPagina = porPagina;
	}


}
