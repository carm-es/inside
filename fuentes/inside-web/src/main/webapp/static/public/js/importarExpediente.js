var expedienteId;
var listaDocId = [];
var uploaderExp;
var uploaderDoc;
var uploaderExpSip;

$(document).ready(function() {
	configurarPlUploadImportarDocumento();
});

function removeDoc() {
	var list = $('#listFileDocEni');

	if (list.val()) {
		var index = listaDocId.indexOf(list.val());
		if (index > -1) {
			listaDocId.splice(index, 1);
		}
		list.find('[id="' + list.val() + '"]').remove();

		if (!list.children().length) {
			$('#removeDocEni').addClass('hidden');
		}
	} else {
		if ($('#listFileDocEni option').size() == 1) {
			listaDocId = [];
			list.empty();
			$('#removeDocEni').addClass('hidden');
		}
	}
}

function configurarPlUploadImportarDocumento() {

	uploaderExp = new plupload.Uploader({
		chunk_size : '10mb',
		max_file_size : "10gb",
		browse_button : 'linkFileExpEni',
		url : $("#context").val() + "/uploadTempData",
		unique_names : false,
		max_file_count : 1
	});

	uploaderExp.bind('UploadProgress', function(up, file) {
		$('#progressExpediente').attr('value', file.percent);
	});

	// Salta con las cabeceras del la response. Xej. un 500
	uploaderExp.bind('Error', function(up, err) {
		console.log("Inicio Error");
		console.log(arguments);
		console.log("Fin Error");
	});

	uploaderExp.bind('FilesAdded', function(up, file_plupload) {
		setTimeout(function() {
			$('#progressExpediente').attr('value', 0).removeClass('hidden');
			$('#butto_importar_expediente').attr('disabled', 'disabled');
			uploaderExp.start();
		}, 1000);
	});

	uploaderExp.bind('UploadComplete', function(up, file_plupload) {
		$('#progressExpediente').addClass('hidden');
		$('#butto_importar_expediente').removeAttr('disabled');
		if (file_plupload.length) {
			var fileName = file_plupload[file_plupload.length - 1].name;
			expedienteId = fileName;
			$('#expediente_text').html(
					file_plupload[file_plupload.length - 1].name);
			//uploaderExp.disableBrowse(true);
		}
	});

	uploaderExp.init();

	uploaderDoc = new plupload.Uploader({
		chunk_size : '10mb',
		max_file_size : "10gb",
		browse_button : 'fileDocEni',
		url : $("#context").val() + "/uploadTempData",
		unique_names : false,
		max_file_count : 1
	});

	uploaderDoc.bind('UploadProgress', function(up, file) {
		$('#progressDocumento').attr('value', file.percent);
	});

	// Salta con las cabeceras del la response. Xej. un 500
	uploaderDoc.bind('Error', function(up, err) {
		console.log("Inicio Error");
		console.log(arguments);
		console.log("Fin Error");
	});

	uploaderDoc.bind('FilesAdded', function(up, file_plupload) {
		setTimeout(function() {
			$('#progressDocumento').attr('value', 0).removeClass('hidden');
			$('#butto_importar_expediente').attr('disabled', 'disabled');
			uploaderDoc.start();
		}, 1000);
	});

	uploaderDoc.bind('UploadComplete', function(up, file_plupload) {
		$('#progressDocumento').addClass('hidden');
		$('#butto_importar_expediente').removeAttr('disabled');
		if (file_plupload.length) {
			var fileName = file_plupload[file_plupload.length - 1].name;
			listaDocId.push(fileName);
			$('#removeDocEni').removeClass('hidden');
			$('#listFileDocEni').append($('<option>', {
				value : fileName,
				text : fileName,
				id : fileName
			}));
		}
	});

	uploaderDoc.init();
	
	uploaderExpSip = new plupload.Uploader({
		chunk_size : '10mb',
		max_file_size : "10gb",
		browse_button : 'linkFileExpSip',
		url : $("#context").val() + "/uploadTempData",
		unique_names : false,
		max_file_count : 1
	});

	uploaderExpSip.bind('UploadProgress', function(up, file) {
		$('#progressExpedienteSip').attr('value', file.percent);
	});

	// Salta con las cabeceras del la response. Xej. un 500
	uploaderExpSip.bind('Error', function(up, err) {
		console.log("Inicio Error");
		console.log(arguments);
		console.log("Fin Error");
	});

	uploaderExpSip.bind('FilesAdded', function(up, file_plupload) {
		setTimeout(function() {
			$('#progressExpedienteSip').attr('value', 0).removeClass('hidden');
			$('#button_importar_expediente_sip').attr('disabled', 'disabled');
			uploaderExpSip.start();
		}, 1000);
	});

	uploaderExpSip.bind('UploadComplete', function(up, file_plupload) {
		$('#progressExpedienteSip').addClass('hidden');
		$('#button_importar_expediente_sip').removeAttr('disabled');
		if (file_plupload.length) {
			var fileName = file_plupload[file_plupload.length - 1].name;
			expedienteId = fileName;
			$('#expediente_sip_text').html(
					file_plupload[file_plupload.length - 1].name);
		}
	});

	uploaderExpSip.init();

}

