package lovera.img.modelos;

import lovera.img.comum.Regras;
import lovera.img.modelos_img.CinzaImg;

public final class LimiarImg {
	
	private int[] arrayBinario;
	private int limiar;
	
	public LimiarImg(CinzaImg cinza) {
		Regras.validarBufferedImgCinza(cinza);
		
		encontrarLimiar();
		
	}
	
	private void encontrarLimiar(){
		//TO DO
		this.limiar = 254;//VALOR CHEGO DE FORMA MAGICA AINDA!!!!!!!!!
	}
	
	public int[] getArrayBinarizado(){
		return this.arrayBinario;
	}
}
