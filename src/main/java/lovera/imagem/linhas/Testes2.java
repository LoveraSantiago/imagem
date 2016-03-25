package lovera.imagem.linhas;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import lovera.comuns.recursos.Imagens;
import lovera.img.contratos.UnidorImagens;
import lovera.img.manipulacao.ImgIO;
import lovera.img.manipulacao.ManipulacaoImg;
import lovera.img.modelos.uniao.UniaoImgPontos;

public class Testes2 {
	

	public static void main(String[] args) {
		BufferedImage img = ImgIO.carregarImg_modoMediaTracker(Imagens.REDACAO_PNG);
		BufferedImage recortado = ManipulacaoImg.recortar(img, new Rectangle(402, 856, 29, 54));
		
		
		List<Point> lista = new ArrayList<>();
		lista.add(new Point(13, 40));
		UnidorImagens uniaoPontos = new UniaoImgPontos("pontoDebugCentro", lista, recortado);
		uniaoPontos.executarTransformacao()
				   .gravar()
				   .abrir();		
	}

}
