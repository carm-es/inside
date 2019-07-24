$mf.autocomplete = function($input) {
    var extraParam = function(params, callback_params, formulario) {
        var aux_param = {}, key;
        for (key in params) {
            if (params[key] == 'all') {
                aux_param.push.apply($('#' + formulario).closest('form').serialize());
            } else if (formulario && params[key]) {
                var params_array = [];
                $('[name^="' + formulario + '[' + params[key] + ']"]').each(function(i, a) {
                    params_array.push(a.value);
                });
                aux_param[formulario + '[' + params[key] + ']'] = JSON.stringify(params_array);
            }
        }
        for (key in callback_params) {
            if (typeof ($mf.autocomplete[callback_params[key]]) == 'function') {
                aux_param[callback_params[key]] = $mf.autocomplete[callback_params[key]]();
            }
        }
        return aux_param;
    };
    this.init = function() {
        var $this = $(this), self = this, data = $this.data(), recuperar_datos = function(ids) {
            var aux = [];
            $.ajax({
                type : 'POST',
                url : data.jsonurl,
                data : {
                    values : ids
                },
                success : function(response) {
                    $.each(response, function(i, a) {
                        aux.push($.extend({
                            id : a.value,
                            text : a.label
                        }, a));
                    });
                },
                dataType : 'json',
                async : false
            });
            return aux;
        };
        this.opciones = {
            width : data.width || 'resolve',
            placeholder : "Buscar...",
            minimumInputLength : 1,
            dropdownAutoWidth : true,
            formatNoMatches : function(term) {
                return '<li class="select2-no-results">No existen resultados</li>';
            },
            formatSearching : function() {
                return '<li class="select2-searching">Buscando...</li>';
            },
            formatInputTooShort : function(term, minLength) {
                return '<li class="select2-no-results">Por favor introduzca al menos ' + minLength + ' '
                        + (minLength == 1 ? 'caracter' : 'caracteres') + '</li>';
            },
            formatSelectionTooBig : function(maxSize) {
                return '<li class="select2-selection-limit">Sólo puede seleccionar ' + maxSize + ' '
                        + (maxSize == 1 ? 'elemento' : 'elementos') + '</li>';
            },
            formatLoadMore : function(pageNumber) {
                return 'Cargando más resultados...';
            },
            triggerChange : true,
            ajax : {
                url : function(term, page) {
                    return data.url + '/' + term;
                },
                type : 'POST',
                dataType : 'json',
                data : function(term, page) {
                    var values = [];
                    $("#autocomplete_" + data.autocomplete).children('input').each(function(key, elements) {
                        values.push($(elements).val())
                    });
                    var aux = extraParam(data.params && data.params.split(/,\s*/) || [],
                            data.callback_params ? data.callback_params.split(/,\s*/) : [], data.form);
                    aux['values'] = values.length < 1 ? [ "" ] : values;
                    return aux;
                },
                results : function(data, page) {
                    var vals = [];
                    $(data).each(function(k, v) {
                        vals.push({
                            'id' : v.value,
                            'text' : v.label
                        });
                    });
                    return {
                        results : vals
                    };
                }
            },
            formatAjaxError : function(jqXHR, textStatus, errorThrown) {
                if (jqXHR.status == 428) {
                    var data = JSON.parse(jqXHR.responseText);
                    data.buttons = [
                            $(
                                    '<button '
                                            + (data.cancel_clazz != undefined ? ('class="' + data.cancel_clazz + '"') : '')
                                            + '>' + (data.cancel_label != undefined ? data.cancel_label : 'Cancelar')
                                            + '</button>').click(function() {
                                $mf.my_dialog.close_dialog();
                            }),
                            $(
                                    '<button '
                                            + (data.accept_clazz != undefined ? ('class="btn-primary '
                                                    + data.accept_clazz + '"') : '') + '>'
                                            + (data.accept_label != undefined ? data.accept_label : 'Aceptar')
                                            + '</button>').click(function() {
                                window.location.href = data.action;
                            }) ];
                    $mf.my_dialog.appendDialog(data, false, true);
                }
                return 'Error en la carga.'
            },
            initSelection : function(element, callback) {
                if (typeof (window['values_' + data.autocomplete]) == 'function') {
                    window['values_' + data.autocomplete] = window['values_' + data.autocomplete]();
                }
                var datos = window['values_' + data.autocomplete], datos_sal = [];
                if (element.val() != '') {
                    if (datos == undefined)
                        datos = [];
                    else if (!$.isArray(datos))
                        datos = [ datos ];
                    $(element.val().split(/,\s*/))
                            .each(
                                    function(i, b) {
                                        var no_esta = 1;
                                        $(datos).each(function(i, a) {
                                            if (a && a.id == b) {
                                                no_esta = 0;
                                                datos_sal.push(a);
                                                return 0;
                                            }
                                        });
                                        if (no_esta) {
                                            var datos_rec = recuperar_datos(b), obj_aux = datos_rec.length
                                                    && datos_rec[0];
                                            datos_rec.length && $.isArray(window['values_' + data.autocomplete]) ? window['values_'
                                                    + data.autocomplete].push(obj_aux) : (window['values_'
                                                    + data.autocomplete] = obj_aux);
                                            datos_rec.length && datos_sal.push(obj_aux);
                                        }
                                    });
                }
                if (datos_sal.length != 0) {
                    callback(datos_sal);
                } else {
                    //  element.val('');
                }
                modular_values.call(self, $.Event);
            },
            multiple : true
        };
        if (!data.multiple)
            this.opciones.maximumSelectionSize = 1;
        if (data.template.length > 0) {
            $.extend(this.opciones, {
                'formatResult' : $mf.autocomplete[data.template],
                'formatSelection' : $mf.autocomplete[data.template]
            });
        }
        if (data.newitems) {
            $.extend(this.opciones, {
                'createSearchChoice' : function(term, data) {
                    if ($(data).filter(function() {
                        return this.text.localeCompare(term) === 0;
                    }).length === 0) {
                        return {
                            id : term,
                            text : term
                        };
                    }
                },
                'createSearchChoicePosition' : function(list, item) {
                    list.splice(0, 0, item);
                }
            });
        }
        if (data.resultslist) {
            $.extend(this.opciones, {
                'resultsList' : $(data.resultslist),
                'closeOnSelect' : false
            });
        }
        if (data.initdata)
            this.opciones.initData = data.initdata;
        if (data.multiple && data.max_values)
            this.opciones.maximumSelectionSize = data.max_values;
        if (data.research_at_end)
            this.opciones.research_at_end = true;
        if (data.url_help) {
            $mf['help_' + data.autocomplete] = function($el, dialog) {
                $mf.init_widgets($el);
                $el.off('click').on('click', 'a', function(e) {
                    e.preventDefault();
                    $this.select2('val', [ $(e.currentTarget).data('id') ]).siblings('div').addClass('is-selected');
                    dialog.close_dialog();
                });
            }
        }
        var eliminar = function($el, id) {
            var siblings = $el.siblings('input');
            if ($el.val() == id)
                $el.val('');
            siblings.each(function(k, v) {
                if ($(v).val() == id) {
                    $(v).remove();
                }
            });
            if (!$el.val() && $el.next().val()) {
                $el.val($el.next().val());
                $el.next().remove();
            }
            if (!siblings.length) {
                var name = $el[0].name;
                if (name.substr(name.length - 2, name.length - 1) == '[]') {
                    $el[0].name = name.substr(0, name.length - 2);
                }
            }
        }, anadir = function($this) {
            $(($this.val().match(/\[(.*)\]/) && $this.val().match(/\[(.*)\]/)[1] || $this.val()).split(/,\s*/)).each(
                    function(i, a) {
                        if (i == 0) {
                            $this.val(a);
                            if ($this.attr('name').substr($this.attr('name').length - 2, 2) != '[]')
                                $this.attr('name', $this[0].name + '[]');
                        } else {
                            $('<input>').attr({
                                type : 'hidden',
                                name : $this[0].name,
                                value : a
                            }).appendTo($this.parent());
                        }
                    })
        }, modular_values = function(e) {
            if (e.added != undefined) {
                if (!data.multiple) {
                    if (e.removed)
                        eliminar($(this), e.removed.id);
                    this.value = e.val;
                    $(this).siblings('.select2-container').find('.select2-search-field').hide();
                    $(this).addClass('is-selected');
                } else {
                    if ($(this).val() != '') {
                        anadir($(this));
                    } else {
                        this.value = e.val;
                    }
                }
            } else {
                if (e.removed)
                    eliminar($(this), e.removed.id);
                if (!data.multiple) {
                    if (e.removed != undefined) {
                        $(this).siblings('.select2-container').find('.select2-search-field').show();
                        $(this).removeClass('is-selected');
                    } else if ($(this).siblings('.select2-container').find('.select2-search-choice').length > 0) {
                        $(this).siblings('.select2-container').find('.select2-search-field').hide();
                        $(this).addClass('is-selected');
                    }
                } else {
                    if ($(this).val() != '') {
                        anadir($(this));
                    }
                }
            }
        }
        $this.select2(this.opciones).on('change', modular_values);
        //        modular_values.call(this,$.Event);
    }
    if (!$input) {
        $('[data-autocomplete]').each(this.init);
    } else if ($input.is('[data-autocomplete]')) {
        this.init.call($input);
    } else {
        $input.find('[data-autocomplete]').each(this.init);
    }
};
