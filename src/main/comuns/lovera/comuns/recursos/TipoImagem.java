package lovera.comuns.recursos;

public enum TipoImagem {
	
	JPG("jpg"),
	PNG("png");
	
	private String tipo;
	
	private TipoImagem(String tipo) {
		this.tipo = tipo;
	}
	
	public String getTipo(){
		return this.tipo;
	}

}
