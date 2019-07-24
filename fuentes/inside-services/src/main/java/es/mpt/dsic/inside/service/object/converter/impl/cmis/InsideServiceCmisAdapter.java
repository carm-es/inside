/*
 * Copyright (C) 2016 MINHAP, Gobierno de España This program is licensed and may be used, modified
 * and redistributed under the terms of the European Public License (EUPL), either version 1.1 or
 * (at your option) any later version as soon as they are approved by the European Commission.
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and more details. You
 * should have received a copy of the EUPL1.1 license along with this program; if not, you may find
 * it at http://joinup.ec.europa.eu/software/page/eupl/licence-eupl
 */

package es.mpt.dsic.inside.service.object.converter.impl.cmis;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import es.mpt.dsic.inside.model.converter.InsideConverterDocumento;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInside;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInsideContenido;
import es.mpt.dsic.inside.model.objetos.documento.metadatos.ObjetoDocumentoInsideMetadatos;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteInside;
import es.mpt.dsic.inside.model.objetos.expediente.metadatos.ObjetoExpedienteInsideMetadatos;
import es.mpt.dsic.inside.model.objetos.firmas.FirmaInside;
import es.mpt.dsic.inside.model.objetos.firmas.contenido.ContenidoFirmaCertificadoAlmacenableInside;
import es.mpt.dsic.inside.model.objetos.firmas.contenido.ContenidoFirmaInside;
import es.mpt.dsic.inside.service.InSideService;
import es.mpt.dsic.inside.service.object.converter.impl.InsideServiceAdapter;
import es.mpt.dsic.inside.service.object.converter.impl.InsideServiceAdapterException;
import es.mpt.dsic.inside.service.store.InsideServiceJta;
import es.mpt.dsic.inside.service.store.exception.InsideStoreObjectNotFoundException;
import es.mpt.dsic.inside.service.util.JAXBMarshallerDocument;
import es.mpt.dsic.inside.store.hibernate.entity.InsideWsAplicacion;
import es.mpt.dsic.inside.store.hibernate.entity.UnidadOrganica;
import es.mpt.dsic.inside.store.hibernate.entity.UnidadUsuario;
import es.mpt.dsic.inside.store.hibernate.entity.UnidadWsAplicacion;
import es.mpt.dsic.inside.store.hibernate.entity.UsuarioInside;
import es.mpt.dsic.inside.xml.eni.documento.TipoDocumento;
import es.mpt.dsic.integration.cmis.adapter.CmisAdapterInterface;
import es.mpt.dsic.integration.cmis.exception.RepositoryCmisException;
import es.mpt.dsic.integration.cmis.model.ContenidoDocumento;

public class InsideServiceCmisAdapter implements InsideServiceAdapter {

  protected static final Log logger = LogFactory.getLog(InsideServiceCmisAdapter.class);

  @Autowired
  private InsideServiceJta insideServiceJta;

  private SessionFactory sessionFactory;

  protected String repoObjectId;
  protected String repoVersionLabel;
  protected String repoPathSeparator;

  protected String documentoObjectTypeId;
  protected String documentoDIR3;
  protected String documentoIdENI;
  protected String documentoCSV;

  private String dir3Default;
  private String almacenarFirma;

  protected CmisAdapterInterface cmisAdapter;

  @Autowired
  private InSideService insideService;

  @PostConstruct
  void config() {

  }


