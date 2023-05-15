package parte2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;


public class UsaConnection2 {

	public static void main(String[] args) throws SQLException, IOException {
		BufferedReader reader = 
				new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Pokemon> pokemonList = new ArrayList<>();
		int opcion;
		String url = "https://pokeapi.co/api/v2/pokemon/?limit=151";
		do {
			Gestor.mostrarMenu();
			opcion = Integer.parseInt(reader.readLine());
			switch (opcion) {
			case 1: {
				Gestor.leerPorNumeroBD(reader ,Gestor.crearConex(), pokemonList);
				break;
			}
			case 2: {
				Gestor.pokemonPesadosDB(Gestor.crearConex(), pokemonList);
				break;
			}
			case 3: {
				Gestor.resultadoRespuestaAPI(url);
				break;
			}
			case 4 : {
				Gestor.modificarNombreDB(reader, Gestor.crearConex(), pokemonList);
				System.out.println();
				break;
			}
			case 5: {
				System.out.println("El programa finalizó");
			}
			default:
				System.out.println("El valor introducido no es correcto");
				break;
			}
			
	} while (opcion != 5);
		
	}

}
