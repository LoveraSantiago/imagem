package lovera.linha.factorylinha;

import java.awt.image.BufferedImage;

import lovera.img.modelos.ErosaoImg;
import lovera.img.modelos.GaussImg;
import lovera.img.modelos.LaplaceImg;

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
