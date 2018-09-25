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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.activation.DataHandler;

import org.apache.axiom.attachments.ByteArrayDataSource;
import org.apache.axiom.om.util.Base64;
import org.apache.commons.io.IOUtils;

import es.mpt.dsic.inside.model.converter.exception.InsideConverterException;
import es.mpt.dsic.inside.xml.eni.documento.TipoDocumento;
import es.mpt.dsic.inside.xml.eni.documento.contenido.TipoContenido;
import es.mpt.dsic.inside.xml.eni.documento.contenido.mtom.TipoContenidoMtom;
import es.mpt.dsic.inside.xml.eni.documento.mtom.TipoDocumentoMtom;
import es.mpt.dsic.inside.xml.eni.expediente.TipoExpediente;
import es.mpt.dsic.inside.xml.eni.expediente.mtom.TipoExpedienteMtom;
import es.mpt.dsic.inside.xml.inside.MetadatoAdicional;
import es.mpt.dsic.inside.xml.inside.TipoDocumentoInside;
import es.mpt.dsic.inside.xml.inside.TipoDocumentoInsideConMAdicionales;
import es.mpt.dsic.inside.xml.inside.TipoExpedienteInside;
import es.mpt.dsic.inside.xml.inside.TipoExpedienteInsideConMAdicionales;
import es.mpt.dsic.inside.xml.inside.TipoMetadatosAdicionales;
import es.mpt.dsic.inside.xml.inside.mtom.TipoDocumentoInsideConMAdicionalesMtom;
import es.mpt.dsic.inside.xml.inside.mtom.TipoDocumentoInsideMtom;
import es.mpt.dsic.inside.xml.inside.mtom.TipoExpedienteInsideConMAdicionalesMtom;
import es.mpt.dsic.inside.xml.inside.mtom.TipoExpedienteInsideMtom;
import es.mpt.dsic.inside.xml.inside.ws.documento.TipoDocumentoEniFileInside;
import es.mpt.dsic.inside.xml.inside.ws.documento.alta.TipoDocumentoAltaInside;
import es.mpt.dsic.inside.xml.inside.ws.documento.alta.TipoDocumentoAsociadoaExpediente;
import es.mpt.dsic.inside.xml.inside.ws.documento.alta.mtom.TipoDocumentoAltaInsideMtom;
import es.mpt.dsic.inside.xml.inside.ws.documento.alta.mtom.TipoDocumentoAsociadoaExpedienteMtom;
import es.mpt.dsic.inside.xml.inside.ws.documento.conversion.TipoDocumentoConversionInside;
import es.mpt.dsic.inside.xml.inside.ws.documento.conversion.mtom.TipoDocumentoConversionInsideMtom;
import es.mpt.dsic.inside.xml.inside.ws.documento.file.DocumentoEniFileInside;
import es.mpt.dsic.inside.xml.inside.ws.documento.file.DocumentoEniFileInsideConMAdicionales;
import es.mpt.dsic.inside.xml.inside.ws.documento.fileMtom.DocumentoEniFileInsideConMAdicionalesMtom;
import es.mpt.dsic.inside.xml.inside.ws.documento.fileMtom.DocumentoEniFileInsideMtom;
import es.mpt.dsic.inside.xml.inside.ws.documento.mtom.TipoDocumentoEniFileInsideMtom;
import es.mpt.dsic.inside.xml.inside.ws.expediente.TipoExpedienteEniFileInside;
import es.mpt.dsic.inside.xml.inside.ws.expediente.file.ExpedienteEniFileInside;
import es.mpt.dsic.inside.xml.inside.ws.expediente.file.ExpedienteEniFileInsideConMAdicionales;
import es.mpt.dsic.inside.xml.inside.ws.expediente.fileMtom.ExpedienteEniFileInsideConMAdicionalesMtom;
import es.mpt.dsic.inside.xml.inside.ws.expediente.fileMtom.ExpedienteEniFileInsideMtom;
import es.mpt.dsic.inside.xml.inside.ws.expediente.mtom.TipoExpedienteEniFileInsideMtom;
import es.mpt.dsic.inside.xml.inside.ws.validacion.documento.TipoDocumentoValidacionInside;
import es.mpt.dsic.inside.xml.inside.ws.validacion.documento.TipoOpcionValidacionDocumento;
import es.mpt.dsic.inside.xml.inside.ws.validacion.documento.TipoOpcionesValidacionDocumentoInside;
import es.mpt.dsic.inside.xml.inside.ws.validacion.documento.mtom.TipoDocumentoValidacionInsideMtom;
import es.mpt.dsic.inside.xml.inside.ws.validacion.documento.mtom.TipoOpcionValidacionDocumentoMtom;
import es.mpt.dsic.inside.xml.inside.ws.validacion.documento.mtom.TipoOpcionesValidacionDocumentoInsideMtom;
import es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.TipoExpedienteValidacionInside;
import es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.TipoOpcionValidacionExpediente;
import es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.TipoOpcionesValidacionExpedienteInside;
import es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.mtom.TipoExpedienteValidacionInsideMtom;
import es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.mtom.TipoOpcionValidacionExpedienteMtom;
import es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.mtom.TipoOpcionesValidacionExpedienteInsideMtom;
import es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.TipoDocumentoEniBinarioOTipo;
import es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.TipoDocumentoVisualizacionInside;
import es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.TipoOpcionesVisualizacionDocumento;
import es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.TipoResultadoVisualizacionDocumentoInside;
import es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.mtom.TipoDocumentoEniBinarioOTipoMtom;
import es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.mtom.TipoDocumentoVisualizacionInsideMtom;
import es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.mtom.TipoOpcionesVisualizacionDocumentoMtom;
import es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.mtom.TipoResultadoVisualizacionDocumentoInsideMtom;

