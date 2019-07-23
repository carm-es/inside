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

package es.mpt.dsic.inside.service.store.impl.hibernate;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Required;
import es.mpt.dsic.inside.model.objetos.ObjectInsideRespuestaEnvioJusticia;
import es.mpt.dsic.inside.model.objetos.ObjetoCredencialEeutil;
import es.mpt.dsic.inside.model.objetos.ObjetoInside;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideAplicacion;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideUnidadAplicacionEeutil;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInside;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoAuditoriaToken;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteInside;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteToken;
import es.mpt.dsic.inside.model.objetos.usuario.ObjetoInsideUsuario;
import es.mpt.dsic.inside.service.store.exception.InsideServiceStoreException;
import es.mpt.dsic.inside.service.store.impl.hibernate.converter.InsideServiceStoreHibernateConverter;
import es.mpt.dsic.inside.service.store.impl.hibernate.converter.InsideServiceStoreHibernateConverterAplicacion;
import es.mpt.dsic.inside.service.store.impl.hibernate.converter.InsideServiceStoreHibernateConverterAuditoriaToken;
import es.mpt.dsic.inside.service.store.impl.hibernate.converter.InsideServiceStoreHibernateConverterExpedienteToken;
import es.mpt.dsic.inside.service.store.impl.hibernate.converter.InsideServiceStoreHibernateConverterUnidadAplicacionEeutil;
import es.mpt.dsic.inside.service.store.impl.hibernate.converter.InsideServiceStoreHibernateConverterUsuario;
import es.mpt.dsic.inside.store.hibernate.entity.AuditoriaAccesoDocumento;
import es.mpt.dsic.inside.store.hibernate.entity.AuditoriaFirmaServidor;
import es.mpt.dsic.inside.store.hibernate.entity.AuditoriaRespuestaEnvioJusticia;
import es.mpt.dsic.inside.store.hibernate.entity.AuditoriaToken;
import es.mpt.dsic.inside.store.hibernate.entity.ComunicacionTokenExpediente;
import es.mpt.dsic.inside.store.hibernate.entity.CredencialesEeutil;
import es.mpt.dsic.inside.store.hibernate.entity.DocumentoInside;
import es.mpt.dsic.inside.store.hibernate.entity.DocumentoInsideFirmas;
import es.mpt.dsic.inside.store.hibernate.entity.DocumentoInsideMetadatosAdicionales;
import es.mpt.dsic.inside.store.hibernate.entity.DocumentoInsideOrgano;
import es.mpt.dsic.inside.store.hibernate.entity.DocumentoUnidad;
import es.mpt.dsic.inside.store.hibernate.entity.EstructuraCarpetaInside;
import es.mpt.dsic.inside.store.hibernate.entity.ExpedienteInside;
import es.mpt.dsic.inside.store.hibernate.entity.ExpedienteInsideIndice;
import es.mpt.dsic.inside.store.hibernate.entity.ExpedienteInsideIndiceCarpetaIndizada;
import es.mpt.dsic.inside.store.hibernate.entity.ExpedienteInsideIndiceDocumentoIndizado;
import es.mpt.dsic.inside.store.hibernate.entity.ExpedienteInsideIndiceFirmas;
import es.mpt.dsic.inside.store.hibernate.entity.ExpedienteInsideInteresado;
import es.mpt.dsic.inside.store.hibernate.entity.ExpedienteInsideMetadatosAdicionales;
import es.mpt.dsic.inside.store.hibernate.entity.ExpedienteInsideOrgano;
import es.mpt.dsic.inside.store.hibernate.entity.ExpedienteInsideRespuestaEnvioJusticia;
import es.mpt.dsic.inside.store.hibernate.entity.ExpedienteNoInsideRespuestaEnvioJusticia;
import es.mpt.dsic.inside.store.hibernate.entity.ExpedienteToken;
import es.mpt.dsic.inside.store.hibernate.entity.ExpedienteUnidad;
import es.mpt.dsic.inside.store.hibernate.entity.GeneradorIdInside;
import es.mpt.dsic.inside.store.hibernate.entity.InsideWsAplicacion;
import es.mpt.dsic.inside.store.hibernate.entity.NumeroProcedimiento;
import es.mpt.dsic.inside.store.hibernate.entity.SolicitudAccesoExpAppUrl;
import es.mpt.dsic.inside.store.hibernate.entity.SolicitudAccesoExpediente;
import es.mpt.dsic.inside.store.hibernate.entity.UnidadAplicacionEeutil;
import es.mpt.dsic.inside.store.hibernate.entity.UnidadOrganica;
import es.mpt.dsic.inside.store.hibernate.entity.UnidadUsuario;
import es.mpt.dsic.inside.store.hibernate.entity.UnidadWsAplicacion;
import es.mpt.dsic.inside.store.hibernate.entity.UsuarioInside;

public class InsideServiceStoreHibernatePersister<I extends ObjetoInside<?>, E extends Object> {

  private static final String IDENTIFICADOR = "identificador";

  protected static final Log logger = LogFactory.getLog(InsideServiceStoreHibernatePersister.class);

  private SessionFactory sessionFactory;

  private InsideServiceStoreHibernateConverter<I, E> converter;

  private InsideServiceStoreHibernateConverterUsuario<E> converterUsuario;

  private InsideServiceStoreHibernateConverterExpedienteToken converterExpedienteToken;

  private InsideServiceStoreHibernateConverterAuditoriaToken converterAuditoriaToken;

  @SuppressWarnings("unchecked")
  public <T extends ObjetoInside<?>> void persistExpedienteOrDocumento(T object,
      Object objectUnidad) throws InsideServiceStoreException {
    Session session = sessionFactory.openSession();

    try {
      if (object instanceof ObjetoExpedienteInside) {
        saveExpediente((ExpedienteInside) converter.toEntity((I) object, session), session,
            objectUnidad);
      } else if (object instanceof ObjetoDocumentoInside) {
        DocumentoInside docUpdate =
            recuperaDocumentoEntity(((ObjetoDocumentoInside) object).getIdentificador());
        saveDocumento((DocumentoInside) converter.toEntity((I) object, session), docUpdate, session,
            objectUnidad);
      } else {
        throw new InsideServiceStoreException(
            "No se como almacenar objetos de tipo " + object.getClass());
      }
    } catch (InsideServiceStoreException e) {
      throw e;
    } catch (Exception e) {
      throw new InsideServiceStoreException("Error almacenando expediente o documento", e);
    } finally {
      session.close();
    }

  }

