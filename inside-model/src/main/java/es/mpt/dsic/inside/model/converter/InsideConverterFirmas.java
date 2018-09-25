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

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.util.Assert;
import org.w3c.dom.Element;

import es.mpt.dsic.inside.model.converter.exception.InsideConverterException;
import es.mpt.dsic.inside.model.objetos.firmas.FirmaInside;
import es.mpt.dsic.inside.model.objetos.firmas.FirmaInsideTipoFirmaEnum;
import es.mpt.dsic.inside.model.objetos.firmas.contenido.ContenidoFirmaCSVInside;
import es.mpt.dsic.inside.model.objetos.firmas.contenido.ContenidoFirmaCertificadoContenidoBinarioInside;
import es.mpt.dsic.inside.model.objetos.firmas.contenido.ContenidoFirmaCertificadoDsSignatureInside;
import es.mpt.dsic.inside.model.objetos.firmas.contenido.ContenidoFirmaCertificadoReferenciaInside;
import es.mpt.dsic.inside.model.objetos.firmas.contenido.ContenidoFirmaInside;
import es.mpt.dsic.inside.xml.eni.firma.Firmas;
import es.mpt.dsic.inside.xml.eni.firma.TipoFirma;
import es.mpt.dsic.inside.xml.eni.firma.TipoFirmasElectronicas;
import es.mpt.dsic.inside.xml.eni.firma.TipoFirmasElectronicas.ContenidoFirma;

public abstract class InsideConverterFirmas {

	protected static final Log logger = LogFactory.getLog(InsideConverterFirmas.class);

	public static List<FirmaInside> firmasEniToInside(Firmas firmas) throws InsideConverterException {
		// Según el ENI del documento, si no existen firmas NO deberá incluirse
		// el nodo Firmas.
		// Assert.notNull(firmas);
		List<FirmaInside> firmasInside = null;
		if (firmas != null) {
			firmasInside = new ArrayList<FirmaInside>();
			for (TipoFirmasElectronicas firmaEni : firmas.getFirma()) {
				firmasInside.add(InsideConverterFirmas.firmaEniToInside(firmaEni, firmas.getFirma().indexOf(firmaEni)));
			}
		}

		return firmasInside;
	}

	public static FirmaInside firmaEniToInside(TipoFirmasElectronicas firmaEni) throws InsideConverterException {
		Assert.notNull(firmaEni);
		FirmaInside firmaInside = new FirmaInside();
		firmaInside.setTipoFirma(FirmaInsideTipoFirmaEnum.fromValue(firmaEni.getTipoFirma().value()));
		firmaInside.setIdentificadorEnDocumento(firmaEni.getId());
		firmaInside.setContenidoFirma(Contenido.contenidoFirmaEniToInside(firmaEni.getContenidoFirma(), firmaEni.getTipoFirma()));
		firmaInside.setRef(firmaEni.getRef());
		return firmaInside;
	}

	public static FirmaInside firmaEniToInside(TipoFirmasElectronicas firmaEni, int orden) throws InsideConverterException {
		Assert.notNull(firmaEni);
		FirmaInside firmaInside = firmaEniToInside(firmaEni);
		firmaInside.setOrden(orden);
		return firmaInside;
	}

	public static Firmas firmasInsideToEni(List<FirmaInside> firmas, boolean convertBase64) throws InsideConverterException {
		// Según el ENI del documento, si no existen firmas NO deberá incluirse
		// el nodo Firmas.
		// El nodo Firmas sin ningún nodo Firma dentro es INCORRECTO.
		Firmas firmasEni = null;

		if (firmas != null && firmas.size() != 0) {
			firmasEni = new Firmas();
			for (FirmaInside firmaInside : firmas) {
				firmasEni.getFirma().add(firmaInsideToEni(firmaInside, convertBase64));
			}
		}

		return firmasEni;
	}

	// Me quedo con la última firma.
	public static Firmas ultimaFirmaInsideToEni(List<FirmaInside> firmas, boolean convertBase64) throws InsideConverterException {
		Firmas firmasEni = null;
		if (firmas != null) {
			firmasEni = new Firmas();
			if (firmas.size() > 0) {
				firmasEni.getFirma().add(firmaInsideToEni(firmas.get(firmas.size() - 1), convertBase64));
			}
		}

		return firmasEni;
	}

