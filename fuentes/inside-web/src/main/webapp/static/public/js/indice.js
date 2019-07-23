var uploader;
var dialogImportDocument;

$(document).ready(function() {
    configurarNuevo();
});

function configurarNuevo() {
    $('#js-crear-carpeta').on('click', crearCarpeta);
    $('#js-crear-estructura').on('click', crearEstructuraCarpetas);
    $('#js-crear-documento').on('click', showDialogCreateDocument);
    $('#js-insertar-documentoInside').on('click', showDialogImportarDocumento);
    $('#js-create-subExpedient').on('click', showDialogImportarExpediente);
    $('#js-create-subExpedientToken').on('click', showDialogImportarExpedienteToken);
    $('#expedienteForm').on('submit', function(e) {
        e.preventDefault();
    });
    $('#js-arbol').on('click', function(e) {
        $(e.currentTarget).find('.js-arbol-lista-item-cont.is-selected').removeClass('is-selected');
    });
    $('#js-gallery-ver').on('click', '.js-gallery-ver--lista-cancel', function(e) {
        var $el = $(e.currentTarget);
        $el.closest('#js-gallery-ver').addClass('hidden');
    }).on('click', '.js-gallery-ver--lista-download', function(e) {
        $("#descargarDocForm").submit();
    }).on('click', '#buttondescargarContenidoDoc', function(e) {
        $("#descargarContenidoDocForm").submit();
    }).on('click', '.js-gallery-ver--mover-dcha', function(e) {
        var visor = $(e.currentTarget).closest('#js-gallery-ver');
        mostrarVisor(visor, getSiguienteItem($('#' + visor.data('id_mostrado'))));
    }).on('click', '.js-gallery-ver--mover-izda', function(e) {
        var visor = $(e.currentTarget).closest('#js-gallery-ver');
        mostrarVisor(visor, getSiguienteItem($('#' + visor.data('id_mostrado')), true));
    });
    configurarPlUpload();
}

function getSiguienteItem(item, anterior) {
    var ret;
    if (anterior) {
        ret = item.prevAll('.js-arbol-lista-item').first();
        if (!ret.length) {
            ret = item.parent().children('.js-arbol-lista-item').last();
        }
    } else {
        ret = item.nextAll('.js-arbol-lista-item').first();
        if (!ret.length) {
            ret = item.parent().children('.js-arbol-lista-item').first();
        }
    }

    return ret;
}

function getFecha(fecha) {
    if (!fecha) {
        fecha = new Date();
    }

    var day = fecha.getDate();
    var month = fecha.getMonth() + 1;
    var year = fecha.getFullYear();

    function format(num) {
        var ret = num + '';

        if (ret.length == 1) {
            ret = '0' + ret;
        }

        return ret;
    }

    return year + '-' + format(month) + '-' + format(day);
}

function configurarPlUpload() {
    uploader = new plupload.Uploader({
        drop_element : 'js-gallery-visor',
        browse_button : 'js-insertar-documento',
        url : $("#context").val() + "/uploadTempData",
        chunk_size : '10mb',
        max_file_size : "10gb"
    });

    uploader.bind('FilesAdded', function(up, files) {
        var form = $('#js-gallery-content').find('.js-gallery-ver--form-fecha');
        mostrarDialogDocumento(files);
    });

    uploader.bind('UploadProgress', function(up, file) {
        $('.mf-dialog #progressDocumento').attr('value', file.percent);
    });

    uploader.bind('UploadComplete', function(up, file_plupload) {
        $('.mf-dialog #progressDocumento').addClass('hidden');

        var velo = dialogImportDocument.find('.js-timer-veil').removeClass('hidden');

        var fecha = dialogImportDocument.find('.js-fecha-documento').val();

        $(file_plupload).each(function(k, v) {

            function salir() {
                uploader.splice();
                velo.addClass('hidden');
                $mf.my_dialog.close_dialog();
            }

            var idExpediente = $("#expedienteForm #identificador").val();

            var lista = getLista();
            enviarAjax2($('#js-arbol').data('url_documento_nuevo'), {
                path : lista.item && getPath2(lista.item),
                idExpediente : idExpediente,
                fileName : v.name,
                fechaIncorpExp : getFecha(),
                ordenDocExp : 1,
                hidden : false
            }, function(data) {
                crearItemLista({
                    id : data.id,
                    texto : data.id,
                    natural : data.nombreNatural,
                    lista : lista.lista,
                    isDocument : true
                });
                salir();
            }, salir);
        });
    });
    //Salta con las cabeceras del la response. Xej. un 500
    uploader.bind('Error', function(up, err) {
        $("#tipoMensaje").val(4);
        $("#valorMensaje").val("Error en la subida del fichero al servidor: " + err);
        showMessage();
    });

    uploader.init();
}

