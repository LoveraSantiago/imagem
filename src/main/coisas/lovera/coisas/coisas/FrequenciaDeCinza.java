package lovera.coisas.coisas;

import static lovera.comuns.comum.Regras.validarNivelCinza;

import lovera.comuns.contratos.Gravavel;

//IMPLEMENTAR GRAVAVEL
public final class FrequenciaDeCinza implements Gravavel{
	
	private int amplitude;
	
	private int[] escalaCinza;
	private int maiorEscalaCinza;
	private int maiorFreqCinza;
	private int menorEscalaCiza;
	private int menorFreqCinza;
	
	private int[] escalaDerivadas;
	private int maiorDerivadaEscalaCinza;
	private int maiorDerivadaCinza;
	private int menorDerivadaEscalaCinza;
	private int menorDerivadaCinza;
	
	@Override
	public String toString() {
		StringBuilder escala = new StringBuilder();
		escala.append("[\n");
		for(int i = 0; i < this.escalaCinza.length; i++){
			escala.append("(" + String.format("%d", i) + " = " + this.escalaCinza[i]+ "),\n");
		}
		escala.append("]");
		
		return String.format("Amplitude = %d\n" + 
							 "Max Cinza = (%d, %d)\n" + 
							 "Min Cinza = (%d, %d) \n",
							  this.amplitude, this.maiorEscalaCinza, this.maiorFreqCinza, 
							  this.menorEscalaCiza, this.menorFreqCinza) + escala.toString();
	}

	public void setEscalaCinza(int[] escalaCinza) {
		if(escalaCinza.length != 256) 
			throw new IllegalArgumentException("Map de pixels deve ter tamanho 256. Map com tamanho" + escalaCinza.length + ".");
		this.escalaCinza = escalaCinza;
	}

	public int[] getEscalaCinza(){
		return this.escalaCinza;
	}
	
	/**
	 * Retorna o tom de cinza que teve a maior frequência.
	 * @return int (entre 0 e 255)
	 */
	public int getMaiorEscalaCinza() {
		return maiorEscalaCinza;
	}

	/**
	 * Seta o tom de cinza que teve a maior frequência.
	 */
	public void setMaiorEscalaCinza(int maiorEscalaCinza) {
		validarNivelCinza(maiorEscalaCinza);
		this.maiorEscalaCinza = maiorEscalaCinza;
	}

	/**
	 * Retorna o valor da maior frequência. 
	 */
	public int getMaiorFrquenciaCinza() {
		return maiorFreqCinza;
	}

	/**
	 * Seta o valor da maior frequência.
	 */
	public void setMaiorFrequenciaCinza(int maiorValorCinza) {
		this.maiorFreqCinza = maiorValorCinza;
	}

	public int getFrequenciaDoTomDeCinza(int escala){
		validarNivelCinza(escala);		
		return this.escalaCinza[escala];		
	}

	/**
	 * Retorna o tom de cinza que teve a menor frequência.
	 * @return int (entre 0 e 255)
	 */
	public int getMenorEscalaCiza() {
		return menorEscalaCiza;
	}

	/**
	 * Seta o tom de cinza que teve a menor frequência.
	 */
	public void setMenorEscalaCiza(int menorEscalaCiza) {
		this.menorEscalaCiza = menorEscalaCiza;
	}

	/**
	 * Retorna o valor da menor frequência. 
	 */
	public int getMenorFrequenciaCinza() {
		return menorFreqCinza;
	}
	
	/**
	 * Seta o valor da menor frequência. 
	 */
	public void setMenorFrequenciaCinza(int menorValorCinza) {
		this.menorFreqCinza = menorValorCinza;
	}

	public int getAmplitude() {
		return amplitude;
	}

	public void setAmplitude(int amplitude) {
		this.amplitude = amplitude;
	}

	public int[] getEscalaDerivadas() {
		return escalaDerivadas;
	}

	public void setEscalaDerivadas(int[] escalaDerivadas) {
		this.escalaDerivadas = escalaDerivadas;
	}
	
	public int getDerivadaDaEscalaDeCinza(int escala){
		validarNivelCinza(escala);
		return this.escalaDerivadas[escala];
	}

	public int getMaiorDerivadaEscalaCinza() {
		return maiorDerivadaEscalaCinza;
	}

	public void setMaiorDerivadaEscalaCinza(int maiorDerivadaEscalaCinza) {
		this.maiorDerivadaEscalaCinza = maiorDerivadaEscalaCinza;
	}

	public int getMaiorDerivadaCinza() {
		return maiorDerivadaCinza;
	}

	public void setMaiorDerivadaCinza(int maiorDerivadaCinza) {
		this.maiorDerivadaCinza = maiorDerivadaCinza;
	}

	public int getMenorDerivadaEscalaCinza() {
		return menorDerivadaEscalaCinza;
	}

	public void setMenorDerivadaEscalaCinza(int menorDerivadaEscalaCinza) {
		this.menorDerivadaEscalaCinza = menorDerivadaEscalaCinza;
	}

	public int getMenorDerivadaCinza() {
		return menorDerivadaCinza;
	}

	public void setMenorDerivadaCinza(int menorDerivadaCinza) {
		this.menorDerivadaCinza = menorDerivadaCinza;
	}

	@Override
	public void gravar() {
		// TODO Auto-generated method stub
		
	}
}
