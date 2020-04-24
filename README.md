# Prerrequisitos

+ Docker 
+ gradle

# rrequisitos ejecucion 
+ imagenes docker :
  + dosel/zalenium
  + elgalu/selenium 

+ Contenedor docker
```
 docker run --rm -ti --name zalenium -p 4444:4444 -v /var/run/docker.sock:/var/run/docker.sock -v /tmp/videos:/home/seluser/videos -e WAIT_FOR_AVAILABLE_NODES=false --privileged dosel/zalenium start --desiredContainers 4
```

+ url visualizacion prueba

```
http://localhost:4444/grid/admin/live
```
#comando gradle 

```
gradle clean  test aggregate --info
```