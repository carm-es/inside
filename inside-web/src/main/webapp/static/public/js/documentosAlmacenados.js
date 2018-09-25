var dataFileExp;

$(document).ready(function() {
	
	tableDocumentos = $('#documentList').dataTable({
		"language": {
			"sProcessing":     "Procesando...",
			"sLengthMenu":     "Mostrar _MENU_",
			"sZeroRecords":    "No se encontraron resultados",
			"sEmptyTable":     "Ningún dato disponible en esta tabla",
			"sInfo":           "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
			"sInfoEmpty":      "Mostrando registros del 0 al 0 de un total de 0 registros",
			"sInfoFiltered":   "(filtrado de un total de _MAX_ registros)",
			"sInfoPostFix":    "",
			"sSearch":         "Buscar",
			"sUrl":            "",
			"sInfoThousands":  ",",
			"sLoadingRecords": "Cargando...",
			"oPaginate": {
				"sFirst":    "Primero",
				"sLast":     "Último",
				"sNext":     "Siguiente",
				"sPrevious": "Anterior"
			},
			"oAria": {
				"sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
				"sSortDescending": ": Activar para ordenar la columna de manera descendente"
			}
        },
        "lengthMenu": [ [10, 25, 50, -1], [10, 25, 50, "Todo"] ],
        "pageLength": 25,
        "dom": '<"top fld dsp_ib" f><"fld dsp_ib" l>t<"bottom" ir><"clear">',
		"bSort" : false,
		"aoColumns" : [ {
			"bSortable" : false,
			"width" : "20%"
		}, {
			"bSortable" : false,
			"width" : "10%"
		}, {
			"class" : "tc",
			"bSortable" : false,
			"width" : "5%"
		}, ],
		"paging" : true
	});
		
});


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

function editarDocumento(documento) {
	$('#documnetoVeil').removeAttr('style').removeClass('hidden');
	$("#editarDocumentoForm #identificador").val(documento);
	var ocultar = navegadorEI();
	$("#editarDocumentoForm #navegador").val(ocultar);
	$("#editarDocumentoForm").submit();
}

function borrarDocumento(documento) {
	$("#borrarDocumentoForm #identificador").val(documento);
	$("#borrarDocumentoForm").submit();
}

function mostrarDialogDeleteDocumento(documento) {
    var divData = $('#init-modal-borrar-documento');
	var data = divData.data();
	var buttons = createButtonsDialog([{
		clase: 'js-documento-cancelar',
		texto: 'Cancelar'
	},{
		clase: 'js-documento-borrar',
		texto: 'Confirmar'
	}]);

	buttons.find('.js-documento-borrar').on('click', function (e) {
		e.preventDefault();
		borrarDocumento(documento);
		$mf.my_dialog.close_dialog();
	});

	buttons.find('.js-documento-cancelar').on('click', function (e) {
		e.preventDefault();
		$mf.my_dialog.close_dialog();
	});

    data.buttons = buttons;
	data.data = divData.html();
	
	$mf.my_dialog.appendDialog(data,true,true);
}

function createButtonsDialog(buttons) {
	
	var ret = $('<ul class="mf-buttonbar">');
	
	$(buttons).each(function (k, v) {
		$('<li class="mf-buttonbar--item"><button ' + (v.submit ? 'type="submit"' : '') + ' class="buttonbar--btn ' + v.clase + '" href="#!">' + v.texto + '</a></li>').appendTo(ret);
	});

	return ret;
}