function habilitarBotones() {
    $('#button_expediente').removeAttr('disabled');
    $('#button_generarExp').css('pointer-events', 'auto');
    $('#button_genEditar').removeAttr('disabled');
    $('#button_genEditarExp').css('pointer-events', 'auto');
}

function elementoSeleccionadoNoEsDocumento(mensaje) {
    var lista = getLista();
    if (typeof (lista.item) === 'undefined' || lista.item.data('tipo') == 'carpeta') {
        return true;
    } else {
        alert('No puede ' + mensaje + ' sobre un documento');
        return false;
    }
}

function mostrarDialogDocumento(files) {
    var divData = $('#init-modal-insertar-documento');
    var data = divData.data();
    var buttons = createButtonsDialog([ {
        clase : 'js-documento-cancelar',
        texto : 'Cancelar'
    }, {
        clase : 'js-documento-crear',
        texto : 'Crear',
        submit : true
    } ]);

    buttons.find('.js-documento-crear').on('click', function(e) {
        e.preventDefault();

        if (elementoSeleccionadoNoEsDocumento('adjuntar documento ENI')) {
            setTimeout(function() {
                $('.mf-dialog #progressDocumento').attr('value', 0).removeClass('hidden');
                deshabitilarBotones();
                uploader.start();
                habilitarBotones();
            }, 1000);

            dialogImportDocument = $(e.currentTarget).closest('.js-dialog-documento');
        }
    });

    buttons.find('.js-documento-cancelar').on('click', function(e) {
        e.preventDefault();
        $mf.my_dialog.close_dialog();
        deleteFilesUpload();
    });

    data.buttons = buttons;
    data.data = divData.html();
    data.init_dialog = initDialogFields2;

    $mf.my_dialog.appendDialog(data, true, true);
}

function deleteFilesUpload() {
    if (uploader.files.length > 0) {
        var count = uploader.files.length;
        var i = 0;
        for (i; i < count; i++) {
            uploader.removeFile(uploader.files[i]);
        }
    }
}

function deshabitilarBotones() {
    $('#button_expediente').attr('disabled', 'disabled');
    $('#button_generarExp').css('pointer-events', 'none');
    $('#button_genEditar').attr('disabled', 'disabled');
    $('#button_genEditarExp').css('pointer-events', 'none');
}

function initDialogFields2($el) {
    var fechaInit = $.datepicker.formatDate("dd-mm-yy", new Date());
    var fecha = $el.find('.js-datepicker-today');
    fecha.val(fechaInit);

    $mf.Datepicker($el);
}

function crearEspaciosDragDrop() {
    var listas = $('#js-arbol .js-arbol-lista');

    listas.find('.js-arbol-espacio').remove();

    function crearEspacioDragDrop(orden) {
        var ordenfinal = orden == 0 ? 1 : orden;
        return $('<li class="js-arbol-espacio" data-orden="' + ordenfinal + '">').attr('ondrop', "drop2(event)").attr(
                'ondragover', "allowDrop2(event)").attr('ondragleave', "dragleave(event)");
    }

    listas.each(function(indice_lista, lista) {
        var $lista = $(lista);
        var items = $lista.children('.js-arbol-lista-item');
        if (items.length) {
            $lista.prepend(crearEspacioDragDrop(0));
        }
    });
    var items = $('#js-arbol>.js-arbol-lista>.js-arbol-lista-item');
    if (items.length)
        espacioHijos(items);

    var itemSubExpedientes = $('#js-arbol>.js-arbol-lista>.js-arbol-lista>.js-arbol-lista-item');
    if (itemSubExpedientes.length)
        espacioHijos(itemSubExpedientes);

    function espacioHijos(items) {
        var hijos = items.length;
        var i = 0;
        while (i < hijos) {
            var $item = $(items[i]);
            $item.after(crearEspacioDragDrop(i + 2));
            var itemsHijos = $item.children('.js-arbol-lista').children('.js-arbol-lista-item');
            if (itemsHijos.length) {
                espacioHijos(itemsHijos);
            }
            i++;
        }
    }
}

function deshabilitarTodosBotonesSuperior() {

    $('#js-crear-carpeta').attr('disabled', 'disabled');
    $('#js-crear-carpeta').css('pointer-events', 'none');

    $('#js-crear-estructura').attr('disabled', 'disabled');
    $('#js-crear-estructura').css('pointer-events', 'none');

    $('#js-crear-documento').attr('disabled', 'disabled');
    $('#js-crear-documento').css('pointer-events', 'none');

    $('#js-insertar-documento').attr('disabled', 'disabled');
    $('#js-insertar-documento').css('pointer-events', 'none');

    $('#js-insertar-documentoInside').attr('disabled', 'disabled');
    $('#js-insertar-documentoInside').css('pointer-events', 'none');

    $('#js-create-subExpedient').attr('disabled', 'disabled');
    $('#js-create-subExpedient').css('pointer-events', 'none');

    $('#js-create-subExpedientToken').attr('disabled', 'disabled');
    $('#js-create-subExpedientToken').css('pointer-events', 'none');

}