  /**
   * Almacena una firma en el repositorio CMIS un conjunto de firmas
   * 
   * @param firmas Las firmas a almacenar en el repositorio
   * @param folder Objeto folder donde serán almacenadas.
   * @throws RepositoryCmisException
   * @throws InsideServiceAdapterException
   */
  private void almacenaFirmasInside(List<FirmaInside> firmas, String[] folder, String idENI)
      throws InsideServiceAdapterException {

    // Obtenemos el dir3 del usuario logado
    String dirusuario = getUserDir();

    if (dirusuario == null) {
      dirusuario = dir3Default;
    }

    Map<String, Object> metadatosRepository = new HashMap<String, Object>();
    if (StringUtils.isNotEmpty(documentoDIR3)) {
      metadatosRepository.put(documentoDIR3, dirusuario);
    }

    for (FirmaInside firma : firmas) {
      if (firma.getContenidoFirma() != null && firma.getContenidoFirma() != null) {
        ContenidoFirmaInside contenidoFirma = firma.getContenidoFirma();
        if (contenidoFirma instanceof ContenidoFirmaCertificadoAlmacenableInside) {
          ContenidoFirmaCertificadoAlmacenableInside contenidoFirmaAlmacenado =
              (ContenidoFirmaCertificadoAlmacenableInside) contenidoFirma;
          try {
            String firmaName = idENI + "_" + firma.getIdentificadorEnDocumento() + "_"
                + System.currentTimeMillis();

            if (StringUtils.isNotEmpty(documentoIdENI)) {
              metadatosRepository.put(documentoIdENI, firmaName);
            }
            Document documentoFirmaCmis = cmisAdapter.altaDocumento(firmaName, folder,
                documentoObjectTypeId, contenidoFirmaAlmacenado.getValorBinario(),
                contenidoFirmaAlmacenado.getMime(), metadatosRepository, false);
            contenidoFirmaAlmacenado.setValorBinario(null);
            contenidoFirmaAlmacenado
                .setIdentificadorRepositorio((String) documentoFirmaCmis.getId());

          } catch (RepositoryCmisException e) {
            throw new InsideServiceAdapterException("Error almacenando la firma");
          }
        }

      }
    }
  }

  public ObjetoExpedienteInside almacenaObjetoExpedienteInside(ObjetoExpedienteInside expediente,
      byte[] bytesContenido) throws InsideServiceAdapterException {

    try {

      // Obtenemos el dir3 del usuario logado
      String dirusuario = getUserDir();

      if (dirusuario == null) {
        dirusuario = dir3Default;
      }

      ObjetoExpedienteInsideMetadatos metadatosEntrada = expediente.getMetadatos();

      if (metadatosEntrada == null) {
        throw new InsideServiceAdapterException(
            "Los metadatos del Expediente de entrada no pueden ser nulos");
      }

      // El expediente no se guarda en la NAS solo se guarda la firma
      if (expediente.getIdentificadorRepositorio() == null) {
        expediente.setIdentificadorRepositorio(expediente.getIdentificador());
      }


      // Almacenaremos la firma si es almacenable
      if (expediente.getIndice() != null && expediente.getIndice().getFirmas() != null
          && Boolean.toString(true).equals(almacenarFirma)) {

        Calendar today = GregorianCalendar.getInstance();
        // Estructura de carpetas: AÑO/MES/DIA/DIR3
        String[] rutaFirma =
            {String.valueOf(today.get(Calendar.YEAR)), String.valueOf(today.get(Calendar.MONTH)),
                String.valueOf(today.get(Calendar.DAY_OF_MONTH)), dirusuario};

        this.almacenaFirmasInside(expediente.getIndice().getFirmas(), rutaFirma,
            expediente.getIdentificador());
      }

    } catch (Exception e) {
      throw new InsideServiceAdapterException("Error convirtiendo el ExpedinteInside a xml", e);
    }
    return expediente;

  }



