var dataFileExp;
var navegador;

$(document).ready(function() {

    navegador = navegadorEI();

    MiniApplet.cargarMiniApplet(baseDownloadURL);

    tableExpedientes = $('#expedientList').dataTable({
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
        "pageLength" : 25,
        "dom" : '<"fld dsp_ib" f><"fld dsp_ib" l>t<"bottom" ir><"clear">',
        "bSort" : false,
        "aoColumns" : [ {
            "bSortable" : false,
            "width" : "10%"
        }, {
            "bSortable" : false,
            "width" : "20%"
        }, {
            "bSortable" : false,
            "width" : "10%"
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

    $('input[type=search]').parent().parent().css('width', '340px');
    $('input[type=search]').css('width', '320px');
});

function expedienteGenerarTokenDescargaPasoInicial(expediente) {
    // $('#almacenadosVeil').removeAttr('style').removeClass('hidden');

    var divData = $('#init-modal-generated-token-PasoPrevio');
    var data = divData.data();

    data.data = divData.html();

    $mf.my_dialog.appendDialog(data, true, true);

    $(".mf-dialog #expeId").val(expediente);

    var interesadosAdded = 0;
    $(".mf-dialog #interesadosAdded").val(interesadosAdded);

    var organosAdded = 0;
    $(".mf-dialog #organosAdded").val(organosAdded);

    // Autocompletado del campo organo.
    $(".mf-dialog #organosModal").autocomplete({
        source : $("#context").val() + '/autocomplete/dir3',
        // autoFocus: true,
        focus : function(event, ui) {
            $(".mf-dialog #organosModal").val(ui.item.key + " - " + ui.item.value);
            event.preventDefault();
        },
        select : function(event, ui) {
            $(".mf-dialog #organosModal").val(ui.item.key + " - " + ui.item.value);
            event.preventDefault();
        }
    }).data("ui-autocomplete")._renderItem = function(ul, item) {
        return $("<li>").append("<a>" + item.key + " - " + item.value + "</a>").appendTo(ul);
    };

    $("ul[id *= ui-id-]").css('zIndex', 9999);

}

function validarEmail(mail) {
    var expresion = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,4})+$/;

    if (mail.trim() != "") {
        var direcciones = mail.split(";");

        for (var i = 0; i < direcciones.length; i++) {
            if (!expresion.test(direcciones[i].trim())) {
                $(".mf-dialog #mailEnvioToken").val("");
                $("#tipoMensaje").val("4");
                $("#valorMensaje").val("Formato incorrecto del mail destino: " + direcciones[i]);
                showMessage();
            }
        }

    } else {
        return true;
    }

}

function expedienteGenerarTokenDescargaPasoFinal() {

    var expediente = $(".mf-dialog #expeId").val();
    var asuntoDesdeModal = $(".mf-dialog #asunto").val();
    var mailEnvioToken = $(".mf-dialog #mailEnvioToken").val();

    var contador = $(".mf-dialog #interesadosAdded").val();
    var nifsDesdeModal = "";
    for (var i = 0; i < contador; i++) {
        var valorNIF = $(".mf-dialog #interesadoslista" + i).val();

        if (nifsDesdeModal != "")
            nifsDesdeModal = nifsDesdeModal + ";" + valorNIF;
        else
            nifsDesdeModal = nifsDesdeModal + valorNIF;

    }

    var auxiliarDir3 = $(".mf-dialog #organosModal").val().split("-");
    var dir3DesdeModal = auxiliarDir3[0].trim();

    var fCadDesdeModal = $(".mf-dialog #doc_fecha_caducidad_token").val();

    $('#almacenadosVeil').removeAttr('style').removeClass('hidden');

    $.ajax({
        url : "expedienteGenerarTokenDescarga",
        dataType : 'json',
        type : 'POST',
        data : {
            "expediente" : expediente,
            "asuntoDesdeModal" : asuntoDesdeModal,
            "nifsDesdeModal" : nifsDesdeModal,
            "dir3DesdeModal" : dir3DesdeModal,
            "fCadDesdeModal" : fCadDesdeModal,
            "mailEnvioTokenDesdeModal" : mailEnvioToken
        },
        success : function(data) {
            var divData = $('#init-modal-generated-token');
            var dataModal = divData.data();

            dataModal.data = $("#init-modal-generated-token").html();

            $mf.my_dialog.appendDialog(dataModal, true, true);

            $(".mf-dialog #tokenIdentificador").val(data.identificador);
            $(".mf-dialog #tokenCsv").val(data.csv);
            $(".mf-dialog #tokenUuid").val(data.uuid);
            $(".mf-dialog #tokenfechaCaducidad").val(data.fechaCaducidad);

            $(".mf-dialog #resultadoEnvioCorreo").val(data.resultadoEnvioCorreo);

            $('#almacenadosVeil').addClass('hidden');
        },
        error : function(e) {
            console.error(e);
            $('#almacenadosVeil').addClass('hidden');
        }
    });

}

function superponer_CalendarioPrimerPlanoModalToken() {
    $("div.datepicker:last").css('zIndex', 9999);
}

function addInteresadoExp() {
    var interesadoValor = $(".mf-dialog #nifToken").val().trim();
    var contador = $(".mf-dialog #interesadosAdded").val();

    if ($(".mf-dialog #nifToken").val().trim() != "") {

        var key_input = document.createElement("input");
        var remove_button = document.createElement("button");
        var div = document.createElement("div");

        key_input.setAttribute("type", "text");
        key_input.setAttribute("id", "interesadoslista" + contador);
        key_input.setAttribute("name", "interesadoslista[" + contador + "]");
        key_input.setAttribute("class", "sticked-input-right");
        key_input.setAttribute("disabled", "disabled");
        key_input.setAttribute("value", interesadoValor);

        remove_button.setAttribute("type", "button");
        remove_button.setAttribute("name", "button");
        remove_button.setAttribute("class", "mf-icon mf-icon-cancel2");
        remove_button.setAttribute("id", "removeInteresadoButton_" + contador);
        remove_button.setAttribute("value", "Eliminar");
        remove_button.setAttribute("onclick", "removeInteresadoExp(" + contador + ")");
        div.setAttribute("id", "divInteresado_" + contador);

        $(".mf-dialog #listInteresados").append(div);

        $(".mf-dialog #divInteresado_" + contador).append(key_input);
        $(".mf-dialog #divInteresado_" + contador).append(remove_button);

        $('.mf-dialog #nifToken').val('');
        contador++;
        $(".mf-dialog #interesadosAdded").val(contador);
    }
}

function removeInteresadoExp(position) {
    $(document.getElementById("interesadoslista" + position)).remove();
    $("#removeInteresadoButton_" + position).remove();
    $("#divInteresado_" + position).remove();
    while (interesadosAdded - 1 > position) {
        var aux = position + 1;
        document.getElementById("interesadoslista" + aux).setAttribute("name", "interesadoslista[" + position + "]");
        document.getElementById("interesadoslista" + aux).setAttribute("id", "interesadoslista" + position);
        $("#removeInteresadoButton_" + aux).attr("onclick", "removeInteresadoExp(" + position + ")");
        $("#removeInteresadoButton_" + aux).attr("id", "removeInteresadoButton_" + position);
        $("#divInteresado_" + aux).attr("id", "divInteresado_" + position);
        position++;
    }
    interesadosAdded--;
}

function ocultarOrden() {
    $('.mf-dialog #ordenModal').addClass('hidden');
    $('.mf-dialog #labelOrdenModal').addClass('hidden');
    $('.mf-dialog #bloqueProcedimiento').addClass('hidden');
    $('.mf-dialog #bloqueNIG').addClass('hidden');
    $('.mf-dialog #bloquePieza').addClass('hidden');
    $('.mf-dialog #bloquePresentador').addClass('hidden');
    $('.mf-dialog #bloqueBotones').addClass('hidden');

}

function mostrarOrden() {
    $('.mf-dialog #ordenModal').removeAttr('style').removeClass('hidden');
    $('.mf-dialog #labelOrdenModal').removeAttr('style').removeClass('hidden');

}

function mostrarUocultarOrdenModal() {

    if ($(".mf-dialog #dir3Justicia").val().trim() == "") {
        $('.mf-dialog #ordenModal').val("");
        $('.mf-dialog #claseProcedimiento').val("");
        $('.mf-dialog #procedimientoanio').val("");
        $('.mf-dialog #procedimientonumero').val("");
        $('.mf-dialog #niganioAsunto').val("");
        $('.mf-dialog #nigcodigoOrden').val("");
        $('.mf-dialog #nigcodigoPoblacion').val("");
        $('.mf-dialog #nignumeroAsunto').val("");
        $('.mf-dialog #nigtipoOrgano').val("");
        $('.mf-dialog #presentadordescripcionPresentadorExpediente').val("");
        ocultarOrden();
        $('.mf-dialog #bloqueProcedimiento').addClass('hidden');

    }

}

function mostrarBloqueProcedimiento() {

    $('.mf-dialog #bloqueProcedimiento').removeAttr('style').removeClass('hidden');
    $('.mf-dialog #bloqueNIG').removeAttr('style').removeClass('hidden');
    //$('.mf-dialog #bloquePieza').removeAttr('style').removeClass('hidden');
    $('.mf-dialog #bloquePresentador').removeAttr('style').removeClass('hidden');
    $('.mf-dialog #bloqueBotones').removeAttr('style').removeClass('hidden');

}

function mostrarUocultarBloqueProcedimiento() {

    if ($(".mf-dialog #ordenModal").val().trim() == "") {
        $('.mf-dialog #claseProcedimiento').val("");
        $('.mf-dialog #bloqueProcedimiento').addClass('hidden');
        $('.mf-dialog #bloqueNIG').addClass('hidden');
        $('.mf-dialog #bloquePieza').addClass('hidden');
        $('.mf-dialog #bloquePresentador').addClass('hidden');
        $('.mf-dialog #bloqueBotones').addClass('hidden');
    }

}

function mensajeNoOrdenYOcultarOrden() {
    if ($(".mf-dialog #ordenModal").val() == "" && $(".mf-dialog #almacenadosVeilGeneraToken").is(":visible")) {
        $(".mf-dialog #almacenadosVeilGeneraToken").addClass('hidden');

        $("#tipoMensaje").val("4");
        $("#valorMensaje").val("No hay ORDEN para el valor: '" + $(".mf-dialog #dir3Justicia").val() + "'");
        showMessage();

        ocultarOrden();
    }

}

function resetear() {
    if ($(".mf-dialog #ordenModal").val().trim() != "") {
        $('.mf-dialog #ordenModal').val("");
        $('.mf-dialog #claseProcedimiento').val("");
        $('.mf-dialog #procedimientoanio').val("");
        $('.mf-dialog #procedimientonumero').val("");
        $('.mf-dialog #niganioAsunto').val("");
        $('.mf-dialog #nigcodigoOrden').val("");
        $('.mf-dialog #nigcodigoPoblacion').val("");
        $('.mf-dialog #nignumeroAsunto').val("");
        $('.mf-dialog #nigtipoOrgano').val("");
        $('.mf-dialog #presentadordescripcionPresentadorExpediente').val("");
        mostrarUocultarBloqueProcedimiento();

    }

}

function openModalRemitirMJU(expediente) {
    var divData = $('#init-modal-remitirMJU');
    var data = divData.data();

    data.data = divData.html();

    $mf.my_dialog.appendDialog(data, true, true);

    $("#remitirMJUExpedienteForm #identificador").val(expediente);

    ocultarOrden();

    // incluir combo para elegir version del expediente que se envia a justicia	
    $.ajax({
        url : "getVersionesExpediente",
        dataType : 'json',
        type : 'POST',
        data : {
            "expediente" : expediente
        },
        success : function(data) {
            var DIR3ParaEnvioJusticia = data.DIR3ParaEnvioJusticia;
            $(".mf-dialog #presentadorcodigoOrganoRemitente").val(DIR3ParaEnvioJusticia);
            var listVersiones = data.versiones;
            for (var i = 0; i < listVersiones.length; i++) {
                var fecha = new Date(listVersiones[i].fechaVersion);
                $('.mf-dialog #versiones_expediente_MJU').append(
                        $('<option>', {
                            value : listVersiones[i].version,
                            text : 'Versión: ' + listVersiones[i].version + ' - Fecha: ' + formatoFecha(fecha) + ', '
                                    + formatoHora(fecha)
                        }));
                $('.mf-dialog #identificador_expediente_MJU').val(expediente);
            }

            $('.mf-dialog #almacenadosVeilVersiones').addClass('hidden');
        },
        error : function(e) {
            console.error(e);
            $('.mf-dialog #almacenadosVeilVersiones').addClass('hidden');
        }
    });

    // Autocompletado del campo organo.
    $(".mf-dialog #dir3Justicia").autocomplete({
        source : $("#context").val() + '/autocomplete/dir3EnvioJusticiaVigentes',
        minLength : 0,
        select : function(event, ui) {
            $(".mf-dialog #dir3Justicia").val(ui.item.key + " - " + ui.item.value + " - " + ui.item.type);
            event.preventDefault();
            buscarOrdenByOrganoDIR3Justicia();
        }
    }).bind('focus', function() {
        $(this).autocomplete("search");
    }).data("ui-autocomplete")._renderItem = function(ul, item) {
        return $("<li>").append("<a>" + item.key + " - " + item.value + " - " + item.type + "</a>").appendTo(ul);
    };

    $("ul[id *= ui-id-]").css('zIndex', 9999);
}

function buscarOrganosRemitentes() {

    // Autocompletado del campo organo.
    $(".mf-dialog #presentadorcodigoOrganoRemitente").autocomplete({
        source : $("#context").val() + '/autocomplete/dir3',
        minLength : 0,
        // autoFocus: true,
        // focus : function(event, ui) {
        // $(".mf-dialog
        // #presentadorcodigoOrganoRemitente").val(ui.item.key + " - " +
        // ui.item.value);
        // event.preventDefault();
        // },
        select : function(event, ui) {
            $(".mf-dialog #presentadorcodigoOrganoRemitente").val(ui.item.key + " - " + ui.item.value);
            event.preventDefault();
        }
    }).bind('focus', function() {
        $(this).autocomplete("search");
    }).data("ui-autocomplete")._renderItem = function(ul, item) {
        return $("<li>").append("<a>" + item.key + " - " + item.value + "</a>").appendTo(ul);
    };

    $("ul[id *= ui-id-]").css('zIndex', 9999);
}

function buscarOrganos() {
    if ($(".mf-dialog #provinciasModal").val().trim() != "") {
        var codigoProvinciaYDescripcion = $(".mf-dialog #provinciasModal").val();

        var splitcodigoProvinciaYDescripcion = codigoProvinciaYDescripcion.split("-");
        var codigoProvincia = splitcodigoProvinciaYDescripcion[0].trim();

        // Autocompletado del campo organo.
        $(".mf-dialog #organos")
                .autocomplete(
                        {
                            source : $("#context").val() + "/justicia/getOrganosByProvincia?codigoprovincia="
                                    + codigoProvincia,
                            minLength : 0,
                            autoFocus : true,
                            // focus : function(event, ui) {
                            // $(".mf-dialog #organos").val(ui.item.key + " - "
                            // + ui.item.value + " - " + ui.item.tipoOrgano + "
                            // - " + ui.item.codigoPoblacion);
                            // event.preventDefault();
                            // },
                            select : function(event, ui) {
                                $(".mf-dialog #organos").val(
                                        ui.item.key + " - " + ui.item.value + " - " + ui.item.tipoOrgano + " - "
                                                + ui.item.codigoPoblacion);
                                event.preventDefault();
                                buscarOrdenByOrgano();
                            }
                        }).bind('focus', function() {
                    $(this).autocomplete("search");
                }).data("ui-autocomplete")._renderItem = function(ul, item) {
            $(ul).css({
                'zIndex' : 9999,
                'display' : 'block'
            });
            return $("<li>").append(
                    "<a>" + item.key + " - " + item.value + " - " + item.tipoOrgano + " - " + item.codigoPoblacion
                            + "</a>").appendTo(ul);
        };
    }
}

function buscarOrdenByOrgano() {
    if ($(".mf-dialog #organos").val() != "") {
        var numOrgano = $(".mf-dialog #organos").val().split("-")[0].trim();
        var codigoPoblacion = $(".mf-dialog #organos").val().split("-")[3].trim();
        var tipoOrgano = $(".mf-dialog #organos").val().split("-")[2].trim();

        // Autocompletado del campo orden.
        $(".mf-dialog #ordenModal").autocomplete(
                {
                    source : $("#context").val() + "/justicia/getOrdenByOrgano?numOrgano=" + numOrgano
                            + "&codigoPoblacion=" + codigoPoblacion + "&tipoOrgano=" + tipoOrgano,
                    minLength : 0,
                    autoFocus : true,
                    // focus : function(event, ui) {
                    // $(".mf-dialog #ordenModal").val(ui.item.key + " - " +
                    // ui.item.value);
                    // event.preventDefault();
                    // },
                    select : function(event, ui) {
                        $(".mf-dialog #ordenModal").val(ui.item.key + " - " + ui.item.value);
                        event.preventDefault();
                        buscarClaseProcedimientoByOrdenAndOrgano();
                    }
                }).bind('focus', function() {
            $(this).autocomplete("search");
        }).data("ui-autocomplete")._renderItem = function(ul, item) {
            $(ul).css({
                'zIndex' : 9999,
                'display' : 'block'
            });
            return $("<li>").append("<a>" + item.key + " - " + item.value + "</a>").appendTo(ul);
        };
    }
}

function buscarOrdenByOrganoDIR3Justicia() {
    if ($(".mf-dialog #dir3Justicia").val() != "") {
        var codigoExternoDIR3 = $(".mf-dialog #dir3Justicia").val().split(" - ")[2].trim();

        var codigoPoblacion = codigoExternoDIR3.substring(0, 5);
        var tipoOrgano = codigoExternoDIR3.substring(5, 7);
        var numOrgano = codigoExternoDIR3.substring(7, 10);

        // Autocompletado del campo orden.
        $(".mf-dialog #ordenModal").autocomplete(
                {
                    source : $("#context").val() + "/justicia/getOrdenByOrgano?numOrgano=" + numOrgano
                            + "&codigoPoblacion=" + codigoPoblacion + "&tipoOrgano=" + tipoOrgano,
                    minLength : 0,
                    autoFocus : true,
                    // focus : function(event, ui) {
                    // $(".mf-dialog #ordenModal").val(ui.item.key + " - " +
                    // ui.item.value);
                    // event.preventDefault();
                    // },
                    select : function(event, ui) {
                        $(".mf-dialog #ordenModal").val(ui.item.key + " - " + ui.item.value);
                        event.preventDefault();
                        buscarClaseProcedimientoByOrdenAndOrgano();
                    }
                }).bind('focus', function() {
            $(this).autocomplete("search");
        }).data("ui-autocomplete")._renderItem = function(ul, item) {
            $(ul).css({
                'zIndex' : 9999,
                'display' : 'block'
            });

            $(".mf-dialog #almacenadosVeilGeneraToken").addClass('hidden');
            return $("<li>").append("<a>" + item.key + " - " + item.value + "</a>").appendTo(ul);
        };

        mostrarOrden();

        $(".mf-dialog #nigcodigoPoblacion").val(codigoPoblacion);
        $(".mf-dialog #nigtipoOrgano").val(tipoOrgano);

    }

}

function buscarClaseProcedimientoByOrdenAndOrgano() {
    $(".mf-dialog #almacenadosVeilGeneraToken").removeAttr('style').removeClass('hidden');
    if ($(".mf-dialog #dir3Justicia").val() != "" && $(".mf-dialog #ordenModal").val() != "") {
        $(".mf-dialog #almacenadosVeilGeneraToken").addClass('hidden');

        var codigoExternoDIR3 = $(".mf-dialog #dir3Justicia").val().split(" - ")[2].trim();

        var codigoPoblacion = codigoExternoDIR3.substring(0, 5);
        var tipoOrgano = codigoExternoDIR3.substring(5, 7);
        var numOrgano = codigoExternoDIR3.substring(7, 10);

        var ordenNum = $(".mf-dialog #ordenModal").val().split("-")[0].trim();
        $(".mf-dialog #nigcodigoOrden").val(ordenNum);

        // Autocompletado del campo orden.
        $(".mf-dialog #claseProcedimiento").autocomplete(
                {
                    source : $("#context").val() + "/justicia/getClaseProcedimientoByOrdenAndOrgano?numOrgano="
                            + numOrgano + "&codigoPoblacion=" + codigoPoblacion + "&tipoOrgano=" + tipoOrgano
                            + "&ordenNum=" + ordenNum,
                    minLength : 0,
                    autoFocus : true,
                    // focus : function(event, ui) {
                    // $(".mf-dialog
                    // #claseProcedimiento").val(ui.item.key + " - " +
                    // ui.item.value);
                    // event.preventDefault();
                    // },
                    select : function(event, ui) {
                        $(".mf-dialog #claseProcedimiento").val(ui.item.key + " - " + ui.item.value);
                        event.preventDefault();
                    }
                }).bind('focus', function() {
            $(this).autocomplete("search");
        }).data("ui-autocomplete")._renderItem = function(ul, item) {
            $(ul).css({
                'zIndex' : 9999,
                'display' : 'block'
            });
            return $("<li>").append("<a>" + item.key + " - " + item.value + "</a>").appendTo(ul);
        };

        mostrarBloqueProcedimiento();

    } else {
        setTimeout(function() {
            mensajeNoOrdenYOcultarOrden()
        }, 3000);//pasan 3 segundos y oculta el orden

    }

}

function cerrarRemitirMJU() {

    $mf.my_dialog.close_dialog();

}

function remitirMJU() {
    $('#almacenadosVeil').removeAttr('style').removeClass('hidden');
    $(".mf-dialog #almacenadosVeilGeneraToken").removeAttr('style').removeClass('hidden');
    $.ajax({
        url : $("#context").val() + "/justicia/remitirExpediente",
        dataType : 'json',
        type : 'POST',
        data : $(".mf-dialog #remitirMJUExpedienteForm").serialize(),
        success : function(data) {
            $("#tipoMensaje").val(data.mensajeUsuario.level);
            $("#valorMensaje").val(data.mensajeUsuario.message);
            showMessage();
            $('#almacenadosVeil').addClass('hidden');
            $(".mf-dialog #almacenadosVeilGeneraToken").addClass('hidden');
        },
        error : function(e) {
            console.error(e);
            $('#almacenadosVeil').addClass('hidden');
            $(".mf-dialog #almacenadosVeilGeneraToken").addClass('hidden');
        }
    });
}

function mostrarDialogDeleteExpediente(expediente) {
    $('#almacenadosVeil').removeAttr('style').removeClass('hidden');
    var divData = $('#init-modal-borrar-expediente');
    var data = divData.data();
    var buttons = createButtonsDialog([ {
        clase : 'js-expediente-cancelar',
        texto : 'Cancelar'
    }, {
        clase : 'js-expediente-borrar',
        texto : 'Confirmar'
    } ]);

    buttons.find('.js-expediente-borrar').on('click', function(e) {
        e.preventDefault();
        $('#almacenadosVeil').removeAttr('style').removeClass('hidden');
        $("#borrarExpedienteForm #identificador").val(expediente);
        $("#borrarExpedienteForm").submit();
        $mf.my_dialog.close_dialog();
    });

    buttons.find('.js-expediente-cancelar').on('click', function(e) {
        e.preventDefault();
        $mf.my_dialog.close_dialog();
    });

    data.buttons = buttons;
    data.data = divData.html();

    $mf.my_dialog.appendDialog(data, true, true);
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

function editarExpediente() {
    var version = $(".mf-dialog #versiones_expediente").find(":selected").val();
    var expediente = $(".mf-dialog #identificador_expediente").val();
    editarExpedienteSubmit(expediente, version);
}

function editarExpedienteSubmit(expediente, version) {
    $('#almacenadosVeil').removeAttr('style').removeClass('hidden');
    $("#editarExpedienteForm #identificador").val(expediente);
    $("#editarExpedienteForm #version").val(version);
    $("#editarExpedienteForm #navegador").val(navegador);
    $("#editarExpedienteForm").submit();
}

function verExpedienteSubmit(expediente) {
    $('#almacenadosVeil').removeAttr('style').removeClass('hidden');
    $("#verExpedienteForm #identificador").val(expediente);
    $("#verExpedienteForm #navegador").val(navegador);
    $("#verExpedienteForm").submit();
}

function volverExpedienteAsociado() {
    $("#volverExpedienteAsociadoForm").submit();
}

function createView(expedient, identifierView, openView) {
    var close = false;
    $('#almacenadosVeil').removeAttr('style').removeClass('hidden');
    $('#vistaVeilVersiones').removeAttr('style').removeClass('hidden');
    if ($("input[name=firmaExp][value='client']").is(':checked')) {
        selectViewType(expedient, identifierView, openView, false);
        close = true;
    } else if ($("input[name=firmaExp][value='server']").is(':checked')) {
        selectViewType(expedient, identifierView, openView, true);
        close = true;
    } else {
        alert('Debe seleccionar un tipo de firma para el expediente. Si no tiene habilitadas opciones contacte con el administrador.');
        $('#vistaVeilVersiones').addClass('hidden');
    }
    return close;
}

function selectViewType(expedient, identifierView, openView, signatureServer) {
    if (openView)
        createViewAjax(expedient, identifierView, openView, '/crearVistaAbiertaExpediente', signatureServer);
    else
        createViewAjax(expedient, identifierView, openView, '/crearVistaCerradaExpediente', signatureServer);
}

function createViewAjax(expedient, identifierView, openView, url, signatureServer) {
    $('#almacenadosVeil').removeAttr('style').removeClass('hidden');
    ocultarMensaje();
    $.ajax({
        url : $("#context").val() + url,
        type : 'POST',
        dataType : 'json',
        data : {
            "identifier" : JSON.stringify(expedient),
            "identifierView" : JSON.stringify(identifierView),
            "firmaServidor" : signatureServer
        },
        success : function(data) {
            if (data.expedienteEni != null) {
                if (signatureServer) {
                    editarExpedienteSubmit(identifierView, null);
                } else {
                    callAFirma(identifierView, openView, data.expedienteEni, data.expedienteIndiceContenido);
                }
            } else {
                if (data.mensajeUsuario != null) {
                    $("#tipoMensaje").val(data.mensajeUsuario.level);
                    $("#valorMensaje").val(data.mensajeUsuario.message);
                    showMessage();
                }
                $('#almacenadosVeil').addClass('hidden');
            }
        },
        error : function(xhr) {
            console.error(xhr.responseText);
            $('#almacenadosVeil').addClass('hidden');
        }
    });
}

function callAFirma(identifierView, openView, expedienteEni, expedienteIndiceContenido) {
    var dataB64 = btoa(expedienteIndiceContenido);
    var id_indiceContenido = "EXP_INDICE_CONTENIDO" + identifierView;

    var format = "XAdES";
    var algorithm = "SHA1withRSA";
    var params = "format=XAdES Enveloped\nnodeToSign=" + id_indiceContenido;

    sessionStorage.setItem('identifierView', identifierView);
    sessionStorage.setItem('openView', openView);
    sessionStorage.setItem('expedienteEni', expedienteEni);
    MiniApplet.sign(dataB64, algorithm, format, params, signCorrect, signError);
}

function signCorrect(signatureB64) {
    var identifierView = sessionStorage.getItem('identifierView');
    var expedienteEni = sessionStorage.getItem('expedienteEni');
    var openView = sessionStorage.getItem('openView');
    eval("updateIndexExp(identifierView, expedienteEni, openView, signatureB64);");
}

function signError(errorType, errorMsg) {
    if (errorType == 'es.gob.afirma.core.AOCancelledOperationException') {
        console.log("operationCancelled");
        error = "operationCancelled";
    } else if (errorType == 'es.gob.afirma.keystores.common.AOCertificatesNotFoundException') {
        console.log("No se ha encontrado certificado en el almacen");
        error = "No se ha encontrado certificado en el almacen";
    } else {
        console.log(errorMsg);
        error = "No se pudo realizar la firma, inténtelo más tarde o contacte con el administrador";
    }
    $('#almacenadosVeil').addClass('hidden');
    $("#tipoMensaje").val(4);
    $("#valorMensaje").val("Error en el proceso de firma: " + error);
    showMessage();
}

function updateIndexExp(idExpediente, expedient, openView, dataSign) {
    $.ajax({
        url : $("#context").val() + '/updateIndexExpedient',
        type : 'POST',
        dataType : 'json',
        data : {
            "indexExpB64" : JSON.stringify(dataSign),
            "expedienteEni" : JSON.stringify(expedient),
            "identificadorExpediente" : JSON.stringify(idExpediente),
            "isNewView" : true
        },
        success : function(data) {
            if (data.mensajeUsuario != null) {
                $("#tipoMensaje").val(data.mensajeUsuario.level);
                $("#valorMensaje").val(data.mensajeUsuario.message);
                editarExpedienteSubmit(idExpediente, null);
            }
            $('#almacenadosVeil').addClass('hidden');
        },
        error : function(xhr) {
            console.error(xhr.responseText);
            $('#almacenadosVeil').addClass('hidden');
        }
    });
}

function showDialogNameView(expedient, mng1, mng2, openView) {
    var divData = $('#init-modal-define-view');
    var data = divData.data();
    var buttons = createButtonsDialog([ {
        clase : 'js-documento-cancelar',
        texto : 'Cancelar'
    }, {
        clase : 'js-documento-crear',
        texto : 'Crear',
        submit : true
    } ]);

    buttons.find('.js-documento-crear').on('click', function(e) {
        e.preventDefault();
        $('.mf-dialog #defineViewVeil').removeAttr('style').removeClass('hidden');
        $('.mf-dialog #infoRegex').addClass('hidden');

        var identifierView = $(".mf-dialog #identificer_view").val();

        if (identifierView === '') {
            var close = createView(expedient, identifierView, openView);
            if (close)
                $mf.my_dialog.close_dialog();
        } else {
            if (checkRegexIdentifierView()) {
                checkExistIdentifierExpedient(expedient, identifierView, mng2, openView);
            } else {
                var text = mng1 + ': ' + $("#expresionRegular").val();
                checkFailedIdentifierView(text);
            }
        }

    });

    buttons.find('.js-documento-cancelar').on('click', function(e) {
        e.preventDefault();
        $mf.my_dialog.close_dialog();
    });

    data.buttons = buttons;
    data.data = divData.html();

    $mf.my_dialog.appendDialog(data, true, true);
}

function campoSinCaracteresEspeciales() {
    var identificador = $(".mf-dialog #identificer_view").val();
    var caracteresValidos = "^[a-zA-Z0-9._-]*$";
    if (!identificador.match(caracteresValidos)) {
        var nombreAcumulado = identificador;
        var ultimoChar = nombreAcumulado.substr(nombreAcumulado.length - 1, nombreAcumulado.length);
        var nombreAcumuladoMenosUltimoChar = nombreAcumulado.substr(0, (nombreAcumulado.length - 1));
        $(".mf-dialog #identificer_view").val(nombreAcumuladoMenosUltimoChar);
        $("#tipoMensaje").val(4);
        $("#valorMensaje").val("El caracter especial: " + ultimoChar + " no se admite en el campo en el identificador");
        showMessage();
    }
}

function checkRegexIdentifierView() {
    var str = $(".mf-dialog #identificer_view").val();
    var patt = new RegExp($("#expresionRegular").val());
    var res = patt.test(str);
    return res;
}

function checkFailedIdentifierView(mng) {
    $('.mf-dialog #infoRegex').removeAttr('style').removeClass('hidden');
    $('.mf-dialog #textInfoRegex').text(mng);
    $('.mf-dialog #defineViewVeil').addClass('hidden');
}

function checkExistIdentifierExpedient(expedient, identifierView, mng2, openView) {
    var resultado = false;
    $.ajax({
        url : $("#context").val() + '/comprobarIdentificadorExpediente',
        type : 'POST',
        dataType : 'json',
        timeout : 999999,
        data : {
            "identificadorExpediente" : identifierView
        },
        success : function(data) {
            if (data.mensajeIdentificador != null) {
                checkFailedIdentifierView(mng2);
            } else {
                if (data.mensajeUsuario != null && data.mensajeUsuario.level == "4") {
                    $("#tipoMensaje").val(data.mensajeUsuario.level);
                    $("#valorMensaje").val(data.mensajeUsuario.message);
                    showMessage();
                    $mf.my_dialog.close_dialog();
                    $('#defineViewVeil').addClass('hidden');
                } else {
                    var close = createView(expedient, identifierView, openView);
                    if (close)
                        $mf.my_dialog.close_dialog();
                }
            }
        },
        error : function(xhr) {
            $('#defineViewVeil').addClass('hidden');
            $mf.my_dialog.close_dialog();
            console.error(xhr.responseText);
        }
    });
    return resultado;
}

function showModalVersionExpediente(expediente) {
    $('#almacenadosVeilVersiones').removeAttr('style').removeClass('hidden');
    var divData = $('#init-modal-versiones-expedientes');
    var data = divData.data();
    var buttons = createButtonsDialog([ {
        clase : 'js-expediente-cancelar',
        texto : 'Cancelar'
    }, {
        clase : 'js-expediente-crear',
        texto : 'Seleccionar',
        submit : true
    } ]);

    buttons.find('.js-expediente-crear').on('click', function(e) {
        e.preventDefault();
        editarExpediente();
        $mf.my_dialog.close_dialog();
        $('#almacenadosVeil').removeAttr('style').removeClass('hidden');
    });

    buttons.find('.js-expediente-cancelar').on('click', function(e) {
        e.preventDefault();
        $mf.my_dialog.close_dialog();
    });

    $.ajax({
        url : "getVersionesExpediente",
        dataType : 'json',
        type : 'POST',
        data : {
            "expediente" : expediente
        },
        success : function(data) {
            var listVersiones = data.versiones;
            for (var i = 0; i < listVersiones.length; i++) {
                var fecha = new Date(listVersiones[i].fechaVersion);
                $('.mf-dialog #versiones_expediente').append(
                        $('<option>', {
                            value : listVersiones[i].version,
                            text : 'Versión: ' + listVersiones[i].version + ' - Fecha: ' + formatoFecha(fecha) + ', '
                                    + formatoHora(fecha) + ' .-. Remitido MJU: ' + listVersiones[i].remitidoMJU
                        }));
                $('.mf-dialog #identificador_expediente').val(expediente);
            }

            $('.mf-dialog #almacenadosVeilVersiones').addClass('hidden');
        },
        error : function(e) {
            console.error(e);
            $('.mf-dialog #almacenadosVeilVersiones').addClass('hidden');
        }
    });

    data.buttons = buttons;
    data.data = divData.html();

    $mf.my_dialog.appendDialog(data, true, true);
}

function formatoFecha(fecha) {
    return dosDigitos(fecha.getDate()) + '-' + dosDigitos((fecha.getMonth() + 1)) + '-' + fecha.getFullYear();
}

function formatoHora(fecha) {
    return dosDigitos(fecha.getHours()) + ':' + dosDigitos(fecha.getMinutes()) + ':' + dosDigitos(fecha.getSeconds());
}

function dosDigitos(valor) {
    if (valor < 10)
        return '0' + valor;
    else
        return valor;
}

function rellenarCeros(idValor) {
    var valorOriginal = $(".mf-dialog #" + idValor).val().trim();
    if (valorOriginal.length > 0) {
        var valorRellenado = ("0000000" + valorOriginal).slice(-7);
        $(".mf-dialog #" + idValor).val(valorRellenado);
    }
}

function navegadorEI() {
    if (navigator.userAgent.indexOf('Firefox') != -1 || navigator.userAgent.indexOf('Chrome') != -1) {
        return false;
    } else {
        return true;
    }
}
