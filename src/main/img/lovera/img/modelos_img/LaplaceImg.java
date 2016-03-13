package lovera.img.modelos_img;

import static lovera.comuns.recursos.Regras.validarOperacaoExecutada;
import static lovera.img.manipulacao.Filtros.laplace;
import static lovera.img.manipulacao.ImgIO.gravarImg;

import java.awt.image.BufferedImage;

import lovera.comuns.contratos.Gravavel;
import lovera.comuns.recursos.Endereco;
import lovera.comuns.recursos.Regras;
import lovera.comuns.recursos.TipoImagem;
import lovera.img.contratos.ImgTransformavel;
/**
 * @author Lovera
 * Classe util para detectar bordas
 */
public final class LaplaceImg implements ImgTransformavel, Gravavel{
	
	private BufferedImage imgTemp;
	private BufferedImage imgLaplace;
	
	public LaplaceImg(GaussImg gauss) {
		this.imgTemp = gauss.getImgTransformada();
	}

	@Override
	public ImgTransformavel executarTransformacao() {
		this.imgLaplace = laplace(imgTemp);
		this.imgTemp = null;
		return this;
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
