/*!
 * Copyright 2019 E.J.I.E., S.A.
 *
 * Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
 * Solo podrá usarse esta obra si se respeta la Licencia.
 * Puede obtenerse una copia de la Licencia en
 *
 *      http://ec.europa.eu/idabc/eupl.html
 *
 * Salvo cuando lo exija la legislación aplicable o se acuerde por escrito, 
 * el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
 * SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
 * Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
 * que establece la Licencia.
 */

jQuery(document).ready(function() {
	//rastro de migas
	$("#zz99PracticaWar_migas").rup_breadCrumb({
		breadCrumb: {}
	});

	//idioma
	$("#zz99PracticaWar_language").rup_language({ languages: $.rup.AVAILABLE_LANGS_ARRAY, modo: "default" });

	//NAVBAR Menu
	if ($("#navbarResponsive").length > 0) {
		$("#navbarResponsive").rup_navbar({
			sticky_nav: false
		});
	}

	initRupI18nPromise.then(function() {
		jQuery.extend(true, jQuery.rup.i18n.base.rup_combo, { blankNotDefined: $.rup.i18nParse($.rup.i18n.app.operacion, 'seleccionar') });
	});

	//pie
	$(".footer [title]").rup_tooltip();

	$(document).on('click', 'a#rupTableButtonsReportsExport', function() {
	    var elemento = $('a#rupTableButtonsReportsExport')[0];
	    var texto = elemento.download;
	    texto = texto.replaceAll('"', '')
	    elemento.download = texto
	});

});