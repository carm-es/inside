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

package es.mpt.dsic.inside.service.util;

public class Constantes {

  public class csvStorage {

    public static final String GUARDADOCORRECTO = "0";

    public static final String YAEXISTE = "1";

  }

  public static final String TEMPLATE_DEFAULT = "DEFECTO";
  public static final String ELEMENTO_DOCUMENTO = "DOC";
  public static final String ELEMENTO_EXPEDIENTE = "EXP";


  // INSIDE
  // ****************************************************************************************************************************

  // valores de los roles correspondientes al id de la tabla InsideRol
  public static final int INSIDE_GESTOR_ROL = 0;
  public static final int INSIDE_ADMINISTRADORORGANISMO_ROL = 1;
  public static final int INSIDE_REMISOR_ROL = 2;
  public static final int INSIDE_CONSULTA_ROL = 3;
  public static final int INSIDE_REDACTOR_ROL = 4;

  // **************************************************************************************************************************************


  // PORTALES
  // ****************************************************************************************************************************

  // roles dados de alta en Portales con estas descripciones
  public static final String INSIDE_GESTOR_DESCRIPCION = "INSIDE-Gestor";
  public static final String INSIDE_ADMINISTRADORORGANISMO_DESCRIPCION =
      "INSIDE-AdministradorOrganismo";
  public static final String INSIDE_REMISOR_DESCRIPCION = "INSIDE-Remisor";
  public static final String INSIDE_CONSULTA_DESCRIPCION = "INSIDE-Consulta";
  public static final String INSIDE_REDACTOR_DESCRIPCION = "INSIDE-Redactor";

  // roles dados de alta en Portales con estos identificadores
  public static final String PORTALES_GESTOR_ID = "inside_gestor";
  public static final String PORTALES_ADMINISTRADORORGANISMO_ID = "inside_administrador_organismo";
  public static final String PORTALES_REMISOR_ID = "inside_remisor";
  public static final String PORTALES_CONSULTA_ID = "inside_consulta";
  public static final String PORTALES_REDACTOR_ID = "inside_redactor";


  // Cargos considerados especiales que pasan a inside como role gestor aunque no esten dados de
  // alta en portales con algun rol de inside
  public static final String PORTALES_ALCALDE = "alcalde";
  public static final String PORTALES_ENTIDAD = "entidad";
  public static final String PORTALES_GESTOR_DESIGNADOE = "gestor_designado";
  public static final String PORTALES_INTERV_TESORERO_ESTATAL = "interv_tesorero_estatal";
  public static final String PORTALES_INTERVENCION_TESORERIA_HAB_ESTATAL =
      "intervencion_tesoreria_hab_estatal";
  public static final String PORTALES_SECRETARIO = "secretario";
  public static final String PORTALES_SECRETARIO_ACCIDENTAL = "secretario_accidental";
  public static final String PORTALES_SECRETARIO_ESTATAL = "secretario_estatal";
  public static final String PORTALES_SECRETARIO_INTERINO = "secretario_interino";
  public static final String PORTALES_TECNICO = "tecnico";
  public static final String PORTALES_PRESIDENTE = "presidente";
  public static final String PORTALES_DIRECTOR_FUNCION_PUBLICA = "director_funcion_publica";

  // **************************************************************************************************************************************


  public static final String MSG_IMPORTAR_DOC_NO_VALIDO_SIN_FIRMAS =
      "importarDocumento.error.docNoValido.sin.firmas";


}
