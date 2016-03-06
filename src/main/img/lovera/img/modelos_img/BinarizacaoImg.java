package lovera.img.modelos_img;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import lovera.comuns.contratos.Gravavel;
import lovera.comuns.recursos.Endereco;
import lovera.comuns.recursos.TipoImagem;
import lovera.img.comum.Regras;
import lovera.img.contratos.ImgTransformavel;
import lovera.img.manipulacao.ImgIO;
import lovera.img.modelos.LimiarImg;

public final class BinarizacaoImg implements ImgTransformavel, Gravavel{
	
	private LimiarImg limiar;
	private BufferedImage imgBinaria;	
	
	public BinarizacaoImg(CinzaImg cinza, LimiarImg limiar) {
		Regras.validarBufferedImgCinza(cinza);
		
		this.imgBinaria = cinza.getImgTransformada();
		this.limiar = limiar;
	}

	@Override
	public ImgTransformavel executarTransformacao() {
		
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
		Regras.validarOperacaoExecutada(this.imgBinaria, this);
		return this.imgBinaria;
	}

	@Override
	public void gravar() {
		Regras.validarOperacaoExecutada(this.imgBinaria, this);
		ImgIO.gravarImg(this.imgBinaria, Endereco.TESTES, "redacaoBinaria", TipoImagem.PNG);		
	}
}
