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

package es.mpt.dsic.inside.service.semanticValidation.impl;

import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInside;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteInside;
import es.mpt.dsic.inside.service.semanticValidation.InsideSemanticValidationService;
import es.mpt.dsic.inside.service.semanticValidation.exception.InsideSemanticValidationException;
import es.mpt.dsic.inside.service.sia.impl.ConsumidorSIA;
import es.mpt.dsic.inside.store.hibernate.entity.UnidadOrganica;

public class InsideSemanticValidationServiceImpl implements InsideSemanticValidationService {

	protected static final Log logger = LogFactory.getLog(InsideSemanticValidationServiceImpl.class);
	
	private SessionFactory sessionFactory;
	
	private ConsumidorSIA consumidorSIA;
	
	private String clasificacionPattern;
	
	@Override
	public void validarUnidadOrganica (ObjetoDocumentoInside documentoInside) throws InsideSemanticValidationException {
		String validate = validateOrganos(documentoInside.getMetadatos().getOrgano());
		if (!validate.equals("")) {
			throw new InsideSemanticValidationException("Los datos: " + documentoInside.getMetadatos().getOrgano() +  " no encuentran correspondencias en las unidades organicas del DIR3");
		}
	}
	
	@Override
	public void validarUnidadOrganica (ObjetoExpedienteInside expedienteInside) throws InsideSemanticValidationException {
		String validate = validateOrganos(expedienteInside.getMetadatos().getOrgano());
		if (!validate.equals("")) {
			throw new InsideSemanticValidationException("Los datos: " + expedienteInside.getMetadatos().getOrgano() +  " no encuentran correspondencias en las unidades organicas del DIR3");
		}
	}
	
	@Override
	public void validarClasificacion (ObjetoExpedienteInside expedienteInside) throws InsideSemanticValidationException {
		boolean validate = validateClasificacion(expedienteInside.getMetadatos().getClasificacion(), expedienteInside.getMetadatos().getFechaAperturaExpediente());
		if (!validate) {
			throw new InsideSemanticValidationException("Los datos: " + expedienteInside.getMetadatos().getClasificacion() +  " no encuentra correspondencia en SIA ni cumple con la expresión regular '" + clasificacionPattern + "'");
		}
	}
	
	private String validateOrganos(List<String> organos) {
		Session session = null;
		StringBuffer tmpBuff = new StringBuffer("");
		try {
			session = sessionFactory.openSession();
			if (organos != null) {
				for (String organo : organos) {
					Criteria crit = session.createCriteria(UnidadOrganica.class);
					crit.add(Restrictions.eq("codigoUnidadOrganica", organo));
					List<?> objetos = (List<?>) crit.list();
					if (objetos.size() < 1) {
						if (!tmpBuff.toString().equals("")) {
							tmpBuff.append(",");
						}
						tmpBuff.append(organo);
					}
				}
			}
		} finally {
			session.close();
		}
		return tmpBuff.toString();
	}
	
	private boolean validateClasificacion(String clasificacion, Calendar fecha) {
		boolean retorno = true;
		Integer anio = fecha.get(Calendar.YEAR);
		Session session = null;
		boolean exists = consumidorSIA.existClasificacion(clasificacion, anio.toString());
		try {
			if (!exists) {
				logger.debug("Valor de clasificación no corresponde en SIA");
				Pattern pattern = Pattern.compile(clasificacionPattern);
				Matcher matcher = pattern.matcher(clasificacion);
				if(!matcher.matches()){
					retorno = false;
				} else {
					session = sessionFactory.openSession();
					Criteria criteria = session
						.createCriteria(UnidadOrganica.class);
					StringTokenizer tmpToken = new StringTokenizer(clasificacion, "_PRO_");
					String data = (String) tmpToken.nextElement();
					criteria.add(Restrictions.eq("codigoUnidadOrganica", data));
					Object value = criteria.uniqueResult();
					if (value == null) {
						retorno = false;
					}
				}
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return retorno;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public ConsumidorSIA getConsumidorSIA() {
		return consumidorSIA;
	}

	public void setConsumidorSIA(ConsumidorSIA consumidorSIA) {
		this.consumidorSIA = consumidorSIA;
	}

	public String getClasificacionPattern() {
		return clasificacionPattern;
	}

	public void setClasificacionPattern(String clasificacionPattern) {
		this.clasificacionPattern = clasificacionPattern;
	}
	
}
