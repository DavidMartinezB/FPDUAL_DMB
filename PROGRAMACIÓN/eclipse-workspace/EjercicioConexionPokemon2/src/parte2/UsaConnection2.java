package parte2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;


public class UsaConnection2 {

	public static void main(String[] args) throws SQLException, IOException {
		BufferedReader reader = 
				new BufferedReader(new InputStreamReader(System.in));
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pokemondb", "root", "Perritocaliente1@#");
		ArrayList<Pokemon> pokemonList = new ArrayList<>();
		int opcion;
		String url = "https://pokeapi.co/api/v2/pokemon/?limit=151";
		
		do {
			Pokemon.mostrarMenu();
			opcion = Integer.parseInt(reader.readLine());
			
			switch (opcion) {
			case 1: {
				Pokemon.leerPorNumeroBD(reader ,conn, pokemonList);
				break;
			}
			case 2: {
				Pokemon.pokemonPesadosDB(conn, pokemonList);
				break;
			}
			case 3: {
				Pokemon.resultadoRespuestaAPI(url);
				break;
			}
			case 4 : {
				Pokemon.modificarNombreDB(reader, conn, pokemonList);
				System.out.println();
				break;
			}
			case 5: {
				System.out.println("El programa finalizó");
			}
			default:
				break;
			}
			
	} while (opcion != 5);
		
	}

}
