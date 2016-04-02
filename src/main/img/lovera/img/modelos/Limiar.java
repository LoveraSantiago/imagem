package lovera.img.modelos;

import lovera.comuns.recursos.Regras;
import lovera.img.comum.Pixel;
import lovera.img.modelos.img.CinzaImg;

/**
 * Calcula um valor de limiar para binarizar a imagem.</br>
 * <strong>Obs:</strong></br>
 * -Ate o momento não faz nada alem de passar um valor 1 como limiar.
 * -A aplicação teria de ser usada em outros exemplos para ver se responde de forma uniforme, do
 * contrário uma implementação seria necessária 
 * @author Lovera
 * @since 02/04/2016
 */
public final class Limiar {
	
	private int[] arrayBinario;
	private int limiar;
	
	public Limiar(CinzaImg cinza) {
		Regras.validarBufferedImgCinza(cinza, this.getClass());
		
		encontrarLimiar();
		fabricarArrayBinario();
	}
	
	private void encontrarLimiar(){
		//TO DO
		this.limiar = 1;//VALOR CHEGO DE FORMA MAGICA AINDA!!!!!!!!!
	}
	
	private void fabricarArrayBinario(){
		this.arrayBinario = new int[256];
		for(int i=0; i < this.arrayBinario.length; i++)
			this.arrayBinario[i] = i < this.limiar ? Pixel.VAZIO.getValor() : Pixel.PREENCHIDO.getValor();
	}
	
	public int[] getArrayBinarizado(){
		return this.arrayBinario;
	}
}
