#language: es

    Característica: busqueda simple con google

      @BusquedaGoogle
          Escenario: Utilizar el navegador google para realizar una busqueda simple
          Dado El usuario ingresa al navegador google
          Cuando ingresa la palabra a buscar
          Entonces verifica la palabra buscada