  private void saveDocumento(DocumentoInside documentoEntity, DocumentoInside docUpdate,
      Session session, Object objectUnidad) throws InsideServiceStoreException {
    logger.debug("Init saveDocumento");
    logger.debug("Parameter <documentoEntity>" + documentoEntity.toString());

    boolean updateDocument = (docUpdate != null && docUpdate.getId() != null) ? true : false;
    if (updateDocument)
      documentoEntity.setId(docUpdate.getId());

    Transaction tx = session.beginTransaction();
    try {

      session.saveOrUpdate(documentoEntity);
      session.saveOrUpdate(objectUnidad);

      if (updateDocument) {
        updateInsideOrgano(documentoEntity, docUpdate, session);
        updateInsideFirmas(documentoEntity, docUpdate, session);
        updateInsideMetadatosAdicionales(documentoEntity, docUpdate, session);
      } else {
        saveInsideOrgano(documentoEntity, session);
        saveInsideFirmas(documentoEntity, session);
        saveInsideMetadatosAdicionales(documentoEntity, session);
      }

      tx.commit();
    } catch (Exception e) {
      tx.rollback();
      throw new InsideServiceStoreException("Excepción almacenando Documento en BBDD", e);
    }

    logger.debug("End saveDocumento");
  }

  public void saveObjetoUnidad(Object objectUnidad, Session session)
      throws InsideServiceStoreException {
    Transaction tx = session.beginTransaction();
    try {
      session.saveOrUpdate(objectUnidad);
      tx.commit();
    } catch (Exception e) {
      tx.rollback();
      throw new InsideServiceStoreException("Excepción almacenando Documento en BBDD", e);
    }
  }

  private void updateInsideMetadatosAdicionales(DocumentoInside documentoEntity,
      DocumentoInside docUpdate, Session session) {
    for (DocumentoInsideMetadatosAdicionales insideMetadatosUpdate : docUpdate
        .getDocumentoInsideMetadatosAdicionaleses()) {
      boolean delete = true;
      for (DocumentoInsideMetadatosAdicionales insideMetadatos : documentoEntity
          .getDocumentoInsideMetadatosAdicionaleses()) {
        if (insideMetadatosUpdate.getNombre().equals(insideMetadatos.getNombre())
            && insideMetadatosUpdate.getValor().equals(insideMetadatos.getValor())
            && insideMetadatosUpdate.getDocumentoInside().getId().intValue() == insideMetadatos
                .getDocumentoInside().getId().intValue()) {
          insideMetadatos.setId(insideMetadatosUpdate.getId());
          delete = false;
        }
      }
      if (delete) {
        session.delete(insideMetadatosUpdate);
      }
    }
    saveInsideMetadatosAdicionales(documentoEntity, session);
  }

  private void updateInsideFirmas(DocumentoInside documentoEntity, DocumentoInside docUpdate,
      Session session) {
    for (DocumentoInsideFirmas insideFirmasUpdate : docUpdate.getDocumentoInsideFirmases()) {
      for (DocumentoInsideFirmas insideFirmas : documentoEntity.getDocumentoInsideFirmases()) {
        if (insideFirmasUpdate.getFirmaInside().getTipoFirma()
            .equals(insideFirmas.getFirmaInside().getTipoFirma())
            && insideFirmasUpdate.getDocumentoInside().getId().intValue() == insideFirmas
                .getDocumentoInside().getId().intValue()
            && insideFirmasUpdate.getFirmaInside().getOrden().intValue() == insideFirmas
                .getFirmaInside().getOrden().intValue()) {
          insideFirmas.setFirmaInside(insideFirmasUpdate.getFirmaInside());
          insideFirmas.setId(insideFirmasUpdate.getId());
        }
      }
    }
    saveInsideFirmas(documentoEntity, session);
  }

  private void updateInsideOrgano(DocumentoInside documentoEntity, DocumentoInside docUpdate,
      Session session) {
    for (DocumentoInsideOrgano insideOrganoUpdate : docUpdate.getDocumentoInsideOrganos()) {
      boolean delete = true;
      for (DocumentoInsideOrgano insideOrgano : documentoEntity.getDocumentoInsideOrganos()) {
        if (insideOrganoUpdate.getOrgano().equals(insideOrgano.getOrgano())
            && insideOrganoUpdate.getDocumentoInside().getId().intValue() == insideOrgano
                .getDocumentoInside().getId().intValue()) {
          insideOrgano.setId(insideOrganoUpdate.getId());
          delete = false;
        }
      }
      if (delete) {
        session.delete(insideOrganoUpdate);
      }
    }
    saveInsideOrgano(documentoEntity, session);
  }

  public void saveInsideMetadatosAdicionales(DocumentoInside documentoEntity, Session session) {
    for (DocumentoInsideMetadatosAdicionales metadato : documentoEntity
        .getDocumentoInsideMetadatosAdicionaleses()) {
      session.saveOrUpdate(metadato);
    }
  }

  public void saveInsideFirmas(DocumentoInside documentoEntity, Session session) {
    for (DocumentoInsideFirmas firma : documentoEntity.getDocumentoInsideFirmases()) {
      session.saveOrUpdate(firma.getFirmaInside());
      session.saveOrUpdate(firma);
    }
  }

  public void saveInsideOrgano(DocumentoInside documentoEntity, Session session) {
    for (DocumentoInsideOrgano organo : documentoEntity.getDocumentoInsideOrganos()) {
      session.saveOrUpdate(organo);
    }
  }

  private void saveExpediente(ExpedienteInside expedienteEntity, Session session,
      Object objectUnidad) throws InsideServiceStoreException {
    logger.debug("Init saveExpediente");
    logger.debug("Parameter <expedienteEntity>" + expedienteEntity.toString());
    Transaction tx = session.beginTransaction();
    try {

      saveIndiceExpediente(expedienteEntity.getExpedienteInsideIndice(), session);

      session.saveOrUpdate(expedienteEntity);
      session.saveOrUpdate(objectUnidad);

      for (ExpedienteInsideIndiceFirmas docFirma : expedienteEntity
          .getExpedienteInsideIndiceFirmases()) {
        session.saveOrUpdate(docFirma.getFirmaInside());
        session.saveOrUpdate(docFirma);
      }
      for (ExpedienteInsideInteresado interesado : expedienteEntity
          .getExpedienteInsideInteresados()) {
        session.saveOrUpdate(interesado);
      }
      for (ExpedienteInsideOrgano organo : expedienteEntity.getExpedienteInsideOrganos()) {
        session.saveOrUpdate(organo);
      }
      for (ExpedienteInsideMetadatosAdicionales metadato : expedienteEntity
          .getExpedienteInsideMetadatosAdicionaleses()) {
        session.saveOrUpdate(metadato);
      }

      tx.commit();
    } catch (Exception e) {
      tx.rollback();
      throw new InsideServiceStoreException("Excepción almacenando Expediente en BBDD", e);
    }

    logger.debug("End saveExpediente");
  }

