package lovera.estatistica.grao;

public final class Estatistica {
	
	private double coefVariacao;
	private double desvio;
	private double desvioMedio;
	private double kurtosis;
	private double maximo;
	private double media;
	private double minimo;
	private double skewnees;
	private double variancia;
	
	@Override
	public String toString() {
		return String.format("Media = %.4f" +
							 "\nDesvio = %.4f" + 
							 "\nDesvio Medio = %.4f" +
						     "\nVariancia = %.4f" + 
							 "\nCoef.Variacao = %.4f" + 
							 "\nSkewnees = %.4f" + 
						     "\nKurtosis = %.4f" +
						     "\nMinimo = %.4f" + 
						     "\nMaximo = %.4f", 
						     this.media, this.desvio, this.desvioMedio, this.variancia, 
						     this.coefVariacao, this.skewnees, this.kurtosis, this.minimo, 
						     this.maximo);		
	}
	
	public double getMedia() {
		return media;
	}
	public void setMedia(double media) {
		this.media = media;
	}
	public double getDesvio() {
		return desvio;
	}
	public void setDesvio(double desvio) {
		this.desvio = desvio;
	}
	public double getDesvioMedio() {
		return desvioMedio;
	}
	public void setDesvioMedio(double desvioMedio) {
		this.desvioMedio = desvioMedio;
	}
	public double getVariancia() {
		return variancia;
	}
	public void setVariancia(double variancia) {
		this.variancia = variancia;
	}
	public double getSkewnees() {
		return skewnees;
	}
	public void setSkewnees(double skewnees) {
		this.skewnees = skewnees;
	}
	public double getKurtosis() {
		return kurtosis;
	}
	public void setKurtosis(double kurtosi) {
		this.kurtosis = kurtosi;
	}
	public double getMinimo() {
		return minimo;
	}
	public void setMinimo(double minimo) {
		this.minimo = minimo;
	}
	public double getMaximo() {
		return maximo;
	}
	public void setMaximo(double maximo) {
		this.maximo = maximo;
	}

	public double getCoefVariacao() {
		return coefVariacao;
	}

	public void setCoefVariacao(double coefVariacao) {
		this.coefVariacao = coefVariacao;
	}
}
