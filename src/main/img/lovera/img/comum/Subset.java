package lovera.img.comum;

import java.awt.Color;

/**
 * Classe de constantes para separacao entre 3 subsets.</br>
 * Usado como pre-processamento.</br> 
 * Separa de acordo com a largura e da altura do CC (Componente conectado). * 
 * @author Lovera
 * @since 15/03/2016
 */
public enum Subset {
	
	S1(1, Color.blue ),
	S2(2, Color.green),
	S3(3, Color.red  );

	private int set;
	private Color cor;
	
	private Subset(int set, Color cor) {
		this.set = set;
		this.cor = cor;
	}
	
	public int getSet() {
		return this.set;
	}
	
	public Color getCor() {
		return this.cor;
	}
}