  private void saveIndiceExpediente(ExpedienteInsideIndice indiceExpedienteEntity, Session session)
      throws InsideServiceStoreException {
    try {

      indiceExpedienteEntity.setCreatedAt(GregorianCalendar.getInstance().getTime());

      session.saveOrUpdate(indiceExpedienteEntity);

      for (ExpedienteInsideIndiceDocumentoIndizado docIndizado : indiceExpedienteEntity
          .getExpedienteInsideIndiceDocumentoIndizados()) {
        docIndizado.setCreatedAt(GregorianCalendar.getInstance().getTime());
        session.saveOrUpdate(docIndizado);
      }
      for (ExpedienteInsideIndice expedienteIndizado : indiceExpedienteEntity
          .getExpedienteInsideIndices()) {
        saveIndiceExpediente(expedienteIndizado, session);
      }
      for (ExpedienteInsideIndiceCarpetaIndizada carpeta : indiceExpedienteEntity
          .getExpedienteInsideIndiceCarpetaIndizadas()) {
        saveCarpetaExpediente(carpeta, session);
      }

    } catch (Exception e) {
      throw new InsideServiceStoreException("Excepción almacenando Indice de Expediente en BBDD",
          e);
    }
  }

  private void saveCarpetaExpediente(ExpedienteInsideIndiceCarpetaIndizada carpetaEntity,
      Session session) throws InsideServiceStoreException {
    try {

      carpetaEntity.setCreatedAt(GregorianCalendar.getInstance().getTime());

      session.saveOrUpdate(carpetaEntity);

      for (ExpedienteInsideIndiceDocumentoIndizado docIndizado : carpetaEntity
          .getExpedienteInsideIndiceDocumentoIndizados()) {
        docIndizado.setCreatedAt(GregorianCalendar.getInstance().getTime());
        session.saveOrUpdate(docIndizado);
      }
      for (ExpedienteInsideIndice expedienteIndizado : carpetaEntity.getExpedienteInsideIndices()) {
        saveIndiceExpediente(expedienteIndizado, session);
      }
      for (ExpedienteInsideIndiceCarpetaIndizada carpeta : carpetaEntity
          .getExpedienteInsideIndiceCarpetaIndizadas()) {
        saveCarpetaExpediente(carpeta, session);
      }

    } catch (Exception e) {
      throw new InsideServiceStoreException("Excepción almacenando Carpeta Indizada en BBDD", e);
    }
  }

  public SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  @Required
  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public InsideServiceStoreHibernateConverter<I, E> getConverter() {
    return converter;
  }

  public void setConverter(InsideServiceStoreHibernateConverter<I, E> converter) {
    this.converter = converter;
  }

  public InsideServiceStoreHibernateConverterUsuario<E> getConverterUsuario() {
    return converterUsuario;
  }

  public void setConverterUsuario(InsideServiceStoreHibernateConverterUsuario<E> converterUsuario) {
    this.converterUsuario = converterUsuario;
  }

  public InsideServiceStoreHibernateConverterExpedienteToken getConverterExpedienteToken() {
    return converterExpedienteToken;
  }

  public void setConverterExpedienteToken(
      InsideServiceStoreHibernateConverterExpedienteToken converterExpedienteToken) {
    this.converterExpedienteToken = converterExpedienteToken;
  }

  public InsideServiceStoreHibernateConverterAuditoriaToken getConverterAuditoriaToken() {
    return converterAuditoriaToken;
  }

  public void setConverterAuditoriaToken(
      InsideServiceStoreHibernateConverterAuditoriaToken converterAuditoriaToken) {
    this.converterAuditoriaToken = converterAuditoriaToken;
  }

  private <T extends ObjetoInside<?>> Class<?> getEntityClass(Class<T> tipo)
      throws InsideServiceStoreException {
    if (tipo == ObjetoExpedienteInside.class) {
      return ExpedienteInside.class;
    } else if (tipo == ObjetoDocumentoInside.class) {
      return DocumentoInside.class;
    } else {
      throw new InsideServiceStoreException(
          "No se corresponden ningun Entity de BBDD con el tipo " + tipo);
    }
  }

  public void deleteDocument(String identificador, Session session)
      throws InsideServiceStoreException {

    Transaction tx = session.beginTransaction();
    try {
      List<DocumentoUnidad> docsUni = getDocumentoUnidadEntity(identificador);
      for (DocumentoUnidad doc : docsUni)
        session.delete(doc);

      List<DocumentoInside> listDoc = recuperaListDocumentoEntity(identificador, session);
      for (DocumentoInside doc : listDoc) {
        deleteDocInsideMetadatosAdicionales(doc, session);
        deleteDocInsideFirmas(doc, session);
        deleteDocInsideOrgano(doc, session);
        session.delete(doc);
      }

      tx.commit();
    } catch (Exception e) {
      tx.rollback();
      throw new InsideServiceStoreException("Excepción eliminando objeto en BBDD", e);
    }
  }

  public void deleteDocInsideMetadatosAdicionales(DocumentoInside documentoEntity,
      Session session) {
    for (DocumentoInsideMetadatosAdicionales metadato : documentoEntity
        .getDocumentoInsideMetadatosAdicionaleses()) {
      session.delete(metadato);
    }
  }

  public void deleteDocInsideFirmas(DocumentoInside documentoEntity, Session session) {
    for (DocumentoInsideFirmas firma : documentoEntity.getDocumentoInsideFirmases()) {
      session.delete(firma);
      session.delete(firma.getFirmaInside());
    }
  }

  public void deleteDocInsideOrgano(DocumentoInside documentoEntity, Session session) {
    for (DocumentoInsideOrgano organo : documentoEntity.getDocumentoInsideOrganos()) {
      session.delete(organo);
    }
  }

