package lovera.img.factory;

import java.awt.image.BufferedImage;

import lovera.img.modelos_img.BinarizacaoImg;
import lovera.img.modelos_img.CorrosaoImg;
import lovera.img.modelos_img.GaussImg;
import lovera.img.modelos_img.LaplaceImg;

public final class FactoryModelo {
	
	public static CorrosaoImg factoryCorrosaoImgFromBufferedImage(BufferedImage img){
		GaussImg gauss = new GaussImg(img);
		gauss.executarTransformacao();
		
		LaplaceImg laplace = new LaplaceImg(gauss);
		laplace.executarTransformacao();
		
		BinarizacaoImg binariza = new BinarizacaoImg(laplace);
		binariza.executarTransformacao();
		
		CorrosaoImg erosao = new CorrosaoImg(binariza);
		erosao.executarTransformacao();
		return (CorrosaoImg) erosao;
	}
	
}
