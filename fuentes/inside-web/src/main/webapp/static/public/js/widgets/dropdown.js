$mf.dropdown = function($el) {
    var cargar = function(i, a) {
        var $el = $(a);
        $el.removeClass('mf-dropdown__css');
        var $button = $el.find('.mf-dropdown--btn'), $list = $el.find('.mf-dropdown--content');
        $button.click(function(e) {
            e.preventDefault();
            $el.toggleClass('is-open');
        });
        $list.children('li').add($list.children('ul').children('li')).click(
                function(e) {
                    if (!$button.hasClass('no_change_title'))
                        $button.find('.mf-dropdown--title').length ? $button.find('.mf-dropdown--title').text(
                                $(this).text()) : $button.text($(this).text());
                    if (!$button.hasClass('no_change_title'))
                        $el.removeClass('is-open');
                });
    };
    if ($el) {
        $el.find('.mf-dropdown--container').each(cargar)
    } else {
        $('.mf-dropdown--container').each(cargar);
    }
};
