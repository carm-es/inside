var $mf = {};
$mf.fijar_cabecera = function($cabecera, $contenedor, my_id, my_class, is_table, has_lat) {
    $contenedor.children().css('position', 'absolute');
    var hh = $cabecera.height();
    function masScroll_h() {
        $cabecera.one('headmasscroll_h', function(e) {
            $(e.currentTarget).wrap('<div id="' + my_id + 'h" style="height:' + hh + 'px"></div>').addClass(my_class);
            menosScroll_h();
        });
    }
    function menosScroll_h() {
        $cabecera.one('headmenosscroll_h', function(e) {
            $(e.currentTarget).unwrap();
            $(e.currentTarget).removeClass(my_class);
            masScroll_h();
        });
    }
    if (!$cabecera.hasClass('not-fixed')) {
        masScroll_h();
        $contenedor.scroll(function() {
            if ($contenedor.scrollTop() > hh) {
                $cabecera.trigger('headmasscroll_h');
            }
            if ($contenedor.scrollTop() < hh) {
                $cabecera.trigger('headmenosscroll_h');
            }
        });
    }
}
$(document).ready(
        function() {
            $(document).click(function() {
                $('.dropdownbtn-menu').removeClass('is-open').addClass('visuallyHidden');
            });

            $('.multiDropdown, .dropdownbtn-btn').each(
                    function(key, value) {
                        $(value).on(
                                'click',
                                function(e) {
                                    e.preventDefault();
                                    $(e.currentTarget).parent().siblings('.dropdownbtn-menu').removeClass(
                                            'visuallyHidden').addClass('is-open');
                                    e.stopImmediatePropagation();
                                });
                    });

            // Mensajes de alerta con botón ocultar

            var $closebtn = '<a href="#!" class="mf-msg--closebtn" title="Ocultar mensaje">Ocultar</a>';

            $($closebtn).prependTo($('[class^="msg-"].is-hidable')).on('click', function() {
                $(this).parent().hide();
            });

            // Cabecera compacta al hacer scroll
            if (!$('#header').data('scroll_disable')) {
                $mf.fijar_cabecera($('#header'), $(window), "mf-header--wrapper", "mf-header__compact", false);
            }
        });

function loadUnidadesOrganicas(identificadorUsuario, context) {
    var divData = $('#init-modal-lista-unidades');
    var data = divData.data();
    var buttons = createButtonsDialog([ {
        clase : 'js-unidades-cancelar',
        texto : 'Cancelar'
    }, {
        clase : 'js-unidades-actualizar',
        texto : 'Actualizar',
        submit : true
    } ]);

    buttons.find('.js-unidades-actualizar').on('click', function(e) {
        e.preventDefault();
        actualizarUnidad();
        $mf.my_dialog.close_dialog();
    });

    buttons.find('.js-unidades-cancelar').on('click', function(e) {
        e.preventDefault();
        $mf.my_dialog.close_dialog();
    });

    $.ajax({
        url : context + "/getUnidadesOrganicas",
        dataType : 'json',
        type : 'POST',
        data : {
            "identificadorUsuario" : identificadorUsuario
        },
        success : function(data) {
            var unidades = data.unidades;
            for (var i = 0; i < unidades.length; i++) {
                var checked = unidades[i].activo ? 'checked' : '';
                var value = unidades[i].codigo;
                var label = unidades[i].codigo + ' - ' + unidades[i].nombre;
                if (unidades[i].numeroProcedimiento) {
                    value = value + '-' + unidades[i].numeroProcedimiento;
                    label = label + ' - Procedimiento: ' + unidades[i].numeroProcedimiento;
                }
                var row = '<li><input type="radio" name="unidades" value="' + value + '" id="' + label + '" ' + checked
                        + ' />&nbsp;' + '<label for="firma">' + label + '</label></li>';
                $('.mf-dialog #lista_unidades').append(row);
            }
        },
        error : function(e) {
            console.error(e);
        }
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

function actualizarUnidad() {
    var codigoUnidad = $('[name="unidades"]:checked').val();
    $('#unidadProcedimiento').val(codigoUnidad);
    $("#updateUnidadActivaForm").submit();
}