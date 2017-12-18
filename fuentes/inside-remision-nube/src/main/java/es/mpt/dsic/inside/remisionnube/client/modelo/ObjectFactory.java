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


package es.mpt.dsic.inside.remisionnube.client.modelo;

import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the es.mpt.dsic.inside.remisionnube.client.modelo package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SignatureMethodTypeHMACOutputLength_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "HMACOutputLength");
    private final static QName _AccesoDocumentoExpediente_QNAME = new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "accesoDocumentoExpediente");
    private final static QName _DocumentoEniFile_QNAME = new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/documentoEniFile", "documentoEniFile");
    private final static QName _DocumentoConversion_QNAME = new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/conversion", "documentoConversion");
    private final static QName _Signature_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "Signature");
    private final static QName _PGPData_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "PGPData");
    private final static QName _DSAKeyValue_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "DSAKeyValue");
    private final static QName _AccesoDocumentoExpedienteResponse_QNAME = new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "accesoDocumentoExpedienteResponse");
    private final static QName _AccesoIndiceExpedienteResponse_QNAME = new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "accesoIndiceExpedienteResponse");
    private final static QName _SPKIData_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "SPKIData");
    private final static QName _ErrorInside_QNAME = new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService/types/error", "errorInside");
    private final static QName _SignedInfo_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "SignedInfo");
    private final static QName _RetrievalMethod_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "RetrievalMethod");
    private final static QName _CanonicalizationMethod_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "CanonicalizationMethod");
    private final static QName _Object_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "Object");
    private final static QName _MetadatosExp_QNAME = new QName("http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/metadatos", "metadatosExp");
    private final static QName _ExpedienteConversion_QNAME = new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e/conversion", "expedienteConversion");
    private final static QName _Metadatos_QNAME = new QName("http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/metadatos", "metadatos");
    private final static QName _Credential_QNAME = new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService/types/credential", "credential");
    private final static QName _Expediente_QNAME = new QName("http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e", "expediente");
    private final static QName _SignatureProperty_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "SignatureProperty");
    private final static QName _Firmas_QNAME = new QName("http://administracionelectronica.gob.es/ENI/XSD/v1.0/firma", "firmas");
    private final static QName _ComunicacionResultadoEnvioMJU_QNAME = new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "comunicacionResultadoEnvioMJU");
    private final static QName _DocumentoVisualizacion_QNAME = new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/visualizacion/documento-e", "documentoVisualizacion");
    private final static QName _ExpedienteResultadoBusqueda_QNAME = new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/busqueda/resultados", "ExpedienteResultadoBusqueda");
    private final static QName _Manifest_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "Manifest");
    private final static QName _SignatureValue_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "SignatureValue");
    private final static QName _Transforms_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "Transforms");
    private final static QName _Transform_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "Transform");
    private final static QName _X509Data_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "X509Data");
    private final static QName _ComunicacionResultadoEnvioMJUResponse_QNAME = new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "comunicacionResultadoEnvioMJUResponse");
    private final static QName _ExpedienteEniFile_QNAME = new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e/expedienteEniFile", "expedienteEniFile");
    private final static QName _Indice_QNAME = new QName("http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/indice-e", "indice");
    private final static QName _AccesoIndiceExpediente_QNAME = new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "accesoIndiceExpediente");
    private final static QName _SignatureMethod_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "SignatureMethod");
    private final static QName _Contenido_QNAME = new QName("http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/contenido", "contenido");
    private final static QName _KeyInfo_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "KeyInfo");
    private final static QName _DocumentoAlta_QNAME = new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/alta", "documentoAlta");
    private final static QName _DigestValue_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "DigestValue");
    private final static QName _DigestMethod_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "DigestMethod");
    private final static QName _ConsultaWsInside_QNAME = new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/busqueda", "consultaWsInside");
    private final static QName _SignatureProperties_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "SignatureProperties");
    private final static QName _MgmtData_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "MgmtData");
    private final static QName _KeyName_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "KeyName");
    private final static QName _KeyValue_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "KeyValue");
    private final static QName _Reference_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "Reference");
    private final static QName _Documento_QNAME = new QName("http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e", "documento");
    private final static QName _DocumentoResultadoBusqueda_QNAME = new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/busqueda/resultados", "DocumentoResultadoBusqueda");
    private final static QName _IndiceContenido_QNAME = new QName("http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/indice-e/contenido", "IndiceContenido");
    private final static QName _RSAKeyValue_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "RSAKeyValue");
    private final static QName _TransformTypeXPath_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "XPath");
    private final static QName _SPKIDataTypeSPKISexp_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "SPKISexp");
    private final static QName _X509DataTypeX509IssuerSerial_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "X509IssuerSerial");
    private final static QName _X509DataTypeX509Certificate_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "X509Certificate");
    private final static QName _X509DataTypeX509SKI_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "X509SKI");
    private final static QName _X509DataTypeX509SubjectName_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "X509SubjectName");
    private final static QName _X509DataTypeX509CRL_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "X509CRL");
    private final static QName _PGPDataTypePGPKeyID_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "PGPKeyID");
    private final static QName _PGPDataTypePGPKeyPacket_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "PGPKeyPacket");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: es.mpt.dsic.inside.remisionnube.client.modelo
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MetadatoBusquedaWsInside }
     * 
     */
    public MetadatoBusquedaWsInside createMetadatoBusquedaWsInside() {
        return new MetadatoBusquedaWsInside();
    }

    /**
     * Create an instance of {@link MetadatoBusquedaWsInside.Valor }
     * 
     */
    public MetadatoBusquedaWsInside.Valor createMetadatoBusquedaWsInsideValor() {
        return new MetadatoBusquedaWsInside.Valor();
    }

    /**
     * Create an instance of {@link TipoOpcionesVisualizacionIndice }
     * 
     */
    public TipoOpcionesVisualizacionIndice createTipoOpcionesVisualizacionIndice() {
        return new TipoOpcionesVisualizacionIndice();
    }

    /**
     * Create an instance of {@link TipoExpedienteConversionInside }
     * 
     */
    public TipoExpedienteConversionInside createTipoExpedienteConversionInside() {
        return new TipoExpedienteConversionInside();
    }

    /**
     * Create an instance of {@link TipoExpedienteConversionInside.MetadatosEni }
     * 
     */
    public TipoExpedienteConversionInside.MetadatosEni createTipoExpedienteConversionInsideMetadatosEni() {
        return new TipoExpedienteConversionInside.MetadatosEni();
    }

    /**
     * Create an instance of {@link TipoOpcionesVisualizacionDocumento }
     * 
     */
    public TipoOpcionesVisualizacionDocumento createTipoOpcionesVisualizacionDocumento() {
        return new TipoOpcionesVisualizacionDocumento();
    }

    /**
     * Create an instance of {@link TipoDocumentoConversionInside }
     * 
     */
    public TipoDocumentoConversionInside createTipoDocumentoConversionInside() {
        return new TipoDocumentoConversionInside();
    }

    /**
     * Create an instance of {@link DocumentoResultadoBusqueda }
     * 
     */
    public DocumentoResultadoBusqueda createDocumentoResultadoBusqueda() {
        return new DocumentoResultadoBusqueda();
    }

    /**
     * Create an instance of {@link ExpedienteResultadoBusqueda }
     * 
     */
    public ExpedienteResultadoBusqueda createExpedienteResultadoBusqueda() {
        return new ExpedienteResultadoBusqueda();
    }

    /**
     * Create an instance of {@link TipoDocumentoAltaInside }
     * 
     */
    public TipoDocumentoAltaInside createTipoDocumentoAltaInside() {
        return new TipoDocumentoAltaInside();
    }

    /**
     * Create an instance of {@link TipoMetadatos2 }
     * 
     */
    public TipoMetadatos2 createTipoMetadatos2() {
        return new TipoMetadatos2();
    }

    /**
     * Create an instance of {@link TipoFirmasElectronicas }
     * 
     */
    public TipoFirmasElectronicas createTipoFirmasElectronicas() {
        return new TipoFirmasElectronicas();
    }

    /**
     * Create an instance of {@link TipoFirmasElectronicas.ContenidoFirma }
     * 
     */
    public TipoFirmasElectronicas.ContenidoFirma createTipoFirmasElectronicasContenidoFirma() {
        return new TipoFirmasElectronicas.ContenidoFirma();
    }

    /**
     * Create an instance of {@link ExpedienteInsideMetadatos }
     * 
     */
    public ExpedienteInsideMetadatos createExpedienteInsideMetadatos() {
        return new ExpedienteInsideMetadatos();
    }

    /**
     * Create an instance of {@link Firmas }
     * 
     */
    public Firmas createFirmas() {
        return new Firmas();
    }

    /**
     * Create an instance of {@link KeyInfoType }
     * 
     */
    public KeyInfoType createKeyInfoType() {
        return new KeyInfoType();
    }

    /**
     * Create an instance of {@link SignedInfoType }
     * 
     */
    public SignedInfoType createSignedInfoType() {
        return new SignedInfoType();
    }

    /**
     * Create an instance of {@link RetrievalMethodType }
     * 
     */
    public RetrievalMethodType createRetrievalMethodType() {
        return new RetrievalMethodType();
    }

    /**
     * Create an instance of {@link DigestMethodType }
     * 
     */
    public DigestMethodType createDigestMethodType() {
        return new DigestMethodType();
    }

    /**
     * Create an instance of {@link SignatureMethodType }
     * 
     */
    public SignatureMethodType createSignatureMethodType() {
        return new SignatureMethodType();
    }

    /**
     * Create an instance of {@link SPKIDataType }
     * 
     */
    public SPKIDataType createSPKIDataType() {
        return new SPKIDataType();
    }

    /**
     * Create an instance of {@link X509DataType }
     * 
     */
    public X509DataType createX509DataType() {
        return new X509DataType();
    }

    /**
     * Create an instance of {@link PGPDataType }
     * 
     */
    public PGPDataType createPGPDataType() {
        return new PGPDataType();
    }

    /**
     * Create an instance of {@link SignatureType }
     * 
     */
    public SignatureType createSignatureType() {
        return new SignatureType();
    }

    /**
     * Create an instance of {@link DSAKeyValueType }
     * 
     */
    public DSAKeyValueType createDSAKeyValueType() {
        return new DSAKeyValueType();
    }

    /**
     * Create an instance of {@link ManifestType }
     * 
     */
    public ManifestType createManifestType() {
        return new ManifestType();
    }

    /**
     * Create an instance of {@link SignatureValueType }
     * 
     */
    public SignatureValueType createSignatureValueType() {
        return new SignatureValueType();
    }

    /**
     * Create an instance of {@link TransformsType }
     * 
     */
    public TransformsType createTransformsType() {
        return new TransformsType();
    }

    /**
     * Create an instance of {@link RSAKeyValueType }
     * 
     */
    public RSAKeyValueType createRSAKeyValueType() {
        return new RSAKeyValueType();
    }

    /**
     * Create an instance of {@link TransformType }
     * 
     */
    public TransformType createTransformType() {
        return new TransformType();
    }

    /**
     * Create an instance of {@link SignaturePropertyType }
     * 
     */
    public SignaturePropertyType createSignaturePropertyType() {
        return new SignaturePropertyType();
    }

    /**
     * Create an instance of {@link KeyValueType }
     * 
     */
    public KeyValueType createKeyValueType() {
        return new KeyValueType();
    }

    /**
     * Create an instance of {@link ReferenceType }
     * 
     */
    public ReferenceType createReferenceType() {
        return new ReferenceType();
    }

    /**
     * Create an instance of {@link CanonicalizationMethodType }
     * 
     */
    public CanonicalizationMethodType createCanonicalizationMethodType() {
        return new CanonicalizationMethodType();
    }

    /**
     * Create an instance of {@link SignaturePropertiesType }
     * 
     */
    public SignaturePropertiesType createSignaturePropertiesType() {
        return new SignaturePropertiesType();
    }

    /**
     * Create an instance of {@link ObjectType }
     * 
     */
    public ObjectType createObjectType() {
        return new ObjectType();
    }

    /**
     * Create an instance of {@link X509IssuerSerialType }
     * 
     */
    public X509IssuerSerialType createX509IssuerSerialType() {
        return new X509IssuerSerialType();
    }

    /**
     * Create an instance of {@link TipoIndiceContenido }
     * 
     */
    public TipoIndiceContenido createTipoIndiceContenido() {
        return new TipoIndiceContenido();
    }

    /**
     * Create an instance of {@link TipoDocumentoIndizado }
     * 
     */
    public TipoDocumentoIndizado createTipoDocumentoIndizado() {
        return new TipoDocumentoIndizado();
    }

    /**
     * Create an instance of {@link TipoCarpetaIndizada }
     * 
     */
    public TipoCarpetaIndizada createTipoCarpetaIndizada() {
        return new TipoCarpetaIndizada();
    }

    /**
     * Create an instance of {@link TipoIndice }
     * 
     */
    public TipoIndice createTipoIndice() {
        return new TipoIndice();
    }

    /**
     * Create an instance of {@link TipoMetadatos }
     * 
     */
    public TipoMetadatos createTipoMetadatos() {
        return new TipoMetadatos();
    }

    /**
     * Create an instance of {@link TipoEstadoElaboracion }
     * 
     */
    public TipoEstadoElaboracion createTipoEstadoElaboracion() {
        return new TipoEstadoElaboracion();
    }

    /**
     * Create an instance of {@link TipoDocumentoAsociadoaExpediente }
     * 
     */
    public TipoDocumentoAsociadoaExpediente createTipoDocumentoAsociadoaExpediente() {
        return new TipoDocumentoAsociadoaExpediente();
    }

    /**
     * Create an instance of {@link InfoResultadosBusquedaInside }
     * 
     */
    public InfoResultadosBusquedaInside createInfoResultadosBusquedaInside() {
        return new InfoResultadosBusquedaInside();
    }

    /**
     * Create an instance of {@link TipoDocumentoEniFileInside }
     * 
     */
    public TipoDocumentoEniFileInside createTipoDocumentoEniFileInside() {
        return new TipoDocumentoEniFileInside();
    }

    /**
     * Create an instance of {@link TipoMetadatosAdicionales }
     * 
     */
    public TipoMetadatosAdicionales createTipoMetadatosAdicionales() {
        return new TipoMetadatosAdicionales();
    }

    /**
     * Create an instance of {@link MetadatoAdicional }
     * 
     */
    public MetadatoAdicional createMetadatoAdicional() {
        return new MetadatoAdicional();
    }

    /**
     * Create an instance of {@link TipoVersionInside }
     * 
     */
    public TipoVersionInside createTipoVersionInside() {
        return new TipoVersionInside();
    }

    /**
     * Create an instance of {@link TipoDocumentoInside }
     * 
     */
    public TipoDocumentoInside createTipoDocumentoInside() {
        return new TipoDocumentoInside();
    }

    /**
     * Create an instance of {@link TipoExpedienteInside }
     * 
     */
    public TipoExpedienteInside createTipoExpedienteInside() {
        return new TipoExpedienteInside();
    }

    /**
     * Create an instance of {@link TipoDocumentoVisualizacionInside }
     * 
     */
    public TipoDocumentoVisualizacionInside createTipoDocumentoVisualizacionInside() {
        return new TipoDocumentoVisualizacionInside();
    }

    /**
     * Create an instance of {@link TipoDocumentoEniBinarioOTipo }
     * 
     */
    public TipoDocumentoEniBinarioOTipo createTipoDocumentoEniBinarioOTipo() {
        return new TipoDocumentoEniBinarioOTipo();
    }

    /**
     * Create an instance of {@link TipoResultadoVisualizacionDocumentoInside }
     * 
     */
    public TipoResultadoVisualizacionDocumentoInside createTipoResultadoVisualizacionDocumentoInside() {
        return new TipoResultadoVisualizacionDocumentoInside();
    }

    /**
     * Create an instance of {@link RespuestaSolicitarAccesoExpedienteType }
     * 
     */
    public RespuestaSolicitarAccesoExpedienteType createRespuestaSolicitarAccesoExpedienteType() {
        return new RespuestaSolicitarAccesoExpedienteType();
    }

    /**
     * Create an instance of {@link RespuestaComunicacionResultadoEnvioMJUType }
     * 
     */
    public RespuestaComunicacionResultadoEnvioMJUType createRespuestaComunicacionResultadoEnvioMJUType() {
        return new RespuestaComunicacionResultadoEnvioMJUType();
    }

    /**
     * Create an instance of {@link PeticionComunicacionTokenExpedienteType }
     * 
     */
    public PeticionComunicacionTokenExpedienteType createPeticionComunicacionTokenExpedienteType() {
        return new PeticionComunicacionTokenExpedienteType();
    }

    /**
     * Create an instance of {@link PeticionAccesoIndiceExpedienteType }
     * 
     */
    public PeticionAccesoIndiceExpedienteType createPeticionAccesoIndiceExpedienteType() {
        return new PeticionAccesoIndiceExpedienteType();
    }

    /**
     * Create an instance of {@link PeticionAccesoDocumentoExpedienteType }
     * 
     */
    public PeticionAccesoDocumentoExpedienteType createPeticionAccesoDocumentoExpedienteType() {
        return new PeticionAccesoDocumentoExpedienteType();
    }

    /**
     * Create an instance of {@link PeticionComunicacionResultadoEnvioMJUType }
     * 
     */
    public PeticionComunicacionResultadoEnvioMJUType createPeticionComunicacionResultadoEnvioMJUType() {
        return new PeticionComunicacionResultadoEnvioMJUType();
    }

    /**
     * Create an instance of {@link PeticionType }
     * 
     */
    public PeticionType createPeticionType() {
        return new PeticionType();
    }

    /**
     * Create an instance of {@link RespuestaType }
     * 
     */
    public RespuestaType createRespuestaType() {
        return new RespuestaType();
    }

    /**
     * Create an instance of {@link RespuestaInformarAccesoExpedienteType }
     * 
     */
    public RespuestaInformarAccesoExpedienteType createRespuestaInformarAccesoExpedienteType() {
        return new RespuestaInformarAccesoExpedienteType();
    }

    /**
     * Create an instance of {@link RespuestaRemisionAJusticiaType }
     * 
     */
    public RespuestaRemisionAJusticiaType createRespuestaRemisionAJusticiaType() {
        return new RespuestaRemisionAJusticiaType();
    }

    /**
     * Create an instance of {@link PeticionSolicitarAccesoExpedienteType }
     * 
     */
    public PeticionSolicitarAccesoExpedienteType createPeticionSolicitarAccesoExpedienteType() {
        return new PeticionSolicitarAccesoExpedienteType();
    }

    /**
     * Create an instance of {@link StringTokenType }
     * 
     */
    public StringTokenType createStringTokenType() {
        return new StringTokenType();
    }

    /**
     * Create an instance of {@link RespuestaAccesoDocumentoExpedienteType }
     * 
     */
    public RespuestaAccesoDocumentoExpedienteType createRespuestaAccesoDocumentoExpedienteType() {
        return new RespuestaAccesoDocumentoExpedienteType();
    }

    /**
     * Create an instance of {@link PeticionInformarAccesoExpedienteType }
     * 
     */
    public PeticionInformarAccesoExpedienteType createPeticionInformarAccesoExpedienteType() {
        return new PeticionInformarAccesoExpedienteType();
    }

    /**
     * Create an instance of {@link RespuestaAccesoIndiceExpedienteType }
     * 
     */
    public RespuestaAccesoIndiceExpedienteType createRespuestaAccesoIndiceExpedienteType() {
        return new RespuestaAccesoIndiceExpedienteType();
    }

    /**
     * Create an instance of {@link PeticionRemisionAJusticiaType }
     * 
     */
    public PeticionRemisionAJusticiaType createPeticionRemisionAJusticiaType() {
        return new PeticionRemisionAJusticiaType();
    }

    /**
     * Create an instance of {@link RespuestaComunicacionTokenExpedienteType }
     * 
     */
    public RespuestaComunicacionTokenExpedienteType createRespuestaComunicacionTokenExpedienteType() {
        return new RespuestaComunicacionTokenExpedienteType();
    }

    /**
     * Create an instance of {@link DatosRemisionJusticiaType }
     * 
     */
    public DatosRemisionJusticiaType createDatosRemisionJusticiaType() {
        return new DatosRemisionJusticiaType();
    }

    /**
     * Create an instance of {@link ParametrosRemision }
     * 
     */
    public ParametrosRemision createParametrosRemision() {
        return new ParametrosRemision();
    }

    /**
     * Create an instance of {@link DocumentoEniFileInsideConMAdicionales }
     * 
     */
    public DocumentoEniFileInsideConMAdicionales createDocumentoEniFileInsideConMAdicionales() {
        return new DocumentoEniFileInsideConMAdicionales();
    }

    /**
     * Create an instance of {@link DocumentoEniFileInside }
     * 
     */
    public DocumentoEniFileInside createDocumentoEniFileInside() {
        return new DocumentoEniFileInside();
    }

    /**
     * Create an instance of {@link TipoExpedienteEniFileInsideConDocumentos }
     * 
     */
    public TipoExpedienteEniFileInsideConDocumentos createTipoExpedienteEniFileInsideConDocumentos() {
        return new TipoExpedienteEniFileInsideConDocumentos();
    }

    /**
     * Create an instance of {@link ExpedienteInsideInfo }
     * 
     */
    public ExpedienteInsideInfo createExpedienteInsideInfo() {
        return new ExpedienteInsideInfo();
    }

    /**
     * Create an instance of {@link TipoExpedienteEniFileInside }
     * 
     */
    public TipoExpedienteEniFileInside createTipoExpedienteEniFileInside() {
        return new TipoExpedienteEniFileInside();
    }

    /**
     * Create an instance of {@link TipoExpediente }
     * 
     */
    public TipoExpediente createTipoExpediente() {
        return new TipoExpediente();
    }

    /**
     * Create an instance of {@link TipoDocumentoIndizadoConversion }
     * 
     */
    public TipoDocumentoIndizadoConversion createTipoDocumentoIndizadoConversion() {
        return new TipoDocumentoIndizadoConversion();
    }

    /**
     * Create an instance of {@link TipoIndiceConversion }
     * 
     */
    public TipoIndiceConversion createTipoIndiceConversion() {
        return new TipoIndiceConversion();
    }

    /**
     * Create an instance of {@link TipoCarpetaIndizadaConversion }
     * 
     */
    public TipoCarpetaIndizadaConversion createTipoCarpetaIndizadaConversion() {
        return new TipoCarpetaIndizadaConversion();
    }

    /**
     * Create an instance of {@link DocumentoInsideMetadatos }
     * 
     */
    public DocumentoInsideMetadatos createDocumentoInsideMetadatos() {
        return new DocumentoInsideMetadatos();
    }

    /**
     * Create an instance of {@link DocumentoInsideInfo }
     * 
     */
    public DocumentoInsideInfo createDocumentoInsideInfo() {
        return new DocumentoInsideInfo();
    }

    /**
     * Create an instance of {@link AccesoDocumentoExpediente }
     * 
     */
    public AccesoDocumentoExpediente createAccesoDocumentoExpediente() {
        return new AccesoDocumentoExpediente();
    }

    /**
     * Create an instance of {@link ComunicacionResultadoEnvioMJUResponse }
     * 
     */
    public ComunicacionResultadoEnvioMJUResponse createComunicacionResultadoEnvioMJUResponse() {
        return new ComunicacionResultadoEnvioMJUResponse();
    }

    /**
     * Create an instance of {@link AccesoIndiceExpediente }
     * 
     */
    public AccesoIndiceExpediente createAccesoIndiceExpediente() {
        return new AccesoIndiceExpediente();
    }

    /**
     * Create an instance of {@link AccesoIndiceExpedienteResponse }
     * 
     */
    public AccesoIndiceExpedienteResponse createAccesoIndiceExpedienteResponse() {
        return new AccesoIndiceExpedienteResponse();
    }

    /**
     * Create an instance of {@link AccesoDocumentoExpedienteResponse }
     * 
     */
    public AccesoDocumentoExpedienteResponse createAccesoDocumentoExpedienteResponse() {
        return new AccesoDocumentoExpedienteResponse();
    }

    /**
     * Create an instance of {@link ComunicacionResultadoEnvioMJU }
     * 
     */
    public ComunicacionResultadoEnvioMJU createComunicacionResultadoEnvioMJU() {
        return new ComunicacionResultadoEnvioMJU();
    }

    /**
     * Create an instance of {@link ExpedienteEniFileInsideConMAdicionales }
     * 
     */
    public ExpedienteEniFileInsideConMAdicionales createExpedienteEniFileInsideConMAdicionales() {
        return new ExpedienteEniFileInsideConMAdicionales();
    }

    /**
     * Create an instance of {@link ExpedienteEniFileInside }
     * 
     */
    public ExpedienteEniFileInside createExpedienteEniFileInside() {
        return new ExpedienteEniFileInside();
    }

    /**
     * Create an instance of {@link WSCredentialInside }
     * 
     */
    public WSCredentialInside createWSCredentialInside() {
        return new WSCredentialInside();
    }

    /**
     * Create an instance of {@link TipoDocumento }
     * 
     */
    public TipoDocumento createTipoDocumento() {
        return new TipoDocumento();
    }

    /**
     * Create an instance of {@link TipoContenido }
     * 
     */
    public TipoContenido createTipoContenido() {
        return new TipoContenido();
    }

    /**
     * Create an instance of {@link ConsultaWsInside }
     * 
     */
    public ConsultaWsInside createConsultaWsInside() {
        return new ConsultaWsInside();
    }

    /**
     * Create an instance of {@link SubConsultaWsInside }
     * 
     */
    public SubConsultaWsInside createSubConsultaWsInside() {
        return new SubConsultaWsInside();
    }

    /**
     * Create an instance of {@link WSErrorInside }
     * 
     */
    public WSErrorInside createWSErrorInside() {
        return new WSErrorInside();
    }

    /**
     * Create an instance of {@link MetadatoBusquedaWsInside.Valor.Between }
     * 
     */
    public MetadatoBusquedaWsInside.Valor.Between createMetadatoBusquedaWsInsideValorBetween() {
        return new MetadatoBusquedaWsInside.Valor.Between();
    }

    /**
     * Create an instance of {@link MetadatoBusquedaWsInside.Valor.DateRange }
     * 
     */
    public MetadatoBusquedaWsInside.Valor.DateRange createMetadatoBusquedaWsInsideValorDateRange() {
        return new MetadatoBusquedaWsInside.Valor.DateRange();
    }

    /**
     * Create an instance of {@link TipoOpcionesVisualizacionIndice.FilasNombreOrganismo }
     * 
     */
    public TipoOpcionesVisualizacionIndice.FilasNombreOrganismo createTipoOpcionesVisualizacionIndiceFilasNombreOrganismo() {
        return new TipoOpcionesVisualizacionIndice.FilasNombreOrganismo();
    }

    /**
     * Create an instance of {@link TipoExpedienteConversionInside.MetadatosEni.Estado }
     * 
     */
    public TipoExpedienteConversionInside.MetadatosEni.Estado createTipoExpedienteConversionInsideMetadatosEniEstado() {
        return new TipoExpedienteConversionInside.MetadatosEni.Estado();
    }

    /**
     * Create an instance of {@link TipoOpcionesVisualizacionDocumento.FilasNombreOrganismo }
     * 
     */
    public TipoOpcionesVisualizacionDocumento.FilasNombreOrganismo createTipoOpcionesVisualizacionDocumentoFilasNombreOrganismo() {
        return new TipoOpcionesVisualizacionDocumento.FilasNombreOrganismo();
    }

    /**
     * Create an instance of {@link TipoDocumentoConversionInside.MetadatosEni }
     * 
     */
    public TipoDocumentoConversionInside.MetadatosEni createTipoDocumentoConversionInsideMetadatosEni() {
        return new TipoDocumentoConversionInside.MetadatosEni();
    }

    /**
     * Create an instance of {@link TipoDocumentoConversionInside.Csv }
     * 
     */
    public TipoDocumentoConversionInside.Csv createTipoDocumentoConversionInsideCsv() {
        return new TipoDocumentoConversionInside.Csv();
    }

    /**
     * Create an instance of {@link DocumentoResultadoBusqueda.Resultados }
     * 
     */
    public DocumentoResultadoBusqueda.Resultados createDocumentoResultadoBusquedaResultados() {
        return new DocumentoResultadoBusqueda.Resultados();
    }

    /**
     * Create an instance of {@link ExpedienteResultadoBusqueda.Resultados }
     * 
     */
    public ExpedienteResultadoBusqueda.Resultados createExpedienteResultadoBusquedaResultados() {
        return new ExpedienteResultadoBusqueda.Resultados();
    }

    /**
     * Create an instance of {@link TipoDocumentoAltaInside.MetadatosEni }
     * 
     */
    public TipoDocumentoAltaInside.MetadatosEni createTipoDocumentoAltaInsideMetadatosEni() {
        return new TipoDocumentoAltaInside.MetadatosEni();
    }

    /**
     * Create an instance of {@link TipoDocumentoAltaInside.Csv }
     * 
     */
    public TipoDocumentoAltaInside.Csv createTipoDocumentoAltaInsideCsv() {
        return new TipoDocumentoAltaInside.Csv();
    }

    /**
     * Create an instance of {@link TipoMetadatos2 .Estado }
     * 
     */
    public TipoMetadatos2 .Estado createTipoMetadatos2Estado() {
        return new TipoMetadatos2 .Estado();
    }

    /**
     * Create an instance of {@link TipoFirmasElectronicas.ContenidoFirma.CSV }
     * 
     */
    public TipoFirmasElectronicas.ContenidoFirma.CSV createTipoFirmasElectronicasContenidoFirmaCSV() {
        return new TipoFirmasElectronicas.ContenidoFirma.CSV();
    }

    /**
     * Create an instance of {@link TipoFirmasElectronicas.ContenidoFirma.FirmaConCertificado }
     * 
     */
    public TipoFirmasElectronicas.ContenidoFirma.FirmaConCertificado createTipoFirmasElectronicasContenidoFirmaFirmaConCertificado() {
        return new TipoFirmasElectronicas.ContenidoFirma.FirmaConCertificado();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "HMACOutputLength", scope = SignatureMethodType.class)
    public JAXBElement<BigInteger> createSignatureMethodTypeHMACOutputLength(BigInteger value) {
        return new JAXBElement<BigInteger>(_SignatureMethodTypeHMACOutputLength_QNAME, BigInteger.class, SignatureMethodType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AccesoDocumentoExpediente }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", name = "accesoDocumentoExpediente")
    public JAXBElement<AccesoDocumentoExpediente> createAccesoDocumentoExpediente(AccesoDocumentoExpediente value) {
        return new JAXBElement<AccesoDocumentoExpediente>(_AccesoDocumentoExpediente_QNAME, AccesoDocumentoExpediente.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoDocumentoEniFileInside }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/documentoEniFile", name = "documentoEniFile")
    public JAXBElement<TipoDocumentoEniFileInside> createDocumentoEniFile(TipoDocumentoEniFileInside value) {
        return new JAXBElement<TipoDocumentoEniFileInside>(_DocumentoEniFile_QNAME, TipoDocumentoEniFileInside.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoDocumentoConversionInside }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/conversion", name = "documentoConversion")
    public JAXBElement<TipoDocumentoConversionInside> createDocumentoConversion(TipoDocumentoConversionInside value) {
        return new JAXBElement<TipoDocumentoConversionInside>(_DocumentoConversion_QNAME, TipoDocumentoConversionInside.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SignatureType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "Signature")
    public JAXBElement<SignatureType> createSignature(SignatureType value) {
        return new JAXBElement<SignatureType>(_Signature_QNAME, SignatureType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PGPDataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "PGPData")
    public JAXBElement<PGPDataType> createPGPData(PGPDataType value) {
        return new JAXBElement<PGPDataType>(_PGPData_QNAME, PGPDataType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DSAKeyValueType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "DSAKeyValue")
    public JAXBElement<DSAKeyValueType> createDSAKeyValue(DSAKeyValueType value) {
        return new JAXBElement<DSAKeyValueType>(_DSAKeyValue_QNAME, DSAKeyValueType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AccesoDocumentoExpedienteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", name = "accesoDocumentoExpedienteResponse")
    public JAXBElement<AccesoDocumentoExpedienteResponse> createAccesoDocumentoExpedienteResponse(AccesoDocumentoExpedienteResponse value) {
        return new JAXBElement<AccesoDocumentoExpedienteResponse>(_AccesoDocumentoExpedienteResponse_QNAME, AccesoDocumentoExpedienteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AccesoIndiceExpedienteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", name = "accesoIndiceExpedienteResponse")
    public JAXBElement<AccesoIndiceExpedienteResponse> createAccesoIndiceExpedienteResponse(AccesoIndiceExpedienteResponse value) {
        return new JAXBElement<AccesoIndiceExpedienteResponse>(_AccesoIndiceExpedienteResponse_QNAME, AccesoIndiceExpedienteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SPKIDataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "SPKIData")
    public JAXBElement<SPKIDataType> createSPKIData(SPKIDataType value) {
        return new JAXBElement<SPKIDataType>(_SPKIData_QNAME, SPKIDataType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSErrorInside }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService/types/error", name = "errorInside")
    public JAXBElement<WSErrorInside> createErrorInside(WSErrorInside value) {
        return new JAXBElement<WSErrorInside>(_ErrorInside_QNAME, WSErrorInside.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SignedInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "SignedInfo")
    public JAXBElement<SignedInfoType> createSignedInfo(SignedInfoType value) {
        return new JAXBElement<SignedInfoType>(_SignedInfo_QNAME, SignedInfoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrievalMethodType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "RetrievalMethod")
    public JAXBElement<RetrievalMethodType> createRetrievalMethod(RetrievalMethodType value) {
        return new JAXBElement<RetrievalMethodType>(_RetrievalMethod_QNAME, RetrievalMethodType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CanonicalizationMethodType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "CanonicalizationMethod")
    public JAXBElement<CanonicalizationMethodType> createCanonicalizationMethod(CanonicalizationMethodType value) {
        return new JAXBElement<CanonicalizationMethodType>(_CanonicalizationMethod_QNAME, CanonicalizationMethodType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObjectType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "Object")
    public JAXBElement<ObjectType> createObject(ObjectType value) {
        return new JAXBElement<ObjectType>(_Object_QNAME, ObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoMetadatos2 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/metadatos", name = "metadatosExp")
    public JAXBElement<TipoMetadatos2> createMetadatosExp(TipoMetadatos2 value) {
        return new JAXBElement<TipoMetadatos2>(_MetadatosExp_QNAME, TipoMetadatos2 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoExpedienteConversionInside }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e/conversion", name = "expedienteConversion")
    public JAXBElement<TipoExpedienteConversionInside> createExpedienteConversion(TipoExpedienteConversionInside value) {
        return new JAXBElement<TipoExpedienteConversionInside>(_ExpedienteConversion_QNAME, TipoExpedienteConversionInside.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoMetadatos }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/metadatos", name = "metadatos")
    public JAXBElement<TipoMetadatos> createMetadatos(TipoMetadatos value) {
        return new JAXBElement<TipoMetadatos>(_Metadatos_QNAME, TipoMetadatos.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSCredentialInside }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService/types/credential", name = "credential")
    public JAXBElement<WSCredentialInside> createCredential(WSCredentialInside value) {
        return new JAXBElement<WSCredentialInside>(_Credential_QNAME, WSCredentialInside.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoExpediente }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e", name = "expediente")
    public JAXBElement<TipoExpediente> createExpediente(TipoExpediente value) {
        return new JAXBElement<TipoExpediente>(_Expediente_QNAME, TipoExpediente.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SignaturePropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "SignatureProperty")
    public JAXBElement<SignaturePropertyType> createSignatureProperty(SignaturePropertyType value) {
        return new JAXBElement<SignaturePropertyType>(_SignatureProperty_QNAME, SignaturePropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Firmas }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://administracionelectronica.gob.es/ENI/XSD/v1.0/firma", name = "firmas")
    public JAXBElement<Firmas> createFirmas(Firmas value) {
        return new JAXBElement<Firmas>(_Firmas_QNAME, Firmas.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ComunicacionResultadoEnvioMJU }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", name = "comunicacionResultadoEnvioMJU")
    public JAXBElement<ComunicacionResultadoEnvioMJU> createComunicacionResultadoEnvioMJU(ComunicacionResultadoEnvioMJU value) {
        return new JAXBElement<ComunicacionResultadoEnvioMJU>(_ComunicacionResultadoEnvioMJU_QNAME, ComunicacionResultadoEnvioMJU.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoDocumentoVisualizacionInside }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/visualizacion/documento-e", name = "documentoVisualizacion")
    public JAXBElement<TipoDocumentoVisualizacionInside> createDocumentoVisualizacion(TipoDocumentoVisualizacionInside value) {
        return new JAXBElement<TipoDocumentoVisualizacionInside>(_DocumentoVisualizacion_QNAME, TipoDocumentoVisualizacionInside.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExpedienteResultadoBusqueda }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/busqueda/resultados", name = "ExpedienteResultadoBusqueda")
    public JAXBElement<ExpedienteResultadoBusqueda> createExpedienteResultadoBusqueda(ExpedienteResultadoBusqueda value) {
        return new JAXBElement<ExpedienteResultadoBusqueda>(_ExpedienteResultadoBusqueda_QNAME, ExpedienteResultadoBusqueda.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ManifestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "Manifest")
    public JAXBElement<ManifestType> createManifest(ManifestType value) {
        return new JAXBElement<ManifestType>(_Manifest_QNAME, ManifestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SignatureValueType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "SignatureValue")
    public JAXBElement<SignatureValueType> createSignatureValue(SignatureValueType value) {
        return new JAXBElement<SignatureValueType>(_SignatureValue_QNAME, SignatureValueType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransformsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "Transforms")
    public JAXBElement<TransformsType> createTransforms(TransformsType value) {
        return new JAXBElement<TransformsType>(_Transforms_QNAME, TransformsType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransformType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "Transform")
    public JAXBElement<TransformType> createTransform(TransformType value) {
        return new JAXBElement<TransformType>(_Transform_QNAME, TransformType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link X509DataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "X509Data")
    public JAXBElement<X509DataType> createX509Data(X509DataType value) {
        return new JAXBElement<X509DataType>(_X509Data_QNAME, X509DataType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ComunicacionResultadoEnvioMJUResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", name = "comunicacionResultadoEnvioMJUResponse")
    public JAXBElement<ComunicacionResultadoEnvioMJUResponse> createComunicacionResultadoEnvioMJUResponse(ComunicacionResultadoEnvioMJUResponse value) {
        return new JAXBElement<ComunicacionResultadoEnvioMJUResponse>(_ComunicacionResultadoEnvioMJUResponse_QNAME, ComunicacionResultadoEnvioMJUResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoExpedienteEniFileInside }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e/expedienteEniFile", name = "expedienteEniFile")
    public JAXBElement<TipoExpedienteEniFileInside> createExpedienteEniFile(TipoExpedienteEniFileInside value) {
        return new JAXBElement<TipoExpedienteEniFileInside>(_ExpedienteEniFile_QNAME, TipoExpedienteEniFileInside.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoIndice }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/indice-e", name = "indice")
    public JAXBElement<TipoIndice> createIndice(TipoIndice value) {
        return new JAXBElement<TipoIndice>(_Indice_QNAME, TipoIndice.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AccesoIndiceExpediente }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", name = "accesoIndiceExpediente")
    public JAXBElement<AccesoIndiceExpediente> createAccesoIndiceExpediente(AccesoIndiceExpediente value) {
        return new JAXBElement<AccesoIndiceExpediente>(_AccesoIndiceExpediente_QNAME, AccesoIndiceExpediente.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SignatureMethodType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "SignatureMethod")
    public JAXBElement<SignatureMethodType> createSignatureMethod(SignatureMethodType value) {
        return new JAXBElement<SignatureMethodType>(_SignatureMethod_QNAME, SignatureMethodType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoContenido }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/contenido", name = "contenido")
    public JAXBElement<TipoContenido> createContenido(TipoContenido value) {
        return new JAXBElement<TipoContenido>(_Contenido_QNAME, TipoContenido.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link KeyInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "KeyInfo")
    public JAXBElement<KeyInfoType> createKeyInfo(KeyInfoType value) {
        return new JAXBElement<KeyInfoType>(_KeyInfo_QNAME, KeyInfoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoDocumentoAltaInside }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e/alta", name = "documentoAlta")
    public JAXBElement<TipoDocumentoAltaInside> createDocumentoAlta(TipoDocumentoAltaInside value) {
        return new JAXBElement<TipoDocumentoAltaInside>(_DocumentoAlta_QNAME, TipoDocumentoAltaInside.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "DigestValue")
    public JAXBElement<byte[]> createDigestValue(byte[] value) {
        return new JAXBElement<byte[]>(_DigestValue_QNAME, byte[].class, null, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DigestMethodType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "DigestMethod")
    public JAXBElement<DigestMethodType> createDigestMethod(DigestMethodType value) {
        return new JAXBElement<DigestMethodType>(_DigestMethod_QNAME, DigestMethodType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaWsInside }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/busqueda", name = "consultaWsInside")
    public JAXBElement<ConsultaWsInside> createConsultaWsInside(ConsultaWsInside value) {
        return new JAXBElement<ConsultaWsInside>(_ConsultaWsInside_QNAME, ConsultaWsInside.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SignaturePropertiesType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "SignatureProperties")
    public JAXBElement<SignaturePropertiesType> createSignatureProperties(SignaturePropertiesType value) {
        return new JAXBElement<SignaturePropertiesType>(_SignatureProperties_QNAME, SignaturePropertiesType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "MgmtData")
    public JAXBElement<String> createMgmtData(String value) {
        return new JAXBElement<String>(_MgmtData_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "KeyName")
    public JAXBElement<String> createKeyName(String value) {
        return new JAXBElement<String>(_KeyName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link KeyValueType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "KeyValue")
    public JAXBElement<KeyValueType> createKeyValue(KeyValueType value) {
        return new JAXBElement<KeyValueType>(_KeyValue_QNAME, KeyValueType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReferenceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "Reference")
    public JAXBElement<ReferenceType> createReference(ReferenceType value) {
        return new JAXBElement<ReferenceType>(_Reference_QNAME, ReferenceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoDocumento }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e", name = "documento")
    public JAXBElement<TipoDocumento> createDocumento(TipoDocumento value) {
        return new JAXBElement<TipoDocumento>(_Documento_QNAME, TipoDocumento.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DocumentoResultadoBusqueda }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/busqueda/resultados", name = "DocumentoResultadoBusqueda")
    public JAXBElement<DocumentoResultadoBusqueda> createDocumentoResultadoBusqueda(DocumentoResultadoBusqueda value) {
        return new JAXBElement<DocumentoResultadoBusqueda>(_DocumentoResultadoBusqueda_QNAME, DocumentoResultadoBusqueda.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoIndiceContenido }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/indice-e/contenido", name = "IndiceContenido")
    public JAXBElement<TipoIndiceContenido> createIndiceContenido(TipoIndiceContenido value) {
        return new JAXBElement<TipoIndiceContenido>(_IndiceContenido_QNAME, TipoIndiceContenido.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RSAKeyValueType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "RSAKeyValue")
    public JAXBElement<RSAKeyValueType> createRSAKeyValue(RSAKeyValueType value) {
        return new JAXBElement<RSAKeyValueType>(_RSAKeyValue_QNAME, RSAKeyValueType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "XPath", scope = TransformType.class)
    public JAXBElement<String> createTransformTypeXPath(String value) {
        return new JAXBElement<String>(_TransformTypeXPath_QNAME, String.class, TransformType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "SPKISexp", scope = SPKIDataType.class)
    public JAXBElement<byte[]> createSPKIDataTypeSPKISexp(byte[] value) {
        return new JAXBElement<byte[]>(_SPKIDataTypeSPKISexp_QNAME, byte[].class, SPKIDataType.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link X509IssuerSerialType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "X509IssuerSerial", scope = X509DataType.class)
    public JAXBElement<X509IssuerSerialType> createX509DataTypeX509IssuerSerial(X509IssuerSerialType value) {
        return new JAXBElement<X509IssuerSerialType>(_X509DataTypeX509IssuerSerial_QNAME, X509IssuerSerialType.class, X509DataType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "X509Certificate", scope = X509DataType.class)
    public JAXBElement<byte[]> createX509DataTypeX509Certificate(byte[] value) {
        return new JAXBElement<byte[]>(_X509DataTypeX509Certificate_QNAME, byte[].class, X509DataType.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "X509SKI", scope = X509DataType.class)
    public JAXBElement<byte[]> createX509DataTypeX509SKI(byte[] value) {
        return new JAXBElement<byte[]>(_X509DataTypeX509SKI_QNAME, byte[].class, X509DataType.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "X509SubjectName", scope = X509DataType.class)
    public JAXBElement<String> createX509DataTypeX509SubjectName(String value) {
        return new JAXBElement<String>(_X509DataTypeX509SubjectName_QNAME, String.class, X509DataType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "X509CRL", scope = X509DataType.class)
    public JAXBElement<byte[]> createX509DataTypeX509CRL(byte[] value) {
        return new JAXBElement<byte[]>(_X509DataTypeX509CRL_QNAME, byte[].class, X509DataType.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "PGPKeyID", scope = PGPDataType.class)
    public JAXBElement<byte[]> createPGPDataTypePGPKeyID(byte[] value) {
        return new JAXBElement<byte[]>(_PGPDataTypePGPKeyID_QNAME, byte[].class, PGPDataType.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "PGPKeyPacket", scope = PGPDataType.class)
    public JAXBElement<byte[]> createPGPDataTypePGPKeyPacket(byte[] value) {
        return new JAXBElement<byte[]>(_PGPDataTypePGPKeyPacket_QNAME, byte[].class, PGPDataType.class, ((byte[]) value));
    }

}
