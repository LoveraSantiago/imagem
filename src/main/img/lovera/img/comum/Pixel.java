package lovera.img.comum;

/**
 * Constantes para pixel preenchido ou vazio. 
 * @author Lovera
 * @since 06/03/2016
 */
public enum Pixel {
	
	VAZIO(0),		 //Preto
	PREENCHIDO(255);//Branco
	
	private int valor;
	
	private Pixel(int valor) {
		this.valor = valor;
	}
	
	public int getValor() {
		return this.valor;
	}
}
