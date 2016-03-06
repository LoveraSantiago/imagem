package lovera.imagem.linhas;

import java.awt.image.BufferedImage;

import lovera.coisas.coisas.Estatistica;
import lovera.coisas.coisas.FrequenciaDeCinza;
import lovera.coisas.factorycoisas.FactoryEstatistica;
import lovera.coisas.factorycoisas.FactoryFreqCinza;
import lovera.comuns.recursos.Imagens;
import lovera.img.manipulacao.ImgIO;
import lovera.img.modelos_img.GaussImg;
import lovera.img.modelos_img.LaplaceImg;

public class TesteEstatistica {
	
	public static void main(String[] args) {
		BufferedImage img = ImgIO.carregarImg_modoMediaTracker(Imagens.REDACAO_PNG);
		GaussImg gauss = new GaussImg(img);
		gauss.executarTransformacao();
		LaplaceImg laplace = new LaplaceImg(gauss);
		laplace.executarTransformacao();
		FrequenciaDeCinza freq = FactoryFreqCinza.factory_FrequenciaDeCinza(laplace.getImgTransformada());
		System.out.println(freq.toString());
	}

}
