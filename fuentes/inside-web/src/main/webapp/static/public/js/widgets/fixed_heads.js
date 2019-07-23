$mf.fixed_heads = function($contenedor, my_class, container_max_height, no_resize) {
    var data = $contenedor.data();
    if (my_class == undefined)
        my_class = data && data.clazz != undefined ? data.clazz : 'mf-table-fixheads';
    this.$el = $contenedor.find('table');
    if (container_max_height == undefined)
        container_max_height = data && data.height != undefined ? data.height : this.$el.outerHeight();
    var table = this, $fixheads_main_wrapper = $contenedor.wrap('<div class="' + my_class + '-wrapper"></div>')
            .parent(), resize_headers = function($headertable) {
        var change = false, $header_cells = $headertable.find('tr').children(), resize = function(i, e) {
            $($header_cells[i]).css({
                'width' : $(e).outerWidth(),
                'height' : $(e).outerHeight(),
                'position' : 'absolute',
                'left' : Math.round($(e).offset().left - $headertable.offset().left)
            });

        }
        if (table.$el.find('td').length == 0) {
            table.$el.css('left', table.corner_head.$el.outerWidth());
        } else {
            table.$el.css('left', 0);
        }

        table.$el.find('tr').first().children().each(function(i, e) {
            if (!change) {
                if ($($header_cells[i]).width() != $(e).outerWidth()) {
                    change = true;
                    resize(i, e);
                }
            } else {
                resize(i, e);
            }
        });
    }, resize_height = function(container_max_height) {
        var aux_height = table.top_head != undefined ? $(table.top_head.$wrapper).outerHeight() : 0;
        $contenedor.css('height', container_max_height - aux_height);
        if (table.left_head != undefined)
            table.left_head.$wrapper.css('height', $contenedor.height());
    }, mover_cabeceras = function() {
        if (table.top_head != undefined)
            table.top_head.$el.css('left', -$contenedor.scrollLeft());
        if (table.left_head != undefined)
            table.left_head.$el.css('top', -$contenedor.scrollTop());
    }, top_head = function(left, col_left) {
        this.$el = table.$el.clone().empty().append(table.$el.children('thead')).addClass(my_class + '--top').css(
                'width', table.$el.width());
        this.$wrapper = $('<div class="' + my_class + '--top-container"></div>');
        this.render = function() {
            if (left && typeof col_left != "object") {
                var index_th = table.$el.children('tbody').find('th:first').index();
                this.$el.find('th:eq(' + (col_left != undefined ? col_left : index_th != -1 ? index_th : 0) + ')')
                        .remove();
            }
            var $top_head = this.$el, $head_table = this.$el.children('thead').clone().prependTo(table.$el);
            if (data.small_cells)
                $head_table.find('th').empty();
            this.$wrapper.append(this.$el).height(table.$el.children('thead').outerHeight());
            this.$el.find('th').each(function(i, a) {
                var $el = $(a);
                $el.find('.right-border-head').on('mousedown', function(e) {
                    $el.on('resize', function(e, width) {
                        $head_table.find('th:eq(' + i + ')').css({
                            'min-width' : width,
                            'max-width' : width
                        });
                        table.$el.find('tr').each(function(j, a) {
                            $(a).find('td:eq(' + i + ')').css({
                                'min-width' : width,
                                'max-width' : width
                            });
                        });
                        resize_headers($top_head);
                    })
                })
            });
            $fixheads_main_wrapper.prepend(this.$wrapper);
            table.$el.css('top', -table.$el.children('thead').outerHeight()).addClass(my_class + '--main-table');
        }
    }, left_head = function(col_left) {
        var sacar_colum = function(num_col) {
            var $tabla_aux = $('<table></table>'), $thead_aux = $('<thead></thead>').appendTo($tabla_aux), $tbody_aux = $(
                    '<tbody></tbody>').appendTo($tabla_aux);
            $trs = table.$el.children('tbody').children('tr');
            $ths = table.$el.children('thead').find('th');
            if (num_col == undefined) {
                var hasth = $trs.find('th:first').index();
                $thead_aux.append($('<tr></tr>').append(hasth != -1 ? $($ths[hasth]) : $($ths[0]).clone(true)));
                $trs.each(function(i, a) {
                    $tbody_aux.append($('<tr></tr>').append(
                            hasth != -1 ? $(a).children('th:first') : $(a).find('td:first').css('height',
                                    $(a).find('td:first').outerHeight())));
                });
            } else {
                $thead_aux.append($('<tr><th>' + $($ths[num_col]).html() + '</th></tr>'));
                $trs.each(function(i, a) {
                    $tbody_aux
                            .append($('<tr></tr>').append(
                                    $(a).children('td:eq(' + num_col + ')').css('height',
                                            $(a).find('td:first').outerHeight())));
                });

            }
            return $tabla_aux;
        }, $el = ''
        if (typeof col_left == "object") {
            $el = col_left;
        } else if (!isNaN(col_left)) {
            $el = sacar_colum(col_left)
        } else {
            $el = sacar_colum();
        }
        this.$el = $el.addClass(my_class + '--left');
        this.$wrapper = this.$el.wrap('<div class="' + my_class + '--left-container"></div>').parent();
        this.render = function() {
            $fixheads_main_wrapper.prepend(this.$wrapper);
            $('<div class="button-row button-container"></div>').appendTo(this.$wrapper.parent());
        }

    }, corner_head = function() {
        this.$el = table.left_head.$el.clone().empty().append(table.left_head.$el.find('th:first')).removeClass(
                my_class + '--left').addClass(my_class + '--corner').css('height',
                table.$el.children('thead').outerHeight());
        this.$wrapper = this.$el.wrap('<div class="' + my_class + '--corner-container"></div>').parent();
        this.render = function() {
            var aux_width = this.$el.outerWidth(), aux_width_children = this.$el.children().outerWidth();
            this.$wrapper.height(table.$el.children('thead').outerHeight()).css({
                'width' : aux_width + 2,
                'overflow' : 'hidden'
            });
            $fixheads_main_wrapper.prepend(this.$wrapper);
            if (aux_width_children > table.left_head.$el.outerWidth()) {
                table.left_head.$el.css('width', aux_width_children);
                table.left_head.$el.parent().css('width', aux_width_children + 2);
            } else {
                this.$wrapper.css('width', table.left_head.$el.outerWidth() + 2);
            }
        }
    }

    this.render = function(top, left, col_left) {
        var window_height = $('body').height();
        if (left) {
            this.left_head = new left_head(col_left);
            this.left_head.render();
        }
        if (top) {
            this.top_head = new top_head(left, col_left);
            this.top_head.render();
        }
        if (top && left) {
            this.corner_head = new corner_head();
            this.corner_head.render();
        }
        resize_headers(this.top_head.$el);
        resize_height(container_max_height);
        $contenedor.on('scroll', mover_cabeceras);
        if ((!data || !data.height) && !no_resize) {
            $(window)
                    .resize(
                            function(e) {
                                setTimeout(
                                        function() {
                                            var aux_max_height = container_max_height
                                                    - (window_height - $('body').height());
                                            aux_max_height = data && data.min_height != undefined ? (aux_max_height > data.min_height ? aux_max_height : data.min_height) : aux_max_height;
                                            resize_height(aux_max_height);

                                        }, 10);
                            });
        }
    }
    this.resize = function() {
        resize_headers(table.top_head.$el);
    }
    if ($contenedor.hasClass('mf-table-fixheads__left')) {
        this.render(false, true, data.column);
    } else if ($contenedor.hasClass('mf-table-fixheads__top')) {
        this.render(true, false, data.column);
    } else if ($contenedor.hasClass('mf-table-fixheads')) {
        this.render(true, true, data.column);
    }
    ;
}
