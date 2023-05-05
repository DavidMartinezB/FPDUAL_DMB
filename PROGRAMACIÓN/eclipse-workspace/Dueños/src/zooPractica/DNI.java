package zooPractica;

public class DNI {
	
	protected long numero;
	protected char letra;
	
	public DNI(long numero, char letra) {
		super();
		this.numero = numero;
		this.letra = letra;
	}
	
	@Override
	public String toString() {
		return String.format("%08d", numero) + letra;
	}
}
