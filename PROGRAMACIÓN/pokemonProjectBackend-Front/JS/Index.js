let total;
let offset = 0;
const limit = 20;
const listas = {};

const main = document.getElementsByClassName('main');
const createForm = document.getElementById('createForm')
const pokemonCards = document.querySelectorAll(".contenedor-Pokemon");
const container = document.getElementById('container');
const buscador = document.getElementById('searchButton');
const nombreForm = document.getElementById('nameForm');
const pesoForm = document.getElementById('weightForm');
const alturaForm = document.getElementById('heightForm');
const crearPokemon = document.getElementById('crearPokemon');
const anterior = document.getElementById('anterior');
const siguiente = document.getElementById('siguiente');

async function getTotalPokemon() {
    try {
        const resp = await fetch(`http://localhost:8080/pokemondb/pokemon`);
        if (resp.ok) {
            const respJson = await resp.json();
            if (respJson != null) {
                console.log(respJson);
                return {
                    nombre: respJson.nombre,
                    id: respJson.numero_pokedex,
                    peso: respJson.peso,
                    altura: respJson.altura
                };
            } else {
                siguiente.style.display = "none";
            }
        } else {
            throw new Error('Error al obtener los detalles del Pokémon');
        }
    } catch (error) {
        console.error(error);
        return null;
    }
}

total = getTotalPokemon;

async function getPokemonInfo(i) {
    try {
        const resp = await fetch(`http://localhost:8080/pokemondb/pokemon/${i}`);
        if (resp.ok) {
            const respJson = await resp.json();
            if (respJson !== null) {
                return {
                    nombre: respJson.nombre,
                    id: respJson.numero_pokedex,
                    peso: respJson.peso,
                    altura: respJson.altura
                };
            } else {
                return null;
            }
        } else {
            throw new Error('Error al obtener los detalles del Pokémon');
        }
    } catch (error) {
        console.error(error);
        return null;
    }
}

async function getPokemonIMGList(offset, limit) {
    const maxLimit = Math.min(limit, 151);
    const pokeImgArray = [];
    for (let i = offset + 1; i <= offset + maxLimit + 1; i++) {
        if (i <= 151) {
            try {
                const respImg = await fetch(`https://pokeapi.co/api/v2/pokemon/${i}`);
                if (respImg.ok) {
                    const respJsonImg = await respImg.json();
                    const img = respJsonImg.sprites.front_default;
                    const imgInfo = respJsonImg.sprites.other['official-artwork'].front_default;
                    pokeImgArray.push({ img, imgInfo });
                } else {
                    throw new Error('Error al obtener las imágenes del Pokémon');
                }
            } catch (error) {
                console.error(error);
                return null;
            }

        } else {
            try { 
                const respImg = await fetch(`https://pokeapi.co/api/v2/pokemon/25`);
                if (respImg.ok) {
                    const respJsonImg = await respImg.json();
                    const img = respJsonImg.sprites.front_default;
                    const imgInfo = respJsonImg.sprites.other['official-artwork'].front_default;
                    pokeImgArray.push({ img, imgInfo });
                } else {
                    throw new Error('Error al obtener las imágenes del Pokémon');
                }
            } catch (error) {
                console.error(error);
                return null;
            }
        }
    }
    return pokeImgArray;
}

async function getPokemonList(offset, limit) {
    var pokeArray = [];
    for (let i = offset + 1; i <= offset + limit; i++) {
        console.log("Llamando a getPokemonInfo() desde getPokemonList()");
        const info = await getPokemonInfo(i);
        pokeArray.push(info);
    }
    return pokeArray;
}

function getPokemonById() {

}

function siguientePag() {
    offset += limit;
    console.log(offset);
    Promise.all([getPokemonList(offset, limit), getPokemonIMGList(offset, limit)])
        .then(([lista, imgList]) => {
            createDiv(lista, imgList);
        });
}

