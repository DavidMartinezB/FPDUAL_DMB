package zooPractica;

public class Duenos {
	
	protected DNI dni;
	protected String nombre;
	protected int edad;

	public Duenos(String nombre, int edad, DNI dni) {
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.dni = dni;
	}

	@Override
	public String toString() {
		return "Duenos [dni=" + dni + ", nombre=" + nombre + ", edad=" + edad + "]";
	}
	
	
}
