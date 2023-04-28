-- EJERCICIO 1 

select numero_pokedex ,nombre 
from pokemon;

-- EJERCICIO 2

select 
	numero_pokedex,
	nombre,
    peso
from pokemon
where peso<10;

-- EJERCICIO 3

select count(nombre)
from pokemon
where altura >= 0.5;

-- EJERCICIO 4

select * 
from movimiento
where (precision_mov between 75 and 100)
order by precision_mov desc;

-- EJERCICIO 4 v2

select * 
from movimiento
where (precision_mov > 75 and precision_mov <=100)
order by precision_mov desc;

-- EJERCICIO 5

select 
	nombre,
    peso
from pokemon
where peso = (select max(peso) from pokemon);

-- EJERCICIO 6

select *
from pokemon 
where numero_pokedex in (select numero_pokedex from pokemon_tipo where id_tipo = (select id_tipo from tipo where nombre = 'fuego'));

-- EJERCICIO 7

select 
	count(numero_pokedex)
from pokemon 
where numero_pokedex in (select numero_pokedex from pokemon_tipo where id_tipo = (select id_tipo from tipo where nombre = 'dragón'));

-- EJERCICIO 8

select *
from movimiento
where descripcion like 'Causa%';

-- EJERCICIO 9

select * 
from pokemon 
where numero_pokedex in (select numero_pokedex 
						from pokemon_tipo where id_tipo in (select id_tipo from tipo where id_tipo_ataque = (select id_tipo_ataque from tipo_ataque where tipo = 'especial')));
                        
-- EJERCICIO 10

select *
from pokemon as p
where numero_pokedex in (select numero_pokedex from pokemon_tipo where id_tipo in (select id_tipo from tipo where nombre = 'fuego') and 
		numero_pokedex in (select numero_pokedex from pokemon_tipo where id_tipo in (select id_tipo from tipo where nombre = 'volador')));
        
-- EJERCICIO 11 Y SEGUNDA PARTE

select 
	nombre,
    potencia
from movimiento
where potencia = (select max(potencia) from movimiento);

-- EJERCICIO 12

select 
	p.numero_pokedex,
    p.nombre,
    e.ataque,
    e.velocidad
from pokemon p
	join estadisticas_base e on e.numero_pokedex = p.numero_pokedex and (velocidad < 75 and ataque >50)
order by p.nombre asc;

-- EJERCICIO 13
 
select 
	t.nombre,
    count(t.id_tipo) as cuenta_de_movimientos 
from tipo t
	join movimiento m on t.id_tipo = m.id_tipo
group by nombre;

-- EJERCICIO 14

select 
	m.nombre,
    me.probabilidad
from movimiento m
	join movimiento_efecto_secundario me on m.id_movimiento = me.id_movimiento
    join efecto_secundario e on me.id_efecto_secundario = e.id_efecto_secundario and e.efecto_secundario like 'Envene%';
    
-- EJERCICIO 15

select 
	m.nombre
from movimiento m
	join pokemon_movimiento_forma pmf on pmf.id_movimiento = m.id_movimiento
	join pokemon p on p.numero_pokedex = pmf.numero_pokedex and p.nombre = 'pikachu';
    
-- EJERCICIO 16
	
select 
	p.nombre,
    p.numero_pokedex
from pokemon p
	join pokemon_forma_evolucion pfe on p.numero_pokedex = pfe.numero_pokedex
	join forma_evolucion fe on fe.id_forma_evolucion = pfe.id_forma_evolucion
    join piedra pd on pd.id_forma_evolucion = fe.id_forma_evolucion;
    
-- EJERCICIO 17

select
	nombre,
    numero_pokedex
from pokemon 
where numero_pokedex not in (select numero_pokedex from pokemon_forma_evolucion);

-- FORMA MALA

select 
	pokemon_evolucionado, 
    pokemon.nombre
from evoluciona_de
	join pokemon on pokemon.numero_pokedex = evoluciona_de.pokemon_evolucionado 
where pokemon_evolucionado not in (select pokemon_origen from evoluciona_de);

-- EJERCICIO 18 

select 
	t.nombre,
    count(p.nombre)
from tipo t
	join pokemon_tipo pt on pt.id_tipo = t.id_tipo
    join pokemon p on p.numero_pokedex = pt.numero_pokedex
group by t.nombre;

-- EJERCICIO 19

select 
	m.nombre
from movimiento m
	join pokemon_movimiento_forma pmf on pmf.id_movimiento = m.id_movimiento
    join forma_aprendizaje fa on fa.id_forma_aprendizaje = pmf.id_forma_aprendizaje
    join mo mo on mo.id_forma_aprendizaje = fa.id_forma_aprendizaje
    join pokemon p on p.numero_pokedex = pmf.numero_pokedex and p.nombre = 'charmander';
    
-- TRIGGERS

select * from pokemon_historico;
	


	