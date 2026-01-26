<%@include file="/WEB-INF/includeTemplate.inc"%>

<h2><spring:message code="titulo.profesion" /></h2>

<jsp:include page="includes/profesionFilterForm.jsp"></jsp:include>

<table id="profesion" class="tableFit table-striped table-bordered table-material" 
	data-url-base="../profesion"
	data-filter-form="#profesion_filter_form">
	<thead>
        <tr>
        	<th data-col-prop="identificador" data-col-sidx="CPROFESION_01" >
            	<h2><spring:message code="tabla.codigo" /></h2>
            </th> 
            <th data-col-prop="descEs" data-col-sidx="DPROFESIONC_01" >
            	<h2><spring:message code="tabla.desccastellano" /></h2>
            </th>
            <th data-col-prop="descEu" data-col-sidx="DPROFESIONE_01" >
            	<h2><spring:message code="tabla.desceuskera" /></h2>
            </th>
            <th data-col-prop="situacion" data-col-sidx="SITUACION_01" >
            	<h2><spring:message code="tabla.situacion" /></h2>
            </th>
            <th data-col-prop="fecha" data-col-sidx="FECHA_01" >
            	<h2><spring:message code="tabla.fecha" /></h2>
            </th>            
        </tr>
    </thead>
</table>

<jsp:include page="includes/profesionEdit.jsp"></jsp:include>