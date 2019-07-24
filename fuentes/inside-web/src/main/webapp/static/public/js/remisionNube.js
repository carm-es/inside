var dataFileExp;
var tablaSolicitudesAccesoExpediente;

$(document).ready(function() {
    tablaSolicitudesAccesoExpediente = $('#solicitudesAccesoExpedienteTabla').dataTable({
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
        "sPaginationType" : "full_numbers",
        "bPaginate" : true,
        "dom" : '<"fld dsp_ib" f><"fld dsp_ib" l>t<"bottom" ir><"clear">',
        "bSort" : false,
        "aoColumns" : [ {
            "bSortable" : false
        }, {
            "bSortable" : false
        }, {
            "bSortable" : false
        }, {
            "bSortable" : false
        }, {
            "bSortable" : false
        }, {
            "bSortable" : false
        }, {
            "bSortable" : false
        }, {
            "bSortable" : false
        } ],
        "paging" : true
    });

    $('input[type=search]').parent().parent().css('width', '340px');
    $('input[type=search]').css('width', '320px');

    tablaSolicitudesAccesoExpediente.fnFilter('Pendiente de enviar', 4);

    $('#verEnviadas').click(function() {
        if ($("#verEnviadas").is(':checked')) {
            tablaSolicitudesAccesoExpediente.fnFilter('', 4);
        } else {
            tablaSolicitudesAccesoExpediente.fnFilter('Pendiente de enviar', 4);
        }
    });

    showMessage();

});

function openModalRemitirTokenSolicitud(expediente, idExpVacio, metadatos) {

    var divData = $('#init-modal-solicitudesAccesoExpediente');
    var data = divData.data();

    data.data = divData.html();

    $mf.my_dialog.appendDialog(data, true, true);

    if (new String(idExpVacio).valueOf() == new String("true").valueOf()) {
        $(".mf-dialog #inputIdentificadorVacio").removeAttr('style').removeClass('hidden');
        $(".mf-dialog #labelMetadatosAdicionales").empty();
        $(".mf-dialog #labelMetadatosAdicionales").append(metadatos);
        // Autocompletado del campo expedientes.
        $(".mf-dialog #identificadorExpedienteEscogidoPorUsuario").autocomplete({
            source : $("#context").val() + '/autocomplete/expedientes?allExp=true&uniAct=false',
            minLength : 0,
            autoFocus : true,
            select : function(event, ui) {
                $(".mf-dialog #identificadorExpedienteEscogidoPorUsuario").val(ui.item.key);
                event.preventDefault();
            }
        }).bind('focus', function() {
            $(this).autocomplete("search");
        }).data("ui-autocomplete")._renderItem = function(ul, item) {
            return $("<li>").append("<a>" + item.value + "</a>").appendTo(ul);
        };

        $("ul[id *= ui-id-]").css('zIndex', 9999);
    }

    $("#remitirTokenForm #identificador").val(expediente);

    var interesadosAdded = 0;
    $(".mf-dialog #interesadosAdded").val(interesadosAdded);

}

function remitirToken() {

    var contador = $(".mf-dialog #interesadosAdded").val();
    var nifsDesdeModal = "";
    for (var i = 0; i < contador; i++) {
        var valorNIF = $(".mf-dialog #interesadoslista" + i).val();

        if (nifsDesdeModal !== "")
            nifsDesdeModal = nifsDesdeModal + ";" + valorNIF;
        else
            nifsDesdeModal = nifsDesdeModal + valorNIF;

    }
    $(".mf-dialog #nifsDesdeModal").val(nifsDesdeModal);
    $(".mf-dialog #rechazado").val("N");

    enviarFormularioRemitirTokenSolicitudAcceso();

}

function rechazarToken(expediente) {

    $.ajax({
        url : $("#context").val() + "/rechazarTokenSolicitudAcceso",
        dataType : 'json',
        type : 'POST',
        data : {
            "identificador" : expediente
        },
        success : function(data) {
            $("#tipoMensaje").val(data.mensajeUsuario.level);
            $("#valorMensaje").val(data.mensajeUsuario.message);
            $mf.my_dialog.close_dialog();
            refrescaListadoSolicitudesToken(data.mensajeUsuario.level, data.mensajeUsuario.message);
        },
        error : function(e) {
            console.error(e);
            $("#tipoMensaje").val(4);
            $("#valorMensaje").val("Error al remitir token");
            showMessage();
        }

    });

}

function enviarFormularioRemitirTokenSolicitudAcceso() {
    $.ajax({
        url : $("#context").val() + "/remitirTokenSolicitudAcceso",
        dataType : 'json',
        type : 'POST',
        data : $(".mf-dialog #remitirTokenForm").serialize(),
        success : function(data) {
            $("#tipoMensaje").val(data.mensajeUsuario.level);
            $("#valorMensaje").val(data.mensajeUsuario.message);
            $mf.my_dialog.close_dialog();
            refrescaListadoSolicitudesToken(data.mensajeUsuario.level, data.mensajeUsuario.message);
        },
        error : function(e) {
            console.error(e);
            $("#tipoMensaje").val(4);
            $("#valorMensaje").val("Error al remitir token");
            showMessage();
        }

    });
}

function refrescaListadoSolicitudesToken(tipoM, valorM) {
    $(location).attr('href', window.location.pathname + '?tipoMensaje=' + tipoM + '&valorMensaje=' + valorM);
    showMessage();
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
    interesadosAdded = $(".mf-dialog #interesadosAdded").val();
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
    $(".mf-dialog #interesadosAdded").val(interesadosAdded);
}
