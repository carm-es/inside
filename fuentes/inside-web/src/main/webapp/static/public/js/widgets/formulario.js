$.fn.getLabel = function(return_object) {
    if (this[0].id) {
        if (return_object) {
            return $('label[for="' + this[0].id + '"]');
        } else {
            return $.trim($('label[for="' + this[0].id + '"]').text());
        }
    }
    return '';
};
$mf.Formulario = function($el) {
    var init = function($el) {
        init_del($el);
        init_add($el);
        $el.find('ul').attr('data-index', $el.find('ul').children().length);
        var data = $el.find('ul').data();
        if (data && data.max_number && $el.find('ul').children().length >= data.max_number) {
            $el.find('a.collection-add').hide();
        }
    }, init_del = function($el) {
        $el.find('a.collection-delete').off('click', sup).on('click', sup);
    }, init_add = function($el) {
        $el.find('a.collection-add').off('click', add).on('click', add);
    }, sup = function(event) {
        event.preventDefault();
        var $ul = $(event.currentTarget).closest('ul'), data = $ul.data(), li = event.currentTarget.parentElement;
        if (li.id != '') {
            $mf.timer.on();
            $.get(event.currentTarget.getAttribute('href')).done(function(response) {
                var res = JSON.parse(response);
                if (res.succesful) {
                    li.parentNode.removeChild(li);
                    if ($(event.currentTarget).hasClass('js-refresh')) {
                        location.reload(true);
                    } else {
                        console.log(res.succesful);
                        $ul.siblings('a.collection-add').show();
                        $mf.timer.off();
                    }
                } else {
                    alert(res.error);
                }
            }).fail(function(response) {
                alert(response.statusText);
                $mf.timer.off();
            });
        } else {
            li.parentNode.removeChild(li);
            $ul.siblings('a.collection-add').show();
        }
    }, add = function(event) {
        event.preventDefault();
        var $ul = $(event.currentTarget).siblings('ul'), data = $ul.data(), prototype = data.prototype, index = parseInt($ul
                .attr('data-index')), regexp = new RegExp('__' + (data.prototype_name || 'name') + '__', "g"), $newForm = $(prototype
                .replace(regexp, index));
        init($newForm.appendTo($ul));
        $mf.init_widgets($newForm);
        $ul.attr('data-index', index + 1);
        if (data && data.max_number && $ul.children().length >= data.max_number) {
            $(event.currentTarget).hide();
        }
    };
    ($el && $el.find('input') || $('input')).one('focus', function(e) {
        $(e.target).one('keyup', function(e) {
            var $el = $(e.target);
            if (e.target.value && $el.getLabel(true)) {
                $el.getLabel(true).removeClass('is-hidden').addClass('is-show');
                $el.closest('.fld').addClass('is-edited');
            }
        });
    });
    ($el && $el.find('[type=password]') || $('[type=password]')).one('focus', function(e) {
        $(e.target).wrap('<span class="mf-showpass"></span>').addClass('mf-showpass--input').focus();
        $('<button class="mf-showpass--btn">Mostrar contraseña</button>').insertAfter(e.target).on('click',
                function(e) {
                    e.preventDefault();
                    var $button = $(e.target)
                    $el = $button.prev();
                    if ($button.hasClass('show_password')) {
                        $el.attr('type', 'text').focus();
                        $button.removeClass('show_password').addClass('hide_password');

                    } else {
                        $el.attr('type', 'password').focus();
                        $button.removeClass('hide_password').addClass('show_password');
                    }
                });
    });
    ($el && ($el.is('.ajax_submit') && $el || $el.find('.ajax_submit')) || $('.ajax_submit')).on('submit', function(e) {
        e.preventDefault();
        var $form = $(e.currentTarget), $container = ($el && $el.find($form.data('selector_replace')) || $($form
                .data('selector_replace')));
        $container.trigger('start_refresh');
        $mf.timer.on($container);
        $[$form[0].method]($form[0].action, $form.serializeArray()).done(function(data) {
            $container.replaceWith($(data).addClass('content_replaced'));
            $('.content_replaced').trigger('end_refresh');
        }).fail(function(data) {
            console.log('Ha fallado submit_ajax:');
            console.log(data);
        }).always(function() {
            $mf.timer.off($container);
        });
    });
    ($el && ($el.is('.auto_submit') && $el || $el.find('.auto_submit')) || $('.auto_submit')).on('change', function(e) {
        $(e.currentTarget).is('form') && $(e.currentTarget).submit() || $(e.currentTarget).closest('form').submit();
    });
    if (!Modernizr.inputtypes['date']) {
        var transform = function(date) {
            var pattern = /(\d{4})\-(\d{2})\-(\d{2})/;
            return date.replace(pattern, '$3-$2-$1');
        };
        ($el && $el.find('[type=date].mf-datepicker') || $('[type=date].mf-datepicker')).each(function(i, a) {
            a.value = transform(a.value);
        });
    }
    ($el && ($el.is('form') || $el.find('form')) || $('form')).each(function(i, a) {
        $(a).on('reset', function(e) {
            $(a).find('[data-autocomplete]').each(function(i, a) {
                $(a).select2('val', '').removeClass('is-selected').siblings('div').removeClass('is-selected');
                $(a).parent().find('.select2-search-field').show()
                var name = a.name, siblings = $(a).siblings('input');
                siblings.each(function(k, v) {
                    $(v).remove();
                });
                if (name.substr(name.length - 2, name.length - 1) == '[]') {
                    a.name = name.substr(0, name.length - 2);
                }
            });
        });
    });
    if (!$el) {
        $('.morfos-collection').each(function(i, a) {
            init($(a));
        });
    } else {
        init($el);
    }
};
