drop database if exists pokemondb;
create database pokemondb
character set utf8mb4;
use pokemondb;

create table tipo_ataque (
	id_tipo_ataque int unsigned,
    tipo varchar (8) not null,
    primary key(id_tipo_ataque)
);

create table tipo (
	id_tipo int unsigned,
    nombre varchar (10) not null,
    id_tipo_ataque int unsigned,
    primary key(id_tipo),
	constraint pok_tipo_fk foreign key (id_tipo_ataque) references tipo_ataque(id_tipo_ataque)
		on delete cascade
        on update cascade
);

create table movimiento (
	id_movimiento int unsigned,
    nombre varchar (20) not null,
    potencia int unsigned not null,
    precision_mov int unsigned not null,
    descripcion varchar(500) not null,
    pp int unsigned not null,
    id_tipo int unsigned,
    prioridad int not null,
    primary key(id_movimiento),
    constraint pok_movimiento_fk foreign key (id_tipo) references tipo(id_tipo)
		on delete cascade
        on update cascade
);

create table pokemon (
	numero_pokedex int unsigned,
    nombre varchar (15) not null,
    peso float (6.3) not null,
    altura float (6.3) not null,
    primary key(numero_pokedex)
);

create table pokemon_tipo (
	numero_pokedex int unsigned,
    id_tipo int unsigned,
    primary key(numero_pokedex, id_tipo),
    constraint pok_pokemon_tipo_fk foreign key (id_tipo) references tipo(id_tipo)
		on delete cascade
        on update cascade,
	constraint pok_pokemon_tipo_fk1 foreign key (numero_pokedex) references pokemon(numero_pokedex)
		on delete cascade
        on update cascade
);

create table estadisticas_base (
	numero_pokedex int unsigned,
    ps int unsigned,
    ataque int unsigned,
    defensa int unsigned,
    especial int unsigned,
    velocidad int unsigned,
    primary key(numero_pokedex),
    constraint pok_estadisticas_base_fk foreign key (numero_pokedex) references pokemon(numero_pokedex)
		on delete cascade
        on update cascade
);

create table pokemon_movimiento_forma (
	numero_pokedex int unsigned,
    id_movimiento int unsigned,
    id_forma_aprendizaje int unsigned,
    primary key(numero_pokedex, id_movimiento, id_forma_aprendizaje),
    constraint pok_pokemon_movimiento_forma_fk foreign key (numero_pokedex) references pokemon(numero_pokedex)
		on delete cascade
        on update cascade,
	constraint pok_pokemon_movimiento_forma_fk1 foreign key (id_movimiento) references movimiento(id_movimiento)
		on delete cascade
        on update cascade
);

create table efecto_secundario (
    id_efecto_secundario int unsigned,
    efecto_secundario varchar(30),
    primary key(id_efecto_secundario)
);


create table movimiento_efecto_secundario (
	id_movimiento int unsigned,
    id_efecto_secundario int unsigned,
    probabilidad float(6.3),
    primary key(id_movimiento, id_efecto_secundario),
    constraint pok_movimiento_efecto_secundario_fk foreign key (id_efecto_secundario) references efecto_secundario(id_efecto_secundario)
		on delete cascade
        on update cascade,
	constraint pok_movimiento_efecto_secundario_fk1 foreign key (id_movimiento) references movimiento(id_movimiento)
		on delete cascade
        on update cascade
);

create table evoluciona_de (
	pokemon_evolucionado int unsigned,
    pokemon_origen int unsigned,
    primary key(pokemon_evolucionado, pokemon_origen),
    constraint pok_evoluciona_de_fk foreign key (pokemon_evolucionado) references pokemon(numero_pokedex)
		on delete cascade
        on update cascade,
	constraint pok_evoluciona_de_fk1 foreign key (pokemon_origen) references pokemon(numero_pokedex)
		on delete cascade
        on update cascade
);

create table tipo_evolucion (
    id_tipo_evolucion int unsigned,
    tipo_evolucion varchar(15),
    primary key(id_tipo_evolucion)
);

