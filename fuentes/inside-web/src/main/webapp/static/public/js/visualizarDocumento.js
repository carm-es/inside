$(document).ready(function() {
    configurarPlUploadVisualizarDocumento();
    var navegador = navegadorEI();
    $("#visualizarDocumentoForm #navegador").val(navegador);
});

function configurarPlUploadVisualizarDocumento() {

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

    //Salta con las cabeceras del la response. Xej. un 500
    uploader.bind('Error', function(up, err) {
        console.log("Inicio Error");
        console.log(arguments);
        console.log("Fin Error");
    });

    uploader.bind('FilesAdded', function(up, file_plupload) {
        setTimeout(function() {
            $('#progressDocumento').attr('value', 0).removeClass('hidden');
            $('#button_visualizar').attr('disabled', 'disabled');
            uploader.start();
        }, 1000);
    });

    uploader.bind('UploadComplete', function(up, file_plupload) {
        $('#progressDocumento').addClass('hidden');
        $('#button_visualizar').removeAttr('disabled');
        if (file_plupload.length) {
            $('#documento_text').html(file_plupload[file_plupload.length - 1].name);
            $("#documentId").val(file_plupload[file_plupload.length - 1].name);
        }
    });

    uploader.init();
}

function openModalSeleccionarPlantilla(errorContenido, errorExtension) {
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

    if (filename == '') {
        alert(errorContenido);
    } else if (extension != 'xml') {
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
                        '<li class="mf-buttonbar--item"><button ' + (v.submit ? 'type="submit"' : '')
                                + ' class="buttonbar--btn ' + v.clase + '" href="#!">' + v.texto + '</a></li>')
                        .appendTo(ret);
            });
    return ret;
}

function navegadorEI() {
    if (navigator.userAgent.indexOf('Firefox') != -1 || navigator.userAgent.indexOf('Chrome') != -1) {
        return false;
    } else {
        return true;
    }
}

function visualizaDescargarContenido() {
    $("#visualizaDescargarContenidoDocForm").submit();
}

function inicializarVisualizacion() {
    $("#visualizarDocumentoInicializarForm").submit();
}