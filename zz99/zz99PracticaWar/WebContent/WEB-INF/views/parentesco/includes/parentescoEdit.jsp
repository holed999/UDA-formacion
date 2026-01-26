<%@include file="/WEB-INF/includeTemplate.inc"%>

<!-- Formulario de detalle -->
<div id="parentesco_detail_div" class="rup-table-formEdit-detail d-none">
	<!-- Barra de navegación del detalle -->
	<div id ="parentesco_detail_navigation" class="row no-gutters"></div>
	<!-- Separador -->
	<hr class="m-1">
	<div class="dialog-content-material">
		<!-- Formulario -->
		<form:form modelAttribute="Parentesco" id="parentesco_detail_form">
			<!-- Feedback del formulario de detalle -->
			<div id ="parentesco_detail_feedback"></div>
				<!-- Campos del formulario de detalle -->
				<div id="divIdentificador" class="form-row d-none">
					<div class="form-groupMaterial col">
						<form:input path="identificador" id="identificador_detail_table" maxlength="100"/>
						<label for="identificador_detail_table">* <spring:message code="tabla.codigo"/></label>
					</div>
				</div>
				<div class="form-row">
					<div class="form-groupMaterial col">
						<form:input path="descEs" id="descEs_detail_table" maxlength="100"/>
						<label for="descEs_detail_table">* <spring:message code="tabla.desccastellano"/></label>
					</div>
				</div>
				<div class="form-row">
					<div class="form-groupMaterial col">
						<form:input path="descEu" id="descEu_detail_table" maxlength="100"/>
						<label for="descEu_detail_table">* <spring:message code="tabla.desceuskera"/></label>
					</div>
				</div>
				<div class="form-row">
					<div class="form-groupMaterial col-xl-2" id="div_situacion">
						<form:select path="situacion" id="situacion_detail_table" />
						<label for="situacion_detail_table" class="select-material" data-i18n="tabla.situacion"><spring:message code="tabla.situacion"/></label>
					</div>
					<div class="form-groupMaterial col-xl-2" id="div_fecha">
						<form:input path="fecha" id="fecha_detail_table" maxlength="100" />
						<label for="fecha_detail_table">* <spring:message code="comun.fecha" /></label>
					</div>
				</div>
				
				<!-- Fin campos del formulario de detalle -->
		</form:form>
	</div>
	<!-- Botonera del formulario de detalle -->
	<div class="rup-table-buttonpanel-material">
		<div class="text-right">
			<!-- Botón cancelar -->
			<button id="parentesco_detail_button_cancel" class="btn-material btn-material-sm btn-material-primary-low-emphasis" type="button">
				<spring:message code="cancel" />
			</button>
			<!-- Botón guardar -->
			<button id="parentesco_detail_button_save" class="btn-material btn-material-sm btn-material-primary-high-emphasis" type="button">
				<spring:message code="save" />
			</button>
		</div>
	</div>	
</div>