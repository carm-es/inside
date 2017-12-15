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

package es.mpt.dsic.inside.service.object.converter.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteInside;
import es.mpt.dsic.inside.model.objetos.expediente.metadatos.ObjetoExpedienteInsideMetadatos;
import es.mpt.dsic.inside.service.object.converter.InsideServiceObjectConverter;
import es.mpt.dsic.inside.service.object.converter.InsideServiceObjectConverterException;
import es.mpt.dsic.inside.service.object.converter.impl.cmis.InsideServiceCmisAdapter;
import es.mpt.dsic.inside.service.object.converter.impl.csvstorage.InsideServiceCsvStorageAdapter;

public class InsideServiceExpedienteConverter
		implements InsideServiceObjectConverter<ObjetoExpedienteInside, ObjetoExpedienteInsideMetadatos> {

	protected InsideServiceCmisAdapter cmisAdapter;
	
	protected InsideServiceCsvStorageAdapter csvStorageAdapter;
	
	protected Boolean cmis;

	protected static final Log logger = LogFactory.getLog(InsideServiceExpedienteConverter.class);

	@Override
	public ObjetoExpedienteInside toRepository(ObjetoExpedienteInside expediente)
			throws InsideServiceObjectConverterException {
		try {
			if (cmis) {
				cmisAdapter.almacenaObjetoExpedienteInside(expediente, null);
			} else {
				csvStorageAdapter.almacenaObjetoExpedienteInside(expediente, null);
			}
		} catch (InsideServiceAdapterException e) {
			throw new InsideServiceObjectConverterException("Error almacenando el Expediente en el repositorio ",
					e);
		}

		return expediente;

	}

	@Override
	public ObjetoExpedienteInside fromRepository(ObjetoExpedienteInside objeto)
			throws InsideServiceObjectConverterException {
		ObjetoExpedienteInside objetoExpedienteInside = null;	
		try {
			if (cmis) {
				objetoExpedienteInside = cmisAdapter.recuperaExpedienteInside(objeto);
			} else {
				objetoExpedienteInside = csvStorageAdapter.recuperaExpedienteInside(objeto);
			}
			
		} catch (InsideServiceAdapterException e) {
			throw new InsideServiceObjectConverterException("Error obteniendo del repositorio el expediente", e);
		}
		return objetoExpedienteInside;
	}

	@Override
	public ObjetoExpedienteInside delete(ObjetoExpedienteInside expediente)
			throws InsideServiceObjectConverterException {
		try {
			if (cmis) {
				cmisAdapter.eliminaExpedienteInside(expediente);
			} else {
				csvStorageAdapter.eliminaExpedienteInside(expediente);
			}
		} catch (InsideServiceAdapterException e) {
			throw new InsideServiceObjectConverterException("Excepción eliminando el expediente del repositorio CSVSTORAGE",e);
		}
		return expediente;
	}

	public InsideServiceCmisAdapter getCmisAdapter() {
		return cmisAdapter;
	}

	public void setCmisAdapter(InsideServiceCmisAdapter cmisAdapter) {
		this.cmisAdapter = cmisAdapter;
	}

	public InsideServiceCsvStorageAdapter getCsvStorageAdapter() {
		return csvStorageAdapter;
	}

	public void setCsvStorageAdapter(
			InsideServiceCsvStorageAdapter csvStorageAdapter) {
		this.csvStorageAdapter = csvStorageAdapter;
	}

	public Boolean getCmis() {
		return cmis;
	}

	public void setCmis(Boolean cmis) {
		this.cmis = cmis;
	}

}
