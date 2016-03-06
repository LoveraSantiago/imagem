package lovera.img.modelos_img;

import java.awt.image.BufferedImage;

import lovera.comuns.contratos.Gravavel;
import lovera.comuns.recursos.Endereco;
import lovera.comuns.recursos.TipoImagem;
import lovera.img.comum.Regras;
import lovera.img.contratos.ImgTransformavel;
import lovera.img.manipulacao.ImgIO;
import lovera.img.manipulacao.ManipulacaoImg;

public final class CinzaImg implements ImgTransformavel, Gravavel{
	
	BufferedImage imgTemp;
	BufferedImage imgCinza;
	
	public CinzaImg(BufferedImage img) {
		this.imgTemp = img;
	}

	@Override
	public ImgTransformavel executarTransformacao() {
		this.imgCinza = ManipulacaoImg.converterToCinzaRGB(this.imgTemp);
		
		return this;
	}

	@Override
	public BufferedImage getImgTransformada() {
		Regras.validarOperacaoExecutada(this.imgCinza, this);
		return this.imgCinza;
	}

	@Override
	public void gravar() {
		Regras.validarOperacaoExecutada(this.imgCinza, this);
		ImgIO.gravarImg(this.imgCinza, Endereco.TESTES, "redacaoCinza", TipoImagem.PNG);		
	}
}
