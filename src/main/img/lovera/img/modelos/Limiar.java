package lovera.img.modelos;

import lovera.img.comum.Pixel;
import lovera.img.comum.Regras;
import lovera.img.modelos_img.CinzaImg;

public final class Limiar {
	
	private int[] arrayBinario;
	private int limiar;
	
	public Limiar(CinzaImg cinza) {
		Regras.validarBufferedImgCinza(cinza);
		
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
			this.arrayBinario[i] = i < this.limiar ? Pixel.VAZIO : Pixel.PREENCHIDO;
	}
	
	public int[] getArrayBinarizado(){
		return this.arrayBinario;
	}
}
