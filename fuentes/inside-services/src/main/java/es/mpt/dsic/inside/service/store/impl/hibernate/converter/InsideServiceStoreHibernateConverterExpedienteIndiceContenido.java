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
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenido;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenidoElementoIndizado;
import es.mpt.dsic.inside.service.store.exception.InsideServiceStoreException;
import es.mpt.dsic.inside.store.hibernate.entity.ExpedienteInside;
import es.mpt.dsic.inside.store.hibernate.entity.ExpedienteInsideIndice;
import es.mpt.dsic.inside.store.hibernate.entity.ExpedienteInsideIndiceCarpetaIndizada;
import es.mpt.dsic.inside.store.hibernate.entity.ExpedienteInsideIndiceDocumentoIndizado;

public class InsideServiceStoreHibernateConverterExpedienteIndiceContenido {

	public static ExpedienteInsideIndice toEntity(ObjetoExpedienteInsideIndiceContenido indiceContenido, Session session)
			throws InsideServiceStoreException {
		ExpedienteInsideIndice entity = new ExpedienteInsideIndice();
		if (indiceContenido.getFechaIndiceElectronico() != null) {
			entity.setFechaIndiceElectronico(indiceContenido.getFechaIndiceElectronico().getTime());
		}

		if (indiceContenido.getElementosIndizados() != null) {
			for (ObjetoExpedienteInsideIndiceContenidoElementoIndizado elementoIndizado : indiceContenido
					.getElementosIndizados()) {
				ElementoIndizado.toEntity(elementoIndizado, entity, session);
			}
		}

		return entity;
	}

	public static ObjetoExpedienteInsideIndiceContenido toInside(ExpedienteInsideIndice entity)
			throws InsideServiceStoreException {
		ObjetoExpedienteInsideIndiceContenido indiceContenido = new ObjetoExpedienteInsideIndiceContenido();

		if (entity.getFechaIndiceElectronico() != null) {
			Calendar fechaIndiceElectronico = GregorianCalendar.getInstance();
			fechaIndiceElectronico.setTime(entity.getFechaIndiceElectronico());
			indiceContenido.setFechaIndiceElectronico(fechaIndiceElectronico);
		}

		List<ObjetoExpedienteInsideIndiceContenidoElementoIndizado> elemIndizados = ElementoIndizado
				.elementosIndizadosToInside(entity);

		List<ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado> docsIndizados = ElementoIndizado
				.GetDocsExpedinte(elemIndizados, null);
		Collections.sort(docsIndizados, new Comparator<ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado>() {
			public int compare(ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado one,
					ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado two) {
				if (one.getFechaIncorporacionExpediente()!=null && two.getFechaIncorporacionExpediente()!=null
					&&	one.getFechaIncorporacionExpediente().before(two.getFechaIncorporacionExpediente())) {
					return 0;
				} else {
					return 1;
				}
			}
		});
		
		indiceContenido.setElementosIndizados(elemIndizados);

		return indiceContenido;

	}

	public static class ElementoIndizado {

