package lovera.linha.factorylinha;

import java.awt.image.BufferedImage;

import lovera.img.modelos.GaussImg;
import lovera.img.modelos.LaplaceImg;
import lovera.linha.modelos.CorrosaoImg;

public final class FactoryLinha {
	
	public static CorrosaoImg factoryErosaoImgFromBufferedImage(BufferedImage img){
		GaussImg gauss = new GaussImg(img);
		gauss.executarTransformacao();
		
		LaplaceImg laplace = new LaplaceImg(gauss);
		laplace.executarTransformacao();
		
		CorrosaoImg erosao = new CorrosaoImg(laplace);
		erosao.executarTransformacao();
		return (CorrosaoImg) erosao;
	}
	
}
