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

package es.mpt.dsic.inside.store.hibernate.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import org.apache.commons.lang.ObjectUtils;

@Entity
@Table(name = "UsuarioInside")
public class UsuarioInside implements java.io.Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private Integer id;
  private String nif;
  private Boolean activo;
  private Date fechaCreacion;
  private Set<UnidadUsuario> listaUnidades = new HashSet<UnidadUsuario>(0);

  private Boolean adminMensaje;

  public UsuarioInside() {}

  public UsuarioInside(Integer id) {
    this.id = id;
  }

  public UsuarioInside(Integer id, String nif) {
    this.id = id;
    this.nif = nif;
  }

  @Id
  // @GeneratedValue(strategy = IDENTITY)
  @TableGenerator(name = "GeneradorPk_UsuarioInside", table = "GeneradorClaves",
      pkColumnName = "GenName", valueColumnName = "GenValue", pkColumnValue = "GEN_UsuarioInside",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "GeneradorPk_UsuarioInside")
  @Column(name = "id", unique = true, nullable = false)
  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Column(name = "nif", unique = true, nullable = false)
  public String getNif() {
    return nif;
  }

  public void setNif(String nif) {
    this.nif = nif;
  }

  @Column(name = "activo", nullable = false)
  public Boolean getActivo() {
    return activo;
  }

  public void setActivo(Boolean activo) {
    this.activo = activo;
  }

  @Column(name = "fechaCreacion", nullable = false)
  public Date getFechaCreacion() {
    return fechaCreacion;
  }

  public void setFechaCreacion(Date fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  }

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "idUsuario")
  public Set<UnidadUsuario> getListaUnidades() {
    return listaUnidades;
  }

  public void setListaUnidades(Set<UnidadUsuario> listaUnidades) {
    this.listaUnidades = listaUnidades;
  }

  @Column(name = "adminMensaje", nullable = false)
  public Boolean getAdminMensaje() {
    return adminMensaje;
  }

  public void setAdminMensaje(Boolean adminMensaje) {
    this.adminMensaje = adminMensaje;
  }


  @Transient
  public UnidadUsuario getUnidadActiva() {
    UnidadUsuario unidad = null;
    if (this.listaUnidades != null && !this.listaUnidades.isEmpty()) {
      for (UnidadUsuario uni : this.listaUnidades) {
        if (uni.isActivo())
          unidad = uni;
      }
    }
    return unidad;
  }

  @Transient
  public UnidadUsuario getUnidad(Integer ideUnidad, Integer ideProcedimiento, Integer ideUsuario) {
    UnidadUsuario unidad = null;
    if (this.listaUnidades != null && !this.listaUnidades.isEmpty()) {
      for (UnidadUsuario uni : this.listaUnidades) {
        if (uni.getUnidad().getId().equals(ideUnidad) && uni.getIdUsuario().equals(ideUsuario)
            && ObjectUtils.equals(uni.getIdProcedimiento(), ideProcedimiento)) {
          unidad = uni;
        }
      }
    }
    return unidad;
  }

  public boolean contieneUnidadActiva() {
    boolean unidadActiva = false;
    if (this.listaUnidades != null && !this.listaUnidades.isEmpty()) {
      for (UnidadUsuario uni : this.listaUnidades) {
        if (uni.isActivo())
          unidadActiva = true;
      }
    }
    return unidadActiva;
  }

}
