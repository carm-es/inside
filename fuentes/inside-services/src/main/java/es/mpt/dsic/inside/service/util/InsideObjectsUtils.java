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

package es.mpt.dsic.inside.service.util;

import java.util.ArrayList;
import java.util.List;

import es.mpt.dsic.inside.model.objetos.ObjetoInsideMetadatoAdicional;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideMetadatos;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInside;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenido;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenidoElementoIndizado;
import es.mpt.dsic.inside.model.objetos.firmas.FirmaInside;
import es.mpt.dsic.inside.model.objetos.firmas.FirmaInsideTipoFirmaEnum;
import es.mpt.dsic.inside.model.objetos.firmas.contenido.ContenidoFirmaCertificadoAlmacenableInside;
import es.mpt.dsic.inside.model.objetos.firmas.contenido.ContenidoFirmaCertificadoReferenciaInside;
import es.mpt.dsic.inside.model.objetos.firmas.contenido.ContenidoFirmaInside;

public class InsideObjectsUtils {
	
	
	/**
	 * Devuelve las firmas que referencian a un nodo en concreto
	 * @param identificadorNodo
	 * @return
	 */
	public static List<FirmaInside> getFirmasReferencianNodo (String identificadorNodo, List<FirmaInside> firmas) {
		List<FirmaInside> firmasRef = new ArrayList<FirmaInside> ();
		if (firmas != null) {
			for (FirmaInside firma : firmas) {
				if (firma.getContenidoFirma() instanceof ContenidoFirmaCertificadoReferenciaInside) {
					ContenidoFirmaCertificadoReferenciaInside contRef = (ContenidoFirmaCertificadoReferenciaInside) firma.getContenidoFirma();
					if (contRef.getReferenciaFirma().replace("#","").contentEquals(identificadorNodo)) {
						firmasRef.add(firma);
					}
				}
			}			
		}
		return firmasRef;
	}
	/**
	 * Devuelve los identificadores de los nodos que son firmas, exceptuando si es firma CSV
	 * @param documentoInside
	 * @return
	 */
	public static List<String> getIdNodosSonFirma (ObjetoDocumentoInside documento) {
		List<String> idNodosToSend = new ArrayList<String> ();
		
		if (documento.getFirmas() != null && documento.getFirmas().size() > 0) {
						
			for (FirmaInside firma : documento.getFirmas()) {
				ContenidoFirmaInside contenidoFirma = firma.getContenidoFirma();
				
				if (contenidoFirma instanceof ContenidoFirmaCertificadoReferenciaInside) {
					ContenidoFirmaCertificadoReferenciaInside contRef = (ContenidoFirmaCertificadoReferenciaInside) contenidoFirma;						
					if (!idNodosToSend.contains(contRef.getReferenciaFirma())) {
						idNodosToSend.add(contRef.getReferenciaFirma().replace("#", ""));
					}
					
				} else if (contenidoFirma instanceof ContenidoFirmaCertificadoAlmacenableInside) {
					ContenidoFirmaCertificadoAlmacenableInside contAlm = (ContenidoFirmaCertificadoAlmacenableInside) contenidoFirma;
					if (!idNodosToSend.contains(firma.getIdentificadorEnDocumento())) {
						idNodosToSend.add(firma.getIdentificadorEnDocumento());
					}
				}
			}
		}
		
		return idNodosToSend;
	}
	
	/**
	 * Devuelve la firma cuyo identificador de nodo coincida con el identificador pasado en el parámetro
	 * @param identificador
	 * @param firmas
	 * @return
	 */
	public static FirmaInside getFirmaInsideByIdentificadorEnDocumento (String identificador, List<FirmaInside> firmas) {
		FirmaInside firma = null;
		if (identificador != null) {
			int i = 0;
			
			while (i < firmas.size() && firma == null) {
				if (identificador.contentEquals(firmas.get(i).getIdentificadorEnDocumento())) {
					firma = firmas.get(i);					
				}
				i++;
			}
		}
		return firma;
	}
	
	/**
	 * Devuelve una lista con las firmas que sean de tipo CSV
	 * @param firmas
	 * @return
	 */
	public static List<FirmaInside> getFirmasCSV (List<FirmaInside> firmas) {
		List<FirmaInside> firmasCSV = new ArrayList<FirmaInside> ();
		if (firmas != null) {
			for (FirmaInside firma : firmas) {
				if (firma.getTipoFirma().equals(FirmaInsideTipoFirmaEnum.TF_01)) {
					firmasCSV.add(firma);
				}
			}
		}
		return firmasCSV;
	}
	
	/**
	 * Devuelve una lista con las firmas que NO sean de tipo CSV
	 * @param firmas
	 * @return
	 */
	public static List<FirmaInside> getFirmasNoCSV (List<FirmaInside> firmas) {
		List<FirmaInside> firmasCSV = new ArrayList<FirmaInside> ();
		if (firmas != null) {
			for (FirmaInside firma : firmas) {
				if (!firma.getTipoFirma().equals(FirmaInsideTipoFirmaEnum.TF_01)) {
					firmasCSV.add(firma);
				}
			}
		}
		return firmasCSV;
	}
	