function anteriorPag() {
    offset -= limit;
    console.log(offset);
    Promise.all([getPokemonList(offset, limit), getPokemonIMGList(offset, limit)])
        .then(([lista, imgList]) => {
            createDiv(lista, imgList);
        });
}

function crearDetallesPokemon(event, pokeArray) {
    document.getElementById('container');
    const blurDiv = document.createElement('div');
    const divDetalles = document.createElement('div');
    const divDetallesImagen = document.createElement('div');
    const divDetallesContent = document.createElement('div');
    const divDetallesContentDesc = document.createElement('div');
    const divDetallesContentStats = document.createElement('div');
    const divDetallesContentTypes = document.createElement('div');
    const tituloPoke = document.createElement('h2');
    const imagen = document.createElement('img');
    const peso = document.createElement('p');
    const altura = document.createElement('p');
    const id = document.createElement('p');
    const desc = document.createElement('p');

    const target = event.currentTarget;
    const imgPokemon = target.querySelector('.imagenInfo-Pokemon').src;
    const nombrePokemon = target.querySelector('.nombre-Pokemon').textContent;
    const idPokemon = target.querySelector('.id-Pokemon').textContent;
    const pesoPokemon = target.querySelector('.peso-Pokemon').textContent;
    const alturaPokemon = target.querySelector('.altura-Pokemon').textContent;
    const pokemon = pokeArray.find(poke => poke.nombre === nombrePokemon);

    tituloPoke.textContent = nombrePokemon;
    peso.textContent = `Peso del pokemon: ${pesoPokemon}`;
    altura.textContent = `Altura del Pokemon: ${alturaPokemon}`;
    id.textContent = `Altura del Pokemon: ${idPokemon}`;
    imagen.src = imgPokemon;

    divDetallesImagen.classList.add('detalles-divImagen-Pokemon');
    divDetallesContent.classList.add('detalles-divContent-Pokemon');
    divDetallesContent.classList.add('detalles-divContentDesc-Pokemon');
    divDetallesContent.classList.add('detalles-divContentStats-Pokemon');
    divDetallesContent.classList.add('detalles-divContentTypes-Pokemon');
    peso.classList.add('detalles-peso-Pokemon');
    imagen.classList.add('detalles-imagen-Pokemon');
    id.classList.add('detalles-id-Pokemon');
    tituloPoke.classList.add('detalles-titulo-Pokemon');
    altura.classList.add('detalles-altura-Pokemon');
    divDetalles.classList.add('detalles-Pokemon');
    blurDiv.classList.add('blur-Pokemon');


    blurDiv.appendChild(divDetalles);
    divDetallesImagen.appendChild(tituloPoke);
    divDetallesImagen.appendChild(imagen);
    divDetallesContentDesc.appendChild(desc);
    divDetallesContentStats.appendChild(peso);
    divDetallesContentStats.appendChild(id);
    divDetallesContentStats.appendChild(altura);
    divDetallesContent.appendChild(divDetallesContentDesc);
    divDetallesContent.appendChild(divDetallesContentStats);
    divDetallesContent.appendChild(divDetallesContentTypes);
    divDetalles.appendChild(divDetallesImagen);
    divDetalles.appendChild(divDetallesContent);
    container.appendChild(blurDiv);
    divDetalles.addEventListener('click', () => {
        divDetalles.remove();
        blurDiv.remove();
    });
}


