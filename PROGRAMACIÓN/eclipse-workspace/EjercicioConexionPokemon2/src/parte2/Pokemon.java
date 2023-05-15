package parte2;

import com.google.gson.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Pokemon {
	private int numero_poke;
	private String nombre;
	private float peso;
	private float altura;

	public Pokemon(int numero_poke, String nombre, float peso, float altura) {
		super();
		this.numero_poke = numero_poke;
		this.nombre = nombre;
		this.peso = peso;
		this.altura = altura;
	}

	public int getNumero_poke() {
		return numero_poke;
	}

	public void setNumero_poke(int numero_poke) {
		this.numero_poke = numero_poke;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}
	
	protected static void mostrarMenu () {
		System.out.println("--------------MENU PRINCIPAL--------------");
		System.out.println("1.  Mostrar información por número del pokemon en la Pokedex");
		System.out.println("2.  Muestra los 10 pokemon más pesados");
		System.out.println("3.  Petición a la POKEAPI para obtener los 151 pokemon");
		System.out.println("4.  Modifica el nombre de un pokemon en la base datos según su número de la pokedex");
		System.out.println("5.  SALIR");
		System.out.println("------------------------------------------");
		System.out.println("Ingrese una opción");
	}
	
	protected static void llenarArray(Connection conn, ArrayList<Pokemon> pokemon) throws SQLException {
		Statement createStatement = conn.createStatement();
		ResultSet resultQuery = createStatement.executeQuery("SELECT numero_pokedex, nombre, peso, altura FROM pokemon");
		while (resultQuery.next()) {
			pokemon.add(new Pokemon(
					resultQuery.getInt("numero_pokedex"), 
					resultQuery.getString("nombre"), 
					resultQuery.getFloat("peso"),
					resultQuery.getFloat("altura")));
		}
	}

	protected static String httpGetRequest(String URL) throws IOException {
    	StringBuilder result = new StringBuilder();
    	URL url = new URL(URL);
    	HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    	connection.setRequestMethod("GET");
    	BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    	String info;
    	while ((info = reader.readLine()) != null) {
    		result.append(info);
    	}
    	reader.close();
    	return result.toString();
    }

	public static void resultadoRespuestaAPI(String url) throws IOException {
			String respuesta = httpGetRequest(url);
			Gson gson = new Gson();
			JsonObject json = gson.fromJson(respuesta, JsonObject.class);
			JsonArray results = json.getAsJsonArray("results");
			
			for (int i = 0; i < results.size(); i++) {
				
				JsonObject pokemonData = results.get(i).getAsJsonObject();
				String pokemonUrl = pokemonData.get("url").getAsString();
				String pokemonResponse = httpGetRequest(pokemonUrl);
				JsonObject pokemonJson = gson.fromJson(pokemonResponse, JsonObject.class);
				
				int numero_pokedex = pokemonJson.get("id").getAsInt();
				String nombre = pokemonJson.get("name").getAsString();
				float peso = pokemonJson.get("weight").getAsFloat();
				float altura = pokemonJson.get("height").getAsFloat();
				
				System.out.println("----------------------------------------------------");
				System.out.format( "%1s %25s %7s %16s","|","Número De Pokedex: ",numero_pokedex,"|");
				System.out.println();
				System.out.format( "%1s %15s %20s %13s", "|" ,"Pokemon: ", nombre, "|");
				System.out.println();
				System.out.format( "%1s %24s %9s %15s","|","Peso del pokemon: ", peso, "|");
				System.out.println();
				System.out.format( "%1s %26s %6s %16s", "|", "Altura del pokemon: ", altura, "|");
				System.out.println();
				System.out.println("----------------------------------------------------");
			}

	}

	protected static void leerPorNumeroBD(BufferedReader reader,Connection conn , ArrayList<Pokemon> pokemon) throws SQLException, NumberFormatException, IOException {
		System.out.println("introduce el numero de la pokedex");
		System.out.println();
		int numero = Integer.parseInt(reader.readLine());
		llenarArray(conn, pokemon);
		System.out.println(pokemon.get(numero-1));
	}

	protected static void pokemonPesadosDB (Connection conn, ArrayList<Pokemon> pokemon) throws SQLException {
		llenarArray(conn, pokemon);
		Collections.sort(pokemon, new Comparator<Pokemon>() {
			@Override
			public int compare(Pokemon o1, Pokemon o2) {
				return Float.compare(o2.getPeso(), o1.getPeso());
			}
		});
		for (int i = 0; i < 10; i++) {
			System.out.println(pokemon.get(i));
		}
	}

	protected static void modificarNombreDB (BufferedReader reader, Connection conn, ArrayList<Pokemon> pokemon) throws SQLException, IOException {
		String nombreAnt;
		System.out.println("Introduzca el nombre del pokemon a cambiar");
		nombreAnt = reader.readLine();
		llenarArray(conn, pokemon);
		for (Pokemon p : pokemon) {
			if (p.getNombre().equals(nombreAnt)) {
				System.out.println("Introduzca el nuevo nombre del pokemon");
				p.nombre = reader.readLine();
				System.out.println("El nombre del pokemon: "+ nombreAnt+ "ha sido sustituido por: " + p.nombre);
			}
		}
	} 

	@Override
	public String toString() {
		return "Pokemon: numero de Pokedex: " + numero_poke + ", nombre: " + nombre + ", peso: " + peso + ", altura: " + altura;
	}	


}
