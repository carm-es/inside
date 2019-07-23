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

package es.mpt.dsic.infofirma.model;

import java.util.Calendar;

/**
 * Información de un firmante de una firma electrónica
 * 
 * @author rus
 *
 */
public class InfoFirmante {

  private String descripcion;
  private String nifcif;
  private String fecha;
  private TipoFirmaFirmante tipoFirmaFirmante;

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public String getNifcif() {
    return nifcif;
  }

  public void setNifcif(String nifcif) {
    this.nifcif = nifcif;
  }

  public String getFecha() {
    return fecha;
  }

  public void setFecha(String fecha) {
    this.fecha = fecha;
  }

  public TipoFirmaFirmante getTipoFirmaFirmante() {
    return tipoFirmaFirmante;
  }

  public void setTipoFirmaFirmante(TipoFirmaFirmante tipoFirmaFirmante) {
    this.tipoFirmaFirmante = tipoFirmaFirmante;
  }



}
