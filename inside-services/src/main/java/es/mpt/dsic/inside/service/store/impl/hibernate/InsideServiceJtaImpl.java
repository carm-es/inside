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

package es.mpt.dsic.inside.service.store.impl.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.mpt.dsic.inside.service.store.InsideServiceJta;

@Repository("insideServiceJta")
public class InsideServiceJtaImpl implements InsideServiceJta {

	protected final static Log logger = LogFactory.getLog(InsideServiceJtaImpl.class);

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public <T> Object getObjeto(Class<T> bean, Object id) {
		return this.em.find(bean, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public <T> List<T> getAllObjetos(Class<T> bean) {
		Session session = (Session) this.em.getDelegate();
		Criteria crit = session.createCriteria(bean);
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public <T> List<T> getAllObjetosCriteria(Class<T> bean, List<Criterion> criterias) {
		Session session = (Session) this.em.getDelegate();
		Criteria crit = session.createCriteria(bean);
		for (Criterion criteria : criterias) {
			crit.add(criteria);
		}
		return crit.list();
	}

	@Override
	@Transactional(readOnly = true)
	public <T> Object findObjeto(Class<T> bean, List<Criterion> criterias) {
		Session session = (Session) this.em.getDelegate();
		Criteria crit = session.createCriteria(bean);
		for (Criterion criteria : criterias) {
			crit.add(criteria);
		}
		return crit.uniqueResult();
	}
	
	@Override
	@Transactional
	public <T> Object saveOrUpdate(T bean) {
		logger.debug("Inicio createMessage");
		Session session = (Session) this.em.getDelegate();
		session.saveOrUpdate(bean);
		return bean;
	}
	
	@Override
	@Transactional
	public <T> Object delete(T bean) {
		logger.debug("Inicio createMessage");
		Session session = (Session) this.em.getDelegate();
		session.delete(bean);
		return bean;
	}
	
}
