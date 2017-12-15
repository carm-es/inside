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

package es.mpt.dsic.inside.model.objetos.documento.metadatos;

import java.io.Serializable;


public class ObjetoDocumentoInsideMetadatosEstadoElaboracion implements Cloneable, Serializable{

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	protected ObjetoDocumentoInsideMetadatosEstadoElaboracionEnumeracion valorEstadoElaboracion;

    protected String identificadorDocumentoOrigen;

    public ObjetoDocumentoInsideMetadatosEstadoElaboracionEnumeracion getValorEstadoElaboracion() {
        return valorEstadoElaboracion;
    }

    public void setValorEstadoElaboracion(ObjetoDocumentoInsideMetadatosEstadoElaboracionEnumeracion value) {
        this.valorEstadoElaboracion = value;
    }

    public String getIdentificadorDocumentoOrigen() {
        return identificadorDocumentoOrigen;
    }

    public void setIdentificadorDocumentoOrigen(String value) {
        this.identificadorDocumentoOrigen = value;
    }
    
    public Object clone () throws CloneNotSupportedException {
    	ObjetoDocumentoInsideMetadatosEstadoElaboracion estado = (ObjetoDocumentoInsideMetadatosEstadoElaboracion) super.clone();
		
    	estado.identificadorDocumentoOrigen = identificadorDocumentoOrigen;
    	estado.valorEstadoElaboracion = valorEstadoElaboracion;
    	return estado;
    }
    
    @Override
    public String toString () {
    	StringBuffer sb = new StringBuffer ("ObjetoDocumentoInsideMetadatosEstadoElaboracion=[");
    	String coma = ", ";
    	
    	if (valorEstadoElaboracion == null) {
    		sb.append("valorEstadoElaboracion=null" + coma);
    	} else {
    		sb.append(valorEstadoElaboracion.value().toString());
    	}
    	sb.append("identificadorDocumentoOrigen=" + identificadorDocumentoOrigen);
    	
    	sb.append("]");
    	return sb.toString();
    }

}
