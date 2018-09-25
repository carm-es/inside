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

package es.mpt.dsic.inside.service.store.impl.hibernate.converter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import es.mpt.dsic.inside.model.objetos.ObjectInsideRespuestaEnvioJusticia;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideMetadatoAdicional;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideVersion;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteInside;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndice;
import es.mpt.dsic.inside.model.objetos.expediente.metadatos.ObjetoExpedienteInsideMetadatosEnumeracionEstados;
import es.mpt.dsic.inside.model.objetos.firmas.FirmaInside;
import es.mpt.dsic.inside.service.store.exception.InsideServiceStoreException;
import es.mpt.dsic.inside.store.hibernate.entity.ExpedienteInside;
import es.mpt.dsic.inside.store.hibernate.entity.ExpedienteInsideIndice;
import es.mpt.dsic.inside.store.hibernate.entity.ExpedienteInsideIndiceCarpetaIndizada;
import es.mpt.dsic.inside.store.hibernate.entity.ExpedienteInsideIndiceFirmas;
import es.mpt.dsic.inside.store.hibernate.entity.ExpedienteInsideInteresado;
import es.mpt.dsic.inside.store.hibernate.entity.ExpedienteInsideMetadatosAdicionales;
import es.mpt.dsic.inside.store.hibernate.entity.ExpedienteInsideOrgano;
import es.mpt.dsic.inside.store.hibernate.entity.ExpedienteInsideRespuestaEnvioJusticia;

