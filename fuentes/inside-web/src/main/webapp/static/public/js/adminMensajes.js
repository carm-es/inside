var tableMessages;

$(document).ready(function() {

    tableMessages = $('#mensajesTable').dataTable({
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
        "dom" : '<"top fld dsp_ib" f><"fld dsp_ib" l>t<"bottom" ir><"clear">',
        "bSort" : false,
        "aoColumns" : [ {
            "bSortable" : false,
            "width" : "70%"
        }, {
            "bSortable" : false,
            "width" : "5%"
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

});

function showDialogCreate() {
    var divData = $('#init-modal-add-message');
    var data = divData.data();
    var buttons = createButtonsDialog([ {
        clase : 'js-documento-cancelar',
        texto : 'Cancelar'
    }, {
        clase : 'js-documento-crear',
        texto : 'Guardar',
        submit : true
    } ]);

    buttons.find('.js-documento-crear').on('click', function(e) {
        e.preventDefault();
        createMessage();
    });

    buttons.find('.js-documento-cancelar').on('click', function(e) {
        e.preventDefault();
        $mf.my_dialog.close_dialog();
    });

    data.buttons = buttons;
    data.data = divData.html();

    $mf.my_dialog.appendDialog(data, true, true);
}

function createMessage() {
    var texto = $('.mf-dialog #texto').val();

    $("#addMessageForm #texto").val(texto);
    $("#addMessageForm").submit();

    $mf.my_dialog.close_dialog();
}

function showDialogEdit(identificador, texto) {
    var divData = $('#init-modal-add-message');
    var data = divData.data();
    var buttons = createButtonsDialog([ {
        clase : 'js-documento-cancelar',
        texto : 'Cancelar'
    }, {
        clase : 'js-documento-crear',
        texto : 'Guardar',
        submit : true
    } ]);

    buttons.find('.js-documento-crear').on('click', function(e) {
        e.preventDefault();
        editMessage(identificador);
    });

    buttons.find('.js-documento-cancelar').on('click', function(e) {
        e.preventDefault();
        $mf.my_dialog.close_dialog();
    });

    data.buttons = buttons;
    data.data = divData.html();

    $mf.my_dialog.appendDialog(data, true, true);

    var textHtml = texto.replace("\n", "<br/>", "g");
    console.log(texto);
    console.log(textHtml);
    $('.mf-dialog #texto').html(textHtml);
}

function editMessage(identificador) {
    var texto = $('.mf-dialog #texto').val();

    $("#editMessageForm #texto").val(texto);
    $("#editMessageForm #identificador").val(identificador);
    $("#editMessageForm").submit();

    $mf.my_dialog.close_dialog();
}

function desactivar(identificador) {
    $("#deactivateForm #identificador").val(identificador);
    $("#deactivateForm").submit();
}

function activar(identificador) {
    $("#activateForm #identificador").val(identificador);
    $("#activateForm").submit();
}

function eliminar(identificador) {
    $("#deleteForm #identificador").val(identificador);
    $("#deleteForm").submit();
}