package lovera.linha.comum;

import java.awt.Color;

//substituir por enuns
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
