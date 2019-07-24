$(document).ready(function() {
	configurarPlUploadVisualizarDocumento();
	var navegador = navegadorEI();
	$("#visualizarDocumentoForm #navegador").val(navegador);
	
	var _base64Response = $('.js-pdf-data').data('pdfbase64');
	if(_base64Response!=null && _base64Response!=""){

        
		regExp = new RegExp('\n', 'g');
		_base64Response = _base64Response.replace(regExp,'');
		_base64 = atob(_base64Response);
		
		PDFJS.getDocument({data: _base64}).then(function getPdfHelloWorld(pdf) {
	        
	        _pdf = pdf;
	        _currentPage = 1;
	        pdf.getPage(1).then(onGetPage);
	        $('.js-pdf-next').on('click',nextPage);
	        $('.js-pdf-prev').on('click',prevPage);

	    });
	
	}
	
	
});

function configurarPlUploadVisualizarDocumento() {

	uploader = new plupload.Uploader({
        chunk_size: '10mb',
        max_file_size: "10gb",
		browse_button: 'documento_button',
		url: $("#context").val() + "/uploadTempData",
		unique_names : false,
		max_file_count : 1
	});

    uploader.bind('UploadProgress', function (up, file) {
		$('#progressDocumento').attr('value', file.percent);
    });

    //Salta con las cabeceras del la response. Xej. un 500
    uploader.bind('Error', function (up, err) {
    	console.log("Inicio Error");
        console.log(arguments);
        console.log("Fin Error");
    });

    uploader.bind('FilesAdded', function (up, file_plupload) {
    	setTimeout(function () {
    		$('#progressDocumento').attr('value', 0).removeClass('hidden');
    		$('#button_visualizar').attr('disabled', 'disabled');
            uploader.start();
        }, 1000);
    });

    uploader.bind('UploadComplete', function (up, file_plupload) {
		$('#progressDocumento').addClass('hidden');
		$('#button_visualizar').removeAttr('disabled');
		if (file_plupload.length) {
			$('#documento_text').html(file_plupload[file_plupload.length - 1].name);
			$("#documentId").val(file_plupload[file_plupload.length - 1].name);
		}
    });

    uploader.init();
}

function openModalSeleccionarPlantilla(errorContenido,errorExtension) {
	var divData = $("#initModalSeleccionPlantilla");
	var data = divData.data();
	var buttons = createButtonsDialog([ {
		clase : 'js-seleccionarPlantilla-cancelar',
		texto : 'Cancelar'
	}, {
		clase : 'js-seleccionarPlantilla-visualizar',
		texto : 'Confirmar'
	} ]);

	buttons.find('.js-seleccionarPlantilla-visualizar').on('click', function(e) {
		e.preventDefault();
		$("#plantilla").val($('.mf-dialog #plantillaModal').val());
		$("#visualizarDocumentoForm").submit();
		$mf.my_dialog.close_dialog();
	});

	buttons.find('.js-seleccionarPlantilla-cancelar').on('click', function(e) {
		e.preventDefault();
		$mf.my_dialog.close_dialog();
	});

	data.buttons = buttons;
	data.data = divData.html();
	
	var filename = $("#documentId").val();
    var extension = filename.replace(/^.*\./, '');
    if (extension == filename) {
        extension = '';
    } else {
        extension = extension.toLowerCase();
    }
    
    if (filename == ''){
    	alert(errorContenido);
    } else if (extension != 'xml'){
    	alert(errorExtension);
    } else {   	
    	$mf.my_dialog.appendDialog(data, true, true);
    }
}

function createButtonsDialog(buttons) {
	var ret = $('<ul class="mf-buttonbar">');
	$(buttons).each(
			function(k, v) {
				$(
						'<li class="mf-buttonbar--item"><button '
								+ (v.submit ? 'type="submit"' : '')
								+ ' class="buttonbar--btn ' + v.clase
								+ '" href="#!">' + v.texto + '</a></li>')
						.appendTo(ret);
			});
	return ret;
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

function visualizaDescargarContenido() {
	$("#visualizaDescargarContenidoDocForm").submit();
}

function inicializarVisualizacion(){
	$("#visualizarDocumentoInicializarForm").submit();
}

var _pdf = null,
_currentPage;

var disableButtons = function(){
    $('.js-pdf-next').attr('disabled','disabled');
    $('.js-pdf-prev').attr('disabled','disabled');
};
var enableButtons = function(){
    $('.js-pdf-next').removeAttr('disabled');
    $('.js-pdf-prev').removeAttr('disabled');
    
};

var isAgoodPage = function(numPage){
    return ((numPage > 0) && (numPage <= _pdf.numPages));
};

var nextPage = function(){
    if(isAgoodPage((_currentPage + 1))){
        _currentPage +=1;
        _pdf.getPage(_currentPage).then(onGetPage);
		disableButtons();
    }
	
};


var prevPage = function(){
    if(isAgoodPage(_currentPage - 1)){
        _currentPage -=1;
        _pdf.getPage(_currentPage).then(onGetPage);
		disableButtons();
    }
};



var onGetPage = function(page){
    var anchoCanvas = $('#pdfContainer').width();
    var altoCanvas = 1024;
    var anchoPagPDF = page.pageInfo.view[2];
    var altoPagPDF = page.pageInfo.view[3];
    var factorAnchos = anchoCanvas / anchoPagPDF;
    var factorAltos = altoCanvas / altoPagPDF;
    var scale = Math.min (anchoCanvas / anchoPagPDF, altoCanvas / altoPagPDF);
    
    
    var viewport = page.getViewport(scale);
    // Prepare canvas using PDF page dimensions.
    var canvas = document.getElementById('pdfContainer');
    var context = canvas.getContext('2d');
    canvas.height = altoCanvas;
    canvas.width = anchoCanvas;
    // Render PDF page into canvas context.
    var renderContext = {
        canvasContext: context,
        viewport: viewport
    };
    
    page.render(renderContext);
    enableButtons();    
   
};




