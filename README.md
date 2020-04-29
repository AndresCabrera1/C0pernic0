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

![Imagenes de Docker descargadas de manera local](https://lh3.googleusercontent.com/EW2N6TrT3T6yRSXCi3RXp6hY7Xt9zqgUWdZgZJLIjUffreDYX8eaGFiRsEdYIZkGHnPqmdI7sqwcd5uS-46xakI6_-2SYzygJATAb62WgijUwKoih974cnNiuwc5gzBcObVGUMSCMkQpVpOlit6LKeSsEYzmjEg_Fh5VMrqG8464RsOGRihOI2d3cQymrUJz69WPdeOxWINwmoDeeJt-qpiJX6LGxSqrxvKBXVnOfriBwDk_ZfnEndI42-EOGeGSSyO5vVkj-XFhlB8JkVoUpMimimsgZHlJdAY2lUd5fkuVHwAQv5nMusgYbnMEmVtRS9JPAQ_1_4V96TT9oRPC6L5Fn0FGBOGJwpReCnKOXV3z8D6eAtPlrLA_p2FXTprT17rNLb2ZmgeN275l8t0yTXK3iJQs9jXxD7kHf28UI-SUWDTeziwkLtq_OBb5WPmFhdwcoh5Le7D-k83aSlqVeSWfelAfKwnPkGNolO4SVzbvGjdw5muOYlIjHp3RZ1ksTuIG0TSQSbn_ohRJdMdkTg7yI18SU0ZfEWPCC4ZXKRT58dmWWSsI0O8oM1EQoCwWV2n9WctXLHqxF78pePic1EZTqnyd0iuDkzvqXL5RRPf0-FfYHG3j836GMhFueUqKcRnWm_GWFhA0PKvTmtb-ZnnGO601VzUhDxJv30u-zGqqg0finmXcP3iVUUWTLA=w704-h86-no)

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

![Arquitectura de Zalenium Local](https://lh3.googleusercontent.com/41XxXNwCo_kINhtBkcUCOIB-W3RZWRDaRqP37xSAytGL91MwfvvLnyt8jPLj2Hd_ocDL6BSwv5WmAB1GR9UEniBZXPXaEITuy8jT7UIe-V6v2Zk_pFFhnkmOnfusp5LBmDOkq6Ooiw57_NZl1KmWqyGN45Pvlf8rOv08dIv4rxPHTLR9bFGg5QRHgTzayKjxyS3Y1avfFqNArPytZn1Hg5Kov0VCibmnRfHboTp85TMPh4mJSlg60YRiU4Y-RyflNMkmj0mu6yCv1xUtWUrnJ20AlSJMLVKqURE3AwVFxvf65zPyZ6IyX6IOV4NZHQFnGmXoDkOljz8nKj-K61rP8sxqqIJXS0z8Ab-9IzkiadnStjdzQEirl2kZC4v3ldmNuYIjLS3RrbulEqpCToE27NJk8OP8KLFxglTRlGnTXyx1m9djXmZDXel0JU0E9TbstFIOviFwamSqWAChOhjkOCL-jwQE4zycvcCdRieAwdZfA_5iRNgtseumDqW-G7QIUMSWtZwXlm55RKSh-o6BUJMlcVz9Vd37psHh2eEGzqOUjgxmcKpMvXA4cib2rEOKdZRmAsxxQIFvniEkIKqQMgnk6VkKJzHda3PuNnYsvgw0boAuDhk81CkplKJfgdwYFa1r2tEJCrbUnyWDO4a4ClPFwhlUkGLdvV_UfbR8_T316CbL7-Xmx3eMF_uTgw=w1027-h519-no)

### Servicio de ejecución de pruebas en la nube
una ventaja es que tenemos más opciones para la ejecución de pruebas. Esta integración entre nuestro suite de pruebas y el servicio de ejecución de pruebas en la nube se hace mediante los atributos **SAUCE_USERNAM** , **AUCE_ACCESS_KEY**, **SAUCE_LABS_URL** dependiendo del servicio donde se valla a ejecutar.
Sauce Labs , BrowserStack, TestingBot , CrossBrowserTesting , LambdaTest  son algunas laboratorios que prestan la integración  con  zalenium

![Arquitectura de Zalenium Remota](https://lh3.googleusercontent.com/WD_gLHWAAy7-ULju2Sez1a2BoUyFR1j6J_0LLqMJWfEzNqHVvYR1QO63Wg57o88EHRAuR3z13VK4Y1oxmoP_D6elpl-SI9-pngNeIlhNM3II9NaytiJb9uuHanLx65Witj08PxAAKZZN5Am7wXPmcXHGX3A7S41MaM1bFvapVDtTqjOjZ73MUyhuSdFgd2-zp0seKi_3c0dNwJBrukoNy2RFEoODH6qVMAbT2002rJKj9EI0-0r9azTpolK8wPO-jxQy0C4Tqr4OnfSUcECvOFQ3tX4AZYvPdDe8YrwVx5s6DI5lquYMB4hXWyDDJspP7POawiG4fdyxu3oXXywGyrE9dNd0oc12y35epdH4aqmgQpxg_IlfD1nMNv6k8ArBZ__Hc_W6RTtsMHMuZ-ledlzYph-UfSjVrtm9Mfmj2cjNrGN97DNvTy9jDo-D5vjzEJ8IYOdL7DZNc9Gyjbb-ixuKT95yLDrOPm7xi6r1nossM-YzIjijLuVxNZpgneuAHWTQ4fem-AlYUWY0xL_52WcjnJAxpOGelKHgLO_xl4w05juUtCc3Ald5Mlhf9J3E7jbadmFvhA1HDkgSdMh3u1MoEzwxYm6FgB19aFJKO9FIb6X2KcuQTbFuXvF7rx3HCJe_613lF_1O53Cb21I2mvyvgDEOgIba4ExZ9ij6blr3LrSVdNI6iENhwCd--w=w985-h477-no)

Cuando ejecutamos de manera local el comando en Docker, este se verá de la siguiente manera

![Vista del comando en CMD](https://lh3.googleusercontent.com/eye7CWYEnTw99SaEBiZxa0tqC0bT8G4bH4ZblE0rODvCbHzw0GCWZpSEMW0CfFS9KZpyEeJyXXP1JWsxPpR_gPPvYKv-Hxxb9-ycGwtOLpb42Ej5OfMahUyIkkNANlCZXSw-oIIax8qFnGLDnGVp7EY-5s9UEe7UPW35rbxKF6YAI4OLU32WMhbt9-b2OPjKmL54t7xyiAA3g0Xp_I0BpL8h8TKEIZFgv8ztPcT5JEV035fM5a3D3SYEPHwEj2z4XKeLejYrfbeNoJ8aZCRzDIZoqgDj2gqedOOGaxQ6VKBjxGN158v_wskd13nF5W-hGFufCirAOj7fd5xYhlKYcbVk-3W_0FYxgSJ4h4uSwYrF1SN8eDVFXwQLnhGdcRqwh08CvHxkN4IUf9I8SKUEkyZdOdExA2wWwbDDAOi0zEBk8g3ueaxoY4NKaVbu7tNxWtTMKVMoJyR8sUGr3ZV4Ckz6II7LTPRoy7nBMuiCvYPuf4z1Lh5TaW48p13Zm4V1t3rr7aMiSw5HAvg6ZmDLK--Z6n48Jjwd2-mjLEWTgdMGfEHZAmwjKdZE7C0ZMdSPWb7ftkNNuOoxT926blJqOyYmF2GDLMhZaoxrV1_BbNYcYEQeQzCwlY06uRcdZBQl-ZidiSJ7rwLZ5SHnuh-M4i_3qPZTnSJsTc5HdXyoOOwRuXUoav9cYUXxoT8pyQ=w1235-h625-no)

Como podemos observar por medio del comando Docker le indicamos el numero de nodos de Selenium grid podemos crear `desiredContainers` una vez el contenedor arranque de manera correcta este despliegas 3 paneles de visualización diferentes: 

1. **Grid Console:** permite visualizar la cantidad de nodos registros por Zalenium, a si mismo como se establece la configuración de los mismos.

 + http://localhost:4444/grid/console

![Vista gird Console en el navegador](https://lh3.googleusercontent.com/L8kG0DeYYCQ1OfWABrzXtbNxz48dz7co-hIkMFaQyexZl94s34X_rjUML5VUdV1mvX7WPe3-dHeV3sW1bnwChzB3_IN1CJudP4PNIOO7ERxd977HrfqmDbP-KypSyr_mVOxylbyjh4NzhrpwAQ5NvMnpllLgb58hsohYFQFmieLIyiN0Sn1l_ptm2LyiY2oceb_h7GKrCHZD4y4bhNnf0MOBu4kANo43u39HEkSvjlmYOlqwxIvEP-xUvOh_a3xgOJp5zNcOoFUiGuBwOKO3ne9aPd2tt_EkN4TpuxVndhmtLG4nRO-fGLbcbHmMD6gt1X1zGA7TQByIE8fjyl2YSAFw-0AtXhLaJtc77rLrCByEW9mSQaSAXHUq3FnUGc41QrbcqmOHuCOteJyyCecJ8I71PoCv121ZaxbMTO8YYqCKMNbmiYgLnAW_9_eL2lndKIQFKNbThMa6A4Op0FaqtaoYqueTbl_yrcgkFYIXYWcz6fg1uYCwz7vBHk0uStXfuWF8s2yVFvxGH0zqlDem5LD2JpcWqitXgjWvKza25kkqxNxIgYaeYmQCN65Onw_uo-cxINOZEpM_xaQranCb6k0qwBSkc7ey-nJ-IsX4XoRzAYdGGu9w9tQMkZBkZ5NluWSNL_8BDmbDmmdPM-yPq1H9GuwepdpAKz92yuw6PpMLwPKvcllh5oHZAWtbgQ=w1171-h807-no)
 
2. **Zalenium – Dashboard:** Tampronto termine el proceso de ejecucion de la prueba, Zalenium guarda el registro y estado dela mismas 
								incluyendo un video donde se visualiza la prueba 

+ http://localhost:4444/dashboard/

![Vista dashboard zalenium](https://lh3.googleusercontent.com/ztR8ApHHdMwbuuBu2IY0fsU20YR_Vph52qJJnugxBtJFWyRGnQ5LVrm05AfGHrSTY-DKu9nTdsApqbGnA6Qc3S7H3KfgOO7FtGx2h3nqDVsj1lWitDJINlPwofx878GepM2ObJ0zb1rAD572NjQ2uF3fuSddj-XayIZA02lIjie-MBCKSYv5P9dAuFhZuMXxE4fhsNlendnoqQos_pXzIbBGo_U_NazWv3fgT0nPE5na9kC_oGYsY2AU1lfRpm0lmHE563rUiDT3BUx_59GxOv4GVNK7z_JXCAgL3CLBI32awUPejPpl62PckhYnpOnmyneNcgtAeD5W9SBYRX5Qyzmb77BZO6WyE7-j2zQbTgDPkmZwPS8zEd6UAa8fUMdxbFrXBhvNhA8o9YyJZwduq2ESKzDH5vT9kuzx79jdw8qnWsVoosHRlEi27PmREoezjhkYTzBHF0mNYGB8ui1dNFeuPVTs0o5v4lsg3-ZrnZUtHnBV3yMfumFP4wQsXa8mVWY1mQcVq-CFBjbDjuLqoUnv7x1R7sDThyLLaPSkPbfm3qyqwV4j8b2B108it2_9q_hZAVRFXq8asNtd0ZustPE9FtBQCVh7viHOsou-nYTxKUk0alpD3VhgIdq6lGko2rPZq_m3RbKIburi492e0MZsiBP3X5nkrhtAoyl-byRNYPmCCV1fP5lEyZPvsQ=w1273-h465-no)

3. **Zalenium Live Preview:** Por medio de este panel se puede visualizar la ejecución de la prueba, acceder directamente al nodo de selenium grid permitiendo interactuar con él. 
								Tiene una serie de configuraciones donde encontramos la dirección de red dirigiéndose al proxy creada por selenium grid , cambar colores del menú entre otras funciones.

+ http://localhost:4444/grid/admin/live

![Vista zalenium gid live](https://lh3.googleusercontent.com/v6aYxtBTX8X06YBxwUX8guH_Y0N9n2FgBN4bY7PVsL1LnmcLOTaGTuNYl0r1RpJgnSGvi8c5jtLjIJxr7spew_AFnbTQieTb3hp6ntF39YuSDqqCQNfzg-nKskhAXRrCCWSxnyEFuG0jySWe0YvgfbAFgKJd6oqjnOK7RJpARR-_OOcdO6xIXqQhBOfx63DAnOvGsi1Sem_BGutXAOI-dy9JIJ49RNI8p-u0-qsIflq6B6lfZafnbHKTuKuhjNj1K3v5oVDbV3WzPVulMpaL_ni5mypG_kKo0wUEk4pt82eaBOj75pc2zgYo3W81fJhB2tmX071q9uIL55WSTRTJJ9Hq0xPlQIYJUKLfJgW46lfZ-pI77sMdJp2JI5akE_90CKDN0xFxaKT6NOuzZTQqj4HUxiy6JfEKPL9QPobBlrAIJwuaq2AmN-C0IEH2s8s-CSGVpzt-7eqpxKuHjESNnJfT49vNCST_hm5kdEHswfUtzkW-y1otcfOKeZbzqBSXozvBmW93ziylM75alVp463Jc-nXQimolaBGGbUfoj4n-QSoVdG4c7mj6S3pF_l0QNZAQFV1Gr5Ms1kbQiDJuniQaqBZTd2-bVD-wHxLnbCVx73AmpUZT4wRS1DmvIfEPVS_wzix-piQ7ob012HNgKdITJYFBQXJySPgyNl8ygxAAxrKvGFGWpE2aUvh-_A=w1151-h569-no)
	
	

### comando gradle 

```
gradle clean  test aggregate --info
```

