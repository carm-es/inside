$mf.Datepicker = function($el, obj) {
    var init = function($el) {
        var options = $.extend({
            mode : 'single',
            position : 'right',
            starts : 1,
            locale : {
                daysMin : [ "D", "L", "M", "X", "J", "V", "S" ],
                months : [ "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre",
                        "Octubre", "Noviembre", "Diciembre" ],
                monthsShort : [ "Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic" ]
            },
            onBeforeShow : function(el) {
                var val = $el.val(), validformat = /^\d{2}\-\d{2}\-\d{4}$/;
                if (validformat.test(val)) {
                    var transform = function(date) {
                        var pattern = /(\d{2})\-(\d{2})\-(\d{4})/;
                        return date.replace(pattern, '$2/$1/$3');
                    };
                    $el.DatePickerSetDate(transform($el.val()), true);
                } else {
                    var date = new Date()
                    $el.DatePickerSetDate(((date.getMonth() + 101) + '').substr(1, 2) + '/'
                            + ((date.getDate() + 100) + '').substr(1, 2) + '/' + date.getFullYear(), true);
                }
            },
            onChange : function(date, el) {
                //                    $(el).val(date.getFullYear()+'-'+((date.getMonth()+101)+'').substr(1,2)+'-'+((date.getDate()+100)+'').substr(1,2));
                $(el).val(
                        ((date.getDate() + 100) + '').substr(1, 2) + '-' + ((date.getMonth() + 101) + '').substr(1, 2)
                                + '-' + date.getFullYear());
                $(el).DatePickerHide();
            }
        }, obj ? obj : {}), data = $el.data();
        if (data.mode == 'range') {
            data.onChange = function(dates, el) {
                $el.val(dates);
            }
        }
        $el.DatePicker($.extend(options, data));
    }
    if (!Modernizr.inputtypes['date']) {
        if (!$el) {
            $('.mf-datepicker').each(function(i, a) {
                init($(a));
            });
        } else if ($el.is('.mf-datepicker')) {
            init($el);
        } else {
            $el.find('.mf-datepicker').each(function(i, a) {
                init($(a));
            });
        }
    }
};
