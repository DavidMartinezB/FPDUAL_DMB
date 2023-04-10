document.addEventListener('DOMContentLoaded', function() {
    const url = 'https://pokeapi.co/api/v2/pokemon/bulbasaur'

    fetch(url)
        .then(Response => {
            if (Response.ok) {
                return Response.json();
            }
            throw new Error('Error al cargar la API')
        })
        .then(data => {
            const container = document.getElementById('container');
            const nombre = document.createElement('p');
            nombre.textContent = `Nombre: ${data.name}`;
            const div = document.createElement('div');
            div.appendChild(nombre);
            container.appendChild(div);
        })

        .catch(error => console.error(error))
});