public class InsideConverterMtom {

	public static TipoDocumentoAltaInside mtomTipoDocumentoAltaToInside(TipoDocumentoAltaInsideMtom mtom)
			throws InsideConverterException {// throws IOException {
		TipoDocumentoAltaInside retorno = new TipoDocumentoAltaInside();
		if (mtom.getAsociadoaExpediente() != null) {
			retorno.setAsociadoaExpediente(mtomTipoDocumentoAsociadoExpedienteToInside(mtom.getAsociadoaExpediente()));
		}
		if (mtom.getContenido() != null) {
			try {
				retorno.setContenido(IOUtils.toByteArray(mtom.getContenido().getInputStream()));
			} catch (IOException e) {
				throw new InsideConverterException("No se puede leer el contenido del DataHandler", e, false);
			}
		}
		if (mtom.getCsv() != null) {
			retorno.setCsv(mtomTipoDocumentoAltaCsvToInside(mtom.getCsv()));
		}
		retorno.setFirmadoConCertificado(mtom.isFirmadoConCertificado());
		retorno.setFirmadoEnServidor(mtom.isFirmadoEnServidor());
		if (mtom.getMetadatosAdicionales() != null) {
			retorno.setMetadatosAdicionales(mtom.getMetadatosAdicionales());
		}
		if (mtom.getMetadatosEni() != null) {
			retorno.setMetadatosEni(mtomTipoDocumentoAltaMetadatosEniToInside(mtom.getMetadatosEni()));
		}
		return retorno;
	}

	private static TipoDocumentoAsociadoaExpediente mtomTipoDocumentoAsociadoExpedienteToInside(
			TipoDocumentoAsociadoaExpedienteMtom mtom) {
		TipoDocumentoAsociadoaExpediente retorno = new TipoDocumentoAsociadoaExpediente();
		retorno.setIdentificadorCarpeta(mtom.getIdentificadorCarpeta());
		retorno.setIdentificadorExpediente(mtom.getIdentificadorExpediente());
		return retorno;
	}

	private static TipoDocumentoAltaInside.Csv mtomTipoDocumentoAltaCsvToInside(TipoDocumentoAltaInsideMtom.Csv mtom) {
		TipoDocumentoAltaInside.Csv retorno = new TipoDocumentoAltaInside.Csv();
		retorno.setRegulacionCSV(mtom.getRegulacionCSV());
		retorno.setValorCSV(mtom.getValorCSV());
		return retorno;
	}

	private static TipoDocumentoAltaInside.MetadatosEni mtomTipoDocumentoAltaMetadatosEniToInside(
			TipoDocumentoAltaInsideMtom.MetadatosEni mtom) {
		TipoDocumentoAltaInside.MetadatosEni retorno = new TipoDocumentoAltaInside.MetadatosEni();
		retorno.setFechaCaptura(mtom.getFechaCaptura());
		retorno.setOrigenCiudadanoAdministracion(mtom.isOrigenCiudadanoAdministracion());
		retorno.setTipoDocumental(mtom.getTipoDocumental());
		if (mtom.getOrgano() != null) {
			for (String organo : mtom.getOrgano()) {
				retorno.getOrgano().add(organo);
			}
		}
		return retorno;
	}