		public static List<ObjetoExpedienteInsideIndiceContenidoElementoIndizado> elementosIndizadosToInside(
				Object carpetaOExpedienteIndizadoEntity) throws InsideServiceStoreException {
			List<ObjetoExpedienteInsideIndiceContenidoElementoIndizado> elementosIndizados = new ArrayList<ObjetoExpedienteInsideIndiceContenidoElementoIndizado>();

			if (carpetaOExpedienteIndizadoEntity instanceof ExpedienteInsideIndice) {
				ExpedienteInsideIndice expedienteIndizadoEntity = (ExpedienteInsideIndice) carpetaOExpedienteIndizadoEntity;

				if (expedienteIndizadoEntity.getExpedienteInsideIndiceDocumentoIndizados() != null) {
					for (ExpedienteInsideIndiceDocumentoIndizado documentoIndizado : expedienteIndizadoEntity
							.getExpedienteInsideIndiceDocumentoIndizados()) {
						ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado documentoInside = ElementoIndizado
								.documentoToInside(documentoIndizado);
						elementosIndizados.add(documentoInside);
					}
				}

				if (expedienteIndizadoEntity.getExpedienteInsideIndiceCarpetaIndizadas() != null) {
					for (ExpedienteInsideIndiceCarpetaIndizada carpetaIndizada : expedienteIndizadoEntity
							.getExpedienteInsideIndiceCarpetaIndizadas()) {
						ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada carpetaInside = ElementoIndizado
								.carpetaToInside(carpetaIndizada);
						elementosIndizados.add(carpetaInside);
					}
				}

				if (expedienteIndizadoEntity.getExpedienteInsideIndices() != null) {
					for (ExpedienteInsideIndice expedienteIndizado : expedienteIndizadoEntity.getExpedienteInsideIndices()) {
						ObjetoExpedienteInsideIndiceContenido expedienteIndizadoInside = ElementoIndizado
								.expedienteToInside(expedienteIndizado);
						elementosIndizados.add(expedienteIndizadoInside);
					}
				}

			} else if (carpetaOExpedienteIndizadoEntity instanceof ExpedienteInsideIndiceCarpetaIndizada) {
				ExpedienteInsideIndiceCarpetaIndizada carpetaEntity = (ExpedienteInsideIndiceCarpetaIndizada) carpetaOExpedienteIndizadoEntity;

				if (carpetaEntity.getExpedienteInsideIndiceDocumentoIndizados() != null) {
					for (ExpedienteInsideIndiceDocumentoIndizado documentoIndizado : carpetaEntity
							.getExpedienteInsideIndiceDocumentoIndizados()) {
						ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado documentoInside = ElementoIndizado
								.documentoToInside(documentoIndizado);
						elementosIndizados.add(documentoInside);
					}
				}

				if (carpetaEntity.getExpedienteInsideIndiceCarpetaIndizadas() != null) {
					for (ExpedienteInsideIndiceCarpetaIndizada carpetaIndizada : carpetaEntity
							.getExpedienteInsideIndiceCarpetaIndizadas()) {
						ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada carpetaInside = ElementoIndizado
								.carpetaToInside(carpetaIndizada);
						elementosIndizados.add(carpetaInside);
					}
				}

				if (carpetaEntity.getExpedienteInsideIndices() != null) {
					for (ExpedienteInsideIndice expedienteIndizado : carpetaEntity.getExpedienteInsideIndices()) {
						ObjetoExpedienteInsideIndiceContenido expedienteIndizadoInside = ElementoIndizado
								.expedienteToInside(expedienteIndizado);
						elementosIndizados.add(expedienteIndizadoInside);
					}
				}
			} else {
				throw new InsideServiceStoreException("No se como recuperar los elementos indizados de : "
						+ carpetaOExpedienteIndizadoEntity.getClass().getName());
			}

			Collections.sort(elementosIndizados);
			return elementosIndizados;
		}

		public static ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada carpetaToInside(
				ExpedienteInsideIndiceCarpetaIndizada carpetaEntity) throws InsideServiceStoreException {
			ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada carpetaInside = new ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada();

			carpetaInside.setIdentificadorCarpeta(carpetaEntity.getIdentificadorCarpeta());
			carpetaInside.setElementosIndizados(ElementoIndizado.elementosIndizadosToInside(carpetaEntity));
			carpetaInside.setOrden(carpetaEntity.getOrden());

			return carpetaInside;
		}

		public static ObjetoExpedienteInsideIndiceContenido expedienteToInside(ExpedienteInsideIndice expedienteEntity)
				throws InsideServiceStoreException {
			ObjetoExpedienteInsideIndiceContenido expedienteIndizado = new ObjetoExpedienteInsideIndiceContenido();

			Calendar fechaIndice = GregorianCalendar.getInstance();
			fechaIndice.setTime(expedienteEntity.getFechaIndiceElectronico());

			expedienteIndizado.setFechaIndiceElectronico(fechaIndice);
			expedienteIndizado.setOrden(expedienteEntity.getOrden());

			List<ObjetoExpedienteInsideIndiceContenidoElementoIndizado> elementosIndizados = new ArrayList<ObjetoExpedienteInsideIndiceContenidoElementoIndizado>();

			if (expedienteEntity.getExpedienteInside() != null) {
				expedienteIndizado.setTipoAsociacion(ObjetoExpedienteInsideIndiceContenido.TipoAsociacion.VINCULACION);
				elementosIndizados.addAll(ElementoIndizado
						.elementosIndizadosToInside(expedienteEntity.getExpedienteInside().getExpedienteInsideIndice()));
			} else if (StringUtils.isNotBlank(expedienteEntity.getIdentificadorExpediente())) {
				expedienteIndizado.setTipoAsociacion(ObjetoExpedienteInsideIndiceContenido.TipoAsociacion.IMPORTACION);
			}
			expedienteIndizado.setIdentificadorExpedienteAsociado(expedienteEntity.getIdentificadorExpediente());

			elementosIndizados.addAll(ElementoIndizado.elementosIndizadosToInside(expedienteEntity));
			expedienteIndizado.setElementosIndizados(elementosIndizados);

			return expedienteIndizado;
		}

