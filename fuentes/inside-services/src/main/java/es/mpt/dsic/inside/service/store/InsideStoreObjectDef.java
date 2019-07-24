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

package es.mpt.dsic.inside.service.store;

import java.util.Map;
import org.springframework.beans.factory.annotation.Required;
import es.mpt.dsic.inside.model.objetos.ObjetoInside;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideMetadatos;
import es.mpt.dsic.inside.service.object.converter.InsideServiceObjectConverter;
import es.mpt.dsic.inside.service.object.metadatos.metadato.Metadato;
import es.mpt.dsic.inside.service.object.signer.InsideServiceObjectSigner;
import es.mpt.dsic.inside.service.object.validators.InsideServiceObjectValidator;

public class InsideStoreObjectDef<T extends ObjetoInside<K>, K extends ObjetoInsideMetadatos> {

  private Map<String, Metadato<?>> metadatos;

  private InsideServiceObjectConverter<T, K> converter;

  private InsideServiceObjectValidator<T, K> validator;

  private InsideServiceObjectSigner<T, K> signer;

  private Class<T> java_class;

  public Map<String, Metadato<?>> getMetadatos() {
    return metadatos;
  }

  @Required
  public void setMetadatos(Map<String, Metadato<?>> metadatos) {
    this.metadatos = metadatos;
  }

  public Class<T> getJavaClass() {
    return java_class;
  }

  public void setJavaClass(Class<T> java_class) {
    this.java_class = java_class;
  }

  public InsideServiceObjectConverter<T, K> getConverter() {
    return converter;
  }

  @Required
  public void setConverter(InsideServiceObjectConverter<T, K> converter) {
    this.converter = converter;
  }

  public InsideServiceObjectValidator<T, K> getValidator() {
    return validator;
  }

  @Required
  public void setValidator(InsideServiceObjectValidator<T, K> validator) {
    this.validator = validator;
  }

  public InsideServiceObjectSigner<T, K> getSigner() {
    return signer;
  }

  public void setSigner(InsideServiceObjectSigner<T, K> signer) {
    this.signer = signer;
  }
}
