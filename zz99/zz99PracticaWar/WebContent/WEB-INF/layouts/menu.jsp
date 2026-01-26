<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
 
<nav class="rup-navbar navbar">
	<button type="button" class="navbar-toggler d-lg-none" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"></button>
	<div id="navbarResponsive" class="collapse navbar-toggleable-md col-md-12 no-gutter">
		<spring:url value="/" var="home" htmlEscape="true"/>
		<a id="menu_home" class="navbar-brand" href="${home}">
    		<spring:message code="inicio" />
    	</a>
    	<ul class="nav navbar-nav">
      		<li class="nav-item dropdown">
        		<a class="nav-link dropdown-toggle" href="#" id="menu_tablas" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        			<spring:message code="tablas" />
        		</a>
        		
        		<div class="dropdown-menu" aria-labelledby="menu_tablas">
	            	<a class="dropdown-item" href="/zz99PracticaWar/profesion/maint">
	            		<spring:message code="titulo.profesion" />
	           		</a>
	            	<a class="dropdown-item" href="/zz99PracticaWar/parentesco/maint">
	            		<spring:message code="titulo.parentesco" />
	           		</a>
	            	<a class="dropdown-item" href="/zz99PracticaWar/persona/maint">
	            		<spring:message code="titulo.persona" />
	           		</a>
		         
        		</div>
      		</li>
    	</ul>
    	<ul class="nav navbar-nav float-md-right rup-nav-tools">
      		<li class="nav-item">
	        	<a class="nav-link rup-nav-tool-icon" href="#" id="zz99PracticaWar_language" data-toggle="dropdown">
	        		<i class="mdi mdi-earth" aria-hidden="true"></i>
	        		<span data-rup-lang-current=""></span>
	        	</a>
				<div class="dropdown-menu" aria-labelledby="zz99PracticaWar_language"></div>
      		</li>
			<li class="nav-item">
				<a class="nav-link rup-nav-user rup-nav-tool-icon" href="#">
					<i class="mdi mdi-account-circle" aria-hidden="true"></i>
					${udaAuthenticationProvider.userCredentials.fullName}
				</a>
			</li>
			<li class="nav-item">
				<a class="nav-link rup-nav-user rup-nav-tool-icon" href="/zz99PracticaWar/logout">
					<i class="mdi mdi-logout" aria-hidden="true"></i>
				</a>
			</li>
			<li class="nav-item swingTop">
				<a class="nav-link rup-nav-user rup-nav-tool-icon" href="#">
					<i class="mdi mdi-arrow-up" aria-hidden="true"></i>
				</a>
			</li>
    	</ul>
    	
  	</div>
</nav>

<div id="overlay"></div>