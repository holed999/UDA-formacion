# INSTALACIÓN DE ENTORNO

[![LOGO](/images/uda-big-250.png "Logo de UDA")](http://uda-ejie.github.io/)

Para poder trabajar con UDA, sin depender de ningún servidor externo, va a ser necesario instalar lo siguiente

  - Weblogic 14
  - Oracle Express Edition
  - SQLDeveloper
  - Un IDE de desarrollo (Eclipse)
  - Una jdk para el eclipse (jdk-17) y otra para weblogic (jdk-11)

Con estas tecnologías instaladas en el entorno, ya se puede desarollar una aplicación de ejemplo de UDA 


## Weblogic 14

Nos descargamos el servidor Weblogic desde [aquí][zip-weblogic]. 

Y ahora descargamos el dominio de Weblogic preconfigurado [aquí][zip-weblogicDomain]

Descomprimimos ambas carpetas en C:\

[![EXPLORADOR](/images/explorador-weblogic.png)]



## Java SE Development Kit

Es necesario para utilizar el eclipse y el sqldeveloper. NO tiene nada que ver con la versión de java que va a utilizar el servidor de aplicaciones. 
Para usar Eclipse, lo ideal es tener la jdk17. Nos la descargamos de [aquí][jdk17-windows]

En cambio, para poder iniciar Weblogic 14, será necesario disponer de la jdk11. La descargamos desde [aquí][jdk11-windows]



## Oracle express edition

Es la versión de oracle gratuita. Se puede descargar desde [aquí][express] para cualquier plataforma. Instalamos todo por defecto y asignamos una contraseña que podamos recordar. Por ejemplo ADMIN.

Antes de crear nada y después de instalar vamos a realizar unos cambios para que no nos den problemas las versiones. En el fichero sqlnet.ora que está en C:\app\admin\product\21c\homes\OraDB21Home1\network\admin\sqlnet.ora, hay que añadir:

Pedirá permisos de administrador para esto.

```sh
SQLNET.ALLOWED_LOGON_VERSION=10
SQLNET.ALLOWED_LOGON_VERSION_CLIENT=10
SQLNET.ALLOWED_LOGON_VERSION_SERVER=10
```

Una vez instalado, si queremos crear una nueva aplicación, podemos hacer lo siguiente:

Abrimos una consola o cmd en windows en modo administrador y ponemos la siguiente sentencia donde el password es el que le acabamos de asignar en la instalación

```sh
sqlplus sys as sysdba
```

Esto nos deja el SQL> abierto donde tenemos que crear el usuario:

~~~~sql
alter session set "_Oracle_SCRIPT"=true;

create user zz99 identified by zz99;

grant CREATE SESSION, ALTER SESSION, CREATE DATABASE LINK, -
  CREATE MATERIALIZED VIEW, CREATE PROCEDURE, CREATE PUBLIC SYNONYM, -
  CREATE ROLE, CREATE SEQUENCE, CREATE SYNONYM, CREATE TABLE, - 
  CREATE TRIGGER, CREATE TYPE, CREATE VIEW, UNLIMITED TABLESPACE -
  to zz99;
~~~~



## SQLDeveloper

No es que sea una herramienta necesaria pero es la homologada en ejie. Para instalarla, descargamos la versión desde [aquí][sqldeveloper]. Nos descargamos la que va sin jdk. Para acceder a los esquemas que hemos creado en express, hacemos lo siguiente:

[![SQLDEVELOPER](/images/sqldeveloper.png)]

En el caso de que al dar a probar la conexión ocurra el siguiente error _Estado: Fallo:Fallo de la prueba: ORA-12541: No se puede conectar. No hay ningún listener en host localhost port 1521. (CONNECTION_ID=1nUVcjbkTdW2HJgUUYLdBg==)_ sustituye el host localhost por la IP que aparece en el fichero tnsnames.ora

[![SQLDEVELOPER](/images/tnsnames-ora.png)]


## Eclipse de UDA

El último paso que nos queda es instalar el eclipse y configurar lo básico necesario para poder funcionar con él. 

### Descarga del eclipse

Descargamos el eclipse [aquí][zip-eclipse]

Descomprimimos el fichero .zip en C:\

[![ECLIPSE](/images/explorador-eclipse.png)]


### Java del eclipse

Para no tener problemas con el plugin de weblogic del eclipse, vamos a editar el fichero eclipse.ini que está a la altura del exe del eclipse que nos hemos descargado con Notepad++. Añadimos la ruta de esa jdk (17) que hemos descargado al principio

-vm
C:/Program Files/Java/jdk-17/bin

Finalmente, ejecutamos el programa con eclipse.exe

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)

   [jdk17-windows]: <https://www.oracle.com/java/technologies/downloads/#java17-windows>
   [jdk11-windows]: <https://www.oracle.com/java/technologies/downloads/#java11-windows>
   [sqldeveloper]:<https://www.oracle.com/database/sqldeveloper/technologies/download/>
   [express]:<https://www.oracle.com/technetwork/database/database-technologies/express-edition/downloads/index.html> 
   [uda-instalar]:<https://github.com/UDA-EJIE/uda-ejie.github.io/wiki/Instalar/>
   [startweblogic.cmd]:</images/startWebLogic.cmd>
   [zip-weblogic]:<https://statics.gobtech.lksnext.com/ejie/practicaUda-4/Weblogic.tar>
   [zip-weblogicDomain]:<https://statics.gobtech.lksnext.com/ejie/practicaUda-4/Weblogic_domains.zip>
   [zip-eclipse]:<https://statics.gobtech.lksnext.com/ejie/practicaUda-4/eclipse.zip>

   

   
