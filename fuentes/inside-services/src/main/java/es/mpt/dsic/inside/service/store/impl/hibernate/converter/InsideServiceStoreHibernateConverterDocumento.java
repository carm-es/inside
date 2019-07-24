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

package es.mpt.dsic.inside.service.store.impl.hibernate.converter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideMetadatoAdicional;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideVersion;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInside;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInsideContenido;
import es.mpt.dsic.inside.model.objetos.documento.metadatos.ObjetoDocumentoInsideMetadatos;
import es.mpt.dsic.inside.model.objetos.documento.metadatos.ObjetoDocumentoInsideMetadatosEstadoElaboracion;
import es.mpt.dsic.inside.model.objetos.documento.metadatos.ObjetoDocumentoInsideMetadatosEstadoElaboracionEnumeracion;
import es.mpt.dsic.inside.model.objetos.documento.metadatos.ObjetoDocumentoInsideMetadatosTipoDocumental;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteInside;
import es.mpt.dsic.inside.model.objetos.firmas.FirmaInside;
import es.mpt.dsic.inside.service.store.exception.InsideServiceStoreException;
import es.mpt.dsic.inside.service.util.InsideUtils;
import es.mpt.dsic.inside.store.hibernate.entity.DocumentoInside;
import es.mpt.dsic.inside.store.hibernate.entity.DocumentoInsideFirmas;
import es.mpt.dsic.inside.store.hibernate.entity.DocumentoInsideMetadatosAdicionales;
import es.mpt.dsic.inside.store.hibernate.entity.DocumentoInsideOrgano;
import es.mpt.dsic.inside.store.hibernate.entity.ExpedienteInsideIndiceDocumentoIndizado;

