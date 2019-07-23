var dataFileExp;
var metadatosDocAdded;
var uploaderDoc;

$(document).ready(function() {

    MiniApplet.cargarMiniApplet(baseDownloadURL);
    crearEspaciosDragDrop();

    // Autocompletado del campo organo.
    if ($('#organos') != null && $('#organos').length > 0) {
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
    }

    // Se pregunta porque en verexpediente.html no existe este elemento: keyMetadatoAdicional
    if (document.getElementById("keyMetadatoAdicional")) {
        // Autocompletado del campo expedientes.
        $("#keyMetadatoAdicional").autocomplete({
            source : $("#context").val() + '/autocomplete/metadatos',
            minLength : 0,
            autoFocus : true,
            select : function(event, ui) {
                $("#keyMetadatoAdicional").val(ui.item.key);
                var elemt = ui.item.key;
                if (elemt.indexOf('Fecha') > -1) {
                    $("#valueMetadatoAdicional").val($("#metadatoAdicionalFechaFinDefecto").val());
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

        $("#js-gallery-createDocument #keyMetadatoAdicional").autocomplete({
            source : $("#context").val() + '/autocomplete/metadatos',
            minLength : 0,
            autoFocus : true,
            select : function(event, ui) {
                $("#js-gallery-createDocument #keyMetadatoAdicional").val(ui.item.key);
                var elemt = ui.item.key;
                if (elemt.indexOf('Fecha') > -1) {
                    $("#js-gallery-createDocument #valueMetadatoAdicional").val('2016-01-01T00:00:00');
                } else {
                    $("#js-gallery-createDocument #valueMetadatoAdicional").val('');
                }
                event.preventDefault();
            }
        }).bind('focus', function() {
            $(this).autocomplete("search");
        }).data("ui-autocomplete")._renderItem = function(ul, item) {
            return $("<li>").append("<a>" + item.value + "</a>").appendTo(ul);
        };

    }

    // Se pregunta porque en verexpediente.html no existe este elemento: button_descargarExpAdicionales
    if (document.getElementById("button_descargarExpAdicionales")) {

        $("#button_descargarExpAdicionales").hide();

        if ($("#expedienteForm #conAdicionales").val() === 'true') {
            $("#button_descargarExpAdicionales").show();
        }
    }

    $("#fieldsetDatosEnvio").hide();

    $("#init-modal-consultaRemisionMJU").addClass('hidden');

    tableRemisiones = $('#remisionList').dataTable({
        "language" : {
            "sProcessing" : "Procesando...",
            "sLengthMenu" : "Mostrar _MENU_",
            "sZeroRecords" : "No se encontraron resultados",
            "sEmptyTable" : "Ningún dato disponible en esta tabla",
            "sInfo" : "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
            "sInfoEmpty" : "Mostrando registros del 0 al 0 de un total de 0 registros",
            "sInfoFiltered" : "(filtrado de un total de _MAX_ registros)",
            "sInfoPostFix" : "",
            "sSearch" : "Buscar",
            "sUrl" : "",
            "sInfoThousands" : ",",
            "sLoadingRecords" : "Cargando...",
            "oPaginate" : {
                "sFirst" : "Primero",
                "sLast" : "Último",
                "sNext" : "Siguiente",
                "sPrevious" : "Anterior"
            },
            "oAria" : {
                "sSortAscending" : ": Activar para ordenar la columna de manera ascendente",
                "sSortDescending" : ": Activar para ordenar la columna de manera descendente"
            }
        },
        "lengthMenu" : [ [ 10, 25, 50, -1 ], [ 10, 25, 50, "Todo" ] ],
        "pageLength" : 50,
        "dom" : '<"top">t<"bottom" ir><"clear">',
        "bSort" : true,
        "aoColumns" : [
        //				{
        //					"bSortable" : false,
        //					"width" : "10%"
        //				}, 
        //				{
        //					"bSortable" : false,
        //					"width" : "20%"
        //				}, 
        //				{
        //					"bSortable" : false,
        //					"width" : "10%"
        //				}, 
        {
            "bSortable" : false,
            "width" : "10%"
        }, {
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

function mostrarDialogIdentificadorExpediente(expediente, mensajeAviso) {
    $('#expedienteVeil').removeAttr('style').removeClass('hidden');
    var divData = $('#init-modal-avisoIdentificador-expediente');
    var data = divData.data();
    var buttons = createButtonsDialog([ {
        clase : 'js-expediente-cancelar',
        texto : 'Cerrar'
    } ]);

    buttons.find('.js-expediente-cancelar').on('click', function(e) {
        e.preventDefault();
        $mf.my_dialog.close_dialog();
    });

    data.buttons = buttons;
    data.data = divData.html();

    $mf.my_dialog.appendDialog(data, true, true);
}

function comprobarIdentificadorExpedienteExiste(mensajeAviso) {
    $('#expedienteVeil').removeAttr('style').removeClass('hidden');

    var identificadorExpediente = $("#expedienteForm #identificador").val();
    $.ajax({
        url : $("#context").val() + '/comprobarIdentificadorExpediente',
        type : 'POST',
        dataType : 'json',
        timeout : 999999,
        data : {
            "identificadorExpediente" : identificadorExpediente,
            "mensajeAviso" : mensajeAviso
        },
        success : function(data) {
            if (data.mensajeIdentificador != null) {
                mostrarDialogIdentificadorExpediente(identificadorExpediente, mensajeAviso);
            } else {
                if (data.mensajeUsuario != null && data.mensajeUsuario.level == "4") {
                    $("#tipoMensaje").val(data.mensajeUsuario.level);
                    $("#valorMensaje").val(data.mensajeUsuario.message);
                    showMessage();

                    $('#expedienteVeil').addClass('hidden');
                } else {
                    saveExpedient(mensajeAviso);
                }
            }
        },
        error : function(xhr) {
            $('#expedienteVeil').addClass('hidden');
            console.error(xhr.responseText);
        }
    });
}

function mostrarDialogIdentificadorExpedienteExisteDesdeGenerarExpInside(expediente, mensajeAviso) {
    $('#expedienteVeil').removeAttr('style').removeClass('hidden');
    var divData = $('#init-modal-avisoIdentificador-expediente');
    var data = divData.data();
    var buttons = createButtonsDialog([ {
        clase : 'js-expediente-cancelar',
        texto : 'Cerrar'
    } ]);

    buttons.find('.js-expediente-cancelar').on('click', function(e) {
        e.preventDefault();
        $mf.my_dialog.close_dialog();
    });

    data.buttons = buttons;
    data.data = divData.html();

    $mf.my_dialog.appendDialog(data, true, true);
}

function comprobarIdentificadorExpedienteExisteDesdeGenerarExpInside(mensajeAviso) {
    $('#expedienteVeil').removeAttr('style').removeClass('hidden');

    var identificadorExpediente = $("#expedienteForm #identificador").val();
    $.ajax({
        url : $("#context").val() + '/comprobarIdentificadorExpediente',
        type : 'POST',
        dataType : 'json',
        timeout : 999999,
        data : {
            "identificadorExpediente" : identificadorExpediente,
            "mensajeAviso" : mensajeAviso
        },
        success : function(data) {
            if (data.mensajeIdentificador != null) {
                mostrarDialogIdentificadorExpedienteExisteDesdeGenerarExpInside(identificadorExpediente, mensajeAviso);
            } else {
                $('#expedienteVeil').addClass('hidden');

            }

        },
        error : function(xhr) {
            $('#expedienteVeil').addClass('hidden');
            console.error(xhr.responseText);
        }
    });
}

function generarExpediente() {
    var operation = '/generarExpediente';
    generateExpedient(operation);
}

function generateExpedient(operation) {

    ocultarMensaje();
    habilitarMetadatosAdicionales();
    habilitarInput("organo", "expediente.metadatosExp.");
    habilitarInput("interesado", "expediente.metadatosExp.");

    $("#actionButtons").hide();
    try {
        var dataB64 = getIndexContent();
        if ($("input[name=firmaExp][value='client']").is(':checked')) {
            authenticate(dataB64, 'fillExpedient', operation);
        } else if ($("input[name=firmaExp][value='server']").is(':checked')) {
            if (dataB64 != "") {
                fillExpedient(dataB64, '', operation);
            } else {
                $('#expedienteVeil').addClass('hidden');
            }
        } else {
            alert('Debe seleccionar un tipo de firma para el expediente. Si no tiene habilitadas opciones contacte con el administrador.');
            $('#expedienteVeil').addClass('hidden');
        }
    } catch (err) {
        $("#tipoMensaje").val(4);
        $("#valorMensaje")
                .val(
                        'Incongruencia de datos. Se modifico el identificador del expediente después de generar el indice y no coinciden sus identificadores, para solucionarlo realice una modificacion en el indice.');
        showMessage();
        $('#expedienteVeil').addClass('hidden');
    }
}

function authenticate(dataB64, callback_method, operation) {
    if (dataB64 != "") {
        end_authentication(callback_method, dataB64, operation);
    } else {
        alert('El indice de un expediente no puede estar vacio.');
        $('#expedienteVeil').addClass('hidden');
    }
}

function signCorrect(signatureB64) {
    var callback_method = sessionStorage.getItem('callback_method');
    var operation = sessionStorage.getItem('operation');
    var error = '';
    eval(callback_method + "(signatureB64,error,operation);");
}

function signError(errorType, errorMsg) {
    if (MiniApplet.errorType == 'es.gob.afirma.core.AOCancelledOperationException') {
        console.log("operationCancelled");
        error = "operationCancelled";
    } else if (MiniApplet.errorType == 'es.gob.afirma.keystores.common.AOCertificatesNotFoundException') {
        console.log("No se ha encontrado certificado en el almacen");
        error = "No se ha encontrado certificado en el almacen";
    } else {
        console.log(errorMsg);
        error = "No se pudo realizar la firma, inténtelo más tarde o contacte con el administrador";
    }
}

function end_authentication(callback_method, dataB64, operation) {
    var id_indiceContenido = "EXP_INDICE_CONTENIDO" + $("#expedienteForm #identificador").val();
    var format = "XAdES";
    var algorithm = "SHA1withRSA";
    var params = "format=XAdES Enveloped\nnodeToSign=" + id_indiceContenido;
    sessionStorage.setItem('callback_method', callback_method);
    sessionStorage.setItem('operation', operation);
    MiniApplet.sign(dataB64, algorithm, format, params, signCorrect, signError);
}

function fillExpedient(signature, error, operation) {
    $("#expedienteBytes").val(signature);
    $("#error").val(error);
    $
            .ajax({
                url : $("#context").val() + operation,
                type : 'POST',
                dataType : 'json',
                timeout : 999999,
                data : $("#expedienteForm").serialize(),
                success : function(data) {
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
                            redireccionaExpedientesAlmacenados(data.mensajeUsuario);
                        }
                        if (operation === '/generarExpediente') {
                            $("#descargarExpedienteEniForm #contenido").val(data.expedienteEniConvert);
                            $("#descargarExpedienteEniForm #identificador").val(
                                    $("#expedienteForm #identificador").val());

                            $("#descargarExpedienteEniAdicionalesForm #contenido").val(
                                    data.expedienteEniConvertconAdicionales);
                            $("#descargarExpedienteEniAdicionalesForm #identificador").val(
                                    $("#expedienteForm #identificador").val());

                            if (data.conAdicionales) {
                                $("#button_descargarExpAdicionales").show();
                                dataFileExp = data.expedienteEniConvertconAdicionales;
                                $("#descargarExpedienteEniCompletoForm #contenido").val(
                                        data.expedienteEniConvertconAdicionales);
                            } else {
                                $("#button_descargarExpAdicionales").hide();
                                dataFileExp = data.expedienteEniConvert;
                                $("#descargarExpedienteEniCompletoForm #contenido").val(data.expedienteEniConvert);
                            }

                            $("#descargarExpedienteEniCompletoForm #identificador").val(
                                    $("#expedienteForm #identificador").val());

                            $("#verContenidoForm #identificador").val($("#expedienteForm #identificador").val());
                            $("#verContenidoForm #visualizar").val(data.visualizar);

                            $("#actionButtons").show();
                        } else {

                        }
                    }
                    $('#expedienteVeil').addClass('hidden');
                },
                error : function(e) {
                    console.error(e);
                    $('#expedienteVeil').addClass('hidden');
                }
            });
}

function habilitarMetadatosAdicionales() {
    $("input[id^='metadatosAdicionales.metadatoAdicional']").removeAttr('disabled');
}

function habilitarInput(input, prefix, postfix) {
    var cont = 0;
    var objeto = null;
    if (postfix != undefined) {
        objeto = document.getElementById(prefix + input + cont + postfix);
    } else {
        objeto = document.getElementById(prefix + input + cont);
    }
    while (objeto != null) {
        objeto.disabled = false;
        cont++;
        if (postfix != undefined) {
            objeto = document.getElementById(prefix + input + cont + postfix);
        } else {
            objeto = document.getElementById(prefix + input + cont);
        }
    }
}

function descargarExpedienteCompleto() {
    $("#descargarExpedienteEniCompletoForm").submit();
}

function descargarExpedienteCompletoFisico() {
    $("#descargarExpedienteEniCompletoFisicoForm").submit();
}

function descargarExpediente() {
    $("#descargarExpedienteEniForm").submit();
}

//function descargarExpedienteValidarFirma() {
//	$("#descargarExpedienteEniValidarFirmaForm").submit();
//}

function remitirMJUDesdeEditarExpediente() {
    $('#expedienteVeil').removeAttr('style').removeClass('hidden');
    $("#remitirMJUExpedienteEniForm").submit();
}

function verContenido() {
    $("#verContenidoForm").submit();
}

function cargarVistasExpediente() {
    $("#cargarVistasExpedienteForm").submit();
}

function volverVistasAsociadas() {
    $("#volverVistasAsociadasForm").submit();
}

function backStored() {
    $("#backStoredExpForm").submit();
}

function cancelExpedient() {
    $("#cancelExpForm").submit();
}

function addInteresadoExp() {
    if ($('#interesados').val().trim() != "") {
        if (interesadosAdded == '') {
            interesadosAdded = 0;
        }

        var key_input = document.createElement("input");
        var remove_button = document.createElement("button");
        var div = document.createElement("div");

        key_input.setAttribute("type", "text");
        key_input.setAttribute("id", "expediente.metadatosExp.interesado" + interesadosAdded);
        key_input.setAttribute("name", "expediente.metadatosExp.interesado[" + interesadosAdded + "]");
        key_input.setAttribute("class", "sticked-input-right");
        key_input.setAttribute("disabled", "disabled");
        key_input.setAttribute("value", $('#interesados').val());

        remove_button.setAttribute("type", "button");
        remove_button.setAttribute("name", "button");
        remove_button.setAttribute("class", "mf-icon mf-icon-cancel2");
        remove_button.setAttribute("id", "removeInteresadoButton_" + interesadosAdded);
        remove_button.setAttribute("value", "Eliminar");
        remove_button.setAttribute("onclick", "removeInteresadoExp(" + interesadosAdded + ")");
        div.setAttribute("id", "divInteresado_" + interesadosAdded);

        $("#listInteresados").append(div);

        $("#divInteresado_" + interesadosAdded).append(key_input);
        $("#divInteresado_" + interesadosAdded).append(remove_button);

        $('#interesados').val('');
        interesadosAdded++;
    }
}

function removeInteresadoExp(position) {
    $(document.getElementById("expediente.metadatosExp.interesado" + position)).remove();
    $("#removeInteresadoButton_" + position).remove();
    $("#divInteresado_" + position).remove();
    while (interesadosAdded - 1 > position) {
        var aux = position + 1;
        document.getElementById("expediente.metadatosExp.interesado" + aux).setAttribute("name",
                "expediente.metadatosExp.interesado[" + position + "]");
        document.getElementById("expediente.metadatosExp.interesado" + aux).setAttribute("id",
                "expediente.metadatosExp.interesado" + position);
        $("#removeInteresadoButton_" + aux).attr("onclick", "removeInteresadoExp(" + position + ")");
        $("#removeInteresadoButton_" + aux).attr("id", "removeInteresadoButton_" + position);
        $("#divInteresado_" + aux).attr("id", "divInteresado_" + position);
        position++;
    }
    interesadosAdded--;
}

function addOrganoExp() {
    if ($('#organos').val().trim() != "") {
        if (organosAdded == '') {
            organosAdded = 0;
        }

        var key_input = document.createElement("input");
        var remove_button = document.createElement("button");
        var div = document.createElement("div");

        key_input.setAttribute("type", "text");
        key_input.setAttribute("id", "expediente.metadatosExp.organo" + organosAdded);
        key_input.setAttribute("name", "expediente.metadatosExp.organo[" + organosAdded + "]");
        key_input.setAttribute("class", "sticked-input-right");
        key_input.setAttribute("disabled", "disabled");
        key_input.setAttribute("value", $('#organos').val());

        remove_button.setAttribute("type", "button");
        remove_button.setAttribute("name", "button");
        remove_button.setAttribute("class", "mf-icon mf-icon-cancel2");
        remove_button.setAttribute("id", "removeOrganoButton_" + organosAdded);
        remove_button.setAttribute("value", "Eliminar");
        remove_button.setAttribute("onclick", "removeOrganoExp(" + organosAdded + ")");
        div.setAttribute("id", "divOrgano_" + organosAdded);

        $("#listOrganos").append(div);

        $("#divOrgano_" + organosAdded).append(key_input);
        $("#divOrgano_" + organosAdded).append(remove_button);

        $('#organos').val('');
        organosAdded++;
    }
}

function removeOrganoExp(position) {
    $(document.getElementById("expediente.metadatosExp.organo" + position)).remove();
    $("#removeOrganoButton_" + position).remove();
    $("#divOrgano_" + position).remove();
    while (organosAdded - 1 > position) {
        var aux = position + 1;
        document.getElementById("expediente.metadatosExp.organo" + aux).setAttribute("name",
                "expediente.metadatosExp.organo[" + position + "]");
        document.getElementById("expediente.metadatosExp.organo" + aux).setAttribute("id",
                "expediente.metadatosExp.organo" + position);
        $("#removeOrganoButton_" + aux).attr("onclick", "removeOrganoExp(" + position + ")");
        $("#removeOrganoButton_" + aux).attr("id", "removeOrganoButton_" + position);
        $("#divOrgano_" + aux).attr("id", "divOrgano_" + position);
        position++;
    }
    organosAdded--;
}

function getIndexContent() {
    var dataB64 = $("#contenidoExp").val();
    var id_indiceContenido = "EXP_INDICE_CONTENIDO" + $("#expedienteForm #identificador").val();
    console.log('dataB64: ' + dataB64);
    console.log('id_indiceContenido: ' + id_indiceContenido);
    if (dataB64.indexOf(id_indiceContenido) == -1)
        throw new Error("Error al comprobar el id expediente y el id indice contenido");
    if (dataB64 == "") {
        dataB64 = $("#expedienteForm #identificador").val();
    }
    dataB64 = btoa(dataB64);
    return dataB64;
}

function saveExpedient(mensaje) {
    generateExpedient('/saveExpedient');
}

function updateExpedient(mensaje) {
    generateExpedient('/updateExpedient');
}

function redireccionaExpedientesAlmacenados(mensajeUsuario) {
    $("#textoMensajeUsuario").val(mensajeUsuario.message);
    $("#nivelMensajeUsuario").val(mensajeUsuario.level);
    $("#redireccionaExpedientesAlmacenadosForm").submit();
}

function obtenerIdDocumentoPorDefecto() {
    $.ajax({
        url : $("#context").val() + '/obtenerIdDocumentoPorDefecto',
        type : 'POST',
        dataType : 'json',
        timeout : 999999,
        success : function(data) {
            $('#js-gallery-createDocument #identificador').val(data.identificadorDocumento);
        },
        error : function(xhr) {
            $('#js-gallery-createDocument #identificador').val('');
        }
    });
}

// Modal crear Documento
function showDialogCreateDocument(e) {
    e.preventDefault();
    if (elementoSeleccionadoNoEsDocumento('crear documento')) {
        // eliminacion datos previos
        obtenerIdDocumentoPorDefecto();
        $('#js-gallery-createDocument #identificadorDocOrigen').val('');
        $('#js-gallery-createDocument #identificadorDocOrigen').attr('disabled', 'disabled');
        $('#js-gallery-createDocument #origen').prop('selectedIndex', 0);
        $('#js-gallery-createDocument #estadoElaboracion').prop('selectedIndex', 0);
        $('#js-gallery-createDocument #fechaCaptura').val($("#fecCapHidden").val());
        $('#js-gallery-createDocument #tipoDocumental').prop('selectedIndex', 0);
        $('#js-gallery-createDocument #organos').val('');
        var position = 0;
        var objeto = document.getElementById("metadatosEni.organo" + position);
        while (objeto != null) {
            //document.getElementById("metadatosEni.organo" + position).remove();
            $("#js-gallery-createDocument #metadatosEni.organo" + position).remove();
            $("#js-gallery-createDocument #removeOrganoButton_" + position).remove();
            $("#js-gallery-createDocument #divOrgano_" + position).remove();
            position++;
            objeto = document.getElementById("metadatosEni.organo" + position);
        }
        $('#js-gallery-createDocument #firma').prop('selectedIndex', 0);

        for (var iCnt = 0; iCnt < metadatosDocAdded; iCnt++) {
            removeMetadatoDoc(iCnt);
        }
        $('#js-gallery-createDocument #js-div-metadatos').addClass('hidden');
        $('#js-gallery-createDocument #valueMetadatoAdicionalNatural').val('');
        $('#js-gallery-createDocument #csv').val('');
        $('#js-gallery-createDocument #regulacionCSV').val('');
        for (var iCnt = 0; iCnt < uploaderDoc.files.length; iCnt++) {
            uploaderDoc.removeFile(iCnt);
        }
        $('#js-gallery-createDocument #documento_text').html('No se ha seleccionado ningún archivo.');
        $('#js-gallery-createDocument #documentId').val('');

        metadatosDocAdded = 0;
        organosAddedDoc = 0;

        // Autocompletado del campo organo.
        $('#js-gallery-createDocument #organos').autocomplete({
            source : $("#context").val() + '/autocomplete/dir3Vigentes',
            // autoFocus: true,
            focus : function(event, ui) {
                $('#js-gallery-createDocument #organos').val(ui.item.key + " - " + ui.item.value);
                event.preventDefault();
            },
            select : function(event, ui) {
                $('#js-gallery-createDocument #organos').val(ui.item.key + " - " + ui.item.value);
                event.preventDefault();
            }
        }).data("ui-autocomplete")._renderItem = function(ul, item) {
            return $("<li>").append("<a>" + item.key + " - " + item.value + "</a>").appendTo(ul);
        };

        var visor = $("#js-gallery-createDocument");
        var panel = visor.siblings('#js-gallery-visor');

        visor.removeClass('hidden')
        // .data('id_mostrado',item.attr('id'))
        .width(panel.width()).height(panel.height()).offset(panel.offset());

        checkEstado();
        checkFirma();
    }
}

function checkEstado() {
    if ($('#js-gallery-createDocument #estadoElaboracion').val() == "EE_02"
            || $('#js-gallery-createDocument #estadoElaboracion').val() == "EE_04") {
        $('#js-gallery-createDocument #identificadorDocOrigen').removeAttr("disabled");
    } else {
        $('#js-gallery-createDocument #identificadorDocOrigen').val('');
        $('#js-gallery-createDocument #identificadorDocOrigen').attr('disabled', 'disabled');
    }
}

function checkFirma() {
    if ($("#js-gallery-createDocument [name=firma][value='firma']").is(':checked')) {
        $('#js-gallery-createDocument #certificadoFirma').show();
    } else {
        $('#js-gallery-createDocument #certificadoFirma').hide();
    }
}

function addOrganoDoc() {
    if ($('#js-gallery-createDocument #organos').val().trim() != "") {
        if (organosAddedDoc == '') {
            organosAddedDoc = 0;
        }

        var key_input = document.createElement("input");
        var remove_button = document.createElement("button");
        var div = document.createElement("div");

        key_input.setAttribute("type", "text");
        key_input.setAttribute("name", "metadatosEni.organo[" + organosAddedDoc + "]");
        key_input.setAttribute("id", "metadatosEni.organo" + organosAddedDoc);
        key_input.setAttribute("class", "sticked-input-right");
        key_input.setAttribute("disabled", "disabled");
        key_input.setAttribute("value", $('#js-gallery-createDocument #organos').val());

        remove_button.setAttribute("type", "button");
        remove_button.setAttribute("name", "button");
        remove_button.setAttribute("class", "mf-icon mf-icon-cancel2");
        remove_button.setAttribute("id", "removeOrganoButton_" + organosAddedDoc);
        remove_button.setAttribute("value", "Eliminar");
        remove_button.setAttribute("onclick", "removeOrganoDoc(" + organosAddedDoc + ")");
        div.setAttribute("id", "divOrgano_" + organosAddedDoc);

        $('#js-gallery-createDocument #listOrganos').append(div);

        $('#js-gallery-createDocument #divOrgano_' + organosAddedDoc).append(key_input);
        $('#js-gallery-createDocument #divOrgano_' + organosAddedDoc).append(remove_button);

        $('#js-gallery-createDocument #organos').val('');
        organosAddedDoc++;
    }
}

function removeOrganoDoc(position) {
    document.getElementById("metadatosEni.organo" + position).remove();
    $("#js-gallery-createDocument #removeOrganoButton_" + position).remove();
    $("#js-gallery-createDocument #divOrgano_" + position).remove();
    while (organosAddedDoc - 1 > position) {
        var aux = position + 1;
        document.getElementById("metadatosEni.organo" + aux).setAttribute("name",
                "metadatosEni.organo[" + position + "]");
        document.getElementById("metadatosEni.organo" + aux).setAttribute("id", "metadatosEni.organo" + position);
        $("#js-gallery-createDocument #removeOrganoButton_" + aux).attr("onclick", "removeOrganoDoc(" + position + ")");
        $("#js-gallery-createDocument #removeOrganoButton_" + aux).attr("id", "removeOrganoButton_" + position);
        $("#js-gallery-createDocument #divOrgano_" + aux).attr("id", "divOrgano_" + position);
        position++;
    }
    organosAddedDoc--;
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
                    generarDocumento();
                }
            }
        },
        error : function(xhr) {
            $('#documentVeil').addClass('hidden');
            console.error(xhr.responseText);
        }
    });
}

function closeDialogDocument() {
    $("#js-gallery-createDocument").addClass('hidden');
}

function addMetadatoDoc() {
    if (metadatosDocAdded == '') {
        metadatosDocAdded = 0;
    }

    var key_input = document.createElement("input");
    var value_input = document.createElement("input");
    var remove_button = document.createElement("button");
    var div = document.createElement("div");

    key_input.setAttribute("type", "text");
    key_input.setAttribute("id", "metadatosAdicionalesDoc.metadatoAdicional" + metadatosDocAdded + ".nombre");
    key_input.setAttribute("name", "metadatosAdicionalesDoc.metadatoAdicional.nombre");
    key_input.setAttribute("class", "sticked-input-right");
    key_input.setAttribute("disabled", "disabled");
    key_input.setAttribute("value", $('#js-gallery-createDocument #keyMetadatoAdicional').val());
    key_input.setAttribute("style", "width:250px");
    value_input.setAttribute("type", "text");
    value_input.setAttribute("name", "metadatosAdicionalesDoc.metadatoAdicional.valor");
    value_input.setAttribute("id", "metadatosAdicionalesDoc.metadatoAdicional" + metadatosDocAdded + ".valor");
    value_input.setAttribute("class", "sticked-input-right");
    value_input.setAttribute("disabled", "disabled");
    value_input.setAttribute("value", $('#js-gallery-createDocument #valueMetadatoAdicional').val());
    value_input.setAttribute("style", "width:200px");
    remove_button.setAttribute("type", "button");
    remove_button.setAttribute("name", "button");
    remove_button.setAttribute("class", "mf-icon mf-icon-cancel2");
    remove_button.setAttribute("id", "removeMetadatoButton_" + metadatosDocAdded);
    remove_button.setAttribute("value", "Eliminar");
    remove_button.setAttribute("onclick", "removeMetadatoDoc(" + metadatosDocAdded + ")");
    div.setAttribute("id", "divMetadatoAdicionalDoc_" + metadatosDocAdded);

    $('#js-gallery-createDocument #listMetadatosAdicionalesDoc').append(div);

    var divNombre = document.createElement("div");
    divNombre.setAttribute("id", "divNombre_" + metadatosDocAdded);
    divNombre.setAttribute("class", "fld");
    var divValor = document.createElement("div");
    divValor.setAttribute("id", "divValor_" + metadatosDocAdded);
    divValor.setAttribute("class", "fld");
    var divButton = document.createElement("div");
    divButton.setAttribute("id", "divButton_" + metadatosDocAdded);
    divButton.setAttribute("class", "fld");

    $("#js-gallery-createDocument #divMetadatoAdicionalDoc_" + metadatosDocAdded).append(divNombre);
    $("#js-gallery-createDocument #divMetadatoAdicionalDoc_" + metadatosDocAdded).append(divValor);
    $("#js-gallery-createDocument #divMetadatoAdicionalDoc_" + metadatosDocAdded).append(divButton);

    $("#js-gallery-createDocument #divNombre_" + metadatosDocAdded).append(key_input);
    $("#js-gallery-createDocument #divValor_" + metadatosDocAdded).append(value_input);
    $("#js-gallery-createDocument #divButton_" + metadatosDocAdded).append(remove_button);

    $('#js-gallery-createDocument #keyMetadatoAdicional').val('');
    $('#js-gallery-createDocument #valueMetadatoAdicional').val('');
    metadatosDocAdded++;
}

function removeMetadatoDoc(position) {
    $("#js-gallery-createDocument input[id='metadatosAdicionalesDoc.metadatoAdicional" + position + ".nombre']")
            .remove();
    $("#js-gallery-createDocument input[id='metadatosAdicionalesDoc.metadatoAdicional" + position + ".valor']")
            .remove();
    $("#js-gallery-createDocument #removeMetadatoButton_" + position).remove();
    $("#js-gallery-createDocument #divMetadatoAdicionalDoc_" + position).remove();
    while (metadatosDocAdded - 1 > position) {
        var aux = position + 1;
        $("#js-gallery-createDocument input[id='metadatosAdicionalesDoc.metadatoAdicional" + aux + ".nombre']").attr(
                "name", "metadatosAdicionalesDoc.metadatoAdicional[" + position + "].nombre");
        $("#js-gallery-createDocument input[id='metadatosAdicionalesDoc.metadatoAdicional" + aux + ".nombre']").attr(
                "id", "metadatosAdicionalesDoc.metadatoAdicional" + position + ".nombre");
        $("#js-gallery-createDocument input[id='metadatosAdicionalesDoc.metadatoAdicional" + aux + ".valor']").attr(
                "name", "metadatosAdicionalesDoc.metadatoAdicional[" + position + "].valor");
        $("#js-gallery-createDocument input[id='metadatosAdicionalesDoc.metadatoAdicional" + aux + ".valor']").attr(
                "id", "metadatosAdicionalesDoc.metadatoAdicional" + position + ".valor");
        $("#js-gallery-createDocument #removeMetadatoButton_" + aux).attr("onclick",
                "removeMetadatoDoc(" + position + ")");
        $("#js-gallery-createDocument #removeMetadatoButton_" + aux).attr("id", "removeMetadatoDoc_" + position);
        $("#js-gallery-createDocument #divMetadatoAdicionalDoc_" + aux).attr("id",
                "divMetadatoAdicionalDoc_" + position);
        position++;
    }
    metadatosDocAdded--;
}

function generarDocumento() {
    $('#js-gallery-createDocument #documentVeil').removeAttr('style').removeClass('hidden');

    ocultarMensaje();
    $('#js-gallery-createDocument #contenidoEni').hide();

    habilitarOrganos();

    var cont = 0, i = 0;
    var advData = [];
    while (i < metadatosDocAdded) {
        if ($("#js-gallery-createDocument #divMetadatoAdicionalDoc_" + cont)[0] != null) {
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
    var nombreNat = $("#js-gallery-createDocument #keyMetadatoAdicionalNatural").val();
    var valorNat = $("#js-gallery-createDocument #valueMetadatoAdicionalNatural").val();
    if (nombreNat != null && nombreNat !== "" && valorNat != null && valorNat !== "") {
        var data = {
            nombre : nombreNat,
            valor : valorNat,
            tipo : "xsd:string"
        };
        advData.push(data);
    }

    $('#js-gallery-createDocument #metadatosAdicionalesDoc').val(JSON.stringify(advData));
    if ($("#js-gallery-createDocument input[name=firma][value='firma']").is(':checked')) {
        authenticateDoc();
    } else {
        if ($('#js-gallery-createDocument #documento') != null
                && $('#js-gallery-createDocument #documento').val() != null
                && $('#js-gallery-createDocument #documento').val() != "") {
            readFile("generarDocumento");
        } else {
            sendAjaxDocForm();
        }

    }
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

function sendAjaxDocForm() {
    $.ajax({
        url : $("#context").val() + '/generarDocumento',
        type : 'POST',
        dataType : 'json',
        timeout : 999999,
        data : $('#js-gallery-createDocument #generarDocumento').serialize(),
        success : function(data) {
            $('#js-gallery-createDocument #documentVeil').addClass('hidden');
            if (data.mensajeUsuario != null) {
                $("#tipoMensaje").val(data.mensajeUsuario.level);
                $("#valorMensaje").val(data.mensajeUsuario.message);
                showMessage();
            } else {
                function salirDoc() {
                    uploaderDoc.splice();
                    $('#js-gallery-createDocument').addClass('hidden');
                }

                var idExpediente = $("#expedienteForm #identificador").val();
                var lista = getLista();
                enviarAjax2($('#js-arbol').data('url_documento_nuevo'), {
                    path : lista.item && getPath2(lista.item),
                    idExpediente : idExpediente,
                    id : data.identificador,
                    fechaIncorpExp : getFecha(),
                    ordenDocExp : 1,
                    hidden : false
                }, function(data) {
                    crearItemLista({
                        id : data.id,
                        texto : data.id,
                        natural : data.nombreNatural,
                        lista : lista.lista,
                        isDocument : true
                    });
                    salirDoc();
                }, salirDoc);
            }
        },
        error : function(e) {
            $('#js-gallery-createDocument #documentVeil').addClass('hidden');
            console.error(e);
        }
    });
}

function authenticateDoc() {
    end_authenticationDoc('sendAjaxDocForm');
}

function calculaProtocolo() {

    var protocolo = "http";
    var protocoloSeguro = protocolo + "s";

    if (window.location.protocol == protocolo + ":") {
        return protocolo;
    } else
        return protocoloSeguro;
}

function signCorrectDoc(signatureB64) {
    var doc = btoa(unescape(encodeURIComponent(uploaderDoc.files[uploaderDoc.files.length - 1].name)));
    $('#js-gallery-createDocument #documentId').val(decodeURIComponent(escape(atob(doc))));
    $("#js-gallery-createDocument #firmaResultado").val(decodeURIComponent(escape(atob(signatureB64))));
    var callback_method = sessionStorage.getItem('callback_method');
    window[callback_method]();
}

function signErrorDoc(errorType, errorMsg) {
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

function end_authenticationDoc(callback_method) {
    if (uploaderDoc.files.length) {
        var doc = btoa(unescape(encodeURIComponent(uploaderDoc.files[uploaderDoc.files.length - 1].name)));
        var mimeType = uploaderDoc.files[uploaderDoc.files.length - 1].type;
        var format = $("#formatoFirma").val();
        var algorithm = "SHA1withRSA";
        var params = "mode=implicit\ndoc=" + doc + "\nserverUrl=" + calculaProtocolo() + "://" + $("#ip_server").val()
                + ":" + $("#port_server").val() + $("#context").val() + "/SignatureService";

        var sesionDesdeHtml = $("#sesionId").val();
        params = "SESION_POR_PARAMETRO_EXTRA=" + sesionDesdeHtml + "\n" + params;
        //		if (format == "XAdEStri Enveloped" || format == "XAdEStri Detached") {
        //			if (format == "XAdEStri Enveloped") {
        //				params = "format=XAdES Enveloped\n" + params;
        //			} else {
        //				params = "format=XAdES Enveloped\n" + params;
        //			}
        //			format = "XAdEStri";
        //		}

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
        MiniApplet.sign(doc, algorithm, format, params, signCorrectDoc, signErrorDoc);
    } else {
        $("#tipoMensaje").val(4);
        $("#valorMensaje").val("Deber seleccionar un fichero para firmar");
        showMessage();
        $('#documentVeil').addClass('hidden');
    }
}

function configurarPlUploadCreateDoc() {
    uploaderDoc = new plupload.Uploader({
        chunk_size : '10mb',
        max_file_size : "10gb",
        browse_button : 'documento_button',
        url : $("#context").val() + "/uploadTempData"
    });

    uploaderDoc.bind('UploadProgress', function(up, file) {
        $('#js-gallery-createDocument #progressDocumento').attr('value', file.percent);
    });

    // Salta con las cabeceras del la response. Xej. un 500
    uploaderDoc.bind('Error', function(up, err) {
        $("#tipoMensaje").val(4);
        $("#valorMensaje").val("Error en la subida del fichero al servidor: " + err);
        showMessage();
    });

    uploaderDoc.bind('FilesAdded', function(up, file_plupload, res) {
        setTimeout(function() {
            $('#js-gallery-createDocument #progressDocumento').attr('value', 0).removeClass('hidden');
            $('#js-gallery-createDocument #button_generarDocumento').attr('disabled', 'disabled');
            uploaderDoc.start();
        }, 1000);
    });

    uploaderDoc.bind('UploadComplete', function(up, file_plupload, res) {
        $('#js-gallery-createDocument #progressDocumento').addClass('hidden');
        $('#js-gallery-createDocument #button_generarDocumento').removeAttr('disabled');
        if (file_plupload.length) {
            $('#js-gallery-createDocument #documento_text').html(file_plupload[file_plupload.length - 1].name);
            $('#js-gallery-createDocument #documentId').val(file_plupload[file_plupload.length - 1].name);
        }
    });

    uploaderDoc.init();
}

function visualizaDescargarResguardoJusticia() {
    var dataResguardoDocBase64 = $(".mf-dialog #dataResguardoDocBase64").val();
    var codigoEnvioResguardo = $(".mf-dialog #codigoEnvioResguardo").val();
    $("#visualizaDescargarResguardoJusticiaForm #dataResguardoDocBase64").val(dataResguardoDocBase64);
    $("#visualizaDescargarResguardoJusticiaForm #codigoEnvioResguardo").val(codigoEnvioResguardo);
    $("#visualizaDescargarResguardoJusticiaForm").submit();
}

function consultaRemisionJusticia(aplicacion, modulo, servicio, marcaTiempo, codigoEnvio) {
    var divData = $('#init-modal-consultaRemisionMJU');
    var data = divData.data();
    data.data = divData.html();
    $mf.my_dialog.appendDialog(data, true, true);

    $('#expedienteVeil').removeAttr('style').removeClass('hidden');
    $(".mf-dialog #modalConsultaRemisionVeil").removeAttr('style').removeClass('hidden');

    $
            .ajax({
                url : $("#context").val() + "/justicia/consultaRemisionMJU",
                dataType : 'json',
                type : 'POST',
                data : {
                    "aplicacion" : aplicacion,
                    "modulo" : modulo,
                    "servicio" : servicio,
                    "marcaTiempo" : marcaTiempo,
                    "codigoEnvio" : codigoEnvio
                },
                success : function(data) {

                    if (data.datosDeEnvio != null) {
                        $(".mf-dialog #identificadorEstado").val(data.datosDeEnvio.identificadorEstado);
                        if (data.datosDeEnvio.identificadorEstado == "ENVIADO") {
                            $('.mf-dialog #recepcion1').addClass('hidden');
                            $('.mf-dialog #recepcion2').addClass('hidden');
                            $('.mf-dialog #envio1').removeClass('hidden');
                            $('.mf-dialog #envio2').removeClass('hidden');
                        } else {
                            $('.mf-dialog #recepcion1').removeClass('hidden');
                            $('.mf-dialog #recepcion2').removeClass('hidden');
                            $('.mf-dialog #envio1').addClass('hidden');
                            $('.mf-dialog #envio2').addClass('hidden');
                        }

                        $(".mf-dialog #descripcionEstado").val(data.datosDeEnvio.descripcionEstado);
                        $(".mf-dialog #comentario").val(data.datosDeEnvio.comentario);

                        // Si no trae comentario no se muestra el fieldSet
                        if (data.datosDeEnvio.comentario == null)
                            $('.mf-dialog #fieldsetComentario').addClass('hidden');

                        if (data.dataResguardoDocBase64 == "") {
                            $('.mf-dialog #bloqueResguardoEnvio').addClass('hidden');
                        } else {
                            $('.mf-dialog #dataResguardoDocBase64').val(data.dataResguardoDocBase64);
                            $('.mf-dialog #codigoEnvioResguardo').val(codigoEnvio);
                        }

                        $('.mf-dialog #visorResguardo').attr('src',
                                'data:application/pdf;base64,' + data.dataResguardoDocBase64);

                        //datos del envio
                        var codigoPoblacion = data.datosDeEnvio.procedimiento.codigoPoblacionProcedimiento;

                        $(".mf-dialog #provinciasModalConsulta").val(codigoPoblacion.substring(0, 2));/////////////////////////////////////////////////////
                        $(".mf-dialog #organosConsulta").val(data.datosDeEnvio.procedimiento.numeroOrganoProcedimiento);
                        $(".mf-dialog #ordenModalConsulta").val(data.datosDeEnvio.procedimiento.ordenProcedimiento);
                        $(".mf-dialog #claseProcedimientoConsulta").val(
                                data.datosDeEnvio.procedimiento.claseProcedimiento);
                        $(".mf-dialog #procedimientoanioConsulta").val(
                                data.datosDeEnvio.procedimiento.anioProcedimiento);
                        $(".mf-dialog #procedimientonumeroConsulta").val(
                                data.datosDeEnvio.procedimiento.numeroProcedimiento);

                        $(".mf-dialog #niganioAsuntoConsulta").val(data.datosDeEnvio.asunto.anioAsunto);
                        $(".mf-dialog #nigcodigoOrdenConsulta").val(data.datosDeEnvio.asunto.codigoOrdenAsunto);
                        $(".mf-dialog #nigcodigoPoblacionConsulta").val(data.datosDeEnvio.asunto.codigoPoblacionAsunto);
                        $(".mf-dialog #nignumeroAsuntoConsulta").val(data.datosDeEnvio.asunto.numeroAsunto);
                        $(".mf-dialog #nigtipoOrganoConsulta").val(data.datosDeEnvio.asunto.tipoOrganoAsunto);

                        $(".mf-dialog #piezaclaseProcedimientoConsulta").val(data.datosDeEnvio.pieza.clasePieza);
                        $(".mf-dialog #piezaanioProcedimientoConsulta").val(data.datosDeEnvio.pieza.anioPieza);
                        $(".mf-dialog #piezanumeroPiezaConsulta").val(data.datosDeEnvio.pieza.numeroPieza);
                        $(".mf-dialog #piezanumeroProcedimientoConsulta").val(
                                data.datosDeEnvio.pieza.numeroProcedimientoPieza);

                        $(".mf-dialog #presentadorcodigoOrganoRemitenteConsulta").val(
                                data.presentadorcodigoOrganoRemitente);
                        $(".mf-dialog #presentadordescripcionPresentadorExpedienteConsulta").val(
                                data.datosDeEnvio.descripcionExpediente);
                    } else {
                        $('.mf-dialog #enlaceOnOff').addClass('hidden');
                        $('.mf-dialog #bloqueResguardoEnvio').addClass('hidden');
                        $(".mf-dialog #identificadorEstado").val("Respuesta Sin valor");
                        $(".mf-dialog #descripcionEstado").val("Respuesta Sin valor");
                        $(".mf-dialog #comentario").val(
                                "Los datos recibidos sobre el env\u00edo no aparecen informados.");

                    }

                    $("#tipoMensaje").val(data.mensajeUsuario.level);
                    $("#valorMensaje").val(data.mensajeUsuario.message);
                    showMessage();
                    $('#expedienteVeil').addClass('hidden');
                    $(".mf-dialog #modalConsultaRemisionVeil").addClass('hidden');

                },
                error : function(e) {
                    console.error(e);
                    $('#expedienteVeil').addClass('hidden');
                    $(".mf-dialog #modalConsultaRemisionVeil").addClass('hidden');

                }
            });

}

function showDialogImportarDocumento(e) {
    e.preventDefault();
    var divData = $('#init-modal-importar-documentoInside');
    var data = divData.data();
    var buttons = createButtonsDialog([ {
        clase : 'js-documento-cancelar',
        texto : 'Cancelar'
    }, {
        clase : 'js-documento-crear',
        texto : 'Adjuntar',
        submit : true
    } ]);

    buttons.find('.js-documento-crear').on('click', function(e) {
        e.preventDefault();
        $mf.my_dialog.close_dialog();
        createDocumentInside();
    });

    buttons.find('.js-documento-cancelar').on('click', function(e) {
        e.preventDefault();
        $mf.my_dialog.close_dialog();
    });

    data.buttons = buttons;
    data.data = divData.html();

    $mf.my_dialog.appendDialog(data, true, true);

    // Autocompletado del campo expedientes.
    $(".mf-dialog #identifier_document").autocomplete({
        source : $("#context").val() + '/autocomplete/documentos',
        minLength : 0,
        autoFocus : true,
        select : function(event, ui) {
            $(".mf-dialog #identifier_document").val(ui.item.key);
            event.preventDefault();
        }
    }).bind('focus', function() {
        $(this).autocomplete("search");
    }).data("ui-autocomplete")._renderItem = function(ul, item) {
        return $("<li>").append("<a>" + item.value + "</a>").appendTo(ul);
    };

    $("ul[id *= ui-id-]").css('zIndex', 9999);
}

function createDocumentInside() {

    var idDocumento = $(".mf-dialog #identifier_document").val();
    var idExpediente = $("#expedienteForm #identificador").val();

    function salir() {
        $('#expedienteVeil').addClass('hidden');
        $mf.my_dialog.close_dialog();
    }

    var lista = getLista();
    enviarAjax2($('#js-arbol').data('url_documento_inside'), {
        path : lista.item && getPath2(lista.item),
        idExpediente : idExpediente,
        id : idDocumento,
        fechaIncorpExp : getFecha(),
        ordenDocExp : 1,
        hidden : false
    }, function(data) {
        crearItemLista({
            id : data.idDocumento,
            texto : data.idDocumento,
            natural : data.nombreNatural,
            lista : lista.lista,
            isDocument : true
        });
        salir();
    }, salir);

}

function showDialogImportarExpediente(e) {
    e.preventDefault();
    var divData = $('#init-modal-importar-subExpediente');
    var data = divData.data();
    var buttons = createButtonsDialog([ {
        clase : 'js-documento-cancelar',
        texto : 'Cancelar'
    }, {
        clase : 'js-documento-crear',
        texto : 'Adjuntar',
        submit : true
    } ]);

    buttons.find('.js-documento-crear').on('click', function(e) {
        e.preventDefault();
        $mf.my_dialog.close_dialog();
        createSubExpedient();
    });

    buttons.find('.js-documento-cancelar').on('click', function(e) {
        e.preventDefault();
        $mf.my_dialog.close_dialog();
    });

    data.buttons = buttons;
    data.data = divData.html();

    $mf.my_dialog.appendDialog(data, true, true);

    // Autocompletado del campo expedientes.
    $(".mf-dialog #identifier_subExpedient").autocomplete({
        source : $("#context").val() + '/autocomplete/expedientes?allExp=true&uniAct=false',
        minLength : 0,
        autoFocus : true,
        select : function(event, ui) {
            $(".mf-dialog #identifier_subExpedient").val(ui.item.key);
            event.preventDefault();
        }
    }).bind('focus', function() {
        $(this).autocomplete("search");
    }).data("ui-autocomplete")._renderItem = function(ul, item) {
        return $("<li>").append("<a>" + item.value + "</a>").appendTo(ul);
    };

    $("ul[id *= ui-id-]").css('zIndex', 9999);
}

function createSubExpedient() {
    $('#expedienteVeil').removeAttr('style').removeClass('hidden');
    var idExpedienteDelInput = $("#expedienteForm #identificador").val();

    var idExpediente = $(".mf-dialog #identifier_subExpedient").val();
    var arbol = $('#js-arbol');

    enviarAjax2(arbol.data('url_subexpediente_nuevo'), {
        identifierSubExpedient : idExpediente,
        idExpediente : idExpedienteDelInput
    }, function(data) {
        if (data.mensajeUsuario != null) {
            $("#tipoMensaje").val(data.mensajeUsuario.level);
            $("#valorMensaje").val(data.mensajeUsuario.message);
            showMessage();
        } else {
            updateTree(data.newElementsIndex);
            crearEspaciosDragDrop();
        }
    });
}

function showDialogImportarExpedienteToken(e) {
    e.preventDefault();
    var divData = $('#init-modal-importar-subExpedienteToken');
    $('#init-modal-importar-subExpedienteToken #buttonSubmit').hide();
    // $('#init-modal-importar-subExpedienteToken #contenedorFichero').hide();

    var data = divData.data();
    var buttons = createButtonsDialog([ {
        clase : 'js-expediente-cancelar',
        texto : 'Cancelar'
    }, {
        clase : 'js-expediente-crear',
        texto : 'Adjuntar',
        submit : true
    } ]);

    buttons.find('.js-expediente-crear').on('click', function(e) {
        e.preventDefault();
        $mf.my_dialog.close_dialog();
        createSubExpedientToken();
    });

    buttons.find('.js-expediente-cancelar').on('click', function(e) {
        e.preventDefault();
        $mf.my_dialog.close_dialog();
    });

    data.buttons = buttons;
    data.data = divData.html();

    $mf.my_dialog.appendDialog(data, true, true);
}

function createSubExpedientToken() {
    $('#expedienteVeil').removeAttr('style').removeClass('hidden');

    var idExpediente = $("#expedienteForm #identificador").val();

    var tokenIdentificador = $(".mf-dialog #tokenIdentificador").val();
    var tokenCsv = $(".mf-dialog #tokenCsv").val();
    var tokenUuid = $(".mf-dialog #tokenUuid").val();
    var arbol = $('#js-arbol');

    enviarAjax2(arbol.data('url_subexpediente_token'), {
        identifierSubExpedient : tokenIdentificador,
        idExpediente : idExpediente,
        tokenCsv : tokenCsv,
        tokenUuid : tokenUuid
    }, function(data) {
        if (data.mensajeUsuario != null) {
            $("#tipoMensaje").val(data.mensajeUsuario.level);
            $("#valorMensaje").val(data.mensajeUsuario.message);
            showMessage();
        } else {
            updateTree(data.newElementsIndex);
            crearEspaciosDragDrop();
        }
    });
}

function conmutaVisibilidad() {
    $(".mf-dialog #fieldsetDatosEnvio").toggle();
}