  /**
   * Almacena un documento electrónico en el repositorio CMIS, siempre que éste tenga contenido. Si
   * no estaba almacenado crea su carpeta En caso contrario modifica sus metadatos y almacena firmas
   * nuevas
   * 
   * @param documento
   * @return
   * @throws InsideServiceAdapterException
   */
  public ObjetoDocumentoInside almacenaObjetoDocumentoInside(ObjetoDocumentoInside documento)
      throws InsideServiceAdapterException {
    ObjetoDocumentoInsideMetadatos metadatosEntrada = documento.getMetadatos();

    if (metadatosEntrada == null) {
      throw new InsideServiceAdapterException(
          "Los metadatos del documento de entrada no pueden ser nulos");
    }

    // Obtenemos el dir3 del usuario logado
    String dirusuario = getUserDir();

    if (dirusuario == null) {
      dirusuario = dir3Default;
    }


    logger.debug("IdentificadorRepositorio: " + documento.getIdentificadorRepositorio());

    Map<String, Object> metadatosRepository = new HashMap<String, Object>();
    if (StringUtils.isNotEmpty(documentoDIR3)) {
      metadatosRepository.put(documentoDIR3, dirusuario);
    }
    if (StringUtils.isNotEmpty(documentoIdENI)) {
      metadatosRepository.put(documentoIdENI, documento.getIdentificador());
    }

    Document documentoCmis = null;
    try {

      TipoDocumento tipoDocumento = InsideConverterDocumento.documentoInsideToEni(documento, null);

      JAXBMarshallerDocument marshaller = new JAXBMarshallerDocument();
      String docMarshall = marshaller.marshallDataDocument(tipoDocumento,
          "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e", "documento",
          "enidoc");

      byte[] contentMarshal = docMarshall.getBytes();
      String mimeType = "application/xml";

      try {
        ObjetoDocumentoInside objetoDocumentoInsideBbdd =
            insideService.getDocumento(documento.getIdentificador());
        if (objetoDocumentoInsideBbdd != null) {
          documento
              .setIdentificadorRepositorio(objetoDocumentoInsideBbdd.getIdentificadorRepositorio());
        }
      } catch (InsideStoreObjectNotFoundException e) {
        logger.info("El documento no está guardado en bbdd");
      }

      if (documento.getIdentificadorRepositorio() != null) {
        ContenidoDocumento contenidoDocumento = new ContenidoDocumento();
        contenidoDocumento.setContenido(contentMarshal);
        contenidoDocumento.setTipoMIME(mimeType);

        cmisAdapter.modificarDocumento(documento.getIdentificadorRepositorio(), metadatosRepository,
            contenidoDocumento);

      } else {
        Calendar today = GregorianCalendar.getInstance();
        // Estructura de carpetas: AÑO/MES/DIA/DIR3
        String[] rutaDocumentoAlta =
            {String.valueOf(today.get(Calendar.YEAR)), String.valueOf(today.get(Calendar.MONTH)),
                String.valueOf(today.get(Calendar.DAY_OF_MONTH)), dirusuario};

        String docName = documento.getIdentificador() + "_" + System.currentTimeMillis();

        documentoCmis = cmisAdapter.altaDocumento(docName, rutaDocumentoAlta, documentoObjectTypeId,
            contentMarshal, mimeType, metadatosRepository, true);

        documento.setIdentificadorRepositorio((String) documentoCmis.getId());
      }
    } catch (RepositoryCmisException rce) {
      String mensajeError = String
          .format("No ha podido almacenar el documento con identificador %s en el repositorio "
              + rce.getMessage(), documento.getIdentificador());
      logger.error(mensajeError);
      throw new InsideServiceAdapterException(mensajeError, rce);
    } catch (Exception rce) {
      String mensajeError = String
          .format("No ha podido insertarse el documento con identificador %s en el repositorio: "
              + rce.getMessage(), documento.getIdentificador());
      logger.error(mensajeError);
      throw new InsideServiceAdapterException(mensajeError, rce);
    }

    return documento;

  }



