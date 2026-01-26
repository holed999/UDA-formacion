<%@include file="/WEB-INF/includeTemplate.inc"%>

<h2><spring:message code="titulo.parentesco" /></h2>

<jsp:include page="includes/parentescoFilterForm.jsp"></jsp:include>

<table id="parentesco" class="tableFit table-striped table-bordered table-material" 
	data-url-base="../parentesco"
	data-filter-form="#parentesco_filter_form">
	<thead>
        <tr>
        	<th data-col-prop="identificador" data-col-sidx="CPARENTESCO_02" >
            	<h2><spring:message code="tabla.codigo" /></h2>
            </th> 
            <th data-col-prop="descEs" data-col-sidx="DPARENTESCOC_02" >
            	<h2><spring:message code="tabla.desccastellano" /></h2>
            </th>
            <th data-col-prop="descEu" data-col-sidx="DPARENTESCOE_02" >
            	<h2><spring:message code="tabla.desceuskera" /></h2>
            </th>
            <th data-col-prop="situacion" data-col-sidx="SITUACION_02" >
            	<h2><spring:message code="tabla.situacion" /></h2>
            </th>
            <th data-col-prop="fecha" data-col-sidx="FECHA_02" >
            	<h2><spring:message code="tabla.fecha" /></h2>
            </th>            
        </tr>
    </thead>
</table>

<jsp:include page="includes/parentescoEdit.jsp"></jsp:include>