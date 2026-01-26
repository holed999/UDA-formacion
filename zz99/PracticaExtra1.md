[![N|Solid](https://uda-ejie.github.io/images/uda-big-250.png)](http://uda-ejie.github.io/)


# PRÁCTICA EXTRA 1

## Requisitos

Consiste en crear una nueva pantalla para dar de alta personas. Existen los siguiente requisitos:

- Un formulario de alta. Se facilita el fichero [sql][sql-inicial] para crear la tabla ZZ99_PERSONA (ZZ9903T00)en BD que es sobre la que se crearán los registros.
	- Los campos de BD son los siguientes:
		- ID_03: Código de la persona. No se muestra en pantalla.
		- NOMBRE_03: Nombre de la persona
		- APELLIDO1_03: Primer apellido de la persona
		- APELLIDO2_03: Segundo apellido de la persona
		- FECHA_NACIMIENTO_03: Fecha de nacimiento de la persona
		- TLFONO_03: Teléfono de contacto
		- PROVINCIA_03: Provincia en la que reside
		- MUNICIPIO_03: Municipio en el que reside
		- CP_03: Código postal
		- DIRECCION_03: Resto de dirección

- Se propone que haya dos apartados en el formulario:
		
		- Datos personales
		- Datos de residencia
 
	Estos apartados serán acordeones, de manera que cuando se haga click sobre cada uno de ellos, se contraigan para ocultar los campos o expandan y se muestren.

- Los campos PROVINCIA y MUNICIPIO serán combos de tipo autocomplete que se cargarán a partir de las tablas ZZ9904T00(PROVINCIA) y ZZ9905T00(MUNICIPIO). El combo de municipio es dependiente del de provincia. No se cargarán datos hasta que se seleccione una provincia. 

- Una vez que se pulse sobre el botón guardar y se haya realizado la inserción en BD, si todo hay ido bien, se mostrará un feedback indicándolo. En el caso de que haya ocurrido algún error, también se mostrará un feedback con un mensaje de error.

- Todos los campos deben ser validados antes de enviarse 


[sql-inicial]:</images/CreateTableZZ99Extra.sql>