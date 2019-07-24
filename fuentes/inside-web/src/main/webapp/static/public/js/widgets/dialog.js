$mf.Dialog = function($dialogo, classes, $dialog_parent) {
    var self = this, window_h = $('body').height(), window_w = $('body').width(), head_h = $('.mf-header--container')
            .height(), cargar_datos = function(callback) {
        var fireEventLoad = function(e) {
            if (typeof (e) == 'function') {
                e(self);
            } else if (typeof (e) == 'string') {
                eval(e)(self);
            }
        }.bind(this);
        if (!fireEventLoad(self.datos.onBeforeLoad)) {
            if (self.datos.url != undefined) {
                if (self.datos.cargando) {
                    callback.call(self, self.datos.cargando);
                } else {
                    $mf.timer.on(self.$data);
                }
                $[self.datos.metod || 'post'](self.datos.url, self.datos.data_url).always(function(text) {
                    fireEventLoad(self.datos.onLoad);
                    if ((text.responseText || text).indexOf('<!DOCTYPE html>') == 0) {
                        window.history.pushState({
                            "html" : text.responseText || text
                        }, "", self.datos.url);
                        document.write(text.responseText || text);
                        $mf.init_widgets($(document));
                    } else {
                        callback.call(self, text.responseText || text);
                        if (self.datos.init_dialog != undefined) {
                            if (typeof (self.datos.init_dialog) === 'function') {
                                self.datos.init_dialog.call(self, self.$data, self);
                            } else {
                                eval(self.datos.init_dialog).call(self, self.$data, self);
                            }
                        } else {
                            $mf.init_widgets(self.$data);
                        }
                    }
                    $mf.timer.off(self.$data);
                    fireEventLoad(self.datos.onAfterLoad);
                });
            } else {
                fireEventLoad(self.datos.onLoad);
                callback.call(self, self.datos.data != undefined ? self.datos.data : self.$data);
                if (self.datos.init_dialog != undefined) {
                    eval(self.datos.init_dialog).call(self, self.$data);
                } else {
                    $mf.init_widgets(self.$data);
                }
                fireEventLoad(self.datos.onAfterLoad);
            }
        }
    }, max_height = function(max) {
        var window_max = !self.datos.drag_container ? ($(window).height() - self.$title_bar.outerHeight() - self.$buttons
                .outerHeight()) * 0.8 : $(self.datos.drag_container).outerHeight() * 0.8;
        return max ? max : window_max;
    }, posicionar = function(datos) {
        self.$data.css({
            'max-height' : max_height(datos.max_height),
            'height' : (datos.height ? datos.height : ''),
            'width' : (datos.width ? datos.width : '')
        });
        if (datos.position_v != undefined || datos.position_h != undefined) {
            $mf.position(self.$el, $(datos.aSelect), datos.position_v, datos.position_h, datos.over_v, datos.over_h);
        }
    };
    this.show = false;
    this.datos = {};
    this.resize_content = function() {
        window_h = $('body').height(), window_w = $('body').width(), posicionar(self.datos);
    };
    this.close_dialog = function(e) {
        if (self.datos.close)
            eval(self.datos.close)();
        if (self.datos.visualizer) {
            self.datos.$reference.replaceWith(self.$data.children().clone(true));
        }
        if (self.$el.hasClass('mf-aux_dialog')) {
            self.$el.remove();
        } else {
            self.$el.hide();
        }
        $('.mf-veil').hide();
        $(window).off('resize', this.resize_content);
        self.show = false;
    };
    this.min_dialog = function(e) {
        self.$data.hide();
        self.$buttons.hide();
        self.$min_button.hide();
        self.$max_button.show();
    };
    this.max_dialog = function(e) {
        self.$data.show();
        if (self.$buttons.hasClass('has-buttons'))
            self.$buttons.show();
        self.$min_button.show();
        self.$max_button.hide();
    };
    this.$title_bar = $('<div class="mf-dialog--titlebar"></div>').appendTo($dialogo);
    this.$title = $('<span class="mf-dialog--title"></span>').appendTo(this.$title_bar);
    this.$close_button = $(
            '<button class="mf-dialog--btn" title="Cerrar"><span class="mf-icon mf-icon-cancel"></span> Cerrar</button>')
            .appendTo(this.$title_bar).click(self.close_dialog);
    this.$max_button = $(
            '<button class="mf-dialog--btn" title="Maximizar"><span class="mf-icon mf-icon-max-window"></span> Maximizar</button>')
            .appendTo(this.$title_bar).click(self.max_dialog).hide();
    this.$min_button = $(
            '<button class="mf-dialog--btn" title="Minimizar"><span class="mf-icon mf-icon-min-window"></span> Minimizar</button>')
            .appendTo(this.$title_bar).click(self.min_dialog).hide();
    this.$data = $('<div class="mf-dialog--content"></div>').appendTo($dialogo);
    this.$buttons = $('<div class="mf-dialog--buttonbar"></div>').appendTo($dialogo).hide();
    this.$el = $dialogo;
    //    this.$el = $($dialogo);
    this.$el.addClass(classes != undefined ? classes : '');
    if ($dialog_parent != undefined) {
        this.$el.appendTo($dialog_parent);
    }
    this.appendDialog = function(datos, es_draggable, es_modal) {
        self.show = true;
        self.datos = datos;
        self.es_modal = es_modal;
        var open_dialog = function(texto) {
            if (texto != undefined && (!(texto instanceof jQuery) || !$(texto).hasClass('mf-dialog--content'))) {
                self.$data.html(texto.responseText ? texto.responseText : texto);
            }
            datos.title != undefined ? self.$title.html(datos.title) : self.$title.empty();
            datos.buttons ? self.$buttons.html(datos.buttons).addClass('has-buttons').show() : self.$buttons.empty()
                    .removeClass('has-buttons').hide();
            if (datos.is_min || self.$data.css('display') != 'none') {
                self.$min_button.show();
                self.$max_button.hide();
            }
            self.$el.removeAttr('class').addClass('mf-dialog');
            if (datos.clazz != undefined) {
                self.$el.addClass(datos.clazz);
            }
            self.$el.show();
            if (!datos.max_height) {
                $(window).on('resize', this.resize_content);
            }
            if (es_draggable)
                draggable.call(this);
            if (es_modal)
                modal.call(this);
            if (datos.visualizer) {
                self.$el.addClass('mf-visualizer');
                self.$el.css({
                    'width' : $(window).width(),
                    'height' : $(window).height()
                })
            } else {
                self.$el.removeClass('mf-visualizer');
            }

            if (self.datos.visualizer || self.datos.hide_resize_button)
                self.$min_button.hide();
            if (self.datos.hide_close_button)
                self.$close_button.hide();
            posicionar(datos);
        }
        cargar_datos(open_dialog);
    }
    this.refreshDialog = function(datos) {
        var refresh_dialog = function(texto) {
            if (texto != undefined && (!(texto instanceof jQuery) || !$(texto).hasClass('mf-dialog--content'))) {
                self.$data.html(texto.responseText ? texto.responseText : texto);
            }
        };
        $.extend(self.datos, datos);
        cargar_datos(refresh_dialog);
    }
    function draggable() {
        this.$title_bar
                .on(
                        'mousedown',
                        function(e) {
                            var init_x = e.clientX, init_y = e.clientY, pos_ini_x = $(e.currentTarget).offset().left, pos_ini_y = $(
                                    e.currentTarget).offset().top, width = $dialogo.outerWidth(), height = $dialogo
                                    .outerHeight();
                            $dialogo.addClass('on_move');
                            $('body').addClass('is-no-selectable');
                            if (!self.datos.drag_container) {
                                $('body').on('mousemove', function(e) {
                                    var pos = {};
                                    if (e.clientX - init_x + pos_ini_x <= 0) {
                                        pos.left = 0;
                                    } else if (e.clientX - init_x + pos_ini_x > window_w - width) {
                                        pos.left = window_w - width - 1;
                                    } else {
                                        pos.left = e.clientX - init_x + pos_ini_x;
                                    }
                                    if (!self.es_modal && e.clientY - init_y + pos_ini_y < head_h) {
                                        pos.top = head_h;
                                    } else if (!self.es_modal && e.clientY - init_y + pos_ini_y > window_h - height) {
                                        pos.top = window_h - height - 1;
                                    } else {
                                        pos.top = e.clientY - init_y + pos_ini_y;
                                    }
                                    $dialogo.offset(pos);
                                });
                            } else {
                                $('body')
                                        .on(
                                                'mousemove',
                                                function(e) {
                                                    var pos = {}, $container = $(self.datos.drag_container), pos_container = $container
                                                            .position(), w_container = $container.width(), h_container = $container
                                                            .height();
                                                    if (e.clientX - init_x + pos_ini_x < pos_container.left) {
                                                        pos.left = pos_container.left;
                                                    } else if (e.clientX - init_x + pos_ini_x > pos_container.left
                                                            + w_container - width) {
                                                        pos.left = pos_container.left + w_container - width - 1;
                                                    } else {
                                                        pos.left = e.clientX - init_x + pos_ini_x;
                                                    }
                                                    if (e.clientY - init_y + pos_ini_y < pos_container.top) {
                                                        pos.top = pos_container.top;
                                                    } else if (e.clientY - init_y + pos_ini_y > pos_container.top
                                                            + h_container - height) {
                                                        pos.top = pos_container.top + h_container - height - 1;
                                                    } else {
                                                        pos.top = e.clientY - init_y + pos_ini_y;
                                                    }
                                                    $dialogo.css(pos);

                                                    function getSplitData() {
                                                        if (self.datos.split) {
                                                            if (typeof (self.datos.split) === 'string') {
                                                                self.datos.split = eval(self.datos.split);
                                                                if (typeof (self.datos.split) !== 'object') {
                                                                    self.datos.split = true;
                                                                }
                                                            }
                                                            if (self.datos.split === true) {
                                                                self.datos.split = {};
                                                            }
                                                            if (self.datos.split) {
                                                                self.datos.split.toSplit = self.datos.split.toSplit || 20;
                                                                self.datos.split.shadowColor = self.datos.split.shadowColor
                                                                        || 'red';
                                                                self.datos.split.placesToSplit = self.datos.split.placesToSplit
                                                                        || 'top bottom left right';
                                                                self.datos.split.container = self.datos.split.container
                                                                        || $container;
                                                                if (typeof (self.datos.split.container) === 'string') {
                                                                    self.datos.split.container = $(self.datos.split.container);
                                                                } else if (typeof (self.datos.split.container) === 'undefined') {
                                                                    self.datos.split.container = $container;
                                                                }
                                                                if (typeof (self.datos.split.placesToSplit) === 'string') {
                                                                    self.datos.split.placesToSplit = self.datos.split.placesToSplit
                                                                            .split(' ');
                                                                }
                                                            }
                                                        }
                                                        return self.datos.split;
                                                    }
                                                    var splitData = getSplitData();
                                                    if (splitData) {
                                                        function checkSplit(place) {
                                                            //console.log(place + ': ' + pos.top + ' ' + pos.left);
                                                            return ((place == 'top' && pos.top < self.datos.split.toSplit)
                                                                    || (place == 'left' && pos.left < self.datos.split.toSplit)
                                                                    || (place == 'bottom' && pos.top > $dialogo
                                                                            .innerHeight()
                                                                            - self.datos.split.toSplit) || (place == 'right' && pos.top > $dialogo
                                                                    .innerWidth()
                                                                    - self.datos.split.toSplit));

                                                        }
                                                        function showShadow(place) {
                                                            var textPosition = '';
                                                            if (place == 'top') {
                                                                textPosition = 'left: 0; top: 0; width: ' + w_container
                                                                        + 'px; height: ' + self.datos.split.toSplit
                                                                        + 'px;';
                                                            } else if (place == 'bottom') {
                                                                textPosition = 'left: 0; top: ' + h_container
                                                                        - self.datos.split.toSplit + 'px; width: '
                                                                        + w_container + 'px; height: '
                                                                        + self.datos.split.toSplit + 'px;';
                                                            } else if (place == 'left') {
                                                                textPosition = 'left: 0; top: 0; width: '
                                                                        + self.datos.split.toSplit + 'px; height: '
                                                                        + h_container + 'px;';
                                                            } else if (place == 'right') {
                                                                textPosition = 'left: ' + w_container
                                                                        - self.datos.split.toSplit
                                                                        + 'px; top: 0; width: '
                                                                        + self.datos.split.toSplit + 'px; height: '
                                                                        + w_container + 'px;';
                                                            }

                                                            var shadow = $('<div class="js-dialog_split_shadow" data-place="'
                                                                    + place
                                                                    + '" style="position: absolute; '
                                                                    + 'background-color: '
                                                                    + self.datos.split.shadowColor
                                                                    + ';'
                                                                    + 'opacity: 0.25; ' + textPosition + '">');
                                                            $container.append(shadow);
                                                        }

                                                        $container.find('.js-dialog_split_shadow').remove();
                                                        var isShadow = false;
                                                        for (var i = 0; i < self.datos.split.placesToSplit.length; i++) {
                                                            var placeToSplit = self.datos.split.placesToSplit[i];
                                                            if (checkSplit(placeToSplit)) {
                                                                showShadow(placeToSplit);
                                                                isShadow = true;
                                                                break;
                                                            }
                                                        }
                                                    }

                                                });
                            }
                        });
        $('body').on('mouseup', function(e) {
            $('body').off('mousemove');
            $('body').removeClass('is-no-selectable');
            $dialogo.removeClass('on_move');

            var $container = $(self.datos.drag_container);
            var shadow = $container.find('.js-dialog_split_shadow');
            if (shadow.length > 0) {
                var place = shadow.data('place');
                var fn = (place == 'top' || place == 'left') ? 'before' : 'after';
                //$container[fn]($dialogo);
                shadow.remove();
            }

        });
    }
    function modal() {
        var $velo = $('.mf-veil').show();
        if (!self.datos.hide_close_button) {
            $velo.click(self.close_dialog);
        }

    }
}
