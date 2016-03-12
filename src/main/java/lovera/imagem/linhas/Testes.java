package lovera.imagem.linhas;

import java.awt.image.BufferedImage;

import lovera.coisas.coisas.FrequenciaDeCinza;
import lovera.coisas.factorycoisas.FactoryFreqCinza;
import lovera.comuns.recursos.Imagens;
import lovera.img.factory.FactoryModelo;
import lovera.img.manipulacao.ImgIO;
import lovera.img.modelos.FloodFillImg;
import lovera.img.modelos_img.BinarizacaoImg;
import lovera.img.modelos_img.ChuvaImg;
import lovera.img.modelos_img.CorrosaoImg;
import lovera.img.modelos_img.LaplaceImg;
import lovera.img.modelos_img.UniaoImgAreas;
import lovera.img.modelos_img.UniaoImgPontos;

public class Testes {
	
	public static void main(String[] args) {
		BufferedImage img = ImgIO.carregarImg_modoMediaTracker(Imagens.LETRA_J);
		BinarizacaoImg binarizacao = FactoryModelo.factoryBinarizacao(img);
		
		FloodFillImg flood = new FloodFillImg(binarizacao);
		UniaoImgAreas uniao = new UniaoImgAreas("floodFillJ", flood, img);
		uniao.executarTransformacao();
		uniao.gravar();
		uniao.abrir();
	}
	
//	public static void main(String[] args) {
//		BufferedImage img = ImgIO.carregarImg_modoMediaTracker(Imagens.REDACAO_PNG);
//		LaplaceImg laplace = FactoryModelo.factoryLaplace(img);
////		FrequenciaDeCinza freqLaplace = FactoryFreqCinza.factory_FrequenciaDeCinzaDerivadas(laplace);
////		System.out.println(freqLaplace);
//		
//		BinarizacaoImg binariza = FactoryModelo.factoryBinarizacao(img);
//		binariza.gravar();
//	}
	
//	public static void main(String[] args) {
//		BufferedImage img = ImgIO.carregarImg_modoMediaTracker(Imagens.REDACAO_PNG);
//		ChuvaImg chuva = FactoryModelo.factoryChuva(img);
//		
//		UniaoImg uniao = new UniaoImg("uniaoChuva", chuva, img);
//		uniao.executarTransformacao();
//		uniao.gravar();
//	}
	
//	public static void main(String[] args) {
//		BufferedImage img = ImgIO.carregarImg_modoMediaTracker(Imagens.REDACAO_PNG);
//		CorrosaoImg corrosao = FactoryModelo.factoryCorrosao(img);
//		
//		UniaoImgPontos uniao = new UniaoImgPontos("uniaoChuva", corrosao, img);
//		uniao.executarTransformacao();
//		uniao.gravar();
//	}

}
