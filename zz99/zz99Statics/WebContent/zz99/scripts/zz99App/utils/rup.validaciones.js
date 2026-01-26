
initRupI18nPromise.then(function() {
	jQuery.validator.addMethod("dniNiePasaporte", function(value, element, params) {
		var tipo = $("#tipoIdentificacion_familiar_detalle").val();
		return this.optional(element) || validarIdentificacion(tipo, value);
	}, jQuery.rup.i18nParse($.rup.i18n.app.validaciones, "dniNiePasaporte"));

	jQuery.validator.addMethod("provinciaRequired", function(value, element, params) {
		var pais = $("#paisNaci_familiar_detalle").val();
		return (pais === '108' && value !== '');
	}, jQuery.rup.i18nParse($.rup.i18n.app.validaciones, "provinciaRequired"));

	jQuery.validator.addMethod("municipioRequired", function(value, element, params) {
		var prov = $("#provNaci_familiar_detalle").val();
		return (prov !== '' && value !== '');
	}, jQuery.rup.i18nParse($.rup.i18n.app.validaciones, "municipioRequired"));

	jQuery.validator.addMethod("municipioNacimientoRequired", function(value, element, params) {
		var prov = $("#provNaci_detalle").val();
		return (prov !== '' && value !== '') || (prov === '' && value === '');
	}, jQuery.rup.i18nParse($.rup.i18n.app.validaciones, "municipioRequired"));

	jQuery.validator.addMethod("provinciaResidenciaRequired", function(value, element, params) {
		var pais = $("#paisResi_detalle").val();
		return (pais === '108' && value !== '');
	}, jQuery.rup.i18nParse($.rup.i18n.app.validaciones, "provinciaRequired"));

	jQuery.validator.addMethod("municipioResidenciaRequired", function(value, element, params) {
		var prov = $("#provResi_detalle").val(); 
		return (prov !== '' && value !== '') || (prov === '' && value === '');
	}, jQuery.rup.i18nParse($.rup.i18n.app.validaciones, "municipioRequired"));

	jQuery.validator.addMethod("provinciaNacimientoRequired", function(value, element, params) {
		var pais = $("#paisNaci_detalle").val();
		return (pais === '108' && value !== '');
	}, jQuery.rup.i18nParse($.rup.i18n.app.validaciones, "provinciaRequired"));
	
	jQuery.validator.addMethod("emailsSeparadosPorComas", function(value, element) {
		var emails = value.split(",");
		for (var i = 0; i < emails.length; i++) {
			if (!jQuery.validator.methods.email.call(this, $.trim(emails[i]), element)) {
				return false;
			}
		}
		return true;
	}, jQuery.rup.i18nParse($.rup.i18n.app.validaciones, "emailPorComas"));

});

function validarIdentificacion(tipo, valor) {
	let regex;
	switch (tipo) {
		case '1':
			return nif(valor);
		case '2':
			return nie(valor);
		case '3':
			regex = /^[A-Z0-9]+$/;  // Combinación de letras y números
			break;
		default:
			return false;  // Tipo no reconocido
	}
	return regex.test(valor);
}

function nif(dni) {
	if (dni.length === 9) {
		var numero = dni.substr(0, 8);
		numero = numero % 23;
		var letra = 'TRWAGMYFPDXBNJZSQVHLCKET';
		letra = letra.substring(numero, numero + 1);
		if (letra != (dni.substr(dni.length - 1, dni.length)).toUpperCase()) {
			return false;
		} else {
			return true;
		}
	} else {
		return false;
	}
}

function nie(valor){
	 var validChars = 'TRWAGMYFPDXBNJZSQVHLCKET';
	 var nieRexp = /^[XYZ]{1}[0-9]{7}[TRWAGMYFPDXBNJZSQVHLCKET]{1}$/i;
	 var str = valor.toString().toUpperCase();

	 if (!nieRexp.test(str)) return false;

	 var nie = str
	      .replace(/^[X]/, '0')
	      .replace(/^[Y]/, '1')
	      .replace(/^[Z]/, '2');

	 var letter = str.substr(-1);
	 var charIndex = parseInt(nie.substr(0, 8)) % 23;

	 if (validChars.charAt(charIndex) === letter) return true;

	 return false;
}