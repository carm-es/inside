var navegador;

$(document).ready(function() {
    $("#valoresToken").css({"max-width": "400px"});
    $("#ficheroArrastradoToken").css({"font-size": "0.900em"});
    $("#contenedorFichero").css({"width": "343px","height": "60px","padding": "20px","border": "2px solid #558899"});
    navegador = navegadorEI();
    $("#navegador").val(navegador);
});



function descargarArchivo(contenidoEnBlob, nombreArchivo) {
    
   if (navigator.userAgent.indexOf('Firefox') !=-1 || navigator.userAgent.indexOf('Chrome') !=-1) 
   {
	   var reader = new FileReader();
	    reader.onload = function (event) {
	        var save = document.createElement('a');
	        save.href = event.target.result;
	        save.target = '_blank';
	        save.download = nombreArchivo || 'archivo.dat';
	        var clicEvent = new MouseEvent('click', {
	            'view': window,
	                'bubbles': true,
	                'cancelable': true
	        });
	        save.dispatchEvent(clicEvent);
	        (window.URL || window.webkitURL).revokeObjectURL(save.href);
	    };
	   reader.readAsDataURL(contenidoEnBlob);
	  }
   else
	   {	  
		   if(window.navigator && window.navigator.msSaveBlob){
		        navigator.msSaveBlob( new Blob([contenidoEnBlob], {type:'application/xml'}), nombreArchivo )
		    } 
   	
	   }
        
};

//Funci�n de ayuda: re�ne los datos a exportar en un solo objeto
function obtenerDatosToken() {
    return {
    	Identificador: $(".mf-dialog #tokenIdentificador").val(),
    	CSV: $(".mf-dialog #tokenCsv").val(),
    	UUID: $(".mf-dialog #tokenUuid").val(),
    	FechaCaducidad: $(".mf-dialog #tokenfechaCaducidad").val(),
    	nombreDescarga: $(".mf-dialog #tokenIdentificador").val() +'_'+ $(".mf-dialog #tokenfechaCaducidad").val()
    	//nombreDescarga: $(".mf-dialog #tokenIdentificador").val()
    
    };
};

//Funci�n de ayuda: "escapa" las entidades XML necesarias
//para los valores (y atributos) del archivo XML
function escaparXML(cadena) {
    if (typeof cadena !== 'string') {
        return '';
    };
    cadena = cadena.replace('&', '&amp;')
        .replace('<', '&lt;')
        .replace('>', '&gt;')
        .replace('"', '&quot;');
    return cadena;
};

//Genera un objeto Blob con los datos en un archivo XML
function generarTokenXml(datos) {
    var texto = [];
    texto.push('<?xml version="1.0" encoding="UTF-8" ?>\n');
    texto.push('<token>\n');
    texto.push('\t<Identificador>');
    texto.push(escaparXML(datos.Identificador));
    texto.push('</Identificador>\n');
    texto.push('\t<CSV>');
    texto.push(escaparXML(datos.CSV));
    texto.push('</CSV>\n');
    texto.push('\t<UUID>');
    texto.push(escaparXML(datos.UUID));
    texto.push('</UUID>\n');
//    texto.push('\t<FechaCaducidad>');
//    texto.push(escaparXML(datos.FechaCaducidad));
//    texto.push('</FechaCaducidad>\n');
    texto.push('</token>');
    //No olvidemos especificar el tipo MIME correcto :)
    return new Blob(texto, {
        type: 'application/xml'
    });
};

function descargarTokenXML() {
	var datos = obtenerDatosToken();
	var nombreArchivo = datos.nombreDescarga + '.xml';
	descargarArchivo(generarTokenXml(datos), nombreArchivo);
}



//Genera un objeto Blob con los datos en un archivo TXT
function generarTokenTexto(datos) {
  var texto = [];
 
  texto.push('Identificador:\t');
  texto.push(datos.Identificador);
  texto.push('\r\n');
  texto.push('CSV:\t\t');
  texto.push(datos.CSV);
  texto.push('\r\n');
  texto.push('UUID:\t\t');
  texto.push(datos.UUID);
  texto.push('\r\n');
//  texto.push('FechaCaducidad:\t');
//  texto.push(datos.FechaCaducidad);
  
  return new Blob(texto, {
      type: 'text/plain'
  });
};

function descargarTokenTXT() {
	var datos = obtenerDatosToken();
	var nombreArchivo = datos.nombreDescarga + '.txt';
	descargarArchivo(generarTokenTexto(datos), nombreArchivo);
}

function handleFileSelectToken(origen,evt) {
    evt.stopPropagation();
    evt.preventDefault();

    var areaArchivo = evt.dataTransfer.files; 
    var fileToken = areaArchivo[0];

    if(origen!=null)
    	$(".mf-dialog #ficheroArrastradoToken").val(escape(fileToken.name));
    else
    	$("#ficheroArrastradoToken").val(escape(fileToken.name));

    var reader = new FileReader();

    var xmlToken;
    reader.onload = function (evt) {

    	xmlToken = evt.target.result; 
    	
        if (window.DOMParser)
        {    	
          	parser=new DOMParser();
          	xmlDoc=parser.parseFromString(xmlToken,"application/xml");
        }
        else // Internet Explorer
        {	 
          	xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
          	xmlDoc.async=false;
          	xmlDoc.loadXML(txt);
        }
        
        // Recupera identificador
        var identificadorXML = xmlDoc.getElementsByTagName("Identificador")[0].childNodes[0].nodeValue;
        if(origen!=null)
        	$(".mf-dialog #tokenIdentificador").val(identificadorXML);
        else
        	$("#tokenIdentificador").val(identificadorXML);
        
        // Recupera CSV
        var CSVXML = xmlDoc.getElementsByTagName("CSV")[0].childNodes[0].nodeValue;
        if(origen!=null)
        	$(".mf-dialog #tokenCsv").val(CSVXML);
        else
        	$("#tokenCsv").val(CSVXML);
        
        // Recupera UUID
        var UUIDXML = xmlDoc.getElementsByTagName("UUID")[0].childNodes[0].nodeValue;
        if(origen!=null)
        	$(".mf-dialog #tokenUuid").val(UUIDXML);
        else
        	$("#tokenUuid").val(UUIDXML);
        
    };
    
    reader.readAsText(fileToken);  

  }

  function handleDragOverToken(evt) {
    evt.stopPropagation();
    evt.preventDefault();
    evt.dataTransfer.dropEffect = 'copy'; 
  }
  
  function navegadorEI() {
		if (navigator.userAgent.indexOf('Firefox') !=-1 || navigator.userAgent.indexOf('Chrome') !=-1) 
		   {
			return false;
		   }
		else
		{
			return true;
		}
	}