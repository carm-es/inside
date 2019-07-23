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

package es.mpt.dsic.inside.test.unitarios.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInside;
import es.mpt.dsic.inside.service.InSideService;
import es.mpt.dsic.inside.test.unitarios.InSideServiceTestBase;

public class InsideServiceTest extends InSideServiceTestBase {

  @Autowired
  @Qualifier("insideService")
  private InSideService insideService;

  @Test
  public void altaDocumento() {
    ObjetoDocumentoInside entrada = UtilsTest.createObjetoDocumentoInside(true, false, true, false);

    try {
      ObjetoDocumentoInside salida = insideService.altaDocumento(entrada, null, false);
      int f = 0;
    } catch (Exception e) {
      e.printStackTrace();
      Assert.assertTrue(false);
    }

    Assert.assertTrue(true);
  }

}
