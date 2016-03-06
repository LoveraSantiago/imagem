package lovera.imagem.linhas;

import java.awt.image.BufferedImage;

import lovera.comuns.recursos.Imagens;
import lovera.img.manipulacao.ImgIO;
import lovera.img.modelos.Limiar;
import lovera.img.modelos_img.BinarizacaoImg;
import lovera.img.modelos_img.CinzaImg;

public class TesteBinarizacao {
	
	public static void main(String[] args) {
		BufferedImage img = ImgIO.carregarImg_modoMediaTracker(Imagens.REDACAO_PNG);
		CinzaImg cinza = new CinzaImg(img);
		cinza.executarTransformacao();
		Limiar limiar = new Limiar(cinza);		
		BinarizacaoImg binarizacao = new BinarizacaoImg(cinza, limiar);
		binarizacao.executarTransformacao();
		binarizacao.gravar();
	}

}
