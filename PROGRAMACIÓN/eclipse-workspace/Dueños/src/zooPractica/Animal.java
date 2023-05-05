package zooPractica;


public abstract class Animal {
	
	protected int edad;
	protected String nombre;
	
	public Animal(int edad, String nombre) {
		super();
		this.edad = edad;
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
