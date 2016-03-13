package lovera.img.comum;

/**
 * Constantes binárias zero ou hum.
 * @author Lovera
 * @since 06/03/2016
 */
@Deprecated //13/03/2016
public enum Binario {

	ZERO_BINARIO(0),
	HUM_BINARIO(1);
	
	private int valor;
	
	private Binario(int valor) {
		this.valor = valor;
	}
	
	public int getValor() {
		return this.valor;
	}
}
