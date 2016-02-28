package lovera.img.modelos;

import static lovera.comuns.comum.Regras.validarOperacaoExecutada;
import static lovera.img.manipulacao.Filtros.Gauss;
import static lovera.img.manipulacao.ImgIO.gravarImg;

import java.awt.image.BufferedImage;

import lovera.comuns.contratos.ImgTransformavel;
import lovera.comuns.recursos.Endereco;
import lovera.comuns.recursos.TipoImagem;

public final class GaussImg implements ImgTransformavel{
	
	private BufferedImage imgTemp;
	private BufferedImage imgGauss;
	
	public GaussImg(BufferedImage img) {
		this.imgTemp = img;
	}

	@Override
	public void executarTransformacao() {
		this.imgGauss = Gauss(this.imgTemp);
		this.imgTemp = null;
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