function habilitarTodosBotonesSuperior() {

    $('#js-crear-carpeta').removeAttr('disabled');
    $('#js-crear-carpeta').css('pointer-events', 'auto');

    $('#js-crear-estructura').removeAttr('disabled');
    $('#js-crear-estructura').css('pointer-events', 'auto');

    $('#js-crear-documento').removeAttr('disabled');
    $('#js-crear-documento').css('pointer-events', 'auto');

    $('#js-insertar-documento').removeAttr('disabled');
    $('#js-insertar-documento').css('pointer-events', 'auto');

    $('#js-insertar-documentoInside').removeAttr('disabled');
    $('#js-insertar-documentoInside').css('pointer-events', 'auto');

    $('#js-create-subExpedient').removeAttr('disabled');
    $('#js-create-subExpedient').css('pointer-events', 'auto');

    $('#js-create-subExpedientToken').removeAttr('disabled');
    $('#js-create-subExpedientToken').css('pointer-events', 'auto');

}

function onClickText(e) {
    deshabilitarTodosBotonesSuperior();
    showInput($(e.currentTarget).closest('.js-arbol-lista-item'));
}

function onClickItem(e) {
    e.stopPropagation();
    var $el = $(e.currentTarget);
    $el.closest('#js-arbol').find('.js-arbol-lista-item-cont.is-selected').removeClass('is-selected');
    $el.find('>.js-arbol-lista-item-cont').addClass('is-selected');
}

function onClickVer(e) {

    var $el = $(e.currentTarget);

    var arbol = $el.closest('#js-arbol');
    var visor = arbol.parent().find('#js-gallery-ver');

    mostrarVisor(visor, $el.closest('.js-arbol-lista-item'));
}

function mostrarVisor(visor, item) {

    var arbol = $('#js-arbol');
    var panel = visor.siblings('#js-gallery-visor');
    $('#expedienteVeil').removeAttr('style').removeClass('hidden');
    enviarAjax2(arbol.data('url_elemento_ver'), {
        idDocumento : item.data('id'),
        hidden : false
    }, function(data) {

        visor.removeClass('hidden').data('id_mostrado', item.attr('id')).width(panel.width()).height(panel.height())
                .offset(panel.offset());

        $("#descargarDocForm #identificador").val(data.identificador);
        $("#descargarContenidoDocForm #identificador").val(data.identificador);
        visor.find('.js-gallery-ver--lista-datos-id').html(data.identificador);
        visor.find('.js-gallery-ver--lista-datos-estado').html(data.desEstado);
        visor.find('.js-gallery-ver--lista-datos-organos').html(data.organos.join(', '));
        visor.find('.js-gallery-ver--visor').attr('src', 'data:application/pdf;base64,' + data.contenidoVisualizar);

        $('#expedienteVeil').addClass('hidden');
    });
}

function eliminarItem(item) {
    var parent = item.parent();
    item.remove();
    if (!parent.children('.js-arbol-lista-item').length && !parent.children('.js-arbol-lista').length) {
        parent.closest('.js-arbol-lista-item').removeClass('has-children');
        parent.remove();
    }

    crearEspaciosDragDrop();
}

function onClickDelete(e) {
    var arbol = $('#js-arbol');
    var item = $(e.currentTarget).closest('.js-arbol-lista-item');
    var par = {
        pathOrigen : getPath2(item, true),
        identificador : item.data('nombre'),
        hidden : true
    };
    enviarAjax2(arbol.data('url_elemento_borrar'), par, function(data) {
        eliminarItem(item);
    });
}

function onClickFolder(e) {
    var item = $(e.currentTarget).closest('.js-arbol-lista-item');
    var list = item.find('>.js-arbol-lista');

    if (list.length) {
        if (list.is(':visible')) {
            list.addClass('hidden');
            item.find('.js-arbol-lista-item-cont').first().removeClass('is-open');
        } else {
            list.removeClass('hidden');
            item.find('.js-arbol-lista-item-cont').first().addClass('is-open');
        }
    }
}

