$(document).ready(function() {
    // Se carga el miniapplet
    MiniApplet.cargarMiniApplet(baseDownloadURL);
});

function signCorrect(signature) {

    var error = "";
    var callback_method = sessionStorage.getItem('callback_method');

    eval(callback_method + "(signature,error);");
    loginCertificate();
}

function signError(errorType, errorMsg) {

    var signature = "";
    var error = "";
    var callback_method = sessionStorage.getItem('callback_method');

    if (errorType == 'es.gob.afirma.core.AOCancelledOperationException') {
        error = "operationCancelled";
    } else if (MiniApplet.getErrorType() == 'es.gob.afirma.keystores.common.AOCertificatesNotFoundException') {
        error = "No se ha encontrado certificado en el almacen";
    } else {
        // alert("Por favor, copie salida de la consola de Java en este
        // punto");
        error = errorMsg;
    }

    eval(callback_method + "(signature,error);");
    loginCertificate();
}

function end_authentication(callback_method) {
    var format = "XAdES";
    var algorithm = "SHA1withRSA";
    // token aleatorio
    var text = "" + Math.random() * 11;

    var dataB64 = MiniApplet.getBase64FromText(text, null);
    sessionStorage.setItem('callback_method', callback_method);

    MiniApplet.sign(dataB64, algorithm, format, null, signCorrect, signError);
}

function authenticate(callback_method) {
    end_authentication(callback_method);

}

function fillCerticate(signature, error) {
    $("#signature").val(signature);
    $("#error").val(error);
}

function loginCertificate() {
    $("#loginCertificateForm").submit();
}
