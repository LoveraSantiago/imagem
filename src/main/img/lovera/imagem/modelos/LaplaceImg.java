package lovera.imagem.modelos;

import static lovera.imagem.comum.Regras.validarOperacaoExecutada;
import static lovera.imagem.manipulacao.Filtros.Laplace;
import static lovera.imagem.manipulacao.ImgIO.gravarImg;

import java.awt.image.BufferedImage;

import lovera.imagem.comum.Regras;
import lovera.imagem.contratos.ImgTransformavel;
import lovera.imagem.recursos.Endereco;
import lovera.imagem.recursos.TipoImagem;

public final class LaplaceImg implements ImgTransformavel{
	
	private BufferedImage imgTemp;
	private BufferedImage imgLaplace;
	
	public LaplaceImg(GaussImg gauss) {
		this.imgTemp = gauss.getImgTransformada();
	}

	@Override
	public void executarTransformacao() {
		this.imgLaplace = Laplace(imgTemp);
		this.imgTemp = null;		
	}

	@Override
	public BufferedImage getImgTransformada() {
		validarOperacaoExecutada(this.imgLaplace, this);
		return this.imgLaplace;
	}

	@Override
	public void gravar() {
		Regras.validarOperacaoExecutada(this.imgLaplace, this);
		gravarImg(this.imgLaplace, Endereco.TESTES, "RedacaoLaplace", TipoImagem.PNG);
	}

}
