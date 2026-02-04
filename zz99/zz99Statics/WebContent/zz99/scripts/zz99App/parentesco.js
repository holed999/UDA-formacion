jQuery(function($) {

	$("#menu_tablas").addClass('active');

	// ===== AÑADIDO: Función para añadir el botón a la toolbar =====
	function añadirBotonAToolbar() {
		// Buscar la toolbar
		var toolbar = $('#parentesco').prev('.rup-toolbar, .dt-buttons');
		if (!toolbar.length) {
			toolbar = $('.rup-toolbar, .dt-buttons').first();
		}
		if (!toolbar.length) return;

		// Crear el botón
		var nuevoBoton = $('<button>', {
			id: 'btnBajaMultiple',
			class: 'btn-material btn-material-primary-high-emphasis',
			html: '<i class="mdi mdi-archive"></i> <span>BAJA MÚLTIPLE</span>',
			click: function() {
				// Llamar a la función de baja múltiple
				iniciarBajaMultiple();
			}
		}).css({
			marginLeft: '10px',
			backgroundColor: '#8B008B',
			borderColor: '#8B008B',
			color: 'white'
		});

		// Buscar el botón de eliminar para insertar después
		var botonEliminar = toolbar.find('#btnBorrar, [id*="eliminar"], [id*="delete"]');
		if (botonEliminar.length) {
			botonEliminar.after(nuevoBoton);
		} else {
			toolbar.append(nuevoBoton);
		}
	}

	// ===== AÑADIDO: Función para iniciar la baja múltiple =====
	function iniciarBajaMultiple() {
		// Obtener registros seleccionados
		var seleccionados = obtenerRegistrosSeleccionados();
		
		if (seleccionados.length === 0) {
			alert("Por favor, selecciona al menos un registro de la tabla.");
			return;
		}
		
		// Verificar que todos los seleccionados estén en alta
		var todosEnAlta = true;
		var registrosEnBaja = [];
		
		for (var i = 0; i < seleccionados.length; i++) {
			var registro = seleccionados[i];
			if (registro.situacion === 'B') {
				todosEnAlta = false;
				registrosEnBaja.push(registro.identificador);
			}
		}
		
		if (!todosEnAlta) {
			var mensajeError = "Algunos registros ya están dados de baja:\n";
			mensajeError += registrosEnBaja.join(", ");
			mensajeError += "\n\nSolo se pueden dar de baja registros en situación 'Alta'.";
			alert(mensajeError);
			return;
		}
		
		// Mostrar confirmación
		var mensaje = "Vas a dar de baja " + seleccionados.length + " registro(s):\n\n";
		var identificadores = seleccionados.map(function(r) { return r.identificador; });
		mensaje += "Identificadores: " + identificadores.join(", ") + "\n\n";
		mensaje += "¿Estás seguro de continuar?";
		
		if (confirm(mensaje)) {
			realizarBajaMasiva(identificadores);
		}
	}

	// ===== AÑADIDO: Obtener registros seleccionados =====
	function obtenerRegistrosSeleccionados() {
		var registros = [];
		var tabla = $('#parentesco');
		
		try {
			// Intentar con la API de selección de rup_table
			var seleccionados = tabla.rup_table('getSelectedRows');
			if (seleccionados && seleccionados.length > 0) {
				return seleccionados;
			}
			
		} catch (e) {
			console.log("Error al obtener selección:", e);
		}
		
		// Método alternativo: buscar filas seleccionadas
		tabla.find('tbody tr.selected, tbody tr.rup_table_selected').each(function() {
			var identificador = $(this).find('td:eq(0)').text().trim();
			var situacionTexto = $(this).find('td:eq(3)').text().trim();
			
			// Convertir situación de texto a código
			var situacionCodigo = 'A';
			if (situacionTexto === $.rup.i18nParse($.rup.i18n.app, "situacion.baja")) {
				situacionCodigo = 'B';
			}
			
			registros.push({
				identificador: parseInt(identificador),
				situacion: situacionCodigo
			});
		});
		
		return registros;
	}

	// ===== AÑADIDO: Función para realizar la baja masiva =====
	function realizarBajaMasiva(identificadores) {
		// Bloquear pantalla mientras se procesa
		bloquearPantalla($.rup.i18nParse($.rup.i18n.app, "comun.guardando"));
		
		// Llamada al endpoint de baja múltiple
		$.ajax({
			url: 'parentesco/bajaMultiple',
			method: 'PUT',
			contentType: 'application/json',
			data: JSON.stringify(identificadores),
			success: function(response) {
				desbloquearPantalla();
				
				if (response && response.success === true) {
					alert(response.message || "Baja múltiple completada correctamente.");
					// Recargar la tabla
					$('#parentesco').rup_table('reload');
				} else {
					alert("Error: " + (response.message || "Error desconocido en el servidor"));
				}
			},
			error: function(xhr, status, error) {
				desbloquearPantalla();
				var errorMsg = "Error en la solicitud: ";
				if (xhr.responseJSON && xhr.responseJSON.message) {
					errorMsg += xhr.responseJSON.message;
				} else {
					errorMsg += error;
				}
				alert(errorMsg);
			}
		});
	}

	var miColModel = [
		{
			name: 'identificador',
			index: 'identificador',
			hidden: false
		},
		{
			name: 'descEs',
			index: 'descEs',
			editable: true
		},
		{
			name: 'descEu',
			index: 'descEu',
			editable: true
		},
		{
			name: 'situacion',
			index: 'situacion',
			editable: true
		},
		{
			name: 'fecha',
			index: 'fecha',
			editable: true
		}

	];

	$("#parentesco").rup_table({
		colModel: miColModel,
		columnDefs: [
			{
				"targets": 0
				, responsivePriority: 0
				, "render": function(data, type, row) {
					return (!data ? "" : data);
				}
			}
			, {
				"targets": 1
				, responsivePriority: 5
				, "render": function(data, type, row) {
					return (!data ? "" : data);
				}
			}
			, {
				"targets": 2
				, "render": function(data, type, row) {
					return (!data ? "" : data);
				}
			}
			, {
				"targets": 3
				, "render": function(data, type, row) {
					switch (data) {
						case 'A':
							return jQuery.rup.i18nParse($.rup.i18n.app, "situacion.alta");
							break;
						case 'B':
							return jQuery.rup.i18nParse($.rup.i18n.app, "situacion.baja");
							break;
						default:
							return data;
					}
				}
			}, {
				"targets": 4
				, "render": function(data, type, row) {
					return (!data ? "" : data);
				}
			}

		],
		formEdit: {
			detailForm: "#parentesco_detail_div",
			fillDataMethod: "clientSide",
			propertiesValidate: {
				liveCheckingErrors:true
			},
			validate: {
				rules: {
					"descEs": {
						required: true
						, maxlength: 200
					}
					,
					"descEu": {
						required: true
						, maxlength: 200
					}
					,
					"situacion": {
						required: true
					}
					,
					"fecha": {
						date: true,
						required: true
					}

				}
			},
			titleForm: jQuery.rup.i18nParse(jQuery.rup.i18n.base, 'rup_table.edit.editCaption')
		},

		primaryKey: "identificador",
		filter: {
			rules: {
				"identificador": {
					digits: true
				},
				"descFilter": {
					maxlength: 200
				}
			}
		}
		, colReorder: {
			fixedColumnsLeft: 1
		}
		, order: [[0, "asc"]]
		, loadOnStartUp: true
		, feedback: { id: "parentesco_feedback" }
		, select: {
			activate: true,
			style: 'multi' // Asegurar selección múltiple
		}
		, buttons: {
			activate: true
			,blackListButtons: ['cloneButton', 'pdfButton', 'odsButton', 'copyButton', 'csvButton']
		}
		// ===== AÑADIDO: Esta línea llama a la función cuando la tabla se inicializa =====
		, initComplete: añadirBotonAToolbar
	});

	$('#parentesco').on('tableEditFormAddEditAfterShowForm', function(event, ctx) {
		$('[aria-describedby="parentesco_detail_div"]').css("width", "60%");
		$('[aria-describedby="parentesco_detail_div"]').css("left", "15%");
	});

	$('#parentesco').on('tableEditFormBeforeCallAjax', function() {
		bloquearPantalla($.rup.i18nParse($.rup.i18n.app, "comun.guardando"));
	});
	$('#parentesco').on('tableEditFormCompleteCallSaveAjax', desbloquearPantalla);
	$('#parentesco_detail_form').on('invalid-form.validate', desbloquearPantalla);

	$('#parentesco').on('tableEditFormAddEditBeforeShowForm', function(event, ctx) {

		$("#identificador_detail_table").prop('disabled', true);

		var tipo = ctx.oInit.formEdit.actionType;
		if (tipo == 'PUT') {
			$("#divIdentificador").removeClass("d-none");
		} else if (tipo == 'POST') {
			$("#divIdentificador").addClass("d-none");
		}
		
	});
	
	
	initRupI18nPromise.then(function() {
		jQuery('#situacion_detail_table').rup_combo({
			source : [
			   {label: $.rup.i18nParse($.rup.i18n.app, "situacion.alta"), value:"A"},
			   {label: $.rup.i18nParse($.rup.i18n.app, "situacion.baja"), value:"B"}
			 ] 
			,width: "80%"
			,ordered: false	
			,rowStriping: true
			,open: function(){
				jQuery('#situacion_detail_table-menu').width(jQuery('#situacion_detail_table-button').width());
			}
		});	
		
		jQuery('#situacion_filter_table').rup_combo({
			source : "combo/situacion",
			sourceParam: {
				label: $.rup.lang == 'es' ? "valueEs" : "valueEu",
				id: "id"
			}
			,blank: ""
			,width: "80%"
			,ordered: false	
			,rowStriping: true
			,open: function(){
				jQuery('#situacion_filter_table-menu').width(jQuery('#situacion_filter_table-button').width());
			}
		});	
	});
	
	$("#fecha_detail_table").rup_date();

	$.rup_date({
		from: "fechaDesde_filter_table",
		to: "fechaHasta_filter_table",
		yearRange: "1900:c"
	});
	
	// Mostrar la pantalla
	$('.zz99-content').addClass('in');
});