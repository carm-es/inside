*************************************************************************************************
* inside-src.zip
* Inside se distribuye bajo la la licencia EUPL1.1.
*************************************************************************************************
Los componentes incluidos en inside-src.zip se enumeran a continuaci贸n:

	- fuentes: Contiene el c贸digo fuente de la aplicaci贸n Inside, compuesta por los m贸dulos
		- inside-mvn-base
		- eeutil-client
		- inside-cliente-firma
		- infofirma-service		
		- inside-model
		- inside-services
		- load-tables
		- inside-util-web
		- csvstorage-client
		- inside-web

	- lib: Librer韆s que usa la aplicaci髇 Inside
	- resources
		
		- config
			- schemas: Esquemas XSD del Expediente y Documento ENI
			- afirma-server-triphase-signer.properties
			- afirma.properties
			- almacen.jks
			- clientWSRegistroElectronico.properties												
			- csvstorage.properties
			- database.properties
			- firma.properties
			- infofirma.properties
			- loadTables-config.properties
			- log4j.properties
			- mailToken.properties
			- messages.properties
			- clave.properties
			- SignModule_SP.xml
			- StorkSamlEngine_SP.xml			
			- SamlEngine.xml												
			- siaService.properties
			- temporalData.properties
			- trustStore.jks
			- utilFirma.properties
			- visualizacion.properties
			- ws-security.jks
			- ws-security.properties
			

		- scripts_bbdd: Scripts de BBDD
			- 1_creacion_objetos_mysql.sql
			- 2_inserts_GeneradorClave_mysql.sql
			- 4_quartz.sql

			- 1 - insert_aplicacion.sql
			- 2_inserts_usuarios.sql
			
		
		- endorsed_lib: Librer韆s a copiar en el directorio endorsed de la JRE
	
	- Web Services externos:

		- firma-remota: Especificaci髇 de WS de firma remota (WSDL).
		- infofirma: Especificaci髇 de WS de obtenci髇 de informaci髇 de firmas (WSDL).
		- visualizacion: Especificaci髇 de WS de visualizaci髇 de documentos ENI e 韓dice de expedientes ENI (WSDL).
		- csvstorage: Especificaci髇 de WS de almacenamiento de documentos (WSDL).
		- clientewsregistroelectronico: Especificaci髇 de WS de registro electr髇ico (WSDL).
		- afirma: Especificaci髇 de WS de validar firma (WSDL).
		- loadTables: Especificaci髇 de WS de carga de tablas (WSDL).
		- siaService: Especificaci髇 de WS de carga c骴igo Sia (WSDL).
		 
		
	- licenses: Contiene las licencias bajo las que se distribuyen las librer韆s de las que depende Inside.

	- LICENSE_EUPL_1.1.pdf: Licencia EUPL1.1, bajo la que se distribuye Inside.
	



