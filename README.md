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

![Imagenes de Docker descargadas de manera local](https://lh3.googleusercontent.com/qg-ZLk9JGgjoqMn6N3ObU9o8zdSsxCSf1XAHFmgYmhQ5ixXeBZnD4wr8hwi6bVl9vSG1IOqEgR41-O-C3Ay03pspLZuVXodNqirVZ2pr0zIhU1m99bkrG8HiI_Twg5BD8zPOshdwgA=w704-h86-no)
												  
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

![Arquitectura de Zalenium Local](https://lh3.googleusercontent.com/zcxQiQmLa74wJHma9i2ivvGmQ6mWxu8VvS73CZcQOuQzVRAYmiOMe097TqWXGb2qxtrIfwIwvPXPWS2J5LmWDyYH-TqRR2RMPgbKBAkFqSijxoGC76gGHYGvpzY3lH51N-EDh34mHw=w1027-h519-no)

### Servicio de ejecución de pruebas en la nube
una ventaja es que tenemos más opciones para la ejecución de pruebas. Esta integración entre nuestro suite de pruebas y el servicio de ejecución de pruebas en la nube se hace mediante los atributos **SAUCE_USERNAM** , **AUCE_ACCESS_KEY**, **SAUCE_LABS_URL** dependiendo del servicio donde se valla a ejecutar.
Sauce Labs , BrowserStack, TestingBot , CrossBrowserTesting , LambdaTest  son algunas laboratorios que prestan la integración  con  zalenium

![Arquitectura de Zalenium Remota](https://lh3.googleusercontent.com/A-hhmkc81PAlabSgiwNw3wHLEGctrqjNefbIWi32tTvLHmQK_1YkDKnB75lL-zbkBDkQjXyq9DIUyxgmmacd5IrMWJgw44DTRg78lsbt4Gj7iUm6b5tcPblvRL0KtGTH8Ss9oVhGNg=w985-h477-no)

Cuando ejecutamos de manera local el comando en Docker, este se verá de la siguiente manera

![Vista del comando en CMD](https://lh3.googleusercontent.com/aNGYnGMzWWI1rN6Ahd3cAyi5bT4SpBWA2U6TEKvQD91l7euEsLZ-gZbJH1Xso5huOj8e1mnbyQZkPAevL5Czl5CEPXNIkAwAgF52MiK5EzowzOxB6p02fmA71mUOgvQ-82tdNzi_Gw=w1006-h510-no)

Como podemos observar por medio del comando Docker le indicamos el numero de nodos de Selenium grid podemos crear `desiredContainers` una vez el contenedor arranque de manera correcta este despliegas 3 paneles de visualización diferentes: 

1. **Grid Console:** permite visualizar la cantidad de nodos registros por Zalenium, a si mismo como se establece la configuración de los mismos.

 + http://localhost:4444/grid/console

![Vista gird Console en el navegador](https://lh3.googleusercontent.com/ODL4PpmaQXS-2TW72EwCWk8hrNMJd5C7cmuwy_vxUW6PbZTVczaGOASJj8-mprJOoTwlqjj9quDDhFSDUHyhXyLr-5vprTjd64zh24w1R76QZAr1JLTKpp-RPv24cqMOGPAYWy9q8Q=w963-h663-no)
 
2. **Zalenium – Dashboard:** Tampronto termine el proceso de ejecucion de la prueba, Zalenium guarda el registro y estado dela mismas 
								incluyendo un video donde se visualiza la prueba 

+ http://localhost:4444/dashboard/

![Vista dashboard zalenium](https://lh3.googleusercontent.com/ivTMTiipjNISucLUoIvGnzah0SNFf6-mhfUgVX1ThD79f77dq0gYUKtBxrle8wbzLOxW8_lQrhq4pZorcMsKcaBI6RE8sbjg1rI_25OfVt6iJcKOyyiZt1JuxI7l8tzOR1xXlrWH1Q=w1006-h368-no)

3. **Zalenium Live Preview:** Por medio de este panel se puede visualizar la ejecución de la prueba, acceder directamente al nodo de selenium grid permitiendo interactuar con él. 
								Tiene una serie de configuraciones donde encontramos la dirección de red dirigiéndose al proxy creada por selenium grid , cambar colores del menú entre otras funciones.

+ http://localhost:4444/grid/admin/live

![Vista zalenium gid live](https://lh3.googleusercontent.com/m4QZkn5MaYD4lIHr6cVCCFDyCwBIe_JN7hwkquycojgEuJqqjcxWrf3R8kz1So5QtNDTuyo72jhUHFDOA_SqRCVNcs7hp1CNPdyhAOawMGOH0n4ozU3_EJqslP-y5COuGB7IHCtFWA=w1006-h498-no)
	
	

### comando gradle 

```
gradle clean  test aggregate --info
```

