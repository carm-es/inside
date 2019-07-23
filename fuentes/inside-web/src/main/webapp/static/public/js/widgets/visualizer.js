$mf.Visualizer = function($el) {
    var jumpfactor = 0.1;
    var ampliar = function(e) {
        e.preventDefault();
        var data = $(e.currentTarget).closest('.mf-visualizer--container').data();
        data.data = $(e.currentTarget).closest('.mf-visualizer--container').find('.org-chart-wrapper').clone(true);
        data.buttons = add_buttons($(e.currentTarget).closest('.mf-visualizer--container')).children();
        data.close = '(function(){$mf.my_dialog.$el.find(".mf-visualizer--toolbar").remove()})';
        data.visualizer = true;
        data.$reference = $(e.currentTarget).parent().siblings();
        $.extend(data, {
            position_v : 'top',
            position_h : 'left'
        })
        $mf.my_dialog.appendDialog(data, false, true);
        data.buttons.parent().attr({
            'data-zoom_new' : $(e.currentTarget).parent().attr('data-zoom_new'),
            'data-zoom' : $(e.currentTarget).parent().attr('data-zoom')
        }).find('.mf-visualizer--tool__ampliar').remove();
    }, zoom_in = function(e) {
        var $buttons = $(e.currentTarget).parent(), $container = $buttons.closest(
                '.mf-visualizer--container, .mf-dialog.mf-visualizer').find('.org-chart-wrapper').children(), zoom_min = Number($buttons
                .attr('data-zoom')), zoom_new = $buttons.attr('data-zoom_new');
        zoom_new = zoom_new ? Number(zoom_new) : zoom_min;
        zoom_new = zoom_new + jumpfactor < 5 ? zoom_new + jumpfactor : 5;
        $buttons.attr('data-zoom_new', zoom_new);
        $container.css({
            'transform' : 'scale(' + zoom_new + ')',
            '-webkit-transform' : 'scale(' + zoom_new + ')',
            '-moz-transform' : 'scale(' + zoom_new + ')',
            '-webkit-transform-origin' : '0 0',
            '-moz-transform-origin' : '0 0'
        });
    }, zoom_out = function(e) {
        var $buttons = $(e.currentTarget).parent(), $container = $buttons.closest(
                '.mf-visualizer--container, .mf-dialog.mf-visualizer').find('.org-chart-wrapper').children(), zoom_min = Number($buttons
                .attr('data-zoom')), zoom_new = $buttons.attr('data-zoom_new');
        zoom_new = zoom_new ? Number(zoom_new) : zoom_min;
        zoom_new = zoom_new - jumpfactor > zoom_min ? zoom_new - jumpfactor : zoom_min;
        $buttons.attr('data-zoom_new', zoom_new);
        $container.css({
            'transform' : 'scale(' + zoom_new + ')',
            '-webkit-transform' : 'scale(' + zoom_new + ')',
            '-moz-transform' : 'scale(' + zoom_new + ')',
            '-webkit-transform-origin' : '0 0',
            '-moz-transform-origin' : '0 0'
        });
    }, zoom_zero = function(e) {
        var $buttons = $(e.currentTarget).parent(), $container = $buttons.closest(
                '.mf-visualizer--container, .mf-dialog.mf-visualizer').find('.org-chart-wrapper').children(), zoom_new = $buttons
                .attr('data-zoom');
        if ($buttons.attr('data-zoom_new') != 1) {
            $buttons.attr('data-zoom_new', 1);
            zoom_new = 1
        } else {
            $buttons.attr('data-zoom_new', zoom_new);
        }
        $container.css({
            'transform' : 'scale(' + zoom_new + ')',
            '-webkit-transform' : 'scale(' + zoom_new + ')',
            '-moz-transform' : 'scale(' + zoom_new + ')',
            '-webkit-transform-origin' : '0 0',
            '-moz-transform-origin' : '0 0'
        });
    }, init_zoom = function($buttons, $container) {
        var zoom_size = $container.width() / ($container[0].scrollWidth * 1.1);
        $buttons.attr('data-zoom', zoom_size);
        $container.children().css({
            'transform' : 'scale(' + zoom_size + ')',
            '-webkit-transform' : 'scale(' + zoom_size + ')',
            '-moz-transform' : 'scale(' + zoom_size + ')',
            '-webkit-transform-origin' : '0 0',
            '-moz-transform-origin' : '0 0'
        }).addClass('mf-visualizer--zoom');
    }, add_buttons = function(a) {
        var $el = $(a), $buttons = $('<div class="mf-visualizer--toolbar"></div>'), data = $el.data();
        init_zoom($buttons, $el.children(".org-chart-wrapper"));
        $(
                '<button class="mf-visualizer--tool mf-visualizer--tool__ampliar"><span class="mf-icon mf-icon-max-window"></span> Ampliar ventana</button>')
                .appendTo($buttons).on('click', ampliar);
        $(
                '<button class="mf-visualizer--tool mf-visualizer--tool__zoom_in"><span class="mf-icon mf-icon-search-plus"></span> Acercar</button>')
                .appendTo($buttons).on('click', zoom_in);
        $(
                '<button class="mf-visualizer--tool mf-visualizer--tool__zoom_out"><span class="mf-icon mf-icon-search-minus"></span> Alejar</button>')
                .appendTo($buttons).on('click', zoom_out);
        $(
                '<button class="mf-visualizer--tool mf-visualizer--tool__zoom_zero"><span class="mf-icon mf-icon-search"></span> 1:1</button>')
                .appendTo($buttons).on('click', zoom_zero);
        data.buttons_ext
                && $.each(eval(data.buttons_ext), function(i, a) {
                    $('<button class="mf-visualizer--tool ' + a[0] + '">' + a[1] + '</button>').appendTo($buttons).on(
                            'click', function(e) {
                                eval(a[2])(e)
                            });
                });
        return $buttons;
    };
    if ($el) {
        $el.prepend(add_buttons($el));
    } else {
        $('.mf-visualizer--container').each(function(i, a) {
            $(a).prepend(add_buttons(a));
        });
    }
}
