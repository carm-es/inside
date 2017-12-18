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

package es.mpt.dsic.infofirma.converter;

import java.util.ArrayList;
import java.util.List;

import es.mpt.dsic.eeutil.client.operFirma.model.FirmaInfo;
import es.mpt.dsic.eeutil.client.operFirma.model.InformacionFirma;
import es.mpt.dsic.eeutil.client.operFirma.model.OpcionesObtenerInformacionFirma;
import es.mpt.dsic.infofirma.model.InfoFirmaElectronica;
import es.mpt.dsic.infofirma.model.InfoFirmante;
import es.mpt.dsic.infofirma.model.OpcionesInfoFirma;
import es.mpt.dsic.infofirma.model.TipoContenidoFirmado;
import es.mpt.dsic.infofirma.model.TipoFirmaFirma;
import es.mpt.dsic.infofirma.model.TipoFirmaFirmante;

public class InfoFirmaConverter {

	/**
	 * Convierte un objeto del modelo del WS de Eeutil a un objeto del modelo de infofirmaService
	 * @param firmaInfo objeto del modelo del WS de Eeeutil
	 * @return objeto que represetna la información de un firmante del modelo de infoFirmaService.
	 */
	private static InfoFirmante firmaInfoToInfoFirmante (FirmaInfo firmaInfo) {
	
		InfoFirmante infoFirmante = new InfoFirmante ();
		infoFirmante.setDescripcion(firmaInfo.getNombre() + " " + firmaInfo.getApellido1() + " " +firmaInfo.getApellido2());
		infoFirmante.setFecha(firmaInfo.getFecha());
		infoFirmante.setNifcif(firmaInfo.getNifcif());
		
		if (firmaInfo.getExtras().equalsIgnoreCase("F")) {
			infoFirmante.setTipoFirmaFirmante(TipoFirmaFirmante.TFF_F);
		} else if (firmaInfo.getExtras().equalsIgnoreCase("CF")) {
			infoFirmante.setTipoFirmaFirmante(TipoFirmaFirmante.TFF_CF);
		} else if (firmaInfo.getExtras().equalsIgnoreCase("XF")) {
			infoFirmante.setTipoFirmaFirmante(TipoFirmaFirmante.TFF_XF);
		}
		
		return infoFirmante;
	}
	
	/**
	 * Convierte un objeto del modelo del WS de Eeutil a un objeto del modelo de infofirmaService
	 * @param firmasInfo objeto del modelo del WS de Eeeutil
	 * @return objeto del modelo de infoFirmaService que represetna una lista de información de firmantes.
	 */
	private static List<InfoFirmante> listFirmaInfoToListInfoFirmante (List<FirmaInfo> firmasInfo) {
		
		List<InfoFirmante> infoFirmanteList = new ArrayList<InfoFirmante> ();
		
		for (FirmaInfo firmaInfo : firmasInfo) {
			infoFirmanteList.add(firmaInfoToInfoFirmante(firmaInfo));
		}
		
		return infoFirmanteList;
		
	}
	/**
	 * Convierte un objeto del modelo del WS de Eeutil a un objeto del modelo de infofirmaService
	 * @param informacionFirma objeto del modelo del WS de Eeeutil
	 * @return objeto del modelo de infoFirmaService.
	 */
	public static InfoFirmaElectronica informacionFirmaToInfoFirmaElectronica (InformacionFirma informacionFirma)  {
		
		InfoFirmaElectronica infoFirmaElectronica = new InfoFirmaElectronica();
		
		if (informacionFirma.getDocumentoFirmado() != null) {
			
			infoFirmaElectronica.setContenidoFirmado (informacionFirma.getDocumentoFirmado().getContenido());
			
			String mime = informacionFirma.getDocumentoFirmado().getTipoMIME();
			infoFirmaElectronica.setMimeContenidoFirmado(mime);
			
			if ("application/xml".contentEquals(mime) || "text/xml".contentEquals(mime)) {
				infoFirmaElectronica.setTipoContenidoFirmado(TipoContenidoFirmado.TC_XML);
			} else {
				infoFirmaElectronica.setTipoContenidoFirmado(TipoContenidoFirmado.TC_B);
			}
		} else if (informacionFirma.getHashFirmado() != null) {
			infoFirmaElectronica.setContenidoFirmado(informacionFirma.getHashFirmado().getBytes());
			infoFirmaElectronica.setTipoContenidoFirmado(TipoContenidoFirmado.TC_H);
		}
		
		if (informacionFirma.getFirmantes() != null && informacionFirma.getFirmantes().getInformacionFirmas() != null 
				&& informacionFirma.getFirmantes().getInformacionFirmas().getInformacionFirmas() != null) {
			
			infoFirmaElectronica.setFirmantes(listFirmaInfoToListInfoFirmante(informacionFirma.getFirmantes().getInformacionFirmas().getInformacionFirmas()));
			
		}
		if (informacionFirma.getTipoDeFirma() != null) {
			TipoFirmaFirma tipoFirma = new TipoFirmaFirma();
			tipoFirma.setTipoFirma(informacionFirma.getTipoDeFirma().getTipoFirma());
			tipoFirma.setModoFirma(informacionFirma.getTipoDeFirma().getModoFirma());
			infoFirmaElectronica.setTipoFirma(tipoFirma);
		}
			
		
		return infoFirmaElectronica;
		
	}
	
	public static OpcionesObtenerInformacionFirma OpcionesInfoFirmaToOpcionesObtenerInformacionFirma (OpcionesInfoFirma opcionesInfoFirma) {
		OpcionesObtenerInformacionFirma opcionesObtenerInformacionFirma = new OpcionesObtenerInformacionFirma();
		opcionesObtenerInformacionFirma.setObtenerDatosFirmados(opcionesInfoFirma.isObtenerDatosFirmados());
		opcionesObtenerInformacionFirma.setObtenerFirmantes(opcionesInfoFirma.isObtenerFirmantes());
		opcionesObtenerInformacionFirma.setObtenerTipoFirma(opcionesInfoFirma.isObtenerTipoFirma());
		return opcionesObtenerInformacionFirma;
	}
	
	
	
	
}

