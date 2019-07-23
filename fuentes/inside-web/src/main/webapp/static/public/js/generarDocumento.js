var uploader;
var validacionDocumento = {
    correcta : true,
    mensaje : ""
};

var timerModal = {
    getTimer : function($el) {
        var timer = $el && $el.find('.js-timer-veil');
        if (timer && timer.length) {
            return timer;
        }
        return $('.js-timer-veil');
    },
    on : function($el) {
        this.getTimer($el).show();
    },
    off : function($el) {
        this.getTimer($el).hide();
    }
};

function configurarPlUploadGenerarDocumento() {

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
        $("#tipoMensaje").val(4);
        $("#valorMensaje").val("Error en la subida del fichero al servidor: " + err);
        showMessage();
    });

    uploader.bind('FilesAdded', function(up, file_plupload) {
        setTimeout(function() {
            $('#progressDocumento').attr('value', 0).removeClass('hidden');
            $('#button_generarDocumento').attr('disabled', 'disabled');
            $('#button_generarDoc').css('pointer-events', 'none');
            uploader.start();
        }, 1000);
    });

    uploader.bind('UploadComplete', function(up, file_plupload) {
        $('#progressDocumento').addClass('hidden');
        $('#button_generarDocumento').removeAttr('disabled');
        $('#button_generarDoc').css('pointer-events', 'auto');
        if (file_plupload.length) {
            $('#documento_text').html(file_plupload[file_plupload.length - 1].name);
            $("#documentId").val(file_plupload[file_plupload.length - 1].name);
        }
    });

    uploader.init();
}

$(document).ready(function() {

    MiniApplet.cargarMiniApplet(baseDownloadURL);

    // Autocompletado del campo organo.
    $('#organos').autocomplete({
        source : $("#context").val() + '/autocomplete/dir3Vigentes',
        // autoFocus: true,
        focus : function(event, ui) {
            $("#organos").val(ui.item.key + " - " + ui.item.value);
            event.preventDefault();
        },
        select : function(event, ui) {
            $('#organos').val(ui.item.key + " - " + ui.item.value);
            event.preventDefault();
        }
    }).data("ui-autocomplete")._renderItem = function(ul, item) {
        return $("<li>").append("<a>" + item.key + " - " + item.value + "</a>").appendTo(ul);
    };

    // Autocompletado del campo expedientes.
    $("#keyMetadatoAdicional").autocomplete({
        source : $("#context").val() + '/autocomplete/metadatos',
        minLength : 0,
        autoFocus : true,
        select : function(event, ui) {
            $("#keyMetadatoAdicional").val(ui.item.key);
            var elemt = ui.item.key;
            if (elemt.indexOf('Fecha') > -1) {
                $("#valueMetadatoAdicional").val('2016-01-01T00:00:00');
            } else {
                $("#valueMetadatoAdicional").val('');
            }
            event.preventDefault();
        }
    }).bind('focus', function() {
        $(this).autocomplete("search");
    }).data("ui-autocomplete")._renderItem = function(ul, item) {
        return $("<li>").append("<a>" + item.value + "</a>").appendTo(ul);
    };

    checkEstado();
    checkFirma();

    $("#button_guardar").hide();
    $("#button_importar").hide();
    $("#button_descargarDocAdicionales").hide();

    if ($("#generarDocumento #conAdicionales").val() === 'true') {
        $("#button_descargarDocAdicionales").show();
    }
});

function calculaProtocolo() {

    var protocolo = "http";
    var protocoloSeguro = protocolo + "s";

    if (window.location.protocol == protocolo + ":") {
        return protocolo;
    } else
        return protocoloSeguro;
}

