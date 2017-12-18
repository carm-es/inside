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

package es.mpt.dsic.inside.model.objetos.expediente.indice;

import java.util.List;

import es.mpt.dsic.inside.model.objetos.firmas.FirmaInside;

public class ObjetoExpedienteInsideIndice {

    protected ObjetoExpedienteInsideIndiceContenido indiceContenido;

    protected List<FirmaInside> firmas;

    public ObjetoExpedienteInsideIndiceContenido getIndiceContenido() {
    	if(indiceContenido == null){
    		indiceContenido = new ObjetoExpedienteInsideIndiceContenido();
    	}
        return indiceContenido;
    }

    public void setIndiceContenido(ObjetoExpedienteInsideIndiceContenido indiceContenido) {
        this.indiceContenido = indiceContenido;
    }

    public List<FirmaInside> getFirmas() {
        return firmas;
    }


    public void setFirmas(List<FirmaInside> firmas) {
        this.firmas = firmas;
    }
    
    @Override
    public String toString () {
    	StringBuffer sb = new StringBuffer ("ObjetoExpedienteInsideIndice=[");
    	String coma = ", ";
    	if (indiceContenido == null) {
    		sb.append("IndiceContenido=null" + coma);
    	} else {
    		sb.append("IndiceContenido=" + indiceContenido.toString() + coma);
    	}
    	if (firmas == null) {
    		sb.append("Firmas=null" + coma);
    	} else {
    		sb.append("Firmas=");
    		for (FirmaInside firma : firmas) {
    			sb.append(firma.toString());
    		}
    		sb.append(coma);
    	}
    	sb.append("]");
    	return sb.toString();
    }

}