	public static TipoDocumentoConversionInside mtomTipoDocumentoConversionToInside(TipoDocumentoConversionInsideMtom mtom)
			throws InsideConverterException {// throws IOException {
		TipoDocumentoConversionInside retorno = new TipoDocumentoConversionInside();
		if (mtom.getContenido() != null) {
			try {
				retorno.setContenido(IOUtils.toByteArray(mtom.getContenido().getInputStream()));
			} catch (IOException e) {
				throw new InsideConverterException("No se puede leer el contenido del DataHandler", e, false);
			}
		}
		if (mtom.getCsv() != null) {
			retorno.setCsv(mtomTipoDocumentoConversionCsvToInside(mtom.getCsv()));
		}
		retorno.setFirmadoConCertificado(mtom.isFirmadoConCertificado());
		if (mtom.getMetadatosEni() != null) {
			retorno.setMetadatosEni(mtomTipoDocumentoConversionMetadatosEniToInside(mtom.getMetadatosEni()));
		}
		return retorno;
	}

	public static TipoDocumentoConversionInsideMtom tipoDocumentoConversionToMtom(TipoDocumentoConversionInside inside) {// throws
																															// IOException
																															// {
		TipoDocumentoConversionInsideMtom retorno = new TipoDocumentoConversionInsideMtom();
		if (inside.getContenido() != null) {
			retorno.setContenido(new DataHandler(new ByteArrayDataSource(inside.getContenido())));
		}
		if (inside.getCsv() != null) {
			retorno.setCsv(tipoDocumentoConversionCsvToMtom(inside.getCsv()));
		}
		retorno.setFirmadoConCertificado(inside.isFirmadoConCertificado());
		if (inside.getMetadatosEni() != null) {
			retorno.setMetadatosEni(tipoDocumentoConversionMetadatosEniToMtom(inside.getMetadatosEni()));
		}
		return retorno;
	}

	private static TipoDocumentoConversionInside.Csv mtomTipoDocumentoConversionCsvToInside(
			TipoDocumentoConversionInsideMtom.Csv mtom) {
		TipoDocumentoConversionInside.Csv retorno = new TipoDocumentoConversionInside.Csv();
		retorno.setRegulacionCSV(mtom.getRegulacionCSV());
		retorno.setValorCSV(mtom.getValorCSV());
		return retorno;
	}

	private static TipoDocumentoConversionInsideMtom.Csv tipoDocumentoConversionCsvToMtom(
			TipoDocumentoConversionInside.Csv inside) {
		TipoDocumentoConversionInsideMtom.Csv retorno = new TipoDocumentoConversionInsideMtom.Csv();
		retorno.setRegulacionCSV(inside.getRegulacionCSV());
		retorno.setValorCSV(inside.getValorCSV());
		return retorno;
	}

	private static TipoDocumentoConversionInside.MetadatosEni mtomTipoDocumentoConversionMetadatosEniToInside(
			TipoDocumentoConversionInsideMtom.MetadatosEni mtom) {
		TipoDocumentoConversionInside.MetadatosEni retorno = new TipoDocumentoConversionInside.MetadatosEni();
		retorno.setEstadoElaboracion(mtom.getEstadoElaboracion());
		retorno.setFechaCaptura(mtom.getFechaCaptura());
		retorno.setIdentificador(mtom.getIdentificador());
		retorno.setOrigenCiudadanoAdministracion(mtom.isOrigenCiudadanoAdministracion());
		retorno.setTipoDocumental(mtom.getTipoDocumental());
		retorno.setVersionNTI(mtom.getVersionNTI());
		if (mtom.getOrgano() != null) {
			for (String organo : mtom.getOrgano()) {
				retorno.getOrgano().add(organo);
			}
		}
		return retorno;
	}

	private static TipoDocumentoConversionInsideMtom.MetadatosEni tipoDocumentoConversionMetadatosEniToMtom(
			TipoDocumentoConversionInside.MetadatosEni inside) {
		TipoDocumentoConversionInsideMtom.MetadatosEni retorno = new TipoDocumentoConversionInsideMtom.MetadatosEni();
		retorno.setEstadoElaboracion(inside.getEstadoElaboracion());
		retorno.setFechaCaptura(inside.getFechaCaptura());
		retorno.setIdentificador(inside.getIdentificador());
		retorno.setOrigenCiudadanoAdministracion(inside.isOrigenCiudadanoAdministracion());
		retorno.setTipoDocumental(inside.getTipoDocumental());
		retorno.setVersionNTI(inside.getVersionNTI());
		if (inside.getOrgano() != null) {
			for (String organo : inside.getOrgano()) {
				retorno.getOrgano().add(organo);
			}
		}
		return retorno;
	}

