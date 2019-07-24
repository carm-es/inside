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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Restrictions;
import es.mpt.dsic.inside.model.busqueda.consulta.ConsultaInside;
import es.mpt.dsic.inside.model.busqueda.consulta.ConsultaMetadatoInside;
import es.mpt.dsic.inside.model.busqueda.consulta.ConsultaSubInside;
import es.mpt.dsic.inside.model.busqueda.metadato.MetadatoValorBetweenInside;
import es.mpt.dsic.inside.model.busqueda.metadato.MetadatoValorDateRange;
import es.mpt.dsic.inside.model.busqueda.metadato.MetadatoValorInside;
import es.mpt.dsic.inside.model.busqueda.metadato.MetadatoValorSimpleInside;
import es.mpt.dsic.inside.model.objetos.ObjetoInside;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideMetadatos;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInside;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteInside;
import es.mpt.dsic.inside.service.object.definitions.InSideServiceObjectDefinitionNotFoundException;
import es.mpt.dsic.inside.service.object.definitions.InsideObjectDefinitionsContainer;
import es.mpt.dsic.inside.service.object.metadatos.metadato.Metadato;
import es.mpt.dsic.inside.service.object.metadatos.metadato.impl.MetadatoList;
import es.mpt.dsic.inside.service.store.InsideStoreObjectDef;
import es.mpt.dsic.inside.service.store.exception.InsideServiceStoreException;

public class InsideServiceHibernateConverterBusqueda {

  protected static final Log logger =
      LogFactory.getLog(InsideServiceHibernateConverterBusqueda.class);

  private InsideObjectDefinitionsContainer objectDefinitions;

  private Map<String, Criteria> joinedCriterias;

  public InsideServiceHibernateConverterBusqueda(
      InsideObjectDefinitionsContainer objectDefinitions) {
    this.objectDefinitions = objectDefinitions;
    this.joinedCriterias = new HashMap<String, Criteria>();
  }

  public <T extends ObjetoInside<K>, K extends ObjetoInsideMetadatos> void addConsultaInsideToCriteria(
      Criteria criteria, ConsultaInside consulta, Class<T> tipo)
      throws InsideServiceStoreException {
    joinedCriterias.clear();

    Criterion crit = consultaInsideToCriterion(criteria, consulta, tipo);
    if (crit != null) {
      criteria.add(crit);
    }

  }

  private <T extends ObjetoInside<K>, K extends ObjetoInsideMetadatos> Criterion consultaInsideToCriterion(
      Criteria criteria, ConsultaInside consulta, Class<T> tipo)
      throws InsideServiceStoreException {
    logger.debug("Creando criterio desde consulta de tipo " + consulta.getClass().getName()
        + " para un objeto de tipo " + tipo.getName());
    if (consulta instanceof ConsultaMetadatoInside) {
      return consultaMetadatoInsideToCriterion(criteria, (ConsultaMetadatoInside) consulta, tipo);
    } else if (consulta instanceof ConsultaSubInside) {
      return consultaSubInsideToCriterion(criteria, (ConsultaSubInside) consulta, tipo);
    } else {
      throw new InsideServiceStoreException(
          "Tipo de consulta no soportado " + consulta.getClass().getName());
    }
  }

  private <T extends ObjetoInside<K>, K extends ObjetoInsideMetadatos> Criterion consultaSubInsideToCriterion(
      Criteria criteria, ConsultaSubInside subConsulta, Class<T> tipo)
      throws InsideServiceStoreException {
    Junction sub = null;
    switch (subConsulta.getTipoSubConsulta()) {
      case AND:
        logger.debug("Creando subconsulta de tipo AND");
        sub = Restrictions.conjunction();
        break;
      case OR:
        logger.debug("Creando subconsulta de tipo OR");
        sub = Restrictions.disjunction();
        break;
      default:
        throw new InsideServiceStoreException(
            "Tipo de SubConsulta no soportado " + subConsulta.getTipoSubConsulta());
    }
    for (ConsultaInside subSubConsulta : subConsulta.getSubconsultas()) {
      Criterion crit = consultaInsideToCriterion(criteria, subSubConsulta, tipo);
      if (crit != null) {
        sub.add(crit);
      }
    }
    return sub;
  }

