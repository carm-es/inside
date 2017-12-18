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

package es.mpt.dsic.inside.model.converter;

import es.mpt.dsic.inside.model.busqueda.resultado.ItemResultadoBusquedaInside;
import es.mpt.dsic.inside.model.busqueda.resultado.ResultadoBusquedaInside;
import es.mpt.dsic.inside.model.busqueda.resultado.ResultadoBusquedaUsuario;
import es.mpt.dsic.inside.model.converter.exception.InsideConverterException;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideMetadatos;
import es.mpt.dsic.inside.model.objetos.documento.metadatos.ObjetoDocumentoInsideMetadatos;
import es.mpt.dsic.inside.model.objetos.expediente.metadatos.ObjetoExpedienteInsideMetadatos;
import es.mpt.dsic.inside.model.objetos.usuario.ObjetoInsideUsuario;
import es.mpt.dsic.inside.xml.inside.ws.busqueda.DocumentoResultadoBusqueda;
import es.mpt.dsic.inside.xml.inside.ws.busqueda.ExpedienteResultadoBusqueda;
import es.mpt.dsic.inside.xml.inside.ws.busqueda.InfoResultadosBusquedaInside;
import es.mpt.dsic.inside.xml.inside.ws.filter.FilterPageRequestResponse;
import es.mpt.dsic.inside.xml.inside.ws.usuario.Usuario;
import es.mpt.dsic.inside.xml.inside.ws.usuario.UsuarioResultadoBusqueda;

public class InsideConverterBusquedaResultado {

	public static ExpedienteResultadoBusqueda respuestaBusquedaExpedienteInsideToXml(ResultadoBusquedaInside<ObjetoExpedienteInsideMetadatos> res) 
		throws InsideConverterException 
	{

		ExpedienteResultadoBusqueda expRes = new ExpedienteResultadoBusqueda();
		expRes.setPaginador(resultadosInsideToPaginador(res));
		for(ItemResultadoBusquedaInside<ObjetoExpedienteInsideMetadatos> resultado: res.getResultados()){
			ExpedienteResultadoBusqueda.Resultados resXml = new ExpedienteResultadoBusqueda.Resultados();
			resXml.setIdentificador(resultado.getIdentificador());
			//try {
				resXml.setMetadatosExp(InsideConverterMetadatos.Expediente.metadatosInsideToEni(resultado.getMetadatos()));
				resXml.setVersionActual(InsideConverterVersion.versionInsideToXml(resultado.getVersion()));
			/*} catch (InsideConverterMetadatosInsideToEniException e) {
				throw new InsideConverterBusquedaResultadoException("Error convirtiendo metadatos del expediente Inside a Eni",e);
			} catch (InsideConverterVersionInsideException e) {
				throw new InsideConverterBusquedaResultadoException("Error convirtiendo la versión del expediente de Inside a Xml",e);
			}*/
			expRes.getResultados().add(resXml);
		}
		return expRes;
	}


	public static DocumentoResultadoBusqueda respuestaBusquedaDocumentoInsideToXml(ResultadoBusquedaInside<ObjetoDocumentoInsideMetadatos> res) 
		throws InsideConverterException 
	{
		DocumentoResultadoBusqueda docRes = new DocumentoResultadoBusqueda();
		docRes.setPaginador(resultadosInsideToPaginador(res));
		for(ItemResultadoBusquedaInside<ObjetoDocumentoInsideMetadatos> resultado: res.getResultados()){
			DocumentoResultadoBusqueda.Resultados resXml = new DocumentoResultadoBusqueda.Resultados();
			resXml.setIdentificador(resultado.getIdentificador());
			//try {
				resXml.setMetadatos(InsideConverterMetadatos.Documento.metadatosInsideToEni(resultado.getMetadatos()));
			/*} catch (InsideConverterDocumentoMetatosInsideToEniException e) {
				throw new InsideConverterBusquedaResultadoException("Error convirtiendo metadatos del documento Inside a Eni",e);
			} catch (InsideConverterVersionInsideException e) {
				throw new InsideConverterBusquedaResultadoException("Error convirtiendo la versión del documento de Inside a Xml",e);
			}*/
			docRes.getResultados().add(resXml);
		}
		return docRes;
	}
	
	public static <K extends ObjetoInsideMetadatos> InfoResultadosBusquedaInside resultadosInsideToPaginador(
			ResultadoBusquedaInside<K> res) 
	{
		InfoResultadosBusquedaInside infoRes = new InfoResultadosBusquedaInside();
		infoRes.setDevueltos(res.getResultados().size());
		infoRes.setPagina(res.getPagina());
		if (res.getPorPagina() == 0) {
			infoRes.setPaginas(0);
		} else {
			int mod = res.getTotales() % res.getPorPagina();
			if (mod == 0) {
				infoRes.setPaginas(res.getTotales() / res.getPorPagina());
			} else {
				infoRes.setPaginas(res.getTotales() / res.getPorPagina() + 1);
			}
			
		}
		//infoRes.setPaginas(res.getTotales() / res.getPorPagina());
		infoRes.setTotales(res.getTotales());
		return infoRes;
	}
	
	public static UsuarioResultadoBusqueda respuestaBusquedaUsuarioInside(ResultadoBusquedaUsuario res) 
			throws InsideConverterException 
		{
			UsuarioResultadoBusqueda usuRes = new UsuarioResultadoBusqueda();
			
			FilterPageRequestResponse infoRes = new FilterPageRequestResponse();
			infoRes.setDevueltos(res.getResultados().size());
			infoRes.setPagina(res.getPagina());
			if (res.getPorPagina() == 0) {
				infoRes.setPaginas(0);
			} else {
				int mod = res.getTotales() % res.getPorPagina();
				if (mod == 0) {
					infoRes.setPaginas(res.getTotales() / res.getPorPagina());
				} else {
					infoRes.setPaginas(res.getTotales() / res.getPorPagina() + 1);
				}
				
			}

			infoRes.setTotales(res.getTotales());
			
			usuRes.setPaginador(infoRes);
			
			for (ObjetoInsideUsuario objUsuario : res.getResultados()) {
				Usuario user = new Usuario();
				user.setNif(objUsuario.getNif());
				user.setActivo(objUsuario.isActivo());
				user.setCodigoUnidadOrganica(objUsuario.getUnidadOrganicaActiva());
				user.setNombreUnidadOrganica(objUsuario.getNombreUnidadOrganicaActiva());
				user.setNumeroProcedimiento(objUsuario.getNumeroProcedimiento());
				usuRes.getResultados().add(user);
			}
			
			
			return usuRes;
		}



}
