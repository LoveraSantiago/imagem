package lovera.comuns.recursos;

public enum Endereco {

//	TESTES("C:/Users/santi_000/Desktop/Proces_Imagens/Imagens/testes/");
	TESTES("C:/Users/Santiago/workspace/exemplos/Imagens/testes/");
	
	private String endereco;
	
	private Endereco(String endereco){
		this.endereco = endereco;		
	}
	
	public String getEndereco(){
		return this.endereco;
	}
}