	public static TipoDocumento mtomTipoDocumentoToInside(TipoDocumentoMtom mtom) throws InsideConverterException {
		// throws IOException {
		TipoDocumento retorno = new TipoDocumento();
		if (mtom.getContenidoMtom() != null) {
			retorno.setContenido(mtomTipoContenidoToInside(mtom.getContenidoMtom()));
		}
		retorno.setFirmas(mtom.getFirmas());
		retorno.setId(mtom.getId());
		retorno.setMetadatos(mtom.getMetadatos());
		return retorno;
	}

	public static TipoDocumentoMtom tipoDocumentoToMtom(TipoDocumento inside) {
		TipoDocumentoMtom retorno = new TipoDocumentoMtom();
		if (inside.getContenido() != null) {
			retorno.setContenidoMtom(tipoContenidoToMtom(inside.getContenido()));
		}
		retorno.setFirmas(inside.getFirmas());
		retorno.setId(inside.getId());
		retorno.setMetadatos(inside.getMetadatos());
		return retorno;
	}

	private static TipoContenido mtomTipoContenidoToInside(TipoContenidoMtom mtom) throws InsideConverterException {// throws
																													// IOException
																													// {
		TipoContenido retorno = new TipoContenido();
		retorno.setDatosXML(mtom.getDatosXML());
		retorno.setId(mtom.getId());
		retorno.setNombreFormato(mtom.getNombreFormato());
		retorno.setReferenciaFichero(mtom.getReferenciaFichero());
		if (mtom.getValorBinario() != null) {
			try {
				retorno.setValorBinario(IOUtils.toByteArray(mtom.getValorBinario().getInputStream()));
			} catch (IOException e) {
				throw new InsideConverterException("No se puede leer el valor binario", e, false);
			}
		}
		return retorno;
	}

	public static TipoContenidoMtom tipoContenidoToMtom(TipoContenido inside) {
		TipoContenidoMtom retorno = new TipoContenidoMtom();
		retorno.setDatosXML(inside.getDatosXML());
		retorno.setId(inside.getId());
		retorno.setNombreFormato(inside.getNombreFormato());
		retorno.setReferenciaFichero(inside.getReferenciaFichero());
		if (inside.getValorBinario() != null) {
			retorno.setValorBinario(new DataHandler(new ByteArrayDataSource(inside.getValorBinario())));
		}
		return retorno;
	}

	public static TipoDocumentoEniFileInsideMtom tipoDocumentoEniFileToMtom(TipoDocumentoEniFileInside inside) {
		TipoDocumentoEniFileInsideMtom retorno = new TipoDocumentoEniFileInsideMtom();
		if (inside.getDocumentoEniBytes() != null) {
			retorno.setDocumentoEniBytes(new DataHandler(new ByteArrayDataSource(inside.getDocumentoEniBytes())));
		}
		return retorno;
	}

	public static TipoExpedienteEniFileInsideMtom tipoExpedienteEniFileToMtom(TipoExpedienteEniFileInside inside) {
		TipoExpedienteEniFileInsideMtom retorno = new TipoExpedienteEniFileInsideMtom();
		if (inside.getExpedienteEniBytes() != null) {
			retorno.setExpedienteEniBytes(new DataHandler(new ByteArrayDataSource(inside.getExpedienteEniBytes())));
		}
		return retorno;
	}

	public static TipoExpediente mtomTipoExpedienteToInside(TipoExpedienteMtom mtom) throws InsideConverterException {// throws
																														// IOException
																														// {
		TipoExpediente retorno = new TipoExpediente();
		retorno.setId(mtom.getId());
		retorno.setIndice(mtom.getIndice());
		retorno.setMetadatosExp(mtom.getMetadatosExp());
		if (mtom.getVisualizacionIndice() != null) {
			retorno.setVisualizacionIndice(mtomTipoContenidoToInside(mtom.getVisualizacionIndice()));
		}
		return retorno;
	}

