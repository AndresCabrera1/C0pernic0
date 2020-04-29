# Zalenium

Uno de los problemas que existen a la hora de ejecutar pruebas en paralelo basadas en Selenium grid
es en mantener el versionamiento de selenium, así como la sincronización de las versiones de los navegadores, controladores etc.
La infraestructura que necesita Selenium Gird para ejecutar los nodos que se configuran sobre una prueba compleja.

Zalenium es un prayecto open source no oficial de Selenium, se plantea como una de las opciones al momento de realizar pruebas en paralelo
debido a que el Hub de ejecución se corre bajo Docker integrando todas las funcionalidades de selenium grid. 
Una gran ventaja es que permite la integración con Docker, Kubernetes, AWS, Google Compute Engine, OpenShift entre otros servicios de la nube.

## Configuracion

Para ejecutar **Zalenium** debemos tener instalado [Docker](https://hub.docker.com/) en el S.O. 
procedemos a descargar la imagen  de **Selenium** y **Zalenium**

*	[docker pull elgalu/selenium](https://hub.docker.com/r/elgalu/selenium)
*	[docker pull dosel/zalenium](https://hub.docker.com/r/dosel/zalenium)

una vez descargando las imágenes mencionadas se verá algo así 

![Imagenes de Docker descargadas de manera local](https://drive.google.com/file/d/1raTUlzDynTz3Imjqf2qmWdwl6Vlwws19/view)

Vamos a ver el comando para crear el contenedor basado en Zalenium:
```
docker run --rm -ti --name zalenium -p 4444:4444 -v /var/run/docker.sock:/var/run/docker.sock -v /tmp/videos:/home/seluser/videos -e WAIT_FOR_AVAILABLE_NODES=false --privileged dosel/zalenium start --desiredContainers 4
```
Aspectos importantes que encontramos dentro del comando de Docker:

+ **desiredContainers:**  Con  este atributo le decimos a Zalenium cuantos nodos cree de Selenium grid 
+ **WAIT_FOR_AVAILABLE_NODES:** crea un nuevo porxy de selenium grid por cada ejecución del comando en Docker
+ **/tmp/videos:** podemos establecer una ruta local para que guarde los videos de las pruebas echas en Zalenium
+ **--privileged:** En la documentación se sugiere que a nivel de ejecución en Docker se especifique con privilegios esto mejora el performance de las pruebas sobre cada nodo especificado

Dentro de la arquitectura de Zalenium esta nos enseña dos escenarios uno de manera local y otra a través de un servicio de ejecución de pruebas en la nube

### Local 
Una ventaja es que podemos configurar nuestro propio servidor para zalenium pero con la limitante de ejecutar nuestras pruebas en Chrome o Firefox.

![Arquitectura de Zalenium Local](https://drive.google.com/file/d/1_QJEhbfTethUjmONf7z-amQPQfs1DFu1/view)

### Servicio de ejecución de pruebas en la nube
una ventaja es que tenemos más opciones para la ejecución de pruebas. Esta integración entre nuestro suite de pruebas y el servicio de ejecución de pruebas en la nube se hace mediante los atributos **SAUCE_USERNAM** , **AUCE_ACCESS_KEY**, **SAUCE_LABS_URL** dependiendo del servicio donde se valla a ejecutar.
Sauce Labs , BrowserStack, TestingBot , CrossBrowserTesting , LambdaTest  son algunas laboratorios que prestan la integración  con  zalenium

![Arquitectura de Zalenium Remota](https://drive.google.com/file/d/1PnIdx7BTUckponyC495Vn54szKSqsPSL/view)

Cuando ejecutamos de manera local el comando en Docker, este se verá de la siguiente manera

![Vista del comando en CMD](https://drive.google.com/file/d/1hFAdY4Uo3zccerLnzgHiAasio3jnErYk/view)

Como podemos observar por medio del comando Docker le indicamos el numero de nodos de Selenium grid podemos crear `desiredContainers` una vez el contenedor arranque de manera correcta este despliegas 3 paneles de visualización diferentes: 

1. **Grid Console:** permite visualizar la cantidad de nodos registros por Zalenium, a si mismo como se establece la configuración de los mismos.

 + http://localhost:4444/grid/console

![Vista gird Console en el navegador](https://drive.google.com/file/d/13rigjbSKNmFZS16kSEVDilhb5cFVQmkt/view)
 
2. **Zalenium – Dashboard:** Tampronto termine el proceso de ejecucion de la prueba, Zalenium guarda el registro y estado dela mismas 
								incluyendo un video donde se visualiza la prueba 

+ http://localhost:4444/dashboard/

![Vista dashboard zalenium](https://drive.google.com/file/d/1kWB9lCJ42TDKJ8BFNgoLcGOIE1gE8HDv/view)

3. **Zalenium Live Preview:** Por medio de este panel se puede visualizar la ejecución de la prueba, acceder directamente al nodo de selenium grid permitiendo interactuar con él. 
								Tiene una serie de configuraciones donde encontramos la dirección de red dirigiéndose al proxy creada por selenium grid , cambar colores del menú entre otras funciones.

+ http://localhost:4444/grid/admin/live

![Vista zalenium gid live](https://drive.google.com/file/d/1nAIL-UpzE-aJj2PNipizWBzuWfXM80EX/view)
	
	

### comando gradle 

```
gradle clean  test aggregate --info
```

