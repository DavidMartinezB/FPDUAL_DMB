package conectionDB;

import java.sql.SQLException;
import java.util.Scanner;

public class UsaConnection {
	public static void main(String[] args) throws SQLException {
		
		int choice;
		String nombre;
		int peso, altura, numero;
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.println("--------------MENU PRINCIPAL--------------");
			System.out.println("1.  Mostrar todos los pokemons");
			System.out.println("2.  Actualizar el nombre del pokemon por su número de Pokédex");
			System.out.println("3.  Borrar pokemon");
			System.out.println("4.  Insertar un nuevo pokemon");
			System.out.println("5.  Mostrar todos los pokemon tipo fuego");
			System.out.println("6.  Sumar altura de todos los pokemon");
			System.out.println("7.  Sumar el peso de todos los pokemon que sean tipo agua");
			System.out.println("8.  SALIR");
			System.out.println("------------------------------------------");
			System.out.println("Ingrese una opción");
			choice = sc.nextInt();
			
			switch (choice) {
			case 1: {
				Connection.lecturaBD();
				System.out.println();
				
				break;
			}
			case 2: {
				System.out.println("Introduzca el numero de pokedex del pokemon a actualizar");
				numero = sc.nextInt();
				System.out.println("Introduzca el nombre del pokemon a actualizar");
				nombre = sc.next();
				
				Connection.actualizarPorNumeroBD(numero, nombre);
				System.out.println("El nombre del pokemon con numero de la pokedex "+numero+" ha sido actualizado a "+ nombre);
				break;
			}
			case 3 : {
				System.out.println("Introduzca el numero de pokedex del pokemon a eliminar");
				numero = sc.nextInt();
				
				Connection.eliminarPorNumeroBD(numero);
				System.out.println("El pokemon con el número de la pokedex "+numero+" ha sido eliminado correctamente" );
				break;
			}
			case 4: {
				System.out.println("Introduzca el nombre del pokemon a crear");
				nombre = sc.next();
				System.out.println("Introduzca el peso del pokemon a crear");
				peso = sc.nextInt();
				System.out.println("Introduzca la altura del pokemon a crear");
				altura = sc.nextInt();
				
				Connection.escrituraBD(nombre, peso, altura);
				
				break;
			}
			case 5: {
				System.out.println("Los pokemon tipo fuego son: ");
				Connection.leerTipoFuegoBD();
			}
			case 6: {
				Connection.sumarAlturaBD();
			}
			case 7: {
				Connection.sumarPesoAguaBD();
			}
			default:
				break;
			}
			
	} while (choice != 8);
		sc.close();
	}
}