	public static TipoExpedienteMtom tipoExpedienteToMtom(TipoExpediente inside) throws InsideConverterException {// throws
																													// IOException
																													// {
		TipoExpedienteMtom retorno = new TipoExpedienteMtom();
		retorno.setId(inside.getId());
		retorno.setIndice(inside.getIndice());
		retorno.setMetadatosExp(inside.getMetadatosExp());
		if (inside.getVisualizacionIndice() != null) {
			retorno.setVisualizacionIndice(tipoContenidoToMtom(inside.getVisualizacionIndice()));
		}
		return retorno;
	}

	public static TipoResultadoVisualizacionDocumentoInsideMtom tipoResultadoVisualizacionDocumentoToMtom(
			TipoResultadoVisualizacionDocumentoInside inside) {
		TipoResultadoVisualizacionDocumentoInsideMtom retorno = new TipoResultadoVisualizacionDocumentoInsideMtom();
		if (inside.getContenido() != null) {
			retorno.setContenido(new DataHandler(new ByteArrayDataSource(inside.getContenido())));
		}
		retorno.setMime(inside.getMime());
		return retorno;
	}

	public static TipoExpedienteInsideMtom tipoExpedienteInsideToMtom(TipoExpedienteInside inside)
			throws InsideConverterException {// throws IOException { {
		TipoExpedienteInsideMtom retorno = new TipoExpedienteInsideMtom();
		if (inside.getExpedienteENI() != null) {
			retorno.setExpedienteENI(tipoExpedienteToMtom(inside.getExpedienteENI()));
		}
		retorno.setInfo(inside.getInfo());
		retorno.setMetadatosAdicionales(inside.getMetadatosAdicionales());
		return retorno;
	}

	public static TipoDocumentoInsideMtom tipoDocumentoInsideToMtom(TipoDocumentoInside inside) {
		TipoDocumentoInsideMtom retorno = new TipoDocumentoInsideMtom();
		if (inside.getDocumentoENI() != null) {
			retorno.setDocumentoENI(tipoDocumentoToMtom(inside.getDocumentoENI()));
		}
		retorno.setInfo(inside.getInfo());
		retorno.setMetadatosAdicionales(inside.getMetadatosAdicionales());
		return retorno;
	}

	public static TipoDocumentoConversionInside tipoDocumentoConversionToInside(TipoDocumentoConversionInsideMtom mtom)
			throws InsideConverterException {
		TipoDocumentoConversionInside retorno = new TipoDocumentoConversionInside();
		if (mtom.getContenido() != null) {
			try {
				retorno.setContenido(IOUtils.toByteArray(mtom.getContenido().getInputStream()));
			} catch (IOException e) {
				throw new InsideConverterException("No se puede extraer el contenido del DataHandler", e, false);
			}
		}
		if (mtom.getCsv() != null) {
			retorno.setCsv(tipoDocumentoConversionCsvToInside(mtom.getCsv()));
		}
		retorno.setFirmadoConCertificado(mtom.isFirmadoConCertificado());
		if (mtom.getMetadatosEni() != null) {
			retorno.setMetadatosEni(tipoDocumentoConversionEniToInside(mtom.getMetadatosEni()));
		}
		return retorno;
	}

	public static TipoDocumentoConversionInside tipoDocumentoConversionToInside(TipoDocumentoConversionInsideMtom mtom,
			byte[] contenido) throws InsideConverterException {
		TipoDocumentoConversionInside retorno = new TipoDocumentoConversionInside();
		retorno.setContenido(contenido);
		if (mtom.getCsv() != null) {
			retorno.setCsv(tipoDocumentoConversionCsvToInside(mtom.getCsv()));
		}
		retorno.setFirmadoConCertificado(mtom.isFirmadoConCertificado());
		if (mtom.getMetadatosEni() != null) {
			retorno.setMetadatosEni(tipoDocumentoConversionEniToInside(mtom.getMetadatosEni()));
		}
		return retorno;
	}

	private static TipoDocumentoConversionInside.Csv tipoDocumentoConversionCsvToInside(
			TipoDocumentoConversionInsideMtom.Csv mtom) {
		TipoDocumentoConversionInside.Csv retorno = new TipoDocumentoConversionInside.Csv();
		retorno.setRegulacionCSV(mtom.getRegulacionCSV());
		retorno.setValorCSV(mtom.getValorCSV());
		return retorno;
	}

