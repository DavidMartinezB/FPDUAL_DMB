let contador = 1;
while (contador<=4) {
    fetch(`https://pokeapi.co/api/v2/pokemon/${contador}`)
        .then(Response => {
            if (Response.ok) {
                return Response.json();
            }
            throw new Error('Error al cargar la API')
        })
        .then(data => {
            const container = document.getElementById('container');
            const imagen = document.createElement('img')
            const nombre = document.createElement('p');
            nombre.textContent = `Nombre: ${data.name}`;
            imagen.src = data.sprites.front_default;
            const div = document.createElement('div');
            div.appendChild(imagen);
            div.appendChild(nombre);
            container.appendChild(div);
        })

        .catch(error => console.error(error))
        contador++;
    }
    console.log(contador);

