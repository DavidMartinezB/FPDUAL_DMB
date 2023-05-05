package zooPractica;

public class AnimalSalvaje extends Animal {

	public AnimalSalvaje(int edad, String nombre) {
		super(edad, nombre);
	}

	@Override
	public String toString() {
		return "AnimalSalvaje [edad=" + edad + ", nombre=" + nombre + "]";
	}
}
