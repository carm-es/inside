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

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.util.Assert;

import es.mpt.dsic.inside.model.objetos.ObjetoInsideMetadatoAdicional;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideVersion;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInside;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInsideContenido;
import es.mpt.dsic.inside.model.objetos.documento.metadatos.ObjetoDocumentoInsideMetadatos;
import es.mpt.dsic.inside.model.objetos.documento.metadatos.ObjetoDocumentoInsideMetadatosEstadoElaboracion;
import es.mpt.dsic.inside.model.objetos.documento.metadatos.ObjetoDocumentoInsideMetadatosEstadoElaboracionEnumeracion;
import es.mpt.dsic.inside.model.objetos.documento.metadatos.ObjetoDocumentoInsideMetadatosTipoDocumental;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteInside;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndice;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenido;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenidoElementoIndizado;
import es.mpt.dsic.inside.model.objetos.expediente.metadatos.ObjetoExpedienteInsideMetadatos;
import es.mpt.dsic.inside.model.objetos.expediente.metadatos.ObjetoExpedienteInsideMetadatosEnumeracionEstados;
import es.mpt.dsic.inside.model.objetos.firmas.FirmaInside;
import es.mpt.dsic.inside.model.objetos.firmas.FirmaInsideTipoFirmaEnum;
import es.mpt.dsic.inside.model.objetos.firmas.contenido.ContenidoFirmaCSVInside;
import es.mpt.dsic.inside.model.objetos.firmas.contenido.ContenidoFirmaCertificadoAlmacenableInside;
import es.mpt.dsic.inside.model.objetos.firmas.contenido.ContenidoFirmaCertificadoContenidoBinarioInside;
import es.mpt.dsic.inside.model.objetos.firmas.contenido.ContenidoFirmaCertificadoReferenciaInside;
import es.mpt.dsic.inside.model.objetos.firmas.contenido.ContenidoFirmaInside;

public class UtilsTest {