	private static TipoDocumentoConversionInside.MetadatosEni tipoDocumentoConversionEniToInside(
			TipoDocumentoConversionInsideMtom.MetadatosEni mtom) {
		TipoDocumentoConversionInside.MetadatosEni retorno = new TipoDocumentoConversionInside.MetadatosEni();
		retorno.setEstadoElaboracion(mtom.getEstadoElaboracion());
		retorno.setFechaCaptura(mtom.getFechaCaptura());
		retorno.setIdentificador(mtom.getIdentificador());
		retorno.setOrigenCiudadanoAdministracion(mtom.isOrigenCiudadanoAdministracion());
		retorno.setTipoDocumental(mtom.getTipoDocumental());
		retorno.setVersionNTI(mtom.getVersionNTI());
		if (mtom.getOrgano() != null) {
			for (String organo : mtom.getOrgano()) {
				retorno.getOrgano().add(organo);
			}
		}
		return retorno;
	}

	public static TipoDocumentoInsideConMAdicionalesMtom tipoDocumentoConMAdicionalesToMtom(
			TipoDocumentoInsideConMAdicionales inside) throws InsideConverterException {
		TipoDocumentoInsideConMAdicionalesMtom retorno = new TipoDocumentoInsideConMAdicionalesMtom();
		if (inside.getDocumento() != null) {
			retorno.setDocumentoMtom(tipoDocumentoToMtom(inside.getDocumento()));
		}
		if (inside.getMetadatosAdicionales() != null && inside.getMetadatosAdicionales().getMetadatoAdicional() != null) {
			List<MetadatoAdicional> metadatos = new ArrayList<MetadatoAdicional>();
			for (MetadatoAdicional metadato : inside.getMetadatosAdicionales().getMetadatoAdicional()) {
				metadatos.add(convertMetadata(metadato));
			}
			retorno.setMetadatosAdicionales(new TipoMetadatosAdicionales());
			retorno.getMetadatosAdicionales().getMetadatoAdicional().addAll(metadatos);
		}
		return retorno;
	}

	public static TipoExpedienteInsideConMAdicionalesMtom tipoExpedienteConMAToMtom(TipoExpedienteInsideConMAdicionales inside)
			throws InsideConverterException {
		TipoExpedienteInsideConMAdicionalesMtom retorno = new TipoExpedienteInsideConMAdicionalesMtom();
		if (inside.getExpediente() != null) {
			retorno.setExpedienteMtom(tipoExpedienteToMtom(inside.getExpediente()));
		}
		if (inside.getMetadatosAdicionales() != null && inside.getMetadatosAdicionales().getMetadatoAdicional() != null) {
			List<MetadatoAdicional> metadatos = new ArrayList<MetadatoAdicional>();
			for (MetadatoAdicional metadato : inside.getMetadatosAdicionales().getMetadatoAdicional()) {
				metadatos.add(convertMetadata(metadato));
			}
			retorno.setMetadatosAdicionales(new TipoMetadatosAdicionales());
			retorno.getMetadatosAdicionales().getMetadatoAdicional().addAll(metadatos);
		}
		return retorno;
	}

