package lovera.img.factory;

import java.awt.image.BufferedImage;

import lovera.img.modelos.Limiar;
import lovera.img.modelos_img.BinarizacaoImg;
import lovera.img.modelos_img.ChuvaImg;
import lovera.img.modelos_img.CinzaImg;
import lovera.img.modelos_img.CorrosaoImg;

public final class FactoryModelo {
	
	public static BinarizacaoImg factoryBinarizacao(BufferedImage img){
		CinzaImg cinza = factoryCinza(img);
		Limiar limiar = new Limiar(cinza);
		BinarizacaoImg binarizacao = new BinarizacaoImg(cinza, limiar);
		binarizacao.executarTransformacao();
		return binarizacao;
	}
	
	public static ChuvaImg factoryChuva(BufferedImage img){
		BinarizacaoImg binarizacao = factoryBinarizacao(img);
		ChuvaImg chuva = new ChuvaImg(binarizacao);
		chuva.executarTransformacao();
		return chuva;
	}

	public static CinzaImg factoryCinza(BufferedImage img){
		CinzaImg cinza = new CinzaImg(img);
		cinza.executarTransformacao();
		return cinza;
	}

	public static CorrosaoImg factoryCorrosao(BufferedImage img){
		BinarizacaoImg binarizacao = factoryBinarizacao(img);
		
		CorrosaoImg erosao = new CorrosaoImg(binarizacao);
		erosao.executarTransformacao();
		return erosao;
	}
}
