$mf.timer = {
    on : function($el) {
        $el = $el == undefined ? $('body') : $el;
        var hash = Math.random(), $veil = $el.find('.mf-veil.timer-veil');
        if ($veil.length) {
            var data = $veil.data('hash');
            if (data) {
                data = eval(data);
                data.push(hash);
            } else {
                data = [ hash ];
            }
            $veil.data('hash', JSON.stringify(data));
        } else {
            $('<div class="mf-veil timer-veil"><p><span></span>Cargando...</p></div>').appendTo($el.css({
                position : 'relative'
            })).css({
                position : 'absolute'
            }).data('hash', JSON.stringify([ hash ]));
        }
        return hash
    },
    off : function($el, hash) {
        $el = $el == undefined ? $('body') : $el;
        var $veil = $el.find('.mf-veil.timer-veil');
        if (hash) {
            var data = $veil.data('hash');
            if (data) {
                data = eval(data);
                data.splice(data.indexOf(hash), 1);
                if (data.length > 0) {
                    $veil.data('hash', JSON.stringify(data));
                } else {
                    $el.find('.timer-veil:first').remove();
                }
            }
        } else {
            $el.find('.timer-veil:first').remove();
        }
    }
};