public class InsideServiceStoreHibernateConverterDocumento
    implements InsideServiceStoreHibernateConverter<ObjetoDocumentoInside, DocumentoInside> {

  @Override
  public DocumentoInside toEntity(ObjetoDocumentoInside documento, Session session)
      throws InsideServiceStoreException {
    DocumentoInside entity = new DocumentoInside();
    entity.setIdentificador(documento.getIdentificador());
    entity.setIdentificadorRepositorio(documento.getIdentificadorRepositorio());

    if (documento.getVersion() != null) {
      if (documento.getVersion().getFechaVersion() != null) {
        entity.setFechaVersion(documento.getVersion().getFechaVersion().getTime());
      }
      entity.setVersion(documento.getVersion().getVersion());

    }

    if (documento.getFechaBaja() != null) {
      entity.setFechaBaja(documento.getFechaBaja().getTime());
    }

    if (documento.getMetadatos() != null) {
      if (documento.getMetadatos().getEstadoElaboracion() != null) {
        if (documento.getMetadatos().getEstadoElaboracion().getValorEstadoElaboracion() != null) {
          entity.setEstadoElaboracion(
              documento.getMetadatos().getEstadoElaboracion().getValorEstadoElaboracion().value());
        }
        entity.setIdentificadorDocumentoOrigen(
            documento.getMetadatos().getEstadoElaboracion().getIdentificadorDocumentoOrigen());
      }

      if (documento.getMetadatos().getFechaCaptura() != null) {
        entity.setFechaCaptura(documento.getMetadatos().getFechaCaptura().getTime());
      }

      if (CollectionUtils.isNotEmpty(documento.getMetadatos().getMetadatosAdicionales())) {
        setInsideMetadatosAdicionales(documento, entity);
      }

      setInsideOrganos(documento, entity);

      if (documento.getMetadatos().getTipoDocumental() != null) {
        entity.setTipoDocumental(documento.getMetadatos().getTipoDocumental().value());
      }
      entity.setVersionNti(documento.getMetadatos().getVersionNTI());
      entity.setOrigenCiudadanoAdministracion(
          documento.getMetadatos().isOrigenCiudadanoAdministracion());

      if (documento.getFirmas() != null) {
        Set<DocumentoInsideFirmas> documentoInsideFirmas = new HashSet<DocumentoInsideFirmas>();
        for (FirmaInside firmaInside : documento.getFirmas()) {
          DocumentoInsideFirmas firmaEntity = new DocumentoInsideFirmas();
          try {
            firmaEntity
                .setFirmaInside(InsideServiceStoreHibernateConverterFirma.toEntity(firmaInside));
          } catch (InsideServiceStoreHibernateConverterFirmaException e) {
            throw new InsideServiceStoreException(
                "Error convirtiendo FirmaInside a Entidad de Hibernate", e);
          }
          firmaEntity.setDocumentoInside(entity);
          // Ponemos a nulo la columna de contenido en la tabla de InsideFirma
          // porque para documentos no se emplea y podría dar problemas de capacidad
          firmaEntity.getFirmaInside().setContenido(null);
          documentoInsideFirmas.add(firmaEntity);
        }

        entity.setDocumentoInsideFirmases(documentoInsideFirmas);
      }

      if (documento.getContenido() != null) {
        entity.setTipoMime(documento.getContenido().getMime());
        entity.setNombreFormato(
            InsideUtils.getNombreFormatoByMime(documento.getContenido().getMime()));
        entity.setIdentificadorEnDocumento(documento.getContenido().getIdentificadorEnDocumento());
        entity.setReferencia(documento.getContenido().getReferencia());
      }

    }
    return entity;
  }

  public void setInsideMetadatosAdicionales(ObjetoDocumentoInside documento,
      DocumentoInside entity) {
    Set<DocumentoInsideMetadatosAdicionales> documentoInsideMetadatosAdicionaleses =
        new HashSet<DocumentoInsideMetadatosAdicionales>();
    for (ObjetoInsideMetadatoAdicional metadatoAdicional : documento.getMetadatos()
        .getMetadatosAdicionales()) {
      DocumentoInsideMetadatosAdicionales metadatoAaditionaEntity =
          new DocumentoInsideMetadatosAdicionales();
      metadatoAaditionaEntity.setDocumentoInside(entity);
      metadatoAaditionaEntity.setNombre(metadatoAdicional.getNombre());
      metadatoAaditionaEntity.setValor(metadatoAdicional.getValor() + "");
      documentoInsideMetadatosAdicionaleses.add(metadatoAaditionaEntity);
    }

    entity.setDocumentoInsideMetadatosAdicionaleses(documentoInsideMetadatosAdicionaleses);
  }

  public void setInsideOrganos(ObjetoDocumentoInside documento, DocumentoInside entity) {
    Set<DocumentoInsideOrgano> documentoInsideOrganos = new HashSet<DocumentoInsideOrgano>();
    for (String organo : documento.getMetadatos().getOrgano()) {
      DocumentoInsideOrgano documentoInsideOrgano = new DocumentoInsideOrgano();
      documentoInsideOrgano.setOrgano(organo);
      documentoInsideOrgano.setDocumentoInside(entity);
      documentoInsideOrganos.add(documentoInsideOrgano);
    }
    entity.setDocumentoInsideOrganos(documentoInsideOrganos);
  }

  // @SuppressWarnings("unchecked")
  @Override
  public ObjetoDocumentoInside toInside(DocumentoInside entity, Session session)
      throws InsideServiceStoreException {
    ObjetoDocumentoInside documento = new ObjetoDocumentoInside();

    documento.setIdentificador(entity.getIdentificador());
    documento.setIdentificadorRepositorio(entity.getIdentificadorRepositorio());

    Calendar fechaVersion = null;
    if (entity.getFechaVersion() != null) {
      fechaVersion = GregorianCalendar.getInstance();
      fechaVersion.setTime(entity.getFechaVersion());
    }
    documento
        .setVersion(new ObjetoInsideVersion(entity.getVersion(), (GregorianCalendar) fechaVersion));

    documento.setMetadatos(new ObjetoDocumentoInsideMetadatos());
    ObjetoDocumentoInsideMetadatosEstadoElaboracion estadoElaboracion =
        new ObjetoDocumentoInsideMetadatosEstadoElaboracion();
    if (entity.getEstadoElaboracion() != null) {
      estadoElaboracion
          .setValorEstadoElaboracion(ObjetoDocumentoInsideMetadatosEstadoElaboracionEnumeracion
              .fromValue(entity.getEstadoElaboracion()));
    }
    estadoElaboracion.setIdentificadorDocumentoOrigen(entity.getIdentificadorDocumentoOrigen());
    documento.getMetadatos().setEstadoElaboracion(estadoElaboracion);

    Calendar fechaCaptura = null;
    if (entity.getFechaCaptura() != null) {
      fechaCaptura = GregorianCalendar.getInstance();
      fechaCaptura.setTime(entity.getFechaCaptura());
    }
    documento.getMetadatos().setFechaCaptura((GregorianCalendar) fechaCaptura);
    documento.getMetadatos().setIdentificadorObjetoInside(entity.getIdentificador());
    documento.getMetadatos()
        .setOrigenCiudadanoAdministracion(entity.getOrigenCiudadanoAdministracion() != null
            && entity.getOrigenCiudadanoAdministracion());

    List<ObjetoInsideMetadatoAdicional> metadatosAdicionales =
        new ArrayList<ObjetoInsideMetadatoAdicional>();
    for (DocumentoInsideMetadatosAdicionales metEntry : entity
        .getDocumentoInsideMetadatosAdicionaleses()) {
      ObjetoInsideMetadatoAdicional valorMetadatoAdicional = new ObjetoInsideMetadatoAdicional();
      valorMetadatoAdicional.setNombre(metEntry.getNombre());
      valorMetadatoAdicional.setValor(metEntry.getValor());
      metadatosAdicionales.add(valorMetadatoAdicional);
    }
    documento.getMetadatos().setMetadatosAdicionales(metadatosAdicionales);

    List<String> organosInside = new ArrayList<String>();
    for (DocumentoInsideOrgano organo : entity.getDocumentoInsideOrganos()) {
      organosInside.add(organo.getOrgano());
    }
    documento.getMetadatos().setOrgano(organosInside);

    documento.getMetadatos().setVersionNTI(entity.getVersionNti());
    if (entity.getTipoDocumental() != null) {
      documento.getMetadatos().setTipoDocumental(
          ObjetoDocumentoInsideMetadatosTipoDocumental.fromValue(entity.getTipoDocumental()));
    }

    List<FirmaInside> firmas = new ArrayList<FirmaInside>();
    for (DocumentoInsideFirmas firmaEntity : entity.getDocumentoInsideFirmases()) {
      firmas.add(InsideServiceStoreHibernateConverterFirma.toInside(firmaEntity.getFirmaInside()));
    }

    Collections.sort(firmas);
    documento.setFirmas(firmas);

    documento.setContenido(new ObjetoDocumentoInsideContenido());
    documento.getContenido().setMime(entity.getTipoMime());
    documento.getContenido()
        .setNombreFormato(InsideUtils.getNombreFormatoByMime(entity.getTipoMime()));
    documento.getContenido().setIdentificadorEnDocumento(entity.getIdentificadorEnDocumento());
    documento.getContenido().setReferencia(entity.getReferencia());

    Calendar fechaBaja = null;
    if (entity.getFechaBaja() != null) {
      fechaBaja = GregorianCalendar.getInstance();
      fechaBaja.setTime(entity.getFechaBaja());
      documento.setFechaBaja((GregorianCalendar) fechaBaja);
    }

    return documento;

  }

  @SuppressWarnings("unchecked")
  public List<ObjetoExpedienteInside> getExpedientesAsociados(String identificador, Session session)
      throws InsideServiceStoreException {
    Map<Integer, ObjetoExpedienteInside> retorno = new HashMap<Integer, ObjetoExpedienteInside>();

    Criteria crit = session.createCriteria(ExpedienteInsideIndiceDocumentoIndizado.class);
    crit.add(Restrictions.eq("identificadorDocumento", identificador));

    List<ExpedienteInsideIndiceDocumentoIndizado> objetos = crit.list();

    InsideServiceStoreHibernateConverterExpediente converter =
        new InsideServiceStoreHibernateConverterExpediente();

    for (ExpedienteInsideIndiceDocumentoIndizado entity : objetos) {

      if (entity.getExpedienteInsideIndice() != null) {
        // si el documento esta vinculado directamente al expediente

        retorno =
            converter.getExpediente(entity.getExpedienteInsideIndice().getId(), session, retorno);

      } else {
        // si el documento esta vinculado a una carpeta dentro del
        // expediente
        retorno = converter.getExpedienteCarpeta(entity.getExpedienteInsideIndiceCarpetaIndizada(),
            session, retorno);
      }
    }
    return new ArrayList<ObjetoExpedienteInside>(retorno.values());
  }
}