function importarExpediente(mensajeExp, mensajeDoc) {
	if (!expedienteId) {
		alert(mensajeExp);
	} else if (listaDocId.length == 0) {
		alert(mensajeDoc);
	} else {
		sendDocumento();
	}
}



function sendDocumento() {
	$('#importarVeil').removeAttr('style').removeClass('hidden');
	ocultarMensaje();
	$.ajax({
				url : $("#context").val() + '/importExpedient',
				type : 'POST',
				dataType : 'json',
				data : {
					"listaIdsDocumento" : JSON.stringify(listaDocId),
					"idExpediente" : JSON.stringify(expedienteId)
				},
				success : function(data) {
						if (data.mensajeUsuario != null) {
							$("#tipoMensaje").val(data.mensajeUsuario.level);
							$("#valorMensaje").val(data.mensajeUsuario.message);
							showMessage();
						}

					$('#importarVeil').addClass('hidden');
				},
				error : function(xhr) {
					console.error(xhr.responseText);
					$('#importarVeil').addClass('hidden');
				}
			});
}

function importarExpedienteSip(mensajeExp) {
	if (!expedienteId) {
		alert(mensajeExp);
	} else {
		sendExpedienteSip();
	}	
}

function sendExpedienteSip() {
	$('#importarVeil').removeAttr('style').removeClass('hidden');
	ocultarMensaje();
	$.ajax({
				url : $("#context").val() + '/importarExpedienteFormatoSIP',
				type : 'POST',
				dataType : 'json',
				data : {
					"idExpediente" : expedienteId
				},
				success : function(data) {
					if (data.mensajeUsuario != null) {
						$("#tipoMensaje").val(data.mensajeUsuario.level);
						$("#valorMensaje").val(data.mensajeUsuario.message);
						showMessage();
					}

					$('#importarVeil').addClass('hidden');
				},
				error : function(xhr) {
					console.error(xhr.responseText);
					$('#importarVeil').addClass('hidden');
				}
			});
}

function updateIndexExp(idExpediente, dataSign) {
	$.ajax({
		url : $("#context").val() + '/updateIndexExp',
		type : 'POST',
		dataType : 'json',
		data : {
			"indexExpB64" : JSON.stringify(dataSign),
			"identificadorExpediente" : JSON.stringify(idExpediente)
		},
		success : function(data) {
			if (data.mensajeUsuario != null) {
				$("#tipoMensaje").val(data.mensajeUsuario.level);
				$("#valorMensaje").val(data.mensajeUsuario.message);
				showMessage();
			}
		},
		error : function(xhr) {
			console.error(xhr.responseText);
			$('#importarVeil').addClass('hidden');
		}
	});
}