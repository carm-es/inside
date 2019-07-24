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

  public static final String MSG_IMPORTAR_DOC_NO_VALIDO_SIN_FIRMAS =
      "importarDocumento.error.docNoValido.sin.firmas";

  public static final String METADATO_NOMBRE_NOMBRE_NATURAL = "eEMGDE3.1.Nombre.NombreNatural";

}
