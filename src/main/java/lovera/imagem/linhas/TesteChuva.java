package lovera.imagem.linhas;

import java.awt.image.BufferedImage;

import lovera.comuns.recursos.Imagens;
import lovera.img.contratos.ImgTransformavel;
import lovera.img.manipulacao.ImgIO;
import lovera.img.modelos.ChuvaImg;
import lovera.img.modelos.GaussImg;
import lovera.img.modelos.LaplaceImg;

public class TesteChuva {
	
	public static void main(String[] args) {
		BufferedImage img = ImgIO.carregarImg_modoMediaTracker(Imagens.REDACAO_PNG);
		ImgTransformavel transformacao = new GaussImg(img);
		transformacao.executarTransformacao();
		transformacao = new LaplaceImg((GaussImg) transformacao);
		transformacao.executarTransformacao();
		ChuvaImg chuva = new ChuvaImg((LaplaceImg) transformacao);
		chuva.executarTransformacao();
		chuva.gravar();
	}

}
