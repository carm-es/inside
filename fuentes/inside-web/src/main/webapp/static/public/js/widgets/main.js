var $mf = {};
$mf.fijar_cabecera = function($cabecera, $contenedor, my_id, my_class, is_table, has_lat) {
    $contenedor.children().css('position', 'absolute');
    var hh = $cabecera.height() - $cabecera.child('.mf-header--container').child('.mf-header--content').height();
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