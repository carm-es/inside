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

package es.mpt.dsic.inside.model.objetos.firmas.contenido;

import java.io.Serializable;


public abstract class ContenidoFirmaInside implements Cloneable, Serializable{


    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public Object clone () throws CloneNotSupportedException  {
    	if(this instanceof ContenidoFirmaCertificadoContenidoBinarioInside){
    		ContenidoFirmaCertificadoContenidoBinarioInside yo = (ContenidoFirmaCertificadoContenidoBinarioInside) this;
    		ContenidoFirmaCertificadoContenidoBinarioInside ret = new ContenidoFirmaCertificadoContenidoBinarioInside();
    		ret.setIdentificadorRepositorio(yo.getIdentificadorRepositorio());
    		ret.setMime(yo.getMime());
    		ret.setValorBinario(yo.getValorBinario());
    		
    		return ret;
    	}else if(this instanceof ContenidoFirmaCertificadoDsSignatureInside){
    		ContenidoFirmaCertificadoDsSignatureInside yo = (ContenidoFirmaCertificadoDsSignatureInside) this;
    		ContenidoFirmaCertificadoDsSignatureInside ret = new ContenidoFirmaCertificadoDsSignatureInside();
    		ret.setIdentificadorRepositorio(yo.getIdentificadorRepositorio());
    		ret.setMime(yo.getMime());
    		ret.setValorBinario(yo.getValorBinario());
    		
    		return ret;
    	}else if(this instanceof ContenidoFirmaCSVInside){
    		ContenidoFirmaCSVInside ret = new ContenidoFirmaCSVInside();
    		ContenidoFirmaCSVInside yo = (ContenidoFirmaCSVInside) this;
    		ret.setRegulacionGeneracionCSV(yo.getRegulacionGeneracionCSV());
    		ret.setValorCSV(yo.getValorCSV());
    		return ret;
    	}else{
    		throw new CloneNotSupportedException("No se como clonar un contenido de Firma " + this.getClass());
    	}
    	
    }

}