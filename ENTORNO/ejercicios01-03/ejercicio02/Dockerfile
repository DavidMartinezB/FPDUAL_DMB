# Ejercicio 02 - Hola mundo parametrizado
#
# Este ejercicio consiste en conseguir construir un contenedor que realice un hola mundo parametrizado por consola cada vez que se ejecute
# Utilizaremos la imagen base de alpine, basada en UNIX
# Crearemos una variable de entorno con la variable NOMBRE, dandole el valor por defecto "mundo"
#   de esta manera la respuesta será "Hola mundo!" o "Hola Juan!", segun si se arranca o no el contenedor con una variable de entrada 
# ====================================================================================================================================

# Utilizamos la imagen base Alpine para el hola mundo, ya que es bastante ligera

# Cambiamos al usuario root, asi no tendremos problemas si queremos ejecutar comandos con elevacion de permisos

# Damos un valor por defecto a la variable NOMBRE, que podremos cambiar al lanzar una ejecucion

# Ejecuta el comando cuando el contenedor se inicie. Pista: Las comillas dobles admiten caracteres de sustitucion

# Cambiamos al usuario 1001 (Usuario no privilegiado, estandar en la comunidad de contenedores)

FROM alpine:3.14

USER root

ENV NOMBRE=mundo

CMD echo "hola $NOMBRE"

USER 1001