	/**
	 * Obtiene el valor de un metadato adicional.
	 * Devuelve la cadena vacía si no lo encuentra
	 * @param metadatos
	 * @param nombreMetadato
	 * @return
	 */
	public static String getMetadatoAdicionalNotNull (ObjetoInsideMetadatos metadatos, String nombreMetadato) {
		String ret = getMetadatoAdicional (metadatos, nombreMetadato);
		if (ret == null) {
			ret = "";
		}
		
		return ret;
	}
	/**
	 * Obtiene el valor de un metadato Adicional.
	 * Devuelve null si no lo encuentra.
	 */
	public static String getMetadatoAdicional (ObjetoInsideMetadatos metadatos, String nombreMetadato) {
		String ret = null;
		if (metadatos != null && metadatos.getMetadatosAdicionales() != null) {
			for (ObjetoInsideMetadatoAdicional data : metadatos.getMetadatosAdicionales()) {
				if (data.getNombre().equals(nombreMetadato)) {
					ret = data.getValor().toString();
				}
			}
		}
		return ret;
	}
	
	/**
	 * Devuelve el número de bytes que tenga el contenido del documento y la firma.
	 * @param documento
	 * @return
	 */
	public static Integer getBytesObjetoDocumentoInside (ObjetoDocumentoInside documento) {

		Integer bytes = 0;
		
		
		// Si el documento está en este campo, obtenemos de aquí el tamaño.
		if (documento.getContenido().getValorBinario() != null) {
			bytes = documento.getContenido().getValorBinario().length;			
		}
		
		// El documento se encuentra en la firma, lo buscamos ahí
		if (documento.getContenido().getReferencia() != null) {
			
			// La referencia comenzará con el carácter "#". Eliminamos este carácter para obtener el identificador.
			String identificadorFirma = documento.getContenido().getReferencia().substring(1);
			FirmaInside firmaInside = getFirmaInsideByIdentificadorEnDocumento (identificadorFirma, documento.getFirmas());
			
			
			if (firmaInside.getContenidoFirma() instanceof ContenidoFirmaCertificadoAlmacenableInside) {
				ContenidoFirmaCertificadoAlmacenableInside contenidoFirma = (ContenidoFirmaCertificadoAlmacenableInside) firmaInside.getContenidoFirma();
				bytes = bytes + contenidoFirma.getValorBinario().length;			
			}
		}
		
		return bytes;
	}
	
	/**
	 * Busca un elemento con un identificador determinado en una lista contenedora de elementos indizados.
	 * Si lo encuentra lo devuelve, si no lo encuentra devuelve NULL.
	 * @param identificadorElemento:
	 * 	- identificador del documento en caso de buscar un documento.
	 * 	- identificador del expediente asociado en caso de buscar un expediente vinculado o importado
	 * 	- identificador de la carpeta en caso de buscar una carpeta
	 * @param clase Clase del elemento a eliminar. Si es nula no se tendrá en cuenta
	 * @param listaContenedora lista de elementos indizados en la que se quiere buscar
	 * @return el elemento indizado si se encuentra, null si no se encuentra.
	 */
	public static <T extends ObjetoExpedienteInsideIndiceContenidoElementoIndizado> ObjetoExpedienteInsideIndiceContenidoElementoIndizado getElementoIndizadoInList (String identificadorElemento, 
																																									 Class<T> clase, 
																																									 List<ObjetoExpedienteInsideIndiceContenidoElementoIndizado> listaContenedora) {
		ObjetoExpedienteInsideIndiceContenidoElementoIndizado elementoEncontrado = null;
		
		int i=0;
		
		while (i < listaContenedora.size() && elementoEncontrado == null) {
			ObjetoExpedienteInsideIndiceContenidoElementoIndizado elemento = listaContenedora.get(i);
			if (elemento instanceof ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado) {
				if (clase == null || clase == ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado.class) {
					ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado documentoIndizado = (ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado) elemento;
					if (documentoIndizado.getIdentificadorDocumento().equalsIgnoreCase( identificadorElemento )) {
						elementoEncontrado = documentoIndizado;
					}
				}
			} else if (elemento instanceof ObjetoExpedienteInsideIndiceContenido) {
				if (clase == null || clase == ObjetoExpedienteInsideIndiceContenido.class) {
					ObjetoExpedienteInsideIndiceContenido contenidoIndizado = (ObjetoExpedienteInsideIndiceContenido) elemento;
					if (contenidoIndizado.getIdentificadorExpedienteAsociado().equalsIgnoreCase(identificadorElemento)) {
						elementoEncontrado = contenidoIndizado;
					}
				}
			} else if (elemento instanceof ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada) {
				if (clase == null || clase == ObjetoExpedienteInsideIndiceContenido.class) {
					ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada carpetaIndizada = (ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada) elemento;
					if (carpetaIndizada.getIdentificadorCarpeta().equalsIgnoreCase(identificadorElemento)) {
						elementoEncontrado = carpetaIndizada;
					}
				}
			}
			i++;
		}
		
		return elementoEncontrado;
	}
	
	/**
	 * Reasigna el orden en una lista de elementos indizados;
	 * @param elementosIndizados
	 */
	public static void asignarOrdenAElementosIndizados (List<ObjetoExpedienteInsideIndiceContenidoElementoIndizado> elementosIndizados) {
		int orden = 1;
		for (ObjetoExpedienteInsideIndiceContenidoElementoIndizado elementoIndizado : elementosIndizados) {
			elementoIndizado.setOrden(orden);
			orden ++;
		}
	}
}
