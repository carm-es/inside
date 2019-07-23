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

public class Organismo {

  private DatosIdentificativos datosIdentificativos;
  private DatosDependencia datosDependencia;
  private DatosVigencia datosVigencia;

  private String codTipoEntPublica;
  private String codTipoUnidad;
  private String codAmbTerritorial;
  private String codAmbEntGeografica;

  public DatosIdentificativos getDatosIdentificativos() {
    return datosIdentificativos;
  }

  public void setDatosIdentificativos(DatosIdentificativos datosIdentificativos) {
    this.datosIdentificativos = datosIdentificativos;
  }

  public DatosDependencia getDatosDependencia() {
    return datosDependencia;
  }

  public void setDatosDependencia(DatosDependencia datosDependencia) {
    this.datosDependencia = datosDependencia;
  }

  public DatosVigencia getDatosVigencia() {
    return datosVigencia;
  }

  public void setDatosVigencia(DatosVigencia datosVigencia) {
    this.datosVigencia = datosVigencia;
  }

  public String getCodTipoEntPublica() {
    return codTipoEntPublica;
  }

  public void setCodTipoEntPublica(String codTipoEntPublica) {
    this.codTipoEntPublica = codTipoEntPublica;
  }

  public String getCodTipoUnidad() {
    return codTipoUnidad;
  }

  public void setCodTipoUnidad(String codTipoUnidad) {
    this.codTipoUnidad = codTipoUnidad;
  }

  public String getCodAmbTerritorial() {
    return codAmbTerritorial;
  }

  public void setCodAmbTerritorial(String codAmbTerritorial) {
    this.codAmbTerritorial = codAmbTerritorial;
  }

  public String getCodAmbEntGeografica() {
    return codAmbEntGeografica;
  }

  public void setCodAmbEntGeografica(String codAmbEntGeografica) {
    this.codAmbEntGeografica = codAmbEntGeografica;
  }

}
