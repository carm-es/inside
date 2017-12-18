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

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import es.mpt.dsic.inside.model.converter.InsideConverterUtils;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideMetadatos;

public class ObjetoDocumentoInsideMetadatos extends ObjetoInsideMetadatos{

	protected String versionNTI;

    protected List<String> organo;

    protected GregorianCalendar fechaCaptura;

    protected boolean origenCiudadanoAdministracion;
    
    protected ObjetoDocumentoInsideMetadatosEstadoElaboracion estadoElaboracion;

    protected ObjetoDocumentoInsideMetadatosTipoDocumental tipoDocumental;

    public String getVersionNTI() {
        return versionNTI;
    }

    public void setVersionNTI(String value) {
        this.versionNTI = value;
    }

    public List<String> getOrgano() {
    	if(this.organo == null){
    		this.organo = new ArrayList<String>();
    	}
        return this.organo;
    }
    
    public void setOrgano(List<String> organos) {
    	this.organo = organos;
    }

    public GregorianCalendar getFechaCaptura() {
        return fechaCaptura;
    }

    public void setFechaCaptura(GregorianCalendar value) {
        this.fechaCaptura = value;
    }

    public boolean isOrigenCiudadanoAdministracion() {
        return origenCiudadanoAdministracion;
    }

    public void setOrigenCiudadanoAdministracion(boolean value) {
        this.origenCiudadanoAdministracion = value;
    }

    public ObjetoDocumentoInsideMetadatosEstadoElaboracion getEstadoElaboracion() {
        return estadoElaboracion;
    }

    public void setEstadoElaboracion(ObjetoDocumentoInsideMetadatosEstadoElaboracion value) {
        this.estadoElaboracion = value;
    }

    public ObjetoDocumentoInsideMetadatosTipoDocumental getTipoDocumental() {
        return tipoDocumental;
    }

    public void setTipoDocumental(ObjetoDocumentoInsideMetadatosTipoDocumental value) {
        this.tipoDocumental = value;
    }
    
    @Override
    public Object clone () throws CloneNotSupportedException{
    	ObjetoDocumentoInsideMetadatos objetoDocumentoInsideMetadatos = (ObjetoDocumentoInsideMetadatos) super.clone();
    	objetoDocumentoInsideMetadatos.versionNTI = versionNTI;
    	objetoDocumentoInsideMetadatos.identificadorObjetoInside = identificadorObjetoInside;
    	objetoDocumentoInsideMetadatos.organo = organo;
    	if (fechaCaptura != null) {
    		objetoDocumentoInsideMetadatos.fechaCaptura = (GregorianCalendar) fechaCaptura.clone();
    	}
    	objetoDocumentoInsideMetadatos.origenCiudadanoAdministracion = origenCiudadanoAdministracion;
    	if (estadoElaboracion != null) {
    		objetoDocumentoInsideMetadatos.estadoElaboracion = (ObjetoDocumentoInsideMetadatosEstadoElaboracion) estadoElaboracion.clone();
    	}
    	
    	objetoDocumentoInsideMetadatos.tipoDocumental = tipoDocumental;
    	
    	return objetoDocumentoInsideMetadatos;
    }
    
    @Override
    public String toString () {
    	String coma = ", ";
    	StringBuffer sb = new StringBuffer ("ObjetoDocumentoInsideMetadatos=[");
		sb.append("VersionNTI=" + this.versionNTI + coma);
		sb.append("fechaCaptura=" + InsideConverterUtils.calendarToStringISO8601(this.fechaCaptura) + coma);
		sb.append("origenCiudadanoAdministracion=" + this.origenCiudadanoAdministracion + coma);
		sb.append("organo=" + InsideConverterUtils.listaToString(this.organo) + coma);
		
		if (estadoElaboracion == null) {
			sb.append("estadoElaboracion=null" + coma);
		} else {
			sb.append("estadoElaboracion=" + estadoElaboracion.toString() + coma);
		}
		
		if (tipoDocumental == null) {
			sb.append("tipoDocumental=null");
		} else {
			sb.append("tipoDocumental=" + tipoDocumental.value() + coma);
		}
		
		sb.append(super.toString());
		sb.append("]");
		return sb.toString();
	}
}