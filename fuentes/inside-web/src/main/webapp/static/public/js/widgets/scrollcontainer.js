$mf.Marquesina = function(event, $el) {
    var init = function($obj) {
        if ($obj.find('.mf-scroll--replace-obj')) {
            var $obj_replace = $obj.find('.mf-scroll--replace-obj'), $new_obj = $(template), $list = $new_obj
                    .find('.mf-scroll-list');
            $obj_replace.find('option').each(function(k, val) {
                $list.append('<li class="mf-scroll-list--item"><a href="' + val.value + '">' + val.text + '</a></li>');
            })
            $obj_replace.replaceWith($new_obj);
        }
        if (calcular($obj))
            init_botones($obj);
    }, init_botones = function($obj) {
        var $boton = $obj.find('.js-nav-btn-prev, .js-nav-btn-next'), $boton_express = $obj
                .find('.js-nav-btn-first, .js-nav-btn-last'), $boton_centrar = $obj.find('.js-nav-btn-active');
        if (_.isUndefined(event)) {
            $boton.on('mouseenter', function(e) {
                var px = calcular($obj)
                if (px) {
                    var movimiento = $(e.currentTarget).hasClass('js-nav-btn-next') ? px[0] : px[1];
                    mover($obj.find('.mf-scroll-list'), movimiento);
                }
            }).on('mouseleave', function(e) {
                $(e.currentTarget).parent().find('.mf-scroll-list').stop();
            });
        } else if (event == 'click') {
            $boton_express.on('click', function(e) {
                var px = calcular($obj);
                if (px) {
                    var movimiento = $(e.currentTarget).hasClass('js-nav-btn-last') ? px[0] : px[1];
                    mover($obj.find('.mf-scroll-list'), movimiento);
                }
            });
            $boton_centrar.on('click', function(e) {
                var min_max = calcular($obj), px = min_max[1] - $obj.find('li.active').position().left
                        + $obj.find('.mf-scroll--wrapper').width() / 8;
                if (px) {
                    var movimiento = min_max[0] > px ? min_max[0] : min_max[1] < px ? min_max[1] : px;
                    mover($obj.find('.mf-scroll-list'), movimiento);
                }
            });
            $boton.on('click', function(e) {
                var min_max = calcular($obj), wrapper = $obj.find('.mf-scroll--wrapper').width();
                px = $(e.currentTarget).hasClass('js-nav-btn-next') ? -wrapper : wrapper;
                px = min_max[0] > px ? min_max[0] : min_max[1] < px ? min_max[1] : px;
                if (px) {
                    mover($obj.find('.mf-scroll-list'), px);
                }
            });
        }
    }, calcular = function($obj) {
        var $data = $obj.find('.mf-scroll-list'), $parent = $obj.hasClass('mf-scroll--wrapper') ? $obj : $obj
                .find('.mf-scroll--wrapper');
        if ($parent.width() - $data.width() < 0) {
            $obj.addClass('is-scrolling');
            var margen = $parent.width() - $data.width(), position = $data[0].style.left != "" ? $data[0].style.left
                    .substring(0, $data[0].style.left.length - 2) : 0, mov_left = margen
                    - (position != "" ? position : 0), mov_right = Math.abs(position) > 0 ? 0 - position : 0;
            return [ mov_left, mov_right ]
        } else {
            return false;
        }
    }, mover = function($data, px) {
        $data.stop().animate({
            'left' : '+=' + px + 'px'
        }, 2000 - 1000 / Math.abs(px));
    }, template = '<div class="mf-scroll--container"><div class="mf-scroll--wrapper"><ul class="mf-scroll-list">'
            + '</ul></div><a href="#!" class="mf-scroll-nav--btn nav-btn-prev js-nav-btn-prev">Anterior</a>'
            + '<a href="#!" class="mf-scroll-nav--btn nav-btn-next js-nav-btn-next">Siguiente</a></div>';
    this.refresh = function($obj, $select_opt, prev_px) {
        var px = -($select_opt.position().left) + 5, prev_px = prev_px != undefined ? prev_px : 0;
        //        px = min_max[0]> px ? min_max[0] : min_max[1] < px ? min_max[1] : px;
        //        prev_px = min_max[0]> prev_px ? min_max[0] : min_max[1] < prev_px ? min_max[1] : prev_px;
        init_botones($obj);
        $obj.find('.mf-scroll-list').css('left', prev_px + 'px');
        //mover($obj.find('.mf-scroll-list'),px-prev_px);
    };

    if (!$el) {
        $('.mf-scroll').each(function(i, a) {
            init($(a));
        });
    } else if ($el.is('.mf-scroll')) {
        init($el);
    } else {
        $el.find('.mf-scroll').each(function(i, a) {
            init($(a));
        });
    }
}
