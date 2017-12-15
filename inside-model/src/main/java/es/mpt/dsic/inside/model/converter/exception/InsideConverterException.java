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

package es.mpt.dsic.inside.model.converter.exception;

public class InsideConverterException extends Exception{
	
	private static final String DESC_PREFIX = " ";

	private static final long serialVersionUID = -4020485130028170415L;
	
	private boolean reportable;

	/**
	 * Error al realizar una conversión entre objetos del modelo de Inside
	 * 
	 * @param descripcion	La descripción del error
	 * @param e				La causa del error
	 */
	public InsideConverterException(String descripcion,Throwable e) {
		super(DESC_PREFIX + descripcion,e);
		if(e instanceof InsideConverterException){
			this.reportable = ((InsideConverterException) e).isReportable();
		} else {
			reportable = false;
		}
	}
	
	/**
	 * Error al realizar una conversión entre objetos del modelo de Inside
	 * 
	 * @param e				La causa del error
	 */
	public InsideConverterException(String descripcion) {
		super(DESC_PREFIX + descripcion);
		reportable = false;
	}
		
	/**
	 * Error al realizar una conversión entre objetos del modelo de Inside
	 * 
	 * @param e				La causa del error
	 */
	public InsideConverterException(Throwable t) {
		super(DESC_PREFIX ,t);
		if(t instanceof InsideConverterException){
			this.reportable = ((InsideConverterException) t).isReportable();
		} else {
			reportable = false;
		}
	}

	/**
	 * Error al realizar una conversión entre objetos del modelo de Inside
	 * 
	 * @param descripcion	La descripción del error
	 * @param reportable	Si el error puede ser notificado (no es de indole interna)
	 */
	public InsideConverterException(String descripcion,boolean reportable) {
		super(DESC_PREFIX + descripcion);
		this.reportable = reportable;
	}
	
	public InsideConverterException (String descripcion, Throwable t, boolean reportable) {
		super (DESC_PREFIX + descripcion, t);
		this.reportable = reportable;
	}

	/**
	 * Indica si el error puede ser notificado (no es de indole interna)
	 * @return
	 */
	public boolean isReportable() {
		return reportable;
	}


}
