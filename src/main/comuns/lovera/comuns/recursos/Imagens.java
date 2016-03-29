package lovera.comuns.recursos;

public enum Imagens {	
		
	LETRA_J("j.png"),
	LINHA_DESCENDO("Linha135Graus.png"),
	LINHA_HORIZONTAL("LinhaHorizontal.png"), 
	LINHA_SUBINDO("Linha45Graus.png"),
	LINHA_VERTICAL("LinhaVertical.png"),
	REDACAO_PNG("redacao.png"); 
	
	private static final String RAIZ = "C:/Users/santi_000/Desktop/Proces_Imagens/Imagens/originais/";
		
	private String endereco;
	
	private Imagens(String endereco){
		this.endereco = endereco;
	}
	
	public String getEndereco(){
		return RAIZ + this.endereco;
	}

}
