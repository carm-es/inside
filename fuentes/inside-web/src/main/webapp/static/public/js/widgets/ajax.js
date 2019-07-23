$mf.AjaxModal = function($el) {
    var init = function($value) {
        var abrir_dialog = function(data) {
            data.url = data.url ? data.url : $value[0].href;
            if (!$value.hasClass('new_dialog')) {
                if ($value.closest('.mf-dialog--content').length) {
                    $mf.my_dialog.refreshDialog(data);
                } else if (!data.only_in_dialog) {
                    data.init_dialog = data.init_dialog ? data.init_dialog : $mf.init_widgets;
                    $mf.my_dialog.appendDialog(data, data.draggable, data.modal);
                } else {
                    window.location.href = data.url;
                }
            } else {
                var $aux_dialog = new $mf.Dialog($('<div class="mf-dialog mf-aux_dialog">').appendTo('body').hide());
                data.init_dialog = data.init_dialog ? data.init_dialog : $mf.init_widgets;
                $aux_dialog.appendDialog(data, data.draggable, data.modal);
            }
        }
        if ($value[0].type && $value[0].type == 'submit') {
            $value.closest('form').on('submit', function(e) {
                e.preventDefault();
                var data = $.extend({}, $(e.currentTarget).data());
                data.url = data.url ? data.url : $value.closest('form')[0].action;
                data.data_url = data.data_url ? data.data_url : $value.closest('form').serializeArray();
                abrir_dialog(data);
            });
        } else {
            $value.on('click', function(e) {
                e.preventDefault();
                var data = $.extend({}, $(e.currentTarget).data());
                abrir_dialog(data);
            });
        }
    }
    if (!$el) {
        $('.open_in_dialog').each(function(i, a) {
            init($(a));
        });
    } else if ($el.is('.open_in_dialog')) {
        init($el);
    } else {
        $el.find('.open_in_dialog').each(function(i, a) {
            init($(a));
        });
    }
}
