let offset = 0;
const limit = 20;

async function getPokemonInfo(i) {
    const resp = await fetch(`http://localhost:8080/pokemondb/pokemon/${i}`);
    const respJson = await resp.json();
    if (respJson !== null) {
        return {
            nombre: respJson.nombre,
            id: respJson.numero_pokedex,
            peso: respJson.peso,
            altura: respJson.altura
        };
    }
 else {
        return null;
    }
}

async function getPokemonIMG(i) {
    const respImg = await fetch(`https://pokeapi.co/api/v2/pokemon/${i}`);
    const respJsonImg = await respImg.json();
    return {
        img: respJsonImg.sprites.front_default,
        imgInfo: respJsonImg.sprites.other["official-artwork"].front_default,
    }
}

async function getPokemonIMGList(offset, limit) {
    var pokeImgArray = [];
    for (let i = offset+1; i <= offset+limit; i++) {
        console.log("Llamando a getPokemonInfo() desde getPokemonList()");
        const info = await getPokemonIMG(i);
        pokeImgArray.push(info);
    }
    return pokeImgArray;
}


async function getPokemonList(offset, limit) {
    var pokeArray = [];
    for (let i = offset+1; i <= offset+limit; i++) {
        console.log("Llamando a getPokemonInfo() desde getPokemonList()");
        const info = await getPokemonInfo(i);
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
        getPokemonList(offset, limit).then(lista => {
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
    getPokemonList(offset, limit).then(lista => {
        createDiv(lista);
    });
}

function crearDetallesPokemon(event, pokeArray, pokeImgArray) {
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
    for (i = 0; i < pokeArray.length; i++) {
        var div = document.createElement('div');
        var imagen = document.createElement('img')
        var name = document.createElement('p');
        var id = document.createElement('p');
        var peso = document.createElement('p');
        var altura = document.createElement('p');
        var imagenInfo = document.createElement('img');

        imagen.src = pokeImgArray[i].img;
        imagenInfo.src = pokeImgArray[i].imgInfo;
        name.textContent = pokeArray[i].nombre; 
        peso.textContent = pokeArray[i].peso;
        altura.textContent = pokeArray[i].altura;
        id.textContent = pokeArray[i].id; 

        id.classList.add('id-Pokemon');
        imagen.classList.add('imagen-Pokemon');
        imagenInfo.classList.add('imagenInfo-Pokemon');
        name.classList.add('nombre-Pokemon');
        peso.classList.add('peso-Pokemon');
        altura.classList.add('altura-Pokemon');
        div.classList.add('contenedor-Pokemon');

        

        div.addEventListener('click', event => {
            crearDetallesPokemon( event, pokeArray);
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

document.addEventListener("DOMContentLoaded", async event => {
    getPokemonList(offset, limit).then(lista => {
        console.log(lista);
        getPokemonIMG(offset, limit).then(imgLista => {
            createDiv(lista, imgLista)
        })
    });
})

