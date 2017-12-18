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

package es.mpt.dsic.inside.model.objetos.expediente.metadatos;

public enum ObjetoExpedienteInsideMetadatosEnumeracionEstados {

    E_01("E01"),
    E_02("E02"),
    E_03("E03");
	
    private final String value;

    ObjetoExpedienteInsideMetadatosEnumeracionEstados(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ObjetoExpedienteInsideMetadatosEnumeracionEstados fromValue(String v) {
        for (ObjetoExpedienteInsideMetadatosEnumeracionEstados c: ObjetoExpedienteInsideMetadatosEnumeracionEstados.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}