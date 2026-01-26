jQuery(function($) {

	$("#menu_tablas").addClass('active');
	
	// --- TOOLBAR DE LA PANTALLA ---
    $("#toolbar_parentesco").rup_toolbar({
        width: "100%",
        buttons: [
            {
                id: "btnNuevo",
                i18nCaption: "comun.nuevo",
                css: "nuevo",
                click: function () {
                    $("#parentesco").rup_table("addRow");
                }
            },
            {
                id: "btnEditar",
                i18nCaption: "comun.editar",
                css: "editar",
                click: function () {
                    $("#parentesco").rup_table("editRow");
                }
            },
            {
                id: "btnBorrar",
                i18nCaption: "comun.eliminar",
                css: "eliminar",
                click: function () {
                    $("#parentesco").rup_table("deleteRow");
                }
            },
	        {
	            id: "btnMiNuevoBoton",
	            i18nCaption: "comun.bajamultiple",
	            css: "editar",
	            click: function () {
	                console.log("Nuevo botón pulsado");
	                alert("Funcionalidad del nuevo botón");
	            }
	        },
            {
                id: "mBtnExportar",
                i18nCaption: "comun.exportar",
                css: "exportar",
                buttons: [
                    {
                        i18nCaption: "PDF",
                        css: "pdf",
                        click: function () {
                            console.log("Exportar a PDF");
                        }
                    },
                    {
                        i18nCaption: "CSV",
                        css: "csv",
                        click: function () {
                            console.log("Exportar a CSV");
                        }
                    }
                ]
            }
        ]
    });

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
						case 'B':
							return jQuery.rup.i18nParse($.rup.i18n.app, "situacion.baja");
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
			activate: true
		}
		, buttons: {
			activate: true
			,blackListButtons: ['cloneButton', 'pdfButton', 'odsButton', 'copyButton', 'csvButton']
		}
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