function createDiv(pokeArray, pokeImgArray) {
    const container = document.getElementById('container');
    while (container.firstChild) {
        container.removeChild(container.lastChild);
    }
    console.log(pokeArray);
    console.log(pokeImgArray);
    for (i = 0; i < pokeArray.length; i++) {
        var div = document.createElement('div');
        var imagen = document.createElement('img')
        var name = document.createElement('p');
        var id = document.createElement('p');
        var peso = document.createElement('p');
        var altura = document.createElement('p');
        var imagenInfo = document.createElement('img');

        name.textContent = pokeArray[i].nombre;
        peso.textContent = pokeArray[i].peso;
        altura.textContent = pokeArray[i].altura;
        id.textContent = pokeArray[i].id;
        imagenInfo.src = pokeImgArray[i].imgInfo;
        imagen.src = pokeImgArray[i].img;

        id.classList.add('id-Pokemon');
        imagen.classList.add('imagen-Pokemon');
        imagenInfo.classList.add('imagenInfo-Pokemon');
        name.classList.add('nombre-Pokemon');
        peso.classList.add('peso-Pokemon');
        altura.classList.add('altura-Pokemon');
        div.classList.add('contenedor-Pokemon');

        div.addEventListener('click', event => {
            crearDetallesPokemon(event, pokeArray);
        });

        div.appendChild(imagenInfo)
        div.appendChild(peso);
        div.appendChild(altura);
        div.appendChild(name);
        div.appendChild(imagen);
        div.appendChild(id);
        container.appendChild(div);

        imagenInfo.style.display = "none";
        peso.style.display = "none";
        altura.style.display = "none";
    }
}
function searchPokemonById(pokemonId) {
    fetch(`https://pokeapi.co/api/v2/pokemon/${pokemonId}`)
        .then(response => response.json())
        .then(data => {
            const pokemon = {
                id: data.id,
                name: data.name,
                image: data.sprites.front_default,
                height: data.height,
                weight: data.weight
            };
            displayPokemon(pokemon);
        })
        .catch(error => {
            console.log("Error al buscar el Pokémon:", error);
        });
}

function displayPokemon(pokemon) {
    const eliminarPokemon = document.createElement("p");
    eliminarPokemon.textContent = 'Eliminar este pokemon';

    const tarjetaPokemon = document.createElement("div");
    tarjetaPokemon.classList.add("contenedor-Pokemon");

    const pokemonImage = document.createElement("img");
    pokemonImage.src = pokemon.image;
    pokemonImage.alt = pokemon.name;
    tarjetaPokemon.appendChild(pokemonImage);

    const pokemonName = document.createElement("p");
    pokemonName.textContent = pokemon.name;
    tarjetaPokemon.appendChild(pokemonName);

    const pokemonId = document.createElement("p");
    pokemonId.textContent = `ID: ${pokemon.id}`;
    tarjetaPokemon.appendChild(pokemonId);

    const pokemonHeight = document.createElement("p");
    pokemonHeight.textContent = `Altura: ${pokemon.height} dm`;
    tarjetaPokemon.appendChild(pokemonHeight);

    const pokemonWeight = document.createElement("p");
    pokemonWeight.textContent = `Peso: ${pokemon.weight} hg`;
    tarjetaPokemon.appendChild(pokemonWeight);

    clearPokemon();
    container.appendChild(tarjetaPokemon);
    container.appendChild(eliminarPokemon);
}

function clearPokemon() {
    container.innerHTML = "";
}

getPokemonList(offset, limit).then(lista => {
    getPokemonIMGList(offset, limit).then(imgLista => {
        createDiv(lista, imgLista);
    });
});

buscador.addEventListener("input", () => {
    const pokemonId = parseInt(buscador.value);
    if (!isNaN(pokemonId) && pokemonId > 0 && pokemonId < 151) {
        searchPokemonById(pokemonId);
    } else {
        clearPokemon();
        getPokemonList(offset, limit).then(lista => {
            getPokemonIMGList(offset, limit).then(imgLista => {
                createDiv(lista, imgLista);
            });
        });

    }
});

createForm.addEventListener("submit", (event) => {
    event.preventDefault();

    const formData = new FormData(event.target);
    const datosPokemon = Object.fromEntries(formData.entries());

    fetch(`http://localhost:8080/pokemondb/pokemon/save`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datosPokemon)
    })
        .then(response => response.json())
        .then(data => { console.log(data) })
        .catch(error => {
            console.error('No se pudo crear el pokemon');
        })

})

siguiente.addEventListener("click", siguientePag);
anterior.addEventListener("click", anteriorPag);