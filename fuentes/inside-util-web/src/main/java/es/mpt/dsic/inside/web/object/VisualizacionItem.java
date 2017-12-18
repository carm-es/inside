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

import java.util.List;
import java.util.Map.Entry;

public class VisualizacionItem {

	private String nombre;
	private List<Entry<String, String>> propiedades;
	private int profundidad;
	private int [] marcadores;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Entry<String, String>> getPropiedades() {
		return propiedades;
	}
	public void setPropiedades(List<Entry<String, String>> propiedades) {
		this.propiedades = propiedades;
	}
	public int getProfundidad() {
		return profundidad;
	}
	public void setProfundidad(int profundidad) {
		this.profundidad = profundidad;
	}
	public int[] getMarcadores() {
		return marcadores;
	}
	public void setMarcadores(int[] marcadores) {
		this.marcadores = marcadores;
	}
	public String marcadoresToString (boolean ignoreFirst) {
		String ret = "";
		if (marcadores != null) {
			int i = 0;
			if (ignoreFirst) {
				i = 1;
			}
			while (i < marcadores.length) {
				ret = ret + marcadores[i] +  ".";
				i++;
			}
		}
		return ret;
	}
}