  public void deleteExpedient(String identificador, Session session)
      throws InsideServiceStoreException {

    Transaction tx = session.beginTransaction();
    try {
      ExpedienteUnidad expUni = getExpedienteUnidadEntity(identificador);
      UnidadOrganica unidad = getUnidadEntity(expUni.getIdUnidad(), session);
      NumeroProcedimiento numeroProcedimiento = expUni.getIdProcedimiento() != null
          ? getNumeroProcesoEntity(expUni.getIdProcedimiento(), session)
          : null;
      session.delete(expUni);

      List<ExpedienteInside> listExp = recuperaListExpedienteEntity(identificador, null, session);
      for (ExpedienteInside exp : listExp) {
        deleteExpInsideRespuestaEnvioJustica(exp, unidad, numeroProcedimiento, session);
        deleteExpInsideMetadatosAdicionales(exp, session);
        deleteExpInsideFirmas(exp, session);
        deleteExpInsideOrgano(exp, session);
        deleteExpInsideInteresado(exp, session);
        session.delete(exp);
        deleteElementInsideIndice(exp.getExpedienteInsideIndice(), session);
      }

      tx.commit();
    } catch (Exception e) {
      tx.rollback();
      throw new InsideServiceStoreException("Excepción eliminando objeto en BBDD", e);
    }
  }

  private void deleteExpInsideRespuestaEnvioJustica(ExpedienteInside expedienteEntity,
      UnidadOrganica unidadOrganica, NumeroProcedimiento numeroProcedimiento, Session session) {
    for (ExpedienteInsideRespuestaEnvioJusticia envio : expedienteEntity
        .getExpedienteInsideRespuestaEnvioJusticia()) {
      AuditoriaRespuestaEnvioJusticia auditoria = new AuditoriaRespuestaEnvioJusticia();
      auditoria.setIdentificadorExpediente(expedienteEntity.getIdentificador());
      auditoria.setUnidad(unidadOrganica);
      auditoria.setNumeroProcedimiento(numeroProcedimiento);
      auditoria.setAuditoriaEsbMarcaTiempo(envio.getAuditoriaEsb_marcaTiempo());
      auditoria.setCodigoEnvio(envio.getCodigoEnvio());
      session.save(auditoria);
      session.delete(envio);
    }
  }

  public void deleteExpInsideMetadatosAdicionales(ExpedienteInside expedienteEntity,
      Session session) {
    for (ExpedienteInsideMetadatosAdicionales metadato : expedienteEntity
        .getExpedienteInsideMetadatosAdicionaleses()) {
      session.delete(metadato);
    }
  }

  public void deleteExpInsideFirmas(ExpedienteInside expedienteEntity, Session session) {
    for (ExpedienteInsideIndiceFirmas expFirma : expedienteEntity
        .getExpedienteInsideIndiceFirmases()) {
      session.delete(expFirma);
      session.delete(expFirma.getFirmaInside());
    }
  }

  public void deleteExpInsideOrgano(ExpedienteInside expedienteEntity, Session session) {
    for (ExpedienteInsideOrgano organo : expedienteEntity.getExpedienteInsideOrganos()) {
      session.delete(organo);
    }
  }

  public void deleteExpInsideInteresado(ExpedienteInside expedienteEntity, Session session) {
    for (ExpedienteInsideInteresado interesado : expedienteEntity
        .getExpedienteInsideInteresados()) {
      session.delete(interesado);
    }
  }

  private void deleteElementInsideIndice(ExpedienteInsideIndice indice, Session session) {
    for (ExpedienteInsideIndiceDocumentoIndizado docIndizado : indice
        .getExpedienteInsideIndiceDocumentoIndizados()) {
      session.delete(docIndizado);
    }
    for (ExpedienteInsideIndice expedienteIndizado : indice.getExpedienteInsideIndices()) {
      deleteElementInsideIndice(expedienteIndizado, session);
    }
    for (ExpedienteInsideIndiceCarpetaIndizada carpeta : indice
        .getExpedienteInsideIndiceCarpetaIndizadas()) {
      deleteCarpetaExpediente(carpeta, session);
    }
    session.delete(indice);
  }

  private void deleteCarpetaExpediente(ExpedienteInsideIndiceCarpetaIndizada carpeta,
      Session session) {
    for (ExpedienteInsideIndiceDocumentoIndizado docIndizado : carpeta
        .getExpedienteInsideIndiceDocumentoIndizados()) {
      session.delete(docIndizado);
    }
    for (ExpedienteInsideIndice expedienteIndizado : carpeta.getExpedienteInsideIndices()) {
      deleteElementInsideIndice(expedienteIndizado, session);
    }
    for (ExpedienteInsideIndiceCarpetaIndizada carpetaIn : carpeta
        .getExpedienteInsideIndiceCarpetaIndizadas()) {
      deleteCarpetaExpediente(carpetaIn, session);
    }
    session.delete(carpeta);
  }

  public void deleteObjetoUnidad(Object objeto, Session session)
      throws InsideServiceStoreException {

    Transaction tx = session.beginTransaction();
    try {
      session.delete(objeto);
      tx.commit();
    } catch (Exception e) {
      tx.rollback();
      throw new InsideServiceStoreException("Excepción eliminando objeto en BBDD", e);
    }
  }

  public void updateObjetoUnidad(Object objeto, Session session)
      throws InsideServiceStoreException {

    Transaction tx = session.beginTransaction();
    try {
      session.update(objeto);
      tx.commit();
    } catch (Exception e) {
      tx.rollback();
      throw new InsideServiceStoreException("Excepción actualizando objeto en BBDD", e);
    }
  }

  public void saveToken(ObjetoExpedienteToken expedienteToken, Session session)
      throws InsideServiceStoreException {
    ExpedienteToken dato = converterExpedienteToken.toEntity(expedienteToken, session);

    Transaction tx = session.beginTransaction();
    try {
      session.saveOrUpdate(dato);
      tx.commit();
    } catch (Exception e) {
      tx.rollback();
      throw new InsideServiceStoreException("Excepción almacenando en BBDD", e);
    }
  }

  public void saveAuditoriaToken(ObjetoAuditoriaToken objectAuditoriaToken, Session session)
      throws InsideServiceStoreException {
    AuditoriaToken dato = converterAuditoriaToken.toEntity(objectAuditoriaToken, session);

    Transaction tx = session.beginTransaction();
    try {
      session.saveOrUpdate(dato);
      tx.commit();
    } catch (Exception e) {
      tx.rollback();
      throw new InsideServiceStoreException("Excepción almacenando en BBDD", e);
    }
  }

