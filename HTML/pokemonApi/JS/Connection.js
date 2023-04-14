
let offset = 0;
const limit = 20;
/* const maxPaginas = Math.ceil(905/limit); */

const anterior = document.getElementById('anterior');
const siguiente = document.getElementById('siguiente');

async function getPokemonMinInfo(i) {
    const resp = await fetch(`https://pokeapi.co/api/v2/pokemon/${i}`);
    const respJson = await resp.json();
    return {
        nombre: respJson.name,
        id: respJson.id,
        img: respJson.sprites.front_default,
        imgInfo: respJson.sprites.other["official-artwork"].front_default,
        tipos: respJson.types
    }
}

async function getPokemonMinList(offset, limit) {
    var pokeArray = [];
    for (let i = offset+1; i < offset+limit+1; i++) {
        console.log("Llamando a getPokemonInfo() desde getPokemonList()");
        const info = await getPokemonMinInfo(i);
        pokeArray.push(info);
    }
    return pokeArray;
}

function siguientePag() {
    /* if ((offset / limit) > ())
        offset = 905 - limit;
    else */
        offset += limit;
        console.log(offset);
        getPokemonMinList(offset, limit).then(lista => {
        createDiv(lista);
    });
    // TODO: Eliminar divs actuales
}

function anteriorPag() {
    /* if ((offset < limit))
        offset = 0;
    else */
        offset -= limit;
    console.log(offset);
    getPokemonMinList(offset, limit).then(lista => {
        createDiv(lista);
    });
}

function crearDetallesPokemon(event, pokeArray) {
    const divDetalles = document.createElement('div');
    const target = event.currentTarget;
    const nombrePokemon = target.querySelector('.nombre-Pokemon').textContent;
    const pokemon = pokeArray.find(poke => poke.nombre === nombrePokemon);
    divDetalles.classList.add('detalles-Pokemon');
    divDetalles.innerHTML = `
      <div class="detalles-Pokemon-content">
        <img class="imagen-detalle" src="${pokemon.imgInfo ? pokemon.imgInfo : pokemon.img}" alt="${nombrePokemon}">
        <h2>${nombrePokemon}</h2>
        <p>Altura: ${pokemon.altura}</p>
        <p>Peso: ${pokemon.peso}</p>
        <p>Tipo:</p>
        <ul>
          ${pokemon.tipos.map(tipo => `<li>${tipo.type.name}</li>`).join('')}
        </ul>
      </div>
    `;
    document.body.appendChild(divDetalles);
    divDetalles.addEventListener('click', () => {
      divDetalles.remove();
    });
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

        name.textContent = pokeArray[i].nombre; 
        id.textContent = pokeArray[i].id; 
        imagen.src = pokeArray[i].img;

        id.classList.add('id-Pokemon');
        imagen.classList.add('imagen-Pokemon');
        name.classList.add('nombre-Pokemon');
        div.classList.add('contenedor-Pokemon');

        div.appendChild(name);
        div.appendChild(imagen);
        div.appendChild(id);
        container.appendChild(div);

        div.addEventListener('click', event => {
            crearDetallesPokemon( event, pokeArray);
        });
    }
}

document.addEventListener("DOMContentLoaded", async event => {
    getPokemonMinList(offset, limit).then(lista => {
        /* console.log(lista); */
        createDiv(lista);
    });
})

siguiente.addEventListener("click", siguientePag);
anterior.addEventListener("click", anteriorPag);