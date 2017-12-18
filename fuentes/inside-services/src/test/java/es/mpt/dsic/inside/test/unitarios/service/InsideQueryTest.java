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

package es.mpt.dsic.inside.test.unitarios.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import es.mpt.dsic.inside.model.busqueda.consulta.ConsultaInside;
import es.mpt.dsic.inside.model.busqueda.consulta.ConsultaMetadatoInside;
import es.mpt.dsic.inside.model.busqueda.metadato.MetadatoBusquedaInside;
import es.mpt.dsic.inside.model.busqueda.metadato.MetadatoValorSimpleInside;
import es.mpt.dsic.inside.model.busqueda.metadato.MetadatoValorSimpleInside.TipoBusqueda;
import es.mpt.dsic.inside.model.busqueda.resultado.ResultadoBusquedaInside;
import es.mpt.dsic.inside.model.objetos.ObjetoInside;
import es.mpt.dsic.inside.model.objetos.documento.metadatos.ObjetoDocumentoInsideMetadatos;
import es.mpt.dsic.inside.service.InSideService;
import es.mpt.dsic.inside.test.unitarios.InSideServiceTestBase;

public class InsideQueryTest <T extends ObjetoInside<?>> extends InSideServiceTestBase {
	
	@Autowired
	@Qualifier("insideService")
	private InSideService insideService;
	
	@Test
	public void testBusquedaDocumento() throws Exception {
		
		
		MetadatoValorSimpleInside valorBusq = new MetadatoValorSimpleInside(TipoBusqueda.equal,"EE01");
		MetadatoBusquedaInside metBusq = new MetadatoBusquedaInside("estado_elaboracion",valorBusq);
		ConsultaInside consulta = new ConsultaMetadatoInside(metBusq);
		ResultadoBusquedaInside<ObjetoDocumentoInsideMetadatos> res = insideService.buscarDocumentos(consulta,0,1);
		logger.info("he obtenido " + res.getTotales() + " resultados");
	}
	
	@Test
	public void testBusquedaDocumentoConOrgano() throws Exception 
	{		
		MetadatoValorSimpleInside valorBusq = new MetadatoValorSimpleInside(TipoBusqueda.equal,"organillo1");
		MetadatoBusquedaInside metBusq = new MetadatoBusquedaInside("organo",valorBusq);
		ConsultaInside consulta = new ConsultaMetadatoInside(metBusq);
		ResultadoBusquedaInside<ObjetoDocumentoInsideMetadatos> res = insideService.buscarDocumentos(consulta,0,1);
		logger.info("he obtenido " + res.getTotales() + " resultados");
	}
		
		
}
	


