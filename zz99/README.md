[![LOGO](/images/uda-big-250.png "Logo de UDA")](http://uda-ejie.github.io/)


En este documento vamos a explicar como se instala la aplicación del repositorio y posteriormente definiremos paso a paso como se ha creado la pantalla de ejemplo de tipos de documentos para que al realizar la práctica podamos basarnos en ella.

Si no se cuenta con un entorno UDA, se puede generar ese entorno en windows siguiendo estas [instrucciones](http://gitlks.lksoutsourcing.local/lks/training/ejie/practica-uda/-/blob/main/Entorno.md)

El enunciando de la práctica básica es el [siguiente](http://gitlks.lksoutsourcing.local/lks/training/ejie/practica-uda/-/blob/main/PracticaBasica.md). Si se completa, se puede pasar a la práctica [avanzada](http://gitlks.lksoutsourcing.local/lks/training/ejie/practica-uda/-/blob/main/PracticaAvanzada.md).

Una vez completadas las prácticas básica y avanzada, se pueden realizar las [prácticas extra 1](http://gitlks.lksoutsourcing.local/lks/training/ejie/practica-uda/-/blob/main/PracticaExtra1.md) y [prácticas extra 2](http://gitlks.lksoutsourcing.local/lks/training/ejie/practica-uda/-/blob/main/PracticaExtra2.md).

# INSTALACIÓN DE LA APLICACIÓN


## Clonar repositorio

Si es la primera vez que vamos a usar Git, tenemos que seguir unos pasos. Nos descargamos Sourcetree desde su página web. Es el cliente que vamos a usar

A ese cliente le tenemos que añadir la clave ssh que vamos a generar. Abrimos terminal en windows y escribimos (cambiamos el comentario de omartinez-gitlab por lo que queramos):

```sh
ssh-keygen -t rsa -b 2048 -C "omartinez-gitlab"
```

Nos pedirá nombre para los ficheros de clave y la contraseña. Estos nos generará unos ficheros en la ruta donde estemos. Coipamos esos ficheros para mayor seguridad en:

C:/Users/"user"/.ssh/

Accedemos a gitlab, y en las preferencias, en SSH Keys, añadimos el contenido del fichero de la clave pública que acabamos de generar. El de extensión .pub.

Ahora accedemos a Sourcetree para añadir la clave privada. Entramos en  Tools > Options y en la primera pestaña, en "SSH Client Configuración", añadimos la clave privada pero indicando que es de tipo OpenSSH. 

Una vez añadida esa clave privada, vamos a la pestaña de Clone y clonamos nuestro proyecto

En el primer registro, la url de git: git@gitlks.lksoutsourcing.local:lks/training/ejie/practica-uda.git

En el segundo registro vamos a poner una carpeta: C:/aplic/zz99
 

## Configurar servidor

Abrimos el eclipse que nos hemos descargado en el entorno y como workspace elegimos C:/aplic/zz99 y pasamos a configurar el servidor.

Abrimos la vista de servidores en la perspectiva javaEE y realizamos los siguientes pasos:

* Elegimos Oracle > Oracle Weblogic Server. Pulsamos en Next
* En Weblogic home, elegimos la carpeta C:\Weblogic\Weblogic141100\wlserver que es donde hemos instalado. Pulsamos en Next
* En Domain directory, elegimos la carpeta C:\Weblogic_domains\domain_wls14. Pulsamos en Next
* Pulsamos en Finish


## Configurar java en el workspace

Ahora que tenemos el servidor que contiene la jdk con la que tiran las aplicaciones, aprovechamos para configurarla a nivel general del workspace, en Window > Preference > Java > Installed JREs ponemos por defecto la versión 17.
Aunque se use la versión 17 de java, para los proyectos se configurará que compilen con la versión 11. Más adelante en el apartado "Importar proyectos", se verá como hacerlo.

![N|Solid](/images/java-1.png)
![N|Solid](/images/java-2.png)

Arrancamos el servidor para comprobar que todo funciona correctamente.


## Importar proyectos

Para que luego tengamos bien configurado el workspace, vamos a seguir una serie de pasos:

* En la ventana "Window>Preferences":

** Acceder a "General>Workspace", y establecer el "Text file encoding" a "UTF-8"

** Acceder a "General>Content Types". Para los de tipo "Text" y pulsando "Update", establecer como "Default enconding" el "UTF-8". Repetir este paso para los subtipos "Java Properties Files" y "JSP"

** Acceder a "Web>CSS Files" y seleccionar el encoding "ISO 10646/Unicode(UTF-8)"

** Acceder a "Web>HTML Files" y seleccionar el encoding "ISO 10646/Unicode(UTF-8)"

** Acceder a "Web>JSP Files" y seleccionar el encoding "ISO 10646/Unicode(UTF-8)"

** Acceder a "XML>XML Files" y seleccionar el encoding "ISO 10646/Unicode(UTF-8)"

** Acceder a "General>Editors>File Associations" y activar como editores por defecto (pulsando botón "Default") para los ficheros ".jsp > JSP Editor"

** Acceder a "UDA" para configurar el path que contiene las plantillas, "ECLIPSE_HOME\templates", y marcar la casilla "Desarrollo para EJIE"

![N|Solid](/images/uda_config.png)


Ahora importamos los proyectos.

Tras importarlos, nos aseguramos de que se compilan con la versión jdk 11.
Sobre los proyectos zz99EARClasses, zz99PracticaWar y zz99Statics se hace click derecho -> Properties. Buscamos Java Build Path, y comprobamos que está con JDK 11.

![N|Solid](/images/build_java1.jpg)


En el caso que no, pulsamos sobre el botón Edit y en la lista desplegable buscamos la versión 11
![N|Solid](/images/build_java2.png)


## Config del proyecto

Para que el servidor vea el config de nuestro proyecto, tenemos que seguir dos pasos, crear un enalce simbólico en la carpeta config/domain_wls14 y luego en el arranque del weblogic añadir esa carpeta domain_wls14.

Necesitaremos abrir la terminal de windows en modo administrador

```sh
mklink /D "C:\config\domain_wls14\zz99" "C:\aplic\zz99\zz99Config"
```


## Datasource

* Accedemos a la consola del weblogic:[http://localhost:7001/console] User y password:weblogic14
* Pulsamos en orígenes de datos
* Nuevo > Origen de datos Genérico
* De nombre elegimos zz99DataSource y de nombre de JNDI zz99.zz99DataSource. Pulsamos en siguiente.
* De controlador de bas de datos, elegimos&nbsp;*Oracle Drivers Thin for Service Connectios  y pulsamos en siguiente
* No tocamos nada y pulsamos en siguiente
* En esta pantalla tenemos que configurar la localización de nuestra base de datos:
** Nombre de la Base de Datos: XE
** Nombre del Host: localhost
** Puerto: 1521
** Nombre de usuario: zz99
** Password: zz99
* Al pulsar en siguiente, vemos el resumen del datasource donde vamos a pulsar en&nbsp; "probar la configuración", si funciona, pulsamos en siguiente.
* Comprobamos que el check de AdminServer está activo y finalizamos.

![N|Solid](/images/datasource1.png)
![N|Solid](/images/datasource2.png)
![N|Solid](/images/datasource3.png)


## Añadir el EAR y los statics

Desde la pestaña de Servers (donde hemos configurado nuestro Weblogic), hacemos click derecho sobre el servidor y pulsamos en Add and remove y añadimos el EAR y el Statics. Publicamos y ya tenemos la aplicación desplegada.

Podemos acecder a ella desde:

http://localhost:7001/zz99PracticaWar


# PASOS PARA CREAR UNA PANTALLA 

Vamos a basar las explicaciones en la pantalla que contiene este repositorio y que se llama Profesión.

Para poder crear una pantalla será necesario completar los siguientes pasos:

  - Crear el controller y enlazar con la pantalla en el menú
  - Completar el jsp y el js
  - Crear los DAOs y los Services
  - Crear los métodos del controller

# Crear el controller y enlazar con la pantalla en el menú

En la ruta */zz99PracticaWar/src/com/ejie/zz99/control/* se crea el controller ProfesionController.java. Son necesarias las siguientes anotaciones:
```java
@Controller
@RequestMapping(value = "/profesion")
public class ProfesionController {
```

Con la anotación @RequestMapping, todas las rutas "zz99PracticaWar/profesion/*", serán controladas en esta clase.

En la misma clase del controller, creamos un método principal que devuelva  el name que se utilizará en el tiles. La anotación:  
```java
@RequestMapping(value = "/maint", method = RequestMethod.GET)
```
Para crear el enlace desde el menú a este método, se crea en el archivo [menu.jsp](http://gitlks.lksoutsourcing.local/lks/training/ejie/practica-uda/-/tree/main/zz99PracticaWar/WebContent/WEB-INF/layouts/menu.jsp) una nueva pestaña con el atributo *href* que contiene la ruta del método maint.
El siguiente paso es declarar las jsp dentro del tiles.xml
```java
<definition extends="template" name="mantProfesion">
	<put-attribute name="content"
		value="/WEB-INF/views/profesion/profesion.jsp" />
	<put-attribute name="includes"
		value="/WEB-INF/views/profesion/profesion-includes.jsp" />
</definition>
```
Por último, se deben crear los archivos JSP y JS. Los JSP se crean en las rutas indicadas en el paso anterior y la ruta del JS se define en el [profesion-includes.jsp](http://gitlks.lksoutsourcing.local/lks/training/ejie/practica-uda/-/tree/main/zz99PracticaWar/WebContent/WEB-INF/views/profesion/profesion-includes.jsp):
```sh
<script src="${staticsUrl}/zz99/scripts/zz99App/profesion.js" type="text/javascript"></script>
```
# Completar el .jsp y el .js     
Una vez definida la navegación pasamos a crear los componentes que formarán la pantalla. La definición de los campos se hace mediante HTML en el JSP, y en el js se declaran los componentes rup y se añaden las funciones que controlan las llamadas con el controller.

Para modificar los estilos automáticos de los componentes o añadir nuevos estilos se modifica el archivo [zz99.css](http://gitlks.lksoutsourcing.local/lks/training/ejie/practica-uda/-/tree/main/zz99Statics/WebContent/zz99/styles/zz99.css) propio de la aplicaci�n.
Además, hay que añadir la dirección del css en el [zz99.styles.inc](http://gitlks.lksoutsourcing.local/lks/training/ejie/practica-uda/-/tree/main/zz99PracticaWar/WebContent/WEB-INF/layouts/includes/zz99.styles.inc) para que los componentes del JSP puedan identificar las nuevas clases de estilos:
```sh
<link href="${staticsUrl}/zz99/styles/zz99.css" rel="stylesheet" type="text/css" />
```
### Declaración de los componentes rup en el .js

  En la práctica zz99 se han declarado varios *rup_combos* y un *rup_table*. Todos los componentes están definidos en la documentación de [UDA](https://github.com/UDA-EJIE/uda-ejie.github.io/wiki/Patrones)
#### Rup_combo
Permite al usuario recuperar un elemento de una gran lista de elementos o de varias listas dependientes
de forma sencilla. La lista se puede cargar de forma remota o local. En ambos casos, el combo se definirá con el id del componente *select* del JSP. A continuación se explican los dos tipos mediante ejemplos de la práctica:

**Local**: carga los datos del fichero JS desplegado como contenido estático.
	
```javascript
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
```
El atributo source está compuesto por un label y un value para cada elemento del combo. El value indica el valor que tomará el componente al seleccionar ese elemento y el label es el texto que se muestra por pantalla. 

La definición de este se realiza mediante el fichero de recursos definido para la aplicación que se encontrará en la parte estática bajo el nombre [zz99Practica.i18n_es.json](http://gitlks.lksoutsourcing.local/lks/training/ejie/practica-uda/-/tree/main/zz99Statics/WebContent/zz99/resources/zz99PracticaWar.i18n_es.json) (con su variante para euskera :[zz99Practica.i18n_eu.json](http://gitlks.lksoutsourcing.local/lks/training/ejie/practica-uda/-/tree/main/zz99Statics/WebContent/zz99/resources/zz99PracticaWar.i18n_eu.json)). En dicho fichero se deberá declarar un objeto JSON cuyo nombre sea el mismo que el id del elemento HTML sobre el que se aplica el componente. Siguiendo el ejemplo:
 
```sh
    ,"situacion": {
		"alta": "Alta",
		"baja": "Baja"
	}
```

**Remoto**: los datos a cargar se solicitan mediante una url al controller.
	 
```javascript
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
```

En el atributo **source** se define la url del método al que se llama. Por otro lado, en el **sourceParam** se define la respuesta, que llega a modo de objeto. Deben configurarse los siguientes datos:

   - label: nombre del atributo del objeto del que se extrae la descripción (el literal a
    mostrar). Se configura para ambos idiomas.
   - value: nombre del atributo del objeto del que se extrae el valor.
    

Otra de las cosas a tener en cuenta a la hora de crear un combo es el atributo blank. Lo añaadiremos si además de la lista recogida(tanto en remoto como en local) queremos un elemento extra que nos muestre un vacío, un label de todos, un seleccione etc. que lleve un valor vacío.
Para definir el label que queramos mostrar, se define el literal en el json bajo el id del combo. Por ejemplo:

```javascript
,"estado_detail_table": {
		"_blank": "[Seleccione]"
	}
```
#### Rup_table
El componente rup_table facilita la lógica necesaria en las acciones básicas, denominadas CRUD (create, read,update y delete), sobre una tabla.
La tabla necesita de una invocación de una llamada javascript sobre una estructura HTML
existente. En el caso de la práctica, la estructura HTML que define la tabla en el JSP es la siguiente:

```html
<form:form modelAttribute="Profesion" id="profesion_filter_form">
	<!-- Barra de herramientas del formulario de filtrado -->
	<div id="profesion_feedback"></div>
	<div id="profesion_filter_toolbar" class="formulario_legend"></div>
	<fieldset id="profesion_filter_fieldset" class="rup-table-filter-fieldset">
		<div class="form-row">
			<!-- Identificador -->
			<div class="form-groupMaterial col-xl-2">
				<form:input path="identificador" id="identificador_filter_table" maxlength="9"/>
				<label for="identificador_filter_table"><spring:message code="comun.codigo"/></label>
			</div>
			<!-- Descripcion -->
			<div class="form-groupMaterial col-xl-8">
				<form:input path="descFilter" id="descFilter_filter_table" maxlength="200"/>
				<label for="descFilter_filter_table"><spring:message code="comun.descripcion"/></label>
			</div>
		</div>
		<div class="form-row">
			<div class="form-groupMaterial col-xl-2">	
				<form:select path="situacion" name="situacion"  id="situacion_filter_table"  >
				</form:select>
				<label for="situacion_filter_table" class="select-material" data-i18n="comun.situacion"><spring:message code="comun.situacion"/></label>
			</div>
			<div class="form-groupMaterial col-xl-2" >
				<form:input path="fechaDesde" id="fechaDesde_filter_table" maxlength="100" />
				<label for="fechaDesde_filter_table"><spring:message code="comun.fecha.desde" /></label>
			</div>
			<div class="form-groupMaterial col-xl-2" >
				<form:input path="fechaHasta" id="fechaHasta_filter_table" maxlength="100" />
				<label for="fechaHasta_filter_table"><spring:message code="comun.fecha.hasta" /></label>
			</div>
		</div>
		<div id="profesion_filter_buttonSet" class="text-right">
			<!-- Botón de filtrado -->
			<button id="profesion_filter_filterButton" type="button" class="btn-material btn-material-primary-high-emphasis" >
				<i class="mdi mdi-filter"></i>
				<span><spring:message code="filter" /></span>
			</button>
			<!-- Botón de limpiar -->
			<button id="profesion_filter_cleanButton" type="button" class="btn-material btn-material-primary-low-emphasis mr-2">
				<i class="mdi mdi-eraser"></i>
				<span><spring:message code="clear" /></span>
			</button>
		</div>
	</fieldset>
</form:form>

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

```
Una vez definida en el JSP, se utiliza el id para declarar el componente en el JS.
En la url se indica la ruta del controller al que llamará el filter para hacer la busqueda y recibir la lista de objetos a mostrar.
Se define el toolbar, indicando que métodos del CRUD se van a utilizar.
Para definir las columnas se utilizan los atributos colName y colModel. En el primero se indican los nombres haciendo referencia a literales del JSON y en el segundo, se detalla la configuración de cada columna. Cogemos una columna como ejemplo.

```javascript
{
	name: 'descEs',
	index: 'descEs',
	editable: true
}
```

La propiedad más importante es el *name*. En ella se indica el nombre que tiene el atributo que queremos mostrar del objeto recibido(este está informado como model).
En la práctica también hemos configurado el filter dentro del rup_table para validar los campos de filtrado antes de la búsqueda. 

```javascript
  filter: {
		rules: {
			"identificador": {
				digits: true
			},
			"descFilter": {
				maxlength: 200
			},
			"fechaDesde": {
				date: true
			},
			"fechaHasta": {
				date: true
			}
		}
	}
```
Algunas de las reglas de validación están definidas por defecto, pero es posible crear otras. Por ejemplo para validar que el campo id es un number se ha definido la siguiente:
```javascript
jQuery.validator.addMethod("number", function(value, element, param) {
	var expr = new RegExp($.rup.i18n.base.rup_validate.regexp.decimal);
	return this.optional(element) || expr.test(value);
}, $.rup.i18n.app.validaciones.number);
```
En el JSON creamos el literal ```$.rup.i18n.app.validaciones.number``` con el mensaje que se quiera mostrar.

Para terminar con la configuración de la tabla, vamos a ver como se crea el diálogo que permite las operaciones de crear y editar un campo. Se define en el formEdit:
```javascript
formEdit: {
	detailForm: "#profesion_detail_div",
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
}
```

Las validaciones y las definiciones de los campos siguen las mismas pautas definidas anteriormente. Para definir funciones o reglas que afectan solo a una de las operaciones CRUD se utilizan las propiedades editOptions y addOptions. De la misma manera, si hay configuraciones del diálogo que comparten ambas acciones se utiliza el addEditOptions. 
El componente rup permite que las llamadas a los métodos CRUD sean automáticas, generando métodos con una anotación concreta en el controller. (En el apartado Controller)

# Crear los DAOs y los Services
Como se ha comentado anteriormente, los componentes realizan llamadas al Controller para conseguir la información necesaria a mostrar. Estos a su vez llaman a los Service y estos a los DAOs. Los DAOs son los que se encargan de recoger la información de base de datos. Por eso, es conveniente crear primero estos y analizar que información se va a recoger y como se va a guardar. 

### DAO
**ruta**: */zz99EARClasses/src/com/ejie/zz99/dao/*

En la práctica se ha creado primero el GenericoDao con los métodos comunes a todos los DAOs. Primero se crea la interfaz con la declaración de los métodos y luego la clase que los implementa(GenericoDaoImpl). La notación para las clases DAO de implementación es:

```java
@Repository()
@Transactional()
public abstract class GenericoDaoImpl<T> extends BaseDaoImpl implements GenericoDao<T> {
```
En el genérico se añade el atributo jdbcTemplate para las llamadas a BBDD.

Una vez creados los genéricos se crean los demás. En la práctica, por ejemplo, se necesita un DAO para los tipos de documento. 
```java
@Repository()
@Transactional()
public class ProfesionDaoImpl extends GenericoDaoImpl<Profesion> implements ProfesionDao {
```
Para poder utilizar los métodos comunes, la clase hereda de la clase GenericoDaoImpl pasándole el objeto [Profesion.java](http://gitlks.lksoutsourcing.local/lks/training/ejie/practica-uda/-/tree/main/zz99EARClasses/src/com/ejie/zz99/model/Profesion.java) en el que se quieran guardar los datos recogidos.

Se deben completar los métodos heredados para formar las querys necesarias. Los métodos que se encargan de recoger datos deben llamar a rowMappers para parsear los datos de BBDD a datos de nuestro modelo. Por ejemplo:

```java
	@Override()
	protected RowMapper<TiposDocumento> getRwMap() {
		return this.rwMap;
	}
	
	public Profesion mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Profesion bean = new Profesion();
			bean.setIdentificador(resultSet.getInt(CPROFESION_01));
			bean.setSituacion(Situacion.fromString(resultSet.getString("SITUACION_01")));
			bean.setDescEs(resultSet.getString("DPROFESIONC_01"));
			bean.setDescEu(resultSet.getString("DPROFESIONE_01"));
			bean.setFecha(resultSet.getDate("FECHA_01"));
			return bean;
	}
```

Los rowMappers pueden declararse en el DAO o en una clase aparte.

El siguiente paso consiste en crear Services que hagan de intermediarios entre el Controller y el DAO. Si necesitamos combinar varias tablas de BBDD se pueden hacer llamadas a distintos DAOs desde el mismo método del service y configurar la lógica que controle lo obtenido.

Para empezar, del mismo modo que con los DAOs, se crearan el GenericoService y el GenericoServiceImpl que lo implemente. 

La clase ProfesionServiceImpl tiene este aspecto:
```java
@Service(value = "ProfesionService")
public class ProfesionServiceImpl extends GenericoServiceImpl<Profesion> implements ProfesionService {

	public ProfesionServiceImpl() {
		super(Profesion.class);
	}

	@Autowired
	private ProfesionDao profesionDao;

	@Override
	protected GenericoDao<Profesion> getDao() {
		return this.profesionDao;
	}

}
```
El value definido en la anotación service es el nombre que se usará en la declaración del controller para poder hacer las llamadas. Por otro lado, los DAOs a los que se llame se definirán con la anotación ```@Autowired```. 
En este ejemplo, las llamadas desde el Controller han sido a métodos generales definidos en el genérico. Sin embargo, es posible incluir los métodos necesarios en el Service. 

Todos los literales necesarios en estas clases se definen en los properties [zz99.i18n_es.properties](http://gitlks.lksoutsourcing.local/lks/training/ejie/practica-uda/-/tree/main/zz99EARClasses/resources/zz99.i18n_es.properties) y [zz99.i18n_eu.properties](http://gitlks.lksoutsourcing.local/lks/training/ejie/practica-uda/-/tree/main/zz99EARClasses/resources/zz99.i18n_eu.properties).

# Crear los métodos del controller

Por último, y después de haber creado lo necesario en el zz99EARClasses, se deben crear los métodos en el controller. 

Primero se deben declarar los Services y añadirles anotación:
```java
	@Autowired
	private ProfesionService profesionService;
```
Al crear cada método, es importante que la anotación sea correcta. Es lo que enlaza las llamadas desde el JS con el Controller. Por ejemplo en el [ProfesionController.java](http://gitlks.lksoutsourcing.local/lks/training/ejie/practica-uda/-/tree/main/zz99PracticaWar/src/com/ejie/zz99/control/ProfesionController.java):

```java
@RequestMapping(value = "/filter", method = RequestMethod.POST)
	public @ResponseBody TableResponseDto<Profesion> filter(@RequestJsonBody(param = "filter") Profesion filter,
			@RequestJsonBody TableRequestDto tableRequestDto) {
		logger.info("[POST - filter] : Obtener servicios");
		return this.profesionService.filter(filter, tableRequestDto);
	}
```

Al clicar en el botón de filtrado entrará automáticamente en este método debido a la notación ```value = "/filter"```. 

Por último, hay que tener en cuenta la creación de las operaciones CRUD. UDA genera automáticamente dichos métodos, pero para ello es necesario anotarlos siguiendo la relación:
```
   Alta: POST   Baja: DELETE
   Modificación: PUT   Consulta: GET
```

Por ejemplo, el método de editar:
```java
@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	public @ResponseBody Profesion edit(@RequestBody Profesion profesion) {
		logger.info("[PUT] : Profesion: edit");
		Profesion rdo = this.profesionService.update(profesion);
		logger.info("[PUT] : Profesion actualizado correctamente");
		return rdo;
	}
```
En cuanto al retorno de los datos (o generación del objeto HttpServletResponse) Spring MVC se encarga
automáticamente de hacerlo, en base al Objeto que se anote con ```@ResponseBody```. 

Después de crear los métodos necesarios ya estaría todo listo para navegar sobre la nueva pantalla. Para poder registrar cada paso de la navegación se utilizan trazas. Para ello, el controlador incluye a nivel de clase un instancia del objeto Logger:

```java
private static final Logger logger = LoggerFactory.getLogger(ProfesionController.class);
```
Las trazas definen diferentes niveles de prioridad:
```
logger.trace("TRACE log");
logger.debug("DEBUG log");
logger.info("INFO log");
logger.Warn("WARN log");
logger.error("ERROR log");`
```
que se podrán visualizar en la carpeta de logs. 
