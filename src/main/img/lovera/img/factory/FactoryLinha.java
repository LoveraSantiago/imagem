package lovera.img.factory;

import java.awt.image.BufferedImage;

import lovera.img.modelos.CorrosaoImg;
import lovera.img.modelos.GaussImg;
import lovera.img.modelos.LaplaceImg;

public final class FactoryLinha {
	
	public static CorrosaoImg factoryCorrosaoImgFromBufferedImage(BufferedImage img){
		GaussImg gauss = new GaussImg(img);
		gauss.executarTransformacao();
		
		LaplaceImg laplace = new LaplaceImg(gauss);
		laplace.executarTransformacao();
		
		CorrosaoImg erosao = new CorrosaoImg(laplace);
		erosao.executarTransformacao();
		return (CorrosaoImg) erosao;
	}
	
}