create table forma_evolucion (
    id_forma_evolucion int unsigned,
    tipo_evolucion int unsigned,
    primary key(id_forma_evolucion),
    constraint pok_forma_evolucion_fk foreign key (tipo_evolucion) references tipo_evolucion(id_tipo_evolucion)
		on delete cascade
        on update cascade
);

create table nivel_evolucion (
    id_forma_evolucion int unsigned,
    nivel int unsigned,
    primary key(id_forma_evolucion),
    constraint pok_nivel_evolucion_fk foreign key (id_forma_evolucion) references forma_evolucion(id_forma_evolucion)
		on delete cascade
        on update cascade
);

create table pokemon_forma_evolucion (
    numero_pokedex int unsigned,
    id_forma_evolucion int unsigned,
    primary key(numero_pokedex, id_forma_evolucion),
    constraint pok_pokemon_forma_evolucion_fk foreign key (numero_pokedex) references pokemon(numero_pokedex)
		on delete cascade
        on update cascade,
	constraint pok_pokemon_forma_evolucion_fk1 foreign key (id_forma_evolucion) references forma_evolucion(id_forma_evolucion)
		on delete cascade
        on update cascade
);

create table tipo_piedra (
    id_tipo_piedra int unsigned,
    nombre_piedra varchar(20),
    primary key(id_tipo_piedra)
);

create table piedra (
    id_forma_evolucion int unsigned,
    id_tipo_piedra int unsigned,
    primary key(id_forma_evolucion),
    constraint pok_piedra_fk foreign key (id_forma_evolucion) references forma_evolucion(id_forma_evolucion)
		on delete cascade
        on update cascade,
    constraint pok_piedra_fk1 foreign key (id_tipo_piedra) references tipo_piedra(id_tipo_piedra)
		on delete cascade
        on update cascade
);

create table tipo_forma_aprendizaje (
    id_tipo_aprendizaje int unsigned,
    tipo_aprendizaje varchar(20),
    primary key(id_tipo_aprendizaje)
);

create table forma_aprendizaje (
    id_forma_aprendizaje int unsigned,
    id_tipo_aprendizaje int unsigned,
    primary key(id_forma_aprendizaje),
    constraint pok_forma_aprendizaje_fk foreign key (id_tipo_aprendizaje) references tipo_forma_aprendizaje(id_tipo_aprendizaje)
		on delete cascade
        on update cascade
);

create table nivel_aprendizaje (
    id_forma_aprendizaje int unsigned,
    nivel int unsigned,
    primary key(id_forma_aprendizaje),
    constraint pok_nivel_aprendizaje_fk foreign key (id_forma_aprendizaje) references forma_aprendizaje(id_forma_aprendizaje)
		on delete cascade
        on update cascade
);

create table mo (
    id_forma_aprendizaje int unsigned,
    mo varchar(5),
    primary key(id_forma_aprendizaje),
    constraint pok_mo_fk foreign key (id_forma_aprendizaje) references forma_aprendizaje(id_forma_aprendizaje)
		on delete cascade
        on update cascade
);

create table mt (
    id_forma_aprendizaje int unsigned,
    mt varchar(5),
    primary key(id_forma_aprendizaje),
    constraint pok_mt_fk foreign key (id_forma_aprendizaje) references forma_aprendizaje(id_forma_aprendizaje)
		on delete cascade
        on update cascade
);

create table pokemon_historico (
		numero_pokedex int unsigned,
		nombre varchar (15) not null,
		peso float (6.3) not null,
		altura float (6.3) not null,
        fecha datetime default current_timestamp on update current_timestamp 
    );

delimiter $$
create trigger crearTablaPokHistoricos after insert on pokemon  
for each row
begin
    insert into pokemon_historico(numero_pokedex, nombre, peso, altura) values (new.numero_pokedex, new.nombre, new.peso, new.altura);
end $$
delimiter ;


