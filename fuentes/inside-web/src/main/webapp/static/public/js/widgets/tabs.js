$mf.Tabs = function($el) {
    var init = function() {
        var $el = $(this), $list = $el.find('ul.tabs_group'), data = $list.data(), $contents = $el.find('.tab_content'), pintar = function(
                e, $li, href) {
            var $tab = $el.find(href);
            $(e.target).trigger('update_tabs');
            $li.addClass('is-selected').siblings().removeClass('is-selected');
            if ($el.hasClass('mf-tabs__slide')) {
                $el.find('.tabs_container').removeClass('is-hidden');
                $el.find('.tabs_container').children().css({
                    left : -$tab.addClass('is-selected').index() * $el.width()
                });
                $el.find('.tabs_container').css({
                    height : $tab.outerHeight()
                });
            } else {
                $tab.removeClass('is-hidden');
            }
            if (data.empty && !$tab.find('.tr-filter--close-btn').length) {
                $(
                        '<button class="tr-filter--close-btn" type="button"><span class="mf-icon mf-icon-close"></span>cerrar</button>')
                        .appendTo($tab).on('click', close_tabs);
            }
            if (data.hide_out) {
                $('.mf-veil').show().addClass('mf-veil__tabs').on('click', function() {
                    $list.trigger('close_tabs');
                }).on('mouseenter', function() {
                    $list.trigger('close_tabs');
                });
            }
            $list.addClass('is-open');
        }, click_handle = function(e) {
            e.preventDefault();
            var $li = $(e.target).parent(), href = e.target.getAttribute('href');
            if ($el.hasClass('mf-tabs__slide')) {
                $contents.removeClass('is-selected');
            } else {
                $contents.addClass('is-hidden');
            }
            if (data.empty && $li.hasClass('is-selected')) {
                close_tabs();
            } else {
                if (href.indexOf('#') != 0) {
                    window.location.href = href;
                } else {
                    pintar(e, $li, href);
                }
            }
        }, hover_handle = function(e) {
            e.preventDefault();
            var $li = $(e.target).parent(), href = e.currentTarget.getAttribute('href');
            if ($el.hasClass('mf-tabs__slide')) {
                $contents.removeClass('is-selected');
            } else {
                $contents.addClass('is-hidden');
            }
            if (data.empty && !$li.hasClass('is-selected')) {
                if (href.indexOf('#') != 0) {
                    href = e.currentTarget.getAttribute('data-content');
                }
                if (href && href.indexOf('#') == 0) {
                    var aux = setTimeout(function() {
                        pintar(e, $li, href);
                    }, 200);
                    $el.one('mouseleave', function() {
                        clearTimeout(aux);
                    });
                }
            }
        }, click_over_handle = function(e) {
            e.preventDefault();
            var href = e.currentTarget.getAttribute('href');
            if (href.indexOf('#') != 0) {
                window.location.href = href;
            }
        }, close_tabs = function() {
            $list.removeClass('is-open').find('.is-selected').removeClass('is-selected');
            if ($el.hasClass('mf-tabs__slide')) {
                $el.find('.tabs_container').addClass('is-hidden');
            } else {
                $contents.addClass('is-hidden');
            }
            if (data.hide_out) {
                $('.mf-veil').hide().removeClass('mf-veil__tabs').off('click');
            }
        }
        $selected = $list.find('.is-selected');
        if ($el.hasClass('mf-tabs__hover')) {
            $list.on('mouseenter', 'li a', hover_handle).on('click', 'li a', click_over_handle);
        } else {
            $list.on('click', 'li a', click_handle);
        }
        $list.on('close_tabs', close_tabs);
        if (!$el.hasClass('mf-tabs__slide')) {
            $contents.addClass('is-hidden');
        } else {
            $contents.each(function(i, a) {
                $(a).css({
                    width : $el.width()
                });
            });
            $el.find('.tabs_container').children().css({
                width : $el.width() * $contents.length
            });

        }
        if ($selected.length) {
            $selected.find('a').trigger('click');
        } else if (!data.empty) {
            $list.find('li:first a').trigger('click');
        }
    };

    if (!$el) {
        $('.tabs').each(init);
    } else if ($el.is('.tabs')) {
        init.call($el);
    } else {
        $el.find('.tabs').each(init);
    }

}