		public static ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado documentoToInside(
				ExpedienteInsideIndiceDocumentoIndizado documentoEntity) {
			ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado documentoIndizadoInside = new ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado();

			Calendar fechaIncorporacionExpediente = null;
			if (documentoEntity.getFechaIncorporacionExpediente() != null) {
				fechaIncorporacionExpediente = GregorianCalendar.getInstance();
				fechaIncorporacionExpediente.setTime(documentoEntity.getFechaIncorporacionExpediente());
			}

			documentoIndizadoInside.setFechaIncorporacionExpediente(fechaIncorporacionExpediente);
			documentoIndizadoInside.setFuncionResumen(documentoEntity.getFuncionResumen());
			documentoIndizadoInside.setIdentificadorDocumento(documentoEntity.getIdentificadorDocumento());
			documentoIndizadoInside.setOrdenDocumentoExpediente(documentoEntity.getOrdenDocumentoExpediente() > -1
					? documentoEntity.getOrdenDocumentoExpediente() : documentoEntity.getOrden());
			documentoIndizadoInside.setOrden(documentoEntity.getOrden());
			documentoIndizadoInside.setValorHuella(documentoEntity.getValorHuella());

			return documentoIndizadoInside;
		}

		public static void toEntity(ObjetoExpedienteInsideIndiceContenidoElementoIndizado elementoIndizado, Object parent,
				Session session) throws InsideServiceStoreException {
			if (elementoIndizado instanceof ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado) {
				ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado documentoIndizado = (ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado) elementoIndizado;
				ExpedienteInsideIndiceDocumentoIndizado documentoEntity = new ExpedienteInsideIndiceDocumentoIndizado();
				if (documentoIndizado.getFechaIncorporacionExpediente() != null) {
					documentoEntity
							.setFechaIncorporacionExpediente(documentoIndizado.getFechaIncorporacionExpediente().getTime());
				}
				documentoEntity.setOrdenDocumentoExpediente(documentoIndizado.getOrdenDocumentoExpediente());
				documentoEntity.setOrden(elementoIndizado.getOrden());
				documentoEntity.setFuncionResumen(documentoIndizado.getFuncionResumen());
				documentoEntity.setValorHuella(documentoIndizado.getValorHuella());
				documentoEntity.setIdentificadorDocumento(documentoIndizado.getIdentificadorDocumento());
				if (parent instanceof ExpedienteInsideIndice) {
					documentoEntity.setExpedienteInsideIndice((ExpedienteInsideIndice) parent);
					((ExpedienteInsideIndice) parent).getExpedienteInsideIndiceDocumentoIndizados().add(documentoEntity);
				} else if (parent instanceof ExpedienteInsideIndiceCarpetaIndizada) {
					documentoEntity.setExpedienteInsideIndiceCarpetaIndizada((ExpedienteInsideIndiceCarpetaIndizada) parent);
					((ExpedienteInsideIndiceCarpetaIndizada) parent).getExpedienteInsideIndiceDocumentoIndizados()
							.add(documentoEntity);
				} else {
					throw new InsideServiceStoreException("Tipo de Padre no soportado: " + parent.getClass().getName());
				}
			} else if (elementoIndizado instanceof ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada) {
				ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada carpetaIndizada = (ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada) elementoIndizado;
				ExpedienteInsideIndiceCarpetaIndizada carpetaEntity = new ExpedienteInsideIndiceCarpetaIndizada();
				carpetaEntity.setIdentificadorCarpeta(carpetaIndizada.getIdentificadorCarpeta());
				carpetaEntity.setOrden(elementoIndizado.getOrden());
				if (parent instanceof ExpedienteInsideIndice) {
					carpetaEntity.setExpedienteInsideIndice((ExpedienteInsideIndice) parent);
					((ExpedienteInsideIndice) parent).getExpedienteInsideIndiceCarpetaIndizadas().add(carpetaEntity);
				} else if (parent instanceof ExpedienteInsideIndiceCarpetaIndizada) {
					carpetaEntity.setExpedienteInsideIndiceCarpetaIndizada((ExpedienteInsideIndiceCarpetaIndizada) parent);
					((ExpedienteInsideIndiceCarpetaIndizada) parent).getExpedienteInsideIndiceCarpetaIndizadas()
							.add(carpetaEntity);
				} else {
					throw new InsideServiceStoreException("Tipo de Padre no soportado: " + parent.getClass().getName());
				}
				for (ObjetoExpedienteInsideIndiceContenidoElementoIndizado hijoIndizado : carpetaIndizada
						.getElementosIndizados()) {
					toEntity(hijoIndizado, carpetaEntity, session);
				}
			} else if (elementoIndizado instanceof ObjetoExpedienteInsideIndiceContenido) {
				ObjetoExpedienteInsideIndiceContenido expedienteIndizado = (ObjetoExpedienteInsideIndiceContenido) elementoIndizado;
				ExpedienteInsideIndice expIndizadoEntity = new ExpedienteInsideIndice();
				expIndizadoEntity.setOrden(elementoIndizado.getOrden());
				if (parent instanceof ExpedienteInsideIndice) {
					expIndizadoEntity.setExpedienteInsideIndice((ExpedienteInsideIndice) parent);
					((ExpedienteInsideIndice) parent).getExpedienteInsideIndices().add(expIndizadoEntity);
				} else if (parent instanceof ExpedienteInsideIndiceCarpetaIndizada) {
					expIndizadoEntity.setExpedienteInsideIndiceCarpetaIndizada((ExpedienteInsideIndiceCarpetaIndizada) parent);
					((ExpedienteInsideIndiceCarpetaIndizada) parent).getExpedienteInsideIndices().add(expIndizadoEntity);
				} else {
					throw new InsideServiceStoreException("Tipo de Padre no soportado: " + parent.getClass().getName());
				}

				if (StringUtils.isNotBlank(expedienteIndizado.getIdentificadorExpedienteAsociado())) {
					expIndizadoEntity.setIdentificadorExpediente(expedienteIndizado.getIdentificadorExpedienteAsociado());
					if (expedienteIndizado
							.getTipoAsociacion() == ObjetoExpedienteInsideIndiceContenido.TipoAsociacion.VINCULACION) {
						Criteria criteria = session.createCriteria(ExpedienteInside.class);
						criteria.add(Restrictions.eq("identificador", expedienteIndizado.getIdentificadorExpedienteAsociado()));
						criteria.addOrder(Order.desc("version"));
						criteria.setMaxResults(1);
						Object expedienteAsociado = criteria.uniqueResult();
						if (expedienteAsociado != null && expedienteAsociado instanceof ExpedienteInside) {
							ExpedienteInside expedienteVinculado = (ExpedienteInside) expedienteAsociado;
							// el setExpedienteInside puede dar lugar a dudas,
							// pero es el método asociado a
							// id_expediente_vinculado
							// TODO hay que guardar el indice completo, si
							// cambia aquí hay que modificarlo en el vinculado
							expIndizadoEntity.setExpedienteInside(expedienteVinculado);
							expIndizadoEntity.setFechaIndiceElectronico(
									expedienteVinculado.getExpedienteInsideIndice().getFechaIndiceElectronico());

							return;
						} else {
							throw new InsideServiceStoreException("No se puede asociar el expediente con identificador "
									+ expedienteIndizado.getIdentificadorExpedienteAsociado() + " porque no existe en Inside");
						}
					} else {
						expIndizadoEntity.setFechaIndiceElectronico(expedienteIndizado.getFechaIndiceElectronico().getTime());
					}

				}

				for (ObjetoExpedienteInsideIndiceContenidoElementoIndizado hijoIndizado : expedienteIndizado
						.getElementosIndizados()) {
					toEntity(hijoIndizado, expIndizadoEntity, session);
				}
			} else {
				throw new InsideServiceStoreException(
						"No se como convertir un " + elementoIndizado.getClass().getName() + " a BBDD");
			}
		}

		public static List<ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado> GetDocsExpedinte(
				List<ObjetoExpedienteInsideIndiceContenidoElementoIndizado> objetos,
				List<ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado> docsIndizados) {
			if (docsIndizados == null) {
				docsIndizados = new ArrayList<ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado>();
			}

			for (ObjetoExpedienteInsideIndiceContenidoElementoIndizado elemento : objetos) {
				if (elemento instanceof ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado) {
					docsIndizados.add((ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado) elemento);
				}
				if (elemento instanceof ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada) {
					GetDocsExpedinte(((ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada) elemento).getElementosIndizados(),
							docsIndizados);
				}
				if (elemento instanceof ObjetoExpedienteInsideIndiceContenido) {
					GetDocsExpedinte(((ObjetoExpedienteInsideIndiceContenido) elemento).getElementosIndizados(), docsIndizados);
				}
			}

			return docsIndizados;
		}
	}

}
