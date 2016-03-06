package lovera.imagem.linhas;

import java.awt.image.BufferedImage;

import lovera.comuns.contratos.Gravavel;
import lovera.comuns.recursos.Imagens;
import lovera.img.contratos.ImgTransformavel;
import lovera.img.manipulacao.ImgIO;
import lovera.img.modelos_img.BinarizacaoImg;
import lovera.img.modelos_img.ChuvaImg;
import lovera.img.modelos_img.GaussImg;
import lovera.img.modelos_img.LaplaceImg;

public class TesteChuva {
	
	public static void main(String[] args) {
		BufferedImage img = ImgIO.carregarImg_modoMediaTracker(Imagens.REDACAO_PNG);
		ImgTransformavel transformacao = new GaussImg(img);
		transformacao.executarTransformacao();
		transformacao = new LaplaceImg((GaussImg) transformacao);
		transformacao.executarTransformacao();
		transformacao = new BinarizacaoImg((LaplaceImg) transformacao);
		transformacao.executarTransformacao();
		((Gravavel) transformacao).gravar();
		ChuvaImg chuva = new ChuvaImg((BinarizacaoImg) transformacao);
		chuva.executarTransformacao();
		chuva.gravar();
	}

}