function drop2(e) {

    e.preventDefault();
    e.stopPropagation();
    var data = e.dataTransfer.getData("text");

    var des = $(e.target).closest('li');

    if (des.data('tipo') == 'carpeta' || des.hasClass('js-arbol-espacio')) {
        var ori = $('#' + data);
        var parent = ori.closest('[data-nombre]');

        var pathOrigen = getPath2(parent, true);
        var pathDestino;
        var orden;
        var ordenfinal;
        var fn;
        if (des.hasClass('js-arbol-espacio')) {
            pathDestino = getPath2(des.closest('.js-arbol-lista-item'), false);
            orden = des.data('orden');
            ordenfinal = orden == 0 ? 1 : orden;
            fn = function(data) {
                var parent = ori.parent();
                des.after(ori);
                if (!parent.children('.js-arbol-lista-item').length && !parent.children('.js-arbol-lista').length) {
                    parent.remove();
                }

                crearEspaciosDragDrop();
            };
        } else {
            pathDestino = getPath2(des);
            orden = des.children('ul.js-arbol-lista').children('.js-arbol-lista-item').length + 1;
            ordenfinal = orden == 0 ? 1 : orden;
            fn = function(data) {
                var lista = des.find('.js-arbol-lista').first();
                if (!lista.length) {
                    lista = crearLista(des);
                }

                var parent = ori.parent();
                ori.appendTo(lista);
                if (!parent.children('.js-arbol-lista-item').length && !parent.children('.js-arbol-lista').length) {
                    parent.remove();
                }

                crearEspaciosDragDrop();
            };
        }

        var idExpediente = $("#expedienteForm #identificador").val();

        if (ori.attr('id') != des.attr('id')) {
            enviarAjax2(ori.closest('#js-arbol').data('url_elemento_mover'), {
                pathOrigen : pathOrigen,
                pathDestino : pathDestino,
                identificador : ori.data('nombre'),
                orden : ordenfinal,
                idExpediente : idExpediente,
                hidden : true
            }, fn);
        }
    }
    des.removeClass('drag-over');
}

function allowDrop2(e) {
    e.preventDefault();

    var li = $(e.target).closest('.js-arbol-espacio,.js-arbol-lista-item');

    var data = e.dataTransfer.getData("text");
    if (li.attr('id') != data && $(e.target).closest('.js-arbol-lista-item').length) {
        console.log(e.target);
    }

    //console.log(e.target);

    li.addClass('drag-over');
}

function dragleave(e) {

    $(e.currentTarget).removeClass('drag-over');
}

function getNameDocument(texto, natural) {
    if (natural != null && natural !== undefined)
        return natural;
    else
        return texto.substring(13, texto.length);
}

function crearItemLista(par) {
    var patternId = 'tree-node';
    var generateNodeId = function() {
        var num;
        do {
            num = Math.floor((Math.random() * 1000) + 0);
        } while ($('#' + patternId + '-' + num).length);

        return patternId + '-' + num;
    }.bind(this);

    var item = $('<li id="'
            + generateNodeId()
            + '" class="js-arbol-lista-item" '
            + (par.isDocument ? 'data-id="' + par.id + '"' : '')
            + ' data-nombre="'
            + par.texto
            + '" data-tipo="'
            + (par.isDocument ? 'documento' : 'carpeta')
            + '"><div class="js-arbol-lista-item-cont g-arbol-lista-item-cont'
            + (!par.isDocument ? ' is-open' : '')
            + '">'
            + (par.isDocument ? '<span class="mf-icon mf-icon-doc"/> ' : '<span class="mf-icon mf-icon-folder js-arbol-lista-item-folder"/>')
            + '<span class="name' + (par.isDocument ? '' : ' js-arbol-lista-item-text') + '" ' + ' title="' + par.texto
            + '">' + (par.isDocument ? getNameDocument(par.texto, par.natural) : par.texto)
            + '</span><div class="actions">'
            + (par.isDocument ? '<i class="mf-icon mf-icon-eye js-arbol-lista-item-ver" title="ver"></i>' : '')
            + '<i class="mf-icon mf-icon-cancel js-arbol-lista-item-delete" title="borrar"> </i></div></div></li>');
    par.lista && item.appendTo(par.lista);

    par.lista.closest('.js-arbol-lista-item').addClass('has-children');

    item.on('click', onClickItem).on('click', '.js-arbol-lista-item-text', onClickText).on('click',
            '.js-arbol-lista-item-ver', onClickVer).on('click', '.js-arbol-lista-item-delete', onClickDelete).on(
            'click', '.js-arbol-lista-item-folder', onClickFolder).attr('ondrop', "drop2(event)").attr('ondragover',
            "allowDrop2(event)").attr('ondragleave', "dragleave(event)").attr('draggable', "true").attr('ondragstart',
            "drag(event)");

    crearEspaciosDragDrop();

    return item;
}

function generarNombreCarpeta(lista) {
    var patron = 'Nueva Carpeta';
    var texto = patron;
    var num = 1;
    while (lista.children('[data-nombre="' + texto + '"]').length) {
        texto = patron + ' ' + (num++);
    }

    return texto;
}

function crearLista(padre) {
    return $('<ul class="js-arbol-lista"/>').appendTo(padre);
}

function getPath2(parentNode, notUseName) {
    var ret = "";

    var parent = notUseName ? parentNode.parents('.js-arbol-lista-item').first() : parentNode;
    //	var name = parent.data('tipo') == 'carpeta' ? parent.data('nombre') : '';
    var name = parent.data('nombre');
    while (name) {
        ret = name + '/' + ret;
        parent = parent.parents('.js-arbol-lista-item').first();
        name = parent.data('nombre');
    }

    return ret;
}

