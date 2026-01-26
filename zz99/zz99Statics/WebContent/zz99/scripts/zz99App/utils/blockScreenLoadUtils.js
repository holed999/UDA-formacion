function bloquearPantalla(mensaje,callback){
	var localMensaje = $.rup.i18nParse($.rup.i18n.app,"comun.cargando");
	
	if(mensaje !== undefined && typeof mensaje === "string"){
		localMensaje = mensaje;
	}
	
	var funcion = callback;
	if(mensaje !== undefined && typeof mensaje === "function"){
		funcion = mensaje;
	}
	
	localMensaje = localMensaje+" ...";
	
	jQuery.blockUI({	
		css: {
			    border: 'none', 
			    padding: '15px',
			    width:	'100%',
			    height: 'auto',
			    left: '0px',
			    backgroundColor: 'transparent',
			    '-webkit-border-radius': '0px', 
			    '-moz-border-radius': '0px',
			    'border-radius': '0px',
				'background-size': '12%',
			    opacity: 0.8, 
			    color: '#F0256F'
		}
		, message: function(){
			return(
					$('<div class="loadText">')						
						.prepend(jQuery("<img>")
							.addClass("imgLoadMessage")
							.attr("src",$.rup.APP_STATICS+"/images/loader.gif")));
		}
		, baseZ: 1004
		, onBlock: function() {
			if(funcion !== undefined && typeof funcion === "function"){
				funcion();
			}
        }
	});
	$('.blockUI').fitText(2); 
}

function desbloquearPantalla(callback){
	$('.zz99-content')
		.addClass('displayNone')
		.removeClass('oculto')
		.show('fade', 200, function(){
			jQuery.unblockUI({ 
                onUnblock: function(){ 
                	$('.v85j-content').removeClass('displayNone');
                	if(callback !== undefined && typeof callback === "function"){
        				callback();
        			}
                } 
            });
		});
}

/**
 * bloquear pantalla con funcion callback y parametro para el mismo
 */
function bloquearPantallaCallbackParams(mensaje,callback, param){
	var localMensaje = $.rup.i18nParse($.rup.i18n.app,"comun.cargando");
	
	if(mensaje !== undefined && typeof mensaje === "string"){
		localMensaje = mensaje;
	}
	
	localMensaje = localMensaje+" ...";
	
	jQuery.blockUI({	
		css: {
			    border: 'none', 
			    padding: '15px',
			    width:	'100%',
			    height: 'auto',
			    left: '0px',
			    backgroundColor: 'transparent',
			    '-webkit-border-radius': '0px', 
			    '-moz-border-radius': '0px',
			    'border-radius': '0px',
				'background-size': '12%',
			    opacity: 0.8, 
			    color: '#F0256F'
		}
		, message: function(){
			return(
					jQuery('<div class="loadText">')						
						.prepend(jQuery("<img>")
							.addClass("imgLoadMessage")
							.attr("src",$.rup.APP_STATICS+"/images/loader.gif")));
		}
		, baseZ: 1004
		, onBlock: function() {
			if(callback !== undefined && typeof callback === "function"){
				callback(param);
			}
        }
	});
	$('.blockUI').fitText(2); 
}

function bloquearElemento($el, mensaje, callback){
	var localMensaje = $.rup.i18nParse($.rup.i18n.app,"comun.cargando");
	
	if(mensaje !== undefined && typeof mensaje === "string"){
		localMensaje = mensaje;
	}
	
	localMensaje = localMensaje+" ...";
	
	$el.block({	
		css: {
			    border: 'none', 
			    padding: '15px',
			    width:	'100%',
			    height: 'auto',
			    left: '0px',
			    backgroundColor: 'transparent',
			    '-webkit-border-radius': '0px', 
			    '-moz-border-radius': '0px',
			    'border-radius': '0px',
				'background-size': '12%',
				'z-index': '1002',
			    opacity: 0.8, 
			    color: '#F0256F'
		}
		, message: function(){
			return(
					jQuery('<div class="loadText">')
					.prepend(jQuery("<img>")
							.addClass("imgLoadMessage")
							.attr("src",$.rup.APP_STATICS+"/images/loader.gif")));
		}
		, baseZ: 1002
		, onBlock: function() {
			if(callback !== undefined && typeof callback === "function"){
				callback();
			}
        }
	});
	jQuery('.blockUI').fitText(2); 
}

function desbloquearElemento($el, callback){
	$el.unblock({
	    onUnblock: function(){ 
	    	jQuery('.zz99-content').removeClass('displayNone'); 
	    	if(callback !== undefined && typeof callback === "function"){
				callback();
			}
	    } 
	});
}