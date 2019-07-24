$mf.Paginator = function($el) {
    var init_pager = function(key, value) {
        $(value).find('div.pager--results>select').change(go_pager);
        $(value).find('div.pager--page>select').change(go_pager);//.select2();
        $(value).find('button.pager--btn').click(go_pager);
    }, go_pager = function(e) {
        var $el = $(e.currentTarget), data = $el.data(), url = e.currentTarget.href || data.url, $container = $el
                .closest(data.container || 'div[id^=grid_]');
        if (e.type == 'change') {
            url += $el.val();
        }
        $mf.timer.on($container);
        $.get(url).done(function(response) {
            pintar(response, $container);
        });
    }, pintar = function(data, $el) {
        var $data = $(data), id = $data.attr('id');
        $el.replaceWith($data);
        $mf.timer.off();
        $mf.init_widgets($data);
    };
    if ($el) {
        $el.find('.pager').each(init_pager);
    } else {
        $('.pager').each(init_pager);
    }
}