function enviarAjax2(url, par, callback, callbackError) {
    $.extend(par, {
        expedientEni : $("#contenidoExp").val(),
        idExpediente : $("#expedienteForm #identificador").val()
    });

    $.ajax({
        url : $("#context").val() + url,
        type : 'POST',
        dataType : 'json',
        timeout : 999999,
        data : par,
        success : function(data) {
            if (data.mensajeUsuario != null) {
                deleteLevelMessageStyles();

                var tipo = data.mensajeUsuario.level;
                var mensaje = data.mensajeUsuario.message;
                if (tipo != "" && mensaje != "") {
                    $("#divMessage").removeClass("dsp_n");
                    if (tipo == "1") {
                        $("#divMessage").addClass("msg-success mf-msg__success");
                    } else if (tipo == "2") {
                        $("#divMessage").addClass("msg-info mf-msg__info");
                    } else if (tipo == "3") {
                        $("#divMessage").addClass("msg-warning mf-msg__warning");
                    } else {
                        $("#divMessage").addClass("msg-error mf-msg__error");
                    }
                    $("#mensajeMostrar").text(mensaje);
                }
                callbackError && callbackError(data);
            } else {
                $("#visualizar").val(data.visualizar);

                if (data.expedienteEniConvert != null) {
                    $("#contenidoExp").val(data.expedienteEniConvert);
                }

                if (par.hidden) {
                    $("#actionButtons").hide();
                }
                callback && callback(data);
            }
        },
        error : function(e) {
            console.log(e);
            timerModal.off(parent);
        }
    });

    $('#expedienteVeil').addClass('hidden');
}

function getLista() {
    var arbol = $('#js-arbol');
    var lista = arbol.find('.js-arbol-lista-cont.is-selected').parent();
    var item;
    var par = {
        path : '',
        name : ''
    };
    if (!lista.length) {
        item = arbol.find('.js-arbol-lista-item-cont.is-selected').parent();
        if (item.length) {
            lista = item.find('>.js-arbol-lista');
            if (!lista.length) {
                lista = crearLista(item);
            }
        }
    }

    if (!lista.length) {
        item = undefined;
        lista = arbol.find('>.js-arbol-lista');
    } else {
        par.path = getPath2(lista.closest('.js-arbol-lista-item'));
    }
    if (!lista.length) {
        item = undefined;
        lista = crearLista(arbol);
    }

    return {
        lista : lista,
        par : par,
        item : item
    };
}

function crearCarpeta(e) {
    e.preventDefault();

    var arbol = $('#js-arbol');
    var lista = getLista();

    if (elementoSeleccionadoNoEsDocumento('crear carpeta')) {
        lista.par.name = generarNombreCarpeta(lista.lista);

        enviarAjax2(arbol.data('url_carpeta_nueva'), lista.par, function(data) {
            crearItemLista({
                texto : lista.par.name,
                lista : lista.lista,
                hidden : true
            });
        });
    }
}

function crearEstructuraCarpetas(e) {
    e.preventDefault();

    var arbol = $('#js-arbol');
    var lista = getLista();

    if (elementoSeleccionadoNoEsDocumento('crear carpeta')) {

        enviarAjax2(arbol.data('url_estructura_carpeta'), lista.par, function(data) {
            if (data.mensajeUsuario != null) {
                $("#tipoMensaje").val(data.mensajeUsuario.level);
                $("#valorMensaje").val(data.mensajeUsuario.message);
            } else {
                updateTree(data.newElementsIndex);
                crearEspaciosDragDrop();
            }
        });
    }
}

function onKeyDownInput(e) {
    if (e.keyCode == 27) {
        hideInput();
    } else if (e.keyCode == 13) {
        e.preventDefault();
        e.stopPropagation();
        //closeInput(e);
    }
}
function closeInput(e) {
    hideInput(e.data);
    habilitarTodosBotonesSuperior();
}

function showInput(item) {
    if ($('#js-arbol-input').is(":hidden")) {
        var distanciaRaiz = getDistanciaPadre(item.parents('li').first());
        $('#js-arbol-input').removeClass('is-hidden').val(item.data('nombre')).select().on('blur', null, item,
                closeInput).on('keypress', null, item, onKeyDownInput).css('top', item.position().top + distanciaRaiz);

    }
}

function getDistanciaPadre(itemli) {
    var distancia = 0;
    while (itemli.position() != undefined) {
        distancia = distancia + itemli.position().top;
        itemli = itemli.parents('li').first();
    }
    return distancia;
}

