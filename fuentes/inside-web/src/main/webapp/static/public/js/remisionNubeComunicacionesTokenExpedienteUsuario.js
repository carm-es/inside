var dataFileExp;
var tablaComunicacionesTokenExpedienteUsuario;

$(document).ready(function() {
    tablaComunicacionesTokenExpedienteUsuario = $('#comunicacionesTokenExpedienteUsuarioTabla').dataTable({
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
        "bSort" : true,
        "aoColumns" : [ {
            "bSortable" : false,
            "width" : "10%"
        }, {
            "bSortable" : false,
            "width" : "24%"
        }, {
            "bSortable" : false,
            "width" : "22%"
        }, {
            "bSortable" : true,
            "iDataSort" : 4,
            "width" : "15%"
        }, {
            "bSortable" : true,
            "bVisible" : false,
            "width" : "0%"
        }, {
            "bSortable" : true,
            "iDataSort" : 4,
            "width" : "15%"
        }, {
            "bSortable" : true,
            "width" : "5%"
        } ],
        "paging" : true
    });

    $('input[type=search]').parent().parent().css('width', '340px');
    $('input[type=search]').css('width', '320px');

    showMessage();

    configurarNuevaPagina();
});

function configurarNuevaPagina() {
    $('#expedienteForm').on('submit', function(e) {
        e.preventDefault();
    });

}

function verExpedienteNoInside(identificador) {

    $("#puestaDisposicionExpedienteNoInsideForm #identificador").val(identificador);

    $("#puestaDisposicionExpedienteNoInsideForm").submit();

}

function volverAListadoComunicaciones() {

    $("#backStoredComunicacionesForm").submit();

}

function onClickVerDocNoInside(e) {

    var $el = $(e.currentTarget);

    var arbol = $el.closest('#js-arbol');
    var visor = arbol.parent().find('#js-gallery-ver');
    var idComunicacion = $('#comunicacionForm #idComunicacion').val();

    mostrarVisorDocNoInside(visor, $el.closest('.js-arbol-lista-item'), idComunicacion);
}

function descargarExpedienteEniNoInside() {
    $("#descargarExpedienteEniNoInsideForm").submit();
}

function descargarExpedienteCompletoFisicoNoInside() {
    descargarConVelo("#descargarExpedienteEniCompletoFisicoNoInsideForm", 20000);
}

function descargarExpedienteCompletoNoInside() {
    descargarConVelo("#descargarExpedienteEniCompletoNoInsideForm", 20000);
}

function mostrarVisorDocNoInside(visor, item, idComunicacion) {

    var arbol = $('#js-arbol');
    var panel = visor.siblings('#js-gallery-visor');
    $('#expedienteVeil').removeAttr('style').removeClass('hidden');
    enviarAjaxNoInside(arbol.data('url_elemento_ver'), {
        idDocumento : item.data('id'),
        idComunicacion : idComunicacion,
        hidden : false
    }, function(data) {

        visor.removeClass('hidden').data('id_mostrado', item.attr('id')).width(panel.width()).height(panel.height())
                .offset(panel.offset());

        $("#descargarDocNoInsideForm #identificador").val(data.identificador);
        $("#descargarDocNoInsideForm #idComunicacion").val(data.idComunicacion);
        $("#descargarContenidoDocNoInsideForm #identificador").val(data.identificador);
        $("#descargarContenidoDocNoInsideForm #idComunicacion").val(data.idComunicacion);
        visor.find('.js-gallery-ver--lista-datos-id').html(data.identificador);
        visor.find('.js-gallery-ver--lista-datos-estado').html(data.desEstado);
        visor.find('.js-gallery-ver--lista-datos-organos').html(data.organos.join(', '));
        visor.find('.js-gallery-ver--visor').attr('src', 'data:application/pdf;base64,' + data.contenidoVisualizar);

        $('#expedienteVeil').addClass('hidden');
        $('#expedienteForm').on('submit', function(e) {
            e.preventDefault();
        });
        $('#js-gallery-ver').on('click', '.js-gallery-ver--lista-cancel', function(e) {
            var $el = $(e.currentTarget);
            $el.closest('#js-gallery-ver').addClass('hidden');
        })
    });
}

function enviarAjaxNoInside(url, par, callback, callbackError) {
    $.extend(par, {
        expedientEni : $("#contenidoExp").val(),
        idExpediente : $("#expedienteForm #identificador").val(),
        visualizar : $("#verContenidoForm #visualizar").val()
    });

    $.ajax({
        url : $("#context").val() + url,
        type : 'POST',
        dataType : 'json',
        timeout : 999999,
        data : par,
        success : function(data) {
            if (data.mensajeUsuario != null) {
                deleteLevelMessageStyles();

                var tipo = data.mensajeUsuario.level;
                var mensaje = data.mensajeUsuario.message;
                if (tipo != "" && mensaje != "") {
                    $("#divMessage").removeClass("dsp_n");
                    if (tipo == "1") {
                        $("#divMessage").addClass("msg-success mf-msg__success");
                    } else if (tipo == "2") {
                        $("#divMessage").addClass("msg-info mf-msg__info");
                    } else if (tipo == "3") {
                        $("#divMessage").addClass("msg-warning mf-msg__warning");
                    } else {
                        $("#divMessage").addClass("msg-error mf-msg__error");
                    }
                    $("#mensajeMostrar").text(mensaje);
                }
                callbackError && callbackError(data);
            } else {
                $("#visualizar").val(data.visualizar);

                if (data.expedienteEniConvert != null) {
                    $("#contenidoExp").val(data.expedienteEniConvert);
                }

                if (par.hidden) {
                    $("#actionButtons").hide();
                }
                callback && callback(data);
            }
        },
        error : function(e) {
            console.log(e);
            timerModal.off(parent);
        }
    });

    $('#expedienteVeil').addClass('hidden');
}
