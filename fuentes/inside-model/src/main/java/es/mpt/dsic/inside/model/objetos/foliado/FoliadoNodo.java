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

package es.mpt.dsic.inside.model.objetos.foliado;

import java.util.ArrayList;
import java.util.List;

public class FoliadoNodo {

	String nombre;
	
	byte[] contenido;
	String mimeContenido;
	//String nombreFormato;
	
	List<FoliadoNodo> hijos;
	
	FoliadoNodo padre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public byte[] getContenido() {
		return contenido;
	}

	public void setContenido(byte[] contenido) {
		this.contenido = contenido;
	}

	public String getMimeContenido() {
		return mimeContenido;
	}

	public void setMimeContenido(String mimeContenido) {
		this.mimeContenido = mimeContenido;
	}

	public List<FoliadoNodo> getHijos() {		
		return hijos;
	}

	public void setHijos(List<FoliadoNodo> hijos) {
		this.hijos = hijos;
	}

	public FoliadoNodo getPadre() {
		return padre;
	}

	public void setPadre(FoliadoNodo padre) {
		this.padre = padre;
	}
	
	public void addHijo (FoliadoNodo hijo) {
		if (hijos == null) {
			hijos = new ArrayList<FoliadoNodo> ();			
		}
		hijos.add(hijo);
	}
	
	/*public String getNombreFormato() {
		return nombreFormato;
	}

	public void setNombreFormato(String nombreFormato) {
		this.nombreFormato = nombreFormato;
	}
	*/
	
}

