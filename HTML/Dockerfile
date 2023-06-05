# Ejercicio 04 - Levantar un servidor web
#
# Este ejercicio consiste en conseguir construir un contenedor que levante un servicio web, y poder acceder desde el exterior
# Utilizaremos una imagen de nginx. En la documentacion podreis ver donde habra que dejar los archivos para que el servidor
# pueda levantarlos

# Si tuviesemos algun problema al levantar el servidor este no diese servicio, podriamos navegar dentro del contenedor
# de manera similar al ejercicio anterior, para llegar al directorio indicado de nginx y comprobar si tenemos alli los
# archivos necesarios
# ====================================================================================================================================

# Utilizamos la imagen base nginx para montar el servidor


# Copiamos la web al path indicado en la documentacion de nginx, en DockerHub
# Podremos hacerlo desde una ruta local, usando CP; 
# o directamente desde un remoto, usando ADD;
# otra forma es usando RUN git clone https://forja.com/repo.git
# CUIDADO: Cuidado con ADD a remoto y con "git clone", el destino puede tener una estructura de carpetas distinta a la que esperamos 

# Exponemos el puerto 80 al exterior. A la hora de arrancar el contenedor, mapearemos con el 8080 de la maquina local

FROM nginx

COPY ./pokemonApi usr/share/nginx/html

EXPOSE 80