  public void saveComunicacionTokenExpediente(
      ComunicacionTokenExpediente comunicacionTokenExpediente, Session session)
      throws InsideServiceStoreException {

    Transaction tx = session.beginTransaction();
    try {
      session.saveOrUpdate(comunicacionTokenExpediente);
      tx.commit();
    } catch (Exception e) {
      tx.rollback();
      throw new InsideServiceStoreException("Excepción almacenando en BBDD", e);
    }
  }

  public void saveAuditoriaAccesoDocumento(AuditoriaAccesoDocumento auditoriaAccesoDocumento,
      Session session) throws InsideServiceStoreException {

    Transaction tx = session.beginTransaction();
    try {
      session.saveOrUpdate(auditoriaAccesoDocumento);
      tx.commit();
    } catch (Exception e) {
      tx.rollback();
      throw new InsideServiceStoreException("Excepción almacenando en BBDD", e);
    }
  }

  public void saveSolicitudAccesoExpediente(SolicitudAccesoExpediente solicitudAccesoExpediente,
      Session session) throws InsideServiceStoreException {

    Transaction tx = session.beginTransaction();
    try {
      session.saveOrUpdate(solicitudAccesoExpediente);
      tx.commit();
    } catch (Exception e) {
      tx.rollback();
      throw new InsideServiceStoreException("Excepción almacenando en BBDD", e);
    }
  }

  public ObjetoInsideUsuario saveUser(ObjetoInsideUsuario usuario, UnidadOrganica unidad,
      Integer idProcedimiento, Session session) throws InsideServiceStoreException {
    UsuarioInside dato = InsideServiceStoreHibernateConverterUsuario.toEntity(usuario);

    Transaction tx = session.beginTransaction();
    try {
      Criteria criteria = session.createCriteria(UsuarioInside.class);
      criteria.add(Restrictions.eq("nif", usuario.getNif()));
      UsuarioInside data = (UsuarioInside) criteria.uniqueResult();
      if (data != null) {
        String[] ignore = new String[3];
        ignore[0] = "id";
        ignore[1] = "fechaCreacion";
        ignore[2] = "listaUnidades";
        BeanUtils.copyProperties(dato, data, ignore);
        session.saveOrUpdate(data);
        actualizarUnidadUsuario(data, unidad.getId(), idProcedimiento, usuario.getRol().getId());
      } else {
        dato.setFechaCreacion(new Date());
        session.saveOrUpdate(dato);
        nuevaUnidadUsuario(dato, unidad.getId(), idProcedimiento, usuario.getRol().getId());
      }

      tx.commit();
    } catch (Exception e) {
      tx.rollback();
      throw new InsideServiceStoreException("Excepción almacenando en BBDD", e);
    }
    return InsideServiceStoreHibernateConverterUsuario.toInside(dato);
  }

  private void actualizarUnidadUsuario(UsuarioInside data, Integer idUnidad,
      Integer idProcedimiento, Integer idRol) {
    desactivarDir3Usuario(data.getListaUnidades());
    UnidadUsuario unidadUsuario = data.getUnidad(idUnidad, idProcedimiento, data.getId());
    if (unidadUsuario != null) {
      unidadUsuario.setActivo(true);
      unidadUsuario.setIdRol(idRol);
    } else {
      nuevaUnidadUsuario(data, idUnidad, idProcedimiento, idRol);
    }
  }

  private void desactivarDir3Usuario(Set<UnidadUsuario> listaUnidades) {
    for (UnidadUsuario unidad : listaUnidades) {
      unidad.setActivo(false);
    }
  }

  public void nuevaUnidadUsuario(UsuarioInside data, Integer idUnidad, Integer idProcedimiento,
      Integer idRol) {
    UnidadUsuario unidadUsuario = new UnidadUsuario();
    unidadUsuario.setUnidad(new UnidadOrganica());
    unidadUsuario.getUnidad().setId(idUnidad);
    if (idProcedimiento != null) {
      unidadUsuario.setProcedimiento(new NumeroProcedimiento());
      unidadUsuario.getProcedimiento().setId(idProcedimiento);
    }
    unidadUsuario.setIdUsuario(data.getId());
    unidadUsuario.setIdRol(idRol);
    unidadUsuario.setActivo(true);
    data.getListaUnidades().add(unidadUsuario);
  }

  public ObjetoInsideUsuario saveUser(UsuarioInside usuario, Session session)
      throws InsideServiceStoreException {
    Transaction tx = session.beginTransaction();
    try {
      session.saveOrUpdate(usuario);
      tx.commit();
    } catch (Exception e) {
      tx.rollback();
      throw new InsideServiceStoreException("Excepción almacenando en BBDD", e);
    }
    return InsideServiceStoreHibernateConverterUsuario.toInside(usuario);
  }

  public ObjetoInsideAplicacion saveApp(ObjetoInsideAplicacion app, UnidadOrganica unidad,
      Integer idProcedimiento, Session session) throws InsideServiceStoreException {
    InsideWsAplicacion dato = InsideServiceStoreHibernateConverterAplicacion.toEntity(app);
    Transaction tx = session.beginTransaction();
    ObjetoInsideAplicacion retorno = null;
    try {
      Criteria criteria = session.createCriteria(InsideWsAplicacion.class);
      criteria.add(Restrictions.eq("nombre", app.getAplicacion()));
      InsideWsAplicacion data = (InsideWsAplicacion) criteria.uniqueResult();
      byte[] digest = getDigest(app);
      dato.setPasswordHash(String.format("%064x", new java.math.BigInteger(1, digest)));
      if (data != null) {
        String[] ignore = new String[4];
        ignore[0] = "id";
        ignore[1] = "fechaCreacion";
        ignore[2] = "listaUnidades";

        BeanUtils.copyProperties(dato, data, ignore);
        session.saveOrUpdate(data);
        actualizarUnidadAplicacion(data, unidad.getId(), idProcedimiento);
        dato = data;
      } else {
        dato.setFechaCreacion(new Date());
        session.saveOrUpdate(dato);
        nuevaUnidadAplicacion(dato, unidad.getId(), idProcedimiento);
      }

      // credenciales eeutil
      Criteria criteriaCredential = session.createCriteria(CredencialesEeutil.class);
      criteriaCredential.add(Restrictions.eq("idAplicacionInterna", dato.getId()));
      CredencialesEeutil dataCredential = (CredencialesEeutil) criteriaCredential.uniqueResult();
      if (dataCredential != null) {
        dataCredential.setIdAplicacion(app.getCredencialEeutil().getAplicacion());
        dataCredential.setPassword(app.getCredencialEeutil().getPassword());
      } else {
        dataCredential = new CredencialesEeutil();
        dataCredential.setIdAplicacionInterna(dato.getId());
        dataCredential.setIdAplicacion(app.getCredencialEeutil().getAplicacion());
        dataCredential.setPassword(app.getCredencialEeutil().getPassword());
      }
      session.saveOrUpdate(dataCredential);

      retorno = InsideServiceStoreHibernateConverterAplicacion.toInside(dato);

      tx.commit();
    } catch (Exception e) {
      tx.rollback();
      throw new InsideServiceStoreException("Excepción almacenando en BBDD", e);
    }
    return retorno;
  }

