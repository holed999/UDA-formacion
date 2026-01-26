<%@include file="/WEB-INF/includeTemplate.inc"%>
<!-- Formulario de filtrado -->
<form:form modelAttribute="ParentescoFilter" id="parentesco_filter_form">
	<!-- Barra de herramientas del formulario de filtrado -->
	<div id="parentesco_feedback"></div>
	<div id="parentesco_filter_toolbar" class="formulario_legend"></div>
	<fieldset id="parentesco_filter_fieldset"
		class="rup-table-filter-fieldset">
		<div class="form-row">
			<!-- Identificador -->
			<div class="form-groupMaterial col-xl-2">
				<form:input path="identificador" id="identificador_filter_table"
					maxlength="9" />
				<label for="identificador_filter_table"><spring:message
						code="comun.codigo" /></label>
			</div>
			<!-- Descripcion -->
			<div class="form-groupMaterial col-xl-8">
				<form:input path="descFilter" id="descFilter_filter_table"
					maxlength="200" />
				<label for="descFilter_filter_table"><spring:message
						code="comun.descripcion" /></label>
			</div>
		</div>
		<div class="form-row">
			<div class="form-groupMaterial col-xl-2">
				<form:select path="situacion" name="situacion"
					id="situacion_filter_table">

				</form:select>
				<label for="situacion_filter_table" class="select-material"
					data-i18n="comun.situacion"><spring:message
						code="comun.situacion" /></label>
			</div>
			<div class="form-groupMaterial col-xl-2">
				<form:input path="fechaDesde" id="fechaDesde_filter_table"
					maxlength="100" />
				<label for="fechaDesde_filter_table"><spring:message
						code="comun.fecha.desde" /></label>
			</div>
			<div class="form-groupMaterial col-xl-2">
				<form:input path="fechaHasta" id="fechaHasta_filter_table"
					maxlength="100" />
				<label for="fechaHasta_filter_table"><spring:message
						code="comun.fecha.hasta" /></label>
			</div>
		</div>
		<div id="parentesco_filter_buttonSet" class="text-right">
			<!-- Botón de filtrado -->
			<button id="parentesco_filter_filterButton" type="button"
				class="btn-material btn-material-primary-high-emphasis">
				<i class="mdi mdi-filter"></i> <span><spring:message
						code="filter" /></span>
			</button>
			<!-- Botón de limpiar -->
			<button id="parentesco_filter_cleanButton" type="button"
				class="btn-material btn-material-primary-low-emphasis mr-2">
				<i class="mdi mdi-eraser"></i> <span><spring:message
						code="clear" /></span>
			</button>
			<!-- Botón de filtrado -->
			<button id="parentesco_filter_filterButton1" type="button"
				class="btn-material btn-material-primary-high-emphasis">
				<i class="mdi mdi-filter"></i> <span><spring:message
						code="filter" /></span>
			</button>
		</div>
	</fieldset>
</form:form>