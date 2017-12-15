/*Para previsualizar las imagenes*/
/*http://stackoverflow.com/questions/17339899/plupload-html5-preview-after-fileselect*/

var uploadersPlupload = function (data_upload) {

    window.dsic_upload_data = data_upload;

    window.uploader = new plupload.Uploader(data_upload);

    uploader.init();
};

function bytesToSize(bytes) {
    var sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB'];
    if (bytes == 0) return 'n/a';
    var i = parseInt(Math.floor(Math.log(bytes) / Math.log(1024)));
    return Math.round(bytes / Math.pow(1024, i), 2) + ' ' + sizes[i];
}


function deleteFileAction(e, url,id_uploader_object) {

    $li_file = $(e.currentTarget).closest('li');

    $.get(url)
        .done(function (response) {

            if (response.success) {
                $li_file.remove();
                $("#subir_"+id_uploader_object).show();
            }

            //alert(response.message);
        })
        .fail(function () {
            alert("Error al borrar el archivo");
        });
}

function cancelFileAction(e, data_json) {

    var file = data_json.file;
    var up = data_json.up;
    var status_before = file.status;

    up.removeFile(file);
    if (up.state == plupload.STARTED && status_before == plupload.UPLOADING) {
        up.stop();
        up.start();
    }

    $(e.currentTarget).closest('li').remove();
    alert("Subida cancelada");

}

function parseResponse(res) {

    try {
        if (!res.response.length) {
            return false;
        }
        return  $.parseJSON(res.response);

    } catch (e) {
        return false;
    }

}

uploadersPlupload.init = function ($el) {

    var aux;
    if ($el) {
        if ($el.hasClass('js-upload-data')) {
            aux = $el;
        } else {
            aux = $el.find('.js-upload-data');
        }
    } else {
        aux = $('.js-upload-data');
    }

    aux.each(function (k, v) {

        var data = $(v).data();

        data.url = $("#context").val() + data.url;

        new uploadersPlupload(data);
    });
};

$(document).ready(function () {
    uploadersPlupload.init();
});
