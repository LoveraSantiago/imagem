package lovera.img.modelos.img;

import static lovera.comuns.recursos.Regras.validarOperacaoExecutada;
import static lovera.img.manipulacao.Filtros.laplace;

import java.awt.image.BufferedImage;

import lovera.comuns.contratos.Gravavel;
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
	public LaplaceImg executarTransformacao() {
		this.imgLaplace = laplace(imgTemp);
		this.imgTemp = null;
		return this;
	}

	@Override
	public BufferedImage getImgTransformada() {
		validarOperacaoExecutada(this.imgLaplace, this.getClass());
		return this.imgLaplace;
	}

	@Override
	public LaplaceImg gravar() {
		ImgTransformavel.super.gravarImg(this.imgLaplace, "redacaoLaplace", this);
		return this;
	}

}
