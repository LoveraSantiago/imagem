package lovera.img.modelos_img;

import static lovera.img.comum.Regras.validarOperacaoExecutada;
import static lovera.img.manipulacao.Filtros.Gauss;
import static lovera.img.manipulacao.ImgIO.gravarImg;

import java.awt.image.BufferedImage;

import lovera.comuns.contratos.Gravavel;
import lovera.comuns.recursos.Endereco;
import lovera.comuns.recursos.TipoImagem;
import lovera.img.contratos.ImgTransformavel;

public final class GaussImg implements ImgTransformavel, Gravavel{
	
	private BufferedImage imgTemp;
	private BufferedImage imgGauss;
	
	/**
	 * Ver comportamento vindo de uma imagem binarizada.
	 */
	@Deprecated
	public GaussImg(BufferedImage img) {
		this.imgTemp = img;
	}

	@Override
	public ImgTransformavel executarTransformacao() {
		this.imgGauss = Gauss(this.imgTemp);
		this.imgTemp = null;
		return this;
	}

	@Override
	public BufferedImage getImgTransformada() {	
		validarOperacaoExecutada(this.imgGauss, this);
		return this.imgGauss;
	}

	@Override
	public void gravar() {
		validarOperacaoExecutada(this.imgGauss, this);
		gravarImg(this.imgGauss, Endereco.TESTES, "redacaoGauss", TipoImagem.PNG);		
	}

}