function hideInput(item) {
    var caracteresValidos = "^[a-zA-Z0-9._-\\s]*$";
    var input = $('#js-arbol-input').addClass('is-hidden').off('blur').off('keyup');

    var newName = input.val();

    if (!newName.match(caracteresValidos)) {
        $("#tipoMensaje").val(4);
        $("#valorMensaje").val(
                "Nombre de carpeta incorrecto: " + newName
                        + ". Caracteres permitidos: letras, digitos, y los caracteres .-_");
        showMessage();
    } else {
        var idExpediente = $("#expedienteForm #identificador").val();
        if (item && item.data('nombre') != newName) {
            enviarAjax2($('#js-arbol').data('url_carpeta_renombrar'), {
                path : getPath2(item, true),
                actualName : item.data('nombre'),
                newName : newName,
                idExpediente : idExpediente,
                hidden : true
            }, function(data) {
                if (data.mensajeUsuario != null) {
                    $("#tipoMensaje").val(data.mensajeUsuario.level);
                    $("#valorMensaje").val(data.mensajeUsuario.message);
                } else {
                    item.attr('data-nombre', newName).data('nombre', newName).find('.js-arbol-lista-item-text').first()
                            .html(newName).attr('title', newName);
                }
            });
        }
    }

}

/************************************************************************************************/
function superponer_datepicker() {

    $("div.datepicker:last").css('zIndex', 10000);
}

function createButtonsDialog(buttons) {

    var ret = $('<ul class="mf-buttonbar">');

    $(buttons).each(
            function(k, v) {
                $(
                        '<li class="mf-buttonbar--item"><button ' + (v.submit ? 'type="submit"' : '')
                                + ' class="buttonbar--btn ' + v.clase + '" href="#!">' + v.texto + '</a></li>')
                        .appendTo(ret);
            });

    return ret;
}

function showFolderDialog(e) {
    e.preventDefault();
    var parentNode = $(e.currentTarget).parents('.item-actions').first().parent();
    var divData = $('#init-modal-crear-carpeta');
    var data = divData.data();
    var buttons = createButtonsDialog([ {
        clase : 'js-carpeta-cancelar',
        texto : 'Cancelar'
    }, {
        clase : 'js-carpeta-crear',
        texto : 'Crear',
        submit : true
    } ]);

    buttons.find('.js-carpeta-crear').on('click', function(e) {
        e.preventDefault();

        var dialog = $(e.currentTarget).parents('.mf-folder-dialog').first();

        if (validateForm(dialog)) {
            createFolder({
                path : getPath(parentNode),
                name : $('.mf-dialog #nombre_carpeta_modal').val()
            });

            $mf.my_dialog.close_dialog();
        }
    });
    buttons.find('.js-carpeta-cancelar').on('click', function(e) {
        e.preventDefault();
        $mf.my_dialog.close_dialog();
    });
    data.buttons = buttons;
    data.data = divData.html();
    data.init_dialog = initDialogFields;

    $mf.my_dialog.appendDialog(data, true, true);
}

function formatResult(tr) {
    var pattern = ';base64,';
    return tr.substring(tr.indexOf(pattern) + pattern.length, tr.length);
}

function activateActions() {
    $(".item-actions").each(function() {
        var item_actions_list = $(this);
        $(this).parent().find('>span').mouseenter(function() {
            showActionsList(item_actions_list);
        });
    });
}

function showActionsList(item_actions_list) {
    if (!$(item_actions_list).hasClass("item-actions")) {
        return false;
    }
    var folder_container = $(item_actions_list).parent();

    if (folder_container.hasClass("activeActions")) {
        return;
    } else {
        hideActionsList(folder_container.parent().parent().children(".item-actions"));
        if (folder_container.hasClass("folder_expanded") || folder_container.hasClass("folder_empty")
                || item_actions_list.siblings('span').first().attr('id') == 'exp-title') {
            folder_container.addClass("activeActions");
            item_actions_list.css('display', 'inline');
        }
        folder_container.mouseleave(function() {
            hideActionsList(item_actions_list);
            showActionsList(folder_container.parent().parent().children(".item-actions"));
        });
    }
}

function deactivateActions(folder_container) {

    $(".item-actions").hide().parent().unbind();
}

function hideActionsList(item_actions_list) {
    if (!$(item_actions_list).hasClass("item-actions")) {
        return false;
    }
    var folder_container = $(item_actions_list).parent();

    folder_container.removeClass("activeActions");
    item_actions_list.hide();
}

function showRemoveDialog(e) {
    e.preventDefault();
    var parentNode = $(e.currentTarget).parents('.item-actions').first().parent();
    var buttons = createButtonsDialog([ {
        clase : 'js-eliminar-cancelar',
        texto : 'Cancelar'
    }, {
        clase : 'js-eliminar-elemento',
        texto : 'Eliminar',
        submit : true
    } ]);
    //var buttons = $("<span><a class='mf-button-em t new_folder_btn js-eliminar-elemento'><span class='ui-icon ui-icon-folder-collapsed'></span>Eliminar</a><a class='mf-button-em t new_folder_btn js-eliminar-cancelar'><span class='ui-icon ui-icon-folder-collapsed'></span>Cancelar</a></span>");
    buttons.find('.js-eliminar-elemento').on('click', function(e) {
        e.preventDefault();

        removeItem({
            pathOrigen : getPath(parentNode.parents('li').first()),
            identificador : parentNode.data('name')
        });

        $mf.my_dialog.close_dialog();
    });
    buttons.find('.js-eliminar-cancelar').on('click', function(e) {
        e.preventDefault();
        $mf.my_dialog.close_dialog();
    });
    var data = {
        buttons : buttons,
        data : 'Â¿EstÃ¡ seguro que desea eliminar el elemento?'
    };

    $mf.my_dialog.appendDialog(data, true, true);
}

