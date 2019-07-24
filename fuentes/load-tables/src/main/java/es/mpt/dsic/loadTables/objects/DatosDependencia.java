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

package es.mpt.dsic.loadTables.objects;

import es.mpt.dsic.loadTables.objects.unitarios.ObjetoSimple;

public class DatosDependencia {
  private ObjetoSimple unidadSuperiorJerarquica;
  private ObjetoSimple unidadOrganicaRaiz;
  private ObjetoSimple unidadRaizEntidadDerechoPublico;
  private byte nivelJerarquico;

  public ObjetoSimple getUnidadSuperiorJerarquica() {
    return unidadSuperiorJerarquica != null ? unidadSuperiorJerarquica : new ObjetoSimple();
  }

  public void setUnidadSuperiorJerarquica(ObjetoSimple unidadSuperiorJerarquica) {
    this.unidadSuperiorJerarquica = unidadSuperiorJerarquica;
  }

  public ObjetoSimple getUnidadOrganicaRaiz() {
    return unidadOrganicaRaiz != null ? unidadOrganicaRaiz : new ObjetoSimple();
  }

  public void setUnidadOrganicaRaiz(ObjetoSimple unidadOrganicaRaiz) {
    this.unidadOrganicaRaiz = unidadOrganicaRaiz;
  }

  public ObjetoSimple getUnidadRaizEntidadDerechoPublico() {
    return unidadRaizEntidadDerechoPublico != null ? unidadRaizEntidadDerechoPublico
        : new ObjetoSimple();
  }

  public void setUnidadRaizEntidadDerechoPublico(ObjetoSimple unidadRaizEntidadDerechoPublico) {
    this.unidadRaizEntidadDerechoPublico = unidadRaizEntidadDerechoPublico;
  }

  public byte getNivelJerarquico() {
    return nivelJerarquico;
  }

  public void setNivelJerarquico(byte nivelJerarquico) {
    this.nivelJerarquico = nivelJerarquico;
  }

  public void setUnidadSuperiorJerarquicaCodigo(String codigo) {
    if (this.unidadSuperiorJerarquica == null) {
      this.unidadSuperiorJerarquica = new ObjetoSimple(new String[] {"codigo", codigo});
    } else {
      this.unidadSuperiorJerarquica.setCodigo(codigo);
    }
  }

  public void setUnidadSuperiorJerarquicaDescripcion(String descripcion) {
    if (this.unidadSuperiorJerarquica == null) {
      this.unidadSuperiorJerarquica = new ObjetoSimple(new String[] {"descripcion", descripcion});
    } else {
      this.unidadSuperiorJerarquica.setDescripcion(descripcion);
    }
  }

  public void setUnidadRaizEntidadDerechoPublicoCodigo(String codigo) {
    if (this.unidadRaizEntidadDerechoPublico == null) {
      this.unidadRaizEntidadDerechoPublico = new ObjetoSimple(new String[] {"codigo", codigo});
    } else {
      this.unidadRaizEntidadDerechoPublico.setCodigo(codigo);
    }
  }

  public void setUnidadRaizEntidadDerechoPublicoDescripcion(String descripcion) {
    if (this.unidadRaizEntidadDerechoPublico == null) {
      this.unidadRaizEntidadDerechoPublico =
          new ObjetoSimple(new String[] {"descripcion", descripcion});
    } else {
      this.unidadRaizEntidadDerechoPublico.setDescripcion(descripcion);
    }
  }

  public void setUnidadOrganicaRaizCodigo(String codigo) {
    if (this.unidadOrganicaRaiz == null) {
      this.unidadOrganicaRaiz = new ObjetoSimple(new String[] {"codigo", codigo});
    } else {
      this.unidadOrganicaRaiz.setCodigo(codigo);
    }
  }

  public void setUnidadOrganicaRaizDescripcion(String descripcion) {
    if (this.unidadOrganicaRaiz == null) {
      this.unidadOrganicaRaiz = new ObjetoSimple(new String[] {"descripcion", descripcion});
    } else {
      this.unidadOrganicaRaiz.setDescripcion(descripcion);
    }
  }

}
