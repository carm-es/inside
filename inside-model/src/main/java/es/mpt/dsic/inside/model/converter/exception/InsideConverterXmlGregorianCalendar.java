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

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class InsideConverterXmlGregorianCalendar {

	public static XMLGregorianCalendar calendarToXmlCalendar(Calendar fecha) 
		throws InsideConverterCalendarToXmlCalendarException
	{
		if(fecha == null){
			return null;
		}
		GregorianCalendar g = new GregorianCalendar();
		g.setTimeInMillis(fecha.getTimeInMillis());
		try {
			XMLGregorianCalendar cal = DatatypeFactory.newInstance().newXMLGregorianCalendar(g);
			return cal;
		} catch (DatatypeConfigurationException e) {
			throw new InsideConverterCalendarToXmlCalendarException(e);
		}
	}
	
	public static Calendar calendarToXmlCalendar(XMLGregorianCalendar fecha) 
	{
		if(fecha == null){
			return null;
		}
		return fecha.toGregorianCalendar();
	}
	
	public static GregorianCalendar calendarToGregorianCalendar (Calendar fecha) {
		if(fecha == null){
			return null;
		}
		GregorianCalendar g = new GregorianCalendar();
		g.setTimeInMillis(fecha.getTimeInMillis());
		return g;
	}

}
