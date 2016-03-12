package lovera.imagem.linhas;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import lovera.comuns.recursos.Imagens;
import lovera.img.factory.FactoryModelo;
import lovera.img.manipulacao.ImgIO;
import lovera.img.modelos_img.BinarizacaoImg;
import lovera.img.modelos_img.UniaoImgPontos;

public class TesteColorir {
	
	public static void main(String[] args) {
		List<Point> coordenadas = new ArrayList<>();
		coordenadas.add(new Point(228, 104));
//		coordenadas.add(new Point(228, 105));
		
		BufferedImage img = ImgIO.carregarImg_modoMediaTracker(Imagens.LETRA_J);
		BinarizacaoImg binarizacao = FactoryModelo.factoryBinarizacao(img);
		
		UniaoImgPontos debug = new UniaoImgPontos("colorir", coordenadas, binarizacao.getImgTransformada());
		debug.executarTransformacao();
		debug.gravar();
		debug.abrir();
	}

}
