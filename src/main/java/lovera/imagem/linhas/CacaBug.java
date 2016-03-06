package lovera.imagem.linhas;

import java.awt.image.BufferedImage;

import lovera.comuns.recursos.Imagens;
import lovera.img.manipulacao.ImgIO;
import lovera.img.modelos.BinarizacaoImg;
import lovera.img.modelos.GaussImg;
import lovera.img.modelos.LaplaceImg;

public class CacaBug {
	
	public static void main(String[] args) {
		BufferedImage img = ImgIO.carregarImg_modoMediaTracker(Imagens.REDACAO_PNG);
		GaussImg gauss = new GaussImg(img);
		gauss.executarTransformacao();
		
		LaplaceImg laplace = new LaplaceImg(gauss);
		laplace.executarTransformacao();
		
		BinarizacaoImg binario = new BinarizacaoImg(laplace);
		binario.executarTransformacao();
		binario.gravar();
	}

}