  public ObjetoDocumentoInside recuperaDocumentoInside(ObjetoDocumentoInside documento)
      throws InsideServiceAdapterException {
    if (documento.getIdentificadorRepositorio() != null) {

      ContenidoDocumento contenidoDocumento = null;

      try {
        // Bytes del documento
        contenidoDocumento = cmisAdapter.getContenidoDoc(documento.getIdentificadorRepositorio());

        if (contenidoDocumento != null && contenidoDocumento.getContenido().length > 0) {
          convertDocumentResponseToObjetoDocumentoInside(contenidoDocumento.getContenido(),
              documento);
        }

      } catch (Exception rce) {
        String mensajeError = String.format(
            "No ha podido obtenerse el documento con identificador %s " + rce.getMessage(),
            documento.getIdentificador());
        logger.error(mensajeError);
        throw new InsideServiceAdapterException(mensajeError, rce);
      }

    }

    logger.debug("convertFromRepository: documento con identificador de Repositorio "
        + documento.getIdentificadorRepositorio() + " convertido correctamente");
    return documento;

  }

  /**
   * Convert document eni response to objeto documento inside.
   *
   * @param documentoENIrespone the documento en irespone
   * @param documento the documento
   * @return the objeto documento inside
   * @throws InsideServiceAdapterException error
   */
  private void convertDocumentResponseToObjetoDocumentoInside(byte[] contenido,
      ObjetoDocumentoInside documento) throws InsideServiceAdapterException {
    try {
      boolean esValido = false;
      if (contenido != null) {

        Object contenidoDeserializado = null;

        try {

          JAXBMarshallerDocument marshaller = new JAXBMarshallerDocument();
          TipoDocumento tipoDocumento = marshaller.unmarshallDataDocument(contenido);

          ObjetoDocumentoInside documentoAux = InsideConverterDocumento
              .documentoEniAndMetadatosToDocumentoInside(tipoDocumento, null);
          documento.setContenido(documentoAux.getContenido());
          documento.setFirmas(documentoAux.getFirmas());

          esValido = true;
        } catch (Exception e) {
          logger.info("El contenido no es un documeto ENI");
          // Para recuperar documentos de versiones anteriores almacenados como
          // ObjetoDocumentoInside
          contenidoDeserializado = SerializationUtils.deserialize(contenido);
          if (contenidoDeserializado instanceof ObjetoDocumentoInside) {

            ObjetoDocumentoInside documentoAux = (ObjetoDocumentoInside) contenidoDeserializado;
            documento.setContenido(documentoAux.getContenido());
            documento.setFirmas(documentoAux.getFirmas());

            esValido = true;

          }

        }

        if (!esValido) {
          logger.error("Error al convertir el documento a ENI o deserializarlo");
          throw new InsideServiceAdapterException("Error recuperar el documento");
        }
      }
    } catch (Exception e) {
      logger.error("Error al convertir el documento", e);
      throw new InsideServiceAdapterException("Error recuperar el documento");
    }
  }


  public ObjetoExpedienteInside recuperaExpedienteInside(ObjetoExpedienteInside expediente)
      throws InsideServiceAdapterException {
    if (StringUtils.isEmpty(expediente.getIdentificadorRepositorio())) {
      throw new InsideServiceAdapterException(
          "El identificador del repositorio del expediente no puede ser nulo");
    }

    try {

      // Firmas del expediente
      if (expediente.getIndice().getFirmas() != null) {

        for (FirmaInside firma : expediente.getIndice().getFirmas()) {
          ContenidoFirmaInside contenidoFirma = firma.getContenidoFirma();
          if (contenidoFirma != null
              && contenidoFirma instanceof ContenidoFirmaCertificadoAlmacenableInside) {
            ContenidoFirmaCertificadoAlmacenableInside contenidoFirmaAlmacenado =
                (ContenidoFirmaCertificadoAlmacenableInside) contenidoFirma;

            if (contenidoFirmaAlmacenado.getIdentificadorRepositorio() != null) {

              byte[] bytesFirma = cmisAdapter
                  .getContenidoDoc(contenidoFirmaAlmacenado.getIdentificadorRepositorio())
                  .getContenido();
              contenidoFirmaAlmacenado.setValorBinario(bytesFirma);
            }
          }
        }

      }

    } catch (RepositoryCmisException rce) {
      String mensajeError = String.format(
          "No ha podido obtenerse el objeto del repositorio cuando intentaba consultarse el expediente con identificador %s "
              + rce.getMessage(),
          expediente.getIdentificador());
      logger.error(mensajeError);
      throw new InsideServiceAdapterException(mensajeError, rce);
    }
    return expediente;
  }


