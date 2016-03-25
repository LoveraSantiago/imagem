package lovera.imagem.linhas;

import java.awt.image.BufferedImage;

import lovera.comuns.recursos.Imagens;
import lovera.img.contratos.UnidorImagens;
import lovera.img.factory.FactoryModelo;
import lovera.img.hough.HoughDosBlocos;
import lovera.img.manipulacao.ImgIO;
import lovera.img.modelos.blocos.AreasParaBlocos;
import lovera.img.modelos.floodfill.FloodFillCCs;
import lovera.img.modelos.img.BinarizacaoImg;
import lovera.img.modelos.uniao.UniaoImgAreas;
import lovera.img.modelos.uniao.UniaoImgLinhas;
import lovera.img.modelos.uniao.UniaoImgPontos;

public class Testes {
	
	//TESTANDO A TRANSFORMADA NO GERAL
	public static void main(String[] args) {
		BufferedImage img = ImgIO.carregarImg_modoMediaTracker(Imagens.REDACAO_PNG);
		BinarizacaoImg binarizacao = FactoryModelo.factoryBinarizacao(img);
		
		FloodFillCCs flood = new FloodFillCCs(binarizacao);	
		flood.executar();
		
		AreasParaBlocos blocos = new AreasParaBlocos(flood, binarizacao);
		blocos.executar();
		
		HoughDosBlocos hough = new HoughDosBlocos(blocos, binarizacao);
		hough.executar();
		
		UnidorImagens uniao = new UniaoImgAreas("redacaoBlocoGauss", blocos, img);
		uniao.executarTransformacao();
		uniao = new UniaoImgLinhas("redacaoBlocoGauss", hough, uniao.getImgTransformada());		
		uniao.executarTransformacao();
		uniao = new UniaoImgPontos("redacaoBlocoGauss", blocos, uniao.getImgTransformada());
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