  private byte[] getDigest(ObjetoInsideAplicacion app)
      throws NoSuchAlgorithmException, UnsupportedEncodingException {
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    String text = app.getPasswordHash();

    md.update(text.getBytes("UTF-8"));
    byte[] digest = md.digest();
    return digest;
  }

  public ObjetoInsideAplicacion actualizarCredencialesEeetuilApp(String app,
      ObjetoCredencialEeutil credential, Session session) throws InsideServiceStoreException {
    InsideWsAplicacion data = null;
    Transaction tx = session.beginTransaction();
    ObjetoInsideAplicacion retorno = null;
    try {
      Criteria criteria = session.createCriteria(InsideWsAplicacion.class);
      criteria.add(Restrictions.eq("nombre", app));
      data = (InsideWsAplicacion) criteria.uniqueResult();
      if (data != null) {
        Criteria criteriaCredential = session.createCriteria(CredencialesEeutil.class);
        criteriaCredential.add(Restrictions.eq("idAplicacionInterna", data.getId()));
        CredencialesEeutil credentialEntity =
            (CredencialesEeutil) criteriaCredential.uniqueResult();
        if (credentialEntity != null) {
          credentialEntity.setIdAplicacion(credential.getAplicacion());
          credentialEntity.setPassword(credential.getPassword());
        } else {
          credentialEntity = new CredencialesEeutil();
          credentialEntity.setIdAplicacionInterna(data.getId());
          credentialEntity.setIdAplicacion(credential.getAplicacion());
          credentialEntity.setPassword(credential.getPassword());
        }
        session.saveOrUpdate(credentialEntity);
        data.setCredencialEeeutil(credentialEntity);
        retorno = InsideServiceStoreHibernateConverterAplicacion.toInside(data);
      }

      tx.commit();
    } catch (Exception e) {
      tx.rollback();
      throw new InsideServiceStoreException("Excepción almacenando en BBDD", e);
    }
    return retorno;
  }

  private void actualizarUnidadAplicacion(InsideWsAplicacion data, Integer idUnidad,
      Integer idProcedimiento) {
    desactivarDir3Aplicacion(data.getListaUnidades());
    UnidadWsAplicacion unidadAplicacion = data.getUnidad(idUnidad, idProcedimiento, data.getId());
    if (unidadAplicacion != null) {
      unidadAplicacion.setActivo(true);
    } else {
      nuevaUnidadAplicacion(data, idUnidad, idProcedimiento);
    }
  }

  private void desactivarDir3Aplicacion(Set<UnidadWsAplicacion> listaUnidades) {
    for (UnidadWsAplicacion unidad : listaUnidades) {
      unidad.setActivo(false);
    }
  }

  public void nuevaUnidadAplicacion(InsideWsAplicacion data, Integer idUnidad,
      Integer idProcedimiento) {
    UnidadWsAplicacion unidadAplicacion = new UnidadWsAplicacion();
    unidadAplicacion.setIdUnidad(idUnidad);
    unidadAplicacion.setIdProcedimiento(idProcedimiento);
    unidadAplicacion.setIdAplicacion(data.getId());
    unidadAplicacion.setActivo(true);
    data.getListaUnidades().add(unidadAplicacion);
  }

  public ObjetoInsideAplicacion saveApp(InsideWsAplicacion app, Session session)
      throws InsideServiceStoreException {
    Transaction tx = session.beginTransaction();
    ObjetoInsideAplicacion retorno = null;
    try {
      session.saveOrUpdate(app);

      retorno = InsideServiceStoreHibernateConverterAplicacion.toInside(app);

      tx.commit();
    } catch (Exception e) {
      tx.rollback();
      throw new InsideServiceStoreException("Excepción almacenando en BBDD", e);
    }
    return retorno;
  }

  public void persistRespuestaRemisionJusticiaExpediente(
      ObjectInsideRespuestaEnvioJusticia objectInsideRespuestaEnvioJusticia,
      String identificadorExpediente, String idVersion) throws InsideServiceStoreException {

    ExpedienteInside expedienteEntity =
        recuperaExpedienteEntity(identificadorExpediente, idVersion);

    // convierto el objeto respuestajusticia a entity
    ExpedienteInsideRespuestaEnvioJusticia respuestaEnvioJusticia =
        new ExpedienteInsideRespuestaEnvioJusticia();
    respuestaEnvioJusticia.setExpedienteInside(expedienteEntity);
    respuestaEnvioJusticia.setAuditoriaEsb_aplicacion(
        objectInsideRespuestaEnvioJusticia.getAuditoriaEsb_aplicacion());
    respuestaEnvioJusticia
        .setAuditoriaEsb_modulo(objectInsideRespuestaEnvioJusticia.getAuditoriaEsb_modulo());
    respuestaEnvioJusticia
        .setAuditoriaEsb_servicio(objectInsideRespuestaEnvioJusticia.getAuditoriaEsb_servicio());
    respuestaEnvioJusticia.setAuditoriaEsb_marcaTiempo(
        objectInsideRespuestaEnvioJusticia.getAuditoriaEsb_marcaTiempo());
    respuestaEnvioJusticia.setAck(objectInsideRespuestaEnvioJusticia.getAck());
    respuestaEnvioJusticia.setCodigoEnvio(objectInsideRespuestaEnvioJusticia.getCodigoEnvio());
    respuestaEnvioJusticia.setMensaje(objectInsideRespuestaEnvioJusticia.getMensaje());
    respuestaEnvioJusticia.setCodigoUnidadOrganoRemitente(
        objectInsideRespuestaEnvioJusticia.getCodigoUnidadOrganoRemitente());

    Session session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
    try {

      session.saveOrUpdate(respuestaEnvioJusticia);
      tx.commit();

    } catch (Exception e) {
      tx.rollback();
      throw new InsideServiceStoreException("Excepción almacenando respuesta justicia en BBDD", e);
    } finally {
      session.close();
    }

  }

