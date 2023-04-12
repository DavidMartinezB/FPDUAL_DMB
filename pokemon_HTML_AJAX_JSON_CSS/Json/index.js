const url = 'https://pokeapi.co/api/v2/pokemon';

const containerImg = document.getElementsByClassName('container-img');
const containerName = document.getElementsByClassName('container-name');
const containerid = document.getElementsByClassName('container-id');
const containerType = document.getElementsByClassName('container-type');

const imgPokemon = document.getElementById('pokemon-img')
const nombrePokemon = document.getElementById('nombre-pokemon');
const numPokedex = document.getElementById('num-pokedex');
const tipoPokemon = document.getElementById('tipo-pokemon');

let arrayPokemon = [];


fetch(url+'/')
        .then(Response => {
            if (Response.ok) {
                return Response.json();
            }
            throw new Error('Error al cargar la API')
        })
        .then(data => {    
            imgPokemon.src = data.sprites.front_default;
        })

        .catch(error => console.error(error))