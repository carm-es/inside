$mf.DataGrid = function($el) {
    var heads = {},
    //        init_pager = function(key,value){
    //            $(value).find('div.pager--results>select').change(go_pager);
    //            $(value).find('div.pager--page>select').change(go_pager);//.select2();
    //            $(value).find('button.pager--btn').click(go_pager);
    //        },
    init_order = function(key, value) {
        $(value).click(go_order);
    }, init_filter = function(key, value) {
        var $value = $(value), $table = $value.parent().find('table'), $filter = $value.children('form');
        if (!$filter.is(':visible')) {
            $value.siblings('.grid-header').find('a.mf-filter--title').click(
                    function() {
                        $filter.toggle();
                        $filter.find('.mf-datagrid--filter-content').css(
                                'max-height',
                                $table.parent('.mf-datagrid--table-container').outerHeight()
                                        - $filter.find('.mf-datagrid--filter-submitbar').outerHeight());
                    });
        }
        $filter.find('.js-filter--toggle').click(function(e) {
            $(this).next().toggle();
            $(this).toggleClass('is-open');
        }).next().toggle();
        $filter.find('.mf-datagrid--filter-content').css('max-height',
                $table.parent('.mf-datagrid--table-container').outerHeight());
        $value.find('.morfos-autocomplete').each(init_autocomplete_filter);
        $filter.submit(function(e) {
            e.preventDefault;
            var $el = $(e.currentTarget);
            $mf.timer.on($el.closest('div[id^=grid_]'));
            $.post($(this).attr('action'), $(this).serialize(), function(data) {
                pintar(data, $el);
            });
            return false;
        });
        //            $filter.find('button.clearForm').click(go_pager);
        $filter.find('button.clearForm').click(go_order);
    }, init_autocomplete_filter = function(key, value) {
        $(value).find('#form_autocompleter').on('change', function(e) {
            $(value).siblings('[type=dsic_morfos_autocompleter]').val(this.value);
        });
    },
    //        go_pager = function(e){
    //            var $el = $(e.currentTarget),
    //                url = $el.data('url');
    //            if (e.type == 'change'){
    //                url += $el.val();
    //            }
    //            $mf.timer.on($el.closest('div[id^=grid_]'));
    //            $.get(url)
    //                .done(function(data){ pintar(data,$el);});
    //        },
    go_order = function(e) {
        e.preventDefault();
        $el = $(e.currentTarget);
        var url = $el.attr('href');
        $mf.timer.on($el.closest('div[id^=grid_]'));
        $.get(url).done(function(data) {
            pintar(data, $el);
        });
    }, pintar = function(data, $el) {
        var $data = $(data), id = $data.attr('id'), $content = $el.closest('mf-datagrid--content'), $table = $content
                .find('table')
        $el.closest('div#' + id).replaceWith($data);
        $mf.timer.off();
        $mf.init_widgets($data);
    };
    if ($el) {
        //            $el.find('.grid_footer .pager').each(init_pager);
        $el.find('th>a.mf-sort-ln').each(init_order);
        new $mf.fixed_heads($el.find('.mf-datagrid--table-container'));
        $el.find('.mf-filter').each(init_filter);
    } else {
        //            $('.grid_footer .pager').each(init_pager);
        $('th>a.mf-sort-ln').each(init_order);
        new $mf.fixed_heads($('.mf-datagrid--table-container'));
        $('.mf-filter').each(init_filter);
    }
}
