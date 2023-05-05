package zooPractica;

public class AnimalDomestico extends Animal {
	
	protected Duenos dueno;
	
	public AnimalDomestico(int edad, String nombre, Duenos dueno) {
		super(edad, nombre);
		this.dueno = dueno;
	}

	@Override
	public String toString() {
		return "AnimalDomestico [dueno=" + dueno + ", edad=" + edad + ", nombre=" + nombre + "]";
	}
	
	
	
}		
