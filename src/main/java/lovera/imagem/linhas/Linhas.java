package lovera.imagem.linhas;

import java.awt.image.BufferedImage;

import lovera.comuns.recursos.Imagens;
import lovera.img.factory.FactoryLinha;
import lovera.img.manipulacao.ImgIO;
import lovera.img.modelos.CorrosaoImg;
import lovera.img.uniaomodelos.UniaoImg;

public class Linhas {
	
	public static void main(String[] args) {
//		BufferedImage img = carregarImg_modoIO(Imagens.REDACAO_PNG);
		BufferedImage img = ImgIO.carregarImg_modoMediaTracker(Imagens.REDACAO_PNG);
		CorrosaoImg corrosao = FactoryLinha.factoryCorrosaoImgFromBufferedImage(img);
		UniaoImg uniao = new UniaoImg("redacaoUniaoCorrosao", corrosao, img);
		uniao.executarTransformacao();
//		erosao.gravar();
		uniao.gravar();
//		System.out.println("Fim");
	}
}
