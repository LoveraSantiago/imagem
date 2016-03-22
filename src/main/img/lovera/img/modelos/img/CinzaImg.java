package lovera.img.modelos.img;

import java.awt.image.BufferedImage;

import lovera.comuns.contratos.Gravavel;
import lovera.comuns.recursos.Regras;
import lovera.img.contratos.ImgTransformavel;
import lovera.img.manipulacao.ManipulacaoImg;

public final class CinzaImg implements ImgTransformavel, Gravavel{
	
	BufferedImage imgTemp;
	BufferedImage imgCinza;
	
	public CinzaImg(BufferedImage img) {
		this.imgTemp = img;
	}

	@Override
	public CinzaImg executarTransformacao() {
		this.imgCinza = ManipulacaoImg.converterToCinzaRGB(this.imgTemp);
		
		return this;
	}

	@Override
	public BufferedImage getImgTransformada() {
		Regras.validarOperacaoExecutada(this.imgCinza, this.getClass());
		return this.imgCinza;
	}

	@Override
	public CinzaImg gravar() {
		ImgTransformavel.super.gravarImg(this.imgCinza, "redacaoCinza", this);
		return this;
	}
}