function getPath(parentNode) {
    var ret = "";

    var parent = parentNode;
    var name = parent.data('name');
    while (name) {
        ret = name + '/' + ret;
        parent = parent.parents('li').first();
        name = parent.data('name');
    }

    return ret;
}

function createFolder(par) {

    enviarAjax("/crearCarpetaIndice", par);
}

function createDocument(par) {

    enviarAjax("/crearDocumentoIndice", par, $mf.my_dialog.$el, true);
}

function removeItem(par) {

    enviarAjax("/borrarElementoIndice", par);
}

function getNameFolder(par) {
    if (par.natural == null)
        return par.texto;
    else
        return par.natural;
}

function updateTree(data, parent) {
    if (!parent) {
        var lista = getLista();
        parent = lista.lista;
    }

    var createNode = function(par) {
        var patternId = 'tree-node';
        var generateNodeId = function() {
            var num;
            do {
                num = Math.floor((Math.random() * 1000) + 0);
            } while ($('#' + patternId + '-' + num).length);

            return patternId + '-' + num;
        }.bind(this);

        var item;

        if (par.isDocument) {
            item = $('<li id="'
                    + generateNodeId()
                    + '" class="js-arbol-lista-item" data-id="'
                    + par.id
                    + '"'
                    + ' data-nombre="'
                    + par.texto
                    + '" data-tipo="documento"><div class="js-arbol-lista-item-cont g-arbol-lista-item-cont"> '
                    + ' <span class="mf-icon mf-icon-doc"/><span class="name" title="'
                    + par.texto
                    + '">'
                    + par.natural
                    + '</span><div class="actions"><i class="mf-icon mf-icon-eye js-arbol-lista-item-ver" title="ver"></i>'
                    + '<i class="mf-icon mf-icon-cancel js-arbol-lista-item-delete" title="borrar"> </i></div></div></li>');
        } else {
            item = $('<li id="'
                    + generateNodeId()
                    + '" class="js-arbol-lista-item" data-id="'
                    + par.id
                    + '"'
                    + ' data-nombre="'
                    + par.texto
                    + '" data-tipo="carpeta"><div class="js-arbol-lista-item-cont g-arbol-lista-item-cont is-open"> '
                    + '<span class="mf-icon mf-icon-folder js-arbol-lista-item-folder"/><span class="name js-arbol-lista-item-text" title="'
                    + par.texto
                    + '">'
                    + getNameFolder(par)
                    + '</span><div class="actions">'
                    + '<i class="mf-icon mf-icon-cancel js-arbol-lista-item-delete" title="borrar"> </i></div></div></li>');
        }

        par.parent && item.appendTo(par.parent);

        par.parent.closest('.js-arbol-lista-item').addClass('has-children');

        item.on('click', onClickItem).on('click', '.js-arbol-lista-item-text', onClickText).on('click',
                '.js-arbol-lista-item-ver', onClickVer).on('click', '.js-arbol-lista-item-delete', onClickDelete).on(
                'click', '.js-arbol-lista-item-folder', onClickFolder).attr('ondrop', "drop2(event)").attr(
                'ondragover', "allowDrop2(event)").attr('ondragleave', "dragleave(event)").attr('draggable', "true")
                .attr('ondragstart', "drag(event)");

        return item;
    }.bind(this);

    $(data).each(function(k, v) {
        var listParent = parent.find('>.js-arbol-lista');
        if (!listParent.length) {
            listParent = $('<ul class="js-arbol-lista">').appendTo(parent);
        }

        var par;

        if (v.identificadorDocumento != null) {
            par = {
                id : v.identificadorDocumento,
                texto : v.identificadorDocumento,
                natural : v.id,
                parent : listParent,
                isDocument : true,
            };
        } else if (v.identificadorCarpeta != null) {
            par = {
                texto : v.identificadorCarpeta,
                id : v.identificadorCarpeta,
                natural : v.id,
                parent : listParent,
                isDocument : false,
            };
        }

        var node = createNode(par);

        if (!par.isDocument) {
            updateTree(v.documentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada, node);
        }
    });
}

function allowDrop(e) {

    e.preventDefault();
}

function drag(e) {

    e.dataTransfer.setData("text", e.target.id);
}

