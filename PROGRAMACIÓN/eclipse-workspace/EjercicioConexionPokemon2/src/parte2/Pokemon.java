package parte2;

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
	

	@Override
	public String toString() {
		return "Pokemon: numero de Pokedex: " + numero_poke + ", nombre: " + nombre + ", peso: " + peso + ", altura: " + altura;
	}	


}
