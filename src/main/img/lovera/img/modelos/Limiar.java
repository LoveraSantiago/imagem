package lovera.img.modelos;

import lovera.comuns.recursos.Regras;
import lovera.img.comum.Pixel;
import lovera.img.modelos.img.CinzaImg;

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
