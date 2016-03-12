package lovera.imagem.linhas;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.List;

import lovera.comuns.recursos.Imagens;
import lovera.img.debug.DebugImgModelo;
import lovera.img.factory.FactoryModelo;
import lovera.img.manipulacao.ImgIO;
import lovera.img.manipulacao.ManipulacaoImg;
import lovera.img.modelos_img.BinarizacaoImg;
import lovera.img.modelos_img.UniaoImgPontos;

public class TesteColorir {
	
	public static void main(String[] args) {
		List<Point> coordenadas = new ArrayList<>();
		coordenadas.add(new Point(228, 104));
//		coordenadas.add(new Point(228, 105));
		
		BufferedImage img = ImgIO.carregarImg_modoMediaTracker(Imagens.LETRA_J);
		BinarizacaoImg binarizacao = FactoryModelo.factoryBinarizacao(img);
		
		UniaoImgPontos uniao = new UniaoImgPontos("colorir", coordenadas, binarizacao.getImgTransformada());
		uniao.executarTransformacao();
		uniao.gravar();		
		uniao.abrir();
		
		desenhar(binarizacao.getImgTransformada());
	}
	
	public static void desenhar(BufferedImage img){
		BufferedImage clone = ManipulacaoImg.copiarImg(img, BufferedImage.TYPE_INT_RGB);
		WritableRaster wRaster = clone.getRaster();
		wRaster.setSample(228, 104, 1, 0);
		DebugImgModelo.debugarImg(clone, "ponto");
	}

}
