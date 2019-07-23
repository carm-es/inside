$mf.draw_helps = function() {
    var helps = this, mfhelpcontainer = '<div id="mf-help-container" style="display: none;"></div>', mfhelpoverlay = '<div id="mf-help-overlay" style="display: none;"></div>', mfhelpframes = '<div id="mf-help-frames" style="display: none;"></div>', $mfhelpNavBar = $('<div id="mf-help-navbar">'
            + '<a id="mf-help-prevbtn" class="simbutton" href"#!">Previa</a>'
            + '<a id="mf-help-nextbtn" class="simbutton" href"#!">Siguiente</a>'
            + '<a id="mf-help-closebtn" class="simbutton" href"#!">¡Muy bien, entendido!</a>'
            + '<span>o</span> '
            + '<a href="#!">Necesito saber más, <u>quiero ir a la ayuda</u></a>' + '</div>');

    $('<div id="coverContainer"></div>').appendTo($('body')).append(mfhelpcontainer, mfhelpoverlay, mfhelpframes);
    $('#mf-help-container').show('blind');
    $('#mf-help-overlay').show();
    $('#mf-help-frames').show();
    $('#mf-help-navbar').show();

    this.charge_help = function(index, $helpObject) {
        var helpData = $helpObject.data();

        $helpObject.addClass('mfhelp-item');

        $('#mf-help-overlay').append('<div class="mf-help-tip" id="mf-help-item-' + index + '"></div>');

        var helpItem = {
            id : '#mf-help-item-' + index,
            text : null,
            background : null,
            title : '<h6 class="mf-help-title">' + helpData.mfhelpTitle + '</h6>'
        };

        if (helpData.mfhelpText) {
            helpItem.text = '<p class="mf-help-text">' + helpData.mfhelpText + '</p>';
        }
        ;

        var offset = $helpObject.offset(), $helpItem = $(helpItem.id);

        if (helpData.mfhelpFrame) {
            var itemFrame = '<div id="mf-help--item-frame-' + index + '" class="mf-help-frame"></div>';

            $('#mf-help-frames').append(itemFrame);

            $('#mf-help--item-frame-' + index).width($helpObject.width() + 20).height($helpObject.height() + 20).css({
                'left' : offset.left - 10,
                'top' : offset.top - 10,
                'position' : 'absolute'
            });
        }
        ;

        $helpItem.append(helpItem.title, helpItem.text);

        if (helpData.mfhelpPos) {
            var top = function() {
                $helpItem.addClass('top');
                $mf.position($helpItem, $helpObject, 'top', 'center', false, true);
            }, right = function() {
                $helpItem.addClass('right');
                $mf.position($helpItem, $helpObject, 'top', 'right', true, false);
            }, bottom = function() {
                $helpItem.addClass('bottom');
                $mf.position($helpItem, $helpObject, 'bottom', 'left', false, true);
            }, left = function() {
                $helpItem.addClass('left');
                $mf.position($helpItem, $helpObject, 'center', 'left', true, false);
            }
            eval(helpData.mfhelpPos)();
        } else {
            $helpItem.addClass('left');
            $mf.position($helpItem, $helpObject, 'center', 'left', true, false);
        }
    }

    var data_body = $('body').data();
    $mfhelpNavBar.appendTo('#coverContainer').find('#mf-help-closebtn').on('click', function() {
        $.cookie('show-help', 0);
        $('#coverContainer').remove();
    });
    if ($('[class*=mfhelp-page]').length != 0) {
        // paginamos los elementos en caso de estar indicado;
        var page = 0, carga_inicial = function() {
            $('.mfhelp-page-0').each(function(index, helpObject) {
                helps.charge_help(index, $(helpObject));
            });
        }, cargar_page = function(n) {
            page = page + n;
            $('.mfhelp-item').removeClass('mfhelp-item');
            $('.mf-help-tip, .mf-help-frame').remove();
            $('.mfhelp-page-' + page).each(function(index, helpObject) {
                helps.charge_help(page + '-' + index, $(helpObject));
            });
        };
        carga_inicial();
        $mfhelpNavBar.find('#mf-help-prevbtn').on('click', function(e) {
            e.preventDefault();
            if (page == 1) {
                $(this).hide();
                cargar_page(-1);
            } else if (page != 0) {
                cargar_page(-1);
            }
            $mfhelpNavBar.find('#mf-help-nextbtn').show();
        }).hide();
        $mfhelpNavBar.find('#mf-help-nextbtn').on('click', function(e) {
            e.preventDefault();
            if ($('.mfhelp-page-' + (page + 2)).length == 0) {
                $(this).hide();
                cargar_page(1);
            } else {
                cargar_page(1);
            }
            $mfhelpNavBar.find('#mf-help-prevbtn').show();
        });
    } else {
        // Buscamos elementos que tengan atributos "data-mfhelp-title"

        $('[data-mfhelp-title]').each(function(index, helpObject) {
            helps.charge_help(index, $(helpObject));
        });
    }
}

$(document).ready(function() {
    if ($('body').hasClass('show_help') && ($.cookie('show-help') != undefined ? eval($.cookie('show-help')) : true)) {
        setTimeout(function() {
            $mf.my_draw_helps = new $mf.draw_helps()
        }, 1000);
    }
});

this.charge_help = function(index, $helpObject) {
    var helpData = $helpObject.data();

    $helpObject.addClass('mfhelp-item');

    $('#mf-help-overlay').append('<div class="mf-help-tip" id="mf-help-item-' + index + '"></div>');

    var helpItem = {
        id : '#mf-help-item-' + index,
        text : null,
        background : null,
        title : '<h6 class="mf-help-title">' + helpData.mfhelpTitle + '</h6>'
    };

    if (helpData.mfhelpText) {
        helpItem.text = '<p class="mf-help-text">' + helpData.mfhelpText + '</p>';
    }
    ;

    var offset = $helpObject.offset(), $helpItem = $(helpItem.id);

    if (helpData.mfhelpFrame) {
        var itemFrame = '<div id="mf-help--item-frame-' + index + '" class="mf-help-frame"></div>';

        $('#mf-help-frames').append(itemFrame);

        $('#mf-help--item-frame-' + index).width($helpObject.width() + 20).height($helpObject.height() + 20).css({
            'left' : offset.left - 10,
            'top' : offset.top - 10,
            'position' : 'absolute'
        });
    }
    ;

    $helpItem.append(helpItem.title, helpItem.text);

    if (helpData.mfhelpPos) {
        var top = function() {
            $helpItem.addClass('top');
            $mf.position($helpItem, $helpObject, 'top', 'center', false, true);
        }, right = function() {
            $helpItem.addClass('right');
            $mf.position($helpItem, $helpObject, 'top', 'right', true, false);
        }, bottom = function() {
            $helpItem.addClass('bottom');
            $mf.position($helpItem, $helpObject, 'bottom', 'left', false, true);
        }, left = function() {
            $helpItem.addClass('left');
            $mf.position($helpItem, $helpObject, 'center', 'left', true, false);
        }
        eval(helpData.mfhelpPos)();
    } else {
        $helpItem.addClass('left');
        $mf.position($helpItem, $helpObject, 'center', 'left', true, false);
    }
};
