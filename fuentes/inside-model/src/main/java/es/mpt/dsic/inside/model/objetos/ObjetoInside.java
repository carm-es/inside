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

package es.mpt.dsic.inside.model.objetos;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.List;
import org.springframework.util.Assert;
import es.mpt.dsic.inside.model.converter.InsideConverterUtils;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteInside;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndice;
import es.mpt.dsic.inside.model.objetos.permisos.ObjetoInsidePermiso;

/**
 * Clase base para los objetos almacenables en Inside
 * 
 * @author miguel.ortega
 * @param <T> El tipo de metadatos que almacena este objeto
 *
 */
public abstract class ObjetoInside<T extends ObjetoInsideMetadatos> implements Serializable {

  private static final long serialVersionUID = -5701757147313066936L;

  protected String identificador;

  protected String identificadorRepositorio;

  protected T metadatos;

  protected GregorianCalendar fechaBaja;

  protected List<ObjetoInsidePermiso> permisos;

  /**
   * Devuelve el identificador asociado al objeto
   * 
   * @return
   */
  public final String getIdentificador() {
    return identificador;
  }

  /**
   * Establece el identificador unico para este objeto También establece el valor del campo
   * IdentificadorObjetoInside de sus metadatos
   * 
   * @param identificador
   */
  public final void setIdentificador(String identificador) {
    Assert.notNull(identificador, "El identificador no puede ir vacío");
    this.identificador = identificador;
    // Si no existen los creamos, si no dará NullPointer en la siguiente instrucción.
    if (this.metadatos == null) {
      this.metadatos = createMetadatos();
    }
    if (this instanceof ObjetoExpedienteInside) {
      ObjetoExpedienteInsideIndice indice = ((ObjetoExpedienteInside) this).getIndice();
      if (indice != null && indice.getIndiceContenido() != null) {
        indice.getIndiceContenido().setIdentificadorExpedienteAsociado(identificador);
      }
    }
    this.metadatos.setIdentificadorObjetoInside(identificador);
  }

  // CARM ### v2.0.7.1
  public final void setNullIdentificador() {
    this.identificador = null;
  }
  // CARM 2.0.7.1 ###

  /**
   * Devuelve el identificador del repositorio asociado al objeto.
   * 
   * @return
   */
  public String getIdentificadorRepositorio() {
    return identificadorRepositorio;
  }


  /**
   * Establece el identificador del repositorio asociado al objeto.
   * 
   * @param identificadorRepositorio
   */
  public void setIdentificadorRepositorio(String identificadorRepositorio) {
    this.identificadorRepositorio = identificadorRepositorio;
  }

  /*
   * @Override public String toString() { return "Objeto Inside, identificador " +
   * this.getIdentificador() + " . "; }
   */

  public T getMetadatos() {
    if (this.metadatos == null) {
      this.metadatos = this.createMetadatos();
    }
    return this.metadatos;
  }

  public void setMetadatos(T metadatos) {
    this.metadatos = metadatos;
  }

  /**
   * Verfica si este objeto es versionable
   * 
   * @return
   */
  public final boolean isVersionable() {
    return this instanceof ObjetoInsideVersionable;
  }

  public GregorianCalendar getFechaBaja() {
    return fechaBaja;
  }

  public void setFechaBaja(GregorianCalendar fechaBaja) {
    this.fechaBaja = fechaBaja;
  }

  /**
   * Los objetos Inside deben disponer de este método para crear una instancia de sus metadatos
   * 
   * @return
   */
  protected abstract T createMetadatos();

  public List<ObjetoInsidePermiso> getPermisos() {
    return permisos;
  }

  public void setPermisos(List<ObjetoInsidePermiso> permisos) {
    this.permisos = permisos;
  }

  @Override
  public String toString() {
    String coma = ", ";
    StringBuffer sb = new StringBuffer("ObjetoInside=[");
    sb.append("identificador=" + identificador + coma);
    sb.append("identificadorRepositorio=" + identificadorRepositorio + coma);
    if (metadatos == null) {
      sb.append("metadatos=null" + coma);
    } else {
      sb.append("metadatos=" + metadatos.toString() + coma);
    }
    sb.append("fechaBaja=" + InsideConverterUtils.calendarToStringISO8601(fechaBaja));
    sb.append("]");
    return sb.toString();
  }

}
