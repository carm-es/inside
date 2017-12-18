var uploader;

$(document).ready(function() {
	configurarPlUploadValidarExpediente();
});

function configurarPlUploadValidarExpediente() {

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
    		$('#button_validar').attr('disabled', 'disabled');
            uploader.start();
        }, 1000);
    });

    uploader.bind('UploadComplete', function (up, file_plupload) {
		$('#progressDocumento').addClass('hidden');
		$('#button_validar').removeAttr('disabled');
		if (file_plupload.length) {
			$('#documento_text').html(file_plupload[file_plupload.length - 1].name);
			$("#expedientId").val(file_plupload[file_plupload.length - 1].name);
		}
    });

    uploader.init();
}


function validarExpediente() {
	$("#contentValidationData").html('');
	$('#importarVeil').removeClass('hidden');
	$.ajax({
		url : $("#context").val() + '/validarExpediente',
		type : 'POST',
		dataType : 'json',
		timeout: 999999,
		data: $("#validarExpedienteForm").serialize(),
		success : function(data) {
			$('#importarVeil').addClass('hidden');
			if (data.mensajeUsuario != null) {
				$("#tipoMensaje").val(data.mensajeUsuario.level);
				$("#valorMensaje").val(data.mensajeUsuario.message);
				showMessage();
			} else {
				$("#contentValidation").removeClass("hidden");
				$(data.resultado.validacionDetalle).each(function (index, data) {
					var divContent = "<div ";
					if (data.resultadoValidacion) {
						divContent += 'class="fld mf-msg mf-msg__success">';
					} else {
						if (data.tipoValidacion == 'TOVE_03'){
							divContent += 'class="fld mf-msg mf-msg__info">';
						} else {
							divContent += 'class="fld mf-msg mf-msg__error">';
						}
					}
					var tipo = typeof($('#' + data.tipoValidacion).val()) != 'undefined' ? $('#' + data.tipoValidacion).val() : data.tipoValidacion;
					divContent += '<legend>Tipo: ' + tipo + '</legend>';  
					divContent += '<pre>Resultado: ' + data.resultadoValidacion + '</pre>';
					divContent += '<pre>Detalle: ' + data.detalleValidacion + '</pre>';
					$("#contentValidationData").append(divContent);
				});
			}
		},
		error: function(e) {
			$('#importarVeil').addClass('hidden');
			console.error(e);
		}
	});
}