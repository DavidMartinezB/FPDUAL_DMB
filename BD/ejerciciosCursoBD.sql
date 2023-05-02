-- Creación de la base de datos 

drop database if exists registrosLogin;
create database registrosLogin
character set utf8mb4;
use registrosLogin;

-- Creación de las tablas de la base de datos

CREATE TABLE `usuarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `usuario` varchar(45) NOT NULL,
  `correo` varchar(45) NOT NULL,
  `contrasena` varchar(45) NOT NULL,
  `fecha` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ; /*Tabla que recoge los datos de los usuarios que se recogen y que
		luego se usa para el login*/
        
CREATE TABLE `sesion` (
  `id` int NOT NULL AUTO_INCREMENT,
  `usuario` varchar(45) NOT NULL,
  `fecha` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ; /*Tabla que recoge las sesiones que se han iniciado por los usuarios,
		con sus fechas y horas*/

-- TRIGGER QUE UNIRÍA LAS DOS TABLAS PARA RECOGER LAS SESIONES

DELIMITER $$ 
CREATE TRIGGER introducir_sesion AFTER INSERT ON usuarios
FOR EACH ROW
BEGIN
INSERT INTO sesion (id, usuario, contrasena) value (new.id, new.usuario, new.contrasena);
END $$
DELIMITER ;

-- EJEMPLO DE INTRODUCCIÓN DE USUARIOS QUE SE REALIZARÍA

INSERT INTO usuarios (usuario, correo, contrasena) VALUES 
	('userEjemplo', 'correoEjemplo@xxxx.com', '1234');


