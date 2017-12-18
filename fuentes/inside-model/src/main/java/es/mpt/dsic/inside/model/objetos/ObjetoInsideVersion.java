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

package es.mpt.dsic.inside.model.objetos;

import java.io.Serializable;
import java.util.GregorianCalendar;

import es.mpt.dsic.inside.model.converter.InsideConverterUtils;

public class ObjetoInsideVersion implements Cloneable, Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	protected int version;
	
	protected GregorianCalendar fechaVersion;
	
	protected String remitidoMJU;
	
	public ObjetoInsideVersion(int version,GregorianCalendar fechaVersion) {
		this.version = version;
		this.fechaVersion = fechaVersion;
	}
	
	public String getRemitidoMJU() {
		return remitidoMJU;
	}

	public void setRemitidoMJU(String remitidoMJU) {
		this.remitidoMJU = remitidoMJU;
	}

	public int getVersion() {
		return version;
	}
	
	public GregorianCalendar getFechaVersion() {
		return fechaVersion;
	}

	public static ObjetoInsideVersion createFirstVersion() {
		return new ObjetoInsideVersion(1, new GregorianCalendar());
	}
	
	public static ObjetoInsideVersion createNextVersion(ObjetoInsideVersion version) {
		return new ObjetoInsideVersion(version.getVersion() + 1, new GregorianCalendar());
	}

	@Override
	public Object clone () throws CloneNotSupportedException{
		
		ObjetoInsideVersion objetoInsideVersion = (ObjetoInsideVersion) super.clone();
		objetoInsideVersion.version = this.version;
		objetoInsideVersion.fechaVersion = (GregorianCalendar) fechaVersion.clone();
		
		return objetoInsideVersion;
		
	}
	
	
	
	@Override
	public String toString () {
		StringBuffer sb = new StringBuffer ("ObjetoInsideVersion=[");
		String coma = ", ";
		sb.append("version=" + version + coma);
		sb.append("fechaVersion=" + InsideConverterUtils.calendarToStringISO8601(fechaVersion));
		sb.append("]");
		return sb.toString();
	}
}