public class InsideServiceStoreHibernateConverterExpediente
		implements
		InsideServiceStoreHibernateConverter<ObjetoExpedienteInside, ExpedienteInside> {

	@Override
	public ExpedienteInside toEntity(ObjetoExpedienteInside expediente,
			Session session) throws InsideServiceStoreException {
		ExpedienteInside entity = new ExpedienteInside();
		entity.setIdentificador(expediente.getIdentificador());
		entity.setIdentificadorRepositorio(expediente
				.getIdentificadorRepositorio());
		if (expediente.getVersion() != null) {
			if (expediente.getVersion().getFechaVersion() != null) {
				entity.setFechaVersion(expediente.getVersion()
						.getFechaVersion().getTime());
			}
			entity.setVersion(expediente.getVersion().getVersion());

		}

		if (expediente.getFechaBaja() != null) {
			entity.setFechaBaja(expediente.getFechaBaja().getTime());
		}

		if (expediente.getMetadatos() != null) {
			entity.setVersionNti(expediente.getMetadatos().getVersionNTI());
			entity.setClasificacion(expediente.getMetadatos()
					.getClasificacion());
			if (expediente.getMetadatos().getFechaAperturaExpediente() != null) {
				entity.setFechaAperturaExpediente(expediente.getMetadatos()
						.getFechaAperturaExpediente().getTime());
			}
			if (expediente.getMetadatos().getEstado() != null) {
				entity.setEstado(expediente.getMetadatos().getEstado().value());
			}
		}

		Set<ExpedienteInsideOrgano> expedienteInsideOrganos = new HashSet<ExpedienteInsideOrgano>();
		for (String organo : expediente.getMetadatos().getOrgano()) {
			ExpedienteInsideOrgano expedienteInsideOrgano = new ExpedienteInsideOrgano();
			expedienteInsideOrgano.setOrgano(organo);
			expedienteInsideOrgano.setExpedienteInside(entity);
			expedienteInsideOrganos.add(expedienteInsideOrgano);
		}
		entity.setExpedienteInsideOrganos(expedienteInsideOrganos);

		Set<ExpedienteInsideInteresado> expedienteInsideInteresados = new HashSet<ExpedienteInsideInteresado>();
		for (String interesado : expediente.getMetadatos().getInteresado()) {
			ExpedienteInsideInteresado expedienteInsideInteresado = new ExpedienteInsideInteresado();
			expedienteInsideInteresado.setInteresado(interesado);
			expedienteInsideInteresado.setExpedienteInside(entity);
			expedienteInsideInteresados.add(expedienteInsideInteresado);
		}
		entity.setExpedienteInsideInteresados(expedienteInsideInteresados);

		if (expediente.getIndice() != null) {
			if (expediente.getIndice().getFirmas() != null
					&& expediente.getIndice().getFirmas().size() > 0) {
				Set<ExpedienteInsideIndiceFirmas> expedienteInsideIndiceFirmas = new HashSet<ExpedienteInsideIndiceFirmas>();
				for (FirmaInside firma : expediente.getIndice().getFirmas()) {
					ExpedienteInsideIndiceFirmas indiceFirma = new ExpedienteInsideIndiceFirmas();
					indiceFirma.setExpedienteInside(entity);
					try {
						indiceFirma
								.setFirmaInside(InsideServiceStoreHibernateConverterFirma
										.toEntity(firma));
					} catch (InsideServiceStoreHibernateConverterFirmaException e) {
						throw new InsideServiceStoreException(
								"Error convirtiendo FirmaInside a Entidad de Hibernate",
								e);
					}
					expedienteInsideIndiceFirmas.add(indiceFirma);
				}

				entity.setExpedienteInsideIndiceFirmases(expedienteInsideIndiceFirmas);
			}

			ExpedienteInsideIndice entityIndice = InsideServiceStoreHibernateConverterExpedienteIndiceContenido
					.toEntity(expediente.getIndice().getIndiceContenido(),
							session);
			entity.setExpedienteInsideIndice(entityIndice);

		}

		if (expediente.getMetadatos().getMetadatosAdicionales().size() > 0) {
			Set<ExpedienteInsideMetadatosAdicionales> expedienteInsideMetadatosAdicionaleses = new HashSet<ExpedienteInsideMetadatosAdicionales>();
			for (ObjetoInsideMetadatoAdicional metadatoAdicional : expediente
					.getMetadatos().getMetadatosAdicionales()) {
				ExpedienteInsideMetadatosAdicionales metadatoAaditionaEntity = new ExpedienteInsideMetadatosAdicionales();
				metadatoAaditionaEntity.setExpedienteInside(entity);
				metadatoAaditionaEntity
						.setNombre(metadatoAdicional.getNombre());
				metadatoAaditionaEntity.setValor(metadatoAdicional.getValor()
						+ "");
				expedienteInsideMetadatosAdicionaleses
						.add(metadatoAaditionaEntity);
			}

			entity.setExpedienteInsideMetadatosAdicionaleses(expedienteInsideMetadatosAdicionaleses);

		}
		
		
		if (expediente.getObjectInsideRespuestaEnvioJusticiaLista().size() > 0) {
			Set<ExpedienteInsideRespuestaEnvioJusticia> expedienteInsideRespuestaEnvioJusticia = new HashSet<ExpedienteInsideRespuestaEnvioJusticia>();

			for (ObjectInsideRespuestaEnvioJusticia objectInsideRespuestaEnvioJusticia : expediente.getObjectInsideRespuestaEnvioJusticiaLista()) 
			{
				ExpedienteInsideRespuestaEnvioJusticia respuestaEnvioJusticia = new ExpedienteInsideRespuestaEnvioJusticia();
				respuestaEnvioJusticia.setExpedienteInside(entity);
				respuestaEnvioJusticia.setAuditoriaEsb_aplicacion(objectInsideRespuestaEnvioJusticia.getAuditoriaEsb_aplicacion());
				respuestaEnvioJusticia.setAuditoriaEsb_modulo(objectInsideRespuestaEnvioJusticia.getAuditoriaEsb_modulo());
				respuestaEnvioJusticia.setAuditoriaEsb_servicio(objectInsideRespuestaEnvioJusticia.getAuditoriaEsb_servicio());
				respuestaEnvioJusticia.setAuditoriaEsb_marcaTiempo(objectInsideRespuestaEnvioJusticia.getAuditoriaEsb_marcaTiempo());
				respuestaEnvioJusticia.setAck(objectInsideRespuestaEnvioJusticia.getAck());
				respuestaEnvioJusticia.setCodigoEnvio(objectInsideRespuestaEnvioJusticia.getCodigoEnvio());
				respuestaEnvioJusticia.setMensaje(objectInsideRespuestaEnvioJusticia.getMensaje());
				expedienteInsideRespuestaEnvioJusticia.add(respuestaEnvioJusticia);
			}

			entity.setExpedienteInsideRespuestaEnvioJusticia(expedienteInsideRespuestaEnvioJusticia);

		}
				
		return entity;

	}

	@Override
	public ObjetoExpedienteInside toInside(ExpedienteInside entity,
			Session session) throws InsideServiceStoreException {
		ObjetoExpedienteInside expediente = new ObjetoExpedienteInside();

		expediente.setIdentificador(entity.getIdentificador());
		expediente.setIdentificadorRepositorio(entity
				.getIdentificadorRepositorio());

		Calendar fechaVersion = null;
		if (entity.getFechaVersion() != null) {
			fechaVersion = GregorianCalendar.getInstance();
			fechaVersion.setTime(entity.getFechaVersion());
		}
		expediente.setVersion(new ObjetoInsideVersion(entity.getVersion(),
				(GregorianCalendar) fechaVersion));

		Calendar fechaAperturaExpediente = null;
		if (entity.getFechaAperturaExpediente() != null) {
			fechaAperturaExpediente = GregorianCalendar.getInstance();
			fechaAperturaExpediente
					.setTime(entity.getFechaAperturaExpediente());
		}

		expediente.getMetadatos().setFechaAperturaExpediente(
				fechaAperturaExpediente);
		expediente.getMetadatos().setVersionNTI(entity.getVersionNti());
		expediente.getMetadatos().setClasificacion(entity.getClasificacion());
		if (entity.getEstado() != null) {
			expediente.getMetadatos().setEstado(
					ObjetoExpedienteInsideMetadatosEnumeracionEstados
							.fromValue(entity.getEstado()));
		}
		expediente.getMetadatos().setIdentificadorObjetoInside(
				entity.getIdentificador());
		expediente.getMetadatos().setClasificacion(entity.getClasificacion());

		List<String> organosInside = new ArrayList<String>();
		for (ExpedienteInsideOrgano organo : entity
				.getExpedienteInsideOrganos()) {
			organosInside.add(organo.getOrgano());
		}
		expediente.getMetadatos().setOrgano(organosInside);

		List<String> interesadosInside = new ArrayList<String>();
		for (ExpedienteInsideInteresado interesado : entity
				.getExpedienteInsideInteresados()) {
			interesadosInside.add(interesado.getInteresado());
		}
		expediente.getMetadatos().setInteresado(interesadosInside);

		expediente.setIndice(new ObjetoExpedienteInsideIndice());

		List<FirmaInside> firmasInside = new ArrayList<FirmaInside>();
		for (ExpedienteInsideIndiceFirmas entityFirma : entity
				.getExpedienteInsideIndiceFirmases()) {
			firmasInside.add(InsideServiceStoreHibernateConverterFirma
					.toInside(entityFirma.getFirmaInside()));
		}
		expediente.getIndice().setFirmas(firmasInside);

		if (entity.getExpedienteInsideIndice() != null) {
			expediente.getIndice().setIndiceContenido(
					InsideServiceStoreHibernateConverterExpedienteIndiceContenido
							.toInside(entity.getExpedienteInsideIndice()));
		}

		List<ObjetoInsideMetadatoAdicional> metadatosAdicionales = new ArrayList<ObjetoInsideMetadatoAdicional>();

		for (ExpedienteInsideMetadatosAdicionales metadatoAaditionaEntity : entity
				.getExpedienteInsideMetadatosAdicionaleses()) {
			ObjetoInsideMetadatoAdicional metadatosAdicional = new ObjetoInsideMetadatoAdicional();
			metadatosAdicional.setNombre(metadatoAaditionaEntity.getNombre());
			metadatosAdicional.setValor(metadatoAaditionaEntity.getValor());
			metadatosAdicionales.add(metadatosAdicional);
		}
		expediente.getMetadatos().setMetadatosAdicionales(metadatosAdicionales);
		
		
		
		List<ObjectInsideRespuestaEnvioJusticia> respuestaEnvioJusticia = new ArrayList<ObjectInsideRespuestaEnvioJusticia>();

		for (ExpedienteInsideRespuestaEnvioJusticia respuestaEnvioJusticiaEntity : entity.getExpedienteInsideRespuestaEnvioJusticia()) 
		{
			ObjectInsideRespuestaEnvioJusticia objectRespuestaEnvioJusticia = new ObjectInsideRespuestaEnvioJusticia();
			objectRespuestaEnvioJusticia.setAuditoriaEsb_aplicacion(respuestaEnvioJusticiaEntity.getAuditoriaEsb_aplicacion());
			objectRespuestaEnvioJusticia.setAuditoriaEsb_modulo(respuestaEnvioJusticiaEntity.getAuditoriaEsb_modulo());
			objectRespuestaEnvioJusticia.setAuditoriaEsb_servicio(respuestaEnvioJusticiaEntity.getAuditoriaEsb_servicio());
			objectRespuestaEnvioJusticia.setAuditoriaEsb_marcaTiempo(respuestaEnvioJusticiaEntity.getAuditoriaEsb_marcaTiempo());
			objectRespuestaEnvioJusticia.setAck(respuestaEnvioJusticiaEntity.getAck());
			objectRespuestaEnvioJusticia.setCodigoEnvio(respuestaEnvioJusticiaEntity.getCodigoEnvio());
			objectRespuestaEnvioJusticia.setMensaje(respuestaEnvioJusticiaEntity.getMensaje());	
			
			objectRespuestaEnvioJusticia.setCodigoUnidadOrganoRemitente(respuestaEnvioJusticiaEntity.getCodigoUnidadOrganoRemitente());
			
			respuestaEnvioJusticia.add(objectRespuestaEnvioJusticia);
		}
		expediente.setObjectInsideRespuestaEnvioJusticiaLista(respuestaEnvioJusticia);
		

		Calendar fechaBaja = null;
		if (entity.getFechaBaja() != null) {
			fechaBaja = GregorianCalendar.getInstance();
			fechaBaja.setTime(entity.getFechaBaja());
			expediente.setFechaBaja((GregorianCalendar) fechaBaja);
		}

		return expediente;
	}

	@SuppressWarnings("unchecked")
	public List<ObjetoExpedienteInside> getExpedientesAsociados(
			String identificador, Session session)
			throws InsideServiceStoreException {
		Map<Integer, ObjetoExpedienteInside> retorno = new HashMap<Integer, ObjetoExpedienteInside>();

		Criteria crit = session
				.createCriteria(ExpedienteInsideIndice.class);
		crit.add(Restrictions.eq("identificadorExpediente", identificador));
		crit.add(Restrictions.isNotNull("expedienteInside"));

		List<ExpedienteInsideIndice> objetos = (List<ExpedienteInsideIndice>) crit
				.list();

		for (ExpedienteInsideIndice entity : objetos) {

			if (entity.getExpedienteInsideIndice() != null) {
				// si el documento esta vinculado directamente al expediente

				retorno = getExpediente(entity
						.getExpedienteInsideIndice().getId(), session, retorno);

			} else {
				// si el documento esta vinculado a una carpeta dentro del
				// expediente
				retorno = getExpedienteCarpeta(
						entity.getExpedienteInsideIndiceCarpetaIndizada(),
						session, retorno);
			}
		}
		return new ArrayList<ObjetoExpedienteInside>(retorno.values());
	}

	public Map<Integer, ObjetoExpedienteInside> getExpedienteCarpeta(
			ExpedienteInsideIndiceCarpetaIndizada carpeta, Session session,
			Map<Integer, ObjetoExpedienteInside> expedientes)
			throws InsideServiceStoreException {
		Map<Integer, ObjetoExpedienteInside> retorno = expedientes;
		if (carpeta.getExpedienteInsideIndice() != null) {
			retorno = getExpediente(
					carpeta.getExpedienteInsideIndice().getId(), session,
					expedientes);
		} else {
			// buscamos hacia arriba en la estructura de carpetas del expediente
			Criteria critCarpeta = session
					.createCriteria(ExpedienteInsideIndiceCarpetaIndizada.class);
			critCarpeta.add(Restrictions.eq("id", carpeta
					.getExpedienteInsideIndiceCarpetaIndizada().getId()));
			ExpedienteInsideIndiceCarpetaIndizada carpetaPadre = (ExpedienteInsideIndiceCarpetaIndizada) critCarpeta
					.uniqueResult();
			retorno = getExpedienteCarpeta(carpetaPadre, session, expedientes);
		}
		return retorno;
	}

	public Map<Integer, ObjetoExpedienteInside> getExpediente(
			Integer idIndice, Session session,
			Map<Integer, ObjetoExpedienteInside> expedientes)
			throws InsideServiceStoreException {
		Map<Integer, ObjetoExpedienteInside> retorno = expedientes;

		// buscamos el expediente asociado al indice
		Criteria critExpIndice = session.createCriteria(ExpedienteInside.class);
		critExpIndice.add(Restrictions
				.eq("expedienteInsideIndice.id", idIndice));
		ExpedienteInside expedienteIndice = (ExpedienteInside) critExpIndice
				.uniqueResult();

		// buscamos la ultima version del expediente
		Criteria critExpLast = session.createCriteria(ExpedienteInside.class);
		critExpLast.add(Restrictions.eq("identificador",
				expedienteIndice.getIdentificador()));
		critExpLast.addOrder(Order.desc("version"));
		ExpedienteInside expendienteLastVersion = (ExpedienteInside) critExpLast
				.list().iterator().next();

		// si coincide es la version correcta del expediente
		if (expedienteIndice.getId() == expendienteLastVersion.getId()) {
			
			// hay que ver que si el exp esta dado de baja, no se devuelve porque ya no estaria el doc vinculado a el
			if(expendienteLastVersion.getFechaBaja() == null)
			{
				retorno.put(expedienteIndice.getId(),toInside((ExpedienteInside) critExpIndice.uniqueResult(),session));
			}
			
		}
		return retorno;
	}

}