function drop(e) {

    e.preventDefault();
    e.stopPropagation();
    var data = e.dataTransfer.getData("text");

    var ori = $('#' + data);
    var des = $(e.target).prop("tagName") == 'LI' ? $(e.target) : $(e.target).parents('li').first();
    var parent = ori.parents('[data-name]').first();

    var pathOrigen = getPath(parent);
    var pathDestino = getPath(des);

    if (ori.attr('id') != des.attr('id')) {
        enviarAjax("/moverElementoIndice", {
            pathOrigen : pathOrigen,
            pathDestino : pathDestino,
            identificador : ori.data('name')
        });
    }
}

var timerModal = {
    getTimer : function($el) {
        var timer = $el && $el.find('.js-timer-veil');
        if (timer && timer.length) {
            return timer;
        }
        return $('.js-timer-veil');
    },
    on : function($el) {
        this.getTimer($el).removeClass('hidden');
    },
    off : function($el) {
        this.getTimer($el).addClass('hidden');
    }
};

function enviarAjax(url, par, parent, closeDialog) {
    $.extend(par, {
        expedientEni : $("#contenido").val()
    });

    timerModal.on(parent);

    $.ajax({
        url : $("#context").val() + url,
        type : 'POST',
        dataType : 'json',
        timeout : 999999,
        data : par,
        success : function(data) {
            if (data.mensajeUsuario != null) {
                deleteLevelMessageStyles();

                var tipo = data.mensajeUsuario.level;
                var mensaje = data.mensajeUsuario.message;
                if (tipo != "" && mensaje != "") {
                    $("#divMessage").removeClass("dsp_n");
                    if (tipo == "1") {
                        $("#divMessage").addClass("msg-success");
                        messages.success(mensaje, 5000);
                        console.log('1');
                    } else if (tipo == "2") {
                        $("#divMessage").addClass("msg-info");
                        messages.log(mensaje, 5000);
                        console.log('2');
                    } else if (tipo == "3") {
                        $("#divMessage").addClass("msg-warning");
                        messages.log(mensaje, 5000);
                        console.log('3');
                    } else {
                        $("#divMessage").addClass("msg-error");
                        messages.error(mensaje, 5000);
                        console.log('4');
                    }
                    $("#mensajeMostrar").text(mensaje);
                }
            } else {
                $("#visualizar").val(data.visualizar);

                if (data.expedienteEniConvert != null) {
                    $("#contenido").val(data.expedienteEniConvert);
                    $("#contenidoExp").val(data.expedienteEniConvert);
                }

                updateTree(sortData(data.indice));

                $("#actionButtons").hide();
            }

            timerModal.off(parent);

            if (closeDialog) {
                $mf.my_dialog.close_dialog();
            }
        },
        error : function(e) {
            console.log(e);
            timerModal.off(parent);

            if (closeDialog) {
                $mf.my_dialog.close_dialog();
            }
        }
    });
}

function sortData(data) {
    var ret = [], fol = {}, doc = {}, folS = [], docS = [];

    $(data)
            .each(
                    function(k, v) {
                        if (v.documentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada instanceof Array) {

                            v.documentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada = sortData(v.documentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada);

                            fol[v.identificadorCarpeta] = v;
                            folS.push(v.identificadorCarpeta);
                        } else {
                            doc[v.identificadorDocumento] = v;
                            docS.push(v.identificadorDocumento);
                        }
                    });
    folS = folS.sort();
    docS = docS.sort();

    $(folS).each(function(k, v) {
        ret.push(fol[v]);
    });
    $(docS).each(function(k, v) {
        ret.push(doc[v]);
    });

    return ret;
}

function validateForm($el) {
    var ret = true;

    $el.find('.fld').removeClass('error');
    var list = $el.find('.error-list').empty();

    $el.find('.js-no-empty').each(function(k, v) {
        var $v = $(v);
        if (!$v.val()) {
            ret = false;
            showErrorMessage('Es necesario rellenar el campo \'' + getLabel($v) + '\'', list);
            $v.parents('.fld').addClass('error');
        }
    });

    return ret;
}

function getLabel($el) {
    var id = $el.attr('id');
    var parent = $el.parents('.mf-dialog--content').first();
    var label = parent.find('[for=' + id + ']');
    return label.html();
}

function initDialogFields($el) {
    var fechaInit = $.datepicker.formatDate("dd-mm-yy", new Date());
    $el.find('.js-datepicker-today').val(fechaInit);

    $el.find('.js-number').on('keydown', function(e) {
        console.log(e.keyCode);
        var validKeys = [ 8, 46, 37, 38, 39, 40 ];
        if (isNaN(e.key) && validKeys.indexOf(e.keyCode) < 0) {
            return false;
        }
    });

    $el.find('.js-submit').on('keydown', function(e) {
        console.log(e);
        if (e.keyCode == 13) {
            $(e.currentTarget).parents('.mf-dialog').find('[type="submit"]').click();
        }
    });
}

function showErrorMessage(text, list) {

    $('<li>' + text + '</li>').appendTo(list);
}
