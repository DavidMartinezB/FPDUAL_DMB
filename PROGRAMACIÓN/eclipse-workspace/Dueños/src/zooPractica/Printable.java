package zooPractica;

import java.util.ArrayList;

public class Printable {
	
	public static void mostrarLista(ArrayList<Animal> lista) {
		for (int x = 0; x < lista.size(); x++) {
			System.out.println(lista.get(x));
		}
		
	}
	
}
