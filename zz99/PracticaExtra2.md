[![N|Solid](https://uda-ejie.github.io/images/uda-big-250.png)](http://uda-ejie.github.io/)


# PRÁCTICA EXTRA 2

## Requisitos

Consiste en crear una nueva pantalla para gestionar los datos de provincias y municipios. Existen los siguiente requisitos:

- Una tabla maestra que alberga los datos de la tabla ZZ99_PROVINCIA(ZZ9904T00).
	- Los campos de BD son los siguientes:
		- ID_04: Id de la provincia
		- NOMBRE_04: Nombre de la provincia
		

- Una tabla detalle debajo de esta, de manera que se carguen los municipios de la provincia seleccionada en la tabla maestra:
	- Los campos de BD son los siguientes:
		- ID_05: Id del municipio
		- NOMBRE_05: Nombre del municipio
		- ID_prov_05: ID de la provincia. Este dato no se muestra en pantalla.
		
		
 
- Cada tabla tendrá un campo para filtrar por el nombre.

- Cada tabla tendrá su botonera para las acciones CRUD

- Todos los campos deben ser validados antes de crearse o modificarse. 

- El botón añadir municipio estará deshabilitado si en la tabla maestra de PROVINCIAS no hay un registro seleccionado.