  public void persistRespuestaRemisionJusticiaExpedienteNoInside(
      ObjectInsideRespuestaEnvioJusticia objectInsideRespuestaEnvioJusticia,
      String identificadorExpediente, String idVersion) throws InsideServiceStoreException {

    // convierto el objeto respuestajusticia a entity
    ExpedienteNoInsideRespuestaEnvioJusticia respuestaEnvioJusticia =
        new ExpedienteNoInsideRespuestaEnvioJusticia();
    respuestaEnvioJusticia.setId_expediente_envio(identificadorExpediente);
    respuestaEnvioJusticia.setAuditoriaEsb_aplicacion(
        objectInsideRespuestaEnvioJusticia.getAuditoriaEsb_aplicacion());
    respuestaEnvioJusticia
        .setAuditoriaEsb_modulo(objectInsideRespuestaEnvioJusticia.getAuditoriaEsb_modulo());
    respuestaEnvioJusticia
        .setAuditoriaEsb_servicio(objectInsideRespuestaEnvioJusticia.getAuditoriaEsb_servicio());
    respuestaEnvioJusticia.setAuditoriaEsb_marcaTiempo(
        objectInsideRespuestaEnvioJusticia.getAuditoriaEsb_marcaTiempo());
    respuestaEnvioJusticia.setAck(objectInsideRespuestaEnvioJusticia.getAck());
    respuestaEnvioJusticia.setCodigoEnvio(objectInsideRespuestaEnvioJusticia.getCodigoEnvio());
    respuestaEnvioJusticia.setMensaje(objectInsideRespuestaEnvioJusticia.getMensaje());
    respuestaEnvioJusticia.setCodigoUnidadOrganoRemitente(
        objectInsideRespuestaEnvioJusticia.getCodigoUnidadOrganoRemitente());

    Session session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
    try {

      session.saveOrUpdate(respuestaEnvioJusticia);
      tx.commit();

    } catch (Exception e) {
      tx.rollback();
      throw new InsideServiceStoreException(
          "Excepción almacenando respuesta justicia expediente No Inside en BBDD", e);
    } finally {
      session.close();
    }

  }

  public List<DocumentoUnidad> getDocumentoUnidadEntity(String identificador) {
    Session session = sessionFactory.openSession();

    List<DocumentoUnidad> doc = null;
    try {
      Criteria documentoUnidad = session.createCriteria(DocumentoUnidad.class);
      documentoUnidad.add(Restrictions.eq(IDENTIFICADOR, identificador));
      doc = documentoUnidad.list();
    } catch (Exception e) {
      logger.debug("Entity documento unidad no encontrado con id documento: " + identificador, e);
    } finally {
      session.close();
    }
    return doc;
  }

  public ExpedienteUnidad getExpedienteUnidadEntity(String identificador) {
    Session session = sessionFactory.openSession();

    ExpedienteUnidad exp = null;
    try {
      Criteria expedienteUnidad = session.createCriteria(ExpedienteUnidad.class);
      expedienteUnidad.add(Restrictions.eq(IDENTIFICADOR, identificador));
      exp = (ExpedienteUnidad) expedienteUnidad.uniqueResult();
    } catch (Exception e) {
      logger.debug("Entity expediente unidad no encontrado con id expediente: " + identificador, e);
    } finally {
      session.close();
    }
    return exp;
  }

  private DocumentoInside recuperaDocumentoEntity(String identificadorDocumento) {
    Session session = sessionFactory.openSession();

    DocumentoInside doc = null;
    try {
      List<DocumentoInside> objetos = recuperaListDocumentoEntity(identificadorDocumento, session);

      if (objetos.isEmpty()) {
        throw new InsideServiceStoreException(
            "Excepcion recuperando entity de documento con identificador: "
                + identificadorDocumento);
      }
      doc = objetos.get(0);
      Hibernate.initialize(doc.getDocumentoInsideOrganos());
      Hibernate.initialize(doc.getDocumentoInsideMetadatosAdicionaleses());
      Hibernate.initialize(doc.getDocumentoInsideFirmases());
      for (DocumentoInsideFirmas insideFirma : doc.getDocumentoInsideFirmases()) {
        Hibernate.initialize(insideFirma.getFirmaInside());
      }
      return doc;
    } catch (Exception e) {
      logger.debug("Entity documento no encontrado con id: " + identificadorDocumento);
    } finally {
      session.close();
    }
    return doc;
  }

  public List<DocumentoInside> recuperaListDocumentoEntity(String identificadorDocumento,
      Session session) {
    Criteria crit = session.createCriteria(DocumentoInside.class);
    crit.add(Restrictions.eq(IDENTIFICADOR, identificadorDocumento));
    crit.addOrder(Order.desc("version"));
    return crit.list();
  }

  private ExpedienteInside recuperaExpedienteEntity(String identificadorExpediente,
      String idVersion) throws InsideServiceStoreException {
    Session session = sessionFactory.openSession();

    ExpedienteInside exp = null;
    try {
      List<ExpedienteInside> objetos =
          recuperaListExpedienteEntity(identificadorExpediente, idVersion, session);

      if (objetos.isEmpty()) {
        throw new InsideServiceStoreException(
            "Excepcion recuperando entity de expediente con identificador: "
                + identificadorExpediente + " y version " + idVersion);
      }

      exp = objetos.get(0);
      Hibernate.initialize(exp.getExpedienteInsideOrganos());
      Hibernate.initialize(exp.getExpedienteInsideInteresados());
      Hibernate.initialize(exp.getExpedienteInsideMetadatosAdicionaleses());
      Hibernate.initialize(exp.getExpedienteInsideRespuestaEnvioJusticia());
      Hibernate.initialize(exp.getExpedienteInsideIndices());
      for (ExpedienteInsideIndice indice : exp.getExpedienteInsideIndices()) {
        Hibernate.initialize(indice.getExpedienteInsideIndiceCarpetaIndizadas());
        Hibernate.initialize(indice.getExpedienteInsideIndiceDocumentoIndizados());
      }
      Hibernate.initialize(exp.getExpedienteInsideIndiceFirmases());
      for (ExpedienteInsideIndiceFirmas insideFirma : exp.getExpedienteInsideIndiceFirmases()) {
        Hibernate.initialize(insideFirma.getFirmaInside());
      }

    } catch (Exception e) {
      throw new InsideServiceStoreException(
          "Excepcion recuperando entity de expediente con identificador: " + identificadorExpediente
              + " y version " + idVersion);
    } finally {
      session.close();
    }
    return exp;
  }

