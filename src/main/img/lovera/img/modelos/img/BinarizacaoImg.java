package lovera.img.modelos.img;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import lovera.comuns.contratos.Gravavel;
import lovera.comuns.recursos.Regras;
import lovera.img.contratos.ImgTransformavel;
import lovera.img.modelos.Limiar;

public final class BinarizacaoImg implements ImgTransformavel, Gravavel{
	
	private Limiar limiar;
	private BufferedImage imgBinaria;	
	
	public BinarizacaoImg(LaplaceImg laplace, Limiar limiar) {
		Regras.validarBufferedImgCinza(laplace, this.getClass());
		
		this.imgBinaria = laplace.getImgTransformada();
		this.limiar = limiar;
	}

	@Override
	public BinarizacaoImg executarTransformacao() {
		
		binarizarImg();		
		this.limiar  = null;		
		return this;
	}
	
	private void binarizarImg(){
		int[] arrayBinario = this.limiar.getArrayBinarizado();
		
		WritableRaster wRaster = this.imgBinaria.getRaster();
		
		for(int i = 0; i < this.imgBinaria.getHeight(); i++)
			for(int j = 0; j < this.imgBinaria.getWidth(); j++){
				int pixel = wRaster.getSample(j, i, 0);
				wRaster.setSample(j, i, 0, arrayBinario[pixel]);
			}
	}

	@Override
	public BufferedImage getImgTransformada() {
		Regras.validarOperacaoExecutada(this.imgBinaria, this.getClass());
		return this.imgBinaria;
	}

	@Override
	public BinarizacaoImg gravar() {
		ImgTransformavel.super.gravarImg(this.imgBinaria, "redacaoBinaria", this);
		return this;
	}
}
