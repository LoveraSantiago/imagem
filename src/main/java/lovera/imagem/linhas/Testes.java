package lovera.imagem.linhas;

import java.awt.image.BufferedImage;

import lovera.comuns.recursos.Imagens;
import lovera.img.factory.FactoryModelo;
import lovera.img.manipulacao.ImgIO;
import lovera.img.modelos_img.ChuvaImg;
import lovera.img.modelos_img.CorrosaoImg;
import lovera.img.modelos_img.UniaoImg;

public class Testes {
	
	public static void main(String[] args) {
		BufferedImage img = ImgIO.carregarImg_modoMediaTracker(Imagens.REDACAO_PNG);
		ChuvaImg chuva = FactoryModelo.factoryChuva(img);
		
		UniaoImg uniao = new UniaoImg("uniaoChuva", chuva, img);
		uniao.executarTransformacao();
		uniao.gravar();
	}
	
//	public static void main(String[] args) {
//		BufferedImage img = ImgIO.carregarImg_modoMediaTracker(Imagens.REDACAO_PNG);
//		CorrosaoImg corrosao = FactoryModelo.factoryCorrosao(img);
//		corrosao.gravar();
//	}

}
