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

package es.mpt.dsic.inside.store.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

@Entity
@Table(name = "UnidadUsuario")
public class UnidadUsuario implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer idUsuario;
	private NumeroProcedimiento procedimiento;
	private Integer idRol;
	private boolean activo;
	private UnidadOrganica unidad;
	

	public UnidadUsuario() {
	}

	@Id
	@TableGenerator(name = "GeneradorPk_UnidadUsuario", table = "GeneradorClaves", pkColumnName = "GenName", valueColumnName = "GenValue", pkColumnValue = "GEN_UnidadUsuario", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "GeneradorPk_UnidadUsuario")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "activo", nullable = false)
	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Column(name = "id_usuario", nullable = false)
	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}


//	public Integer getIdProcedimiento() {
//		Integer idProc = null;
//		if (procedimiento != null) {
//			idProc = procedimiento.getId();
//		}
//		return idProc;
//	}

//	public void setIdProcedimiento(Integer idProcedimiento) {
//		if (procedimiento == null) {
//			procedimiento = new NumeroProcedimiento();
//		}
//		procedimiento.setId(idProcedimiento);
//	}

	@Column(name = "id_rol", nullable = false)
	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_unidad", nullable = false, insertable = false, updatable = false)
	public UnidadOrganica getUnidad() {
		return unidad;
	}

	public void setUnidad(UnidadOrganica unidadOrganica) {
		this.unidad = unidadOrganica;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_procedimiento", nullable = true, insertable = false, updatable = false)
	public NumeroProcedimiento getProcedimiento() {
		return procedimiento;
	}

	public void setProcedimiento(NumeroProcedimiento procedimiento) {
		this.procedimiento = procedimiento;
	}
	
	@Transient
	public Integer getIdProcedimiento() {
		Integer idProc = null;
		if (procedimiento != null) {
			idProc = procedimiento.getId();
		}
		return idProc;
	}


}