  public List<ExpedienteInside> recuperaListExpedienteEntity(String identificadorExpediente,
      String idVersion, Session session) {
    Criteria crit = session.createCriteria(ExpedienteInside.class);
    crit.add(Restrictions.eq(IDENTIFICADOR, identificadorExpediente));
    if (idVersion != null) {
      crit.add(Restrictions.eq("version", Integer.parseInt(idVersion)));
    } else {
      crit.addOrder(Order.desc("version"));
    }

    return crit.list();
  }

  public ObjetoInsideUnidadAplicacionEeutil saveUnidadAplicacionEeutil(
      UnidadAplicacionEeutil unidadAplicacionEeutil, Session session)
      throws InsideServiceStoreException {

    Transaction tx = session.beginTransaction();
    try {
      session.saveOrUpdate(unidadAplicacionEeutil);
      tx.commit();
    } catch (Exception e) {
      tx.rollback();
      throw new InsideServiceStoreException("Excepción almacenando en BBDD", e);
    }

    return InsideServiceStoreHibernateConverterUnidadAplicacionEeutil
        .toInside(unidadAplicacionEeutil, null);
  }

  public String saveNumeroProcedimiento(NumeroProcedimiento numeroProcedimiento, Session session)
      throws InsideServiceStoreException {
    Transaction tx = session.beginTransaction();
    String retorno = null;
    try {
      session.saveOrUpdate(numeroProcedimiento);

      retorno = numeroProcedimiento.getNumProcedimiento();

      tx.commit();
    } catch (Exception e) {
      tx.rollback();
      throw new InsideServiceStoreException("Excepción almacenando en BBDD", e);
    }
    return retorno;
  }

  public EstructuraCarpetaInside saveEstructuraCarpeta(EstructuraCarpetaInside estructuraCarpeta,
      Session session) throws InsideServiceStoreException {
    Transaction tx = session.beginTransaction();
    EstructuraCarpetaInside retorno = null;
    try {
      saveCarpetaExpediente(estructuraCarpeta.getExpedienteInsideIndiceCarpetaIndizada(), session);

      session.saveOrUpdate(estructuraCarpeta);

      retorno = estructuraCarpeta;

      tx.commit();
    } catch (Exception e) {
      tx.rollback();
      throw new InsideServiceStoreException("Excepción almacenando en BBDD", e);
    }
    return retorno;
  }

  public void deleteEstructuraCarpeta(EstructuraCarpetaInside estructuraCarpeta, Session session)
      throws InsideServiceStoreException {
    Transaction tx = session.beginTransaction();
    try {
      deleteCarpetaExpediente(estructuraCarpeta.getExpedienteInsideIndiceCarpetaIndizada(),
          session);
      session.delete(estructuraCarpeta);
      tx.commit();
    } catch (Exception e) {
      tx.rollback();
      throw new InsideServiceStoreException("Excepción almacenando en BBDD", e);
    }
  }

  public UnidadOrganica getUnidadEntity(Object idUnidad, Session session)
      throws InsideServiceStoreException {
    UnidadOrganica unidad;
    // Recuperamos la unidad orgánica
    Criteria critUnidad = session.createCriteria(UnidadOrganica.class);
    if (idUnidad instanceof String)
      critUnidad.add(Restrictions.eq("codigoUnidadOrganica", idUnidad));
    else if (idUnidad instanceof Integer)
      critUnidad.add(Restrictions.eq("id", idUnidad));
    else
      throw new InsideServiceStoreException(
          "Proceso incompleto. No se pudo obtener unidad orgánica no existe en BBDD");
    unidad = (UnidadOrganica) critUnidad.uniqueResult();

    if (unidad == null)
      throw new InsideServiceStoreException(
          "Proceso incompleto. La unidad orgánica no existe en BBDD");

    return unidad;
  }

  public NumeroProcedimiento getNumeroProcesoEntity(Object numeroProc, Session session)
      throws InsideServiceStoreException {
    NumeroProcedimiento numeroProcedimiento;
    Criteria critUnidad = session.createCriteria(NumeroProcedimiento.class);

    if (numeroProc instanceof String)
      critUnidad.add(Restrictions.eq("numProcedimiento", numeroProc));
    else
      critUnidad.add(Restrictions.eq("id", numeroProc));

    numeroProcedimiento = (NumeroProcedimiento) critUnidad.uniqueResult();

    if (numeroProcedimiento == null)
      throw new InsideServiceStoreException(
          "Proceso incompleto. El número de procedimiento no existe en BBDD");

    return numeroProcedimiento;
  }

  public SolicitudAccesoExpAppUrl saveSolicitudAccesoExpAppUrl(SolicitudAccesoExpAppUrl dato,
      Session session) throws InsideServiceStoreException {

    Transaction tx = session.beginTransaction();
    try {
      session.saveOrUpdate(dato);
      tx.commit();
    } catch (Exception e) {
      tx.rollback();
      throw new InsideServiceStoreException("Excepción almacenando en BBDD", e);
    }
    return dato;
  }

  public GeneradorIdInside saveDefaultId(GeneradorIdInside idInside, Session session)
      throws InsideServiceStoreException {
    Transaction tx = session.beginTransaction();
    try {
      session.saveOrUpdate(idInside);
      tx.commit();
    } catch (Exception e) {
      tx.rollback();
      throw new InsideServiceStoreException("Excepción almacenando en BBDD", e);
    }
    return idInside;
  }

  public void saveAuditoriaFirmaServidor(AuditoriaFirmaServidor dato, Session session)
      throws InsideServiceStoreException {

    Transaction tx = session.beginTransaction();
    try {
      session.saveOrUpdate(dato);
      tx.commit();
    } catch (Exception e) {
      tx.rollback();
      throw new InsideServiceStoreException("Excepción almacenando en BBDD", e);
    }
  }
}
