$mf.TopBar = function($el) {
    if (typeof ($mf.my_dialog) == 'undefined')
        $mf.my_dialog = new $mf.Dialog($("div.mf-dialog").empty());
    var mostrar_todos = function(ind, el) {
        $(el).click(function(event) {
            event.preventDefault();
            $a = $(el).children('a');
            $mf.my_dialog.appendDialog({
                'url' : $a.attr('href'),
                'title' : $a.html(),
                'position_v' : 'center',
                'position_h' : 'center',
                'init_dialog' : function() {
                    $mf.DataGrid($el);
                    $mf.Paginator($el);
                }
            }, false, true);
        });
    }, init = function($el) {
        $el.find('.show_all').each(mostrar_todos);
        var $selectProfile = $el.find('.first>a>span');
        $selectProfile.each(function(i, a) {
            var profileText = $.trim($(a).text());
            $(a).text(
                    profileText.length > 25 ? profileText.substr(0, 10) + '... ...'
                            + profileText.substr(profileText.length - 10, 10) : profileText);
        });
    };

    if (!$el) {
        $('#userMenu').each(function(i, a) {
            init($(a));
        });
    } else if ($el.is('#userMenu')) {
        init($el);
    } else {
        $el.find('#userMenu').each(function(i, a) {
            init($(a));
        });
    }
}
