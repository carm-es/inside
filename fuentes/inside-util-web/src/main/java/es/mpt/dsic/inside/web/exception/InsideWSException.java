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

package es.mpt.dsic.inside.web.exception;

import es.mpt.dsic.inside.service.exception.InSideServiceException;

public abstract class InsideWSException extends Exception {

	private static final long serialVersionUID = -7118686767313851664L;

	private boolean reportable;

	public InsideWSException(String mensaje,boolean reportable){
		super(mensaje);
	}
	public InsideWSException(String mensaje,Throwable ex,boolean reportable){
		super(mensaje,ex);
		this.reportable = reportable;
	}
	
	public InsideWSException(String mensaje,InSideServiceException ex){
		super(mensaje,ex);
		this.reportable = ex.isReportable();
	}
	
	/**
	 * Indica si una excepción del tipo Inside Service puede
	 * ser devuelta al usuario (aplicación) final
	 * @return
	 */
	public boolean isReportable() {
		return reportable;
	}
	
	public void setReportable(boolean reportable) {
		this.reportable = reportable;
	}
	
}
