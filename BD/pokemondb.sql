DROP DATABASE IF EXISTS pokemondb;
CREATE DATABASE pokemondb
CHARACTER SET UTF8MB4;
USE pokemondb;

CREATE TABLE tipo_ataque (
	id_tipo_ataque INT UNSIGNED,
    tipo VARCHAR (8) NOT NULL,
    PRIMARY KEY(id_tipo_ataque)
);

CREATE TABLE tipo (
	id_tipo INT UNSIGNED,
    nombre VARCHAR (10) NOT NULL,
    id_tipo_ataque INT UNSIGNED,
    PRIMARY KEY(id_tipo),
	CONSTRAINT pok_tipo_fk FOREIGN KEY (id_tipo_ataque) REFERENCES tipo_ataque(id_tipo_ataque)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE movimiento (
	id_movimiento INT UNSIGNED,
    nombre VARCHAR (20) NOT NULL,
    potencia INT UNSIGNED NOT NULL,
    precision_mov INT UNSIGNED NOT NULL,
    descripcion VARCHAR(500) NOT NULL,
    pp INT UNSIGNED NOT NULL,
    id_tipo INT UNSIGNED,
    prioridad INT NOT NULL,
    PRIMARY KEY(id_movimiento),
    CONSTRAINT pok_movimiento_fk FOREIGN KEY (id_tipo) REFERENCES tipo(id_tipo)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE pokemon (
	numero_pokedex INT UNSIGNED,
    nombre VARCHAR (15) NOT NULL,
    peso FLOAT (6.3) NOT NULL,
    altura FLOAT (6.3) NOT NULL,
    PRIMARY KEY(numero_pokedex)
);

CREATE TABLE pokemon_tipo (
	numero_pokedex INT UNSIGNED,
    id_tipo INT UNSIGNED,
    PRIMARY KEY(numero_pokedex, id_tipo),
    CONSTRAINT pok_pokemon_tipo_fk FOREIGN KEY (id_tipo) REFERENCES tipo(id_tipo)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
	CONSTRAINT pok_pokemon_tipo_fk1 FOREIGN KEY (numero_pokedex) REFERENCES pokemon(numero_pokedex)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE estadisticas_base (
	numero_pokedex INT UNSIGNED,
    ps INT UNSIGNED,
    ataque INT UNSIGNED,
    defensa INT UNSIGNED,
    especial INT UNSIGNED,
    velocidad INT UNSIGNED,
    PRIMARY KEY(numero_pokedex),
    CONSTRAINT pok_estadisticas_base_fk FOREIGN KEY (numero_pokedex) REFERENCES pokemon(numero_pokedex)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE pokemon_movimiento_forma (
	numero_pokedex INT UNSIGNED,
    id_movimiento INT UNSIGNED,
    id_forma_aprendizaje INT UNSIGNED,
    PRIMARY KEY(numero_pokedex, id_movimiento, id_forma_aprendizaje),
    CONSTRAINT pok_pokemon_movimiento_forma_fk FOREIGN KEY (numero_pokedex) REFERENCES pokemon(numero_pokedex)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
	CONSTRAINT pok_pokemon_movimiento_forma_fk1 FOREIGN KEY (id_movimiento) REFERENCES movimiento(id_movimiento)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE efecto_secundario (
    id_efecto_secundario INT UNSIGNED,
    efecto_secundario VARCHAR(30),
    PRIMARY KEY(id_efecto_secundario)
);


CREATE TABLE movimiento_efecto_secundario (
	id_movimiento INT UNSIGNED,
    id_efecto_secundario INT UNSIGNED,
    probabilidad FLOAT(6.3),
    PRIMARY KEY(id_movimiento, id_efecto_secundario),
    CONSTRAINT pok_movimiento_efecto_secundario_fk FOREIGN KEY (id_efecto_secundario) REFERENCES efecto_secundario(id_efecto_secundario)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
	CONSTRAINT pok_movimiento_efecto_secundario_fk1 FOREIGN KEY (id_movimiento) REFERENCES movimiento(id_movimiento)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE evoluciona_de (
	pokemon_evolucionado INT UNSIGNED,
    pokemon_origen INT UNSIGNED,
    PRIMARY KEY(pokemon_evolucionado, pokemon_origen),
    CONSTRAINT pok_evoluciona_de_fk FOREIGN KEY (pokemon_evolucionado) REFERENCES pokemon(numero_pokedex)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
	CONSTRAINT pok_evoluciona_de_fk1 FOREIGN KEY (pokemon_origen) REFERENCES pokemon(numero_pokedex)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE tipo_evolucion (
    id_tipo_evolucion INT UNSIGNED,
    tipo_evolucion VARCHAR(15),
    PRIMARY KEY(id_tipo_evolucion)
);

CREATE TABLE forma_evolucion (
    id_forma_evolucion INT UNSIGNED,
    tipo_evolucion INT UNSIGNED,
    PRIMARY KEY(id_forma_evolucion),
    CONSTRAINT pok_forma_evolucion_fk FOREIGN KEY (tipo_evolucion) REFERENCES tipo_evolucion(id_tipo_evolucion)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE nivel_evolucion (
    id_forma_evolucion INT UNSIGNED,
    nivel INT UNSIGNED,
    PRIMARY KEY(id_forma_evolucion),
    CONSTRAINT pok_nivel_evolucion_fk FOREIGN KEY (id_forma_evolucion) REFERENCES forma_evolucion(id_forma_evolucion)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE pokemon_forma_evolucion (
    numero_pokedex INT UNSIGNED,
    id_forma_evolucion INT UNSIGNED,
    PRIMARY KEY(numero_pokedex, id_forma_evolucion),
    CONSTRAINT pok_pokemon_forma_evolucion_fk FOREIGN KEY (numero_pokedex) REFERENCES pokemon(numero_pokedex)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
	CONSTRAINT pok_pokemon_forma_evolucion_fk1 FOREIGN KEY (id_forma_evolucion) REFERENCES forma_evolucion(id_forma_evolucion)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE tipo_piedra (
    id_tipo_piedra INT UNSIGNED,
    nombre_piedra VARCHAR(20),
    PRIMARY KEY(id_tipo_piedra)
);

CREATE TABLE piedra (
    id_forma_evolucion INT UNSIGNED,
    id_tipo_piedra INT UNSIGNED,
    PRIMARY KEY(id_forma_evolucion),
    CONSTRAINT pok_piedra_fk FOREIGN KEY (id_forma_evolucion) REFERENCES forma_evolucion(id_forma_evolucion)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT pok_piedra_fk1 FOREIGN KEY (id_tipo_piedra) REFERENCES tipo_piedra(id_tipo_piedra)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE tipo_forma_aprendizaje (
    id_tipo_aprendizaje INT UNSIGNED,
    tipo_aprendizaje VARCHAR(20),
    PRIMARY KEY(id_tipo_aprendizaje)
);

CREATE TABLE forma_aprendizaje (
    id_forma_aprendizaje INT UNSIGNED,
    id_tipo_aprendizaje INT UNSIGNED,
    PRIMARY KEY(id_forma_aprendizaje),
    CONSTRAINT pok_forma_aprendizaje_fk FOREIGN KEY (id_tipo_aprendizaje) REFERENCES tipo_forma_aprendizaje(id_tipo_aprendizaje)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE nivel_aprendizaje (
    id_forma_aprendizaje INT UNSIGNED,
    nivel INT UNSIGNED,
    PRIMARY KEY(id_forma_aprendizaje),
    CONSTRAINT pok_nivel_aprendizaje_fk FOREIGN KEY (id_forma_aprendizaje) REFERENCES forma_aprendizaje(id_forma_aprendizaje)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE mo (
    id_forma_aprendizaje INT UNSIGNED,
    mo VARCHAR(5),
    PRIMARY KEY(id_forma_aprendizaje),
    CONSTRAINT pok_mo_fk FOREIGN KEY (id_forma_aprendizaje) REFERENCES forma_aprendizaje(id_forma_aprendizaje)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE mt (
    id_forma_aprendizaje INT UNSIGNED,
    mt VARCHAR(5),
    PRIMARY KEY(id_forma_aprendizaje),
    CONSTRAINT pok_mt_fk FOREIGN KEY (id_forma_aprendizaje) REFERENCES forma_aprendizaje(id_forma_aprendizaje)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE pokemon_historico (
		numero_pokedex INT UNSIGNED,
		nombre VARCHAR (15) NOT NULL,
		peso FLOAT (6.3) NOT NULL,
		altura FLOAT (6.3) NOT NULL,
        fecha DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP 
    );

delimiter $$
CREATE TRIGGER crearTablaPokHistoricos AFTER INSERT ON pokemon  
FOR EACH ROW
BEGIN
    INSERT INTO pokemon_historico(numero_pokedex, nombre, peso, altura) VALUES (new.numero_pokedex, new.nombre, new.peso, new.altura);
END $$
delimiter ;


