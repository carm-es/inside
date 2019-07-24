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

package es.mpt.dsic.inside.service.visualizacion;

import java.util.ArrayList;
import java.util.List;

public class OpcionesVisualizacionIndice {

  private boolean estamparImagen;
  private boolean estamparNombreOrganismo;
  private List<String> lineasNombreOrganismo;
  private boolean externo;
  private boolean estamparPie;
  private String textoPie;

  public OpcionesVisualizacionIndice() {

  }



  public boolean isEstamparNombreOrganismo() {
    return estamparNombreOrganismo;
  }



  public void setEstamparNombreOrganismo(boolean estamparNombreOrganismo) {
    this.estamparNombreOrganismo = estamparNombreOrganismo;
  }



  public boolean isEstamparImagen() {
    return estamparImagen;
  }


  public void setEstamparImagen(boolean estamparImagen) {
    this.estamparImagen = estamparImagen;
  }


  public List<String> getLineasNombreOrganismo() {
    if (lineasNombreOrganismo == null) {
      lineasNombreOrganismo = new ArrayList<String>();
    }
    return lineasNombreOrganismo;
  }


  public void setLineasNombreOrganismo(List<String> lineasNombreOrganismo) {
    this.lineasNombreOrganismo = lineasNombreOrganismo;
  }


  public boolean isExterno() {
    return externo;
  }


  public void setExterno(boolean externo) {
    this.externo = externo;
  }



  public boolean isEstamparPie() {
    return estamparPie;
  }



  public void setEstamparPie(boolean estamparPie) {
    this.estamparPie = estamparPie;
  }



  public String getTextoPie() {
    return textoPie;
  }



  public void setTextoPie(String textoPie) {
    this.textoPie = textoPie;
  }



}