	public static ObjetoDocumentoInside createObjetoDocumentoInside (boolean conMetadatos,
														 boolean conIdentificador,
														 boolean conContenido,
														 boolean conFirma) {
		
		ObjetoDocumentoInside documento = new ObjetoDocumentoInside ();
		
		if (conMetadatos) {
			ObjetoDocumentoInsideMetadatos metadatos = new ObjetoDocumentoInsideMetadatos();			
			ObjetoDocumentoInsideMetadatosEstadoElaboracion estadoElaboracion = new ObjetoDocumentoInsideMetadatosEstadoElaboracion();
			estadoElaboracion.setIdentificadorDocumentoOrigen("idOrigen");
			estadoElaboracion.setValorEstadoElaboracion(ObjetoDocumentoInsideMetadatosEstadoElaboracionEnumeracion.EE_01);
			metadatos.setEstadoElaboracion(estadoElaboracion);
			
			metadatos.setFechaCaptura(new GregorianCalendar());
			
			List<String> organos = new ArrayList<String> ();
			organos.add("organo1");
			organos.add("organo2");
			
			metadatos.setOrgano(organos);
			
			metadatos.setOrigenCiudadanoAdministracion(true);
			metadatos.setTipoDocumental(ObjetoDocumentoInsideMetadatosTipoDocumental.TD_01);
			metadatos.setVersionNTI("version 1.0");
			
			documento.setMetadatos(metadatos);
		}
		
		
		if (conContenido) {
			ObjetoDocumentoInsideContenido contenido = new ObjetoDocumentoInsideContenido();
			
			try {
				contenido.setValorBinario(IOUtils.toByteArray(new FileInputStream("src/test/resources/PEDEFE.pdf")));
				contenido.setMime("application/pdf");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			contenido.setNombreFormato("PDF");
			
			documento.setContenido(contenido);
		}
		
		if (conFirma) {
			FirmaInside firma = new FirmaInside ();
			firma.setTipoFirma(FirmaInsideTipoFirmaEnum.TF_02);
			ContenidoFirmaCertificadoContenidoBinarioInside contenidoFirma = new ContenidoFirmaCertificadoContenidoBinarioInside();
			try {
				
				contenidoFirma.setValorBinario(IOUtils.toByteArray(new FileInputStream("src/test/resources/firmaXades.xsig")));
				contenidoFirma.setMime("text/xml");
		
			} catch (Exception e) {
				e.printStackTrace();
			}

			firma.setContenidoFirma(contenidoFirma);
			
			documento.getFirmas().add(firma);
		}
		
		documento.setVersion(ObjetoInsideVersion.createFirstVersion());
		
		if (conIdentificador) {
			documento.setIdentificador(UUID.randomUUID().toString());
		}
		
		return documento;
		 
	}
	
	
	public static ObjetoExpedienteInside createObjetoExpedienteInside (boolean conIdentificador,
														   			  boolean conMetadatos,
														   			  boolean conIndice) {
		
		ObjetoExpedienteInside expediente = new ObjetoExpedienteInside();
		
		if (conMetadatos) {
			ObjetoExpedienteInsideMetadatos metadatos = new ObjetoExpedienteInsideMetadatos ();
			metadatos.setClasificacion("clasificacion");
			metadatos.setEstado(ObjetoExpedienteInsideMetadatosEnumeracionEstados.E_01);
			metadatos.setFechaAperturaExpediente(new GregorianCalendar ());			
			metadatos.setVersionNTI("version 1.0");
			List<String> interesados = new ArrayList<String> ();
			interesados.add("12345678Z");
			interesados.add("99999999R");
			metadatos.setInteresado(interesados);
			
			List<String> organos = new ArrayList<String> ();
			organos.add("organo1");
			organos.add("organo2");
			
			metadatos.setOrgano(organos);
			
			expediente.setMetadatos(metadatos);
			
		}
		
		
		if (conIndice) {
			
		}
		
		expediente.setVersion(ObjetoInsideVersion.createFirstVersion());
		
		if (conIdentificador) {			
			expediente.setIdentificador(UUID.randomUUID().toString());
		}
		
		return expediente;
	}
	
	
	public static void assertEqual(ObjetoExpedienteInside expediente1,ObjetoExpedienteInside expediente2, boolean checkContenidoFirma) 
		throws AssertionError
	{
		Assert.notNull(expediente1);
		Assert.notNull(expediente2);
		if(!StringUtils.equals(expediente1.getIdentificador(),expediente2.getIdentificador())){
			throw new AssertionError("No coincide el identificador");
		}
		if(!StringUtils.equals(expediente1.getIdentificadorRepositorio(),expediente2.getIdentificadorRepositorio())){
			throw new AssertionError("No coincide el identificador del repositorio");
		}
		assertEqual(expediente1.getMetadatos(),expediente2.getMetadatos());
		assertEqual(expediente1.getVersion(),expediente2.getVersion());
		assertEqual(expediente1.getIndice(),expediente2.getIndice(),checkContenidoFirma);
	}
	
	private static void assertEqual(ObjetoExpedienteInsideIndice indice1,ObjetoExpedienteInsideIndice indice2, boolean checkContenidoFirma) 
		throws AssertionError
	{
		if(indice1 != null && indice2 != null){
			assertEqual(indice1.getIndiceContenido(),indice2.getIndiceContenido());
			asserEqualFirmas(indice1.getFirmas(),indice2.getFirmas(),checkContenidoFirma);
			
		}else if(indice1 == null && indice2 == null){
			//ok
		}else{
			throw new AssertionError("Uno de los objetos indice es null y el otro no");
		}
		
	}


	private static void asserEqualFirmas(List<FirmaInside> firmas1,List<FirmaInside> firmas2, boolean checkContenidoFirma)
	{
		if(firmas1 != null && firmas2 != null){
			for(FirmaInside firma1 : firmas1){	
				boolean iguales = false;
				for(FirmaInside firma2 : firmas2){
					try {
						assertEqual(firma1,firma2,checkContenidoFirma);
						iguales = true;
						break;
					} catch (AssertionError e) {
					}
				}
				if(!iguales){
					throw new AssertionError("No coinciden las firmas");
				}
			}
		}else if(firmas1 == null && firmas2 == null){
			//ok
		}else if(firmas1 == null && (firmas2 != null && firmas2.size() == 0 )){
			//ok
		}else if(firmas2 == null && (firmas1 != null && firmas1.size() == 0 )){
			//ok
		}else{
			throw new AssertionError("Uno de los listados de firmas está vacio y el otro no");
		}
		
	}


	private static void assertEqual(ObjetoExpedienteInsideIndiceContenido indiceContenido1,ObjetoExpedienteInsideIndiceContenido indiceContenido2) 
		throws AssertionError
	{
		if(indiceContenido1 != null && indiceContenido2 != null){
			assertEqual(indiceContenido1.getFechaIndiceElectronico(),indiceContenido2.getFechaIndiceElectronico());
			if(indiceContenido1.getElementosIndizados() != null && indiceContenido2.getElementosIndizados() != null){
				if(indiceContenido1.getElementosIndizados().size() != indiceContenido1.getElementosIndizados().size()){
					throw new AssertionError("No coincide el número de elementos indizados");
				}
				
				for(ObjetoExpedienteInsideIndiceContenidoElementoIndizado elementoIndizado1 : indiceContenido1.getElementosIndizados()){	
					boolean iguales = false;
					for(ObjetoExpedienteInsideIndiceContenidoElementoIndizado elementoIndizado2 : indiceContenido2.getElementosIndizados()){
						try {
							assertEqual(elementoIndizado1,elementoIndizado2);
							iguales = true;
							break;
						} catch (AssertionError e) {
						}
					}
					if(!iguales){
						throw new AssertionError("No coinciden los elementos indizados");
					}
				}
			}else if(indiceContenido1.getElementosIndizados() == null && indiceContenido2.getElementosIndizados() == null){
				//ok
			}else if(indiceContenido1.getElementosIndizados() == null && (indiceContenido2.getElementosIndizados() != null && indiceContenido2.getElementosIndizados().size() == 0 )){
				//ok
			}else if(indiceContenido2.getElementosIndizados() == null && (indiceContenido1.getElementosIndizados() != null && indiceContenido1.getElementosIndizados().size() == 0 )){
				//ok
			}else{
				throw new AssertionError("Uno de los listados de elementos del índice está vacio y el otro no");
			}
			
		}else if(indiceContenido1 == null && indiceContenido2 == null){
			//ok
		}else{
			throw new AssertionError("Uno de los objetos indiceContenido es null y el otro no");
		}
		
		
	}


	/**
	 * Comrpueba que dos elementos indizados son equivalentes
	 * 
	 * @param elementoIndizado1
	 * @param elementoIndizado2
	 * 
	 * @throws AssertionError	Si algún elemento de los elementos indizados difiere
	 */
	private static void assertEqual( 
			ObjetoExpedienteInsideIndiceContenidoElementoIndizado elementoIndizado1,
			ObjetoExpedienteInsideIndiceContenidoElementoIndizado elementoIndizado2) 
		throws AssertionError
	{
		if(elementoIndizado1 != null && elementoIndizado2 != null){
			if(elementoIndizado1.getClass() != elementoIndizado2.getClass()){
				throw new AssertionError("No coincide el tipo de elemento Indizado");
			}
			if(elementoIndizado1 instanceof ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado && elementoIndizado2 instanceof ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado){
				ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado documentoIndizado1 = (ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado) elementoIndizado1;
				ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado documentoIndizado2 = (ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado) elementoIndizado2;
				assertEqual(documentoIndizado1.getFechaIncorporacionExpediente(),documentoIndizado2.getFechaIncorporacionExpediente());
				if(!StringUtils.equals(documentoIndizado1.getFuncionResumen(),documentoIndizado2.getFuncionResumen())){
					throw new AssertionError("No coincide la función de reumen");
				}
				if(!StringUtils.equals(documentoIndizado1.getIdentificadorDocumento(),documentoIndizado2.getIdentificadorDocumento())){
					throw new AssertionError("No coincide el identificador del documento");
				}
				if(documentoIndizado1.getOrden() - documentoIndizado2.getOrden() != 0){
					throw new AssertionError("No coincide el orden del documento");
				}
				if(!StringUtils.equals(documentoIndizado1.getValorHuella(),documentoIndizado2.getValorHuella())){
					throw new AssertionError("No coincide el valor de la huella");
				}
			}else if(elementoIndizado1 instanceof ObjetoExpedienteInsideIndiceContenido && elementoIndizado2 instanceof ObjetoExpedienteInsideIndiceContenido){
				ObjetoExpedienteInsideIndiceContenido indiceContenido1 = (ObjetoExpedienteInsideIndiceContenido) elementoIndizado1;
				ObjetoExpedienteInsideIndiceContenido indiceContenido2 = (ObjetoExpedienteInsideIndiceContenido) elementoIndizado2;
				assertEqual(indiceContenido1,indiceContenido2);
			}else if(elementoIndizado1 instanceof ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada && elementoIndizado2 instanceof ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada){
				throw new AssertionError("No esta implementado");
			}else{
				throw new AssertionError("No coincide el tipo de elemento Indizado");
			}
		}else if(elementoIndizado1 == null && elementoIndizado2 == null){
			//ok
		}else{
			throw new AssertionError("Uno de los objetos ElementoIndizado es null y el otro no");
		}	
		
	}


	public static void assertEqual(ObjetoDocumentoInside documento1,ObjetoDocumentoInside documento2, boolean checkContenidoFirma, boolean checkContenidoFirmaDocumento) 
		throws AssertionError
	{
		Assert.notNull(documento1);
		Assert.notNull(documento2);
		if(!StringUtils.equals(documento1.getIdentificador(),documento2.getIdentificador())){
			throw new AssertionError("No coincide el identificador");
		}
		if(!StringUtils.equals(documento1.getIdentificadorRepositorio(),documento2.getIdentificadorRepositorio())){
			throw new AssertionError("No coincide el identificador del repositorio");
		}
		assertEqual(documento1.getMetadatos(),documento2.getMetadatos());
		assertEqual(documento1.getVersion(),documento2.getVersion());
		asserEqualFirmas(documento1.getFirmas(),documento2.getFirmas(),checkContenidoFirma);
		assertEqual(documento1.getContenido(),documento2.getContenido(),checkContenidoFirmaDocumento);
	}
	
	private static void assertEqual(ObjetoDocumentoInsideContenido contenido1,
			ObjetoDocumentoInsideContenido contenido2, boolean checkContenidoFirmaDocumento)
	{
		if(contenido1 != null && contenido2 != null){
			if(!StringUtils.equals(contenido1.getMime(),contenido2.getMime())){
				throw new AssertionError("No coincide el tipo mime");
			}
			if(!StringUtils.equals(contenido1.getNombreFormato(),contenido2.getNombreFormato())){
				throw new AssertionError("No coincide el nombre del formato");
			}
			if(checkContenidoFirmaDocumento && contenido1.getValorBinario() != contenido2.getValorBinario()){
				throw new AssertionError("No coincide el valor binario");
			}
		}else if(contenido1 == null && contenido2 == null){
			//ok
		}else{
			throw new AssertionError("Uno de los objetos Contenido es null y el otro no");
		}	
	}


	/**
	 * Comprueba que los metadatos del Expediente son equivalentes
	 * 
	 * @param metadatosExp1
	 * @param metadatosExp2
	 * 
	 * @throws AssertionError Si algún elemento de las metadatosExp1 difiere al del otro
	 */
	public static void assertEqual(ObjetoExpedienteInsideMetadatos metadatosExp1,ObjetoExpedienteInsideMetadatos metadatosExp2){
		if(metadatosExp1 != null &&metadatosExp2 != null){
			assertEqual(metadatosExp1.getFechaAperturaExpediente(),metadatosExp2.getFechaAperturaExpediente());
			assertEqual(metadatosExp1.getOrgano(), metadatosExp2.getOrgano());
			assertEqual(metadatosExp1.getInteresado(), metadatosExp2.getInteresado());
			assertEqual(metadatosExp1.getMetadatosAdicionales(),metadatosExp2.getMetadatosAdicionales());
			if(!StringUtils.equals(metadatosExp1.getVersionNTI(),metadatosExp2.getVersionNTI())){
				throw new AssertionError("No coincide la versión NTI del expediente");
			}
			if(!StringUtils.equals(metadatosExp1.getIdentificadorObjetoInside(),metadatosExp2.getIdentificadorObjetoInside())){
				throw new AssertionError("No coinciden el identificador del expediente");
			}
			if(!StringUtils.equals(metadatosExp1.getClasificacion(),metadatosExp2.getClasificacion())){
				throw new AssertionError("No coincide la clasificación del expediente");
			}
			if(metadatosExp1.getEstado().compareTo(metadatosExp1.getEstado()) != 0){
				throw new AssertionError("No coincide el estado del expediente");
			}
		}else if(metadatosExp1 == null && metadatosExp2 == null){
			//ok
		}else{
			throw new AssertionError("Una de los objetos metadatos es null y el otro no");
		}
	
	}
	
	/**
	 * Comprueba que los metadatos del Documento son equivalentes
	 * 
	 * @param metadatosDoc1
	 * @param metadatosDoc2
	 * 
	 * @throws AssertionError Si algún elemento de las metadatosDoc1 difiere al del otro
	 */
	public static void assertEqual(ObjetoDocumentoInsideMetadatos metadatosDoc1,ObjetoDocumentoInsideMetadatos metadatosDoc2){
		if(metadatosDoc1 != null &&metadatosDoc2 != null){
			assertEqual(metadatosDoc1.getEstadoElaboracion(),metadatosDoc2.getEstadoElaboracion());
			assertEqual(metadatosDoc1.getFechaCaptura(),metadatosDoc2.getFechaCaptura());
			if(!StringUtils.equals(metadatosDoc1.getIdentificadorObjetoInside(),metadatosDoc2.getIdentificadorObjetoInside())){
				throw new AssertionError("No coinciden los identificadores de objetos de Inside");
			}
			assertEqual(metadatosDoc1.getOrgano(), metadatosDoc2.getOrgano());
			assertEqual(metadatosDoc1.getMetadatosAdicionales(),metadatosDoc2.getMetadatosAdicionales());
			if(metadatosDoc1.getTipoDocumental().compareTo(metadatosDoc2.getTipoDocumental()) != 0){
				throw new AssertionError("No coincide el tipo documental");
			}
			if(!StringUtils.equals(metadatosDoc1.getVersionNTI(),metadatosDoc2.getVersionNTI())){
				throw new AssertionError("No coincide la versión NTI");
			}
		
		}else if(metadatosDoc1 == null && metadatosDoc2 == null){
			//ok
		}else{
			throw new AssertionError("Una de los objetos metadatos es null y el otro no");
		}
		
	}
	
	
	private static void assertEqual(
			ObjetoDocumentoInsideMetadatosEstadoElaboracion estadoElaboracion1,
			ObjetoDocumentoInsideMetadatosEstadoElaboracion estadoElaboracion2)
	{
		if(estadoElaboracion1 != null && estadoElaboracion2 != null){
			if(!StringUtils.equals(estadoElaboracion1.getIdentificadorDocumentoOrigen(),estadoElaboracion2.getIdentificadorDocumentoOrigen())){
				throw new AssertionError("No coincide el identificador de documento de origen");
			}
			if(estadoElaboracion1.getValorEstadoElaboracion().compareTo(estadoElaboracion2.getValorEstadoElaboracion()) != 0){
				throw new AssertionError("No coincide el estado de elaboración");
			}
		}else if(estadoElaboracion1 == null && estadoElaboracion2 == null){
			//ok
		}else{
			throw new AssertionError("Uno de los estados de elaboración es null y el otro no");
		}
		
	}


	/**
	 * Comprueba que las versiones son equivalentes
	 * 
	 * @param version1
	 * @param version2
	 * 
	 * @throws AssertionError Si algún elemento de las version1 difiere al de la otra
	 */
	public static void assertEqual(ObjetoInsideVersion version1,ObjetoInsideVersion version2) throws AssertionError{
		if(version1 != null && version2 != null){
			assertEqual(version1.getFechaVersion(), version2.getFechaVersion());
			if(version1.getVersion() != version2.getVersion()){
				throw new AssertionError("No coinciden las versiones");
			}
		}else if(version1 == null && version2 == null){
			//ok
		}else{
			throw new AssertionError("Una de las versiones es null y la otra no");
		}
		
	}
	
	/**
	 * Comprueba que las firmas son equivalentes
	 * 
	 * @param firma1
	 * @param firma2
	 * @param checkContenidoFirma 
	 * 
	 * @throws AssertionError Si algún elemento de la firma1 difiere al de la otra
	 */
	public static void assertEqual(FirmaInside firma1,FirmaInside firma2, boolean checkContenidoFirma) 
		throws AssertionError
	{
		if(firma1 != null && firma2 != null){
			if(firma1.getTipoFirma().compareTo(firma2.getTipoFirma()) != 0){
				throw new AssertionError("No coincide Los tipos de firma");
			}
			assertEqual(firma1.getContenidoFirma(),firma2.getContenidoFirma(),checkContenidoFirma);
		}else if(firma1 == null && firma1 == null){
			//ok
		}else{
			throw new AssertionError("Una de las firmas es null y la otra no");
		}
		
		
	}
	
	private static void assertEqual(ContenidoFirmaInside contenidoFirma1,ContenidoFirmaInside contenidoFirma2, boolean checkContenidoFirma)
		 throws AssertionError
	{
		if(contenidoFirma1 != null && contenidoFirma2 != null){
			if(contenidoFirma1.getClass() == contenidoFirma2.getClass()){
				if(contenidoFirma1 instanceof ContenidoFirmaCertificadoAlmacenableInside){
					ContenidoFirmaCertificadoAlmacenableInside contenidofirmaAlmacenable1 = (ContenidoFirmaCertificadoAlmacenableInside) contenidoFirma1;
					ContenidoFirmaCertificadoAlmacenableInside contenidofirmaAlmacenable2 = (ContenidoFirmaCertificadoAlmacenableInside) contenidoFirma2;	
					
					if(!StringUtils.equals(contenidofirmaAlmacenable1.getIdentificadorRepositorio(),contenidofirmaAlmacenable2.getIdentificadorRepositorio())){
						throw new AssertionError("No coincide el identificador del repositorio de las firmas");
					}
					if(!StringUtils.equals(contenidofirmaAlmacenable1.getMime(),contenidofirmaAlmacenable2.getMime())){
						throw new AssertionError("No coincide el tipo mime de las firmas");
					}
					if(checkContenidoFirma && contenidofirmaAlmacenable1.getValorBinario() != contenidofirmaAlmacenable2.getValorBinario()){
						throw new AssertionError("No coincide el valor binario de las firmas");
					}
				}else if(contenidoFirma1 instanceof ContenidoFirmaCertificadoReferenciaInside){
					ContenidoFirmaCertificadoReferenciaInside contenidofirmaReferencia1 = (ContenidoFirmaCertificadoReferenciaInside) contenidoFirma1;
					ContenidoFirmaCertificadoReferenciaInside contenidofirmaReferencia2 = (ContenidoFirmaCertificadoReferenciaInside) contenidoFirma2;
					if(!StringUtils.equals(contenidofirmaReferencia1.getReferenciaFirma(),contenidofirmaReferencia2.getReferenciaFirma())){
						throw new AssertionError("No coincide la referencia de las firmas");
					}
				}else if(contenidoFirma1 instanceof ContenidoFirmaCSVInside){

					ContenidoFirmaCSVInside contenidofirmaCsv1 = (ContenidoFirmaCSVInside) contenidoFirma1;
					ContenidoFirmaCSVInside contenidofirmaCsv2 = (ContenidoFirmaCSVInside) contenidoFirma2;
					
					if(!StringUtils.equals(contenidofirmaCsv1.getRegulacionGeneracionCSV(),contenidofirmaCsv2.getRegulacionGeneracionCSV())){
						throw new AssertionError("No coincide la regulación del CSV");
					}
					if(!StringUtils.equals(contenidofirmaCsv1.getValorCSV(),contenidofirmaCsv2.getValorCSV())){
						throw new AssertionError("No coincide el valor del CSV");
					}
				}else{
					throw new AssertionError("No sé comparar contenidos de firmas de tipo" + contenidoFirma1.getClass());
				}
			}else{
				throw new AssertionError("Las firmas son de tipos distintos");
			}
		}else if(contenidoFirma1 == null && contenidoFirma2 == null){
			//ok
		}else{
			throw new AssertionError("Uno de los contenidos de la firma y el otro no");
		}
		
	}


	/**
	 * Comprueba que las fechas son equivalentes
	 * 
	 * @param fecha1
	 * @param fecha2
	 * 
	 * @throws AssertionError Si las fechas difieren
	 */
	public static void assertEqual(Calendar fecha1,Calendar fecha2)
		throws AssertionError
	{
		if(fecha1 != null && fecha2 != null){
			if(!DateUtils.isSameInstant(fecha1, fecha1)){
				throw new AssertionError("No coinciden las fechas");
			}
		}else if(fecha1 == null && fecha2 == null){
			//ok
		}else{
			throw new AssertionError("Una de las fechas es null y la otra no");
		}
	}
	
	/**
	 * Comprueba que dos listas  son equivalentes
	 * 
	 * @param fecha1
	 * @param fecha2
	 * 
	 * @throws AssertionError Si las listas difieren
	 */
	public static void assertEqual(List<?> lista1,List<?> lista2){
		if(lista1 != null && lista2 != null){
			if(lista1.size() != lista2.size()){
				throw new AssertionError("No coincide el tamaño de las listas");
			}
			if(!lista1.containsAll(lista2)){
				throw new AssertionError("Las listas no contienen los mismos elementos");
			}
		}else if(lista1 == null && lista2 == null){
			//ok
		}else{
			throw new AssertionError("Una de las listas es null y la otra no");
		}
	}
	
	private static void assertEqual(
			Map<String, ObjetoInsideMetadatoAdicional> metadatosAdicionales,
			Map<String, ObjetoInsideMetadatoAdicional> metadatosAdicionales2) {
		// TODO Auto-generated method stub
		
	}
	

}
