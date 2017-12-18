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

import java.util.Calendar;

public class ObjetoExpedienteInsideIndiceContenido extends ObjetoExpedienteInsideIndiceContenidoElementoContenedorElementos{

	public enum TipoAsociacion{
		VINCULACION,
		IMPORTACION
	}
	
	protected Calendar fechaIndiceElectronico;
    
    protected String identificadorExpedienteAsociado;
    
    protected TipoAsociacion tipoAsociacion;
    
	public Calendar getFechaIndiceElectronico() {
        return fechaIndiceElectronico;
    }

    public void setFechaIndiceElectronico(Calendar value) {
        this.fechaIndiceElectronico = value;
    }
    
	public String getIdentificadorExpedienteAsociado() {
		return identificadorExpedienteAsociado;
	}

	public void setIdentificadorExpedienteAsociado(
			String identificadorExpedienteAsociado) {
		this.identificadorExpedienteAsociado = identificadorExpedienteAsociado;
	}

	public TipoAsociacion getTipoAsociacion() {
		return tipoAsociacion;
	}

	public void setTipoAsociacion(TipoAsociacion tipoAsociacion) {
		this.tipoAsociacion = tipoAsociacion;
	}
	
	@Override
	public String toString () {		
		StringBuffer sb = new StringBuffer ("ObjetoExpedienteInsideIndiceContenido=[");
		String coma = ", ";
		sb.append("fechaIndiceElectronico=" + fechaIndiceElectronico + coma);
		sb.append("identificadorExpedienteAsociado=" + identificadorExpedienteAsociado + coma);
		sb.append("tipoAsociacion=" + tipoAsociacion + coma);		
		sb.append(super.toString() + coma);
		sb.append("]");
		return sb.toString();
	}
	

}
