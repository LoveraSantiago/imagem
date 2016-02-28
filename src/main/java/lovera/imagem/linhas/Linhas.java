package lovera.imagem.linhas;

import java.awt.image.BufferedImage;

import lovera.comuns.recursos.Imagens;
import lovera.img.manipulacao.ImgIO;
import lovera.linha.factorylinha.FactoryLinha;
import lovera.linha.modelos.ErosaoImg;
import lovera.linha.uniao.UniaoImgErosao;

public class Linhas {
	
	public static void main(String[] args) {
//		BufferedImage img = carregarImg_modoIO(Imagens.REDACAO_PNG);
		BufferedImage img = ImgIO.carregarImg_modoMediaTracker(Imagens.REDACAO_PNG);
		ErosaoImg erosao = FactoryLinha.factoryErosaoImgFromBufferedImage(img);
		UniaoImgErosao uniao = new UniaoImgErosao(erosao, img);
		uniao.executarTransformacao();
		uniao.gravar();
//		erosao.gravar();
//		System.out.println("Fim");
	}
}
