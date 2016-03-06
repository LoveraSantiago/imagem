package lovera.img.factory;

import java.awt.image.BufferedImage;

import lovera.img.modelos.Limiar;
import lovera.img.modelos_img.BinarizacaoImg;
import lovera.img.modelos_img.ChuvaImg;
import lovera.img.modelos_img.CinzaImg;
import lovera.img.modelos_img.CorrosaoImg;
import lovera.img.modelos_img.GaussImg;
import lovera.img.modelos_img.LaplaceImg;

public final class FactoryModelo {
	
	public static BinarizacaoImg factoryBinarizacao(BufferedImage img){
		CinzaImg cinza = factoryCinza(img);
		Limiar limiar = new Limiar(cinza);
		
		LaplaceImg laplace = factoryLaplace(img);
		
		BinarizacaoImg binarizacao = new BinarizacaoImg(laplace, limiar);
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
	
	public static GaussImg factoryGauss(BufferedImage img){
		CinzaImg cinza = factoryCinza(img);
		GaussImg gauss = new GaussImg(cinza);
		gauss.executarTransformacao();
		return gauss;
	}
	
	public static LaplaceImg factoryLaplace(BufferedImage img){
		GaussImg gauss = factoryGauss(img);
		LaplaceImg laplace = new LaplaceImg(gauss);
		laplace.executarTransformacao();
		return laplace;
	}
}
