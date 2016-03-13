package lovera.imagem.linhas;

import java.awt.image.BufferedImage;
import java.util.List;

import org.apache.log4j.net.SyslogAppender;

import lovera.comuns.recursos.Imagens;
import lovera.estatistica.grao.Estatistica;
import lovera.img.factory.FactoryModelo;
import lovera.img.manipulacao.ImgIO;
import lovera.img.modelos.floodfill.FloodFillCCs;
import lovera.img.modelos.img.BinarizacaoImg;
import lovera.img.modelos.img.UniaoImgAreas;
import lovera.img.modelos.img.UniaoImgAreasCor;
import lovera.linha.grao.AlturaSubset;
import lovera.linha.modelos.AlturaDeAreas;
import lovera.linha.modelos.ClassifAltura;

public class Testes {
	
	public static void main(String[] args) {
		BufferedImage img = ImgIO.carregarImg_modoMediaTracker(Imagens.REDACAO_PNG);
		BinarizacaoImg binarizacao = FactoryModelo.factoryBinarizacao(img);
		
		FloodFillCCs flood = new FloodFillCCs(binarizacao);
//		UniaoImgAreas uniao = new UniaoImgAreas("redacaoFloodFill", flood, img);
//		uniao.executarTransformacao();
//		uniao.gravar();
//		uniao.abrir();
		
		AlturaDeAreas alturas = new AlturaDeAreas(flood);
		alturas.gerarEstatistica();
		Estatistica estats = alturas.getEstatistica();		
//		System.out.println(estats);
		ClassifAltura classAltura = new ClassifAltura(flood, estats);
		classAltura.classificarAreas();
		UniaoImgAreasCor uniao = new UniaoImgAreasCor("redacaoAClassif", classAltura, img);
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
