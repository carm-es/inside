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
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import es.mpt.dsic.inside.model.objetos.expediente.ObjetoEstructuraCarpetaInside;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenidoElementoIndizado;
import es.mpt.dsic.inside.service.store.exception.InsideServiceStoreException;
import es.mpt.dsic.inside.store.hibernate.entity.EstructuraCarpetaInside;
import es.mpt.dsic.inside.store.hibernate.entity.ExpedienteInsideIndiceCarpetaIndizada;

public class InsideServiceStoreHibernateConverterEstructuraCarpeta {

	public static EstructuraCarpetaInside toEntity(ObjetoEstructuraCarpetaInside objetoEstructuraCarpetaInside)
			throws InsideServiceStoreException {
		EstructuraCarpetaInside entity = new EstructuraCarpetaInside();
		entity.setIdentificadorEstructura(objetoEstructuraCarpetaInside.getIdentificadorEstructura());
		ExpedienteInsideIndiceCarpetaIndizada carpetaDefault = new ExpedienteInsideIndiceCarpetaIndizada(
				objetoEstructuraCarpetaInside.getIdentificadorEstructura(), 0, GregorianCalendar.getInstance().getTime());
		entity.setExpedienteInsideIndiceCarpetaIndizada(carpetaDefault);

		if (CollectionUtils.isNotEmpty(objetoEstructuraCarpetaInside.getElementosIndizados())) {
			for (ObjetoExpedienteInsideIndiceContenidoElementoIndizado objCarpetaIndizado : objetoEstructuraCarpetaInside
					.getElementosIndizados()) {
				carpetaIndizadaToEntity(objCarpetaIndizado, carpetaDefault);
			}
		}

		return entity;
	}

	public static ObjetoEstructuraCarpetaInside toInside(EstructuraCarpetaInside entity) throws InsideServiceStoreException {
		ObjetoEstructuraCarpetaInside indiceContenido = new ObjetoEstructuraCarpetaInside();

		List<ObjetoExpedienteInsideIndiceContenidoElementoIndizado> elemIndizados = carpetaIndizadaToInside(
				entity.getExpedienteInsideIndiceCarpetaIndizada());
		indiceContenido.setIdentificadorEstructura(entity.getIdentificadorEstructura());

		if (entity.getUnidad() != null)
			indiceContenido.setCodigoUnidadOrganica(entity.getUnidad().getCodigoUnidadOrganica());
		if (entity.getNumeroProcedimiento() != null)
			indiceContenido.setNumeroProcedimiento(entity.getNumeroProcedimiento().getNumProcedimiento());

		Collections.sort(elemIndizados);
		indiceContenido.setElementosIndizados(elemIndizados);

		return indiceContenido;
	}

	public static void carpetaIndizadaToEntity(ObjetoExpedienteInsideIndiceContenidoElementoIndizado objElementoIndizado,
			ExpedienteInsideIndiceCarpetaIndizada parent) throws InsideServiceStoreException {
		ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada objCarpetaIndizada = (ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada) objElementoIndizado;
		ExpedienteInsideIndiceCarpetaIndizada carpetaEntity = new ExpedienteInsideIndiceCarpetaIndizada();
		carpetaEntity.setIdentificadorCarpeta(objCarpetaIndizada.getIdentificadorCarpeta());
		carpetaEntity.setOrden(objCarpetaIndizada.getOrden());

		carpetaEntity.setExpedienteInsideIndiceCarpetaIndizada(parent);
		parent.getExpedienteInsideIndiceCarpetaIndizadas().add(carpetaEntity);

		for (ObjetoExpedienteInsideIndiceContenidoElementoIndizado hijoIndizado : objCarpetaIndizada.getElementosIndizados()) {
			carpetaIndizadaToEntity(hijoIndizado, carpetaEntity);
		}
	}

	public static List<ObjetoExpedienteInsideIndiceContenidoElementoIndizado> carpetaIndizadaToInside(
			ExpedienteInsideIndiceCarpetaIndizada carpetaEntity) throws InsideServiceStoreException {
		List<ObjetoExpedienteInsideIndiceContenidoElementoIndizado> elementosIndizados = new ArrayList<ObjetoExpedienteInsideIndiceContenidoElementoIndizado>();

		if (carpetaEntity.getExpedienteInsideIndiceCarpetaIndizadas() != null) {
			for (ExpedienteInsideIndiceCarpetaIndizada carpetaIndizada : carpetaEntity
					.getExpedienteInsideIndiceCarpetaIndizadas()) {
				ObjetoExpedienteInsideIndiceContenidoElementoIndizado carpetaInside = carpetaToInside(carpetaIndizada);
				elementosIndizados.add(carpetaInside);
			}
		}
		return elementosIndizados;
	}

	public static ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada carpetaToInside(
			ExpedienteInsideIndiceCarpetaIndizada carpetaEntity) throws InsideServiceStoreException {
		ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada carpetaInside = new ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada();

		carpetaInside.setIdentificadorCarpeta(carpetaEntity.getIdentificadorCarpeta());
		carpetaInside.setElementosIndizados(carpetaIndizadaToInside(carpetaEntity));
		carpetaInside.setOrden(carpetaEntity.getOrden());

		return carpetaInside;
	}
}