	public static MetadatoAdicional convertMetadata(MetadatoAdicional data) throws InsideConverterException {
		try {
			String valor = (String) data.getValor();
			byte[] decoded = Base64.decode(valor);
			data.setValor(new String(decoded, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new InsideConverterException(e.getMessage(), e, false);
		}
		return data;
	}

	public static TipoDocumentoValidacionInside tipoDocumentoValidacionToInside(TipoDocumentoValidacionInsideMtom mtom)
			throws IOException {
		TipoDocumentoValidacionInside retorno = new TipoDocumentoValidacionInside();
		if (mtom.getContenido() != null) {
			retorno.setContenido(IOUtils.toByteArray(mtom.getContenido().getInputStream()));
		}
		if (mtom.getOpcionesValidacionDocumento() != null) {
			retorno.setOpcionesValidacionDocumento(
					tipoOpcionesValidacionDocumentoToInside(mtom.getOpcionesValidacionDocumento()));
		}
		return retorno;
	}

	private static TipoOpcionesValidacionDocumentoInside tipoOpcionesValidacionDocumentoToInside(
			TipoOpcionesValidacionDocumentoInsideMtom mtom) {
		TipoOpcionesValidacionDocumentoInside retorno = new TipoOpcionesValidacionDocumentoInside();
		if (mtom.getOpcionValidacionDocumento() != null) {
			for (TipoOpcionValidacionDocumentoMtom opcion : mtom.getOpcionValidacionDocumento()) {
				if (opcion.compareTo(TipoOpcionValidacionDocumentoMtom.TOVD_01) == 0) {
					retorno.getOpcionValidacionDocumento().add(TipoOpcionValidacionDocumento.TOVD_01);
				}
				if (opcion.compareTo(TipoOpcionValidacionDocumentoMtom.TOVD_02) == 0) {
					retorno.getOpcionValidacionDocumento().add(TipoOpcionValidacionDocumento.TOVD_02);
				}
			}
		}
		return retorno;
	}

	public static TipoExpedienteValidacionInside tipoExpedienteValidacionToInside(TipoExpedienteValidacionInsideMtom mtom)
			throws IOException {
		TipoExpedienteValidacionInside retorno = new TipoExpedienteValidacionInside();
		if (mtom.getContenido() != null) {
			retorno.setContenido(IOUtils.toByteArray(mtom.getContenido().getInputStream()));
		}
		if (mtom.getOpcionesValidacionExpediente() != null) {
			retorno.setOpcionesValidacionExpediente(
					tipoOpcionesValidacionDocumentoToInside(mtom.getOpcionesValidacionExpediente()));
		}
		return retorno;
	}

	private static TipoOpcionesValidacionExpedienteInside tipoOpcionesValidacionDocumentoToInside(
			TipoOpcionesValidacionExpedienteInsideMtom mtom) {
		TipoOpcionesValidacionExpedienteInside retorno = new TipoOpcionesValidacionExpedienteInside();
		if (mtom.getOpcionValidacionExpediente() != null) {
			for (TipoOpcionValidacionExpedienteMtom opcion : mtom.getOpcionValidacionExpediente()) {
				if (opcion.compareTo(TipoOpcionValidacionExpedienteMtom.TOVE_01) == 0) {
					retorno.getOpcionValidacionExpediente().add(TipoOpcionValidacionExpediente.TOVE_01);
				}
				if (opcion.compareTo(TipoOpcionValidacionExpedienteMtom.TOVE_02) == 0) {
					retorno.getOpcionValidacionExpediente().add(TipoOpcionValidacionExpediente.TOVE_02);
				}
				if (opcion.compareTo(TipoOpcionValidacionExpedienteMtom.TOVE_03) == 0) {
					retorno.getOpcionValidacionExpediente().add(TipoOpcionValidacionExpediente.TOVE_03);
				}
			}
		}
		return retorno;
	}

	public static TipoDocumentoVisualizacionInside tipoDocumentoVisualizacionToInside(TipoDocumentoVisualizacionInsideMtom mtom)
			throws IOException {
		TipoDocumentoVisualizacionInside retorno = new TipoDocumentoVisualizacionInside();
		if (mtom.getDocumentoEni() != null) {
			retorno.setDocumentoEni(tipoDocumentoEniBinariooTipoToInside(mtom.getDocumentoEni()));
		}
		retorno.setMetadatosAdicionales(mtom.getMetadatosAdicionales());
		if (mtom.getOpcionesVisualizacionDocumento() != null) {
			retorno.setOpcionesVisualizacionDocumento(
					tipoOpcionesVisualizacionDocumentoToInside(mtom.getOpcionesVisualizacionDocumento()));
		}
		return retorno;
	}

	private static TipoDocumentoEniBinarioOTipo tipoDocumentoEniBinariooTipoToInside(TipoDocumentoEniBinarioOTipoMtom mtom)
			throws IOException {
		TipoDocumentoEniBinarioOTipo retorno = new TipoDocumentoEniBinarioOTipo();
		if (mtom.getDocumentoEniBinario() != null) {
			retorno.setDocumentoEniBinario(IOUtils.toByteArray(mtom.getDocumentoEniBinario().getInputStream()));
		} else {
			retorno.setDocumentoEniTipo(tipoDocumentoToInside(mtom.getDocumentoEniTipo()));
		}
		return retorno;
	}

	private static TipoDocumento tipoDocumentoToInside(TipoDocumentoMtom mtom) throws IOException {
		TipoDocumento retorno = new TipoDocumento();
		if (mtom.getContenidoMtom() != null) {
			retorno.setContenido(tipoContenidoToMtom(mtom.getContenidoMtom()));
		}
		retorno.setFirmas(mtom.getFirmas());
		retorno.setId(mtom.getId());
		retorno.setMetadatos(mtom.getMetadatos());
		return retorno;
	}

	private static TipoContenido tipoContenidoToMtom(TipoContenidoMtom mtom) throws IOException {
		TipoContenido retorno = new TipoContenido();
		retorno.setDatosXML(mtom.getDatosXML());
		retorno.setId(mtom.getId());
		retorno.setNombreFormato(mtom.getNombreFormato());
		retorno.setReferenciaFichero(mtom.getReferenciaFichero());
		if (mtom.getValorBinario() != null) {
			retorno.setValorBinario(IOUtils.toByteArray(mtom.getValorBinario().getInputStream()));
		}
		return retorno;
	}

	private static TipoOpcionesVisualizacionDocumento tipoOpcionesVisualizacionDocumentoToInside(
			TipoOpcionesVisualizacionDocumentoMtom mtom) {
		TipoOpcionesVisualizacionDocumento retorno = new TipoOpcionesVisualizacionDocumento();
		retorno.setEstamparImagen(mtom.isEstamparImagen());
		retorno.setEstamparNombreOrganismo(mtom.isEstamparNombreOrganismo());
		if (mtom.getFilasNombreOrganismo() != null) {
			retorno.setFilasNombreOrganismo(filasNombreOrganismoToInside(mtom.getFilasNombreOrganismo()));
		}
		return retorno;
	}

	private static TipoOpcionesVisualizacionDocumento.FilasNombreOrganismo filasNombreOrganismoToInside(
			TipoOpcionesVisualizacionDocumentoMtom.FilasNombreOrganismo mtom) {
		TipoOpcionesVisualizacionDocumento.FilasNombreOrganismo retorno = new TipoOpcionesVisualizacionDocumento.FilasNombreOrganismo();
		if (mtom.getFila() != null) {
			for (String fila : mtom.getFila()) {
				retorno.getFila().add(fila);
			}
		}
		return retorno;
	}

	public static DocumentoEniFileInsideMtom documentoEniFileToMtom(DocumentoEniFileInside tipoDocumento) {
		DocumentoEniFileInsideMtom retorno = new DocumentoEniFileInsideMtom();
		retorno.setDocumentoMtom(InsideConverterMtom.tipoDocumentoToMtom(tipoDocumento.getDocumento()));
		retorno.setDocumentoEniBytes(new DataHandler(new ByteArrayDataSource(tipoDocumento.getDocumentoEniBytes())));
		return retorno;
	}

	public static DocumentoEniFileInsideConMAdicionalesMtom documentoEniFileToMtomConAdicionales(DocumentoEniFileInsideConMAdicionales documento) {
		DocumentoEniFileInsideConMAdicionalesMtom retorno = new DocumentoEniFileInsideConMAdicionalesMtom();
		retorno.setDocumentoMtom(InsideConverterMtom.tipoDocumentoToMtom(documento.getDocumento()));
		retorno.setMetadatosAdicionales(documento.getMetadatosAdicionales());
		retorno.setDocumentoEniBytes(new DataHandler(new ByteArrayDataSource(documento.getDocumentoEniBytes())));
		return retorno;
	}

	public static ExpedienteEniFileInsideMtom expedienteEniFileToMtom(ExpedienteEniFileInside expediente)
			throws InsideConverterException {
		ExpedienteEniFileInsideMtom retorno = new ExpedienteEniFileInsideMtom();
		retorno.setExpedienteMtom(InsideConverterMtom.tipoExpedienteToMtom(expediente.getExpediente()));
		retorno.setExpedienteEniBytes(new DataHandler(new ByteArrayDataSource(expediente.getExpedienteEniBytes())));
		return retorno;
	}

	public static ExpedienteEniFileInsideConMAdicionalesMtom expedienteEniFileToMtomConAdicionales(
			ExpedienteEniFileInsideConMAdicionales expedienteEniFile) throws InsideConverterException {
		ExpedienteEniFileInsideConMAdicionalesMtom retorno = new ExpedienteEniFileInsideConMAdicionalesMtom();
		retorno.setExpedienteMtom(InsideConverterMtom.tipoExpedienteToMtom(expedienteEniFile.getExpediente()));
		retorno.setMetadatosAdicionales(expedienteEniFile.getMetadatosAdicionales());
		retorno.setExpedienteEniBytes(new DataHandler(new ByteArrayDataSource(expedienteEniFile.getExpedienteEniBytes())));
		return retorno;
	}

}
