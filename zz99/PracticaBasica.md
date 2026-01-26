[![N|Solid](https://uda-ejie.github.io/images/uda-big-250.png)](http://uda-ejie.github.io/)


# PRÁCTICA BÁSICA


## Documentación de referencia 

Para el desarrollo, es importante tener a mano la siguiente documentación

| Tecnología | Enlace |
| ------ | ------ |
| UDA | [Tecnologías y patrones][UDA-patrones] |
| Portal demostrativo de UDA | [Demostraciones de los componentes de UDA][portalUDA] |
| Jquery | [Introducción básica][jquery] |
| Spring | [spring mvc básico][spring-mvc] |
| ORACLE-SQL | [listado de Comandos básicos][oracle-sql] |


## Cosas previas a tener en cuenta

  - La práctica la vamos a realizar sobre la aplicación zz99 que está en este repositorio. Dentro de la aplicación, existe una pantalla a modo de ejemplo que está explicada en el readme del repositorio 
  - Las tablas que  vamos a utilizar, se encuentran en el esquema de ZZ99 y son ZZ9901T00 y ZZ9902T00. Los sinónimos són: ZZ99_PROFESION y ZZ99_PARENTESCO. Si la base de datos se ve comprometida o se quiere reiniciar, bastaría con lanzar el siguiente [sql][sql-inicial]

## Requisitos de la práctica

Consistirá en crear un CRUD de parentescos. Tendremos los siguientes requisitos:

  - La pantalla se llamará parentescos y gestionará la tabla de ZZ9902T00 (ZZ99_PARENTESCO).
  - La pantalla la colocaremos en el menú principal "Tablas" debajo de la pantalla de ejemplo.
  - La tabla de BBDD tiene los siguientes campos:
    - CPARENTESCO_02: Es la PK de la tabla y se informa con una secuencia (ZZ9902Q00).
    - DPARENTESCOC_02: Descripción parentesco Castellano
    - DPARENTESCOE_02: Descripción parentesco Euskera
    - SITUACION_02: Situación de alta/baja, A o B
    - FECHA_02: Fecha
  - La pantalla va a tener botón de alta, de editar y eliminar. Aparte de esos botones tendremos el exportar que está en la pantalla de ejemplo.
  - Los filtros de búsqueda de la pantalla serán los siguientes:
    - Filtros de código y descripción. El texto que se escriba en descripción lo buscará en los campos castellano y euskera.
Combo para filtrar la situación. El combo tendrá tres registros:
        - Vacío (Todos)
        - ALTA
        - BAJA
    - Fecha desde - hasta 
  - La tabla de resultados sacará las columnas:
    - Código:
    - Descripción en castellano
    - Descripción en euskera
    - Situación (Alta o Baja)
    - Fecha
  - La pantalla no debe filtrar automáticamente al acceder
  - Se validará al insertar o editar que todos los campos sean requeridos.
  

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)


   [UDA-patrones]: <https://github.com/UDA-EJIE/uda-ejie.github.io/wiki/Patrones>
   [jquery]:<https://programarfacil.com/blog/programacion/introduccion-a-jquery-i/>
   [spring-mvc]:<https://expertojavaua.github.io/www.jtech.ua.es/j2ee/publico/spring-2012-13/>
   [oracle-sql]:<https://www.1keydata.com/es/sql/>
   [sql-inicial]:</images/CreateTableZZ99.sql>
   [portalUDA]:<https://www.ejie.eus/x21aAppWar/>
   
