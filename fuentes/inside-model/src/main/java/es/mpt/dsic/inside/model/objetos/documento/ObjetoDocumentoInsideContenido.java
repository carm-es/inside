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

package es.mpt.dsic.inside.model.objetos.documento;

import java.io.Serializable;

public class ObjetoDocumentoInsideContenido implements Cloneable, Serializable{

    //protected Object datosXML;
	
    //protected String referenciaFichero;

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	//TODO Si sale el listado de formatos del NTI, lo cambiamos a ObjetoDocumentoInsideContenidoNombreFormato	
    protected String nombreFormato;
    
    protected String mime;
    
    protected byte[] valorBinario;
    
    protected String identificadorEnDocumento;
    
    protected String referencia;
    

    /*public Object getDatosXML() {
        return datosXML;
    }

    public void setDatosXML(Object value) {
        this.datosXML = value;
    }
    

    public String getReferenciaFichero() {
        return referenciaFichero;
    }

    public void setReferenciaFichero(String value) {
        this.referenciaFichero = value;
    }*/

  

	public byte[] getValorBinario() {
        return valorBinario;
    }

    public void setValorBinario(byte[] value) {
        this.valorBinario = ((byte[]) value);
    }


    public String getNombreFormato() {
        return nombreFormato;
    }

    public void setNombreFormato(String value) {
        this.nombreFormato = value;
    }

    public String getMime() {
		return mime;
	}

	public void setMime(String mime) {
		this.mime = mime;
	}

	
	public String getIdentificadorEnDocumento() {
		return identificadorEnDocumento;
	}

	public void setIdentificadorEnDocumento(String identificadorEnDocumento) {
		this.identificadorEnDocumento = identificadorEnDocumento;
	}
	
	 public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	@Override
	public Object clone () throws CloneNotSupportedException{
		ObjetoDocumentoInsideContenido objetoDocumentoInsideContenido = (ObjetoDocumentoInsideContenido) super.clone();
		objetoDocumentoInsideContenido.nombreFormato = nombreFormato;
		objetoDocumentoInsideContenido.mime = mime;
		objetoDocumentoInsideContenido.identificadorEnDocumento = identificadorEnDocumento;
		objetoDocumentoInsideContenido.referencia = referencia;
		if (valorBinario != null) {
			objetoDocumentoInsideContenido.valorBinario = (byte[]) valorBinario.clone();
		}
		
		return objetoDocumentoInsideContenido;
	}
	
	@Override
	public String toString () {
		StringBuffer sb = new StringBuffer ("ObjetoDocumentoInsideContenido=[");   
		String coma = ", ";
		sb.append("nombreFormato=" + nombreFormato + coma);
		sb.append("mime=" + mime + coma);
		sb.append("identificadorEnDocumento=" + identificadorEnDocumento + coma);
		sb.append("referencia=" + referencia + coma);
		if (valorBinario == null) {
			sb.append("valorBinario=null");
		} else {
			sb.append("valorBinario=[....bytes...]");
		}
		sb.append("]");
		return sb.toString();
	}

}
