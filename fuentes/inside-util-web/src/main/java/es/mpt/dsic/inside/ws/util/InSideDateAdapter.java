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

package es.mpt.dsic.inside.ws.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class InSideDateAdapter extends XmlAdapter<String, Date> {

	DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	
	DateFormat dfChrome = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public Date unmarshal(String date) throws Exception {
		return df.parse(date);
	}

	@Override
	public String marshal(Date date) throws Exception {
		return df.format(date);
	}
	
	
	public String marshalChrome(Date date) throws Exception {
		return dfChrome.format(date);
	}
}
