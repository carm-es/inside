$(document).ready(function() {
    configurarPlUploadImportarDocumento();

    // Autocompletado del campo expedientes.
    $("#identificadorExpediente").autocomplete({
        source : $("#context").val() + '/autocomplete/expedientes?allExp=false&uniAct=true',
        minLength : 0,
        autoFocus : true,
        select : function(event, ui) {
            $("#identificadorExpediente").val(ui.item.key);
            event.preventDefault();
        }
    }).bind('focus', function() {
        $(this).autocomplete("search");
    }).data("ui-autocomplete")._renderItem = function(ul, item) {
        return $("<li>").append("<a>" + item.value + "</a>").appendTo(ul);
    };

    MiniApplet.cargarMiniApplet(baseDownloadURL);
});

function configurarPlUploadImportarDocumento() {
    uploader = new plupload.Uploader({
        chunk_size : '10mb',
        max_file_size : "10gb",
        browse_button : 'documento_button',
        url : $("#context").val() + "/uploadTempData",
        unique_names : false,
        max_file_count : 1
    });

    uploader.bind('UploadProgress', function(up, file) {
        $('#progressDocumento').attr('value', file.percent);
    });

    // Salta con las cabeceras del la response. Xej. un 500
    uploader.bind('Error', function(up, err) {
        console.log("Inicio Error");
        console.log(arguments);
        console.log("Fin Error");
    });

    uploader.bind('FilesAdded', function(up, file_plupload) {
        setTimeout(function() {
            $('#progressDocumento').attr('value', 0).removeClass('hidden');
            $('#button_importar').attr('disabled', 'disabled');
            uploader.start();
        }, 1000);
    });

    uploader.bind('UploadComplete', function(up, file_plupload) {
        $('#progressDocumento').addClass('hidden');
        $('#button_importar').removeAttr('disabled');
        if (file_plupload.length) {
            $('#documento_text').html(file_plupload[file_plupload.length - 1].name);
            $("#documentId").val(file_plupload[file_plupload.length - 1].name);
        }
    });

    uploader.init();
}

function importarDocumento() {
    var exp = $("#identificadorExpediente").val();
    if (exp.trim()) {
        if ($("input[name=firmaExp][value='client']").is(':checked')) {
            sendDocumento(exp, false);
        } else if ($("input[name=firmaExp][value='server']").is(':checked')) {
            sendDocumento(exp, true);
        } else {
            alert('Debe seleccionar un tipo de firma para el expediente. Si no tiene habilitadas opciones contacte con el administrador.');
        }
    } else {
        sendDocumento();
    }
}

function sendDocumento(idExpediente, firmaServidor) {
    $('#importarVeil').removeAttr('style').removeClass('hidden');
    ocultarMensaje();
    $.ajax({
        url : $("#context").val() + '/importNewDocumentXml',
        type : 'POST',
        dataType : 'json',
        data : {
            "documentId" : JSON.stringify($("#documentId").val()),
            "identificadorExpediente" : JSON.stringify(idExpediente),
            "firmaServidor" : firmaServidor
        },
        success : function(data) {
            if (data.expedienteEni != null && !firmaServidor) {
                callAfirma(idExpediente, data.expedienteEni);
            } else {
                if (data.mensajeUsuario != null) {
                    $("#tipoMensaje").val(data.mensajeUsuario.level);
                    $("#valorMensaje").val(data.mensajeUsuario.message);
                    showMessage();
                }
            }
            $('#importarVeil').addClass('hidden');
        },
        error : function(xhr) {
            console.error(xhr.responseText);
            $('#importarVeil').addClass('hidden');
        }
    });
}

function signCorrect(signatureB64) {
    var idExpediente = sessionStorage.getItem('idExpediente');
    var expedient = sessionStorage.getItem('expedient');
    eval("updateIndexExp(idExpediente, expedient, signatureB64);");
}

function signError(errorType, errorMsg) {
    if (errorType == 'es.gob.afirma.core.AOCancelledOperationException') {
        console.log("operationCancelled");
        error = "operación cancelada";
    } else if (errorType == 'es.gob.afirma.keystores.common.AOCertificatesNotFoundException') {
        console.log("No se ha encontrado certificado en el almacen");
        error = "No se ha encontrado certificado en el almacen";
    } else {
        console.log(errorMsg);
        error = "No se pudo realizar la firma, inténtelo más tarde o contacte con el administrador";
    }
    $('#documentVeil').addClass('hidden');
    $("#tipoMensaje").val(4);
    $("#valorMensaje").val("Error en el proceso de firma: " + error);
    showMessage();
}

function callAfirma(idExpediente, expedient) {
    var id_indiceContenido = "EXP_INDICE_CONTENIDO" + idExpediente;
    var dataB64 = btoa(expedient);
    var format = "XAdES";
    var algorithm = "SHA1withRSA";
    var params = "format=XAdES Enveloped\nnodeToSign=" + id_indiceContenido;
    sessionStorage.setItem('idExpediente', idExpediente);
    sessionStorage.setItem('expedient', expedient);
    MiniApplet.sign(dataB64, algorithm, format, params, signCorrect, signError);
}

function updateIndexExp(idExpediente, expedient, dataSign) {
    $.ajax({
        url : $("#context").val() + '/updateIndexExp',
        type : 'POST',
        dataType : 'json',
        data : {
            "indexExpB64" : JSON.stringify(dataSign),
            "expedienteEni" : JSON.stringify(expedient),
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

function mostrarUocultarFirmaExpediente() {
    if ($("#identificadorExpediente").val().trim() != "") {
        $('#firmaExpediente').removeAttr('style').removeClass('hidden');
    } else {
        $('#firmaExpediente').addClass('hidden');

    }
}