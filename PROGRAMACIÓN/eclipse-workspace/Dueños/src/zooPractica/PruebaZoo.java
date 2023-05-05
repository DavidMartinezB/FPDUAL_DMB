package zooPractica;

import java.util.ArrayList;

public class PruebaZoo {

	public static void main(String[] args) {
		
		ArrayList<Animal> lista = new ArrayList<>();
		
		AnimalDomestico ad1 = new AnimalDomestico(
			2, 
			"pepe", 
			new Duenos (
				"David",
				20,
				new DNI(77029755, 'H')
			)
		);
		
		AnimalDomestico ad2 = new AnimalDomestico(
			3, 
			"max", 
			new Duenos (
				"Manuel",
				69,
				new DNI(69696969, 'X')
			)
		);
		
		AnimalDomestico ad3 = new AnimalDomestico(
			1, 
			"doggo", 
			new Duenos (
				"Alberto",
				35,
				new DNI (75687586, 'D')
			)
		);
		
		
		AnimalSalvaje as1 = new AnimalSalvaje(1, "Juan Alberto");
		
		lista.add(ad1);
		lista.add(ad2);
		lista.add(ad3);
		lista.add(as1);
		
		
		Printable.mostrarLista(lista);
	}

}
