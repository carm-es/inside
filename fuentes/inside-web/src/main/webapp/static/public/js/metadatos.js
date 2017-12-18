function addMetadatoExp(metadatoName, metadatoValue) {
	if ($('#keyMetadatoAdicional').val() && $('#valueMetadatoAdicional').val()) {
		if (typeof(metadatosAdded) === 'undefined') {
			window.metadatosAdded = 2;
		}	
		//se añade un input key y otro input valor de metadato 
		var key_input = document.createElement("input");
		var value_input = document.createElement("input");
		var remove_button = document.createElement("button");
		var div = document.createElement("div");	
		
		key_input.setAttribute("type", "text");
		key_input.setAttribute("id", "metadatosAdicionales.metadatoAdicional" + metadatosAdded + ".nombre");
		key_input.setAttribute("name", "metadatosAdicionales.metadatoAdicional[" + metadatosAdded + "].nombre");
		key_input.setAttribute("class", "sticked-input-right");
		key_input.setAttribute("disabled", "disabled");
		key_input.setAttribute("value", $('#keyMetadatoAdicional').val());
		key_input.setAttribute("style", "width:250px");
		value_input.setAttribute("type", "text");
		value_input.setAttribute("name", "metadatosAdicionales.metadatoAdicional[" + metadatosAdded + "].valor");
		value_input.setAttribute("id", "metadatosAdicionales.metadatoAdicional" + metadatosAdded + ".valor");
		value_input.setAttribute("class", "sticked-input-right");
		value_input.setAttribute("disabled", "disabled");
		value_input.setAttribute("value", $('#valueMetadatoAdicional').val());
		value_input.setAttribute("style", "width:200px");
		remove_button.setAttribute("type", "button");
		remove_button.setAttribute("name", "button");
		remove_button.setAttribute("class", "mf-icon mf-icon-cancel2");
		remove_button.setAttribute("id", "removeMetadatoButton_" + metadatosAdded);
		remove_button.setAttribute("value", "Eliminar");
		remove_button.setAttribute("onclick", "removeMetadatoExp(" + metadatosAdded+")");
		div.setAttribute("id", "divMetadatoAdicional_" + metadatosAdded);
			
		$("#listMetadatosAdicionalesExp").append(div);	
		
		var divNombre = document.createElement("div");
		divNombre.setAttribute("id", "divNombre_" + metadatosAdded);
		divNombre.setAttribute("class", "fld");
		var divValor = document.createElement("div");
		divValor.setAttribute("id", "divValor_" + metadatosAdded);
		divValor.setAttribute("class", "fld");
		var divButton = document.createElement("div");
		divButton.setAttribute("id", "divButton_" + metadatosAdded);
		divButton.setAttribute("class", "fld");
		
		$("#divMetadatoAdicional_" + metadatosAdded).append(divNombre);
		$("#divMetadatoAdicional_" + metadatosAdded).append(divValor);
		$("#divMetadatoAdicional_" + metadatosAdded).append(divButton);
		
		$("#divNombre_" + metadatosAdded).append(key_input);
		$("#divValor_" + metadatosAdded).append(value_input);	
		$("#divButton_" + metadatosAdded).append(remove_button);	
		
		$('#keyMetadatoAdicional').val('');
		$('#valueMetadatoAdicional').val('');	
		metadatosAdded++;	
	}
}

function removeMetadatoExp (position){
	
	$("#metadatosAdicionales.metadatoAdicional" + position + ".nombre").remove();
	$("#metadatosAdicionales.metadatoAdicional" + position + ".valor").remove();
	$("#removeMetadatoButton_" + position).remove();
	$("#divMetadatoAdicional_" + position).remove();
	
	
	
	while(metadatosAdded-1 > position) {
		var aux = position + 1;
		
		$("#metadatosAdicionales.metadatoAdicional" + aux + ".nombre").setAttribute("name", "metadatosAdicionales.metadatoAdicional[" + position + "].nombre");
		$("#metadatosAdicionales.metadatoAdicional" + aux + ".nombre").setAttribute("id", "metadatosAdicionales.metadatoAdicional" + position + ".nombre");
		$("#metadatosAdicionales.metadatoAdicional" + aux + ".valor").setAttribute("name", "metadatosAdicionales.metadatoAdicional[" + position + "].valor");
		$("#metadatosAdicionales.metadatoAdicional" + aux + ".valor").setAttribute("id", "metadatosAdicionales.metadatoAdicional" + position + ".valor");

		
		$("#removeMetadatoButton_" + aux).attr("onclick", "removeMetadatoDoc(" + position + ")");
		$("#removeMetadatoButton_" + aux).attr("id", "removeMetadatoDoc_" + position);
		$("#divMetadatoAdicional_" + aux).attr("id", "divMetadatoAdicional_" + position);
		position++;
	}
	metadatosAdded--;
}


function campoSinCaracteresEspeciales(formulario, campo, entrada)
{
	var caracteresValidos = "^[a-zA-Z0-9._-]*$";
	var logitudMaxima = "^[a-zA-Z0-9._-]{0,52}$"
	if(!entrada.value.match(caracteresValidos)){
		devuelveMensajeValidacionIdentificador(formulario, campo, entrada, 'caracteresMal');
	} else if(!entrada.value.match(logitudMaxima)) {
		devuelveMensajeValidacionIdentificador(formulario, campo, entrada, 'longitudMal');
	}
}

function devuelveMensajeValidacionIdentificador(formulario, campo, entrada, mensaje) {
	
	var nombreAcumulado = entrada.value;
	var ultimoChar = nombreAcumulado.substr(nombreAcumulado.length - 1, nombreAcumulado.length);
	var nombreAcumuladoMenosUltimoChar = nombreAcumulado.substr(0, (nombreAcumulado.length - 1));
	$("#"+formulario+" #"+campo+"").val(nombreAcumuladoMenosUltimoChar);
	$("#tipoMensaje").val(4);
	if(mensaje === 'caracteresMal') {
		$("#valorMensaje").val("El caracter especial: " + ultimoChar + " no se admite en el campo: "+campo);
	} else if(mensaje === 'longitudMal') {
		$("#valorMensaje").val("La longitud máxima es 52 caracteres");
	}
	showMessage();
	
}
