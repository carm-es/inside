$(document).ready(function() {

    tableUsuariosUnidadOrganica = $('#userList').dataTable({
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
            "width" : "10%"
        }, {
            "bSortable" : false,
            "width" : "40%"
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

function comprobarExisteNifInside() {
    var nifABuscar = $("#altaUsuarioWebInsideForm #nif").val();
    $.ajax({
        url : $("#context").val() + '/altaUsuarioWebInsidePost/altaUsuarioExiteNif',
        type : 'POST',
        dataType : 'json',
        timeout : 999999,
        data : {
            "nif" : nifABuscar,

        },
        success : function(data) {
            if (data.existeUsuario != null && data.existeUsuario == true) {
                //mostrarDialogIdentificadorDocumentoGenerarDocInside();
                mostrarDialogAltaUsuario();

            } else {
                darAltaUsuario();
            }

        },
        error : function(xhr) {
            $('#documentVeil').addClass('hidden');
            console.error(xhr.responseText);
        }
    });
}

function mostrarDialogAltaUsuario(mensaje, nuevo) {
    var divData = $('#init-modal-alta-usuario');
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
        darAltaUsuario();
        $mf.my_dialog.close_dialog();
    });

    buttons.find('.js-documento-cancelar').on('click', function(e) {
        e.preventDefault();
        $mf.my_dialog.close_dialog();
    });

    data.buttons = buttons;
    data.data = divData.html();

    $mf.my_dialog.appendDialog(data, true, true);

}

function darAltaUsuario() {
    $("#altaUsuarioWebInsideForm").submit();
}

function showDialogDelete(user, numeroProcedimiento) {
    var divData = $('#init-modal-borrar-usuario');
    var data = divData.data();

    var buttons = createButtonsDialog([ {
        clase : 'js-documento-cancelar',
        texto : 'Cancelar'
    }, {
        clase : 'js-documento-borrar',
        texto : 'Confirmar'
    } ]);

    buttons.find('.js-documento-borrar').on('click', function(e) {
        borrarUsuario(user, numeroProcedimiento);
        $mf.my_dialog.close_dialog();
    });

    buttons.find('.js-documento-cancelar').on('click', function(e) {
        $mf.my_dialog.close_dialog();
    });

    data.buttons = buttons;
    data.data = divData.html();
    $mf.my_dialog.appendDialog(data, true, true);
}

function borrarUsuario(user, numeroProcedimiento) {
    if (user != "") {
        $("#borrarUsuarioForm #nif").val(user);
    }

    if (numeroProcedimiento != null) {
        $("#borrarUsuarioForm #numeroProcedimiento").val(numeroProcedimiento);
    }

    $("#borrarUsuarioForm").submit();
}

function showDialogEdit(user, numeroProcedimiento, rol) {
    if (user != "") {
        $("#editUsuarioForm #nif").val(user);
    }

    if (numeroProcedimiento != null) {
        $("#editUsuarioForm #numeroProcedimiento").val(numeroProcedimiento);
    }

    if (rol != null) {
        $("#editUsuarioForm #rol").val(rol);
    }

    $("#editUsuarioForm").submit();
}

function modificarUsuario() {
    $("#modificarUsuarioWebInsideForm").submit();
}