  private String getUserDir() {

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String nif = auth.getName();
    String dir3 = null;
    Integer idUnidad = null;

    List<Criterion> criterias = new ArrayList<Criterion>();
    criterias.add(Restrictions.eq("nif", nif));
    UsuarioInside usuario =
        (UsuarioInside) insideServiceJta.findObjeto(UsuarioInside.class, criterias);

    if (usuario != null) {

      criterias = new ArrayList<Criterion>();
      criterias.add(Restrictions.eq("idUsuario", usuario.getId()));
      criterias.add(Restrictions.eq("activo", true));
      List<UnidadUsuario> usuariosUnidad =
          insideServiceJta.getAllObjetosCriteria(UnidadUsuario.class, criterias);

      if (usuariosUnidad != null && usuariosUnidad.size() > 0) {
        idUnidad = ((UnidadUsuario) usuariosUnidad.get(0)).getUnidad().getId();
      }

    } else {
      criterias = new ArrayList<Criterion>();
      criterias.add(Restrictions.eq("nombre", nif));
      InsideWsAplicacion aplicacion =
          (InsideWsAplicacion) insideServiceJta.findObjeto(InsideWsAplicacion.class, criterias);

      criterias = new ArrayList<Criterion>();
      criterias.add(Restrictions.eq("idAplicacion", aplicacion.getId()));
      criterias.add(Restrictions.eq("activo", true));
      List<UnidadWsAplicacion> aplicacionesUnidad =
          insideServiceJta.getAllObjetosCriteria(UnidadWsAplicacion.class, criterias);

      if (aplicacionesUnidad != null && aplicacionesUnidad.size() > 0) {
        idUnidad = ((UnidadWsAplicacion) aplicacionesUnidad.get(0)).getIdUnidad();
      }
    }


    if (idUnidad != null) {
      Session session = null;
      try {
        session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(UnidadOrganica.class);
        criteria.add(Restrictions.eq("id", idUnidad));
        Object value = criteria.uniqueResult();

        if (value != null) {
          dir3 = ((UnidadOrganica) value).getCodigoUnidadOrganica();
        }
      } catch (HibernateException e) {
        logger.error(e);
      } finally {
        session.close();
      }


    }

    return dir3;
  }


  @Override
  public ObjetoDocumentoInside eliminaDocumentoInside(ObjetoDocumentoInside documento)
      throws InsideServiceAdapterException {
    if (documento.getIdentificadorRepositorio() != null) {
      try {
        cmisAdapter.borrarDocumento(documento.getIdentificadorRepositorio(), true);
      } catch (RepositoryCmisException e) {
        logger.error("Error al eliminar el documento");
        throw new InsideServiceAdapterException("Error al eliminar el documento");
      }
    }
    return documento;
  }


