$mf.ConfNav = function($el) {
    var init = function(i, a) {
        $(a)
                .click(
                        function(e) {
                            e.preventDefault();
                            var data = $(e.currentTarget).data();
                            if (!$(this).is("input") || this.checked) {
                                this.checked = false;
                                data.buttons = [
                                        $(
                                                '<button '
                                                        + (data.cancel_clazz != undefined ? ('class="'
                                                                + data.cancel_clazz + '"') : '')
                                                        + '>'
                                                        + (data.cancel_label != undefined ? data.cancel_label : 'Cancelar')
                                                        + '</button>').click(function() {
                                            $mf.my_dialog.close_dialog();
                                        }),
                                        $(
                                                '<button '
                                                        + (data.accept_clazz != undefined ? ('class="btn-primary '
                                                                + data.accept_clazz + '"') : '')
                                                        + '>'
                                                        + (data.accept_label != undefined ? data.accept_label : 'Aceptar')
                                                        + '</button>').click(function() {
                                            window.location.href = data.action;
                                        }) ];
                                $mf.my_dialog.appendDialog(data, false, true);
                            } else {
                                window.location.href = data.action;
                            }
                        });
    }
    if (!$el) {
        $('.mf-dialog-confirm-nav').each(init);
    } else if ($el.hasClass('mf-dialog-confirm-nav')) {
        this.init(0, $el);
    } else {
        $el.find('.mf-dialog-confirm-nav').each(init);
    }
}