function signCorrect(signatureB64) {
    var doc = btoa(unescape(encodeURIComponent(uploader.files[uploader.files.length - 1].name)));
    $("#documentId").val(decodeURIComponent(escape(atob(doc))));
    $("#firmaResultado").val(decodeURIComponent(escape(atob(signatureB64))));
    var callback_method = sessionStorage.getItem('callback_method');
    var operation = sessionStorage.getItem('operation');
    eval(callback_method + "(operation);");
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

function end_authentication(callback_method, operation) {
    if (uploader.files.length) {
        var doc = btoa(unescape(encodeURIComponent(uploader.files[uploader.files.length - 1].name)));
        var mimeType = uploader.files[uploader.files.length - 1].type;
        var format = $("#formatoFirma").val();
        var algorithm = "SHA1withRSA";
        var params = "mode=implicit\ndoc=" + doc + "\nserverUrl=" + calculaProtocolo() + "://" + $("#ip_server").val()
                + ":" + $("#port_server").val() + $("#context").val() + "/SignatureService";

        var sesionDesdeHtml = $("#sesionId").val();
        params = "SESION_POR_PARAMETRO_EXTRA=" + sesionDesdeHtml + "\n" + params;

        // Si es Default se decide por el mimetype del fichero
        if (format == "Default") {
            if (mimeType == "application/pdf") {
                format = "PAdEStri";
            } else if (mimeType == "text/xml" || mimeType == "application/xml") {
                params = "format=XAdES Enveloped\n" + params;
                format = "XAdEStri";
                params = params + "\nmimeType=" + mimeType;
            } else {
                format = "CAdEStri";
            }

        } else {
            if (format == "XAdEStri Enveloped" || format == "XAdEStri Detached") {
                if (format == "XAdEStri Enveloped") {
                    params = "format=XAdES Enveloped\n" + params;
                } else {
                    params = "format=XAdES Enveloped\n" + params;
                }
                format = "XAdEStri";
                params = params + "\nmimeType=" + mimeType;
            }

        }

        sessionStorage.setItem('callback_method', callback_method);
        sessionStorage.setItem('operation', operation);
        MiniApplet.sign(doc, algorithm, format, params, signCorrect, signError);
    } else {
        $("#tipoMensaje").val(4);
        $("#valorMensaje").val("Deber seleccionar un fichero para firmar");
        showMessage();
        $('#documentVeil').addClass('hidden');
    }
}

function authenticate(operation) {
    end_authentication('sendAjaxForm', operation);
}

function checkEstado() {
    if ($("#estadoElaboracion").val() == "EE_02" || $("#estadoElaboracion").val() == "EE_04") {
        $('#identificadorDocOrigen').removeAttr("disabled");
    } else {
        $("#identificadorDocOrigen").val('');
        $('#identificadorDocOrigen').attr('disabled', 'disabled');
    }
}

function checkFirma() {
    if ($("input[name=firma][value='firma']").is(':checked')) {
        $('#certificadoFirma').show();
    } else {
        $('#certificadoFirma').hide();
    }
}

function sendAjaxForm(operation) {
    $.ajax({
        url : $("#context").val() + operation,
        type : 'POST',
        dataType : 'json',
        timeout : 999999,
        data : $("#generarDocumento").serialize(),
        success : function(data) {

            $('#documentVeil').addClass('hidden');
            if (data.mensajeUsuario != null && data.mensajeUsuario.level == "4") {
                $("#tipoMensaje").val(data.mensajeUsuario.level);
                $("#valorMensaje").val(data.mensajeUsuario.message);
                showMessage();
            } else {
                if (data.mensajeUsuario != null && data.errorGuardar != null) {
                    $("#tipoMensaje").val(data.mensajeUsuario.level);
                    $("#valorMensaje").val(data.mensajeUsuario.message);
                    showMessage();
                } else {
                    redireccionaDocumentosAlmacenados(data.mensajeUsuario);
                }
                $("#descargarEniForm #identificador").val($("#generarDocumento #identificador").val());
                $("#descargarAditionalForm #identificador").val($("#generarDocumento #identificador").val());
                $("#verContenidoForm #identificador").val($("#generarDocumento #identificador").val());

                if (data.conAdicionales) {
                    $("#button_descargarDocAdicionales").show();
                } else {
                    $("#button_descargarDocAdicionales").hide();
                }

                if (data.showFirmaServer) {
                    $(".mf-dialog #showFirmaServer").show();
                } else {
                    $(".mf-dialog #showFirmaServer").hide();
                }

                $("#generarDocumento #conAdicionales").val(data.conAdicionales);
                $("#actionButtons").show();
                $("#button_guardar").show();
                $("#button_descargar_contenido_documento").hide();
                $("#button_importar").show();
            }
        },
        error : function(e) {

            $('#documentVeil').addClass('hidden');
            console.error(e);
        }
    });
}

function generarDocumento() {
    var operation = '/generarDocumento';
    createDocument(operation);
}

function checkValidacionDocumento() {
    if ((!$('#csv').val() && $('#regulacionCSV').val()) || ($('#csv').val() && !$('#regulacionCSV').val())) {
        validacionDocumento.correcta = false;
        validacionDocumento.mensaje = 'En caso de firma CSV ambos campos tienen que ir completados';
    } else {
        validacionDocumento.correcta = true;
    }
}

function createDocument(operation) {
    checkValidacionDocumento();
    if (validacionDocumento.correcta) {
        $('#documentVeil').removeAttr('style').removeClass('hidden');

        ocultarMensaje();
        $("#actionButtons").hide();

        habilitarOrganos();

        var cont = 0, i = 0;
        var advData = [];
        while (i < metadatosDocAdded) {
            if ($("#divMetadatoAdicionalDoc_" + cont)[0] != null) {
                var nombre = document.getElementById("metadatosAdicionalesDoc.metadatoAdicional" + cont + ".nombre");
                var valor = document.getElementById("metadatosAdicionalesDoc.metadatoAdicional" + cont + ".valor");
                if (nombre != null && valor != null) {
                    var data = {
                        nombre : nombre.value,
                        valor : valor.value,
                        tipo : "xsd:string"
                    };
                    advData.push(data);
                }
                i++;
            }
            cont++;
        }

        // metadato Nombre Natural
        var nombreNat = $("#keyMetadatoAdicionalNatural").val();
        var valorNat = $("#valueMetadatoAdicionalNatural").val();
        if (nombreNat != null && nombreNat !== "" && valorNat != null && valorNat !== "") {
            var data = {
                nombre : nombreNat,
                valor : valorNat,
                tipo : "xsd:string"
            };
            advData.push(data);
        }

        $("#metadatosAdicionalesDoc").val(JSON.stringify(advData));
        if ($("input[name=firma][value='firma']").is(':checked')) {
            authenticate(operation);
        } else {
            sendAjaxForm(operation);
        }
    } else {
        alert(validacionDocumento.mensaje);
        $('#documentVeil').addClass('hidden');
    }
}

function addOrganoDoc() {
    if ($('#organos').val().trim() != "") {
        if (organosAdded == '') {
            organosAdded = 0;
        }

        var key_input = document.createElement("input");
        var remove_button = document.createElement("button");
        var div = document.createElement("div");

        key_input.setAttribute("type", "text");
        key_input.setAttribute("name", "metadatosEni.organo[" + organosAdded + "]");
        key_input.setAttribute("id", "metadatosEni.organo" + organosAdded);
        key_input.setAttribute("class", "sticked-input-right");
        key_input.setAttribute("disabled", "disabled");
        key_input.setAttribute("value", $('#organos').val());

        remove_button.setAttribute("type", "button");
        remove_button.setAttribute("name", "button");
        remove_button.setAttribute("class", "mf-icon mf-icon-cancel2");
        remove_button.setAttribute("id", "removeOrganoButton_" + organosAdded);
        remove_button.setAttribute("value", "Eliminar");
        remove_button.setAttribute("onclick", "removeOrganoDoc(" + organosAdded + ")");
        div.setAttribute("id", "divOrgano_" + organosAdded);

        $("#listOrganos").append(div);

        $("#divOrgano_" + organosAdded).append(key_input);
        $("#divOrgano_" + organosAdded).append(remove_button);

        $('#organos').val('');
        organosAdded++;
    }
}

function removeOrganoDoc(position) {
    document.getElementById("metadatosEni.organo" + position).remove();
    $("#removeOrganoButton_" + position).remove();
    $("#divOrgano_" + position).remove();
    while (organosAdded - 1 > position) {
        var aux = position + 1;
        document.getElementById("metadatosEni.organo" + aux).setAttribute("name",
                "metadatosEni.organo[" + position + "]");
        document.getElementById("metadatosEni.organo" + aux).setAttribute("id", "metadatosEni.organo" + position);
        $("#removeOrganoButton_" + aux).attr("onclick", "removeOrganoDoc(" + position + ")");
        $("#removeOrganoButton_" + aux).attr("id", "removeOrganoButton_" + position);
        $("#divOrgano_" + aux).attr("id", "divOrgano_" + position);
        position++;
    }
    organosAdded--;
}

function habilitarOrganos() {
    var cont = 0;
    var objeto = document.getElementById("metadatosEni.organo" + cont);
    while (objeto != null) {
        objeto.disabled = false;
        cont++;
        objeto = document.getElementById("metadatosEni.organo" + cont);
    }
}

function descargarDocumento() {
    $("#descargarEniForm").submit();
}

function verContenido() {
    $("#verContenidoForm").submit();
}

function descargarContenido() {
    $("#descargarContenidoDocForm").submit();
}

function backStored() {
    $("#backStoredDocForm").submit();
}

function cancelDocument() {
    $("#cancelDocForm").submit();
}

function addMetadatoDoc(metadatoName, metadatoValue) {
    if ($('#keyMetadatoAdicional').val() && $('#valueMetadatoAdicional').val()) {
        if (typeof (metadatosDocAdded) === 'undefined') {
            window.metadatosDocAdded = 0;
        }
        //se añade un input key y otro input valor de metadato 
        var key_input = document.createElement("input");
        var value_input = document.createElement("input");
        var remove_button = document.createElement("button");
        var div = document.createElement("div");

        key_input.setAttribute("type", "text");
        key_input.setAttribute("id", "metadatosAdicionalesDoc.metadatoAdicional" + metadatosDocAdded + ".nombre");
        key_input.setAttribute("name", "metadatosAdicionalesDoc.metadatoAdicional[" + metadatosDocAdded + "].nombre");
        key_input.setAttribute("class", "sticked-input-right");
        key_input.setAttribute("disabled", "disabled");
        key_input.setAttribute("value", $('#keyMetadatoAdicional').val());
        key_input.setAttribute("style", "width:250px");
        value_input.setAttribute("type", "text");
        value_input.setAttribute("name", "metadatosAdicionalesDoc.metadatoAdicional[" + metadatosDocAdded + "].valor");
        value_input.setAttribute("id", "metadatosAdicionalesDoc.metadatoAdicional" + metadatosDocAdded + ".valor");
        value_input.setAttribute("class", "sticked-input-right");
        value_input.setAttribute("disabled", "disabled");
        value_input.setAttribute("value", $('#valueMetadatoAdicional').val());
        value_input.setAttribute("style", "width:200px");
        remove_button.setAttribute("type", "button");
        remove_button.setAttribute("name", "button");
        remove_button.setAttribute("class", "mf-icon mf-icon-cancel2");
        remove_button.setAttribute("id", "removeMetadatoButton_" + metadatosDocAdded);
        remove_button.setAttribute("value", "Eliminar");
        remove_button.setAttribute("onclick", "removeMetadatoDoc(" + metadatosDocAdded + ")");
        div.setAttribute("id", "divMetadatoAdicionalDoc_" + metadatosDocAdded);

        $("#listMetadatosAdicionalesDoc").append(div);

        var divNombre = document.createElement("div");
        divNombre.setAttribute("id", "divNombre_" + metadatosDocAdded);
        divNombre.setAttribute("class", "fld");
        var divValor = document.createElement("div");
        divValor.setAttribute("id", "divValor_" + metadatosDocAdded);
        divValor.setAttribute("class", "fld");
        var divButton = document.createElement("div");
        divButton.setAttribute("id", "divButton_" + metadatosDocAdded);
        divButton.setAttribute("class", "fld");

        $("#divMetadatoAdicionalDoc_" + metadatosDocAdded).append(divNombre);
        $("#divMetadatoAdicionalDoc_" + metadatosDocAdded).append(divValor);
        $("#divMetadatoAdicionalDoc_" + metadatosDocAdded).append(divButton);

        $("#divNombre_" + metadatosDocAdded).append(key_input);
        $("#divValor_" + metadatosDocAdded).append(value_input);
        $("#divButton_" + metadatosDocAdded).append(remove_button);

        $('#keyMetadatoAdicional').val('');
        $('#valueMetadatoAdicional').val('');
        metadatosDocAdded++;
    }
}

function removeMetadatoDoc(position) {

    $("#metadatosAdicionalesDoc.metadatoAdicional" + position + ".nombre").remove();
    $("#metadatosAdicionalesDoc.metadatoAdicional" + position + ".valor").remove();
    $("#removeMetadatoButton_" + position).remove();
    $("#divMetadatoAdicionalDoc_" + position).remove();

    while (metadatosDocAdded - 1 > position) {
        var aux = position + 1;

        $("#metadatosAdicionalesDoc.metadatoAdicional" + aux + ".nombre").setAttribute("name",
                "metadatosAdicionalesDoc.metadatoAdicional[" + position + "].nombre");
        $("#metadatosAdicionalesDoc.metadatoAdicional" + aux + ".nombre").setAttribute("id",
                "metadatosAdicionalesDoc.metadatoAdicional" + position + ".nombre");
        $("#metadatosAdicionalesDoc.metadatoAdicional" + aux + ".valor").setAttribute("name",
                "metadatosAdicionalesDoc.metadatoAdicional[" + position + "].valor");
        $("#metadatosAdicionalesDoc.metadatoAdicional" + aux + ".valor").setAttribute("id",
                "metadatosAdicionalesDoc.metadatoAdicional" + position + ".valor");

        $("#removeMetadatoButton_" + aux).attr("onclick", "removeMetadatoDoc(" + position + ")");
        $("#removeMetadatoButton_" + aux).attr("id", "removeMetadatoDoc_" + position);
        $("#divMetadatoAdicionalDoc_" + aux).attr("id", "divMetadatoAdicionalDoc_" + position);
        position++;
    }
    metadatosDocAdded--;
}

function mostrarDialogImportarDocumento(mensaje, nuevo) {
    var divData = $('#init-modal-importar-documento');
    var data = divData.data();
    var buttons = createButtonsDialog([ {
        clase : 'js-documento-cancelar',
        texto : 'Cancelar'
    }, {
        clase : 'js-documento-crear',
        texto : 'Importar',
        submit : true
    } ]);

    buttons.find('.js-documento-crear').on('click', function(e) {
        e.preventDefault();
        $('#documentVeil').removeAttr('style').removeClass('hidden');
        var close = importarDocumento(mensaje, nuevo);
        if (close)
            $mf.my_dialog.close_dialog();
    });

    buttons.find('.js-documento-cancelar').on('click', function(e) {
        e.preventDefault();
        $mf.my_dialog.close_dialog();
    });

    data.buttons = buttons;
    data.data = divData.html();

    $mf.my_dialog.appendDialog(data, true, true);

    // Autocompletado del campo expedientes.
    $(".mf-dialog #doc_identificador_expediente").autocomplete({
        source : $("#context").val() + '/autocomplete/expedientes?allExp=false&uniAct=true',
        minLength : 0,
        autoFocus : true,
        select : function(event, ui) {
            $(".mf-dialog #doc_identificador_expediente").val(ui.item.key);
            event.preventDefault();
        }
    }).bind('focus', function() {
        $(this).autocomplete("search");
    }).data("ui-autocomplete")._renderItem = function(ul, item) {
        return $("<li>").append("<a>" + item.value + "</a>").appendTo(ul);
    };

    $("ul[id *= ui-id-]").css('zIndex', 9999);
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

function mostrarDialogIdentificadorDocumento() {
    $('#documentVeil').removeAttr('style').removeClass('hidden');
    var divData = $('#init-modal-avisoIdentificador-documento');
    var data = divData.data();
    var buttons = createButtonsDialog([ {
        clase : 'js-documento-cancelar',
        texto : 'Cerrar'
    } ]);

    buttons.find('.js-documento-cancelar').on('click', function(e) {
        e.preventDefault();
        $mf.my_dialog.close_dialog();
    });

    data.buttons = buttons;
    data.data = divData.html();

    $mf.my_dialog.appendDialog(data, true, true);
}

function comprobarIdentificadorDocumentoExiste(crear, mensaje1) {
    $('#documentVeil').removeAttr('style').removeClass('hidden');

    var identificadorDocumento = $("#generarDocumento #identificador").val();
    $.ajax({
        url : $("#context").val() + '/comprobarIdentificadorDocumento',
        type : 'POST',
        dataType : 'json',
        timeout : 999999,
        data : {
            "identificadorDocumento" : identificadorDocumento,

        },
        success : function(data) {
            if (data.mensajeIdentificador != null) {
                mostrarDialogIdentificadorDocumento();
            } else {
                if (data.mensajeUsuario != null && data.mensajeUsuario.level == "4") {
                    $("#tipoMensaje").val(data.mensajeUsuario.level);
                    $("#valorMensaje").val(data.mensajeUsuario.message);
                    showMessage();

                    $('#documentVeil').addClass('hidden');
                } else {
                    if (crear)
                        saveDocument();
                    else
                        mostrarDialogImportarDocumento(mensaje1, true);
                }
            }
        },
        error : function(xhr) {
            $('#documentVeil').addClass('hidden');
            console.error(xhr.responseText);
        }
    });
}

function mostrarDialogIdentificadorDocumentoGenerarDocInside() {
    $('#documentVeil').removeAttr('style').removeClass('hidden');
    var divData = $('#init-modal-avisoIdentificador-documento');
    var data = divData.data();
    var buttons = createButtonsDialog([ {
        clase : 'js-documento-cancelar',
        texto : 'Cerrar'
    } ]);

    buttons.find('.js-documento-cancelar').on('click', function(e) {
        e.preventDefault();
        $mf.my_dialog.close_dialog();
    });

    data.buttons = buttons;
    data.data = divData.html();

    $mf.my_dialog.appendDialog(data, true, true);
}

function comprobarIdentificadorDocumentoExisteGenerarDocInside() {
    $('#documentVeil').removeAttr('style').removeClass('hidden');
    $("#actionButtons").hide();

    var identificadorDocumento = $("#generarDocumento #identificador").val();
    $.ajax({
        url : $("#context").val() + '/comprobarIdentificadorDocumento',
        type : 'POST',
        dataType : 'json',
        timeout : 999999,
        data : {
            "identificadorDocumento" : identificadorDocumento,

        },
        success : function(data) {
            if (data.mensajeIdentificador != null) {
                mostrarDialogIdentificadorDocumentoGenerarDocInside();
            } else {
                $('#documentVeil').addClass('hidden');

            }

        },
        error : function(xhr) {
            $('#documentVeil').addClass('hidden');
            console.error(xhr.responseText);
        }
    });
}

function saveDocument() {
    createDocument('/saveDocument');
}

function updateDocument() {
    createDocument('/updateDocument');
}

function importarDocumento(mensaje, nuevo) {
    $('.mf-dialog #importVeil').removeAttr('style').removeClass('hidden');
    var close = false;
    var idExpediente = $(".mf-dialog #doc_identificador_expediente").val();
    if (idExpediente) {
        ocultarMensaje();

        if ($("input[name=firmaExp][value='client']").is(':checked')) {
            chooseImportType(idExpediente, nuevo, false);
            close = true;
        } else if ($("input[name=firmaExp][value='server']").is(':checked')) {
            chooseImportType(idExpediente, nuevo, true);
            close = true;
        } else {
            $('.mf-dialog #importVeil').addClass('hidden');
            alert('Debe seleccionar un tipo de firma para el expediente. Si no tiene habilitadas opciones contacte con el administrador.');
        }
    } else {
        alert(mensaje);
    }
    return close;
}

function chooseImportType(idExpediente, nuevo, firmaServidor) {
    if (nuevo)
        sendImportar('/importNewDocument', idExpediente, firmaServidor);
    else
        sendImportar('/importDocument', idExpediente, firmaServidor);

}

function sendImportar(operacion, idExpediente, firmaServidor) {
    $('#documentVeil').removeAttr('style').removeClass('hidden');
    $.ajax({
        url : $("#context").val() + operacion,
        type : 'POST',
        dataType : 'json',
        data : {
            "identificadorExpediente" : JSON.stringify(idExpediente),
            "documentId" : JSON.stringify($("#generarDocumento #identificador").val() + ".xml"),
            "firmaServidor" : firmaServidor
        },
        success : function(data) {
            if (data.expedienteEni != null && !firmaServidor) {
                callAFirma(idExpediente, data.expedienteEni);
            } else {
                if (data.mensajeUsuario != null && data.errorGuardar != null) {
                    $("#tipoMensaje").val(data.mensajeUsuario.level);
                    $("#valorMensaje").val(data.mensajeUsuario.message);
                    showMessage();
                } else {
                    redireccionaDocumentosAlmacenados(data.mensajeUsuario);
                }
            }
        },
        error : function(xhr) {
            console.error(xhr.responseText);
            $('.mf-dialog #importVeil').addClass('hidden');
        }
    });

}

function signCorrectImport(signatureB64) {
    var idExpediente = sessionStorage.getItem('idExpediente');
    var expedient = sessionStorage.getItem('expedient');
    eval("updateIndexExp(idExpediente, expedient, signatureB64);");
    $('.mf-dialog #importVeil').addClass('hidden');
}

function signErrorImport(errorType, errorMsg) {
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
    $('.mf-dialog #importVeil').addClass('hidden');
    $("#tipoMensaje").val(4);
    $("#valorMensaje").val("Error en el proceso de firma: " + error);
    showMessage();
}

function callAFirma(idExpediente, expedient) {
    var id_indiceContenido = "EXP_INDICE_CONTENIDO" + idExpediente;
    var dataB64 = btoa(expedient);
    var format = "XAdES";
    var algorithm = "SHA1withRSA";
    var params = "format=XAdES Enveloped\nnodeToSign=" + id_indiceContenido;
    sessionStorage.setItem('idExpediente', idExpediente);
    sessionStorage.setItem('expedient', expedient);
    MiniApplet.sign(dataB64, algorithm, format, params, signCorrectImport, signErrorImport);
}

function redireccionaDocumentosAlmacenados(mensajeUsuario) {
    $("#textoMensajeUsuario").val(mensajeUsuario.message);
    $("#nivelMensajeUsuario").val(mensajeUsuario.level);
    $("#redireccionarDocumentosAlmacenadosForm").submit();
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
            if (data.mensajeUsuario != null && data.errorGuardar != null) {
                $("#tipoMensaje").val(data.mensajeUsuario.level);
                $("#valorMensaje").val(data.mensajeUsuario.message);
                showMessage();
            } else {
                redireccionaDocumentosAlmacenados(data.mensajeUsuario);
            }
        },
        error : function(xhr) {
            console.error(xhr.responseText);
            $('#importarVeil').addClass('hidden');
        }
    });
}