  @Override
  public ObjetoExpedienteInside eliminaExpedienteInside(ObjetoExpedienteInside expediente)
      throws InsideServiceAdapterException {
    logger.info("Entra en recuperaExpedienteInside");
    if (StringUtils.isEmpty(expediente.getIdentificadorRepositorio())) {
      throw new InsideServiceAdapterException(
          "El identificador del repositorio del expediente no puede ser nulo");
    }

    try {
      logger.debug("IdentificadorRepositorio: " + expediente.getIdentificadorRepositorio());
      // Se están recuperando las firmas que son las que se almacenan
      if (expediente.getIndice().getFirmas() != null) {

        for (FirmaInside firma : expediente.getIndice().getFirmas()) {
          ContenidoFirmaInside contenidoFirma = firma.getContenidoFirma();
          if (contenidoFirma != null
              && contenidoFirma instanceof ContenidoFirmaCertificadoAlmacenableInside) {
            ContenidoFirmaCertificadoAlmacenableInside contenidoFirmaAlmacenado =
                (ContenidoFirmaCertificadoAlmacenableInside) contenidoFirma;

            if (StringUtils.isNotEmpty(contenidoFirmaAlmacenado.getIdentificadorRepositorio())) {

              logger.debug("contenidoFirmaAlmacenado IdentificadorRepositorio: "
                  + contenidoFirmaAlmacenado.getIdentificadorRepositorio());
              cmisAdapter.borrarDocumento(contenidoFirmaAlmacenado.getIdentificadorRepositorio(),
                  true);
            }

          }
        }

      }
      logger.info("Sale de recuperaExpedienteInside");

    } catch (RepositoryCmisException e) {
      logger.error("Error al recuperar las firmas del expediente");
      throw new InsideServiceAdapterException("Error al recuperar las firmas del expediente", e);
    }
    return expediente;
  }

  public CmisAdapterInterface getCmisAdapter() {
    return cmisAdapter;
  }

  @Required
  public void setCmisAdapter(CmisAdapterInterface cmisAdapter) {
    this.cmisAdapter = cmisAdapter;
  }

  public String getDocumentoObjectTypeId() {
    return documentoObjectTypeId;
  }

  @Required
  public void setDocumentoObjectTypeId(String objectTypeId) {
    this.documentoObjectTypeId = objectTypeId;
  }



  public String getRepoObjectId() {
    return repoObjectId;
  }

  @Required
  public void setRepoObjectId(String repoObjectId) {
    this.repoObjectId = repoObjectId;
  }

  public String getRepoVersionLabel() {
    return repoVersionLabel;
  }

  @Required
  public void setRepoVersionLabel(String repoVersionLabel) {
    this.repoVersionLabel = repoVersionLabel;
  }

  public String getRepoPathSeparator() {
    return repoPathSeparator;
  }

  @Required
  public void setRepoPathSeparator(String repoPathSeparator) {
    this.repoPathSeparator = repoPathSeparator;
  }


  /**
   * @return the documentoIdENI
   */
  public String getDocumentoIdENI() {
    return documentoIdENI;
  }

  /**
   * @param documentoIdENI the documentoIdENI to set
   */
  public void setDocumentoIdENI(String documentoIdENI) {
    this.documentoIdENI = documentoIdENI;
  }

  public SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public String getDocumentoDIR3() {
    return documentoDIR3;
  }

  public void setDocumentoDIR3(String documentoDIR3) {
    this.documentoDIR3 = documentoDIR3;
  }

  public String getDocumentoCSV() {
    return documentoCSV;
  }


  public void setDocumentoCSV(String documentoCSV) {
    this.documentoCSV = documentoCSV;
  }


  @Override
  public ObjetoDocumentoInsideContenido getcontenidoByUuid(String uuid, String idSession)
      throws InsideServiceAdapterException {
    // TODO Auto-generated method stub
    return null;
  }

  public long getContentSizeByUuid(String uuid) {
    return 0;
  }


  @Override
  public String getValorHuellaDocumento(String uuid, String algoritmo)
      throws InsideServiceAdapterException {
    // TODO Auto-generated method stub
    return null;
  }


  /**
   * @return the dir3Default
   */
  public String getDir3Default() {
    return dir3Default;
  }


  /**
   * @param dir3Default the dir3Default to set
   */
  public void setDir3Default(String dir3Default) {
    this.dir3Default = dir3Default;
  }


  /**
   * @return the almacenarFirma
   */
  public String getAlmacenarFirma() {
    return almacenarFirma;
  }


  /**
   * @param almacenarFirma the almacenarFirma to set
   */
  public void setAlmacenarFirma(String almacenarFirma) {
    this.almacenarFirma = almacenarFirma;
  }


}
