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

package es.mpt.dsic.inside.model.objetos.firmas;

import java.io.Serializable;

import es.mpt.dsic.inside.model.objetos.firmas.contenido.ContenidoFirmaInside;



public class FirmaInside implements Cloneable, Comparable<FirmaInside>, Serializable {

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	protected FirmaInsideTipoFirmaEnum tipoFirma;
    protected ContenidoFirmaInside contenidoFirma;
    protected String identificadorEnDocumento;
    protected int orden;
    protected String ref;
    
    public FirmaInside () {
    	
    }
    public FirmaInside (FirmaInsideTipoFirmaEnum tipoFirma,
    					ContenidoFirmaInside contenidoFirma,
    					String identificadorEnDocumento,
    					int orden) {
    	this.tipoFirma = tipoFirma;
    	this.contenidoFirma = contenidoFirma;
    	this.identificadorEnDocumento = identificadorEnDocumento;
    	this.orden = orden;
    }
    public FirmaInsideTipoFirmaEnum getTipoFirma() {
        return tipoFirma;
    }

    public void setTipoFirma(FirmaInsideTipoFirmaEnum value) {
        this.tipoFirma = value;
    }

    public ContenidoFirmaInside getContenidoFirma() {
        return contenidoFirma;
    }

   
    public void setContenidoFirma(ContenidoFirmaInside value) {
        this.contenidoFirma = value;
    }
    
    public String getIdentificadorEnDocumento() {
		return identificadorEnDocumento;
	}

	public void setIdentificadorEnDocumento(String identificadorEnDocumento) {
		this.identificadorEnDocumento = identificadorEnDocumento;
	}
	
	public int getOrden () {
		return this.orden;
	}
	
	public void setOrden (int orden) {
		this.orden = orden;
	}
    
    public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	
	@Override
    public Object clone () throws CloneNotSupportedException {
    	FirmaInside firmaInside = (FirmaInside) super.clone();
    	firmaInside.tipoFirma = tipoFirma;
    	firmaInside.identificadorEnDocumento = identificadorEnDocumento;
    	firmaInside.orden = orden;
    	if (contenidoFirma != null) {
    		firmaInside.contenidoFirma = (ContenidoFirmaInside) contenidoFirma.clone();
    	}
    	firmaInside.ref = ref;
    	return firmaInside;
    }
    
    /**
     * Devuelve el tipo de firma según el mime a firmar.
     * @param mime
     * @return
     */
    public static FirmaInsideTipoFirmaEnum getTipoFirmaByMimeAFirmar (String mime) {
    	FirmaInsideTipoFirmaEnum tipoFirma = null;
    	if ("application/pdf".contentEquals(mime)) {
    		tipoFirma = FirmaInsideTipoFirmaEnum.TF_06;
    	} else if ("text/xml".contentEquals(mime)) {
    		tipoFirma = FirmaInsideTipoFirmaEnum.TF_03;
    	} else if (mime!=null) {
    		tipoFirma = FirmaInsideTipoFirmaEnum.TF_02;
    	}
    	return tipoFirma;
    }
    
	@Override
	/**
	 * Devuelve >0 si el orden del argumento es menor que el del "this".
	 */
	public int compareTo(FirmaInside firma) {	
		return this.orden - firma.getOrden();
	}
   
	@Override
	public String toString () {
		String coma = ", ";
		StringBuilder sb = new StringBuilder ("FirmaInside=[");
		sb.append("tipoFirma=" + tipoFirma + coma);
		if (contenidoFirma == null) {
			sb.append("contenidoFirma=null" + coma);
		} else {
			sb.append("contenidoFirma=" + contenidoFirma.toString() + coma);
		}
		sb.append("identificadorEnDocumento=" + identificadorEnDocumento + coma);
		sb.append("orden=" + orden + coma);
		sb.append("ref=" + ref + coma);
		sb.append("]");
		return sb.toString();
	}

	

    

}
