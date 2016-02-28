package lovera.imagem.factorylinha;

import java.awt.image.BufferedImage;

import lovera.imagem.modelos.ErosaoImg;
import lovera.imagem.modelos.GaussImg;
import lovera.imagem.modelos.LaplaceImg;

public final class FactoryImgs {
	
	public static ErosaoImg factoryErosaoImgFromBufferedImage(BufferedImage img){
		GaussImg gauss = new GaussImg(img);
		gauss.executarTransformacao();
		
		LaplaceImg laplace = new LaplaceImg(gauss);
		laplace.executarTransformacao();
		
		ErosaoImg erosao = new ErosaoImg(laplace);
		erosao.executarTransformacao();
		return (ErosaoImg) erosao;
	}
	
}