	// public static FirmaInside firmaEniToInside (TipoFirmasElectronicas
	// firmaEni) throws InsideConverterFirmasException {
	// FirmaInside firmaInside = null;
	// if (firmaEni != null) {
	// firmaInside = new FirmaInside ();
	// firmaInside.setTipoFirma(FirmaInsideTipoFirmaEnum.fromValue(firmaEni.getTipoFirma().value()));
	// firmaInside.setContenidoFirma(Contenido.contenidoFirmaEniToInside(firmaEni.getContenidoFirma()));
	// }
	//
	//
	// return firmaInside;
	// }

	private static TipoFirmasElectronicas firmaInsideToEni(FirmaInside firmaInside, boolean convertBase64)
			throws InsideConverterException {
		Assert.notNull(firmaInside);
		TipoFirmasElectronicas firma = new TipoFirmasElectronicas();
		if (StringUtils.isNotEmpty(firmaInside.getRef())) {
			firma.setRef(firmaInside.getRef());
		}
		firma.setContenidoFirma(Contenido.contenidoFirmaInsideToEni(firmaInside.getContenidoFirma(), convertBase64));
		firma.setId(firmaInside.getIdentificadorEnDocumento());
		firma.setTipoFirma(TipoFirma.fromValue(firmaInside.getTipoFirma().value()));
		return firma;
	}

	public static class Contenido {

		// public static Contenido contenidoFirmaEniToInside(
		// es.mpt.dsic.inside.xml.eni.firma.TipoFirmasElectronicas.ContenidoFirma
		// contenidoFirma) throws InsideConverterFirmasException {
		//
		// Contenido contenido = null;
		// if (contenidoFirma != null) {
		// contenido = new Contenido();
		// contenido.setCSV(CSV.contenidoFirmaCSVEniToInside(contenidoFirma.getCSV()));
		// contenido.setFirmaConCertificado(FirmaConCertificado.firmaConCetificadoEniToInside(contenidoFirma.getFirmaConCertificado()));
		// }
		//
		// return contenido;
		// }

		public static ContenidoFirma contenidoFirmaInsideToEni(ContenidoFirmaInside contenidoFirmaInside, boolean convertBase64)
				throws InsideConverterException {
			TipoFirmasElectronicas.ContenidoFirma contenidoFirmaEni = new TipoFirmasElectronicas.ContenidoFirma();

			Assert.notNull(contenidoFirmaInside);

			if (contenidoFirmaInside instanceof ContenidoFirmaCertificadoContenidoBinarioInside) {
				ContenidoFirmaCertificadoContenidoBinarioInside contenidoCertificadoBinario = (ContenidoFirmaCertificadoContenidoBinarioInside) contenidoFirmaInside;
				TipoFirmasElectronicas.ContenidoFirma.FirmaConCertificado firmaConCertificadoEni = new TipoFirmasElectronicas.ContenidoFirma.FirmaConCertificado();
				if (convertBase64) {
					logger.warn("establecemos la firma base 64:" + Base64.encode(contenidoCertificadoBinario.getValorBinario()));
					firmaConCertificadoEni.setFirmaBase64(Base64.encode(contenidoCertificadoBinario.getValorBinario()));
				} else {
					firmaConCertificadoEni.setFirmaBase64(contenidoCertificadoBinario.getValorBinario());
				}
				contenidoFirmaEni.setFirmaConCertificado(firmaConCertificadoEni);
			} else if (contenidoFirmaInside instanceof ContenidoFirmaCertificadoDsSignatureInside) {
				ContenidoFirmaCertificadoDsSignatureInside contenidoCertificadoSignature = (ContenidoFirmaCertificadoDsSignatureInside) contenidoFirmaInside;
				TipoFirmasElectronicas.ContenidoFirma.FirmaConCertificado firmaConCertificadoEni = new TipoFirmasElectronicas.ContenidoFirma.FirmaConCertificado();
				try {
					firmaConCertificadoEni.setSignature(
							InsideConverterUtils.arrayOfBytesToSignature(contenidoCertificadoSignature.getValorBinario()));
				} catch (JAXBException e) {
					throw new InsideConverterException("Error convirtiendo el contenido del Signature a SignatureType ", e);
				}
				contenidoFirmaEni.setFirmaConCertificado(firmaConCertificadoEni);
			} else if (contenidoFirmaInside instanceof ContenidoFirmaCSVInside) {
				ContenidoFirmaCSVInside contenidoFirmaInsideCSV = (ContenidoFirmaCSVInside) contenidoFirmaInside;
				ContenidoFirma.CSV csvEni = new ContenidoFirma.CSV();
				csvEni.setValorCSV(contenidoFirmaInsideCSV.getValorCSV());
				csvEni.setRegulacionGeneracionCSV(contenidoFirmaInsideCSV.getRegulacionGeneracionCSV());
				contenidoFirmaEni.setCSV(csvEni);
			} else if (contenidoFirmaInside instanceof ContenidoFirmaCertificadoReferenciaInside) {
				ContenidoFirmaCertificadoReferenciaInside contenidoFirmaInsideReferencia = (ContenidoFirmaCertificadoReferenciaInside) contenidoFirmaInside;
				TipoFirmasElectronicas.ContenidoFirma.FirmaConCertificado firmaConCertificadoEni = new TipoFirmasElectronicas.ContenidoFirma.FirmaConCertificado();
				firmaConCertificadoEni.setReferenciaFirma(contenidoFirmaInsideReferencia.getReferenciaFirma());
				contenidoFirmaEni.setFirmaConCertificado(firmaConCertificadoEni);
			} else {
				throw new InsideConverterException(
						"No sé como convertir una firma de tipo " + contenidoFirmaInside.getClass() + " a ENI");
			}

			return contenidoFirmaEni;
		}

