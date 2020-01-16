# Inside 2.0.8.3
Instalación y evolutivo de la versión distribuible de InSiDE (Infraestructura y Sistemas de Documentación Electrónica).

Se parte de la versión distribuible 2.0.8.3 que se ofrece en el área de descargas de la Suite Inside del Centro de Transferencia Tecnológica: https://administracionelectronica.gob.es/ctt/inside



### Requisitos
Para trabajar con el código fuente necesitarás: 

* Java 1.7 o superior
* Maven 3.3.9 o superior

### Obtener el código fuente
Clona el repositorio de código fuente utilizando git:

```
git clone https://github.com/carm-es/inside.git
```

O crea tu propio `Fork` a fin de reincorporar tus cambios al repositorio utilizando un `Pull Request` [leer más](https://help.github.com/articles/fork-a-repo). 


### Compilar el proyecto
Para compilar el proyecto ejecuta los siguientes comandos en el directorio donde descargarte los fuentes:

```
cd inside
mvn install
```

La aplicación web final la encontrarás en `fuentes/inside-web/target/inside.war`


### Ficheros de configuración, scripts de base de datos y primer despliegue

Los ficheros de configuración se ubican en <raiz_repositorio>/resources/config y deben ubicarse todos en el mismo directorio.

Los ficheros de base de datos se ubican en <raiz_repositorio>/resources/scripts_bbdd.

En los ficheros de configuración se han definido entre dobles llaves los valores a configurar en la instalación: `{{...}}`.

Hay que revisar los ficheros de configuración para realizar el reemplazo de las propiedades adecuadas así como la sustitución de los certificados necesarios en los almacenes JKS, no obstante, para probar el despliegue del war en el servidor de aplicaciones basta con fijar las tres propiedades de conexión a base de datos en el fichero `database.properties` (url, username y password). 

#### VM arguments necesarios
`... -Dconfig.path="/path/to/resources/config/" -DtmpShared.path="/path/to/temp/folder/" -Dinside.hostDomainPort=localhost:8080 -Djavax.net.ssl.trustStore="/path/to/resources/config/trustStore.jks" -Djavax.net.ssl.trustStorePassword="changeit"`


### Dependencia del componente Eeutils
Eeutils es el componente que agrupa diversas funcionalidades relacionadas con la generación de CSV y gestión de firmas e informes. Inside depende de este componente. 

Aunque en los ficheros de configuración que se proporcionan en <raiz_repositorio>/resources/config se marcan todas las Eeutils como desactivadas, algunas son necesarias para que Inside proporcione la funcionalidad mínima para documento y expediente electrónico. 

Mediante [ticket al MINHAP](https://ssweb.seap.minhap.es/ayuda/) se puede solicitar un usuario para acceso a los servicios en la nube de este componente de Eeutils. 

Desde noviembre de 2018 se ha publicado en el Portal de la Administración Electrónica, la primera versión de los distribuibles de Eeutils (v.4.2.0), bajo licencia EUPL, de manera que ya existe la posibilidad de no tener que utilizar los recursos en la nube.

#### Información básica sobre Eeutils

##### Eeutil-Firma
* Firma en servidor.
* Necesaria para alta y modificación de expedientes con operaciones de interfaz WS.
* Si se desactiva se podrían dar de alta expedientes con la firma de cliente desde la interfaz Web, pero no con firma en servidor.
* Activación configurable en fichero `firma.properties`.

##### Eeutil-Oper-Firma
* Obtención de información de firmas para poder:
  * guardar documentos del tipo "Archivo firmado previamente" desde la interfaz Web.
  * guardar documentos usando la operación *altaDocumentoOriginal* de interfaz WS.
* Activación configurable en fichero `infofirma.properties`.  

##### Eeutil-Vis-Docexp
* Si está desactivada no se podría obtener:
  * la visualización del índice de un expediente (en pdf) desde la operación *getExpedienteEni* (la operación funciona pero no devuelve esa información).
  * la visualización de un documento con la operación *visualizarDocumento*, que incluye metadatos entre los que se encuentran las firmas (en este caso la operación no funciona, devolviendo un error 500 - "Error interno de Inside : El WS de VISUALIZACION no se encuentra activo").
* Activación configurable en fichero `visualizacion.properties`.

##### Eeutil-Misc
* Validación ENI.
*	Funcionarían todas las operaciones de interfaz WS y la interfaz Web si se desactiva temporalmente.
* Activación configurable en fichero `enhanced_carm.properties`.

##### Eeutil-Util-Firma
* Es inocua si no se va a utilizar la opción "Generar credenciales de acceso" que se proporciona desde la pantalla de expedientes almacenados.
* Activación configurable en fichero `utilFirma.properties`.
