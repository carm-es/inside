$(document).ready(function() {

    tableExpedientes = $('#busquedaElasticTable').DataTable({
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
            "class" : "details-control",
            "orderable" : false,
            "data" : null,
            "defaultContent" : "",
            "width" : "10%"
        }, {
            "bSortable" : false,
            "width" : "10%"
        }, {
            "bSortable" : false,
            "width" : "80%"
        }, {
            "bSortable" : false,
            "width" : "10%"
        }, {
            "bSortable" : false,
            "bSearchable" : false,
            "width" : "10%",
            "data" : "Metadatos"
        }, {
            "bSortable" : false,
            "width" : "10%"

        }, {
            "bSortable" : false,
            "bSearchable" : false,
            "width" : "10%",
            "data" : "MetadatosAdicionales"
        } ],
        "paging" : true
    });

    $('input[type=search]').parent().parent().css('width', '340px');
    $('input[type=search]').css('width', '320px');

    // Array to track the ids of the details displayed rows
    var detailRows = [];

    $('#busquedaElasticTable tbody').on('click', 'tr td.details-control', function() {
        var tr = $(this).closest('tr');
        var row = tableExpedientes.row(tr);
        var idx = $.inArray(tr.attr('id'), detailRows);

        if (row.child.isShown()) {
            tr.removeClass('details');
            row.child.hide();

            // Remove from the 'open' array
            detailRows.splice(idx, 1);
        } else {
            tr.addClass('details');
            row.child(format(row.data())).show();

            // Add to the 'open' array
            if (idx === -1) {
                detailRows.push(tr.attr('id'));
            }
        }
    });

    // On each draw, loop over the `detailRows` array and show any child rows
    tableExpedientes.on('draw', function() {
        $.each(detailRows, function(i, id) {
            $('#' + id + ' tableExpedientes.details-control').trigger('click');
        });
    });

});

function format(d) {

    var $metadatos = JSON.parse(d['Metadatos']);
    var $metadatosAdicionales = JSON.parse(d['MetadatosAdicionales']);

    var html = '';

    $.each($metadatos, function(key, value) {
        html += '<p><b>' + key + '</b> : ' + value + '</p>';
    });

    $.each($metadatosAdicionales, function(key, value) {
        html += '<p><b>' + key + '</b> : ' + value + '</p>';
    });

    return html;
}