		public static ContenidoFirmaInside contenidoFirmaEniToInside(TipoFirmasElectronicas.ContenidoFirma contenidoFirmaEni,
				TipoFirma tipoFirma) throws InsideConverterException {
			ContenidoFirmaInside contenidoFirmaInside = null;

			if (contenidoFirmaEni != null && contenidoFirmaEni.getFirmaConCertificado() != null) {
				if (contenidoFirmaEni.getFirmaConCertificado().getFirmaBase64() != null) {
					ContenidoFirmaCertificadoContenidoBinarioInside contenidoFirma = new ContenidoFirmaCertificadoContenidoBinarioInside();
					byte[] bytesFirma = InsideConverterUtils
							.base64Decode(contenidoFirmaEni.getFirmaConCertificado().getFirmaBase64());
					contenidoFirma.setValorBinario(bytesFirma);
					contenidoFirma.setMime(InsideConverterUtils.getMimeByTipoFirma(tipoFirma.value()));
					/*
					 * try {
					 * contenidoFirma.setMime(InsideConverterUtils.getMimeType(
					 * bytesFirma); } catch (InsideConverterUtilsException e) {
					 * throw new InsideConverterFirmasException(
					 * "Error extrayendo el mimeType de la firma",e); }
					 */
					contenidoFirmaInside = contenidoFirma;
				} else if (contenidoFirmaEni.getFirmaConCertificado().getSignature() != null) {
					ContenidoFirmaCertificadoDsSignatureInside contenidoFirma = new ContenidoFirmaCertificadoDsSignatureInside();
					try {
						contenidoFirma.setValorBinario(InsideConverterUtils
								.signatureToArrayOfBytes(contenidoFirmaEni.getFirmaConCertificado().getSignature()));
						contenidoFirma.setMime("application/octect-stream");
					} catch (JAXBException e) {
						throw new InsideConverterException("Error convirtiendo el nodo ds:Signature a array de bytes", e, true);
					}
					contenidoFirmaInside = contenidoFirma;

				} else if (contenidoFirmaEni.getFirmaConCertificado().getReferenciaFirma() != null) {
					ContenidoFirmaCertificadoReferenciaInside contenidoFirma = new ContenidoFirmaCertificadoReferenciaInside();
					if (contenidoFirmaEni.getFirmaConCertificado().getReferenciaFirma() instanceof String) {
						contenidoFirma
								.setReferenciaFirma(contenidoFirmaEni.getFirmaConCertificado().getReferenciaFirma().toString());
					} else {
						Element e = (Element) contenidoFirmaEni.getFirmaConCertificado().getReferenciaFirma();
						contenidoFirma.setReferenciaFirma(e.getTextContent());
					}
					contenidoFirmaInside = contenidoFirma;
				}

			} else if (contenidoFirmaEni != null && contenidoFirmaEni.getCSV() != null) {
				ContenidoFirmaCSVInside contenidoFirma = new ContenidoFirmaCSVInside();
				contenidoFirma.setValorCSV(contenidoFirmaEni.getCSV().getValorCSV());
				contenidoFirma.setRegulacionGeneracionCSV(contenidoFirmaEni.getCSV().getRegulacionGeneracionCSV());
				contenidoFirmaInside = contenidoFirma;
			}

			return contenidoFirmaInside;
		}
	}

}
