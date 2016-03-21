package lovera.img.modelos.img;

import static lovera.comuns.recursos.Regras.validarOperacaoExecutada;
import static lovera.img.manipulacao.Filtros.gauss;

import java.awt.image.BufferedImage;

import lovera.comuns.contratos.Gravavel;
import lovera.comuns.recursos.Regras;
import lovera.img.contratos.ImgTransformavel;

public final class GaussImg implements ImgTransformavel, Gravavel{
	
	private BufferedImage imgTemp;
	private BufferedImage imgGauss;
	
	public GaussImg(CinzaImg cinza) {
		Regras.validarBufferedImgCinza(cinza, this.getClass());
		
		this.imgTemp = cinza.getImgTransformada();
	}

	@Override
	public ImgTransformavel executarTransformacao() {
		this.imgGauss = gauss(this.imgTemp);
		this.imgTemp = null;
		return this;
	}

	@Override
	public BufferedImage getImgTransformada() {	
		validarOperacaoExecutada(this.imgGauss, this.getClass());
		return this.imgGauss;
	}

	@Override
	public void gravar() {
		ImgTransformavel.super.gravarImg(this.imgGauss, "redacaoGauss", this);
	}

}
