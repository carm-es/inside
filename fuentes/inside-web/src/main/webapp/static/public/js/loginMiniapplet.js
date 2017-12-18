$(document).ready(function() {
    // Se carga el miniapplet
    MiniApplet.cargarMiniApplet(baseDownloadURL);
});

function end_authentication(callback_method) {
	var format = "XAdES";
	var algorithm = "SHA1withRSA";
	// token aleatorio
	var text = "" + Math.random() * 11;
	var signature;
	var error = "";

	try {
		var dataB64 = MiniApplet.getBase64FromText(text, null);
		signature = MiniApplet.sign(dataB64, algorithm, format, null);

	} catch (e) {
		if (MiniApplet.getErrorType() == 'es.gob.afirma.core.AOCancelledOperationException') {
			error = "operationCancelled";
		} else if (MiniApplet.getErrorType() == 'es.gob.afirma.keystores.common.AOCertificatesNotFoundException') {
			error = "No se ha encontrado certificado en el almacen";
		} else {
			// alert("Por favor, copie salida de la consola de Java en este
			// punto");
			error = MiniApplet.getErrorMessage();
		}
	}
	eval(callback_method + "(signature,error);");
}

function authenticate(callback_method) {
	end_authentication(callback_method);
	$("#loginCertificateForm").submit();
}

function fillCerticate(signature,error) {
	$("#signature").val(signature);
	$("#error").val(error);
}