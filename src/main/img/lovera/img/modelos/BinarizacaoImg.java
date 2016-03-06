package lovera.img.modelos;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import lovera.comuns.contratos.Gravavel;
import lovera.comuns.recursos.Endereco;
import lovera.comuns.recursos.TipoImagem;
import lovera.img.comum.Pixel;
import lovera.img.comum.Regras;
import lovera.img.contratos.ImgTransformavel;
import lovera.img.manipulacao.ImgIO;
import lovera.img.manipulacao.ManipulacaoImg;

public final class BinarizacaoImg implements ImgTransformavel, Gravavel{
	
	private BufferedImage imgBinaria;
	
	public BinarizacaoImg(LaplaceImg laplace) {
		Regras.validarBufferedImgCinza(laplace);
		
		this.imgBinaria = ManipulacaoImg.copiarImg(laplace.getImgTransformada());
	}
	
	public BinarizacaoImg(BufferedImage img) {
		Regras.validarBufferedImgCinza(img);
		this.imgBinaria = img;
	}

	@Override
	public ImgTransformavel executarTransformacao() {
		int[] arrayBinario = iniciarArrayBinario();
		binarizarImg(arrayBinario);		
		return this;
	}
	
	private int[] iniciarArrayBinario(){
		int[] pixels = new int[256];
		for(int i = 0; i < pixels.length; i++) pixels[i] = Pixel.PREENCHIDO;
		pixels[0] = Pixel.VAZIO;
		return pixels;
	}
	
	private void binarizarImg(int[] arrayBinario){
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
