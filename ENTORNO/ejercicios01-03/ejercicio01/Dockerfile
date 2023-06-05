# Ejercicio 01 - Hola mundo! 
#
# Este ejercicio consiste en conseguir construir un contenedor que realice un hola mundo por consola cada vez que se ejecute
# Utilizaremos la imagen base de alpine, basada en UNIX
# Realizaremos algunos pasos que, aunque no son estrictamente necesarios, nos haran conocer mas instrucciones del Dockefile
# ====================================================================================================================================

# Utilizamos la imagen base Alpine para el hola mundo, ya que es bastante ligera

# Cambiamos al usuario root, asi no tendremos problemas si queremos ejecutar comandos con elevacion de permisos

# Ejecuta el comando cuando el contenedor se inicie. Las comillas simples muestran el literal, no admiten caracteres de sustitucion.

# Cambiamos al usuario 1001 (Usuario no privilegiado, estandar en la comunidad de contenedores)

FROM alpine:3.14

USER root

CMD echo "hola mundo!"

USER 1001