package lovera.imagem.modelos;

import static lovera.imagem.comum.Regras.validarOperacaoExecutada;
import static lovera.imagem.manipulacao.Filtros.Gauss;
import static lovera.imagem.manipulacao.ImgIO.gravarImg;

import java.awt.image.BufferedImage;

import lovera.imagem.contratos.ImgTransformavel;
import lovera.imagem.recursos.Endereco;
import lovera.imagem.recursos.TipoImagem;

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