  /**
   * Devuelve un criterio nuevo o null si se tiene que hacer un inner Join
   * 
   * @param criteria
   * @param consultaMetadato
   * @param tipo
   * @return
   * @throws InsideServiceStoreException
   */
  private <T extends ObjetoInside<K>, K extends ObjetoInsideMetadatos> Criterion consultaMetadatoInsideToCriterion(
      Criteria criteria, ConsultaMetadatoInside consultaMetadato, Class<T> tipo)
      throws InsideServiceStoreException {
    logger.debug(
        "Creando criterio para metadato con nombre " + consultaMetadato.getMetadato().getNombre());
    try {
      InsideStoreObjectDef<T, K> def = objectDefinitions.getObjectDefinition(tipo);

      String nombreMetadato = consultaMetadato.getMetadato().getNombre();

      // buscamos el metadato dentro de las definiciones, por la clave o el nombre
      // boolean relacionado = true;
      Iterator<Entry<String, Metadato<?>>> it = def.getMetadatos().entrySet().iterator();
      while (it.hasNext()) {
        Entry<String, Metadato<?>> entry = it.next();
        String metadatoKey = entry.getKey();
        Metadato<?> metadato = entry.getValue();
        if (metadato.getNombre().equalsIgnoreCase(nombreMetadato)
            || metadatoKey.equalsIgnoreCase(nombreMetadato)) {
          nombreMetadato = StringUtils.uncapitalize(metadato.getNombre());
          if (MetadatoList.class.isInstance(metadato)) {
            String tablaRelacionada =
                getTableNameForInsideObject(tipo) + StringUtils.capitalize(nombreMetadato);
            logger.debug("El metadato " + nombreMetadato
                + " es multivaluado, se debe de encontrar en una tabla relacionada "
                + tablaRelacionada);

            // criteria.createCriteria(getRelationName(tablaRelacionada))
            // .add(metadatoValorToCriterion(nombreMetadato,consultaMetadato.getMetadato().getValor()));

            this.joinCriteria(getRelationName(tablaRelacionada), criteria, metadatoValorToCriterion(
                nombreMetadato, consultaMetadato.getMetadato().getValor()));

            return null;



          }
          logger.debug("El metadadato buscado " + consultaMetadato.getMetadato().getNombre()
              + " se ecuentra en la tabla de los " + tipo.getName());
          return metadatoValorToCriterion(consultaMetadato.getMetadato().getNombre(),
              consultaMetadato.getMetadato().getValor());
        }
      }

      // el metadato se supone que es adicional
      logger.debug("El metadato " + consultaMetadato.getMetadato().getNombre()
          + " debe de estar en los metadatos adicionales");

      Conjunction sub = Restrictions.conjunction();
      sub.add(Restrictions.eq("nombre", nombreMetadato))
          .add(metadatoValorToCriterion("valor", consultaMetadato.getMetadato().getValor()));
      // criteria.createCriteria(getRelationName(getTableNameForInsideObject(tipo) +
      // "MetadatosAdicionales")).add(sub);
      this.joinCriteria(getRelationName(getTableNameForInsideObject(tipo) + "MetadatosAdicionales"),
          criteria, sub);

      return null;
    } catch (InSideServiceObjectDefinitionNotFoundException e) {
      throw new InsideServiceStoreException(e.getMessage(), e);
    }

  }


  private Criterion metadatoValorToCriterion(String nombreMetadato,
      MetadatoValorInside valorMetadato) throws InsideServiceStoreException {
    Criterion crit = null;
    if (valorMetadato instanceof MetadatoValorSimpleInside) {
      MetadatoValorSimpleInside val = (MetadatoValorSimpleInside) valorMetadato;

      switch (val.getTipoBusqueda()) {
        case equal:
          crit = Restrictions.eq(nombreMetadato, val.getValor());
          break;
        case lessThan:
          crit = Restrictions.lt(nombreMetadato, val.getValor());
          break;
        case greaterThan:
          crit = Restrictions.gt(nombreMetadato, val.getValor());
          break;
        case like:
          crit = Restrictions.like(nombreMetadato, "%" + val.getValor() + "%");
          break;
        default:
          throw new InsideServiceStoreException(
              "Tipo de operador de busqueda " + val.getTipoBusqueda() + " no soportado");
      }
    } else if (valorMetadato instanceof MetadatoValorBetweenInside) {
      MetadatoValorBetweenInside val = (MetadatoValorBetweenInside) valorMetadato;
      crit = Restrictions.between(nombreMetadato, val.getInfRange(), val.getSupRange());

    } else if (valorMetadato instanceof MetadatoValorDateRange) {
      MetadatoValorDateRange val = (MetadatoValorDateRange) valorMetadato;
      crit = Restrictions.between(nombreMetadato, val.getFrom().getTime(), val.getTo().getTime());

    } else {
      throw new InsideServiceStoreException(
          "Tipo de valor de metadato no soportado " + valorMetadato.getClass().getName());
    }
    return crit;
  }

  private void joinCriteria(String asociationPath, Criteria criteria, Criterion crit) {
    if (!joinedCriterias.containsKey(asociationPath)) {
      joinedCriterias.put(asociationPath, criteria.createCriteria(asociationPath));
    }
    joinedCriterias.get(asociationPath).add(crit);
  }

  /**
   * Devuelve el nombre de la tabala donde se deben almacenar los objetos del tipo pasado por
   * parámetro
   * 
   * @param tipo
   * @return
   * @throws InsideServiceStoreException
   */
  public static <T extends ObjetoInside<K>, K extends ObjetoInsideMetadatos> String getTableNameForInsideObject(
      Class<T> tipo) throws InsideServiceStoreException {
    if (tipo == ObjetoExpedienteInside.class) {
      return "ExpedienteInside";
    } else if (tipo == ObjetoDocumentoInside.class) {
      return "DocumentoInside";
    } else {
      throw new InsideServiceStoreException(
          "No se que tabla le corresponde al tipo" + tipo.getName());
    }
  }

  /**
   * Mapeo entre el nombre de las tablas relacionadas con ExpedienteInside y DocumentoInside con el
   * precioso nombre que hibernate les asigna en los *hbm.xml con su plugin de ingenieria inversa
   * para eclipse
   * 
   * @param tablaRelacionada
   * @return
   * @throws InsideServiceStoreException
   */
  public static <T extends ObjetoInside<K>, K extends ObjetoInsideMetadatos> String getRelationName(
      String tablaRelacionada) throws InsideServiceStoreException {
    if (tablaRelacionada.equals("DocumentoInsideOrgano")) {
      return "documentoInsideOrganos";
    } else if (tablaRelacionada.equals("ExpedienteInsideOrgano")) {
      return "expedienteInsideOrganos";
    } else if (tablaRelacionada.equals("ExpedienteInsideInteresado")) {
      return "expedienteInsideInteresados";
    } else if (tablaRelacionada.equals("DocumentoInsideMetadatosAdicionales")) {
      return "documentoInsideMetadatosAdicionaleses";
    } else if (tablaRelacionada.equals("ExpedienteInsideMetadatosAdicionales")) {
      return "expedienteInsideMetadatosAdicionaleses";
    } else {
      throw new InsideServiceStoreException(
          "No se como se llama la relación para la tabla" + tablaRelacionada);
    }
  }

}
