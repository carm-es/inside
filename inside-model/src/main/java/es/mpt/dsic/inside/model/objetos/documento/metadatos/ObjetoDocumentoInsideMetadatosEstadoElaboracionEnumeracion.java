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

package es.mpt.dsic.inside.model.objetos.documento.metadatos;

import java.io.Serializable;

public enum ObjetoDocumentoInsideMetadatosEstadoElaboracionEnumeracion implements Serializable {

	/**
	- EE01 - Original. 	
	- EE02 - Copia electrónica auténtica con cambio de formato.	
	- EE03 - Copia electrónica auténtica de documento papel. 	
	- EE04 - Copia electrónica parcial auténtica.	
	- EE99 - Otros.
	**/
	EE_01("EE01"),

	EE_02("EE02"),

	EE_03("EE03"),

	EE_04("EE04"),

	EE_99("EE99");
	
    private final String value;

    ObjetoDocumentoInsideMetadatosEstadoElaboracionEnumeracion(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ObjetoDocumentoInsideMetadatosEstadoElaboracionEnumeracion fromValue(String v) {
        for (ObjetoDocumentoInsideMetadatosEstadoElaboracionEnumeracion c: ObjetoDocumentoInsideMetadatosEstadoElaboracionEnumeracion.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
