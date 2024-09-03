Se realiza prueba de rastreo de IP:

Url del código: [https://github.com/jmiguel1121/test-rastreo/tree/main](https://github.com/jmiguel1121/test-rastreo/tree/main)

Clonar repositorio:

·       git clone [https://github.com/jmiguel1121/test-rastreo.git](https://github.com/jmiguel1121/test-rastreo.git)

Se validan las APIssugeridas, pero se realiza el cambio a otras gratuitas ya que algunas no permitíanobtener información relevante para la prueba por tema de permisos

Tecnologías:

·   Spring-boot

·   Webflux

·   r2dbc

·   Redis

·   H2

·   Thymeleaf

·   Jquery

Posterior a la descargade repositorio se debe ejecutar el siguiente comando en cmd:

·      cd test-rastreo/

·       docker-compose up --build -d

Esto descarga imágenesy dependencias necesarias para ejecutara la aplicación en un contenedor y a su vezejecutara un contenedor de Redis para el manejo de la cache.

Ya desplegada la aplicaciónse deberá acceder a la url: http://localhost:8080/ que permitirá acceder al Frontde la aplicacioni para ejecutar los diferentes servicios Api de la aplicación