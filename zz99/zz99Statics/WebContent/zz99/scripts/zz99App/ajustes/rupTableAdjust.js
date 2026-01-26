(function ($) {
     $.fn.rup_table('extend', {
        _createTooltip(id) {
            if (id !== undefined && id.text() !== undefined && id.text() !== '') {
                id.rup_tooltip({
                    content: {
                        text: id.text()
                    },
                    show: {
                        event: 'mouseover'
                    },
                    position: {
			            my: 'top left',
			            at: 'bottom center',
			            target: 'event',
			            effect: false,
                        viewport: $(window),
                        adjust: {
                            method: 'flip'
                        }
                    }

                });
            }
        }
    
     });
     
     
     $.fn.rup_table('extend', {
     _showSearchCriteria() {
            var $self = this,
                settings = $('#' + $self[0].id).data('settings' + $self[0].id),
                searchString = ' ',
                label, numSelected,
                field, fieldId, fieldName, fieldValue,
                aux = settings.filter.$filterContainer.serializeArray(),
                searchForm = settings.filter.$filterContainer,
                filterMulticombo = [];
            var obj;
            let fieldIteration = 0;
            let isRadio;
            let isCheckbox;

            //añadir arbol
            var arboles = $('.jstree', settings.filter.$filterContainer);
            $.each(arboles, function (index, item) {
                obj = {};
                obj.name = $(item).attr('name');
                obj.value = $(item).rup_tree('getRupValue').length;
                obj.type = 'rup_tree';
                aux.push(obj);
            });

            let forEachDiv = (index, item) => {
                if (item.name === field.attr('id')) {
                    if (item.value != 0) {
                        fieldValue += ' = ' + item.value;
                    }
                } else {
                    fieldValue = '';
                }
            };

            for (var i = 0; i < aux.length; i++) {
                if (aux[i].value !== '' && $.inArray(aux[i].name, settings.filter.excludeSummary) < 0) {
                    //CAMPO a tratar
                    field = $('[name=\'' + aux[i].name + '\']', searchForm);

                    //Comprobar si se debe excluir el campo
                    if ($.inArray(field.attr('id'), settings.filter.filterExclude) !== -1) {
                        continue;
                    }

                    //Seleccionar radio
                    if (field.length > 1) {
                        field = $('[name=\'' + aux[i].name + '\']:checked', searchForm);
                        switch (field.prop('type')) {
                        	case 'radio':
                                isRadio = true;
                        		break;
                        	case 'checkbox':
                        		isCheckbox = true;
                        		break;
                        }
                    }
                    //Omitir campos hidden
                    if ($(field).attr('type') === 'hidden') {
                        continue;
                    }

                    //ID del campo
                    fieldId = $(field[fieldIteration++]).attr('id');
                    
                    // Reinicia el contador porque ya se han iterado todos los campos
                    if (fieldIteration === field.length) {
                    	fieldIteration = 0;
                    }
                    
                    //ID para elementos tipo rup.combo
                    if ($(field).attr('ruptype') === 'combo' && field.next('.ui-multiselect').length === 0) {
                        fieldId += '-button';
                    }
                    //ID para elementos tipo rup.autocomplete
                    if ($(field).attr('ruptype') === 'autocomplete') {
                        fieldId = fieldId.substring(0, fieldId.indexOf('_label'));
                    }

                    //NAME
                    label = $('label[for^=\'' + fieldId + '\']', searchForm);
                    if (isRadio && settings.adapter === 'table_material') {
                    	fieldName = $('#' + fieldId).closest('.form-radioGroupMaterial').children('label').html();
                    	isRadio = false;
                    } else if (isCheckbox && settings.adapter === 'table_material') {
                		fieldName = $('#' + fieldId).closest('.form-checkboxGroupMaterial').children('label').html();
                    	if (searchString !== '' && searchString !== undefined && new RegExp(fieldName, 'i').test(searchString)) {
                    		searchString = searchString.replace(/.{2}$/,","); 
                    		fieldName = '';
                    	}
                    	isCheckbox = false;
                    } else if (label.length > 0) {
                        fieldName = label.html();
                    } else {
                        if ($(field).attr('ruptype') !== 'combo') {
                        	//Mirar si es masterDetail
                            fieldName = $('[name=\'' + aux[i].name + '\']', searchForm).prev('div').find('label').first().html();
                            if(settings.masterDetail !== undefined && settings.masterDetail.masterPrimaryKey === aux[i].name){
                            	let md = settings.masterDetail;
                            	fieldName = (md.masterPrimaryLabel !== undefined) ? md.masterPrimaryLabel : md.masterPrimaryKey;
                            }
                        } else {
                            // Buscamos el label asociado al combo
                            // Primero por id
                            var $auxField = $('[name=\'' + aux[i].name + '\']', searchForm),
                                $labelField;

                            $labelField = jQuery('[for=\'' + $auxField.attr('id') + '\']');

                            if ($labelField.length > 0) {
                                fieldName = $labelField.first().text();
                            } else {

                                fieldName = $('[name=\'' + aux[i].name + '\']', searchForm).parent().prev('div').find('label').first().html();

                            }
                        }
                    }
                    if (fieldName === null || fieldName === undefined) {
                        fieldName = '';
                    }

                    //VALUE
                    fieldValue = ' = ';

                    switch ($(field)[0].tagName) {
	                    case 'INPUT':
	                        if ($(field)[0].type === 'checkbox' || $(field)[0].type === 'radio') {
	                            fieldValue += label.html();
	                        } else {
	                        	//Mirar si es masterDetail
	                            
	                            if(settings.masterDetail !== undefined && settings.masterDetail.masterPrimaryKey === aux[i].name){
	                            	let md = settings.masterDetail;
	                            	fieldValue += (md.masterPrimaryNid) ? field.data('nid') : $(field).val();
	                            }else{
	                            	fieldValue += $(field).val();
	                            }
	                        }
	                        break;
	                        //Rup-tree
	                    case 'DIV':
	                        $.each(aux, forEachDiv);
	                        if (fieldValue === '') {
	                            fieldName = '';
	                        }
	                        break;
	                    case 'SELECT':
	                        if (field.next('.ui-multiselect').length === 0) {
	                            fieldValue = fieldValue + $('option[value=\'' + aux[i].value + '\']', field).html();
	                        } else {
	                            if ($.inArray($(field).attr('id'), filterMulticombo) === -1) {
	                                numSelected = field.rup_combo('value').length;
	                                if (numSelected !== 0) {
	                                    fieldValue += numSelected;
	                                } else {
	                                    fieldName = '';
	                                    fieldValue = '';
	                                }
	                                filterMulticombo.push($(field).attr('id'));
	                            } else {
	                                fieldName = '';
	                                fieldValue = '';
	                            }
	                        }
	                        break;
                    }

                    //Parsear NAME
                    var parseableChars = new Array(':', '=');
                    for (var j = 0; j < parseableChars.length; j++) {
                        if (fieldName !== '' && fieldName.indexOf(parseableChars[j]) !== -1) {
                            fieldName = fieldName.substring(0, fieldName.indexOf(parseableChars[j]));
                            break;
                        }
                    }

                    //Controlar rup.combo con valor vacío
                    while (fieldValue.indexOf('&amp;nbsp;') !== -1) {
                        fieldValue = fieldValue.replace('&amp;nbsp;', '');
                    }

                    //Si no tiene NAME sacar solo el valor
                    if (fieldName === '' && fieldValue.indexOf(' = ') !== -1) {
                        fieldValue = fieldValue.substring(2, fieldValue.length);
                    }


                    //Si no tiene NAME ni VALUE omitir
                    if (fieldName === '' && fieldValue.trim() === '') {
                        continue;
                    }
                    searchString = searchString + fieldName + fieldValue + '; ';
                }
            }

            //Añadir criterios
            if (settings.multiFilter !== undefined && typeof settings.multiFilter.fncFilterName === "function") {
                searchString = settings.multiFilter.fncFilterName.bind($self, searchString)();
            }

            settings.filter.$filterSummary.html(' <i>' + searchString + '</i>');
        }
        });
     
 })(jQuery);