
let offset = 0;
const limit = 20;

const anterior = document.getElementById('anterior');
const siguiente = document.getElementById('siguiente');

function getPokemonInfo(i) {   
    var info = {};
    fetch(`https://pokeapi.co/api/v2/pokemon/${i}`)
        .then(Response => {
            if (Response.ok) {
                return Response.json();
            }
            throw new Error('Error al cargar la API')
        })
        .then(data => {
            info.nombre = data.name;
            info.id = data.id;
            info.img = data.sprites.front_default;
            console.log('finalizado------');
        });
    return info;
}

function getPokemonList(offset, limit) {
    var pokeArray = [];
    for (let i = offset+1; i < offset+limit+1; i++) {
        pokeArray.push(getPokemonInfo(i));
    }
    return pokeArray;
}

function siguientePag() {
    offset += limit;
    pokeArray = getPokemonList(offset, limit);
    // TODO: Eliminar divs actuales
    createDiv(pokeArray);
}

function createDiv(pokeArray) {
    const container = document.getElementById('container');
    while (container.firstChild) {
        container.removeChild(container.lastChild);
    }
    for (i = 0; i < pokeArray.length; i++) {
        var div = document.createElement('div');
        var imagen = document.createElement('img')
        var name = document.createElement('p');
        var id = document.createElement('p');

        name.textContent += pokeArray[i].nombre; 
        id.textContent = pokeArray[i].id; 
        imagen.src = pokeArray[i].img;


        id.classList.add('id-Pokemon');
        imagen.classList.add('imagen-Pokemon');
        name.classList.add('nombre-Pokemon');
        div.classList.add('contenedor-Pokemon');

        div.appendChild(imagen);
        div.appendChild(id);
        div.appendChild(name);
        container.appendChild(div);
    }
}

document.addEventListener("DOMContentLoaded", event => {
    var lista = getPokemonList(offset, limit);
    console.log(lista)
    createDiv(lista);
})


siguiente.addEventListener("click", siguientePag);

