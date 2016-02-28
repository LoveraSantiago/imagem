package lovera.img.modelos;

import static lovera.img.comum.Regras.validarOperacaoExecutada;
import static lovera.img.manipulacao.Filtros.Laplace;
import static lovera.img.manipulacao.ImgIO.gravarImg;

import java.awt.image.BufferedImage;

import lovera.comuns.contratos.Gravavel;
import lovera.comuns.recursos.Endereco;
import lovera.comuns.recursos.TipoImagem;
import lovera.img.comum.Regras;
import lovera.img.contratos.ImgTransformavel;

public final class LaplaceImg implements ImgTransformavel, Gravavel{
	
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
