$(document).ready(function() {
	showMessage();
});

// muestra mensaje de usuario en la pantalla
function showMessage() {

	deleteLevelMessageStyles();
	
	var tipo = $("#tipoMensaje").val();
	var mensaje = $("#valorMensaje").val();
	if (mensaje == "") {
		$(".errorsclass").each(
		function(k, v) {
			if (mensaje != "") {
				mensaje += "<br/>";
				mensaje += "<br/>";
			}
			mensaje += v.textContent.replace(/\n/g, "<br/>");
		});
	}
	
	if (tipo != ""
		&& mensaje != "") {
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
		$("#mensajeMostrar").html(mensaje);
	}
}

// oculta el mensaje de usuario en la pantalla
function ocultarMensaje() {
	deleteLevelMessageStyles();
	deleteErrorMessages();
	$("#divMessage").addClass("dsp_n");
}

// borra los estilos por niveles definidos para los mensajes de usuario
function deleteLevelMessageStyles() {
	$("#divMessage").removeClass("msg-success mf-msg__success msg-info mf-msg__info msg-warning mf-msg__warning msg-error mf-msg__error");
}


// borra los mensajes y estilos de error de la pantalla
function deleteErrorMessages() {
	$("input").removeClass("error");
	$("label[class=error]").remove();
}