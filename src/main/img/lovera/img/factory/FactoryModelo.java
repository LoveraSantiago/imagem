package lovera.img.factory;

import java.awt.image.BufferedImage;

import lovera.img.modelos.LimiarImg;
import lovera.img.modelos_img.BinarizacaoImg;
import lovera.img.modelos_img.CinzaImg;
import lovera.img.modelos_img.CorrosaoImg;

public final class FactoryModelo {
	
	public static CorrosaoImg factoryCorrosao(BufferedImage img){
		BinarizacaoImg binarizacao = factoryBinarizacao(img);
		
		CorrosaoImg erosao = new CorrosaoImg(binarizacao);
		erosao.executarTransformacao();
		return erosao;
	}
	
	public static BinarizacaoImg factoryBinarizacao(BufferedImage img){
		CinzaImg cinza = factoryCinza(img);
		LimiarImg limiar = new LimiarImg(cinza);
		BinarizacaoImg binarizacao = new BinarizacaoImg(cinza, limiar);
		binarizacao.executarTransformacao();
		return binarizacao;
	}
	
	public static CinzaImg factoryCinza(BufferedImage img){
		CinzaImg cinza = new CinzaImg(img);
		cinza.executarTransformacao();
		return cinza;
	}
}
